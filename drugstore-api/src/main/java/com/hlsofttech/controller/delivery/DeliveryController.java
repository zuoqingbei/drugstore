package com.hlsofttech.controller.delivery;

import com.alibaba.dubbo.config.annotation.Reference;
import com.hlsofttech.annotation.AuthPower;
import com.hlsofttech.base.BaseController;
import com.hlsofttech.common.Constant;
import com.hlsofttech.entity.delivery.dto.*;
import com.hlsofttech.rsp.Result;
import com.hlsofttech.service.delivery.DeliveryMeituanService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 配送相关
 * @Date: 2019/7/29 14:49
 * @Author: suncy
 **/
@RestController
@Api(tags = "配送相关", value = "配送相关", description = "配送相关 @author suncy")
public class DeliveryController extends BaseController {

    @Reference(version = Constant.VERSION, timeout = Constant.TIMEOUT)
    public DeliveryMeituanService deliveryMeituanService;

    @AuthPower(avoidVersion = false, avoidPower = true, avoidSign = true, avoidLogin = true, avoidPlatform = true)
    @ApiOperation(value = "创建门店-美团", notes = "创建门店-美团", httpMethod = "POST")
    @PostMapping(value = "/api/delivery/meituan/createShop")
    public Result createShop(ShopInfoDTO shopInfoDTO) {
        return deliveryMeituanService.createShop(shopInfoDTO);
    }

    @AuthPower(avoidVersion = false, avoidPower = true, avoidSign = true, avoidLogin = true, avoidPlatform = true)
    @ApiOperation(value = "查询门店-美团", notes = "查询门店-美团", httpMethod = "GET")
    @GetMapping(value = "/api/delivery/meituan/queryShop/{shopId}")
    public Result queryShop(@PathVariable("shopId") String shopId) {
        return deliveryMeituanService.queryShop(shopId);
    }

    @AuthPower(avoidVersion = false, avoidPower = true, avoidSign = true, avoidLogin = true, avoidPlatform = true)
    @ApiOperation(value = "创建订单-美团", notes = "创建订单-美团", httpMethod = "POST")
    @PostMapping(value = "/api/delivery/meituan/createOrder")
    public Result createOrder(CreateOrderDTO createOrderDTO) {
        return deliveryMeituanService.createOrder(createOrderDTO);
    }

    @AuthPower(avoidVersion = false, avoidPower = true, avoidSign = true, avoidLogin = true, avoidPlatform = true)
    @ApiOperation(value = "取消订单-美团", notes = "取消订单-美团", httpMethod = "POST")
    @PostMapping(value = "/api/delivery/meituan/cancelOrder")
    public Result cancelOrder(CancelOrderDTO cancelOrderDTO) {
        return deliveryMeituanService.cancelOrder(cancelOrderDTO);
    }

    @AuthPower(avoidVersion = false, avoidPower = true, avoidSign = true, avoidLogin = true, avoidPlatform = true)
    @ApiOperation(value = "查询订单状态-美团", notes = "查询订单状态-美团", httpMethod = "POST")
    @PostMapping(value = "/api/delivery/meituan/queryOrder")
    public Result queryOrder(QueryOrderDTO queryOrderDTO) {
        return deliveryMeituanService.queryOrder(queryOrderDTO);
    }

    @AuthPower(avoidVersion = false, avoidPower = true, avoidSign = true, avoidLogin = true, avoidPlatform = true)
    @ApiOperation(value = "评价骑手-美团", notes = "评价骑手-美团", httpMethod = "POST")
    @PostMapping(value = "/api/delivery/meituan/orderEvaluate")
    public Result orderEvaluate(OrderEvaluateDTO orderEvaluateDTO) {
        return deliveryMeituanService.orderEvaluate(orderEvaluateDTO);
    }

    @AuthPower(avoidVersion = false, avoidPower = true, avoidSign = true, avoidLogin = true, avoidPlatform = true)
    @ApiOperation(value = "配送能力校验-美团", notes = "配送能力校验-美团", httpMethod = "POST")
    @PostMapping(value = "/api/delivery/meituan/orderCheck")
    public Result orderCheck(OrderCheckDTO orderCheckDTO) {
        return deliveryMeituanService.orderCheck(orderCheckDTO);
    }

    @AuthPower(avoidVersion = false, avoidPower = true, avoidSign = true, avoidLogin = true, avoidPlatform = true)
    @ApiOperation(value = "获取骑手位置-美团", notes = "获取骑手位置-美团", httpMethod = "POST")
    @PostMapping(value = "/api/delivery/meituan/getRiderLocation")
    public Result getRiderLocation(QueryOrderDTO queryOrderDTO) {
        return deliveryMeituanService.getRiderLocation(queryOrderDTO);
    }

    @AuthPower(avoidVersion = false, avoidPower = true, avoidSign = true, avoidLogin = true, avoidPlatform = true)
    @ApiOperation(value = "查询合作方配送范围-美团", notes = "查询合作方配送范围-美团", httpMethod = "POST")
    @PostMapping(value = "/api/delivery/meituan/queryShopArea")
    public Result queryShopArea(QueryShopAreaDTO queryShopAreaDTO) {
        return deliveryMeituanService.queryShopArea(queryShopAreaDTO);
    }


}

