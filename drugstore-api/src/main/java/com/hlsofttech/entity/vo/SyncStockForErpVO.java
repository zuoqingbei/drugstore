package com.hlsofttech.entity.vo;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/***
 * @Author: suntf
 * @Description:erp-库存同步
 * @Date: 2019/8/6
 **/
@Data
public class SyncStockForErpVO {

    /**
     * 药品统一编码（国药准字）
     */
    @NotBlank(message = "药品统一编码（国药准字）不能为空")
    @ApiModelProperty(value = "药品统一编码（国药准字）")
    private String drugCode;
    /**
     * 库存
     */
    @ApiModelProperty(value = "库存")
    private Integer stock;


}
