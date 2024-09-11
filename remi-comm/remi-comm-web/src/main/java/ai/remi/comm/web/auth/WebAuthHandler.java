package ai.remi.comm.web.auth;


import com.google.common.collect.Sets;
import ai.remi.comm.core.constant.HeaderConstant;
import ai.remi.comm.core.enums.PermissionScopeEnum;
import ai.remi.comm.util.auth.AuthInfo;
import ai.remi.comm.util.auth.CompanyAuthInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

/**
 * @author dianjiu【公众号 点九开源】
 * @email dianjiuxyz@gmail.com
 * @desc B端集团公司验证类
 */
@Component
public class WebAuthHandler implements AuthHandler {

    @Override
    public AuthInfo getAuthInfo(HttpServletRequest request, HttpServletResponse response) {
        String companyTenantId = request.getHeader(HeaderConstant.X_COMPANY_TENANT_ID);
        String deptTenantId = request.getHeader(HeaderConstant.X_DEPT_TENANT_ID);
        String authToken = request.getHeader(HeaderConstant.X_ACCESS_TOKEN);
        String userId = request.getHeader(HeaderConstant.X_UNIQUE_ID);
        String language = request.getHeader(HeaderConstant.X_USER_LANGUAGE);
        String permissionScopeCode = request.getHeader(HeaderConstant.X_PERMISSION_SCOPE);
        PermissionScopeEnum permissionScopeEnum = StringUtils.isBlank(permissionScopeCode)?null:
            PermissionScopeEnum.codeOf(permissionScopeCode);
        Set<String> companyIds = Sets.newHashSet(request.getHeaders(HeaderConstant.X_COMPANY_IDS).asIterator());
        Set<String> deptIds = Sets.newHashSet(request.getHeaders(HeaderConstant.X_DEPT_IDS).asIterator());
        CompanyAuthInfo companyAuthInfo = new CompanyAuthInfo();
        companyAuthInfo.setCompanyTenantId(companyTenantId);
        companyAuthInfo.setDeptTenantId(deptTenantId);
        companyAuthInfo.setAccessToken(authToken);
        companyAuthInfo.setUniqueId(userId);
        companyAuthInfo.setUserLanguage(language);
        companyAuthInfo.setPermissionScope(permissionScopeEnum);
        companyAuthInfo.setHasPermissionCompanyIds(companyIds);
        companyAuthInfo.setHasPermissionDeptIds(deptIds);
        return companyAuthInfo;
    }
}
