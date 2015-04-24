package com.kangdainfo.common.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kangdainfo.common.model.bo.*;

public interface AuthenticationService {

    public boolean authenticate(CommonUser currentUser, HttpSession session) throws Exception ;
    public boolean authenticate(String userName, String password, HttpServletRequest request, HttpServletResponse response) throws Exception;
    public void passAuthentication(String userName, HttpServletRequest request) throws Exception;
    public void passAuthentication(String userName,HttpSession session)  throws Exception;
    public boolean isAlreadyAuthenticated(HttpSession session) throws Exception;
    public boolean isAlreadyAuthenticated(HttpServletRequest request) throws Exception;
    public CommonUser getCurrentUser();
    public CommonUser getCurrentUser(HttpSession session);
    public CommonUser getCurrentUser(HttpServletRequest request);
    public int getCurrentUserId(HttpServletRequest request);    
    public CommonDtree getCurrentDtree();
    public void setCurrentDtree(CommonDtree dtree);
    
	public int checkAuthentication(HttpServletRequest request, HttpServletResponse response);
	public void invalidateSession(HttpServletRequest request);
	
	
}
