package ai.remi.boot.app;

import ai.remi.comm.feign.annotation.EnableRemiFeignClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.jmx.support.RegistrationPolicy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//@Enablestartdistem
//@EnableLogRecord
@EnableDiscoveryClient
@EnableRemiFeignClients
@EnableTransactionManagement
@SpringBootApplication(scanBasePackages = "ai.remi")
@EnableMBeanExport(registration = RegistrationPolicy.IGNORE_EXISTING)
public class Application {
    
    
    public static void main(String[] args) {
        
        SpringApplication.run(Application.class, args);
        
        System.out.println("(♥◠‿◠)ﾉﾞ  Remi IAM App 启动成功   ლ(´ڡ`ლ)ﾞ ");
        
    }

}
