package com.hlsofttech.delivery.platform.meituan.constants;

/**
 * @Description: 开放平台账号配置信息
 * 后面再放在properties中
 * @Date: 2019/7/24
 **/
public class OpenApiConfig {

    /**
     * 版本
     */
    public static final String MEITUAN_VERSION = "1.0";

    /**
     * 说明：品类需按门店真实配送品类选择，如无法判断可咨询您的销售经理。
     * https://peisong.meituan.com/open/doc#section4-5
     */
    public static final Integer MEITUAN_CATEGORY = 200;
    /**
     * 说明：品类需按门店真实配送品类选择，如无法判断可咨询您的销售经理。
     * https://peisong.meituan.com/open/doc#section4-5
     */
    public static final Integer MEITUAN_SECONDCATEGORY = 200001;

    /**
     * 坐标类型，0：火星坐标（高德，腾讯地图均采用火星坐标） 1：百度坐标 （默认值为0）
     */
    public static final Integer coordinateType = 0;
    /**
     * 配送服务代码，详情见合同
     * <p>
     * 飞速达:4002
     * <p>
     * 快速达:4011
     * <p>
     * 及时达:4012
     * <p>
     * 集中送:4013
     * <p>
     * 例如：4011,4012(多个英文逗号隔开)
     */
    public static final String deliveryServiceCodes = "4002,4011";

    /**
     * 正式账号 appkey，用于线上环境真实发单
     */
    public static final String FORMAL_APP_KEY = "5871a2757c8343b28f5d9d9e4b9e110d";

    /**
     * 正式账号 secret，用于线上环境真实发单
     */
    public static final String FORMAL_SECRET = "i#+6vf%qK*?mu-064tF%)}Bx0Lq*9lmFN*o+mOXfTm.SC}TelV<a>ZgvoeUzH9a8";

    /**
     * 测试账号 appkey，用于线上环境联调测试
     */
    public static final String TEST_APP_KEY = "cf218a5307df4306875e6fd91420acce";

    /**
     * 测试账号 secret，用于线上环境联调测试
     */
    public static final String TEST_SECRET = "*}#*|DP5duEz4)HZ6<ScA1>!-rQU5Js-mtXfP-yP&H8q[q2^cEQ8tiKW=s:enZzv";

    /**
     * 测试账号 appkey，用于线上环境联调测试
     */
    public static final String APP_KEY = TEST_APP_KEY;

    /**
     * 测试账号 secret，用于线上环境联调测试
     */
    public static final String SECRET = TEST_SECRET;


}
