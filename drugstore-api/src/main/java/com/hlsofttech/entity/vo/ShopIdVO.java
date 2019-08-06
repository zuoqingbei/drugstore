package com.hlsofttech.entity.vo;

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
    private String shopId;
    /**
     * 营业执照统一信用代码
     */
    @NotBlank(message = "统一信用代码不能为空")
    private String code;
}
