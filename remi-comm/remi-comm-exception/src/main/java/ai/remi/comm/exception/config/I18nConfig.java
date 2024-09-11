package ai.remi.comm.exception.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class I18nConfig implements WebMvcConfigurer {

    //@Value("${i18n.messages.basename:}")
    //private String messagesBasename;

    //@Value("${i18n.messages.encoding:}")
    //private String messagesEncoding;

    @Bean(name = "messageSource")
    public ReloadableResourceBundleMessageSource messageSource() {
        ReloadableResourceBundleMessageSource rbms = new ReloadableResourceBundleMessageSource();
        //if (!StringUtils.isEmpty(messagesBasename)) {
        //    //设置path
        //    rbms.addBasenames(messagesBasename.split(","));
        //}
        //rbms.setDefaultEncoding(messagesEncoding);
        rbms.setBasenames("classpath:i18n/messages");
        rbms.setDefaultEncoding("UTF-8");
        rbms.setUseCodeAsDefaultMessage(true);
        rbms.setFallbackToSystemLocale(false);
        return rbms;
    }

    @Bean
    public LocalValidatorFactoryBean getValidator() {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource());
        return bean;
    }
}

