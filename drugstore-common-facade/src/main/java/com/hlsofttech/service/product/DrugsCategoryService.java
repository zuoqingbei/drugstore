package com.hlsofttech.service.product;

import com.hlsofttech.entity.product.DrugsCategory;
import com.github.pagehelper.PageInfo;

/**
 * @date 2019-08-02
 * @author suncy
 * 药品库-药品分类服务类
 */
public interface DrugsCategoryService{


	 /**
     * @date   2019-08-02
     * @author suncy
     * @todo   药品库-药品分类新增或者修改
     */
	DrugsCategory saveOrUpdate(DrugsCategory entity);
	/**
     * @date   2019-08-02
     * @author suncy
     * @todo   药品库-药品分类逻辑删除
     */
	boolean deleteLogic(String id);
	
	/**
     * @date   2018-10-11
     * @author suncy
     * @todo   药品库-药品分类单条数据查询
     */
	DrugsCategory getById(String id);
	
	/**
     * @date   2019-08-02
     * @author suncy
     * @todo   药品库-药品分类分页查询
     */
	PageInfo<DrugsCategory> pageList(DrugsCategory entity, Integer pageNum, Integer pageSize);
	
	


}
