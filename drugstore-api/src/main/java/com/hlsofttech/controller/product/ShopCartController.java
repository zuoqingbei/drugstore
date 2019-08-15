package com.hlsofttech.controller.product;

import com.alibaba.dubbo.config.annotation.Reference;
import com.hlsofttech.annotation.AuthPower;
import com.hlsofttech.common.Constant;
import com.hlsofttech.entity.product.dto.CartOfShopDto;
import com.hlsofttech.rsp.Result;
import com.hlsofttech.service.product.ShopCartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/***
 * @Author: suntf
 * @Description:购物控制类
 * @Date: 2019/8/9
 **/
@RestController
@Api(tags = "购物车", value = "购物车", description = "购物车 @author suntf")
public class ShopCartController {
    @Reference(version = Constant.VERSION, timeout = Constant.TIMEOUT)
    ShopCartService shopCartService;

    /***
     * @Author: suntf
     * @Description:获取当前用户当前店铺内的购物车商品列表
     * @Date: 2019/8/9
     * @return: java.util.List
     **/
    @AuthPower(avoidVersion = false, avoidPower = true, avoidSign = true, avoidLogin = false, avoidPlatform = true)
    @ApiOperation(value = "店铺内购物车", notes = "店铺内购物车", httpMethod = "GET")
    @GetMapping("/api/drugsCart/shop/{shopId}")
    public Result getShopCartFromRedisOnShop(@PathVariable Integer shopId) {
        Long userId = 123456L;
        List<CartOfShopDto> list = shopCartService.getShopCartFromRedisOnShop(userId, shopId);
        return Result.newSuccessResult(list);
    }

    /***
     * @Author: suntf
     * @Description:获取当前用户购物车商品列表（按店铺距离排序）
     * @Date: 2019/8/9
     * @return: Map
     **/
    @AuthPower(avoidVersion = false, avoidPower = true, avoidSign = true, avoidLogin = false, avoidPlatform = true)
    @ApiOperation(value = "首页购物车", notes = "首页购物车", httpMethod = "GET")
    @GetMapping("/api/drugsCart/all")
    public Result getShopCartFromRedisOutShop(String longitude, String latitude) {
        Long userId = 123456L;
        // 经度
        Double longitudeD;
        // 纬度
        Double latitudeD;
        // 劲舞欸度初始化
        if (StringUtils.isBlank(longitude)) {
            longitudeD = null;
        } else {
            longitudeD = Double.valueOf(longitude);
        }
        if (StringUtils.isBlank(latitude)) {
            latitudeD = null;
        } else {
            latitudeD = Double.valueOf(latitude);
        }
        Map<String, List<CartOfShopDto>> map = shopCartService.getShopCartFromRedisOutShop(userId, longitudeD, latitudeD);
        return Result.newSuccessResult(map);
    }

    /***
     * @Author: suntf
     * @Description:店铺内添加购物车
     * @Date: 2019/8/8
     * @return: java.lang.Boolean
     **/
    @AuthPower(avoidVersion = false, avoidPower = true, avoidSign = true, avoidLogin = false, avoidPlatform = true)
    @ApiOperation(value = "加入购物车", notes = "加入购物车", httpMethod = "POST")
    @PostMapping("/api/drugsCart/add")
    public Result addShopCartOnShop(@RequestBody CartOfShopDto cartOfShopDto) {
        Long userId = 123456L;
        cartOfShopDto.setUserId(userId);
        Boolean ret = shopCartService.addShopCartOnShop(cartOfShopDto);
        return Result.newSuccessResult(ret);
    }

    /***
     * @Author: suntf
     * @Description:删除购物车商品
     * @Date: 2019/8/8
     * @return: java.lang.Boolean
     **/
    @AuthPower(avoidVersion = false, avoidPower = true, avoidSign = true, avoidLogin = false, avoidPlatform = true)
    @ApiOperation(value = "删除购物车", notes = "删除购物车", httpMethod = "GET")
    @GetMapping("/api/drugsCart/del")
    public Result delShopCart(@RequestParam("shopId") Integer shopId, @RequestParam("skuId") Long skuId) {
        Long userId = 123456L;

        Boolean ret = shopCartService.delShopCart(userId, shopId, skuId);
        return Result.newSuccessResult(ret);
    }

    /***
     * @Author: suntf
     * @Description:获取各店铺购物车商品数量
     * @Date: 2019/8/8
     * @return: Map
     **/
    @AuthPower(avoidVersion = false, avoidPower = true, avoidSign = true, avoidLogin = false, avoidPlatform = true)
    @ApiOperation(value = "获取购物车商品数量", notes = "获取购物车商品数量", httpMethod = "GET")
    @GetMapping("/api/drugsCart/allNum")
    public Result getAnyShopCartNum() {
        Long userId = 123456L;
        Map<Integer, Integer> map = shopCartService.getAnyShopCartNum(userId);
        return Result.newSuccessResult(map);
    }
}
