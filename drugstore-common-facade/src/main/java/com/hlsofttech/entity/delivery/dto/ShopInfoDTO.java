package com.hlsofttech.entity.delivery.dto;


import lombok.Data;

import java.io.Serializable;

/***
 * @Description: 配送平台创建店铺统一信息
 * @Date: 2019/7/25 13:23
 * @Author: suncy
 **/
@Data
public class ShopInfoDTO implements Serializable {
    /**
     * 取货门店id，
     */
    private String shopId;
    /**
     * 取货门店名称
     */
    private String shopName;
    /**
     * 门店联系人姓名
     */
    private String contactName;
    /**
     * 联系电话
     */
    private String contactPhone;
    /**
     * 联系邮箱
     */
    private String contactEmail;
    /**
     * 门店地址
     */
    private String shopAddress;
    /**
     * 门牌号
     */
    private String shopAddressDetail;
    /**
     * 门店经度（火星坐标或百度坐标，和 coordinate_type 字段配合使用），坐标 * （10的六次方），如 116398419
     * 说明：请提供准确坐标，便于骑手取货
     */
    private Integer shopLng;
    /**
     * 门店纬度（火星坐标或百度坐标，和 coordinate_type 字段配合使用），坐标 * （10的六次方），如 39985005
     * 说明：请提供准确坐标，便于骑手取货
     */
    private Integer shopLat;

    /**
     * @Description: 配送时间
     * <p>
     * 例：[{"beginTime":"00:00","endTime":"24:00"}]
     * @Date: 2019/7/25 10:10
     * @Author: suncy
     **/
    private String deliveryHours;

}
