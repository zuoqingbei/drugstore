package com.hlsofttech.service.shop;

import com.github.pagehelper.PageInfo;
import com.hlsofttech.entity.shop.DrugsShopDeliveryScope;

/**
 * @author suncy
 * 药店-配送范围服务类
 * @date 2019-08-16
 */
public interface DrugsShopDeliveryScopeService {


    /**
     * @date 2019-08-16
     * @author suncy
     * @todo 药店-配送范围新增或者修改
     */
    DrugsShopDeliveryScope saveOrUpdate(DrugsShopDeliveryScope entity);

    /**
     * @date 2019-08-16
     * @author suncy
     * @todo 药店-配送范围逻辑删除
     */
    boolean deleteLogic(String id);

    /**
     * @date 2018-10-11
     * @author suncy
     * @todo 药店-配送范围单条数据查询
     */
    DrugsShopDeliveryScope getById(String id);

    /**
     * @date 2019-08-16
     * @author suncy
     * @todo 药店-配送范围分页查询
     */
    PageInfo<DrugsShopDeliveryScope> pageList(DrugsShopDeliveryScope entity, Integer pageNum, Integer pageSize);


}
