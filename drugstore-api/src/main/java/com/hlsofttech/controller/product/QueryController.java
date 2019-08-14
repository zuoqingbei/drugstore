package com.hlsofttech.controller.product;

import com.hlsofttech.annotation.AuthPower;
import com.hlsofttech.redis.JedisUtil;
import com.hlsofttech.rsp.Result;
import com.hlsofttech.utils.RedisPrefixUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
 * @Author: suntf
 * @Description:搜索相关接口
 * @Date: 2019/8/12
 **/
@RestController
@Api(tags = "搜索", value = "搜索", description = "搜索 @author suntf")
public class QueryController {

    /***
     * @Author: suntf
     * @Description:搜索商品/店铺
     * @Date: 2019/8/12
     * @return: com.hlsofttech.rsp.Result
     **/
    @AuthPower(avoidVersion = false, avoidPower = true, avoidSign = true, avoidLogin = false, avoidPlatform = true)
    @ApiOperation(value = "搜索-搜索商品/店铺", notes = "搜索-搜索商品/店铺", httpMethod = "GET")
    @GetMapping("/api/drugsQuery/query/queryProductOrShop")
    public Result queryProduct(String keyWord) {
        String userId = "12345";
        List<String> ret = new ArrayList<>();

        // 登录状态下，搜索历史存入缓存
        if (!StringUtils.isBlank(userId) && !StringUtils.isBlank(keyWord)) {
            String redisKey = RedisPrefixUtil.User_Query_History_Prefix + userId;
            // 当前用户在当前店铺的购车产品列表
            if (JedisUtil.getJedis().llen(redisKey) >= 10) {
                JedisUtil.getJedis().ltrim(redisKey, 0, 8);
            }
            // 移除相同元素
            JedisUtil.getJedis().lrem(redisKey,1,keyWord);
            // 新元素放到列表头
            JedisUtil.getJedis().lpush(redisKey, keyWord);
        }
        return Result.newSuccessResult(ret);
    }

    /***
     * @Author: suntf
     * @Description:获取搜索历史和热门搜索列表
     * @Date: 2019/8/12
     * @return: com.hlsofttech.rsp.Result
     **/
    @AuthPower(avoidVersion = false, avoidPower = true, avoidSign = true, avoidLogin = false, avoidPlatform = true)
    @ApiOperation(value = "搜索-搜索历史和热门搜索", notes = "搜索-搜索历史和热门搜索", httpMethod = "GET")
    @GetMapping("/api/drugsQuery/query/historyAndHotQuery")
    public Result getHistoryAndHotQuery() {
        String userId = "12345";
        Map<String, List<String>> ret = new HashMap<>();

        // 从缓存中获取搜索历史
        String redisKey = RedisPrefixUtil.User_Query_History_Prefix + userId;
        List<String> historyList = JedisUtil.getJedis().lrange(redisKey, 0, 9);
        ret.put("history", historyList);

        List<String> hotList = new ArrayList<>();
        hotList.add("热门1");
        hotList.add("热门2");
        hotList.add("热门3");
        hotList.add("热门4");
        ret.put("hot", hotList);
        return Result.newSuccessResult(ret);
    }
}