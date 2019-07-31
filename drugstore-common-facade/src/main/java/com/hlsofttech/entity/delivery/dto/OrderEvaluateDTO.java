package com.hlsofttech.entity.delivery.dto;

import lombok.Data;

import java.io.Serializable;

/***
 * @Description: 评价骑手
 * 以美团为例，后期接入别的可以更改本实体进行扩展
 * @Date: 2019/7/30 14:34
 * @Author: suncy
 **/
@Data
public class OrderEvaluateDTO implements Serializable {
    /**
     * 配送活动标识
     */
    private Long deliveryId;
    /**
     * 美团配送内部订单id，最长不超过32个字符
     */
    private String mtPeisongId;
    /**
     * 评分（5分制）
     * 预留参数，不作为骑手反馈参考
     * 合作方需传入0-5之间分数或者不传，否则报错
     */
    private Integer score;
    /**
     * 评论内容（评论的字符长度需小于1024）
     */
    private String commentContent;
}
