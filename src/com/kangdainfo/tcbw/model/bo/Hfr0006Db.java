package com.kangdainfo.tcbw.model.bo;

import org.apache.commons.lang.builder.ToStringBuilder;

public class Hfr0006Db implements java.io.Serializable{

	private Long id;
	private Hfr0001Db hfr0001Db;
	private String evaluateDate;
	private Long evaluateCommittee;
	private String unExpectedClassify;
	private String unExpectedReason;
	private String evidentiary;
	private String secSeverity;
	private String administrativeLevel;
	private String assessments;
	private String evaluateResult;
	private String isClosed;
	private String secExplain;
	private String unSecExplain;
	private String creator;
    private String createDate;
    private String createTime;
    private String modifier;
    private String modifyDate;
    private String modifyTime;
    
    public Hfr0006Db(){
    	
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
	
	public Long getEvaluateCommittee() {
		return evaluateCommittee;
	}

	public void setEvaluateCommittee(Long evaluateCommittee) {
		this.evaluateCommittee = evaluateCommittee;
	}

	public String getAssessments() {
		return assessments;
	}

	public void setAssessments(String assessments) {
		this.assessments = assessments;
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

	public String getSecSeverity() {
		return secSeverity;
	}

	public void setSecSeverity(String secSeverity) {
		this.secSeverity = secSeverity;
	}

	public String getAdministrativeLevel() {
		return administrativeLevel;
	}

	public void setAdministrativeLevel(String administrativeLevel) {
		this.administrativeLevel = administrativeLevel;
	}
	
	public String getEvaluateDate() {
		return evaluateDate;
	}

	public void setEvaluateDate(String evaluateDate) {
		this.evaluateDate = evaluateDate;
	}

	public String getEvaluateResult() {
		return evaluateResult;
	}

	public void setEvaluateResult(String evaluateResult) {
		this.evaluateResult = evaluateResult;
	}
	
	public String getIsClosed() {
		return isClosed;
	}

	public void setIsClosed(String isClosed) {
		this.isClosed = isClosed;
	}
	
	public String getSecExplain() {
		return secExplain;
	}

	public void setSecExplain(String secExplain) {
		this.secExplain = secExplain;
	}
	
	public String getUnSecExplain() {
		return unSecExplain;
	}

	public void setUnSecExplain(String unSecExplain) {
		this.unSecExplain = unSecExplain;
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
