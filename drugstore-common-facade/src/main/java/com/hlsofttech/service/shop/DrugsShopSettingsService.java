package com.hlsofttech.service.shop;

import com.github.pagehelper.PageInfo;
import com.hlsofttech.entity.shop.DrugsShopSettings;

/**
 * @author suncy
 * 药店-相关设置服务类
 * @date 2019-08-16
 */
public interface DrugsShopSettingsService {


    /**
     * @date 2019-08-16
     * @author suncy
     * @todo 药店-相关设置新增或者修改
     */
    DrugsShopSettings saveOrUpdate(DrugsShopSettings entity);

    /**
     * @date 2019-08-16
     * @author suncy
     * @todo 药店-相关设置逻辑删除
     */
    boolean deleteLogic(String id);

    /**
     * @date 2018-10-11
     * @author suncy
     * @todo 药店-相关设置单条数据查询
     */
    DrugsShopSettings getById(String id);

    /**
     * @date 2019-08-16
     * @author suncy
     * @todo 药店-相关设置分页查询
     */
    PageInfo<DrugsShopSettings> pageList(DrugsShopSettings entity, Integer pageNum, Integer pageSize);


    /**
     * @date 2018-10-11
     * @author suncy
     * @todo 药店-相关设置单条数据查询
     */
    DrugsShopSettings getByDrugsShopId(String drugsShopId);

}
