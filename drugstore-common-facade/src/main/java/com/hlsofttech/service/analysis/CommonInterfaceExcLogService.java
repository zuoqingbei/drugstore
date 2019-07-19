package com.hlsofttech.service.analysis;

import com.hlsofttech.entity.analysis.CommonInterfaceExcLog;
import com.github.pagehelper.PageInfo;

/**
 * @date 2019-03-13
 * @author zuoqb123
 * 服务类
 */
public interface CommonInterfaceExcLogService{


	 /**
     * @date   2019-03-13
     * @author zuoqb123
     * @todo   新增或者修改
     */
	boolean saveOrUpdate(CommonInterfaceExcLog entity);
	/**
     * @date   2019-03-13
     * @author zuoqb123
     * @todo   逻辑删除
     */
	boolean deleteLogic(String id);
	
	/**
     * @date   2018-10-11
     * @author zuoqb123
     * @todo   单条数据查询
     */
	CommonInterfaceExcLog getById(String id);
	
	/**
     * @date   2019-03-13
     * @author zuoqb123
     * @todo   分页查询
     */
	PageInfo<CommonInterfaceExcLog> pageList(CommonInterfaceExcLog entity,Integer pageNum,Integer pageSize);
	
	


}
