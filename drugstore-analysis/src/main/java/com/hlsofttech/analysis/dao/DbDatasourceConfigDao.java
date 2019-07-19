package com.hlsofttech.analysis.dao;
import com.hlsofttech.entity.analysis.DbDatasourceConfig;

import org.apache.ibatis.annotations.Mapper;

import com.github.pagehelper.PageInfo;
import com.hlsofttech.base.BaseMappers;

/**
  * 数据源配置Mapper接口
 * @author zuoqb123
 * @date 2019-03-13
 */
 @Mapper
public interface DbDatasourceConfigDao extends BaseMappers<DbDatasourceConfig> {
 /**
     * @date   2019年3月8日10:53:29
     * @author zuoqb123
     * @todo   字典新增或者修改
     */
	boolean saveOrUpdate(DbDatasourceConfig entity);
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
	DbDatasourceConfig getById(String id);
	
	/**
     * @date   2019年3月8日10:53:29
     * @author zuoqb123
     * @todo   字典分页查询
     */
	PageInfo<DbDatasourceConfig> pageList(DbDatasourceConfig entity,Integer pageNum,Integer pageSize);
}


