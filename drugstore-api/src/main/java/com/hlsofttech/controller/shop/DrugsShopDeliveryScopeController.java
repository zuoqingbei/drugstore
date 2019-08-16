package com.hlsofttech.controller.shop;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.hlsofttech.annotation.AuthPower;
import com.hlsofttech.base.BaseController;
import com.hlsofttech.common.Constant;
import com.hlsofttech.entity.shop.DrugsShopDeliveryScope;
import com.hlsofttech.entity.vo.DrugsShopDeliveryScopeVO;
import com.hlsofttech.rsp.Result;
import com.hlsofttech.service.shop.DrugsShopDeliveryScopeService;
import com.hlsofttech.util.ProcessBusiness;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author suncy
 * @date 2019-08-16
 * @todo 药店-配送范围路由
 */
@Slf4j
@RestController
@Api(tags = "药店-配送范围", value = "药店-配送范围", description = "药店-配送范围 @author suncy")
public class DrugsShopDeliveryScopeController extends BaseController implements ProcessBusiness {

    @Reference(version = Constant.VERSION, timeout = Constant.TIMEOUT)
    public DrugsShopDeliveryScopeService drugsShopDeliveryScopeService;


    /**
     * @date @date   2019-08-16
     * @author suncy
     * @todo 药店-获取药店配送范围列表
     */
    @AuthPower(avoidVersion = false, avoidPower = true, avoidSign = true, avoidLogin = true, avoidPlatform = true)
    @ApiOperation(value = "药店-获取药店配送范围列表", notes = "药店-获取药店配送范围列表", httpMethod = "GET")
    @GetMapping(value = "/api/drugsShopDeliveryScope/list")
    public Result<List<DrugsShopDeliveryScopeVO>> pageList(
            @RequestParam(value = "drugsShopId", required = true) String drugsShopId,
            @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {

        DrugsShopDeliveryScope entity = new DrugsShopDeliveryScope();
        entity.setDrugsShopId(drugsShopId);
        PageInfo<DrugsShopDeliveryScope> pageInfo = drugsShopDeliveryScopeService.pageList(entity, pageNum, pageSize);

        // DTOlist 转VOList
        List<DrugsShopDeliveryScopeVO> drugsShopDeliveryScopeVOList = new ArrayList<>();
        if (pageInfo != null && pageInfo.getList() != null && pageInfo.getList().size() > 0) {
            drugsShopDeliveryScopeVOList = pageInfo.getList().stream().map(drugsShopDeliveryScope ->
                    this.convertTarget(drugsShopDeliveryScope, DrugsShopDeliveryScopeVO::new))
                    .collect(Collectors.toList());
        } else {
            log.error("[--获取药店配送范围列表 pageInfo.getList() 失败--],pageInfo.getList():" + pageInfo.getList());
        }

        return Result.newSuccessResult(drugsShopDeliveryScopeVOList);

    }


    /**
     * @date @date   2019-08-16
     * @author suncy
     * @todo 药店-配送范围新增或者修改
     */
    @SuppressWarnings("rawtypes")
    @AuthPower(avoidVersion = false, avoidPower = true, avoidSign = true, avoidLogin = true, avoidPlatform = true)
    @ApiOperation(value = "药店-配送范围新增或者修改", notes = "药店-配送范围新增或者修改", httpMethod = "POST")
    @PostMapping("/api/drugsShopDeliveryScope/creation")
    public Result creation(DrugsShopDeliveryScopeVO drugsShopDeliveryScopeVO) {

        DrugsShopDeliveryScope entity = new DrugsShopDeliveryScope();
        // vo转bean
        BeanUtils.copyProperties(drugsShopDeliveryScopeVO, entity);
        DrugsShopDeliveryScope drugsShopDeliveryScope = drugsShopDeliveryScopeService.saveOrUpdate(entity);

        if (drugsShopDeliveryScope != null) {

            // bean转VO
            BeanUtils.copyProperties(drugsShopDeliveryScope, drugsShopDeliveryScopeVO);
            return Result.newSuccessResult(drugsShopDeliveryScopeVO);
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
    @ApiOperation(value = "药店-删除配送范围", notes = "删除药店-配送范围", httpMethod = "DELETE")
    @DeleteMapping("/api/drugsShopDeliveryScope/delete/{id}")
    public Result deleteLogic(@PathVariable("id") String id) {
        return Result.newSuccessResult(drugsShopDeliveryScopeService.deleteLogic(id));
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
        return Result.newSuccessResult(drugsShopDeliveryScopeService.getById(id));
    }


}

