package com.hlsofttech.platform.meituan.util;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.UUID;

/***
 * @Description: 生成app_key和app_screct
 * @Date: 2019/8/2 17:17
 * @Author: suncy
 **/
public class GuidUtil {
    public String app_key;

    /**
     * @return
     * @description:随机获取key值
     */
    public String guid() {
        UUID uuid = UUID.randomUUID();
        String key = uuid.toString();
        return key;
    }

    /**
     * 这是其中一个url的参数，是GUID的，全球唯一标志符
     *
     * @return
     */
    public String app_key() {
        GuidUtil g = new GuidUtil();
        String guid = g.guid();
        app_key = guid;
        return app_key;
    }

    /**
     * 根据md5加密
     *
     * @return
     */
    public String app_screct() {
        String mw = "key" + app_key;
        return DigestUtils.md5Hex(mw).toUpperCase();
    }

    public static void main(String[] args) {
        GuidUtil gd = new GuidUtil();
        String app_key = gd.app_key();
        System.out.println("app_key: " + app_key);
        String app_screct = gd.app_screct();
        System.out.println("app_screct: " + app_screct);
    }




}
