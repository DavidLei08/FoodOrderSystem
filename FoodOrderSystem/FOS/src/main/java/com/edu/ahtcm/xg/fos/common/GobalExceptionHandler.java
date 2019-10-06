package com.edu.ahtcm.xg.fos.common;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GobalExceptionHandler {
	
	/**
	 * 内部日志
	 */
	protected static final Logger logger =LoggerFactory.getLogger(GobalExceptionHandler.class);
	
	@ResponseStatus(value=HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value=Exception.class)
	public String exceptionHandler(Exception ex,HttpServletRequest request){
		logger.error(ex.getMessage()+Constants.BREAK_LINE+ex.getCause());
		request.setAttribute(Constants.ERROR_MSG, ex.getMessage());
		request.setAttribute("responseStatus",400);
		return Constants.ERROR_PAGE;
	}

	@ExceptionHandler(value=APIException.class)
	public String apiExceptionHandler(APIException ex,HttpServletRequest request,HttpServletResponse response){
		logger.error(ex.getErrorMsg()+Constants.BREAK_LINE+ex.getStatus());
		request.setAttribute(Constants.ERROR_MSG, ex.getErrorMsg());
		request.setAttribute("responseStatus",ex.getStatus());
		response.setStatus(ex.getStatus());
		return Constants.ERROR_PAGE;
	}
	
}
