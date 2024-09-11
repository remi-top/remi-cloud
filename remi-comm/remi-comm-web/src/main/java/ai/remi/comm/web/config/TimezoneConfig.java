package ai.remi.comm.web.config;

import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

/**
 * @author dianjiu【公众号 点九开源】
 * @email dianjiuxyz@gmail.com
 * @desc 设置项目默认时区
 */
@Configuration
public class TimezoneConfig {

    @PostConstruct
    public void defaultTimeZone() {
        // 设置用户时区为 UTC
        //TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        // 设置用户时区为 Asia/Shanghai
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
    }

}
