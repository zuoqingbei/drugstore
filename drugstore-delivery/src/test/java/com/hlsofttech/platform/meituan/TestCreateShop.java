package com.hlsofttech.platform.meituan;

import com.hlsofttech.delivery.platform.meituan.constants.RequestConstant;
import com.hlsofttech.delivery.platform.meituan.request.CreateShopRequest;
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
 * @Description: 创建门店TEST
 * @Date: 2019/7/24 17:44
 * @Author: suncy
 **/
public class TestCreateShop {
    private static final Log logger = LogFactory.getLog(TestCreateShop.class);

    // 门店ID
    private static String TEST_SHOP_ID = String.valueOf(DateUtil.unixTime());

    // 门店名称
    private static String TEST_SHOP_NAME = "门店" + TEST_SHOP_ID;


    @Test
    public void testCreateShop() throws Exception {
        String appkey = OpenApiConfig.APP_KEY;
        String secret = OpenApiConfig.SECRET;

        CreateShopRequest request = buildMockRequest(appkey);

        Map<String, String> params = ParamBuilder.convertToMap(request);
        String sign = SignHelper.generateSign(params, secret);

        params.put("sign", sign);

        try {
            String res = HttpClient.post(RequestConstant.SHOP_CREATE, params);
            logger.info(String.format("reponse data: %s", res));
        } catch (Exception e) {
            throw e;
        }
    }

    private CreateShopRequest buildMockRequest(String appkey) {
        CreateShopRequest request = new CreateShopRequest();
        request.setAppkey(appkey);
        request.setTimestamp(DateUtil.unixTime());
        request.setVersion("1.0");

        request.setShopId(TEST_SHOP_ID);
        request.setShopName(TEST_SHOP_NAME);
        request.setCategory(200);
        request.setSecondCategory(200001);
        request.setContactName("孙曹永");
        request.setContactPhone("15192509019");
        request.setContactEmail("suncaoyong@163.com");
        request.setShopAddress("青岛市北宁安路");
        request.setShopAddressDetail("888号");
        request.setShopLng(116398419);
        request.setShopLat(39985005);
        request.setCoordinateType(0);
        request.setDeliveryServiceCodes("4002");
        request.setBusinessHours("[{\"beginTime\":\"00:00\",\"endTime\":\"24:00\"}]");

        return request;
    }

}
