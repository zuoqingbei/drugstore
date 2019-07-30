package com.hlsofttech.delivery.platform.fengniao;

import com.hlsofttech.common.ResultDO;
import com.hlsofttech.delivery.platform.Delivery;
import com.hlsofttech.entity.delivery.dto.CancelOrderDTO;
import com.hlsofttech.entity.delivery.dto.CreateOrderDTO;
import com.hlsofttech.entity.delivery.dto.QueryOrderDTO;
import com.hlsofttech.entity.delivery.dto.ShopInfoDTO;
import com.hlsofttech.rsp.Result;
import lombok.extern.slf4j.Slf4j;

/**
 * @Description: 蜂鸟配送
 * @Date: 2019/7/25 16:51
 * @Author: suncy
 **/
@Slf4j
public class FengniaoDelivery implements Delivery {

    @Override
    public Result<Object> createShop(ShopInfoDTO shopInfoDTO) {
        return null;
    }

    @Override
    public Result<Object> createOrder(CreateOrderDTO createOrderDTO) {
        return null;
    }

    @Override
    public Result<Object> cancelOrder(CancelOrderDTO cancelOrderDTO) {
        return null;
    }

    @Override
    public Result<Object> queryOrder(QueryOrderDTO queryOrderDTO) {
        return null;
    }
}
