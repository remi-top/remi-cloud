package ai.remi.comm.web.exception;

import ai.remi.comm.core.result.ResultBean;
import ai.remi.comm.exception.custom.BusinessException;
import ai.remi.comm.exception.enums.CommExceptionCode;
import ai.remi.comm.exception.util.MessageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

/**
 * @author dianjiu【公众号 点九开源】
 * @email dianjiuxyz@gmail.com
 * @desc GlobalExceptionHandler
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 全局异常
     *
     * @return R
     */
    @ResponseBody
    @ExceptionHandler({Exception.class, Throwable.class})
    public ResultBean<?> exception(Exception e) {
        log.error("全局异常信息 ==> {}", e);
        return ResultBean.error(MessageUtils.getMessage("system.error"));
    }

    /**
     * 系统异常
     *
     * @return R
     */
    @ResponseBody
    @ExceptionHandler(RuntimeException.class)
    public ResultBean<?> runtimeException(Exception e) {
        log.error("全局系统异常信息 ==> {}", e);
        return ResultBean.error(e.getMessage());
    }

    /**
     * 业务异常
     */
    @ResponseBody
    @ExceptionHandler(BusinessException.class)
    public ResultBean<?> businessException(BusinessException e) {
        log.error("全局业务异常信息 ==>{}:{}", e.getCode(), e.getMessage());
        return ResultBean.error(e.getCode(), e.getMessage());
    }

    /**
     * 上传异常
     */
    @ResponseBody
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    //@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultBean handleMaxUploadSizeExceededException(MaxUploadSizeExceededException e) {
        log.error("文件上传太大异常 ==> {}", e.getMessage(), e);
        return ResultBean.error("Upload file too large");
    }

    /**
     * 非法参数异常
     */
    @ResponseBody
    @ExceptionHandler(IllegalArgumentException.class)
    //@ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResultBean<?> illegalArgumentException(IllegalArgumentException e) {
        log.error("非法参数异常 ==> {}", e.getMessage(), e);
        return ResultBean.error(CommExceptionCode.ILLEGAL_ARGUMENT.getCode(), MessageUtils.getMessage(CommExceptionCode.ILLEGAL_ARGUMENT.getKey()), null);
    }

    /**
     * 处理Get请求中 使用@Valid 验证路径中请求实体校验失败后抛出的异常，详情继续往下看代码
     */
    @ResponseBody
    @ExceptionHandler(BindException.class)
    //@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResultBean<?> bindExceptionHandler(BindException e) {
        String message = e.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(", "));
        log.error("绑定异常 ==> {}", message, e);
        return ResultBean.error(CommExceptionCode.ILLEGAL_ARGUMENT.getCode(), MessageUtils.getMessage(CommExceptionCode.ILLEGAL_ARGUMENT.getKey()), null);
    }

    /**
     * 处理请求参数格式错误 @RequestParam上validate失败后抛出的异常是javax.validation.ConstraintViolationException
     */
    @ResponseBody
    @ExceptionHandler(ConstraintViolationException.class)
    //@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResultBean<?> constraintViolationExceptionHandler(ConstraintViolationException e) {
        String message = e.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.joining(", "));
        log.error("请求参数格式错误 ==> {}", message, e);
        return ResultBean.error(CommExceptionCode.ILLEGAL_ARGUMENT.getCode(), MessageUtils.getMessage(CommExceptionCode.ILLEGAL_ARGUMENT.getKey()), null);
    }

    /**
     * 处理请求参数格式错误 @RequestBody上validate失败后抛出的异常是MethodArgumentNotValidException异常。
     */
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    //@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResultBean<?> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        String message = e.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(", "));
        log.error("请求参数格式错误 ==> {}", message, e);
        return ResultBean.error(CommExceptionCode.ILLEGAL_ARGUMENT.getCode(), MessageUtils.getMessage(CommExceptionCode.ILLEGAL_ARGUMENT.getKey()), null);
    }

    /**
     * 处理请求数据格式异常，比如JSON格式错误。
     */
    @ResponseBody
    @ExceptionHandler(HttpMessageNotReadableException.class)
    //@ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResultBean<?> httpMessageNotReadableExceptionHandler(HttpMessageNotReadableException e) {
        String message = e.getCause() == null ? e.getMessage() : e.getCause().getMessage();
        log.error("请求数据格式错误 ==> {}", message, e);
        return ResultBean.error(CommExceptionCode.ILLEGAL_ARGUMENT.getCode(), MessageUtils.getMessage(CommExceptionCode.ILLEGAL_ARGUMENT.getKey()), null);
    }

    /**
     * 缺少请求头参数
     */
    @ResponseBody
    @ExceptionHandler(MissingRequestHeaderException.class)
    //@ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResultBean<?> missingRequestHeaderException(MissingRequestHeaderException e) {
        String message = e.getCause() == null ? e.getMessage() : e.getCause().getMessage();
        log.error("缺少请求头参数 ==> {}", message, e);
        return ResultBean.error(CommExceptionCode.ILLEGAL_ARGUMENT.getCode(), MessageUtils.getMessage(CommExceptionCode.ILLEGAL_ARGUMENT.getKey()), null);
    }


    @ResponseBody
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    //@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public ResultBean<?> httpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        String message = e.getCause() == null ? e.getMessage() : e.getCause().getMessage();
        log.error("请求方法类型错误 ==> {}", message, e);
        return ResultBean.error(CommExceptionCode.ILLEGAL_ARGUMENT.getCode(), MessageUtils.getMessage(CommExceptionCode.ILLEGAL_ARGUMENT.getKey()), null);
    }

    @ResponseBody
    @ExceptionHandler(MissingServletRequestParameterException.class)
    //@ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResultBean<?> missingServletRequestParameterException(MissingServletRequestParameterException e) {
        String message = String.format("参数 %s 不存在", e.getParameterName());
        log.error("请求参数不存在 ==> {}", message, e);
        return ResultBean.error(CommExceptionCode.ILLEGAL_ARGUMENT.getCode(), MessageUtils.getMessage(CommExceptionCode.ILLEGAL_ARGUMENT.getKey()), null);
    }
}


