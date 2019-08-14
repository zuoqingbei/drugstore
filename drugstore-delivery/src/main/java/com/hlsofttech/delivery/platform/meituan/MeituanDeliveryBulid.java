package com.hlsofttech.delivery.platform.meituan;

import com.hlsofttech.delivery.platform.meituan.request.*;
import com.hlsofttech.delivery.platform.meituan.util.DateUtil;
import com.hlsofttech.delivery.platform.meituan.util.DeliveryTimeUtil;
import com.hlsofttech.delivery.platform.meituan.vo.OpenApiGood;
import com.hlsofttech.delivery.platform.meituan.vo.OpenApiGoods;
import com.hlsofttech.entity.delivery.dto.*;
import com.hlsofttech.platform.meituan.constants.OpenApiConfig;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/***
 * @Description: 配送服务美团配送平台实现
 * @Date: 2019/7/25 13:41
 * @Author: suncy
 **/
@Slf4j
public class MeituanDeliveryBulid {

    /**
     * 构建创建门店请求信息
     */
    public static CreateShopRequest buildData(ShopInfoDTO shopInfoDTO) {
        CreateShopRequest request = new CreateShopRequest();
        request.setAppkey(OpenApiConfig.APP_KEY);
        request.setTimestamp(DateUtil.unixTime());
        request.setVersion(OpenApiConfig.MEITUAN_VERSION);

        request.setShopId(shopInfoDTO.getShopId());
        request.setShopName(shopInfoDTO.getShopName());
        request.setCategory(OpenApiConfig.MEITUAN_CATEGORY);
        request.setSecondCategory(OpenApiConfig.MEITUAN_SECONDCATEGORY);
        request.setContactName(shopInfoDTO.getContactName());
        request.setContactPhone(shopInfoDTO.getContactPhone());
        request.setContactEmail(shopInfoDTO.getContactEmail());
        request.setShopAddress(shopInfoDTO.getShopAddress());
        request.setShopAddressDetail(shopInfoDTO.getShopAddressDetail());
        request.setShopLng(shopInfoDTO.getShopLng());
        request.setShopLat(shopInfoDTO.getShopLat());
        request.setCoordinateType(OpenApiConfig.coordinateType);
        request.setDeliveryServiceCodes(OpenApiConfig.deliveryServiceCodes);
        request.setBusinessHours(shopInfoDTO.getDeliveryHours());

        return request;
    }

    /**
     * 构建创建订单请求信息
     */
    public static CreateOrderByShopRequest buildData(CreateOrderDTO createOrderDTO) {
        CreateOrderByShopRequest request = new CreateOrderByShopRequest();
        request.setAppkey(OpenApiConfig.APP_KEY);
        request.setTimestamp(DateUtil.unixTime());
        request.setVersion(OpenApiConfig.MEITUAN_VERSION);
        request.setDeliveryId(createOrderDTO.getDeliveryId());
        request.setOrderId(createOrderDTO.getOrderId());
        request.setOrderType(createOrderDTO.getOrderTypeMeituan());
        request.setDeliveryServiceCode(createOrderDTO.getDeliveryServiceCode());
        // 设置测试门店 id，测试门店的坐标地址为 97235456,31065079（高德坐标），配送范围3km
        request.setShopId(createOrderDTO.getShopId());
        request.setReceiverName(createOrderDTO.getReceiverName());
        request.setReceiverAddress(createOrderDTO.getReceiverAddress());
        request.setReceiverPhone(createOrderDTO.getReceiverPhone());

        // 收件人坐标
        request.setReceiverLng(createOrderDTO.getReceiverLng());
        request.setReceiverLat(createOrderDTO.getReceiverLat());
        request.setCoordinateType(createOrderDTO.getCoordinateType());

        // 计算送达时间
        request.setExpectedDeliveryTime(DeliveryTimeUtil.calcDeliveryTime(createOrderDTO.getDeliveryServiceCode(), DateUtil.unixTimeLong()));
        request.setPoiSeq(createOrderDTO.getPoiSeq());
        request.setGoodsWeight(createOrderDTO.getGoodsWeight());

        OpenApiGoods openApiGoods = new OpenApiGoods();
        List<OpenApiGood> goods = new ArrayList<>();

        if (createOrderDTO.getGoodsDetail() != null && createOrderDTO.getGoodsDetail().getGoods() != null &&
                createOrderDTO.getGoodsDetail().getGoods().size() > 0) {
            for (GoodDTO goodDTO : createOrderDTO.getGoodsDetail().getGoods()) {
                OpenApiGood openApiGood = new OpenApiGood();

                openApiGood.setGoodCount(goodDTO.getGoodCount());
                openApiGood.setGoodName(goodDTO.getGoodName());
                openApiGood.setGoodPrice(goodDTO.getGoodPrice());
                openApiGood.setGoodUnit(goodDTO.getGoodUnit());

                goods.add(openApiGood);
            }
            openApiGoods.setGoods(goods);
        }
        // 设置备注信息
        request.setNote(createOrderDTO.getNote());

        return request;
    }

    /**
     * 构建取消订单请求信息
     */
    public static CancelOrderRequest buildData(CancelOrderDTO cancelOrderDTO) {
        CancelOrderRequest request = new CancelOrderRequest();
        request.setAppkey(OpenApiConfig.APP_KEY);
        request.setTimestamp(DateUtil.unixTime());
        request.setVersion(OpenApiConfig.MEITUAN_VERSION);

        request.setDeliveryId(cancelOrderDTO.getDeliveryId());
        request.setMtPeisongId(cancelOrderDTO.getMtPeisongId());
        request.setCancelOrderReasonId(cancelOrderDTO.getCancelOrderReasonId());
        request.setCancelReason(cancelOrderDTO.getCancelReason());

        return request;
    }

    /**
     * 构建查询订单请求信息
     */
    public static QueryOrderRequest buildData(QueryOrderDTO queryOrderDTO) {
        QueryOrderRequest request = new QueryOrderRequest();
        request.setAppkey(OpenApiConfig.APP_KEY);
        request.setTimestamp(DateUtil.unixTime());
        request.setVersion(OpenApiConfig.MEITUAN_VERSION);

        request.setDeliveryId(queryOrderDTO.getDeliveryId());
        request.setMtPeisongId(queryOrderDTO.getMtPeisongId());

        return request;
    }

    /**
     * 构建评价骑手请求信息
     */
    public static EvaluateRequest buildData(OrderEvaluateDTO orderEvaluateDTO) {
        EvaluateRequest request = new EvaluateRequest();
        request.setAppkey(OpenApiConfig.APP_KEY);
        request.setTimestamp(DateUtil.unixTime());
        request.setVersion(OpenApiConfig.MEITUAN_VERSION);

        request.setDeliveryId(orderEvaluateDTO.getDeliveryId());
        request.setMtPeisongId(orderEvaluateDTO.getMtPeisongId());
        request.setScore(orderEvaluateDTO.getScore());
        request.setCommentContent(orderEvaluateDTO.getCommentContent());

        return request;
    }

    /**
     * 构建评价骑手请求信息
     */
    public static CheckRequest buildData(OrderCheckDTO orderCheckDTO) {
        CheckRequest request = new CheckRequest();
        request.setAppkey(OpenApiConfig.APP_KEY);
        request.setTimestamp(DateUtil.unixTime());
        request.setVersion(OpenApiConfig.MEITUAN_VERSION);

        request.setShopId(orderCheckDTO.getShopId());
        request.setDeliveryServiceCode(orderCheckDTO.getDeliveryServiceCode());
        request.setReceiverAddress(orderCheckDTO.getReceiverAddress());
        request.setReceiverLng(orderCheckDTO.getReceiverLat());
        request.setReceiverLat(orderCheckDTO.getReceiverLat());
        request.setCheckType(orderCheckDTO.getCheckType());
        request.setMockOrderTime(orderCheckDTO.getMockOrderTime());

        return request;
    }

    /**
     * 构建查询合作方配送范围请求信息
     */
    public static QueryShopAreaRequest buildData(QueryShopAreaDTO queryShopAreaDTO) {
        QueryShopAreaRequest request = new QueryShopAreaRequest();
        request.setAppkey(OpenApiConfig.APP_KEY);
        request.setTimestamp(DateUtil.unixTime());
        request.setVersion(OpenApiConfig.MEITUAN_VERSION);

        request.setDeliveryServiceCode(queryShopAreaDTO.getDeliveryServiceCode());
        request.setShopId(queryShopAreaDTO.getShopId());

        return request;
    }

}
