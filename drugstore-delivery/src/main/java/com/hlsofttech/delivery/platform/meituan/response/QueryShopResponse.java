package com.hlsofttech.delivery.platform.meituan.response;

import com.hlsofttech.delivery.platform.meituan.vo.ShopInfo;
import lombok.Data;

/**
 * @Description: 查询门店信息响应
 * @Date: 2019/7/25 10:01
 * @Author: suncy
 **/
@Data
public class QueryShopResponse extends AbstractResponse {

    private ShopInfo data;
}
