package ai.remi.boot.server.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ai.remi.comm.redis.service.RedisService;
import ai.remi.comm.util.collection.CollectionUtils;
import ai.remi.comm.util.date.LocalDateTimeUtils;
import ai.remi.comm.util.object.ObjectUtils;
import ai.remi.boot.domain.entity.AccessToken;
import ai.remi.boot.domain.entity.RefreshToken;
import ai.remi.boot.domain.enums.RedisKeyEnum;
import ai.remi.boot.infra.mapper.RefreshTokenMapper;
import ai.remi.boot.server.service.RefreshTokenService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * @author DianJiu
 * @email dianjiuxyz@gmail.com
 * @desc 刷新令牌表(RefreshToken)服务实现层
 */
@Service("refreshTokenService")
public class RefreshTokenServiceImpl extends ServiceImpl<RefreshTokenMapper, RefreshToken> implements RefreshTokenService {

    @Resource
    private RefreshTokenMapper refreshTokenMapper;

    @Resource
    private RedisService redisService;

    @Override
    public void create(AccessToken accessToken) {
        RefreshToken refreshToken = RefreshToken.builder().accessToken(accessToken.getAccessToken()).refreshToken(accessToken.getRefreshToken()).used(0).expiresTime(accessToken.getExpiresTime()).build();
        set(accessToken.getRefreshToken(), refreshToken);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, transactionManager = "transactionManager")
    public void set(String token, RefreshToken refreshToken) {
        refreshTokenMapper.insert(refreshToken);
        redisService.hmset(RedisKeyEnum.REFRESH_TOKEN.getKey(token), ObjectUtils.objectToMap(refreshToken), 6 * 60 * 60);
    }

    @Override
    public RefreshToken get(String token) throws Exception {
        Map<Object, Object> hmget = redisService.hmget(RedisKeyEnum.REFRESH_TOKEN.getKey(token));
        if(CollectionUtils.isNotEmpty(hmget)) {
            return ObjectUtils.mapToObject(hmget, RefreshToken.class);
        }
        return refreshTokenMapper.selectOne(Wrappers.<RefreshToken>lambdaQuery().eq(RefreshToken::getRefreshToken, token).last("limit 1"));
    }

    @Override
    @Transactional(rollbackFor = Exception.class, transactionManager = "transactionManager")
    public void remove(String token) {
        refreshTokenMapper.delete(Wrappers.<RefreshToken>lambdaQuery().eq(RefreshToken::getRefreshToken, token));
        redisService.del(RedisKeyEnum.REFRESH_TOKEN.getKey(token));
    }

    @Override
    public boolean verifyExpired(String token) throws Exception {
        RefreshToken refreshToken = get(token);
        return LocalDateTimeUtils.isAfter(LocalDateTime.now(), refreshToken.getExpiresTime());
    }
}

