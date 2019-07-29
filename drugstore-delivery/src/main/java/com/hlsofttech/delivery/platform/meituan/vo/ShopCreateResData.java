package com.hlsofttech.delivery.platform.meituan.vo;

import lombok.Data;

/***
 * @Description:返回信息
 * @Date: 2019/7/24 17:37
 * @Author: suncy
 **/
@Data
public class ShopCreateResData {

    /**
     * 门店ID
     */
    private String shopId;
    /**
     * 配送唯一标识
     */
    private String status;
}
