package com.hlsofttech.delivery.platform.meituan.request;

import lombok.Data;

/**
 * @Description: 查询订单参数
 * @Date: 2019/7/24
 * @Author: suncy
 **/
@Data
public class QueryOrderRequest extends AbstractRequest {

    /**
     * 配送活动标识
     */
    private Long deliveryId;

    /**
     * 美团配送内部订单id，最长不超过32个字符
     */
    private String mtPeisongId;
}
