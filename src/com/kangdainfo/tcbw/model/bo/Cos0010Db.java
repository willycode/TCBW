package com.kangdainfo.tcbw.model.bo;

import java.io.Serializable;

public class Cos0010Db  implements Serializable 
{
	/** identifier field */
    private Long id;
    private String applNo;	//案件號碼
    private String notifyDate;	//通知日期
    private String isComplaintYn;	//是否接獲該產品其他消費者客訴
    private String complaintNum;	//客訴件數
    private String dealWith;	//類似客訴案件之後續處理情形
    private String notifierProcess;
    private String replyOther;	//其他回覆
    private String replyDate;	//回覆日期
    private String isClose;
    private String receiveSystem;	//介接異動系統
    private String receiveDate;	//介接異動日期
    private String receiveTime;	 //介接異動時間
    private String creator;	 //建檔者
    private String createDate;  //建檔日期
    private String createTime;  //建檔時間
    private String modifier;  //最後異動者
    private String modifyDate;  // 最後異動日期
    private String modifyTime;  //最後異動時間
    private Long cos0009DbId;

	/** default constructor */
    public Cos0010Db() {
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getApplNo() {
		return applNo;
	}

	public void setApplNo(String applNo) {
		this.applNo = applNo;
	}

	public String getNotifyDate() {
		return notifyDate;
	}

	public void setNotifyDate(String notifyDate) {
		this.notifyDate = notifyDate;
	}

	public String getIsComplaintYn() {
		return isComplaintYn;
	}

	public void setIsComplaintYn(String isComplaintYn) {
		this.isComplaintYn = isComplaintYn;
	}

	public String getComplaintNum() {
		return complaintNum;
	}

	public void setComplaintNum(String complaintNum) {
		this.complaintNum = complaintNum;
	}

	public String getDealWith() {
		return dealWith;
	}

	public void setDealWith(String dealWith) {
		this.dealWith = dealWith;
	}

	public String getReplyOther() {
		return replyOther;
	}

	public String getNotifierProcess() {
		return notifierProcess;
	}

	public void setNotifierProcess(String notifierProcess) {
		this.notifierProcess = notifierProcess;
	}

	public void setReplyOther(String replyOther) {
		this.replyOther = replyOther;
	}

	public String getReplyDate() {
		return replyDate;
	}

	public void setReplyDate(String replyDate) {
		this.replyDate = replyDate;
	}

	public String getIsClose() {
		return isClose;
	}

	public void setIsClose(String isClose) {
		this.isClose = isClose;
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

	public Long getCos0009DbId() {
		return cos0009DbId;
	}

	public void setCos0009DbId(Long cos0009DbId) {
		this.cos0009DbId = cos0009DbId;
	}

	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append("Cos0010Db [id=");
		builder.append(id);
		builder.append(", applNo=");
		builder.append(applNo);
		builder.append(", notifyDate=");
		builder.append(notifyDate);
		builder.append(", isComplaintYn=");
		builder.append(isComplaintYn);
		builder.append(", complaintNum=");
		builder.append(complaintNum);
		builder.append(", dealWith=");
		builder.append(dealWith);
		builder.append(", notifierProcess=");
		builder.append(notifierProcess);
		builder.append(", replyOther=");
		builder.append(replyOther);
		builder.append(", replyDate=");
		builder.append(replyDate);
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
		builder.append(", cos0009DbId=");
		builder.append(cos0009DbId);
		builder.append("]");
		return builder.toString();
	}
	
}
