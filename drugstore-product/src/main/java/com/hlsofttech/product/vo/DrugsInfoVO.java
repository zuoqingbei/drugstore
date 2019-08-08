package com.hlsofttech.product.vo;

import lombok.Data;

@Data
public class DrugsInfoVO {
    /**
     * 中文名称
     */
    private String name;
    /**
     * 来源
     */
    private String source;
    /**
     * 药品类别
     */
    private String second;
    /**
     * 批准文号
     */
    private String pzwh;

    /**
     * 是否有详情   0-有  1-无，无详情时取pdf图片
     */
    private Integer hasContent;
    /**
     * 详情
     */
    private String content;
    /**
     * 下载链接，无详情时显示此字段
     */
    private String down;

    /**
     * 适应症
     */
    private String mainFunction;
    /**
     * 用法用量
     */
    private String usageDosage;


    /**
     * 中文名称
     */
    private String chinesename;
    /**
     * 英文名称
     */
    private String englishname;
    /**
     * 商品名称
     */
    private String shangpinname;
    /**
     * 生产企业
     */
    private String shengchanqiye;
    /**
     * 成分
     */
    private String chengfen;
    /**
     * 性状
     */
    private String xingzhuang;
    /**
     * 规格
     */
    private String guige;
    /**
     * 剂型
     */
    private String jingji;
    /**
     * 贮藏
     */
    private String zhucang;
    /**
     * 包装
     */
    private String baozhuang;
    /**
     * 有效期
     */
    private String youxiaoqi;
    /**
     * 执行标准
     */
    private String zhixingbiaozhun;


    /**
     * 功能主治
     */
    private String zhuzhi;
    /**
     * 用法用量
     */
    private String yongfa;
    /**
     * 不良反应
     */
    private String fanying;
    /**
     * 注意
     */
    private String zhuyi;
    /**
     * 过量
     */
    private String guoliang;
    /**
     * 特殊人群用药
     */
    private String special;
    /**
     * 药理毒理
     */
    private String yaoli;
    /**
     * 药物相互作用
     */
    private String xianghuzhuoyong;
    /**
     * 药代动力学
     */
    private String donglixue;


    /**
     * 核准日期
     */
    private String hezhunri;
    /**
     * 修改日期
     */
    private String xiugairi;

}
