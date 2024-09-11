package ai.remi.comm.core.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author dianjiu【公众号 点九开源】
 * @email dianjiuxyz@gmail.com
 * @desc OAuth2异常常量
 */
public class OAuth2Constants {

    /**
     * 认证异常
     */
    @Getter
    @AllArgsConstructor
    public enum Error {
        USERNAME_NOT_FOUND("username_not_found", "用户名未找到", ""),
        BAD_CREDENTIALS("bad_credentials", "错误凭证", "AbstractUserDetailsAuthenticationProvider.badCredentials ： Bad credentials"),
        USER_LOCKED("user_locked", "用户被锁", "AbstractUserDetailsAuthenticationProvider.locked : User account is locked"),
        USER_DISABLE("user_disable", "用户禁用", "AbstractUserDetailsAuthenticationProvider.disabled : User is disabled"),
        USER_EXPIRED("user_expired", "用户过期", "AbstractUserDetailsAuthenticationProvider.expired : User account has expired"),
        CREDENTIALS_EXPIRED("credentials_expired", "证书过期", "AbstractUserDetailsAuthenticationProvider.credentialsExpired : User credentials have expired"),
        INVALID_SCOPE("invalid_scope", "无效作用域", "AbstractAccessDecisionManager.accessDenied : invalid_scope"),
        SCOPE_IS_EMPTY("scope_is_empty", "scope 为空异常", ""),
        TOKEN_MISSING("token_missing", "令牌不存在", ""),
        UN_KNOW_LOGIN_ERROR("un_know_login_error", "未知的登录异常", ""),
        INVALID_BEARER_TOKEN("invalid_bearer_token", "不合法的Token", "");

        private final String code;
        private final String info;
        private final String msg;

    }
}