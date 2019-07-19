package com.hlsofttech.entity.analysis;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.hlsofttech.base.BaseModel;

import io.swagger.annotations.ApiModelProperty;
/**
 * 数据源配置
 * @author zuoqb123
 * @date 2019-03-13
 */
@TableName("db_datasource_config")
public class DbDatasourceConfig extends BaseModel<DbDatasourceConfig> {

   private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
   @ApiModelProperty(name="编号",value="id",dataType="String")
   private String id;
    /**
     * 归属平台
     */
   @ApiModelProperty(name="归属平台",value="sysPlatId",dataType="String")
   @TableField("sys_plat_id")
   private String sysPlatId;
    /**
     * 数据源名称
     */
   @ApiModelProperty(name="数据源名称",value="name",dataType="String")
   private String name;
    /**
     * 数据源英文名称
     */
   @ApiModelProperty(name="数据源英文名称",value="enname",dataType="String")
   private String enname;
    /**
     * 数据源类型
     */
   @ApiModelProperty(name="数据源类型",value="dbType",dataType="String")
   @TableField("db_type")
   private String dbType;
    /**
     * 数据源驱动
     */
   @ApiModelProperty(name="数据源驱动",value="dbDiver",dataType="String")
   @TableField("db_diver")
   private String dbDiver;
    /**
     * 数据连接地址
     */
   @ApiModelProperty(name="数据连接地址",value="dbUrl",dataType="String")
   @TableField("db_url")
   private String dbUrl;
    /**
     * 用户名
     */
   @ApiModelProperty(name="用户名",value="dbName",dataType="String")
   @TableField("db_name")
   private String dbName;
    /**
     * 连接密码
     */
   @ApiModelProperty(name="连接密码",value="dbPassword",dataType="String")
   @TableField("db_password")
   private String dbPassword;
    /**
     * 数据库版本
     */
   @ApiModelProperty(name="数据库版本",value="dbVersion",dataType="String")
   @TableField("db_version")
   private String dbVersion;
    /**
     * 最大连接数
     */
   @ApiModelProperty(name="最大连接数",value="maxNum",dataType="Integer")
   @TableField("max_num")
   private Integer maxNum;
   /**
	* 创建者
	*/
	@ApiModelProperty(value="创建者",name="createBy",dataType="String")
	@TableField("create_by")
	private String createBy;
	/**
	 * 创建时间
	 */
	@ApiModelProperty(value="创建时间",name="createDate",dataType="Date")
	@TableField("create_date")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")//去掉时间后面的.0
	private Date createDate;
	/**
	 * 更新者
	 */
	@ApiModelProperty(value="更新者",name="updateBy",dataType="String")
	@TableField("update_by")
	private String updateBy;
	/**
	 * 更新时间
	 */
	@ApiModelProperty(value="更新时间",name="updateDate",dataType="Date")
	@TableField("update_date")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")//去掉时间后面的.0
	private Date updateDate;
	/**
	 * 备注信息
	 */
	@ApiModelProperty(value="备注",name="remarks",dataType="String")
	private String remarks;
	/**
	 * 删除标记
	 */
	@ApiModelProperty(value="删除标记",name="delFlag",dataType="String")
	@TableField("del_flag")
	private String delFlag;

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

public String getId() {
      return id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public String getSysPlatId() {
      return sysPlatId;
   }

   public void setSysPlatId(String sysPlatId) {
      this.sysPlatId = sysPlatId;
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

   public String getDbType() {
      return dbType;
   }

   public void setDbType(String dbType) {
      this.dbType = dbType;
   }

   public String getDbDiver() {
      return dbDiver;
   }

   public void setDbDiver(String dbDiver) {
      this.dbDiver = dbDiver;
   }

   public String getDbUrl() {
      return dbUrl;
   }

   public void setDbUrl(String dbUrl) {
      this.dbUrl = dbUrl;
   }

   public String getDbName() {
      return dbName;
   }

   public void setDbName(String dbName) {
      this.dbName = dbName;
   }

   public String getDbPassword() {
      return dbPassword;
   }

   public void setDbPassword(String dbPassword) {
      this.dbPassword = dbPassword;
   }

   public String getDbVersion() {
      return dbVersion;
   }

   public void setDbVersion(String dbVersion) {
      this.dbVersion = dbVersion;
   }

   public Integer getMaxNum() {
      return maxNum;
   }

   public void setMaxNum(Integer maxNum) {
      this.maxNum = maxNum;
   }

   @Override
   protected Serializable pkVal() {
      return this.id;
   }

   @Override
   public String toString() {
      return "DbDatasourceConfig{" +
         ", id=" + id +
         ", sysPlatId=" + sysPlatId +
         ", name=" + name +
         ", enname=" + enname +
         ", dbType=" + dbType +
         ", dbDiver=" + dbDiver +
         ", dbUrl=" + dbUrl +
         ", dbName=" + dbName +
         ", dbPassword=" + dbPassword +
         ", dbVersion=" + dbVersion +
         ", maxNum=" + maxNum +
         "}";
   }
}
