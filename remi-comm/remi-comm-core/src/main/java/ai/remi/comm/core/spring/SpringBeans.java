package ai.remi.comm.core.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.Map;

/**
 * @author dianjiu【公众号 点九开源】
 * @email dianjiuxyz@gmail.com
 * @desc SpringBeanUtils
 */
@Component
public class SpringBeans implements ApplicationContextAware {
    
    private static ApplicationContext applicationContext;
    
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (SpringBeans.applicationContext == null) {
            SpringBeans.applicationContext = applicationContext;
        }
    }
    
    /**
     * Created by dianjiu on 2022-06-23 ,contact lidianjiu@njydsz.com. 获取applicationContext.
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }
    
    /**
     * Created by dianjiu on 2022-06-23 ,contact lidianjiu@njydsz.com. 通过name获取 Bean.
     */
    public static Object getBean(String name) {
        return getApplicationContext().getBean(name);
    }
    
    /**
     * Created by dianjiu on 2022-06-23 ,contact lidianjiu@njydsz.com. 通过class获取Bean.
     */
    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }
    
    /**
     * Created by dianjiu on 2022-06-23 ,contact lidianjiu@njydsz.com. 通过name,以及Clazz返回指定的Bean.
     */
    public static <T> T getBean(String name, Class<T> clazz) {
        return getApplicationContext().getBean(name, clazz);
    }
    
    /**
     * 根据clazz类型获取spring容器中的对象
     */
    public static <T> Map<String, T> getBeansOfType(Class<T> clazz) {
        return getApplicationContext().getBeansOfType(clazz);
    }
    
    /**
     * 根据注解类从容器中获取对象
     */
    public static <T> Map<String, Object> getBeansOfAnnotation(Class<? extends Annotation> clazz) {
        return getApplicationContext().getBeansWithAnnotation(clazz);
    }
    
}
