package ai.remi.comm.feign.annotation;

import org.springframework.cloud.openfeign.EnableFeignClients;

import java.lang.annotation.*;


/**
 * @author Remi
 * @email remi@dianjiu.cc
 * @desc 自定义feign注解,添加basePackages路径
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@EnableFeignClients
public @interface EnableRemiFeignClients {
    String[] value() default {};

    String[] basePackages() default {"ai.remi"};

    Class<?>[] basePackageClasses() default {};

    Class<?>[] defaultConfiguration() default {};

    Class<?>[] clients() default {};
}
