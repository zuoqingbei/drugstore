package com.hlsofttech.entity.delivery.dto;

import lombok.Data;

import java.io.Serializable;


/***
 * @Description: 查询合作方配送范围
 * @Date: 2019/7/30 16:08
 * @Author: suncy
 **/
@Data
public class QueryShopAreaDTO implements Serializable {
    /**
     * 配送服务代码
     */
    private Integer deliveryServiceCode;
    /**
     * 取货门店id
     */
    private String shopId;
}
