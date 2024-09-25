package ai.remi.boot.web.controller;

import ai.remi.comm.core.result.ResultBean;
import ai.remi.comm.exception.util.MessageUtils;
import ai.remi.comm.redis.service.RedisService;
import ai.remi.comm.util.asserts.AssertUtils;
import ai.remi.boot.domain.constant.Oauth2Constant;
import ai.remi.boot.domain.constant.SsoConstant;
import ai.remi.boot.domain.converter.AccessTokenConverter;
import ai.remi.boot.domain.entity.AccessToken;
import ai.remi.boot.domain.entity.OauthCode;
import ai.remi.boot.domain.enums.Oauth2TypeEnum;
import ai.remi.boot.domain.vo.AccessTokenVO;
import ai.remi.boot.server.service.AccessTokenService;
import ai.remi.boot.server.service.OauthCodeService;
import ai.remi.boot.server.service.RefreshTokenService;
import ai.remi.boot.server.util.AppUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author LiMengwei
 * @email limengwei@dianjiu.cc
 * @desc 授权码表(OauthCode)控制层
 */
@Validated
@Controller
@Tag(name = "授权令牌")
@RequestMapping(SsoConstant.AUTH_PATH)
public class Oauth2Controller {

    @Resource
    private RedisService redisService;

    @Resource
    private OauthCodeService authCodeService;

    @Resource
    private AccessTokenService accessTokenService;

    @Resource
    private RefreshTokenService refreshTokenService;

    /**
     * 通过authCode获取accessToken
     *
     * @return
     */
    @Validated
    @ResponseBody
    @GetMapping("/accessToken")
    @Operation(summary = "通过authCode获取鉴权令牌")
    @Parameters({
            @Parameter(name = Oauth2Constant.AUTH_TYPE, description = "授权类型", required = true),
            @Parameter(name = Oauth2Constant.AUTH_CODE, description = "授权码", required = true),
            @Parameter(name = Oauth2Constant.APP_KEY, description = "应用的appKey", required = true),
            @Parameter(name = Oauth2Constant.APP_SECRET, description = "应用的appSecret", required = true)
    })
    //@LogRecord(content = "通过authCode获取鉴权令牌", businessType = BusinessType.GRANT)
    public ResultBean<AccessTokenVO> getTokenByAuthCode(@RequestParam(value = Oauth2Constant.AUTH_TYPE) String authType,
                                                           @RequestParam(value = Oauth2Constant.AUTH_CODE) String authCode,
                                                           @RequestParam(value = Oauth2Constant.APP_KEY) String appKey,
                                                           @RequestParam(value = Oauth2Constant.APP_SECRET) String appSecret) throws Exception {
        // 校验授权码方式
        AssertUtils.isTrue(Oauth2TypeEnum.AUTHORIZATION_CODE.getValue().equals(authType), MessageUtils.getMessage("only.support.code.error"));

        // 校验应用
        Boolean validate = AppUtils.validate(appKey, appSecret);
        AssertUtils.isTrue(validate, MessageUtils.getMessage("check.app.secret.error"));

        // 校验授权码
        OauthCode oauthCode = authCodeService.get(authCode);
        // code有误或已过期
        AssertUtils.isNotNull(oauthCode, MessageUtils.getMessage("check.auth.code.error"));
        // code 有效则兑换token
        authCodeService.remove(authCode);

        // 校验凭证
        AssertUtils.isNotNull(redisService.get(oauthCode.getTicket()), "check.ticket.error");

        // 保存鉴权Token
        AccessToken accessToken = accessTokenService.create(appKey, oauthCode);

        // 保存刷新Token
        refreshTokenService.create(accessToken);

        // 返回token
        return ResultBean.success(AccessTokenConverter.INSTANT.entityToVO(accessToken));
    }

    /**
     * 通过authCode获取accessToken
     *
     * @return
     */
    @Validated
    @ResponseBody
    @GetMapping("/refreshToken")
    @Operation(summary = "通过refreshToken获取鉴权令牌")
    @Parameters({
            @Parameter(name = Oauth2Constant.APP_KEY, description = "应用的appKey", required = true),
            @Parameter(name = Oauth2Constant.APP_SECRET, description = "应用的appSecret", required = true),
            @Parameter(name = Oauth2Constant.REFRESH_TOKEN, description = "刷新token", required = true)
    })
    //@LogRecord(content = "通过refreshToken获取鉴权令牌", businessType = BusinessType.GRANT)
    public ResultBean<AccessTokenVO> getTokenByRefreshToken(@RequestParam(value = Oauth2Constant.APP_KEY) String appKey,
                                                            @RequestParam(value = Oauth2Constant.APP_SECRET) String appSecret,
                                                            @RequestParam(value = Oauth2Constant.REFRESH_TOKEN) String refreshToken) {
        // 校验应用
        //if (!appManager.exists(appId)) {
        //    return Result.createError("非法应用");
        //}

        // 校验刷新Token时效
        //TokenContent atContent = tokenManager.get(refreshToken);
        //if (atContent == null) {
        //    return Result.createError("refreshToken有误或已过期");
        //}

        // 删除原有token
        //accessTokenManager.remove(refreshToken);

        // 创建新token
        //TokenContent tc = tokenManager.create(atContent);

        // 刷新服务端凭证时效
        //ticketGrantingTicketManager.refresh(tc.getTgt());

        // 返回新token
        //return Result.createSuccess(new Token(tc.getAccessToken(), tokenManager.getAccessTokenTimeout(), tc.getRefreshToken(),
        //        tokenManager.getRefreshTokenTimeout(), tc.getUserinfo()));
        return ResultBean.success();
    }

    /**
     * 校验鉴权令牌
     *
     * @return
     */
    @Validated
    @GetMapping("/checkToken")
    @Operation(summary = "校验鉴权令牌")
    //@LogRecord(content = "校验鉴权令牌的有效性", businessType = BusinessType.GRANT)
    public ResultBean<Boolean> checkToken(@RequestParam(value = Oauth2Constant.APP_KEY) String appKey,
                                          @RequestParam(value = Oauth2Constant.ACCESS_TOKEN) String accessToken) {

        return ResultBean.success();
    }
}

