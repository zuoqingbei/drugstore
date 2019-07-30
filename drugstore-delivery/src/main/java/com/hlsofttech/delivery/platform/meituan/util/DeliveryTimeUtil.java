package com.hlsofttech.delivery.platform.meituan.util;

/***
 * @Description: 计算预计送达时间工具类
 * @Date: 2019/7/29 9:45
 * @Author: sunc y
 **/
public class DeliveryTimeUtil {


    /***
     * 配送服务代码，详情见合同
     * 飞速达:4002
     * 快速达:4011
     * 及时达:4012
     * 集中送:4013
     **/

    /***
     * 期望送达时间，时区为GMT+8，当前距离Epoch（1970年1月1日) 以秒计算的时间，即unix-timestamp
     * 即时单：以发单时间 + 服务包时效作为期望送达时间（当天送服务包需客户指定期望送达时间）
     * 预约单：以客户传参数据为准（预约时间必须大于当前下单时间+服务包时效+3分钟）
     * 服务包	编码	即时单期望送达时间规则
     * 飞速达	4002	发单时间+45mins
     * 快速达	4011	发单时间+1h
     * 及时达	4012	发单时间+2h
     * 集中送	4013	发单时间+2h
     **/

    /***
     * @Description: 计算送达时间
     * @Date: 2019/7/29 9:48
     * @param deliveryServiceCode:
     * @param createOrderTime:
     * @return: java.lang.Long
     * @Author: suncy
     **/
    public static Long calcDeliveryTime(Integer deliveryServiceCode, Long createOrderTime) {
        if (deliveryServiceCode == null || createOrderTime == null) {
            return createOrderTime;
        }
        if (deliveryServiceCode == 4002) {
            createOrderTime = createOrderTime + ((3600L / 4) * 3);
        } else if (deliveryServiceCode == 4011) {
            createOrderTime = createOrderTime + 3600L;
        } else if (deliveryServiceCode == 4012 || deliveryServiceCode == 4013) {
            createOrderTime = createOrderTime + (3600L * 2);
        }

        return createOrderTime;
    }

    public static void main(String[] args) {
        System.out.println(calcDeliveryTime(4011, Long.valueOf(DateUtil.unixTime())));
    }
}
