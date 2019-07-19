package com.hlsofttech.analysis.dao;
import org.apache.ibatis.annotations.Mapper;

import com.github.pagehelper.PageInfo;
import com.hlsofttech.base.BaseMappers;
import com.hlsofttech.entity.analysis.CommonInterfaceExcHistory;

/**
  * Mapper接口
 * @author zuoqb123
 * @date 2019-03-13
 */
 @Mapper
public interface CommonInterfaceExcHistoryDao extends BaseMappers<CommonInterfaceExcHistory> {
 /**
     * @date   2019年3月8日10:53:29
     * @author zuoqb123
     * @todo   字典新增或者修改
     */
	boolean saveOrUpdate(CommonInterfaceExcHistory entity);
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
	CommonInterfaceExcHistory getById(String id);
	
	/**
     * @date   2019年3月8日10:53:29
     * @author zuoqb123
     * @todo   字典分页查询
     */
	PageInfo<CommonInterfaceExcHistory> pageList(CommonInterfaceExcHistory entity,Integer pageNum,Integer pageSize);
}


