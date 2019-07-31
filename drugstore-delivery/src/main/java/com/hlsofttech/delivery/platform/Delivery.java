package com.hlsofttech.delivery.platform;

import com.hlsofttech.entity.delivery.dto.*;
import com.hlsofttech.rsp.Result;

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
     * @return: com.hlsofttech.rsp.Result
     * @Author: suncy
     **/
    Result createShop(ShopInfoDTO shopInfoDTO);

    /***
     * @Description: 查询门店信息
     * @Date: 2019/7/30 16:21
     * @param shopId:
     * @return: com.hlsofttech.rsp.Result
     * @Author: suncy
     **/
    Result queryShop(String shopId);

    /***
     * @Description: 创建订单统一接口
     * @Date: 2019/7/29 9:10
     * @param createOrderDTO:
     * @return: com.hlsofttech.rsp.Result
     * @Author: suncy
     **/
    Result createOrder(CreateOrderDTO createOrderDTO);

    /***
     * @Description: 取消订单
     * @Date: 2019/7/29 10:43
     * @param cancelOrderDTO:
     * @return: com.hlsofttech.rsp.Result
     * @Author: suncy
     **/
    Result cancelOrder(CancelOrderDTO cancelOrderDTO);

    /***
     * @Description: 查询订单状态
     * @Date: 2019/7/29 14:17
     * @param queryOrderDTO:
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
     * @Date: 2019/7/30 16:09
     * @param queryShopAreaDTO:
     * @return: com.hlsofttech.rsp.Result
     * @Author: suncy
     **/
    Result queryShopArea(QueryShopAreaDTO queryShopAreaDTO);

}
