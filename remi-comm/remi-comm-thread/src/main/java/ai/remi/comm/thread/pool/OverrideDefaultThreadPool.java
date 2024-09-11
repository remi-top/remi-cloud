package ai.remi.comm.thread.pool;

import ai.remi.comm.thread.config.CustomThreadPollTaskExecutor;
import ai.remi.comm.thread.config.ThreadPoolConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.annotation.Resource;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author DianJiu 【公众号 点九开源】
 * @author DianJiu 【公众号 点九开源】
 * @email lidianjiu@njydsz.com
 * @date 2022-07-20
 * @desc 重写默认线程池配置
 * 基于ThreadPoolTaskExecutor线程池配置
 * 分析下继承关系：
 * <p>
 * 1、ThreadPoolTaskExecutor extends (2)ExecutorConfigurationSupport
 * implements (3)AsyncListenableTaskExecutor, (4)systemTaskExecutor
 * 2、 ExecutorConfigurationSupport extends CustomizableThreadFactory implements BeanNameAware, InitializingBean, DisposableBean
 * <p>
 * 3、public interface AsyncListenableTaskExecutor extends AsyncTaskExecutor
 * 4、public interface systemTaskExecutor extends AsyncTaskExecutor
 * <p>
 * 从上继承关系可知：
 * ThreadPoolExecutor是一个java类不提供spring生命周期和参数装配。
 * ThreadPoolTaskExecutor实现了InitializingBean, DisposableBean ，xxaware等，具有spring特性
 * <p>
 * AsyncListenableTaskExecutor提供了监听任务方法(相当于添加一个任务监听，提交任务完成都会回调该方法)
 * <p>
 * 简单理解：
 * 1、ThreadPoolTaskExecutor使用ThreadPoolExecutor并增强，扩展了更多特性
 * 2、ThreadPoolTaskExecutor只关注自己增强的部分，任务执行还是ThreadPoolExecutor处理。
 * 3、前者spring自己用着爽，后者离开spring我们用ThreadPoolExecutor爽。
 * 注意：ThreadPoolTaskExecutor 不会自动创建ThreadPoolExecutor需要手动调initialize才会创建
 * 如果@Bean 就不需手动，会自动InitializingBean的afterPropertiesSet来调initialize
 */
@Configuration
@EnableAsync
public class OverrideDefaultThreadPool implements AsyncConfigurer {

    private Logger log = LoggerFactory.getLogger(OverrideDefaultThreadPool.class);

    @Resource
    private ThreadPoolConfiguration threadPoolConfiguration;

    /**
     * 使用方式：在某个service方法上使用该注解
     *
     * @Async("customizeThreadPool") 注意：
     * 如下方式会使@Async失效
     * 一、异步方法使用static修饰
     * 二、异步类没有使用@Component注解（或其他注解）导致spring无法扫描到异步类
     * 三、异步方法不能与异步方法在同一个类中
     * 四、类中需要使用@Autowired或@Resource等注解自动注入，不能自己手动new对象
     * 五、如果使用SpringBoot框架必须在启动类中增加@EnableAsync注解
     * 六、在Async 方法上标注@Transactional是没用的。 在Async 方法调用的方法上标注@Transactional 有效。
     * 七、调用被@Async标记的方法的调用者不能和被调用的方法在同一类中不然不会起作用！！！！！！！
     * 八、使用@Async时要求是不能有返回值的不然会报错的 因为异步要求是不关心结果的
     */
    @Bean(name = "customizeThreadPool")
    public Executor customizeThreadExecutor() {
        log.info("start customizeThreadExecutor");
        ThreadPoolTaskExecutor executor = new CustomThreadPollTaskExecutor();
        //配置核心线程数
        executor.setCorePoolSize(threadPoolConfiguration.getCorePoolSize());
        //配置最大线程数
        executor.setMaxPoolSize(threadPoolConfiguration.getMaxPoolSize());
        //配置队列大小
        executor.setQueueCapacity(threadPoolConfiguration.getQueueCapacity());
        //活跃时间
        executor.setKeepAliveSeconds(threadPoolConfiguration.getKeepAliveSeconds());
        //配置线程池中的线程的名称前缀
        executor.setThreadNamePrefix("customize-thread-");
        //设置是否允许核心线程超时。若允许，核心线程超时后，会被销毁。默认为不允许（fasle）。
        executor.setAllowCoreThreadTimeOut(false);
        //设置shutdown时是否等到所有任务完成再真正关闭
        executor.setWaitForTasksToCompleteOnShutdown(true);
        //当 setWaitForTasksToCompleteOnShutdown(true)时，setAwaitTerminationSeconds 设置在 shutdown 之后最多等待多长时间后再真正关闭线程池
        executor.setAwaitTerminationSeconds(60);
        // rejection-policy：当pool已经达到max size的时候，如何处理新任务
        // CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //执行初始化
        executor.initialize();
        return executor;
    }


    /**
     * 保存主表日志线程池
     *
     * @return Executor
     */
    @Bean(name = "saveMasterLogThreadPool")
    public Executor saveMasterLogThreadExecutor() {
        log.info("start saveMasterLogThreadExecutor");
        ThreadPoolTaskExecutor executor = new CustomThreadPollTaskExecutor();
        //配置核心线程数
        executor.setCorePoolSize(threadPoolConfiguration.getCorePoolSize());
        //配置最大线程数
        executor.setMaxPoolSize(threadPoolConfiguration.getMaxPoolSize());
        //配置队列大小
        executor.setQueueCapacity(threadPoolConfiguration.getQueueCapacity());
        //活跃时间
        executor.setKeepAliveSeconds(threadPoolConfiguration.getKeepAliveSeconds());
        //配置线程池中的线程的名称前缀
        executor.setThreadNamePrefix("saveMasterLog-thread-");
        //设置是否允许核心线程超时。若允许，核心线程超时后，会被销毁。默认为不允许（fasle）。
        executor.setAllowCoreThreadTimeOut(false);
        //设置shutdown时是否等到所有任务完成再真正关闭
        executor.setWaitForTasksToCompleteOnShutdown(true);
        //当 setWaitForTasksToCompleteOnShutdown(true)时，setAwaitTerminationSeconds 设置在 shutdown 之后最多等待多长时间后再真正关闭线程池
        executor.setAwaitTerminationSeconds(60);
        // rejection-policy：当pool已经达到max size的时候，如何处理新任务
        // CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //执行初始化
        executor.initialize();
        return executor;
    }

    /**
     * 保存子表日志线程池
     *
     * @return Executor
     */
    @Bean(name = "saveDayLogThreadPool")
    public Executor saveDayLogThreadExecutor() {
        log.info("start saveDayLogThreadExecutor");
        ThreadPoolTaskExecutor executor = new CustomThreadPollTaskExecutor();
        //配置核心线程数
        executor.setCorePoolSize(threadPoolConfiguration.getCorePoolSize());
        //配置最大线程数
        executor.setMaxPoolSize(threadPoolConfiguration.getMaxPoolSize());
        //配置队列大小
        executor.setQueueCapacity(threadPoolConfiguration.getQueueCapacity());
        //活跃时间
        executor.setKeepAliveSeconds(threadPoolConfiguration.getKeepAliveSeconds());
        //配置线程池中的线程的名称前缀
        executor.setThreadNamePrefix("saveDayLog-thread-");
        //设置是否允许核心线程超时。若允许，核心线程超时后，会被销毁。默认为不允许（fasle）。
        executor.setAllowCoreThreadTimeOut(false);
        //设置shutdown时是否等到所有任务完成再真正关闭
        executor.setWaitForTasksToCompleteOnShutdown(true);
        //当 setWaitForTasksToCompleteOnShutdown(true)时，setAwaitTerminationSeconds 设置在 shutdown 之后最多等待多长时间后再真正关闭线程池
        executor.setAwaitTerminationSeconds(60);
        // rejection-policy：当pool已经达到max size的时候，如何处理新任务
        // CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //执行初始化
        executor.initialize();
        return executor;
    }


    /**
     * 保存子表线程池
     *
     * @return Executor
     */
    @Bean(name = "saveDayThreadPool")
    public Executor saveDayThreadExecutor() {
        log.info("start saveDayThreadExecutor");
        ThreadPoolTaskExecutor executor = new CustomThreadPollTaskExecutor();
        //配置核心线程数
        executor.setCorePoolSize(threadPoolConfiguration.getCorePoolSize());
        //配置最大线程数
        executor.setMaxPoolSize(threadPoolConfiguration.getMaxPoolSize());
        //配置队列大小
        executor.setQueueCapacity(threadPoolConfiguration.getQueueCapacity());
        //活跃时间
        executor.setKeepAliveSeconds(threadPoolConfiguration.getKeepAliveSeconds());
        //配置线程池中的线程的名称前缀
        executor.setThreadNamePrefix("saveDay-thread-");
        //设置是否允许核心线程超时。若允许，核心线程超时后，会被销毁。默认为不允许（fasle）。
        executor.setAllowCoreThreadTimeOut(false);
        //设置shutdown时是否等到所有任务完成再真正关闭
        executor.setWaitForTasksToCompleteOnShutdown(true);
        //当 setWaitForTasksToCompleteOnShutdown(true)时，setAwaitTerminationSeconds 设置在 shutdown 之后最多等待多长时间后再真正关闭线程池
        executor.setAwaitTerminationSeconds(60);
        // rejection-policy：当pool已经达到max size的时候，如何处理新任务
        // CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //执行初始化
        executor.initialize();
        return executor;
    }


    /**
     * 保存redis线程池
     *
     * @return Executor
     */
    @Bean(name = "saveRedisThreadPool")
    public Executor saveRedisThreadExecutor() {
        log.info("start saveRedisThreadExecutor");
        ThreadPoolTaskExecutor executor = new CustomThreadPollTaskExecutor();
        //配置核心线程数
        executor.setCorePoolSize(threadPoolConfiguration.getCorePoolSize());
        //配置最大线程数
        executor.setMaxPoolSize(threadPoolConfiguration.getMaxPoolSize());
        //配置队列大小
        executor.setQueueCapacity(threadPoolConfiguration.getQueueCapacity());
        //活跃时间
        executor.setKeepAliveSeconds(threadPoolConfiguration.getKeepAliveSeconds());
        //配置线程池中的线程的名称前缀
        executor.setThreadNamePrefix("saveRedis-thread-");
        //设置是否允许核心线程超时。若允许，核心线程超时后，会被销毁。默认为不允许（fasle）。
        executor.setAllowCoreThreadTimeOut(false);
        //设置shutdown时是否等到所有任务完成再真正关闭
        executor.setWaitForTasksToCompleteOnShutdown(true);
        //当 setWaitForTasksToCompleteOnShutdown(true)时，setAwaitTerminationSeconds 设置在 shutdown 之后最多等待多长时间后再真正关闭线程池
        executor.setAwaitTerminationSeconds(60);
        // rejection-policy：当pool已经达到max size的时候，如何处理新任务
        // CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //执行初始化
        executor.initialize();
        return executor;
    }


    /**
     * 使用方式：在某个service方法上使用该注解
     *
     * @return
     * @Async
     */
    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //核心线程池大小
        executor.setCorePoolSize(threadPoolConfiguration.getCorePoolSize());
        //最大线程数
        executor.setMaxPoolSize(threadPoolConfiguration.getMaxPoolSize());
        //队列容量
        executor.setQueueCapacity(threadPoolConfiguration.getQueueCapacity());
        //活跃时间
        executor.setKeepAliveSeconds(threadPoolConfiguration.getKeepAliveSeconds());
        //线程名字前缀
        executor.setThreadNamePrefix("default-thread-");
        //设置是否允许核心线程超时。若允许，核心线程超时后，会被销毁。默认为不允许（fasle）。
        executor.setAllowCoreThreadTimeOut(false);
        //设置shutdown时是否等到所有任务完成再真正关闭
        executor.setWaitForTasksToCompleteOnShutdown(true);
        //当 setWaitForTasksToCompleteOnShutdown(true)时，setAwaitTerminationSeconds 设置在 shutdown 之后最多等待多长时间后再真正关闭线程池
        executor.setAwaitTerminationSeconds(60);
        // rejection-policy：当pool已经达到max size的时候，如何处理新任务
        // CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //执行初始化
        executor.initialize();
        return executor;
    }

    /**
     * 异步任务中异常处理
     *
     * @return
     */
    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return (ex, method, params) -> {
            log.error("==========================" + ex.getMessage() + "=======================", ex);
            log.error("default-thread-pool exception method:" + method.getName());
        };
    }
}
