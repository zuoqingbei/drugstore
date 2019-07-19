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
 * 
 * @author zuoqb123
 * @date 2019-03-13
 */
@TableName("common_interface_exc_log")
public class CommonInterfaceExcLog extends BaseModel<CommonInterfaceExcLog> {

   private static final long serialVersionUID = 1L;

    /**
     * 键
     */
   @ApiModelProperty(name="键",value="id",dataType="String")
   private String id;
    /**
     * 查询指标标识
     */
   @ApiModelProperty(name="查询指标标识",value="dataType",dataType="String")
   @TableField("data_type")
   private String dataType;
    /**
     * 命名空间
     */
   @ApiModelProperty(name="命名空间",value="dataSpace",dataType="String")
   @TableField("data_space")
   private String dataSpace;
    /**
     * 查询日期坐标
     */
   @ApiModelProperty(name="查询日期坐标",value="dateDt",dataType="String")
   @TableField("date_dt")
   private String dateDt;
    /**
     * 开始执行时间
     */
   @ApiModelProperty(name="开始执行时间",value="beginTime",dataType="Date")
   @TableField("begin_time")
   private Date beginTime;
    /**
     * 结束时间
     */
   @ApiModelProperty(name="结束时间",value="endTime",dataType="Date")
   @TableField("end_time")
   private Date endTime;
    /**
     * R执行中，F执行结果异常，S执行结果正常
     */
   @ApiModelProperty(name="R执行中，F执行结果异常，S执行结果正常",value="excStatus",dataType="String")
   @TableField("exc_status")
   private String excStatus;
    /**
     * 错误重复执行次数
     */
   @ApiModelProperty(name="错误重复执行次数",value="errorExcNum",dataType="Integer")
   @TableField("error_exc_num")
   private Integer errorExcNum;
    /**
     * 执行结果内容，以json格式保存
     */
   @ApiModelProperty(name="执行结果内容，以json格式保存",value="result",dataType="String")
   private String result;
    /**
     * 缓存过期状态，1正在使用，0已过期(数据不准，不要作为执行依据)
     */
   @ApiModelProperty(name="缓存过期状态，1正在使用，0已过期(数据不准，不要作为执行依据)",value="cacheStatus",dataType="String")
   @TableField("cache_status")
   private String cacheStatus;
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
	
	@ApiModelProperty(value="参数",name="params",dataType="String")
	@TableField("params")
	private String params;

   public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
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

public String getId() {
      return id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public String getDataType() {
      return dataType;
   }

   public void setDataType(String dataType) {
      this.dataType = dataType;
   }

   public String getDataSpace() {
      return dataSpace;
   }

   public void setDataSpace(String dataSpace) {
      this.dataSpace = dataSpace;
   }

   public String getDateDt() {
      return dateDt;
   }

   public void setDateDt(String dateDt) {
      this.dateDt = dateDt;
   }

   public Date getBeginTime() {
      return beginTime;
   }

   public void setBeginTime(Date beginTime) {
      this.beginTime = beginTime;
   }

   public Date getEndTime() {
      return endTime;
   }

   public void setEndTime(Date endTime) {
      this.endTime = endTime;
   }

   public String getExcStatus() {
      return excStatus;
   }

   public void setExcStatus(String excStatus) {
      this.excStatus = excStatus;
   }

   public Integer getErrorExcNum() {
      return errorExcNum;
   }

   public void setErrorExcNum(Integer errorExcNum) {
      this.errorExcNum = errorExcNum;
   }

   public String getResult() {
      return result;
   }

   public void setResult(String result) {
      this.result = result;
   }

   public String getCacheStatus() {
      return cacheStatus;
   }

   public void setCacheStatus(String cacheStatus) {
      this.cacheStatus = cacheStatus;
   }

   @Override
   protected Serializable pkVal() {
      return this.id;
   }

   @Override
   public String toString() {
      return "CommonInterfaceExcLog{" +
         ", id=" + id +
         ", dataType=" + dataType +
         ", dataSpace=" + dataSpace +
         ", dateDt=" + dateDt +
         ", beginTime=" + beginTime +
         ", endTime=" + endTime +
         ", excStatus=" + excStatus +
         ", errorExcNum=" + errorExcNum +
         ", result=" + result +
         ", cacheStatus=" + cacheStatus +
         "}";
   }
}
