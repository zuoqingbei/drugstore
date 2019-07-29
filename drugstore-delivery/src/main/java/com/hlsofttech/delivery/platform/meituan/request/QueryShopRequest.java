package com.hlsofttech.delivery.platform.meituan.request;

import lombok.Data;

/**
 * @Description: 查询门店信息
 * @Date: 2019/7/25 10:01
 * @Author: suncy
 **/
@Data
public class QueryShopRequest extends AbstractRequest {

    /**
     * 门店ID
     */
    private String shopId;
}
