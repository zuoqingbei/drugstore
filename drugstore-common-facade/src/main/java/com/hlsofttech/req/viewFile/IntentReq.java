package com.hlsofttech.req.viewFile;

import com.hlsofttech.req.AbsReq;

/**
 * @author zuoqb
 * @date 2017/11/4 下午4:53
 * @description跳转请求
 */
public class IntentReq extends AbsReq {

    private String to="index";//目标页面名称
    private String accessToken;


	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

   
}
