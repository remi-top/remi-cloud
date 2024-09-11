package ai.remi.comm.exception.enums;

/**
 * 异常code接口
 */
public interface ExceptionCode {
    /**
     * 异常code
     * @return
     */
    String getCode();
    /**
     * 异常消息
     * @return
     */
    String getKey();
}
