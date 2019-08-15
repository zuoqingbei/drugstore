package com.hlsofttech.utils;

/**
 * @author zuoqb
 * @date 2017/11/2 下午2:32
 * @description 向Redis存储键值对时，Key必须要使用本类定义的标准前缀！！！
 * 从而减少Hash冲突，从而提高查询效率
 */
public class RedisPrefixUtil {
    /**
     * redis key-后购药平台
     */
    public static final String First = "DRUG_STORE:";
    /**
     * 接口访问权限的前缀
     */
    public static final String Access_Auth_Prefix = First + "AUTH:";

    /**
     * SessionID的前缀
     */
    public static final String SessionID_Prefix = First + "SESSION:";

    /**
     * 用户信息
     */
    public static final String User_Prefix = First + "USER:";
    /**
     * 用户购物车信息
     */
    public static final String User_Cart_Prefix = First + "CART:";
    /**
     * 用户搜索历史相关
     */
    public static final String User_Query_History_Prefix = First + "QUERY_HISTORY:";
}
