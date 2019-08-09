package com.hlsofttech.entity.product.dto;

import lombok.Data;

/***
 * @Author: suntf
 * @Description:购物车实体类
 * @Date: 2019/8/8
 **/
@Data
public class CartOfShopDto {
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 店铺id
     */
    private Integer shopId;
    /**
     * 店铺名
     */
    private String shopName;
    /**
     * 经度
     */
    private double longitude;
    /**
     * 纬度
     */
    private double latitude;
    /**
     * 商品id
     */
    private Long skuId;
    /**
     * 商品名
     */
    private String skuName;
    /**
     * 商品图片
     */
    private String skuImg;
    /**
     * 改变后的数量
     */
    private Integer quantity;
    /**
     * 规格属性
     */
    private String prop;
    /**
     * 市场价
     */
    private Long marketPrice;
    /**
     * 现卖价
     */
    private Long salePrice;
    /**
     * 距离用户距离-单位千米
     */
    private double range;
}
