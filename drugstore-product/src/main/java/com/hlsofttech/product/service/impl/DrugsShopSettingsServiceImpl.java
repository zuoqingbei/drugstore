package com.hlsofttech.product.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hlsofttech.common.Constant;
import com.hlsofttech.entity.shop.DrugsShopSettings;
import com.hlsofttech.product.dao.DrugsShopSettingsDao;
import com.hlsofttech.service.shop.DrugsShopSettingsService;
import com.hlsofttech.utils.UUIDUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * @author suncy
 * 药店-相关设置服务实现类
 * @date 2019-08-16
 */
@Service(version = Constant.VERSION, timeout = Constant.TIMEOUT)
public class DrugsShopSettingsServiceImpl implements DrugsShopSettingsService, Constant {

    @Autowired
    private DrugsShopSettingsDao drugsShopSettingsDao;

    /**
     * @date 2019-08-16
     * @author suncy
     * @todo 药店-相关设置新增或者修改
     */
    @Override
    public DrugsShopSettings saveOrUpdate(DrugsShopSettings entity) {
        if (StringUtils.isBlank(entity.getId())) {
            //新增
            entity.setId(UUIDUtils.getUuid());
            entity.setCreateDate(new Date());
            if (drugsShopSettingsDao.insert(entity) > 0) {
                return entity;
            }
            return null;
        } else {
            entity.setUpdateDate(new Date());
            if (drugsShopSettingsDao.updateById(entity) > 0) {
                return entity;
            }
            return null;
        }
    }

    /**
     * @date 2019-08-16
     * @author suncy
     * @todo 药店-相关设置逻辑删除
     */
    @Override
    public boolean deleteLogic(String id) {
        DrugsShopSettings entity = new DrugsShopSettings();
        entity.setId(id);
        entity.setDelFlag(DEL_FLAG);
        entity.setUpdateDate(new Date());
        return drugsShopSettingsDao.updateById(entity) > 0;
    }

    /**
     * @date 2019-08-16
     * @author suncy
     * @todo 药店-相关设置单条数据查询
     */
    @Override
    public DrugsShopSettings getById(String id) {
        return drugsShopSettingsDao.selectById(id);
    }

    /**
     * @date @date 2019-08-16
     * @author suncy
     * @todo 药店-相关设置分页查询
     */
    @Override
    public PageInfo<DrugsShopSettings> pageList(DrugsShopSettings entity, Integer pageNum,
                                                Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        EntityWrapper<DrugsShopSettings> wrapper = searchWrapper(entity);
        List<DrugsShopSettings> list = drugsShopSettingsDao.selectList(wrapper);
        PageInfo<DrugsShopSettings> page = new PageInfo<DrugsShopSettings>(list);
        return page;
    }

    @Override
    public DrugsShopSettings getByDrugsShopId(String drugsShopId) {
        DrugsShopSettings drugsShopSettings = new DrugsShopSettings();
        drugsShopSettings.setDrugsShopId(drugsShopId);
        return drugsShopSettingsDao.selectOne(drugsShopSettings);
    }

    /**
     * @date 2019-08-16
     * @author suncy
     * @todo 药店-相关设置构建查询条件-以后扩展
     */
    private EntityWrapper<DrugsShopSettings> searchWrapper(DrugsShopSettings entity) {
        EntityWrapper<DrugsShopSettings> wrapper = new EntityWrapper<DrugsShopSettings>();
        wrapper.where("del_flag={0}", UN_DEL_FLAG);
        //根据主键模糊查询
        if (entity.getId() != null && StringUtils.isNotBlank(String.valueOf(entity.getId()))) {
            wrapper.like("id", String.valueOf(entity.getId()));
        }
        //根据药店ID模糊查询
        if (entity.getDrugsShopId() != null && StringUtils.isNotBlank(String.valueOf(entity.getDrugsShopId()))) {
            wrapper.like("drugs_shop_id", String.valueOf(entity.getDrugsShopId()));
        }
        //根据配送方式（0=店家配送，1=骑手配送）模糊查询
        if (entity.getDeliveryWay() != null && StringUtils.isNotBlank(String.valueOf(entity.getDeliveryWay()))) {
            wrapper.like("delivery_way", String.valueOf(entity.getDeliveryWay()));
        }
        //根据常规时间开始模糊查询
        if (entity.getRoutineTimeBegin() != null && StringUtils.isNotBlank(String.valueOf(entity.getRoutineTimeBegin()))) {
            wrapper.like("routine_time_begin", String.valueOf(entity.getRoutineTimeBegin()));
        }
        //根据常规时间结束模糊查询
        if (entity.getRoutineTimeEnd() != null && StringUtils.isNotBlank(String.valueOf(entity.getRoutineTimeEnd()))) {
            wrapper.like("routine_time_end", String.valueOf(entity.getRoutineTimeEnd()));
        }
        //根据特殊时间开始模糊查询
        if (entity.getSpecialTimeBegin() != null && StringUtils.isNotBlank(String.valueOf(entity.getSpecialTimeBegin()))) {
            wrapper.like("special_time_begin", String.valueOf(entity.getSpecialTimeBegin()));
        }
        //根据特殊时间结束模糊查询
        if (entity.getSpecialTimeEnd() != null && StringUtils.isNotBlank(String.valueOf(entity.getSpecialTimeEnd()))) {
            wrapper.like("special_time_end", String.valueOf(entity.getSpecialTimeEnd()));
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
