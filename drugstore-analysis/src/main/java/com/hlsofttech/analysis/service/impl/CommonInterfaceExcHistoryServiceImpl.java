package com.hlsofttech.analysis.service.impl;

import com.hlsofttech.entity.analysis.CommonInterfaceExcHistory;
import com.hlsofttech.service.analysis.CommonInterfaceExcHistoryService;
import com.alibaba.dubbo.config.annotation.Service;

import org.springframework.beans.factory.annotation.Autowired;

import com.hlsofttech.utils.UUIDUtils;
import com.hlsofttech.analysis.dao.CommonInterfaceExcHistoryDao;
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
public class CommonInterfaceExcHistoryServiceImpl implements CommonInterfaceExcHistoryService,Constant {

    @Autowired
    private CommonInterfaceExcHistoryDao commonInterfaceExcHistoryDao;
    
     /**
     * @date 2019-03-14
     * @author zuoqb123
     * @todo   新增或者修改
     */
    @Override
	public boolean saveOrUpdate(CommonInterfaceExcHistory entity) {
		if(StringUtils.isBlank(entity.getId())){
			//新增
			entity.setId(UUIDUtils.getUuid());
			entity.setCreateDate(new Date());
			return commonInterfaceExcHistoryDao.insert(entity)>0;
		}else{
			entity.setUpdateDate(new Date());
			return commonInterfaceExcHistoryDao.updateById(entity)>0;
		}
	}

	/**
     * @date 2019-03-14
     * @author zuoqb123
     * @todo   逻辑删除
     */
	@Override
	public boolean deleteLogic(String id) {
		CommonInterfaceExcHistory entity=new CommonInterfaceExcHistory();
		entity.setId(id);
		entity.setDelFlag(DEL_FLAG);
		entity.setUpdateDate(new Date());
		return commonInterfaceExcHistoryDao.updateById(entity)>0;
	}

	/**
     * @date 2019-03-14
     * @author zuoqb123
     * @todo   单条数据查询
     */
	@Override
	public CommonInterfaceExcHistory getById(String id) {
		return commonInterfaceExcHistoryDao.selectById(id);
	}

	/**
     * @date   @date 2019-03-14
     * @author zuoqb123
     * @todo   分页查询
     */
	@Override
	public PageInfo<CommonInterfaceExcHistory> pageList(CommonInterfaceExcHistory entity, Integer pageNum,
			Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		EntityWrapper<CommonInterfaceExcHistory> wrapper = searchWrapper(entity);
		List<CommonInterfaceExcHistory> list = commonInterfaceExcHistoryDao.selectList(wrapper);
		PageInfo<CommonInterfaceExcHistory> page = new PageInfo<CommonInterfaceExcHistory>(list);
		return page;
	}
    
	
	 /**
     * @date 2019-03-14
     * @author zuoqb123
     * @todo   构建查询条件-以后扩展
     */
    private EntityWrapper<CommonInterfaceExcHistory> searchWrapper(CommonInterfaceExcHistory entity) {
		EntityWrapper<CommonInterfaceExcHistory> wrapper = new EntityWrapper<CommonInterfaceExcHistory>();
		wrapper.where("del_flag={0}", UN_DEL_FLAG);
		//根据模糊查询
		if(entity.getId()!=null&&StringUtils.isNotBlank(String.valueOf(entity.getId()))){
			wrapper.like("id", String.valueOf(entity.getId()));
		}
		//根据接口编码模糊查询
		if(entity.getDataExcId()!=null&&StringUtils.isNotBlank(String.valueOf(entity.getDataExcId()))){
			wrapper.like("data_exc_id", String.valueOf(entity.getDataExcId()));
		}
		//根据查询指标标识模糊查询
		if(entity.getDataType()!=null&&StringUtils.isNotBlank(String.valueOf(entity.getDataType()))){
			wrapper.like("data_type", String.valueOf(entity.getDataType()));
		}
		//根据命名空间模糊查询
		if(entity.getDataSpace()!=null&&StringUtils.isNotBlank(String.valueOf(entity.getDataSpace()))){
			wrapper.like("data_space", String.valueOf(entity.getDataSpace()));
		}
		//根据需要执行的sql语句，需要传参的位置使用#{参数名}，动态日期类型date_dt_kpi固定参数名称。模糊查询
		if(entity.getDataSql()!=null&&StringUtils.isNotBlank(String.valueOf(entity.getDataSql()))){
			wrapper.like("data_sql", String.valueOf(entity.getDataSql()));
		}
		//根据sql需要传入的参数，如果需要动态日期date_dt_kpi无需配置模糊查询
		if(entity.getParamId()!=null&&StringUtils.isNotBlank(String.valueOf(entity.getParamId()))){
			wrapper.like("param_id", String.valueOf(entity.getParamId()));
		}
		//根据需要历史记录放入缓存的开始时间,根据更新类型（cache_type）模糊查询
		if(entity.getBeginDate()!=null&&StringUtils.isNotBlank(String.valueOf(entity.getBeginDate()))){
			wrapper.like("begin_date", String.valueOf(entity.getBeginDate()));
		}
		//根据需要查询的时间格式，例如：yyyyMMdd模糊查询
		if(entity.getDateFormat()!=null&&StringUtils.isNotBlank(String.valueOf(entity.getDateFormat()))){
			wrapper.like("date_format", String.valueOf(entity.getDateFormat()));
		}
		//根据是否需要定时刷新的标识,0不刷新，1刷新模糊查询
		if(entity.getFreshFlag()!=null&&StringUtils.isNotBlank(String.valueOf(entity.getFreshFlag()))){
			wrapper.like("fresh_flag", String.valueOf(entity.getFreshFlag()));
		}
		//根据数据缓存天数，根据更新类型（cache_type）判断模糊查询
		if(entity.getUpdateDays()!=null&&StringUtils.isNotBlank(String.valueOf(entity.getUpdateDays()))){
			wrapper.like("update_days", String.valueOf(entity.getUpdateDays()));
		}
		//根据定任务执行时间偏移量，默认执行时间减一天。注：该设置只对定时任务有效。模糊查询
		if(entity.getTimerOffset()!=null&&StringUtils.isNotBlank(String.valueOf(entity.getTimerOffset()))){
			wrapper.like("timer_offset", String.valueOf(entity.getTimerOffset()));
		}
		//根据Cron表达式是一个字符串配置定时任务执行时间模糊查询
		if(entity.getExcTime()!=null&&StringUtils.isNotBlank(String.valueOf(entity.getExcTime()))){
			wrapper.like("exc_time", String.valueOf(entity.getExcTime()));
		}
		//根据放入缓存的方式，按天数存放（0），或者按开始时间存放（1）模糊查询
		if(entity.getCacheType()!=null&&StringUtils.isNotBlank(String.valueOf(entity.getCacheType()))){
			wrapper.like("cache_type", String.valueOf(entity.getCacheType()));
		}
		//根据横竖数据格式转换，默认纵向排列（V：垂直排列，H水平排列）模糊查询
		if(entity.getTransformData()!=null&&StringUtils.isNotBlank(String.valueOf(entity.getTransformData()))){
			wrapper.like("transform_data", String.valueOf(entity.getTransformData()));
		}
		//根据创建时间模糊查询
		if(entity.getCreateTime()!=null&&StringUtils.isNotBlank(String.valueOf(entity.getCreateTime()))){
			wrapper.like("create_time", String.valueOf(entity.getCreateTime()));
		}
		//根据最后更新主体模糊查询
		if(entity.getLastUpdateBy()!=null&&StringUtils.isNotBlank(String.valueOf(entity.getLastUpdateBy()))){
			wrapper.like("last_update_by", String.valueOf(entity.getLastUpdateBy()));
		}
		//根据最后更新时间模糊查询
		if(entity.getLastUpdateTime()!=null&&StringUtils.isNotBlank(String.valueOf(entity.getLastUpdateTime()))){
			wrapper.like("last_update_time", String.valueOf(entity.getLastUpdateTime()));
		}
		//根据备注模糊查询
		if(entity.getRemark()!=null&&StringUtils.isNotBlank(String.valueOf(entity.getRemark()))){
			wrapper.like("remark", String.valueOf(entity.getRemark()));
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
