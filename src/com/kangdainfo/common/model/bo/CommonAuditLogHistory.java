package com.kangdainfo.common.model.bo;

/**
 * CommonAuditLog entity. @author MyEclipse Persistence Tools
 */

public class CommonAuditLogHistory implements java.io.Serializable {

	// Fields

	private String id;
	private String progCode;
	private String progName;
	private String logDate;
	private String logTime;
	private String userId;
	private String userName;
	private String userIp;
	private String methodName;
	private String entity;
	private String queryString;
	private String queryValues;
	private String exceptionMessage;

	// Constructors

	/** default constructor */
	public CommonAuditLogHistory() {
	}

	/** full constructor */
	public CommonAuditLogHistory(String logDate, String logTime, String userId,
			String userName, String userIp, String methodName, String entity,
			String queryString, String queryValues, String exceptionMessage) {
		this.logDate = logDate;
		this.logTime = logTime;
		this.userId = userId;
		this.userName = userName;
		this.userIp = userIp;
		this.methodName = methodName;
		this.entity = entity;
		this.queryString = queryString;
		this.queryValues = queryValues;
		this.exceptionMessage = exceptionMessage;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLogDate() {
		return this.logDate;
	}

	public void setLogDate(String logDate) {
		this.logDate = logDate;
	}

	public String getLogTime() {
		return this.logTime;
	}

	public void setLogTime(String logTime) {
		this.logTime = logTime;
	}
	
	public String getProgCode() {
		return progCode;
	}

	public void setProgCode(String progCode) {
		this.progCode = progCode;
	}

	public String getProgName() {
		return progName;
	}

	public void setProgName(String progName) {
		this.progName = progName;
	}
	
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserIp() {
		return this.userIp;
	}

	public void setUserIp(String userIp) {
		this.userIp = userIp;
	}

	public String getMethodName() {
		return this.methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getEntity() {
		return this.entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}

	public String getQueryString() {
		return this.queryString;
	}

	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}

	public String getQueryValues() {
		return this.queryValues;
	}

	public void setQueryValues(String queryValues) {
		this.queryValues = queryValues;
	}

	public String getExceptionMessage() {
		return this.exceptionMessage;
	}

	public void setExceptionMessage(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}

}