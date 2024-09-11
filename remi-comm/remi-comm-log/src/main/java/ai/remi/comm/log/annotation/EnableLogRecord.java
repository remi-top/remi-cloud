package ai.remi.comm.log.annotation;

import ai.remi.comm.log.config.LogRecordAutoConfigure;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.Order;

import java.lang.annotation.*;

/**
 * @author DianJiu 【公众号 点九开源】
 * @email dianjiuxyz@gmail.com
 * @date 2022-07-19
 * @desc 日志记录开启注解
 */
@Documented
@Inherited
@Order(Integer.MIN_VALUE)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(LogRecordAutoConfigure.class)
public @interface EnableLogRecord {

}