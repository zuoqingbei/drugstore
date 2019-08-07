package com.hlsofttech.rsp;

import lombok.Data;

import java.io.Serializable;

/***
 * @Author: suntf
 * @Description:请求药质网返回对象
 * @Date: 2019/8/7
 **/
@Data
public class ResultYaoZhi implements Serializable {

    /**
     * 返回码
     */
    private Integer code;
    /**
     * 返回信息
     */
    private String msg;
    /**
     * 返回数据
     */
    private String data;
}
