package com.hlsofttech.service.product;

import com.hlsofttech.entity.product.Test;
import com.github.pagehelper.PageInfo;

/**
 * @date 2019-07-18
 * @author zuoqb123
 * 新闻收藏服务类
 */
public interface TestService{


	 /**
     * @date   2019-07-18
     * @author zuoqb123
     * @todo   新闻收藏新增或者修改
     */
	Test saveOrUpdate(Test entity);
	/**
     * @date   2019-07-18
     * @author zuoqb123
     * @todo   新闻收藏逻辑删除
     */
	boolean deleteLogic(String id);
	
	/**
     * @date   2018-10-11
     * @author zuoqb123
     * @todo   新闻收藏单条数据查询
     */
	Test getById(String id);
	
	/**
     * @date   2019-07-18
     * @author zuoqb123
     * @todo   新闻收藏分页查询
     */
	PageInfo<Test> pageList(Test entity,Integer pageNum,Integer pageSize);
	
	


}
