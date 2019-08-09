package com.hlsofttech.delivery.platform.meituan;

import com.alibaba.fastjson.JSON;
import com.hlsofttech.delivery.platform.Delivery;
import com.hlsofttech.delivery.platform.meituan.constants.ErrorContent;
import com.hlsofttech.delivery.platform.meituan.constants.OpenApiConfig;
import com.hlsofttech.delivery.platform.meituan.constants.RequestConstant;
import com.hlsofttech.delivery.platform.meituan.request.*;
import com.hlsofttech.delivery.platform.meituan.response.*;
import com.hlsofttech.delivery.platform.meituan.util.DateUtil;
import com.hlsofttech.delivery.platform.meituan.util.MeituanUtils;
import com.hlsofttech.delivery.platform.meituan.util.ParamBuilder;
import com.hlsofttech.entity.delivery.dto.*;
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

    @Override
    public Result orderEvaluate(OrderEvaluateDTO orderEvaluateDTO) {
        log.info("===============美团配送：评价骑手 orderEvaluate =============================");
        log.info(orderEvaluateDTO.toString());

        try {
            EvaluateRequest request = MeituanDeliveryBulid.buildData(orderEvaluateDTO);

            Map<String, String> params = ParamBuilder.convertToMap(request);

            // 加密并请求
            String res = MeituanUtils.signAndRequest(params, RequestConstant.ORDER_EVALUATE);

            // 解析response信息
            EvaluateResponse response = JSON.parseObject(res, EvaluateResponse.class);

            // 返回
            return MeituanUtils.getResult(response, response.getCode(), response.getMessage());

        } catch (Exception e) {
            log.error(String.format("美团配送:评价骑手失败 %s", e));
            e.printStackTrace();
        }

        return new Result(false, ErrorContent.ORDER_EVALUATE_ERROR);
    }

    @Override
    public Result orderCheck(OrderCheckDTO orderCheckDTO) {
        log.info("===============美团配送：配送能力校验 orderCheck =============================");
        log.info(orderCheckDTO.toString());

        try {
            CheckRequest request = MeituanDeliveryBulid.buildData(orderCheckDTO);

            Map<String, String> params = ParamBuilder.convertToMap(request);

            // 加密并请求
            String res = MeituanUtils.signAndRequest(params, RequestConstant.ORDER_CHECK_DELIVERY_ABILITY);

            // 解析response信息
            CheckResponse response = JSON.parseObject(res, CheckResponse.class);

            // 返回
            return MeituanUtils.getResult(response, response.getCode(), response.getMessage());

        } catch (Exception e) {
            log.error(String.format("美团配送:配送能力校验 %s", e));
            e.printStackTrace();
        }

        return new Result(false, ErrorContent.ORDER_CHECK_ERROR);
    }

    @Override
    public Result getRiderLocation(QueryOrderDTO queryOrderDTO) {
        log.info("===============美团配送：获取骑手位置 getRiderLocation =============================");
        log.info(queryOrderDTO.toString());

        try {
            QueryOrderRequest request = MeituanDeliveryBulid.buildData(queryOrderDTO);

            Map<String, String> params = ParamBuilder.convertToMap(request);

            // 加密并请求
            String res = MeituanUtils.signAndRequest(params, RequestConstant.GET_RIDER_LOCATION);

            // 解析response信息，此处用AbstractResponse只是为了获取code和message
            AbstractResponse response = JSON.parseObject(res, AbstractResponse.class);

            // 直接返回res
            return MeituanUtils.getResult(res, response.getCode(), response.getMessage());

        } catch (Exception e) {
            log.error(String.format("美团配送:获取骑手位置 %s", e));
            e.printStackTrace();
        }

        return new Result(false, ErrorContent.ORDER_CHECK_ERROR);
    }

    @Override
    public Result queryShopArea(QueryShopAreaDTO queryShopAreaDTO) {
        log.info("===============美团配送：查询合作方配送范围 getRiderLocation =============================");
        log.info(queryShopAreaDTO.toString());

        try {
            QueryShopAreaRequest request = MeituanDeliveryBulid.buildData(queryShopAreaDTO);

            Map<String, String> params = ParamBuilder.convertToMap(request);

            // 加密并请求
            String res = MeituanUtils.signAndRequest(params, RequestConstant.GET_RIDER_LOCATION);

            // 解析response信息，此处用AbstractResponse只是为了获取code和message
            AbstractResponse response = JSON.parseObject(res, AbstractResponse.class);

            // 直接返回res
            return MeituanUtils.getResult(res, response.getCode(), response.getMessage());

        } catch (Exception e) {
            log.error(String.format("美团配送:查询合作方配送范围 %s", e));
            e.printStackTrace();
        }

        return new Result(false, ErrorContent.QUERY_SHOP_AREA_ERROR);
    }

    @Override
    public Result queryShop(String shopId) {
        log.info("===============美团配送：查询门店信息 queryShop =============================");
        log.info(shopId);

        try {
            QueryShopRequest request = new QueryShopRequest();
            request.setAppkey(OpenApiConfig.APP_KEY);
            request.setTimestamp(DateUtil.unixTime());
            request.setVersion(OpenApiConfig.MEITUAN_VERSION);
            request.setShopId(shopId);

            Map<String, String> params = ParamBuilder.convertToMap(request);

            // 加密并请求
            String res = MeituanUtils.signAndRequest(params, RequestConstant.SHOP_QUERY);

            // 解析response信息
            QueryShopResponse response = JSON.parseObject(res, QueryShopResponse.class);

            // 返回
            return MeituanUtils.getResult(response, response.getCode(), response.getMessage());

        } catch (Exception e) {
            log.error(String.format("美团配送:查询门店信息 %s", e));
            e.printStackTrace();
        }

        return new Result(false, ErrorContent.QUERY_SHOP_AREA_ERROR);
    }
}
