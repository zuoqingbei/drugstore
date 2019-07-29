package com.hlsofttech.service.delivery;

import com.hlsofttech.common.ResultDO;
import com.hlsofttech.entity.delivery.dto.CancelOrderDTO;
import com.hlsofttech.entity.delivery.dto.CreateOrderDTO;
import com.hlsofttech.entity.delivery.dto.QueryOrderDTO;
import com.hlsofttech.entity.delivery.dto.ShopInfoDTO;

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
     * @return: com.hlsofttech.common.ResultDO
     * @Author: suncy
     **/
    ResultDO createShop(ShopInfoDTO shopInfoDTO);

    /***
     * @Description: 创建订单
     * @Date: 2019/7/29 9:29
     * @param createOrderDTO:
     * @return: com.hlsofttech.common.ResultDO
     * @Author: suncy
     **/
    ResultDO createOrder(CreateOrderDTO createOrderDTO);

    /***
     * @Description: 取消订单
     * @Date: 2019/7/29 10:42
     * @param cancelOrderDTO:
     * @return: com.hlsofttech.common.ResultDO
     * @Author: suncy
     **/
    ResultDO cancelOrder(CancelOrderDTO cancelOrderDTO);


    /***
     * @Description: 查询订单状态
     * @Date: 2019/7/29 14:05
     * @param queryOrderDTO:
     * @return: com.hlsofttech.common.ResultDO
     * @Author: suncy
     **/
    ResultDO queryOrder(QueryOrderDTO queryOrderDTO);

}
