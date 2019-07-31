package com.hlsofttech.entity.delivery.dto;

import lombok.Data;

import java.io.Serializable;

/***
 * @Description: 查询订单DTO
 * 以美团为例，后期接入别的可以更改本实体进行扩展
 * @Date: 2019/7/29 14:05
 * @Author: suncy
 **/
@Data
public class QueryOrderDTO implements Serializable {

    /**
     * 配送活动标识
     */
    private Long deliveryId;

    /**
     * 美团配送内部订单id，最长不超过32个字符
     */
    private String mtPeisongId;

}
