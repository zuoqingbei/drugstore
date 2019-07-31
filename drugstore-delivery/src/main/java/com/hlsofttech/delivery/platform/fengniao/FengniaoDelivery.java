package com.hlsofttech.delivery.platform.fengniao;

import com.hlsofttech.delivery.platform.Delivery;
import com.hlsofttech.entity.delivery.dto.*;
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

    @Override
    public Result orderEvaluate(OrderEvaluateDTO orderEvaluateDTO) {
        return null;
    }

    @Override
    public Result orderCheck(OrderCheckDTO orderCheckDTO) {
        return null;
    }

    @Override
    public Result getRiderLocation(QueryOrderDTO queryOrderDTO) {
        return null;
    }

    @Override
    public Result queryShopArea(QueryShopAreaDTO queryShopAreaDTO) {
        return null;
    }

    @Override
    public Result queryShop(String shopId) {
        return null;
    }
}
