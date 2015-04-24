package com.kangdainfo.web.listener;

import java.util.Date;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import org.apache.log4j.Logger;

import com.kangdainfo.common.model.bo.CommonUser;
import com.kangdainfo.web.util.WebConstants;

public class MyServletContextUsersListener implements ServletContextListener, HttpSessionListener, HttpSessionAttributeListener {

	private Logger logger = Logger.getLogger(this.getClass());
    
	public void contextInitialized(ServletContextEvent sce) {
		MyServletContext.getInstance().setOnlistUserMap(new java.util.concurrent.ConcurrentHashMap<String, CommonUser>());
	}
		
	public void contextDestroyed(ServletContextEvent sce) {
		MyServletContext.getInstance().getOnlistUserMap().clear();
		//MyServletContext.getInstance().setOnlistUserMap(null);
	}

	public void attributeAdded(HttpSessionBindingEvent paramHttpSessionBindingEvent) {
		Object attributeValue = paramHttpSessionBindingEvent.getValue();
		if (attributeValue instanceof CommonUser) {
			CommonUser user = (CommonUser)attributeValue;
			MyServletContext.getInstance().getOnlistUserMap().put(user.getSessionId(), user);			
			logger.info(user.getSessionId() + "- " + user.getId() + "-" + user.getUserName()+"\t 登入系統\t"+(new Date())); 
		}	
	}

	public void sessionCreated(HttpSessionEvent paramHttpSessionEvent) {		
	}

	public void sessionDestroyed(HttpSessionEvent paramHttpSessionEvent) {
		Object attributeValue = paramHttpSessionEvent.getSession().getAttribute(WebConstants.SESSION_CURRENT_USER);
		if (attributeValue!=null && attributeValue instanceof CommonUser) {
			CommonUser user = (CommonUser)attributeValue;
			logger.info(user.getSessionId() + "- " + user.getId() + "-" + user.getUserName()+"\t 登出系統\t"+(new Date()));
			MyServletContext.getInstance().getOnlistUserMap().remove(user.getSessionId());			
		}
	}
	
	public void attributeRemoved(HttpSessionBindingEvent paramHttpSessionBindingEvent) {
		/**
		Object attributeValue = paramHttpSessionBindingEvent.getValue();		
		if (attributeValue instanceof CommonUser) {
			CommonUser user = (CommonUser)attributeValue;
			logger.info(user.getSessionId() + "- " + user.getId() + "-" + user.getUserName()+"\t attributeRemoved\t"+(new Date()));			
			//if (user.getSessionId()!=null) MyServletContext.getInstance().getOnlistUserMap().remove(user.getSessionId());
		}	
		**/	
	}

	public void attributeReplaced(HttpSessionBindingEvent paramHttpSessionBindingEvent) {
		/**
		Object attributeValue = paramHttpSessionBindingEvent.getValue();		
		if (attributeValue instanceof CommonUser) {			
			CommonUser user = (CommonUser)attributeValue;
			logger.info(user.getSessionId() + "- " + user.getId() + "-" + user.getUserName()+"\t attributeReplaced\t"+(new Date()));
			//System.out.println(user.getSessionId() + " - " + user.getUserName() + " attributeReplaced");
			//MyServletContext.getInstance().getOnlistUserMap().put(user.getSessionId(), user);
		}
		**/		
	}

	
}
