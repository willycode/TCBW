package com.kangdainfo.tcbw.model.bo;

import java.io.Serializable;


public class Med7003Db  implements Serializable 
{
    /** identifier field */
    private Long id;
    private String changedate;
    private String changereason;
    private String changesubject;
    private String changecontext;
    private String changeremark;
    private String changerecheckdate;   
    private String changeVersion;
	private String receiveSystem;//介接異動系統
    private String receiveDate;//介接異動日期
    private String receiveTime;//介接異動時間
    private String creator;//建檔者
    private String createDate;//建檔日期
    private String createTime;//建檔時間
    private String modifier;//最後異動者
    private String modifyDate;// 最後異動日期
    private String modifyTime;//最後異動時間
    private Med7001Db med7001Db;
    
    /** default constructor */
    public Med7003Db() {
    }

	public String toString() 
	{
		StringBuilder builder = new StringBuilder();
		builder.append("Med7003Db [id=");
		builder.append(id);
		builder.append(", med7001Db=");
		builder.append(med7001Db);
		builder.append(", changedate=");
		builder.append(changedate);		
		builder.append(", changereason=");
	    builder.append(changereason);	
		builder.append(", changesubject=");
		builder.append(changesubject);
		builder.append(", changecontext=");
		builder.append(changecontext);
		builder.append(", changeremark=");
		builder.append(changeremark);
		builder.append(", changerecheckdate=");
		builder.append(changerecheckdate);		
		builder.append(", receiveSystem=");
		builder.append(receiveSystem);
		builder.append(", receiveDate=");
		builder.append(receiveDate);
		builder.append(", receiveTime=");
		builder.append(receiveTime);	
		builder.append(", creator=");
		builder.append(creator);
		builder.append(", createDate=");
		builder.append(createDate);
		builder.append(", createTime=");
		builder.append(createTime);
		builder.append(", modifier=");
		builder.append(modifier);
		builder.append(", modifyDate=");
		builder.append(modifyDate);
		builder.append(", modifyTime=");
		builder.append(modifyTime);
		builder.append("]");
		return builder.toString();
	}    
    
	public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }    
	
	public String getChangedate() {
		return changedate;
	}

	public void setChangedate(String changedate) {
		this.changedate = changedate;
	}

	public String getChangereason() {
		return changereason;
	}

	public void setChangereason(String changereason) {
		this.changereason = changereason;
	}

	public String getChangesubject() {
		return changesubject;
	}

	public void setChangesubject(String changesubject) {
		this.changesubject = changesubject;
	}

	public String getChangecontext() {
		return changecontext;
	}

	public void setChangecontext(String changecontext) {
		this.changecontext = changecontext;
	}

	public String getChangeremark() {
		return changeremark;
	}

	public void setChangeremark(String changeremark) {
		this.changeremark = changeremark;
	}

	public String getChangerecheckdate() {
		return changerecheckdate;
	}

	public void setChangerecheckdate(String changerecheckdate) {
		this.changerecheckdate = changerecheckdate;
	}

	public Med7001Db getMed7001Db() {
		return med7001Db;
	}

	public void setMed7001Db(Med7001Db med7001Db) {
		this.med7001Db = med7001Db;
	}
	
	

	public String getChangeVersion() {
		return changeVersion;
	}

	public void setChangeVersion(String changeVersion) {
		this.changeVersion = changeVersion;
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
}