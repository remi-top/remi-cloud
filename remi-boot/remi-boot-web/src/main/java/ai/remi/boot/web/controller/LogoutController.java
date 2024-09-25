package ai.remi.boot.web.controller;

import ai.remi.comm.core.result.ResultBean;
import ai.remi.comm.redis.service.RedisService;
import ai.remi.comm.util.object.ObjectUtils;
import ai.remi.boot.domain.constant.Oauth2Constant;
import ai.remi.boot.domain.constant.SsoConstant;
import ai.remi.boot.domain.enums.RedisKeyEnum;
import ai.remi.boot.server.service.AccessTokenService;
import ai.remi.boot.server.service.RefreshTokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Set;

/**
 * 单点退出
 */
@RestController
@Tag(name = "登出接口")
@RequestMapping(SsoConstant.LOGOUT_PATH)
public class LogoutController {

    @Resource
    private RedisService redisService;

    @Resource
    private AccessTokenService accessTokenService;
    @Resource
    private RefreshTokenService refreshTokenService;

    /**
     * 退出
     *
     * @param redirectUrl
     * @return
     */
    @Operation(summary = "根据Ticket登出")
    @RequestMapping(method = RequestMethod.GET)
    //@LogRecord(content = "通过Ticket登出系统", businessType = BusinessType.LOGOUT)
    public ResultBean<String> logout(
            @RequestParam(value = SsoConstant.REDIRECT_URL) String redirectUrl,
            @RequestParam(value = Oauth2Constant.CLIENT_TICKET) String ticket,
            @RequestParam(value = Oauth2Constant.CLIENT_STATE) String state) {
        // 删除所有的鉴权token
        Set<Object> tokens = redisService.sGet(RedisKeyEnum.TICKET.getKey(ticket));
        for (Object token : tokens) {
            if (ObjectUtils.isNotNull(token) && String.valueOf(token).startsWith("TK-")) {
                continue;
            }
            if (ObjectUtils.isNotNull(token) && String.valueOf(token).startsWith("AT-")) {
                accessTokenService.remove(String.valueOf(token));
            }
            if (ObjectUtils.isNotNull(token) && String.valueOf(token).startsWith("RT-")) {
                refreshTokenService.remove(String.valueOf(token));
            }
        }
        //删除登录凭证
        redisService.del(RedisKeyEnum.TICKET.getKey(ticket));
        return ResultBean.success("登出成功！");
    }
}