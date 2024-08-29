package ai.remi.boot.server.exception;

import ai.remi.comm.exception.custom.BusinessException;

public class BpmServiceException extends BusinessException {

    public BpmServiceException(String code, String message) {
        super(code, message);
    }

    public BpmServiceException(String code, Throwable cause) {
        super(code, cause);
    }

    public BpmServiceException(String code, String message, Throwable cause) {
        super(code, message, cause);
    }

    public BpmServiceException(String code, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(code, message, cause, enableSuppression, writableStackTrace);
    }
}
