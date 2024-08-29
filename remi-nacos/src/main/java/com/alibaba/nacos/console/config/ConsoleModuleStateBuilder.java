package com.alibaba.nacos.console.config;

import com.alibaba.nacos.config.ConsoleConfig;
import com.alibaba.nacos.sys.module.ModuleState;
import com.alibaba.nacos.sys.module.ModuleStateBuilder;
import com.alibaba.nacos.sys.utils.ApplicationUtils;

/**
 * Console module state builder.
 *
 * @author xiweng.yy
 */
public class ConsoleModuleStateBuilder implements ModuleStateBuilder {

	public static final String CONSOLE_MODULE = "console";

	private static final String CONSOLE_UI_ENABLED = "console_ui_enabled";

	@Override
	public ModuleState build() {
		ModuleState result = new ModuleState(CONSOLE_MODULE);
		try {
			ConsoleConfig consoleConfig = ApplicationUtils.getBean(ConsoleConfig.class);
			result.newState(CONSOLE_UI_ENABLED, consoleConfig.isConsoleUiEnabled());
		}
		catch (Exception ignored) {
		}
		return result;
	}

}
