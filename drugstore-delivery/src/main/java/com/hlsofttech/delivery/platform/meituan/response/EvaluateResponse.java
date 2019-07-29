package com.hlsofttech.delivery.platform.meituan.response;

import com.hlsofttech.delivery.platform.meituan.vo.OrderIdInfo;
import lombok.Data;

/**
 * @Description: 评价骑手响应类
 * @Date: 2019/7/24 17:24
 * @Author: suncy
 **/
@Data
public class EvaluateResponse extends AbstractResponse {

    private OrderIdInfo data;
}
