package com.hlsofttech.product.dao;
import com.hlsofttech.entity.product.Test;
import org.apache.ibatis.annotations.Mapper;

import com.github.pagehelper.PageInfo;
import com.hlsofttech.base.BaseMappers;

/**
  * 新闻收藏Mapper接口
 * @author zuoqb123
 * @date 2019-07-18
 */
 @Mapper
public interface TestDao extends BaseMappers<Test> {
 /**
     * @date   2019年3月8日10:53:29
     * @author zuoqb123
     * @todo   字典新增或者修改
     */
	boolean saveOrUpdate(Test entity);
	/**
     * @date   2019年3月8日10:53:29
     * @author zuoqb123
     * @todo   字典逻辑删除
     */
	boolean deleteLogic(String id);
	
	/**
     * @date   2018-10-11
     * @author zuoqb123
     * @todo   字典单条数据查询
     */
	Test getById(String id);
	
	/**
     * @date   2019年3月8日10:53:29
     * @author zuoqb123
     * @todo   字典分页查询
     */
	PageInfo<Test> pageList(Test entity,Integer pageNum,Integer pageSize);
}


