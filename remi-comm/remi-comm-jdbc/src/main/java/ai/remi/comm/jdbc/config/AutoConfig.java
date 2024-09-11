package ai.remi.comm.jdbc.config;

import ai.remi.comm.jdbc.handler.CompanyTenantHandler;
import ai.remi.comm.jdbc.handler.DeptTenantHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author dianjiu【公众号 点九开源】
 * @email dianjiuxyz@gmail.com
 * @desc 自动配置类
 */
@Configuration
@Import({CompanyTenantHandler.class, DeptTenantHandler.class,TenantConfig.class})
public class AutoConfig {



}
