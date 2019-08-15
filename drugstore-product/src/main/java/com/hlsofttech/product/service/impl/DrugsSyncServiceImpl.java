package com.hlsofttech.product.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hlsofttech.common.Constant;
import com.hlsofttech.platform.meituan.util.HttpUtils;
import com.hlsofttech.product.vo.DrugsInfoVO;
import com.hlsofttech.product.vo.DrugsListVO;
import com.hlsofttech.rsp.ResultYaoZhi;
import com.hlsofttech.service.product.DrugsSyncService;
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
                    if (StringUtils.isBlank(drugsListVO.getName())) {
                        // 药品名字空不予添加
                        continue;
                    }
                    DrugsInfoVO drugsInfoVO = new DrugsInfoVO();
                    drugsInfoVO.setName(drugsListVO.getName());
                    drugsInfoVO.setSource(drugsListVO.getSource());

                    // 1.有详情
                    if (drugsListVO.getDowncheck() == 0) {
                        // 查询详情
                        JSONObject drugsObj = syncDrugInfo(drugsListVO.getId());
                        if (drugsObj != null) {
                            // 取头信息
                            if (drugsObj.containsKey("Title")) {
                                // 保存药品 国药码！！！
//                                System.out.println("id=" + drugsListVO.getId() + ",Title=" + drugsObj.getJSONObject("Title"));
                            }
                            // 药品图片

                            // 药品详情------0代表该条数据返回未拆分的正文内容，1代表该条数据有详细拆分的正文内容
                            if (drugsListVO.getXiangqing() == 0) {
                                String content = drugsObj.getJSONObject("Content").getString("content");
                                if (StringUtils.isBlank(content)) {
                                    log.warn(drugsListVO.getId() + "详情为空");
                                    continue;
                                }
                                try {
                                    // 截取药品适应症和用法用量
                                    String[] temp1 = content.replaceAll(" ", "").
                                            replace("【适应证】", "【适应症】").
                                            split("【适应症】");
                                    String mainFunction = temp1[1].substring(0, temp1[1].indexOf("【")).
                                            replaceAll("<br>", "").
                                            replaceAll("<p>", "").
                                            replaceAll("<b>", "").
                                            replaceAll("</b>", "");

                                    String[] temp2 = content.replaceAll(" ", "").
                                            replace("【用法与用量】", "【用法用量】").
                                            replace("【用法和用量】", "【用法用量】").
                                            split("【用法用量】");
                                    String usageDosage = temp2[1].substring(0, temp2[1].indexOf("【")).
                                            replaceAll("<br>", "").
                                            replaceAll("<p>", "").
                                            replaceAll("<b>", "").
                                            replaceAll("</b>", "");
                                    drugsInfoVO.setContent(content);
                                    drugsInfoVO.setMainFunction(mainFunction);
                                    drugsInfoVO.setUsageDosage(usageDosage);
                                } catch (ArrayIndexOutOfBoundsException e) {
                                    e.printStackTrace();
                                    log.error(content);
                                }

                            } else if (drugsListVO.getXiangqing() == 1) {
                                // 全面解析
                                // 1.基本信息结果集
                                if (drugsObj.containsKey("Jbxx")) {
                                    String Jbxx = drugsObj.getString("Jbxx");
                                    drugsInfoVO = JSONObject.parseObject(Jbxx, DrugsInfoVO.class);
                                }

                                // 2.功能主治结果集
                                if (drugsObj.containsKey("Gnzz")) {
                                    String Gnzz = drugsObj.getString("Gnzz");
                                    drugsInfoVO = JSONObject.parseObject(Gnzz, DrugsInfoVO.class);
                                }

                                // 3.用法用量结果集
                                if (drugsObj.containsKey("Yfyl")) {
                                    String Yfyl = drugsObj.getString("Yfyl");
                                    drugsInfoVO = JSONObject.parseObject(Yfyl, DrugsInfoVO.class);
                                }

                                // 4.不良反应结果集
                                if (drugsObj.containsKey("Blfy")) {
                                    String Blfy = drugsObj.getString("Blfy");
                                    drugsInfoVO = JSONObject.parseObject(Blfy, DrugsInfoVO.class);
                                }
                                // 5.注意事项结果集
                                if (drugsObj.containsKey("Zyss")) {
                                    String Zyss = drugsObj.getString("Zyss");
                                    drugsInfoVO = JSONObject.parseObject(Zyss, DrugsInfoVO.class);
                                }
                                // 6.特殊人群用药结果集
                                if (drugsObj.containsKey("Ts")) {
                                    String Ts = drugsObj.getString("Ts");
                                    drugsInfoVO = JSONObject.parseObject(Ts, DrugsInfoVO.class);
                                }
                                // 7.药理作用结果集
                                if (drugsObj.containsKey("Ylzy")) {
                                    String Ylzy = drugsObj.getString("Ylzy");
                                    drugsInfoVO = JSONObject.parseObject(Ylzy, DrugsInfoVO.class);
                                }
                                // 8.说明书修订日期结果集
                                if (drugsObj.containsKey("Date")) {
                                    String Date = drugsObj.getString("Date");
                                    drugsInfoVO = JSONObject.parseObject(Date, DrugsInfoVO.class);
                                }
                            }
                        }
                    } else {
                        // 无详情，保存附件下载链接
                        drugsInfoVO.setDown(drugsListVO.getDown());
                    }
                    log.info("同步药品信息：" + drugsInfoVO.toString());
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
