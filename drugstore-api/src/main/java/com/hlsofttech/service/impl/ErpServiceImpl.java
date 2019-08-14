package com.hlsofttech.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.bjucloud.goodscenter.dto.ItemDTO;
import com.bjucloud.goodscenter.service.ItemExportService;
import com.hlsofttech.common.Constant;
import com.hlsofttech.entity.product.DrugsInfo;
import com.hlsofttech.entity.vo.DrugsAddRequest;
import com.hlsofttech.entity.vo.DrugsAddVO;
import com.hlsofttech.exception.CommonBizException;
import com.hlsofttech.exception.ExpCodeEnum;
import com.hlsofttech.rsp.Result;
import com.hlsofttech.service.ErpService;
import com.hlsofttech.service.product.DrugsInfoService;
import com.hlsofttech.service.shop.DrugsShopInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
public class ErpServiceImpl implements ErpService {

    @Reference(version = Constant.VERSION, group = "com.hlsofttech.product", timeout = Constant.TIMEOUT)
    private DrugsInfoService drugsInfoService;

    @Reference(version = Constant.VERSION, group = "com.bjucloud.goodscenter", timeout = Constant.TIMEOUT)
    private ItemExportService itemExportService;


    @Override
    public Result drugsSyn(DrugsAddRequest drugsAddRequest) {
        List<DrugsAddVO> data = drugsAddRequest.getData();
        if (data == null || data.size() == 0) {
            log.error("传来的data为空");
            return Result.newFailureResult(new CommonBizException(ExpCodeEnum.PARAM_NULL));
        }

        // 药品库中不存在的药品列表
        List<DrugsAddVO> dataNotIn = new ArrayList<>();

        // 查询所有的药品列表，然后比较两个list
        List<DrugsInfo> allList = drugsInfoService.findList(new DrugsInfo());
        if (allList != null && allList.size() > 0) {

            Set<String> approvalNumberSet = new HashSet<>();
            for (DrugsInfo drugsInfo : allList) {
                approvalNumberSet.add(drugsInfo.getApprovalNumber());
            }

            for (DrugsAddVO drugsAddVO : data) {
                // 存在当前药品库中
                if (approvalNumberSet.contains(drugsAddVO.getDrugCode())) {

                    // TODO: 2019/8/14 goodscenter.itemExportService.addItemInfo(ItemDTO itemDTO)  保存商品基础信息
                    ItemDTO itemDTO = new ItemDTO();
                    itemDTO.setAd("");
                    itemExportService.addItemInfo(itemDTO);

                    // TODO: 2019/8/14 goodscenter.itemPriceService.insertItemSellPrices(itemInfoDTO) 保存商品价格

                    // TODO: 2019/8/14 goodscenter.itemPriceService.insertItemSkuSellPrices(skuInfoDTO, itemInfoDTO);保存sku商品价格

                }
                // 不存在药品库中
                else {
                    log.error("当前药品库中不存在【" + drugsAddVO.getDrugCode() + "】【" + drugsAddVO.getName() + "】");
                    dataNotIn.add(drugsAddVO);
                }
            }
        }

        return Result.newSuccessResult(dataNotIn);
    }
}
