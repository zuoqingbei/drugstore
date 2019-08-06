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
     * 门店唯一的key，可以是营业执照号、统一信用代码。。。
     */
    private String code;
    /**
     * 药品国标码
     */
    private String drugCode;
    /**
     * 库存
     */
    private Integer stock;

}
