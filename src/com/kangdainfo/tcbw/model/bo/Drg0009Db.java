package com.kangdainfo.tcbw.model.bo;

import java.io.Serializable;

public class Drg0009Db  implements Serializable 
{
	
	/** identifier field */
    private Long id;
    
    private String applNo;	//案件號碼
    private String analyDate;	//分析日期
    private String analyMan;	//分析日期
    private String medicineType;	//學名藥/原廠藥
    private String produceType;	//國產/輸入
    private String lotType;	//批號範圍
    private String defect;	//不良品缺陷
    private String defectOther;	//不良品缺陷(說明)
    private String survey;	//調查結果
    private String surveyOther;	//調查結果(說明)
    private String precaution;	//預防措施
    private String precautionOther;	//預防措施(說明)

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

	public String getAnalyDate() {
		return analyDate;
	}

	public void setAnalyDate(String analyDate) {
		this.analyDate = analyDate;
	}

	public String getAnalyMan() {
		return analyMan;
	}

	public void setAnalyMan(String analyMan) {
		this.analyMan = analyMan;
	}

	public String getMedicineType() {
		return medicineType;
	}

	public void setMedicineType(String medicineType) {
		this.medicineType = medicineType;
	}

	public String getProduceType() {
		return produceType;
	}

	public void setProduceType(String produceType) {
		this.produceType = produceType;
	}

	public String getLotType() {
		return lotType;
	}

	public void setLotType(String lotType) {
		this.lotType = lotType;
	}

	public String getDefect() {
		return defect;
	}

	public void setDefect(String defect) {
		this.defect = defect;
	}

	public String getDefectOther() {
		return defectOther;
	}

	public void setDefectOther(String defectOther) {
		this.defectOther = defectOther;
	}

	public String getSurvey() {
		return survey;
	}

	public void setSurvey(String survey) {
		this.survey = survey;
	}

	public String getSurveyOther() {
		return surveyOther;
	}

	public void setSurveyOther(String surveyOther) {
		this.surveyOther = surveyOther;
	}

	public String getPrecaution() {
		return precaution;
	}

	public void setPrecaution(String precaution) {
		this.precaution = precaution;
	}

	public String getPrecautionOther() {
		return precautionOther;
	}

	public void setPrecautionOther(String precautionOther) {
		this.precautionOther = precautionOther;
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
    public Drg0009Db() {
    }

	public String toString() 
	{
		StringBuilder builder = new StringBuilder();
		builder.append("Drg0009Db [id=");
		builder.append(id);
		builder.append(", applNo=");
		builder.append(applNo);
		builder.append(", analyDate=");
		builder.append(analyDate);
		builder.append(", analyMan=");
		builder.append(analyMan);
		builder.append(", medicineType=");
		builder.append(medicineType);
		builder.append(", produceType=");
		builder.append(produceType);
		builder.append(", lotType=");
		builder.append(lotType);
		builder.append(", defect=");
		builder.append(defect);
		builder.append(", defectOther=");
		builder.append(defectOther);
		builder.append(", survey=");
		builder.append(survey);
		builder.append(", surveyOther=");
		builder.append(surveyOther);
		builder.append(", precaution=");
		builder.append(precaution);
		builder.append(", precautionOther=");
		builder.append(precautionOther);
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
