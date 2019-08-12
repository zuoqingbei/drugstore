package com.hlsofttech.entity.shop;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.hlsofttech.base.BaseModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * 药店签约信息
 *
 * @author suntf123
 * @date 2019-08-12
 */
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


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "DrugsShopInfo{" +
                ", id=" + id +
                ", code=" + code +
                ", appKey=" + appKey +
                ", appSecret=" + appSecret +
                ", createBy=" + createBy +
                ", createDate=" + createDate +
                ", updateBy=" + updateBy +
                ", updateDate=" + updateDate +
                ", remarks=" + remarks +
                ", delFlag=" + delFlag +
                "}";
    }
}
