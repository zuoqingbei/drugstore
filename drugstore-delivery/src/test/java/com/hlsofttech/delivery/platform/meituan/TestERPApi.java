package com.hlsofttech.delivery.platform.meituan;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hlsofttech.platform.meituan.sign.SignHelper;
import com.hlsofttech.platform.meituan.util.DateUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 测试ERP加密
 * @Date: 2019/8/5 14:53
 * @Author: suncy
 **/
@Slf4j
public class TestERPApi {

    public static String appkey = "f91e95c6-ebb5-4451-bafa-5550d9dfd9b5";
    public static String app_secret = "7BB4DDA93C2972B9D9E447EE30E0A772";
    public static String timestamp = String.valueOf(DateUtil.unixTime());
    public static String version = "1.0";

    @Data
    public class UnifiedCreditCodeDTO {
        private String unifiedCreditCode;
    }

    @Test
    public void testERPApi() {

        try {
            // 组装参数
            Map<String, String> params = new HashMap<>();
            params.put("appkey", appkey);
            params.put("timestamp", timestamp);
            params.put("version", version);

            List<UnifiedCreditCodeDTO> beanList = getUnifiedCreditCodeBeans();

            params.put("data", JSON.toJSONString(beanList));
            System.out.println("data:" + JSON.toJSONString(beanList));

            // 加密
            String sign = SignHelper.generateSign(params, app_secret);
            System.out.println(sign);
            params.put("sign", sign);

            String jsonString = JSON.toJSONString(params);

            System.out.println("传入参数加密完成：" + jsonString);



            // 验签
            JSONObject jsonObject = (JSONObject) JSONObject.parse(jsonString);
            Map<String, String> paramsMap = new HashMap<>();
            paramsMap.put("appkey", jsonObject.getString("appkey"));
            paramsMap.put("timestamp", jsonObject.getString("timestamp"));
            paramsMap.put("sign", jsonObject.getString("sign"));
            paramsMap.put("version", jsonObject.getString("version"));

            /*JSONArray array = jsonObject.getJSONArray("data");
            List<UnifiedCreditCodeDTO> listBean = new ArrayList<>();
            for (int i = 0; i < array.size(); i++) {
                UnifiedCreditCodeDTO beanA = new UnifiedCreditCodeDTO();
                JSONObject jo = array.getJSONObject(i);
                String unifiedCreditCodeStr = jo.getString("unifiedCreditCode");
                System.out.println(unifiedCreditCodeStr);
                beanA.setUnifiedCreditCode(unifiedCreditCodeStr);
                listBean.add(beanA);
            }*/
            paramsMap.put("data", jsonObject.getString("data"));

            String nowSign = SignHelper.generateSign(paramsMap, app_secret);
            log.info("回调收到的sign【" + params.get("sign") + "】，参数加密后的sign【" + nowSign + "】");
            boolean flag = params.get("sign").equals(nowSign);

            // 验签成功，开始处理业务
            if (flag) {
                log.info("验签成功,返回接收的参数，开始处理业务");
            } else {
                log.info("验签失败！！！！！！！！！！！！！！");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<UnifiedCreditCodeDTO> getUnifiedCreditCodeBeans() {
        List<UnifiedCreditCodeDTO> beanList = new ArrayList<>();
        UnifiedCreditCodeDTO bean = new UnifiedCreditCodeDTO();
        bean.setUnifiedCreditCode("44444444");
        beanList.add(bean);

        UnifiedCreditCodeDTO bean2 = new UnifiedCreditCodeDTO();
        bean2.setUnifiedCreditCode("555555555");
        beanList.add(bean2);

        UnifiedCreditCodeDTO bean3 = new UnifiedCreditCodeDTO();
        bean3.setUnifiedCreditCode("6666666666");
        beanList.add(bean3);
        return beanList;
    }
}
