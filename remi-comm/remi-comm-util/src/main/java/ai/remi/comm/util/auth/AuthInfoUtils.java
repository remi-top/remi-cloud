package ai.remi.comm.util.auth;


import ai.remi.comm.core.enums.IdentityTypeEnum;
import ai.remi.comm.core.enums.PermissionScopeEnum;

import java.util.Set;

/**
 * @author dianjiu【公众号 点九开源】
 * @email dianjiuxyz@gmail.com
 * @desc AuthInfoUtils
 */
public class AuthInfoUtils {

    /**
     * 获取认证信息
     */
    public static AuthInfo getAuthInfo() {
        return RequestHolder.getAuthInfo();
    }

    /**
     * 获取用户语言
     */
    public static String getUserLanguage() {
        return RequestHolder.getAuthInfo().getUserLanguage();
    }

    /**
     * 鉴权Token
     * @return
     */
    public static String getAccessToken() {
        return RequestHolder.getCompanyAuthInfo().getAccessToken();
    }

    /**
     * 获取用户唯一id 对应 {@link IdentityTypeEnum}
     */
    public static String getUniqueId() {
        return RequestHolder.getAuthInfo().getUniqueId();
    }

    /**
     * 获取部门租户号
     */
    public static String getDeptTenantId() {
        return RequestHolder.getAuthInfo().getDeptTenantId();
    }

    /**
     * 获取公司租户号
     */
    public static String getCompanyTenantId() {
        return RequestHolder.getAuthInfo().getCompanyTenantId();
    }


    /**
     * 获取用户类型  瑞米官方账户、集团公司账户、访客体验用户
     */
    public static IdentityTypeEnum getIdentityTypeEnum() {
        return RequestHolder.getAuthInfo().getIdentityTypeEnum();
    }

    /**
     * 获取当前服务类型，主要是给feign调用内部服务时使用
     */
    public static String getServiceTypeCode() {
        return RequestHolder.getAuthInfo().getServiceTypeCode();
    }

    /**
     * 获取权限范围
     */
    public static PermissionScopeEnum getPermissionScopeEnum() {
        return RequestHolder.getCompanyAuthInfo().getPermissionScope();
    }

    /**
     * 当权限范围为 {@link PermissionScopeEnum } 时，
     * 为当前请求可操作、查询的公司id集合
     */
    public static Set<String> getHasPermissionCompanyIds() {
        return RequestHolder.getCompanyAuthInfo().getHasPermissionCompanyIds();
    }


    /**
     * 获取瑞米信息
     *
     * @return 瑞米信息
     * @createTime 2022/1/6 19:35
     */
    //public static CustomerAuthInfo getCustomerAuthInfo() {
    //    return RequestHolder.getCustomerAuthInfo();
    //}

    /**
     * 获取公司信息
     *
     * @return 客户信息
     * @createTime 2022/1/6 19:35
     */
    public static CompanyAuthInfo getCompanyAuthInfo() {
        return RequestHolder.getCompanyAuthInfo();
    }

    /**
     * 获取信息
     *
     * @return 访客信息
     * @createTime 2022/1/6 19:35
     */
    //public static CompanyAuthInfo getCompanyAuthInfo() {
    //    return RequestHolder.getCompanyAuthInfo();
    //}

}
