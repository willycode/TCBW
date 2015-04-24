package com.kangdainfo.tcbw.model.bo;

import java.io.Serializable;

public class Drg0008Db  implements Serializable 
{
	/** identifier field */
    private Long id;
    
    private String applNo;	//案件號碼
    private String assessDate; //評估日期
    private String assessMan; //評估人員
    private String payDate;	//廠商交付CAPA日期
    private String isPostYn;	//是否發文
    private String fdaPostNo;	//FDA發文字號
    private String reason;	//不發文理由
    private String delayDate;	//展延日期
    private String dealWith;	//擬辦事項
    private String drgLev;	//風險等級
    private String capaDownDate;	//CAPA執行完成日期
    private String drgReason;	//本案原由
    private String assessResult;	//評估結果
    private String addDocDate;	//補件日期
    private String checkResult;	//清查結果	NVARCHAR(100)
    private String survey;	//調查結果	NVARCHAR(100)
    private String precaution;	//預防矯正措施及改善時程	NVARCHAR(100)
    private String reAssessDate; //再評估日期
    private String reAssessMan; //再評估人員


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
    public Drg0008Db() {
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

	public String getPayDate() {
		return payDate;
	}

	public void setPayDate(String payDate) {
		this.payDate = payDate;
	}

	public String getIsPostYn() {
		return isPostYn;
	}

	public void setIsPostYn(String isPostYn) {
		this.isPostYn = isPostYn;
	}

	public String getFdaPostNo() {
		return fdaPostNo;
	}

	public void setFdaPostNo(String fdaPostNo) {
		this.fdaPostNo = fdaPostNo;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getDelayDate() {
		return delayDate;
	}

	public void setDelayDate(String delayDate) {
		this.delayDate = delayDate;
	}

	public String getDealWith() {
		return dealWith;
	}

	public void setDealWith(String dealWith) {
		this.dealWith = dealWith;
	}

	public String getAssessResult() {
		return assessResult;
	}

	public void setAssessResult(String assessResult) {
		this.assessResult = assessResult;
	}

	public String getAddDocDate() {
		return addDocDate;
	}

	public void setAddDocDate(String addDocDate) {
		this.addDocDate = addDocDate;
	}

	public String getCheckResult() {
		return checkResult;
	}

	public void setCheckResult(String checkResult) {
		this.checkResult = checkResult;
	}

	public String getSurvey() {
		return survey;
	}

	public void setSurvey(String survey) {
		this.survey = survey;
	}

	public String getPrecaution() {
		return precaution;
	}

	public void setPrecaution(String precaution) {
		this.precaution = precaution;
	}

	public String getDrgLev() {
		return drgLev;
	}

	public void setDrgLev(String drgLev) {
		this.drgLev = drgLev;
	}	
	
	public String getCapaDownDate() {
		return capaDownDate;
	}

	public void setCapaDownDate(String capaDownDate) {
		this.capaDownDate = capaDownDate;
	}

	public String getDrgReason() {
		return drgReason;
	}

	public void setDrgReason(String drgReason) {
		this.drgReason = drgReason;
	}	
	
	public String getReAssessDate() {
		return reAssessDate;
	}

	public void setReAssessDate(String reAssessDate) {
		this.reAssessDate = reAssessDate;
	}

	public String getReAssessMan() {
		return reAssessMan;
	}

	public void setReAssessMan(String reAssessMan) {
		this.reAssessMan = reAssessMan;
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
		builder.append("Drg0008Db [id=");
		builder.append(id);
		builder.append(", applNo=");
		builder.append(applNo);
		builder.append(", assessDate=");
		builder.append(assessDate);
		builder.append(", assessMan=");
		builder.append(assessMan);
		builder.append(", payDate=");
		builder.append(payDate);
		builder.append(", isPostYn=");
		builder.append(isPostYn);
		builder.append(", fdaPostNo=");
		builder.append(fdaPostNo);
		builder.append(", reason=");
		builder.append(reason);
		builder.append(", delayDate=");
		builder.append(delayDate);
		builder.append(", dealWith=");
		builder.append(dealWith);
		builder.append(", drgReason=");
		builder.append(drgReason);
		builder.append(", drgLev=");
		builder.append(drgLev);
		builder.append(", capaDownDate=");
		builder.append(capaDownDate);
		builder.append(", assessResult=");
		builder.append(assessResult);
		builder.append(", addDocDate=");
		builder.append(addDocDate);
		builder.append(", checkResult=");
		builder.append(checkResult);
		builder.append(", survey=");
		builder.append(survey);
		builder.append(", precaution=");
		builder.append(precaution);
		builder.append(", reAssessDate=");
		builder.append(reAssessDate);
		builder.append(", assessMan=");
		builder.append(assessMan);
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
