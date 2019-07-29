package com.hlsofttech.entity.delivery.dto;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;

/**
 * 订单商品信息
 */
@Data
public class GoodDTO {
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


