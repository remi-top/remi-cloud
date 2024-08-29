package com.alibaba.nacos.console.controller.v2;

import com.alibaba.nacos.api.model.v2.Result;
import com.alibaba.nacos.console.paramcheck.ConsoleDefaultHttpParamExtractor;
import com.alibaba.nacos.core.cluster.health.ModuleHealthCheckerHolder;
import com.alibaba.nacos.core.cluster.health.ReadinessResult;
import com.alibaba.nacos.core.paramcheck.ExtractorManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Health ControllerV2.
 *
 * @author DiligenceLai
 */
@RestController("consoleHealthV2")
@RequestMapping("/v2/console/health")
@ExtractorManager.Extractor(httpExtractor = ConsoleDefaultHttpParamExtractor.class)
public class HealthControllerV2 {

	/**
	 * Whether the Nacos is in broken states or not, and cannot recover except by being
	 * restarted.
	 * @return HTTP code equal to 200 indicates that Nacos is in right states. HTTP code
	 * equal to 500 indicates that Nacos is in broken states.
	 */
	@GetMapping("/liveness")
	public Result<String> liveness() {
		return Result.success("ok");
	}

	/**
	 * Ready to receive the request or not.
	 * @return HTTP code equal to 200 indicates that Nacos is ready. HTTP code equal to
	 * 500 indicates that Nacos is not ready.
	 */
	@GetMapping("/readiness")
	public Result<String> readiness(HttpServletRequest request) {
		ReadinessResult result = ModuleHealthCheckerHolder.getInstance().checkReadiness();
		if (result.isSuccess()) {
			return Result.success("ok");
		}
		return Result.failure(result.getResultMessage());
	}

}
