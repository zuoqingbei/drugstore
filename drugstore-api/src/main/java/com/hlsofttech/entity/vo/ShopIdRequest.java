package com.hlsofttech.entity.vo;

import lombok.Data;

import javax.validation.Valid;
import java.util.List;

/**
 * @Description: 门店ID获取请求
 * @Date: 2019/8/6 13:54
 * @Author: suncy
 **/
@Data
public class ShopIdRequest extends AbstractERPRequest {

    @Valid
    private List<ShopIdVO> data;

}
