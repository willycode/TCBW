package com.kangdainfo.tcbw.model.bo;

import org.apache.commons.lang.builder.ToStringBuilder;

public class Hfr0005Db implements java.io.Serializable{

	
	private Long id;
	private Hfr0001Db hfr0001Db;
	private String preCompleteDate;
	private String assessmentSendDate;
	private String assessmentCompleteDate;
	private String caseType;
	private String unExpectedClassify;
	private String unExpectedReason;
	private String evidentiary;
	private String preSeverity;
	private String administrativeLevel;
	private String preOpinion;
	private String preRemark;
	private String preExplain;
	private String unPreExplain;
	private String isClosed;
	private String creator;
    private String createDate;
    private String createTime;
    private String modifier;
    private String modifyDate;
    private String modifyTime;
    
    public Hfr0005Db(){
    	
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Hfr0001Db getHfr0001Db() {
		return hfr0001Db;
	}

	public void setHfr0001Db(Hfr0001Db hfr0001Db) {
		this.hfr0001Db = hfr0001Db;
	}

	public String getPreCompleteDate() {
		return preCompleteDate;
	}

	public void setPreCompleteDate(String preCompleteDate) {
		this.preCompleteDate = preCompleteDate;
	}

	public String getAssessmentSendDate() {
		return assessmentSendDate;
	}

	public void setAssessmentSendDate(String assessmentSendDate) {
		this.assessmentSendDate = assessmentSendDate;
	}
	
	public String getAssessmentCompleteDate() {
		return assessmentCompleteDate;
	}

	public void setAssessmentCompleteDate(String assessmentCompleteDate) {
		this.assessmentCompleteDate = assessmentCompleteDate;
	}

	public String getCaseType() {
		return caseType;
	}

	public void setCaseType(String caseType) {
		this.caseType = caseType;
	}

	public String getUnExpectedClassify() {
		return unExpectedClassify;
	}

	public void setUnExpectedClassify(String unExpectedClassify) {
		this.unExpectedClassify = unExpectedClassify;
	}
	
	public String getUnExpectedReason() {
		return unExpectedReason;
	}

	public void setUnExpectedReason(String unExpectedReason) {
		this.unExpectedReason = unExpectedReason;
	}

	public String getEvidentiary() {
		return evidentiary;
	}

	public void setEvidentiary(String evidentiary) {
		this.evidentiary = evidentiary;
	}

	public String getPreSeverity() {
		return preSeverity;
	}

	public void setPreSeverity(String preSeverity) {
		this.preSeverity = preSeverity;
	}

	public String getAdministrativeLevel() {
		return administrativeLevel;
	}

	public void setAdministrativeLevel(String administrativeLevel) {
		this.administrativeLevel = administrativeLevel;
	}

	public String getPreOpinion() {
		return preOpinion;
	}

	public void setPreOpinion(String preOpinion) {
		this.preOpinion = preOpinion;
	}

	public String getPreRemark() {
		return preRemark;
	}

	public void setPreRemark(String preRemark) {
		this.preRemark = preRemark;
	}
	
	public String getPreExplain() {
		return preExplain;
	}

	public void setPreExplain(String preExplain) {
		this.preExplain = preExplain;
	}
	
	public String getUnPreExplain() {
		return unPreExplain;
	}

	public void setUnPreExplain(String unPreExplain) {
		this.unPreExplain = unPreExplain;
	}

	public String getIsClosed() {
		return isClosed;
	}

	public void setIsClosed(String isClosed) {
		this.isClosed = isClosed;
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
    
	@Override
	public String toString() {
		return new ToStringBuilder(this)
						.append("id", getId())
						.toString();
	}

	
}
