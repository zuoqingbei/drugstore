package com.hlsofttech.controller.product;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.hlsofttech.annotation.AuthPower;
import com.hlsofttech.base.BaseController;
import com.hlsofttech.common.Constant;
import com.hlsofttech.entity.product.DrugsInfo;
import com.hlsofttech.rsp.Result;
import com.hlsofttech.service.product.DrugsInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author suncy
 * @date 2019-08-14
 * @todo 药品库-药品信息表路由
 */
@RestController
@Slf4j
@Api(tags = "药品库-药品信息", value = "药品库-药品信息", description = "药品库-药品信息 @author suncy")
public class DrugsInfoController extends BaseController {

    @Reference(version = Constant.VERSION, timeout = Constant.TIMEOUT)
    DrugsInfoService drugsInfoService;

    /**
     * @date @date   2019-08-14
     * @author suncy
     * @todo 药品库-药品信息表数据分页查询
     */
    @AuthPower(avoidVersion = false, avoidPower = true, avoidSign = true, avoidLogin = true, avoidPlatform = true)
    @ApiOperation(value = "药品库-药品信息表数据分页查询", notes = "药品库-药品信息表数据分页查询", httpMethod = "GET")
    @GetMapping(value = "/api/drugsInfo/list")
    public Result<PageInfo<DrugsInfo>> pageList(@RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                                @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return Result.newSuccessResult(drugsInfoService.pageList(new DrugsInfo(), pageNum, pageSize));
    }


}

