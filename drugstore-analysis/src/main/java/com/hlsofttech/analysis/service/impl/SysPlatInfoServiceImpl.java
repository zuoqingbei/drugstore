package com.hlsofttech.analysis.service.impl;

import com.hlsofttech.entity.analysis.SysPlatInfo;
import com.hlsofttech.service.analysis.SysPlatInfoService;
import com.alibaba.dubbo.config.annotation.Service;

import org.springframework.beans.factory.annotation.Autowired;

import com.hlsofttech.utils.RandPwdUtil;
import com.hlsofttech.utils.UUIDUtils;
import com.hlsofttech.analysis.dao.SysPlatInfoDao;
import com.hlsofttech.common.Constant;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
/**
 * @date 2019-03-25
 * @author zuoqb123
 * 平台信息服务实现类
 */
@Service(version = Constant.VERSION,group="com.hlsofttech.middleground")
@org.springframework.stereotype.Service
public class SysPlatInfoServiceImpl implements SysPlatInfoService,Constant {

    @Autowired
    private SysPlatInfoDao sysPlatInfoDao;
    
     /**
     * @date 2019-03-25
     * @author zuoqb123
     * @todo   平台信息新增或者修改
     */
    @Override
	public boolean saveOrUpdate(SysPlatInfo entity) {
		if(StringUtils.isBlank(entity.getId())){
			//新增
			entity.setId(UUIDUtils.getUuid());
			entity.setCreateDate(new Date());
			entity.setAppKey(UUIDUtils.getUuid());
			entity.setSecretKey(RandPwdUtil.genRandPwd(24));
			return sysPlatInfoDao.insert(entity)>0;
		}else{
			entity.setUpdateDate(new Date());
			return sysPlatInfoDao.updateById(entity)>0;
		}
	}

	/**
     * @date 2019-03-25
     * @author zuoqb123
     * @todo   平台信息逻辑删除
     */
	@Override
	public boolean deleteLogic(String id) {
		SysPlatInfo entity=new SysPlatInfo();
		entity.setId(id);
		entity.setDelFlag(DEL_FLAG);
		entity.setUpdateDate(new Date());
		return sysPlatInfoDao.updateById(entity)>0;
	}

	/**
     * @date 2019-03-25
     * @author zuoqb123
     * @todo   平台信息单条数据查询
     */
	@Override
	public SysPlatInfo getById(String id) {
		return sysPlatInfoDao.selectById(id);
	}

	/**
     * @date   @date 2019-03-25
     * @author zuoqb123
     * @todo   平台信息分页查询
     */
	@Override
	public PageInfo<SysPlatInfo> pageList(SysPlatInfo entity, Integer pageNum,
			Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		EntityWrapper<SysPlatInfo> wrapper = searchWrapper(entity);
		List<SysPlatInfo> list = sysPlatInfoDao.selectList(wrapper);
		PageInfo<SysPlatInfo> page = new PageInfo<SysPlatInfo>(list);
		return page;
	}
    
	
	 /**
     * @date 2019-03-25
     * @author zuoqb123
     * @todo   平台信息构建查询条件-以后扩展
     */
    private EntityWrapper<SysPlatInfo> searchWrapper(SysPlatInfo entity) {
		EntityWrapper<SysPlatInfo> wrapper = new EntityWrapper<SysPlatInfo>();
		wrapper.where("del_flag={0}", UN_DEL_FLAG);
		//根据平台编号模糊查询
		if(entity.getId()!=null&&StringUtils.isNotBlank(String.valueOf(entity.getId()))){
			wrapper.like("id", String.valueOf(entity.getId()));
		}
		//根据平台名称模糊查询
		if(entity.getName()!=null&&StringUtils.isNotBlank(String.valueOf(entity.getName()))){
			wrapper.like("name", String.valueOf(entity.getName()));
		}
		//根据平台英文名称模糊查询
		if(entity.getEnname()!=null&&StringUtils.isNotBlank(String.valueOf(entity.getEnname()))){
			wrapper.like("enname", String.valueOf(entity.getEnname()));
		}
		//根据接口版本模糊查询
		if(entity.getVersions()!=null&&StringUtils.isNotBlank(String.valueOf(entity.getVersions()))){
			wrapper.like("versions", String.valueOf(entity.getVersions()));
		}
		//根据应用说明模糊查询
		if(entity.getDescription()!=null&&StringUtils.isNotBlank(String.valueOf(entity.getDescription()))){
			wrapper.like("description", String.valueOf(entity.getDescription()));
		}
		//根据状态模糊查询
		if(entity.getStatus()!=null&&StringUtils.isNotBlank(String.valueOf(entity.getStatus()))){
			wrapper.like("status", String.valueOf(entity.getStatus()));
		}
		//根据应用key模糊查询
		if(entity.getAppKey()!=null&&StringUtils.isNotBlank(String.valueOf(entity.getAppKey()))){
			wrapper.like("app_key", String.valueOf(entity.getAppKey()));
		}
		//根据平台秘钥 X-Sign模糊查询
		if(entity.getSecretKey()!=null&&StringUtils.isNotBlank(String.valueOf(entity.getSecretKey()))){
			wrapper.like("secret_key", String.valueOf(entity.getSecretKey()));
		}
		//根据平台联系人模糊查询
		if(entity.getContacts()!=null&&StringUtils.isNotBlank(String.valueOf(entity.getContacts()))){
			wrapper.like("contacts", String.valueOf(entity.getContacts()));
		}
		//根据联系人电话模糊查询
		if(entity.getContactsTel()!=null&&StringUtils.isNotBlank(String.valueOf(entity.getContactsTel()))){
			wrapper.like("contacts_tel", String.valueOf(entity.getContactsTel()));
		}
		//根据联系人邮箱模糊查询
		if(entity.getContactsMail()!=null&&StringUtils.isNotBlank(String.valueOf(entity.getContactsMail()))){
			wrapper.like("contacts_mail", String.valueOf(entity.getContactsMail()));
		}
		//根据创建者模糊查询
		if(entity.getCreateBy()!=null&&StringUtils.isNotBlank(String.valueOf(entity.getCreateBy()))){
			wrapper.like("create_by", String.valueOf(entity.getCreateBy()));
		}
		//根据创建时间模糊查询
		if(entity.getCreateDate()!=null&&StringUtils.isNotBlank(String.valueOf(entity.getCreateDate()))){
			wrapper.like("create_date", String.valueOf(entity.getCreateDate()));
		}
		//根据更新者模糊查询
		if(entity.getUpdateBy()!=null&&StringUtils.isNotBlank(String.valueOf(entity.getUpdateBy()))){
			wrapper.like("update_by", String.valueOf(entity.getUpdateBy()));
		}
		//根据更新时间模糊查询
		if(entity.getUpdateDate()!=null&&StringUtils.isNotBlank(String.valueOf(entity.getUpdateDate()))){
			wrapper.like("update_date", String.valueOf(entity.getUpdateDate()));
		}
		//根据备注信息模糊查询
		if(entity.getRemarks()!=null&&StringUtils.isNotBlank(String.valueOf(entity.getRemarks()))){
			wrapper.like("remarks", String.valueOf(entity.getRemarks()));
		}
		//根据删除标记模糊查询
		if(entity.getDelFlag()!=null&&StringUtils.isNotBlank(String.valueOf(entity.getDelFlag()))){
			wrapper.like("del_flag", String.valueOf(entity.getDelFlag()));
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
