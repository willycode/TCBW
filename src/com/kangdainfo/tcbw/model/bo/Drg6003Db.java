package com.kangdainfo.tcbw.model.bo;

import java.io.Serializable;


public class Drg6003Db  implements Serializable 
{
    /** identifier field */
    private Long id;
    
    private Drg6001Db drg6001Db;   
    private String medType;		//使用藥品類別
    private String permitKey;	//輸入事件前-許可證字
    private String permitNo;	//輸入事件前-許可證號
    private String scientificName;	//輸入事件前-學名
    private String productName;	//輸入事件前-商品名
    private String applicationName;	//輸入事件前-申請商
    private String manufactorID;	//製造商統編
    private String manufactorName;	//輸入事件前-製造廠
    private String manufactorNo;	//輸入事件前-批號
    private String content;	//輸入事件前-含量
    private String medModel;	//輸入事件前-劑型
    private String medModelOther;	//劑型其他
    private String medPath;	//輸入事件前-給藥途徑
    private String medPathOther;	//給藥途徑其他
    private String dosage;	//輸入事件前-劑量
    private String frequency;	//輸入事件前-頻率
    private String frequencyOther;	//頻率其他
    private String startDare;	//輸入事件前-起始日期
    private String endDate;	//輸入事件前-結束日期
    private String indication;	//輸入事件前-適應症
    
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
    
    private String trans;	          //是否為轉檔資料
    private String old_CaseNoE;	      //案件號碼(Excel)
    private String old_CaseNoD;	      //案件號碼(db)

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
	public Drg6001Db getDrg6001Db() {
		return drg6001Db;
	}

	public void setDrg6001Db(Drg6001Db drg6001Db) {
		this.drg6001Db = drg6001Db;
	}

	public String getMedType() {
		return medType;
	}

	public void setMedType(String medType) {
		this.medType = medType;
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

	public String getScientificName() {
		return scientificName;
	}

	public void setScientificName(String scientificName) {
		this.scientificName = scientificName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	public String getManufactorID() {
		return manufactorID;
	}

	public void setManufactorID(String manufactorID) {
		this.manufactorID = manufactorID;
	}

	public String getManufactorName() {
		return manufactorName;
	}

	public void setManufactorName(String manufactorName) {
		this.manufactorName = manufactorName;
	}

	public String getManufactorNo() {
		return manufactorNo;
	}

	public void setManufactorNo(String manufactorNo) {
		this.manufactorNo = manufactorNo;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getMedModel() {
		return medModel;
	}

	public void setMedModel(String medModel) {
		this.medModel = medModel;
	}

	public String getMedModelOther() {
		return medModelOther;
	}

	public void setMedModelOther(String medModelOther) {
		this.medModelOther = medModelOther;
	}

	public String getMedPath() {
		return medPath;
	}

	public void setMedPath(String medPath) {
		this.medPath = medPath;
	}

	public String getMedPathOther() {
		return medPathOther;
	}

	public void setMedPathOther(String medPathOther) {
		this.medPathOther = medPathOther;
	}

	public String getDosage() {
		return dosage;
	}

	public void setDosage(String dosage) {
		this.dosage = dosage;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public String getFrequencyOther() {
		return frequencyOther;
	}

	public void setFrequencyOther(String frequencyOther) {
		this.frequencyOther = frequencyOther;
	}

	public String getStartDare() {
		return startDare;
	}

	public void setStartDare(String startDare) {
		this.startDare = startDare;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getIndication() {
		return indication;
	}

	public void setIndication(String indication) {
		this.indication = indication;
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
	
	public String getTrans() {
		return trans;
	}
	public void setTrans(String trans) {
		this.trans = trans;
	}
	public String getOld_CaseNoE() {
		return old_CaseNoE;
	}
	public void setOld_CaseNoE(String old_CaseNoE) {
		this.old_CaseNoE = old_CaseNoE;
	}
	public String getOld_CaseNoD() {
		return old_CaseNoD;
	}
	public void setOld_CaseNoD(String old_CaseNoD) {
		this.old_CaseNoD = old_CaseNoD;
	}
    
    /** default constructor */
    public Drg6003Db() {
    }

	public String toString() 
	{
		StringBuilder builder = new StringBuilder();
		builder.append("Drg6003Db [id=");
		builder.append(id);
		builder.append(", drg6001Db=");
		builder.append(drg6001Db);
		builder.append(", permitKey=");
		builder.append(permitKey);
		builder.append(", permitNo=");
		builder.append(permitNo);
		builder.append(", scientificName=");
		builder.append(scientificName);
		builder.append(", productName=");
		builder.append(productName);
		builder.append(", applicationName=");
		builder.append(applicationName);
		builder.append(", manufactorName=");
		builder.append(manufactorName);
		builder.append(", manufactorNo=");
		builder.append(manufactorNo);
		builder.append(", content=");
		builder.append(content);
		builder.append(", medModel=");
		builder.append(medModel);
		builder.append(", medPath=");
		builder.append(medPath);
		builder.append(", dosage=");
		builder.append(dosage);
		builder.append(", frequency=");
		builder.append(frequency);
		builder.append(", startDare=");
		builder.append(startDare);
		builder.append(", endDate=");
		builder.append(endDate);
		builder.append(", indication=");
		builder.append(indication);	
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
		builder.append(", trans=");
		builder.append(trans);
		builder.append(", old_CaseNoE=");
		builder.append(old_CaseNoE);
		builder.append(", old_CaseNoD=");
		builder.append(old_CaseNoD);
		builder.append("]");
		return builder.toString();
	}    
    

	
}
