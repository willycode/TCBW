package com.kangdainfo.tcbw.view.hfrex;

import javax.swing.table.DefaultTableModel;

import net.sf.jasperreports.engine.data.JRTableModelDataSource;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.model.bo.CommonCode;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Con0001Db;
import com.kangdainfo.tcbw.model.bo.Hfr4001Db;
import com.kangdainfo.tcbw.model.bo.Hfr4002Db;
import com.kangdainfo.tcbw.model.bo.Hfr4003Db;
import com.kangdainfo.tcbw.model.bo.Hfr4004Db;
import com.kangdainfo.tcbw.util.TCBWCommon;

public class HFREX0104F extends HFREX0101F {

	private String notifierRevDate;
	private String notifierRepDate;
	private String occurDate;
	private String isSameNotifier;
	private String notifierName;
	private String notifierTel;
	private String notifierIdNum;
	private String notifierEamil;
	private String notifierType;
	private String notifierDept;
	private String notifierDeptID;
	private String notifierTitle;
	private String address;
	private String notifierTelArea;						// 電話區碼
	private String notifierTelExt;						// 電話分機
	private String eatersTelArea;
	private String eatersTelExt;
	private String notifierArea;						// 縣市
	private String notifierZipCode;						// 郵遞區號	
	
	private String eatersId;
	private String eatersName;
	private String eatersSex;
	private String eatersBirthYear;
	private String eatersHight;
	private String eatersWeight;
	private String eatersTel;
	private String eatersIdNum;
	private String drugAllergies;
	private String foodAllergies;
	private String isReactionResult;
	private String informedSources;
	private String drugOther;
	private String foodOther;
	private String diseaseOther;
	private String lifeOther;
	private String[] diseaseHistory;
	private String[] lifeHistory;
	
	public String getNotifierRevDate() {		return checkGet(notifierRevDate);	}
	public void setNotifierRevDate(String notifierRevDate) {		this.notifierRevDate = checkSet(notifierRevDate);	}
	public String getNotifierRepDate() {		return checkGet(notifierRepDate);	}
	public void setNotifierRepDate(String notifierRepDate) {		this.notifierRepDate = checkSet(notifierRepDate);	}
	public String getOccurDate() {		return checkGet(occurDate);	}
	public void setOccurDate(String occurDate) {		this.occurDate = checkSet(occurDate);	}
	public String getIsSameNotifier() {		return checkGet(isSameNotifier);	}
	public void setIsSameNotifier(String isSameNotifier) {		this.isSameNotifier = checkSet(isSameNotifier);	}
	public String getNotifierName() {		return checkGet(notifierName);	}
	public void setNotifierName(String notifierName) {		this.notifierName = checkSet(notifierName);	}
	public String getNotifierTel() {		return checkGet(notifierTel);	}
	public void setNotifierTel(String notifierTel) {		this.notifierTel = checkSet(notifierTel);	}
	public String getNotifierIdNum() {		return checkGet(notifierIdNum);	}
	public void setNotifierIdNum(String notifierIdNum) {		this.notifierIdNum = checkSet(notifierIdNum);	}
	public String getNotifierEamil() {		return checkGet(notifierEamil);	}
	public void setNotifierEamil(String notifierEamil) {		this.notifierEamil = checkSet(notifierEamil);	}
	public String getNotifierType() {		return checkGet(notifierType);	}
	public void setNotifierType(String notifierType) {		this.notifierType = checkSet(notifierType);	}
	public String getNotifierDept() {		return checkGet(notifierDept);	}
	public void setNotifierDept(String notifierDept) {		this.notifierDept = checkSet(notifierDept);	}
	public String getNotifierDeptID() {		return checkGet(notifierDeptID);}	
	public void setNotifierDeptID(String notifierDeptID) {		this.notifierDeptID = checkSet(notifierDeptID);	}
	public String getNotifierTitle() {		return checkGet(notifierTitle);	}
	public void setNotifierTitle(String notifierTitle) {		this.notifierTitle = checkSet(notifierTitle);	}
	public String getEatersId() {		return checkGet(eatersId);	}
	public void setEatersId(String eatersId) {		this.eatersId = checkSet(eatersId);	}
	public String getEatersName() {		return checkGet(eatersName);	}
	public void setEatersName(String eatersName) {		this.eatersName = checkSet(eatersName);	}
	public String getEatersSex() {		return checkGet(eatersSex);	}
	public void setEatersSex(String eatersSex) {		this.eatersSex = checkSet(eatersSex);	}
	public String getEatersBirthYear() {		return checkGet(eatersBirthYear);	}
	public void setEatersBirthYear(String eatersBirthYear) {		this.eatersBirthYear = checkSet(eatersBirthYear);	}
	public String getEatersHight() {		return checkGet(eatersHight);	}
	public void setEatersHight(String eatersHight) {		this.eatersHight = checkSet(eatersHight);	}
	public String getEatersWeight() {		return checkGet(eatersWeight);	}
	public void setEatersWeight(String eatersWeight) {		this.eatersWeight = checkSet(eatersWeight);	}
	public String getAddress() {		return checkGet(address);	}
	public void setAddress(String address) {		this.address = checkSet(address);	}
	public String getEatersTel() {		return checkGet(eatersTel);	}
	public void setEatersTel(String eatersTel) {		this.eatersTel = checkSet(eatersTel);	}
	public String getEatersIdNum() {		return checkGet(eatersIdNum);	}
	public void setEatersIdNum(String eatersIdNum) {		this.eatersIdNum = checkSet(eatersIdNum);	}
	public String getDrugAllergies() {		return checkGet(drugAllergies);	}
	public void setDrugAllergies(String drugAllergies) {		this.drugAllergies = checkSet(drugAllergies);	}
	public String getFoodAllergies() {		return checkGet(foodAllergies);	}
	public void setFoodAllergies(String foodAllergies) {		this.foodAllergies = checkSet(foodAllergies);	}
	public String getIsReactionResult() {		return checkGet(isReactionResult);	}
	public void setIsReactionResult(String isReactionResult) {		this.isReactionResult = checkSet(isReactionResult);	}
	public String getInformedSources() {		return checkGet(informedSources);	}
	public void setInformedSources(String informedSources) {		this.informedSources = checkSet(informedSources);	}
	public String getDrugOther() {		return checkGet(drugOther);	}
	public void setDrugOther(String drugOther) {		this.drugOther = checkSet(drugOther);	}
	public String getFoodOther() {		return checkGet(foodOther);	}
	public void setFoodOther(String foodOther) {		this.foodOther = checkSet(foodOther);	}
	public String getDiseaseOther() {		return checkGet(diseaseOther);	}
	public void setDiseaseOther(String diseaseOther) {		this.diseaseOther = checkSet(diseaseOther);	}
	public String getLifeOther() {		return checkGet(lifeOther);	}
	public void setLifeOther(String lifeOther) {		this.lifeOther = checkSet(lifeOther);	}
	public String[] getDiseaseHistory() {		return diseaseHistory;}
	public void setDiseaseHistory(String[] diseaseHistory) {		this.diseaseHistory = diseaseHistory;	}
	public String[] getLifeHistory() {		return lifeHistory;	}
	public void setLifeHistory(String[] lifeHistory) {		this.lifeHistory = lifeHistory;	}
	public String getNotifierTelArea() {		return checkGet(notifierTelArea);	}
	public void setNotifierTelArea(String notifierTelArea) {		this.notifierTelArea = checkSet(notifierTelArea);	}
	public String getNotifierTelExt() {		return checkGet(notifierTelExt);	}
	public void setNotifierTelExt(String notifierTelExt) {		this.notifierTelExt = checkSet(notifierTelExt);	}
	public String getEatersTelArea() {		return checkGet(eatersTelArea);	}
	public void setEatersTelArea(String eatersTelArea) {		this.eatersTelArea = checkSet(eatersTelArea);	}
	public String getEatersTelExt() {		return checkGet(eatersTelExt);	}
	public void setEatersTelExt(String eatersTelExt) {		this.eatersTelExt = checkSet(eatersTelExt);	}
	public String getNotifierArea() {		return checkGet(notifierArea);	}
	public void setNotifierArea(String notifierArea) {		this.notifierArea = checkSet(notifierArea);	}
	public String getNotifierZipCode() {		return checkGet(notifierZipCode);	}
	public void setNotifierZipCode(String notifierZipCode) {		this.notifierZipCode = checkSet(notifierZipCode);	}
	
	private String unHealthIsYes;
	private String unHealthBrief;
	private String unReactionResults;
	private String deathDate;
	private String deathResult;
	private String endangerLife;
	private String otherNonResponse;
	private String[] otherrElevantInformation;
	private String allergicDescription;
	private String othersDescribeHistory;

	public String getUnHealthIsYes() {		return checkGet(unHealthIsYes);	}
	public void setUnHealthIsYes(String unHealthIsYes) {		this.unHealthIsYes = checkSet(unHealthIsYes);	}
	public String getUnHealthBrief() {		return checkGet(unHealthBrief);	}
	public void setUnHealthBrief(String unHealthBrief) {		this.unHealthBrief = checkSet(unHealthBrief);	}
	public String getUnReactionResults() {		return checkGet(unReactionResults);	}
	public void setUnReactionResults(String unReactionResults) {		this.unReactionResults = checkSet(unReactionResults);	}
	public String getDeathDate() {		return checkGet(deathDate);	}
	public void setDeathDate(String deathDate) {		this.deathDate = checkSet(deathDate);	}
	public String getDeathResult() {		return checkGet(deathResult);	}
	public void setDeathResult(String deathResult) {		this.deathResult = checkSet(deathResult);	}
	public String getEndangerLife() {		return checkGet(endangerLife);	}
	public void setEndangerLife(String endangerLife) {		this.endangerLife = checkSet(endangerLife);	}
	public String getOtherNonResponse() {		return checkGet(otherNonResponse);	}
	public void setOtherNonResponse(String otherNonResponse) {		this.otherNonResponse = checkSet(otherNonResponse);	}
	public String[] getOtherrElevantInformation() {		return otherrElevantInformation;	}
	public void setOtherrElevantInformation(String[] otherrElevantInformation) {		this.otherrElevantInformation = otherrElevantInformation;	}
	public String getAllergicDescription() {		return checkGet(allergicDescription);	}
	public void setAllergicDescription(String allergicDescription) {		this.allergicDescription = checkSet(allergicDescription);	}
	public String getOthersDescribeHistory() {		return checkGet(othersDescribeHistory);	}
	public void setOthersDescribeHistory(String othersDescribeHistory) {		this.othersDescribeHistory = checkSet(othersDescribeHistory);	}

	private String againEatingHealthFood;
	private String healthFoodName;
	private String againOtherNonResponse;
	private String stopEatingReaction;
	private String againEatingReaction;
	
	public String getAgainEatingHealthFood() {		return checkGet(againEatingHealthFood);	}
	public void setAgainEatingHealthFood(String againEatingHealthFood) {		this.againEatingHealthFood = checkSet(againEatingHealthFood);	}
	public String getHealthFoodName() {		return checkGet(healthFoodName);	}
	public void setHealthFoodName(String healthFoodName) {		this.healthFoodName = checkSet(healthFoodName);	}
	public String getAgainOtherNonResponse() {		return checkGet(againOtherNonResponse);	}
	public void setAgainOtherNonResponse(String againOtherNonResponse) {		this.againOtherNonResponse = checkSet(againOtherNonResponse);	}
	public String getStopEatingReaction() {		return checkGet(stopEatingReaction);	}
	public void setStopEatingReaction(String stopEatingReaction) {		this.stopEatingReaction = checkSet(stopEatingReaction);	}
	public String getAgainEatingReaction() {		return checkGet(againEatingReaction);	}
	public void setAgainEatingReaction(String againEatingReaction) {		this.againEatingReaction = checkSet(againEatingReaction);	}

	
	//簡表列印
	public DefaultTableModel getSimpleDefaultTableModel() throws Exception
	{		
		DefaultTableModel model = new javax.swing.table.DefaultTableModel();
    	String[] cols = new String[]
    	         {"id","applNo","status","occurDate","notifierName","notifierTel","address",
    			"notifierEamil","eatersName","eatersHight","eatersWeight","eatersAge","eatersSex",
    			"eatersTel", "eatersIdNum","unHealthIsYes","unHealthBrief","occurredAfter",
    			"symptom","severity","symptomDuration","stopEatingReaction","againEatingReaction",
    			"isMedical","isWhileMedicine","isWhileCMedicine","isWhileOther","otherExplainMemo",
    			"drugAllergies","foodAllergies","diseaseHistory","lifeHistory","isReactionResult",
    			"obj1","obj2","obj1and2text","obj3","obj3text","obj4","obj4text","obj5","obj5text"
    			}; 
//    	java.util.List objList = new java.util.ArrayList<String[]>();
//    	java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
    	
    	java.util.ArrayList<Object[]> arrList = new java.util.ArrayList<Object[]>();
    	String hql = "from Hfr4001Db where 1=1 ";
		
		if(!"".equals(getId()))
			hql += "and id = " + Common.getLong(getId());
		if(!"".equals(getApplNo()))
			hql += "and applNo = " + Common.sqlChar(getApplNo());
		
		java.util.List list = ServiceGetter.getInstance().getCommonService().load(hql);
		if (list!=null && list.size()>0) {
			for (int i=0; i<list.size(); i++) {
				Hfr4001Db obj = (Hfr4001Db) list.get(i);
				Object rowArray[]=new Object[cols.length];
				rowArray[0]=Common.get(obj.getId());
				rowArray[1]=Common.get(obj.getApplNo());
				rowArray[2]=Common.get(obj.getStatus());
				rowArray[3]=Common.formatYYYMMDD(obj.getOccurDate(),2); 
				rowArray[4]=Common.get(obj.getNotifierName());
				rowArray[5]=Common.get(obj.getNotifierTel());
				rowArray[6]=Common.get(obj.getAddress());
				rowArray[7]=Common.get(obj.getNotifierEamil());
				rowArray[8]=Common.get(obj.getEatersName());
				rowArray[9]=Common.get(obj.getEatersHight()) + "公分";
				rowArray[10]=Common.get(obj.getEatersWeight()) + "公斤";
				rowArray[11]=Common.get(obj.getEatersAge()) + "歲";
				
				if("M".equals(Common.get(obj.getEatersSex()))) {
					rowArray[12]="■男　□女";
				} else if("F".equals(Common.get(obj.getEatersSex()))) {
					rowArray[12]="□男　■女";
				} else {
					rowArray[12]="□男　□女";
				}
				rowArray[13]=Common.get(obj.getEatersTel());
				
				rowArray[14]=Common.get(obj.getEatersIdNum());
				
				if("Y".equals(obj.getUnHealthIsYes())) {
					rowArray[15]="■是(請簡述)" + Common.get(obj.getUnHealthBrief()) + "　□否";
					rowArray[16]=Common.get(obj.getUnHealthBrief());
				} else if("N".equals(obj.getUnHealthIsYes())) {
					rowArray[15]="□是(請簡述)____________________■否";
					rowArray[16]=Common.get(obj.getUnHealthBrief());
				} else {
					rowArray[15]="□是(請簡述)____________________　□否";
					rowArray[16]=Common.get(obj.getUnHealthBrief());
				}
				
				rowArray[17]=Common.get(obj.getOccurredAfter());
				rowArray[18]=Common.get(obj.getSymptom());
	  			rowArray[19]=Common.get(obj.getSeverity());
	  			rowArray[20]=Common.get(obj.getSymptomDuration());	  			  
  			  	rowArray[21]=Common.get(obj.getStopEatingReaction());
  			  	
  			  	if("Y".equals(Common.get(obj.getAgainEatingReaction()))) {
			  		rowArray[22]="■是　　□否";
			  	} else if("N".equals(Common.get(obj.getAgainEatingReaction()))) {
			  		rowArray[22]="□是　　■否";
			  	} else {
			  		rowArray[22]="□是　　□否";
			  	}
  			  	
  			  	if("Y".equals(Common.get(obj.getIsMedical()))) {
  			  		rowArray[23]="■是，就醫日期及醫療院所：" + Common.formatYYYMMDD(obj.getMedicalDate(),2) + "，" + Common.get(obj.getHospital()) + "　□否";
  			  	} else if("N".equals(Common.get(obj.getIsMedical()))) {
  			  		rowArray[23]="□是，就醫日期及醫療院所：____________　■否";
  			  	} else {
  			  		rowArray[23]="□是，就醫日期及醫療院所：____________　□否";
  			  	}
  			  	
  			  	if("Y".equals(Common.get(obj.getIsWhileMedicine()))) {
  			  		rowArray[24]="■是，藥品名" + Common.get(obj.getWestDrugName()) + "　□否";
  			  	} else if ("N".equals(Common.get(obj.getIsWhileMedicine()))) {
  			  		rowArray[24]="□是，藥品名________　■否";
  			  	} else {
  			  		rowArray[24]="□是，藥品名________　□否";
  			  	}
  			  	
  			  	if("Y".equals(Common.get(obj.getIsWhileCMedicine()))) {
			  		rowArray[25]="■是，中草藥品名" + Common.get(obj.getEastDrugName()) + "　□否";
			  	} else if ("N".equals(Common.get(obj.getIsWhileCMedicine()))) {
			  		rowArray[25]="□是，中草藥品名________　■否";
			  	} else {
			  		rowArray[25]="□是，中草藥品名________　□否";
			  	}
  			  	
  			  	if("Y".equals(Common.get(obj.getIsWhileOther()))) {
			  		rowArray[26]="■是，產品名稱" + Common.get(obj.getProductName()) + "　□否";
			  	} else if ("N".equals(Common.get(obj.getProductName()))) {
			  		rowArray[26]="□是，產品名稱________　■否";
			  	} else {
			  		rowArray[26]="□是，產品名稱________　□否";
			  	}
  			  	rowArray[27]=Common.get(obj.getOtherExplainMemo());
  			  	if("Y".equals(Common.get(obj.getDrugAllergies()))) {
			  		rowArray[28]="■是　　□否　　□無法得知";
			  	} else if("N".equals(Common.get(obj.getDrugAllergies()))) {
			  		rowArray[28]="□是　　■否　　□無法得知";
			  	} else if("U".equals(Common.get(obj.getDrugAllergies()))) {
			  		rowArray[28]="□是　　□否　　■無法得知";
			  	} else {
			  		rowArray[28]="□是　　□否　　□無法得知";
			  	}
  			  	
  			  	if("Y".equals(Common.get(obj.getFoodAllergies()))) {
			  		rowArray[29]="■是　　□否　　□無法得知";
			  	} else if("N".equals(Common.get(obj.getFoodAllergies()))) {
			  		rowArray[29]="□是　　■否　　□無法得知";
			  	} else if("U".equals(Common.get(obj.getFoodAllergies()))) {
			  		rowArray[29]="□是　　□否　　■無法得知";
			  	} else {
			  		rowArray[29]="□是　　□否　　□無法得知";
			  	}
  			  	
  			  	rowArray[30]=getDiseaseHistory(Common.get(obj.getDiseaseHistory()));
  			  	rowArray[31]=getLlifeHistory(Common.get(obj.getLifeHistory()));
  			  
  			  	if("Y".equals(Common.get(obj.getIsReactionResult()))) {
			  		rowArray[32]="■是　　□否";
			  	} else if("N".equals(Common.get(obj.getFoodAllergies()))) {
			  		rowArray[32]="□是　　■否";
			  	} else {
			  		rowArray[32]="□是　　□否";
			  	}
  			  	
  			    if(obj.getHfr4002Dbs()!=null && obj.getHfr4002Dbs().size()>0)
				{
					java.util.Iterator it2 = obj.getHfr4002Dbs().iterator();
					rowArray[33] = new JRTableModelDataSource(getSRSubModel01(it2));
				}
  			    
  			    if(obj.getHfr4002Dbs()!=null && obj.getHfr4002Dbs().size()>0)
				{
					java.util.Iterator it2 = obj.getHfr4002Dbs().iterator();
					rowArray[34] = new JRTableModelDataSource(getSRSubModel02(it2));
				}
  			   
  			    rowArray[35] = "產品名稱(中、英文)";
  			    
  			    if(obj.getHfr4002Dbs()!=null && obj.getHfr4002Dbs().size()>0)
				{
					java.util.Iterator it2 = obj.getHfr4002Dbs().iterator();
					rowArray[36] = new JRTableModelDataSource(getSRSubModel03(it2));
				}
  			  	
  			  	rowArray[37] = "產品名稱(中、英文)";
  			  	
  			   if(obj.getHfr4002Dbs()!=null && obj.getHfr4002Dbs().size()>0)
				{
					java.util.Iterator it2 = obj.getHfr4002Dbs().iterator();
					rowArray[38] = new JRTableModelDataSource(getSRSubModel04(it2));
				}
			  	
			  	rowArray[39] = "食用方式";
			  	
			  	if(obj.getHfr4002Dbs()!=null && obj.getHfr4002Dbs().size()>0)
				{
					java.util.Iterator it2 = obj.getHfr4002Dbs().iterator();
					rowArray[40] = new JRTableModelDataSource(getSRSubModel05(it2));
				}
			  	
			  	rowArray[41] = "食用日期~停止食用日期";
			  	

				arrList.add(rowArray);
			}
		}
    
		if (arrList!=null && arrList.size()>0) 
		{
			Object[][] rs = new Object[0][0];
			rs = (Object[][])arrList.toArray(rs);
			model.setDataVector(rs, cols);
		}	
		return model;    	
	}
	
	//簡表-子報表路徑設定
	public void setSR_Parameter(java.util.HashMap<String, Object> params)
	{
		String sr_subreport0FilePath = 
			ServiceGetter.getInstance().getMyServletContext().getServletContext().getRealPath("/report/hfr/hfrin0102sr_subreport0.jasper");
		String sr_subreport1FilePath = 
			ServiceGetter.getInstance().getMyServletContext().getServletContext().getRealPath("/report/hfr/hfrin0102sr_subreport1.jasper");
		String sr_subreport2FilePath = 
			ServiceGetter.getInstance().getMyServletContext().getServletContext().getRealPath("/report/hfr/hfrin0102sr_subreport2.jasper");
		String sr_subreport3FilePath = 
			ServiceGetter.getInstance().getMyServletContext().getServletContext().getRealPath("/report/hfr/hfrin0102sr_subreport3.jasper");
		String sr_subreport4FilePath = 
			ServiceGetter.getInstance().getMyServletContext().getServletContext().getRealPath("/report/hfr/hfrin0102sr_subreport4.jasper");
		
		
		params.put("sr_subreport0", sr_subreport0FilePath);
		params.put("sr_subreport1", sr_subreport1FilePath);
		params.put("sr_subreport2", sr_subreport2FilePath);
		params.put("sr_subreport3", sr_subreport3FilePath);
		params.put("sr_subreport4", sr_subreport4FilePath);
		

	}
	
	//簡表子報表1
	public DefaultTableModel getSRSubModel01(java.util.Iterator it2) throws Exception
	{
		String[] col02 = new String[]{"chProduct","enProduct","permitKey","permitNo"};	
		
		DefaultTableModel model = new javax.swing.table.DefaultTableModel();
		
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();		

		while(it2.hasNext())
		{
			Hfr4002Db hfr4002Db = (Hfr4002Db)it2.next();
			String[] rowArray= new String[col02.length];
			
			rowArray[0]=Common.get(hfr4002Db.getChProduct());
			rowArray[1]=Common.get(hfr4002Db.getEnProduct());
			rowArray[2]=getPermitKey(Common.get(hfr4002Db.getPermitKey())) + "字";
			rowArray[3]="第" + Common.get(hfr4002Db.getPermitNo()) + "號";
			//只填入許可證字為無"規"的
			if("H".equals(hfr4002Db.getPermitKey()) || "Q".equals(hfr4002Db.getPermitKey())) {
				arrList.add(rowArray);
			}
		}
		if(null != arrList && arrList.size() >0)
		{
			Object[][] rs = new Object[0][0];
	        rs = (Object[][])arrList.toArray(rs);
	        model.setDataVector(rs, col02);
		}
        return model;
	}	
	
	//簡表子報表2
	public DefaultTableModel getSRSubModel02(java.util.Iterator it2) throws Exception
	{
		String[] col02 = new String[]{"chProduct","enProduct","permitKey","permitNo"};	
		
		DefaultTableModel model = new javax.swing.table.DefaultTableModel();
		
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();		

		while(it2.hasNext())
		{
			Hfr4002Db hfr4002Db = (Hfr4002Db)it2.next();
			String[] rowArray= new String[col02.length];
			
			rowArray[0]=Common.get(hfr4002Db.getChProduct());
			rowArray[1]=Common.get(hfr4002Db.getEnProduct());
			rowArray[2]=getPermitKey(Common.get(hfr4002Db.getPermitKey())) + "字";
			rowArray[3]="第" + Common.get(hfr4002Db.getPermitNo()) + "號";
			//只填入許可證字為有"規"的
			if("J".equals(hfr4002Db.getPermitKey()) || "R".equals(hfr4002Db.getPermitKey())) {
				arrList.add(rowArray);
			}
		}
		if(null != arrList && arrList.size() >0)
		{
			Object[][] rs = new Object[0][0];
	        rs = (Object[][])arrList.toArray(rs);
	        model.setDataVector(rs, col02);
		}
        return model;
	}	
	
	//簡表子報表3
	public DefaultTableModel getSRSubModel03(java.util.Iterator it2) throws Exception
	{
		String[] col02 = new String[]{"chProduct","enProduct"};	
		
		DefaultTableModel model = new javax.swing.table.DefaultTableModel();
		
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();		

		while(it2.hasNext())
		{
			Hfr4002Db hfr4002Db = (Hfr4002Db)it2.next();
			String[] rowArray= new String[col02.length];
			
			rowArray[0]=Common.get(hfr4002Db.getChProduct());
			rowArray[1]=Common.get(hfr4002Db.getEnProduct());
			//只填入一般食品
			if("N".equals(hfr4002Db.getType())) {
				arrList.add(rowArray);
			}
		}
		if(null != arrList && arrList.size() >0)
		{
			Object[][] rs = new Object[0][0];
	        rs = (Object[][])arrList.toArray(rs);
	        model.setDataVector(rs, col02);
		}
        return model;
	}
	
	//簡表子報表4
	public DefaultTableModel getSRSubModel04(java.util.Iterator it2) throws Exception
	{
		String[] col02 = new String[]{"doseDay","doseNum","frequencyNum","frequencyMl"};	
		
		DefaultTableModel model = new javax.swing.table.DefaultTableModel();
		
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();		

		java.util.Map<String, String> frequencyUnitMap = TCBWCommon.getCommonCodeMap("DRG0305");
		while(it2.hasNext())
		{
			Hfr4002Db hfr4002Db = (Hfr4002Db)it2.next();
			String[] rowArray= new String[col02.length];
			
			if(!"".equals(Common.get(hfr4002Db.getDoseDay()))) {
				rowArray[0]=Common.get(hfr4002Db.getDoseDay()) + "日";
			} else {
				rowArray[0]="____日";
			}
			
			if(!"".equals(Common.get(hfr4002Db.getDoseNum()))) {
				rowArray[1]=Common.get(hfr4002Db.getDoseNum()) + "次；";
			} else {
				rowArray[1]="____次；";
			}

			if(!"".equals(Common.get(hfr4002Db.getFrequency()))) {
				rowArray[2]="一次" + Common.get(hfr4002Db.getFrequency());
			} else {
				rowArray[2]="一次____";
			}
			
			if(!"".equals(Common.get(hfr4002Db.getFrequencyUnit()))) {
				rowArray[3]=Common.get(frequencyUnitMap.get(hfr4002Db.getFrequencyUnit()));
			} else {
				rowArray[3]="";
			}

			//只填入一般食品
			if("N".equals(hfr4002Db.getType())) {
				arrList.add(rowArray);
			}
		}
		if(null != arrList && arrList.size() >0)
		{
			Object[][] rs = new Object[0][0];
	        rs = (Object[][])arrList.toArray(rs);
	        model.setDataVector(rs, col02);
		}
        return model;
	}
	
	//簡表子報表5
	public DefaultTableModel getSRSubModel05(java.util.Iterator it2) throws Exception
	{
		String[] col02 = new String[]{"edibleDateS","edibleDateE",};	
		
		DefaultTableModel model = new javax.swing.table.DefaultTableModel();
		
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();		

		while(it2.hasNext())
		{
			Hfr4002Db hfr4002Db = (Hfr4002Db)it2.next();
			String[] rowArray= new String[col02.length];
			
			if(!"".equals(Common.get(hfr4002Db.getEdibleDateS()))) {
				rowArray[0]=Common.formatYYYMMDD(hfr4002Db.getEdibleDateS()) + "　~　";
			} else {
				rowArray[0]="___年___月___日　~　";
			}
			
			if(!"".equals(Common.get(hfr4002Db.getEdibleDateE()))) {
				rowArray[1]=Common.get(hfr4002Db.getEdibleDateE());
			} else {
				rowArray[1]="___年___月___日";
			}
			//只填入一般食品
			if("N".equals(hfr4002Db.getType())) {
				arrList.add(rowArray);
			}
		}
		if(null != arrList && arrList.size() >0)
		{
			Object[][] rs = new Object[0][0];
	        rs = (Object[][])arrList.toArray(rs);
	        model.setDataVector(rs, col02);
		}
        return model;
	}
	
	//一般表列印
	public DefaultTableModel getGeneralDefaultTableModel() throws Exception
	{		
		DefaultTableModel model = new javax.swing.table.DefaultTableModel();
    	String[] cols = new String[]
    	         {"id","applNo","status","occurDate","notifierRevDate","notifierRepDate","notifierName",
    			  "notifierDept","notifierTel","address","notifierEamil","notifierIdNum","notifierType",
    			  "notifierTitle","eatersId","eatersName","eatersHight","eatersWeight","eatersSex",
    			  "eatersBirthYear","eatersIdNum","unHealthIsYes","unHealthBrief","unReactionResults",
    			  "otherrElevantInformation","stopEatingReaction","againEatingReaction",
    			  "againEatingHealthFood","drugAllergies","foodAllergies","diseaseHistory","lifeHistory",
    			  "isReactionResult","eatersTel","obj1","obj2","obj3","obj4","obj5","obj1text","obj2text",
    			  "obj3text","obj4text","obj5text","obj6","obj7"
    			 };  
    	    	
    	java.util.List<Object[]> arrList = new java.util.ArrayList<Object[]>();
    	
    	String hql = "from Hfr4001Db where 1=1 ";
		
		if(!"".equals(getId()))
			hql += "and id = " + Common.getLong(getId());
		if(!"".equals(getApplNo()))
			hql += "and applNo = " + Common.sqlChar(getApplNo());
		
		java.util.List list = ServiceGetter.getInstance().getCommonService().load(hql);
		
		if (list!=null && list.size()>0) 
		{
			for (int i=0; i<list.size(); i++) 
			{
				Hfr4001Db obj = (Hfr4001Db) list.get(i);
				
				Object[] rowArray= new Object[cols.length];
				
				rowArray[0]=Common.get(obj.getId());
				rowArray[1]=Common.get(obj.getApplNo());
				rowArray[2]=Common.get(obj.getStatus());
				rowArray[3]=Common.formatYYYMMDD(obj.getOccurDate(),2); 
				rowArray[4]=Common.formatYYYMMDD(obj.getNotifierRevDate(),2); 
				rowArray[5]=Common.formatYYYMMDD(obj.getNotifierRepDate(),2); 
				rowArray[6]=Common.get(obj.getNotifierName());
				rowArray[7]=Common.get(obj.getNotifierDept());
				rowArray[8]=Common.get(obj.getNotifierTel());
				rowArray[9]=Common.get(obj.getAddress());
				rowArray[10]=Common.get(obj.getNotifierEamil());
				rowArray[11]=Common.get(obj.getNotifierIdNum());
				
				if("".equals(Common.get(obj.getNotifierType()))) {
					rowArray[12]="□　廠商\n□　醫療人員\n□　衛生單位";
					rowArray[13]=getNotifierTitle(Common.get(obj.getNotifierTitle()));
				} else if("02".equals(Common.get(obj.getNotifierType()))) {
					rowArray[12]="■　廠商\n□　醫療人員\n□　衛生單位";
					rowArray[13]=getNotifierTitle(Common.get(obj.getNotifierTitle()));
				} else if("03".equals(Common.get(obj.getNotifierType()))) {
					rowArray[12]="□　廠商\n■　醫療人員\n□　衛生單位";
					rowArray[13]=getNotifierTitle(Common.get(obj.getNotifierTitle()));
				} else if("04".equals(Common.get(obj.getNotifierType()))) {
					rowArray[12]="□　廠商\n□　醫療人員\n■　衛生單位";
					rowArray[13]=getNotifierTitle(Common.get(obj.getNotifierTitle()));
				}
				rowArray[14]=Common.get(obj.getEatersId());
				rowArray[15]=Common.get(obj.getEatersName());
				rowArray[16]=Common.get(obj.getEatersHight());
				rowArray[17]=Common.get(obj.getEatersWeight());
				
				if("M".equals(Common.get(obj.getEatersSex()))) {
					rowArray[18]="■男　　□女";
				} else if("F".equals(Common.get(obj.getEatersSex()))) {
					rowArray[18]="□男　　■女";
				} else {
					rowArray[18]="□男　　□女";
				}
				
				rowArray[19]=Common.get(obj.getEatersBirthYear());
				rowArray[20]=Common.get(obj.getEatersIdNum());
				
				if("Y".equals(obj.getUnHealthIsYes())) {
					rowArray[21]="■是(請簡述)　□否";
					rowArray[22]=Common.get(obj.getUnHealthBrief());
				} else if("N".equals(obj.getUnHealthIsYes())) {
					rowArray[21]="□是(請簡述)　■否";
					rowArray[22]=Common.get(obj.getUnHealthBrief());
				}  else {
					rowArray[21]="□是(請簡述)　□否";
					rowArray[22]="________";
				}

				rowArray[23]=getUnReactionResults(Common.get(obj.getUnReactionResults()),Common.formatYYYMMDD(obj.getDeathDate(),2),Common.get(obj.getDeathResult()),Common.get(obj.getEndangerLife()),Common.get(obj.getOtherNonResponse()));
  			  	rowArray[24]=getOtherrElevantInformation(obj.getOtherrElevantInformation(),Common.get(obj.getAllergicDescription()),Common.get(obj.getOthersDescribeHistory()));
  			  	
  			  	if("Y".equals(Common.get(obj.getStopEatingReaction()))) {
  			  		rowArray[25]="■是　　□否　　□無法得知";
  			  	} else if("N".equals(Common.get(obj.getStopEatingReaction()))) {
  			  		rowArray[25]="□是　　■否　　□無法得知";
  			  	} else if("U".equals(Common.get(obj.getStopEatingReaction()))) {
  			  		rowArray[25]="□是　　□否　　■無法得知";
  			  	} else {
  			  		rowArray[25]="□是　　□否　　□無法得知";
  			  	}
  			  	
  			  	if("Y".equals(Common.get(obj.getAgainEatingReaction()))) {
			  		rowArray[26]="■是　　□否　　□無法得知";
			  	} else if("N".equals(Common.get(obj.getAgainEatingReaction()))) {
			  		rowArray[26]="□是　　■否　　□無法得知";
			  	} else if("U".equals(Common.get(obj.getAgainEatingReaction()))) {
			  		rowArray[26]="□是　　□否　　■無法得知";
			  	} else {
			  		rowArray[26]="□是　　□否　　□無法得知";
			  	}
  			  	
  			  	if("Y".equals(Common.get(obj.getAgainEatingHealthFood()))) {
			  		rowArray[27]="■是，商品名：" + Common.get(obj.getHealthFoodName()) + "，曾發生非預期反應：" + Common.get(obj.getAgainOtherNonResponse()) + "　　□否　　□無法得知";
			  	} else if("N".equals(Common.get(obj.getAgainEatingHealthFood()))) {
			  		rowArray[27]="□是，商品名：________，曾發生非預期反應：________　　■否　　□無法得知";
			  	} else if("U".equals(Common.get(obj.getAgainEatingHealthFood()))) {
			  		rowArray[27]="□是，商品名：________，曾發生非預期反應：________　　□否　　■無法得知";
			  	} else {
			  		rowArray[27]="□是，商品名：________，曾發生非預期反應：________　　□否　　□無法得知";
			  	}
  			  	
  			  	if("Y".equals(Common.get(obj.getDrugAllergies()))) {
			  		rowArray[28]="■是　　□否　　□無法得知";
			  	} else if("N".equals(Common.get(obj.getDrugAllergies()))) {
			  		rowArray[28]="□是　　■否　　□無法得知";
			  	} else if("U".equals(Common.get(obj.getDrugAllergies()))) {
			  		rowArray[28]="□是　　□否　　■無法得知";
			  	} else {
			  		rowArray[28]="□是　　□否　　□無法得知";
			  	}
  			  	
  			  	if("Y".equals(Common.get(obj.getFoodAllergies()))) {
			  		rowArray[29]="■是　　□否　　□無法得知";
			  	} else if("N".equals(Common.get(obj.getFoodAllergies()))) {
			  		rowArray[29]="□是　　■否　　□無法得知";
			  	} else if("U".equals(Common.get(obj.getFoodAllergies()))) {
			  		rowArray[29]="□是　　□否　　■無法得知";
			  	} else {
			  		rowArray[29]="□是　　□否　　□無法得知";
			  	}
  			  	
  			  	rowArray[30]=getDiseaseHistory(Common.get(obj.getDiseaseHistory()));
  			  	rowArray[31]=getLlifeHistory(Common.get(obj.getLifeHistory()));
  			  	
  			  	if("Y".equals(Common.get(obj.getIsReactionResult()))) {
			  		rowArray[32]="■是　　□否";
			  	} else if("N".equals(Common.get(obj.getFoodAllergies()))) {
			  		rowArray[32]="□是　　■否";
			  	} else {
			  		rowArray[32]="□是　　□否";
			  	}
  			  	rowArray[33]=Common.get(obj.getEatersTel());
  			  	
  			  	
  			    if(obj.getHfr4002Dbs()!=null && obj.getHfr4002Dbs().size()>0)
				{
					java.util.Iterator it2 = obj.getHfr4002Dbs().iterator();
					rowArray[34] = new JRTableModelDataSource(getGRSubModel01(it2));
				}
  			    if(obj.getHfr4002Dbs()!=null && obj.getHfr4002Dbs().size()>0)
				{
					java.util.Iterator it2 = obj.getHfr4002Dbs().iterator();
					rowArray[35] = new JRTableModelDataSource(getGRSubModel02(it2));
				}
  			    if(obj.getHfr4002Dbs()!=null && obj.getHfr4002Dbs().size()>0)
				{
					java.util.Iterator it2 = obj.getHfr4002Dbs().iterator();
					rowArray[36] = new JRTableModelDataSource(getGRSubModel03(it2));
				}
  			    if(obj.getHfr4002Dbs()!=null && obj.getHfr4002Dbs().size()>0)
				{
					java.util.Iterator it2 = obj.getHfr4002Dbs().iterator();
					rowArray[37] = new JRTableModelDataSource(getGRSubModel04(it2));
				}
  			   if(obj.getHfr4002Dbs()!=null && obj.getHfr4002Dbs().size()>0)
				{
					java.util.Iterator it2 = obj.getHfr4002Dbs().iterator();
					rowArray[38] = new JRTableModelDataSource(getGRSubModel05(it2));
				}
  			   if(obj.getHfr4002Dbs()!=null && obj.getHfr4002Dbs().size()>0)
				{
  				    String row39 = "22.健康食品\n(";
				    String row40 = "23.健康食品\n(";
					java.util.Iterator it2 = obj.getHfr4002Dbs().iterator();
					while(it2.hasNext())
					{
						//判斷許可證字有"規"或無"規"
		  			    String permitKey = "";
		  			    String permitNo = "";
		  			    String permitKey1 = "";
					    String permitNo1 = "";
					    Hfr4002Db hfr4002db = (Hfr4002Db) it2.next();;
		  			    if((("H".equals(hfr4002db.getPermitKey())) || ("Q".equals(hfr4002db.getPermitKey())))) {
		  				  permitKey = getPermitKey(Common.get(hfr4002db.getPermitKey()));
		  				  permitNo = Common.get(hfr4002db.getPermitNo());
		  			  	} else {
		  			  		permitKey = "";
		  			  		permitNo = "";
		  			  	}
		  			    
		  			    if((("J".equals(hfr4002db.getPermitKey())) || ("R".equals(hfr4002db.getPermitKey())))) {
		  			    	permitKey1=getPermitKey(Common.get(hfr4002db.getPermitKey()));
		  			    	permitNo1=Common.get(hfr4002db.getPermitNo());
		  			  	} else {
		  			  		permitKey1="";
		  			  		permitNo1="";
		  			  	}
		  			    
		  			    if(!"".equals(permitKey)) {
		  			    	row39 +=  permitKey + "字號\n第" + permitNo + "號";
		  			    	row39 += "、";
		  			    }
		  			    if(!"".equals(permitKey1)) {
		  			    	row40 +=  permitKey1 + "字號\n第" + permitNo1 + "號";
		  			    	row40 += "、";
		  			    }
					}
					if("、".equals(row39.charAt(row39.length()-1))) {
						row39 = row39.substring(0,row39.length()-1);//去除最後一個頓號
					}
					if("、".equals(row40.charAt(row40.length()-1))) {
						row40 = row40.substring(0,row40.length()-1);//去除最後一個頓號
					}
					row39 += ")";
					row40 += ")";	  			    
					rowArray[39] = row39;
					rowArray[40] = row40;
				} else {
					rowArray[39] = "22.健康食品\n_____字號\n第_____號";
					rowArray[40] = "23.健康食品\n_____字號\n第_____號";
				}
  			    rowArray[41] = "24.非衛生署核淮為健康食品之錠、膠劑型食品";
  			    rowArray[42] = "25.併用藥品(西藥、中藥)";
  			    rowArray[43] = "26.併用其他錠、膠劑型食品";
   			   if(obj.getHfr4003Dbs()!=null && obj.getHfr4003Dbs().size()>0)
				{
					java.util.Iterator it2 = obj.getHfr4003Dbs().iterator();
					rowArray[44] = new JRTableModelDataSource(getGRSubModel06(it2));
				}
   			   if(obj.getHfr4004Dbs()!=null && obj.getHfr4004Dbs().size()>0)
				{
					java.util.Iterator it2 = obj.getHfr4004Dbs().iterator();
					rowArray[45] = new JRTableModelDataSource(getGRSubModel07(it2));
				}
  			    arrList.add(rowArray);
			}
		}
		if (arrList!=null && arrList.size()>0) 
		{
			Object[][] rs = new Object[0][0];
			rs = (Object[][])arrList.toArray(rs);
			model.setDataVector(rs, cols);
		}	
		return model;    	
	}
	
	//一般表-子報表路徑設定
	public void setGR_Parameter(java.util.HashMap<String, Object> params)
	{
		String gr_subreport0FilePath = 
			ServiceGetter.getInstance().getMyServletContext().getServletContext().getRealPath("/report/hfr/hfrin0102gr_subreport0.jasper");
		String gr_subreport1FilePath = 
			ServiceGetter.getInstance().getMyServletContext().getServletContext().getRealPath("/report/hfr/hfrin0102gr_subreport1.jasper");
		String gr_subreport2FilePath = 
			ServiceGetter.getInstance().getMyServletContext().getServletContext().getRealPath("/report/hfr/hfrin0102gr_subreport2.jasper");
		String gr_subreport3FilePath = 
			ServiceGetter.getInstance().getMyServletContext().getServletContext().getRealPath("/report/hfr/hfrin0102gr_subreport3.jasper");
		String gr_subreport4FilePath = 
			ServiceGetter.getInstance().getMyServletContext().getServletContext().getRealPath("/report/hfr/hfrin0102gr_subreport4.jasper");
		String gr_subreport5FilePath = 
			ServiceGetter.getInstance().getMyServletContext().getServletContext().getRealPath("/report/hfr/hfrin0102gr_subreport5.jasper");
		String gr_subreport6FilePath = 
			ServiceGetter.getInstance().getMyServletContext().getServletContext().getRealPath("/report/hfr/hfrin0102gr_subreport6.jasper");
		
		params.put("gr_subreport0", gr_subreport0FilePath);
		params.put("gr_subreport1", gr_subreport1FilePath);
		params.put("gr_subreport2", gr_subreport2FilePath);
		params.put("gr_subreport3", gr_subreport3FilePath);
		params.put("gr_subreport4", gr_subreport4FilePath);
		params.put("gr_subreport5", gr_subreport5FilePath);
		params.put("gr_subreport6", gr_subreport6FilePath);

	}
	//一般表子報表1
	public DefaultTableModel getGRSubModel01(java.util.Iterator it2) throws Exception
	{
		String[] col02 = new String[]{"chProduct","ingredient","content","doseNum","doseDay",
				"frequency","frequencyUnit","edibleDateS","edibleDateE","buySource"};	
		
		DefaultTableModel model = new javax.swing.table.DefaultTableModel();
		
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();	
		
		java.util.Map<String, String> frequencyUnitMap = TCBWCommon.getCommonCodeMap("DRG0305");

		while(it2.hasNext())
		{
			Hfr4002Db hfr4002Db = (Hfr4002Db)it2.next();
			String[] rowArray= new String[col02.length];
			
			rowArray[0]=Common.get(hfr4002Db.getChProduct());
			rowArray[1]=Common.get(hfr4002Db.getIngredient());
			rowArray[2]=Common.get(hfr4002Db.getContent());
			rowArray[3]=Common.get(hfr4002Db.getDoseNum());
			rowArray[4]=Common.get(hfr4002Db.getDoseDay());
			rowArray[5]=Common.get(hfr4002Db.getFrequency());
			rowArray[6]=Common.get(frequencyUnitMap.get(hfr4002Db.getFrequencyUnit()));
			rowArray[7]=Common.formatYYYMMDD(hfr4002Db.getEdibleDateS());
			rowArray[8]=Common.formatYYYMMDD(hfr4002Db.getEdibleDateE());
			rowArray[9]=getBuySource(Common.get(hfr4002Db.getBuySource()));
			
			if("H".equals(hfr4002Db.getPermitKey()) || "Q".equals(hfr4002Db.getPermitKey())) {
				arrList.add(rowArray);
			}
		}
		if(null != arrList && arrList.size() >0)
		{
			Object[][] rs = new Object[0][0];
	        rs = (Object[][])arrList.toArray(rs);
	        model.setDataVector(rs, col02);
		}
        return model;
	}	
	//一般表子報表2
	public DefaultTableModel getGRSubModel02(java.util.Iterator it2) throws Exception
	{
		String[] col02 = new String[]{"chProduct","ingredient","content","doseNum","doseDay",
				"frequency","frequencyUnit","edibleDateS","edibleDateE","buySource"};	
		
		DefaultTableModel model = new javax.swing.table.DefaultTableModel();
		
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		
		java.util.Map<String, String> frequencyUnitMap = TCBWCommon.getCommonCodeMap("DRG0305");

		while(it2.hasNext())
		{
			Hfr4002Db hfr4002Db = (Hfr4002Db)it2.next();
			String[] rowArray= new String[col02.length];
			
			rowArray[0]=Common.get(hfr4002Db.getChProduct());
			rowArray[1]=Common.get(hfr4002Db.getIngredient());
			rowArray[2]=Common.get(hfr4002Db.getContent());
			rowArray[3]=Common.get(hfr4002Db.getDoseNum());
			rowArray[4]=Common.get(hfr4002Db.getDoseDay());
			rowArray[5]=Common.get(hfr4002Db.getFrequency());
			rowArray[6]=Common.get(frequencyUnitMap.get(hfr4002Db.getFrequencyUnit()));
			rowArray[7]=Common.formatYYYMMDD(hfr4002Db.getEdibleDateS());
			rowArray[8]=Common.formatYYYMMDD(hfr4002Db.getEdibleDateE());
			rowArray[9]=getBuySource(Common.get(hfr4002Db.getBuySource()));

			if("J".equals(hfr4002Db.getPermitKey()) || "R".equals(hfr4002Db.getPermitKey())) {
				arrList.add(rowArray);
			}
		}
		if(null != arrList && arrList.size() >0)
		{
			Object[][] rs = new Object[0][0];
	        rs = (Object[][])arrList.toArray(rs);
	        model.setDataVector(rs, col02);
		}
        return model;
	}	
	
	//一般表子報表3
	public DefaultTableModel getGRSubModel03(java.util.Iterator it2) throws Exception
	{
		String[] col02 = new String[]{"chProduct","ingredient","content","doseNum","doseDay",
				"frequency","frequencyUnit","edibleDateS","edibleDateE","buySource"};	
		
		DefaultTableModel model = new javax.swing.table.DefaultTableModel();
		
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();	
		
		java.util.Map<String, String> frequencyUnitMap = TCBWCommon.getCommonCodeMap("DRG0305");

		while(it2.hasNext())
		{
			Hfr4002Db hfr4002Db = (Hfr4002Db)it2.next();
			String[] rowArray= new String[col02.length];
			
			rowArray[0]=Common.get(hfr4002Db.getChProduct());
			rowArray[1]=Common.get(hfr4002Db.getIngredient());
			rowArray[2]=Common.get(hfr4002Db.getContent());
			rowArray[3]=Common.get(hfr4002Db.getDoseNum());
			rowArray[4]=Common.get(hfr4002Db.getDoseDay());
			rowArray[5]=Common.get(hfr4002Db.getFrequency());
			rowArray[6]=Common.get(frequencyUnitMap.get(hfr4002Db.getFrequencyUnit()));
			rowArray[7]=Common.formatYYYMMDD(hfr4002Db.getEdibleDateS());
			rowArray[8]=Common.formatYYYMMDD(hfr4002Db.getEdibleDateE());
			rowArray[9]=getBuySource(Common.get(hfr4002Db.getBuySource()));
			
			if("N".equals(hfr4002Db.getType())) {
				arrList.add(rowArray);
			}
		}
		if(null != arrList && arrList.size() >0)
		{
			Object[][] rs = new Object[0][0];
	        rs = (Object[][])arrList.toArray(rs);
	        model.setDataVector(rs, col02);
		}
        return model;
	}	
	
	//一般表子報表4
	public DefaultTableModel getGRSubModel04(java.util.Iterator it2) throws Exception
	{
		String[] col02 = new String[]{"chProduct","enProduct","content","medModel","doseNum","doseDay",
				"frequency","frequencyUnit","edibleDateS","edibleDateE","brand","permitNo"};	
		
		DefaultTableModel model = new javax.swing.table.DefaultTableModel();
		
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();	
		
		java.util.Map<String, String> frequencyUnitMap = TCBWCommon.getCommonCodeMap("DRG0305");

		while(it2.hasNext())
		{
			Hfr4002Db hfr4002Db = (Hfr4002Db)it2.next();
			String[] rowArray= new String[col02.length];
			
			rowArray[0]=Common.get(hfr4002Db.getChProduct());
			rowArray[1]=Common.get(hfr4002Db.getEnProduct());
			rowArray[2]=Common.get(hfr4002Db.getContent());
			rowArray[3]=Common.get(hfr4002Db.getMedModel());
			rowArray[4]=Common.get(hfr4002Db.getDoseNum());
			rowArray[5]=Common.get(hfr4002Db.getDoseDay());
			rowArray[6]=Common.get(hfr4002Db.getFrequency());
			rowArray[7]=Common.get(frequencyUnitMap.get(hfr4002Db.getFrequencyUnit()));
			rowArray[8]=Common.formatYYYMMDD(hfr4002Db.getEdibleDateS());
			rowArray[9]=Common.formatYYYMMDD(hfr4002Db.getEdibleDateE());
			rowArray[10]=Common.get(hfr4002Db.getBrand());
			rowArray[11]=Common.get(hfr4002Db.getPermitKey());
			
			if("D".equals(hfr4002Db.getType())) {
				arrList.add(rowArray);
			}
		}
		if(null != arrList && arrList.size() >0)
		{
			Object[][] rs = new Object[0][0];
	        rs = (Object[][])arrList.toArray(rs);
	        model.setDataVector(rs, col02);
		}
        return model;
	}	
	
	//一般表子報表5
	public DefaultTableModel getGRSubModel05(java.util.Iterator it2) throws Exception
	{
		String[] col02 = new String[]{"chProduct","enProduct","content","medModel","doseNum","doseDay",
				"frequency","frequencyUnit","edibleDateS","edibleDateE","brand","permitNo"};	
		
		DefaultTableModel model = new javax.swing.table.DefaultTableModel();
		
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();	
		
		java.util.Map<String, String> frequencyUnitMap = TCBWCommon.getCommonCodeMap("DRG0305");

		while(it2.hasNext())
		{
			Hfr4002Db hfr4002Db = (Hfr4002Db)it2.next();
			String[] rowArray= new String[col02.length];
			
			rowArray[0]=Common.get(hfr4002Db.getChProduct());
			rowArray[1]=Common.get(hfr4002Db.getEnProduct());
			rowArray[2]=Common.get(hfr4002Db.getContent());
			rowArray[3]=Common.get(hfr4002Db.getMedModel());
			rowArray[4]=Common.get(hfr4002Db.getDoseNum());
			rowArray[5]=Common.get(hfr4002Db.getDoseDay());
			rowArray[6]=Common.get(hfr4002Db.getFrequency());
			rowArray[7]=Common.get(frequencyUnitMap.get(hfr4002Db.getFrequencyUnit()));
			rowArray[8]=Common.formatYYYMMDD(hfr4002Db.getEdibleDateS());
			rowArray[9]=Common.formatYYYMMDD(hfr4002Db.getEdibleDateE());
			rowArray[10]=Common.get(hfr4002Db.getBrand());
			rowArray[11]=Common.get(hfr4002Db.getPermitKey());
			
			if("O".equals(hfr4002Db.getType())) {
				arrList.add(rowArray);
			}
		}
		if(null != arrList && arrList.size() >0)
		{
			Object[][] rs = new Object[0][0];
	        rs = (Object[][])arrList.toArray(rs);
	        model.setDataVector(rs, col02);
		}
        return model;
	}
	
	//一般表子報表6
	public DefaultTableModel getGRSubModel06(java.util.Iterator it2) throws Exception
	{
		String[] col02 = new String[]{"discriptDate","position","symptom","severity","doResponse"};	
		
		DefaultTableModel model = new javax.swing.table.DefaultTableModel();
		
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();	
		
		while(it2.hasNext())
		{
			Hfr4003Db hfr4003Db = (Hfr4003Db)it2.next();
			String[] rowArray= new String[col02.length];
			
			rowArray[0]=Common.formatYYYMMDD(Common.get(hfr4003Db.getDiscriptDate()), 2);
			rowArray[1]=Common.get(hfr4003Db.getPosition());
			rowArray[2]=Common.get(hfr4003Db.getSymptom());
			rowArray[3]=Common.get(hfr4003Db.getSeverity());
			rowArray[4]=Common.get(hfr4003Db.getDoResponse());
			
			
			arrList.add(rowArray);
			
		}
		if(null != arrList && arrList.size() >0)
		{
			Object[][] rs = new Object[0][0];
	        rs = (Object[][])arrList.toArray(rs);
	        model.setDataVector(rs, col02);
		}
        return model;
	}
	//一般表子報表7
	public DefaultTableModel getGRSubModel07(java.util.Iterator it2) throws Exception
	{
		String[] col02 = new String[]{"doctorDate","bloodIndex","liverIndex"};	
		
		DefaultTableModel model = new javax.swing.table.DefaultTableModel();
		
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();	
		
		while(it2.hasNext())
		{
			Hfr4004Db hfr4004Db = (Hfr4004Db)it2.next();
			String[] rowArray= new String[col02.length];
			
			rowArray[0]=Common.formatYYYMMDD(Common.get(hfr4004Db.getDoctorDate()), 2);
			rowArray[1]=Common.get(hfr4004Db.getBloodIndex());
			rowArray[2]=Common.get(hfr4004Db.getLiverIndex());
			arrList.add(rowArray);
			
		}
		if(null != arrList && arrList.size() >0)
		{
			Object[][] rs = new Object[0][0];
	        rs = (Object[][])arrList.toArray(rs);
	        model.setDataVector(rs, col02);
		}
        return model;
	}
	
	

	//Common_CodeKind查詢條件
	public String getCommonCodeKindHQL(String codeKindId) {
		String HQL = "from CommonCode where 1=1 and codeKindId ='" + codeKindId + "'";
		return HQL;
	}
	
	//職稱
	public String getNotifierTitle(String NotifierStaffTitle) 
	{
		String hql = getCommonCodeKindHQL("52");

		String rowArray2 = "";
		
		java.util.List list = ServiceGetter.getInstance().getCommonService().load(hql);
		if (list!=null && list.size()>0) 
		{
			for (int i=0; i<list.size(); i++) 
			{
				CommonCode obj = (CommonCode) list.get(i);
				String checkbox = "□";				
				if(obj.getCodeId().equals(NotifierStaffTitle)) 
				{
					checkbox = "■" + obj.getCodeName();
				}
				else
				{
					checkbox = checkbox + obj.getCodeName();
				}
				if(i==2) {
					rowArray2 += checkbox + "　\n";
				} else {
					rowArray2 += checkbox +"　";
				}
			}
		}
		return rowArray2;
		
	}
	
	//非預期反應結果
	public String getUnReactionResults(String UnReactionResults,String DeathDate,String DeathResult,String EndangerLife,String OtherNonResponse) {
		String hql = getCommonCodeKindHQL("56");
		String rowArray2 = "";
		String[] UnReactionResultsList = UnReactionResults.split(",");
		
		java.util.List list = ServiceGetter.getInstance().getCommonService().load(hql);
		if (list!=null && list.size()>0) 
		{
			for (int i=0; i<list.size(); i++) 
			{
				CommonCode obj = (CommonCode) list.get(i);
				
				String check = "□";
				String check2 = "□";
				for(int j=0; j<UnReactionResultsList.length; j++) 
				{
					if(obj.getCodeId().equals(UnReactionResultsList[j]))
					{
						check = "■";
						if("1".equals(UnReactionResultsList[j]))
						{
							check = check + obj.getCodeName() + "，日期：" + DeathDate + "，死亡原因：" + DeathResult + "　或危及生命：" + EndangerLife + "\n"; //不良反應結果為死亡時帶入日期與死亡原因
						} else if("7".equals(UnReactionResultsList[j]))
						{
							check2 = "■";
							check2 = check2 + obj.getCodeName() + OtherNonResponse;
						}
					}
					
				}
				if(i == 0) 
				{
					if("■".equals(check.substring(0,1))) {
						rowArray2 += check + "\n";//不良反應結果為死亡時
					} else {
						rowArray2 += check + obj.getCodeName() +  "，日期___年___月___日，死亡原因：________　或危及生命：_______\n";//非死亡時
					}
					
				} else if(i == 7)
				{
					if("■".equals(check.substring(0,1))) {
						rowArray2 += check2 + "\n";//不良反應結果為非嚴重不良反應時
					} else {
						rowArray2 += check + obj.getCodeName() +  "之非預期反應________\n";//非嚴重不良反應時
					}
					
				}
				else 
				{
					rowArray2 += check + obj.getCodeName() + "\n";
				}
			}
		}
		return rowArray2;
	}
	
	//其他相關描述
	public String getOtherrElevantInformation(String OtherrElevantInformation,String AllergicDescription,String OthersDescribeHistory) {
		String hql = getCommonCodeKindHQL("109");

		String rowArray2 = "";
		String[] dh = OtherrElevantInformation.split(",");
		java.util.List list = ServiceGetter.getInstance().getCommonService().load(hql);
		if (list!=null && list.size()>0) 
		{
			for (int i=0; i<list.size(); i++) 
			{
				CommonCode obj = (CommonCode) list.get(i);
				String checkbox = "□";	
				checkbox = checkbox + obj.getCodeName();
				if("1".equals(obj.getCodeId()) || "6".equals(obj.getCodeId()))
				{
					
					checkbox += "__________";
				}
				for(int j=0; j < dh.length; j++) {
					if(obj.getCodeId().equals(dh[j])) {
						CommonCode commoncode = (CommonCode)View.getObject("from CommonCode where 1=1 and CodeKindId = '109' and codeId ='" + dh[j] + "'");
						checkbox = "■" + obj.getCodeName();
						if("1".equals(dh[j])) {
							checkbox += AllergicDescription;
						}
						if("6".equals(OtherrElevantInformation))
						{
							checkbox += OthersDescribeHistory;
						}
					}
				}
				rowArray2 += checkbox +"　"+"\n";
			}
		}
		return rowArray2;
		
	}
	
	//疾病史
	public String getDiseaseHistory(String DiseaseHistory) {
		String hql = getCommonCodeKindHQL("54");

		String rowArray2 = "";
		String[] dh = DiseaseHistory.split(",");
		java.util.List list = ServiceGetter.getInstance().getCommonService().load(hql);
		if (list!=null && list.size()>0) 
		{
			for (int i=0; i<list.size(); i++) 
			{
				CommonCode obj = (CommonCode) list.get(i);
				String checkbox = "□";
				checkbox = checkbox + obj.getCodeName();
				
				for(int j=0; j < dh.length; j++) {
					if(obj.getCodeId().equals(dh[j])) {
						CommonCode commoncode = (CommonCode)View.getObject("from CommonCode where 1=1 and CodeKindId = '54' and codeId ='" + dh[j] + "'");
						checkbox = "■" + obj.getCodeName();
					}
				}
				rowArray2 += checkbox +"　";
			}
			
		}
		return rowArray2;
	}
	
	//生活史
	public String getLlifeHistory(String LlifeHistory) {
		String hql = getCommonCodeKindHQL("55");

		String rowArray2 = "";
		String[] dh = LlifeHistory.split(",");
		java.util.List list = ServiceGetter.getInstance().getCommonService().load(hql);
		if (list!=null && list.size()>0) 
		{
			for (int i=0; i<list.size(); i++) 
			{
				CommonCode obj = (CommonCode) list.get(i);
				String checkbox = "□";				
				for(int j=0; j < dh.length; j++) {
					if(obj.getCodeId().equals(dh[j])) {
						CommonCode commoncode = (CommonCode)View.getObject("from CommonCode where 1=1 and CodeKindId = '54' and codeId ='" + dh[j] + "'");
						checkbox = "■" + obj.getCodeName();
					}
				}
				rowArray2 += checkbox +"　";
			}
		}
		return rowArray2;
		
	}
	
	//子報表-購買來源
	public String getBuySource(String BuySource) {
		String hql = getCommonCodeKindHQL("58");

		String rowArray2 = "";
		java.util.List list = ServiceGetter.getInstance().getCommonService().load(hql);
		if (list!=null && list.size()>0) 
		{
			for (int i=0; i<list.size(); i++) 
			{
				CommonCode obj = (CommonCode) list.get(i);
				String checkbox = "";				
				if(obj.getCodeId().equals(BuySource)) 
				{
					checkbox += obj.getCodeName();
				}
				rowArray2 += checkbox;
			}
		}
		return rowArray2;
	}
	
	//子報表-許可證字
	public String getPermitKey(String PermitKey) {
		String hql = getCommonCodeKindHQL("53");

		String rowArray2 = "";
		java.util.List list = ServiceGetter.getInstance().getCommonService().load(hql);
		if (list!=null && list.size()>0) 
		{
			for (int i=0; i<list.size(); i++) 
			{
				CommonCode obj = (CommonCode) list.get(i);
				String checkbox = "";				
				if(obj.getCodeId().equals(PermitKey)) 
				{
					checkbox += obj.getCodeName();
				}
				rowArray2 += checkbox;
			}
		}
		return rowArray2;
	}
	
	public void doInsert()throws Exception{
		ServiceGetter.getInstance().getTcbwService().getHfrexDao().insertByHFREX0104F(this);
	}
	
	public Object doQueryOne() throws Exception {
		HFREX0104F obj = this;
		Hfr4001Db c = (Hfr4001Db)View.getObject(" from Hfr4001Db where id = " + Common.getLong(getId()));
		if(c != null){
			obj.setId(Common.get(c.getId()));
			obj.setApplNo(c.getApplNo());
			obj.setHfrType(c.getHfrType());
			obj.setStatus(c.getStatus());
			obj.setNotifierRevDate(c.getNotifierRevDate());
			obj.setNotifierRepDate(c.getNotifierRepDate());
			obj.setOccurDate(c.getOccurDate());
			obj.setNotifierName(c.getNotifierName());
			obj.setNotifierIdNum(c.getNotifierIdNum());
			obj.setNotifierTelArea(c.getNotifierTelArea());
			obj.setNotifierTel(c.getNotifierTel());
			obj.setNotifierTelExt(c.getNotifierTelExt());
			obj.setNotifierType(c.getNotifierType());
			obj.setNotifierDept(c.getNotifierDept());
			obj.setNotifierDeptID(c.getNotifierDeptId());
			obj.setNotifierEamil(c.getNotifierEamil());
			obj.setNotifierTitle(c.getNotifierTitle());
			obj.setNotifierArea(c.getNotifierArea());
			obj.setNotifierZipCode(c.getNotifierZipCode());
			obj.setAddress(c.getAddress());
			
			if(!"".equals(Common.get(c.getNotifierName()))){
				if(Common.get(c.getEatersName()).equals(Common.get(c.getNotifierName()))){
					obj.setIsSameNotifier("Y");
				}else{
					obj.setIsSameNotifier("N");
				}
			}else{
				obj.setIsSameNotifier("");
			}
			
			obj.setEatersName(c.getEatersName());
			obj.setEatersId(c.getEatersId());
			obj.setEatersSex(c.getEatersSex());
			obj.setEatersBirthYear(c.getEatersBirthYear());
			obj.setEatersHight(c.getEatersHight());
			obj.setEatersWeight(c.getEatersWeight());
			obj.setEatersIdNum(c.getEatersIdNum());
			obj.setEatersTelArea(c.getEatersTelArea());
			obj.setEatersTel(c.getEatersTel());
			obj.setEatersTelExt(c.getEatersTelExt());
			obj.setDrugAllergies(c.getDrugAllergies());
			obj.setDrugOther(c.getDrugOther());
			obj.setFoodAllergies(c.getFoodAllergies());
			obj.setFoodOther(c.getFoodOther());
			obj.setIsReactionResult(c.getIsReactionResult());
			obj.setInformedSources(c.getInformedSources());
			if(!"".equals(Common.get(c.getDiseaseHistory()))){
				String[] t = Common.get(c.getDiseaseHistory()).split(",");
				obj.setDiseaseHistory(t);
			}else{
				obj.setDiseaseHistory(null);
			}
			obj.setDiseaseOther(c.getDiseaseOther());
			if(!"".equals(Common.get(c.getLifeHistory()))){
				String[] t = Common.get(c.getLifeHistory()).split(",");
				obj.setLifeHistory(t);
			}else{
				obj.setLifeHistory(null);
			}
			obj.setLifeOther(c.getLifeOther());
			
			obj.setUnHealthIsYes(c.getUnHealthIsYes());
			obj.setUnHealthBrief(c.getUnHealthBrief());
			obj.setUnReactionResults(c.getUnReactionResults());
			obj.setDeathDate(c.getDeathDate());
			obj.setDeathResult(c.getDeathResult());
			obj.setEndangerLife(c.getEndangerLife());
			obj.setOtherNonResponse(c.getOtherNonResponse());
			if(!"".equals(Common.get(c.getOtherrElevantInformation()))){
				String[] tmp = Common.get(c.getOtherrElevantInformation()).split(",");
				obj.setOtherrElevantInformation(tmp);
			}else{
				obj.setOtherrElevantInformation(null);
			}
			obj.setAllergicDescription(c.getAllergicDescription());
			obj.setOthersDescribeHistory(c.getOthersDescribeHistory());
			
			genHfr4003DbItemSet(c.getHfr4003Dbs());
			genHfr4004DbItemSet(c.getHfr4004Dbs());
			if(c.getHfr4002Dbs()!=null && c.getHfr4002Dbs().size()>0){
				java.util.List<Hfr4002Db> gFood = new java.util.ArrayList<Hfr4002Db>();
				java.util.List<Hfr4002Db> nFood = new java.util.ArrayList<Hfr4002Db>();
				java.util.List<Hfr4002Db> dFood = new java.util.ArrayList<Hfr4002Db>();
				java.util.List<Hfr4002Db> oFood = new java.util.ArrayList<Hfr4002Db>();
				
				for(Object dtlObj : c.getHfr4002Dbs()){
					Hfr4002Db dtl = (Hfr4002Db)dtlObj;
					if("G".equals(Common.get(dtl.getType()))){
						gFood.add(dtl);
					}else if("N".equals(Common.get(dtl.getType()))){
						nFood.add(dtl);
					}else if("D".equals(Common.get(dtl.getType()))){
						dFood.add(dtl);
					}else if("O".equals(Common.get(dtl.getType()))){
						oFood.add(dtl);
					}
				}
				obj.genGHfr4002DbItemSet(gFood);
				obj.genNHfr4002DbItemSet(nFood);
				obj.genDrHfr4002DbItemSet(dFood, "dr");
				obj.genDrHfr4002DbItemSet(oFood, "odr");
			}
			obj.setAgainEatingHealthFood(c.getAgainEatingHealthFood());
			obj.setHealthFoodName(c.getHealthFoodName());
			obj.setAgainOtherNonResponse(c.getAgainOtherNonResponse());
			obj.setStopEatingReaction(c.getStopEatingReaction());
			obj.setAgainEatingReaction(c.getAgainEatingReaction());
			
			java.util.List<Con0001Db> fileList = ServiceGetter.getInstance().getTcbwService().load(" from Con0001Db where fileKind = 'H' and dbName = 'HFR4001DB' and upLoadId = " + Common.getLong(obj.getId()));
			if(fileList!=null && fileList.size()>0){
				obj.genFileRowItemSet(fileList, "food");				
			}
		}
		return  obj;
	}
	
	public void doDelete() throws Exception{
		ServiceGetter.getInstance().getTcbwService().getHfrexDao().deleteByHFREX0104F(this);
	}
	
	public void doUpdateType() throws Exception{
		ServiceGetter.getInstance().getTcbwService().getHfrexDao().updateByHFREX0104F(this, false);
	}
	
	
	
}
