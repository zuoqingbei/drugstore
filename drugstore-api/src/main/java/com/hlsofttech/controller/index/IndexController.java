package com.hlsofttech.controller.index;

import com.alibaba.dubbo.config.annotation.Reference;
import com.hlsofttech.annotation.AuthPower;
import com.hlsofttech.base.BaseController;
import com.hlsofttech.common.Constant;
import com.hlsofttech.entity.user.dto.BannerDTO;
import com.hlsofttech.entity.user.dto.CategoryDTO;
import com.hlsofttech.rsp.Result;
import com.hlsofttech.service.delivery.DeliveryMeituanService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/***
 * @Description: 用户端首页
 * @Date: 2019/8/13 9:17
 * @Author: suncy
 **/
@RestController
@Api(tags = "用户端首页", value = "用户端首页", description = "用户端首页 @author suncy")
public class IndexController extends BaseController {

    @Reference(version = Constant.VERSION, group = "com.hlsofttech.delivery", timeout = Constant.TIMEOUT)
    public DeliveryMeituanService deliveryMeituanService;

    /**
     * @Description: 查询用户端轮播图
     * @Date: 2019/8/13 9:44
     * @return: com.hlsofttech.rsp.Result
     * @Author: suncy
     **/
    @AuthPower(avoidVersion = false, avoidPower = true, avoidSign = true, avoidLogin = true, avoidPlatform = true)
    @ApiOperation(value = "用户端首页-轮播图列表", notes = "用户端首页-轮播图列表", httpMethod = "GET")
    @GetMapping(value = "/api/index/bannerList")
    public Result<List<BannerDTO>> bannerList() {

        // TODO: 2019/8/13 中台服务
        List<BannerDTO> bannerDTOList = new ArrayList<>();

        return Result.newSuccessResult(bannerDTOList);
    }

    /**
     * @Description: 查询用户分类信息
     * @Date: 2019/8/13 9:44
     * @return: com.hlsofttech.rsp.Result
     * @Author: suncy
     **/
    @AuthPower(avoidVersion = false, avoidPower = true, avoidSign = true, avoidLogin = true, avoidPlatform = true)
    @ApiOperation(value = "用户端首页-类别列表", notes = "用户端首页-类别列表", httpMethod = "GET")
    @GetMapping(value = "/api/index/categoryList")
    public Result<List<CategoryDTO>> categoryList() {

        // TODO: 2019/8/13 中台服务
        List<CategoryDTO> categoryDTOList = new ArrayList<>();

        return Result.newSuccessResult(categoryDTOList);
    }








}

