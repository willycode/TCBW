package com.kangdainfo.tcbw.model.bo;

import java.io.Serializable;

public class Cos0015Db  implements Serializable 
{
	/** identifier field */
    private Long id;
    private String cosType;	 //不良事件類別
    private String applNo;	//案件號碼
    private String chargeMan;	//作業人員
    private String cosCorrelation;	//不良反應與可疑化粧品相關性
    private String feedBack;	//對通報者的回饋
    private String recordOpinion;	//紀錄者意見
    private String receiveSystem;	//介接異動系統
    private String receiveDate;	//介接異動日期
    private String receiveTime;	 //介接異動時間
    private String creator;	 //建檔者
    private String createDate;  //建檔日期
    private String createTime;  //建檔時間
    private String modifier;  //最後異動者
    private String modifyDate;  // 最後異動日期
    private String modifyTime;  //最後異動時間

	/** default constructor */
    public Cos0015Db() {
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCosType() {
		return cosType;
	}

	public void setCosType(String cosType) {
		this.cosType = cosType;
	}

	public String getApplNo() {
		return applNo;
	}

	public void setApplNo(String applNo) {
		this.applNo = applNo;
	}

	public String getChargeMan() {
		return chargeMan;
	}

	public void setChargeMan(String chargeMan) {
		this.chargeMan = chargeMan;
	}

	public String getCosCorrelation() {
		return cosCorrelation;
	}

	public void setCosCorrelation(String cosCorrelation) {
		this.cosCorrelation = cosCorrelation;
	}

	public String getFeedBack() {
		return feedBack;
	}

	public void setFeedBack(String feedBack) {
		this.feedBack = feedBack;
	}

	public String getRecordOpinion() {
		return recordOpinion;
	}

	public void setRecordOpinion(String recordOpinion) {
		this.recordOpinion = recordOpinion;
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

	public String toString() 
	{
		StringBuilder builder = new StringBuilder();
		builder.append("Cos0015Db [id=");
		builder.append(id);
		builder.append(", cosType=");
		builder.append(cosType);
		builder.append(", applNo=");
		builder.append(applNo);
		builder.append(", chargeMan=");
		builder.append(chargeMan);
		builder.append(", cosCorrelation=");
		builder.append(cosCorrelation);
		builder.append(", feedBack=");
		builder.append(feedBack);
		builder.append(", recordOpinion=");
		builder.append(recordOpinion);
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
