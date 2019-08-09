package com.hlsofttech.delivery.platform.meituan.response.notify;

import lombok.Data;

/***
 * @Description: 门店状态回调信息
 * @Date: 2019/7/30 14:14
 * @Author: suncy
 **/
@Data
public class ShopStatusNotifyResponse extends AbstractNotifyResponse {

    /**
     * 门店名称
     */
    private String shop_name;
    /**
     * 取货门店id，即合作方向美团提供的门店id
     */
    private String shop_id;
    /**
     * 10-审核驳回
     * 20-审核通过
     * 30-创建成功
     * 40-上线可发单
     */
    private Integer status;
    /**
     * 驳回原因
     */
    private String reject_message;
}
