package com.hlsofttech.delivery.platform.meituan.response;

import com.hlsofttech.delivery.platform.meituan.vo.ShopCreateResData;
import lombok.Data;

/**
 * @Description: 创建门店响应类
 * @Date: 2019/7/24 17:24
 * @Author: suncy
 **/
@Data
public class CreateShopResponse extends AbstractResponse {

    private ShopCreateResData data;
}
