package com.alibaba.nacos.console.controller;

import com.alibaba.nacos.common.model.RestResult;
import com.alibaba.nacos.common.model.RestResultUtils;
import com.alibaba.nacos.console.paramcheck.ConsoleDefaultHttpParamExtractor;
import com.alibaba.nacos.core.paramcheck.ExtractorManager;
import com.alibaba.nacos.sys.module.ModuleState;
import com.alibaba.nacos.sys.module.ModuleStateHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Server state controller.
 *
 * @author xingxuechao on:2019/2/27 11:17 AM
 */
@RestController
@RequestMapping("/v1/console/server")
@ExtractorManager.Extractor(httpExtractor = ConsoleDefaultHttpParamExtractor.class)
public class ServerStateController {

	/**
	 * Get server state of current server.
	 * @return state json.
	 */
	@GetMapping("/state")
	public ResponseEntity<Map<String, String>> serverState() {
		Map<String, String> serverState = new HashMap<>(4);
		for (ModuleState each : ModuleStateHolder.getInstance().getAllModuleStates()) {
			each.getStates().forEach((s, o) -> serverState.put(s, null == o ? null : o.toString()));
		}
		return ResponseEntity.ok().body(serverState);
	}

	@GetMapping("/announcement")
	public RestResult<String> getAnnouncement(
			@RequestParam(required = false, name = "language", defaultValue = "zh-CN") String language) {
		return RestResultUtils.success();
	}

	@GetMapping("/guide")
	public RestResult<String> getConsoleUiGuide() {
		return RestResultUtils.success();
	}

}
