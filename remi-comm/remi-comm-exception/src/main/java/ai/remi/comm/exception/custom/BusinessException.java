package ai.remi.comm.exception.custom;

import ai.remi.comm.core.constant.Constants;
import ai.remi.comm.exception.util.MessageUtils;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author dianjiu【公众号 点九开源】
 * @email dianjiuxyz@gmail.com
 * @desc BusinessException
 */
@Data
@NoArgsConstructor
public class BusinessException extends RuntimeException {

    @Getter
    @Setter
    private String code;
    @Getter
    @Setter
    private String key;
    @Getter
    @Setter
    private Object[] params;
    @Getter
    @Setter
    private String message;

    public BusinessException(String key) {
        super(MessageUtils.getMessage(key));
        this.code = Constants.ERROR;
        this.message = MessageUtils.getMessage(key);
    }

    public BusinessException(String key, Object[] params) {
        super(MessageUtils.getMessage(key, params));
        this.code = Constants.ERROR;
        this.message = MessageUtils.getMessage(key, params);
    }

    public BusinessException(String code, String key) {
        super(MessageUtils.getMessage(key));
        this.code = code;
        this.message = MessageUtils.getMessage(key);
    }

    public BusinessException(String code, String key, Object[] params) {
        super(MessageUtils.getMessage(key, params));
        this.code = code;
        this.message = MessageUtils.getMessage(key, params);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

    public BusinessException(String code, Throwable cause) {
        super(cause);
        this.code = code;
    }

    public BusinessException(String code, String key, Throwable cause) {
        super(MessageUtils.getMessage(key), cause);
        this.code = code;
        this.message = MessageUtils.getMessage(key);
    }

    public BusinessException(String code, String key, Object[] params, Throwable cause) {
        super(MessageUtils.getMessage(key, params), cause);
        this.code = code;
        this.message = MessageUtils.getMessage(key, params);
    }

    public BusinessException(String code, String key, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(MessageUtils.getMessage(key), cause, enableSuppression, writableStackTrace);
        this.code = code;
        this.message = MessageUtils.getMessage(key);
    }

    public BusinessException(String code, String key, Object[] params, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(MessageUtils.getMessage(key, params), cause, enableSuppression, writableStackTrace);
        this.code = code;
        this.message = MessageUtils.getMessage(key, params);
    }
}

