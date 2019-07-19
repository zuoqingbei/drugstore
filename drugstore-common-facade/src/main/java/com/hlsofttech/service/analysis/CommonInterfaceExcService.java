package com.hlsofttech.service.analysis;

import com.github.pagehelper.PageInfo;
import com.hlsofttech.common.PublicResult;
import com.hlsofttech.entity.analysis.CommonInterfaceExc;
import com.hlsofttech.req.analysis.CommonInterReq;

/**
 * @date 2019-03-13
 * @author zuoqb123
 * 统一接口服务类
 */
public interface CommonInterfaceExcService{
	
	/**
	 * 
	 * @time   2018年9月13日 上午10:39:24
	 * @author zuoqb
	 * @todo   根据数据源与SQL查询数据 或者查询当前库中SQL结果
	 * isVertical-数据格式，true-垂直 false-横向数据
	 */
	PublicResult<?> getBySql(CommonInterReq inter);
	
	/**
	 * @time   2018年9月27日 上午9:13:27
	 * @author zuoqb
	 * @todo   统一接口查询数据
	 * 平台-数据源-接口
	 * @param dataType-接口类型  params-动态参数 格式 time::20180731;;cbkCode::BD1011001,,startIndex::1;;pageSize::10
	 * SQL中不要带“;” 否则会报错
	 */
	PublicResult<?> getByDataType(CommonInterReq inter);
	
	 /**
	 * @time   2018年9月27日 上午9:13:27
	 * @author zuoqb
	 * @todo   统一接口插入数据
	 * 平台-数据源-接口
	 * @param dataType-接口类型  params-动态参数 格式 time::20180731;;cbkCode::BD1011001,,startIndex::1;;pageSize::10
	 * SQL中不要带“;” 否则会报错
	 */
	PublicResult<?> insertDate(CommonInterReq inter);
	

	 /**
     * @date   2019-03-13
     * @author zuoqb123
     * @todo   统一接口新增或者修改
     */
	boolean saveOrUpdate(CommonInterfaceExc entity);
	/**
     * @date   2019-03-13
     * @author zuoqb123
     * @todo   统一接口逻辑删除
     */
	boolean deleteLogic(String id);
	
	/**
     * @date   2018-10-11
     * @author zuoqb123
     * @todo   统一接口单条数据查询
     */
	CommonInterfaceExc getById(String id);
	
	/**
     * @date   2019-03-13
     * @author zuoqb123
     * @todo   统一接口分页查询
     */
	PageInfo<CommonInterfaceExc> pageList(CommonInterfaceExc entity,Integer pageNum,Integer pageSize);


}
