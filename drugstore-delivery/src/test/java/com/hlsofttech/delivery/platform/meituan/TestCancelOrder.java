package com.hlsofttech.delivery.platform.meituan;

import com.hlsofttech.constant.delivery.CancelOrderReasonId;
import com.hlsofttech.delivery.platform.meituan.constants.RequestConstant;
import com.hlsofttech.delivery.platform.meituan.request.CancelOrderRequest;
import com.hlsofttech.delivery.platform.meituan.util.ParamBuilder;
import com.hlsofttech.platform.meituan.constants.OpenApiConfig;
import com.hlsofttech.platform.meituan.sign.SignHelper;
import com.hlsofttech.platform.meituan.util.DateUtil;
import com.hlsofttech.platform.meituan.util.HttpClient;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

/**
 * 取消订单例子
 */
public class TestCancelOrder {
    private static final Log logger = LogFactory.getLog(TestCancelOrder.class);

    // 测试delivery_id，可以替换为之前测试创建时返回的delivery_id
    private static Long TEST_DELIVERY_ID = 1497257791L;

    // 测试美团配送id，可以替换为之前测试创建时返回的mt_peisong_id
    private static String TEST_MT_PEISONG_ID = "1497257786087057";

    @Test
    public void testCancelOrder() throws IOException, NoSuchAlgorithmException {
        String appkey = OpenApiConfig.APP_KEY;
        String secret = OpenApiConfig.SECRET;

        CancelOrderRequest request = buildMockRequest(appkey);

        Map<String, String> params = ParamBuilder.convertToMap(request);
        String sign = SignHelper.generateSign(params, secret);

        params.put("sign", sign);

        try {
            String res = HttpClient.post(RequestConstant.ORDER_CANCEL, params);
            logger.info(String.format("reponse data: %s", res));
        } catch (Exception e) {
            throw e;
        }
    }

    private CancelOrderRequest buildMockRequest(String appkey) {
        CancelOrderRequest request = new CancelOrderRequest();
        request.setAppkey(appkey);
        request.setTimestamp(DateUtil.unixTime());
        request.setVersion("1.0");
        request.setDeliveryId(TEST_DELIVERY_ID);
        request.setMtPeisongId(TEST_MT_PEISONG_ID);
        request.setCancelOrderReasonId(CancelOrderReasonId.PARTNER_REASON);
        request.setCancelReason("测试取消");
        return request;
    }
}
