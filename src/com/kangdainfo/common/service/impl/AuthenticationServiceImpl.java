package com.kangdainfo.common.service.impl;

import java.util.Collection;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.log4j.MDC;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.model.bo.*;
import com.kangdainfo.common.service.AuthenticationService;
import com.kangdainfo.common.service.CommonService;
import com.kangdainfo.common.util.*;
import com.kangdainfo.web.util.WebConstants;

public class AuthenticationServiceImpl implements AuthenticationService {

	private Logger logger = Logger.getLogger(this.getClass());
	
	public interface KEY {
		String ACTION_TYPE = "action";

		interface ACTION {
			String LOGIN = "login";
			String LOGOUT = "logout";
		}

		String USER_ID = "userID";
		String USER_PASSWORD = "userPWD";

	}
	
	
	public int checkAuthentication(HttpServletRequest request, HttpServletResponse response) {

		String action = request.getParameter(KEY.ACTION_TYPE);

		logger.debug("action : " + action);

		if (action != null && action.trim().equals(KEY.ACTION.LOGIN)) {
			boolean authenticated = authenticate(request, response);

			return authenticated ?
					WebConstants.AuthenticationStatus.AUTHENTICATION_PASSED:
					WebConstants.AuthenticationStatus.AUTHENTICATION_FAILED;					
		} else {
			try {
				return isAlreadyAuthenticated(request) ? 
						WebConstants.AuthenticationStatus.ALREADY_AUTHENTICATED:
						WebConstants.AuthenticationStatus.NOT_ALREADY_AUTHENTICATED;
			} catch (Exception e) {
				e.printStackTrace();
				return WebConstants.AuthenticationStatus.NOT_ALREADY_AUTHENTICATED;
			}
		}
	}

	private boolean authenticate(HttpServletRequest request, HttpServletResponse response) {
		boolean authenticated = false;
		try {
			boolean sFlag = isAlreadyAuthenticated(request.getSession());
			if (sFlag==false) {
				CommonService service = ServiceGetter.getInstance().getCommonService();
				String userName = request.getParameter(KEY.USER_ID);
				String password = request.getParameter(KEY.USER_PASSWORD);

				logger.debug("userId : " + userName);
				//logger.debug("password : " + password);			
				if (userName!=null && password!=null && Validate.checkSpecialChar(userName)==false && Validate.checkSpecialChar(password)==false) {					
					CommonUser user = service.getCommonUserDao().saveOrGetVerifiedUser(userName, Common.getDigestString(password,"SHA-1"));
					if (user!=null) authenticated = true;
				}				
			}
		} catch (Exception e) {
			logger.error("", e);
			return false;
		}
		return authenticated;
	}

    public boolean isAlreadyAuthenticated(HttpServletRequest request) throws Exception {
    	return isAlreadyAuthenticated(request.getSession());
    }
    public boolean isAlreadyAuthenticated(HttpSession session) throws Exception {
        try {
            CommonUser currentUser = (CommonUser) session.getAttribute(WebConstants.SessionKeys.CURRENT_USER);
            if (currentUser != null) {            	
            	return true;
            }
            else {
            	return false;
            }
        } catch (Exception e) {
            String msg = "Runtime Exception!";
            this.logger.error(msg, e);
            throw new Exception(msg, e);
        }
    }

	
	public void invalidateSession(HttpServletRequest request) {
		for (Enumeration en = request.getSession().getAttributeNames(); en
				.hasMoreElements();) {
			String key = (String) en.nextElement();
			Object value = request.getSession().getAttribute(key);
			if (value instanceof Map) {
				((Map) value).clear();
			} else if (value instanceof Collection) {
				((Collection) value).clear();
			}
			value = null;
			request.getSession().removeAttribute(key);
			request.getSession().invalidate();
		}
	}

	public boolean authenticate(CommonUser currentUser, HttpSession session) throws Exception {
		session.setAttribute(WebConstants.SessionKeys.CURRENT_USER, currentUser);		
		return true;
	}

	public boolean authenticate(String userName, String password, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (userName!=null && password!=null && Validate.checkSpecialChar(userName)==false && Validate.checkSpecialChar(password)==false) {
			try {
				CommonUser user = ServiceGetter.getInstance().getCommonService().getCommonUserDao().saveOrGetVerifiedUser(userName, Common.getDigestString(password,"SHA-1"));
				if (user!=null) return true;
				else return false;
			} catch (Exception e) {
				//String msg = "Could not authenticate username[" + userName + "] password[" + password + "]";
				String msg = "Could not authenticate username[" + userName + "] ";
				this.logger.error(msg, e);
				return false;
			}
		}
		return false;
	}

	public CommonUser getCurrentUser() {
		return (CommonUser) MDC.get(WebConstants.SessionKeys.CURRENT_USER);
	}
	
	public CommonDtree getCurrentDtree() {
		return (CommonDtree) MDC.get(WebConstants.SessionKeys.CURRENT_DTREE);
	}	
	public void setCurrentDtree(CommonDtree dtree) {
		MDC.put(WebConstants.SessionKeys.CURRENT_DTREE, dtree);
	}		
	public CommonUser getCurrentUser(HttpSession session) {
    	return (CommonUser) session.getAttribute(WebConstants.SessionKeys.CURRENT_USER);
	}

	public CommonUser getCurrentUser(HttpServletRequest request) {
    	return (CommonUser) request.getSession().getAttribute(WebConstants.SessionKeys.CURRENT_USER);
	}

	public int getCurrentUserId(HttpServletRequest request) {
		return ((CommonUser) request.getSession().getAttribute(WebConstants.SessionKeys.CURRENT_USER)).getId();
	}

	public void passAuthentication(String userName, HttpServletRequest request) throws Exception {
		passAuthentication(userName,request.getSession());		
	}

	public void passAuthentication(String userName, HttpSession session) throws Exception {
		try {			
			CommonUser currentUser = ServiceGetter.getInstance().getCommonService().getCommonUserDao().getCommonUserByUserId(userName);
			session.setAttribute(WebConstants.SessionKeys.CURRENT_USER, currentUser);
		} catch (Exception e) {
			String msg = "Could not pass authentication";
			this.logger.error(msg, e);
			throw new Exception(msg, e);
		}		
	}
	

	
	
}
