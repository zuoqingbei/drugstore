package com.hlsofttech.delivery.platform.meituan;

import com.hlsofttech.delivery.platform.meituan.constants.RequestConstant;
import com.hlsofttech.delivery.platform.meituan.request.EvaluateRequest;
import com.hlsofttech.delivery.platform.meituan.util.ParamBuilder;
import com.hlsofttech.platform.meituan.constants.OpenApiConfig;
import com.hlsofttech.platform.meituan.sign.SignHelper;
import com.hlsofttech.platform.meituan.util.DateUtil;
import com.hlsofttech.platform.meituan.util.HttpClient;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import java.util.Map;

public class TestEvaluate {

    private static final Log logger = LogFactory.getLog(TestEvaluate.class);
    // 配送唯一标识，实际实现时需要替换为自己的delivery_id
    private static Long TEST_DELIVERY_ID = 1516949299L;
    //美团配送内部订单id，最长不超过32个字符，实际实现时需要替换为自己的
    private static String MT_PEISONG_ID = "1516949300027436";

    @Test
    public void testEvaluate() throws Exception {
        String appkey = OpenApiConfig.APP_KEY;
        String secret = OpenApiConfig.SECRET;

        EvaluateRequest request = buildMockRequest(appkey);

        Map<String, String> params = ParamBuilder.convertToMap(request);
        String sign = SignHelper.generateSign(params, secret);

        params.put("sign", sign);

        try {
            String res = HttpClient.post(RequestConstant.ORDER_EVALUATE, params);
            logger.info(String.format("reponse data: %s", res));
        } catch (Exception e) {
            throw e;
        }
    }

    private EvaluateRequest buildMockRequest(String appkey) {
        EvaluateRequest request = new EvaluateRequest();
        request.setAppkey(appkey);
        request.setTimestamp(DateUtil.unixTime());
        request.setVersion("1.0");

        request.setDeliveryId(TEST_DELIVERY_ID);
        request.setMtPeisongId(MT_PEISONG_ID);
        request.setScore(1);
        request.setCommentContent("小哥神速!");

        return request;
    }

}