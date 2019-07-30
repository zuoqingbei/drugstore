package com.hlsofttech.service.delivery;

import com.hlsofttech.entity.delivery.dto.CancelOrderDTO;
import com.hlsofttech.entity.delivery.dto.CreateOrderDTO;
import com.hlsofttech.entity.delivery.dto.QueryOrderDTO;
import com.hlsofttech.entity.delivery.dto.ShopInfoDTO;
import com.hlsofttech.rsp.Result;

/***
 * @Description: 美团配送服务
 * @Date: 2019/7/25 17:07
 * @Author: suncy
 **/
public interface DeliveryMeituanService {

    /***
     * @Description: 创建门店
     * @Date: 2019/7/25 17:35
     * @param shopInfoDTO:
     * @return: com.hlsofttech.rsp.Result
     * @Author: suncy
     **/
    Result createShop(ShopInfoDTO shopInfoDTO);

    /***
     * @Description: 创建订单
     * @Date: 2019/7/29 9:29
     * @param createOrderDTO:
     * @return: com.hlsofttech.rsp.Result
     * @Author: suncy
     **/
    Result createOrder(CreateOrderDTO createOrderDTO);

    /***
     * @Description: 取消订单
     * @Date: 2019/7/29 10:42
     * @param cancelOrderDTO:
     * @return: com.hlsofttech.rsp.Result
     * @Author: suncy
     **/
    Result cancelOrder(CancelOrderDTO cancelOrderDTO);

    /**
      * @Description: 查询订单状态
      * @Date: 2019/7/29 16:23
      * @param queryOrderDTO:
      * @return: com.hlsofttech.rsp.Result
      * @Author: suncy
      **/
    Result queryOrder(QueryOrderDTO queryOrderDTO);

}
