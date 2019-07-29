package com.hlsofttech.delivery.platform.meituan;

import com.hlsofttech.constant.delivery.OrderTypeMeituan;
import com.hlsofttech.delivery.platform.meituan.constants.OpenApiConfig;
import com.hlsofttech.delivery.platform.meituan.constants.RequestConstant;
import com.hlsofttech.delivery.platform.meituan.request.CreateOrderByCoordinatesRequest;
import com.hlsofttech.delivery.platform.meituan.sign.SignHelper;
import com.hlsofttech.delivery.platform.meituan.util.DateUtil;
import com.hlsofttech.delivery.platform.meituan.util.HttpClient;
import com.hlsofttech.delivery.platform.meituan.util.ParamBuilder;
import com.hlsofttech.delivery.platform.meituan.vo.OpenApiGood;
import com.hlsofttech.delivery.platform.meituan.vo.OpenApiGoods;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Map;

/**
 * Created by gw on 2018/1/26.
 */
public class TestCreateByCoordinates {
	private static final Log logger = LogFactory.getLog(TestCreateOrderByShop.class);

	// TEST_DELIVERY_ID，配送唯一标识，实际实现时需要替换为自己的delivery_id
	private static Long TEST_DELIVERY_ID = Long.valueOf(DateUtil.unixTime());

	// TEST_ORDER_ID 订单号，实际实现时需要替换为自己的订单id
	private static String TEST_ORDER_ID = String.valueOf(DateUtil.unixTime());


	@Test
	public void testCreateByCoordinates() throws Exception {
		String appkey = OpenApiConfig.APP_KEY;
		String secret = OpenApiConfig.SECRET;

		CreateOrderByCoordinatesRequest request = buildMockRequest(appkey);

		Map<String, String> params = ParamBuilder.convertToMap(request);
		String sign = SignHelper.generateSign(params, secret);

		params.put("sign", sign);

		try {
			String res = HttpClient.post(RequestConstant.ORDER_CREATE_BY_COORDINATES, params);
			logger.info(String.format("reponse data: %s", res));
		} catch (Exception e) {
			throw e;
		}
	}

	private CreateOrderByCoordinatesRequest buildMockRequest(String appkey) {
		CreateOrderByCoordinatesRequest request = new CreateOrderByCoordinatesRequest();
		request.setAppkey(appkey);
		request.setTimestamp(DateUtil.unixTime());
		request.setVersion("1.0");

		// 设置订单号及配送服务标识
		request.setDeliveryId(TEST_DELIVERY_ID);
		request.setOrderId(TEST_ORDER_ID);

		// 设置订单类型为预约单
		request.setOrderType(OrderTypeMeituan.PREBOOK);

		/**
		 * 设置配送服务编码
		 *
		 * 飞速达:4002
		 * 快速达:4011
		 * 及时达:4012
		 * 集中送:4013
		 */
		request.setDeliveryServiceCode(4012);

		/**
		 * 货物取货类型，目前只支持1
		 * 1：客户配送至站点
		 * 2：美团自取
		 */
		request.setPickUpType(1);

		// 设置取货人信息，请根据测试门店地址 在测试发单时合理设置送货地址
		request.setReceiverName("测试收货人");
		request.setReceiverPhone("18523657373");

		request.setReceiverProvince("北京");
		request.setReceiverCity("北京");
		request.setReceiverCountry("朝阳区");
		request.setReceiverTown("望京东路");
		request.setReceiverDetailAddress("科创大厦A座");

		// 收件人坐标
		request.setReceiverLng(97235458);
		request.setReceiverLat(31065074);
		// 收件人坐标类型
		request.setCoordinateType(0);

		// 设置预计送达时间为1小时以后
		request.setExpectedDeliveryTime(DateUtil.unixTime() + 3600L);

		// 设置门店流水号，门店流水号为一天中单个门店的订单序号，方便骑手线下到门店取货
		request.setPoiSeq("1");

		// 设置商品重量，单位为kg
		request.setGoodsWeight(new BigDecimal(3));

		// 设置商品详情
		OpenApiGoods openApiGoods = new OpenApiGoods();

		OpenApiGood openApiGood1 = new OpenApiGood();
		// 商品数量
		openApiGood1.setGoodCount(1);
		// 商品名称
		openApiGood1.setGoodName("进口红提");
		// 商品单价
		openApiGood1.setGoodPrice(new BigDecimal("20.5"));
		// 商品数量单位
		openApiGood1.setGoodUnit("盒");

		OpenApiGood openApiGood2 = new OpenApiGood();
		// 商品数量
		openApiGood2.setGoodCount(1);
		// 商品名称
		openApiGood2.setGoodName("进口芒果");
		// 商品单价
		openApiGood2.setGoodPrice(new BigDecimal("40.5"));
		// 商品数量单位
		openApiGood2.setGoodUnit("盒");

		openApiGoods.setGoods(Arrays.asList(openApiGood1, openApiGood2));

		request.setGoodsDetail(openApiGoods);

		// 设置备注信息
		request.setNote("小哥麻烦要一下发票");
		return request;
	}
}
