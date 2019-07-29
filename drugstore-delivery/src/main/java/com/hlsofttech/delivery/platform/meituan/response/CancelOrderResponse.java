package com.hlsofttech.delivery.platform.meituan.response;

import com.hlsofttech.delivery.platform.meituan.vo.OrderIdInfo;
import lombok.Data;

/**
 * @Description: 取消订单响应类
 * @Date: 2019/7/24 17:23
 * @Author: suncy
 **/
@Data
public class CancelOrderResponse extends AbstractResponse {

    private OrderIdInfo data;
}
