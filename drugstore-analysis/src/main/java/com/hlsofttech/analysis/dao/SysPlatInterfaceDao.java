package com.hlsofttech.analysis.dao;
import com.hlsofttech.entity.analysis.SysPlatInterface;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.github.pagehelper.PageInfo;
import com.hlsofttech.base.BaseMappers;

/**
  * 应用接口申请信息Mapper接口
 * @author zuoqb123
 * @date 2019-03-25
 */
 @Mapper
public interface SysPlatInterfaceDao extends BaseMappers<SysPlatInterface> {
 /**
     * @date   2019年3月8日10:53:29
     * @author zuoqb123
     * @todo   字典新增或者修改
     */
	boolean saveOrUpdate(SysPlatInterface entity);
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
	SysPlatInterface getById(String id);
	
	/**
     * @date   2019年3月8日10:53:29
     * @author zuoqb123
     * @todo   字典分页查询
     */
	PageInfo<SysPlatInterface> pageList(SysPlatInterface entity,Integer pageNum,Integer pageSize);
	
	/**
   * 查找list
   *
   * @param sysPlatInterface
   * @return
   */
  List<SysPlatInterface> searchList(@Param("model") SysPlatInterface sysPlatInterface);
}


