package com.hlsofttech.delivery.platform.meituan.util;


import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 回调参数构造器
 * @Date: 2019/7/30 12:24
 * @Author: suncy
 **/
public class ParamNotifyBuilder {

    public static Map<String, String> bulidParamsMap(JSONObject jsonObject) {

        Map<String, String> params = new HashMap<>();
        if (jsonObject == null) {
            return params;
        }

        params.put("appkey", jsonObject.getString("appkey"));
        params.put("timestamp", jsonObject.getString("timestamp"));
        params.put("sign", jsonObject.getString("sign"));

        params.put("delivery_id", jsonObject.getString("delivery_id"));
        params.put("mt_peisong_id", jsonObject.getString("mt_peisong_id"));
        params.put("order_id", jsonObject.getString("order_id"));
        params.put("status", jsonObject.getString("status"));
        params.put("courier_name", jsonObject.getString("courier_name"));
        params.put("courier_phone", jsonObject.getString("courier_phone"));
        params.put("cancel_reason_id", jsonObject.getString("cancel_reason_id"));
        params.put("cancel_reason", jsonObject.getString("cancel_reason"));

        return params;
    }
}
