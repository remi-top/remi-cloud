package ai.remi.comm.email.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import javax.annotation.Resource;
import java.util.Properties;

/**
 * @author dianjiu【公众号 点九开源】
 * @email startdis@njydsz.com
 * @desc MinioConfig
 */
@Configuration
@EnableConfigurationProperties(EmailProperties.class)
public class EmailConfig {

    @Resource
    private EmailProperties emailProperties;


    /**
     * 初始化 JavaMailSender
     */
    @Bean
    public JavaMailSenderImpl javaMailSenderImpl() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setProtocol(emailProperties.getProtocol());
        javaMailSender.setHost(emailProperties.getSmtpHost());
        javaMailSender.setPort(Integer.parseInt(emailProperties.getSmtpPort()));
        javaMailSender.setDefaultEncoding(emailProperties.getEncoding());
        javaMailSender.setUsername(emailProperties.getFromMail());
        javaMailSender.setPassword(emailProperties.getPassword());
        // TODO 设置邮件参数
        Properties props = new Properties();
        // props.setProperty("mail.smtp.auth", emailProperties.get());
        javaMailSender.setJavaMailProperties(props);
        return javaMailSender;
    }
}
