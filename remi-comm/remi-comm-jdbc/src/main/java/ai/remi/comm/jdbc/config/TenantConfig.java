package ai.remi.comm.jdbc.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author dianjiu【公众号 点九开源】
 * @email dianjiuxyz@gmail.com
 * @desc 租户配置类
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "sql-intercept.tenant")
public class TenantConfig {

    //不拦截的请求
    private String anonUrlSet="";

    private InterceptConfig deptTenant=new InterceptConfig();

    private InterceptConfig companyTenant=new InterceptConfig();


}
