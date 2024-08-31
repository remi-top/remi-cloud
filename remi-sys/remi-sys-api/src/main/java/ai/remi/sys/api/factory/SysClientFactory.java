package ai.remi.sys.api.factory;

import ai.remi.sys.api.client.SysClientApi;
import ai.remi.sys.api.fallback.SysClientFallback;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class SysClientFactory implements FallbackFactory<SysClientApi> {

    @Override
    public SysClientApi create(Throwable cause) {
        SysClientFallback fallback = new SysClientFallback();
        fallback.setCause(cause);
        return fallback;
    }
}

