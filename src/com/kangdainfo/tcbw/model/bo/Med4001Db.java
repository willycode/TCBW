package com.kangdainfo.tcbw.model.bo;

import java.io.Serializable;
import java.util.Set;

public class Med4001Db  implements Serializable 
{
	/** identifier field */
    private Long id;
    private Long med0001ID;
   
    private String applNo;//案件號碼
    private String applNo1;//案件號碼
    private String status;//案件狀態
    private String occurDate;//發生日期
    private String inOrOutcreator;
    private String enrolledDate;//收案日期
    private String revision;//版次
    private String notifierRevDate;//通報者獲知日期
    private String notifierRepDate;//通報中心接獲通報日期
    
    private String notifierName;//通報者-姓名
    private String notifierAreaCode;//通報者-區域號碼
    private String notifierTel;//通報者-電話
    private String notifierTelExt;//通報者-電話分機
    private String notifierCounty;	//通報者縣市	VARCHAR(2)
	private String notifierZipCode;	//通報者地區	VARCHAR(5)
    private String notifierAddress;//通報者-地址
    private String notifierEamil;//通報者-電子郵件
    private String notifierType;//通報者-屬性
    private String notifierStaffHospital;//通報者-醫療人員-醫院名稱
    private String notifierStaffTitle;//通報者-醫療人員-職稱
    private String notifierStaffTitleOther;//通報者-醫療人員-職稱-其他描述
    private String notifierFirmDept;//通報者-廠商-服務機構
    private String notifierDeptID;//通報者資訊-服務機構ID
    private String notifierDept;//通報者資訊-服務機構NAME

    private String isContactYn;//是否提供聯絡資料以助廠商分析不良事件
    private String drugEventsSources;//原始藥物不良事件獲知來源
    private String medicalStaff;//原始藥物不良事件獲知來源-由醫療人員轉知-選項
    private String medicalStaffOther;//原始藥物不良事件獲知來源-由醫療人員轉知-其他說明
    private String healthUnits;//原始藥物不良事件獲知來源-由衛生單位得知-選項
    private String healthUnitsOther;//原始藥物不良事件獲知來源-由衛生單位得知-其他說明
    private String caseSource;//案例來源
    private String caseSourceOutCountry;//案例來源-國外-國家
    private String reportKind;//報告類別
    private String trackingNum;//報告類別-追蹤報告-次數
    private String correctiveAction;//報告類別-矯正措施
    private String attachment;//附件
    private String attachmentYnum;//附件-有-件數
    private String drugSafetyMonitoring;//產品經公告列入藥物安全監視

    
    
    
    private String medPermit;//懷疑的醫療器材-許可證字號-字
    private String medPermitNumber;//懷疑的醫療器材-許可證字號-號
    private String medCname;//懷疑的醫療器材-中文品名
    
    private String medPermitFirmCode;//懷疑的醫療器材-許可證申請商代碼
    private String medPermitFirm;//懷疑的醫療器材-許可證申請商
    
    private String medMainCategoryCode;//懷疑的醫療器材-醫材主類別代碼
    private String medMainCategory;//懷疑的醫療器材-醫材主類別
    
    private String medSecCategoryCode;//懷疑的醫療器材-醫材次類別代碼
    private String medSecCategory;//懷疑的醫療器材-醫材次類別
    
    private String medCountry;//懷疑的醫療器材-製造國別
    private String medFactory;//懷疑的醫療器材-製造廠
    
    
    private String medModel;//懷疑的醫療器材-型號
    private String medNo;//懷疑的醫療器材-序號
    private String medLotNum;//懷疑的醫療器材-批號
    private String medSoftwareVersion;//懷疑的醫療器材-軟體版本
    private String medManufactureDate;//懷疑的醫療器材-製造日期
    private String medEffectiveDate;//懷疑的醫療器材-有效日期/保存期間
    private String medPurchaseDate;//懷疑的醫療器材-採購日期
    private String medUseDate;//懷疑的醫療器材-使用日期
    private String medUseReason;//懷疑的醫療器材-使用原因

    private String eventKind;//事件類別
    private String badReactionPatientCode;//不良反應相關資料-病人識別代號
    private String badReactionSex;//不良反應相關資料-性別
    private String badReactionBirthday;//不良反應相關資料-出生日期
    private String badReactionAge;//不良反應相關資料-年齡
    private String badReactionWeight;//不良反應相關資料-體重
    private String badReactionHeight;//不良反應相關資料-身高
    private String badReactionResults;//不良反應結果
    private String badReactionResultsDeathDate;//不良反應結果-A死亡-日期
    private String badReactionResultsDeathReason;//不良反應結果-A死亡-死亡原因
    private String badReactionResultsOther;//不良反應結果-G非嚴重不良反應-請敘述
    
    private String medOperator;//醫療器材操作者
    private String medDisposalStatus;//器材處置現況
    private String medDisposalStatusDate;//器材處置現況-尚植於病患體內-日期
    private String medUse;//器材使用
    private String medUseOther;//器材使用-其他
    private String onceUseMed;//曾使用同類醫材之經驗
    private String onceUseMedName;//曾使用同類醫材之經驗-是-醫材名稱
    private String onceUseMedOther;//曾使用同類醫材之經驗-是-若發生不良反應請描述
    private String stopMedMitigate;//本次事件停用醫材後，症狀是否減輕
    private String sameReaction;//承上，再使用是否出現同樣反應 
    private String otherRelatedData;//其他相關資料
    
    private String productProblemKind1;//產品問題分類-器材操作
    private String productProblemKind2;//產品問題分類-環境設施
    private String productProblemKind3;//產品問題分類-人因
    private String productProblemKind4;//產品問題分類-物理特性
    private String productProblemKindOther;//產品問題分類-其他
    private String defProductDescription;//不良品缺陷描述
    private String defProductOtherDescription;//其他資料
    
    private String medEname;
    
    private String isExcelUpdate;//是否有Excel轉入補強資料
    
    private String receiveSystem;//介接異動系統
    private String receiveDate;//介接異動日期
    private String receiveTime;//介接異動時間
    private String creator;//建檔者
    private String createDate;//建檔日期
    private String createTime;//建檔時間
    private String modifier;//最後異動者
    private String modifyDate;// 最後異動日期
    private String modifyTime;//最後異動時間

    private Set med4002Dbs;
	private Set med4003Dbs;
	private Set med4004Dbs;
	private Set med4005Dbs;
	
	public Long getMed0001ID() {
		return med0001ID;
	}

	public void setMed0001ID(Long med0001id) {
		med0001ID = med0001id;
	}

	
	public Set getMed4002Dbs() {
		return med4002Dbs;
	}

	public void setMed4002Dbs(Set med4002Dbs) {
		this.med4002Dbs = med4002Dbs;
	}

	public Set getMed4003Dbs() {
		return med4003Dbs;
	}

	public void setMed4003Dbs(Set med4003Dbs) {
		this.med4003Dbs = med4003Dbs;
	}

	public Set getMed4004Dbs() {
		return med4004Dbs;
	}

	public void setMed4004Dbs(Set med4004Dbs) {
		this.med4004Dbs = med4004Dbs;
	}
	
	public Set getMed4005Dbs() {
		return med4005Dbs;
	}

	public void setMed4005Dbs(Set med4005Dbs) {
		this.med4005Dbs = med4005Dbs;
	}
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	
	public String getInOrOutcreator() {
		return inOrOutcreator;
	}

	public void setInOrOutcreator(String inOrOutcreator) {
		this.inOrOutcreator = inOrOutcreator;
	}

	public String getApplNo() {
		return applNo;
	}

	public void setApplNo(String applNo) {
		this.applNo = applNo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOccurDate() {
		return occurDate;
	}

	public void setOccurDate(String occurDate) {
		this.occurDate = occurDate;
	}

	public String getNotifierRevDate() {
		return notifierRevDate;
	}

	public void setNotifierRevDate(String notifierRevDate) {
		this.notifierRevDate = notifierRevDate;
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

	public String getNotifierRepDate() {
		return notifierRepDate;
	}

	public void setNotifierRepDate(String notifierRepDate) {
		this.notifierRepDate = notifierRepDate;
	}

	public String getNotifierName() {
		return notifierName;
	}

	public void setNotifierName(String notifierName) {
		this.notifierName = notifierName;
	}

	public String getNotifierAreaCode() {
		return notifierAreaCode;
	}

	public void setNotifierAreaCode(String notifierAreaCode) {
		this.notifierAreaCode = notifierAreaCode;
	}

	public String getNotifierTel() {
		return notifierTel;
	}

	public void setNotifierTel(String notifierTel) {
		this.notifierTel = notifierTel;
	}

	public String getNotifierAddress() {
		return notifierAddress;
	}

	public void setNotifierAddress(String notifierAddress) {
		this.notifierAddress = notifierAddress;
	}

	public String getNotifierEamil() {
		return notifierEamil;
	}

	public void setNotifierEamil(String notifierEamil) {
		this.notifierEamil = notifierEamil;
	}

	public String getNotifierType() {
		return notifierType;
	}

	public void setNotifierType(String notifierType) {
		this.notifierType = notifierType;
	}

	public String getNotifierStaffHospital() {
		return notifierStaffHospital;
	}

	public void setNotifierStaffHospital(String notifierStaffHospital) {
		this.notifierStaffHospital = notifierStaffHospital;
	}

	public String getNotifierStaffTitle() {
		return notifierStaffTitle;
	}

	public void setNotifierStaffTitle(String notifierStaffTitle) {
		this.notifierStaffTitle = notifierStaffTitle;
	}

	
	public String getNotifierTelExt() {
		return notifierTelExt;
	}

	public void setNotifierTelExt(String notifierTelExt) {
		this.notifierTelExt = notifierTelExt;
	}

	public String getNotifierStaffTitleOther() {
		return notifierStaffTitleOther;
	}

	public void setNotifierStaffTitleOther(String notifierStaffTitleOther) {
		this.notifierStaffTitleOther = notifierStaffTitleOther;
	}

	public String getNotifierDeptID() {
		return notifierDeptID;
	}

	public void setNotifierDeptID(String notifierDeptID) {
		this.notifierDeptID = notifierDeptID;
	}

	public String getNotifierDept() {
		return notifierDept;
	}

	public void setNotifierDept(String notifierDept) {
		this.notifierDept = notifierDept;
	}

	public String getIsContactYn() {
		return isContactYn;
	}

	public void setIsContactYn(String isContactYn) {
		this.isContactYn = isContactYn;
	}

	public String getDrugEventsSources() {
		return drugEventsSources;
	}

	public void setDrugEventsSources(String drugEventsSources) {
		this.drugEventsSources = drugEventsSources;
	}

	public String getMedicalStaff() {
		return medicalStaff;
	}
	
	public String getMedPermit() {
		return medPermit;
	}

	public void setMedPermit(String medPermit) {
		this.medPermit = medPermit;
	}

	public void setMedicalStaff(String medicalStaff) {
		this.medicalStaff = medicalStaff;
	}

	public String getMedicalStaffOther() {
		return medicalStaffOther;
	}

	public void setMedicalStaffOther(String medicalStaffOther) {
		this.medicalStaffOther = medicalStaffOther;
	}

	public String getHealthUnits() {
		return healthUnits;
	}

	public void setHealthUnits(String healthUnits) {
		this.healthUnits = healthUnits;
	}

	public String getHealthUnitsOther() {
		return healthUnitsOther;
	}

	public void setHealthUnitsOther(String healthUnitsOther) {
		this.healthUnitsOther = healthUnitsOther;
	}

	public String getCaseSource() {
		return caseSource;
	}

	public void setCaseSource(String caseSource) {
		this.caseSource = caseSource;
	}

	public String getCaseSourceOutCountry() {
		return caseSourceOutCountry;
	}

	public void setCaseSourceOutCountry(String caseSourceOutCountry) {
		this.caseSourceOutCountry = caseSourceOutCountry;
	}

	public String getReportKind() {
		return reportKind;
	}

	public void setReportKind(String reportKind) {
		this.reportKind = reportKind;
	}

	public String getTrackingNum() {
		return trackingNum;
	}

	public void setTrackingNum(String trackingNum) {
		this.trackingNum = trackingNum;
	}

	public String getCorrectiveAction() {
		return correctiveAction;
	}

	public void setCorrectiveAction(String correctiveAction) {
		this.correctiveAction = correctiveAction;
	}

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	public String getAttachmentYnum() {
		return attachmentYnum;
	}

	public void setAttachmentYnum(String attachmentYnum) {
		this.attachmentYnum = attachmentYnum;
	}

	public String getDrugSafetyMonitoring() {
		return drugSafetyMonitoring;
	}

	public void setDrugSafetyMonitoring(String drugSafetyMonitoring) {
		this.drugSafetyMonitoring = drugSafetyMonitoring;
	}

	public String getMedCname() {
		return medCname;
	}

	public void setMedCname(String medCname) {
		this.medCname = medCname;
	}

	public String getMedPermitNumber() {
		return medPermitNumber;
	}

	public void setMedPermitNumber(String medPermitNumber) {
		this.medPermitNumber = medPermitNumber;
	}

	public String getMedPermitFirm() {
		return medPermitFirm;
	}

	public void setMedPermitFirm(String medPermitFirm) {
		this.medPermitFirm = medPermitFirm;
	}

	public String getMedCountry() {
		return medCountry;
	}

	public void setMedCountry(String medCountry) {
		this.medCountry = medCountry;
	}

	public String getMedFactory() {
		return medFactory;
	}

	public void setMedFactory(String medFactory) {
		this.medFactory = medFactory;
	}

	public String getMedModel() {
		return medModel;
	}

	public void setMedModel(String medModel) {
		this.medModel = medModel;
	}

	public String getMedNo() {
		return medNo;
	}

	public void setMedNo(String medNo) {
		this.medNo = medNo;
	}

	public String getMedLotNum() {
		return medLotNum;
	}

	public void setMedLotNum(String medLotNum) {
		this.medLotNum = medLotNum;
	}

	public String getMedSoftwareVersion() {
		return medSoftwareVersion;
	}

	public void setMedSoftwareVersion(String medSoftwareVersion) {
		this.medSoftwareVersion = medSoftwareVersion;
	}

	public String getMedManufactureDate() {
		return medManufactureDate;
	}

	public void setMedManufactureDate(String medManufactureDate) {
		this.medManufactureDate = medManufactureDate;
	}

	public String getMedEffectiveDate() {
		return medEffectiveDate;
	}

	public void setMedEffectiveDate(String medEffectiveDate) {
		this.medEffectiveDate = medEffectiveDate;
	}

	public String getMedPurchaseDate() {
		return medPurchaseDate;
	}

	public void setMedPurchaseDate(String medPurchaseDate) {
		this.medPurchaseDate = medPurchaseDate;
	}

	public String getMedUseDate() {
		return medUseDate;
	}

	public void setMedUseDate(String medUseDate) {
		this.medUseDate = medUseDate;
	}

	public String getMedUseReason() {
		return medUseReason;
	}

	public void setMedUseReason(String medUseReason) {
		this.medUseReason = medUseReason;
	}

	public String getEventKind() {
		return eventKind;
	}

	public void setEventKind(String eventKind) {
		this.eventKind = eventKind;
	}

	public String getBadReactionPatientCode() {
		return badReactionPatientCode;
	}

	public void setBadReactionPatientCode(String badReactionPatientCode) {
		this.badReactionPatientCode = badReactionPatientCode;
	}

	public String getBadReactionSex() {
		return badReactionSex;
	}

	public void setBadReactionSex(String badReactionSex) {
		this.badReactionSex = badReactionSex;
	}

	public String getBadReactionBirthday() {
		return badReactionBirthday;
	}

	public void setBadReactionBirthday(String badReactionBirthday) {
		this.badReactionBirthday = badReactionBirthday;
	}

	public String getBadReactionAge() {
		return badReactionAge;
	}

	public void setBadReactionAge(String badReactionAge) {
		this.badReactionAge = badReactionAge;
	}

	public String getBadReactionWeight() {
		return badReactionWeight;
	}

	public void setBadReactionWeight(String badReactionWeight) {
		this.badReactionWeight = badReactionWeight;
	}

	public String getBadReactionHeight() {
		return badReactionHeight;
	}

	public void setBadReactionHeight(String badReactionHeight) {
		this.badReactionHeight = badReactionHeight;
	}

	public String getBadReactionResults() {
		return badReactionResults;
	}

	public void setBadReactionResults(String badReactionResults) {
		this.badReactionResults = badReactionResults;
	}

	public String getBadReactionResultsDeathDate() {
		return badReactionResultsDeathDate;
	}

	public void setBadReactionResultsDeathDate(String badReactionResultsDeathDate) {
		this.badReactionResultsDeathDate = badReactionResultsDeathDate;
	}

	public String getBadReactionResultsDeathReason() {
		return badReactionResultsDeathReason;
	}

	public void setBadReactionResultsDeathReason(
			String badReactionResultsDeathReason) {
		this.badReactionResultsDeathReason = badReactionResultsDeathReason;
	}

	public String getBadReactionResultsOther() {
		return badReactionResultsOther;
	}

	public void setBadReactionResultsOther(String badReactionResultsOther) {
		this.badReactionResultsOther = badReactionResultsOther;
	}

	public String getMedOperator() {
		return medOperator;
	}

	public void setMedOperator(String medOperator) {
		this.medOperator = medOperator;
	}

	public String getMedDisposalStatus() {
		return medDisposalStatus;
	}

	public void setMedDisposalStatus(String medDisposalStatus) {
		this.medDisposalStatus = medDisposalStatus;
	}

	public String getMedDisposalStatusDate() {
		return medDisposalStatusDate;
	}

	public void setMedDisposalStatusDate(String medDisposalStatusDate) {
		this.medDisposalStatusDate = medDisposalStatusDate;
	}

	public String getMedUse() {
		return medUse;
	}

	public void setMedUse(String medUse) {
		this.medUse = medUse;
	}

	public String getMedUseOther() {
		return medUseOther;
	}

	public void setMedUseOther(String medUseOther) {
		this.medUseOther = medUseOther;
	}

	public String getOnceUseMed() {
		return onceUseMed;
	}

	public void setOnceUseMed(String onceUseMed) {
		this.onceUseMed = onceUseMed;
	}

	public String getOnceUseMedName() {
		return onceUseMedName;
	}

	public void setOnceUseMedName(String onceUseMedName) {
		this.onceUseMedName = onceUseMedName;
	}

	public String getOnceUseMedOther() {
		return onceUseMedOther;
	}

	public void setOnceUseMedOther(String onceUseMedOther) {
		this.onceUseMedOther = onceUseMedOther;
	}

	public String getStopMedMitigate() {
		return stopMedMitigate;
	}

	public void setStopMedMitigate(String stopMedMitigate) {
		this.stopMedMitigate = stopMedMitigate;
	}

	public String getSameReaction() {
		return sameReaction;
	}

	public void setSameReaction(String sameReaction) {
		this.sameReaction = sameReaction;
	}

	public String getOtherRelatedData() {
		return otherRelatedData;
	}

	public void setOtherRelatedData(String otherRelatedData) {
		this.otherRelatedData = otherRelatedData;
	}

	public String getProductProblemKind1() {
		return productProblemKind1;
	}

	public void setProductProblemKind1(String productProblemKind1) {
		this.productProblemKind1 = productProblemKind1;
	}
	public String getProductProblemKind2() {
		return productProblemKind2;
	}

	public void setProductProblemKind2(String productProblemKind2) {
		this.productProblemKind2 = productProblemKind2;
	}
	public String getProductProblemKind3() {
		return productProblemKind3;
	}

	public void setProductProblemKind3(String productProblemKind3) {
		this.productProblemKind3= productProblemKind3;
	}
	public String getProductProblemKind4() {
		return productProblemKind4;
	}

	public void setProductProblemKind4(String productProblemKind4) {
		this.productProblemKind4 = productProblemKind4;
	}

	public String getProductProblemKindOther() {
		return productProblemKindOther;
	}

	public void setProductProblemKindOther(String productProblemKindOther) {
		this.productProblemKindOther = productProblemKindOther;
	}

	public String getDefProductDescription() {
		return defProductDescription;
	}

	public void setDefProductDescription(String defProductDescription) {
		this.defProductDescription = defProductDescription;
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

	
	
	public String getEnrolledDate() {
		return enrolledDate;
	}

	public void setEnrolledDate(String enrolledDate) {
		this.enrolledDate = enrolledDate;
	}

	public String getRevision() {
		return revision;
	}

	public void setRevision(String revision) {
		this.revision = revision;
	}

	
	
	public String getApplNo1() {
		return applNo1;
	}

	public void setApplNo1(String applNo1) {
		this.applNo1 = applNo1;
	}

	
	
	public String getMedPermitFirmCode() {
		return medPermitFirmCode;
	}

	public void setMedPermitFirmCode(String medPermitFirmCode) {
		this.medPermitFirmCode = medPermitFirmCode;
	}

	public String getMedMainCategoryCode() {
		return medMainCategoryCode;
	}

	public void setMedMainCategoryCode(String medMainCategoryCode) {
		this.medMainCategoryCode = medMainCategoryCode;
	}

	public String getMedSecCategoryCode() {
		return medSecCategoryCode;
	}

	public void setMedSecCategoryCode(String medSecCategoryCode) {
		this.medSecCategoryCode = medSecCategoryCode;
	}

	public String getDefProductOtherDescription() {
		return defProductOtherDescription;
	}

	public void setDefProductOtherDescription(String defProductOtherDescription) {
		this.defProductOtherDescription = defProductOtherDescription;
	}
	
	public String getNotifierCounty() {
		return notifierCounty;
	}

	public void setNotifierCounty(String notifierCounty) {
		this.notifierCounty = notifierCounty;
	}

	public String getNotifierZipCode() {
		return notifierZipCode;
	}

	public void setNotifierZipCode(String notifierZipCode) {
		this.notifierZipCode = notifierZipCode;
	}

	public String getNotifierFirmDept() {
		return notifierFirmDept;
	}

	public void setNotifierFirmDept(String notifierFirmDept) {
		this.notifierFirmDept = notifierFirmDept;
	}

	
	
	public String getMedEname() {
		return medEname;
	}

	public void setMedEname(String medEname) {
		this.medEname = medEname;
	}
	
	public String getIsExcelUpdate() {
		return isExcelUpdate;
	}

	public void setIsExcelUpdate(String isExcelUpdate) {
		this.isExcelUpdate = isExcelUpdate;
	}

	/** default constructor */
    public Med4001Db() {
    }

    
  
	public String toString() 
	{
		StringBuilder builder = new StringBuilder();
		builder.append("Med4001Db [id=");
		builder.append(id);
		builder.append(", enrolledDate=");
		builder.append(enrolledDate);
		builder.append(", revision=");
		builder.append(revision);
		builder.append(", applNo=");
		builder.append(applNo);
		builder.append(", applNo1=");
		builder.append(applNo1);
		builder.append(", status=");
		builder.append(status);
		builder.append(", occurDate=");
		builder.append(occurDate);
		builder.append(", notifierRevDate=");
		builder.append(notifierRevDate);
		builder.append(", notifierRepDate=");
		builder.append(notifierRepDate);
		builder.append(", notifierName=");
		builder.append(notifierName);
		builder.append(", notifierAreaCode=");
		builder.append(notifierAreaCode);
		builder.append(", notifierTel=");
		builder.append(notifierTel);
		builder.append(", notifierAddress=");
		builder.append(notifierAddress);
		builder.append(", notifierEamil=");
		builder.append(notifierEamil);
		builder.append(", notifierType=");
		builder.append(notifierType);
		builder.append(", medMainCategory=");
		builder.append(medMainCategory);
		builder.append(", medSecCategory=");
		builder.append(medSecCategory);
		builder.append(", notifierStaffHospital=");
		builder.append(notifierStaffHospital);
		builder.append(", notifierStaffTitle=");
		builder.append(notifierStaffTitle);
		builder.append(", notifierStaffTitleOther=");
		builder.append(notifierStaffTitleOther);
		builder.append(", isContactYn=");
		builder.append(isContactYn);
		builder.append(", drugEventsSources=");
		builder.append(drugEventsSources);
		builder.append(", medicalStaff=");
		builder.append(medicalStaff);
		builder.append(", medicalStaffOther=");
		builder.append(medicalStaffOther);
		builder.append(", healthUnits=");
		builder.append(healthUnits);
		builder.append(", healthUnitsOther=");
		builder.append(healthUnitsOther);
		builder.append(", caseSource=");
		builder.append(caseSource);
		builder.append(", caseSourceOutCountry=");
		builder.append(caseSourceOutCountry);
		builder.append(", reportKind=");
		builder.append(reportKind);
		builder.append(", trackingNum=");
		builder.append(trackingNum);
		builder.append(", correctiveAction=");
		builder.append(correctiveAction);
		builder.append(", attachment=");
		builder.append(attachment);
		builder.append(", attachmentYnum=");
		builder.append(attachmentYnum);
		builder.append(", drugSafetyMonitoring=");
		builder.append(drugSafetyMonitoring);
		builder.append(", medCname=");
		builder.append(medCname);
		builder.append(", medPermit=");
		builder.append(medPermit);
		builder.append(", medPermitNumber=");
		builder.append(medPermitNumber);
		builder.append(", medPermitFirm=");
		builder.append(medPermitFirm);
		builder.append(", medCountry=");
		builder.append(medCountry);
		builder.append(", medFactory=");
		builder.append(medFactory);
		builder.append(", medModel=");
		builder.append(medModel);
		builder.append(", medNo=");
		builder.append(medNo);
		builder.append(", medLotNum=");
		builder.append(medLotNum);
		builder.append(", medSoftwareVersion=");
		builder.append(medSoftwareVersion);
		builder.append(", medManufactureDate=");
		builder.append(medManufactureDate);
		builder.append(", medEffectiveDate=");
		builder.append(medEffectiveDate);
		builder.append(", medPurchaseDate=");
		builder.append(medPurchaseDate);
		builder.append(", medUseDate=");
		builder.append(medUseDate);
		builder.append(", medUseReason=");
		builder.append(medUseReason);
		builder.append(", eventKind=");
		builder.append(eventKind);
		builder.append(", badReactionPatientCode=");
		builder.append(badReactionPatientCode);
		builder.append(", badReactionSex=");
		builder.append(badReactionSex);
		builder.append(", badReactionBirthday=");
		builder.append(badReactionBirthday);
		builder.append(", badReactionAge=");
		builder.append(badReactionAge);
		builder.append(", badReactionWeight=");
		builder.append(badReactionWeight);
		builder.append(", badReactionHeight=");
		builder.append(badReactionHeight);
		builder.append(", badReactionResults=");
		builder.append(badReactionResults);
		builder.append(", badReactionResultsDeathDate=");
		builder.append(badReactionResultsDeathDate);
		builder.append(", badReactionResultsDeathReason=");
		builder.append(badReactionResultsDeathReason);
		builder.append(", badReactionResultsOther=");
		builder.append(badReactionResultsOther);
		builder.append(", medOperator=");
		builder.append(medOperator);
		builder.append(", medDisposalStatus=");
		builder.append(medDisposalStatus);
		builder.append(", medDisposalStatusDate=");
		builder.append(medDisposalStatusDate);
		builder.append(", medUse=");
		builder.append(medUse);
		builder.append(", medUseOther=");
		builder.append(medUseOther);
		builder.append(", onceUseMed=");
		builder.append(onceUseMed);
		builder.append(", onceUseMedName=");
		builder.append(onceUseMedName);
		builder.append(", onceUseMedOther=");
		builder.append(onceUseMedOther);
		builder.append(", stopMedMitigate=");
		builder.append(stopMedMitigate);
		builder.append(", sameReaction=");
		builder.append(sameReaction);
		builder.append(", otherRelatedData=");
		builder.append(otherRelatedData);
		builder.append(", productProblemKind1=");
		builder.append(productProblemKind1);
		builder.append(", productProblemKind2=");
		builder.append(productProblemKind2);
		builder.append(", productProblemKind3=");
		builder.append(productProblemKind3);
		builder.append(", productProblemKind4=");
		builder.append(productProblemKind4);
		builder.append(", productProblemKindOther=");
		builder.append(productProblemKindOther);
		builder.append(", defProductDescription=");
		builder.append(defProductDescription);
		builder.append(", inOrOutcreator=");
		builder.append(inOrOutcreator);
		builder.append(", isReCalibration=");
		builder.append(", receiveSystem=");
		builder.append(receiveSystem);
		builder.append(", isExcelUpdate=");
		builder.append(isExcelUpdate);
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
