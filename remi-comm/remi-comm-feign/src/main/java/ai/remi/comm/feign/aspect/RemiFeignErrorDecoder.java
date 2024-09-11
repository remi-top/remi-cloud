package ai.remi.comm.feign.aspect;

import ai.remi.comm.feign.exception.BadRequestException;
import ai.remi.comm.feign.exception.NotFoundException;
import ai.remi.comm.feign.exception.OpenFeignException;
import feign.Response;
import feign.codec.ErrorDecoder;

public class RemiFeignErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {

        return switch (response.status()) {
            case 400 -> new BadRequestException();
            case 404 -> new NotFoundException("Remi Feign ");
            default -> new OpenFeignException("Remi Feign error");
        };
    }
}
