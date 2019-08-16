package com.hlsofttech.controller.shop;

import com.alibaba.dubbo.config.annotation.Reference;
import com.bjucloud.common.DataGrid;
import com.bjucloud.common.ExecuteResult;
import com.bjucloud.common.Pager;
import com.bjucloud.storecenter.dto.ShopCategorySellerDTO;
import com.bjucloud.storecenter.dto.ShopDTO;
import com.bjucloud.storecenter.service.ShopCategorySellerExportService;
import com.bjucloud.storecenter.service.ShopExportService;
import com.hlsofttech.annotation.AuthPower;
import com.hlsofttech.common.Constant;
import com.hlsofttech.entity.vo.ShopVO;
import com.hlsofttech.rsp.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/***
 * @Author: suntf
 * @Description:药店相关接口
 * @Date: 2019/8/15
 **/
@RestController
@Api(tags = "用户端-店铺", value = "用户端-店铺", description = "用户端-店铺 @author suntf")
public class DrugsShopController {

    @Reference(version = Constant.VERSION_ZT, timeout = Constant.TIMEOUT)
    ShopExportService shopExportService;

    @Reference(version = Constant.VERSION_ZT, timeout = Constant.TIMEOUT)
    ShopCategorySellerExportService shopCategorySellerExportService;

    /***
     * @Author: suntf
     * @Description:药店列表
     * @Date: 2019/8/15
     * @return: com.hlsofttech.rsp.Result
     **/
    @AuthPower(avoidVersion = false, avoidPower = true, avoidSign = true, avoidLogin = false, avoidPlatform = true)
    @ApiOperation(value = "药店列表", notes = "药店列表", httpMethod = "GET")
    @GetMapping("/api/drugsShop/list")
    public Result queryShopList() {

        ShopDTO shopDTO = new ShopDTO();
        shopDTO.setShopType(10);
        Pager<ShopDTO> pager = new Pager<>(1, 15);
        ExecuteResult<DataGrid<ShopDTO>> result = shopExportService.findShopInfoByCondition(shopDTO, pager);
        if (result.isSuccess() && result.getResult() != null) {
            DataGrid<ShopDTO> data = result.getResult();
            List<ShopDTO> list = data.getRows();
            // 返回对象
            DataGrid<ShopVO> retData = new DataGrid<>();
            retData.setPages(data.getPages());
            retData.setSize(data.getSize());

            List<ShopVO> retList = new ArrayList<>();
            // 对象转换
            for (ShopDTO shop : list) {
                ShopVO shopVO = new ShopVO();
                BeanUtils.copyProperties(shop, shopVO);
                retList.add(shopVO);
            }
            retData.setRows(retList);
            return Result.newSuccessResult(retData);
        } else {
            return null;
        }
    }
//
//    /***
//     * @Author: suntf
//     * @Description:根据id查询药店详细信息
//     * @Date: 2019/8/15
//     * @return: com.hlsofttech.rsp.Result
//     **/
//    @AuthPower(avoidVersion = false, avoidPower = true, avoidSign = true, avoidLogin = false, avoidPlatform = true)
//    @ApiOperation(value = "药店详情", notes = "药店详情", httpMethod = "GET")
//    @GetMapping("/api/drugsShop/info")
//    public Result queryShopInfo(Long shopId) {
//
//        ExecuteResult<ShopDTO> result = shopExportService.findShopInfoById(shopId);
//        if (result.isSuccess() && result.getResult() != null) {
//            return Result.newSuccessResult(result.getResult());
//        } else {
//            return null;
//        }
//    }


    /***
     * @Author: suntf
     * @Description:根据店铺id查询店铺内分类列表
     * @Date: 2019/8/15
     * @return: com.hlsofttech.rsp.Result
     **/
    @AuthPower(avoidVersion = false, avoidPower = true, avoidSign = true, avoidLogin = false, avoidPlatform = true)
    @ApiOperation(value = "店铺内分类列表", notes = "店铺内分类列表", httpMethod = "GET")
    @GetMapping("/api/drugsShop/category/list")
    public Result queryShopCategoryList(Long shopId, int lev) {
        ShopCategorySellerDTO shopCategorySellerDTO = new ShopCategorySellerDTO();
        shopCategorySellerDTO.setShopId(shopId);
        shopCategorySellerDTO.setLev(lev);

        Pager<ShopCategorySellerDTO> pager = new Pager<>(1, 99);
        ExecuteResult<DataGrid<ShopCategorySellerDTO>> result = shopCategorySellerExportService.queryShopCategoryList(shopCategorySellerDTO, pager);
        if (result.isSuccess() && result.getResult() != null) {
            return Result.newSuccessResult(result.getResult().getRows());
        } else {
            return null;
        }
    }

}
