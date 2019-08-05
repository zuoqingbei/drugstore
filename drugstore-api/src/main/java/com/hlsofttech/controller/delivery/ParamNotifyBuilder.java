package com.hlsofttech.controller.delivery;


import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 回调参数构造器
 * @Date: 2019/7/30 12:24
 * @Author: suncy
 **/
public class ParamNotifyBuilder {

    /**
     * 订单状态回调
     */
    public static Map<String, String> bulidParamsMapOrderStatus(JSONObject jsonObject) {

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

    /**
     * 异常订单回调
     */
    public static Map<String, String> bulidParamsMapOrderError(JSONObject jsonObject) {

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
        params.put("exception_id", jsonObject.getString("exception_id"));
        params.put("exception_code", jsonObject.getString("exception_code"));
        params.put("exception_descr", jsonObject.getString("exception_descr"));
        params.put("exception_time", jsonObject.getString("exception_time"));
        params.put("courier_name", jsonObject.getString("courier_name"));
        params.put("courier_phone", jsonObject.getString("courier_phone"));

        return params;
    }

    /**
     * 异常订单回调
     */
    public static Map<String, String> bulidParamsMapshopStatus(JSONObject jsonObject) {

        Map<String, String> params = new HashMap<>();
        if (jsonObject == null) {
            return params;
        }

        params.put("appkey", jsonObject.getString("appkey"));
        params.put("timestamp", jsonObject.getString("timestamp"));
        params.put("sign", jsonObject.getString("sign"));

        params.put("shop_name", jsonObject.getString("shop_name"));
        params.put("shop_id", jsonObject.getString("shop_id"));
        params.put("status", jsonObject.getString("status"));
        params.put("reject_message", jsonObject.getString("reject_message"));

        return params;
    }
}
