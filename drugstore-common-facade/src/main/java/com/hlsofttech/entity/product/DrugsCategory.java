package com.hlsofttech.entity.product;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.hlsofttech.base.BaseModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 药品库-药品分类
 *
 * @author suncy
 * @date 2019-08-02
 */
@Data
@TableName("drugs_category")
public class DrugsCategory extends BaseModel<DrugsCategory> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty(name = "id", value = "主键", dataType = "String")
    private String id;
    /**
     * 分类名称
     */
    @ApiModelProperty(name = "title", value = "分类名称", dataType = "String")
    private String title;
    /**
     * 父ID
     */
    @ApiModelProperty(name = "parentId", value = "父ID", dataType = "String")
    @TableField("parent_id")
    private String parentId;
    /**
     * 级别1/2/3
     */
    @ApiModelProperty(name = "level", value = "级别1/2/3", dataType = "String")
    private String level;
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
