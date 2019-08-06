package com.hlsofttech.entity.vo;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/***
 * @Author: suntf
 * @Description:erp-订单列表
 * @Date: 2019/8/6
 **/
@Data
public class OrderListForErpVO {
    /**
     * 门店唯一的key，可以是营业执照号、统一信用代码。。。
     */
    @NotBlank(message = "门店唯一识别key不可为空")
    @ApiModelProperty(value = "门店唯一的key，可以是营业执照号、统一信用代码。。。")
    private String code;
    /**
     * 查询时间段-开始
     */
    @ApiModelProperty(value = "查询时间段-开始")
    private String timeStart;
    /**
     * 查询时间段-结束
     */
    @ApiModelProperty(value = "查询时间段-结束")
    private String timeEnd;

}
