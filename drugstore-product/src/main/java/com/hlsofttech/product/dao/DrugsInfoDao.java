package com.hlsofttech.product.dao;
import com.hlsofttech.entity.product.DrugsInfo;
import org.apache.ibatis.annotations.Mapper;

import com.github.pagehelper.PageInfo;
import com.hlsofttech.base.BaseMappers;

/**
  * 药品库-药品信息表Mapper接口
 * @author suncy
 * @date 2019-08-14
 */
 @Mapper
public interface DrugsInfoDao extends BaseMappers<DrugsInfo> {
 /**
     * @date   2019年3月8日10:53:29
     * @author zuoqb123
     * @todo   字典新增或者修改
     */
	boolean saveOrUpdate(DrugsInfo entity);
	/**
     * @date   2019年3月8日10:53:29
     * @author zuoqb123
     * @todo   字典逻辑删除
     */
	boolean deleteLogic(String id);
	
	/**
     * @date   2018-10-11
     * @author zuoqb123
     * @todo   字典单条数据查询
     */
	DrugsInfo getById(String id);
	
	/**
     * @date   2019年3月8日10:53:29
     * @author zuoqb123
     * @todo   字典分页查询
     */
	PageInfo<DrugsInfo> pageList(DrugsInfo entity, Integer pageNum, Integer pageSize);
}


