package com.hlsofttech.common;

import java.text.SimpleDateFormat;

/**
 * @author zuoqb
 * @className Constant.java
 */
public interface Constant {
    public static final String TOKEN_FLAG = "token";// TOKEN标识
    public static String USER_INFO = "hailian_user";
    public static final String UN_DEL_FLAG = "0";// 未删除标识
    public static final String DEL_FLAG = "1";// 删除标识
    public static final SimpleDateFormat sdf = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss");
    public static final String Default_X_SIGN = "hlsofttech";// 默认SQL接口签名
    public static final String VERTICAL_DATA_FORMAT = "H";// 横向数据格式
    public static final int TIMEOUT = 60000;//dubbo请求超时时间
    public static final String VERSION = "2.0.0";//dubbo服务版本
    public static final String VERSION_ZT = "2.0.0-SNAPSHOT";//中台dubbo服务版本
}
