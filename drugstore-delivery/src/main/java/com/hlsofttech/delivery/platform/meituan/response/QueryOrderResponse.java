package com.hlsofttech.delivery.platform.meituan.response;

import com.hlsofttech.delivery.platform.meituan.vo.OrderStatusInfo;
import lombok.Data;

/**
 * @Description: 查询订单状态响应类
 * @Date: 2019/7/24 17:24
 * @Author: suncy
 **/
@Data
public class QueryOrderResponse extends AbstractResponse {

    private OrderStatusInfo data;
}
