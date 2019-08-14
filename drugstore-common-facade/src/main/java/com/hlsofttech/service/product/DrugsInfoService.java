package com.hlsofttech.service.product;

import com.hlsofttech.entity.product.DrugsInfo;
import com.github.pagehelper.PageInfo;

/**
 * @date 2019-08-14
 * @author suncy
 * 药品库-药品信息表服务类
 */
public interface DrugsInfoService{


	 /**
     * @date   2019-08-14
     * @author suncy
     * @todo   药品库-药品信息表新增或者修改
     */
	DrugsInfo saveOrUpdate(DrugsInfo entity);
	/**
     * @date   2019-08-14
     * @author suncy
     * @todo   药品库-药品信息表逻辑删除
     */
	boolean deleteLogic(String id);
	
	/**
     * @date   2018-10-11
     * @author suncy
     * @todo   药品库-药品信息表单条数据查询
     */
	DrugsInfo getById(String id);
	
	/**
     * @date   2019-08-14
     * @author suncy
     * @todo   药品库-药品信息表分页查询
     */
	PageInfo<DrugsInfo> pageList(DrugsInfo entity, Integer pageNum, Integer pageSize);
	
	


}
