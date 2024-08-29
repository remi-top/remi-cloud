package ai.remi.boot.api.factory;

import ai.remi.boot.api.client.FeignClientApi;
import ai.remi.boot.api.fallback.FeignClientFallback;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class FeignClientFactory implements FallbackFactory<FeignClientApi> {

    @Override
    public FeignClientApi create(Throwable cause) {
        FeignClientFallback fallback = new FeignClientFallback();
        fallback.setCause(cause);
        return fallback;
    }
}

