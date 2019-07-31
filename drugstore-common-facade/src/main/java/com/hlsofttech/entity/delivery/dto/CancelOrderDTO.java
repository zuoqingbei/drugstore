package com.hlsofttech.entity.delivery.dto;

import com.hlsofttech.constant.delivery.CancelOrderReasonId;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description: 取消订单
 * 以美团为例，后期接入别的可以更改本实体进行扩展
 * @Date: 2019/7/24
 * @Author: suncy
 **/
@Data
public class CancelOrderDTO implements Serializable {
    /**
     * 配送活动标识
     */
    private long deliveryId;

    /**
     * 配送内部订单id，最长不超过32个字符
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
