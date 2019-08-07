package com.hlsofttech.product.service;

import com.hlsofttech.entity.product.DrugsCategory;
import com.hlsofttech.product.dao.DrugsCategoryDao;
import com.hlsofttech.service.product.DrugsCategoryService;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.hlsofttech.utils.UUIDUtils;
import com.hlsofttech.common.Constant;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.StringUtils;
/**
 * @date 2019-08-02
 * @author suncy
 * 药品库-药品分类服务实现类
 */
public class DrugsCategoryServiceImpl implements DrugsCategoryService,Constant {

    @Autowired
    private DrugsCategoryDao drugsCategoryDao;
    
     /**
     * @date 2019-08-02
     * @author suncy
     * @todo   药品库-药品分类新增或者修改
     */
    @Override
	public DrugsCategory saveOrUpdate(DrugsCategory entity) {
		if(StringUtils.isBlank(entity.getId())){
			//新增
			entity.setId(UUIDUtils.getUuid());
			entity.setCreateDate(new Date());
			if(drugsCategoryDao.insert(entity)>0){
				return entity;
			}
			return null;
		}else{
			entity.setUpdateDate(new Date());
			if(drugsCategoryDao.updateById(entity)>0){
				return entity;
			}
			return null;
		}
	}

	/**
     * @date 2019-08-02
     * @author suncy
     * @todo   药品库-药品分类逻辑删除
     */
	@Override
	public boolean deleteLogic(String id) {
		DrugsCategory entity=new DrugsCategory();
		entity.setId(id);
		entity.setDelFlag(DEL_FLAG);
		entity.setUpdateDate(new Date());
		return drugsCategoryDao.updateById(entity)>0;
	}

	/**
     * @date 2019-08-02
     * @author suncy
     * @todo   药品库-药品分类单条数据查询
     */
	@Override
	public DrugsCategory getById(String id) {
		return drugsCategoryDao.selectById(id);
	}

	/**
     * @date   @date 2019-08-02
     * @author suncy
     * @todo   药品库-药品分类分页查询
     */
	@Override
	public PageInfo<DrugsCategory> pageList(DrugsCategory entity, Integer pageNum,
			Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		EntityWrapper<DrugsCategory> wrapper = searchWrapper(entity);
		List<DrugsCategory> list = drugsCategoryDao.selectList(wrapper);
		PageInfo<DrugsCategory> page = new PageInfo<DrugsCategory>(list);
		return page;
	}
    
	
	 /**
     * @date 2019-08-02
     * @author suncy
     * @todo   药品库-药品分类构建查询条件-以后扩展
     */
    private EntityWrapper<DrugsCategory> searchWrapper(DrugsCategory entity) {
		EntityWrapper<DrugsCategory> wrapper = new EntityWrapper<DrugsCategory>();
		wrapper.where("del_flag={0}", UN_DEL_FLAG);
		//根据主键模糊查询
		if(entity.getId()!=null&&StringUtils.isNotBlank(String.valueOf(entity.getId()))){
			wrapper.like("id", String.valueOf(entity.getId()));
		}
		//根据分类名称模糊查询
		if(entity.getTitle()!=null&&StringUtils.isNotBlank(String.valueOf(entity.getTitle()))){
			wrapper.like("title", String.valueOf(entity.getTitle()));
		}
		//根据父ID模糊查询
		if(entity.getParentId()!=null&&StringUtils.isNotBlank(String.valueOf(entity.getParentId()))){
			wrapper.like("parent_id", String.valueOf(entity.getParentId()));
		}
		//根据级别1/2/3模糊查询
		if(entity.getLevel()!=null&&StringUtils.isNotBlank(String.valueOf(entity.getLevel()))){
			wrapper.like("level", String.valueOf(entity.getLevel()));
		}
		//根据状态（0启用1=停用）模糊查询
		if(entity.getStatus()!=null&&StringUtils.isNotBlank(String.valueOf(entity.getStatus()))){
			wrapper.like("status", String.valueOf(entity.getStatus()));
		}
		//根据创建人模糊查询
		if(entity.getCreateBy()!=null&&StringUtils.isNotBlank(String.valueOf(entity.getCreateBy()))){
			wrapper.like("create_by", String.valueOf(entity.getCreateBy()));
		}
		//根据创建时间模糊查询
		if(entity.getCreateDate()!=null&&StringUtils.isNotBlank(String.valueOf(entity.getCreateDate()))){
			wrapper.like("create_date", String.valueOf(entity.getCreateDate()));
		}
		//根据修改人模糊查询
		if(entity.getUpdateBy()!=null&&StringUtils.isNotBlank(String.valueOf(entity.getUpdateBy()))){
			wrapper.like("update_by", String.valueOf(entity.getUpdateBy()));
		}
		//根据修改时间模糊查询
		if(entity.getUpdateDate()!=null&&StringUtils.isNotBlank(String.valueOf(entity.getUpdateDate()))){
			wrapper.like("update_date", String.valueOf(entity.getUpdateDate()));
		}
		//根据备注模糊查询
		if(entity.getRemarks()!=null&&StringUtils.isNotBlank(String.valueOf(entity.getRemarks()))){
			wrapper.like("remarks", String.valueOf(entity.getRemarks()));
		}
		//根据删除标识（1=已删除0未删除）模糊查询
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
