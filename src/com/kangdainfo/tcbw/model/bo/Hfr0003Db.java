package com.kangdainfo.tcbw.model.bo;

import org.apache.commons.lang.builder.ToStringBuilder;

public class Hfr0003Db implements java.io.Serializable {

	private Long id;
	private Hfr0001Db hfr0001Db;
	private String discriptDate;
	private String position;
	private String symptom;
	private String severity;
	private String doResponse;
	private String creator;
    private String createDate;
    private String createTime;
    private String modifier;
    private String modifyDate;
    private String modifyTime;
    
    public Hfr0003Db(){
    	
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
	
	public String getDiscriptDate() {
		return discriptDate;
	}

	public void setDiscriptDate(String discriptDate) {
		this.discriptDate = discriptDate;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getSymptom() {
		return symptom;
	}

	public void setSymptom(String symptom) {
		this.symptom = symptom;
	}

	public String getSeverity() {
		return severity;
	}

	public void setSeverity(String severity) {
		this.severity = severity;
	}

	public String getDoResponse() {
		return doResponse;
	}

	public void setDoResponse(String doResponse) {
		this.doResponse = doResponse;
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
