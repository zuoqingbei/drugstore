package com.hlsofttech.delivery.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.hlsofttech.common.Constant;
import com.hlsofttech.delivery.platform.DeliveryContext;
import com.hlsofttech.delivery.platform.meituan.MeituanDelivery;
import com.hlsofttech.entity.delivery.dto.*;
import com.hlsofttech.rsp.Result;
import com.hlsofttech.service.delivery.DeliveryMeituanService;

/***
 * @Description: 美团配送服务
 * @Date: 2019/7/25 17:10
 * @Author: suncy
 **/
@Service(version = Constant.VERSION, group = "com.hlsofttech.delivery", timeout = Constant.TIMEOUT)
@org.springframework.stereotype.Service
public class DeliveryMeituanServiceImpl implements DeliveryMeituanService {

    DeliveryContext deliveryContext = new DeliveryContext(MeituanDelivery.getInstance());

    @Override
    public Result createShop(ShopInfoDTO shopInfoDTO) {
        return deliveryContext.createShop(shopInfoDTO);
    }

    @Override
    public Result createOrder(CreateOrderDTO createOrderDTO) {
        return deliveryContext.createOrder(createOrderDTO);
    }

    @Override
    public Result cancelOrder(CancelOrderDTO cancelOrderDTO) {
        return deliveryContext.cancelOrder(cancelOrderDTO);
    }

    @Override
    public Result queryOrder(QueryOrderDTO queryOrderDTO) {
        return deliveryContext.queryOrder(queryOrderDTO);
    }

    @Override
    public Result orderEvaluate(OrderEvaluateDTO orderEvaluateDTO) {
        return deliveryContext.orderEvaluate(orderEvaluateDTO);
    }

    @Override
    public Result orderCheck(OrderCheckDTO orderCheckDTO) {
        return deliveryContext.orderCheck(orderCheckDTO);
    }

    @Override
    public Result getRiderLocation(QueryOrderDTO queryOrderDTO) {
        return deliveryContext.getRiderLocation(queryOrderDTO);
    }

    @Override
    public Result queryShopArea(QueryShopAreaDTO queryShopAreaDTO) {
        return deliveryContext.queryShopArea(queryShopAreaDTO);
    }

    @Override
    public Result queryShop(String shopId) {
        return deliveryContext.queryShop(shopId);
    }
}
