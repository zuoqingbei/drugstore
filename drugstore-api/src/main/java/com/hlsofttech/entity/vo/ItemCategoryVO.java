package com.hlsofttech.entity.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/***
 * @Description: 药品分类VO
 * @Date: 2019/8/13 10:35
 * @Author: suncy
 **/
@Data
public class ItemCategoryVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    /**
     * 上级分类id,如果没有表示是一级分类,直接上级的id
     */
    private Long parentCode;
    /**
     * logo图片
     */
    private String iconUrl;
    /**
     * 名称
     */
    private String categoryName;
    /**
     * 删除状态，0未删除，1已删除
     */
    private Integer status;
    /**
     * 排序
     */
    private Integer treeSort;
    /**
     * 链接地址
     */
    private String pathUrl;
    /**
     * 分类名字的简称
     */
    private String categoryNameShort;
    /**
     * banner图地址
     */
    private String bannerUrl;

    /**
     * 是否最末级
     */
    private String treeLeaf;
    /**
     * 所有父级编号
     */
    private String parentCodes;
    /**
     * 层次级别,0是顶级。
     */
    private BigDecimal treeLevel;
    /**
     * 所有父级排序号
     */
    private String treeSorts;
    /**
     * 全节点名
     */
    private String treeNames;
    /**
     * 上架状态:1上架,0下架
     */
    private Integer sellStatus;
    /**
     * 子集
     */
    private List<ItemCategoryVO> childNode = new ArrayList<>();
}
