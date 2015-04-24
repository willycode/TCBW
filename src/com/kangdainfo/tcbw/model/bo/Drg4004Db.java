package com.kangdainfo.tcbw.model.bo;

import java.io.Serializable;

public class Drg4004Db  implements Serializable 
{
	
	/** identifier field */
    private Long id;
    
    private String applNo;			//案件號碼
    private String gradeMan;		//分級確認人員
    private String gradeDate;		//分級確認日期
    private String medNti;			//藥品成分-NTI Drugs
    private String medAtcCode;		//藥品成分-藥理治療分類(ATC code)
    private String conSequence;		//通報事件後果
    private String effectChange;	//通報事件後果-藥效改變
    private String effectChangeDesc;//通報事件後果-藥效改變狀況
    private String badReaction;		//通報事件後果-不良反應
    private String badReactionLev;	//通報事件後果-不良反應等級
    private String badReactionDesc;	//通報事件後果-不良反應狀況
    private String badReactionDra;	//通報事件後果-不良反應MedDRA代碼
    private String assessEC1;		//相關性評估-藥效改變-時序姓
    private String assessEC2;		//相關性評估-藥效改變-併用藥物
    private String assessEC3;		//相關性評估-藥效改變-維持藥效
    private String assessBR1;		//相關性評估-不良反應-時序姓
    private String assessBR2;		//相關性評估-不良反應-併用藥物
    private String assessBR3;		//相關性評估-不良反應-減輕或消失
    private String assessResult;	//療效不等評估結果
    private String notifierAging;	//通報時效
    private String notifierQuality;	//通報品質
    private String assessRemark;	//評估註記
    private String intervalDays;	//間隔天數
    private String remark;			//備註
    private String isCouncilYn;		//是否提報諮議會
    
    private String receiveSystem;//介接異動系統
    private String receiveDate;//介接異動日期
    private String receiveTime;//介接異動時間
    private String creator;//建檔者
    private String createDate;//建檔日期
    private String createTime;//建檔時間
    private String modifier;//最後異動者
    private String modifyDate;// 最後異動日期
    private String modifyTime;//最後異動時間
    
    private String trans;	          //是否為轉檔資料
    private String old_CaseNoE;	      //案件號碼(Excel)

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

	public String getGradeMan() {
		return gradeMan;
	}

	public void setGradeMan(String gradeMan) {
		this.gradeMan = gradeMan;
	}

	public String getGradeDate() {
		return gradeDate;
	}

	public void setGradeDate(String gradeDate) {
		this.gradeDate = gradeDate;
	}

	public String getMedNti() {
		return medNti;
	}

	public void setMedNti(String medNti) {
		this.medNti = medNti;
	}

	public String getMedAtcCode() {
		return medAtcCode;
	}

	public void setMedAtcCode(String medAtcCode) {
		this.medAtcCode = medAtcCode;
	}

	public String getConSequence() {
		return conSequence;
	}

	public void setConSequence(String conSequence) {
		this.conSequence = conSequence;
	}

	public String getEffectChange() {
		return effectChange;
	}

	public void setEffectChange(String effectChange) {
		this.effectChange = effectChange;
	}

	public String getEffectChangeDesc() {
		return effectChangeDesc;
	}

	public void setEffectChangeDesc(String effectChangeDesc) {
		this.effectChangeDesc = effectChangeDesc;
	}

	public String getBadReaction() {
		return badReaction;
	}

	public void setBadReaction(String badReaction) {
		this.badReaction = badReaction;
	}

	public String getBadReactionLev() {
		return badReactionLev;
	}

	public void setBadReactionLev(String badReactionLev) {
		this.badReactionLev = badReactionLev;
	}

	public String getBadReactionDesc() {
		return badReactionDesc;
	}

	public void setBadReactionDesc(String badReactionDesc) {
		this.badReactionDesc = badReactionDesc;
	}

	public String getBadReactionDra() {
		return badReactionDra;
	}

	public void setBadReactionDra(String badReactionDra) {
		this.badReactionDra = badReactionDra;
	}

	public String getAssessEC1() {
		return assessEC1;
	}

	public void setAssessEC1(String assessEC1) {
		this.assessEC1 = assessEC1;
	}

	public String getAssessEC2() {
		return assessEC2;
	}

	public void setAssessEC2(String assessEC2) {
		this.assessEC2 = assessEC2;
	}

	public String getAssessEC3() {
		return assessEC3;
	}

	public void setAssessEC3(String assessEC3) {
		this.assessEC3 = assessEC3;
	}

	public String getAssessBR1() {
		return assessBR1;
	}

	public void setAssessBR1(String assessBR1) {
		this.assessBR1 = assessBR1;
	}

	public String getAssessBR2() {
		return assessBR2;
	}

	public void setAssessBR2(String assessBR2) {
		this.assessBR2 = assessBR2;
	}

	public String getAssessBR3() {
		return assessBR3;
	}

	public void setAssessBR3(String assessBR3) {
		this.assessBR3 = assessBR3;
	}

	public String getAssessResult() {
		return assessResult;
	}

	public void setAssessResult(String assessResult) {
		this.assessResult = assessResult;
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

	public String getAssessRemark() {
		return assessRemark;
	}

	public void setAssessRemark(String assessRemark) {
		this.assessRemark = assessRemark;
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

	public String getIsCouncilYn() {
		return isCouncilYn;
	}

	public void setIsCouncilYn(String isCouncilYn) {
		this.isCouncilYn = isCouncilYn;
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

	public String getTrans() {
		return trans;
	}
	public void setTrans(String trans) {
		this.trans = trans;
	}
	public String getOld_CaseNoE() {
		return old_CaseNoE;
	}
	public void setOld_CaseNoE(String old_CaseNoE) {
		this.old_CaseNoE = old_CaseNoE;
	}

	/** default constructor */
    public Drg4004Db() {
    }

	public String toString() 
	{
		StringBuilder builder = new StringBuilder();
		builder.append("Drg4004Db [id=");
		builder.append(id);	
		builder.append(", applNo=");
		builder.append(applNo);
		builder.append(", gradeMan=");
		builder.append(gradeMan);
		builder.append(", gradeDate=");
		builder.append(gradeDate);
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
		builder.append(", trans=");
		builder.append(trans);
		builder.append(", old_CaseNoE=");
		builder.append(old_CaseNoE);	
		builder.append("]");
		return builder.toString();
	}    
    

	
}
