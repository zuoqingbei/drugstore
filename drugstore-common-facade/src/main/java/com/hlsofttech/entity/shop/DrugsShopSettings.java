package com.hlsofttech.entity.shop;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.hlsofttech.base.BaseModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 药店-相关设置
 *
 * @author suncy
 * @date 2019-08-16
 */
@Data
@TableName("drugs_shop_settings")
public class DrugsShopSettings extends BaseModel<DrugsShopSettings> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @ApiModelProperty(name = "id", value = "主键id", dataType = "Long")
    private String id;
    /**
     * 药店ID
     */
    @ApiModelProperty(name = "drugsShopId", value = "药店ID", dataType = "String")
    @TableField("drugs_shop_id")
    private String drugsShopId;
    /**
     * 配送方式（0=店家配送，1=骑手配送）
     */
    @ApiModelProperty(name = "deliveryWay", value = "配送方式（0=店家配送，1=骑手配送）", dataType = "String")
    @TableField("delivery_way")
    private String deliveryWay;
    /**
     * 常规时间开始
     */
    @ApiModelProperty(name = "routineTimeBegin", value = "常规时间开始", dataType = "String")
    @TableField("routine_time_begin")
    private String routineTimeBegin;
    /**
     * 常规时间结束
     */
    @ApiModelProperty(name = "routineTimeEnd", value = "常规时间结束", dataType = "String")
    @TableField("routine_time_end")
    private String routineTimeEnd;
    /**
     * 特殊时间开始
     */
    @ApiModelProperty(name = "specialTimeBegin", value = "特殊时间开始", dataType = "String")
    @TableField("special_time_begin")
    private String specialTimeBegin;
    /**
     * 特殊时间结束
     */
    @ApiModelProperty(name = "specialTimeEnd", value = "特殊时间结束", dataType = "String")
    @TableField("special_time_end")
    private String specialTimeEnd;
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
