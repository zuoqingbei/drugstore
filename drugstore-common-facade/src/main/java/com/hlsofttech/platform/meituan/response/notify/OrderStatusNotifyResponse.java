package com.hlsofttech.delivery.platform.meituan.response.notify;

import com.hlsofttech.delivery.platform.meituan.constants.OrderStatus;
import lombok.Data;

/***
 * @Description: 订单状态回调信息
 * @Date: 2019/7/30 14:14
 * @Author: suncy
 **/
@Data
public class OrderStatusNotifyResponse extends AbstractNotifyResponse {

    /**
     * 配送活动标识
     */
    private Long delivery_id;
    /**
     * 美团配送内部订单id，最长不超过32个字符
     */
    private String mt_peisong_id;
    /**
     * 外部订单号，最长不超过32个字符
     */
    private String order_id;
    /**
     * 状态代码，可选值为
     * 0：待调度
     * <p>
     * 20：已接单
     * <p>
     * 30：已取货
     * <p>
     * 50：已送达
     * <p>
     * 99：已取消
     * <p>
     * 回调接口的订单状态改变可能会跳过中间状态，比如从待调度状态直接变为已取货状态。
     * <p>
     * 订单状态不会回流。即订单不会从已取货状态回到待调度状态。
     * <p>
     * 订单状态为“已接单”和“已取货”时，如果当前骑手不能完成配送，会出现改派操作，例如：将订单从骑手A改派给骑手B，由骑手B完成后续配送，因此会出现同一订单多次返回同一状态不同骑手信息的情况”
     */
    private OrderStatus status;
    /**
     * 配送员姓名（已接单，已取货状态的订单，配送员信息可能改变）
     */
    private String courier_name;
    /**
     * 配送员电话（已接单，已取货状态的订单，配送员信息可能改变
     */
    private String courier_phone;
    /**
     * 取消原因id，详情参考 美团配送开放平台接口文档--门户页面-4.3，订单取消原因列表
     */
    private Integer cancel_reason_id;
    /**
     * 取消原因详情，最长不超过256个字符
     */
    private String cancel_reason;
}
