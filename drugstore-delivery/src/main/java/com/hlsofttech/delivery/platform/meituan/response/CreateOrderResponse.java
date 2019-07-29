package com.hlsofttech.delivery.platform.meituan.response;

import com.hlsofttech.delivery.platform.meituan.vo.OrderIdInfo;
import lombok.Data;

/**
 * @Description: 创建订单响应类
 * @Date: 2019/7/24 17:24
 * @Author: suncy
 **/
@Data
public class CreateOrderResponse extends AbstractResponse {

    private OrderIdInfo data;
}
