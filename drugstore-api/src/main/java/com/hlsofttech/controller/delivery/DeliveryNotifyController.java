package com.hlsofttech.controller.delivery;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hlsofttech.annotation.AuthPower;
import com.hlsofttech.base.BaseController;
import com.hlsofttech.delivery.platform.meituan.response.notify.OrderErrorNotifyResponse;
import com.hlsofttech.delivery.platform.meituan.response.notify.OrderStatusNotifyResponse;
import com.hlsofttech.delivery.platform.meituan.response.notify.ShopStatusNotifyResponse;
import com.hlsofttech.delivery.platform.meituan.sign.SignHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

    private static final String successNotifyStr = "{\"code\":0} ";

    @AuthPower(avoidVersion = false, avoidPower = true, avoidSign = true, avoidLogin = true, avoidPlatform = true)
    @ApiOperation(value = "订单状态回调", notes = "订单状态回调-美团", httpMethod = "POST")
    @PostMapping(value = "/api/delivery/notify/meituan/orderStatus")
    public String orderStatus(@RequestBody String param) {

        log.info("订单状态回调:" + param);
        // 解析参数
        JSONObject jsonObject = (JSONObject) JSONObject.parse(param);
        boolean flag = false;
        try {
            Map<String, String> params = ParamNotifyBuilder.bulidParamsMapOrderStatus(jsonObject);
            flag = SignHelper.signAndCheck(params);
        } catch (Exception e) {
            log.error("加密错误！");
        }

        // 验签成功，开始处理业务
        if (flag) {
            log.info("验签成功，开始处理业务");
            OrderStatusNotifyResponse response = JSON.parseObject(param, OrderStatusNotifyResponse.class);

            // TODO: 2019/7/30 处理订单信息


            // 返回成功，告知对方
            return successNotifyStr;

        }

        return null;
    }

    @AuthPower(avoidVersion = false, avoidPower = true, avoidSign = true, avoidLogin = true, avoidPlatform = true)
    @ApiOperation(value = "订单异常回调", notes = "订单异常回调-美团", httpMethod = "POST")
    @PostMapping(value = "/api/delivery/notify/meituan/OrderError")
    public String orderError(@RequestBody String param) {

        log.info("订单异常回调:" + param);
        // 解析参数
        JSONObject jsonObject = (JSONObject) JSONObject.parse(param);
        boolean flag = false;
        try {
            Map<String, String> params = ParamNotifyBuilder.bulidParamsMapOrderError(jsonObject);
            flag = SignHelper.signAndCheck(params);
        } catch (Exception e) {
            log.error("加密错误！");
        }

        // 验签成功，开始处理业务
        if (flag) {
            log.info("验签成功，开始处理业务");
            OrderErrorNotifyResponse response = JSON.parseObject(param, OrderErrorNotifyResponse.class);

            // TODO: 2019/7/30 处理订单信息


            // 返回成功，告知对方
            return successNotifyStr;

        }

        return null;
    }

    @AuthPower(avoidVersion = false, avoidPower = true, avoidSign = true, avoidLogin = true, avoidPlatform = true)
    @ApiOperation(value = "门店状态回调", notes = "门店状态回调-美团", httpMethod = "POST")
    @PostMapping(value = "/api/delivery/notify/meituan/shopStatus")
    public String shopStatus(@RequestBody String param) {

        log.info("门店状态回调:" + param);
        // 解析参数
        JSONObject jsonObject = (JSONObject) JSONObject.parse(param);
        boolean flag = false;
        try {
            Map<String, String> params = ParamNotifyBuilder.bulidParamsMapshopStatus(jsonObject);
            flag = SignHelper.signAndCheck(params);
        } catch (Exception e) {
            log.error("加密错误！");
        }

        // 验签成功，开始处理业务
        if (flag) {
            log.info("验签成功，开始处理业务");
            ShopStatusNotifyResponse response = JSON.parseObject(param, ShopStatusNotifyResponse.class);

            // TODO: 2019/7/30 处理业务

            // 返回成功，告知对方
            return successNotifyStr;

        }

        return null;
    }
}

