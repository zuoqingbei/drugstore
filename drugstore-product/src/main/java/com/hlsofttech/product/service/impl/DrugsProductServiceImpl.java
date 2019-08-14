package com.hlsofttech.product.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.bjucloud.common.Pager;
import com.bjucloud.goodscenter.dto.ItemQueryInDTO;
import com.bjucloud.goodscenter.service.ItemExportService;
import com.hlsofttech.service.product.DrugsProductService;

import java.util.List;

/***
 * @Author: suntf
 * @Description:药品商品服务
 * @Date: 2019/8/7
 **/
public class DrugsProductServiceImpl implements DrugsProductService {

    @Reference
    ItemExportService itemExportService;

    @Override
    public List ItemExportService() {
        ItemQueryInDTO queryInDTO = new ItemQueryInDTO();
        Pager pager = new Pager();
        return itemExportService.queryItemsList(queryInDTO, pager);
    }
}
