package ai.remi.comm.web.auth;

import ai.remi.comm.core.enums.ServiceTypeEnum;
import ai.remi.comm.web.excel.SpringContextUtil;

import java.util.Map;

/**
 * @author dianjiu【公众号 点九开源】
 * @email dianjiuxyz@gmail.com
 * @desc AuthHandlerFactory
 */
public class AuthHandlerFactory {

    public static AuthHandler getAuthHandler(ServiceTypeEnum serviceTypeEnum) {
        //Map<String, AuthHandler> authHandlerMap = SpringUtil.getBeansOfType(AuthHandler.class);
        Map<String, AuthHandler> authHandlerMap = SpringContextUtil.getApplicationContext().getBeansOfType(AuthHandler.class);
        //AssertUtils.check(CollectionUtil.isNotEmpty(authHandlerMap), "AuthHandler 服务未注册");
        switch (serviceTypeEnum) {
            case WEB_SERVICE:
                return authHandlerMap.get("webAuthHandler");
            case APP_SERVICE:
                return authHandlerMap.get("appAuthHandler");
            default:
                return authHandlerMap.get("defaultAuthHandler");
        }
    }

}
