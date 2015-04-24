package com.kangdainfo.tcbw.view.cosin;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.model.bo.CommonCode;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Con0001Db;
import com.kangdainfo.tcbw.model.bo.Cos0001Db;
import com.kangdainfo.tcbw.model.bo.Cos0002Db;
import com.kangdainfo.tcbw.model.bo.Cos0003Db;
import com.kangdainfo.tcbw.model.bo.Cos0004Db;
import com.kangdainfo.tcbw.model.bo.Cos0005Db;
import com.kangdainfo.tcbw.model.bo.Cos1001Db;
import com.kangdainfo.tcbw.util.TCBWCommon;

public class COSIN0400F extends SuperBean {
	
	private String id;
	private String relationId;
	private String applNo;
    private String status;
    private String statusCh;
    private String chargeMan;
    private String caseOwner;
    private String occurDate;	
    private String notifierRevDate;	
    private String notifierRepDate;	
    private String notifierSource;	
    private String notifierSourceOther;	
    private String notifierName;	
    private String notifierTitle;					
    private String notifierDeptID;
    private String notifierDept;					
    private String notifierTelArea;
    private String notifierTel;	
    private String notifierTelExt;
    private String notifierPhone;	
    private String notifierFax;	
    private String notifierArea;
    private String notifierZipCode;
    private String address;	
    private String notifierEamil;	
    private String notifierType; 					
    private String isContactYn;	
    private String cosType;	
    private String permitKey;	
    private String permitNo;	
    private String chProduct; 	
    private String enProduct;	
    private String caseNo;	
    private String ingredient;	
    private String traffickWay;	
    private String traffickWayOther;	
    private String tradePlace;	
    private String tradePlaceZipCode;
    private String tradePlaceAddr;
    private String tradeDate;	
    private String businessName;	
    private String manufactorID;
    private String manufactorName;	
    private String manufactorArea;	
    private String manufactorZipCode;
    private String manufactorAddr;	
    private String manufactorTelArea;
    private String manufactorTelExt;
    private String manufactorTel;	
    private String manufactorNo;	
    private String expirationDate;	
    private String isSampleYn;
    private String evenContactYn;	
    private String dealWith;	
    private String isSimilarYn;	
    private String isRecurrenceYn;	
    private String isOtherDeptYn;	
    private String otherDpetName;	
    private String isDamageYn;
    private String otherInformation;
    private String otherExplain;
    private String cos40001Id;
    private String isSend06Mail;						//是否有傳送過mail, 如果有則寫入Measure
    
    private String applno1;
    private String status1;
    
	public String getId() {		return checkGet(id);	}
	public void setId(String id) {		this.id = checkSet(id);	}
	public String getRelationId() {		return checkGet(relationId);	}
	public void setRelationId(String relationId) {		this.relationId = checkSet(relationId);	}
	public String getApplNo() {		return checkGet(applNo);	}
	public void setApplNo(String applNo) {		this.applNo = checkSet(applNo);	}
	public String getStatus() {		return checkGet(status);	}
	public void setStatus(String status) {		this.status = checkSet(status);	}
	public String getCaseOwner() {		return checkGet(caseOwner);	}
	public void setCaseOwner(String caseOwner) {		this.caseOwner = checkSet(caseOwner);	}
	public String getStatusCh() {		return checkGet(statusCh);	}
	public void setStatusCh(String statusCh) {		this.statusCh = checkSet(statusCh);	}
	public String getChargeMan() {		return checkGet(chargeMan);	}
	public void setChargeMan(String chargeMan) {		this.chargeMan = checkSet(chargeMan);	}
	public String getOccurDate() {		return checkGet(occurDate);	}
	public void setOccurDate(String occurDate) {		this.occurDate = checkSet(occurDate);	}
	public String getNotifierRevDate() {		return checkGet(notifierRevDate);	}
	public void setNotifierRevDate(String notifierRevDate) {		this.notifierRevDate = checkSet(notifierRevDate);	}
	public String getNotifierRepDate() {		return checkGet(notifierRepDate);	}
	public void setNotifierRepDate(String notifierRepDate) {		this.notifierRepDate = checkSet(notifierRepDate);	}
	public String getNotifierSource() {		return checkGet(notifierSource);	}
	public void setNotifierSource(String notifierSource) {		this.notifierSource = checkSet(notifierSource);	}
	public String getNotifierSourceOther() {		return checkGet(notifierSourceOther);	}
	public void setNotifierSourceOther(String notifierSourceOther) {		this.notifierSourceOther = checkSet(notifierSourceOther);	}
	public String getNotifierName() {		return checkGet(notifierName);	}
	public void setNotifierName(String notifierName) {		this.notifierName = checkSet(notifierName);	}
	public String getNotifierTitle() {		return checkGet(notifierTitle);	}
	public void setNotifierTitle(String notifierTitle) {		this.notifierTitle = checkSet(notifierTitle);	}
	public String getNotifierDept() {		return checkGet(notifierDept);	}
	public void setNotifierDept(String notifierDept) {		this.notifierDept = checkSet(notifierDept);	}
	public String getNotifierTel() {		return checkGet(notifierTel);	}
	public void setNotifierTel(String notifierTel) {		this.notifierTel = checkSet(notifierTel);	}
	public String getNotifierPhone() {		return checkGet(notifierPhone);	}
	public void setNotifierPhone(String notifierPhone) {		this.notifierPhone = checkSet(notifierPhone);	}
	public String getNotifierFax() {		return checkGet(notifierFax);	}
	public void setNotifierFax(String notifierFax) {		this.notifierFax = checkSet(notifierFax);	}
	public String getAddress() {		return checkGet(address);	}
	public void setAddress(String address) {		this.address = checkSet(address);	}
	public String getNotifierEamil() {		return checkGet(notifierEamil);	}
	public void setNotifierEamil(String notifierEamil) {		this.notifierEamil = checkSet(notifierEamil);	}
	public String getNotifierType() {		return checkGet(notifierType);	}
	public void setNotifierType(String notifierType) {		this.notifierType = checkSet(notifierType);	}
	public String getIsContactYn() {		return checkGet(isContactYn);	}
	public void setIsContactYn(String isContactYn) {		this.isContactYn = checkSet(isContactYn);	}
	public String getCosType() {		return checkGet(cosType);	}
	public void setCosType(String cosType) {		this.cosType = checkSet(cosType);	}
	public String getPermitKey() {		return checkGet(permitKey);	}
	public void setPermitKey(String permitKey) {		this.permitKey = checkSet(permitKey);	}
	public String getPermitNo() {		return checkGet(permitNo);	}
	public void setPermitNo(String permitNo) {		this.permitNo = checkSet(permitNo);	}
	public String getChProduct() {		return checkGet(chProduct);	}
	public void setChProduct(String chProduct) {		this.chProduct = checkSet(chProduct);	}
	public String getEnProduct() {		return get(enProduct);	}
	public void setEnProduct(String enProduct) {		this.enProduct = checkSet(enProduct);	}
	public String getCaseNo() {		return checkGet(caseNo);	}
	public void setCaseNo(String caseNo) {		this.caseNo = checkSet(caseNo);	}
	public String getIngredient() {		return checkGet(ingredient);	}
	public void setIngredient(String ingredient) {		this.ingredient = checkSet(ingredient);	}
	public String getTraffickWay() {		return checkGet(traffickWay);	}
	public void setTraffickWay(String traffickWay) {		this.traffickWay = checkSet(traffickWay);	}
	public String getTraffickWayOther() {		return checkGet(traffickWayOther);	}
	public void setTraffickWayOther(String traffickWayOther) {		this.traffickWayOther = checkSet(traffickWayOther);	}
	public String getTradePlace() {		return checkGet(tradePlace);	}
	public void setTradePlace(String tradePlace) {		this.tradePlace = checkSet(tradePlace);	}
	public String getTradePlaceZipCode() {		return checkGet(tradePlaceZipCode);	}
	public void setTradePlaceZipCode(String tradePlaceZipCode) {		this.tradePlaceZipCode = checkSet(tradePlaceZipCode);	}
	public String getTradePlaceAddr() {		return checkGet(tradePlaceAddr);	}
	public void setTradePlaceAddr(String tradePlaceAddr) {		this.tradePlaceAddr = checkSet(tradePlaceAddr);	}
	public String getTradeDate() {		return checkGet(tradeDate);	}
	public void setTradeDate(String tradeDate) {		this.tradeDate = checkSet(tradeDate);	}
	public String getBusinessName() {		return checkGet(businessName);	}
	public void setBusinessName(String businessName) {		this.businessName = checkSet(businessName);	}
	public String getManufactorName() {		return checkGet(manufactorName);	}
	public void setManufactorName(String manufactorName) {		this.manufactorName = checkSet(manufactorName);	}
	public String getManufactorAddr() {		return checkGet(manufactorAddr);	}
	public void setManufactorAddr(String manufactorAddr) {		this.manufactorAddr = checkSet(manufactorAddr);	}
	public String getManufactorTel() {		return checkGet(manufactorTel);	}
	public void setManufactorTel(String manufactorTel) {		this.manufactorTel = checkSet(manufactorTel);	}
	public String getManufactorNo() {		return checkGet(manufactorNo);	}
	public void setManufactorNo(String manufactorNo) {		this.manufactorNo = checkSet(manufactorNo);	}
	public String getExpirationDate() {	return checkGet(expirationDate);	}
	public void setExpirationDate(String expirationDate) {		this.expirationDate = checkSet(expirationDate);	}
	public String getIsSampleYn() {		return checkGet(isSampleYn);	}
	public void setIsSampleYn(String isSampleYn) {		this.isSampleYn = checkSet(isSampleYn);	}
	public String getEvenContactYn() {		return checkGet(evenContactYn);	}
	public void setEvenContactYn(String evenContactYn) {		this.evenContactYn = checkSet(evenContactYn);	}
	public String getDealWith() {		return checkGet(dealWith);	}
	public void setDealWith(String dealWith) {		this.dealWith = checkSet(dealWith);	}
	public String getIsSimilarYn() {		return checkGet(isSimilarYn);	}
	public void setIsSimilarYn(String isSimilarYn) {		this.isSimilarYn = checkSet(isSimilarYn);	}
	public String getIsRecurrenceYn() {		return checkGet(isRecurrenceYn);	}
	public void setIsRecurrenceYn(String isRecurrenceYn) {		this.isRecurrenceYn = checkSet(isRecurrenceYn);	}
	public String getIsOtherDeptYn() {	return checkGet(isOtherDeptYn);	}
	public void setIsOtherDeptYn(String isOtherDeptYn) {		this.isOtherDeptYn = checkSet(isOtherDeptYn);	}
	public String getOtherDpetName() {		return checkGet(otherDpetName);	}
	public void setOtherDpetName(String otherDpetName) {		this.otherDpetName = checkSet(otherDpetName);	}
	public String getIsDamageYn() {		return checkGet(isDamageYn);	}
	public void setIsDamageYn(String isDamageYn) {		this.isDamageYn = checkSet(isDamageYn);	}
	public String getOtherInformation() {		return checkGet(otherInformation);	}
	public void setOtherInformation(String otherInformation) {		this.otherInformation = checkSet(otherInformation);	}
	public String getNotifierDeptID() {		return checkGet(notifierDeptID);	}
	public void setNotifierDeptID(String notifierDeptID) {		this.notifierDeptID = checkSet(notifierDeptID);	}
	public String getNotifierTelArea() {	return checkGet(notifierTelArea);	}
	public void setNotifierTelArea(String notifierTelArea) {		this.notifierTelArea = checkSet(notifierTelArea);	}
	public String getNotifierTelExt() {		return checkGet(notifierTelExt);	}
	public void setNotifierTelExt(String notifierTelExt) {		this.notifierTelExt = checkSet(notifierTelExt);	}
	public String getNotifierArea() {		return checkGet(notifierArea);	}
	public void setNotifierArea(String notifierArea) {		this.notifierArea = checkSet(notifierArea);	}
	public String getNotifierZipCode() {		return checkGet(notifierZipCode);	}
	public void setNotifierZipCode(String notifierZipCode) {		this.notifierZipCode = checkSet(notifierZipCode);	}
	public String getManufactorID() {		return checkGet(manufactorID);	}
	public void setManufactorID(String manufactorID) {		this.manufactorID = checkSet(manufactorID);	}
	public String getManufactorArea() {		return checkGet(manufactorArea);	}
	public void setManufactorArea(String manufactorArea) {		this.manufactorArea = checkSet(manufactorArea);	}
	public String getManufactorZipCode() {		return checkGet(manufactorZipCode);	}
	public void setManufactorZipCode(String manufactorZipCode) {		this.manufactorZipCode = checkSet(manufactorZipCode);	}
	public String getManufactorTelArea() {		return checkGet(manufactorTelArea);	}
	public void setManufactorTelArea(String manufactorTelArea) {		this.manufactorTelArea = checkSet(manufactorTelArea);	}
	public String getManufactorTelExt() {		return checkGet(manufactorTelExt);	}
	public void setManufactorTelExt(String manufactorTelExt) {		this.manufactorTelExt = checkSet(manufactorTelExt);	}
	public String getOtherExplain() {		return checkGet(otherExplain);	}
	public void setOtherExplain(String otherExplain) {		this.otherExplain = checkSet(otherExplain);	}
	public String getCos40001Id() {return checkGet(cos40001Id);}
	public void setCos40001Id(String cos40001Id) {this.cos40001Id = checkSet(cos40001Id);}
	public String getIsSend06Mail() {return checkGet(isSend06Mail);}
	public void setIsSend06Mail(String isSend06Mail) {this.isSend06Mail = checkSet(isSend06Mail);}
	public String getApplno1() {return checkGet(applno1);}
	public void setApplno1(String applno1) {this.applno1 = checkSet(applno1);}
	public String getStatus1() {return checkGet(status1);}
	public void setStatus1(String status1) {this.status1 = checkSet(status1);}
	
	// FOR 純粹畫面顯示不良類別用
	private String[] showCosType;
	
	public String[] getShowCosType() {		return checkGet(showCosType);	}
	public void setShowCosType(String[] showCosType) {		this.showCosType = checkSet(showCosType);	}

	// COS0002_DB
	private String adverseCondition;	
    private String nonSeriousOther;
    private String nonSeriousAR;
    private String nonSeriousAROther;
    private String nonSeriousDis;
    private String useDateS;	
    private String useDateE;	
    private String useRate;	
    private String useMethod;	
    private String isMitigateYn;	
    private String isRecurYn;	
    private String diagnosisProof;	 
    private String diagnosisReport;	 
    private String diagnosisOther;	 
    
	public String getAdverseCondition() {		return checkGet(adverseCondition);	}
	public void setAdverseCondition(String adverseCondition) {		this.adverseCondition = checkSet(adverseCondition);	}
	public String getNonSeriousOther() {		return checkGet(nonSeriousOther);	}
	public void setNonSeriousOther(String nonSeriousOther) {		this.nonSeriousOther = checkSet(nonSeriousOther);	}
	public String getNonSeriousAR() {		return checkGet(nonSeriousAR);	}
	public void setNonSeriousAR(String nonSeriousAR) {		this.nonSeriousAR = checkSet(nonSeriousAR);}
	public String getNonSeriousAROther() {		return checkGet(nonSeriousAROther);	}
	public void setNonSeriousAROther(String nonSeriousAROther) {		this.nonSeriousAROther = checkSet(nonSeriousAROther);	}
	public String getNonSeriousDis() {		return checkGet(nonSeriousDis);	}
	public void setNonSeriousDis(String nonSeriousDis) {		this.nonSeriousDis = checkSet(nonSeriousDis);	}
	public String getUseDateS() {		return checkGet(useDateS);	}
	public void setUseDateS(String useDateS) {		this.useDateS = checkSet(useDateS);	}
	public String getUseDateE() {		return checkGet(useDateE);	}
	public void setUseDateE(String useDateE) {		this.useDateE = checkSet(useDateE);	}
	public String getUseRate() {		return checkGet(useRate);	}
	public void setUseRate(String useRate) {		this.useRate = checkSet(useRate);	}
	public String getUseMethod() {		return checkGet(useMethod);	}
	public void setUseMethod(String useMethod) {		this.useMethod = checkSet(useMethod);	}
	public String getIsMitigateYn() {		return checkGet(isMitigateYn);	}
	public void setIsMitigateYn(String isMitigateYn) {		this.isMitigateYn = checkSet(isMitigateYn);	}
	public String getIsRecurYn() {		return checkGet(isRecurYn);	}
	public void setIsRecurYn(String isRecurYn) {		this.isRecurYn = checkSet(isRecurYn);	}
	public String getDiagnosisProof() {		return checkGet(diagnosisProof);	}
	public void setDiagnosisProof(String diagnosisProof) {		this.diagnosisProof = checkSet(diagnosisProof);	}
	public String getDiagnosisReport() {		return checkGet(diagnosisReport);	}
	public void setDiagnosisReport(String diagnosisReport) {		this.diagnosisReport = checkSet(diagnosisReport);	}
	public String getDiagnosisOther() {		return checkGet(diagnosisOther);	}
	public void setDiagnosisOther(String diagnosisOther) {		this.diagnosisOther = checkSet(diagnosisOther);	} 
	
	// COS0003_DB
	private String[] subCode;
	private String[] otherDescribe;
	
	public String[] getSubCode() {		return checkGet(subCode);	}
	public void setSubCode(String[] subCode) {		this.subCode = checkSet(subCode);	}
	public String[] getOtherDescribe() {		return checkGet(otherDescribe);	}
	public void setOtherDescribe(String[] otherDescribe) {		this.otherDescribe = checkSet(otherDescribe);	}

	private String[] CFileRow;
	private String[] COSSDFileRow;
	private String[] COSDPFileRow;
	private String[] COSIDFileRow;
	private String[] COS0004DbRow;
	private String[] COS0005DbRow;
	
	public String[] getCFileRow() {		return checkGet(CFileRow);	}
	public void setCFileRow(String[] cFileRow) {		CFileRow = checkSet(cFileRow);	}
	public String[] getCOSSDFileRow() {		return checkGet(COSSDFileRow);	}
	public void setCOSSDFileRow(String[] cOSSDFileRow) {		COSSDFileRow = checkSet(cOSSDFileRow);	}
	public String[] getCOSDPFileRow() {		return checkGet(COSDPFileRow);	}
	public void setCOSDPFileRow(String[] cOSDPFileRow) {		COSDPFileRow = checkSet(cOSDPFileRow);	}
	public String[] getCOSIDFileRow() {return checkGet(COSIDFileRow);}
	public void setCOSIDFileRow(String[] cOSIDFileRow) {COSIDFileRow = checkSet(cOSIDFileRow);}
	public String[] getCOS0004DbRow() {		return checkGet(COS0004DbRow);	}
	public void setCOS0004DbRow(String[] cOS0004DbRow) {		COS0004DbRow = checkSet(cOS0004DbRow);	}
	public String[] getCOS0005DbRow() {		return checkGet(COS0005DbRow);	}
	public void setCOS0005DbRow(String[] cOS0005DbRow) {		COS0005DbRow = checkSet(cOS0005DbRow);	}

	private String CFileRowSBuilder;
	private String COSDPFileRowSBuilder;
	private String COSSDFileRowSBuilder;
	private String COSIDFileRowSBuilder;
	private String COS0004DbRowSBuilder;
	private String COS0005DbRowSBuilder;
	
	public String getCFileRowSBuilder() {		return get(CFileRowSBuilder);	}
	public void setCFileRowSBuilder(String cFileRowSBuilder) {		CFileRowSBuilder = checkSet(cFileRowSBuilder);	}
	public String getCOSDPFileRowSBuilder() {		return get(COSDPFileRowSBuilder);	}
	public void setCOSDPFileRowSBuilder(String cOSDPFileRowSBuilder) {		COSDPFileRowSBuilder = checkSet(cOSDPFileRowSBuilder);	}
	public String getCOSSDFileRowSBuilder() {		return get(COSSDFileRowSBuilder);	}
	public void setCOSSDFileRowSBuilder(String cOSSDFileRowSBuilder) {		COSSDFileRowSBuilder = checkSet(cOSSDFileRowSBuilder);	}
	public String getCOSIDFileRowSBuilder() {		return get(COSIDFileRowSBuilder);	}
	public void setCOSIDFileRowSBuilder(String cOSIDFileRowSBuilder) {		COSIDFileRowSBuilder = checkSet(cOSIDFileRowSBuilder);	}
	public String getCOS0004DbRowSBuilder() {		return get(COS0004DbRowSBuilder);	}
	public void setCOS0004DbRowSBuilder(String cOS0004DbRowSBuilder) {		COS0004DbRowSBuilder = checkSet(cOS0004DbRowSBuilder);	}
	public String getCOS0005DbRowSBuilder() {		return get(COS0005DbRowSBuilder);	}
	public void setCOS0005DbRowSBuilder(String cOS0005DbRowSBuilder) {		COS0005DbRowSBuilder = checkSet(cOS0005DbRowSBuilder);	}

	public final String[] arrCFileFieldName = {"CFileName", "CFileNameRoute", "CFileExplan"};
	public final String[] arrCOSDPFileFieldName = {"COSDPFileName", "COSDPFileNameRoute", "COSDPFileExplan"};
	public final String[] arrCOSSDFileFieldName = {"COSSDFileName", "COSSDFileNameRoute", "COSSDFileExplan"};
	public final String[] arrCOSIDFileFieldName = {"COSIDFileName", "COSIDFileNameRoute", "COSIDFileExplan"};
	public final String[] arrCOS0004DbFieldName = {"testDate","testItems","testNum"};
	public final String[] arrCOS0005DbFieldName = {"cName","manufactorName","useDateS","useDateE","useRate","useMethod","manufactorNo","expirationDate","tradeDate"};
	
	private String isNeedBackQuery;
	private javax.servlet.ServletRequest httpRequest;

	public String getIsNeedBackQuery() {		return checkGet(isNeedBackQuery);	}
	public void setIsNeedBackQuery(String isNeedBackQuery) {		this.isNeedBackQuery = checkSet(isNeedBackQuery);	}
	public javax.servlet.ServletRequest getHttpRequest() {	return httpRequest;	}
	public void setHttpRequest(javax.servlet.ServletRequest r) {	this.httpRequest = r;	}
	
	public String getCommonUserOption(String selectedOne){
		return View.getOption(" select userId, userName from CommonUser where inORout = 'in' order by userId", selectedOne, false, 1);
	}
	
	public String genFileRowItemSet(java.util.List<Con0001Db> dtlList, String fileType) throws Exception{
		if(dtlList!=null && dtlList.size()>0){
	    	StringBuilder sb = new StringBuilder(1024);        	
	    	for(Con0001Db dtl : dtlList){
	    		sb.append("addFile").append(fileType).append("('tabFile" + fileType + "'");
	    		sb.append(",").append(Common.sqlChar(dtl.getFileName()));
	    		sb.append(",").append(Common.sqlChar(dtl.getFileRoute()));
	    		sb.append(",").append(Common.sqlChar(dtl.getFileExplan()));
				sb.append(",'").append(dtl.getId()!=null?dtl.getId():"").append("'");
				sb.append(");\n");
	    	}
	    	if("C".equals(fileType)){
	    		this.setCFileRowSBuilder(sb.toString());
	    	}else if("COSSD".equals(fileType)){
	    		this.setCOSSDFileRowSBuilder(sb.toString());
	    	}else if("COSDP".equals(fileType)){
	    		this.setCOSDPFileRowSBuilder(sb.toString());
	    	}else if("COSID".equals(fileType)){
	    		this.setCOSIDFileRowSBuilder(sb.toString());
	    	}
	    }
		return "";
	}
	
	public String getCACRadioBoxOption(String className, String checkBoxFieldName, String checkedOne, String appendName, int under){
    	StringBuilder sb = new StringBuilder();    	
    	java.util.List dtlList = ServiceGetter.getInstance().getTcbwService().load("select codeId, codeName from CommonCode where commonCodeKind.codeKindId = 'CAC' and isStop = 'N' order by codeId ");
    	if(dtlList!=null && dtlList.size()>0){
    		sb.append(appendName);
    		for(Object dtlObj : dtlList){
    			Object[] dtl = (Object[])dtlObj;
    			
    			sb.append("<input class=\"").append(className).append("\" type=\"radio\" name=\"").append(checkBoxFieldName).append("\" value=\"").append(Common.get(dtl[0])).append("\"");
    			if(Common.get(dtl[0]).equals(checkedOne)){
    				sb.append(" checked");
    			}
    			sb.append(">").append(Common.get(dtl[1])).append(" ");
    			
    			if("99".equals(Common.get(dtl[0]))){
    				
    			}else{
    				if(under <= Common.getInt(dtl[0])){
        				sb.append("<br>");
        			}
    			}
    		}
    	}
    	return sb.toString();    	
    }  
	
	public String genCos0004DbItemSet(java.util.List<Cos0004Db> dtlList) throws Exception{
		if(dtlList!=null && dtlList.size()>0){
			StringBuilder sb = new StringBuilder(1024);
			for(Cos0004Db dtl : dtlList){
				sb.append("addCOS0004DbRow('tabCOS0004Db'");
				sb.append(",").append(Common.sqlChar(dtl.getTestDate()));
				sb.append(",").append(Common.sqlChar(dtl.getTestItems()));
				sb.append(",").append(Common.sqlChar(dtl.getTestNum()));
				sb.append(",'").append(dtl.getId()!=null?dtl.getId():"").append("'");
				sb.append(");\n");
			}
			this.setCOS0004DbRowSBuilder(sb.toString());
		}
		return "";
	}
	
	public String genCos0005DbItemSet(java.util.List<Cos0005Db> dtlList) throws Exception{
		if(dtlList!=null && dtlList.size()>0){
			StringBuilder sb = new StringBuilder(1024);
			for(Cos0005Db dtl : dtlList){
				sb.append("addCOS0005DbRow('tabCOS0005Db'");
				sb.append(",").append(Common.sqlChar(dtl.getcName()));
				sb.append(",").append(Common.sqlChar(dtl.getManufactorName()));
				sb.append(",").append(Common.sqlChar(dtl.getUseDateS()));
				sb.append(",").append(Common.sqlChar(dtl.getUseDateE()));
				sb.append(",").append(Common.sqlChar(dtl.getUseRate()));
				sb.append(",").append(Common.sqlChar(dtl.getUseMethod()));
				sb.append(",").append(Common.sqlChar(dtl.getManufactorNo()));
				sb.append(",").append(Common.sqlChar(dtl.getExpirationDate()));
				sb.append(",").append(Common.sqlChar(dtl.getTradeDate()));
				sb.append(",'").append(dtl.getId()!=null?dtl.getId():"").append("'");
				sb.append(");\n");
			}
			this.setCOS0005DbRowSBuilder(sb.toString());
		}
		return "";
	}
	
	public String getCOS0003DbCheckBoxOption(String className, String checkBoxFieldName, String otherFieldName, String[] checkedOne, String id, boolean isNeedShowOthers) throws Exception{
		StringBuilder sb = new StringBuilder();
		
		java.util.List<CommonCode> commonCodeList = ServiceGetter.getInstance().getCommonService().getCommonCodeDao().getCommonCodeList("COSDPD");
		if(commonCodeList!=null && commonCodeList.size()>0){
			java.util.Map<String, String> selectOneMap = new java.util.HashMap<String, String>();
			if(checkedOne!=null && checkedOne.length>0){
				for(String rid : checkedOne){
					selectOneMap.put(Common.get(rid), "Y");
				}
			}
			for(CommonCode dtl : commonCodeList){
				sb.append(Common.get(dtl.getCodeName())).append(" : ");
				
				boolean hasOthers = false;
				String othersValue = "";
				java.util.List<Cos1001Db> cosList = ServiceGetter.getInstance().getTcbwService().load(" from Cos1001Db where cdpCode = " + Common.sqlChar(dtl.getCodeId()) + " and isStop = 'N' order by dpdKind ");
				if(cosList!=null && cosList.size()>0){
					for(Cos1001Db cos : cosList){
						boolean isChecked = Common.get(selectOneMap.get(Common.get(cos.getDpdKind()))).equals("")?false:true;
						sb.append("<input class='" + className + "'").append(" type='checkbox' name='" + checkBoxFieldName + "' value='" + cos.getDpdKind() + "' ");
						sb.append( isChecked?"checked":"" );
						if(Common.get(cos.getDpdKind()).indexOf("99") != -1){
							sb.append(" onclick='if(this.checked){$(\"span[id=otherSpan]\").show();}else{$(\"span[id=otherSpan]\").hide();}'");
						}
						sb.append(">");
						sb.append(Common.get(cos.getDpdKindName()));
						
						if(!isNeedShowOthers){
							continue;
						}
						if(Common.get(cos.getDpdKind()).indexOf("99") != -1){
							hasOthers = true;
							othersValue = View.getLookupField(" select otherDescribe from Cos0003Db where cos0001Db.id = " + Common.getLong(id) + 
															  " and mainCode = " + Common.sqlChar(cos.getCdpCode()));
							sb.append("<span id='otherSpan' style='display:"+(isChecked?";":"none;")+"'>");
							sb.append("，請描述 : ")
							  .append("<input class='" + className + "'").append(" type='text' name='" + otherFieldName + "' size='30' maxlength='100'")
							  .append(" value='" + othersValue +"'>");
							sb.append("</span>");
						}
					}
					cosList.clear();
				}
				if(!hasOthers && isNeedShowOthers){
					sb.append("<input type='hidden' name='" + otherFieldName + "'>");
				}
				sb.append("<br>");
			}
			selectOneMap.clear();
		}
		return sb.toString();
	}
	
	public void setBaseData(COSIN0400F obj, Cos0001Db c) throws Exception {
		
		Cos0001Db c1 = (Cos0001Db)View.getObject(" from Cos0001Db where foreignApplNo = " + Common.sqlChar(c.getApplNo()));
		java.util.Map<String, String> status2Map = TCBWCommon.getCommonCodeMap("CCS");
		if(null!=c1) {
			obj.setRelationId(Common.get(c1.getId()));
			obj.setApplno1(c1.getApplNo());//關聯案件編號
			obj.setStatus1(status2Map.get(c1.getStatus()));
		}
		
		obj.setStatusCh(status2Map.get(c.getStatus()));//案件狀態轉中文
		
		obj.setId(Common.get(c.getId()));
		obj.setCos40001Id(Common.get(c.getCos4001DbId()));
		obj.setCaseOwner("");
		obj.setApplNo(c.getApplNo());
		obj.setStatus(c.getStatus());
		obj.setCosType(c.getCosType());
		obj.setChargeMan(c.getChargeMan());
		obj.setOccurDate(c.getOccurDate());
		obj.setNotifierRevDate(c.getNotifierRevDate());
		obj.setNotifierRepDate(c.getNotifierRepDate());
		obj.setNotifierSource(c.getNotifierSource());
		obj.setNotifierSourceOther(c.getNotifierSourceOther());
		obj.setNotifierName(c.getNotifierName());
		obj.setNotifierTelArea(c.getNotifierTelArea());
		obj.setNotifierTel(c.getNotifierTel());
		obj.setNotifierTelExt(c.getNotifierTelExt());
		obj.setNotifierEamil(c.getNotifierEamil());
		obj.setNotifierTitle(c.getNotifierTitle());
		obj.setNotifierArea(c.getNotifierArea());
		obj.setNotifierZipCode(c.getNotifierZipCode());
		obj.setAddress(c.getAddress());
		obj.setOccurDate(c.getOccurDate());
		obj.setManufactorArea(c.getManufactorArea());
		obj.setManufactorZipCode(c.getManufactorZipCode());
		obj.setNotifierType(c.getNotifierType());
		obj.setNotifierDeptID(c.getNotifierDeptId());
		obj.setNotifierDept(View.getNotifierDeptName(obj.getNotifierDeptID(), obj.getNotifierType()));
		
		if("3".equals(Common.get(c.getCosType()))){
			obj.setShowCosType(new String[]{"1","2"});
		}else{
			obj.setShowCosType(new String[]{Common.get(c.getCosType())});
		}
		obj.setIsContactYn(c.getIsContactYn());
		
		obj.setChProduct(c.getChProduct());
		obj.setEnProduct(c.getEnProduct());
		obj.setPermitKey(c.getPermitKey());
		obj.setPermitNo(c.getPermitNo());
		obj.setTraffickWay(c.getTraffickWay());
		obj.setTraffickWayOther(c.getTraffickWayOther());
		obj.setTradePlace(c.getTradePlace());
		obj.setTradePlaceZipCode(c.getTradePlaceZipCode());
		obj.setTradePlaceAddr(c.getTradePlaceAddr());
		obj.setBusinessName(c.getBusinessName());
		obj.setManufactorName(c.getManufactorName());
		obj.setManufactorTelArea(c.getManufactorTelArea());
		obj.setManufactorTel(c.getManufactorTel());
		obj.setManufactorTelExt(c.getManufactorTelExt());
		obj.setManufactorAddr(c.getManufactorAddr());
		obj.setManufactorNo(c.getManufactorNo());
		obj.setManufactorID(c.getManufactorID());
		obj.setExpirationDate(c.getExpirationDate());
		obj.setTradeDate(c.getTradeDate());
		obj.setIsSampleYn(c.getIsSampleYn());
		obj.setEvenContactYn(c.getEvenContactYn());
		obj.setDealWith(c.getDealWith());
		obj.setIsSimilarYn(c.getIsSimilarYn());
		obj.setIsRecurrenceYn(c.getIsRecurrenceYn());
		obj.setIsOtherDeptYn(c.getIsOtherDeptYn());
		obj.setOtherDpetName(c.getOtherDpetName());
		obj.setCaseNo(c.getCaseNo());
		obj.setIngredient(c.getIngredient());
		obj.setIsSend06Mail(c.getIsSend06Mail());
		
		if("1".equals(Common.get(c.getCosType())) || "3".equals(Common.get(c.getCosType()))){
			obj.setIsDamageYn(c.getIsDamageYn());
			obj.setOtherInformation(c.getOtherInformation());
			obj.setOtherExplain(c.getOtherExplain());
			if(c.getCos0003Dbs()!=null && c.getCos0003Dbs().size()>0){
				java.util.List<String> subCodeList = new java.util.ArrayList<String>(); 
				for(Object dtlObj : c.getCos0003Dbs()){
					Cos0003Db dtl = (Cos0003Db)dtlObj;
					
					String[] tmp = Common.get(dtl.getSubCode()).split(",");
					if(tmp!=null && tmp.length>0){
						for(String codeId : tmp){
							subCodeList.add(codeId);
						}
					}
				}
				if(subCodeList.size() > 0){
					String[] codeArray = new String[subCodeList.size()];
					for(int i=0; i<subCodeList.size(); i++){
						codeArray[i] = Common.get(subCodeList.get(i)); 
					}
					subCodeList.clear();
					
					obj.setSubCode(codeArray);
				}
			}
		}
		if("2".equals(Common.get(c.getCosType())) || "3".equals(Common.get(c.getCosType()))){
			if(c.getCos0002Dbs()!=null && c.getCos0002Dbs().size()>0){
				boolean flag = true;
				for(Object dtlObj : c.getCos0002Dbs()){
					if(flag){
						Cos0002Db dtl = (Cos0002Db)dtlObj;
						obj.setAdverseCondition(dtl.getAdverseCondition());
						obj.setNonSeriousOther(dtl.getNonSeriousOther());
						obj.setNonSeriousAR(dtl.getNonSeriousAR());
						obj.setNonSeriousAROther(dtl.getNonSeriousAROther());
						obj.setNonSeriousDis(dtl.getNonSeriousDis());
						obj.setUseDateS(dtl.getUseDateS());
						obj.setUseDateE(dtl.getUseDateE());
						obj.setUseMethod(dtl.getUseMethod());
						obj.setUseRate(dtl.getUseRate());
						obj.setIsMitigateYn(dtl.getIsMitigateYn());
						obj.setIsRecurYn(dtl.getIsRecurYn());
						obj.setDiagnosisProof(dtl.getDiagnosisProof());
						obj.setDiagnosisReport(dtl.getDiagnosisReport());
						obj.setDiagnosisOther(dtl.getDiagnosisOther());
						
						java.util.List<Cos0004Db> cos04List = ServiceGetter.getInstance().getTcbwService().load(" from Cos0004Db where cos0002Db.id = " + dtl.getId());
						this.genCos0004DbItemSet(cos04List);
						
						java.util.List<Cos0005Db> cos05List = ServiceGetter.getInstance().getTcbwService().load(" from Cos0005Db where cos0002Db.id = " + dtl.getId());
						this.genCos0005DbItemSet(cos05List);
						
						java.util.List<Con0001Db> COSSDFileList = ServiceGetter.getInstance().getTcbwService().load(" from Con0001Db where fileKind = 'COSSD' and dbName = 'COS0002DB' and upLoadId = " + dtl.getId());
						this.genFileRowItemSet(COSSDFileList, "COSSD");
						
						java.util.List<Con0001Db> COSDPFileList = ServiceGetter.getInstance().getTcbwService().load(" from Con0001Db where fileKind = 'COSDP' and dbName = 'COS0002DB' and upLoadId = " + dtl.getId());
						this.genFileRowItemSet(COSDPFileList, "COSDP");
						
						java.util.List<Con0001Db> COSIDFileList = ServiceGetter.getInstance().getTcbwService().load(" from Con0001Db where fileKind = 'COSID' and dbName = 'COS0002DB' and upLoadId = " + dtl.getId());
						this.genFileRowItemSet(COSIDFileList, "COSID");
						
						if(cos04List!=null && cos04List.size()>0){
							cos04List.clear();
						}
						if(cos05List!=null && cos05List.size()>0){
							cos05List.clear();
						}
						if(COSSDFileList!=null && COSSDFileList.size()>0){
							COSSDFileList.clear();
						}
						if(COSDPFileList!=null && COSDPFileList.size()>0){
							COSDPFileList.clear();
						}
						if(COSIDFileList!=null && COSIDFileList.size()>0){
							COSIDFileList.clear();
						}
						flag = false;
					}
					if(!flag)	break;
				}
			}
		}
		
		// 相關資料頁籤
		java.util.List<Con0001Db> CFileList = ServiceGetter.getInstance().getTcbwService().load(" from Con0001Db where fileKind = 'C' and dbName = 'COS0001DB' and upLoadId = " + c.getId());
		this.genFileRowItemSet(CFileList, "C");
		
		if(CFileList!=null && CFileList.size()>0){
			CFileList.clear();
		}
	}

	@Override
	public Object doQueryOne() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object doQueryAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void doCreate() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doUpdate() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doDelete() throws Exception {
		// TODO Auto-generated method stub
		
	}

}
