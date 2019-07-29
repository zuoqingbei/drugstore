package com.hlsofttech.delivery.platform.meituan;

import com.alibaba.fastjson.JSON;
import com.hlsofttech.common.ResultDO;
import com.hlsofttech.delivery.platform.Delivery;
import com.hlsofttech.delivery.platform.meituan.constants.ErrorContent;
import com.hlsofttech.delivery.platform.meituan.constants.OpenApiConfig;
import com.hlsofttech.delivery.platform.meituan.constants.RequestConstant;
import com.hlsofttech.delivery.platform.meituan.request.CreateOrderByShopRequest;
import com.hlsofttech.delivery.platform.meituan.request.CreateShopRequest;
import com.hlsofttech.delivery.platform.meituan.response.CreateOrderResponse;
import com.hlsofttech.delivery.platform.meituan.response.CreateShopResponse;
import com.hlsofttech.delivery.platform.meituan.sign.SignHelper;
import com.hlsofttech.delivery.platform.meituan.util.CamelCaseUtils;
import com.hlsofttech.delivery.platform.meituan.util.HttpClient;
import com.hlsofttech.delivery.platform.meituan.util.ParamBuilder;
import com.hlsofttech.entity.delivery.dto.CreateOrderDTO;
import com.hlsofttech.entity.delivery.dto.ShopInfoDTO;
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
    public ResultDO createShop(ShopInfoDTO shopInfoDTO) {
        log.info("===============美团配送：创建店铺=============================");
        log.info(shopInfoDTO.toString());

        try {
            // 构建请求数据
            CreateShopRequest request = MeituanDeliveryBulid.buildData(shopInfoDTO);

            // 数据格式转换
            Map<String, String> params = ParamBuilder.convertToMap(request);
            String sign = SignHelper.generateSign(params, OpenApiConfig.SECRET);

            params.put("sign", sign);

            String res = CamelCaseUtils.toCamelCase(HttpClient.post(RequestConstant.SHOP_CREATE, params));
            log.info(String.format("reponse data: %s", res));
            // 解析response信息
            CreateShopResponse response = JSON.parseObject(res, CreateShopResponse.class);

            return new ResultDO(true, response);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResultDO(false, ErrorContent.CREATE_SHOP_ERROR);
    }


    @Override
    public ResultDO createOrder(CreateOrderDTO createOrderDTO) {

        log.info("===============美团配送：创建订单=============================");
        log.info(createOrderDTO.toString());
        try {
            CreateOrderByShopRequest request = MeituanDeliveryBulid.buildData(createOrderDTO);

            Map<String, String> params = ParamBuilder.convertToMap(request);
            String sign = SignHelper.generateSign(params, OpenApiConfig.SECRET);

            params.put("sign", sign);

            String res = CamelCaseUtils.toCamelCase(HttpClient.post(RequestConstant.ORDER_CREATE_BY_SHOP, params));
            log.info(String.format("reponse data: %s", res));
            // 解析response信息
            CreateOrderResponse response = JSON.parseObject(res, CreateOrderResponse.class);

            return new ResultDO(true, response);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResultDO(false, ErrorContent.CREATE_ORDER_ERROR);
    }
}
