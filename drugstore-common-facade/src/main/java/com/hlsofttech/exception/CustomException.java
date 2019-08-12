package com.hlsofttech.exception;

/**
 *功能描述: 自定义异常(CustomException)
 *
 * @author suntf
 * @date 2019/8/12
 */
public class CustomException extends RuntimeException {

    public CustomException(String msg){
        super(msg);
    }

    public CustomException() {
        super();
    }
}