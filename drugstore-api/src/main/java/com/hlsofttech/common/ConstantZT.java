package com.hlsofttech.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/***
 * @Description: 中台为购药平台提供的定制信息
 * @Date: 2019/8/16 16:42
 * @Author: suncy
 **/
@Component
public class ConstantZT {
    /**
     * 广告位ID
     */
    public static String adZoneId;

    @Value("${common.wxpay.notifyUrl}")
    public void setAdZoneId(String adZoneId) {
        this.adZoneId = adZoneId;
    }
}
