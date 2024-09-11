## 方式1: 实现ApplicationContextInitializer接口
实现接口后，将此类注入到Spring容器中，有两种方式。当然也可以直接调用静态方法

第一种： 在此类上加`@Component`注解

第二种：在 `resources/META-INF/spring.factories`文件中添加以下配置:  org.springframework.context.ApplicationContextInitializer=CustomerApplicationContextInitializer的路径

实现ApplicationContextInitializer的源码示例:
```java

public class CustomApplicationContextInitializer implements ApplicationContextInitializer {
  @Override
  public void initialize(ConfigurableApplicationContext applicationContext) {    
     SpringBeanUtils.setApplicationContext(applicationContext);
  }
}

```
 

## 方式2. 实现ApplicationListener接口
实现接口后，将此类注入到Spring容器中，有两种方式。当然也可以直接调用静态方法

第一种： 在此类上加`@Component`注解

第二种：在 `resources/META-INF/spring.factories`文件中添加以下配置:  org.springframework.context.ApplicationContextInitializer=CustomerApplicationListener的路径

实现ApplicationListener的源码示例:
```java

public class CustomApplicationListener implements ApplicationListener<ApplicationContextEvent> {
  @Override
  public void onApplicationEvent(ApplicationContextEvent event) {        
     SpringBeanUtils.setApplicationContext(event.getApplicationContext());
  }
}

```

方式3. 在启动类main方法中设置
```java

@SpringBootApplication
@EnableSwagger2
public class Application {
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(WangMikeSpringApplication.class, args);        
        SpringBeanUtils.setApplicationContext(applicationContext);
    }
}

```

方式4. 实现ApplicationContextAware接口
```java

@Component
public class SpringBeanUtils implements ApplicationContextAware {
    private static ApplicationContext applicationContext;
    public  void setApplicationContext(ApplicationContext applicationContext){
        SpringBeanUtils.applicationContext = applicationContext;
    }
 
    public static ApplicationContext getApplicationContext(
        return applicationContext;
    }
}

```
方式5. 直接通过@Resource注解注入
```java

@Component
@Order(value = 1)
@Slf4j
public class AppStartRunner implements org.springframework.boot.ApplicationRunner {
    protected static Logger logger = LoggerFactory.getLogger(AppStartRunner.class);
 
    @Resource
    ApplicationContext applicationContext;
}

```