package com.hlsofttech.delivery.platform.meituan.response;

import lombok.Data;

/**
 * @Description: 抽象响应父类
 * @Date: 2019/7/24 17:23
 * @Author: suncy
 **/
@Data
public abstract class AbstractResponse {
    protected String code;
    protected String message;

}
