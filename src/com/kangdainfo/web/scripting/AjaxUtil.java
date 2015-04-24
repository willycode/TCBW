/**
 * Copyright (c) 2000-2009 Kangda, Inc All Rights Reserved.
 */
package com.kangdainfo.web.scripting;

import java.io.InputStream;
import java.util.Date;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.log4j.Logger;

import com.kangdainfo.util.DateUtil;

/**
 * AjaxUtil
 * 
 * @author catty
 * @version 1.0, Created on 2006/11/9
 * 
 * @author shark
 * @version 1.1 Modify Date 2009/06/30
 * 
 */
public class AjaxUtil {

	public static final String DEFAULT_ENCODING = "utf-8";

	protected static Logger logger = Logger.getLogger(AjaxUtil.class);

	public static void main(String[] args) {
		String text = (String) AjaxUtil.connectTo(args[0]);
		logger.info(text);
	}

	public static Object connectTo(String url) {
		return connectTo(url, DEFAULT_ENCODING, null, false);
	}

	public static Object connectTo(String url, String encoding) {
		return connectTo(url, encoding, null, false);
	}

	public static Object connectTo(String url, String encoding, Map params) {
		return connectTo(url, encoding, params, false);
	}

	/**
	 * connectTo
	 * 
	 * @param url
	 * @param encoding
	 * @param params
	 * @param isBinary
	 * @return String or InputStream
	 */
	public static Object connectTo(String url, String encoding, Map params,	boolean isBinary) {
		logger.info("[connectTo.url] = " + url);
		StringBuffer sb = new StringBuffer();
		try {
			HttpClient client = new HttpClient();
			PostMethod method = new AjaxPostMethod(url, encoding);

			boolean isMultipart = false; //version 1.1
			java.util.Map<String,Part> h = new java.util.HashMap<String,Part>(); //version 1.1
			
			// add request parameter
			if (params != null && params.size() > 0) {
				for (Object idx : params.keySet()) {
					String key = (String) idx;
					Object value = params.get(key);
					if (value instanceof Date) {
						method.addParameter(key, DateUtil.parseToString((Date) value));
					} else if (value instanceof java.io.File) { //version 1.1
						isMultipart = true;	
						Part p = new FilePart(key, (java.io.File)value);
						h.put(key, p);
					} else {
						method.addParameter(key, value.toString());
					}
				}
				
				//version 1.1
				if (isMultipart && h!=null && h.size()>0) {
					Part[] parts = new Part[params.size()];
					int i=0;
					for (Object idx : params.keySet()) {
						String key = (String) idx;
						Object value = params.get(key);
						if (value instanceof Date) {
							parts[i] = new StringPart(key,DateUtil.parseToString((Date) value));
						} else if (value instanceof java.io.File) { //version 1.1
							parts[i] = h.get(key);
						} else {
							parts[i] = new StringPart(key,value.toString());
						}
						i++;
					}
					method.setRequestEntity(new MultipartRequestEntity(parts, method.getParams()));
				}
			}

			// execute httpRequest
			client.executeMethod(method);

			// check status code
			if (method.getStatusCode() == HttpStatus.SC_OK) {
				try {
					if (isBinary) {
						InputStream is = method.getResponseBodyAsStream();
						return is;
					} else {
						String ss = method.getResponseBodyAsString();
						sb.append(ss);
					}
				} catch (Exception e) {
					throw e;
				}
			} else {
				sb.append(method.getResponseBodyAsString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Connection Failure", e);
			sb.append(e.getMessage());
		}
		return sb.toString();
	}

}

class AjaxPostMethod extends PostMethod {
	private String encoding = "UTF-8"; // default

	public AjaxPostMethod(String url) {
		super(url);
	}

	public AjaxPostMethod(String url, String encoding) {
		super(url);
		this.encoding = encoding;
	}

	public String getRequestCharSet() {
		return encoding;
	}
}
