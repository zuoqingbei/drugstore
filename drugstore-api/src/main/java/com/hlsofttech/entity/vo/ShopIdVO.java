package com.hlsofttech.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/***
 * @Description: 获取门店IDDTO
 * @Date: 2019/8/6 13:55
 * @Author: suncy
 **/
@Data
public class ShopIdVO {

    /**
     * 平台门店ID
     */
    @ApiModelProperty(value = "平台门店ID,唯一值")
    private String shopId;
    /**
     * 营业执照统一信用代码
     */
    @NotBlank(message = "统一信用代码不能为空")
    @ApiModelProperty(value = "营业执照统一信用代码")
    private String code;
}
