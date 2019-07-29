package com.hlsofttech.entity.delivery.dto;

import lombok.Data;

import java.util.List;

/**
 * 订单商品信息列表
 */
@Data
public class GoodListDTO {
    private List<GoodDTO> goods;
}


