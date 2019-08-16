package com.hlsofttech.controller.shop;

import com.alibaba.dubbo.config.annotation.Reference;
import com.hlsofttech.annotation.AuthPower;
import com.hlsofttech.base.BaseController;
import com.hlsofttech.common.Constant;
import com.hlsofttech.entity.shop.DrugsShopSettings;
import com.hlsofttech.entity.vo.DrugsShopSettingsVO;
import com.hlsofttech.rsp.Result;
import com.hlsofttech.service.shop.DrugsShopSettingsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author suncy
 * @date 2019-08-16
 * @todo 药店-设置路由
 */
@Slf4j
@RestController
@Api(tags = "药店-设置", value = "药店-设置", description = "药店-相关设置 @author suncy")
public class DrugsShopSettingsController extends BaseController {

    @Reference(version = Constant.VERSION, timeout = Constant.TIMEOUT)
    public DrugsShopSettingsService drugsShopSettingsService;


    /**
     * @date @date   2019-08-16
     * @author suncy
     * @todo 药店-药店设置修改
     */
    @SuppressWarnings("rawtypes")
    @AuthPower(avoidVersion = false, avoidPower = true, avoidSign = true, avoidLogin = true, avoidPlatform = true)
    @ApiOperation(value = "药店-药店设置修改", notes = "药店-药店设置修改", httpMethod = "POST")
    @PostMapping("/api/drugsShopSettings/modify")
    public Result modify(DrugsShopSettingsVO drugsShopSettingsVO) {
        DrugsShopSettings shopSettings = new DrugsShopSettings();
        // vo转bean
        BeanUtils.copyProperties(drugsShopSettingsVO, shopSettings);

        DrugsShopSettings drugsShopSettings = drugsShopSettingsService.saveOrUpdate(shopSettings);

        if (drugsShopSettings != null) {

            // bean转VO
            BeanUtils.copyProperties(drugsShopSettings, drugsShopSettingsVO);
            return Result.newSuccessResult(drugsShopSettingsVO);
        }
        return Result.newFailureResult();
    }

    /**
     * @date @date   2019-08-16
     * @author suncy
     * @todo 药店-获取设置信息
     */
    @AuthPower(avoidVersion = false, avoidPower = true, avoidSign = true, avoidLogin = true, avoidPlatform = true)
    @ApiOperation(value = "药店-查询药店设置", notes = "药店-查询药店设置", httpMethod = "GET")
    @GetMapping(value = "/api/drugsShopSettings/{drugsShopId}")
    public Result<DrugsShopSettingsVO> getByDrugsShopId(@PathVariable("drugsShopId") String drugsShopId) {

        DrugsShopSettingsVO drugsShopSettingsVO = new DrugsShopSettingsVO();
        DrugsShopSettings shopSettings = drugsShopSettingsService.getByDrugsShopId(drugsShopId);
        // 转VO
        BeanUtils.copyProperties(shopSettings, drugsShopSettingsVO);

        if (shopSettings != null) {
            return Result.newSuccessResult(drugsShopSettingsVO);
        }
        return Result.newFailureResult();
    }

}

