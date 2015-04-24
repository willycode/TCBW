/**
 * Copyright (c) 2000-2005 Kangda, Inc
 * Road, Taipei, Taiwan, R.O.C. All Rights Reserved.
 */
package com.kangdainfo.web.util;

import java.io.File;

import javax.servlet.ServletContext;

import org.springframework.web.context.support.WebApplicationObjectSupport;

/**
 * 
 * WebContextInfo
 * 
 * @author catty.liao
 * @version 1.0, Created on 2006/7/20
 */
public class WebContextInfo extends WebApplicationObjectSupport {
	private final static String WEB_INF = "WEB-INF";

	/**
	 * Get ServletContext
	 * 
	 * @return
	 */
	public ServletContext getParentServletContext() {
		return super.getServletContext();
	}

	/**
	 * getRealPath
	 * @param path
	 * @return
	 */
	public String getRealPath(String path) {
		return super.getServletContext().getRealPath(path);
	}

	/**
	 * Get real path of ServletContext root
	 * 
	 * @return
	 */
	public String getServletContextRootRealPath() {
		return super.getServletContext().getRealPath("/");
	}

	/**
	 * Get WEB-INF real path
	 * 
	 * @return
	 */
	public String getWebInfRealPath() {
		return super.getServletContext().getRealPath("/" + WEB_INF);
	}

	/**
	 * Get Servlet Context Attribute
	 * 
	 * @return
	 */
	public Object getServeltContextAttribute(String key) {
		return super.getServletContext().getAttribute(key);
	}

	/**
	 * Get WEB-INF CLASSPATH
	 * 
	 * @return
	 */
	public String getWebInfClassPath() {
		StringBuffer sb = new StringBuffer(getWebInfRealPath()).append(
				File.separator).append("classes");
		File libDir = new File(getWebInfRealPath() + File.separator + "lib");
		File[] files = libDir.listFiles();
		for (int i = 0; i < files.length; i++) {
			sb.append(File.pathSeparator);
			sb.append(files[i].getAbsolutePath());
		}
		return sb.toString();
	}
}
