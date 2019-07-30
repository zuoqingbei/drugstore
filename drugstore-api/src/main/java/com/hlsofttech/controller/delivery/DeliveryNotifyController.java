package com.hlsofttech.controller.delivery;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hlsofttech.annotation.AuthPower;
import com.hlsofttech.base.BaseController;
import com.hlsofttech.common.Constant;
import com.hlsofttech.delivery.platform.meituan.constants.OpenApiConfig;
import com.hlsofttech.delivery.platform.meituan.response.CreateOrderResponse;
import com.hlsofttech.delivery.platform.meituan.response.notify.OrderStatusNotifyResponse;
import com.hlsofttech.delivery.platform.meituan.sign.SignHelper;
import com.hlsofttech.rsp.Result;
import com.hlsofttech.service.delivery.DeliveryMeituanService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 配送相关
 * @Date: 2019/7/29 14:49
 * @Author: suncy
 **/
@Slf4j
@RestController
@Api(tags = "配送相关", value = "配送回调", description = "配送回调相关 @author suncy")
public class DeliveryNotifyController extends BaseController {

    @Reference(version = Constant.VERSION, group = "com.hlsofttech.delivery", timeout = Constant.TIMEOUT)
    public DeliveryMeituanService deliveryMeituanService;

    @AuthPower(avoidVersion = false, avoidPower = true, avoidSign = true, avoidLogin = true, avoidPlatform = true)
    @ApiOperation(value = "订单状态回调", notes = "订单状态回调-美团", httpMethod = "POST")
    @PostMapping(value = "/api/delivery/notify/meituan/orderStatus")
    public Result createShop(@RequestBody String param) {

        log.info("订单状态回调:" + param);
        // 解析参数
        JSONObject jsonObject = (JSONObject) JSONObject.parse(param);
        boolean flag = false;
        try {
            flag = SignHelper.signAndCheck(jsonObject);
        } catch (Exception e) {
            log.error("加密错误！");
        }

        // 验签成功，开始处理业务
        if (flag) {
            log.info("验签成功，开始处理业务");
            OrderStatusNotifyResponse response = JSON.parseObject(param, OrderStatusNotifyResponse.class);

            // TODO: 2019/7/30 处理订单信息

        }

        return null;
    }
}

