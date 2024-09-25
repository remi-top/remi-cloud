package ai.remi.boot.web.controller;


import ai.remi.comm.core.result.ResultBean;
import ai.remi.comm.exception.util.MessageUtils;
import ai.remi.comm.redis.service.RedisService;
import ai.remi.comm.util.asserts.AssertUtils;
import ai.remi.comm.util.bean.BeanCopyUtils;
import ai.remi.comm.util.id.UUIDUtils;
import ai.remi.comm.util.object.ObjectUtils;
import ai.remi.comm.util.string.StringUtils;
import ai.remi.boot.domain.constant.Oauth2Constant;
import ai.remi.boot.domain.constant.SsoConstant;
import ai.remi.boot.domain.converter.AccessTokenConverter;
import ai.remi.boot.domain.converter.OauthCodeConverter;
import ai.remi.boot.domain.dto.post.LoginPostDTO;
import ai.remi.boot.domain.dto.post.OauthCodePostDTO;
import ai.remi.boot.domain.entity.AccessToken;
import ai.remi.boot.domain.entity.OauthCode;
import ai.remi.boot.domain.entity.User;
import ai.remi.boot.domain.enums.RedisKeyEnum;
import ai.remi.boot.domain.vo.AccessTokenVO;
import ai.remi.boot.domain.vo.OauthCodeVO;
import ai.remi.boot.server.service.AccessTokenService;
import ai.remi.boot.server.service.OauthCodeService;
import ai.remi.boot.server.service.RefreshTokenService;
import ai.remi.boot.server.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;

/**
 * 单点登录管理
 */
@RestController
@Tag(name = "登录接口")
@RequestMapping(SsoConstant.LOGIN_PATH)
public class LoginController {

    @Resource
    private RedisService redisService;

    @Resource
    private OauthCodeService codeService;

    @Resource
    private AccessTokenService accessTokenService;

    @Resource
    private RefreshTokenService refreshTokenService;

    @Resource
    private UserService userService;

    /**
     * 登录页
     *
     * @param redirectUrl
     * @param appKey
     * @param state
     * @return
     */
    @GetMapping("/ticket")
    @Operation(summary = "根据Ticket登录")
    //@LogRecord(content = "通过Ticket登录系统", businessType = BusinessType.LOGIN)
    public ResultBean<OauthCodeVO> ticketLogin(
            @RequestParam(value = SsoConstant.REDIRECT_URL) String redirectUrl,
            @RequestParam(value = Oauth2Constant.APP_KEY) String appKey,
            @RequestParam(value = Oauth2Constant.CLIENT_TICKET) String ticket,
            @RequestParam(value = Oauth2Constant.CLIENT_STATE) String state) throws Exception {
        // 校验凭证
        AssertUtils.isNotNull(redisService.sGet(RedisKeyEnum.TICKET.getKey(ticket)), "check.ticket.error");
        // 已存在登录凭证，则直接生成授权码
        OauthCodePostDTO oauthCodePost = OauthCodePostDTO.builder().appKey(appKey).redirectUrl(redirectUrl).state(state).build();
        return ResultBean.success(generateCodeAndRedirect(ticket, oauthCodePost));
    }

    /**
     * 登录提交
     *
     * @param redirectUrl
     * @param appKey
     * @param loginPostDTO
     * @return
     * @throws UnsupportedEncodingException
     */
    @PostMapping("/other")
    @Operation(summary = "根据账号密码登录")
    //@LogRecord(content = "三方系统根据账号密码登录", businessType = BusinessType.LOGIN)
    public ResultBean<OauthCodeVO> otherLogin(
            @RequestParam(value = SsoConstant.REDIRECT_URL) String redirectUrl,
            @RequestParam(value = Oauth2Constant.APP_KEY) String appKey,
            @RequestParam(value = Oauth2Constant.CLIENT_STATE) String state,
            LoginPostDTO loginPostDTO) throws Exception {

        // 校验账号、密码
        User user = userService.login(loginPostDTO.getLoginName(), loginPostDTO.getPassword());
        AssertUtils.isTrue(!ObjectUtils.isEmpty(user), MessageUtils.getMessage("check.login.error"));

        // 校验通过，则直接生成授权码
        OauthCodePostDTO oauthCodePost = OauthCodePostDTO.builder().userId(user.getId()).userCode(user.getUserCode()).appKey(appKey).redirectUrl(redirectUrl).state(state).build();
        return ResultBean.success(generateCodeAndRedirect("", oauthCodePost));
    }

    /**
     * 登录提交
     *
     * @param redirectUrl
     * @param appKey
     * @param loginPostDTO
     * @return
     * @throws UnsupportedEncodingException
     */
    @PostMapping("/local")
    @Operation(summary = "根据账号密码登录")
    //@LogRecord(content = "本地系统根据账号密码登录", businessType = BusinessType.LOGIN)
    public ResultBean<AccessTokenVO> localLogin(
            @RequestParam(value = SsoConstant.REDIRECT_URL) String redirectUrl,
            @RequestParam(value = Oauth2Constant.APP_KEY) String appKey,
            @RequestParam(value = Oauth2Constant.CLIENT_STATE) String state,
            LoginPostDTO loginPostDTO) throws Exception {

        // 校验账号、密码
        User user = userService.login(loginPostDTO.getLoginName(), loginPostDTO.getPassword());
        AssertUtils.isTrue(!ObjectUtils.isEmpty(user), MessageUtils.getMessage("check.login.error"));

        // 校验通过，则直接生成授权码
        OauthCodePostDTO oauthCodePost = OauthCodePostDTO.builder().userId(user.getId()).userCode(user.getUserCode()).appKey(appKey).redirectUrl(redirectUrl).state(state).build();
        OauthCodeVO oauthCodeVO = generateCodeAndRedirect("", oauthCodePost);

        // 保存鉴权Token
        AccessToken accessToken = accessTokenService.create(appKey, BeanCopyUtils.copyPropertiesThird(oauthCodeVO,OauthCode.class));

        // 保存刷新Token
        refreshTokenService.create(accessToken);

        // code 有效则兑换token
        codeService.remove(oauthCodeVO.getAuthCode());

        // 返回token
        AccessTokenVO accessTokenVO = AccessTokenConverter.INSTANT.entityToVO(accessToken);
        accessTokenVO.setTicket(oauthCodeVO.getTicket());
        return ResultBean.success(accessTokenVO);
    }

    /**
     * 创建授权码，跳转到redirectUri
     *
     * @param oauthCodePost
     * @return
     * @throws UnsupportedEncodingException
     */
    private OauthCodeVO generateCodeAndRedirect(String ticket, OauthCodePostDTO oauthCodePost) {
        // 生成登录凭证
        if (StringUtils.isBlank(ticket)) {
            ticket = "TK-" + UUIDUtils.simpleUuid();
            redisService.sSet(RedisKeyEnum.TICKET.getKey(ticket), ticket);
            redisService.expire(RedisKeyEnum.TICKET.getKey(ticket), 3 * 60 * 60);
        }
        // 创建授权码
        oauthCodePost.setTicket(ticket);
        OauthCode oauthCode = codeService.create(oauthCodePost);
        OauthCodeVO oauthCodeVO = OauthCodeConverter.INSTANT.entityToVO(oauthCode);
        oauthCodeVO.setTicket(ticket);
        return oauthCodeVO;
    }
}
