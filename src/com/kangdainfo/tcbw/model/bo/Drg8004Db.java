package com.kangdainfo.tcbw.model.bo;

import java.io.Serializable;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;

public class Drg8004Db implements Serializable{
	
	private Long id;
	private String status;
	private String assessextendate;
	private String assessdate;
	private String assessrecyclereason;
	private String assesssurveyresult;
	private String assesssurveyreport;
	private String assessprecaution;
	private String assessdealWith;
	private String assessresult;
	private String anadate;
	private String anamedicineType;
	private String anaproduceType;
	private String analotType;
	private String anarecyclereason;
	private String anarecyclersdesc;
	private String anasurvey;
	private String anasurveyOther;
	private String anaprecaution;
	private String anaprecautionOther;
	private String receiveSystem;
	private String receiveDate;
	private String receiveTime;
	private String creator;
	private String createDate;
	private String createTime;
	private String modifier;
	private String modifyDate;
	private String modifyTime;
	private com.kangdainfo.tcbw.model.bo.Drg8001Db Drg8001Db;
	
	public Drg8004Db() {		
	    }
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}	
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAssessextendate() {
		return assessextendate;
	}

	public void setAssessextendate(String assessextendate) {
		this.assessextendate = assessextendate;
	}

	public String getAssessdate() {
		return assessdate;
	}

	public void setAssessdate(String assessdate) {
		this.assessdate = assessdate;
	}

	public String getAssessrecyclereason() {
		return assessrecyclereason;
	}

	public void setAssessrecyclereason(String assessrecyclereason) {
		this.assessrecyclereason = assessrecyclereason;
	}

	public String getAssesssurveyresult() {
		return assesssurveyresult;
	}

	public void setAssesssurveyresult(String assesssurveyresult) {
		this.assesssurveyresult = assesssurveyresult;
	}

	public String getAssesssurveyreport() {
		return assesssurveyreport;
	}

	public void setAssesssurveyreport(String assesssurveyreport) {
		this.assesssurveyreport = assesssurveyreport;
	}

	public String getAssessprecaution() {
		return assessprecaution;
	}

	public void setAssessprecaution(String assessprecaution) {
		this.assessprecaution = assessprecaution;
	}

	public String getAssessdealWith() {
		return assessdealWith;
	}

	public void setAssessdealWith(String assessdealWith) {
		this.assessdealWith = assessdealWith;
	}

	public String getAssessresult() {
		return assessresult;
	}

	public void setAssessresult(String assessresult) {
		this.assessresult = assessresult;
	}

	public String getAnadate() {
		return anadate;
	}

	public void setAnadate(String anadate) {
		this.anadate = anadate;
	}

	public String getAnamedicineType() {
		return anamedicineType;
	}

	public void setAnamedicineType(String anamedicineType) {
		this.anamedicineType = anamedicineType;
	}

	public String getAnaproduceType() {
		return anaproduceType;
	}

	public void setAnaproduceType(String anaproduceType) {
		this.anaproduceType = anaproduceType;
	}

	public String getAnalotType() {
		return analotType;
	}

	public void setAnalotType(String analotType) {
		this.analotType = analotType;
	}

	public String getAnarecyclereason() {
		return anarecyclereason;
	}

	public void setAnarecyclereason(String anarecyclereason) {
		this.anarecyclereason = anarecyclereason;
	}

	public String getAnarecyclersdesc() {
		return anarecyclersdesc;
	}

	public void setAnarecyclersdesc(String anarecyclersdesc) {
		this.anarecyclersdesc = anarecyclersdesc;
	}

	public String getAnasurvey() {
		return anasurvey;
	}

	public void setAnasurvey(String anasurvey) {
		this.anasurvey = anasurvey;
	}

	public String getAnasurveyOther() {
		return anasurveyOther;
	}

	public void setAnasurveyOther(String anasurveyOther) {
		this.anasurveyOther = anasurveyOther;
	}

	public String getAnaprecaution() {
		return anaprecaution;
	}

	public void setAnaprecaution(String anaprecaution) {
		this.anaprecaution = anaprecaution;
	}

	public String getAnaprecautionOther() {
		return anaprecautionOther;
	}

	public void setAnaprecautionOther(String anaprecautionOther) {
		this.anaprecautionOther = anaprecautionOther;
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
	
	public com.kangdainfo.tcbw.model.bo.Drg8001Db getDrg8001Db() {
		return Drg8001Db;
	}

	public void setDrg8001Db(com.kangdainfo.tcbw.model.bo.Drg8001Db drg8001Db) {
		Drg8001Db = drg8001Db;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
						.append("id", getId())
						.toString();
	}
}
