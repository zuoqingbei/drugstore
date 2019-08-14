package com.hlsofttech.controller.erpapi;

import com.alibaba.fastjson.JSONObject;
import com.hlsofttech.platform.meituan.sign.SignHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: ERP参数解析验证工具类
 * @Date: 2019/7/30 12:24
 * @Author: suncy
 **/
@Slf4j
public class ERPParamUtil {


    /**
     * 检查参数并验证签名
     */
    public static boolean checkInfo(@RequestBody String param, String secret) {

        // 解析参数
        JSONObject jsonObject = (JSONObject) JSONObject.parse(param);
        Map<String, String> params = ERPParamUtil.bulidParamsMap(jsonObject);
        boolean flag = false;
        try {
            flag = SignHelper.signAndCheck(params, secret);
        } catch (Exception e) {
            log.error("加密错误！");
        }

        if (flag) {
            log.info("验签成功");
            return true;
        }
        log.error("验签失败");
        return false;
    }


    /**
     * 传递参数解析
     */
    public static Map<String, String> bulidParamsMap(JSONObject jsonObject) {

        Map<String, String> params = new HashMap<>();
        if (jsonObject == null) {
            return params;
        }

        params.put("appkey", jsonObject.getString("appkey"));
        params.put("timestamp", jsonObject.getString("timestamp"));
        params.put("sign", jsonObject.getString("sign"));

        params.put("data", jsonObject.getString("data"));
        return params;
    }
}
