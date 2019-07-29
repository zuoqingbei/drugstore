package com.hlsofttech.delivery.platform.fengniao;

import com.hlsofttech.common.ResultDO;
import com.hlsofttech.delivery.platform.Delivery;
import com.hlsofttech.delivery.platform.meituan.constants.ErrorContent;
import com.hlsofttech.entity.delivery.dto.CreateOrderDTO;
import com.hlsofttech.entity.delivery.dto.ShopInfoDTO;
import lombok.extern.slf4j.Slf4j;

/**
 * @Description: 蜂鸟配送
 * @Date: 2019/7/25 16:51
 * @Author: suncy
 **/
@Slf4j
public class FengniaoDelivery implements Delivery {

    @Override
    public ResultDO createShop(ShopInfoDTO shopInfoDTO) {
        System.out.println("===============蜂鸟配送=================");
        return new ResultDO(false, ErrorContent.CREATE_SHOP_ERROR);
    }

    @Override
    public ResultDO createOrder(CreateOrderDTO createOrderDTO) {
        return null;
    }
}
