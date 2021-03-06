package com.hlsofttech.entity.vo;


import lombok.Data;

import javax.validation.Valid;

/***
 * @Author: suntf
 * @Description:erp-同步库存
 * @Date: 2019/8/6
 **/
@Data
public class SyncStockForErpRequest extends AbstractERPRequest {
    @Valid
    private SyncStockForErpListVO data;

}
