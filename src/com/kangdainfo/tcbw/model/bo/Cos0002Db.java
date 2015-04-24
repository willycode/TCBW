package com.kangdainfo.tcbw.model.bo;

import java.io.Serializable;
import java.util.Set;

public class Cos0002Db  implements Serializable 
{
	/** identifier field */
    private Long id;
    private Cos0001Db cos0001Db;						// 主檔
    private String adverseCondition;					// 不良反應狀況
    private String nonSeriousOther;						// 非嚴重不良反應症狀
    private String nonSeriousAR;						// 非嚴重不良反應症狀-代碼
    private String nonSeriousAROther;					// 非嚴重不良反應症狀-代碼(其他)
    private String nonSeriousDis;	//非嚴重不良反應描述
    private String useDateS;	//使用期間起
    private String useDateE;	//使用期間訖
    private String useRate;	//使用頻率
    private String useMethod;	//使用方法
    private String isMitigateYn;	//停用後不良反應是否減輕
    private String isRecurYn;	//再使用是否出現同樣反應
    private String diagnosisProof;	 //醫師診斷證明
    private String diagnosisReport;	 //就醫紀錄(病歷報告)
    private String diagnosisOther;	 //其他相關資料
    private String receiveSystem;	//介接異動系統
    private String receiveDate;	//介接異動日期
    private String receiveTime;	 //介接異動時間
    private String creator;	 //建檔者
    private String createDate;  //建檔日期
    private String createTime;  //建檔時間
    private String modifier;  //最後異動者
    private String modifyDate;  // 最後異動日期
    private String modifyTime;  //最後異動時間
    
    private Set cos0004Dbs;
    private Set cos0005Dbs;

	/** default constructor */
    public Cos0002Db() {
    }
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cos0001Db getCos0001Db() {
		return cos0001Db;
	}

	public void setCos0001Db(Cos0001Db cos0001Db) {
		this.cos0001Db = cos0001Db;
	}

	public String getAdverseCondition() {
		return adverseCondition;
	}

	public void setAdverseCondition(String adverseCondition) {
		this.adverseCondition = adverseCondition;
	}

	public String getNonSeriousOther() {
		return nonSeriousOther;
	}

	public void setNonSeriousOther(String nonSeriousOther) {
		this.nonSeriousOther = nonSeriousOther;
	}
	
	public String getNonSeriousAR() {
		return nonSeriousAR;
	}

	public void setNonSeriousAR(String nonSeriousAR) {
		this.nonSeriousAR = nonSeriousAR;
	}

	public String getNonSeriousAROther() {
		return nonSeriousAROther;
	}

	public void setNonSeriousAROther(String nonSeriousAROther) {
		this.nonSeriousAROther = nonSeriousAROther;
	}

	public String getNonSeriousDis() {
		return nonSeriousDis;
	}

	public void setNonSeriousDis(String nonSeriousDis) {
		this.nonSeriousDis = nonSeriousDis;
	}

	public String getUseDateS() {
		return useDateS;
	}

	public void setUseDateS(String useDateS) {
		this.useDateS = useDateS;
	}

	public String getUseDateE() {
		return useDateE;
	}

	public void setUseDateE(String useDateE) {
		this.useDateE = useDateE;
	}

	public String getUseRate() {
		return useRate;
	}

	public void setUseRate(String useRate) {
		this.useRate = useRate;
	}

	public String getUseMethod() {
		return useMethod;
	}

	public void setUseMethod(String useMethod) {
		this.useMethod = useMethod;
	}

	public String getIsMitigateYn() {
		return isMitigateYn;
	}

	public void setIsMitigateYn(String isMitigateYn) {
		this.isMitigateYn = isMitigateYn;
	}

	public String getIsRecurYn() {
		return isRecurYn;
	}

	public void setIsRecurYn(String isRecurYn) {
		this.isRecurYn = isRecurYn;
	}

	public String getDiagnosisProof() {
		return diagnosisProof;
	}

	public void setDiagnosisProof(String diagnosisProof) {
		this.diagnosisProof = diagnosisProof;
	}

	public String getDiagnosisReport() {
		return diagnosisReport;
	}

	public void setDiagnosisReport(String diagnosisReport) {
		this.diagnosisReport = diagnosisReport;
	}

	public String getDiagnosisOther() {
		return diagnosisOther;
	}

	public void setDiagnosisOther(String diagnosisOther) {
		this.diagnosisOther = diagnosisOther;
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

	public Set getCos0004Dbs() {
		return cos0004Dbs;
	}

	public void setCos0004Dbs(Set cos0004Dbs) {
		this.cos0004Dbs = cos0004Dbs;
	}
	
	public Set getCos0005Dbs() {
		return cos0005Dbs;
	}

	public void setCos0005Dbs(Set cos0005Dbs) {
		this.cos0005Dbs = cos0005Dbs;
	}

	public String toString() 
	{
		StringBuilder builder = new StringBuilder();
		builder.append("Cos0002Db [id=");
		builder.append(id);
		builder.append(", cos0001Db=");
		builder.append(cos0001Db);
		builder.append(", adverseCondition=");
		builder.append(adverseCondition);
		builder.append(", nonSeriousOther=");
		builder.append(nonSeriousOther);
		builder.append(", nonSeriousAR=");
		builder.append(nonSeriousAR);
		builder.append(", nonSeriousAROther=");
		builder.append(nonSeriousAROther);
		builder.append(", nonSeriousDis=");
		builder.append(nonSeriousDis);
		builder.append(", useDateS=");
		builder.append(useDateS);
		builder.append(", useDateE=");
		builder.append(useDateE);
		builder.append(", useRate=");
		builder.append(useRate);
		builder.append(", useMethod=");
		builder.append(useMethod);
		builder.append(", isMitigateYn=");
		builder.append(isMitigateYn);
		builder.append(", isRecurYn=");
		builder.append(isRecurYn);
		builder.append(", diagnosisProof=");
		builder.append(diagnosisProof);
		builder.append(", diagnosisReport=");
		builder.append(diagnosisReport);
		builder.append(", diagnosisOther=");
		builder.append(diagnosisOther);
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
