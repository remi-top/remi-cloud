package ai.remi.comm.core.spring;

import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * @author dianjiu【公众号 点九开源】
 * @email dianjiuxyz@gmail.com
 * @desc 通过"server.port"作为name格式调用getPropertiesValue()方法
 */
@Component
public class SpringProperties implements EnvironmentAware {
    
    private static Environment env;
    
    @Override
    public void setEnvironment(Environment environment) {
        env = environment;
    }
    
    public static String getString(String key) {
        return env.getProperty(key);
    }
    
}
