package com.hlsofttech.entity.analysis;

import java.io.Serializable;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.hlsofttech.base.BaseModel;
import com.hlsofttech.utils.IntegerUtils;
import io.swagger.annotations.ApiModelProperty;
/**
 * 应用接口申请信息
 * @author zuoqb123
 * @date 2019-03-25
 */
@TableName("sys_plat_interface")
public class SysPlatInterface extends BaseModel<SysPlatInterface> {

   private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
   @ApiModelProperty(name="主键",value="id",dataType="String")
   private String id;
    /**
     * 平台主键
     */
   @ApiModelProperty(name="平台主键",value="platId",dataType="String")
   @TableField("plat_id")
   private String platId;
    /**
     * 应用key
     */
   @ApiModelProperty(name="应用key",value="appKey",dataType="String")
   @TableField("app_key")
   private String appKey;
    /**
     * 类型：1-算法、2-翻译
     */
   @ApiModelProperty(name="类型：1-算法、2-翻译",value="type",dataType="Integer")
   private Integer type;
    /**
     * 状态
     */
   @ApiModelProperty(name="审批状态 0-驳回 1-使用中 3-关闭 2-已过期  9-未审批 5-重新申请 ",value="backStatus",dataType="Integer")
   @TableField("back_status")
   private Integer backStatus;
    /**
     * 接口
     */
   @ApiModelProperty(name="接口",value="interfCode",dataType="String")
   @TableField("interf_code")
   private String interfCode;
    /**
     * 接口名称
     */
   @ApiModelProperty(name="接口名称",value="interfName",dataType="String")
   @TableField("interf_name")
   private String interfName;
    /**
     * 有效开始时间
     */
   @ApiModelProperty(name="有效开始时间",value="validityBegin",dataType="Date")
   @TableField("validity_begin")
   private Date validityBegin;
    /**
     * 有效截止时间
     */
   @ApiModelProperty(name="有效截止时间",value="validityEnd",dataType="Date")
   @TableField("validity_end")
   private Date validityEnd;
    /**
     * 创建者
     */
   @ApiModelProperty(name="创建者",value="createBy",dataType="String")
   @TableField("create_by")
   private String createBy;
    /**
     * 创建时间
     */
   @ApiModelProperty(name="创建时间",value="createDate",dataType="Date")
   @TableField("create_date")
   private Date createDate;
    /**
     * 更新者
     */
   @ApiModelProperty(name="更新者",value="updateBy",dataType="String")
   @TableField("update_by")
   private String updateBy;
    /**
     * 更新时间
     */
   @ApiModelProperty(name="更新时间",value="updateDate",dataType="Date")
   @TableField("update_date")
   private Date updateDate;
    /**
     * 备注信息
     */
   @ApiModelProperty(name="备注信息",value="remarks",dataType="String")
   private String remarks;
    /**
     * 删除标记
     */
   @ApiModelProperty(name="删除标记",value="delFlag",dataType="String")
   @TableField("del_flag")
   private String delFlag;

   @TableField(exist=false)
   private String platName;// 平台名称
   @TableField(exist=false)
   private String description;// 应用说明
   @TableField(exist=false)
   private String secretKey;// 平台秘钥
   
   @TableField(exist=false)
   private String cdateBegin;// 创建时间开始
   @TableField(exist=false)
   private String cdateEnd;// 创建时间结束
   @TableField(exist=false)
   private String statusName;// 审批状态 0-驳回 1-使用中 3-关闭 2-已过期  9-未审批 5-重新申请
   
   
   public String getSecretKey() {
    return secretKey;
  }

  public void setSecretKey(String secretKey) {
    this.secretKey = secretKey;
  }

  public String getStatusName() {
     return statusName;
  }

  public void setStatusName(String statusName) {
    this.statusName = statusName;
  }

  public String getCdateBegin() {
    return cdateBegin;
  }

  public void setCdateBegin(String cdateBegin) {
    this.cdateBegin = cdateBegin;
  }

  public String getCdateEnd() {
    return cdateEnd;
  }

  public void setCdateEnd(String cdateEnd) {
    this.cdateEnd = cdateEnd;
  }

  public String getPlatName() {
    return platName;
  }

  public void setPlatName(String platName) {
    this.platName = platName;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getId() {
      return id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public String getPlatId() {
      return platId;
   }

   public void setPlatId(String platId) {
      this.platId = platId;
   }

   public String getAppKey() {
      return appKey;
   }

   public void setAppKey(String appKey) {
      this.appKey = appKey;
   }

   public Integer getType() {
      return type;
   }

   public void setType(Integer type) {
      this.type = type;
   }

  public Integer getBackStatus() {
    return backStatus;
  }

  public void setBackStatus(Integer backStatus) {
    this.backStatus = backStatus;
  }

   public String getInterfCode() {
      return interfCode;
   }

   public void setInterfCode(String interfCode) {
      this.interfCode = interfCode;
   }

   public String getInterfName() {
      return interfName;
   }

   public void setInterfName(String interfName) {
      this.interfName = interfName;
   }

   public Date getValidityBegin() {
      return validityBegin;
   }

   public void setValidityBegin(Date validityBegin) {
      this.validityBegin = validityBegin;
   }

   public Date getValidityEnd() {
      return validityEnd;
   }

   public void setValidityEnd(Date validityEnd) {
      this.validityEnd = validityEnd;
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
      return "SysPlatInterface{" +
         ", id=" + id +
         ", platId=" + platId +
         ", appKey=" + appKey +
         ", type=" + type +
         ", backStatus=" + backStatus +
         ", interfCode=" + interfCode +
         ", interfName=" + interfName +
         ", validityBegin=" + validityBegin +
         ", validityEnd=" + validityEnd +
         ", createBy=" + createBy +
         ", createDate=" + createDate +
         ", updateBy=" + updateBy +
         ", updateDate=" + updateDate +
         ", remarks=" + remarks +
         ", delFlag=" + delFlag +
         "}";
   }
}
