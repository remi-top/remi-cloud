package ai.remi.comm.swagger.config;

import ai.remi.comm.core.constant.HeaderConstant;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.headers.Header;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
@EnableAutoConfiguration
@ConditionalOnProperty(name = "knife4j.enable", matchIfMissing = true)
public class OpenApiConfig {
    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                // 接口文档标题
                .info(new Info().title("Remi API 3")
                        // 接口文档描述
                        .description("<div style='font-size:14px;color:red;'>Remi OpenApi 3</div>")
                        // 接口文档版本
                        .version("v2.0.8")
                        // 开发者联系方式
                        .contact(new Contact().name("点九开源").email("dianjiuxyz@gmail.com").url("https://dianjiu.xyz")))
                .components(new Components()
                        .headers(setHeaderParam()))
                .externalDocs(new ExternalDocumentation()
                        // 额外补充说明
                        .description("瑞米平台官网")
                        // 额外补充链接
                        .url("https://startdis.com/"));
    }

    /**
     * JWT token
     */
    private Map<String, Header> setHeaderParam() {
        Map<String, Header> headers = new LinkedHashMap();

        Header serviceType = new Header();
        serviceType.setDescription("公共-服务类型");
        serviceType.setRequired(false);
        headers.put(HeaderConstant.X_SERVICE_TYPE, serviceType);

        Header deptTenantId = new Header();
        deptTenantId.setDescription("公共-部门租户Id");
        deptTenantId.setRequired(false);
        headers.put(HeaderConstant.X_DEPT_TENANT_ID, deptTenantId);

        Header companyTenantId = new Header();
        companyTenantId.setDescription("公共-公司租户Id");
        companyTenantId.setRequired(false);
        headers.put(HeaderConstant.X_COMPANY_TENANT_ID, companyTenantId);

        Header uniqueId = new Header();
        uniqueId.setDescription("公共-用户唯一ID");
        uniqueId.setRequired(false);
        headers.put(HeaderConstant.X_UNIQUE_ID, uniqueId);

        Header accessToken = new Header();
        accessToken.setDescription("公共-用户鉴权Token");
        accessToken.setRequired(false);
        headers.put(HeaderConstant.X_ACCESS_TOKEN, accessToken);

        Header distinctId = new Header();
        distinctId.setDescription("公共-设备唯一标识");
        distinctId.setRequired(false);
        headers.put(HeaderConstant.X_DISTINCT_ID, distinctId);

        Header permissionScope = new Header();
        permissionScope.setDescription("公共-权限类型");
        permissionScope.setRequired(false);
        headers.put(HeaderConstant.X_PERMISSION_SCOPE, permissionScope);

        Header companyIds = new Header();
        companyIds.setDescription("公共-公司ID集合");
        companyIds.setRequired(false);
        headers.put(HeaderConstant.X_COMPANY_IDS, companyIds);

        Header deptIds = new Header();
        deptIds.setDescription("公共-部门ID集合");
        deptIds.setRequired(false);
        headers.put(HeaderConstant.X_DEPT_IDS, deptIds);

        return headers;
    }
}
