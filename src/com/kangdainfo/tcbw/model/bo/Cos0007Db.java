package com.kangdainfo.tcbw.model.bo;

import java.io.Serializable;

public class Cos0007Db  implements Serializable 
{
	/** identifier field */
    private Long id;
    private Cos0006Db cos0006Db;	//化妝品不良品分類資料檔    
    private String applNo;	//案件號碼
    private String assessMan;	//評估人員
    private String assessDate;	//評估日期
    private String assessResult;	//評估結果
    private String assessRemark;	//評估備註    
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
    public Cos0007Db() {
    }
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
    public Cos0006Db getCos0006Db() {
		return cos0006Db;
	}

	public void setCos0006Db(Cos0006Db cos0006Db) {
		this.cos0006Db = cos0006Db;
	}

	public String getApplNo() {
		return applNo;
	}

	public void setApplNo(String applNo) {
		this.applNo = applNo;
	}

	public String getAssessMan() {
		return assessMan;
	}

	public void setAssessMan(String assessMan) {
		this.assessMan = assessMan;
	}

	public String getAssessDate() {
		return assessDate;
	}

	public void setAssessDate(String assessDate) {
		this.assessDate = assessDate;
	}

	public String getAssessResult() {
		return assessResult;
	}

	public void setAssessResult(String assessResult) {
		this.assessResult = assessResult;
	}

	public String getAssessRemark() {
		return assessRemark;
	}

	public void setAssessRemark(String assessRemark) {
		this.assessRemark = assessRemark;
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
		builder.append("Cos0007Db [id=");
		builder.append(id);
		builder.append(", cos0006Db=");
		builder.append(cos0006Db);
		builder.append(", applNo=");
		builder.append(applNo);
		builder.append(", assessMan=");
		builder.append(assessMan);
		builder.append(", assessDate=");
		builder.append(assessDate);
		builder.append(", assessResult=");
		builder.append(assessResult);
		builder.append(", assessRemark=");
		builder.append(assessRemark);
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
