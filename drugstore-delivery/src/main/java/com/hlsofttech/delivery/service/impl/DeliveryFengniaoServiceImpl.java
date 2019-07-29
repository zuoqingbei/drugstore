package com.hlsofttech.delivery.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.hlsofttech.common.Constant;
import com.hlsofttech.common.ResultDO;
import com.hlsofttech.delivery.platform.Delivery;
import com.hlsofttech.delivery.platform.DeliveryContext;
import com.hlsofttech.delivery.platform.fengniao.FengniaoDelivery;
import com.hlsofttech.entity.delivery.dto.CreateOrderDTO;
import com.hlsofttech.entity.delivery.dto.ShopInfoDTO;
import com.hlsofttech.service.delivery.DeliveryFengniaoService;

import javax.annotation.Resource;

/***
 * @Description: 蜂鸟配送服务
 * @Date: 2019/7/25 17:10
 * @Author: suncy
 **/
@Service(version = Constant.VERSION, group = "com.hlsofttech.delivery", timeout = Constant.TIMEOUT)
@org.springframework.stereotype.Service
public class DeliveryFengniaoServiceImpl implements DeliveryFengniaoService {

    private DeliveryContext deliveryContext = new DeliveryContext(new FengniaoDelivery());

    /***
     * @Description: 创建门店
     * @Date: 2019/7/26 17:36
     * @param shopInfoDTO:
     * @return: com.hlsofttech.common.ResultDO
     * @Author: suncy
     **/
    public ResultDO createShop(ShopInfoDTO shopInfoDTO) {
        return deliveryContext.createShop(shopInfoDTO);
    }

    @Override
    public ResultDO createOrder(CreateOrderDTO createOrderDTO) {
        return null;
    }
}
