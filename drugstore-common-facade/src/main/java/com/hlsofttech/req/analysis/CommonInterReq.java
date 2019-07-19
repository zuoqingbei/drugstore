package com.hlsofttech.req.analysis;

import com.hlsofttech.req.AbsReq;

/**
 * 
 * @time   2019年3月14日 上午11:23:28
 * @author zuoqb
 * @todo   统一接口执行
 */
public class CommonInterReq extends AbsReq {

    private String sql;

    private String dataSourceId;

    private String params;
    
    private boolean isVertical;

    private String dataType;

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public String getDataSourceId() {
		return dataSourceId;
	}

	public void setDataSourceId(String dataSourceId) {
		this.dataSourceId = dataSourceId;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public boolean isVertical() {
		return isVertical;
	}

	public void setVertical(boolean isVertical) {
		this.isVertical = isVertical;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

  
}
