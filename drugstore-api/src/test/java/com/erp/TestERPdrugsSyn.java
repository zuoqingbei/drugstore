package com.erp;

import com.alibaba.fastjson.JSONObject;
import com.hlsofttech.entity.vo.DrugsAddVO;
import com.hlsofttech.delivery.platform.meituan.sign.SignHelper;
import com.hlsofttech.delivery.platform.meituan.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 测试ERP
 * @Date: 2019/8/5 14:53
 * @Author: suncy
 **/
@Slf4j
public class TestERPdrugsSyn {

    public static String appkey = "f91e95c6-ebb5-4451-bafa-5550d9dfd9b5";
    public static String app_secret = "7BB4DDA93C2972B9D9E447EE30E0A772";

    @Test
    public void testERPdrugsSyn() {
        String timestamp = String.valueOf(DateUtil.unixTime());
        try {
            // 业务数据
            List<DrugsAddVO> data = getBeanList();

            // 组装加密参数
            String sign = getSign(timestamp, data);

            // 需要提交的参数
            Map<String, Object> params = getParams(timestamp, data, sign);

            // 发送 http请求





        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送的参数
     */
    private Map<String, Object> getParams(String timestamp, List<DrugsAddVO> data, String sign) {
        Map<String, Object> map = new HashMap<>();
        map.put("appkey", appkey);
        map.put("timestamp", timestamp);
        map.put("sign", sign);
        map.put("data", data);
        return map;
    }

    /**
     * 获取加密字符串
     */
    private String getSign(String timestamp, List<DrugsAddVO> data) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        Map<String, String> signMap = new HashMap<>();
        signMap.put("appkey", appkey);
        signMap.put("timestamp", timestamp);
        signMap.put("data", JSONObject.toJSON(data).toString());

        return SignHelper.generateSign(signMap, app_secret);
    }

    /**
     * 拼装业务数据
     */
    private List<DrugsAddVO> getBeanList() {
        List<DrugsAddVO> data = new ArrayList<>();
        DrugsAddVO vo1 = new DrugsAddVO();
        vo1.setDrugCode("code1");
        vo1.setName("name1");
        vo1.setPrice(1111L);
        vo1.setStock(100);
        vo1.setUnit("盒");
        data.add(vo1);

        DrugsAddVO vo2 = new DrugsAddVO();
        vo2.setDrugCode("code2");
        vo2.setName("name2");
        vo2.setPrice(2222L);
        vo2.setStock(200);
        vo2.setUnit("袋");
        data.add(vo2);
        return data;
    }
}
