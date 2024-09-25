package ai.remi.boot.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ai.remi.boot.domain.entity.AccessToken;
import ai.remi.boot.domain.entity.RefreshToken;

/**
 * @author DianJiu
 * @email dianjiuxyz@gmail.com
 * @desc 刷新令牌表(RefreshToken)服务定义层
 */
public interface RefreshTokenService extends IService<RefreshToken> {

    void create(AccessToken accessToken);

    void set(String token, RefreshToken refreshToken);

    RefreshToken get(String token) throws Exception;

    void remove(String token);

    boolean verifyExpired(String token) throws Exception;
}
