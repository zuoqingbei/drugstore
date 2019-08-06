package com.hlsofttech.entity.vo;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.Valid;
import java.util.List;

/***
 * @Author: suntf
 * @Description:erp-库存同步
 * @Date: 2019/8/6
 **/
@Data
public class SyncStockForErpListVO {
    /**
     * 门店唯一的key，可以是营业执照号、统一信用代码。。。
     */
    @NotBlank(message = "门店唯一的key不可为空")
    @ApiModelProperty(value = "门店唯一的key，可以是营业执照号、统一信用代码。。。")
    private String code;

    /**
     * 药品信息集合
     */
    @Valid
    private List<SyncStockForErpVO> syncStockForErpVOList;

}
