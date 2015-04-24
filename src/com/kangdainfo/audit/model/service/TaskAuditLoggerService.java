package com.kangdainfo.audit.model.service;

import com.kangdainfo.audit.model.bo.TaskAudit;

public interface TaskAuditLoggerService {

	public interface TaskName {
		
		public interface Web {
			String PREFIX = "web.";
			String CRUD = PREFIX + "crud";
		}
		
	}
	

	public TaskAuditLogger createTaskAuditLogger(TaskAudit taskAudit);
	
	
}
