package com.hlsofttech.service.product;

import com.hlsofttech.entity.product.dto.CartOfShopDto;

import java.util.List;
import java.util.Map;

/***
 * @Author: suntf
 * @Description:店铺购物车服务
 * @Date: 2019/8/8
 **/
public interface ShopCartService {

    /***
     * @Author: suntf
     * @Description:获取当前用户当前店铺内的购物车商品列表
     * @Date: 2019/8/8
     * @param userId: 用户id
     * @param shopId: 店铺id
     * @return: java.util.List
     **/
    List<CartOfShopDto> getShopCartFromRedisOnShop(Long userId, Integer shopId);

    /***
     * @Author: suntf
     * @Description:获取当前用户购物车商品列表（按店铺距离排序）
     * @Date: 2019/8/8
     * @param userId: 用户id
     * @param longitude: 店铺经度
     * @param latitude: 店铺纬度
     * @return: Map
     **/
    Map<String, List<CartOfShopDto>> getShopCartFromRedisOutShop(Long userId, double longitude, double latitude);

    /***
     * @Author: suntf
     * @Description:店铺内添加购物车
     * @Date: 2019/8/8
     * @param cartOfShopDto:cartOfShopDto
     * @return: java.lang.Boolean
     **/
    Boolean addShopCartOnShop(CartOfShopDto cartOfShopDto);

    /***
     * @Author: suntf
     * @Description:删除购物车商品
     * @Date: 2019/8/8
     * @param userId:用户id
     * @param shopId:店铺id
     * @param skuId:skuId
     * @return: java.lang.Boolean
     **/
    Boolean delShopCart(Long userId, Integer shopId, Long skuId);

    /***
     * @Author: suntf
     * @Description:获取各店铺购物车商品数量
     * @Date: 2019/8/8
     * @param userId:用户id
     * @return: List
     **/
    List<Map<Integer, Integer>> getAnyShopCartNum(Long userId);
}
