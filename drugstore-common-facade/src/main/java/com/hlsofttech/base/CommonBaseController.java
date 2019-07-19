package com.hlsofttech.base;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hlsofttech.common.Constant;

public class CommonBaseController implements Constant{
	
	public void clearSessionUser(HttpServletRequest request,HttpServletResponse response,String key){
		request.getSession().setAttribute(key, null);
	}
	
	/**
	 * 设置session
	 * */
	public void setSession(HttpServletRequest request,HttpServletResponse response,String key,Object value) {
		request.getSession().setAttribute(key, value);
		if(USER_INFO.equals(key)){
			request.getSession().setMaxInactiveInterval(3600);
		}
	}
	/**
	 * 获取session
	 * @return 
	 * */
	public Object getSession(HttpServletRequest request,HttpServletResponse response,String key) {
		return request.getSession().getAttribute(key);
	}
	/**
	 * 获取当前登录人
	 * @param request
	 * @param response
	 * @return
	 */
	/*public UserEntity getLoginUser(HttpServletRequest request){
		UserEntity user=(UserEntity) request.getSession().getAttribute(USER_INFO);
		if(user==null)
			user=new UserEntity();
		return user;
	}
	
	public UserEntity getUserByUid(String uid){
		return new UserEntity();
	}
	*//**
	 * @time   2018年9月26日 上午11:24:33
	 * @author zuoqb
	 * @todo   判断是否为超级管理员
	 * @return_type   boolean
	 *//*
	public boolean isAdmin(HttpServletRequest request){
		return true;
	}*/
}
