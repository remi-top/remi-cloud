package ai.remi.boot.server.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ai.remi.comm.redis.service.RedisService;
import ai.remi.comm.util.collection.CollectionUtils;
import ai.remi.comm.util.date.LocalDateTimeUtils;
import ai.remi.comm.util.id.UUIDUtils;
import ai.remi.comm.util.object.ObjectUtils;
import ai.remi.boot.domain.entity.AccessToken;
import ai.remi.boot.domain.entity.OauthCode;
import ai.remi.boot.domain.enums.RedisKeyEnum;
import ai.remi.boot.domain.vo.UserVO;
import ai.remi.boot.infra.mapper.AccessTokenMapper;
import ai.remi.boot.server.service.AccessTokenService;
import ai.remi.boot.server.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Map;

/**
 * @author DianJiu
 * @email dianjiuxyz@gmail.com
 * @desc 鉴权令牌表(AccessToken)服务实现层
 */
@Service("accessTokenService")
public class AccessTokenServiceImpl extends ServiceImpl<AccessTokenMapper, AccessToken> implements AccessTokenService {
    @Resource
    private AccessTokenMapper accessTokenMapper;
    @Resource
    private RedisService redisService;
    @Resource
    private UserService userService;

    @Override
    public AccessToken create(String appKey, OauthCode oauthCode) throws Exception {
        // 生成Token信息
        String aToken = "AT-" + UUIDUtils.simpleUuid();
        String rToken = "RT-" + UUIDUtils.simpleUuid();
        // 保存Token信息
        LocalDateTime now = LocalDateTime.now();
        AccessToken accessToken = AccessToken.builder().accessToken(aToken).refreshToken(rToken).appKey(appKey).expiresTime(LocalDateTimeUtils.plus(now, 3, ChronoUnit.HOURS)).build();
        set(aToken, accessToken);
        // 刷新Ticket（服务端凭证）
        redisService.sSet(RedisKeyEnum.TICKET.getKey(oauthCode.getTicket()), aToken);
        redisService.sSet(RedisKeyEnum.TICKET.getKey(oauthCode.getTicket()), rToken);
        redisService.expire(RedisKeyEnum.TICKET.getKey(oauthCode.getTicket()), 3 * 60 * 60);
        // 缓存用户信息
        UserVO userVO = userService.getLoginById(oauthCode.getUserId());
        redisService.hmset(RedisKeyEnum.USER_INFO.getKey(aToken), ObjectUtils.objectToMap(userVO), 12 * 60 * 60);
        return accessToken;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, transactionManager = "transactionManager")
    public void set(String token, AccessToken accessToken) {
        accessTokenMapper.insert(accessToken);
        redisService.hmset(RedisKeyEnum.ACCESS_TOKEN.getKey(token), ObjectUtils.objectToMap(accessToken), 6 * 60 * 60);
    }

    @Override
    public AccessToken get(String token) throws Exception {
        Map<Object, Object> hmget = redisService.hmget(RedisKeyEnum.ACCESS_TOKEN.getKey(token));
        if (CollectionUtils.isNotEmpty(hmget)) {
            return ObjectUtils.mapToObject(hmget, AccessToken.class);
        }
        return accessTokenMapper.selectOne(Wrappers.<AccessToken>lambdaQuery().eq(AccessToken::getAccessToken, token).last("limit 1"));
    }

    @Override
    @Transactional(rollbackFor = Exception.class, transactionManager = "transactionManager")
    public void remove(String token) {
        accessTokenMapper.delete(Wrappers.<AccessToken>lambdaQuery().eq(AccessToken::getAccessToken, token));
        redisService.del(RedisKeyEnum.ACCESS_TOKEN.getKey(token));
        redisService.del(RedisKeyEnum.USER_INFO.getKey(token));
    }

    @Override
    public boolean verifyExpired(String token) throws Exception {
        AccessToken accessToken = get(token);
        return LocalDateTimeUtils.isAfter(LocalDateTime.now(), accessToken.getExpiresTime());
    }
}

