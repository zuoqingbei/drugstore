package com.hlsofttech.base;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * 
 * @time   2018年9月26日 下午2:26:29
 * @author zuoqb
 * @todo   通用实体类，包含公共属性
 */
public abstract class BaseModel<T extends Model> extends Model<T> {
	
	/**
	 * 排序字段 值必须对照表中字段
	 */
	@ApiModelProperty(value="排序字段 值必须对照表中字段",name="orderBy",dataType="String")
	@TableField(exist = false)
	private String orderBy;
	/**
	 * 排序方式  默认升序排列
	 */
	@ApiModelProperty(value="是否为升序  降序：true；升序-false",name="asc",dataType="boolean")
	@TableField(exist = false)
	private boolean asc=true   ;//是否为升序;
	@TableField(exist = false)
	/**
	 * 开始时间（用于检索创建时间）
	 */
	@ApiModelProperty(value="检索开始时间",name="startDate",dataType="Date")
	private Date startDate;
	@TableField(exist = false)
	/**
	 * 结束时间（用于检索创建时间）
	 */
	@ApiModelProperty(value="检索结束时间",name="endDate",dataType="Date")
	private Date endDate;
	
	public String getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	public boolean isAsc() {
		return asc;
	}
	public void setAsc(boolean asc) {
		this.asc = asc;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

}
