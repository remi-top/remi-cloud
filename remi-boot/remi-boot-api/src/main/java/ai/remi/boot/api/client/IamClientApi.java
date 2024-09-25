package ai.remi.boot.api.client;

import ai.remi.comm.core.result.ResultBean;
import ai.remi.comm.feign.aspect.FeignAutoConfiguration;
import ai.remi.boot.api.factory.IamClientFactory;
import ai.remi.boot.domain.dto.post.LogRecordPostDTO;
import ai.remi.boot.domain.vo.ConfigVO;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "remi-boot-web", configuration = FeignAutoConfiguration.class, fallbackFactory = IamClientFactory.class)
public interface IamClientApi {

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
