package com.kangdainfo.tcbw.model.bo;

import java.io.Serializable;


public class Med0007Db  implements Serializable 
{
    /** identifier field */
    private Long id;
    
    private Med0001Db med0001Db;
   
    private String bulletinQuality;//通報品質
    private String evaluationDate;//評估日期
    private String instructionSheet;//仿單
    private String documentRecords;//文獻紀錄
    private String documentNum;//文獻紀錄-件數
    private String policeQuery;//警訊查詢
    private String policeFdaNum;//警訊查詢-FDA件數
    private String policeMhraNum;//警訊查詢-MHRA件數
    private String policeEcriNum;//警訊查詢-ECRI件數
    private String medicalIssues;//醫材問題代碼
    private String patientIssues;//病人問題代碼
    
    private String assessAdverseReactions;//評估不良反應
    private String adverseReactionsResult;//評估不良反應結果
    private String adverseTotal;//評估不良反應總分
    
    private String eventSeverity;//事件嚴重程度
    private String commentOpinion;//初評意見
    private String commentExplanation1;//初評意見說明1
    private String commentExplanation2;//初評意見說明2
    private String otherOpinion;//其他意見
    
    private String ncarOptions;//Ncar通報篩選
    private String ncarResult;//Ncar通報篩選結果
    private String ncarTotal;//Ncar總分
    
    private String resultsNotification;//結果通知
    private String eventDetails;//再次取得詳細說明
    
    
    
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
	

	
	public String getEvaluationDate() {
		return evaluationDate;
	}

	public void setEvaluationDate(String evaluationDate) {
		this.evaluationDate = evaluationDate;
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

	public String getInstructionSheet() {
		return instructionSheet;
	}

	public void setInstructionSheet(String instructionSheet) {
		this.instructionSheet = instructionSheet;
	}

	public String getDocumentRecords() {
		return documentRecords;
	}

	public void setDocumentRecords(String documentRecords) {
		this.documentRecords = documentRecords;
	}

	public String getDocumentNum() {
		return documentNum;
	}

	public void setDocumentNum(String documentNum) {
		this.documentNum = documentNum;
	}

	public String getPoliceQuery() {
		return policeQuery;
	}

	public void setPoliceQuery(String policeQuery) {
		this.policeQuery = policeQuery;
	}

	public String getPoliceFdaNum() {
		return policeFdaNum;
	}

	public void setPoliceFdaNum(String policeFdaNum) {
		this.policeFdaNum = policeFdaNum;
	}

	public String getPoliceMhraNum() {
		return policeMhraNum;
	}

	public void setPoliceMhraNum(String policeMhraNum) {
		this.policeMhraNum = policeMhraNum;
	}

	public String getPoliceEcriNum() {
		return policeEcriNum;
	}

	public void setPoliceEcriNum(String policeEcriNum) {
		this.policeEcriNum = policeEcriNum;
	}

	public String getMedicalIssues() {
		return medicalIssues;
	}

	public void setMedicalIssues(String medicalIssues) {
		this.medicalIssues = medicalIssues;
	}

	public String getPatientIssues() {
		return patientIssues;
	}

	public void setPatientIssues(String patientIssues) {
		this.patientIssues = patientIssues;
	}

	public String getAssessAdverseReactions() {
		return assessAdverseReactions;
	}

	public void setAssessAdverseReactions(String assessAdverseReactions) {
		this.assessAdverseReactions = assessAdverseReactions;
	}

	public String getAdverseReactionsResult() {
		return adverseReactionsResult;
	}

	public void setAdverseReactionsResult(String adverseReactionsResult) {
		this.adverseReactionsResult = adverseReactionsResult;
	}

	public String getEventSeverity() {
		return eventSeverity;
	}

	public void setEventSeverity(String eventSeverity) {
		this.eventSeverity = eventSeverity;
	}

	public String getCommentOpinion() {
		return commentOpinion;
	}

	public void setCommentOpinion(String commentOpinion) {
		this.commentOpinion = commentOpinion;
	}

	public String getCommentExplanation1() {
		return commentExplanation1;
	}

	public void setCommentExplanation1(String commentExplanation1) {
		this.commentExplanation1 = commentExplanation1;
	}

	public String getCommentExplanation2() {
		return commentExplanation2;
	}

	public void setCommentExplanation2(String commentExplanation2) {
		this.commentExplanation2 = commentExplanation2;
	}

	public String getOtherOpinion() {
		return otherOpinion;
	}

	public void setOtherOpinion(String otherOpinion) {
		this.otherOpinion = otherOpinion;
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

	public String getResultsNotification() {
		return resultsNotification;
	}

	public void setResultsNotification(String resultsNotification) {
		this.resultsNotification = resultsNotification;
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
    
	
	
    public String getAdverseTotal() {
		return adverseTotal;
	}

	public void setAdverseTotal(String adverseTotal) {
		this.adverseTotal = adverseTotal;
	}

	public String getNcarTotal() {
		return ncarTotal;
	}

	public void setNcarTotal(String ncarTotal) {
		this.ncarTotal = ncarTotal;
	}

	/** default constructor */
    public Med0007Db() {
    }

	public String toString() 
	{
		StringBuilder builder = new StringBuilder();
		builder.append("Med0007Db [id=");
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
