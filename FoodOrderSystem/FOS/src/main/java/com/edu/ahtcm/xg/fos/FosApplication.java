package com.edu.ahtcm.xg.fos;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.edu.ahtcm.xg.fos.utils.ip.IPAddrUtil;

@EnableTransactionManagement
@SpringBootApplication
public class FosApplication extends SpringBootServletInitializer{
	
	protected static final Logger logger =LoggerFactory.getLogger(FosApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(FosApplication.class, args);
		logger.info("fos system start successful");
		System.out.println("-----------------------------------------Food Order By Yourself System is started---------------------------------------");
		System.out.println("LocalUrl: http://localhost:8080/fos/login");
		System.out.println("NetWorkUrl: http://"+IPAddrUtil.getLocalIpAddr()+":8080/fos/login");
	}
	
    @Override
	//为了打包springboot项目
    protected SpringApplicationBuilder configure(
            SpringApplicationBuilder builder) {
        return builder.sources(this.getClass());
    }
}
