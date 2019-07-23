package com.hlsofttech.service.user;

import com.hlsofttech.entity.user.TestInfo;
import com.github.pagehelper.PageInfo;

/**
 * @author suncy123
 * 新闻收藏服务类
 * @date 2019-07-23
 */
public interface TestInfoService {


    /**
     * @date 2019-07-23
     * @author suncy123
     * @todo 新闻收藏新增或者修改
     */
    TestInfo saveOrUpdate(TestInfo entity);

    /**
     * @date 2019-07-23
     * @author suncy123
     * @todo 新闻收藏逻辑删除
     */
    boolean deleteLogic(String id);

    /**
     * @date 2018-10-11
     * @author suncy123
     * @todo 新闻收藏单条数据查询
     */
    TestInfo getById(String id);

    /**
     * @date 2019-07-23
     * @author suncy123
     * @todo 新闻收藏分页查询
     */
    PageInfo<TestInfo> pageList(TestInfo entity, Integer pageNum, Integer pageSize);


}
