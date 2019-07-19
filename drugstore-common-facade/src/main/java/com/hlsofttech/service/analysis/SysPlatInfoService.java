package com.hlsofttech.service.analysis;

import com.hlsofttech.entity.analysis.SysPlatInfo;
import com.github.pagehelper.PageInfo;

/**
 * @date 2019-03-25
 * @author zuoqb123
 * 平台信息服务类
 */
public interface SysPlatInfoService{


	 /**
     * @date   2019-03-25
     * @author zuoqb123
     * @todo   平台信息新增或者修改
     */
	boolean saveOrUpdate(SysPlatInfo entity);
	/**
     * @date   2019-03-25
     * @author zuoqb123
     * @todo   平台信息逻辑删除
     */
	boolean deleteLogic(String id);
	
	/**
     * @date   2018-10-11
     * @author zuoqb123
     * @todo   平台信息单条数据查询
     */
	SysPlatInfo getById(String id);
	
	/**
     * @date   2019-03-25
     * @author zuoqb123
     * @todo   平台信息分页查询
     */
	PageInfo<SysPlatInfo> pageList(SysPlatInfo entity,Integer pageNum,Integer pageSize);
	
	


}
