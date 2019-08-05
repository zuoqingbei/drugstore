package com.hlsofttech.controller.erpapi;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hlsofttech.entity.erp.UnifiedCreditCodeBean;
import com.hlsofttech.platform.meituan.sign.SignHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    public static Map<String, String> checkInfo(@RequestBody String param, String secret) {

        // 解析参数
        JSONObject jsonObject = (JSONObject) JSONObject.parse(param);
        Map<String, String> params = ERPParamUtil.bulidParamsMapData(jsonObject);
        boolean flag = false;
        try {
            // 验签
            flag = SignHelper.signAndCheck(params, secret);
        } catch (Exception e) {
            log.error("加密错误！");
        }

        // 验签成功，开始处理业务
        if (flag) {
            log.info("验签成功");
            return params;
        }
        log.error("验签失败");
        return null;
    }


    /**
     * 门店同步传递参数解析
     */
    public static Map<String, String> bulidParamsMapData(JSONObject jsonObject) {

        Map<String, String> params = new HashMap<>();
        if (jsonObject == null) {
            return params;
        }

        params.put("appkey", jsonObject.getString("appkey"));
        params.put("timestamp", jsonObject.getString("timestamp"));
        params.put("sign", jsonObject.getString("sign"));
        params.put("version", jsonObject.getString("version"));

        JSONArray array = jsonObject.getJSONArray("data");
        List<UnifiedCreditCodeBean> listBean = new ArrayList<>();
        for (int i = 0; i < array.size(); i++) {
            UnifiedCreditCodeBean beanA = new UnifiedCreditCodeBean();
            JSONObject jo = array.getJSONObject(i);
            String unifiedCreditCodeStr = jo.getString("unifiedCreditCode");
            beanA.setUnifiedCreditCode(unifiedCreditCodeStr);
            listBean.add(beanA);
        }
        params.put("data", JSON.toJSONString(listBean));
        return params;
    }
}
