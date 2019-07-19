package com.hlsofttech.analysis.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hlsofttech.analysis.dao.SysPlatInfoDao;
import com.hlsofttech.analysis.dao.SysPlatInterfaceDao;
import com.hlsofttech.common.Constant;
import com.hlsofttech.entity.analysis.SysPlatInfo;
import com.hlsofttech.entity.analysis.SysPlatInterface;
import com.hlsofttech.service.analysis.SysPlatInterfaceService;
import com.hlsofttech.utils.ObjUtils;
import com.hlsofttech.utils.RandPwdUtil;
import com.hlsofttech.utils.UUIDUtils;
/**
 * @date 2019-03-25
 * @author zuoqb123
 * 应用接口申请信息服务实现类
 */
@Service(version = Constant.VERSION,group="com.hlsofttech.middleground")
@org.springframework.stereotype.Service
public class SysPlatInterfaceServiceImpl implements SysPlatInterfaceService,Constant {

    @Autowired
    private SysPlatInterfaceDao sysPlatInterfaceDao;
    @Autowired
    private SysPlatInfoDao sysPlatInfoDao;
    
     /**
     * @date 2019-03-25
     * @author zuoqb123
     * @todo   应用接口申请信息新增或者修改
     */
    @Override
	public SysPlatInterface saveOrUpdate(SysPlatInterface entity) {
      if (StringUtils.isBlank(entity.getId())) {
        // 新增
        EntityWrapper<SysPlatInfo> wrap = new EntityWrapper<SysPlatInfo>();
        wrap.where("del_flag={0}", UN_DEL_FLAG);
        wrap.eq("name", entity.getPlatName());
        wrap.eq("description", entity.getDescription());
        wrap.eq("create_by", entity.getCreateBy());
        List<SysPlatInfo> li = sysPlatInfoDao.selectList(wrap);
        if(ObjUtils.isEmpty(li)) {
          // 先保存平台信息
          SysPlatInfo spi = new SysPlatInfo();
          spi.setId(UUIDUtils.getUuid());
          spi.setCreateDate(new Date());
          spi.setAppKey(spi.getId());
          spi.setSecretKey(RandPwdUtil.genRandPwd(24));
          spi.setName(entity.getPlatName());
          spi.setDescription(entity.getDescription());
          spi.setCreateBy(entity.getCreateBy());
          sysPlatInfoDao.insert(spi);
          
          entity.setPlatId(spi.getId());
          entity.setAppKey(spi.getAppKey());
        }else {
          SysPlatInfo db = li.get(0);
          entity.setPlatId(db.getId());
          entity.setAppKey(db.getAppKey());
        }
        
        entity.setId(UUIDUtils.getUuid());
        
        Calendar ca = Calendar.getInstance();
        entity.setCreateDate(ca.getTime());
//        entity.setValidityBegin(ca.getTime());// 有效开始时间
        // 加1年
//        ca.add(Calendar.YEAR, 1);
//        entity.setValidityEnd(ca.getTime());// 有效截止时间
        
        entity.setBackStatus(9);// 状态：9-审核中、1-审核通过、0-审核失败-->0-驳回 1-使用中 3-关闭 2-已过期  9-未审批 5-重新申请
        if (sysPlatInterfaceDao.insert(entity) > 0) {
          return entity;
        }
        return null;
      } else {
        entity.setUpdateDate(new Date());
        if (sysPlatInterfaceDao.updateById(entity) > 0) {
          return entity;
        }
        return null;
      }
	}

	/**
     * @date 2019-03-25
     * @author zuoqb123
     * @todo   应用接口申请信息逻辑删除
     */
	@Override
	public boolean deleteLogic(String id) {
		SysPlatInterface entity=new SysPlatInterface();
		entity.setId(id);
		entity.setDelFlag(DEL_FLAG);
		entity.setUpdateDate(new Date());
		return sysPlatInterfaceDao.updateById(entity)>0;
	}

	/**
     * @date 2019-03-25
     * @author zuoqb123
     * @todo   应用接口申请信息单条数据查询
     */
	@Override
	public SysPlatInterface getById(String id) {
		return sysPlatInterfaceDao.selectById(id);
	}

	/**
     * @date   @date 2019-03-25
     * @author zuoqb123
     * @todo   应用接口申请信息分页查询
     */
	@Override
	public PageInfo<SysPlatInterface> pageList(SysPlatInterface entity, Integer pageNum,
			Integer pageSize) {
	  entity.setDelFlag(UN_DEL_FLAG);
		PageHelper.startPage(pageNum, pageSize);
		List<SysPlatInterface> list = sysPlatInterfaceDao.searchList(entity);
		EntityWrapper<SysPlatInterface> wrapper = searchWrapper(entity);
		//List<SysPlatInterface> list = sysPlatInterfaceDao.selectList(wrapper);
		PageInfo<SysPlatInterface> page = new PageInfo<SysPlatInterface>(list);
		return page;
	}
    
	
	 /**
     * @date 2019-03-25
     * @author zuoqb123
     * @todo   应用接口申请信息构建查询条件-以后扩展
     */
	private EntityWrapper<SysPlatInterface> searchWrapper(SysPlatInterface entity) {
    EntityWrapper<SysPlatInterface> wrapper = new EntityWrapper<SysPlatInterface>();
    wrapper.where("del_flag={0}", UN_DEL_FLAG);
    //根据主键模糊查询
    if(entity.getId()!=null&&StringUtils.isNotBlank(String.valueOf(entity.getId()))){
      wrapper.like("id", String.valueOf(entity.getId()));
    }
    //根据平台主键模糊查询
    if(entity.getPlatId()!=null&&StringUtils.isNotBlank(String.valueOf(entity.getPlatId()))){
      wrapper.like("plat_id", String.valueOf(entity.getPlatId()));
    }
    //根据应用key模糊查询
    if(entity.getAppKey()!=null&&StringUtils.isNotBlank(String.valueOf(entity.getAppKey()))){
      wrapper.like("app_key", String.valueOf(entity.getAppKey()));
    }
    //根据类型：1-算法、2-翻译模糊查询
    if(entity.getType()!=null&&StringUtils.isNotBlank(String.valueOf(entity.getType()))){
      wrapper.like("type", String.valueOf(entity.getType()));
    }
    //根据状态模糊查询
    if(entity.getBackStatus()!=null&&StringUtils.isNotBlank(String.valueOf(entity.getBackStatus()))){
      wrapper.like("back_status", String.valueOf(entity.getBackStatus()));
    }
    //根据接口模糊查询
    if(entity.getInterfCode()!=null&&StringUtils.isNotBlank(String.valueOf(entity.getInterfCode()))){
      wrapper.like("interf_code", String.valueOf(entity.getInterfCode()));
    }
    //根据接口名称模糊查询
    if(entity.getInterfName()!=null&&StringUtils.isNotBlank(String.valueOf(entity.getInterfName()))){
      wrapper.like("interf_name", String.valueOf(entity.getInterfName()));
    }
    //根据有效开始时间模糊查询
    if(entity.getValidityBegin()!=null&&StringUtils.isNotBlank(String.valueOf(entity.getValidityBegin()))){
      wrapper.like("validity_begin", String.valueOf(entity.getValidityBegin()));
    }
    //根据有效截止时间模糊查询
    if(entity.getValidityEnd()!=null&&StringUtils.isNotBlank(String.valueOf(entity.getValidityEnd()))){
      wrapper.like("validity_end", String.valueOf(entity.getValidityEnd()));
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
