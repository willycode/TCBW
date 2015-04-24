package com.kangdainfo.tcbw.model.bo;

import java.io.Serializable;

public class Hfr1003Db  implements Serializable 
{
	
	/** identifier field */
    private Long id;
    
    private String meetingYear;//評估委員會會期(年度)
    private String meetingSeason;//	評估委員會會期(期別)
    private String meetingDate;//	召開日期
    private String meetingStime;//	召開時間(起)
    private String meetingEtime;//	召開時間(迄)
    private String receiveSystem;//介接異動系統
    private String receiveDate;//介接異動日期
    private String receiveTime;//介接異動時間
    private String creator;//建檔者
    private String createDate;//建檔日期
    private String createTime;//建檔時間
    private String modifier;//最後異動者
    private String modifyDate;// 最後異動日期
    private String modifyTime;//最後異動時間    
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMeetingYear() {
		return meetingYear;
	}

	public void setMeetingYear(String meetingYear) {
		this.meetingYear = meetingYear;
	}

	public String getMeetingSeason() {
		return meetingSeason;
	}

	public void setMeetingSeason(String meetingSeason) {
		this.meetingSeason = meetingSeason;
	}

	public String getMeetingDate() {
		return meetingDate;
	}

	public void setMeetingDate(String meetingDate) {
		this.meetingDate = meetingDate;
	}

	public String getMeetingStime() {
		return meetingStime;
	}

	public void setMeetingStime(String meetingStime) {
		this.meetingStime = meetingStime;
	}

	public String getMeetingEtime() {
		return meetingEtime;
	}

	public void setMeetingEtime(String meetingEtime) {
		this.meetingEtime = meetingEtime;
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
	
	/** default constructor */
    public Hfr1003Db() {
    }

	public String toString() 
	{
		StringBuilder builder = new StringBuilder();
		builder.append("Hfr1003Db [id=");
		builder.append(id);
		builder.append(", meetingYear=");
		builder.append(meetingYear);
		builder.append(", meetingSeason=");
		builder.append(meetingSeason);
		builder.append(", meetingDate=");
		builder.append(meetingDate);
		builder.append(", meetingStime=");
		builder.append(meetingStime);
		builder.append(", meetingEtime=");
		builder.append(meetingEtime);
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
    

	
}
