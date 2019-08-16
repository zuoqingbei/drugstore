package com.hlsofttech.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.bjucloud.common.ExecuteResult;
import com.bjucloud.goodscenter.dto.ItemDTO;
import com.bjucloud.goodscenter.service.ItemExportService;
import com.bjucloud.tradecenter.service.TradeOrderExportService;
import com.hlsofttech.common.Constant;
import com.hlsofttech.entity.product.DrugsInfo;
import com.hlsofttech.entity.vo.DrugsAddRequest;
import com.hlsofttech.entity.vo.DrugsAddVO;
import com.hlsofttech.entity.vo.SyncRefundStatusVO;
import com.hlsofttech.entity.vo.SyncStockForErpVO;
import com.hlsofttech.exception.CommonBizException;
import com.hlsofttech.exception.ExpCodeEnum;
import com.hlsofttech.rsp.Result;
import com.hlsofttech.service.ErpService;
import com.hlsofttech.service.product.DrugsInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class ErpServiceImpl implements ErpService {

    @Reference(version = Constant.VERSION, timeout = Constant.TIMEOUT)
    private DrugsInfoService drugsInfoService;

    @Reference(version = Constant.VERSION_ZT, timeout = Constant.TIMEOUT)
    private ItemExportService itemExportService;

    @Reference(version = Constant.VERSION_ZT, timeout = Constant.TIMEOUT)
    TradeOrderExportService tradeOrderExportService;

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

    @Override
    public Map<String, Integer> drugsStockSyn(List<SyncStockForErpVO> syncStockForErpVOS) {
        int success = 0;
        int fail = 0;
        Map<String, Integer> ret = new HashMap<>(2);
        for (SyncStockForErpVO stockForErpVO : syncStockForErpVOS) {
            ItemDTO itemDTO = new ItemDTO();
//            TODO 中台提供商品关联字段---药品批准文号
            itemDTO.setInventory(stockForErpVO.getStock());
            ExecuteResult<ItemDTO> result = itemExportService.modifyItemById(itemDTO);
            if (result.isSuccess() && result.getResult() != null) {
                success += 1;
            } else {
                fail += 1;
            }
        }
        if (fail > 0) {
            log.warn("药品库存同步结果：success【" + success + "】个，fail【" + fail + "】个");
        }
        ret.put("success", success);
        ret.put("fail", fail);
        return ret;
    }

    @Override
    public Boolean drugsRefundResult(SyncRefundStatusVO refundStatusVO) {
//        TODO 待中台提供退货款服务
        tradeOrderExportService.modifyOrderStatus(1L,1);
        return null;
    }
}
