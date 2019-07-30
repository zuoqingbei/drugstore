package com.hlsofttech.delivery.platform.meituan.vo;

import lombok.Data;

/***
 * @Description:店铺响应信息
 * @Date: 2019/7/24 17:37
 * @Author: suncy
 **/
@Data
public class ShopInfo {

    /**
     * 取货门店id，即合作方向美团提供的门店id
     */
    private String shop_id;
    /**
     * 城市ID，见城市代码表
     */
    private Integer city;
    /**
     * 说明：品类需按门店真实配送品类选择，如无法判断可咨询您的销售经理。
     * https://peisong.meituan.com/open/doc#section4-5
     */
    private Integer category;
    /**
     * 说明：品类需按门店真实配送品类选择，如无法判断可咨询您的销售经理。
     * https://peisong.meituan.com/open/doc#section4-5
     */
    private Integer second_category;
    /**
     * 门店联系人姓名
     */
    private String contact_name;
    /**
     * 联系电话
     */
    private String contact_phone;
    /**
     * 联系邮箱
     */
    private String contact_email;
    /**
     * 门店地址
     */
    private String shop_address;
    /**
     * 门牌号
     */
    private String shop_address_detail;
    /**
     * 门店经度（火星坐标或百度坐标，和 coordinate_type 字段配合使用），坐标 * （10的六次方），如 116398419
     * 说明：请提供准确坐标，便于骑手取货
     */
    private Integer shop_lng;
    /**
     * 门店纬度（火星坐标或百度坐标，和 coordinate_type 字段配合使用），坐标 * （10的六次方），如 39985005
     * 说明：请提供准确坐标，便于骑手取货
     */
    private Integer shop_lat;
    /**
     * 坐标类型，0：火星坐标（高德，腾讯地图均采用火星坐标） 1：百度坐标 （默认值为0）
     */
    private Integer coordinate_type;
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
    private String delivery_service_codes;
    /**
     * @Description: 配送时间
     * <p>
     * 例：[{"beginTime":"00:00","endTime":"24:00"}]
     * @Date: 2019/7/25 10:10
     * @Author: suncy
     **/
    private String delivery_hours;
    /**
     * 是否支持预约单，0：不支持，1：支持
     */
    private Integer prebook;
    /**
     * 是否支持营业时间外预约单，0：不支持，1：支持
     */
    private Integer prebook_out_of_biz_time;
    /**
     * 预约单时间段，格式为{"start": "0", "end": "2"}，单位为天
     */
    private String prebook_period;


}
