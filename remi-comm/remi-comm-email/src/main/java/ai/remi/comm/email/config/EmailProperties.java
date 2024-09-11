package ai.remi.comm.email.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author dianjiu【公众号 点九开源】
 * @email startdis@njydsz.com
 * @desc Email配置类
 */
@Data
@Component
@ConfigurationProperties(prefix = "remi-comm.email")
public class EmailProperties {
    /**
     * 发送协议
     */
    private String protocol;
    /**
     * 服务器地址
     */
    private String smtpHost;
    /**
     * 服务器端口
     */
    private String smtpPort;
    /**
     * 发件人名称
     */
    private String fromName;
    /**
     * 发件人邮箱
     */
    private String fromMail;
    /**
     * 发件人密码
     */
    private String password;
    /**
     * 邮件编码
     */
    private String encoding;

    /**
     * SSL配置
     */
    @Data
    @Configuration
    @ConfigurationProperties("remi-comm.email.ssl")
    class sslConf {
        private String enabled;
        private String sslPort;
    }
}
