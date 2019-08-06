package com.hlsofttech.entity.erp;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/***
 * @Description: erp接口根据统一信用代码查询平台门店ID
 * @Date: 2019/8/5 15:50
 * @Author: suncy
 **/
@Data
public class DrugsDTO extends AbstractERPDTO {

    List<DrugsDetailDTO> data = new ArrayList<>();

}
