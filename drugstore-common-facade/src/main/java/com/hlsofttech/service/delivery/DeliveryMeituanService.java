package com.hlsofttech.service.delivery;

import com.hlsofttech.entity.delivery.dto.*;
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
     * @Description: 查询门店信息
     * @Date: 2019/7/30 16:26
     * @param shopId:
     * @return: com.hlsofttech.rsp.Result
     * @Author: suncy
     **/
    Result queryShop(String shopId);

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
     * @param queryOrderDTO:
     * @Description: 查询订单状态
     * @Date: 2019/7/29 16:23
     * @return: com.hlsofttech.rsp.Result
     * @Author: suncy
     **/
    Result queryOrder(QueryOrderDTO queryOrderDTO);

    /***
     * @Description: 评价骑手
     * @Date: 2019/7/30 14:43
     * @param orderEvaluateDTO:
     * @return: com.hlsofttech.rsp.Result
     * @Author: suncy
     **/
    Result orderEvaluate(OrderEvaluateDTO orderEvaluateDTO);

    /***
     * @Description: 配送能力校验
     * 可以通过【查询合作方配送范围】接口将配送范围缓存到本地，然后利用缓存的配送范围进行配送能力校验。
     * @Date: 2019/7/30 15:26
     * @param orderCheckDTO:
     * @return: com.hlsofttech.rsp.Result
     * @Author: suncy
     **/
    Result orderCheck(OrderCheckDTO orderCheckDTO);

    /***
     * @Description: 获取骑手位置
     * @Date: 2019/7/30 15:37
     * @param queryOrderDTO:
     * @return: com.hlsofttech.rsp.Result
     * @Author: suncy
     **/
    Result getRiderLocation(QueryOrderDTO queryOrderDTO);

    /***
     * @Description: 查询合作方配送范围
     * @Date: 2019/7/30 16:16
     * @param queryShopAreaDTO:
     * @return: com.hlsofttech.rsp.Result
     * @Author: suncy
     **/
    Result queryShopArea(QueryShopAreaDTO queryShopAreaDTO);

}
