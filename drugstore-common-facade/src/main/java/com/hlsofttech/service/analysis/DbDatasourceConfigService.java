package com.hlsofttech.service.analysis;

import com.hlsofttech.entity.analysis.DbDatasourceConfig;
import com.github.pagehelper.PageInfo;

/**
 * @date 2019-03-13
 * @author zuoqb123
 * 数据源配置服务类
 */
public interface DbDatasourceConfigService{


	 /**
     * @date   2019-03-13
     * @author zuoqb123
     * @todo   数据源配置新增或者修改
     */
	boolean saveOrUpdate(DbDatasourceConfig entity);
	/**
     * @date   2019-03-13
     * @author zuoqb123
     * @todo   数据源配置逻辑删除
     */
	boolean deleteLogic(String id);
	
	/**
     * @date   2018-10-11
     * @author zuoqb123
     * @todo   数据源配置单条数据查询
     */
	DbDatasourceConfig getById(String id);
	
	/**
     * @date   2019-03-13
     * @author zuoqb123
     * @todo   数据源配置分页查询
     */
	PageInfo<DbDatasourceConfig> pageList(DbDatasourceConfig entity,Integer pageNum,Integer pageSize);
	
	


}
