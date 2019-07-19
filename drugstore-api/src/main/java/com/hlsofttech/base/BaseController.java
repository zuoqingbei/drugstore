package com.hlsofttech.base;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.hlsofttech.common.Constant;
import com.hlsofttech.constant.ESWebStatusEnum;
import com.hlsofttech.constant.ResponseVo;

public class BaseController implements Constant{
  /*@Reference(version = Constant.VERSION, timeout = TIMEOUT)
  public RedisService redisService;*/
//  @Reference(version = Constant.VERSION, timeout = TIMEOUT)
//  public AlgorithmUtilService algorithmUtilService;

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
	public static String getIpAddress(HttpServletRequest request) { 
	    String ip = request.getHeader("x-forwarded-for"); 
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	      ip = request.getHeader("Proxy-Client-IP"); 
	    } 
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	      ip = request.getHeader("WL-Proxy-Client-IP"); 
	    } 
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	      ip = request.getHeader("HTTP_CLIENT_IP"); 
	    } 
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	      ip = request.getHeader("HTTP_X_FORWARDED_FOR"); 
	    } 
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	      ip = request.getRemoteAddr(); 
	    } 
	    if (StringUtils.equals("0:0:0:0:0:0:0:1", ip)) {
	        ip = "127.0.0.1";
	    }
	    return ip; 
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
	
	/**
	 * @time   2018年9月26日 上午11:24:33
	 * @author zuoqb
	 * @todo   判断是否为超级管理员
	 * @return_type   boolean
	 */
	public boolean isAdmin(HttpServletRequest request){
		return true;
	}
	
	
	/**
   * 生成统一的返回响应对象
   *
   * @param webStatusEnum 状态码枚举
   * @param data 数据对象
   * @param <T> 数据对象类型参数
   * @return
   */
  public <T> ResponseVo generateResponseVo(ESWebStatusEnum webStatusEnum, T data) {
      return new ResponseVo(webStatusEnum.getCode(), webStatusEnum.getDesc(), data);
  }
  
  public ResponseVo<?> genFailedResponseVo(List<String> errorMessages) {
    ResponseVo<?> rv = new ResponseVo<>(ESWebStatusEnum.FAILED.getCode(), ESWebStatusEnum.FAILED.getDesc(), null);
    rv.setErrorMessages(errorMessages);
    return rv;
  }
  
  /**
   * Date日期数据解析绑定
   * @param binder
   */
  @InitBinder
  public void initBinder(WebDataBinder binder) {
    binder.registerCustomEditor(Date.class, new DateEditor());
  }
}
