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
@TableName("common_interface_exc_history")
public class CommonInterfaceExcHistory extends
		BaseModel<CommonInterfaceExcHistory> {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(name = "", value = "id", dataType = "String")
	private String id;
	/**
	 * 接口编码
	 */
	@ApiModelProperty(name = "接口编码", value = "dataExcId", dataType = "String")
	@TableField("data_exc_id")
	private String dataExcId;
	/**
	 * 查询指标标识
	 */
	@ApiModelProperty(name = "查询指标标识", value = "dataType", dataType = "String")
	@TableField("data_type")
	private String dataType;
	/**
	 * 命名空间
	 */
	@ApiModelProperty(name = "命名空间", value = "dataSpace", dataType = "String")
	@TableField("data_space")
	private String dataSpace;
	/**
	 * 需要执行的sql语句，需要传参的位置使用#{参数名}，动态日期类型date_dt_kpi固定参数名称。
	 */
	@ApiModelProperty(name = "需要执行的sql语句，需要传参的位置使用#{参数名}，动态日期类型date_dt_kpi固定参数名称。", value = "dataSql", dataType = "String")
	@TableField("data_sql")
	private String dataSql;
	/**
	 * sql需要传入的参数，如果需要动态日期date_dt_kpi无需配置
	 */
	@ApiModelProperty(name = "sql需要传入的参数，如果需要动态日期date_dt_kpi无需配置", value = "paramId", dataType = "String")
	@TableField("param_id")
	private String paramId;
	/**
	 * 需要历史记录放入缓存的开始时间,根据更新类型（cache_type）
	 */
	@ApiModelProperty(name = "需要历史记录放入缓存的开始时间,根据更新类型（cache_type）", value = "beginDate", dataType = "String")
	@TableField("begin_date")
	private String beginDate;
	/**
	 * 需要查询的时间格式，例如：yyyyMMdd
	 */
	@ApiModelProperty(name = "需要查询的时间格式，例如：yyyyMMdd", value = "dateFormat", dataType = "String")
	@TableField("date_format")
	private String dateFormat;
	/**
	 * 是否需要定时刷新的标识,0不刷新，1刷新
	 */
	@ApiModelProperty(name = "是否需要定时刷新的标识,0不刷新，1刷新", value = "freshFlag", dataType = "Integer")
	@TableField("fresh_flag")
	private Integer freshFlag;
	/**
	 * 数据缓存天数，根据更新类型（cache_type）判断
	 */
	@ApiModelProperty(name = "数据缓存天数，根据更新类型（cache_type）判断", value = "updateDays", dataType = "Integer")
	@TableField("update_days")
	private Integer updateDays;
	/**
	 * 定任务执行时间偏移量，默认执行时间减一天。注：该设置只对定时任务有效。
	 */
	@ApiModelProperty(name = "定任务执行时间偏移量，默认执行时间减一天。注：该设置只对定时任务有效。", value = "timerOffset", dataType = "Integer")
	@TableField("timer_offset")
	private Integer timerOffset;
	/**
	 * Cron表达式是一个字符串配置定时任务执行时间
	 */
	@ApiModelProperty(name = "Cron表达式是一个字符串配置定时任务执行时间", value = "excTime", dataType = "String")
	@TableField("exc_time")
	private String excTime;
	/**
	 * 放入缓存的方式，按天数存放（0），或者按开始时间存放（1）
	 */
	@ApiModelProperty(name = "放入缓存的方式，按天数存放（0），或者按开始时间存放（1）", value = "cacheType", dataType = "Integer")
	@TableField("cache_type")
	private Integer cacheType;
	/**
	 * 横竖数据格式转换，默认纵向排列（V：垂直排列，H水平排列）
	 */
	@ApiModelProperty(name = "横竖数据格式转换，默认纵向排列（V：垂直排列，H水平排列）", value = "transformData", dataType = "String")
	@TableField("transform_data")
	private String transformData;
	/**
	 * 创建时间
	 */
	@ApiModelProperty(name = "创建时间", value = "createTime", dataType = "Date")
	@TableField("create_time")
	private Date createTime;
	/**
	 * 最后更新主体
	 */
	@ApiModelProperty(name = "最后更新主体", value = "lastUpdateBy", dataType = "String")
	@TableField("last_update_by")
	private String lastUpdateBy;
	/**
	 * 最后更新时间
	 */
	@ApiModelProperty(name = "最后更新时间", value = "lastUpdateTime", dataType = "Date")
	@TableField("last_update_time")
	private Date lastUpdateTime;
	/**
	 * 备注
	 */
	@ApiModelProperty(name = "备注", value = "remark", dataType = "String")
	private String remark;

	/**
	 * 创建者
	 */
	@ApiModelProperty(value = "创建者", name = "createBy", dataType = "String")
	@TableField("create_by")
	private String createBy;
	/**
	 * 创建时间
	 */
	@ApiModelProperty(value = "创建时间", name = "createDate", dataType = "Date")
	@TableField("create_date")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	// 去掉时间后面的.0
	private Date createDate;
	/**
	 * 更新者
	 */
	@ApiModelProperty(value = "更新者", name = "updateBy", dataType = "String")
	@TableField("update_by")
	private String updateBy;
	/**
	 * 更新时间
	 */
	@ApiModelProperty(value = "更新时间", name = "updateDate", dataType = "Date")
	@TableField("update_date")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	// 去掉时间后面的.0
	private Date updateDate;
	/**
	 * 备注信息
	 */
	@ApiModelProperty(value = "备注", name = "remarks", dataType = "String")
	private String remarks;
	/**
	 * 删除标记
	 */
	@ApiModelProperty(value = "删除标记", name = "delFlag", dataType = "String")
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

	public String getDataExcId() {
		return dataExcId;
	}

	public void setDataExcId(String dataExcId) {
		this.dataExcId = dataExcId;
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

	public String getDataSql() {
		return dataSql;
	}

	public void setDataSql(String dataSql) {
		this.dataSql = dataSql;
	}

	public String getParamId() {
		return paramId;
	}

	public void setParamId(String paramId) {
		this.paramId = paramId;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getDateFormat() {
		return dateFormat;
	}

	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}

	public Integer getFreshFlag() {
		return freshFlag;
	}

	public void setFreshFlag(Integer freshFlag) {
		this.freshFlag = freshFlag;
	}

	public Integer getUpdateDays() {
		return updateDays;
	}

	public void setUpdateDays(Integer updateDays) {
		this.updateDays = updateDays;
	}

	public Integer getTimerOffset() {
		return timerOffset;
	}

	public void setTimerOffset(Integer timerOffset) {
		this.timerOffset = timerOffset;
	}

	public String getExcTime() {
		return excTime;
	}

	public void setExcTime(String excTime) {
		this.excTime = excTime;
	}

	public Integer getCacheType() {
		return cacheType;
	}

	public void setCacheType(Integer cacheType) {
		this.cacheType = cacheType;
	}

	public String getTransformData() {
		return transformData;
	}

	public void setTransformData(String transformData) {
		this.transformData = transformData;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getLastUpdateBy() {
		return lastUpdateBy;
	}

	public void setLastUpdateBy(String lastUpdateBy) {
		this.lastUpdateBy = lastUpdateBy;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "CommonInterfaceExcHistory{" + ", id=" + id + ", dataExcId="
				+ dataExcId + ", dataType=" + dataType + ", dataSpace="
				+ dataSpace + ", dataSql=" + dataSql + ", paramId=" + paramId
				+ ", beginDate=" + beginDate + ", dateFormat=" + dateFormat
				+ ", freshFlag=" + freshFlag + ", updateDays=" + updateDays
				+ ", timerOffset=" + timerOffset + ", excTime=" + excTime
				+ ", cacheType=" + cacheType + ", transformData="
				+ transformData + ", createTime=" + createTime
				+ ", lastUpdateBy=" + lastUpdateBy + ", lastUpdateTime="
				+ lastUpdateTime + ", remark=" + remark + "}";
	}
}
