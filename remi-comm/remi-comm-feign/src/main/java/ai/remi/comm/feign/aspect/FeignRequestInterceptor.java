package ai.remi.comm.feign.aspect;

import ai.remi.comm.core.constant.HeaderConstant;
import ai.remi.comm.core.enums.IdentityTypeEnum;
import ai.remi.comm.core.enums.PermissionScopeEnum;
import ai.remi.comm.util.auth.AuthInfoUtils;
import ai.remi.comm.util.http.ServletUtils;
import ai.remi.comm.util.ip.IpAddrUtils;
import ai.remi.comm.util.string.StringUtils;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Optional;

/**
 * @author Remi
 * @email remi@dianjiu.cc
 * @desc Feign 请求拦截器
 */
@Component
public class FeignRequestInterceptor implements RequestInterceptor {
    private Logger log = LoggerFactory.getLogger(FeignRequestInterceptor.class);

    @Override
    public void apply(RequestTemplate requestTemplate) {
        HttpServletRequest httpServletRequest = ServletUtils.getRequest();
        Map<String, String> headers = ServletUtils.getHeaders(httpServletRequest);
        // 传递用户信息请求头，防止丢失
        //String userId = headers.get(HeaderConstant.X_UNIQUE_ID);
        if (StringUtils.isNotNull(httpServletRequest)) {
            // 传递服务类型请求头，防止丢失
            log.info("FeignHeaderRequestInterceptor X_SERVICE_TYPE:{}", AuthInfoUtils.getServiceTypeCode());
            requestTemplate.header(HeaderConstant.X_SERVICE_TYPE, AuthInfoUtils.getServiceTypeCode());
            // 传递部门租户请求头，防止丢失
            requestTemplate.header(HeaderConstant.X_DEPT_TENANT_ID, AuthInfoUtils.getDeptTenantId());
            // 传递公司租户请求头，防止丢失
            requestTemplate.header(HeaderConstant.X_COMPANY_TENANT_ID, AuthInfoUtils.getCompanyTenantId());
            // 传递用户信息请求头，防止丢失
            requestTemplate.header(HeaderConstant.X_UNIQUE_ID, AuthInfoUtils.getUniqueId());
            // 传递系统语言请求头，防止丢失
            requestTemplate.header(HeaderConstant.X_USER_LANGUAGE, AuthInfoUtils.getUserLanguage());
            // 传递用户设备请求头，防止丢失
            requestTemplate.header(HeaderConstant.X_DISTINCT_ID, headers.get(HeaderConstant.X_DISTINCT_ID));
            // 传递鉴权信息请求头，防止丢失
            requestTemplate.header(HeaderConstant.X_ACCESS_TOKEN, headers.get(HeaderConstant.X_ACCESS_TOKEN));

            // 判断用户类型是否为集团公司，是的话进一步判断是否为集团权限，是集团权限需要传递集团下属公司IDs
            IdentityTypeEnum identityTypeEnum = AuthInfoUtils.getIdentityTypeEnum();
            //集团公司的用户
            if (identityTypeEnum == IdentityTypeEnum.COMPANY) {
                //权限范围
                String permissionScope = Optional.ofNullable(AuthInfoUtils.getPermissionScopeEnum())
                        .map(PermissionScopeEnum::getCode).orElse("");
                requestTemplate.header(HeaderConstant.X_PERMISSION_SCOPE, permissionScope);
                //当权限范围为集团时，为这个用户管理公司的id
                requestTemplate.header(HeaderConstant.X_COMPANY_IDS, AuthInfoUtils.getHasPermissionCompanyIds());
                //当权限范围为公司时，为这个用户管理部门的id
                requestTemplate.header(HeaderConstant.X_COMPANY_IDS, AuthInfoUtils.getHasPermissionCompanyIds());
            } else if (identityTypeEnum == IdentityTypeEnum.VISITOR) {
                //访客体验的用户 do anything
            }

            // 传递请求来源请求头，防止丢失
            requestTemplate.header("X-Forwarded-For", IpAddrUtils.getIpAddr(ServletUtils.getRequest()));
        }
    }
}