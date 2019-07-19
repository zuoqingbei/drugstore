package com.hlsofttech.common;

import java.io.Serializable;

import com.hlsofttech.base.BaseResult;
import com.hlsofttech.enumeration.PublicResultConstant;


/**
 * @author zuoqb
 * @since 2018-05-03
 */
public class PublicResult <T> extends BaseResult<T> implements Serializable{

    public static final String DEFAULT_CODE = "90000003";
    public PublicResult(PublicResultConstant publicResultConstant, T data) {
        super(publicResultConstant.getResult(), publicResultConstant.getMsg(), data);
    }
    public PublicResult(PublicResultConstant publicResultConstant,String errorMsg, T data) {
        super(publicResultConstant.getResult(), publicResultConstant.getMsg(),errorMsg, data);
    }
    public PublicResult(String message, T data) {
        super(DEFAULT_CODE, message, data);
    }
}
