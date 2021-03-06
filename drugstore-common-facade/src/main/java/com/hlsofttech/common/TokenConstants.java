package com.hlsofttech.common;

import java.util.concurrent.TimeUnit;

/**
 * @author Jack
 * @date 2018/1/22
 */
public class TokenConstants {

    /**
     * 请求平台
     */
    public static final String X_PLATFORM = "X-Platform";

    /**
     * 过期时间  3个月
     */
    public static final int TOKEN_EXPIRES_TIME = 60 * 60 * 24 * 30 * 3;
    /**
     * 过期时间单位
     */
    public static final TimeUnit TOKEN_EXPIRES_TIMEUNIT = TimeUnit.SECONDS;

    /**
     * 是否允许多平台登录
     */
    public static final boolean ENABLE_MULTI_CLIENT = true;

    /**
     * 登录用户的redis key  前缀
     */
    public static final String LOGIN_USER_KEY = "LOGIN_USER_KEY_";

    /**
     * 当前登录用户
     */
    public static final String CURRENT_LOGIN_TOKEN = "CURRENT_LOGIN_TOKEN:";

    public static final String JWT_KEY = "USER_ID";
}
