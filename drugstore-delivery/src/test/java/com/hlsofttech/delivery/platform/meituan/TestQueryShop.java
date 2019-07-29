package com.hlsofttech.delivery.platform.meituan;

import com.hlsofttech.delivery.platform.meituan.constants.OpenApiConfig;
import com.hlsofttech.delivery.platform.meituan.constants.RequestConstant;
import com.hlsofttech.delivery.platform.meituan.request.QueryShopRequest;
import com.hlsofttech.delivery.platform.meituan.sign.SignHelper;
import com.hlsofttech.delivery.platform.meituan.util.DateUtil;
import com.hlsofttech.delivery.platform.meituan.util.HttpClient;
import com.hlsofttech.delivery.platform.meituan.util.ParamBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import java.util.Map;

/**
 * 订单店铺例子
 */
public class TestQueryShop {
    private static final Log logger = LogFactory.getLog(TestCancelOrder.class);

    // 门店ID
    private static String TEST_SHOP_ID = "test_0001";

    @Test
    public void testQueryShop() throws Exception {
        String appkey = OpenApiConfig.APP_KEY;
        String secret = OpenApiConfig.SECRET;

        QueryShopRequest request = buildMockRequest(appkey);

        Map<String, String> params = ParamBuilder.convertToMap(request);
        String sign = SignHelper.generateSign(params, secret);

        params.put("sign", sign);

        try {
            String res = HttpClient.post(RequestConstant.SHOP_QUERY, params);
            logger.info(String.format("reponse data: %s", res));
        } catch (Exception e) {
            throw e;
        }
    }

    private QueryShopRequest buildMockRequest(String appkey) {
        QueryShopRequest request = new QueryShopRequest();
        request.setAppkey(appkey);
        request.setTimestamp(DateUtil.unixTime());
        request.setVersion("1.0");
        request.setShopId(TEST_SHOP_ID);
        return request;
    }
}
