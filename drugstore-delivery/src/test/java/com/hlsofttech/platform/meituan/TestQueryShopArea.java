package com.hlsofttech.platform.meituan;

import com.hlsofttech.delivery.platform.meituan.constants.RequestConstant;
import com.hlsofttech.delivery.platform.meituan.request.QueryShopAreaRequest;
import com.hlsofttech.delivery.platform.meituan.util.ParamBuilder;
import com.hlsofttech.platform.meituan.constants.OpenApiConfig;
import com.hlsofttech.platform.meituan.sign.SignHelper;
import com.hlsofttech.platform.meituan.util.DateUtil;
import com.hlsofttech.platform.meituan.util.HttpClient;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import java.util.Map;

/**
 * 查询合作方门店配送范围的例子
 */
public class TestQueryShopArea {
    private static final Log logger = LogFactory.getLog(TestQueryShopArea.class);

    // 配送服务代码（即门店目前支持的服务包）
    private static Integer DELIVERY_SERVICE_CODE = 4002;

    // 门店id
    private static String SHOP_ID = "1497257786087057";

    @Test
    public void testQueryShopArea() throws Exception {
        String appkey = OpenApiConfig.APP_KEY;
        String secret = OpenApiConfig.SECRET;

        QueryShopAreaRequest request = buildMockRequest(appkey);

        Map<String, String> params = ParamBuilder.convertToMap(request);
        String sign = SignHelper.generateSign(params, secret);

        params.put("sign", sign);

        try {
            String res = HttpClient.post(RequestConstant.QUERY_SHOP_AREA, params);
            logger.info(String.format("reponse data: %s", res));
        } catch (Exception e) {
            throw e;
        }
    }

    private QueryShopAreaRequest buildMockRequest(String appkey) {
        QueryShopAreaRequest request = new QueryShopAreaRequest();
        request.setAppkey(appkey);
        request.setTimestamp(DateUtil.unixTime());
        request.setVersion("1.0");
        request.setDeliveryServiceCode(DELIVERY_SERVICE_CODE);
        request.setShopId(SHOP_ID);
        return request;
    }
}
