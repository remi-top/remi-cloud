package ai.remi.comm.job.config;

import ai.remi.comm.job.properties.XxlJobProperties;
import com.xxl.job.core.executor.impl.XxlJobSpringExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * 定时任务配置
 *
 * @author dianjiu
 */
@Slf4j
@Configuration
@EnableConfigurationProperties(value = XxlJobProperties.class)
@ConditionalOnProperty(value = "xxl-job.enabled", havingValue = "true")
public class XxlJobConfiguration {

    @Resource
    private XxlJobProperties xxlJobProperties;

    /**
     * 针对多网卡、容器内部署等情况，可借助 "spring-cloud-commons" 提供的 "InetUtils" 组件灵活定制注册IP；
     * <p>
     * 1、引入依赖：
     * <dependency>
     * <groupId>org.springframework.cloud</groupId>
     * <artifactId>spring-cloud-commons</artifactId>
     * <version>${version}</version>
     * </dependency>
     * <p>
     * 2、配置文件，或者容器启动变量
     * spring.cloud.inetutils.preferred-networks: 'xxx.xxx.xxx.'
     * <p>
     * 3、获取IP
     * String ip_ = inetUtils.findFirstNonLoopbackHostInfo().getIpAddress();
     */
    @Bean
    public XxlJobSpringExecutor xxlJobExecutor() {
        log.info(">>>>>>>>>>> xxl-job config init start.");
        XxlJobSpringExecutor xxlJobSpringExecutor = new XxlJobSpringExecutor();
        xxlJobSpringExecutor.setAdminAddresses(xxlJobProperties.getAdminAddresses());
        xxlJobSpringExecutor.setAppname(xxlJobProperties.getExecutor().getAppname());
        xxlJobSpringExecutor.setAddress(xxlJobProperties.getExecutor().getAddress());
        xxlJobSpringExecutor.setIp(xxlJobProperties.getExecutor().getIp());
        xxlJobSpringExecutor.setPort(xxlJobProperties.getExecutor().getPort());
        xxlJobSpringExecutor.setAccessToken(xxlJobProperties.getAccessToken());
        xxlJobSpringExecutor.setLogPath(xxlJobProperties.getExecutor().getLogPath());
        xxlJobSpringExecutor.setLogRetentionDays(xxlJobProperties.getExecutor().getLogRetentionDays());
        log.info(">>>>>>>>>>> xxl-job config init end.");
        return xxlJobSpringExecutor;
    }


}
