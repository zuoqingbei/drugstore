package com.hlsofttech.entity.vo;


import lombok.Data;

/***
 * @Author: suntf
 * @Description:erp-库存同步
 * @Date: 2019/8/6
 **/
@Data
public class SyncStockForErpVO {

    /**
     * 药品国标码
     */
    private String drugCode;
    /**
     * 库存
     */
    private Integer stock;


}
