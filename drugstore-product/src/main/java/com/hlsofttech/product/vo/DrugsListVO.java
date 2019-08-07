package com.hlsofttech.product.vo;

import lombok.Data;

@Data
public class DrugsListVO {

    /**
     * id值
     */
    private Integer id;

    private String downcheck;

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
     * 总条数
     */
    private Long count;
    /**
     * 当前页
     */
    private Long page;


}
