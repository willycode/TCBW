package com.kangdainfo.tcbw.model.bo;

import java.io.Serializable;
import java.util.Set;

public class Cos0006Db implements Serializable 
{
	/** identifier field */
    private Long id;
    private String applNo;								 //案件號碼
    private String seqNo;								 //判定回數
    private String firstResult;							 //初步判定結果
    private String leaveCaseReason;
    private String determineMan;						 //判定人員
    private String summary;								 //調查摘要
    private String dealWith;							 //採取措施
    private String nonDefective;						 //初步判定非不良品原因
    private String marked;								 //標示問題
    private String unMarked;						  	 //無標示
    private String markedIncomplete;					 //標示不全
    private String markedFalse;							 //標示不實
    private String lawlessIng;							 //疑似含有不法及有效成分
    private String lawlessIngOther;						 //疑似含有不法及有效成分(說明)
    private String exteriorError;						 //外觀異常
    private String exteriorErrorOther;					 //外觀異常(說明)
    private String packageError; 						 //包裝瑕疵
    private String packageErrorOther;					 //包裝瑕疵(說明)
    private String expired;								 //過期
    private String expiredOther;						 //過期(說明)
    private String others;								 //其他
    private String othersDesc;							 //其他(說明)
    private String measure; 							 //採取措施
    private String disDate;
    private String recordOpinion;						 //紀錄者意見
    
    private String centralResultDesc;//初步判定說明與建議
    private String centralInvestigate;//通報中心調查摘要
    private String centralSuggest;//通報中心初步建議
    
    
    private String receiveSystem;						 //介接異動系統
    private String receiveDate;							 //介接異動日期
    private String receiveTime;	 						 //介接異動時間
	private String creator;	 							 //建檔者
    private String createDate;  						 //建檔日期
    private String createTime;  						 //建檔時間
    private String modifier;  							 //最後異動者
    private String modifyDate; 							 //最後異動日期
    private String modifyTime; 							 //最後異動時間    
    private Set cos0016Dbs;						   		 //與Cos0016Db關聯
    
	/** default constructor */
 //   public Cos0006Db(Set Cos0016Dbs) {             
 //       this.Cos0016Dbs = Cos0016Dbs;      
 //   }

    public Cos0006Db(){
    	
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

	public String getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
	}

	public String getFirstResult() {
		return firstResult;
	}

	public void setFirstResult(String firstResult) {
		this.firstResult = firstResult;
	}
	
	public String getLeaveCaseReason() {
		return leaveCaseReason;
	}

	public void setLeaveCaseReason(String leaveCaseReason) {
		this.leaveCaseReason = leaveCaseReason;
	}

	public String getDetermineMan() {
		return determineMan;
	}

	public void setDetermineMan(String determineMan) {
		this.determineMan = determineMan;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getDealWith() {
		return dealWith;
	}

	public void setDealWith(String dealWith) {
		this.dealWith = dealWith;
	}

	public String getNonDefective() {
		return nonDefective;
	}

	public void setNonDefective(String nonDefective) {
		this.nonDefective = nonDefective;
	}

	public String getMarked() {
		return marked;
	}

	public void setMarked(String marked) {
		this.marked = marked;
	}

	public String getUnMarked() {
		return unMarked;
	}

	public void setUnMarked(String unMarked) {
		this.unMarked = unMarked;
	}

	public String getMarkedIncomplete() {
		return markedIncomplete;
	}

	public void setMarkedIncomplete(String markedIncomplete) {
		this.markedIncomplete = markedIncomplete;
	}

	public String getMarkedFalse() {
		return markedFalse;
	}

	public void setMarkedFalse(String markedFalse) {
		this.markedFalse = markedFalse;
	}

	public String getLawlessIng() {
		return lawlessIng;
	}

	public void setLawlessIng(String lawlessIng) {
		this.lawlessIng = lawlessIng;
	}

	public String getLawlessIngOther() {
		return lawlessIngOther;
	}

	public void setLawlessIngOther(String lawlessIngOther) {
		this.lawlessIngOther = lawlessIngOther;
	}

	public String getExteriorError() {
		return exteriorError;
	}

	public void setExteriorError(String exteriorError) {
		this.exteriorError = exteriorError;
	}

	public String getExteriorErrorOther() {
		return exteriorErrorOther;
	}

	public void setExteriorErrorOther(String exteriorErrorOther) {
		this.exteriorErrorOther = exteriorErrorOther;
	}

	public String getPackageError() {
		return packageError;
	}

	public void setPackageError(String packageError) {
		this.packageError = packageError;
	}

	public String getPackageErrorOther() {
		return packageErrorOther;
	}

	public void setPackageErrorOther(String packageErrorOther) {
		this.packageErrorOther = packageErrorOther;
	}

	public String getExpired() {
		return expired;
	}

	public void setExpired(String expired) {
		this.expired = expired;
	}

	public String getExpiredOther() {
		return expiredOther;
	}

	public void setExpiredOther(String expiredOther) {
		this.expiredOther = expiredOther;
	}

	public String getOthers() {
		return others;
	}

	public void setOthers(String others) {
		this.others = others;
	}

	public String getOthersDesc() {
		return othersDesc;
	}

	public void setOthersDesc(String othersDesc) {
		this.othersDesc = othersDesc;
	}

	public String getMeasure() {
		return measure;
	}

	public void setMeasure(String measure) {
		this.measure = measure;
	}
	
	public String getDisDate() {
		return disDate;
	}

	public void setDisDate(String disDate) {
		this.disDate = disDate;
	}

	public String getRecordOpinion() {
		return recordOpinion;
	}

	public void setRecordOpinion(String recordOpinion) {
		this.recordOpinion = recordOpinion;
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
	
	public String getCentralInvestigate() {
		return centralInvestigate;
	}

	public void setCentralInvestigate(String centralInvestigate) {
		this.centralInvestigate = centralInvestigate;
	}

	public String getCentralSuggest() {
		return centralSuggest;
	}

	public void setCentralSuggest(String centralSuggest) {
		this.centralSuggest = centralSuggest;
	}
	
	

	public String getCentralResultDesc() {
		return centralResultDesc;
	}

	public void setCentralResultDesc(String centralResultDesc) {
		this.centralResultDesc = centralResultDesc;
	}

	public Set getCos0016Dbs() {	
		return cos0016Dbs;
	}
	public void setCos0016Dbs(Set cos0016Dbs) {
		this.cos0016Dbs = cos0016Dbs;
	}		
	
	public String toString() 
	{
		StringBuilder builder = new StringBuilder();
		builder.append("Cos0006Db [id=");
		builder.append(id);
		builder.append(", applNo=");
		builder.append(applNo);
		builder.append(", seqNo=");
		builder.append(seqNo);
		builder.append(", firstResult=");
		builder.append(firstResult);
		builder.append(", leaveCaseReason=");
		builder.append(leaveCaseReason);
		builder.append(", determineMan=");
		builder.append(determineMan);
		builder.append(", summary=");
		builder.append(summary);
		builder.append(", dealWith=");
		builder.append(dealWith);
		builder.append(", nonDefective=");
		builder.append(nonDefective);
		builder.append(", marked=");
		builder.append(marked);
		builder.append(", unMarked=");
		builder.append(unMarked);
		builder.append(", markedIncomplete=");
		builder.append(markedIncomplete);
		builder.append(", markedFalse=");
		builder.append(markedFalse);
		builder.append(", lawlessIng=");
		builder.append(lawlessIng);
		builder.append(", lawlessIngOther=");
		builder.append(lawlessIngOther);
		builder.append(", exteriorError=");
		builder.append(exteriorError);
		builder.append(", exteriorErrorOther=");
		builder.append(exteriorErrorOther);
		builder.append(", packageError=");
		builder.append(packageError);
		builder.append(", packageErrorOther=");
		builder.append(packageErrorOther);
		builder.append(", expired=");
		builder.append(expired);
		builder.append(", expiredOther=");
		builder.append(expiredOther);
		builder.append(", others=");
		builder.append(others);
		builder.append(", othersDesc=");
		builder.append(othersDesc);
		builder.append(", measure=");
		builder.append(measure);
		builder.append(", disDate=");
		builder.append(disDate);
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
