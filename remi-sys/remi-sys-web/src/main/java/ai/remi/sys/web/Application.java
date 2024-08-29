package ai.remi.sys.web;

import ai.remi.comm.feign.annotation.EnableRemiFeignClients;
import ai.remi.comm.log.annotation.EnableLogRecord;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.jmx.support.RegistrationPolicy;

/**
 * 系统服务启动程序
 */
@EnableLogRecord
@EnableDiscoveryClient
@EnableRemiFeignClients
@SpringBootApplication(scanBasePackages = "ai.remi")
@EnableMBeanExport(registration = RegistrationPolicy.IGNORE_EXISTING)
public class Application {

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);

        System.out.println("(♥◠‿◠)ﾉﾞ  Remi SYS Web Startup Completed!   ლ(´ڡ`ლ)ﾞ ");

    }

}
