package com.hlsofttech.entity.analysis;

import java.io.Serializable;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.hlsofttech.base.BaseModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * 平台信息
 * @author zuoqb123
 * @date 2019-03-25
 */
@TableName("sys_plat_info")
public class SysPlatInfo extends BaseModel<SysPlatInfo> {

   private static final long serialVersionUID = 1L;

    /**
     * 平台编号
     */
   @ApiModelProperty(name="平台编号",value="id",dataType="String")
   private String id;
    /**
     * 平台名称
     */
   @ApiModelProperty(name="平台名称",value="name",dataType="String")
   private String name;
    /**
     * 平台英文名称
     */
   @ApiModelProperty(name="平台英文名称",value="enname",dataType="String")
   private String enname;
    /**
     * 接口版本
     */
   @ApiModelProperty(name="接口版本",value="versions",dataType="String")
   private String versions;
    /**
     * 应用说明
     */
   @ApiModelProperty(name="应用说明",value="description",dataType="String")
   private String description;
    /**
     * 状态
     */
   @ApiModelProperty(name="状态",value="status",dataType="Integer")
   private Integer status;
    /**
     * 应用key
     */
   @ApiModelProperty(name="应用key",value="appKey",dataType="String")
   @TableField("app_key")
   private String appKey;
    /**
     * 平台秘钥 X-Sign
     */
   @ApiModelProperty(name="平台秘钥 X-Sign",value="secretKey",dataType="String")
   @TableField("secret_key")
   private String secretKey;
    /**
     * 平台联系人
     */
   @ApiModelProperty(name="平台联系人",value="contacts",dataType="String")
   private String contacts;
    /**
     * 联系人电话
     */
   @ApiModelProperty(name="联系人电话",value="contactsTel",dataType="String")
   @TableField("contacts_tel")
   private String contactsTel;
    /**
     * 联系人邮箱
     */
   @ApiModelProperty(name="联系人邮箱",value="contactsMail",dataType="String")
   @TableField("contacts_mail")
   private String contactsMail;
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


   public String getId() {
      return id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getEnname() {
      return enname;
   }

   public void setEnname(String enname) {
      this.enname = enname;
   }

   public String getVersions() {
      return versions;
   }

   public void setVersions(String versions) {
      this.versions = versions;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public Integer getStatus() {
      return status;
   }

   public void setStatus(Integer status) {
      this.status = status;
   }

   public String getAppKey() {
      return appKey;
   }

   public void setAppKey(String appKey) {
      this.appKey = appKey;
   }

   public String getSecretKey() {
      return secretKey;
   }

   public void setSecretKey(String secretKey) {
      this.secretKey = secretKey;
   }

   public String getContacts() {
      return contacts;
   }

   public void setContacts(String contacts) {
      this.contacts = contacts;
   }

   public String getContactsTel() {
      return contactsTel;
   }

   public void setContactsTel(String contactsTel) {
      this.contactsTel = contactsTel;
   }

   public String getContactsMail() {
      return contactsMail;
   }

   public void setContactsMail(String contactsMail) {
      this.contactsMail = contactsMail;
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
      return "SysPlatInfo{" +
         ", id=" + id +
         ", name=" + name +
         ", enname=" + enname +
         ", versions=" + versions +
         ", description=" + description +
         ", status=" + status +
         ", appKey=" + appKey +
         ", secretKey=" + secretKey +
         ", contacts=" + contacts +
         ", contactsTel=" + contactsTel +
         ", contactsMail=" + contactsMail +
         ", createBy=" + createBy +
         ", createDate=" + createDate +
         ", updateBy=" + updateBy +
         ", updateDate=" + updateDate +
         ", remarks=" + remarks +
         ", delFlag=" + delFlag +
         "}";
   }
}
