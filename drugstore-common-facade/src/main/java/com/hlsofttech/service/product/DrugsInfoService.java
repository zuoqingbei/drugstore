package com.hlsofttech.service.product;

import com.hlsofttech.entity.product.DrugsInfo;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author suncy
 * 药品库-药品信息表服务类
 * @date 2019-08-14
 */
public interface DrugsInfoService {


    /**
     * @date 2019-08-14
     * @author suncy
     * @todo 药品库-药品信息表新增或者修改
     */
    DrugsInfo saveOrUpdate(DrugsInfo entity);

    /**
     * @date 2019-08-14
     * @author suncy
     * @todo 药品库-药品信息表逻辑删除
     */
    boolean deleteLogic(String id);

    /**
     * @date 2018-10-11
     * @author suncy
     * @todo 药品库-药品信息表单条数据查询
     */
    DrugsInfo getById(String id);

    /**
     * @date 2019-08-14
     * @author suncy
     * @todo 药品库-药品信息表查询
     */
    List<DrugsInfo> findList(DrugsInfo entity);

    /**
     * @date 2019-08-14
     * @author suncy
     * @todo 药品库-药品信息表分页查询
     */
    PageInfo<DrugsInfo> pageList(DrugsInfo entity, Integer pageNum, Integer pageSize);

    /**
     * @date 2018-10-11
     * @author suncy
     * @todo 药品库-根据条件查询单个药品信息
     */
    DrugsInfo getDrugsInfo(DrugsInfo entity);

    /**
     * @param approvalNumber:
     * @Description: 根据批准文号查询药品信息
     * @Date: 2019/8/14 13:50
     * @return: com.hlsofttech.entity.product.DrugsInfo
     * @Author: suncy
     **/
    DrugsInfo getByApprovalNumber(String approvalNumber);


}
