package com.kangdainfo.web.util;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class WebProperties {

	private static final String NEWLINE = "\r\n";
	
	private Map internalMap;
	
	public WebProperties() {
		internalMap = new TreeMap();
	}
	
	public String[] getNames() {
		if(internalMap.size()==0) {
			return null;
		}
		return (String[]) internalMap.keySet().toArray(new String[internalMap.size()]);
	}
	public Object getValue(String name) {
		return internalMap.get(name);
	}
	
	public static WebProperties getRequestAttributes(HttpServletRequest request) {
		WebProperties webProperties = new WebProperties();
		Enumeration keys = request.getAttributeNames();
		while(keys.hasMoreElements()) {
			String key = (String) keys.nextElement();
			Object value = request.getAttribute(key);
			webProperties.internalMap.put(key, value);
		}
		return webProperties;
	}
	public static WebProperties getRequestParameters(HttpServletRequest request) {
		WebProperties webProperties = new WebProperties();
        Map parameterMap = request.getParameterMap();
        String keys[] = (String[]) parameterMap.keySet().toArray(new String[parameterMap.size()]);
        for(int i=0; i < keys.length; i++) {
        	String key = keys[i];
        	Object value = (Object) parameterMap.get(key);
        	if(value!=null) {
        		String values[] = (String[]) value;
        		if(values.length==1) {
        			webProperties.internalMap.put(key, values[0]);
        		}
        		else {
        			webProperties.internalMap.put(key, value);
        		}
        	}
        }
        return webProperties;
	}	
	public static WebProperties getSessionAttributes(HttpSession session) {
		WebProperties webProperties = new WebProperties();
		Enumeration keys = session.getAttributeNames();
		while(keys.hasMoreElements()) {
			String key = (String) keys.nextElement();
			Object value = session.getAttribute(key);
			webProperties.internalMap.put(key, value);
		}
		return webProperties;
	}
	public static WebProperties getSessionAttributes(HttpServletRequest request) {
		return getSessionAttributes(request.getSession());
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		String names[] = getNames();
		if(names!=null) {
			for(int i=0; i < names.length; i++) {
				String name = (String) names[i];
				Object value = getValue(name);
				if(value instanceof String[]) {
					String values[] = (String[]) value;
					sb.append(name + "=[");
					for(int j=0; j < values.length; j++) {
						sb.append(values[j]);
						if(j<values.length-1) {
							sb.append(",");
						}
					}
					sb.append("]" + NEWLINE);
				}
				else {
					sb.append(name + "=" + value + NEWLINE);
				}
			}
		}
		return sb.toString();
	}
	public String toHtmlTable() {
		StringBuffer sb = new StringBuffer();
		String names[] = getNames();
		if(names!=null) {
			for(int i=0; i < names.length; i++) {
				String name = (String) names[i];
				Object value = getValue(name);
				if(value instanceof String[]) {
					sb.append("<TR>" + NEWLINE);
					String values[] = (String[]) value;
					sb.append("<td nowrap>").append(name).append("</TD>").append(NEWLINE);
					sb.append("<td nowrap>");
					for(int j=0; j < values.length; j++) {
						sb.append(values[j]);
						if(j<values.length-1) {
							sb.append(",");
						}
					}
					sb.append("</TD>").append(NEWLINE);
					sb.append("</TR>" + NEWLINE);
				}
				else {
					sb.append("<TR>" + NEWLINE);
					sb.append("<td nowrap>").append(name).append("</TD>").append(NEWLINE);
					sb.append("<td nowrap>").append(value).append("</TD>").append(NEWLINE);
					sb.append("</TR>" + NEWLINE);
				}
			}
		}
		return sb.toString();		
	}
	
	
}
