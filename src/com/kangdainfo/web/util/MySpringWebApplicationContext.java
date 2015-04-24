package com.kangdainfo.web.util;

import org.springframework.web.context.WebApplicationContext;

public class MySpringWebApplicationContext {

	public static WebApplicationContext defaultWebApplicationContext;
	
	public static Object getBean(String beanName) {
		return defaultWebApplicationContext.getBean(beanName);
	}
	
}
