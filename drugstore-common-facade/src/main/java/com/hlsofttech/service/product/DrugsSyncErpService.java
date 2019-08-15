package com.hlsofttech.service.product;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

/***
 * @Author: suntf
 * @Description:ERP同步服务
 * @Date: 2019/8/7
 **/
public interface DrugsSyncErpService {

    /***
     * @Author: suntf
     * @Description:订单数据同步至药店erp系统
     * @Date: 2019/8/7

     * @return: java.util.Map
     **/
    JSONObject syncErpOrder(Map<String, String> methodParam, String url);
}
