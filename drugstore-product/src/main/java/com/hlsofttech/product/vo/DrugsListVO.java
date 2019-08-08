package com.hlsofttech.product.vo;

import lombok.Data;

@Data
public class DrugsListVO {

    /**
     * id值
     */
    private Integer id;

    /**
     * 是否有详情信息   0-有  1-非0代表该条数据有附件可供下载
     */
    private Integer downcheck;
    /**
     * 下载链接
     */
    private String down;
    /**
     * 药品名称
     */
    private String name;
    /**
     * 来源
     */
    private String source;
    /**
     * 0代表该条数据返回未拆分的正文内容，1代表该条数据有详细拆分的正文内容
     */
    private Integer xiangqing;
    /**
     * 总条数
     */
    private Long count;
    /**
     * 当前页
     */
    private Long page;


}
