package com.hlsofttech.entity.delivery.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 订单商品信息列表
 * 以美团为例，后期接入别的可以更改本实体进行扩展
 */
@Data
public class GoodListDTO implements Serializable {
    private List<GoodDTO> goods;
}


