package com.hlsofttech.entity.shop;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.hlsofttech.base.BaseModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 药店-配送范围
 *
 * @author suncy
 * @date 2019-08-16
 */
@Data
@TableName("drugs_shop_delivery_scope")
public class DrugsShopDeliveryScope extends BaseModel<DrugsShopDeliveryScope> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @ApiModelProperty(name = "id", value = "主键id", dataType = "Long")
    private String id;
    /**
     * 自定义范围名称
     */
    @ApiModelProperty(name = "title", value = "自定义范围名称", dataType = "String")
    private String title;
    /**
     * 起送价
     */
    @ApiModelProperty(name = "startPrice", value = "起送价", dataType = "Integer")
    @TableField("start_price")
    private Integer startPrice;
    /**
     * 配送范围开始
     */
    @ApiModelProperty(name = "scopeBegin", value = "配送范围开始", dataType = "Integer")
    @TableField("scope_begin")
    private Integer scopeBegin;
    /**
     * 配送范围结束
     */
    @ApiModelProperty(name = "scopeEnd", value = "配送范围结束", dataType = "Integer")
    @TableField("scope_end")
    private Integer scopeEnd;
    /**
     * 配送费（分）
     */
    @ApiModelProperty(name = "deliveryFee", value = "配送费（分）", dataType = "Integer")
    @TableField("delivery_fee")
    private Integer deliveryFee;
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
