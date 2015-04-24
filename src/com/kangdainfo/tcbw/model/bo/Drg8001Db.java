package com.kangdainfo.tcbw.model.bo;

import java.io.Serializable;
import java.util.Set;

public class Drg8001Db  implements Serializable 
{
	/** identifier field */
    private Long id;   
    private String applNo;
    private String notifierRepDate;
    private String chProduct;
    private String enProduct;
    private String permitKey;
    private String permitNo;
    private String ingredient;
    private String medModel;
    private String manufactorName;
    private String manufactorAddr;
    private String manufactorCountry;
    private String appID;
    private String appName;
    private String appAddr;
    private String orirecyclereason;
    private String danger;
    private String prerecycledate;
    private String isabroad;
    private String abroadCountry;
    private String abroadCountryOther;
    private String drugsupplier;
    private String precaution;
    private String lotnumStockcity;
    private String lotnumStockarea;
    private String lotnumStock;
    private String contactman;
    private String contacttel;
    private String postDate;
    private String postNo;
    private String recycleclass;
    private String msgsource;
    private String msgsourcedesc;
    private String cureapplno;
    private String quaapplno;
    private String recycledeadline;
    private String healthbureau1;
    private String healthbureautype1;
    private String healthbureau2;
    private String healthbureautype2;
    private String appRecDate;
    private String appRecMan;
    private String apprecyclestorage;
    private String apprecyclestoragedesc;
    private String appprepunishdate;
    private String appreplydate;
    private String appmedicineType;
    private String appproduceType;
    private String applotType;
    private String apprecyclereason;
    private String apprecyclersdesc;
    private String appsurvey;
    private String appsurveyOther;
    private String appprecaution;
    private String appprecautionOther;
    private String appsurveyresult;
    private String appsurveyreport;
    private String appprecautiontime;
    private String appcheckmanudate;
    private String appcheckmanuresult;
    private String checkpunishdate;
    private String checkcyclestorage;
    private String checkcyclestorageOther;
    private Long checktotalNum;
    private String checktotalUnit;
    private String checkhealthbureau;
    private String ischeckmatchnum;
    private String checknonmatchreason;
    private String checkmanudate;
    private String checkcontactman;
    private String checkcontacttel;
    private String checkUpdateYn; 
    private String status;
    private String receiveSystem;
    private String receiveDate;
    private String receiveTime;
    private String creator;
    private String createDate;
    private String createTime;
    private String modifier;
    private String modifyDate;
    private String modifyTime;
    private Set Drg8002Dbs;
    private Set Drg8003Dbs;
    private Set Drg8004Dbs;
    private Set Drg8005Dbs;
    
    /*
     *   轉檔,舊資料欄位 
     **/
    private String isTransferData;	   //是否為轉檔資料
    private String old_LotNumber;	   //批號
    private String old_startMonth;	   //回收月份
    private String old_appRecDateS;	   //啟動回收日期
    private String old_pubweb;	       //公布於網站
    private String old_issuePre;	   //發布新聞
    private String old_noticeGMP;	   //副知風管組
    private String old_healContact;	   //衛生局聯絡人
    private String old_isDoGMP;	       //是否請辦風管組
    private String old_recall;	       //廠商主動/被動回收
    private String old_recallNumber;   //回收啟動文號(FDA藥字)
    private String old_recalldeadline; //要求完成回收時間
    private String old_informMembers;  //國外衛生主管機關警訊發布情形

    
	/** default constructor */
    public Drg8001Db() {    
    
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
	
	public String getNotifierRepDate() {
		return notifierRepDate;
	}

	public void setNotifierRepDate(String notifierRepDate) {
		this.notifierRepDate = notifierRepDate;
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

	public String getIngredient() {
		return ingredient;
	}

	public void setIngredient(String ingredient) {
		this.ingredient = ingredient;
	}

	public String getMedModel() {
		return medModel;
	}

	public void setMedModel(String medModel) {
		this.medModel = medModel;
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

	public String getManufactorCountry() {
		return manufactorCountry;
	}

	public void setManufactorCountry(String manufactorCountry) {
		this.manufactorCountry = manufactorCountry;
	}

	public String getAppID() {
		return appID;
	}

	public void setAppID(String appID) {
		this.appID = appID;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getAppAddr() {
		return appAddr;
	}

	public void setAppAddr(String appAddr) {
		this.appAddr = appAddr;
	}

	public String getOrirecyclereason() {
		return orirecyclereason;
	}

	public void setOrirecyclereason(String orirecyclereason) {
		this.orirecyclereason = orirecyclereason;
	}

	public String getDanger() {
		return danger;
	}

	public void setDanger(String danger) {
		this.danger = danger;
	}

	public String getPrerecycledate() {
		return prerecycledate;
	}

	public void setPrerecycledate(String prerecycledate) {
		this.prerecycledate = prerecycledate;
	}

	public String getIsabroad() {
		return isabroad;
	}

	public void setIsabroad(String isabroad) {
		this.isabroad = isabroad;
	}

	public String getAbroadCountry() {
		return abroadCountry;
	}

	public void setAbroadCountry(String abroadCountry) {
		this.abroadCountry = abroadCountry;
	}

	public String getAbroadCountryOther() {
		return abroadCountryOther;
	}

	public void setAbroadCountryOther(String abroadCountryOther) {
		this.abroadCountryOther = abroadCountryOther;
	}

	public String getDrugsupplier() {
		return drugsupplier;
	}

	public void setDrugsupplier(String drugsupplier) {
		this.drugsupplier = drugsupplier;
	}

	public String getPrecaution() {
		return precaution;
	}

	public void setPrecaution(String precaution) {
		this.precaution = precaution;
	}

	public String getLotnumStockcity() {
		return lotnumStockcity;
	}

	public void setLotnumStockcity(String lotnumStockcity) {
		this.lotnumStockcity = lotnumStockcity;
	}

	public String getLotnumStockarea() {
		return lotnumStockarea;
	}

	public void setLotnumStockarea(String lotnumStockarea) {
		this.lotnumStockarea = lotnumStockarea;
	}

	public String getLotnumStock() {
		return lotnumStock;
	}

	public void setLotnumStock(String lotnumStock) {
		this.lotnumStock = lotnumStock;
	}

	public String getContactman() {
		return contactman;
	}

	public void setContactman(String contactman) {
		this.contactman = contactman;
	}

	public String getContacttel() {
		return contacttel;
	}

	public void setContacttel(String contacttel) {
		this.contacttel = contacttel;
	}

	public String getPostDate() {
		return postDate;
	}

	public void setPostDate(String postDate) {
		this.postDate = postDate;
	}

	public String getPostNo() {
		return postNo;
	}

	public void setPostNo(String postNo) {
		this.postNo = postNo;
	}

	public String getRecycleclass() {
		return recycleclass;
	}

	public void setRecycleclass(String recycleclass) {
		this.recycleclass = recycleclass;
	}

	public String getMsgsource() {
		return msgsource;
	}

	public void setMsgsource(String msgsource) {
		this.msgsource = msgsource;
	}

	public String getMsgsourcedesc() {
		return msgsourcedesc;
	}

	public void setMsgsourcedesc(String msgsourcedesc) {
		this.msgsourcedesc = msgsourcedesc;
	}

	public String getCureapplno() {
		return cureapplno;
	}

	public void setCureapplno(String cureapplno) {
		this.cureapplno = cureapplno;
	}

	public String getQuaapplno() {
		return quaapplno;
	}

	public void setQuaapplno(String quaapplno) {
		this.quaapplno = quaapplno;
	}

	public String getRecycledeadline() {
		return recycledeadline;
	}

	public void setRecycledeadline(String recycledeadline) {
		this.recycledeadline = recycledeadline;
	}

	public String getHealthbureau1() {
		return healthbureau1;
	}

	public void setHealthbureau1(String healthbureau1) {
		this.healthbureau1 = healthbureau1;
	}

	public String getHealthbureautype1() {
		return healthbureautype1;
	}

	public void setHealthbureautype1(String healthbureautype1) {
		this.healthbureautype1 = healthbureautype1;
	}

	public String getHealthbureau2() {
		return healthbureau2;
	}

	public void setHealthbureau2(String healthbureau2) {
		this.healthbureau2 = healthbureau2;
	}

	public String getHealthbureautype2() {
		return healthbureautype2;
	}

	public void setHealthbureautype2(String healthbureautype2) {
		this.healthbureautype2 = healthbureautype2;
	}

	public String getAppRecDate() {
		return appRecDate;
	}

	public void setAppRecDate(String appRecDate) {
		this.appRecDate = appRecDate;
	}

	public String getAppRecMan() {
		return appRecMan;
	}

	public void setAppRecMan(String appRecMan) {
		this.appRecMan = appRecMan;
	}

	public String getApprecyclestorage() {
		return apprecyclestorage;
	}

	public void setApprecyclestorage(String apprecyclestorage) {
		this.apprecyclestorage = apprecyclestorage;
	}

	public String getApprecyclestoragedesc() {
		return apprecyclestoragedesc;
	}

	public void setApprecyclestoragedesc(String apprecyclestoragedesc) {
		this.apprecyclestoragedesc = apprecyclestoragedesc;
	}

	public String getAppprepunishdate() {
		return appprepunishdate;
	}

	public void setAppprepunishdate(String appprepunishdate) {
		this.appprepunishdate = appprepunishdate;
	}

	public String getAppreplydate() {
		return appreplydate;
	}

	public void setAppreplydate(String appreplydate) {
		this.appreplydate = appreplydate;
	}

	public String getAppmedicineType() {
		return appmedicineType;
	}

	public void setAppmedicineType(String appmedicineType) {
		this.appmedicineType = appmedicineType;
	}

	public String getAppproduceType() {
		return appproduceType;
	}

	public void setAppproduceType(String appproduceType) {
		this.appproduceType = appproduceType;
	}

	public String getApplotType() {
		return applotType;
	}

	public void setApplotType(String applotType) {
		this.applotType = applotType;
	}

	public String getApprecyclereason() {
		return apprecyclereason;
	}

	public void setApprecyclereason(String apprecyclereason) {
		this.apprecyclereason = apprecyclereason;
	}

	public String getApprecyclersdesc() {
		return apprecyclersdesc;
	}

	public void setApprecyclersdesc(String apprecyclersdesc) {
		this.apprecyclersdesc = apprecyclersdesc;
	}

	public String getAppsurvey() {
		return appsurvey;
	}

	public void setAppsurvey(String appsurvey) {
		this.appsurvey = appsurvey;
	}

	public String getAppsurveyOther() {
		return appsurveyOther;
	}

	public void setAppsurveyOther(String appsurveyOther) {
		this.appsurveyOther = appsurveyOther;
	}

	public String getAppprecaution() {
		return appprecaution;
	}

	public void setAppprecaution(String appprecaution) {
		this.appprecaution = appprecaution;
	}

	public String getAppprecautionOther() {
		return appprecautionOther;
	}

	public void setAppprecautionOther(String appprecautionOther) {
		this.appprecautionOther = appprecautionOther;
	}

	public String getAppsurveyresult() {
		return appsurveyresult;
	}

	public void setAppsurveyresult(String appsurveyresult) {
		this.appsurveyresult = appsurveyresult;
	}

	public String getAppsurveyreport() {
		return appsurveyreport;
	}

	public void setAppsurveyreport(String appsurveyreport) {
		this.appsurveyreport = appsurveyreport;
	}

	public String getAppprecautiontime() {
		return appprecautiontime;
	}

	public void setAppprecautiontime(String appprecautiontime) {
		this.appprecautiontime = appprecautiontime;
	}

	public String getAppcheckmanudate() {
		return appcheckmanudate;
	}

	public void setAppcheckmanudate(String appcheckmanudate) {
		this.appcheckmanudate = appcheckmanudate;
	}

	public String getAppcheckmanuresult() {
		return appcheckmanuresult;
	}

	public void setAppcheckmanuresult(String appcheckmanuresult) {
		this.appcheckmanuresult = appcheckmanuresult;
	}

	public String getCheckpunishdate() {
		return checkpunishdate;
	}

	public void setCheckpunishdate(String checkpunishdate) {
		this.checkpunishdate = checkpunishdate;
	}

	public String getCheckcyclestorage() {
		return checkcyclestorage;
	}

	public void setCheckcyclestorage(String checkcyclestorage) {
		this.checkcyclestorage = checkcyclestorage;
	}

	public String getCheckcyclestorageOther() {
		return checkcyclestorageOther;
	}

	public void setCheckcyclestorageOther(String checkcyclestorageOther) {
		this.checkcyclestorageOther = checkcyclestorageOther;
	}

	public Long getChecktotalNum() {
		return checktotalNum;
	}

	public void setChecktotalNum(Long checktotalNum) {
		this.checktotalNum = checktotalNum;
	}

	public String getChecktotalUnit() {
		return checktotalUnit;
	}

	public void setChecktotalUnit(String checktotalUnit) {
		this.checktotalUnit = checktotalUnit;
	}

	public String getCheckhealthbureau() {
		return checkhealthbureau;
	}

	public void setCheckhealthbureau(String checkhealthbureau) {
		this.checkhealthbureau = checkhealthbureau;
	}

	public String getIscheckmatchnum() {
		return ischeckmatchnum;
	}

	public void setIscheckmatchnum(String ischeckmatchnum) {
		this.ischeckmatchnum = ischeckmatchnum;
	}

	public String getChecknonmatchreason() {
		return checknonmatchreason;
	}

	public void setChecknonmatchreason(String checknonmatchreason) {
		this.checknonmatchreason = checknonmatchreason;
	}

	public String getCheckmanudate() {
		return checkmanudate;
	}

	public void setCheckmanudate(String checkmanudate) {
		this.checkmanudate = checkmanudate;
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

	public String getCheckUpdateYn() {
		return checkUpdateYn;
	}

	public void setCheckUpdateYn(String checkUpdateYn) {
		this.checkUpdateYn = checkUpdateYn;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
	
	public Set getDrg8002Dbs() {
		return Drg8002Dbs;
	}
	public void setDrg8002Dbs(Set drg8002Dbs) {
		Drg8002Dbs = drg8002Dbs;
	}	
	
	public Set getDrg8003Dbs() {
		return Drg8003Dbs;
	}
	public void setDrg8003Dbs(Set drg8003Dbs) {
		Drg8003Dbs = drg8003Dbs;
	}	
	public Set getDrg8004Dbs() {
		return Drg8004Dbs;
	}
	public void setDrg8004Dbs(Set drg8004Dbs) {
		Drg8004Dbs = drg8004Dbs;
	}	
	public Set getDrg8005Dbs() {
		return Drg8005Dbs;
	}
	public void setDrg8005Dbs(Set drg8005Dbs) {
		Drg8005Dbs = drg8005Dbs;
	}	
	
	public String getIsTransferData() {
		return isTransferData;
	}
	public void setIsTransferData(String isTransferData) {
		this.isTransferData = isTransferData;
	}
	public String getOld_LotNumber() {
		return old_LotNumber;
	}
	public void setOld_LotNumber(String old_LotNumber) {
		this.old_LotNumber = old_LotNumber;
	}
	public String getOld_startMonth() {
		return old_startMonth;
	}
	public void setOld_startMonth(String old_startMonth) {
		this.old_startMonth = old_startMonth;
	}
	public String getOld_appRecDateS() {
		return old_appRecDateS;
	}
	public void setOld_appRecDateS(String old_appRecDateS) {
		this.old_appRecDateS = old_appRecDateS;
	}
	public String getOld_pubweb() {
		return old_pubweb;
	}
	public void setOld_pubweb(String old_pubweb) {
		this.old_pubweb = old_pubweb;
	}
	public String getOld_issuePre() {
		return old_issuePre;
	}
	public void setOld_issuePre(String old_issuePre) {
		this.old_issuePre = old_issuePre;
	}
	public String getOld_noticeGMP() {
		return old_noticeGMP;
	}
	public void setOld_noticeGMP(String old_noticeGMP) {
		this.old_noticeGMP = old_noticeGMP;
	}
	public String getOld_healContact() {
		return old_healContact;
	}
	public void setOld_healContact(String old_healContact) {
		this.old_healContact = old_healContact;
	}
	public String getOld_isDoGMP() {
		return old_isDoGMP;
	}
	public void setOld_isDoGMP(String old_isDoGMP) {
		this.old_isDoGMP = old_isDoGMP;
	}
	public String getOld_recall() {
		return old_recall;
	}
	public void setOld_recall(String old_recall) {
		this.old_recall = old_recall;
	}
	public String getOld_recallNumber() {
		return old_recallNumber;
	}
	public void setOld_recallNumber(String old_recallNumber) {
		this.old_recallNumber = old_recallNumber;
	}
	public String getOld_recalldeadline() {
		return old_recalldeadline;
	}
	public void setOld_recalldeadline(String old_recalldeadline) {
		this.old_recalldeadline = old_recalldeadline;
	}
	public String getOld_informMembers() {
		return old_informMembers;
	}
	public void setOld_informMembers(String old_informMembers) {
		this.old_informMembers = old_informMembers;
	}

	public String toString() 
	{
		StringBuilder builder = new StringBuilder();
		builder.append("Drg8001Db [id=");
		builder.append(id);			
		builder.append(", applNo=");
		builder.append(applNo);
		builder.append(", notifierRepDate=");
		builder.append(notifierRepDate);		
		builder.append(", chProduct=");
		builder.append(chProduct);
		builder.append(", enProduct=");
		builder.append(enProduct);
		builder.append(", permitKey=");
		builder.append(permitKey);
		builder.append(", permitNo=");
		builder.append(permitNo);
		builder.append(", ingredient=");
		builder.append(ingredient);
		builder.append(", medModel=");
		builder.append(medModel);
		builder.append(", manufactorName=");
		builder.append(manufactorName);
		builder.append(", manufactorAddr=");
		builder.append(manufactorAddr);
		builder.append(", manufactorCountry=");
		builder.append(manufactorCountry);
		builder.append(", appID=");
		builder.append(appID);
		builder.append(", appName=");
		builder.append(appName);
		builder.append(", appAddr=");
		builder.append(appAddr);
		builder.append(", orirecyclereason=");
		builder.append(orirecyclereason);
		builder.append(", danger=");
		builder.append(danger);
		builder.append(", prerecycledate=");
		builder.append(prerecycledate);
		builder.append(", isabroad=");
		builder.append(isabroad);
		builder.append(", abroadCountry=");
		builder.append(abroadCountry);
		builder.append(", abroadCountryOther=");
		builder.append(abroadCountryOther);
		builder.append(", drugsupplier=");
		builder.append(drugsupplier);
		builder.append(", precaution=");
		builder.append(precaution);
		builder.append(", lotnumStockcity=");
		builder.append(lotnumStockcity);
		builder.append(", lotnumStockarea=");
		builder.append(lotnumStockarea);
		builder.append(", lotnumStock=");
		builder.append(lotnumStock);
		builder.append(", contactman=");
		builder.append(contactman);
		builder.append(", contacttel=");
		builder.append(contacttel);	
		builder.append(", postDate=");
		builder.append(postDate);		
		builder.append(", postNo=");
		builder.append(postNo);
		builder.append(", recycleclass=");
		builder.append(recycleclass);
		builder.append(", msgsource=");
		builder.append(msgsource);
		builder.append(", msgsourcedesc=");
		builder.append(msgsourcedesc);
		builder.append(", cureapplno=");
		builder.append(cureapplno);
		builder.append(", quaapplno=");
		builder.append(quaapplno);
		builder.append(", recycledeadline=");
		builder.append(recycledeadline);		
		builder.append(", healthbureau1=");
		builder.append(healthbureau1);
		builder.append(", healthbureautype1=");
		builder.append(healthbureautype1);
		builder.append(", healthbureau2=");
		builder.append(healthbureau2);
		builder.append(", healthbureautype2=");
		builder.append(healthbureautype2);
		builder.append(", appRecDate=");
		builder.append(appRecDate);
		builder.append(", appRecMan=");
		builder.append(appRecMan);
		builder.append(", apprecyclestorage=");
		builder.append(apprecyclestorage);
		builder.append(", apprecyclestoragedesc=");
		builder.append(apprecyclestoragedesc);
		builder.append(", appprepunishdate=");
		builder.append(appprepunishdate);
		builder.append(", appreplydate=");
		builder.append(appreplydate);
		builder.append(", appmedicineType=");
		builder.append(appmedicineType);
		builder.append(", appproduceType=");
		builder.append(appproduceType);
		builder.append(", applotType=");
		builder.append(applotType);
		builder.append(", apprecyclereason=");
		builder.append(apprecyclereason);
		builder.append(", apprecyclersdesc=");
		builder.append(apprecyclersdesc);
		builder.append(", appsurvey=");
		builder.append(appsurvey);		
		builder.append(", appsurveyOther=");
		builder.append(appsurveyOther);
		builder.append(", appprecaution=");
		builder.append(appprecaution);
		builder.append(", appprecautionOther=");
		builder.append(appprecautionOther);
		builder.append(", appsurveyresult=");
		builder.append(appsurveyresult);
		builder.append(", appsurveyreport=");
		builder.append(appsurveyreport);
		builder.append(", appprecautiontime=");
		builder.append(appprecautiontime);
		builder.append(", appcheckmanudate=");
		builder.append(appcheckmanudate);
		builder.append(", appcheckmanuresult=");
		builder.append(appcheckmanuresult);
		builder.append(", checkpunishdate=");
		builder.append(checkpunishdate);
		builder.append(", checkcyclestorage=");
		builder.append(checkcyclestorage);
		builder.append(", checkcyclestorageOther=");
		builder.append(checkcyclestorageOther);
		builder.append(", checktotalNum=");
		builder.append(checktotalNum);
		builder.append(", checktotalUnit=");
		builder.append(checktotalUnit);
		builder.append(", checkhealthbureau=");
		builder.append(checkhealthbureau);
		builder.append(", ischeckmatchnum=");
		builder.append(ischeckmatchnum);		
		builder.append(", checknonmatchreason=");
		builder.append(checknonmatchreason);
		builder.append(", checkmanudate=");
		builder.append(checkmanudate);
		builder.append(", checkcontactman=");
		builder.append(checkcontactman);
		builder.append(", checkcontacttel=");
		builder.append(checkcontacttel);
		builder.append(", checkUpdateYn=");
		builder.append(checkUpdateYn);
		builder.append(", status=");
		builder.append(status);
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
		builder.append(", isTransferData=");
		builder.append(isTransferData);
		builder.append(", old_LotNumber=");
		builder.append(old_LotNumber);
		builder.append(", old_startMonth=");
		builder.append(old_startMonth);
		builder.append(", old_appRecDateS=");
		builder.append(old_appRecDateS);
		builder.append(", old_pubweb=");
		builder.append(old_pubweb);
		builder.append(", old_issuePre=");
		builder.append(old_issuePre);
		builder.append(", old_noticeGMP=");
		builder.append(old_noticeGMP);
		builder.append(", old_healContact=");
		builder.append(old_healContact);
		builder.append(", old_isDoGMP=");
		builder.append(old_isDoGMP);
		builder.append(", old_recall=");
		builder.append(old_recall);
		builder.append(", old_recallNumber=");
		builder.append(old_recallNumber);
		builder.append(", old_recalldeadline=");
		builder.append(old_recalldeadline);
		builder.append(", old_informMembers=");
		builder.append(old_informMembers);
		builder.append("]");
		return builder.toString();
	}
	
}
