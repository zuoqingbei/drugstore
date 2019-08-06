package com.erp;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hlsofttech.entity.vo.DrugsAddVO;
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
 * @Description: 测试ERP加密
 * @Date: 2019/8/5 14:53
 * @Author: suncy
 **/
@Slf4j
public class TestERPdrugsSyn {

    public static String appkey = "f91e95c6-ebb5-4451-bafa-5550d9dfd9b5";
    public static String app_secret = "7BB4DDA93C2972B9D9E447EE30E0A772";
    public static String timestamp = String.valueOf(DateUtil.unixTime());

    @Test
    public void testERPdrugsSyn() {

        try {
            // 组装参数
            Map<String, String> params = new HashMap<>();
            params.put("appkey", appkey);
            params.put("timestamp", timestamp);

            List<DrugsAddVO> data = new ArrayList<>();
            DrugsAddVO vo1 = new DrugsAddVO();
            vo1.setCode("code1");
            vo1.setName("name1");
            vo1.setPrice(1111L);
            vo1.setStock(100);
            vo1.setUnit("盒");
            data.add(vo1);

            DrugsAddVO vo2 = new DrugsAddVO();
            vo2.setCode("code2");
            vo2.setName("name2");
            vo2.setPrice(2222L);
            vo2.setStock(200);
            vo2.setUnit("袋");
            data.add(vo2);

            params.put("data", JSON.toJSONString(data));
            System.out.println("data:" + JSON.toJSONString(data));

            // 加密
            String sign = SignHelper.generateSign(params, app_secret);

            Map<String, Object> params2 = new HashMap<>();
            params2.put("appkey", appkey);
            params2.put("timestamp", timestamp);
            params2.put("sign", sign);
            params2.put("data", data);
            String jsonString = JSON.toJSONString(params2);
            System.out.println("传入参数加密完成：" + jsonString);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
