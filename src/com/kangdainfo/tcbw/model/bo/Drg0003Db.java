package com.kangdainfo.tcbw.model.bo;

import java.io.Serializable;

public class Drg0003Db  implements Serializable 
{
	
	/** identifier field */
    private Long id;
    
    private String applNo;	//案件號碼
    private String assessDate;	//初評人員
    private String assessMan;	//初評人員
    private String firstResult;	//不良品風險評估結果
    private String notifierAging;	//通報時效
    private String notifierQuality;	//通報品質
    private String intervalDays;	//間隔天數
    private String remark;	//備註
    
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

	public String getApplNo() {
		return applNo;
	}

	public void setApplNo(String applNo) {
		this.applNo = applNo;
	}

	public String getAssessDate() {
		return assessDate;
	}

	public void setAssessDate(String assessDate) {
		this.assessDate = assessDate;
	}

	public String getAssessMan() {
		return assessMan;
	}

	public void setAssessMan(String assessMan) {
		this.assessMan = assessMan;
	}

	public String getFirstResult() {
		return firstResult;
	}

	public void setFirstResult(String firstResult) {
		this.firstResult = firstResult;
	}

	public String getNotifierAging() {
		return notifierAging;
	}

	public void setNotifierAging(String notifierAging) {
		this.notifierAging = notifierAging;
	}

	public String getNotifierQuality() {
		return notifierQuality;
	}

	public void setNotifierQuality(String notifierQuality) {
		this.notifierQuality = notifierQuality;
	}

	public String getIntervalDays() {
		return intervalDays;
	}

	public void setIntervalDays(String intervalDays) {
		this.intervalDays = intervalDays;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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
    public Drg0003Db() {
    }

	public String toString() 
	{
		StringBuilder builder = new StringBuilder();
		builder.append("Drg0003Db [id=");
		builder.append(id);
		builder.append(", applNo=");
		builder.append(applNo);
		builder.append(", assessDate=");
		builder.append(assessDate);
		builder.append(", assessMan=");
		builder.append(assessMan);
		builder.append(", firstResult=");
		builder.append(firstResult);
		builder.append(", notifierAging=");
		builder.append(notifierAging);
		builder.append(", notifierQuality=");
		builder.append(notifierQuality);
		builder.append(", intervalDays=");
		builder.append(intervalDays);
		builder.append(", remark=");
		builder.append(remark);
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
