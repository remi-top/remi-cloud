package ai.remi.comm.core.result;

import ai.remi.comm.core.constant.Constants;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

/**
 * @author dianjiu【公众号 点九开源】
 * @email dianjiuxyz@gmail.com
 * @desc ResultBean
 */
@Data
@SuperBuilder
public class ResultBean<T> implements Serializable {

    private static final long serialVersionUID = 8676131899637805509L;

    /**
     * 成功
     */
    public static final String SUCCESS = Constants.SUCCESS;

    /**
     * 失败
     */
    public static final String ERROR = Constants.ERROR;

    // 返回编码
    private String code;
    // 返回信息
    private String msg;
    // 返回数据封装
    @Builder.Default
    private T data = (T) "";

    public ResultBean() {
    }

    private static <T> ResultBean<T> restResult(String code, String msg, T data) {
        ResultBean<T> apiResult = new ResultBean<>();
        apiResult.setCode(code);
        apiResult.setData(data);
        apiResult.setMsg(msg);
        return apiResult;
    }

    /**
     * 返回成功消息
     *
     * @return 成功消息
     */
    public static <T> ResultBean<T> success() {
        return restResult(SUCCESS, null, null);
    }

    public static <T> ResultBean<T> success(String msg) {
        return restResult(SUCCESS, msg, null);
    }

    public static <T> ResultBean<T> success(T data) {
        return restResult(SUCCESS, null, data);
    }

    public static <T> ResultBean<T> success(String msg, T data) {
        return restResult(SUCCESS, msg, data);
    }

    public static <T> ResultBean<T> error() {
        return restResult(ERROR, null, null);
    }

    public static <T> ResultBean<T> error(String msg) {
        return restResult(ERROR, msg, null);
    }

    public static <T> ResultBean<T> error(T data) {
        return restResult(ERROR, null, data);
    }

    public static <T> ResultBean<T> error(String msg, T data) {
        return restResult(ERROR, msg, data);
    }

    public static <T> ResultBean<T> error(String code, String msg, T data) {
        return restResult(code, msg, data);
    }
}
