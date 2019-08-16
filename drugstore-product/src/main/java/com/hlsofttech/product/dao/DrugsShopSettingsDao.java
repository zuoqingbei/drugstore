package com.hlsofttech.product.dao;

import com.github.pagehelper.PageInfo;
import com.hlsofttech.base.BaseMappers;
import com.hlsofttech.entity.shop.DrugsShopSettings;
import org.apache.ibatis.annotations.Mapper;

/**
 * 药店-相关设置Mapper接口
 *
 * @author suncy
 * @date 2019-08-16
 */
@Mapper
public interface DrugsShopSettingsDao extends BaseMappers<DrugsShopSettings> {
    /**
     * @date 2019年3月8日10:53:29
     * @author zuoqb123
     * @todo 字典新增或者修改
     */
    boolean saveOrUpdate(DrugsShopSettings entity);

    /**
     * @date 2019年3月8日10:53:29
     * @author zuoqb123
     * @todo 字典逻辑删除
     */
    boolean deleteLogic(String id);

    /**
     * @date 2018-10-11
     * @author zuoqb123
     * @todo 字典单条数据查询
     */
    DrugsShopSettings getById(String id);

    /**
     * @date 2019年3月8日10:53:29
     * @author zuoqb123
     * @todo 字典分页查询
     */
    PageInfo<DrugsShopSettings> pageList(DrugsShopSettings entity, Integer pageNum, Integer pageSize);
}


