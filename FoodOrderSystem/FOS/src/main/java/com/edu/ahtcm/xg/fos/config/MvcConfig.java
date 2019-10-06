package com.edu.ahtcm.xg.fos.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.edu.ahtcm.xg.fos.authorization.interceptor.AuthorizationInterceptor;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

	@Autowired
	private AuthorizationInterceptor authorizationInterceptor;

	public void addInterceptors(InterceptorRegistry registry){
		registry.addInterceptor(authorizationInterceptor);
		
	}
	
	@Profile("dev")
	public void addCorsMapping(CorsRegistry registry){
		registry.addMapping("/**").allowedOrigins("*");
	}

}


