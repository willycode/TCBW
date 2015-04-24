package com.kangdainfo.audit.model.bo;

import javax.servlet.http.HttpServletRequest;

public class TaskAudit {

	private Integer taskId;
	private String moduleName;
	private String taskName;
	private Integer executingUser;
	private HttpServletRequest request;
	private boolean longAudit;
	
	public Integer getExecutingUser() {
		return executingUser;
	}
	public void setExecutingUser(Integer executingUser) {
		this.executingUser = executingUser;
	}
	public boolean isLongAudit() {
		return longAudit;
	}
	public void setLongAudit(boolean longAudit) {
		this.longAudit = longAudit;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	public Integer getTaskId() {
		return taskId;
	}
	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	
	

	
	
}
