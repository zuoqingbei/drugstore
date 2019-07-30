package com.hlsofttech.delivery.platform.meituan;

import com.alibaba.fastjson.JSON;
import com.hlsofttech.delivery.platform.Delivery;
import com.hlsofttech.delivery.platform.meituan.constants.ErrorContent;
import com.hlsofttech.delivery.platform.meituan.constants.RequestConstant;
import com.hlsofttech.delivery.platform.meituan.request.CancelOrderRequest;
import com.hlsofttech.delivery.platform.meituan.request.CreateOrderByShopRequest;
import com.hlsofttech.delivery.platform.meituan.request.CreateShopRequest;
import com.hlsofttech.delivery.platform.meituan.request.QueryOrderRequest;
import com.hlsofttech.delivery.platform.meituan.response.CancelOrderResponse;
import com.hlsofttech.delivery.platform.meituan.response.CreateOrderResponse;
import com.hlsofttech.delivery.platform.meituan.response.CreateShopResponse;
import com.hlsofttech.delivery.platform.meituan.response.QueryOrderResponse;
import com.hlsofttech.delivery.platform.meituan.util.MeituanUtils;
import com.hlsofttech.delivery.platform.meituan.util.ParamBuilder;
import com.hlsofttech.entity.delivery.dto.CancelOrderDTO;
import com.hlsofttech.entity.delivery.dto.CreateOrderDTO;
import com.hlsofttech.entity.delivery.dto.QueryOrderDTO;
import com.hlsofttech.entity.delivery.dto.ShopInfoDTO;
import com.hlsofttech.rsp.Result;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/***
 * @Description: 配送服务美团配送平台实现
 * @Date: 2019/7/25 13:41
 * @Author: suncy
 **/
@Slf4j
public class MeituanDelivery implements Delivery {

    private static MeituanDelivery instance;

    private MeituanDelivery() {
    }

    public static synchronized MeituanDelivery getInstance() {
        if (instance == null) {
            instance = new MeituanDelivery();
        }
        return instance;
    }

    @Override
    public Result createShop(ShopInfoDTO shopInfoDTO) {
        log.info("===============美团配送：创建门店=============================");
        log.info(shopInfoDTO.toString());

        try {
            // 构建请求数据
            CreateShopRequest request = MeituanDeliveryBulid.buildData(shopInfoDTO);

            // 数据格式转换
            Map<String, String> params = ParamBuilder.convertToMap(request);

            // 加密并请求
            String res = MeituanUtils.signAndRequest(params, RequestConstant.SHOP_CREATE);

            // 解析response信息
            CreateShopResponse response = JSON.parseObject(res, CreateShopResponse.class);

            // 返回
            return MeituanUtils.getResult(response, response.getCode(), response.getMessage());

        } catch (Exception e) {
            log.error(String.format("美团配送:创建门店失败 %s", e));
            e.printStackTrace();
        }

        return new Result(false, ErrorContent.CREATE_SHOP_ERROR);
    }

    @Override
    public Result createOrder(CreateOrderDTO createOrderDTO) {

        log.info("===============美团配送：创建订单=============================");
        log.info(createOrderDTO.toString());

        try {
            // 构建数据
            CreateOrderByShopRequest request = MeituanDeliveryBulid.buildData(createOrderDTO);

            // 数据格式转换
            Map<String, String> params = ParamBuilder.convertToMap(request);

            // 加密并请求
            String res = MeituanUtils.signAndRequest(params, RequestConstant.ORDER_CREATE_BY_SHOP);

            // 解析response信息
            CreateOrderResponse response = JSON.parseObject(res, CreateOrderResponse.class);

            // 返回
            return MeituanUtils.getResult(response, response.getCode(), response.getMessage());

        } catch (Exception e) {
            log.error(String.format("美团配送:创建订单失败 %s", e));
            e.printStackTrace();
        }
        return new Result(false, ErrorContent.CREATE_ORDER_ERROR);
    }

    @Override
    public Result cancelOrder(CancelOrderDTO cancelOrderDTO) {
        log.info("===============美团配送：取消订单 cancelOrder=============================");
        log.info(cancelOrderDTO.toString());

        try {
            // 构建数据
            CancelOrderRequest request = MeituanDeliveryBulid.buildData(cancelOrderDTO);

            // 数据格式转换
            Map<String, String> params = ParamBuilder.convertToMap(request);

            // 加密并请求
            String res = MeituanUtils.signAndRequest(params, RequestConstant.ORDER_CANCEL);

            // 解析response信息
            CancelOrderResponse response = JSON.parseObject(res, CancelOrderResponse.class);

            // 返回
            return MeituanUtils.getResult(response, response.getCode(), response.getMessage());

        } catch (Exception e) {
            log.error(String.format("美团配送:取消订单失败 %s", e));
            e.printStackTrace();
        }
        return new Result(false, ErrorContent.CANCEL_ORDER_ERROR);
    }

    @Override
    public Result queryOrder(QueryOrderDTO queryOrderDTO) {
        log.info("===============美团配送：查询订单 queryOrder =============================");
        log.info(queryOrderDTO.toString());

        try {
            QueryOrderRequest request = MeituanDeliveryBulid.buildData(queryOrderDTO);

            Map<String, String> params = ParamBuilder.convertToMap(request);

            // 加密并请求
            String res = MeituanUtils.signAndRequest(params, RequestConstant.ORDER_QUERY);

            // 解析response信息
            QueryOrderResponse response = JSON.parseObject(res, QueryOrderResponse.class);

            // 返回
            return MeituanUtils.getResult(response, response.getCode(), response.getMessage());

        } catch (Exception e) {
            log.error(String.format("美团配送:取消订单失败 %s", e));
            e.printStackTrace();
        }

        return new Result(false, ErrorContent.QUERY_ORDER_ERROR);
    }
}
