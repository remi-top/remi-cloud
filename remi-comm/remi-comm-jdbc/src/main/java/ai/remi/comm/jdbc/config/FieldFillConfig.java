package ai.remi.comm.jdbc.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author dianjiu【公众号 点九开源】
 * @email dianjiuxyz@gmail.com
 * @desc 字段填充配置类
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "sql-intercept.field-fill")
public class FieldFillConfig {

    private InterceptConfig createdByIntercept=new InterceptConfig();

    private InterceptConfig updateByIntercept=new InterceptConfig();

    private InterceptConfig createAtIntercept=new InterceptConfig();

    private InterceptConfig updateAtIntercept=new InterceptConfig();

}
