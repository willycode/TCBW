package com.kangdainfo.tcbw.model.bo;

import java.io.Serializable;
import java.util.Set;

public class Cos4001Db implements Serializable {

	 private Long id;

	 private String applNo;	//案件號碼
	 private String status;	//案件狀態
	 private String chargeMan;	//作業人員
	 private String occurDate;	//發生日期
	 private String notifierRevDate;	//通報者接獲日期
	    private String notifierRepDate;	//通報中心接獲通報日期
	    private String notifierSource;	//通報來源
	    private String notifierSourceOther;	//通報來源(其他)
	    private String notifierName;	//通報者姓名
	    private String notifierTitle;	//通報者職稱
	    private String notifierDept;	//通報者服務機構
	    private String notifierDeptId;
	    private String notifierTelArea;
		private String notifierTelExt;
	    private String notifierTel;	//通報者電話
	    private String notifierPhone;	//通報者行動電話
	    private String notifierFax;	//通報者傳真
	    private String notifierArea;
		private String notifierZipCode;
	    private String address;	//通報者地址
	    private String notifierEamil;	//通報者電子信箱
	    private String notifierType; //通報者屬性
	    private String isContactYn;	//是否願意提供廠商個人聯絡資訊
	    private String cosType;	//不良事件類別
	    private String permitKey;	//許可證字
	    private String permitNo;	//許可證號
	    private String chProduct; 	//品名中文
	    private String enProduct;	//品名英文
	    private String caseNo;	//登錄編號
	    private String ingredient;	//化粧品項目
	    private String traffickWay;	//販賣通路
	    private String traffickWayOther;	//販賣通路(其他)
	    private String tradePlace;	//購買地點
	    private String tradePlaceZipCode;
	    private String tradePlaceAddr;
	    private String tradeDate;	//購買日期
	    private String BusinessName;	//商家名稱
	    private String manufactorName;	//國內製造廠/國外產品進口(代理)商
	    private String manufactorID;	//國內製造廠/國外產品進口(代理)商統編
	    private String manufactorArea;	//廠商地址
	    private String manufactorZipCode;	//廠商地址
	    private String manufactorAddr;	//廠商地址
	    private String manufactorTelArea;	//廠商電話
	    private String manufactorTel;	//廠商電話
	    private String manufactorTelExt;	//廠商電話
	    private String manufactorNo;	//製造批號或製造日期
	    private String expirationDate;	//保存期限
	    private String isSampleYn;	//是否可提供樣品
	    private String evenContactYn;	//是否已與廠商接觸過
	    private String dealWith;	//後續處理
	    private String isSimilarYn;	//同產品是否有類似案例
	    private String isRecurrenceYn;	//處理後是否再度發生
	    private String isOtherDeptYn;	//是否已送交相關單位處理
	    private String otherDpetName;	//相關單位名稱
	    private String isDamageYn;		//是否有損害使用者身體或健康之事實
	    private String otherIsDamageYn;	//其他是否有損害使用者身體或健康之事實
	    private String otherInformation;
	    private String otherExplain;
	    private String caseOwner;
	    private String receiveSystem;	//介接異動系統
	    private String receiveDate;	//介接異動日期
	    private String receiveTime;	 //介接異動時間
	    private String creator;	 //建檔者
	    private String createDate;  //建檔日期
	    private String createTime;  //建檔時間
	    private String modifier;  //最後異動者
	    private String modifyDate;  // 最後異動日期
	    private String modifyTime;  //最後異動時間

		private Set cos4002Dbs;
		private Set cos4003Dbs;
	    
		/** default constructor */
	    public Cos4001Db() {
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

		public String getNotifierSourceOther() {
			return notifierSourceOther;
		}

		public void setNotifierSourceOther(String notifierSourceOther) {
			this.notifierSourceOther = notifierSourceOther;
		}

		public String getNotifierName() {
			return notifierName;
		}

		public void setNotifierName(String notifierName) {
			this.notifierName = notifierName;
		}

		public String getNotifierTitle() {
			return notifierTitle;
		}

		public void setNotifierTitle(String notifierTitle) {
			this.notifierTitle = notifierTitle;
		}

		public String getNotifierDept() {
			return notifierDept;
		}

		public void setNotifierDept(String notifierDept) {
			this.notifierDept = notifierDept;
		}
		
		public String getNotifierDeptId() {
			return notifierDeptId;
		}

		public void setNotifierDeptId(String notifierDeptId) {
			this.notifierDeptId = notifierDeptId;
		}

		public String getNotifierTel() {
			return notifierTel;
		}

		public void setNotifierTel(String notifierTel) {
			this.notifierTel = notifierTel;
		}

		public String getNotifierPhone() {
			return notifierPhone;
		}

		public void setNotifierPhone(String notifierPhone) {
			this.notifierPhone = notifierPhone;
		}

		public String getNotifierFax() {
			return notifierFax;
		}

		public void setNotifierFax(String notifierFax) {
			this.notifierFax = notifierFax;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
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

		public String getIsContactYn() {
			return isContactYn;
		}

		public void setIsContactYn(String isContactYn) {
			this.isContactYn = isContactYn;
		}

		public String getCosType() {
			return cosType;
		}

		public void setCosType(String cosType) {
			this.cosType = cosType;
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

		public String getCaseNo() {
			return caseNo;
		}

		public void setCaseNo(String caseNo) {
			this.caseNo = caseNo;
		}

		public String getIngredient() {
			return ingredient;
		}

		public void setIngredient(String ingredient) {
			this.ingredient = ingredient;
		}

		public String getTraffickWay() {
			return traffickWay;
		}

		public void setTraffickWay(String traffickWay) {
			this.traffickWay = traffickWay;
		}

		public String getTraffickWayOther() {
			return traffickWayOther;
		}

		public void setTraffickWayOther(String traffickWayOther) {
			this.traffickWayOther = traffickWayOther;
		}

		public String getTradePlace() {
			return tradePlace;
		}

		public void setTradePlace(String tradePlace) {
			this.tradePlace = tradePlace;
		}
		
		public String getTradePlaceZipCode() {
			return tradePlaceZipCode;
		}

		public void setTradePlaceZipCode(String tradePlaceZipCode) {
			this.tradePlaceZipCode = tradePlaceZipCode;
		}

		public String getTradePlaceAddr() {
			return tradePlaceAddr;
		}

		public void setTradePlaceAddr(String tradePlaceAddr) {
			this.tradePlaceAddr = tradePlaceAddr;
		}

		public String getTradeDate() {
			return tradeDate;
		}

		public void setTradeDate(String tradeDate) {
			this.tradeDate = tradeDate;
		}

		public String getBusinessName() {
			return BusinessName;
		}

		public void setBusinessName(String businessName) {
			BusinessName = businessName;
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

		public String getManufactorAddr() {
			return manufactorAddr;
		}

		public void setManufactorAddr(String manufactorAddr) {
			this.manufactorAddr = manufactorAddr;
		}

		public String getManufactorTel() {
			return manufactorTel;
		}

		public void setManufactorTel(String manufactorTel) {
			this.manufactorTel = manufactorTel;
		}

		public String getManufactorNo() {
			return manufactorNo;
		}

		public void setManufactorNo(String manufactorNo) {
			this.manufactorNo = manufactorNo;
		}

		public String getExpirationDate() {
			return expirationDate;
		}

		public void setExpirationDate(String expirationDate) {
			this.expirationDate = expirationDate;
		}

		public String getIsSampleYn() {
			return isSampleYn;
		}

		public void setIsSampleYn(String isSampleYn) {
			this.isSampleYn = isSampleYn;
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

		public String getIsSimilarYn() {
			return isSimilarYn;
		}

		public void setIsSimilarYn(String isSimilarYn) {
			this.isSimilarYn = isSimilarYn;
		}

		public String getIsRecurrenceYn() {
			return isRecurrenceYn;
		}

		public void setIsRecurrenceYn(String isRecurrenceYn) {
			this.isRecurrenceYn = isRecurrenceYn;
		}

		public String getIsOtherDeptYn() {
			return isOtherDeptYn;
		}

		public void setIsOtherDeptYn(String isOtherDeptYn) {
			this.isOtherDeptYn = isOtherDeptYn;
		}

		public String getOtherDpetName() {
			return otherDpetName;
		}

		public void setOtherDpetName(String otherDpetName) {
			this.otherDpetName = otherDpetName;
		}
		
		public String getIsDamageYn() {
			return isDamageYn;
		}

		public void setIsDamageYn(String isDamageYn) {
			this.isDamageYn = isDamageYn;
		}
		
		public String getOtherIsDamageYn() {
			return otherIsDamageYn;
		}

		public void setOtherIsDamageYn(String otherIsDamageYn) {
			this.otherIsDamageYn = otherIsDamageYn;
		}

		public String getOtherInformation() {
			return otherInformation;
		}

		public void setOtherInformation(String otherInformation) {
			this.otherInformation = otherInformation;
		}
		
		public String getOtherExplain() {
			return otherExplain;
		}

		public void setOtherExplain(String otherExplain) {
			this.otherExplain = otherExplain;
		}

		public String getCaseOwner() {
			return caseOwner;
		}

		public void setCaseOwner(String caseOwner) {
			this.caseOwner = caseOwner;
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

		public String getNotifierArea() {
			return notifierArea;
		}

		public void setNotifierArea(String notifierArea) {
			this.notifierArea = notifierArea;
		}

		public String getNotifierZipCode() {
			return notifierZipCode;
		}

		public void setNotifierZipCode(String notifierZipCode) {
			this.notifierZipCode = notifierZipCode;
		}
		
		public String getManufactorArea() {
			return manufactorArea;
		}

		public void setManufactorArea(String manufactorArea) {
			this.manufactorArea = manufactorArea;
		}

		public String getManufactorZipCode() {
			return manufactorZipCode;
		}

		public void setManufactorZipCode(String manufactorZipCode) {
			this.manufactorZipCode = manufactorZipCode;
		}

		public String getManufactorTelArea() {
			return manufactorTelArea;
		}

		public void setManufactorTelArea(String manufactorTelArea) {
			this.manufactorTelArea = manufactorTelArea;
		}

		public String getManufactorTelExt() {
			return manufactorTelExt;
		}

		public void setManufactorTelExt(String manufactorTelExt) {
			this.manufactorTelExt = manufactorTelExt;
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

		public Set getCos4002Dbs() {
			return cos4002Dbs;
		}

		public void setCos4002Dbs(Set cos4002Dbs) {
			this.cos4002Dbs = cos4002Dbs;
		}

		public Set getCos4003Dbs() {
			return cos4003Dbs;
		}

		public void setCos4003Dbs(Set cos4003Dbs) {
			this.cos4003Dbs = cos4003Dbs;
		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("Cos4001Db [id=");
			builder.append(id);
			builder.append(", applNo=");
			builder.append(applNo);
			builder.append(", status=");
			builder.append(status);
			builder.append(", chargeMan=");
			builder.append(chargeMan);
			builder.append(", occurDate=");
			builder.append(occurDate);
			builder.append(", notifierRevDate=");
			builder.append(notifierRevDate);
			builder.append(", notifierRepDate=");
			builder.append(notifierRepDate);
			builder.append(", notifierSource=");
			builder.append(notifierSource);
			builder.append(", notifierSourceOther=");
			builder.append(notifierSourceOther);
			builder.append(", notifierName=");
			builder.append(notifierName);
			builder.append(", notifierTitle=");
			builder.append(notifierTitle);
			builder.append(", notifierDept=");
			builder.append(notifierDept);
			builder.append(", notifierTelArea=");
			builder.append(notifierTelArea);
			builder.append(", notifierTelExt=");
			builder.append(notifierTelExt);
			builder.append(", notifierTel=");
			builder.append(notifierTel);
			builder.append(", notifierPhone=");
			builder.append(notifierPhone);
			builder.append(", notifierFax=");
			builder.append(notifierFax);
			builder.append(", notifierArea=");
			builder.append(notifierArea);
			builder.append(", notifierZipCode=");
			builder.append(notifierZipCode);
			builder.append(", address=");
			builder.append(address);
			builder.append(", notifierEamil=");
			builder.append(notifierEamil);
			builder.append(", notifierType=");
			builder.append(notifierType);
			builder.append(", isContactYn=");
			builder.append(isContactYn);
			builder.append(", cosType=");
			builder.append(cosType);
			builder.append(", permitKey=");
			builder.append(permitKey);
			builder.append(", permitNo=");
			builder.append(permitNo);
			builder.append(", chProduct=");
			builder.append(chProduct);
			builder.append(", enProduct=");
			builder.append(enProduct);
			builder.append(", caseNo=");
			builder.append(caseNo);
			builder.append(", ingredient=");
			builder.append(ingredient);
			builder.append(", traffickWay=");
			builder.append(traffickWay);
			builder.append(", traffickWayOther=");
			builder.append(traffickWayOther);
			builder.append(", tradePlace=");
			builder.append(tradePlace);
			builder.append(", tradePlaceZipCode=");
			builder.append(tradePlaceZipCode);
			builder.append(", tradePlaceAddr=");
			builder.append(tradePlaceAddr);
			builder.append(", tradeDate=");
			builder.append(tradeDate);
			builder.append(", BusinessName=");
			builder.append(BusinessName);
			builder.append(", manufactorName=");
			builder.append(manufactorName);
			builder.append(", manufactorID=");
			builder.append(manufactorID);
			builder.append(", manufactorArea=");
			builder.append(manufactorArea);
			builder.append(", manufactorZipCode=");
			builder.append(manufactorZipCode);
			builder.append(", manufactorAddr=");
			builder.append(manufactorAddr);
			builder.append(", manufactorTelArea=");
			builder.append(manufactorTelArea);
			builder.append(", manufactorTel=");
			builder.append(manufactorTel);
			builder.append(", manufactorTelExt=");
			builder.append(manufactorTelExt);
			builder.append(", manufactorNo=");
			builder.append(manufactorNo);
			builder.append(", expirationDate=");
			builder.append(expirationDate);
			builder.append(", isSampleYn=");
			builder.append(isSampleYn);
			builder.append(", evenContactYn=");
			builder.append(evenContactYn);
			builder.append(", dealWith=");
			builder.append(dealWith);
			builder.append(", isSimilarYn=");
			builder.append(isSimilarYn);
			builder.append(", isRecurrenceYn=");
			builder.append(isRecurrenceYn);
			builder.append(", isOtherDeptYn=");
			builder.append(isOtherDeptYn);
			builder.append(", otherDpetName=");
			builder.append(otherDpetName);
			builder.append(", isDamageYn=");
			builder.append(isDamageYn);
			builder.append(", otherInformation=");
			builder.append(otherInformation);
			builder.append(", otherExplain=");
			builder.append(otherExplain);
			builder.append(", caseOwner=");
			builder.append(caseOwner);
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
