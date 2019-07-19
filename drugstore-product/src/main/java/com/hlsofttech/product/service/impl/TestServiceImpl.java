package com.hlsofttech.product.service.impl;

import com.hlsofttech.entity.product.Test;
import com.hlsofttech.product.dao.TestDao;
import com.hlsofttech.service.product.TestService;
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
 * @date 2019-07-18
 * @author zuoqb123
 * 新闻收藏服务实现类
 */
@Service(version = Constant.VERSION,group="com.hlsofttech.product",timeout=Constant.TIMEOUT)
@org.springframework.stereotype.Service
public class TestServiceImpl implements TestService,Constant {

    @Autowired
    private TestDao testDao;
    
     /**
     * @date 2019-07-18
     * @author zuoqb123
     * @todo   新闻收藏新增或者修改
     */
    @Override
	public Test saveOrUpdate(Test entity) {
		if(StringUtils.isBlank(entity.getId())){
			//新增
			entity.setId(UUIDUtils.getUuid());
			entity.setCreateDate(new Date());
			if(testDao.insert(entity)>0){
				return entity;
			}
			return null;
		}else{
			entity.setUpdateDate(new Date());
			if(testDao.updateById(entity)>0){
				return entity;
			}
			return null;
		}
	}

	/**
     * @date 2019-07-18
     * @author zuoqb123
     * @todo   新闻收藏逻辑删除
     */
	@Override
	public boolean deleteLogic(String id) {
		Test entity=new Test();
		entity.setId(id);
		entity.setDelFlag(DEL_FLAG);
		entity.setUpdateDate(new Date());
		return testDao.updateById(entity)>0;
	}

	/**
     * @date 2019-07-18
     * @author zuoqb123
     * @todo   新闻收藏单条数据查询
     */
	@Override
	public Test getById(String id) {
		return testDao.selectById(id);
	}

	/**
     * @date   @date 2019-07-18
     * @author zuoqb123
     * @todo   新闻收藏分页查询
     */
	@Override
	public PageInfo<Test> pageList(Test entity, Integer pageNum,
			Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		EntityWrapper<Test> wrapper = searchWrapper(entity);
		List<Test> list = testDao.selectList(wrapper);
		PageInfo<Test> page = new PageInfo<Test>(list);
		return page;
	}
    
	
	 /**
     * @date 2019-07-18
     * @author zuoqb123
     * @todo   新闻收藏构建查询条件-以后扩展
     */
    private EntityWrapper<Test> searchWrapper(Test entity) {
		EntityWrapper<Test> wrapper = new EntityWrapper<Test>();
		wrapper.where("del_flag={0}", UN_DEL_FLAG);
		//根据编号模糊查询
		if(entity.getId()!=null&&StringUtils.isNotBlank(String.valueOf(entity.getId()))){
			wrapper.like("id", String.valueOf(entity.getId()));
		}
		//根据用户编码模糊查询
		if(entity.getUserId()!=null&&StringUtils.isNotBlank(String.valueOf(entity.getUserId()))){
			wrapper.like("user_id", String.valueOf(entity.getUserId()));
		}
		//根据新闻编码编码模糊查询
		if(entity.getNewsId()!=null&&StringUtils.isNotBlank(String.valueOf(entity.getNewsId()))){
			wrapper.like("news_id", String.valueOf(entity.getNewsId()));
		}
		//根据类型 媒体/社交/专利模糊查询
		if(entity.getType()!=null&&StringUtils.isNotBlank(String.valueOf(entity.getType()))){
			wrapper.like("type", String.valueOf(entity.getType()));
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
