package ai.remi.sys.api.factory;

import ai.remi.sys.api.client.IamClientApi;
import ai.remi.sys.api.fallback.IamClientFallback;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class IamClientFactory implements FallbackFactory<IamClientApi> {

    @Override
    public IamClientApi create(Throwable cause) {
        IamClientFallback fallback = new IamClientFallback();
        fallback.setCause(cause);
        return fallback;
    }
}

