package com.hlsofttech.controller.shop;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.hlsofttech.annotation.AuthPower;
import com.hlsofttech.base.BaseController;
import com.hlsofttech.common.Constant;
import com.hlsofttech.entity.shop.DrugsShopSettings;
import com.hlsofttech.rsp.Result;
import com.hlsofttech.service.shop.DrugsShopSettingsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

/**
 * @author suncy
 * @date 2019-08-16
 * @todo 药店-相关设置路由
 */
@Slf4j
@RestController
@Api(tags = "药店-相关设置", value = "药店-相关设置", description = "药店-相关设置 @author suncy")
public class DrugsShopSettingsController extends BaseController {

    @Reference(version = Constant.VERSION, timeout = Constant.TIMEOUT)
    public DrugsShopSettingsService iDrugsShopSettingsService;


    /**
     * @date @date   2019-08-16
     * @author suncy
     * @todo 药店-相关设置新增或者修改
     */
    @SuppressWarnings("rawtypes")
    @AuthPower(avoidVersion = false, avoidPower = true, avoidSign = true, avoidLogin = true, avoidPlatform = true)
    @ApiOperation(value = "药店-相关设置新增或者修改", notes = "药店-相关设置新增或者修改", httpMethod = "POST")
    @PostMapping("/api/drugsShopSettings/creation")
    public Result creation(DrugsShopSettings entity) {
        if ((entity = iDrugsShopSettingsService.saveOrUpdate(entity)) != null) {
            return Result.newSuccessResult(entity);
        }
        return Result.newFailureResult();
    }

    /**
     * @date @date   2019-08-16
     * @author suncy
     * @todo 药店-相关设置逻辑删除
     */
    @SuppressWarnings("rawtypes")
    @AuthPower(avoidVersion = false, avoidPower = true, avoidSign = true, avoidLogin = true, avoidPlatform = true)
    @ApiOperation(value = "删除药店-相关设置", notes = "删除药店-相关设置", httpMethod = "DELETE")
    @DeleteMapping("/api/drugsShopSettings/delete/{id}")
    public Result deleteLogic(@PathVariable("id") String id) {
        return Result.newSuccessResult(iDrugsShopSettingsService.deleteLogic(id));
    }

    /**
     * @date @date   2019-08-16
     * @author suncy
     * @todo 药店-相关设置单条数据查询
     */
    @AuthPower(avoidVersion = false, avoidPower = true, avoidSign = true, avoidLogin = true, avoidPlatform = true)
    @ApiOperation(value = "药店-相关设置单条数据查询", notes = "药店-相关设置单条数据查询", httpMethod = "GET")
    @GetMapping(value = "/api/drugsShopSettings/{id}")
    public Result<DrugsShopSettings> getById(@PathVariable("id") String id) {
        if (iDrugsShopSettingsService.deleteLogic(id)) {
            return Result.newSuccessResult();
        }
        return Result.newFailureResult();
    }

    /**
     * @date @date   2019-08-16
     * @author suncy
     * @todo 药店-相关设置数据分页查询
     */
    @AuthPower(avoidVersion = false, avoidPower = true, avoidSign = true, avoidLogin = true, avoidPlatform = true)
    @ApiOperation(value = "药店-相关设置数据分页查询", notes = "药店-相关设置数据分页查询", httpMethod = "GET")
    @GetMapping(value = "/api/drugsShopSettings/list")
    public Result<PageInfo<DrugsShopSettings>> pageList(DrugsShopSettings entity, @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                                        @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return Result.newSuccessResult(iDrugsShopSettingsService.pageList(entity, pageNum, pageSize));
    }


}

