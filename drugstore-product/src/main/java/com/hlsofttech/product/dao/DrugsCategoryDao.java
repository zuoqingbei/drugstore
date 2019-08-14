package com.hlsofttech.product.dao;
import com.hlsofttech.entity.product.DrugsCategory;
import org.apache.ibatis.annotations.Mapper;

import com.github.pagehelper.PageInfo;
import com.hlsofttech.base.BaseMappers;

/**
  * 药品库-药品分类Mapper接口
 * @author suncy
 * @date 2019-08-02
 */
 @Mapper
public interface DrugsCategoryDao extends BaseMappers<DrugsCategory> {
 /**
     * @date   2019年3月8日10:53:29
     * @author suncy
     * @todo   字典新增或者修改
     */
	boolean saveOrUpdate(DrugsCategory entity);
	/**
     * @date   2019年3月8日10:53:29
     * @author suncy
     * @todo   字典逻辑删除
     */
	boolean deleteLogic(String id);
	
	/**
     * @date   2018-10-11
     * @author suncy
     * @todo   字典单条数据查询
     */
	DrugsCategory getById(String id);
	
	/**
     * @date   2019年3月8日10:53:29
     * @author suncy
     * @todo   字典分页查询
     */
	PageInfo<DrugsCategory> pageList(DrugsCategory entity, Integer pageNum, Integer pageSize);
}


