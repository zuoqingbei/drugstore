package com.hlsofttech.platform.meituan.response.notify;

import lombok.Data;

/***
 * @Description: 回调统一参数
 * @Date: 2019/7/30 11:14
 * @Author: suncy
 **/
@Data
public class AbstractNotifyResponse {
    /**
     * 开放平台分配的appkey，合作方唯一标识。
     */
    protected String appkey;
    /**
     * 时间戳，格式为long，时区为GMT+8，当前距 离Epoch（1970年1月1日) 以秒计算的时间，即 unix-timestamp。
     */
    protected String timestamp;
    /**
     * 数据签名
     */
    protected String sign;
}
