package com.hlsofttech.delivery.platform.meituan.request;

import lombok.Data;

/**
 * @Description: 查询门店配送区域
 * @Date: 2019/7/24
 * @Author: suncy
 **/
@Data
public class QueryShopAreaRequest extends AbstractRequest {

    /**
     * 配送服务代码
     */
    private Integer deliveryServiceCode;

    /**
     * 取货门店id
     */
    private String shopId;
}
