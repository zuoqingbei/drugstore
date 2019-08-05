package com.hlsofttech.controller.erpapi;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hlsofttech.annotation.AuthPower;
import com.hlsofttech.base.BaseController;
import com.hlsofttech.common.Constant;
import com.hlsofttech.rsp.Result;
import com.hlsofttech.service.product.DrugsCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Description: 对接药店ERP系统API
 * @Date: 2019/8/2 15:08
 * @Author: suncy
 **/
@Slf4j
@RestController
@Api(tags = "ERP系统对接", value = "ERP系统对接", description = "ERP系统对接 @author suncy")
public class ERPApiController extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(ERPApiController.class);

    @Reference(version = Constant.VERSION, group = "com.hlsofttech.product", timeout = Constant.TIMEOUT)
    public DrugsCategoryService iDrugsCategoryService;

    /***
     * @Description: 门店对照关系同步(根据统一信用代码)
     * @Date: 2019/8/5 9:23
     * @param param: 门店在ERP系统中的ID
     * @return: java.lang.String 门店在购药平台的ID
     * @Author: suncy
     **/
    @SuppressWarnings("rawtypes")
    @AuthPower(avoidVersion = false, avoidPower = true, avoidSign = true, avoidLogin = true, avoidPlatform = true)
    @ApiOperation(value = "ERP系统对接-门店批量同步", notes = "ERP系统对接-门店批量同步", httpMethod = "POST")
    @PostMapping("/api/erpApi/shop/add")
    public Result shopAdd(@RequestBody String param) {

        // 测试
        String secret = "7BB4DDA93C2972B9D9E447EE30E0A772";

        log.info("ERP系统对接-门店批量同步:" + param);
        Result result = null;

        try {
            // 解析参数并进行验签处理，验签成功返回接收的参数
            Map<String, String> paramsMap = ERPParamUtil.checkInfo(param, secret);
            if (paramsMap != null) {
                JSONObject json = JSONObject.parseObject(param);
                JSONArray array = json.getJSONArray("data");

                List<String> shopIdList = new ArrayList<>();
                for (int i = 0; i < array.size(); i++) {
                    JSONObject jo = array.getJSONObject(i);
                    String unifiedCreditCodeStr = jo.getString("unifiedCreditCode");
                    System.out.println(unifiedCreditCodeStr);

                    // TODO: 2019/8/5  根据统一信用代码查询门店在平台中的门店ID
                    String shopId = unifiedCreditCodeStr;
                    shopIdList.add(shopId);
                }
                result = new Result(true, "同步成功", shopIdList);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = new Result(false, "同步失败");
        }
        return result;
    }


    /***
     * @Description: 药品信息同步
     * @Date: 2019/8/5 9:31
     * @param param:
     * @return: java.lang.String
     * @Author: suncy
     **/
    /*@SuppressWarnings("rawtypes")
    @AuthPower(avoidVersion = false, avoidPower = true, avoidSign = true, avoidLogin = true, avoidPlatform = true)
    @ApiOperation(value = "ERP系统对接-药品信息同步", notes = "ERP系统对接-药品信息同步", httpMethod = "POST")
    @PostMapping("/api/erpApi/drugs/add")
    public String drugsAdd(@RequestBody String param) {
        log.info("ERP系统对接-药品分类新增或者修改:" + param);
        if (checkInfo(param)) {
            // TODO: 2019/8/5 处理业务
        }
        return null;
    }*/

}

