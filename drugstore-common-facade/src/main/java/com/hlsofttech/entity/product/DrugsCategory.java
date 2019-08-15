package com.hlsofttech.entity.product;

import java.io.Serializable;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.hlsofttech.base.BaseModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * 药品库-药品分类
 * @author suncy
 * @date 2019-08-02
 */
@TableName("drugs_category")
public class DrugsCategory extends BaseModel<DrugsCategory> {

   private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
   @ApiModelProperty(name="id",value="主键",dataType="String")
   private String id;
    /**
     * 分类名称
     */
   @ApiModelProperty(name="title",value="分类名称",dataType="String")
   private String title;
    /**
     * 父ID
     */
   @ApiModelProperty(name="parentId",value="父ID",dataType="String")
   @TableField("parent_id")
   private String parentId;
    /**
     * 级别1/2/3
     */
   @ApiModelProperty(name="level",value="级别1/2/3",dataType="String")
   private String level;
    /**
     * 状态（0启用1=停用）
     */
   @ApiModelProperty(name="status",value="状态（0启用1=停用）",dataType="String")
   private String status;
    /**
     * 创建人
     */
   @ApiModelProperty(name="createBy",value="创建人",dataType="String")
   @TableField("create_by")
   private String createBy;
    /**
     * 创建时间
     */
   @ApiModelProperty(name="createDate",value="创建时间",dataType="Date")
   @TableField("create_date")
   private Date createDate;
    /**
     * 修改人
     */
   @ApiModelProperty(name="updateBy",value="修改人",dataType="String")
   @TableField("update_by")
   private String updateBy;
    /**
     * 修改时间
     */
   @ApiModelProperty(name="updateDate",value="修改时间",dataType="Date")
   @TableField("update_date")
   private Date updateDate;
    /**
     * 备注
     */
   @ApiModelProperty(name="remarks",value="备注",dataType="String")
   private String remarks;
    /**
     * 删除标识（1=已删除0未删除）
     */
   @ApiModelProperty(name="delFlag",value="删除标识（1=已删除0未删除）",dataType="String")
   @TableField("del_flag")
   private String delFlag;


   public String getId() {
      return id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public String getTitle() {
      return title;
   }

   public void setTitle(String title) {
      this.title = title;
   }

   public String getParentId() {
      return parentId;
   }

   public void setParentId(String parentId) {
      this.parentId = parentId;
   }

   public String getLevel() {
      return level;
   }

   public void setLevel(String level) {
      this.level = level;
   }

   public String getStatus() {
      return status;
   }

   public void setStatus(String status) {
      this.status = status;
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
      return "DrugsCategory{" +
         ", id=" + id +
         ", title=" + title +
         ", parentId=" + parentId +
         ", level=" + level +
         ", status=" + status +
         ", createBy=" + createBy +
         ", createDate=" + createDate +
         ", updateBy=" + updateBy +
         ", updateDate=" + updateDate +
         ", remarks=" + remarks +
         ", delFlag=" + delFlag +
         "}";
   }
}
