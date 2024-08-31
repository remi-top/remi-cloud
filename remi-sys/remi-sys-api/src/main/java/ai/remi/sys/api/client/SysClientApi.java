package ai.remi.sys.api.client;

import ai.remi.comm.core.result.ResultBean;
import ai.remi.comm.feign.aspect.FeignAutoConfiguration;
import ai.remi.sys.api.factory.SysClientFactory;
import ai.remi.sys.domain.dto.post.LogRecordPostDTO;
import ai.remi.sys.domain.vo.ConfigVO;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "remi-sys-web", configuration = FeignAutoConfiguration.class, fallbackFactory = SysClientFactory.class)
public interface SysClientApi {

    @Operation(summary = "保存日志")
    @PostMapping("/logRecord/add")
    ResultBean<Boolean> addLogRecord(@RequestBody @Validated LogRecordPostDTO logRecordDTO);

    @Operation(summary = "查询用户全局配置")
    @PostMapping(value = "/config/getUserConfigs")
    ResultBean<List<ConfigVO>> getUserConfigs();

    @Operation(summary = "查询应用全局配置")
    @PostMapping(value = "/config/getAppConfigs")
    ResultBean<List<ConfigVO>> getAppConfigs(@RequestParam("appId") String appId, @RequestParam("confType") Integer confType);
}
