package com.hlsofttech.product.dao;

import com.github.pagehelper.PageInfo;
import com.hlsofttech.base.BaseMappers;
import com.hlsofttech.entity.shop.DrugsShopInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * 药店签约信息Mapper接口
 *
 * @author suntf123
 * @date 2019-08-12
 */
@Mapper
public interface DrugsShopInfoDao extends BaseMappers<DrugsShopInfo> {
    /**
     * @date 2019年3月8日10:53:29
     * @author suncy
     * @todo 字典新增或者修改
     */
    boolean saveOrUpdate(DrugsShopInfo entity);

    /**
     * @date 2019年3月8日10:53:29
     * @author suncy
     * @todo 字典逻辑删除
     */
    boolean deleteLogic(String id);

    /**
     * @date 2018-10-11
     * @author suncy
     * @todo 字典单条数据查询
     */
    DrugsShopInfo getById(String id);

    /**
     * @date 2019年3月8日10:53:29
     * @author suncy
     * @todo 字典分页查询
     */
    PageInfo<DrugsShopInfo> pageList(DrugsShopInfo entity, Integer pageNum, Integer pageSize);
}


