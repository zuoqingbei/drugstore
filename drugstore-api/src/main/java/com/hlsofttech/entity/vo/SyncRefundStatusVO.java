package com.hlsofttech.entity.vo;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/***
 * @Author: suntf
 * @Description:erp-退货款状态同步
 * @Date: 2019/8/6
 **/
@Data
public class SyncRefundStatusVO {

    /**
     * 订单id
     */
    @NotBlank(message = "订单id不能为空")
    @ApiModelProperty(value = "订单id")
    private String orderId;
    /**
     * 退货款单id
     */
    @NotBlank(message = "退货款单id不能为空")
    @ApiModelProperty(value = "退货款单id")
    private String refundId;
    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private Integer status;


}
