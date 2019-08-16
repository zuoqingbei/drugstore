package com.hlsofttech.controller.product;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.hlsofttech.annotation.AuthPower;
import com.hlsofttech.base.BaseController;
import com.hlsofttech.common.Constant;
import com.hlsofttech.entity.product.DrugsCategory;
import com.hlsofttech.rsp.Result;
import com.hlsofttech.service.product.DrugsCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

/**
 * @author suncy
 * @date 2019-08-02
 * @todo 药品库-药品分类路由
 */
@RestController
@Api(tags = "药品库-药品分类", value = "药品库-药品分类", description = "药品库-药品分类 @author suncy")
public class DrugsCategoryController extends BaseController {

    @Reference(version = Constant.VERSION, timeout = Constant.TIMEOUT)
    public DrugsCategoryService iDrugsCategoryService;


    /**
     * @date @date   2019-08-02
     * @author suncy
     * @todo 药品库-药品分类数据分页查询
     */
    @AuthPower(avoidVersion = false, avoidPower = true, avoidSign = true, avoidLogin = true, avoidPlatform = true)
    @ApiOperation(value = "药品库-药品分类数据分页查询", notes = "药品库-药品分类数据分页查询", httpMethod = "GET")
    @GetMapping(value = "/api/drugsCategory/list")
    public Result<PageInfo<DrugsCategory>> pageList(@RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                                    @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return Result.newSuccessResult(iDrugsCategoryService.pageList(new DrugsCategory(), pageNum, pageSize));
    }


}

