package com.kangdainfo.web.filter;


import java.io.IOException;
import java.sql.ResultSet;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.log4j.Logger;

import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.Database;
import com.kangdainfo.web.listener.MyServletContext;
import com.kangdainfo.web.util.WebProperties;

public class IPFilter implements Filter {
	private static java.util.ArrayList<String[]> ipList = null;
	private boolean loaded = false;
	private Logger logger = Logger.getLogger(this.getClass());
	private FilterConfig filterConfig = null;

	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;	
		this.load();
		
		MyServletContext.getInstance().setMyIPFilter(this);		
	}

	public void destroy() {
		this.filterConfig = null;
		this.ipList = null;
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest myRequest = null;
		HttpServletResponse myResponse = null;

		if (request instanceof HttpServletRequest) {
			myRequest = (HttpServletRequest) request;
		}
		if (response instanceof HttpServletResponse) {
			myResponse = (HttpServletResponse) response;
		}

		if (ipList!=null && ipList.size()>0) {
			String uri = Common.get(myRequest!=null?myRequest.getRequestURI():"");
			boolean match = false;
			String s = myRequest.getRemoteAddr();
			for (String[] ips : ipList ) {				
				if (s.matches(ips[0])) {
					if (Common.get(ips[1]).equals("A")) match = true;
					else if (!"".equals(Common.get(ips[1])) && !"".equals(uri)) {
						if (Common.get(ips[1]).equals("C")) {
							if (uri.indexOf("wsCivil.jws")!=-1) match = true;
						} else if (Common.get(ips[1]).equals("L")) {
							if (uri.indexOf("wsLand.jws")!=-1) match = true;
						}
					}					
					break;
				}				
			}
			if (match) {
				Enumeration headerNames = myRequest.getHeaderNames();
				ToStringBuilder toStringBuilder = new ToStringBuilder("headers");
				while (headerNames.hasMoreElements()) {
					String headerName = (String) headerNames.nextElement();
					String headerValue = myRequest.getHeader(headerName);
					toStringBuilder.append(headerName, headerValue);
				}
				toStringBuilder.append("localAddr", myRequest.getLocalAddr());
				toStringBuilder.append("localName", myRequest.getLocalName());
				toStringBuilder.append("localPort", myRequest.getLocalPort());
				toStringBuilder.append("remoteAddr", myRequest.getRemoteAddr());
				toStringBuilder.append("remoteHost", myRequest.getRemoteHost());
				toStringBuilder.append("remotePort", myRequest.getRemotePort());
				logger.info(toStringBuilder.toString());				
				logger.debug(WebProperties.getRequestParameters(myRequest).toString());	
				
				chain.doFilter(request, response);
			} else {
				myResponse.sendError(406, "不允許的要求位址:" + myRequest!=null?myRequest.getRemoteAddr():"");
				logger.debug("不允許的要求位址:" + myRequest.getRemoteAddr());
	            return;				
			}
		} else chain.doFilter(request, response);				
	}


	public synchronized void load() {
		if(loaded) { return; }
		
		loaded = true;
		this.clear();
		
		if (ipList==null) ipList = new java.util.ArrayList<String[]>();		
		Database db = new Database();
		//File file = new File(ServiceGetter.getInstance().getWebContextInfo().getWebInfRealPath()+"/properties/hosts.allow");
		//FileInputStream fis;
		try {
			ResultSet rs = db.querySQL("select ip,ws from dsIPFilter",null);
			while (rs.next()) {
				ipList.add(new String[]{Common.get(rs.getString(1)),Common.get(rs.getString(2))});
			}
			rs.close();
			//fis = new FileInputStream(file);
			//InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
			//BufferedReader br = new BufferedReader(isr);
			//String s;			
			//while((s=br.readLine())!=null){
			//	if ((s.charAt(0) != '#') && (s.charAt(0) != '!')) {
			//		ipList.add(Common.escapeReturnChar(s));
			//	}
			//}
			//br.close();
			//isr.close();
			//fis.close();		
		} catch (Exception e) {
			System.out.print("Tomcat啟動時，因為xml元件尚未載入，而造成，可不管此錯誤。");
			e.printStackTrace();
		} finally {
			db.closeAll();
		}
	}
	
	public synchronized void reload() {
		loaded = false;
		load();
	}
	
	public synchronized void clear() {
		if (ipList!=null) ipList.clear();
	}	
	
	public FilterConfig getFilterConfig() {
		return filterConfig;
	}

	public void setFilterConfig(FilterConfig filterConfig) {
		this.filterConfig = filterConfig;
	}	

}

