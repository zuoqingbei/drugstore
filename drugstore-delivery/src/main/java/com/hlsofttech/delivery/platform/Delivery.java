package com.hlsofttech.delivery.platform;

import com.hlsofttech.common.ResultDO;
import com.hlsofttech.entity.delivery.dto.CancelOrderDTO;
import com.hlsofttech.entity.delivery.dto.CreateOrderDTO;
import com.hlsofttech.entity.delivery.dto.QueryOrderDTO;
import com.hlsofttech.entity.delivery.dto.ShopInfoDTO;

/***
 * @Description: 配送平台统一接口服务
 * @Date: 2019/7/25 13:19
 * @Author: suncy
 **/
public interface Delivery {

    /***
     * @Description: 创建门店
     * @Date: 2019/7/25 16:26
     * @param shopInfoDTO:
     * @return: com.hlsofttech.common.ResultDO
     * @Author: suncy
     **/
    ResultDO createShop(ShopInfoDTO shopInfoDTO);


    /***
     * @Description: 创建订单统一接口
     * @Date: 2019/7/29 9:10
     * @param createOrderDTO:
     * @return: com.hlsofttech.common.ResultDO
     * @Author: suncy
     **/
    ResultDO createOrder(CreateOrderDTO createOrderDTO);

    /***
     * @Description: 取消订单
     * @Date: 2019/7/29 10:43
     * @param cancelOrderDTO:
     * @return: com.hlsofttech.common.ResultDO
     * @Author: suncy
     **/
    ResultDO cancelOrder(CancelOrderDTO cancelOrderDTO);

    /***
     * @Description: 查询订单状态
     * @Date: 2019/7/29 14:17
     * @param queryOrderDTO:
     * @return: com.hlsofttech.common.ResultDO
     * @Author: suncy
     **/
    ResultDO queryOrder(QueryOrderDTO queryOrderDTO);

}
