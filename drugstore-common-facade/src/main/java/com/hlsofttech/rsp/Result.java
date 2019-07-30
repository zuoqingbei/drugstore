package com.hlsofttech.rsp;

import com.hlsofttech.exception.CommonBizException;
import com.hlsofttech.exception.ExpCodeEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author zuoqb
 * @Date 2017/10/27 下午10:59
 * restful接口通用返回结果
 */
@Data
public class Result<T> implements Serializable {

    /**
     * 执行结果
     */
    private boolean isSuccess;

    /**
     * 错误码
     */
    private String errorCode;

    /**
     * 错误原因
     */
    private String message;

    /**
     * 返回数据
     */
    private T data;

    /**
     * 返回成功的结果
     *
     * @param data 需返回的结果
     * @param <T>
     * @return
     */
    public static <T> Result<T> newSuccessResult(T data) {
        Result<T> result = new Result<>();
        result.isSuccess = true;
        result.data = data;
        return result;
    }

    /**
     * 返回成功的结果
     *
     * @param <T>
     * @return
     */
    public static <T> Result<T> newSuccessResult() {
        Result<T> result = new Result<>();
        result.isSuccess = true;
        return result;
    }

    /**
     * 返回失败的结果
     * PS：返回"未知异常"
     *
     * @param <T>
     * @return
     */
    public static <T> Result<T> newFailureResult() {
        Result<T> result = new Result<>();
        result.isSuccess = false;
        result.errorCode = ExpCodeEnum.UNKNOW_ERROR.getCode();
        result.message = ExpCodeEnum.UNKNOW_ERROR.getMessage();
        return result;
    }

    public static <T> Result<T> newFailureResult(Exception exception) {
        Result<T> result = new Result<>();
        result.isSuccess = false;
        result.errorCode = ExpCodeEnum.SERVER_ERROR.getCode();
        result.message = exception.getMessage();
        return result;
    }

    /**
     * 返回失败的结果
     *
     * @param commonBizException 异常
     * @param <T>
     * @return
     */
    public static <T> Result<T> newFailureResult(CommonBizException commonBizException) {
        Result<T> result = new Result<>();
        result.isSuccess = false;
        result.errorCode = commonBizException.getCodeEnum().getCode();
        result.message = commonBizException.getCodeEnum().getMessage();
        return result;
    }

    /**
     * 返回失败的结果
     *
     * @param commonBizException 异常
     * @param data               需返回的数据
     * @param <T>
     * @return
     */
    public static <T> Result<T> newFailureResult(CommonBizException commonBizException, T data) {
        Result<T> result = new Result<>();
        result.isSuccess = false;
        result.errorCode = commonBizException.getCodeEnum().getCode();
        result.message = commonBizException.getCodeEnum().getMessage();
        result.data = data;
        return result;
    }

    public Result(boolean isSuccess, String errorCode, String message, T data) {
        this.isSuccess = isSuccess;
        this.errorCode = errorCode;
        this.message = message;
        this.data = data;
    }

    public Result(boolean isSuccess, String message, T data) {
        this.isSuccess = isSuccess;
        this.message = message;
        this.data = data;
    }

    public Result(boolean isSuccess, String message) {
        this.isSuccess = isSuccess;
        this.message = message;
    }

    public Result() {
    }
}
