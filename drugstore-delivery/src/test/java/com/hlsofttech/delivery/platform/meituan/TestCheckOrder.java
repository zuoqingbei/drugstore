package com.hlsofttech.delivery.platform.meituan;

import com.hlsofttech.delivery.platform.meituan.constants.RequestConstant;
import com.hlsofttech.delivery.platform.meituan.request.CheckRequest;
import com.hlsofttech.delivery.platform.meituan.util.ParamBuilder;
import com.hlsofttech.platform.meituan.constants.OpenApiConfig;
import com.hlsofttech.platform.meituan.sign.SignHelper;
import com.hlsofttech.platform.meituan.util.DateUtil;
import com.hlsofttech.platform.meituan.util.HttpClient;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import java.util.Map;

public class TestCheckOrder {
	private static final Log logger = LogFactory.getLog(TestCheckOrder.class);

	@Test
	public void testCheckOrder() throws Exception {
		String appkey = OpenApiConfig.APP_KEY;
		String secret = OpenApiConfig.SECRET;

		CheckRequest request = buildMockRequest(appkey);

		Map<String, String> params = ParamBuilder.convertToMap(request);
		String sign = SignHelper.generateSign(params, secret);

		params.put("sign", sign);

		try {
			String res = HttpClient.post(RequestConstant.ORDER_CHECK_DELIVERY_ABILITY, params);
			logger.info(String.format("reponse data: %s", res));
		} catch (Exception e) {
			throw e;
		}
	}

	private CheckRequest buildMockRequest(String appkey) {
		CheckRequest request = new CheckRequest();
		request.setAppkey(appkey);
		request.setTimestamp(DateUtil.unixTime());
		request.setVersion("1.0");
		/**
		 * 设置配送服务编码
		 *
		 * 光速达:4001
		 * 快速达:4011
		 * 及时达:4012
		 * 集中送:4013
		 * 当天达:4021
		 */
		request.setDeliveryServiceCode(4001);
		// 设置测试门店 id，测试门店的坐标地址为 97235456,31065079（高德坐标），配送范围3km
		request.setShopId("test_0001");
		// 收件人地址，最长不超过 512 个字符
		request.setReceiverAddress("测试收货人地址");
		//收件人经度（高德坐标），高德坐标 *（ 10 的六次方），如 116398419
		request.setReceiverLng(97235456);
		//收件人纬度（高德坐标），高德坐标 *（ 10 的六次方），如 39985005
		request.setReceiverLat(31065079);
		//预留字段，方便以后扩充校验规则，check_type = 1
		request.setCheckType(1);
		//模拟发单时间，时区为 GMT+8，当前距离 Epoch（1970年1月1日) 以秒计算的时间，即 unix-timestamp。
		request.setMockOrderTime(1516305600L);

		return request;
	}

}