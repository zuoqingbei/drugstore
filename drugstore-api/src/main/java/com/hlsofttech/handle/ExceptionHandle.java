package com.hlsofttech.handle;

import com.hlsofttech.exception.CommonBizException;
import com.hlsofttech.exception.ExpCodeEnum;
import com.hlsofttech.rsp.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author zuoqb
 * @Date 2017/10/27 下午11:02
 * REST接口的通用异常处理
 */
@ControllerAdvice
@ResponseBody
public class ExceptionHandle {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 业务异常处理
     *
     * @param exception
     * @param <T>
     * @return
     */
    @ExceptionHandler(CommonBizException.class)
    public <T> Result<T> exceptionHandler(CommonBizException exception) {
        return Result.newFailureResult(exception);
    }

    /**
     * 请求方法不正确
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Result HttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException exception) {
        throw new CommonBizException(ExpCodeEnum.HTTP_REQ_METHOD_ERROR);
    }

    /**
     * 系统异常处理
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException exception) {
        logger.error("参数异常 ", exception);
        BindingResult bindingResult = exception.getBindingResult();
        StringBuffer errMessage = new StringBuffer(bindingResult.getFieldErrors().size() * 16);
        errMessage.append("Invalid  Request:");
        for (int i = 0; i < bindingResult.getFieldErrors().size(); i++) {
            if (i > 0) {
                errMessage.append(",");
            }
            FieldError fieldError = bindingResult.getFieldErrors().get(i);
            errMessage.append(fieldError.getField());
            errMessage.append(":");
            errMessage.append(fieldError.getDefaultMessage());
        }
        return new Result(false, errMessage.toString());
    }

    /**
     * 系统异常处理
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(Exception.class)
    public Result sysExpHandler(Exception exception) {
        logger.error("系统异常 ", exception);
        return new Result(false, exception.getMessage());
    }

}
