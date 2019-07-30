package com.hlsofttech.controller.delivery;

import com.alibaba.dubbo.config.annotation.Reference;
import com.hlsofttech.annotation.AuthPower;
import com.hlsofttech.base.BaseController;
import com.hlsofttech.common.Constant;
import com.hlsofttech.entity.delivery.dto.CreateOrderDTO;
import com.hlsofttech.entity.delivery.dto.ShopInfoDTO;
import com.hlsofttech.rsp.Result;
import com.hlsofttech.service.delivery.DeliveryMeituanService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final Logger logger = LoggerFactory.getLogger(DeliveryController.class);

    @Reference(version = Constant.VERSION, group = "com.hlsofttech.delivery", timeout = Constant.TIMEOUT)
    public DeliveryMeituanService deliveryMeituanService;

    @AuthPower(avoidVersion = false, avoidPower = true, avoidSign = true, avoidLogin = true, avoidPlatform = true)
    @ApiOperation(value = "创建门店", notes = "创建门店-美团", httpMethod = "POST")
    @PostMapping(value = "/api/delivery/meituan/createShop")
    public Result createShop(ShopInfoDTO shopInfoDTO) {
        return deliveryMeituanService.createShop(shopInfoDTO);
    }

    @AuthPower(avoidVersion = false, avoidPower = true, avoidSign = true, avoidLogin = true, avoidPlatform = true)
    @ApiOperation(value = "创建订单", notes = "创建订单-美团", httpMethod = "POST")
    @PostMapping(value = "/api/delivery/meituan/createOrder")
    public Result createOrder(CreateOrderDTO createOrderDTO) {
        return deliveryMeituanService.createOrder(createOrderDTO);
    }


}

