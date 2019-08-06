package com.hlsofttech.entity.erp;


import lombok.Data;

/***
 * @Description: ERP参数传递类
 * @Date: 2019/8/6 9:46
 * @Author: suncy
 **/
@Data
public class AbstractERPDTO {

    /**
     * 每个appkey会绑定一个secret，用于计算签名。请妥善保管secret，避免泄密。如果secret意外泄露，可要求重新生成。
     */
    private String appkey;

    /**
     * 时间戳，格式为long，时区为GMT+8，即合作方调用接口时距离Epoch（1970年1月1日) 以秒计算的时间（unix-timestamp）。
     */
    private long timestamp;

    /**
     * API请求参数的签名计算结果。
     */
    private String sign;


}
