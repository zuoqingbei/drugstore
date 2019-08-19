package com.hlsofttech.controller.business;

import com.alibaba.dubbo.config.annotation.Reference;
import com.bjucloud.common.ExecuteResult;
import com.bjucloud.storecenter.dto.ShopDTO;
import com.bjucloud.storecenter.service.ShopExportService;
import com.bjucloud.usercenter.dto.RegisterDTO;
import com.hlsofttech.annotation.AuthPower;
import com.hlsofttech.common.Constant;
import com.hlsofttech.rsp.Result;
import com.hlsofttech.util.MemberCacheUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/***
 * @Author: suntf
 * @Description:商户端-店铺相关
 * @Date: 2019/8/19
 **/
@RestController
@Api(tags = "商户端-店铺相关", value = "商户端-店铺相关", description = "商户端-店铺相关 @author suntf")
public class ShopController {

    @Reference(version = Constant.VERSION_ZT, timeout = Constant.TIMEOUT)
    ShopExportService shopExportService;

    /***
     * @Author: suntf
     * @Description:获取当前登陆的店铺信息
     * @Date: 2019/8/19
     * @return: java.lang.String
     **/
    @RequiresAuthentication
    @GetMapping("/api/business/shop/info")
    @ApiOperation(value = "店铺-店铺信息", notes = "店铺-店铺信息", httpMethod = "GET")
    @AuthPower(avoidVersion = false, avoidPower = true, avoidSign = true, avoidLogin = false, avoidPlatform = true)
    public Result getShopInfo() {
        // 查询登录用户是否存在
        RegisterDTO registerDTO = MemberCacheUtil.getSessionUserInfo();
        Long shopId = registerDTO.getShopId();
        if (shopId == null) {
            return Result.newFailureResult(new Exception("当前用户未开通店铺"));
        }
        // 根据shopId查询店铺信息
        ExecuteResult<ShopDTO> result = shopExportService.findShopInfoById(shopId);
        if (result.isSuccess() && result.getResult() != null) {
            return Result.newSuccessResult(result.getResult());
        } else {
            return Result.newFailureResult(new Exception("当前用户未开通店铺"));
        }
    }

    /***
     * @Author: suntf
     * @Description:中台店铺入驻
     * @Date: 2019/8/19
     * @return: java.lang.String
     **/
    @GetMapping("/api/business/center/addNewShop")
    @ApiOperation(value = "(禁止调用)店铺-开通店铺", notes = "(禁止调用)店铺-开通店铺", httpMethod = "GET")
    @AuthPower(avoidVersion = false, avoidPower = true, avoidSign = true, avoidLogin = false, avoidPlatform = true)
    public Result addShop(String shopName, Long mobile) {
        ShopDTO shopDTO = new ShopDTO();
        shopDTO.setShopName(shopName);
        shopDTO.setShopType(10);
        shopDTO.setMobile(mobile);
        shopDTO.setScope("5.0");
        ExecuteResult<String> result = shopExportService.saveShopInfo(shopDTO);
        return Result.newSuccessResult(result);
    }


}
