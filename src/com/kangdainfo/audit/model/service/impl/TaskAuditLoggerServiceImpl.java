package com.kangdainfo.audit.model.service.impl;

import com.kangdainfo.audit.model.bo.TaskAudit;
import com.kangdainfo.audit.model.service.TaskAuditLogger;
import com.kangdainfo.audit.model.service.TaskAuditLoggerService;

public class TaskAuditLoggerServiceImpl implements TaskAuditLoggerService {

	public TaskAuditLogger createTaskAuditLogger(TaskAudit taskAudit) {
		return new TaskAuditLoggerImpl();
	}
}
