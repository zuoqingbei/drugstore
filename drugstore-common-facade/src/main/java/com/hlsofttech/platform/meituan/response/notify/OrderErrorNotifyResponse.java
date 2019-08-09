package com.hlsofttech.delivery.platform.meituan.response.notify;

import com.hlsofttech.delivery.platform.meituan.constants.ExceptionCode;
import lombok.Data;

/***
 * @Description: 订单异常回调信息
 * @Date: 2019/7/30 14:14
 * @Author: suncy
 **/
@Data
public class OrderErrorNotifyResponse extends AbstractNotifyResponse {

    /**
     * 配送活动标识
     */
    private Long delivery_id;
    /**
     * 美团配送内部订单id，最长不超过32个字符
     */
    private String mt_peisong_id;
    /**
     * 外部订单号，最长不超过32个字符
     */
    private String order_id;
    /**
     * 异常ID，用来唯一标识一个订单异常信息。接入方用此字段用保证接口调用的幂等性。
     */
    private Long exception_id;
    /**
     * 订单异常代码，当前可能的值为：
     * <p>
     * 10001：顾客电话关机
     * <p>
     * 10002：顾客电话已停机
     * <p>
     * 10003：顾客电话无人接听
     * <p>
     * 10004：顾客电话为空号
     * <p>
     * 10005：顾客留错电话
     * <p>
     * 10006：联系不上顾客其他原因
     * <p>
     * 10101：顾客更改收货地址
     * <p>
     * 10201：送货地址超区
     * <p>
     * 10202：顾客拒收货品
     * <p>
     * 10203：顾客要求延迟配送
     * <p>
     * 10401：商家关店/未营业
     */
    private ExceptionCode exception_code;
    /**
     * 订单异常详细信息
     */
    private String exception_descr;
    /**
     * 配送员上报订单异常的时间，格式为long，时区为GMT+8，距离Epoch(1970年1月1日) 以秒计算的时间，即unix-timestamp。
     */
    private Long exception_time;
    /**
     * 上报订单异常的配送员姓名
     */
    private String courier_name;
    /**
     * 上报订单异常的配送员电话
     */
    private String courier_phone;
}
