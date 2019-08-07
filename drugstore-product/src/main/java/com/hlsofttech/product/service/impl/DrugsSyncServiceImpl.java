package com.hlsofttech.product.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hlsofttech.common.Constant;
import com.hlsofttech.platform.meituan.util.HttpUtils;
import com.hlsofttech.product.service.DrugsSyncService;
import com.hlsofttech.product.vo.DrugsListVO;
import com.hlsofttech.rsp.ResultYaoZhi;
import com.hlsofttech.utils.MD5Util;
import com.hlsofttech.utils.UUIDUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/***
 * @Author: suntf
 * @Description:药品同步服务
 * @Date: 2019/8/7
 **/
@Slf4j
@Service(version = Constant.VERSION, group = "com.hlsofttech.product", timeout = Constant.TIMEOUT)
public class DrugsSyncServiceImpl implements DrugsSyncService {
    private final ExecutorService syncDrugsCacheExecutor = Executors.newFixedThreadPool(10);
    public static String listUrl = "http://openapi-dev.yaozh.com/instruct";
    public static String infoUrl = "http://openapi-dev.yaozh.com/instruct/read";
    //    public static String url = "openapi.yaozh.com";
    // 药智网提供的appkey（唯一码）
    public static String appKey = "99190806";
    // 药智网提供的appsecret（密文,不可泄漏）
    public static String appSecret = "b6ec3f178d98e40b114ca3580e5aabfe";
    // 同步每页的条数
    public static Integer pageSize = 5;

    @Override
    public void syncDrugsStart() {
        long startTime = System.currentTimeMillis();
        log.info("开始同步药品信息: " + startTime);

        int nowpage = 1;
        while (nowpage < 1000) {
            final int finalNowpage = nowpage;
            Future<Integer> future = syncDrugsCacheExecutor.submit(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    try {
                        Thread.sleep(100);
                        JSONObject drugsListObj = syncDrugList(finalNowpage);
                        if (drugsListObj == null) {
                            return null;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return finalNowpage;
                }

            });
            nowpage++;
        }
        syncDrugsCacheExecutor.shutdown();
        // 这个方法是统计所有线程全部执行完毕的时间
        while (true) {
            if (syncDrugsCacheExecutor.isTerminated()) {
                long end = System.currentTimeMillis();
                log.info("同步药品信息用时: " + (end - startTime) + "ms");
                break;
            }
        }
    }

    /***
     * @Author: suntf
     * @Description:同步药品列表
     * @Date: 2019/8/7
     * @param nowpage:
     * @return: com.alibaba.fastjson.JSONObject
     **/
    private JSONObject syncDrugList(int nowpage) {
        Map<String, Object> methodParam = new HashMap<>();
        methodParam.put("page", nowpage);
        methodParam.put("pageSize", pageSize);
        JSONObject drugsListObj = signAndGetHttpData(methodParam, listUrl);
        if (drugsListObj != null) {
            String res = drugsListObj.getString("res");
            Integer page = drugsListObj.getInteger("page");
            Integer count = drugsListObj.getInteger("count");
            if (pageSize * pageSize > count) {
                return null;
            }
            // 取出列表信息，遍历列表，查询详情
            JSONArray jsonArray = JSONArray.parseArray(res);
            if (!jsonArray.isEmpty()) {
                List<DrugsListVO> drugsListVOList = jsonArray.toJavaList(DrugsListVO.class);
                for (DrugsListVO drugsListVO : drugsListVOList) {
                    // TODO 此处更新药品信息到数据库
                    System.out.println(syncDrugInfo(drugsListVO.getId()));
                }
            }
        }

        return null;
    }

    /***
     * @Author: suntf
     * @Description:同步药品信息
     * @Date: 2019/8/7
     * @param id:
     * @return: com.alibaba.fastjson.JSONObject
     **/
    private JSONObject syncDrugInfo(int id) {
        Map<String, Object> methodParam = new HashMap<>();
        methodParam.put("id", id);
        return signAndGetHttpData(methodParam, infoUrl);
    }


    private JSONObject signAndGetHttpData(Map<String, Object> methodParam, String url) {
        // 组装参数
        Map<String, String> params = new LinkedHashMap<>();
        if (methodParam.size() > 0) {
            for (String key : methodParam.keySet()) {
                params.put(key, methodParam.get(key).toString());
            }
        }
        int t = (int) (System.currentTimeMillis() / 1000);

        methodParam.put("appkey", appKey);
        // 当前时间戳
        methodParam.put("t", Long.toString(t));
        // r : 随机字符串:MD5(t+appkey+15位随机字符串)；
        String str = UUIDUtils.getUuid().substring(0, 15);
        String r = MD5Util.MD5(t + appKey + str);
        methodParam.put("r", r);
        try {
            // 业务参数
            String businessParamStr = "";
            if (methodParam.size() > 0) {
                Map<String, Object> businessParam = new TreeMap<>();
                for (String key : methodParam.keySet()) {
                    businessParam.put(key, methodParam.get(key));
                }
                StringBuilder sb = new StringBuilder("&");
                for (String key : businessParam.keySet()) {
                    sb.append(key).append("=").append(businessParam.get(key)).append("&");
                }
                businessParamStr = sb.toString().substring(0, sb.length() - 1);
            }

            params.put("appkey", appKey);
            params.put("r", r);
            // 当前时间戳
            params.put("t", Long.toString(t));
            String T = businessParamStr;
            String sign = MD5Util.MD5(MD5Util.MD5(appSecret + T + appSecret)).toUpperCase();
            params.put("sign", sign);
            Thread.sleep(500);
            String ret = HttpUtils.doGet(buildUrl(url, params));
            if (StringUtils.isNotBlank(ret)) {
                ResultYaoZhi resultYaoZhi = JSONObject.parseObject(ret, ResultYaoZhi.class);
                if (resultYaoZhi.getCode() == 200) {
                    return JSONObject.parseObject(resultYaoZhi.getData());
                } else {
                    log.error(resultYaoZhi.getMsg());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String buildUrl(String host, Map<String, String> querys) throws UnsupportedEncodingException {
        StringBuilder sbUrl = new StringBuilder();
        sbUrl.append(host);
        if (null != querys) {
            StringBuilder sbQuery = new StringBuilder();
            for (Map.Entry<String, String> query : querys.entrySet()) {
                if (0 < sbQuery.length()) {
                    sbQuery.append("&");
                }
                if (StringUtils.isBlank(query.getKey()) && !StringUtils.isBlank(query.getValue())) {
                    sbQuery.append(query.getValue());
                }
                if (!StringUtils.isBlank(query.getKey())) {
                    sbQuery.append(query.getKey());
                    if (!StringUtils.isBlank(query.getValue())) {
                        sbQuery.append("=");
                        sbQuery.append(URLEncoder.encode(query.getValue(), "utf-8"));
                    }
                }
            }
            if (0 < sbQuery.length()) {
                sbUrl.append("?").append(sbQuery);
            }
        }
        return sbUrl.toString();
    }
}
