package com.hlsofttech.utils;

/**
 * @author zuoqb
 * @date 2017/11/2 下午2:32
 * @description 向Redis存储键值对时，Key必须要使用本类定义的标准前缀！！！
 * 从而减少Hash冲突，从而提高查询效率
 */
public class RedisPrefixUtil {

    /**
     * 接口访问权限的前缀
     */
    public static final String Access_Auth_Prefix = "AUTH:";

    /**
     * SessionID的前缀
     */
    public static final String SessionID_Prefix = "SESSION:";

    /**
     * 用户信息
     */
    public static final String User_Prefix = "USER:";
    /**
     * 用户购物车信息
     */
    public static final String User_Cart_Prefix = "CART:";
}
