package com.alibaba.nacos;

import com.alibaba.nacos.config.ConfigConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author nacos
 * <p>
 * nacos console 源码运行，方便开发 生产从官网下载zip最新版集群配置运行
 */
@Slf4j
@EnableScheduling
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		if (initEnv()) {

			SpringApplication.run(Application.class, args);

			System.out.println("(♥◠‿◠)ﾉﾞ  Remi Nacos Startup Completed!   ლ(´ڡ`ლ)ﾞ ");
		}
	}

	/**
	 * 初始化运行环境
	 */
	private static boolean initEnv() {
		// Nacos 是以独立模式运行的
		System.setProperty(ConfigConstants.STANDALONE_MODE, "true");
		// 启用了 Nacos 的身份验证功能
		System.setProperty(ConfigConstants.AUTH_ENABLED, "true");
		// 设置日志文件存储的基本目录为 logs
		System.setProperty(ConfigConstants.LOG_BASEDIR, "logs");
		// Nacos 不会记录日志信息
		System.setProperty(ConfigConstants.LOG_ENABLED, "false");
		// 设置 Nacos 控制台的上下文路径为 /nacos
		System.setProperty(ConfigConstants.NACOS_CONTEXT_PATH, "/nacos");
		return true;
	}

}
