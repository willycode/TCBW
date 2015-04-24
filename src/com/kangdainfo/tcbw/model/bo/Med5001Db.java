package com.kangdainfo.tcbw.model.bo;

import java.io.Serializable;
import java.util.Set;

public class Med5001Db  implements Serializable 
{
	/** identifier field */
    private Long id;
    private Long med2001ID;
    private String inOrOutcreator;//案件擁有者
    private String revision;//版次	VARCHAR(3)
    
    private String applNo;//案件號碼	VARCHAR(11)
    private String status;//案件狀態	VARCHAR(2)
    private String occurDate;//發生日期	VARCHAR(7)
    private String enrolledDate;//收案日期 VARCHAR(7)
    private String notifierRevDate;//通報者獲知日期	VARCHAR(7)
    private String notifierRepDate;//通報中心接獲通報日期	VARCHAR(7)
    private String notifierName;//通報者-姓名	NVARCHAR(10)
    private String notifierAreaCode;//通報者-區域號碼	VARCHAR(3)
    private String notifierTel;//通報者-電話	VARCHAR(10)
    private String notifierDeptID;//通報者-廠商-服務機構
    private String notifierDept;
    private String notifierTelExt;
    private String notifierCounty;	//通報者縣市	VARCHAR(2)
	private String notifierZipCode;	//通報者地區	VARCHAR(5)
    private String notifierAddress;//通報者-地址	NVARCHAR(50)
    private String notifierEamil;//通報者-電子郵件	VARCHAR(30)
    private String notifierType;//通報者-屬性	VARCHAR(2)
    private String notifierTitle;//通報者-醫療人員職稱	NVARCHAR(20)
    private String caseSource;//案例來源	VARCHAR(4)
    
    private String caseSourceOutCountry;//案例來源-國外-國家	NVARCHAR(20)
    private String caseSourceInHospital;//案例來源-國內-試驗醫院	NVARCHAR(30)
    private String caseSourceInDoctor;//案例來源-國內-試驗醫師	NVARCHAR(20)
    
    private String reportKind;//報告類別	VARCHAR(2)
    private String trackingNum;//報告類別-追蹤報告-次數	VARCHAR(2)
    private String testName;//試驗名稱	NVARCHAR(20)
    private String fdaNum;//衛生福利主管機關核准函-文號	NVARCHAR(20)
    private String fdaOptions;//衛生福利主管機關核准函-選項	VARCHAR(2)
    private String approvedUnits;//核准單位 	VARCHAR(2)
    private String approvedUnitsOther;//核准單位-其他-描述	NVARCHAR(30)
    private String firmTestNo;//廠商試驗編號	NVARCHAR(20)
    private String patientId;//病人基本資料-識別代碼	VARCHAR(10)
    private String patientSex;//病人基本資料-性別	VARCHAR(1)
    private String patientBirth;//病人基本資料-出生日期	VARCHAR(7)
    private String patientAge;//病人基本資料-年齡	VARCHAR(3)
    private String patientWeight;//病人基本資料-體重	VARCHAR(3)
    private String patientHeigth;//病人基本資料-身高	VARCHAR(3)
    private String badReactionResults;//不良反應結果	VARCHAR(2)
    private String badReactionResultsDeathDate;//不良反應結果-A死亡-日期	VARCHAR(7)
    private String badReactionResultsDeathReason;//不良反應結果-A死亡-死亡原因	NVARCHAR(30)
    private String badReactionResultsOther;//不良反應結果-H非嚴重不良事件-請敘述	NVARCHAR(30)
    
    private String isAdverseEvents;//是否為非預期之不良事件
    
    private String medPermit;//懷疑的醫療器材-許可證字號-字	VARCHAR(10)
    private String medPermitNumber;//懷疑的醫療器材-許可證字號-號	VARCHAR(10)
    private String medTestMedical;//懷疑的醫療器材-試驗醫材名稱	NVARCHAR(30)
    private String medType;//懷疑的醫療器材-器材種類	NVARCHAR(30)
    private String medFactory;//懷疑的醫療器材-製造商	NVARCHAR(30)
    private String medMainCategoryCode;//懷疑的醫療器材-醫材主類別代碼
    private String medMainCategory;//懷疑的醫療器材-醫材主類別
    private String medSecCategoryCode;//懷疑的醫療器材-醫材次類別代碼
    private String medSecCategory;//懷疑的醫療器材-醫材次類別
    private String medSupplier;//懷疑的醫療器材-供應商	NVARCHAR(30)
    private String medModel;//懷疑的醫療器材-型號	VARCHAR(10)
    private String medNo;//懷疑的醫療器材-序號	VARCHAR(10)
    private String medLotNum;//懷疑的醫療器材-批號	VARCHAR(10)
    private String medManufactureDate;//懷疑的醫療器材-製造日期	VARCHAR(7)
    private String medOperator;//懷疑的醫療器材-醫療器材操作者	VARCHAR(2)
   
    private String medUseDate;//懷疑的醫療器材-使用日期	VARCHAR(7)
    private String medStopDate;//懷疑的醫療器材-停用日期	VARCHAR(7)
   
    private String medUseReason;//懷疑的醫療器材-使用原因	NVARCHAR(30)
    private String medUseIsYn;//懷疑的醫療器材-是否可提供器材作評估	VARCHAR(1)
    private String medYesSoruce;//懷疑的醫療器材-是否可提供器材作評估-是-取得來源	NVARCHAR(30)
    private String medNoReturn;//懷疑的醫療器材-是否可提供器材作評估-否-已於退還	VARCHAR(1)
    private String medNoReturnDate;//懷疑的醫療器材-是否可提供器材作評估-否-已於退還日期	VARCHAR(7)
    private String medOnceUseMed;//懷疑的醫療器材-曾經使用同類醫材之經驗	VARCHAR(1)
    private String medOnceUseMedName;//懷疑的醫療器材-曾經使用同類醫材之經驗-是-醫材	NVARCHAR(30)
    private String medOnceUseBadReaction;//懷疑的醫療器材-曾經使用同類醫材之經驗-是-不良反應	NVARCHAR(30)
    private String medStopMedMitigate;//懷疑的醫療器材-停用後不良反應是否減輕	VARCHAR(1)
    private String onceSameReaction;//併用之醫療器材或藥品-再使用是否出現同樣反應	VARCHAR(1)
    private String sameTimeUse;//併用之醫療器材或藥品-是否同時使用	VARCHAR(2)
    private String sameTimeUseOther;//併用之醫療器材或藥品-是否同時使用-其他描述	NVARCHAR(30)
    private String medSea;//試驗醫師評估醫材與SAE之因果關係	VARCHAR(2)
    private String procedureSea;//試驗醫師評估手續程序與SAE之因果關係	VARCHAR(2)
    private String noticeSponsor;//本案是否依規定進行相關通報作業-本案是否立即通知試驗委託者	VARCHAR(1)
    private String noticeSponsorWritten;//本案是否依規定進行相關通報作業-本案是否立即通知試驗委託者-書面	VARCHAR(1)
    private String noticeIRB;//本案是否依規定進行相關通報作業-本案是否立即通知人體試驗委員會	VARCHAR(1)
    private String noticeIRBWritten;//本案是否依規定進行相關通報作業-本案是否立即通知人體試驗委員會-書面	VARCHAR(1)
    private String noticeApprovedUnits;//本案是否依規定進行相關通報作業-本案是否立即通知試驗核准單位	VARCHAR(1)
    private String noticeApprovedUnitsWritten;//本案是否依規定進行相關通報作業-本案是否立即通知試驗核准單位-書面	VARCHAR(1)


    private String receiveSystem;//介接異動系統
    private String receiveDate;//介接異動日期
    private String receiveTime;//介接異動時間
    private String creator;//建檔者
    private String createDate;//建檔日期
    private String createTime;//建檔時間
    private String modifier;//最後異動者
    private String modifyDate;// 最後異動日期
    private String modifyTime;//最後異動時間
    
    private String otherDesc;//其他相關資料
    
    private String isExcelUpdate;//是否有Excel轉入補強資料

    private Set med5002Dbs;
	private Set med5003Dbs;
	private Set med5004Dbs;
	private Set med5005Dbs;
	
	

	

	public Long getMed2001ID() {
		return med2001ID;
	}

	public void setMed2001ID(Long med2001id) {
		med2001ID = med2001id;
	}

	public Set getMed5002Dbs() {
		return med5002Dbs;
	}

	public void setMed5002Dbs(Set med5002Dbs) {
		this.med5002Dbs = med5002Dbs;
	}

	public Set getMed5003Dbs() {
		return med5003Dbs;
	}

	public void setMed5003Dbs(Set med5003Dbs) {
		this.med5003Dbs = med5003Dbs;
	}

	public Set getMed5004Dbs() {
		return med5004Dbs;
	}

	public void setMed5004Dbs(Set med5004Dbs) {
		this.med5004Dbs = med5004Dbs;
	}
	
	public Set getMed5005Dbs() {
		return med5005Dbs;
	}

	public void setMed5005Dbs(Set med5005Dbs) {
		this.med5005Dbs = med5005Dbs;
	}
	

	/** default constructor */
    public Med5001Db() {
    }

  
	public String toString() 
	{
		StringBuilder builder = new StringBuilder();
		builder.append("Med5001Db [id=");
		builder.append(id);
		builder.append(", applNo=");
		builder.append(applNo);
		builder.append(", status=");
		builder.append(status);
		builder.append(", occurDate=");
		builder.append(occurDate);
		builder.append(", enrolledDate=");
		builder.append(enrolledDate);
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
		builder.append(", notifierTitle=");
		builder.append(notifierTitle);
		builder.append(", caseSource=");
		builder.append(caseSource);
		builder.append(", caseSourceInHospital=");
		builder.append(caseSourceInHospital);
		builder.append(", caseSourceInDoctor=");
		builder.append(caseSourceInDoctor);
		builder.append(", reportKind=");
		builder.append(reportKind);
		builder.append(", trackingNum=");
		builder.append(trackingNum);
		builder.append(", testName=");
		builder.append(testName);
		builder.append(", fdaNum=");
		builder.append(fdaNum);
		builder.append(", fdaOptions=");
		builder.append(fdaOptions);
		builder.append(", approvedUnits=");
		builder.append(approvedUnits);
		builder.append(", approvedUnitsOther=");
		builder.append(approvedUnitsOther);
		builder.append(", firmTestNo=");
		builder.append(firmTestNo);
		builder.append(", patientId=");
		builder.append(patientId);
		builder.append(", patientSex=");
		builder.append(patientSex);
		builder.append(", patientBirth=");
		builder.append(patientBirth);
		builder.append(", patientAge=");
		builder.append(patientAge);
		builder.append(", patientWeight=");
		builder.append(patientWeight);
		builder.append(", patientHeigth=");
		builder.append(patientHeigth);
		builder.append(", badReactionResults=");
		builder.append(badReactionResults);
		builder.append(", badReactionResultsDeathDate=");
		builder.append(badReactionResultsDeathDate);
		builder.append(", badReactionResultsDeathReason=");
		builder.append(badReactionResultsDeathReason);
		builder.append(", badReactionResultsOther=");
		builder.append(badReactionResultsOther);
		builder.append(", medPermit=");
		builder.append(medPermit);
		builder.append(", medPermitNumber=");
		builder.append(medPermitNumber);
		builder.append(", medTestMedical=");
		builder.append(medTestMedical);
		builder.append(", medType=");
		builder.append(medType);
		builder.append(", medFactory=");
		builder.append(medFactory);
		builder.append(", medSupplier=");
		builder.append(medSupplier);
		builder.append(", medModel=");
		builder.append(medModel);
		builder.append(", medNo=");
		builder.append(medNo);
		builder.append(", medLotNum=");
		builder.append(medLotNum);
		builder.append(", medManufactureDate=");
		builder.append(medManufactureDate);
		builder.append(", medOperator=");
		builder.append(medOperator);
		builder.append(", medStopDate=");
		builder.append(medStopDate);
		builder.append(", medUseDate=");
		builder.append(medUseDate);
		builder.append(", medUseReason=");
		builder.append(medUseReason);
		builder.append(", medUseIsYn=");
		builder.append(medUseIsYn);
		builder.append(", medYesSoruce=");
		builder.append(medYesSoruce);
		builder.append(", medNoReturn=");
		builder.append(medNoReturn);
		builder.append(", medNoReturnDate=");
		builder.append(medNoReturnDate);
		builder.append(", medOnceUseMed=");
		builder.append(medOnceUseMed);
		builder.append(", medOnceUseMedName=");
		builder.append(medOnceUseMedName);
		builder.append(", medOnceUseBadReaction=");
		builder.append(medOnceUseBadReaction);
		builder.append(", medStopMedMitigate=");
		builder.append(medStopMedMitigate);
		builder.append(", onceSameReaction=");
		builder.append(onceSameReaction);
		builder.append(", sameTimeUse=");
		builder.append(sameTimeUse);
		builder.append(", sameTimeUseOther=");
		builder.append(sameTimeUseOther);
		builder.append(", medSea=");
		builder.append(medSea);
		builder.append(", procedureSea=");
		builder.append(procedureSea);
		builder.append(", noticeSponsor=");
		builder.append(noticeSponsor);
		builder.append(", noticeSponsorWritten=");
		builder.append(noticeSponsorWritten);
		builder.append(", noticeIRB=");
		builder.append(noticeIRB);
		builder.append(", noticeIRBWritten=");
		builder.append(noticeIRBWritten);
		builder.append(", noticeApprovedUnits=");
		builder.append(noticeApprovedUnits);
		builder.append(", noticeApprovedUnitsWritten=");
		builder.append(noticeApprovedUnitsWritten);
		builder.append(", caseSourceOutCountry=");
		builder.append(caseSourceOutCountry);
		builder.append(", isAdverseEvents=");
		builder.append(isAdverseEvents);
		builder.append(", medMainCategoryCode=");
		builder.append(medMainCategoryCode);
		builder.append(", medMainCategory=");
		builder.append(medMainCategory);
		builder.append(", medSecCategoryCode=");
		builder.append(medSecCategoryCode);
		builder.append(", medSecCategory=");
		builder.append(medSecCategory);
		builder.append(", isExcelUpdate=");
		builder.append(isExcelUpdate);
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
		builder.append(", revision=");
		builder.append(revision);
		builder.append(", med2001ID=");
		builder.append(med2001ID);
		builder.append("]");
		return builder.toString();
	}    
    
	public String getMedPermit() {
		return medPermit;
	}

	public void setMedPermit(String medPermit) {
		this.medPermit = medPermit;
	}

	public String getMedPermitNumber() {
		return medPermitNumber;
	}

	public void setMedPermitNumber(String medPermitNumber) {
		this.medPermitNumber = medPermitNumber;
	}


	public String getRevision() {
		return revision;
	}

	public void setRevision(String revision) {
		this.revision = revision;
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

	public String getMedMainCategoryCode() {
		return medMainCategoryCode;
	}

	public void setMedMainCategoryCode(String medMainCategoryCode) {
		this.medMainCategoryCode = medMainCategoryCode;
	}
	
	public String getMedMainCategory() {
		return medMainCategory;
	}

	public void setMedMainCategory(String medMainCategory) {
		this.medMainCategory = medMainCategory;
	}

	public String getMedSecCategoryCode() {
		return medSecCategoryCode;
	}

	public void setMedSecCategoryCode(String medSecCategoryCode) {
		this.medSecCategoryCode = medSecCategoryCode;
	}
	
	public String getMedSecCategory() {
		return medSecCategory;
	}

	public void setMedSecCategory(String medSecCategory) {
		this.medSecCategory = medSecCategory;
	}

	public String getNotifierRevDate() {
		return notifierRevDate;
	}

	public void setNotifierRevDate(String notifierRevDate) {
		this.notifierRevDate = notifierRevDate;
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

	public String getNotifierTitle() {
		return notifierTitle;
	}

	public void setNotifierTitle(String notifierTitle) {
		this.notifierTitle = notifierTitle;
	}

	public String getCaseSource() {
		return caseSource;
	}

	public void setCaseSource(String caseSource) {
		this.caseSource = caseSource;
	}

	public String getCaseSourceInHospital() {
		return caseSourceInHospital;
	}

	public void setCaseSourceInHospital(String caseSourceInHospital) {
		this.caseSourceInHospital = caseSourceInHospital;
	}

	public String getCaseSourceInDoctor() {
		return caseSourceInDoctor;
	}

	public void setCaseSourceInDoctor(String caseSourceInDoctor) {
		this.caseSourceInDoctor = caseSourceInDoctor;
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

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public String getFdaNum() {
		return fdaNum;
	}

	public void setFdaNum(String fdaNum) {
		this.fdaNum = fdaNum;
	}

	public String getFdaOptions() {
		return fdaOptions;
	}

	public void setFdaOptions(String fdaOptions) {
		this.fdaOptions = fdaOptions;
	}

	public String getApprovedUnits() {
		return approvedUnits;
	}

	public void setApprovedUnits(String approvedUnits) {
		this.approvedUnits = approvedUnits;
	}

	public String getApprovedUnitsOther() {
		return approvedUnitsOther;
	}

	public void setApprovedUnitsOther(String approvedUnitsOther) {
		this.approvedUnitsOther = approvedUnitsOther;
	}

	public String getFirmTestNo() {
		return firmTestNo;
	}

	public void setFirmTestNo(String firmTestNo) {
		this.firmTestNo = firmTestNo;
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getPatientSex() {
		return patientSex;
	}

	public void setPatientSex(String patientSex) {
		this.patientSex = patientSex;
	}

	public String getPatientBirth() {
		return patientBirth;
	}

	public void setPatientBirth(String patientBirth) {
		this.patientBirth = patientBirth;
	}

	public String getPatientAge() {
		return patientAge;
	}

	public void setPatientAge(String patientAge) {
		this.patientAge = patientAge;
	}

	public String getPatientWeight() {
		return patientWeight;
	}

	public void setPatientWeight(String patientWeight) {
		this.patientWeight = patientWeight;
	}

	public String getPatientHeigth() {
		return patientHeigth;
	}

	public void setPatientHeigth(String patientHeigth) {
		this.patientHeigth = patientHeigth;
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

	public String getMedTestMedical() {
		return medTestMedical;
	}

	public void setMedTestMedical(String medTestMedical) {
		this.medTestMedical = medTestMedical;
	}

	public String getMedType() {
		return medType;
	}

	public void setMedType(String medType) {
		this.medType = medType;
	}

	public String getMedFactory() {
		return medFactory;
	}

	public void setMedFactory(String medFactory) {
		this.medFactory = medFactory;
	}

	public String getMedSupplier() {
		return medSupplier;
	}

	public void setMedSupplier(String medSupplier) {
		this.medSupplier = medSupplier;
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

	public String getMedManufactureDate() {
		return medManufactureDate;
	}

	public void setMedManufactureDate(String medManufactureDate) {
		this.medManufactureDate = medManufactureDate;
	}

	public String getMedOperator() {
		return medOperator;
	}

	public void setMedOperator(String medOperator) {
		this.medOperator = medOperator;
	}

	public String getMedStopDate() {
		return medStopDate;
	}

	public void setMedStopDate(String medStopDate) {
		this.medStopDate = medStopDate;
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

	public String getMedUseIsYn() {
		return medUseIsYn;
	}

	public void setMedUseIsYn(String medUseIsYn) {
		this.medUseIsYn = medUseIsYn;
	}

	public String getMedYesSoruce() {
		return medYesSoruce;
	}

	public void setMedYesSoruce(String medYesSoruce) {
		this.medYesSoruce = medYesSoruce;
	}

	public String getMedNoReturn() {
		return medNoReturn;
	}

	public void setMedNoReturn(String medNoReturn) {
		this.medNoReturn = medNoReturn;
	}

	public String getMedNoReturnDate() {
		return medNoReturnDate;
	}

	public void setMedNoReturnDate(String medNoReturnDate) {
		this.medNoReturnDate = medNoReturnDate;
	}

	public String getMedOnceUseMed() {
		return medOnceUseMed;
	}

	public void setMedOnceUseMed(String medOnceUseMed) {
		this.medOnceUseMed = medOnceUseMed;
	}

	public String getMedOnceUseMedName() {
		return medOnceUseMedName;
	}

	public void setMedOnceUseMedName(String medOnceUseMedName) {
		this.medOnceUseMedName = medOnceUseMedName;
	}

	public String getMedOnceUseBadReaction() {
		return medOnceUseBadReaction;
	}

	public void setMedOnceUseBadReaction(String medOnceUseBadReaction) {
		this.medOnceUseBadReaction = medOnceUseBadReaction;
	}

	public String getMedStopMedMitigate() {
		return medStopMedMitigate;
	}

	public void setMedStopMedMitigate(String medStopMedMitigate) {
		this.medStopMedMitigate = medStopMedMitigate;
	}

	public String getOnceSameReaction() {
		return onceSameReaction;
	}

	public void setOnceSameReaction(String onceSameReaction) {
		this.onceSameReaction = onceSameReaction;
	}

	public String getSameTimeUse() {
		return sameTimeUse;
	}

	public void setSameTimeUse(String sameTimeUse) {
		this.sameTimeUse = sameTimeUse;
	}

	public String getSameTimeUseOther() {
		return sameTimeUseOther;
	}

	public void setSameTimeUseOther(String sameTimeUseOther) {
		this.sameTimeUseOther = sameTimeUseOther;
	}

	public String getMedSea() {
		return medSea;
	}

	public void setMedSea(String medSea) {
		this.medSea = medSea;
	}

	public String getProcedureSea() {
		return procedureSea;
	}

	public void setProcedureSea(String procedureSea) {
		this.procedureSea = procedureSea;
	}

	public String getNoticeSponsor() {
		return noticeSponsor;
	}

	public void setNoticeSponsor(String noticeSponsor) {
		this.noticeSponsor = noticeSponsor;
	}

	public String getNoticeSponsorWritten() {
		return noticeSponsorWritten;
	}

	public void setNoticeSponsorWritten(String noticeSponsorWritten) {
		this.noticeSponsorWritten = noticeSponsorWritten;
	}

	public String getNoticeIRB() {
		return noticeIRB;
	}

	public void setNoticeIRB(String noticeIRB) {
		this.noticeIRB = noticeIRB;
	}

	public String getNoticeIRBWritten() {
		return noticeIRBWritten;
	}

	public void setNoticeIRBWritten(String noticeIRBWritten) {
		this.noticeIRBWritten = noticeIRBWritten;
	}

	public String getNoticeApprovedUnits() {
		return noticeApprovedUnits;
	}
	
	public String getCaseSourceOutCountry() {
		return caseSourceOutCountry;
	}

	public void setCaseSourceOutCountry(String caseSourceOutCountry) {
		this.caseSourceOutCountry = caseSourceOutCountry;
	}

	public void setNoticeApprovedUnits(String noticeApprovedUnits) {
		this.noticeApprovedUnits = noticeApprovedUnits;
	}

	public String getNoticeApprovedUnitsWritten() {
		return noticeApprovedUnitsWritten;
	}

	public void setNoticeApprovedUnitsWritten(String noticeApprovedUnitsWritten) {
		this.noticeApprovedUnitsWritten = noticeApprovedUnitsWritten;
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
	
	public String getIsAdverseEvents() {
		return isAdverseEvents;
	}

	public void setIsAdverseEvents(String isAdverseEvents) {
		this.isAdverseEvents = isAdverseEvents;
	}


	public String getInOrOutcreator() {
		return inOrOutcreator;
	}


	public void setInOrOutcreator(String inOrOutcreator) {
		this.inOrOutcreator = inOrOutcreator;
	}


	public String getEnrolledDate() {
		return enrolledDate;
	}


	public void setEnrolledDate(String enrolledDate) {
		this.enrolledDate = enrolledDate;
	}

	public String getNotifierTelExt() {
		return notifierTelExt;
	}

	public void setNotifierTelExt(String notifierTelExt) {
		this.notifierTelExt =notifierTelExt;
	}

	public String getOtherDesc() {
		return otherDesc;
	}

	public void setOtherDesc(String otherDesc) {
		this.otherDesc = otherDesc;
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
	
	public String getIsExcelUpdate() {
		return isExcelUpdate;
	}

	public void setIsExcelUpdate(String isExcelUpdate) {
		this.isExcelUpdate = isExcelUpdate;
	}
	
	
}
