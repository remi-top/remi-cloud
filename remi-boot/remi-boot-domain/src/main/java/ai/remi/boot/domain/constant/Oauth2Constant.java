package ai.remi.boot.domain.constant;

/**
 * Oauth2 单点登录常量
 * Oauth2方式-单点登录常量
 */
public class Oauth2Constant {
    /**
     * 授权方式
     */
    public static final String AUTH_TYPE = "authType";

    /**
     * 应用唯一标识
     */
    public static final String APP_KEY = "appKey";

    /**
     * 应用密钥
     */
    public static final String APP_SECRET = "appSecret";

    /**
     * 刷新token
     */
    public static final String REFRESH_TOKEN = "refreshToken";

    /**
     * 刷新token
     */
    public static final String ACCESS_TOKEN = "accessToken";

    /**
     * 授权码（授权码模式）
     */
    public static final String AUTH_CODE = "authCode";

    /**
     * 登录前的客户端地址
     */
    public static final String REDIRECT_URL = "redirectUrl";

    /**
     * client端的状态值
     */
    public static final String CLIENT_STATE = "state";

        /**
     * client端的状态值
     */
    public static final String CLIENT_TICKET = "ticket";

    /**
     * 获取authCode地址
     */
    public static final String AUTH_CODE_PATH = SsoConstant.AUTH_PATH + "/authCode";

    /**
     * 获取accessToken地址
     */
    public static final String ACCESS_TOKEN_PATH = SsoConstant.AUTH_PATH + "/accessToken";

    /**
     * 刷新refreshToken地址
     */
    public static final String REFRESH_TOKEN_PATH = SsoConstant.AUTH_PATH + "/refreshToken";

    /**
     * 校验accessToken地址
     */
    public static final String CHECK_TOKEN_PATH = SsoConstant.AUTH_PATH + "/checkToken";
}
