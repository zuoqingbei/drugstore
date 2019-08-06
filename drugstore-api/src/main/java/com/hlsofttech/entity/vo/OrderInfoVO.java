package com.hlsofttech.entity.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 功能描述 订单信息DTO
 *
 * @author sevenSeconds
 * @date 2019/2/25 14:42
 */

@Data
public class OrderInfoVO implements Serializable {
    private static final long serialVersionUID = -4460193212983830780L;

    /**
     * 主键
     */
    private Long id;
    /**
     * 用户ID
     */
    private Long memberId;
    /**
     * 订单号
     */
    private String orderCode;
    /**
     * 订单状态（字典order_status）
     */
    private Integer orderStatus;
    /**
     * 联系人
     */
    private String contact;
    /**
     * 联系方式
     */
    private String phone;
    /**
     * 省
     */
    private String province;
    /**
     * 市
     */
    private String city;
    /**
     * 区
     */
    private String district;
    /**
     * 详细地址
     */
    private String detail;
    /**
     * 订单总金额包含运费
     */
    private Long totalMoney;
    /**
     * 商品总价
     */
    private Long totalProductMoney;
    /**
     * 运费
     */
    private Long totalWeight;
    /**
     * 支付时间
     */
    private Date payDate;
    /**
     * 收货时间
     */
    private Date receivedDate;
    /**
     * 订单来源（字典order_from）
     */
    private Integer orderFrom;
    /**
     * 买家备注
     */
    private String buyerRemark;
    /**
     * 卖家备注
     */
    private String sellerRemark;
    /**
     * 物流名称
     */
    private String expressName;
    /**
     * 发货时间
     */
    private Date shipDate;
    /**
     * 订单产品列表
     */
    private List<OrderProductVO> orderProductVOList = new ArrayList<>();

}
