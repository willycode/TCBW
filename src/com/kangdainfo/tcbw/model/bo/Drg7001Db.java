package com.kangdainfo.tcbw.model.bo;

import java.io.Serializable;
import java.util.Set;

public class Drg7001Db  implements Serializable 
{
	/** identifier field */
    private Long id;    
    private Long msgNum;
    private Long recycleNum;
    private String applNo;
    private String dataRevDate;
    private String publDept;
    private String istransfer;
    private String transferdept;
    private String datasourWebSite;
    private String situation;
    private String publDate;    
    private String chProduct;
    private String scientificName;
    private String warningmedModel;
    private String druggist;
    private String manufactorName;
    private String lotNo;
    private String eventDesc;
    private String qualitywarningtype;
    private String recycleType;
    private String subject;
    private String contextsummary;
    private String indication;
    private String iswarning;
    private String warninDesc;
    private String warningremark;
    private String status;
    private String estimateDate;
    private String ispublnews;
    private String ispublconsumer;
    private String publconsumerDate;
    private String lamp;
    private String aftereffect;
    private String aftereffectOther;
    private String estimateremark;
    private String receiveSystem;	//介接異動系統
    private String receiveDate;	//介接異動日期
    private String receiveTime;	 //介接異動時間
    private String creator;	 //建檔者
    private String createDate;  //建檔日期
    private String createTime;  //建檔時間
    private String modifier;  //最後異動者
    private String modifyDate;  // 最後異動日期
    private String modifyTime;  //最後異動時間
    private String isTransferData;	   //是否為轉檔資料
    private Set drg7002Dbs;
    private Set drg7003Dbs;
	/** default constructor */
    public Drg7001Db() {             

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

	public Long getMsgNum() {
		return msgNum;
	}

	public void setMsgNum(Long msgNum) {
		this.msgNum = msgNum;
	}

	public String getDataRevDate() {
		return dataRevDate;
	}

	public void setDataRevDate(String dataRevDate) {
		this.dataRevDate = dataRevDate;
	}

	public String getPublDept() {
		return publDept;
	}

	public void setPublDept(String publDept) {
		this.publDept = publDept;
	}

	public String getIstransfer() {
		return istransfer;
	}

	public void setIstransfer(String istransfer) {
		this.istransfer = istransfer;
	}

	public String getTransferdept() {
		return transferdept;
	}

	public void setTransferdept(String transferdept) {
		this.transferdept = transferdept;
	}

	public String getDatasourWebSite() {
		return datasourWebSite;
	}

	public void setDatasourWebSite(String datasourWebSite) {
		this.datasourWebSite = datasourWebSite;
	}

	public String getSituation() {
		return situation;
	}

	public void setSituation(String situation) {
		this.situation = situation;
	}

	public String getPublDate() {
		return publDate;
	}

	public void setPublDate(String publDate) {
		this.publDate = publDate;
	}

	public Long getRecycleNum() {
		return recycleNum;
	}

	public void setRecycleNum(Long recycleNum) {
		this.recycleNum = recycleNum;
	}

	public String getChProduct() {
		return chProduct;
	}

	public void setChProduct(String chProduct) {
		this.chProduct = chProduct;
	}

	public String getScientificName() {
		return scientificName;
	}

	public void setScientificName(String scientificName) {
		this.scientificName = scientificName;
	}

	public String getWarningmedModel() {
		return warningmedModel;
	}

	public void setWarningmedModel(String warningmedModel) {
		this.warningmedModel = warningmedModel;
	}

	public String getDruggist() {
		return druggist;
	}

	public void setDruggist(String druggist) {
		this.druggist = druggist;
	}

	public String getManufactorName() {
		return manufactorName;
	}

	public void setManufactorName(String manufactorName) {
		this.manufactorName = manufactorName;
	}

	public String getLotNo() {
		return lotNo;
	}

	public void setLotNo(String lotNo) {
		this.lotNo = lotNo;
	}

	public String getEventDesc() {
		return eventDesc;
	}

	public void setEventDesc(String eventDesc) {
		this.eventDesc = eventDesc;
	}

	public String getQualitywarningtype() {
		return qualitywarningtype;
	}

	public void setQualitywarningtype(String qualitywarningtype) {
		this.qualitywarningtype = qualitywarningtype;
	}

	public String getRecycleType() {
		return recycleType;
	}

	public void setRecycleType(String recycleType) {
		this.recycleType = recycleType;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContextsummary() {
		return contextsummary;
	}

	public void setContextsummary(String contextsummary) {
		this.contextsummary = contextsummary;
	}

	public String getIndication() {
		return indication;
	}

	public void setIndication(String indication) {
		this.indication = indication;
	}

	public String getIswarning() {
		return iswarning;
	}

	public void setIswarning(String iswarning) {
		this.iswarning = iswarning;
	}

	public String getWarninDesc() {
		return warninDesc;
	}

	public void setWarninDesc(String warninDesc) {
		this.warninDesc = warninDesc;
	}

	public String getWarningremark() {
		return warningremark;
	}

	public void setWarningremark(String warningremark) {
		this.warningremark = warningremark;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getEstimateDate() {
		return estimateDate;
	}

	public void setEstimateDate(String estimateDate) {
		this.estimateDate = estimateDate;
	}

	public String getIspublnews() {
		return ispublnews;
	}

	public void setIspublnews(String ispublnews) {
		this.ispublnews = ispublnews;
	}

	public String getIspublconsumer() {
		return ispublconsumer;
	}

	public void setIspublconsumer(String ispublconsumer) {
		this.ispublconsumer = ispublconsumer;
	}

	public String getPublconsumerDate() {
		return publconsumerDate;
	}

	public void setPublconsumerDate(String publconsumerDate) {
		this.publconsumerDate = publconsumerDate;
	}

	public String getLamp() {
		return lamp;
	}

	public void setLamp(String lamp) {
		this.lamp = lamp;
	}

	public String getAftereffect() {
		return aftereffect;
	}

	public void setAftereffect(String aftereffect) {
		this.aftereffect = aftereffect;
	}

	public String getAftereffectOther() {
		return aftereffectOther;
	}

	public void setAftereffectOther(String aftereffectOther) {
		this.aftereffectOther = aftereffectOther;
	}

	public String getEstimateremark() {
		return estimateremark;
	}

	public void setEstimateremark(String estimateremark) {
		this.estimateremark = estimateremark;
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
	
	public String getIsTransferData() {
		return isTransferData;
	}
	
	public void setIsTransferData(String isTransferData) {
		this.isTransferData = isTransferData;
	}

	public Set getDrg7002Dbs() {
		return drg7002Dbs;
	}

	public void setDrg7002Dbs(Set drg7002Dbs) {
		this.drg7002Dbs = drg7002Dbs;
	}

	public Set getDrg7003Dbs() {
		return drg7003Dbs;
	}

	public void setDrg7003Dbs(Set drg7003Dbs) {
		this.drg7003Dbs = drg7003Dbs;
	}

	public String toString() 
	{
		StringBuilder builder = new StringBuilder();
		builder.append("Drg7001Db [id=");
		builder.append(id);			
		builder.append(", applNo=");
		builder.append(applNo);
		builder.append(", msgNum=");
		builder.append(msgNum);
		builder.append(", dataRevDate=");
		builder.append(dataRevDate);
		builder.append(", publDept=");
		builder.append(publDept);
		builder.append(", istransfer=");
		builder.append(istransfer);
		builder.append(", transferdept=");
		builder.append(transferdept);
		builder.append(", datasourWebSite=");
		builder.append(datasourWebSite);
		builder.append(", situation=");
		builder.append(situation);
		builder.append(", publDate=");
		builder.append(publDate);
		builder.append(", recycleNum=");
		builder.append(recycleNum);
		builder.append(", chProduct=");
		builder.append(chProduct);
		builder.append(", scientificName=");
		builder.append(scientificName);
		builder.append(", warningmedModel=");
		builder.append(warningmedModel);
		builder.append(", druggist=");
		builder.append(druggist);
		builder.append(", manufactorName=");
		builder.append(manufactorName);
		builder.append(", lotNo=");
		builder.append(lotNo);
		builder.append(", eventDesc=");
		builder.append(eventDesc);
		builder.append(", qualitywarningtype=");
		builder.append(qualitywarningtype);
		builder.append(", recycleType=");
		builder.append(recycleType);
		builder.append(", subject=");
		builder.append(subject);
		builder.append(", contextsummary=");
		builder.append(contextsummary);
		builder.append(", indication=");
		builder.append(indication);
		builder.append(", iswarning=");
		builder.append(iswarning);
		builder.append(", warninDesc=");
		builder.append(warninDesc);	
		builder.append(", warningremark=");
		builder.append(warningremark);
		builder.append(", status=");
		builder.append(status);
		builder.append(", estimateDate=");
		builder.append(estimateDate);
		builder.append(", ispublnews=");
		builder.append(ispublnews);
		builder.append(", ispublconsumer=");
		builder.append(ispublconsumer);
		builder.append(", publconsumerDate=");
		builder.append(publconsumerDate);
		builder.append(", lamp=");
		builder.append(lamp);
		builder.append(", aftereffect=");
		builder.append(aftereffect);
		builder.append(", estimateremark=");
		builder.append(estimateremark);
		builder.append(", isTransferData=");
		builder.append(isTransferData);
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
