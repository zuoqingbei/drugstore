package com.hlsofttech.entity.shop;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.hlsofttech.base.BaseModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 药店签约信息
 *
 * @author suntf123
 * @date 2019-08-12
 */
@Data
@TableName("drugs_shop_info")
public class DrugsShopInfo extends BaseModel<DrugsShopInfo> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @ApiModelProperty(name = "id", value = "主键id", dataType = "Long")
    private String id;
    /**
     * 营业执照统一信用代码
     */
    @ApiModelProperty(name = "code", value = "营业执照统一信用代码", dataType = "String")
    private String code;
    /**
     * 签约方key
     */
    @ApiModelProperty(name = "appKey", value = "签约方key", dataType = "String")
    @TableField("app_key")
    private String appKey;
    /**
     * 签约密钥
     */
    @ApiModelProperty(name = "appSecret", value = "签约密钥", dataType = "String")
    @TableField("app_secret")
    private String appSecret;
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
