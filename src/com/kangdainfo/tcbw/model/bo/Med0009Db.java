package com.kangdainfo.tcbw.model.bo;

import java.io.Serializable;


public class Med0009Db  implements Serializable 
{
    /** identifier field */
    private Long id;
    
    private Med0001Db med0001Db;
   
    private String bulletinQuality;//通報品質
    private String evaluationDate;//評估日期
    private String eventClass;//事件等級
    private String eventDetails;//需再取得事件詳細說明
    
    private String medicalIssues;//醫材問題代碼
    private String assessProposal;//評估建議
    private String ncarOptions;//Ncar通報篩選
    private String ncarResult;//Ncar通報篩選結果
    private String ncarTotal;//Ncar總分
    
	/** persistent field */
	private String receiveSystem;//介接異動系統
    private String receiveDate;//介接異動日期
    private String receiveTime;//介接異動時間
    private String creator;//建檔者
    private String createDate;//建檔日期
    private String createTime;//建檔時間
    private String modifier;//最後異動者
    private String modifyDate;// 最後異動日期
    private String modifyTime;//最後異動時間

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
	public Med0001Db getMed0001Db() {
		return med0001Db;
	}

	public void setMed0001Db(Med0001Db med0001Db) {
		this.med0001Db = med0001Db;
	}

	

	public String getNcarTotal() {
		return ncarTotal;
	}

	public void setNcarTotal(String ncarTotal) {
		this.ncarTotal = ncarTotal;
	}

	public String getEvaluationDate() {
		return evaluationDate;
	}

	public void setEvaluationDate(String evaluationDate) {
		this.evaluationDate = evaluationDate;
	}

	public String getEventClass() {
		return eventClass;
	}

	public void setEventClass(String eventClass) {
		this.eventClass = eventClass;
	}

	public String getEventDetails() {
		return eventDetails;
	}

	public void setEventDetails(String eventDetails) {
		this.eventDetails = eventDetails;
	}

	public String getBulletinQuality() {
		return bulletinQuality;
	}

	public void setBulletinQuality(String bulletinQuality) {
		this.bulletinQuality = bulletinQuality;
	}

	
	public void setMedicalIssues(String medicalIssues) {
		this.medicalIssues = medicalIssues;
	}

	public String getAssessProposal() {
		return assessProposal;
	}

	public void setAssessProposal(String assessProposal) {
		this.assessProposal = assessProposal;
	}

	public String getMedicalIssues() {
		return medicalIssues;
	}

	public String getNcarOptions() {
		return ncarOptions;
	}

	public void setNcarOptions(String ncarOptions) {
		this.ncarOptions = ncarOptions;
	}

	public String getNcarResult() {
		return ncarResult;
	}

	public void setNcarResult(String ncarResult) {
		this.ncarResult = ncarResult;
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
    
    /** default constructor */
    public Med0009Db() {
    }

	public String toString() 
	{
		StringBuilder builder = new StringBuilder();
		builder.append("Med0009Db [id=");
		builder.append(id);
		builder.append(", med0001Db=");
		builder.append(med0001Db);
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
