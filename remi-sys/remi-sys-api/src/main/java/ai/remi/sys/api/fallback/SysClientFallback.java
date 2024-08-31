package ai.remi.sys.api.fallback;

import ai.remi.comm.core.result.ResultBean;
import ai.remi.sys.api.client.SysClientApi;
import ai.remi.sys.domain.dto.post.LogRecordPostDTO;
import ai.remi.sys.domain.vo.ConfigVO;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class SysClientFallback implements SysClientApi {
    @Setter
    private Throwable cause;

    @Override
    public ResultBean<Boolean> addLogRecord(LogRecordPostDTO logRecordDTO) {
        log.error("addLogRecord = {}", cause.getMessage());
        return null;
    }

    @Override
    public ResultBean<List<ConfigVO>> getUserConfigs() {
        log.error("getUserConfigs = {}", cause.getMessage());
        return null;
    }

    @Override
    public ResultBean<List<ConfigVO>> getAppConfigs(String appId, Integer confType) {
        log.error("getAppConfigs = {}", cause.getMessage());
        return null;
    }

}
