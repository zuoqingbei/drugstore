package com.hlsofttech.entity.vo;

import lombok.Data;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/***
 * @Description: erp接口同步药品数据
 * @Date: 2019/8/5 15:50
 * @Author: suncy
 **/
@Data
public class DrugsAddRequest extends AbstractERPRequest {

    @Valid
    private List<DrugsAddVO> data;

}
