package com.hlsofttech.entity.vo;


import com.hlsofttech.entity.erp.AbstractERPDTO;
import lombok.Data;

import javax.validation.Valid;

/***
 * @Author: suntf
 * @Description:erp-订单列表
 * @Date: 2019/8/6
 **/
@Data
public class OrderListForErpRequest extends AbstractERPDTO {

    @Valid
    private OrderListForErpVO data;

}
