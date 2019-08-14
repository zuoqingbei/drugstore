package com.hlsofttech.service;

import com.hlsofttech.entity.vo.DrugsAddRequest;
import com.hlsofttech.rsp.Result;

/***
 * @Description: erp相关业务接口
 * @Date: 2019/8/14 17:47
 * @Author: suncy
 **/
public interface ErpService {

    /***
     * @Description: 处理erp同步药品信息业务
     * @Date: 2019/8/14 17:51
     * @param drugsAddRequest:
     * @return: com.hlsofttech.rsp.Result
     * @Author: suncy
     **/
    Result drugsSyn(DrugsAddRequest drugsAddRequest);

}
