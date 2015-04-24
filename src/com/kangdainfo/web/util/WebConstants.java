package com.kangdainfo.web.util;

public interface WebConstants {	
    public static final String SESSION_CURRENT_USER = "User";
    public static final String SESSION_CURRENT_USER_DEPARTMENT = "Dept";
    public static final String SESSION_CURRENT_SYSTEM_ID = "current_sysid";    
    public static final String SESSION_IS_ADMIN = "is_admin";    
    public static final String PROGRAM_CODE = "DTREE_PROGRAM_IDENTIFIER";
    
    public int CURRENT_USER_NOT_FOUND = -1;
	public int SYSTEM_IDENTIFIER = 1;
	
    public interface SessionKeys {
    	public String CURRENT_USER = "User";
    	public String DEBUG_MODE = "debug";
    	public String ERROR_ID = "current_errorId";
    	public String CURRENT_SUB_SYSTEM = "current_sub_system";
    	public String IS_SYSTEM = "is_system";
    	public String IS_ADMIN = "is_admin";
    	public String CURRENT_PROGID = "PID";
    	public String CURRENT_DTREE = "CURRENT_DTREE";
    }   
    
	public interface AuthenticationStatus {
		int AUTHENTICATION_PASSED = 1;
		int AUTHENTICATION_FAILED = 2;
		int ALREADY_AUTHENTICATED = 3;
		int NOT_ALREADY_AUTHENTICATED = 4;
	}

}
