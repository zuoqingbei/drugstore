package com.hlsofttech.product.service.impl;

import com.hlsofttech.entity.shop.DrugsShopDeliveryScope;
import com.hlsofttech.product.dao.DrugsShopDeliveryScopeDao;
import com.alibaba.dubbo.config.annotation.Service;
import com.hlsofttech.service.shop.DrugsShopDeliveryScopeService;
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
 * @author suncy
 * 药店-配送范围服务实现类
 * @date 2019-08-16
 */
@Service(version = Constant.VERSION, timeout = Constant.TIMEOUT)
public class DrugsShopDeliveryScopeServiceImpl implements DrugsShopDeliveryScopeService, Constant {

    @Autowired
    private DrugsShopDeliveryScopeDao drugsShopDeliveryScopeDao;

    /**
     * @date 2019-08-16
     * @author suncy
     * @todo 药店-配送范围新增或者修改
     */
    @Override
    public DrugsShopDeliveryScope saveOrUpdate(DrugsShopDeliveryScope entity) {
        if (StringUtils.isBlank(entity.getId())) {
            //新增
            entity.setId(UUIDUtils.getUuid());
            entity.setCreateDate(new Date());
            if (drugsShopDeliveryScopeDao.insert(entity) > 0) {
                return entity;
            }
            return null;
        } else {
            entity.setUpdateDate(new Date());
            if (drugsShopDeliveryScopeDao.updateById(entity) > 0) {
                return entity;
            }
            return null;
        }
    }

    /**
     * @date 2019-08-16
     * @author suncy
     * @todo 药店-配送范围逻辑删除
     */
    @Override
    public boolean deleteLogic(String id) {
        DrugsShopDeliveryScope entity = new DrugsShopDeliveryScope();
        entity.setId(id);
        entity.setDelFlag(DEL_FLAG);
        entity.setUpdateDate(new Date());
        return drugsShopDeliveryScopeDao.updateById(entity) > 0;
    }

    /**
     * @date 2019-08-16
     * @author suncy
     * @todo 药店-配送范围单条数据查询
     */
    @Override
    public DrugsShopDeliveryScope getById(String id) {
        return drugsShopDeliveryScopeDao.selectById(id);
    }

    /**
     * @date @date 2019-08-16
     * @author suncy
     * @todo 药店-配送范围分页查询
     */
    @Override
    public PageInfo<DrugsShopDeliveryScope> pageList(DrugsShopDeliveryScope entity, Integer pageNum,
                                                     Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        EntityWrapper<DrugsShopDeliveryScope> wrapper = searchWrapper(entity);
        List<DrugsShopDeliveryScope> list = drugsShopDeliveryScopeDao.selectList(wrapper);
        PageInfo<DrugsShopDeliveryScope> page = new PageInfo<DrugsShopDeliveryScope>(list);
        return page;
    }


    /**
     * @date 2019-08-16
     * @author suncy
     * @todo 药店-配送范围构建查询条件-以后扩展
     */
    private EntityWrapper<DrugsShopDeliveryScope> searchWrapper(DrugsShopDeliveryScope entity) {
        EntityWrapper<DrugsShopDeliveryScope> wrapper = new EntityWrapper<DrugsShopDeliveryScope>();
        wrapper.where("del_flag={0}", UN_DEL_FLAG);
        //根据主键模糊查询
        if (entity.getId() != null && StringUtils.isNotBlank(String.valueOf(entity.getId()))) {
            wrapper.like("id", String.valueOf(entity.getId()));
        }
        //根据药店ID
        if (entity.getDrugsShopId() != null && StringUtils.isNotBlank(String.valueOf(entity.getDrugsShopId()))) {
            wrapper.eq("drugs_shop_id", String.valueOf(entity.getDrugsShopId()));
        }
        //根据自定义范围名称模糊查询
        if (entity.getTitle() != null && StringUtils.isNotBlank(String.valueOf(entity.getTitle()))) {
            wrapper.like("title", String.valueOf(entity.getTitle()));
        }
        //根据起送价模糊查询
        if (entity.getStartPrice() != null && StringUtils.isNotBlank(String.valueOf(entity.getStartPrice()))) {
            wrapper.like("start_price", String.valueOf(entity.getStartPrice()));
        }
        //根据配送范围开始模糊查询
        if (entity.getScopeBegin() != null && StringUtils.isNotBlank(String.valueOf(entity.getScopeBegin()))) {
            wrapper.like("scope_begin", String.valueOf(entity.getScopeBegin()));
        }
        //根据配送范围结束模糊查询
        if (entity.getScopeEnd() != null && StringUtils.isNotBlank(String.valueOf(entity.getScopeEnd()))) {
            wrapper.like("scope_end", String.valueOf(entity.getScopeEnd()));
        }
        //根据配送费（分）模糊查询
        if (entity.getDeliveryFee() != null && StringUtils.isNotBlank(String.valueOf(entity.getDeliveryFee()))) {
            wrapper.like("delivery_fee", String.valueOf(entity.getDeliveryFee()));
        }
        //根据状态（0启用1=停用）模糊查询
        if (entity.getStatus() != null && StringUtils.isNotBlank(String.valueOf(entity.getStatus()))) {
            wrapper.like("status", String.valueOf(entity.getStatus()));
        }
        //根据创建人模糊查询
        if (entity.getCreateBy() != null && StringUtils.isNotBlank(String.valueOf(entity.getCreateBy()))) {
            wrapper.like("create_by", String.valueOf(entity.getCreateBy()));
        }
        //根据创建时间模糊查询
        if (entity.getCreateDate() != null && StringUtils.isNotBlank(String.valueOf(entity.getCreateDate()))) {
            wrapper.like("create_date", String.valueOf(entity.getCreateDate()));
        }
        //根据修改人模糊查询
        if (entity.getUpdateBy() != null && StringUtils.isNotBlank(String.valueOf(entity.getUpdateBy()))) {
            wrapper.like("update_by", String.valueOf(entity.getUpdateBy()));
        }
        //根据修改时间模糊查询
        if (entity.getUpdateDate() != null && StringUtils.isNotBlank(String.valueOf(entity.getUpdateDate()))) {
            wrapper.like("update_date", String.valueOf(entity.getUpdateDate()));
        }
        //根据备注模糊查询
        if (entity.getRemarks() != null && StringUtils.isNotBlank(String.valueOf(entity.getRemarks()))) {
            wrapper.like("remarks", String.valueOf(entity.getRemarks()));
        }
        //根据删除标识（1=已删除0未删除）模糊查询
        if (entity.getDelFlag() != null && StringUtils.isNotBlank(String.valueOf(entity.getDelFlag()))) {
            wrapper.like("del_flag", String.valueOf(entity.getDelFlag()));
        }
        if (entity.getStartDate() != null) {
            wrapper.ge("create_date", entity.getStartDate());
        }
        if (entity.getEndDate() != null) {
            wrapper.le("create_date", entity.getEndDate());
        }
        if (StringUtils.isNotBlank(entity.getOrderBy())) {
            wrapper.orderBy(entity.getOrderBy(), entity.isAsc());
        } else {
            wrapper.orderBy("create_date", false);
        }
        //System.out.println(wrapper.originalSql());
        return wrapper;
    }

}
