package com.kangdainfo.audit.model.service.impl;

import com.kangdainfo.audit.model.service.TaskAuditLogger;
import com.kangdainfo.util.uid.*;

public class TaskAuditLoggerImpl implements TaskAuditLogger {

	public void begin(Object caller) {}
	public void end(Object caller) {}
	public UniqueId debug(Object caller, Exception e) 
	{
		return debug(caller,null,e);
	}
	public UniqueId debug(Object caller, String logMessage, Exception e) 
	{
		UniqueIdGenerator gen = TimeBasedUniqueIdGenerator.getInstance();
		UniqueId uniqueId = gen.next();
		return uniqueId;		
	}
	public void debug(Object caller, String logMessage) {}
	public void info(Object caller, String logMessage) {}
	public void warn(Object caller, String logMessage) {}
	public void error(Object caller, String logMessage) {}
	public void fatal(Object caller, String logMessage) {}
	
}
