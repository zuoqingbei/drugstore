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
 * 统一接口
 * 
 * @author zuoqb123
 * @date 2019-03-13
 */
@TableName("common_interface_exc")
public class CommonInterfaceExc extends BaseModel<CommonInterfaceExc> {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(name = "id", value = "编码", dataType = "String")
	private String id;
	/**
	 * 归属数据源 默认当前数据库
	 */
	@ApiModelProperty(value = "归属数据源 默认当前数据库", name = "dbDatasourceId", dataType = "String")
	@TableField("db_datasource_id")
	private String dbDatasourceId;
	/**
	 * 查询指标标识
	 */
	@ApiModelProperty(value = "查询指标标识", name = "dataType", dataType = "String")
	@TableField("data_type")
	private String dataType;
	/**
	 * 命名空间
	 */
	@ApiModelProperty(value = "命名空间", name = "dataSpace", dataType = "String")
	@TableField("data_space")
	private String dataSpace;
	/**
	 * 需要执行的sql语句，需要传参的位置使用#{参数名}，动态日期类型date_dt_kpi固定参数名称。
	 */
	@ApiModelProperty(value = "需要执行的sql语句，需要传参的位置使用#{参数名}，动态日期类型date_dt_kpi固定参数名称。", name = "dataSql", dataType = "String")
	@TableField("data_sql")
	private String dataSql;
	/**
	 * 横竖数据格式转换，默认纵向排列（V：垂直排列，H水平排列）
	 */
	@ApiModelProperty(value = "横竖数据格式转换，默认纵向排列（V：垂直排列，H水平排列）", name = "transformData", dataType = "String")
	@TableField("transform_data")
	private String transformData;

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

	public String getDbDatasourceId() {
		return dbDatasourceId;
	}

	public void setDbDatasourceId(String dbDatasourceId) {
		this.dbDatasourceId = dbDatasourceId;
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

	public String getTransformData() {
		return transformData;
	}

	public void setTransformData(String transformData) {
		this.transformData = transformData;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "CommonInterfaceExc{" + ", id=" + id + ", dbDatasourceId="
				+ dbDatasourceId + ", dataType=" + dataType + ", dataSpace="
				+ dataSpace + ", dataSql=" + dataSql + ", transformData="
				+ transformData + "}";
	}
}
