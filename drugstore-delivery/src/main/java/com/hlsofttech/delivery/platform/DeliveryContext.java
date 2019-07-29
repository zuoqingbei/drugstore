package com.hlsofttech.delivery.platform;

import com.hlsofttech.common.ResultDO;
import com.hlsofttech.entity.delivery.dto.CreateOrderDTO;
import com.hlsofttech.entity.delivery.dto.ShopInfoDTO;
import lombok.Data;

/***
 * @Description: 策略类交互
 * @Date: 2019/7/25 17:00
 * @Author: suncy
 **/
@Data
public class DeliveryContext {

    private Delivery delivery;

    public DeliveryContext(Delivery delivery) {
        super();
        this.delivery = delivery;
    }

    /***
     * @Description: 创建门店
     * @Date: 2019/7/25 17:30
     * @param shopInfoDTO:
     * @return: com.hlsofttech.common.ResultDO
     * @Author: suncy
     **/
    public ResultDO createShop(ShopInfoDTO shopInfoDTO) {
        return delivery.createShop(shopInfoDTO);
    }

    /***
     * @Description: 创建订单
     * @Date: 2019/7/29 9:30
     * @param createOrderDTO:
     * @return: com.hlsofttech.common.ResultDO
     * @Author: suncy
     **/
    public ResultDO createOrder(CreateOrderDTO createOrderDTO) {
        return delivery.createOrder(createOrderDTO);
    }

}
