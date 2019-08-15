package com.hlsofttech.product.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hlsofttech.common.Constant;
import com.hlsofttech.entity.shop.DrugsShopInfo;
import com.hlsofttech.product.dao.DrugsShopInfoDao;
import com.hlsofttech.service.shop.DrugsShopInfoService;
import com.hlsofttech.utils.UUIDUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * @author suntf123
 * 药店签约信息服务实现类
 * @date 2019-08-12
 */
@Service(version = Constant.VERSION, timeout = Constant.TIMEOUT)
@org.springframework.stereotype.Service
public class DrugsShopInfoServiceImpl implements DrugsShopInfoService, Constant {

    @Autowired
    private DrugsShopInfoDao drugsShopInfoDao;

    /**
     * @date 2019-08-12
     * @author suntf123
     * @todo 药店签约信息新增或者修改
     */
    @Override
    public DrugsShopInfo saveOrUpdate(DrugsShopInfo entity) {
        if (StringUtils.isBlank(entity.getId())) {
            //新增
            entity.setId(UUIDUtils.getUuid());
            entity.setCreateDate(new Date());
            if (drugsShopInfoDao.insert(entity) > 0) {
                return entity;
            }
            return null;
        } else {
            entity.setUpdateDate(new Date());
            if (drugsShopInfoDao.updateById(entity) > 0) {
                return entity;
            }
            return null;
        }
    }

    /**
     * @date 2019-08-12
     * @author suntf123
     * @todo 药店签约信息逻辑删除
     */
    @Override
    public boolean deleteLogic(String id) {
        DrugsShopInfo entity = new DrugsShopInfo();
        entity.setId(id);
        entity.setDelFlag(DEL_FLAG);
        entity.setUpdateDate(new Date());
        return drugsShopInfoDao.updateById(entity) > 0;
    }

    /**
     * @date 2019-08-12
     * @author suntf123
     * @todo 药店签约信息单条数据查询
     */
    @Override
    public DrugsShopInfo getById(String id) {
        return drugsShopInfoDao.selectById(id);
    }

    /**
     * @date @date 2019-08-12
     * @author suntf123
     * @todo 药店签约信息分页查询
     */
    @Override
    public PageInfo<DrugsShopInfo> pageList(DrugsShopInfo entity, Integer pageNum,
                                            Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        EntityWrapper<DrugsShopInfo> wrapper = searchWrapper(entity);
        List<DrugsShopInfo> list = drugsShopInfoDao.selectList(wrapper);
        PageInfo<DrugsShopInfo> page = new PageInfo<DrugsShopInfo>(list);
        return page;
    }

    @Override
    public DrugsShopInfo getByAppKey(String appKey) {
        DrugsShopInfo param = new DrugsShopInfo();
        param.setAppKey(appKey);
        return drugsShopInfoDao.selectOne(param);
    }


    /**
     * @date 2019-08-12
     * @author suntf123
     * @todo 药店签约信息构建查询条件-以后扩展
     */
    private EntityWrapper<DrugsShopInfo> searchWrapper(DrugsShopInfo entity) {
        EntityWrapper<DrugsShopInfo> wrapper = new EntityWrapper<DrugsShopInfo>();
        wrapper.where("del_flag={0}", UN_DEL_FLAG);
        //根据主键id模糊查询
        if (entity.getId() != null && StringUtils.isNotBlank(String.valueOf(entity.getId()))) {
            wrapper.like("id", String.valueOf(entity.getId()));
        }
        //根据营业执照统一信用代码模糊查询
        if (entity.getCode() != null && StringUtils.isNotBlank(String.valueOf(entity.getCode()))) {
            wrapper.like("code", String.valueOf(entity.getCode()));
        }
        //根据签约方key模糊查询
        if (entity.getAppKey() != null && StringUtils.isNotBlank(String.valueOf(entity.getAppKey()))) {
            wrapper.like("app_key", String.valueOf(entity.getAppKey()));
        }
        //根据签约密钥模糊查询
        if (entity.getAppSecret() != null && StringUtils.isNotBlank(String.valueOf(entity.getAppSecret()))) {
            wrapper.like("app_secret", String.valueOf(entity.getAppSecret()));
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
