package ai.remi.boot.api.factory;

import ai.remi.boot.api.client.IamClientApi;
import ai.remi.boot.api.fallback.IamClientFallback;
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

