package ai.remi.comm.util.auth;

import ai.remi.comm.core.enums.IdentityTypeEnum;
import ai.remi.comm.core.enums.PermissionScopeEnum;
import ai.remi.comm.core.enums.ServiceTypeEnum;
import lombok.Data;

import java.util.Set;

/**
 * @author dianjiu【公众号 点九开源】
 * @email dianjiuxyz@gmail.com
 * @desc CompanyAuthInfo
 */
@Data
public class CompanyAuthInfo implements AuthInfo {

    /**
     * 用户系统语言
     */
    private String userLanguage;

    /**
     * 根据 {@link IdentityTypeEnum} 不同代表不同的身份
     * 用户id、不同端代表对应的id
     */
    private String uniqueId;

    /**
     * 部门租户号
     */
    private String deptTenantId;

    /**
     * 公司租户号
     */
    private String companyTenantId;

    /**
     * 鉴权Token
     */
    private String accessToken;

    /**
     * 权限范围
     */
    private PermissionScopeEnum permissionScope;

    /**
     * 如果具有集团范围权限时，这个值为拥有这个权限的公司id集合
     */
    private Set<String> hasPermissionCompanyIds;

    /**
     * 如果具有公司范围权限时，这个值为拥有这个权限的部门id集合
     */
    private Set<String> hasPermissionDeptIds;

    @Override
    public IdentityTypeEnum getIdentityTypeEnum() {
        return IdentityTypeEnum.COMPANY;
    }

    @Override
    public String getServiceTypeCode() {
        return ServiceTypeEnum.WEB_SERVICE.getCode();
    }
}
