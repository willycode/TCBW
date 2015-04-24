package com.kangdainfo.tcbw.view.cosex;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.model.bo.CommonCode;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Con0001Db;
import com.kangdainfo.tcbw.model.bo.Cos1001Db;
import com.kangdainfo.tcbw.model.bo.Cos4004Db;
import com.kangdainfo.tcbw.model.bo.Cos4005Db;

public class COSEX0100F extends SuperBean {
	
	private String id;
	private String applNo;
    private String status;	
    private String occurDate;	
    private String notifierRevDate;	
    private String notifierRepDate;	
    private String notifierSource;	
    private String notifierSourceOther;	
    private String notifierName;	
    private String notifierTitle;	
    private String notifierDept;	
    private String notifierTel;	
    private String notifierPhone;	
    private String notifierFax;	
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
    private String manufactorName;
    private String manufactorAddr;	
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
    private String otherIsDamageYn;
    private String otherInformation;
    
    private String notifierTelArea;						// 電話區碼
	private String notifierTelExt;						// 電話分機
	private String notifierArea;						// 縣市
	private String notifierZipCode;						// 郵遞區號
	private String manufactorArea;	
    private String manufactorZipCode;
    private String manufactorTelArea;
    private String manufactorTelExt;
	private String otherExplain;
	
	public String getId() {		return checkGet(id);	}
	public void setId(String id) {		this.id = checkSet(id);	}
	public String getApplNo() {		return checkGet(applNo);	}
	public void setApplNo(String applNo) {		this.applNo = checkSet(applNo);	}
	public String getStatus() {		return checkGet(status);	}
	public void setStatus(String status) {		this.status = checkSet(status);	}
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
	public void setTradePlaceAddr(String tradePlaceAddr) {
		this.tradePlaceAddr = checkSet(tradePlaceAddr);
	}
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
	public String getOtherIsDamageYn() {return checkGet(otherIsDamageYn);}
	public void setOtherIsDamageYn(String otherIsDamageYn) {this.otherIsDamageYn = checkSet(otherIsDamageYn);}
	public String getOtherInformation() {		return checkGet(otherInformation);	}
	public void setOtherInformation(String otherInformation) {		this.otherInformation = checkSet(otherInformation);	}
	
	public String getNotifierTelArea() {		return checkGet(notifierTelArea);	}
	public void setNotifierTelArea(String notifierTelArea) {		this.notifierTelArea = checkSet(notifierTelArea);	}
	public String getNotifierTelExt() {		return checkGet(notifierTelExt);	}
	public void setNotifierTelExt(String notifierTelExt) {		this.notifierTelExt = checkSet(notifierTelExt);	}
	public String getNotifierArea() {		return checkGet(notifierArea);	}
	public void setNotifierArea(String notifierArea) {		this.notifierArea = checkSet(notifierArea);	}
	public String getNotifierZipCode() {		return checkGet(notifierZipCode);	}
	public void setNotifierZipCode(String notifierZipCode) {		this.notifierZipCode = checkSet(notifierZipCode);	}
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

	// FOR 純粹畫面顯示不良類別用
	private String[] showCosType;
	
	public String[] getShowCosType() {		return checkGet(showCosType);	}
	public void setShowCosType(String[] showCosType) {		this.showCosType = checkSet(showCosType);	}

	// COS4002_DB
	private String adverseCondition;	
    private String nonSeriousOther;	
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
	
	// COS4003_DB
	private String[] subCode;
	private String[] otherDescribe;
	
	public String[] getSubCode() {		return checkGet(subCode);	}
	public void setSubCode(String[] subCode) {		this.subCode = checkSet(subCode);	}
	public String[] getOtherDescribe() {		return checkGet(otherDescribe);	}
	public void setOtherDescribe(String[] otherDescribe) {		this.otherDescribe = checkSet(otherDescribe);	}

	private String[] CFileRow;
	private String[] COSDPFileRow;
	private String[] COSSDFileRow;
	private String[] COSIDFileRow;
	private String[] COS4004DbRow;
	private String[] COS4005DbRow;
	
	public String[] getCFileRow() {		return checkGet(CFileRow);	}
	public void setCFileRow(String[] cFileRow) {		CFileRow = checkSet(cFileRow);	}
	public String[] getCOSDPFileRow() {		return checkGet(COSDPFileRow);	}
	public void setCOSDPFileRow(String[] cOSDPFileRow) {		COSDPFileRow = checkSet(cOSDPFileRow);	}
	public String[] getCOSSDFileRow() {		return checkGet(COSSDFileRow);	}
	public void setCOSSDFileRow(String[] cOSSDFileRow) {		COSSDFileRow = checkSet(cOSSDFileRow);	}
	public String[] getCOSIDFileRow() {return checkGet(COSIDFileRow);}
	public void setCOSIDFileRow(String[] cOSIDFileRow) {COSIDFileRow = checkSet(cOSIDFileRow);}
	public String[] getCOS4004DbRow() {		return checkGet(COS4004DbRow);	}
	public void setCOS4004DbRow(String[] cOS4004DbRow) {		COS4004DbRow = checkSet(cOS4004DbRow);	}
	public String[] getCOS4005DbRow() {		return checkGet(COS4005DbRow);	}
	public void setCOS4005DbRow(String[] cOS4005DbRow) {		COS4005DbRow = checkSet(cOS4005DbRow);	}

	private String CFileRowSBuilder;
	private String COSSDFileRowSBuilder;
	private String COSDPFileRowSBuilder;
	private String COSIDFileRowSBuilder;
	private String COS4004DbRowSBuilder;
	private String COS4005DbRowSBuilder;
	
	public String getCFileRowSBuilder() {		return get(CFileRowSBuilder);	}
	public void setCFileRowSBuilder(String cFileRowSBuilder) {		CFileRowSBuilder = checkSet(cFileRowSBuilder);	}
	public String getCOSSDFileRowSBuilder() {		return get(COSSDFileRowSBuilder);	}
	public void setCOSSDFileRowSBuilder(String cOSSDFileRowSBuilder) {		COSSDFileRowSBuilder = checkSet(cOSSDFileRowSBuilder);	}
	public String getCOSDPFileRowSBuilder() {		return get(COSDPFileRowSBuilder);	}
	public void setCOSDPFileRowSBuilder(String cOSDPFileRowSBuilder) {		COSDPFileRowSBuilder = checkSet(cOSDPFileRowSBuilder);	}
	public String getCOSIDFileRowSBuilder() {return get(COSIDFileRowSBuilder);}
	public void setCOSIDFileRowSBuilder(String cOSIDFileRowSBuilder) {COSIDFileRowSBuilder = checkSet(cOSIDFileRowSBuilder);}
	public String getCOS4004DbRowSBuilder() {		return get(COS4004DbRowSBuilder);	}
	public void setCOS4004DbRowSBuilder(String cOS4004DbRowSBuilder) {		COS4004DbRowSBuilder = checkSet(cOS4004DbRowSBuilder);	}
	public String getCOS4005DbRowSBuilder() {		return get(COS4005DbRowSBuilder);	}
	public void setCOS4005DbRowSBuilder(String cOS4005DbRowSBuilder) {		COS4005DbRowSBuilder = checkSet(cOS4005DbRowSBuilder);	}

	public final String[] arrCFileFieldName = {"CFileName", "CFileNameRoute", "CFileExplan"};
	public final String[] arrCOSSDFileFieldName = {"COSSDFileName", "COSSDFileNameRoute", "COSSDFileExplan"};
	public final String[] arrCOSDPFileFieldName = {"COSDPFileName", "COSDPFileNameRoute", "COSDPFileExplan"};
	public final String[] arrCOSIDFileFieldName = {"COSIDFileName", "COSIDFileNameRoute", "COSIDFileExplan"};
	public final String[] arrCOS4004DbFieldName = {"testDate","testItems","testNum"};
	public final String[] arrCOS4005DbFieldName = {"cName","manufactorName","useDateS","useDateE","useRate","useMethod","manufactorNo","expirationDate","tradeDate"};
	
	private String doType;
	private String isNeedBackQuery;
	private javax.servlet.ServletRequest httpRequest;
	private String tabId;

	public String getDoType() {		return checkGet(doType);	}
	public void setDoType(String doType) {		this.doType = checkSet(doType);	}
	public String getIsNeedBackQuery() {		return checkGet(isNeedBackQuery);	}
	public void setIsNeedBackQuery(String isNeedBackQuery) {		this.isNeedBackQuery = checkSet(isNeedBackQuery);	}
	public javax.servlet.ServletRequest getHttpRequest() {	return httpRequest;	}
	public void setHttpRequest(javax.servlet.ServletRequest r) {	this.httpRequest = r;	}
	public String getTabId() {return checkGet(tabId);}
	public void setTabId(String tabId) {this.tabId = checkSet(tabId);}
	
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
						sb.append("<input class='" + className + "'").append(" type='checkbox' name='" + checkBoxFieldName + "' value='" + cos.getDpdKind() + "' ");
						sb.append( Common.get(selectOneMap.get(Common.get(cos.getDpdKind()))).equals("Y")?"checked":"" );
						sb.append(" onClick='chgSubCode();'>");
						sb.append(Common.get(cos.getDpdKindName()));
						
						if(!isNeedShowOthers){
							continue;
						}
						if(Common.get(cos.getDpdKind()).indexOf("99") != -1){
							hasOthers = true;
							othersValue = View.getLookupField(" select otherDescribe from Cos4003Db where cos4001Db.id = " + Common.getLong(id) + 
															  " and mainCode = " + Common.sqlChar(cos.getCdpCode()));
							sb.append("，請描述 : ")
							  .append("<input class='" + className + "'").append(" type='text' id='" + otherFieldName + cos.getDpdKind()+ "' name='" + otherFieldName + "' size='30' maxlength='100'")
							  .append(" value='" + othersValue + "' disabled>");
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
	
	public String genCos4004DbItemSet(java.util.List<Cos4004Db> dtlList) throws Exception{
		if(dtlList!=null && dtlList.size()>0){
			StringBuilder sb = new StringBuilder(1024);
			for(Cos4004Db dtl : dtlList){
				sb.append("addCOS4004DbRow('tabCOS4004Db'");
				sb.append(",").append(Common.sqlChar(dtl.getTestDate()));
				sb.append(",").append(Common.sqlChar(dtl.getTestItems()));
				sb.append(",").append(Common.sqlChar(dtl.getTestNum()));
				sb.append(",'").append(dtl.getId()!=null?dtl.getId():"").append("'");
				sb.append(");\n");
			}
			this.setCOS4004DbRowSBuilder(sb.toString());
		}
		return "";
	}
	
	public String genCos4005DbItemSet(java.util.List<Cos4005Db> dtlList) throws Exception{
		if(dtlList!=null && dtlList.size()>0){
			StringBuilder sb = new StringBuilder(1024);
			for(Cos4005Db dtl : dtlList){
				sb.append("addCOS4005DbRow('tabCOS4005Db'");
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
			this.setCOS4005DbRowSBuilder(sb.toString());
		}
		return "";
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
