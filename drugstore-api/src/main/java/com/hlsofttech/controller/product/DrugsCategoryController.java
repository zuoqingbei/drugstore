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
    private final Logger logger = LoggerFactory.getLogger(DrugsCategoryController.class);

    @Reference(version = Constant.VERSION, group = "com.hlsofttech.product", timeout = Constant.TIMEOUT)
    public DrugsCategoryService iDrugsCategoryService;


    /**
     * @date @date   2019-08-02
     * @author suncy
     * @todo 药品库-药品分类新增或者修改
     */
    @SuppressWarnings("rawtypes")
    @AuthPower(avoidVersion = false, avoidPower = true, avoidSign = true, avoidLogin = true, avoidPlatform = true)
    @ApiOperation(value = "药品库-药品分类新增或者修改", notes = "药品库-药品分类新增或者修改", httpMethod = "POST")
    @PostMapping("/api/drugsCategory/creation")
    public Result creation(DrugsCategory entity) {
        if ((entity = iDrugsCategoryService.saveOrUpdate(entity)) != null) {
            return Result.newSuccessResult(entity);
        }
        return Result.newFailureResult();
    }

    /**
     * @date @date   2019-08-02
     * @author suncy
     * @todo 药品库-药品分类逻辑删除
     */
    @SuppressWarnings("rawtypes")
    @AuthPower(avoidVersion = false, avoidPower = true, avoidSign = true, avoidLogin = true, avoidPlatform = true)
    @ApiOperation(value = "删除药品库-药品分类", notes = "删除药品库-药品分类", httpMethod = "DELETE")
    @DeleteMapping("/api/drugsCategory/delete/{id}")
    public Result deleteLogic(@PathVariable("id") String id) {
        return Result.newSuccessResult(iDrugsCategoryService.deleteLogic(id));
    }

    /**
     * @date @date   2019-08-02
     * @author suncy
     * @todo 药品库-药品分类单条数据查询
     */
    @AuthPower(avoidVersion = false, avoidPower = true, avoidSign = true, avoidLogin = true, avoidPlatform = true)
    @ApiOperation(value = "药品库-药品分类单条数据查询", notes = "药品库-药品分类单条数据查询", httpMethod = "GET")
    @GetMapping(value = "/api/drugsCategory/{id}")
    public Result<DrugsCategory> getById(@PathVariable("id") String id) {
        if (iDrugsCategoryService.deleteLogic(id)) {
            return Result.newSuccessResult();
        }
        return Result.newFailureResult();
    }

    /**
     * @date @date   2019-08-02
     * @author suncy
     * @todo 药品库-药品分类数据分页查询
     */
    @AuthPower(avoidVersion = false, avoidPower = true, avoidSign = true, avoidLogin = true, avoidPlatform = true)
    @ApiOperation(value = "药品库-药品分类数据分页查询", notes = "药品库-药品分类数据分页查询", httpMethod = "GET")
    @GetMapping(value = "/api/drugsCategory/list")
    public Result<PageInfo<DrugsCategory>> pageList(DrugsCategory entity, @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                                    @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return Result.newSuccessResult(iDrugsCategoryService.pageList(entity, pageNum, pageSize));
    }


}

