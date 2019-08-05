package com.hlsofttech.controller.erpapi;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.hlsofttech.annotation.AuthPower;
import com.hlsofttech.base.BaseController;
import com.hlsofttech.common.Constant;
import com.hlsofttech.entity.product.DrugsCategory;
import com.hlsofttech.platform.meituan.response.notify.OrderStatusNotifyResponse;
import com.hlsofttech.platform.meituan.sign.SignHelper;
import com.hlsofttech.rsp.Result;
import com.hlsofttech.service.product.DrugsCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

/**
 * @Description: 对接药店ERP系统API
 * @Date: 2019/8/2 15:08
 * @Author: suncy
 **/
@Slf4j
@RestController
@Api(tags = "ERP系统对接-药品分类", value = "ERP系统对接-药品分类", description = "ERP系统对接-药品分类 @author suncy")
public class ERPApiController extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(ERPApiController.class);

    @Reference(version = Constant.VERSION, group = "com.hlsofttech.product", timeout = Constant.TIMEOUT)
    public DrugsCategoryService iDrugsCategoryService;


    /**
     * @param param:
     * @Description: 药品分类信息同步
     * @Date: 2019/8/2 15:11
     * @return: java.lang.String
     * @Author: suncy
     **/
    @SuppressWarnings("rawtypes")
    @AuthPower(avoidVersion = false, avoidPower = true, avoidSign = true, avoidLogin = true, avoidPlatform = true)
    @ApiOperation(value = "ERP系统对接-药品分类新增或者修改", notes = "ERP系统对接-药品分类新增或者修改", httpMethod = "POST")
    @PostMapping("/api/erpApi/category/add")
    public String categoryAdd(@RequestBody String param) {
        log.info("ERP系统对接-药品分类新增或者修改:" + param);
        // 解析参数
        JSONObject jsonObject = (JSONObject) JSONObject.parse(param);
        boolean flag = false;
        try {
            flag = SignHelper.signAndCheck(jsonObject);
        } catch (Exception e) {
            log.error("加密错误！");
        }

        // 验签成功，开始处理业务
        if (flag) {
            log.info("验签成功，开始处理业务");
            OrderStatusNotifyResponse response = JSON.parseObject(param, OrderStatusNotifyResponse.class);
        }

        return null;
    }


}

