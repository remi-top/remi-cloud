package ai.remi.demo.api.fallback;

import ai.remi.demo.api.client.FeignClientApi;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FeignClientFallback implements FeignClientApi {
    @Setter
    private Throwable cause;

    @Override
    public String findById(int goodsId) {
        log.error("findById = {}", cause.getMessage());
        return null;
    }
}
