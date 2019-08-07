package com.erp;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hlsofttech.entity.vo.ShopIdVO;
import com.hlsofttech.platform.meituan.sign.SignHelper;
import com.hlsofttech.platform.meituan.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 测试
 * @Date: 2019/8/5 14:53
 * @Author: suncy
 **/
@Slf4j
public class TestERPgetShopIds {

    public static String appkey = "f91e95c6-ebb5-4451-bafa-5550d9dfd9b5";
    public static String app_secret = "7BB4DDA93C2972B9D9E447EE30E0A772";
    public static String timestamp = String.valueOf(DateUtil.unixTime());

    @Test
    public void testERPgetShopIds() {

        try {
            // 组装参数
            Map<String, String> params = new HashMap<>();
            params.put("appkey", appkey);
            params.put("timestamp", timestamp);

            List<ShopIdVO> data = new ArrayList<>();
            ShopIdVO shopIdDetailVO1 = new ShopIdVO();
            shopIdDetailVO1.setCode("A0000001");
            data.add(shopIdDetailVO1);
            ShopIdVO shopIdDetailVO2 = new ShopIdVO();
            shopIdDetailVO2.setCode("A0000002");
            data.add(shopIdDetailVO2);

            params.put("data", JSONObject.toJSON(data).toString());
            System.out.println("data:" + JSONObject.toJSON(data).toString());

            // 加密
            String sign = SignHelper.generateSign(params, app_secret);

            Map<String, Object> params2 = new HashMap<>();
            params2.put("appkey", appkey);
            params2.put("timestamp", timestamp);
            params2.put("sign", sign);
            params2.put("data", data);
            String jsonString = JSON.toJSONString(params2);
            System.out.println("传入参数加密完成：" + jsonString);


            // 验签
            JSONObject jsonObject = (JSONObject) JSONObject.parse(jsonString);
            Map<String, String> paramsMap = new HashMap<>();
            paramsMap.put("appkey", jsonObject.getString("appkey"));
            paramsMap.put("timestamp", jsonObject.getString("timestamp"));
            paramsMap.put("sign", jsonObject.getString("sign"));
            paramsMap.put("data", jsonObject.getString("data"));

            String nowSign = SignHelper.generateSign(paramsMap, app_secret);
            log.info("回调收到的sign【" + params2.get("sign") + "】，参数加密后的sign【" + nowSign + "】");
            boolean flag = params2.get("sign").equals(nowSign);

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
}
