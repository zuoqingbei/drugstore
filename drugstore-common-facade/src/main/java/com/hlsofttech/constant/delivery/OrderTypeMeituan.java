package com.hlsofttech.constant.delivery;

/**
 * @Description: 订单类型枚举
 * @Date: 2019/7/24
 **/
public enum OrderTypeMeituan {
    NORMAL(0), // 及时单
    PREBOOK(1); // 预约单

    private final int code;

    private OrderTypeMeituan(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

}
