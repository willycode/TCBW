package com.kangdainfo.tcbw.model.bo;

import java.io.Serializable;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;

public class Drg7003Db implements Serializable{
	
	private Long id;	
	private String receiveSystem;
	private String receiveDate;
	private String receiveTime;
	private String creator;
	private String createDate;
	private String createTime;
	private String modifier;
	private String modifyDate;
	private String modifyTime;	
	private String permitKey;
	private String permitNo;
	private String applicationId;
	private String applicationName;
	private com.kangdainfo.tcbw.model.bo.Drg7001Db drg7001Db;
	private com.kangdainfo.tcbw.model.bo.Drg7002Db drg7002Db;	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}	
	public String getPermitKey() {
		return permitKey;
	}
	public void setPermitKey(String permitKey) {
		this.permitKey = permitKey;
	}
	public String getPermitNo() {
		return permitNo;
	}
	public void setPermitNo(String permitNo) {
		this.permitNo = permitNo;
	}
	public String getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}
	public String getApplicationName() {
		return applicationName;
	}
	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}	
	public String getReceiveSystem() {
		return receiveSystem;
	}
	public void setReceiveSystem(String receiveSystem) {
		this.receiveSystem = receiveSystem;
	}
	public String getReceiveDate() {
		return receiveDate;
	}
	public void setReceiveDate(String receiveDate) {
		this.receiveDate = receiveDate;
	}
	public String getReceiveTime() {
		return receiveTime;
	}
	public void setReceiveTime(String receiveTime) {
		this.receiveTime = receiveTime;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getModifier() {
		return modifier;
	}
	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
	public String getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}
	public String getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}	
	public com.kangdainfo.tcbw.model.bo.Drg7001Db getDrg7001Db() {
		return drg7001Db;
	}
	public void setDrg7001Db(com.kangdainfo.tcbw.model.bo.Drg7001Db drg7001Db) {
		this.drg7001Db = drg7001Db;
	}
	public com.kangdainfo.tcbw.model.bo.Drg7002Db getDrg7002Db() {
		return drg7002Db;
	}
	public void setDrg7002Db(com.kangdainfo.tcbw.model.bo.Drg7002Db drg7002Db) {
		this.drg7002Db = drg7002Db;
	}
	@Override
	public String toString() {
		return new ToStringBuilder(this)
						.append("id", getId())
						.toString();
	}
}
