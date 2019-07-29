package com.hlsofttech.delivery.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.hlsofttech.common.Constant;
import com.hlsofttech.common.ResultDO;
import com.hlsofttech.delivery.platform.DeliveryContext;
import com.hlsofttech.delivery.platform.meituan.MeituanDelivery;
import com.hlsofttech.entity.delivery.dto.CreateOrderDTO;
import com.hlsofttech.entity.delivery.dto.ShopInfoDTO;
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
    public ResultDO createShop(ShopInfoDTO shopInfoDTO) {
        return deliveryContext.createShop(shopInfoDTO);
    }

    @Override
    public ResultDO createOrder(CreateOrderDTO createOrderDTO) {
        return null;
    }
}
