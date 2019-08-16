package com.hlsofttech.product.dao;

import com.github.pagehelper.PageInfo;
import com.hlsofttech.base.BaseMappers;
import com.hlsofttech.entity.shop.DrugsShopDeliveryScope;
import org.apache.ibatis.annotations.Mapper;

/**
  * 药店-配送范围Mapper接口
 * @author suncy
 * @date 2019-08-16
 */
 @Mapper
public interface DrugsShopDeliveryScopeDao extends BaseMappers<DrugsShopDeliveryScope> {
 /**
     * @date   2019年3月8日10:53:29
     * @author zuoqb123
     * @todo   字典新增或者修改
     */
	boolean saveOrUpdate(DrugsShopDeliveryScope entity);
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
	DrugsShopDeliveryScope getById(String id);
	
	/**
     * @date   2019年3月8日10:53:29
     * @author zuoqb123
     * @todo   字典分页查询
     */
	PageInfo<DrugsShopDeliveryScope> pageList(DrugsShopDeliveryScope entity, Integer pageNum, Integer pageSize);
}


