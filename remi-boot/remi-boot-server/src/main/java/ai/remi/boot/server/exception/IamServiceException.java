package ai.remi.boot.server.exception;

import ai.remi.comm.exception.custom.BusinessException;

public class IamServiceException extends BusinessException {

    public IamServiceException(String code, String message) {
        super(code, message);
    }

    public IamServiceException(String code, Throwable cause) {
        super(code, cause);
    }

    public IamServiceException(String code, String message, Throwable cause) {
        super(code, message, cause);
    }

    public IamServiceException(String code, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(code, message, cause, enableSuppression, writableStackTrace);
    }
}
