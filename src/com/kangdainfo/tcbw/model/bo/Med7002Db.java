package com.kangdainfo.tcbw.model.bo;

import java.io.Serializable;


public class Med7002Db  implements Serializable 
{    
    private Long id;    
    private String permitKey;
    private String permitNo;
    private String chProduct;
    private String enProduct;
    private String medapprovedate;
    private String medEffectiveDate;
    private String applicationId;
    private String applicationName;
    private String manufactorName;
    private String manufactorCountry;
    private String medclass;
    private String medMainCategory;
    private String medSecCategory;
    private String replydate;
    private String iswarning;
    private String iswarningOther;
    private String iseffectinternal;
    private String replysummary;
    private String medModel;
    private String lotNo;
    private String effectnum;
    private String checkcontactman;
    private String checkcontacttel;
    private String checkcontactemail;	
    
    private String notificationDate;//通知日期
    private String adverseRecord;//不良反應通報紀錄
    private String defRecord;//不良品通報紀錄


    private String applyObject;//施行對象
    private String proposedAction;//建議行動
    private String excerptDraft;//摘譯初稿
    
    private String trans;//是否透過轉檔轉入
	private String receiveSystem;//介接異動系統
    private String receiveDate;//介接異動日期
    private String receiveTime;//介接異動時間
    private String creator;//建檔者
    private String createDate;//建檔日期
    private String createTime;//建檔時間
    private String modifier;//最後異動者
    private String modifyDate;// 最後異動日期
    private String modifyTime;//最後異動時間
    private Med7001Db med7001Db;
    private Med7004Db med7004Db;    
    
    /** default constructor */
    public Med7002Db() {
    }

	public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
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

	public String getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
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

	public String getReplydate() {
		return replydate;
	}

	public void setReplydate(String replydate) {
		this.replydate = replydate;
	}

	public String getIswarning() {
		return iswarning;
	}

	public void setIswarning(String iswarning) {
		this.iswarning = iswarning;
	}

	public String getIseffectinternal() {
		return iseffectinternal;
	}

	public void setIseffectinternal(String iseffectinternal) {
		this.iseffectinternal = iseffectinternal;
	}

	public String getReplysummary() {
		return replysummary;
	}

	public void setReplysummary(String replysummary) {
		this.replysummary = replysummary;
	}

	public String getMedModel() {
		return medModel;
	}

	public void setMedModel(String medModel) {
		this.medModel = medModel;
	}

	public String getLotNo() {
		return lotNo;
	}

	public void setLotNo(String lotNo) {
		this.lotNo = lotNo;
	}

	public String getEffectnum() {
		return effectnum;
	}

	public void setEffectnum(String effectnum) {
		this.effectnum = effectnum;
	}

	public String getCheckcontactman() {
		return checkcontactman;
	}

	public void setCheckcontactman(String checkcontactman) {
		this.checkcontactman = checkcontactman;
	}

	public String getCheckcontacttel() {
		return checkcontacttel;
	}

	public void setCheckcontacttel(String checkcontacttel) {
		this.checkcontacttel = checkcontacttel;
	}

	public String getCheckcontactemail() {
		return checkcontactemail;
	}

	public void setCheckcontactemail(String checkcontactemail) {
		this.checkcontactemail = checkcontactemail;
	}

	public Med7001Db getMed7001Db() {
		return med7001Db;
	}

	public void setMed7001Db(Med7001Db med7001Db) {
		this.med7001Db = med7001Db;
	}

	public Med7004Db getMed7004Db() {
		return med7004Db;
	}

	public void setMed7004Db(Med7004Db med7004Db) {
		this.med7004Db = med7004Db;
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

	public String getNotificationDate() {
		return notificationDate;
	}

	public void setNotificationDate(String notificationDate) {
		this.notificationDate = notificationDate;
	}

	public String getAdverseRecord() {
		return adverseRecord;
	}

	public void setAdverseRecord(String adverseRecord) {
		this.adverseRecord = adverseRecord;
	}

	public String getDefRecord() {
		return defRecord;
	}

	public void setDefRecord(String defRecord) {
		this.defRecord = defRecord;
	}

	public String getIswarningOther() {
		return iswarningOther;
	}

	public void setIswarningOther(String iswarningOther) {
		this.iswarningOther = iswarningOther;
	}

	public String getApplyObject() {
		return applyObject;
	}

	public void setApplyObject(String applyObject) {
		this.applyObject = applyObject;
	}

	public String getProposedAction() {
		return proposedAction;
	}

	public void setProposedAction(String proposedAction) {
		this.proposedAction = proposedAction;
	}

	public String getExcerptDraft() {
		return excerptDraft;
	}

	public void setExcerptDraft(String excerptDraft) {
		this.excerptDraft = excerptDraft;
	}
	
	
}
