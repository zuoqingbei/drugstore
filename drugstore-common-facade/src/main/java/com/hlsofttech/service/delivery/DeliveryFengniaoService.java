package com.hlsofttech.service.delivery;

import com.hlsofttech.entity.delivery.dto.ShopInfoDTO;
import com.hlsofttech.rsp.Result;

/***
 * @Description: 蜂鸟配送服务
 * @Date: 2019/7/25 17:07
 * @Author: suncy
 **/
public interface DeliveryFengniaoService {

    /***
     * @Description: 创建门店
     * @Date: 2019/7/25 17:35
     * @param shopInfoDTO:
     * @return: com.hlsofttech.common.ResultDO
     * @Author: suncy
     **/
    Result<Object> createShop(ShopInfoDTO shopInfoDTO);
}
