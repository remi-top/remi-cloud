package ai.remi.boot.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ai.remi.boot.domain.entity.AccessToken;
import ai.remi.boot.domain.entity.OauthCode;

/**
 * @author DianJiu
 * @email dianjiuxyz@gmail.com
 * @desc 鉴权令牌表(AccessToken)服务定义层
 */
public interface AccessTokenService extends IService<AccessToken> {

    AccessToken create(String appKey, OauthCode oauthCode) throws Exception;

    void set(String token, AccessToken accessToken);

    AccessToken get(String token) throws Exception;

    void remove(String token);

    boolean verifyExpired(String token) throws Exception;
}
