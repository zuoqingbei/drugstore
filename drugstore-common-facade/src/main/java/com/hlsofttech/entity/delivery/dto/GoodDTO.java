package com.hlsofttech.entity.delivery.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 订单商品信息
 * 以美团为例，后期接入别的可以更改本实体进行扩展
 */
@Data
public class GoodDTO implements Serializable {
    /**
     * 商品数量
     */
    private int goodCount;

    /**
     * 商品名称
     */
    private String goodName;

    /**
     * 商品价格，单位为元
     */
    private BigDecimal goodPrice;

    /**
     * 商品单位，如个
     */
    private String goodUnit;
}


