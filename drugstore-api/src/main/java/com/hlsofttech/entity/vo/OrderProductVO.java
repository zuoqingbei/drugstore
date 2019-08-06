package com.hlsofttech.entity.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 订单产品表
 * </p>
 *
 * @author sevenSeconds
 * @since 2019-02-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class OrderProductVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 订单id
     */
    private Long orderId;
    /**
     * 订单号
     */
    private String orderCode;
    /**
     * 客户id
     */
    private Long memberId;
    /**
     * productId
     */
    private Long productId;
    /**
     * skuId
     */
    private Long skuId;
    /**
     * erpSkuId
     */
    private Long erpSkuId;
    /**
     * sku名称
     */
    private String skuName;
    /**
     * 购买数量
     */
    private Integer quantity;
    /**
     * 现价
     */
    private Long currentPrice;
}
