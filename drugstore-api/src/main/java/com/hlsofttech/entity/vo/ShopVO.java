package com.hlsofttech.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/***
 * @Author: suntf
 * @Description:店铺对象
 * @Date: 2019/8/16
 **/
@Data
public class ShopVO {
    /**
     * 店铺id
     */
    @ApiModelProperty(value = "店铺id")
    private Long shopId;
    /**
     * 店铺名
     */
    @ApiModelProperty(value = "店铺名")
    private String shopName;
    /**
     * 药店LOGO
     */
    @ApiModelProperty(value = "药店LOGO")
    private String logoUrl;
    /**
     * 简介
     */
    @ApiModelProperty(value = "简介")
    private String introduce;
    /**
     * 商家电话
     */
    @ApiModelProperty(value = "商家电话")
    private Long mobile;
    /**
     * 评价星级
     */
    @ApiModelProperty(value = "评价星级")
    private String scope;
    /**
     * 销量
     */
    @ApiModelProperty(value = "销量")
    private String salesVolume;

//////////////////////////////////////////////////////////////
    /**
     * 月销量
     */
    @ApiModelProperty(value = "月销量")
    private String monthSalesVolume;
    /**
     * 起送价
     */
    @ApiModelProperty(value = "起送价")
    private String startDeliveryPrice;
    /**
     * 配送费
     */
    @ApiModelProperty(value = "配送费")
    private String deliveryPrice;
    /**
     * 平均配送时间
     */
    @ApiModelProperty(value = "平均配送时间")
    private String deliveryTime;
    /**
     * 距离
     */
    @ApiModelProperty(value = "距离")
    private String distance;
    /**
     * 营业时间
     */
    @ApiModelProperty(value = "营业时间")
    private String saleTime;
    /**
     * 地址
     */
    @ApiModelProperty(value = "地址")
    private String shopAddress;
}
