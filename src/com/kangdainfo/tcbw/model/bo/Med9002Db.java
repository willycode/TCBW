package com.kangdainfo.tcbw.model.bo;

import java.io.Serializable;
import java.util.Set;


public class Med9002Db  implements Serializable 
{
    /** identifier field */
    private Long id;
    private Long reportIssueno;
    private String reporttype;
    private String handstatus;
    private String prehanddate;
    private String handdate;
    private String monitorsdate;
    private String monitoredate;
    private String reportreceiveno;
    private String reportsummary;
    private Long inDefCaseNum;
    private Long inUnDefCaseNum;
    private Long abDefCaseNum;
    private Long abUnDefCaseNum;
    private Long scienceNum;
    private String reportremark;
    private String reportupdatedate;
    private String reportupdateman;
    private String assessdate;
    private String assesssummary;
    private String assessresult;
    private String assessresultdesc;
    private String assessremark;
	private String receiveSystem;
    private String receiveDate;
    private String receiveTime;
    private String creator;
    private String createDate;
    private String createTime;
    private String modifier;
    private String modifyDate;
    private String modifyTime;
    private Med9001Db med9001Db;
    private Set med9003Dbs;
    
    /** default constructor */
    public Med9002Db() {
    	
    }

	public String toString() 
	{
		StringBuilder builder = new StringBuilder();
		builder.append("Med9002Db [id=");
		builder.append(id);
		builder.append(", med9001Db=");
		builder.append(med9001Db);
		builder.append(", reportIssueno=");
		builder.append(reportIssueno);
		builder.append(", reporttype=");
	    builder.append(reporttype);		
	    builder.append(", handstatus=");
		builder.append(handstatus);
		builder.append(", prehanddate=");
		builder.append(prehanddate);
		builder.append(", handdate=");
		builder.append(handdate);
		builder.append(", monitorsdate=");
		builder.append(monitorsdate);
		builder.append(", monitoredate=");
		builder.append(monitoredate);
		builder.append(", reportreceiveno=");
		builder.append(reportreceiveno);
		builder.append(", reportsummary=");
		builder.append(reportsummary);
		builder.append(", inDefCaseNum=");
		builder.append(inDefCaseNum);
		builder.append(", inUnDefCaseNum=");
		builder.append(inUnDefCaseNum);
		builder.append(", abDefCaseNum=");
		builder.append(abDefCaseNum);
		builder.append(", abUnDefCaseNum=");
		builder.append(abUnDefCaseNum);
		builder.append(", scienceNum=");
		builder.append(scienceNum);
		builder.append(", reportremark=");
		builder.append(reportremark);
		builder.append(", reportupdatedate=");
		builder.append(reportupdatedate);
		builder.append(", reportupdateman=");
		builder.append(reportupdateman);
		builder.append(", assessdate=");
		builder.append(assessdate);
		builder.append(", assesssummary=");
		builder.append(assesssummary);
		builder.append(", assessresult=");
		builder.append(assessresult);
		builder.append(", assessresultdesc=");
		builder.append(assessresultdesc);
		builder.append(", assessremark=");
		builder.append(assessremark);		
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
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
	public Long getReportIssueno() {
		return reportIssueno;
	}

	public void setReportIssueno(Long reportIssueno) {
		this.reportIssueno = reportIssueno;
	}

	public String getReporttype() {
		return reporttype;
	}

	public void setReporttype(String reporttype) {
		this.reporttype = reporttype;
	}

	public String getHandstatus() {
		return handstatus;
	}

	public void setHandstatus(String handstatus) {
		this.handstatus = handstatus;
	}

	public String getPrehanddate() {
		return prehanddate;
	}

	public void setPrehanddate(String prehanddate) {
		this.prehanddate = prehanddate;
	}

	public String getHanddate() {
		return handdate;
	}

	public void setHanddate(String handdate) {
		this.handdate = handdate;
	}

	public String getMonitorsdate() {
		return monitorsdate;
	}

	public void setMonitorsdate(String monitorsdate) {
		this.monitorsdate = monitorsdate;
	}

	public String getMonitoredate() {
		return monitoredate;
	}

	public void setMonitoredate(String monitoredate) {
		this.monitoredate = monitoredate;
	}

	public String getReportreceiveno() {
		return reportreceiveno;
	}

	public void setReportreceiveno(String reportreceiveno) {
		this.reportreceiveno = reportreceiveno;
	}

	public String getReportsummary() {
		return reportsummary;
	}

	public void setReportsummary(String reportsummary) {
		this.reportsummary = reportsummary;
	}

	public Long getInDefCaseNum() {
		return inDefCaseNum;
	}

	public void setInDefCaseNum(Long inDefCaseNum) {
		this.inDefCaseNum = inDefCaseNum;
	}

	public Long getInUnDefCaseNum() {
		return inUnDefCaseNum;
	}

	public void setInUnDefCaseNum(Long inUnDefCaseNum) {
		this.inUnDefCaseNum = inUnDefCaseNum;
	}

	public Long getAbDefCaseNum() {
		return abDefCaseNum;
	}

	public void setAbDefCaseNum(Long abDefCaseNum) {
		this.abDefCaseNum = abDefCaseNum;
	}

	public Long getAbUnDefCaseNum() {
		return abUnDefCaseNum;
	}

	public void setAbUnDefCaseNum(Long abUnDefCaseNum) {
		this.abUnDefCaseNum = abUnDefCaseNum;
	}

	public Long getScienceNum() {
		return scienceNum;
	}

	public void setScienceNum(Long scienceNum) {
		this.scienceNum = scienceNum;
	}

	public String getReportremark() {
		return reportremark;
	}

	public void setReportremark(String reportremark) {
		this.reportremark = reportremark;
	}

	public String getReportupdatedate() {
		return reportupdatedate;
	}

	public void setReportupdatedate(String reportupdatedate) {
		this.reportupdatedate = reportupdatedate;
	}

	public String getReportupdateman() {
		return reportupdateman;
	}

	public void setReportupdateman(String reportupdateman) {
		this.reportupdateman = reportupdateman;
	}

	public String getAssessdate() {
		return assessdate;
	}

	public void setAssessdate(String assessdate) {
		this.assessdate = assessdate;
	}	

	public String getAssesssummary() {
		return assesssummary;
	}

	public void setAssesssummary(String assesssummary) {
		this.assesssummary = assesssummary;
	}

	public String getAssessresult() {
		return assessresult;
	}

	public void setAssessresult(String assessresult) {
		this.assessresult = assessresult;
	}

	public String getAssessresultdesc() {
		return assessresultdesc;
	}

	public void setAssessresultdesc(String assessresultdesc) {
		this.assessresultdesc = assessresultdesc;
	}

	public String getAssessremark() {
		return assessremark;
	}

	public void setAssessremark(String assessremark) {
		this.assessremark = assessremark;
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

	public Med9001Db getMed9001Db() {
		return med9001Db;
	}

	public void setMed9001Db(Med9001Db med9001Db) {
		this.med9001Db = med9001Db;
	}

	public Set getMed9003Dbs() {
		return med9003Dbs;
	}

	public void setMed9003Dbs(Set med9003Dbs) {
		this.med9003Dbs = med9003Dbs;
	}	
}
