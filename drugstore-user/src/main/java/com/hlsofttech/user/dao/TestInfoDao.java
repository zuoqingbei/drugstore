package com.hlsofttech.user.dao;
import com.hlsofttech.entity.user.TestInfo;
import org.apache.ibatis.annotations.Mapper;

import com.github.pagehelper.PageInfo;
import com.hlsofttech.base.BaseMappers;

/**
  * 新闻收藏Mapper接口
 * @author suncy123
 * @date 2019-07-23
 */
 @Mapper
public interface TestInfoDao extends BaseMappers<TestInfo> {
 /**
     * @date   2019年3月8日10:53:29
     * @author zuoqb123
     * @todo   字典新增或者修改
     */
	boolean saveOrUpdate(TestInfo entity);
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
	TestInfo getById(String id);
	
	/**
     * @date   2019年3月8日10:53:29
     * @author zuoqb123
     * @todo   字典分页查询
     */
	PageInfo<TestInfo> pageList(TestInfo entity, Integer pageNum, Integer pageSize);
}


