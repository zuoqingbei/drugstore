package com.hlsofttech;

import com.hlsofttech.entity.product.dto.CartOfShopDto;
import com.hlsofttech.service.product.ShopCartService;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

public class CartShopTest extends BaseTest {
    @Resource
    ShopCartService shopCartService;

    /***
     * @Author: suntf
     * @Description:获取当前用户当前店铺内的购物车商品列表
     * @Date: 2019/8/8
     * @return: java.util.List
     **/
    @Test
    public void getShopCartFromRedisOnShop() {
        Long userId = 123456L;
        Integer shopId = 1001;
        List<CartOfShopDto> list = shopCartService.getShopCartFromRedisOnShop(userId, shopId);
        for (CartOfShopDto cartOfShopDto : list) {
            System.out.println(cartOfShopDto.toString());
        }
    }

    /***
     * @Author: suntf
     * @Description:获取当前用户购物车商品列表（按店铺距离排序）
     * @Date: 2019/8/8
     * @return: Map
     **/
    @Test
    public void getShopCartFromRedisOutShop() {
        Long userId = 123456L;
        double longitude = 120;
        double latitude = 101;
        Map<String, List<CartOfShopDto>> map = shopCartService.getShopCartFromRedisOutShop(userId, longitude, latitude);
        for (String key : map.keySet()) {
            System.out.println("店铺【" + key + "】商品↓↓↓↓↓↓↓↓↓↓");
            for (CartOfShopDto cartOfShopDto : map.get(key)) {
                System.out.println(cartOfShopDto.toString());
            }
        }
    }

    /***
     * @Author: suntf
     * @Description:店铺内添加购物车
     * @Date: 2019/8/8
     * @return: java.lang.Boolean
     **/
    @Test
    public void addShopCartOnShop() {
        Long userId = 123456L;
        Integer shopId = 1002;

        for (int i = 0; i < 6; i++) {
            CartOfShopDto cartOfShopDto = new CartOfShopDto();
            cartOfShopDto.setUserId(userId);
            cartOfShopDto.setShopId(shopId);
            cartOfShopDto.setSkuId(Long.valueOf("1000" + i));
            cartOfShopDto.setQuantity(2);
            cartOfShopDto.setSalePrice(100L);
            cartOfShopDto.setSkuName("测试商品" + i);
            Boolean ret = shopCartService.addShopCartOnShop(cartOfShopDto);
            System.out.println(ret);
        }

    }

    /***
     * @Author: suntf
     * @Description:删除购物车商品
     * @Date: 2019/8/8
     * @return: java.lang.Boolean
     **/
    @Test
    public void delShopCart() {
        Long userId = 123456L;
        Integer shopId = 1001;
        Long skuId = 10001L;
        Boolean ret = shopCartService.delShopCart(userId, shopId, skuId);
        System.out.println(ret);
    }

    /***
     * @Author: suntf
     * @Description:获取各店铺购物车商品数量
     * @Date: 2019/8/8
     * @return: Map
     **/
    @Test
    public void getAnyShopCartNum() {
        Long userId = 123456L;
        Map<Integer, Integer> map = shopCartService.getAnyShopCartNum(userId);
        System.out.println(map.toString());
    }
}
