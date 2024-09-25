package ai.remi.boot.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ai.remi.boot.domain.dto.post.OauthCodePostDTO;
import ai.remi.boot.domain.entity.OauthCode;

/**
 * @author DianJiu
 * @email dianjiuxyz@gmail.com
 * @desc 授权码表(OauthCode)服务定义层
 */
public interface OauthCodeService extends IService<OauthCode> {

    OauthCode create(OauthCodePostDTO oauthCodePost);

    void set(String code, OauthCode oauthCode);

    OauthCode get(String code) throws Exception;

    void remove(String code);

    boolean verifyExpired(String code) throws Exception;
}
