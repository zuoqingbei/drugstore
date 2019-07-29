package com.hlsofttech.delivery.platform.meituan.request;

import lombok.Data;

/**
 * @Description: 创建门店
 * @Date: 2019/7/24 17:21
 * @Author: suncy
 **/
@Data
public class CreateShopRequest extends AbstractRequest {

    /**
     * 取货门店id，即合作方向美团提供的门店id
     */
    private String shopId;
    /**
     * 门店名称 说明：门店名称格式请按照 【XX品牌-XX店】填写，
     * 例：百果园-望京店，注：该名称需与实体门店门牌保持一致，保证骑手取货可确认门店。
     */
    private String shopName;
    /**
     * 说明：品类需按门店真实配送品类选择，如无法判断可咨询您的销售经理。
     * https://peisong.meituan.com/open/doc#section4-5
     */
    private Integer category;
    /**
     * 说明：品类需按门店真实配送品类选择，如无法判断可咨询您的销售经理。
     * https://peisong.meituan.com/open/doc#section4-5
     */
    private Integer secondCategory;
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
     * 坐标类型，0：火星坐标（高德，腾讯地图均采用火星坐标） 1：百度坐标 （默认值为0）
     */
    private Integer coordinateType;
    /**
     * 配送服务代码，详情见合同
     * <p>
     * 飞速达:4002
     * <p>
     * 快速达:4011
     * <p>
     * 及时达:4012
     * <p>
     * 集中送:4013
     * <p>
     * 例如：4011,4012(多个英文逗号隔开)
     */
    private String deliveryServiceCodes;
    /**
     * 营业时间
     * <p>
     * 例：[{"beginTime":"00:00","endTime":"24:00"}]
     * <p>
     * 注：传入后美团根据区域可配送时间取交集时间作为门店配送时间
     */
    private String businessHours;


}
