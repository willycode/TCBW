package com.kangdainfo.tcbw.model.bo;

import java.io.Serializable;
import java.util.Set;

public class Med9001Db  implements Serializable 
{
	/** identifier field */
    private Long id;
    private String applNo;    
    private String monitorNo;
    private String permitKey;
    private String permitNo;
    private String chProduct;
    private String enProduct;
    private String medapprovedate;
    private String medEffectiveDate;
    private String applicationID;
    private String applicationName;
    private String manufactorName;
    private String manufactorCountry;
    private String medclass;
    private String medMainCategory;
    private String medSecCategory;
    private String medModel;
    private String medeffect;
    private String monitorSDate;
    private String monitorEDate;
    private Long reportIssuenum;
    private Long intervalmonth;
    private String monitorremark;
    private String status;    
    
    private String bulletinDate;
    private String assessPerson;
    private String scheduledDate;
    private String determineDate;
    
    private String closingDate;
    
    private String trans;
    
    private String receiveSystem;
    private String receiveDate;
    private String receiveTime;
    private String creator;
    private String createDate;
    private String createTime;
    private String modifier;
    private String modifyDate;
    private String modifyTime;
    private Set med9002Dbs;
	private Set med9003Dbs;
	
	/** default constructor */
    public Med9001Db() {

    }
  
	public String toString() 
	{
		StringBuilder builder = new StringBuilder();
		builder.append("Med9001Db [id=");
		builder.append(id);
		builder.append(", applNo=");
		builder.append(applNo);		
		builder.append(", monitorNo=");
		builder.append(monitorNo);
		builder.append(", permitKey=");
		builder.append(permitKey);
		builder.append(", permitNo=");
		builder.append(permitNo);
		builder.append(", chProduct =");
		builder.append(chProduct );
		builder.append(", enProduct=");
		builder.append(enProduct);
		builder.append(", medapprovedate=");
		builder.append(medapprovedate);
		builder.append(", medEffectiveDate=");
		builder.append(medEffectiveDate);
		builder.append(", applicationID=");
		builder.append(applicationID);
		builder.append(", applicationName=");
		builder.append(applicationName);
		builder.append(", manufactorName=");
		builder.append(manufactorName);
		builder.append(", manufactorCountry=");
		builder.append(manufactorCountry);
		builder.append(", medclass=");
		builder.append(medclass);
		builder.append(", medMainCategory=");
		builder.append(medMainCategory);
		builder.append(", medSecCategory=");
		builder.append(medSecCategory);
		builder.append(", medModel=");
		builder.append(medModel);
		builder.append(", medeffect=");
		builder.append(medeffect);
		builder.append(", monitorSDate=");
		builder.append(monitorSDate);
		builder.append(", monitorEDate=");
		builder.append(monitorEDate);
		builder.append(", reportIssuenum=");
		builder.append(reportIssuenum);
		builder.append(", intervalmonth=");
		builder.append(intervalmonth);
		builder.append(", monitorremark=");
		builder.append(monitorremark);
		builder.append(", status=");
		builder.append(status);
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
	
	public String getMonitorNo() {
		return monitorNo;
	}

	public void setMonitorNo(String monitorNo) {
		this.monitorNo = monitorNo;
	}

	public String getPermitKey() {
		return permitKey;
	}

	public void setPermitKey(String permitKey) {
		this.permitKey = permitKey;
	}

	public String getPermitNo() {
		return permitNo;
	}

	public void setPermitNo(String permitNo) {
		this.permitNo = permitNo;
	}

	public String getChProduct() {
		return chProduct;
	}

	public void setChProduct(String chProduct) {
		this.chProduct = chProduct;
	}

	public String getEnProduct() {
		return enProduct;
	}

	public void setEnProduct(String enProduct) {
		this.enProduct = enProduct;
	}

	public String getMedapprovedate() {
		return medapprovedate;
	}

	public void setMedapprovedate(String medapprovedate) {
		this.medapprovedate = medapprovedate;
	}

	public String getMedEffectiveDate() {
		return medEffectiveDate;
	}

	public void setMedEffectiveDate(String medEffectiveDate) {
		this.medEffectiveDate = medEffectiveDate;
	}
	
	public String getApplicationID() {
		return applicationID;
	}

	public void setApplicationID(String applicationID) {
		this.applicationID = applicationID;
	}

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	public String getManufactorName() {
		return manufactorName;
	}

	public void setManufactorName(String manufactorName) {
		this.manufactorName = manufactorName;
	}

	public String getManufactorCountry() {
		return manufactorCountry;
	}

	public void setManufactorCountry(String manufactorCountry) {
		this.manufactorCountry = manufactorCountry;
	}

	public String getMedclass() {
		return medclass;
	}

	public void setMedclass(String medclass) {
		this.medclass = medclass;
	}

	public String getMedMainCategory() {
		return medMainCategory;
	}

	public void setMedMainCategory(String medMainCategory) {
		this.medMainCategory = medMainCategory;
	}

	public String getMedSecCategory() {
		return medSecCategory;
	}

	public void setMedSecCategory(String medSecCategory) {
		this.medSecCategory = medSecCategory;
	}

	public String getMedModel() {
		return medModel;
	}

	public void setMedModel(String medModel) {
		this.medModel = medModel;
	}

	public String getMedeffect() {
		return medeffect;
	}

	public void setMedeffect(String medeffect) {
		this.medeffect = medeffect;
	}

	public String getMonitorSDate() {
		return monitorSDate;
	}

	public void setMonitorSDate(String monitorSDate) {
		this.monitorSDate = monitorSDate;
	}

	public String getMonitorEDate() {
		return monitorEDate;
	}

	public void setMonitorEDate(String monitorEDate) {
		this.monitorEDate = monitorEDate;
	}

	public Long getReportIssuenum() {
		return reportIssuenum;
	}

	public void setReportIssuenum(Long reportIssuenum) {
		this.reportIssuenum = reportIssuenum;
	}

	public Long getIntervalmonth() {
		return intervalmonth;
	}

	public void setIntervalmonth(Long intervalmonth) {
		this.intervalmonth = intervalmonth;
	}

	public String getMonitorremark() {
		return monitorremark;
	}

	public void setMonitorremark(String monitorremark) {
		this.monitorremark = monitorremark;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public Set getMed9002Dbs() {
		return med9002Dbs;
	}

	public void setMed9002Dbs(Set med9002Dbs) {
		this.med9002Dbs = med9002Dbs;
	}

	public Set getMed9003Dbs() {
		return med9003Dbs;
	}

	public void setMed9003Dbs(Set med9003Dbs) {
		this.med9003Dbs = med9003Dbs;
	}

	public String getBulletinDate() {
		return bulletinDate;
	}

	public void setBulletinDate(String bulletinDate) {
		this.bulletinDate = bulletinDate;
	}

	public String getAssessPerson() {
		return assessPerson;
	}

	public void setAssessPerson(String assessPerson) {
		this.assessPerson = assessPerson;
	}

	public String getScheduledDate() {
		return scheduledDate;
	}

	public void setScheduledDate(String scheduledDate) {
		this.scheduledDate = scheduledDate;
	}

	public String getDetermineDate() {
		return determineDate;
	}

	public void setDetermineDate(String determineDate) {
		this.determineDate = determineDate;
	}

	public String getClosingDate() {
		return closingDate;
	}

	public void setClosingDate(String closingDate) {
		this.closingDate = closingDate;
	}

	public String getTrans() {
		return trans;
	}

	public void setTrans(String trans) {
		this.trans = trans;
	}

	
	
}
