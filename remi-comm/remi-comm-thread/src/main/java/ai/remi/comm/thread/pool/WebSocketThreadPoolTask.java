package ai.remi.comm.thread.pool;

import ai.remi.comm.thread.config.ThreadPoolConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.annotation.Resource;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 基于ThreadPoolTaskExecutor线程池配置
 * 分析下继承关系：
 *
 *  1、ThreadPoolTaskExecutor extends (2)ExecutorConfigurationSupport
 * 	implements (3)AsyncListenableTaskExecutor, (4)systemTaskExecutor
 * 2、 ExecutorConfigurationSupport extends CustomizableThreadFactory implements BeanNameAware, InitializingBean, DisposableBean
 *
 * 3、public interface AsyncListenableTaskExecutor extends AsyncTaskExecutor
 * 4、public interface systemTaskExecutor extends AsyncTaskExecutor
 *
 * 从上继承关系可知：
 * ThreadPoolExecutor是一个java类不提供spring生命周期和参数装配。
 * ThreadPoolTaskExecutor实现了InitializingBean, DisposableBean ，xxaware等，具有spring特性
 *
 * AsyncListenableTaskExecutor提供了监听任务方法(相当于添加一个任务监听，提交任务完成都会回调该方法)
 *
 * 简单理解：
 * 1、ThreadPoolTaskExecutor使用ThreadPoolExecutor并增强，扩展了更多特性
 * 2、ThreadPoolTaskExecutor只关注自己增强的部分，任务执行还是ThreadPoolExecutor处理。
 * 3、前者spring自己用着爽，后者离开spring我们用ThreadPoolExecutor爽。
 * 注意：ThreadPoolTaskExecutor 不会自动创建ThreadPoolExecutor需要手动调initialize才会创建
 *     如果@Bean 就不需手动，会自动InitializingBean的afterPropertiesSet来调initialize
 * @author DianJiu 【公众号 点九开源】
 */
@Configuration
public class WebSocketThreadPoolTask {
	private Logger log = LoggerFactory.getLogger(WebSocketThreadPoolTask.class);

	@Resource
	private ThreadPoolConfiguration threadPoolConfiguration;

	/**
	 * 使用方式
	 * @Resource(name = "socketServerTaskPool")
	 * private ThreadPoolTaskExecutor serverExecutor
	 * 然后调用excute方法或者submit方法
	 */
	@Bean(name = "socketServerTaskPool")
	public ThreadPoolTaskExecutor serverTaskPool() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		//获取到服务器的cpu内核
		int i = Runtime.getRuntime().availableProcessors();
		//核心线程数
		executor.setCorePoolSize(threadPoolConfiguration.getCorePoolSize());
		//最大线程数
		executor.setMaxPoolSize(threadPoolConfiguration.getMaxPoolSize());
		//存活时间 300s
		executor.setKeepAliveSeconds(threadPoolConfiguration.getKeepAliveSeconds());
		//阻塞队列长度
		executor.setQueueCapacity(threadPoolConfiguration.getQueueCapacity());
		//线程名前缀
		executor.setThreadNamePrefix("socket-server-");
		//拒绝策略
		//AbortPolicy：用于被拒绝任务的处理程序，它将抛出RejectedExecutionException
		//CallerRunsPolicy：用于被拒绝任务的处理程序，它直接在execute方法的调用线程中运行被拒绝的任务。
		//DiscardOldestPolicy：用于被拒绝任务的处理程序，它放弃最旧的未处理请求，然后重试execute。
		//DiscardPolicy：用于被拒绝任务的处理程序，默认情况下它将丢弃被拒绝的任务。
		executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
		//执行初始化
		executor.initialize();
		return executor;
	}

	@Bean(name = "socketMessageTaskPool")
	public ThreadPoolTaskExecutor serverMessageTaskPool() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		//核心线程数
		executor.setCorePoolSize(threadPoolConfiguration.getCorePoolSize());
		//最大线程数
		executor.setMaxPoolSize(threadPoolConfiguration.getMaxPoolSize());
		//存活时间 300s
		executor.setKeepAliveSeconds(threadPoolConfiguration.getKeepAliveSeconds());
		//阻塞队列长度
		executor.setQueueCapacity(threadPoolConfiguration.getQueueCapacity());
		//线程名前缀
		executor.setThreadNamePrefix("socket-message-");
		//拒绝策略
		//AbortPolicy：用于被拒绝任务的处理程序，它将抛出RejectedExecutionException
		//CallerRunsPolicy：用于被拒绝任务的处理程序，它直接在execute方法的调用线程中运行被拒绝的任务。
		//DiscardOldestPolicy：用于被拒绝任务的处理程序，它放弃最旧的未处理请求，然后重试execute。
		//DiscardPolicy：用于被拒绝任务的处理程序，默认情况下它将丢弃被拒绝的任务。
		executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
		//执行初始化
		executor.initialize();
		return executor;
	}
}
