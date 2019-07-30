package com.hlsofttech.delivery.platform.meituan.vo;

import com.hlsofttech.delivery.platform.meituan.constants.OrderStatus;
import lombok.Data;

/**
 * 订单状态信息
 */
@Data
public class OrderStatusInfo {
    /**
     * 配送活动标识
     */
    private String delivery_id;

    /**
     * 美团配送内部订单id，最长不超过32个字符
     */
    private String mt_peisong_id;

    /**
     * 外部订单号，最长不超过32个字符
     */
    private String order_id;

    /**
     * 订单状态代码
     */
    private OrderStatus status;

    /**
     * 配送员姓名（订单已被骑手接单后会返回骑手信息）
     */
    private String courier_name;

    /**
     * 配送员电话（订单已被骑手接单后会返回骑手信息）
     */
    private String courier_phone;

    /**
     * 取消原因id
     */
    private int cancel_reason_id;

    /**
     * 取消原因详情，最长不超过256个字符
     */
    private String cancel_reason;

}
