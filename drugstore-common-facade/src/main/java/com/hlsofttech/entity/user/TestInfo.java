package com.hlsofttech.entity.user;

import java.io.Serializable;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.hlsofttech.base.BaseModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * 新闻收藏
 * @author suncy123
 * @date 2019-07-23
 */
@TableName("test_info")
public class TestInfo extends BaseModel<TestInfo> {

   private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
   @ApiModelProperty(name="id",value="编号",dataType="String")
   private String id;
    /**
     * 用户编码
     */
   @ApiModelProperty(name="userId",value="用户编码",dataType="String")
   @TableField("user_id")
   private String userId;
    /**
     * 新闻编码编码
     */
   @ApiModelProperty(name="newsId",value="新闻编码编码",dataType="String")
   @TableField("news_id")
   private String newsId;
    /**
     * 类型 媒体/社交/专利
     */
   @ApiModelProperty(name="type",value="类型 媒体/社交/专利",dataType="String")
   private String type;
    /**
     * 创建者
     */
   @ApiModelProperty(name="createBy",value="创建者",dataType="String")
   @TableField("create_by")
   private String createBy;
    /**
     * 创建时间
     */
   @ApiModelProperty(name="createDate",value="创建时间",dataType="Date")
   @TableField("create_date")
   private Date createDate;
    /**
     * 更新者
     */
   @ApiModelProperty(name="updateBy",value="更新者",dataType="String")
   @TableField("update_by")
   private String updateBy;
    /**
     * 更新时间
     */
   @ApiModelProperty(name="updateDate",value="更新时间",dataType="Date")
   @TableField("update_date")
   private Date updateDate;
    /**
     * 备注信息
     */
   @ApiModelProperty(name="remarks",value="备注信息",dataType="String")
   private String remarks;
    /**
     * 删除标记
     */
   @ApiModelProperty(name="delFlag",value="删除标记",dataType="String")
   @TableField("del_flag")
   private String delFlag;


   public String getId() {
      return id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public String getUserId() {
      return userId;
   }

   public void setUserId(String userId) {
      this.userId = userId;
   }

   public String getNewsId() {
      return newsId;
   }

   public void setNewsId(String newsId) {
      this.newsId = newsId;
   }

   public String getType() {
      return type;
   }

   public void setType(String type) {
      this.type = type;
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
      return "TestInfo{" +
         ", id=" + id +
         ", userId=" + userId +
         ", newsId=" + newsId +
         ", type=" + type +
         ", createBy=" + createBy +
         ", createDate=" + createDate +
         ", updateBy=" + updateBy +
         ", updateDate=" + updateDate +
         ", remarks=" + remarks +
         ", delFlag=" + delFlag +
         "}";
   }
}
