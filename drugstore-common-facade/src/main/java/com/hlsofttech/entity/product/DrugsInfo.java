package com.hlsofttech.entity.product;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.hlsofttech.base.BaseModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 药品库-药品信息表
 *
 * @author suncy
 * @date 2019-08-14
 */
@TableName("drugs_info")
@Data
public class DrugsInfo extends BaseModel<DrugsInfo> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty(name = "id", value = "主键", dataType = "String")
    private String id;
    /**
     * 唯一码
     */
    @ApiModelProperty(name = "guid", value = "唯一码", dataType = "String")
    private String guid;
    /**
     * 药品名称
     */
    @ApiModelProperty(name = "drugName", value = "药品名称", dataType = "String")
    @TableField("drug_name")
    private String drugName;
    /**
     * 一级分类
     */
    @ApiModelProperty(name = "c1Id", value = "一级分类", dataType = "Integer")
    @TableField("c1_id")
    private Integer c1Id;
    /**
     * 二级分类
     */
    @ApiModelProperty(name = "c2Id", value = "二级分类", dataType = "Integer")
    @TableField("c2_id")
    private Integer c2Id;
    /**
     * 三级分类
     */
    @ApiModelProperty(name = "c3Id", value = "三级分类", dataType = "Integer")
    @TableField("c3_id")
    private Integer c3Id;
    /**
     * 标签
     */
    @ApiModelProperty(name = "tags", value = "标签", dataType = "String")
    private String tags;
    /**
     * 是否外用药
     */
    @ApiModelProperty(name = "isExternalUsed", value = "是否外用药", dataType = "String")
    @TableField("is_external_used")
    private String isExternalUsed;
    /**
     * 是否OTC
     */
    @ApiModelProperty(name = "otcOrRx", value = "是否OTC", dataType = "String")
    @TableField("otc_or_rx")
    private String otcOrRx;
    /**
     * OTC级别
     */
    @ApiModelProperty(name = "otcLevel", value = "OTC级别", dataType = "String")
    @TableField("otc_level")
    private String otcLevel;
    /**
     * 价格
     */
    @ApiModelProperty(name = "price", value = "价格", dataType = "Integer")
    private Integer price;
    /**
     * 通用名
     */
    @ApiModelProperty(name = "commonName", value = "通用名", dataType = "String")
    @TableField("common_name")
    private String commonName;
    /**
     * 商品名称
     */
    @ApiModelProperty(name = "productName", value = "商品名称", dataType = "String")
    @TableField("product_name")
    private String productName;
    /**
     * 批准文号
     */
    @ApiModelProperty(name = "approvalNumber", value = "批准文号", dataType = "String")
    @TableField("approval_number")
    private String approvalNumber;
    /**
     * 生产企业
     */
    @ApiModelProperty(name = "manufacturer", value = "生产企业", dataType = "String")
    private String manufacturer;
    /**
     * 产品图片
     */
    @ApiModelProperty(name = "productImages", value = "产品图片", dataType = "String")
    @TableField("product_images")
    private String productImages;
    /**
     * 规格
     */
    @ApiModelProperty(name = "specification", value = "规格", dataType = "String")
    private String specification;
    /**
     * 有效期
     */
    @ApiModelProperty(name = "validityPeriod", value = "有效期", dataType = "Date")
    @TableField("validity_period")
    private Date validityPeriod;
    /**
     * 剂型
     */
    @ApiModelProperty(name = "formulation", value = "剂型", dataType = "String")
    private String formulation;
    /**
     * 适应症
     */
    @ApiModelProperty(name = "indication", value = "适应症", dataType = "String")
    private String indication;
    /**
     * 用法用量
     */
    @ApiModelProperty(name = "dosage", value = "用法用量", dataType = "String")
    private String dosage;
    /**
     * 不良反应
     */
    @ApiModelProperty(name = "adverseReactions", value = "不良反应", dataType = "String")
    @TableField("adverse_reactions")
    private String adverseReactions;
    /**
     * 温馨提示
     */
    @ApiModelProperty(name = "tips", value = "温馨提示", dataType = "String")
    private String tips;
    /**
     * 成份
     */
    @ApiModelProperty(name = "chocolateIngredient", value = "成份", dataType = "String")
    @TableField("chocolate_ingredient")
    private String chocolateIngredient;
    /**
     * 性状
     */
    @ApiModelProperty(name = "traits", value = "性状", dataType = "String")
    private String traits;
    /**
     * 作用类别
     */
    @ApiModelProperty(name = "effectCategory", value = "作用类别", dataType = "String")
    @TableField("effect_category")
    private String effectCategory;
    /**
     * 禁忌
     */
    @ApiModelProperty(name = "taboo", value = "禁忌", dataType = "String")
    private String taboo;
    /**
     * 注意事项
     */
    @ApiModelProperty(name = "precautions", value = "注意事项", dataType = "String")
    private String precautions;
    /**
     * 药物相互作用
     */
    @ApiModelProperty(name = "medicineInteractions", value = "药物相互作用", dataType = "String")
    @TableField("medicine_interactions")
    private String medicineInteractions;
    /**
     * 药理作用
     */
    @ApiModelProperty(name = "pharmacologicalAction", value = "药理作用", dataType = "String")
    @TableField("pharmacological_action")
    private String pharmacologicalAction;
    /**
     * 贮藏
     */
    @ApiModelProperty(name = "storage", value = "贮藏", dataType = "String")
    private String storage;
    /**
     * 包装
     */
    @ApiModelProperty(name = "subpackage", value = "包装", dataType = "String")
    private String subpackage;
    /**
     * 状态（0启用1=停用）
     */
    @ApiModelProperty(name = "status", value = "状态（0启用1=停用）", dataType = "String")
    private String status;
    /**
     * 创建人
     */
    @ApiModelProperty(name = "createBy", value = "创建人", dataType = "String")
    @TableField("create_by")
    private String createBy;
    /**
     * 创建时间
     */
    @ApiModelProperty(name = "createDate", value = "创建时间", dataType = "Date")
    @TableField("create_date")
    private Date createDate;
    /**
     * 修改人
     */
    @ApiModelProperty(name = "updateBy", value = "修改人", dataType = "String")
    @TableField("update_by")
    private String updateBy;
    /**
     * 修改时间
     */
    @ApiModelProperty(name = "updateDate", value = "修改时间", dataType = "Date")
    @TableField("update_date")
    private Date updateDate;
    /**
     * 备注
     */
    @ApiModelProperty(name = "remarks", value = "备注", dataType = "String")
    private String remarks;
    /**
     * 删除标识（1=已删除0未删除）
     */
    @ApiModelProperty(name = "delFlag", value = "删除标识（1=已删除0未删除）", dataType = "String")
    @TableField("del_flag")
    private String delFlag;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
