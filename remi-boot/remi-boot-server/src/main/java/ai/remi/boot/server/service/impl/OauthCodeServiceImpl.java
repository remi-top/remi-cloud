package ai.remi.boot.server.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ai.remi.comm.redis.service.RedisService;
import ai.remi.comm.util.collection.CollectionUtils;
import ai.remi.comm.util.date.LocalDateTimeUtils;
import ai.remi.comm.util.object.ObjectUtils;
import ai.remi.boot.domain.converter.OauthCodeConverter;
import ai.remi.boot.domain.dto.post.OauthCodePostDTO;
import ai.remi.boot.domain.entity.OauthCode;
import ai.remi.boot.domain.enums.RedisKeyEnum;
import ai.remi.boot.infra.mapper.OauthCodeMapper;
import ai.remi.boot.server.service.OauthCodeService;
import ai.remi.boot.server.util.AuthUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Map;

/**
 * @author DianJiu
 * @email dianjiuxyz@gmail.com
 * @desc 授权码表(OauthCode)服务实现层
 */
@Service("oauthCodeService")
public class OauthCodeServiceImpl extends ServiceImpl<OauthCodeMapper, OauthCode> implements OauthCodeService {

    @Resource
    private OauthCodeMapper oauthCodeMapper;

    @Resource
    private RedisService redisService;

    @Override
    public OauthCode create(OauthCodePostDTO oauthCodePost) {
        OauthCode oauthCode = OauthCodeConverter.INSTANT.postDtoToEntity(oauthCodePost);
        // 生成授权码
        String code = "AC-" + AuthUtils.getAuthCode(oauthCodePost.getAppKey(), oauthCodePost.getState());
        oauthCode.setAuthCode(code);
        // 设置为30分钟后过期
        oauthCode.setExpiresTime(LocalDateTimeUtils.plus(LocalDateTime.now(), 30, ChronoUnit.MINUTES));
        // 同时保存到数据库和缓存中
        set(code, oauthCode);
        return oauthCode;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, transactionManager = "transactionManager")
    public void set(String code, OauthCode oauthCode) {
        oauthCodeMapper.insert(oauthCode);
        redisService.hmset(RedisKeyEnum.AUTH_CODE.getKey(code), ObjectUtils.objectToMap(oauthCode), 30 * 60);
    }

    @Override
    public OauthCode get(String code) throws Exception {
        Map<Object, Object> hmget = redisService.hmget(RedisKeyEnum.AUTH_CODE.getKey(code));
        if(CollectionUtils.isNotEmpty(hmget)){
            return ObjectUtils.mapToObject(hmget, OauthCode.class);
        }
        return oauthCodeMapper.selectOne(Wrappers.<OauthCode>lambdaQuery().eq(OauthCode::getAuthCode, code).last("limit 1"));
    }

    @Override
    @Transactional(rollbackFor = Exception.class, transactionManager = "transactionManager")
    public void remove(String code) {
        oauthCodeMapper.delete(Wrappers.<OauthCode>lambdaQuery().eq(OauthCode::getAuthCode, code));
        redisService.del(RedisKeyEnum.AUTH_CODE.getKey(code));
    }

    @Override
    public boolean verifyExpired(String code) throws Exception {
        OauthCode oauthCode = get(code);
        return LocalDateTimeUtils.isAfter(LocalDateTime.now(), oauthCode.getExpiresTime());
    }

}

