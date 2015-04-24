package com.kangdainfo.tcbw.model.bo;

import org.apache.commons.lang.builder.ToStringBuilder;

public class Hfr0007Db implements java.io.Serializable{

	
	private Long id;
	private Hfr0001Db hfr0001Db;
	private String committeeMeeting;
	private String committeeDate;
	private String recordDate;
	private String caseBackDate;
	private String unExpectedClassify;
	private String unExpectedReason;
	private String evidentiary;
	private String thiSeverity;
	private String administrativeLevel;
	private String reEvaluateResult;
	private String thiExplain;
	private String unThiExplain;
	private String isClosed;
	private String creator;
    private String createDate;
    private String createTime;
    private String modifier;
    private String modifyDate;
    private String modifyTime;
    
    public Hfr0007Db(){}

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

	public String getCommitteeMeeting() {
		return committeeMeeting;
	}

	public void setCommitteeMeeting(String committeeMeeting) {
		this.committeeMeeting = committeeMeeting;
	}

	public String getCommitteeDate() {
		return committeeDate;
	}

	public void setCommitteeDate(String committeeDate) {
		this.committeeDate = committeeDate;
	}

	public String getRecordDate() {
		return recordDate;
	}

	public void setRecordDate(String recordDate) {
		this.recordDate = recordDate;
	}

	public String getCaseBackDate() {
		return caseBackDate;
	}

	public void setCaseBackDate(String caseBackDate) {
		this.caseBackDate = caseBackDate;
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

	public String getThiSeverity() {
		return thiSeverity;
	}

	public void setThiSeverity(String thiSeverity) {
		this.thiSeverity = thiSeverity;
	}

	public String getAdministrativeLevel() {
		return administrativeLevel;
	}

	public void setAdministrativeLevel(String administrativeLevel) {
		this.administrativeLevel = administrativeLevel;
	}

	public String getReEvaluateResult() {
		return reEvaluateResult;
	}

	public void setReEvaluateResult(String reEvaluateResult) {
		this.reEvaluateResult = reEvaluateResult;
	}
	
	public String getIsClosed() {
		return isClosed;
	}

	public void setIsClosed(String isClosed) {
		this.isClosed = isClosed;
	}	

	public String getThiExplain() {
		return thiExplain;
	}

	public void setThiExplain(String thiExplain) {
		this.thiExplain = thiExplain;
	}
	
	public String getUnThiExplain() {
		return unThiExplain;
	}

	public void setUnThiExplain(String unThiExplain) {
		this.unThiExplain = unThiExplain;
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
