package com.hlsofttech.delivery.platform.meituan.constants;

/**
 * @Description: 门店审核状态
 * @Date: 2019/7/25 9:54
 * @Author: suncy
 **/
public enum ShopAuditStatus {
    AUDIT_REJECT(10, "审核驳回"),
    AUDIT_PASS(20, "审核通过"),
    AUDIT_CREATE_SUCESS(10, "创建成功"),
    ON_LINE(40, "上线可发单");

    private int code;
    private String msg;

    ShopAuditStatus(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static ShopAuditStatus findByCode(int code) {
        for (ShopAuditStatus type : ShopAuditStatus.values()) {
            if (type.getCode() == code) {
                return type;
            }
        }
        return null;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
