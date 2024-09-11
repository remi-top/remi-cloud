package ai.remi.comm.jdbc.handler;

import ai.remi.comm.jdbc.config.TenantConfig;
import ai.remi.comm.util.auth.AuthInfoUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

/**
 * @author dianjiu【公众号 点九开源】
 * @email dianjiuxyz@gmail.com
 * @desc 部门租户ID字段填充拦截器
 */
@Slf4j
public class CompanyTenantHandler extends AbstractTenantHandler {


    public CompanyTenantHandler(TenantConfig tenantConfig) {
        super(tenantConfig.getCompanyTenant());
    }


    @Override
    protected String doGetTenantId() {
        return Optional.ofNullable(AuthInfoUtils.getCompanyTenantId()).orElse("");
    }

    @Override
    protected String getDefaultColumn() {
        return "company_tenant_id";
    }
}
