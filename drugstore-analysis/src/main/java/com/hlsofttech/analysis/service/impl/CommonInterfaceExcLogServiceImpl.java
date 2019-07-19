package com.hlsofttech.analysis.service.impl;

import com.hlsofttech.entity.analysis.CommonInterfaceExcLog;
import com.hlsofttech.service.analysis.CommonInterfaceExcLogService;
import com.alibaba.dubbo.config.annotation.Service;

import org.springframework.beans.factory.annotation.Autowired;

import com.hlsofttech.utils.UUIDUtils;
import com.hlsofttech.analysis.dao.CommonInterfaceExcLogDao;
import com.hlsofttech.common.Constant;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
/**
 * @date 2019-03-14
 * @author zuoqb123
 * 服务实现类
 */
@Service(version = Constant.VERSION,group="com.hlsofttech.middleground")
@org.springframework.stereotype.Service
public class CommonInterfaceExcLogServiceImpl implements CommonInterfaceExcLogService,Constant {

    @Autowired
    private CommonInterfaceExcLogDao commonInterfaceExcLogDao;
    
     /**
     * @date 2019-03-14
     * @author zuoqb123
     * @todo   新增或者修改
     */
    @Override
	public boolean saveOrUpdate(CommonInterfaceExcLog entity) {
		if(StringUtils.isBlank(entity.getId())){
			//新增
			entity.setId(UUIDUtils.getUuid());
			entity.setCreateDate(new Date());
			return commonInterfaceExcLogDao.insert(entity)>0;
		}else{
			entity.setUpdateDate(new Date());
			return commonInterfaceExcLogDao.updateById(entity)>0;
		}
	}

	/**
     * @date 2019-03-14
     * @author zuoqb123
     * @todo   逻辑删除
     */
	@Override
	public boolean deleteLogic(String id) {
		CommonInterfaceExcLog entity=new CommonInterfaceExcLog();
		entity.setId(id);
		entity.setDelFlag(DEL_FLAG);
		entity.setUpdateDate(new Date());
		return commonInterfaceExcLogDao.updateById(entity)>0;
	}

	/**
     * @date 2019-03-14
     * @author zuoqb123
     * @todo   单条数据查询
     */
	@Override
	public CommonInterfaceExcLog getById(String id) {
		return commonInterfaceExcLogDao.selectById(id);
	}

	/**
     * @date   @date 2019-03-14
     * @author zuoqb123
     * @todo   分页查询
     */
	@Override
	public PageInfo<CommonInterfaceExcLog> pageList(CommonInterfaceExcLog entity, Integer pageNum,
			Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		EntityWrapper<CommonInterfaceExcLog> wrapper = searchWrapper(entity);
		List<CommonInterfaceExcLog> list = commonInterfaceExcLogDao.selectList(wrapper);
		PageInfo<CommonInterfaceExcLog> page = new PageInfo<CommonInterfaceExcLog>(list);
		return page;
	}
    
	
	 /**
     * @date 2019-03-14
     * @author zuoqb123
     * @todo   构建查询条件-以后扩展
     */
    private EntityWrapper<CommonInterfaceExcLog> searchWrapper(CommonInterfaceExcLog entity) {
		EntityWrapper<CommonInterfaceExcLog> wrapper = new EntityWrapper<CommonInterfaceExcLog>();
		wrapper.where("del_flag={0}", UN_DEL_FLAG);
		//根据键模糊查询
		if(entity.getId()!=null&&StringUtils.isNotBlank(String.valueOf(entity.getId()))){
			wrapper.like("id", String.valueOf(entity.getId()));
		}
		//根据查询指标标识模糊查询
		if(entity.getDataType()!=null&&StringUtils.isNotBlank(String.valueOf(entity.getDataType()))){
			wrapper.like("data_type", String.valueOf(entity.getDataType()));
		}
		//根据命名空间模糊查询
		if(entity.getDataSpace()!=null&&StringUtils.isNotBlank(String.valueOf(entity.getDataSpace()))){
			wrapper.like("data_space", String.valueOf(entity.getDataSpace()));
		}
		//根据查询日期坐标模糊查询
		if(entity.getDateDt()!=null&&StringUtils.isNotBlank(String.valueOf(entity.getDateDt()))){
			wrapper.like("date_dt", String.valueOf(entity.getDateDt()));
		}
		//根据开始执行时间模糊查询
		if(entity.getBeginTime()!=null&&StringUtils.isNotBlank(String.valueOf(entity.getBeginTime()))){
			wrapper.like("begin_time", String.valueOf(entity.getBeginTime()));
		}
		//根据结束时间模糊查询
		if(entity.getEndTime()!=null&&StringUtils.isNotBlank(String.valueOf(entity.getEndTime()))){
			wrapper.like("end_time", String.valueOf(entity.getEndTime()));
		}
		//根据R执行中，F执行结果异常，S执行结果正常模糊查询
		if(entity.getExcStatus()!=null&&StringUtils.isNotBlank(String.valueOf(entity.getExcStatus()))){
			wrapper.like("exc_status", String.valueOf(entity.getExcStatus()));
		}
		//根据错误重复执行次数模糊查询
		if(entity.getErrorExcNum()!=null&&StringUtils.isNotBlank(String.valueOf(entity.getErrorExcNum()))){
			wrapper.like("error_exc_num", String.valueOf(entity.getErrorExcNum()));
		}
		//根据执行结果内容，以json格式保存模糊查询
		if(entity.getResult()!=null&&StringUtils.isNotBlank(String.valueOf(entity.getResult()))){
			wrapper.like("result", String.valueOf(entity.getResult()));
		}
		//根据缓存过期状态，1正在使用，0已过期(数据不准，不要作为执行依据)模糊查询
		if(entity.getCacheStatus()!=null&&StringUtils.isNotBlank(String.valueOf(entity.getCacheStatus()))){
			wrapper.like("cache_status", String.valueOf(entity.getCacheStatus()));
		}
		if(entity.getStartDate()!=null){
			wrapper.ge("create_date", entity.getStartDate());
		}
		if(entity.getEndDate()!=null){
			wrapper.le("create_date", entity.getEndDate());
		}
		if(StringUtils.isNotBlank(entity.getOrderBy())){
			wrapper.orderBy(entity.getOrderBy(), entity.isAsc());
		}else{
			wrapper.orderBy("create_date", false);
		}
		//System.out.println(wrapper.originalSql());
		return wrapper;
	}
   
}
