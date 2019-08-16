package com.hlsofttech.controller.shop;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.hlsofttech.annotation.AuthPower;
import com.hlsofttech.base.BaseController;
import com.hlsofttech.common.Constant;
import com.hlsofttech.entity.shop.DrugsShopDeliveryScope;
import com.hlsofttech.rsp.Result;
import com.hlsofttech.service.shop.DrugsShopDeliveryScopeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @author suncy
 * @date 2019-08-16
 * @todo 药店-配送范围路由
 */
@Slf4j
@RestController
@Api(tags = "药店-配送范围", value = "药店-配送范围", description = "药店-配送范围 @author suncy")
public class DrugsShopDeliveryScopeController extends BaseController {

    @Reference(version = Constant.VERSION, timeout = Constant.TIMEOUT)
    public DrugsShopDeliveryScopeService iDrugsShopDeliveryScopeService;


    /**
     * @date @date   2019-08-16
     * @author suncy
     * @todo 药店-配送范围新增或者修改
     */
    @SuppressWarnings("rawtypes")
    @AuthPower(avoidVersion = false, avoidPower = true, avoidSign = true, avoidLogin = true, avoidPlatform = true)
    @ApiOperation(value = "药店-配送范围新增或者修改", notes = "药店-配送范围新增或者修改", httpMethod = "POST")
    @PostMapping("/api/drugsShopDeliveryScope/creation")
    public Result creation(DrugsShopDeliveryScope entity) {
        if ((entity = iDrugsShopDeliveryScopeService.saveOrUpdate(entity)) != null) {
            return Result.newSuccessResult(entity);
        }
        return Result.newFailureResult();
    }

    /**
     * @date @date   2019-08-16
     * @author suncy
     * @todo 药店-配送范围逻辑删除
     */
    @SuppressWarnings("rawtypes")
    @AuthPower(avoidVersion = false, avoidPower = true, avoidSign = true, avoidLogin = true, avoidPlatform = true)
    @ApiOperation(value = "删除药店-配送范围", notes = "删除药店-配送范围", httpMethod = "DELETE")
    @DeleteMapping("/api/drugsShopDeliveryScope/delete/{id}")
    public Result deleteLogic(@PathVariable("id") String id) {
        return Result.newSuccessResult(iDrugsShopDeliveryScopeService.deleteLogic(id));
    }

    /**
     * @date @date   2019-08-16
     * @author suncy
     * @todo 药店-配送范围单条数据查询
     */
    @AuthPower(avoidVersion = false, avoidPower = true, avoidSign = true, avoidLogin = true, avoidPlatform = true)
    @ApiOperation(value = "药店-配送范围单条数据查询", notes = "药店-配送范围单条数据查询", httpMethod = "GET")
    @GetMapping(value = "/api/drugsShopDeliveryScope/{id}")
    public Result<DrugsShopDeliveryScope> getById(@PathVariable("id") String id) {
        if (iDrugsShopDeliveryScopeService.deleteLogic(id)) {
            return Result.newSuccessResult();
        }
        return Result.newFailureResult();
    }

    /**
     * @date @date   2019-08-16
     * @author suncy
     * @todo 药店-配送范围数据分页查询
     */
    @AuthPower(avoidVersion = false, avoidPower = true, avoidSign = true, avoidLogin = true, avoidPlatform = true)
    @ApiOperation(value = "药店-配送范围数据分页查询", notes = "药店-配送范围数据分页查询", httpMethod = "GET")
    @GetMapping(value = "/api/drugsShopDeliveryScope/list")
    public Result<PageInfo<DrugsShopDeliveryScope>> pageList(DrugsShopDeliveryScope entity, @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                                             @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return Result.newSuccessResult(iDrugsShopDeliveryScopeService.pageList(entity, pageNum, pageSize));
    }


}

