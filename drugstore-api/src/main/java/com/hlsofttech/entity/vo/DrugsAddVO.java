package com.hlsofttech.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/***
 * @Description: erp接口根据统一信用代码查询平台门店ID
 * @Date: 2019/8/5 15:50
 * @Author: suncy
 **/
@Data
public class DrugsAddVO {

    /**
     * 药品统一编码（国药准字）
     */
    @NotBlank(message = "药品统一编码（国药准字）不能为空")
    @ApiModelProperty(value = "药品统一编码（国药准字）")
    private String drugCode;
    /**
     * 药品名称
     */
    @NotBlank(message = "药品名称不能为空")
    @ApiModelProperty(value = "药品名称")
    private String name;
    /**
     * 药品价格
     */
    @NotNull(message = "药品价格不能为空（单位分）")
    @ApiModelProperty(value = "药品价格")
    private Long price;

    /**
     * 药品库存
     */
    @NotNull(message = "药品库存不能为空")
    @ApiModelProperty(value = "药品库存")
    private Integer stock;

    /**
     * 单位
     */
    @ApiModelProperty(value = "单位")
    private String unit;


}
