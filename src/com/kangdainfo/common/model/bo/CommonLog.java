package com.kangdainfo.common.model.bo;

/**
 * CommonAuditLog entity. @author MyEclipse Persistence Tools
 */

public class CommonLog implements java.io.Serializable {

	// Fields

	private Long id;
	private String code;
	private String openDate;
	private String openTime;
	private String userId;
	private String ip;
	private String methodName;
	private String db;
	private String dbId;
	private String applNo;
	private String openUserId;
	private String openUserName;



	// Constructors

	/** default constructor */
	public CommonLog() {
	}

	/** full constructor */
	public CommonLog(String code,String openDate,
			String openTime, String userId, String ip, String methodName, 
			String openUserId,String openUserName) 
	{
		this.code = code;
		this.openDate = openDate;
		this.openTime =openTime;
		this.userId = userId;
		this.ip = ip;
		this.methodName = methodName;
		this.openUserId = openUserId;
		this.openUserName = openUserName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getOpenDate() {
		return openDate;
	}

	public void setOpenDate(String openDate) {
		this.openDate = openDate;
	}

	public String getOpenTime() {
		return openTime;
	}

	public void setOpenTime(String openTime) {
		this.openTime = openTime;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getOpenUserId() {
		return openUserId;
	}

	public void setOpenUserId(String openUserId) {
		this.openUserId = openUserId;
	}

	public String getDb() {
		return db;
	}

	public void setDb(String db) {
		this.db = db;
	}

	public String getDbId() {
		return dbId;
	}

	public void setDbId(String dbId) {
		this.dbId = dbId;
	}

	public String getApplNo() {
		return applNo;
	}

	public void setApplNo(String applNo) {
		this.applNo = applNo;
	}

	public String getOpenUserName() {
		return openUserName;
	}

	public void setOpenUserName(String openUserName) {
		this.openUserName = openUserName;
	}

	

}