package com.hlsofttech.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 药店-配送范围VO
 *
 * @author suncy
 * @date 2019-08-16
 */
@Data
public class DrugsShopDeliveryScopeVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private String id;
    /**
     * 药店ID
     */
    private String drugsShopId;
    /**
     * 自定义范围名称
     */
    private String title;
    /**
     * 起送价
     */
    private Integer startPrice;
    /**
     * 配送范围开始
     */
    private Integer scopeBegin;
    /**
     * 配送范围结束
     */
    private Integer scopeEnd;
    /**
     * 配送费（分）
     */
    private Integer deliveryFee;
    /**
     * 状态（0启用1=停用）
     */
    private String status;
}
