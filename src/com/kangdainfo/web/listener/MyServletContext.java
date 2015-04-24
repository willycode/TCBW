package com.kangdainfo.web.listener;

import javax.servlet.ServletContext;

import com.kangdainfo.common.model.bo.*;

public class MyServletContext {

	public static MyServletContext instance;
	
	public static MyServletContext getInstance() {
		if(instance==null) {
			instance = new MyServletContext();
		}
		return instance;
	}
	
	private ServletContext servletContext;
	public ServletContext getServletContext() { return servletContext; }
	public void setServletContext(ServletContext servletContext) { this.servletContext = servletContext; }
	
	private com.kangdainfo.web.filter.IPFilter myIPFilter;
	public com.kangdainfo.web.filter.IPFilter getMyIPFilter() {	return myIPFilter;	}
	public void setMyIPFilter(com.kangdainfo.web.filter.IPFilter myIPFilter) {	this.myIPFilter = myIPFilter;}
	
	private java.util.Map<String, CommonUser> onlistUserMap;
	public java.util.Map<String, CommonUser> getOnlistUserMap() {return onlistUserMap;	}
	public void setOnlistUserMap(java.util.Map<String, CommonUser> onlistUserMap) {	this.onlistUserMap = onlistUserMap;	}
	
	private java.util.Map<String, CommonDtree> functionMap;
	public java.util.Map<String, CommonDtree> getFunctionMap() {return functionMap;	}
	public void setFunctionMap(java.util.Map<String, CommonDtree> functionMap) {this.functionMap = functionMap;	}
	
	
}
