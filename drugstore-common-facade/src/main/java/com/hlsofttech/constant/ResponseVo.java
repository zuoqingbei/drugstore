package com.hlsofttech.constant;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.hlsofttech.utils.ObjUtils;

/**
 * 响应信息
 * @author sdc
 *
 * @param <T>
 */
public class ResponseVo<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 状态码
	 */
	private String code;

	/**
	 * 状态码对应的信息
	 */
	private String message;

	/**
	 * 数据对象
	 */
	private T data;

	/**
   * 错误信息
   */
  private List<String> errorMessages = new ArrayList<String>();
	
  public ResponseVo() {
    super();
  }
  
	public ResponseVo(String code, String message, T data) {
		super();
		this.code = code;
		this.message = message;
		this.data = data;
	}
	
	/**
   * 判断当前执行结果是否正确
   * @return
   */
  public boolean isSuccess(){
//    SUCCESS("5000", "成功"), 
//    FAILED("7000", "失败");
    if(StringUtils.equals("5000", code) && ObjUtils.isEmpty(errorMessages)) {
      return true;
    }else {
      return false;
    }
  }
	
	
	
	

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

  public List<String> getErrorMessages() {
    return errorMessages;
  }

  public void setErrorMessages(List<String> errorMessages) {
    this.errorMessages = errorMessages;
  }

}
