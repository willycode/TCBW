package com.kangdainfo.tcbw.model.bo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Drg0001Db  implements Serializable 
{
	/** identifier field */
    private Long id;

    private String applNo;	//案件號碼
    private String status;	//案件狀態
    private String chargeMan;	//作業人員
    private String occurDate;	//發生日期
    private String enrolledDate;  //收案日期
    private String notifierUserID;  //通報者ID
    private String isComplete; //完整性
    private String notifierRevDate;	 //通報者接獲日期
    private String notifierRepDate;	//通報中心接獲通報日期
    private String notifierSource;	//通報來源
    private String notifierName;	//通報者姓名
    private String notifierDeptID;	//通報者服務機構ID
    private String notifierDept;	//通報者服務機構
    private String notifierTel;	//通報者電話
    private String notifierTelArea;	//通報者電話區碼
    private String notifierTelExt;	//通報者電話分機
    private String notifierCounty;	//通報者地址縣市
    private String notifierZipCode;	//通報者地址鄉鎮區
    private String notifierAddress;	//通報者地址
    private String notifierEmail;	//通報者電子信箱
    private String notifierType;	//通報者屬性
    private String notifierTitle;	//通報者職稱
    private String permitKey;	//許可證字
    private String permitNo;	//許可證號
    private String chProduct; 	//商品名稱中文
    private String enProduct; 	//商品名稱英文
    private String ingredient;	//有效成分名稱
    private String content;	//單位含量
    private String medModel;	//劑型
    private String medModelOther;	//劑型(描述)
    private String medPackage;	//包裝形式
    private String medPackageOther;	//包裝形式(描述)
    private String applicationID; //藥商/申請商(統編)
    private String applicationName;	//藥商/申請商
    private String manufactorName;	//製造商/製造廠
    private String manufactorCountry;	//製造商/製造廠國別
    private String manufactorNo;	//製造批號
    private String manufactorDate;	//製造日期
    private String expirationDate;	//保存期限
    private String storage;	//儲存環境
    private String storageOther;	//儲存環境(描述)
    private String isFindYn;	//是否一經拆封即發現本不良品缺陷
    private String isSingleYn;	//本次通報事件是否為單一個案
    private String sameNum;	//同批號件數
    private String diffNum;	//不同批號件數
    private String isHarmYn;	//是否已對人體健康產生危害
    private String isUsedYn;	//是否為病人使用後發現不良品，向醫療人員反應
    private String evenContactYn;	//是否已連絡廠商
    private String dealWith;	//後續處理
    private String isContactYn;	//是否提供聯絡資訊供廠商後續調查評估
    private String defectDesc;	//不良品缺陷描述
    private String firstResult;	//不良品原因初評
    private String firstRemark;	//不良品原因初評(描述)
    private String receiveSystem;	//介接異動系統
    private String receiveDate;	//介接異動日期
    private String receiveTime;	 //介接異動時間
    private String creator;	 //建檔者
    private String createDate;  //建檔日期
    private String createTime;  //建檔時間
    private String modifier;  //最後異動者
    private String modifyDate;  // 最後異動日期
    private String modifyTime;  //最後異動時間
    private String trans;  //轉入資料註記

	private Set drg0002Dbs;

	/** default constructor */
    public Drg0001Db() {
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

	public String getChargeMan() {
		return chargeMan;
	}

	public void setChargeMan(String chargeMan) {
		this.chargeMan = chargeMan;
	}

	public String getEnrolledDate() {
		return enrolledDate;
	}

	public void setEnrolledDate(String enrolledDate) {
		this.enrolledDate = enrolledDate;
	}

	public String getNotifierUserID() {
		return notifierUserID;
	}

	public void setNotifierUserID(String notifierUserID) {
		this.notifierUserID = notifierUserID;
	}

	public String getIsComplete() {
		return isComplete;
	}

	public void setIsComplete(String isComplete) {
		this.isComplete = isComplete;
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

	public String getNotifierRepDate() {
		return notifierRepDate;
	}

	public void setNotifierRepDate(String notifierRepDate) {
		this.notifierRepDate = notifierRepDate;
	}

	public String getNotifierSource() {
		return notifierSource;
	}

	public void setNotifierSource(String notifierSource) {
		this.notifierSource = notifierSource;
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

	public String getNotifierTel() {
		return notifierTel;
	}

	public void setNotifierTel(String notifierTel) {
		this.notifierTel = notifierTel;
	}

	public String getNotifierTelArea() {
		return notifierTelArea;
	}

	public void setNotifierTelArea(String notifierTelArea) {
		this.notifierTelArea = notifierTelArea;
	}

	public String getNotifierTelExt() {
		return notifierTelExt;
	}

	public void setNotifierTelExt(String notifierTelExt) {
		this.notifierTelExt = notifierTelExt;
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

	public String getNotifierAddress() {
		return notifierAddress;
	}

	public void setNotifierAddress(String notifierAddress) {
		this.notifierAddress = notifierAddress;
	}

	public String getNotifierEmail() {
		return notifierEmail;
	}

	public void setNotifierEmail(String notifierEmail) {
		this.notifierEmail = notifierEmail;
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

	public String getIngredient() {
		return ingredient;
	}

	public void setIngredient(String ingredient) {
		this.ingredient = ingredient;
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

	public String getMedPackage() {
		return medPackage;
	}

	public void setMedPackage(String medPackage) {
		this.medPackage = medPackage;
	}

	public String getMedPackageOther() {
		return medPackageOther;
	}

	public void setMedPackageOther(String medPackageOther) {
		this.medPackageOther = medPackageOther;
	}

	public String getApplicationID() {
		return applicationID;
	}

	public void setApplicationID(String applicationID) {
		this.applicationID = applicationID;
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

	public String getManufactorNo() {
		return manufactorNo;
	}

	public void setManufactorNo(String manufactorNo) {
		this.manufactorNo = manufactorNo;
	}

	public String getManufactorCountry() {
		return manufactorCountry;
	}

	public void setManufactorCountry(String manufactorCountry) {
		this.manufactorCountry = manufactorCountry;
	}

	public String getManufactorDate() {
		return manufactorDate;
	}

	public void setManufactorDate(String manufactorDate) {
		this.manufactorDate = manufactorDate;
	}

	public String getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String getStorage() {
		return storage;
	}

	public void setStorage(String storage) {
		this.storage = storage;
	}

	public String getStorageOther() {
		return storageOther;
	}

	public void setStorageOther(String storageOther) {
		this.storageOther = storageOther;
	}

	public String getIsFindYn() {
		return isFindYn;
	}

	public void setIsFindYn(String isFindYn) {
		this.isFindYn = isFindYn;
	}

	public String getIsSingleYn() {
		return isSingleYn;
	}

	public void setIsSingleYn(String isSingleYn) {
		this.isSingleYn = isSingleYn;
	}

	public String getSameNum() {
		return sameNum;
	}

	public void setSameNum(String sameNum) {
		this.sameNum = sameNum;
	}

	public String getDiffNum() {
		return diffNum;
	}

	public void setDiffNum(String diffNum) {
		this.diffNum = diffNum;
	}

	public String getIsHarmYn() {
		return isHarmYn;
	}

	public void setIsHarmYn(String isHarmYn) {
		this.isHarmYn = isHarmYn;
	}

	public String getEvenContactYn() {
		return evenContactYn;
	}

	public void setEvenContactYn(String evenContactYn) {
		this.evenContactYn = evenContactYn;
	}

	public String getDealWith() {
		return dealWith;
	}

	public void setDealWith(String dealWith) {
		this.dealWith = dealWith;
	}

	public String getIsContactYn() {
		return isContactYn;
	}

	public void setIsContactYn(String isContactYn) {
		this.isContactYn = isContactYn;
	}

	public String getFirstResult() {
		return firstResult;
	}

	public void setFirstResult(String firstResult) {
		this.firstResult = firstResult;
	}

	public String getFirstRemark() {
		return firstRemark;
	}

	public void setFirstRemark(String firstRemark) {
		this.firstRemark = firstRemark;
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

	public String getIsUsedYn() {
		return isUsedYn;
	}

	public void setIsUsedYn(String isUsedYn) {
		this.isUsedYn = isUsedYn;
	}

	public String getDefectDesc() {
		return defectDesc;
	}

	public void setDefectDesc(String defectDesc) {
		this.defectDesc = defectDesc;
	}

	public String getTrans() {
		return trans;
	}

	public void setTrans(String trans) {
		this.trans = trans;
	}

	public Set getDrg0002Dbs() {
		return drg0002Dbs;
	}

	public void setDrg0002Dbs(Set drg0002Dbs) {
		this.drg0002Dbs = drg0002Dbs;
	}
	
	public String toString() 
	{
		StringBuilder builder = new StringBuilder();
		builder.append("Drg0001Db [id=");
		builder.append(id);
		builder.append(", applNo=");
		builder.append(applNo);
		builder.append(", status=");
		builder.append(status);
		builder.append(", chargeMan=");
		builder.append(chargeMan);
		builder.append(", enrolledDate=");
		builder.append(enrolledDate);
		builder.append(", notifierUserID=");
		builder.append(notifierUserID);
		builder.append(", isComplete=");
		builder.append(isComplete);
		builder.append(", occurDate=");
		builder.append(occurDate);
		builder.append(", notifierRevDate=");
		builder.append(notifierRevDate);
		builder.append(", notifierRepDate=");
		builder.append(notifierRepDate);
		builder.append(", notifierSource=");
		builder.append(notifierSource);
		builder.append(", notifierName=");
		builder.append(notifierName);
		builder.append(", notifierDeptID=");
		builder.append(notifierDeptID);
		builder.append(", notifierDept=");
		builder.append(notifierDept);
		builder.append(", notifierTel=");
		builder.append(notifierTel);		
		builder.append(", notifierTelArea=");
		builder.append(notifierTelArea);
		builder.append(", notifierTelExt=");
		builder.append(notifierTelExt);
		builder.append(", notifierCounty=");
		builder.append(notifierCounty);
		builder.append(", notifierZipCode=");
		builder.append(notifierZipCode);
		builder.append(", notifierAddress=");
		builder.append(notifierAddress);
		builder.append(", notifierEmail=");
		builder.append(notifierEmail);
		builder.append(", notifierType=");
		builder.append(notifierType);
		builder.append(", notifierTitle=");
		builder.append(notifierTitle);
		builder.append(", permitKey=");
		builder.append(permitKey);
		builder.append(", permitNo=");
		builder.append(permitNo);
		builder.append(", chProduct=");
		builder.append(chProduct);
		builder.append(", enProduct=");
		builder.append(enProduct);
		builder.append(", ingredient=");
		builder.append(ingredient);
		builder.append(", content=");
		builder.append(content);
		builder.append(", medModel=");
		builder.append(medModel);
		builder.append(", medModelOther=");
		builder.append(medModelOther);
		builder.append(", medPackage=");
		builder.append(medPackage);
		builder.append(", medPackageOther=");
		builder.append(medPackageOther);
		builder.append(", applicationID=");
		builder.append(applicationID);
		builder.append(", applicationName=");
		builder.append(applicationName);
		builder.append(", manufactorName=");
		builder.append(manufactorName);
		builder.append(", manufactorNo=");
		builder.append(manufactorNo);
		builder.append(", manufactorCountry=");
		builder.append(manufactorCountry);		
		builder.append(", manufactorDate=");
		builder.append(manufactorDate);
		builder.append(", expirationDate=");
		builder.append(expirationDate);
		builder.append(", storage=");
		builder.append(storage);
		builder.append(", storageOther=");
		builder.append(storageOther);
		builder.append(", isFindYn=");
		builder.append(isFindYn);
		builder.append(", isSingleYn=");
		builder.append(isSingleYn);
		builder.append(", sameNum=");
		builder.append(sameNum);
		builder.append(", diffNum=");
		builder.append(diffNum);
		builder.append(", isHarmYn=");
		builder.append(isHarmYn);
		builder.append(", isUsedYn=");
		builder.append(isUsedYn);
		builder.append(", evenContactYn=");
		builder.append(evenContactYn);
		builder.append(", dealWith=");
		builder.append(dealWith);
		builder.append(", isContactYn=");
		builder.append(isContactYn);
		builder.append(", defectDesc=");
		builder.append(defectDesc);		
		builder.append(", firstResult=");
		builder.append(firstResult);
		builder.append(", firstRemark=");
		builder.append(firstRemark);		
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
		builder.append("]");
		return builder.toString();
	}
	
}
