package com.hlsofttech.product;

import com.hlsofttech.platform.meituan.util.HttpUtils;
import com.hlsofttech.service.product.DrugsSyncService;
import com.hlsofttech.utils.MD5Util;
import com.hlsofttech.utils.UUIDUtils;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;
import java.util.concurrent.*;

/***
 * @Author: suntf
 * @Description:测试 请求药智网
 * @Date: 2019/8/7
 **/
public class DrugsSyncTest extends BaseTest{
    public static String url = "http://openapi-dev.yaozh.com/instruct";
    //    public static String url = "openapi.yaozh.com";
    // 药智网提供的appkey（唯一码）
    public static String appKey = "99190806";
    // 药智网提供的appsecret（密文,不可泄漏）
    public static String appSecret = "b6ec3f178d98e40b114ca3580e5aabfe";
    @Resource
    DrugsSyncService drugsSyncService;

    @Test
    public void testService() {
        drugsSyncService.syncDrugsStart();
    }

    @Test
    public void testMain() {
        Map<String, Object> map = new TreeMap<>();
        map.put("g1d", 333);
        map.put("f1d", 222);
        map.put("a1d", 111);
        System.out.println(map.toString());
    }

    @Test
    public void testDrugsListSync() {
        ExecutorService syncDrugsCacheExecutor = Executors.newFixedThreadPool(5);
        List<Callable<Boolean>> callableList = new ArrayList<>();

        Callable<Boolean> call = new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                try {
                    return syncDrug();
                } catch (Exception e) {
                    System.out.println("执行异常：" + e);
                    return null;
                }
            }
        };
        callableList.add(call);

        try {
            List<Future<Boolean>> futureList = syncDrugsCacheExecutor.invokeAll(callableList);
            for (Future<Boolean> future : futureList) {
                Boolean flag = future.get(5, TimeUnit.MINUTES);
                if (flag) {
                    System.out.println(" 成功");
                } else {
                    System.out.println(" 失败");
                }
            }
        } catch (Exception e) {
            System.out.println(" ----- 异常 -----" + e.getMessage());
        }

    }

    private boolean syncDrug() {
        Map<String, Object> methodParam = new HashMap<>();
        int t = (int) (System.currentTimeMillis() / 1000);
        methodParam.put("page", 1);
        methodParam.put("pageSize", 100);
        methodParam.put("appkey", appKey);
        // 当前时间戳
        methodParam.put("t", Long.toString(t));
        // r : 随机字符串:MD5(t+appkey+15位随机字符串)；
        String str = UUIDUtils.getUuid().substring(0, 15);
        String r = MD5Util.MD5(t + appKey + str);
        methodParam.put("r", r);
        try {
            // 业务参数
            String businessParamStr = "";
            if (methodParam.size() > 0) {
                Map<String, Object> businessParam = new TreeMap<>();
                for (String key : methodParam.keySet()) {
                    businessParam.put(key, methodParam.get(key));
                }
                StringBuilder sb = new StringBuilder("&");
                for (String key : businessParam.keySet()) {
                    sb.append(key).append("=").append(businessParam.get(key)).append("&");
                }
                businessParamStr = sb.toString().substring(0, sb.length() - 1);
            }
            // 组装参数
            Map<String, String> params = new LinkedHashMap<>();
            params.put("appkey", appKey);
            params.put("r", r);
            // 当前时间戳
            params.put("t", Long.toString(t));
            String T = businessParamStr;
            String sign = MD5Util.MD5(MD5Util.MD5(appSecret + T + appSecret)).toUpperCase();
            params.put("sign", sign);
            params.put("page", "1");
            params.put("pageSize", "100");

            String ret = HttpUtils.doGet(buildUrl(url, params));
            System.out.println(ret);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    private static String buildUrl(String host, Map<String, String> querys) throws UnsupportedEncodingException {
        StringBuilder sbUrl = new StringBuilder();
        sbUrl.append(host);
        if (null != querys) {
            StringBuilder sbQuery = new StringBuilder();
            for (Map.Entry<String, String> query : querys.entrySet()) {
                if (0 < sbQuery.length()) {
                    sbQuery.append("&");
                }
                if (StringUtils.isBlank(query.getKey()) && !StringUtils.isBlank(query.getValue())) {
                    sbQuery.append(query.getValue());
                }
                if (!StringUtils.isBlank(query.getKey())) {
                    sbQuery.append(query.getKey());
                    if (!StringUtils.isBlank(query.getValue())) {
                        sbQuery.append("=");
                        sbQuery.append(URLEncoder.encode(query.getValue(), "utf-8"));
                    }
                }
            }
            if (0 < sbQuery.length()) {
                sbUrl.append("?").append(sbQuery);
            }
        }
        System.out.println("请求url：" + sbUrl);
        return sbUrl.toString();
    }
}
