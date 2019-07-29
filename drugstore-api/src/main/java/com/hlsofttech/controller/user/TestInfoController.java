package com.hlsofttech.controller.user;

import com.hlsofttech.entity.delivery.dto.ShopInfoDTO;
import com.hlsofttech.service.delivery.DeliveryFengniaoService;
import com.hlsofttech.service.delivery.DeliveryMeituanService;
import org.springframework.web.bind.annotation.RestController;
import com.hlsofttech.base.BaseController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.hlsofttech.service.user.TestInfoService;
import com.hlsofttech.entity.user.TestInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.github.pagehelper.PageInfo;
import com.alibaba.dubbo.config.annotation.Reference;
import com.hlsofttech.rsp.Result;
import com.hlsofttech.annotation.AuthPower;
import com.hlsofttech.common.Constant;

/**
 * @author suncy123
 * @date 2019-07-23
 * @todo 新闻收藏路由
 */
@RestController
@Api(tags = "测试", value = "测试", description = "测试 @author suncy123")
public class TestInfoController extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(TestInfoController.class);

    @Reference(version = Constant.VERSION, group = "com.hlsofttech.delivery", timeout = Constant.TIMEOUT)
    public DeliveryMeituanService deliveryMeituanService;

    @Reference(version = Constant.VERSION, group = "com.hlsofttech.delivery", timeout = Constant.TIMEOUT)
    public DeliveryFengniaoService deliveryFengniaoService;


    @AuthPower(avoidVersion = false, avoidPower = true, avoidSign = true, avoidLogin = true, avoidPlatform = true)
    @ApiOperation(value = "测试", notes = "测试", httpMethod = "GET")
    @GetMapping(value = "/api/testInfo/createShop")
    public Result<TestInfo> createShop() {

        deliveryMeituanService.createShop(new ShopInfoDTO());

        deliveryFengniaoService.createShop(new ShopInfoDTO());

        return null;
    }


}

