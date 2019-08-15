package com.hlsofttech.entity.vo;

import lombok.Data;

import java.io.Serializable;

/***
 * @Description: advertisementVO
 * @Date: 2019/8/13 9:28
 * @Author: suncy
 **/
@Data
public class AdvertisementVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String adId;//广告id
    private String zoneId;//广告位id
    private String zoneName;//广告位名称
    private String name;//名称
    private String rank;//排序
    private String linkIos;//链接-iOS
    private String linkAndroid;//链接-android
    private String linkPhone;//链接-手机
    private String image;//主图
}
