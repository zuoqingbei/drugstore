package com.hlsofttech.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 药店-相关设置VO
 *
 * @author suncy
 * @date 2019-08-16
 */
@Data
public class DrugsShopSettingsVO implements Serializable {

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
     * 配送方式（0=店家配送，1=骑手配送）
     */
    private String deliveryWay;
    /**
     * 常规时间开始
     */
    private String routineTimeBegin;
    /**
     * 常规时间结束
     */
    private String routineTimeEnd;
    /**
     * 特殊时间开始
     */
    private String specialTimeBegin;
    /**
     * 特殊时间结束
     */
    private String specialTimeEnd;
    /**
     * 状态（0启用1=停用）
     */
    private String status;
}
