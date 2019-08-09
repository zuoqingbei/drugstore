package com.hlsofttech.product.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSONArray;
import com.hlsofttech.common.Constant;
import com.hlsofttech.entity.product.dto.CartOfShopDto;
import com.hlsofttech.redis.JedisUtil;
import com.hlsofttech.service.product.ShopCartService;
import com.hlsofttech.utils.DistanceUtils;
import com.hlsofttech.utils.RedisPrefixUtil;
import org.apache.commons.lang.StringUtils;

import java.util.*;

/***
 * @Author: suntf
 * @Description:店铺购物车服务
 * @Date: 2019/8/8
 **/
@Service(version = Constant.VERSION, group = "com.hlsofttech.product", timeout = Constant.TIMEOUT)
public class ShopCartServiceImpl implements ShopCartService {

    @Override
    public List<CartOfShopDto> getShopCartFromRedisOnShop(Long userId, Integer shopId) {

        // 从缓存中找到当前登录人的购物车信息
        String redisKey = RedisPrefixUtil.User_Cart_Prefix + userId;
        String hashKey = shopId.toString();
        // 当前用户在当前店铺的购车产品列表
        String redisSkus = JedisUtil.getJedis().hget(redisKey, hashKey);
        if (!StringUtils.isBlank(redisSkus)) {
            JSONArray skusArray = JSONArray.parseArray(redisSkus);
            // 购物车中有商品
            if (skusArray.size() > 0) {
                // 替换实体类
                return skusArray.toJavaList(CartOfShopDto.class);
            }
        }
        return null;
    }

    @Override
    public Map<String, List<CartOfShopDto>> getShopCartFromRedisOutShop(Long userId, Double longitude, Double latitude) {
        // 从缓存中找到当前登录人的购物车信息
        String redisKey = RedisPrefixUtil.User_Cart_Prefix + userId;
        // 当前用户购车产品列表
        Map<String, String> redisSkus = JedisUtil.getJedis().hgetAll(redisKey);
        if (redisSkus.size() > 0) {
            List<CartOfShopDto> sortList = new ArrayList<>();
            // 循环店铺计算距离，判断是否失效
            for (String key : redisSkus.keySet()) {
                Integer shopId = Integer.parseInt(key);
                // todo 根据shopId从数据库获取店铺详情（经纬度），判断店铺是否失效
                CartOfShopDto shopDto = new CartOfShopDto();// from db
                boolean isOpen = true;// from db
                double range = 0;
                if (longitude != null && latitude != null) {
                    range = DistanceUtils.getDistance(shopDto.getLongitude(), shopDto.getLatitude(), longitude, latitude);
                }
                CartOfShopDto sortShopDto = new CartOfShopDto();
                // 门店是否关闭
                sortShopDto.setIsopen(isOpen ? 0 : 1);
                sortShopDto.setShopId(shopId);
                sortShopDto.setRange(range);
                sortList.add(sortShopDto);
            }
            Map<String, List<CartOfShopDto>> ret = new LinkedHashMap<>();
            if (sortList.size() > 1) {
                // 按距离排序店铺并组合返回参数
                sortList.sort(Comparator.comparingDouble(CartOfShopDto::getRange));
            }
            for (CartOfShopDto cartOfShopDto : sortList) {
                String cartSkuList = redisSkus.get(cartOfShopDto.getShopId().toString());
                ret.put(cartOfShopDto.getShopId().toString(), JSONArray.parseArray(cartSkuList).toJavaList(CartOfShopDto.class));
            }
            return ret;
        }
        return null;
    }

    @Override
    public Boolean addShopCartOnShop(CartOfShopDto cartOfShopDto) {

        // 从缓存中找到当前登录人的购物车信息
        String redisKey = RedisPrefixUtil.User_Cart_Prefix + cartOfShopDto.getUserId();
        String hashKey = cartOfShopDto.getShopId().toString();
        // 当前用户在当前店铺的购车产品列表
        String redisSkus = JedisUtil.getJedis().hget(redisKey, hashKey);
        List<CartOfShopDto> list = new ArrayList<>();
        if (!StringUtils.isBlank(redisSkus)) {
            JSONArray skusArray = JSONArray.parseArray(redisSkus);
            // 购物车中有商品
            if (skusArray.size() > 0) {
                list = skusArray.toJavaList(CartOfShopDto.class);
                boolean hasSame = false;
                for (CartOfShopDto shopDto : list) {
                    if (cartOfShopDto.getSkuId().equals(shopDto.getSkuId())) {
                        shopDto.setQuantity(cartOfShopDto.getQuantity());
                        hasSame = true;
                    }
                }
                if (!hasSame) {
                    // 没有相等的则插入
                    list.add(cartOfShopDto);
                }
            }
        } else {
            list.add(cartOfShopDto);
        }
        JSONArray redisRet = getJSONArrayByList(list);
        JedisUtil.getJedis().hset(redisKey, hashKey, redisRet.toString());
        return true;
    }

    @Override
    public Boolean delShopCart(Long userId, Integer shopId, Long skuId) {
        // 从缓存中找到当前登录人的购物车信息
        String redisKey = RedisPrefixUtil.User_Cart_Prefix + userId;
        String hashKey = shopId.toString();
        // 当前用户在当前店铺的购车产品列表
        String redisSkus = JedisUtil.getJedis().hget(redisKey, hashKey);
        if (!StringUtils.isBlank(redisSkus)) {
            List<CartOfShopDto> ret = new ArrayList<>();
            JSONArray skusArray = JSONArray.parseArray(redisSkus);
            // 购物车中有商品
            if (skusArray.size() > 0) {
                List<CartOfShopDto> list = skusArray.toJavaList(CartOfShopDto.class);
                for (CartOfShopDto cartOfShopDto : list) {
                    if (skuId.compareTo(cartOfShopDto.getSkuId()) != 0) {
                        ret.add(cartOfShopDto);
                    }
                }
            }
            // 重置redis中购物车数据
            JSONArray jsonArray = getJSONArrayByList(ret);
            if (jsonArray.size() > 0) {
                JedisUtil.getJedis().hset(redisKey, hashKey, jsonArray.toString());
            } else {
                JedisUtil.getJedis().hdel(redisKey, hashKey);
            }
        }
        return true;
    }

    @Override
    public Map<Integer, Integer> getAnyShopCartNum(Long userId) {
        // 从缓存中找到当前登录人的购物车信息
        String redisKey = RedisPrefixUtil.User_Cart_Prefix + userId;
        // 当前用户购车产品列表
        Map<String, String> redisSkus = JedisUtil.getJedis().hgetAll(redisKey);
        if (redisSkus.size() > 0) {
            Map<Integer, Integer> ret = new HashMap<>();
            // 循环店铺统计各个店铺购物车商品数量
            for (String key : redisSkus.keySet()) {
                Integer shopId = Integer.parseInt(key);
                String skuArray = redisSkus.get(key);
                JSONArray jsonArray = JSONArray.parseArray(skuArray);
                ret.put(shopId, jsonArray.size());
            }
            return ret;
        }
        return null;
    }

    private static JSONArray getJSONArrayByList(List<?> list) {
        JSONArray jsonArray = new JSONArray();
        if (list == null || list.isEmpty()) {
            return jsonArray;
        }

        for (Object object : list) {
            jsonArray.add(object);
        }
        return jsonArray;
    }

}
