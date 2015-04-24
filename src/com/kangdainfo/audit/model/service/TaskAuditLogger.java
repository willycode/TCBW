package com.kangdainfo.audit.model.service;

import com.kangdainfo.util.uid.UniqueId;


public interface TaskAuditLogger {

	public void begin(Object caller);
	public void end(Object caller);
	public UniqueId debug(Object caller, Exception e);
	public UniqueId debug(Object caller, String logMessage, Exception e);
	public void debug(Object caller, String logMessage);
	public void info(Object caller, String logMessage);
	public void warn(Object caller, String logMessage);
	public void error(Object caller, String logMessage);
	public void fatal(Object caller, String logMessage);
	

	
}
