package ai.remi.comm.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * @author dianjiu【公众号 点九开源】
 * @email dianjiuxyz@gmail.com
 * @desc 设置项目默认时区
 */
@Configuration
//@ComponentScan("ai.remi")
//@PropertySource("classpath:jdbc.properties")
//spring中开启事务保证事务的完整性
@EnableTransactionManagement
public class SpringConfig {
    
    @Bean(name = "transactionManager")
    public DataSourceTransactionManager dataSourceTransactionManager(DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }
    
}
