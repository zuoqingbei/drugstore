package com.hlsofttech.exception;

import java.io.Serializable;

import static com.hlsofttech.utils.ExpPrefixUtil.*;

/**
 * @Author zuoqb
 * @Date 2017/10/27 下午10:37
 * 全局的异常状态码 和 异常描述
 * <p>
 * PS:异常码一共由5位组成，前两位为固定前缀，请参考{@link com.hlsofttech.utils.ExpPrefixUtil}
 */
public enum ExpCodeEnum implements Serializable {

    /**
     * 通用异常
     */
    UNKNOW_ERROR(ComExpPrefix + "000", "未知异常"),
    ERROR_404(ComExpPrefix + "001", "没有该接口"),
    PARAM_NULL(ComExpPrefix + "002", "参数为空"),
    NO_REPEAT(ComExpPrefix + "003", "请勿重复提交"),
    SESSION_NULL(ComExpPrefix + "004", "请求头中SessionId不存在"),
    HTTP_REQ_METHOD_ERROR(ComExpPrefix + "005", "HTTP请求方法不正确"),
    JSONERROR(ComExpPrefix + "006", "JSON解析异常"),
    LIST_DTO_TO_VO(ComExpPrefix + "007", "List-DTO转List-VO异常"),

    /**
     * User模块异常
     */
    USERNAME_NULL(UserExpPrefix + "000", "用户名为空"),
    PASSWD_NULL(UserExpPrefix + "001", "密码为空"),
    AUTH_NULL(UserExpPrefix + "002", "手机、电子邮件、用户名 至少填一个"),
    LOGIN_FAIL(UserExpPrefix + "003", "登录失败"),
    UNLOGIN(UserExpPrefix + "004", "尚未登录"),
    NO_PERMISSION(UserExpPrefix + "005", "没有权限"),
    NO_USER(UserExpPrefix + "006", "用户不存在或未开通店铺权限"),
    NAME_OR_PWD_ERROR(UserExpPrefix + "007", "用户名或密码不正确"),
    SERVER_ERROR(ComExpPrefix + "008", "服务异常"),

    /**
     * ERP模块异常
     */
    SIGN_FAIL(ErpExpPrefix + "000", "签名不正确"),
    SECRET_FAIL(ErpExpPrefix + "001", "当前用户不存在"),
    SYS_ERROR(ErpExpPrefix + "008", "服务异常");

    private String code;
    private String message;

    private ExpCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    ExpCodeEnum() {
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
