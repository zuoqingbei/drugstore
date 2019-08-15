package com.hlsofttech.controller.erpapi;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.hlsofttech.annotation.AuthPower;
import com.hlsofttech.base.BaseController;
import com.hlsofttech.common.Constant;
import com.hlsofttech.entity.shop.DrugsShopInfo;
import com.hlsofttech.entity.vo.*;
import com.hlsofttech.exception.CommonBizException;
import com.hlsofttech.exception.ExpCodeEnum;
import com.hlsofttech.rsp.Result;
import com.hlsofttech.service.shop.DrugsShopInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description: 对接药店ERP系统API
 * @Date: 2019/8/2 15:08
 * @Author: suncy
 **/
@Slf4j
@RestController
@Api(tags = "ERP系统对接", value = "ERP系统对接", description = "ERP系统对接 @author suncy")
public class ERPApiController extends BaseController {

    @Reference(version = Constant.VERSION, timeout = Constant.TIMEOUT)
    private DrugsShopInfoService drugsShopInfoService;


    // 测试
    public static String app_secret = "7BB4DDA93C2972B9D9E447EE30E0A772";

    /***
     * @Description: 门店对照关系同步(根据统一信用代码)
     * @Date: 2019/8/5 9:23
     * @param shopIdRequest: 请求参数
     * @return: java.lang.String 门店在购药平台的ID
     * @Author: suncy
     **/
    @SuppressWarnings("rawtypes")
    @ApiOperation(value = "ERP系统对接-门店批量同步", notes = "ERP系统对接-门店批量同步", httpMethod = "POST")
    @PostMapping("/api/erpApi/shop/getShopIds")
    public Result getShopIds(@RequestBody @Validated ShopIdRequest shopIdRequest) {

        log.info("ERP系统对接-门店批量同步:" + shopIdRequest.toString());
        DrugsShopInfo drugsShopInfo = drugsShopInfoService.getByAppKey(shopIdRequest.getAppkey());
        if (drugsShopInfo == null) {
            // appkey不存在
            return Result.newFailureResult(new CommonBizException(ExpCodeEnum.SECRET_FAIL));
        }
        try {
            // 解析参数并进行验签处理，验签成功返回接收的参数
            boolean checkFlag = ERPParamUtil.checkInfo(JSONObject.toJSON(shopIdRequest).toString(), drugsShopInfo.getAppSecret());
            if (!checkFlag) {
                // 验签失败
                return Result.newFailureResult(new CommonBizException(ExpCodeEnum.SIGN_FAIL));
            }

            // 验签成功
            List<ShopIdVO> data = shopIdRequest.getData();
            if (data != null && data.size() > 0) {
                for (ShopIdVO shopIdVO : data) {
                    shopIdVO.setShopId(shopIdVO.getCode() + "1");
                }
            }

            return Result.newSuccessResult(data);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.newFailureResult(new CommonBizException(ExpCodeEnum.SYS_ERROR));
        }
    }


    /***
     * @Description: 药品信息同步
     * @Date: 2019/8/5 9:31
     * @param drugsAddRequest: 国标码、条形码、
     * @return: java.lang.String
     * @Author: suncy
     **/
    @SuppressWarnings("rawtypes")
    @ApiOperation(value = "ERP系统对接-药品信息批量同步", notes = "ERP系统对接-药品信息批量同步", httpMethod = "POST")
    @PostMapping("/api/erpApi/drugs/drugsSyn")
    public Result drugsSyn(@RequestBody @Validated DrugsAddRequest drugsAddRequest) {
        log.info("ERP系统对接-药品信息批量同步:" + drugsAddRequest.toString());
        DrugsShopInfo drugsShopInfo = drugsShopInfoService.getByAppKey(drugsAddRequest.getAppkey());
        if (drugsShopInfo == null) {
            // appkey不存在
            return Result.newFailureResult(new CommonBizException(ExpCodeEnum.SECRET_FAIL));
        }
        try {
            // 解析参数并进行验签处理，验签成功返回接收的参数
            boolean checkFlag = ERPParamUtil.checkInfo(JSONObject.toJSON(drugsAddRequest).toString(), drugsShopInfo.getAppSecret());
            if (!checkFlag) {
                // 验签失败
                return Result.newFailureResult(new CommonBizException(ExpCodeEnum.SIGN_FAIL));
            }

            // 验签成功
            List<DrugsAddVO> data = drugsAddRequest.getData();
            if (data != null && data.size() > 0) {
                for (DrugsAddVO drugsAddVO : data) {
                    System.out.println(drugsAddVO.toString());
                }
            }
            return Result.newSuccessResult(data);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.newFailureResult(new CommonBizException(ExpCodeEnum.SYS_ERROR));
        }
    }

    /***
     * @Author: suntf
     * @Description:库存同步,支持批量
     * @Date: 2019/8/6
     * @param syncStockForErpRequest:
     * @return: com.hlsofttech.rsp.Result
     **/
    @AuthPower(avoidSign = false, avoidLogin = true)
    @ApiOperation(value = "ERP系统对接-库存同步", notes = "ERP系统对接-库存同步", httpMethod = "POST")
    @PostMapping("/api/erpApi/stock/sync")
    public Result syncStock(@RequestBody SyncStockForErpRequest syncStockForErpRequest) {
        DrugsShopInfo drugsShopInfo = drugsShopInfoService.getByAppKey(syncStockForErpRequest.getAppkey());
        if (drugsShopInfo == null) {
            // appkey不存在
            return Result.newFailureResult(new CommonBizException(ExpCodeEnum.SECRET_FAIL));
        }
        try {
            // 解析参数并进行验签处理，验签成功返回接收的参数
            boolean checkSign = ERPParamUtil.checkInfo(JSONObject.toJSON(syncStockForErpRequest).toString(), drugsShopInfo.getAppSecret());
            if (checkSign) {
                log.info("中台同步药品库存");

            } else {
                // 验签失败
                return Result.newFailureResult(new CommonBizException(ExpCodeEnum.SIGN_FAIL));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Result.newFailureResult(new CommonBizException(ExpCodeEnum.SYS_ERROR));
        }
        return Result.newSuccessResult("");
    }

    /***
     * @Author: suntf
     * @Description:退货款状态同步
     * @Date: 2019/8/6
     * @param syncRefundStatusRequest:
     * @return: com.hlsofttech.rsp.Result
     **/
    @AuthPower(avoidSign = false, avoidLogin = true)
    @ApiOperation(value = "ERP系统对接-退货款状态同步", notes = "ERP系统对接-退货款状态同步", httpMethod = "POST")
    @PostMapping("/api/erpApi/refund/syncStatus")
    public Result syncRefundStatus(@RequestBody SyncRefundStatusRequest syncRefundStatusRequest) {
        DrugsShopInfo drugsShopInfo = drugsShopInfoService.getByAppKey(syncRefundStatusRequest.getAppkey());
        if (drugsShopInfo == null) {
            // appkey不存在
            return Result.newFailureResult(new CommonBizException(ExpCodeEnum.SECRET_FAIL));
        }
        try {
            // 解析参数并进行验签处理，验签成功返回接收的参数
            boolean checkSign = ERPParamUtil.checkInfo(JSONObject.toJSON(syncRefundStatusRequest).toString(), drugsShopInfo.getAppSecret());
            if (checkSign) {
                log.info("中台同步退货款状态");

            } else {
                // 验签失败
                return Result.newFailureResult(new CommonBizException(ExpCodeEnum.SIGN_FAIL));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Result.newFailureResult(new CommonBizException(ExpCodeEnum.SYS_ERROR));
        }
        return Result.newSuccessResult("");
    }
}

