package com.hlsofttech.entity.user.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/***
 * @Description: bannerDTO
 * @Date: 2019/8/13 9:28
 * @Author: suncy
 **/
@Data
public class BannerDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Integer id;
    /**
     * 类型（0=顶部轮播 、1=活动图片）
     */
    private String type;
    /**
     * 图片地址
     */
    private String imgUrl;
    /**
     * 事件类型
     */
    private String event;
    /**
     * 事件地址
     */
    private String target;
    /**
     * 图片
     */
    private String color;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 创建人
     */
    private Long createId;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 更新人
     */
    private Long updateId;
    /**
     * 删除标记(1删除 0未删除)
     */
    private String delFlag;
    /**
     * 开启标记(1关闭 0开启)
     */
    private String isOpen;
    /**
     * 排序字段，从小到大
     */
    private Integer sort;
    /**
     * 标题，轮播不用，活动需要填写
     */
    private String title;
}
