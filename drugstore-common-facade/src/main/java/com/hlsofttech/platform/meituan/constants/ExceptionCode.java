package com.hlsofttech.platform.meituan.constants;

/**
 * @Description: 订单异常代码
 * @Date: 2019/7/30 14:14
 * @Author: suncy
 **/
public enum ExceptionCode {

    EX_10001(10001, "顾客电话关机"),
    EX_10002(10002, "顾客电话已停机"),
    EX_10003(10003, "顾客电话无人接听"),
    EX_10004(10004, "顾客电话为空号"),
    EX_10005(10005, "顾客留错电话"),
    EX_10006(10006, "联系不上顾客其他原因"),
    EX_10101(10101, "顾客更改收货地址"),
    EX_10201(10201, "送货地址超区"),
    EX_10202(10202, "顾客拒收货品"),
    EX_10203(10203, "顾客要求延迟配送"),
    EX_10401(10401, "商家关店/未营业");

    private int code;
    private String description;

    ExceptionCode(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public static ExceptionCode findByCode(int code) {
        for (ExceptionCode type : ExceptionCode.values()) {
            if (type.getCode() == code) {
                return type;
            }
        }
        return null;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
