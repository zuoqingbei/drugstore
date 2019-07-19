package com.hlsofttech.service.analysis;

import com.hlsofttech.entity.analysis.SysPlatInterface;
import com.github.pagehelper.PageInfo;

/**
 * @date 2019-03-25
 * @author zuoqb123
 * 应用接口申请信息服务类
 */
public interface SysPlatInterfaceService{


	 /**
     * @date   2019-03-25
     * @author zuoqb123
     * @todo   应用接口申请信息新增或者修改
     */
  SysPlatInterface saveOrUpdate(SysPlatInterface entity);
	/**
     * @date   2019-03-25
     * @author zuoqb123
     * @todo   应用接口申请信息逻辑删除
     */
	boolean deleteLogic(String id);
	
	/**
     * @date   2018-10-11
     * @author zuoqb123
     * @todo   应用接口申请信息单条数据查询
     */
	SysPlatInterface getById(String id);
	
	/**
     * @date   2019-03-25
     * @author zuoqb123
     * @todo   应用接口申请信息分页查询
     */
	PageInfo<SysPlatInterface> pageList(SysPlatInterface entity,Integer pageNum,Integer pageSize);
	
	


}
