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
    private String deliveryId;

    /**
     * 美团配送内部订单id，最长不超过32个字符
     */
    private String mtPeisongId;

    /**
     * 外部订单号，最长不超过32个字符
     */
    private String orderId;

    /**
     * 订单状态代码
     */
    private OrderStatus status;

    /**
     * 配送员姓名（订单已被骑手接单后会返回骑手信息）
     */
    private String courierName;

    /**
     * 配送员电话（订单已被骑手接单后会返回骑手信息）
     */
    private String courierPhone;

    /**
     * 取消原因id
     */
    private int cancelReasonId;

    /**
     * 取消原因详情，最长不超过256个字符
     */
    private String cancelReason;

}
