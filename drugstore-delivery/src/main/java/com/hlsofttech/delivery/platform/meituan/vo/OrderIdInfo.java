package com.hlsofttech.delivery.platform.meituan.vo;

import lombok.Data;

/**
 * 订单id信息
 */
@Data
public class OrderIdInfo {
	/**
     * 配送唯一标识
     */
    private String mtPeisongId;
	/**
     * 订单ID
     */
    private String orderId;
	/**
     * 配送活动标识
     */
    private long deliveryId;
	/**
     * 目的地id
     */
    private String destinationId;
	/**
     * 订单配送距离
     */
    private Integer deliveryDistance;
	/**
     * 订单配送价格（面向商家）
     */
    private Double deliveryFee;
	/**
     * 路区信息
     */
    private String roadArea;
}
