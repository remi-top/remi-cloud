package ai.remi.boot.domain.constant;

/**
 * 单点登录基础常量
 */
public class SsoConstant {
    /**
     * 服务端登录路径
     */
    public static final String LOGIN_PATH = "/sso/login";

    /**
     * 服务端退出路径
     */
    public static final String LOGOUT_PATH = "/sso/logout";

    /**
     * 认证路径
     */
    public static final String AUTH_PATH = "/sso/oauth2";

    /**
     * 服务端回调客户端地址参数名称
     */
    public static final String REDIRECT_URL = "redirectUrl";

    /**
     * 服务端单点退出回调客户端退出参数名称
     */
    public static final String LOGOUT_PARAMETER_NAME = "logoutRequest";
}
