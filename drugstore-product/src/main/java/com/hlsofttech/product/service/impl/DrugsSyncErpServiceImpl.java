package com.hlsofttech.product.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSONObject;
import com.hlsofttech.common.Constant;
import com.hlsofttech.platform.meituan.util.HttpUtils;
import com.hlsofttech.rsp.ResultErp;
import com.hlsofttech.service.product.DrugsSyncErpService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import java.util.Map;

/***
 * @Author: suntf
 * @Description:ERP同步服务
 * @Date: 2019/8/7
 **/
@Slf4j
@Service(version = Constant.VERSION, group = "com.hlsofttech.product", timeout = Constant.TIMEOUT)
public class DrugsSyncErpServiceImpl implements DrugsSyncErpService {
    private static final String erpurl = "http://openapi-dev.yaozh.com/instruct";
    private static final String version = "v1";
    /**
     * 网店编码
     */
    private static final String olshopid = "101212";
    /**
     * 企业编码
     */
    private static final String groupid = "1012";
    /**
     * 平台编码
     */
    private static final String eccode = "9999";


    @Override
    public JSONObject syncErpOrder(Map<String, String> methodParam, String url) {
        // 企业编码
        methodParam.put("groupId", groupid);
        // 网店编码
        methodParam.put("olshopid", olshopid);
        // 平台编码
        methodParam.put("eccode", eccode);

        try {
            String ret = HttpUtils.doPost(url, methodParam);
            if (StringUtils.isNotBlank(ret)) {
                ResultErp resultErp = JSONObject.parseObject(ret, ResultErp.class);
                if (resultErp.getCode() == 200) {
                    return JSONObject.parseObject(resultErp.getData());
                } else {
                    log.error(resultErp.getMsg());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
