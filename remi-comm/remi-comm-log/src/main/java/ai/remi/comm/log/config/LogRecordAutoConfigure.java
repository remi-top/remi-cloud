package ai.remi.comm.log.config;

import ai.remi.comm.log.aspect.LogRecordAspect;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @author DianJiu 【公众号 点九开源】
 * @email dianjiuxyz@gmail.com
 * @date 2022-07-19
 * @desc
 */
@Slf4j
@RefreshScope
@Configuration
public class LogRecordAutoConfigure {
    /**
     * 是否开启监控配置参数
     */
    @Value("${remi-log.record.enabled}")
    private Boolean enabled;

    /**
     * 日志监控的应用名称
     */
    @Value("${remi-log.record.appName}")
    private String appName;

    /**
     * 在方法成功执行后打点，记录方法的执行时间发送到指标系统，默认关闭
     * 在方法成功失败后打点，记录方法的执行时间发送到指标系统，默认关闭
     */
    @Value("${remi-log.record.pushUrl}")
    private String pushUrl;


    /**
     * 根据运行环境决定是否开启日志记录监控
     */
    @PostConstruct
    protected LogRecordAspect init() {
        if(!enabled){
            return null;
        }
        if (enabled && StringUtils.isBlank(appName)) {
            log.error("Please config the remi-log.record property in application.yml file to enabled log record, but appName is null.");
            throw new RuntimeException("Please config the remi-log.record property in application.yml file to config log record appName.");
        }
        return new LogRecordAspect();
    }
}
