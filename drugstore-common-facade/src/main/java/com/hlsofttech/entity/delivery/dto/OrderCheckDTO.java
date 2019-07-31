package com.hlsofttech.entity.delivery.dto;

import lombok.Data;

import java.io.Serializable;

/***
 * @Description: 配送能力校验
 * 以美团为例，后期接入别的可以更改本实体进行扩展
 * @Date: 2019/7/30 15:24
 * @Author: suncy
 **/
@Data
public class OrderCheckDTO implements Serializable {
    /**
     * 取货门店 id，即合作方向美团提供的门店 id。注：测试门店的 shop_id 固定为 test_0001，仅用于对接时联调测试。
     */
    private String shopId;
    /**
     * 配送服务代码，详情见合同
     * 光速达: 4001
     * 快速达: 4011
     * 及时达: 4012
     * 集中送: 4013
     * 当天达: 4021
     */
    private Integer deliveryServiceCode;
    /**
     * 收件人地址，最长不超过 512 个字符
     */
    private String receiverAddress;
    /**
     * 收件人经度（高德坐标），高德坐标 *（ 10 的六次方），如 116398419
     */
    private Integer receiverLng;
    /**
     * 收件人纬度（高德坐标），高德坐标 *（ 10 的六次方），如 39985005
     */
    private Integer receiverLat;
    /**
     * 预留字段，方便以后扩充校验规则，check_type = 1
     */
    private Integer checkType;
    /**
     * 模拟发单时间，时区为 GMT+8，当前距离 Epoch（1970年1月1日) 以秒计算的时间，即 unix-timestamp。
     */
    private Long mockOrderTime;
}
