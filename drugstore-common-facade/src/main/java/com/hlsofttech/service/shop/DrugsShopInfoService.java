package com.hlsofttech.service.shop;

import com.github.pagehelper.PageInfo;
import com.hlsofttech.entity.shop.DrugsShopInfo;

/**
 * @author suntf123
 * 药店签约信息服务类
 * @date 2019-08-12
 */
public interface DrugsShopInfoService {


    /**
     * @date 2019-08-12
     * @author suntf123
     * @todo 药店签约信息新增或者修改
     */
    DrugsShopInfo saveOrUpdate(DrugsShopInfo entity);

    /**
     * @date 2019-08-12
     * @author suntf123
     * @todo 药店签约信息逻辑删除
     */
    boolean deleteLogic(String id);

    /**
     * @date 2018-10-11
     * @author suntf123
     * @todo 药店签约信息单条数据查询
     */
    DrugsShopInfo getById(String id);

    /**
     * @date 2019-08-12
     * @author suntf123
     * @todo 药店签约信息分页查询
     */
    PageInfo<DrugsShopInfo> pageList(DrugsShopInfo entity, Integer pageNum, Integer pageSize);

    /**
     * @date 2019-08-12
     * @author suntf123
     * @todo 根据appkey获取店铺信息
     */
    DrugsShopInfo getByAppKey(String appKey);

}
