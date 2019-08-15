package com.hlsofttech.controller.index;

import com.alibaba.dubbo.config.annotation.Reference;
import com.bjucloud.contentcenter.dto.AdvertisementDTO;
import com.bjucloud.contentcenter.service.AdvertisementService;
import com.bjucloud.goodscenter.dto.ItemCategoryDTO;
import com.hlsofttech.annotation.AuthPower;
import com.hlsofttech.base.BaseController;
import com.hlsofttech.common.Constant;
import com.hlsofttech.entity.vo.AdvertisementVO;
import com.hlsofttech.entity.vo.ItemCategoryVO;
import com.hlsofttech.rsp.Result;
import com.hlsofttech.util.ProcessBusiness;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/***
 * @Description: 用户端首页
 * @Date: 2019/8/13 9:17
 * @Author: suncy
 **/
@Slf4j
@RestController
@Api(tags = "用户端-首页", value = "用户端-首页", description = "用户端-首页 @author suncy")
public class IndexController extends BaseController implements ProcessBusiness {

    @Reference(version = Constant.VERSION_ZT, timeout = Constant.TIMEOUT)
    AdvertisementService advertisementService;

    /**
     * @Description: 查询用户首页轮播图
     * @Date: 2019/8/13 9:44
     * @return: com.hlsofttech.rsp.Result
     * @Author: suncy
     **/
    @AuthPower(avoidVersion = false, avoidPower = true, avoidSign = true, avoidLogin = true, avoidPlatform = true)
    @ApiOperation(value = "用户端首页-轮播图列表", notes = "用户端首页-轮播图列表", httpMethod = "GET")
    @GetMapping(value = "/api/index/bannerList")
    public Result<List<AdvertisementVO>> bannerList() {

        // TODO: 2019/8/15 后续再加缓存

        // 查询中台的广告图列表
        AdvertisementDTO searchDTO = new AdvertisementDTO();
        searchDTO.setZoneId("2");// 广告位ID,中台提供
        searchDTO.setYn("1"); // 是否有效标识位，固定1
        List<AdvertisementDTO> advertisementDTOList = advertisementService.getListByCondition(searchDTO).getResult();

        // DTOlist 转VOList
        List<AdvertisementVO> advertisementVOList = new ArrayList<>();
        if (advertisementDTOList != null && advertisementDTOList.size() > 0) {
            advertisementVOList = advertisementDTOList.stream().map(advertisementDTO -> this.convertTarget(advertisementDTO, AdvertisementVO::new))
                    .collect(Collectors.toList());
        } else {
            log.error("[--查询用户首页轮播图 itemCategoryDTOList 失败--],advertisementDTOList:" + advertisementDTOList);
        }
        return Result.newSuccessResult(advertisementVOList);
    }

    /**
     * @Description: 查询用户首页分类信息
     * @Date: 2019/8/13 9:44
     * @return: com.hlsofttech.rsp.Result
     * @Author: suncy
     **/
    @AuthPower(avoidVersion = false, avoidPower = true, avoidSign = true, avoidLogin = true, avoidPlatform = true)
    @ApiOperation(value = "用户端首页-类别列表", notes = "用户端首页-类别列表", httpMethod = "GET")
    @GetMapping(value = "/api/index/categoryList")
    public Result<List<ItemCategoryVO>> categoryList() {

        // TODO: 2019/8/15 后续再缓存

        // 查询中台的分类列表
        List<ItemCategoryDTO> itemCategoryDTOList = new ArrayList<>();

        // DTOlist 转VOList
        List<ItemCategoryVO> itemCategoryVOList = new ArrayList<>();
        if (itemCategoryDTOList != null && itemCategoryDTOList.size() > 0) {
            itemCategoryVOList = itemCategoryDTOList.stream().map(itemCategoryDTO -> this.convertTarget(itemCategoryDTO, ItemCategoryVO::new))
                    .collect(Collectors.toList());
        } else {
            log.error("[--查询用户首页分类信息 itemCategoryDTOList 失败--],itemCategoryDTOList:" + itemCategoryDTOList);
        }

        return Result.newSuccessResult(itemCategoryVOList);
    }





}

