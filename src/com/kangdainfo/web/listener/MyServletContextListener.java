package com.kangdainfo.web.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import com.kangdainfo.common.model.bo.CommonDtree;

public class MyServletContextListener implements ServletContextListener {

	public static final String NL = "\r\n";
	
	private Logger logger = Logger.getLogger(this.getClass());
    
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext servletContext = sce.getServletContext();
		MyServletContext.getInstance().setServletContext(servletContext);
		MyServletContext.getInstance().setFunctionMap(new java.util.concurrent.ConcurrentHashMap<String, CommonDtree>());
		
		String message = NL;
		message += "##############################################################" + NL;
		message += "############### " + servletContext.getServletContextName() + " Started!" + NL;
		message += "##############################################################" + NL;
		System.out.println(message);
		logger.info(message);
	}
	
	
	public void contextDestroyed(ServletContextEvent sce) {
		
		String message = NL;
		message += "##############################################################" + NL;
		message += "############### " + MyServletContext.getInstance().getServletContext().getServletContextName() + " Shutdown!" + NL;
		message += "##############################################################" + NL;
		System.out.println(message);
		logger.info(message);
		
		MyServletContext.getInstance().setFunctionMap(null);
		MyServletContext.getInstance().setServletContext(null);
		
	}	 

	
}
