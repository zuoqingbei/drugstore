package com.hlsofttech.util;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.hlsofttech.exception.CommonBizException;
import com.hlsofttech.exception.ExpCodeEnum;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;

import java.util.function.Supplier;

/**
 * @Description: 转换
 * @Date: 2019/8/15 9:47
 * @Author: suncy
 **/

public interface ProcessBusiness {

    default <E, T> Wrapper<E> structure(T t) {
        if (StringUtils.isEmpty(t)) {
            throw new CommonBizException(ExpCodeEnum.LIST_DTO_TO_VO);
        }
        return new EntityWrapper<>();
    }

    default <T, R> R convertTarget(T t, Supplier<R> supplier) {
        R r = supplier.get();
        BeanUtils.copyProperties(t, r);
        return r;
    }
}
