package com.hlsofttech.delivery.platform.meituan.request;

import com.hlsofttech.delivery.platform.meituan.constants.CancelOrderReasonId;
import lombok.Data;

/**
 * @Description: 取消订单
 * @Date: 2019/7/24
 * @Author: suncy
 **/
@Data
public class CancelOrderRequest extends AbstractRequest {
    /**
     * 配送活动标识
     */
    private long deliveryId;

    /**
     * 美团配送内部订单id，最长不超过32个字符
     */
    private String mtPeisongId;

    /**
     * 取消原因类别，默认为接入方原因
     */
    private CancelOrderReasonId cancelOrderReasonId;

    /**
     * 详细取消原因，最长不超过256个字符
     */
    private String cancelReason;
}
