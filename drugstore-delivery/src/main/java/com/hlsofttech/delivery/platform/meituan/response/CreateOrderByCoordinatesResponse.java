package com.hlsofttech.delivery.platform.meituan.response;

import com.hlsofttech.delivery.platform.meituan.vo.OrderIdInfo;
import lombok.Data;

/**
 * @Description: 订单创建(送货分拣方式)响应类
 * @Date: 2019/7/24 17:23
 * @Author: suncy
 **/
@Data
public class CreateOrderByCoordinatesResponse extends AbstractResponse {

    private OrderIdInfo data;
}
