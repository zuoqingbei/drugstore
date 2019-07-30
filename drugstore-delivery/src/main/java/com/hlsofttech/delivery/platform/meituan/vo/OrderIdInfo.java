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
    private String mt_peisong_id;
    /**
     * 订单ID
     */
    private String order_id;
    /**
     * 配送活动标识
     */
    private long delivery_id;
    /**
     * 目的地id
     */
    private String destination_id;
    /**
     * 订单配送距离
     */
    private Integer delivery_distance;
    /**
     * 订单配送价格（面向商家）
     */
    private Double delivery_fee;
    /**
     * 路区信息
     */
    private String road_area;
}
