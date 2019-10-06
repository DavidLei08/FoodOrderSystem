package com.edu.ahtcm.xg.fos.common;

/**
 * 系统自定义响应异常
 * @author DavidLei
 *
 */
public class APIException  extends RuntimeException{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 6416667147194820699L;
	
	private Integer status;
	
	private String errorMsg;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	
	public APIException(){
		
	}
	
	public APIException(Integer status, String errorMsg) {
		super();
		this.status = status;
		this.errorMsg = errorMsg;
	}
	
}
