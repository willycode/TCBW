package com.kangdainfo.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.owasp.esapi.ESAPI;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.model.bo.CommonUser;
import com.kangdainfo.web.util.WebConstants;

/**
 * the AuthenticateFilter
 * 
 * @author airy.huang
 */
public class AuthenticateFilter implements Filter {

	public interface KEY {
		public String REQUEST_STARTTIME = "request_start_time";
	}
	
	private Logger logger = Logger.getLogger(this.getClass());

	private FilterConfig filterConfig = null;

	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}

	public void destroy() {
		this.filterConfig = null;
	}

	public void putMdc(HttpSession session) {
		if(session!=null) {				
			CommonUser user = (CommonUser) session.getAttribute(WebConstants.SessionKeys.CURRENT_USER);
			if(user!=null) {
				MDC.put(WebConstants.SessionKeys.CURRENT_USER, user);				
			}
		}
	
//		if (request instanceof HttpServletRequest) {
//			HttpServletRequest httpRequest = (HttpServletRequest) request;
//			HttpSession session = httpRequest.getSession();
//			if(session!=null) {				
//				CommonUser user = (CommonUser) session.getAttribute(WebConstants.SessionKeys.CURRENT_USER);
//				if(user!=null) {
//					MDC.put(WebConstants.SessionKeys.CURRENT_USER, user);				
//				}
				
				/**
				//加入程式功能代號				
				String pid = Common.get(request.getParameter("progID"));	
				//if ("".equals(pid)) pid = (String) session.getAttribute(WebConstants.SessionKeys.CURRENT_PROGID);						
				if (pid!=null && !"".equals(Common.get(pid)) && Common.getLong(pid)>0) {
					CommonDtree dtree = ServiceGetter.getInstance().getMyServletContext().getFunctionMap().get(pid);
					if (dtree==null) {
						try {
							dtree = (CommonDtree) ServiceGetter.getInstance().getCommonService().getCommonDtreeDao().getNode(Common.getLong(pid));
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					if (dtree!=null) {
						//session.setAttribute(WebConstants.SessionKeys.CURRENT_PROGID,dtree.getId().toString());
						ServiceGetter.getInstance().getMyServletContext().getFunctionMap().put(dtree.getId().toString(), dtree);
						MDC.put(WebConstants.SessionKeys.CURRENT_DTREE, dtree);
					}
				}
				//End of 程式功能代號
				**/
//			}
//		}
	}
	
	public void removeMdc(ServletRequest request) {
		//MDC.remove(WebConstants.SessionKeys.CURRENT_USER);	
		/*
		if (request instanceof HttpServletRequest) {
			HttpServletRequest httpRequest = (HttpServletRequest) request;
			HttpSession session = httpRequest.getSession();
			if(session!=null) {		
				MDC.remove(WebConstants.SessionKeys.CURRENT_USER);				
			}
		}
		*/	
			
	}
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		//putMdc(request);
		
		HttpSession session = null;
		HttpServletRequest myRequest = null;
		HttpServletResponse myResponse = null;

		if (request instanceof HttpServletRequest) {
			myRequest = (HttpServletRequest) request;
			session = myRequest.getSession(false);
		}
		if (response instanceof HttpServletResponse) {
			myResponse = (HttpServletResponse) response;
		}
		/** 為了節省點效能,先mark起來 
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
		ServletContext context = session.getServletContext();		
		**/		

		if (session != null) {
			// authenticate
			int authenticationStatus = authenticate(myRequest, myResponse);
			if(authenticationStatus==WebConstants.AuthenticationStatus.AUTHENTICATION_PASSED) {
				authenticationPassed(myRequest,myResponse);
			}
			boolean authenticated = authenticationStatus==WebConstants.AuthenticationStatus.ALREADY_AUTHENTICATED ||
			authenticationStatus==WebConstants.AuthenticationStatus.AUTHENTICATION_PASSED;
			
			if (authenticated) {				
				putMdc(session);				
				chain.doFilter(request, response);				
				//removeMdc(request);				
				//return;
			} else {
				session.invalidate();
				//String url = myRequest.getScheme()+"://"+myRequest.getServerName()+":"+myRequest.getServerPort()+myRequest.getContextPath();				
				//myResponse.sendRedirect(url);				
				//context.getRequestDispatcher("/index.jsp").forward(request, response);
				try {
					ESAPI.httpUtilities().sendRedirect(myResponse,myRequest.getContextPath());
				} catch (Exception e) {
					e.printStackTrace();
					logger.error(e.getMessage(), e);				    
				}
				//context.getRequestDispatcher("/index.jsp").forward(request, response);
				logger.info("Can not pass Authentic filter ");
			}
		} else {			
			//String url = myRequest.getScheme()+"://"+myRequest.getServerName()+":"+myRequest.getServerPort()+myRequest.getContextPath();				
			//myResponse.sendRedirect(url);				
			//context.getRequestDispatcher("/index.jsp").forward(request, response);
			try {
				ESAPI.httpUtilities().sendRedirect(myResponse,myRequest!=null?myRequest.getContextPath():"");
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e.getMessage(), e);				    
			}			
			//context.getRequestDispatcher("/index.jsp").forward(request, response);
		}
	}
	
	/**
	 * authenticate
	 * @param request
	 * @param response
	 * @see com.kangdainfo.web.util.WebConstants$AuthenticationStatus
	 * @return
	 */
	protected int authenticate(HttpServletRequest request, HttpServletResponse response) {
		return ServiceGetter.getInstance().getAuthenticationService().checkAuthentication(request, response);
	}

	protected void authenticationPassed(HttpServletRequest request, HttpServletResponse response) {
		
	}

	public FilterConfig getFilterConfig() {
		return filterConfig;
	}

	public void setFilterConfig(FilterConfig filterConfig) {
		this.filterConfig = filterConfig;
	}

	
}
