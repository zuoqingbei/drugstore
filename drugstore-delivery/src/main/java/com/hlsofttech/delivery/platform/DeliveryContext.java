package com.hlsofttech.delivery.platform;

import com.hlsofttech.entity.delivery.dto.*;
import com.hlsofttech.rsp.Result;
import lombok.Data;

/***
 * @Description: 策略类交互
 * @Date: 2019/7/25 17:00
 * @Author: suncy
 **/
@Data
public class DeliveryContext {

    private Delivery delivery;

    public DeliveryContext(Delivery delivery) {
        super();
        this.delivery = delivery;
    }

    /***
     * @Description: 创建门店
     * @Date: 2019/7/25 17:30
     * @param shopInfoDTO:
     * @return: com.hlsofttech.rsp.Result
     * @Author: suncy
     **/
    public Result createShop(ShopInfoDTO shopInfoDTO) {
        return delivery.createShop(shopInfoDTO);
    }

    /***
     * @Description: 查询门店信息
     * @Date: 2019/7/30 16:27
     * @param shopId:
     * @return: com.hlsofttech.rsp.Result
     * @Author: suncy
     **/
    public Result queryShop(String shopId) {
        return delivery.queryShop(shopId);
    }

    /***
     * @Description: 创建订单
     * @Date: 2019/7/29 9:30
     * @param createOrderDTO:
     * @return: com.hlsofttech.rsp.Result
     * @Author: suncy
     **/
    public Result createOrder(CreateOrderDTO createOrderDTO) {
        return delivery.createOrder(createOrderDTO);
    }

    /***
     * @Description: 取消订单
     * @Date: 2019/7/29 10:44
     * @param cancelOrderDTO:
     * @return: com.hlsofttech.rsp.Result
     * @Author: suncy
     **/
    public Result cancelOrder(CancelOrderDTO cancelOrderDTO) {
        return delivery.cancelOrder(cancelOrderDTO);
    }

    /***
     * @Description: 查询订单
     * @Date: 2019/7/29 14:23
     * @param queryOrderDTO:
     * @return: com.hlsofttech.rsp.Result
     * @Author: suncy
     **/
    public Result queryOrder(QueryOrderDTO queryOrderDTO) {
        return delivery.queryOrder(queryOrderDTO);
    }

    /***
     * @Description: 配送能力校验
     * @Date: 2019/7/30 15:52
     * @param orderEvaluateDTO:
     * @return: com.hlsofttech.rsp.Result
     * @Author: suncy
     **/
    public Result orderEvaluate(OrderEvaluateDTO orderEvaluateDTO) {
        return delivery.orderEvaluate(orderEvaluateDTO);
    }

    /***
     * @Description: 配送能力校验
     * @Date: 2019/7/30 15:52
     * @param orderCheckDTO:
     * @return: com.hlsofttech.rsp.Result
     * @Author: suncy
     **/
    public Result orderCheck(OrderCheckDTO orderCheckDTO) {
        return delivery.orderCheck(orderCheckDTO);
    }

    /***
     * @Description: 获取骑手位置
     * @Date: 2019/7/30 15:52
     * @param queryOrderDTO:
     * @return: com.hlsofttech.rsp.Result
     * @Author: suncy
     **/
    public Result getRiderLocation(QueryOrderDTO queryOrderDTO) {
        return delivery.getRiderLocation(queryOrderDTO);
    }

    /***
     * @Description: 查询合作方配送范围
     * @Date: 2019/7/30 16:17
     * @param queryShopAreaDTO:
     * @return: com.hlsofttech.rsp.Result
     * @Author: suncy
     **/
    public Result queryShopArea(QueryShopAreaDTO queryShopAreaDTO) {
        return delivery.queryShopArea(queryShopAreaDTO);
    }
}
