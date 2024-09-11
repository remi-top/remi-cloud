package ai.remi.comm.util.auth;


import ai.remi.comm.core.enums.IdentityTypeEnum;

/**
 * @author dianjiu【公众号 点九开源】
 * @email dianjiuxyz@gmail.com
 * @desc AuthInfo
 */
public interface AuthInfo {

    /**
     * 用户系统语言
     */
    String getUserLanguage();

    /**
     * 用户唯一标识
     */
    String getUniqueId();

    /**
     * 部门租户号
     */
    String getDeptTenantId();

    /**
     * 公司租户号
     */
    String getCompanyTenantId();

    /**
     * 身份类型
     */
    IdentityTypeEnum getIdentityTypeEnum();

    /**
     * 服务类型
     */
    String getServiceTypeCode();

    /**
     * 鉴权Token
     * @return
     */
    String getAccessToken();
}
