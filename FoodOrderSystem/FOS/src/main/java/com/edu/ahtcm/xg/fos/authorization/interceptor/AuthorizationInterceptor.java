package com.edu.ahtcm.xg.fos.authorization.interceptor;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.edu.ahtcm.xg.fos.authorization.annotation.Authorizated;
import com.edu.ahtcm.xg.fos.common.Constants;

@Configuration
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if(handler instanceof HandlerMethod){
			HandlerMethod handlerMethod=(HandlerMethod)handler;
			Method method=handlerMethod.getMethod();
			if(method.getAnnotation(Authorizated.class)!=null){
				if(request.getSession().getAttribute(Constants.USER_INFO)!=null){
					return true;
				}else {
					response.sendRedirect(Constants.LOGIN_PAGE);
					return false;
				}
			}
		}
		return super.preHandle(request, response, handler);
	}

	
}
