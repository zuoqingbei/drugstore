package com.hlsofttech.entity.vo;


import lombok.Data;

import javax.validation.Valid;

/***
 * @Author: suntf
 * @Description:erp-订单列表
 * @Date: 2019/8/6
 **/
@Data
public class OrderListForErpRequest extends AbstractERPRequest {

    @Valid
    private OrderListForErpVO data;

}
