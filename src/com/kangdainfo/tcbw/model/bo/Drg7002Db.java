package com.kangdainfo.tcbw.model.bo;

import java.io.Serializable;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;

public class Drg7002Db implements Serializable{
	
	private Long id;
	private String replyDate;
	private String replyDesc;
	private String isImport;
	private String nonImportReason;
	private String nonImportReasonDesc;
	private String eventapplNo;
	private String receiveSystem;
	private String receiveDate;
	private String receiveTime;
	private String creator;
	private String createDate;
	private String createTime;
	private String modifier;
	private String modifyDate;
	private String modifyTime;
	private com.kangdainfo.tcbw.model.bo.Drg7001Db drg7001Db;
	private Set drg7003Dbs;
	
	public Drg7002Db() {   	        
 
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
						.append("id", getId())
						.toString();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}	
	public String getReplyDate() {
		return replyDate;
	}
	public void setReplyDate(String replyDate) {
		this.replyDate = replyDate;
	}
	public String getReplyDesc() {
		return replyDesc;
	}
	public void setReplyDesc(String replyDesc) {
		this.replyDesc = replyDesc;
	}
	public String getIsImport() {
		return isImport;
	}
	public void setIsImport(String isImport) {
		this.isImport = isImport;
	}
	public String getNonImportReason() {
		return nonImportReason;
	}
	public void setNonImportReason(String nonImportReason) {
		this.nonImportReason = nonImportReason;
	}
	public String getNonImportReasonDesc() {
		return nonImportReasonDesc;
	}
	public void setNonImportReasonDesc(String nonImportReasonDesc) {
		this.nonImportReasonDesc = nonImportReasonDesc;
	}
	public String getEventapplNo() {
		return eventapplNo;
	}
	public void setEventapplNo(String eventapplNo) {
		this.eventapplNo = eventapplNo;
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
	public Set getDrg7003Dbs() {
		return drg7003Dbs;
	}
	public void setDrg7003Dbs(Set drg7003Dbs) {
		this.drg7003Dbs = drg7003Dbs;
	}
}
