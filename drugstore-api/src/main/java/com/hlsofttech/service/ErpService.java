package com.hlsofttech.service;

import com.hlsofttech.entity.vo.DrugsAddRequest;
import com.hlsofttech.entity.vo.SyncRefundStatusVO;
import com.hlsofttech.entity.vo.SyncStockForErpVO;
import com.hlsofttech.rsp.Result;

import java.util.List;
import java.util.Map;

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

    /***
     * @Author: suntf
     * @Description:处理erp同步药品库存
     * @Date: 2019/8/16
     * @param syncStockForErpVOS:
     * @return: com.hlsofttech.rsp.Result
     **/
    Map<String, Integer> drugsStockSyn(List<SyncStockForErpVO> syncStockForErpVOS);

    /***
     * @Author: suntf
     * @Description:退换货结果通知
     * @Date: 2019/8/16
     * @param refundStatusVO: 1
     * @return: com.hlsofttech.rsp.Result
     **/
    Boolean drugsRefundResult(SyncRefundStatusVO refundStatusVO);
}
