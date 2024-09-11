package ai.remi.comm.feign.exception;

import ai.remi.comm.core.constant.Constants;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Remi
 * @email remi@dianjiu.cc
 * @desc OpenFeignException
 */
@Data
@NoArgsConstructor
public class OpenFeignException extends RuntimeException {

    @Getter
    @Setter
    private String code;
    @Getter
    @Setter
    private String message;

    @Getter
    @Setter
    private Object data;

    public OpenFeignException(Throwable cause) {
        super(cause);
    }

    public OpenFeignException(String message) {
        super(message);
        this.code = Constants.ERROR;
        this.message = message;
    }

    public OpenFeignException(String code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public OpenFeignException(String code, Throwable cause) {
        super(cause);
        this.code = code;
        this.message = cause.getMessage();
    }

    public OpenFeignException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
        this.message = message;
    }

    public OpenFeignException(String code, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.code = code;
        this.message = message;
    }
}

