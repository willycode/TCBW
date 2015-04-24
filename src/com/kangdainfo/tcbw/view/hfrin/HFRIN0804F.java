package com.kangdainfo.tcbw.view.hfrin;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Con0001Db;
import com.kangdainfo.tcbw.model.bo.Hfr0001Db;
import com.kangdainfo.tcbw.model.bo.Hfr0002Db;
import com.kangdainfo.tcbw.model.bo.Hfr0005Db;
import com.kangdainfo.tcbw.model.bo.Hfr0006Db;
import com.kangdainfo.tcbw.model.bo.Hfr0007Db;

public class HFRIN0804F extends HFRIN0801F implements HFRIN0002F {
	
	private String notifierRevDate;
	private String notifierRepDate;
	private String occurDate;
	private String isSameNotifier;
	private String caseOwner;
	private String notifierName;
	private String notifierTelArea;
	private String notifierTel;
	private String notifierTelExt;
	private String notifierIdNum;
	private String notifierEamil;
	private String notifierType;
	private String notifierDeptID;
	private String notifierDept;
	private String notifierTitle;
	private String address;
	private String notifierArea;						// 縣市
	private String notifierZipCode;						// 郵遞區號
	private String eatersTelArea;
	private String eatersTelExt;
	private String foodOther;
	private String drugOther;
	private String diseaseOther;
	private String lifeOther;
	
	private String isCloseUserInfo;		//列印時是否遮蔽個資
	
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
	private String[] diseaseHistory;
	private String[] lifeHistory;
	
	public String getFoodOther() {		return checkGet(foodOther);	}
	public void setFoodOther(String foodOther) {		this.foodOther = checkSet(foodOther);	}
	public String getDrugOther() {		return checkGet(drugOther);	}
	public void setDrugOther(String drugOther) {		this.drugOther = checkSet(drugOther);	}
	public String getDiseaseOther() {		return checkGet(diseaseOther);	}
	public void setDiseaseOther(String diseaseOther) {		this.diseaseOther = checkSet(diseaseOther);	}
	public String getLifeOther() {		return checkGet(lifeOther);	}
	public void setLifeOther(String lifeOther) {		this.lifeOther = checkSet(lifeOther);	}
	public String getCaseOwner() {		return checkGet(caseOwner);	}
	public void setCaseOwner(String caseOwner) {		this.caseOwner = checkSet(caseOwner);	}
	public String getNotifierTelArea() {		return checkGet(notifierTelArea);	}
	public void setNotifierTelArea(String notifierTelArea) {		this.notifierTelArea = checkSet(notifierTelArea);	}
	public String getNotifierTelExt() {		return checkGet(notifierTelExt);	}
	public void setNotifierTelExt(String notifierTelExt) {		this.notifierTelExt = checkSet(notifierTelExt);	}
	public String getNotifierDeptID() {		return checkGet(notifierDeptID);	}
	public void setNotifierDeptID(String notifierDeptID) {		this.notifierDeptID = checkSet(notifierDeptID);	}
	public String getNotifierArea() {		return checkGet(notifierArea);	}
	public void setNotifierArea(String notifierArea) {		this.notifierArea = checkSet(notifierArea);	}
	public String getNotifierZipCode() {		return checkGet(notifierZipCode);	}
	public void setNotifierZipCode(String notifierZipCode) {		this.notifierZipCode = checkSet(notifierZipCode);	}
	public String getEatersTelArea() {		return checkGet(eatersTelArea);	}
	public void setEatersTelArea(String eatersTelArea) {		this.eatersTelArea = checkSet(eatersTelArea);	}	
	public String getEatersTelExt() {		return checkGet(eatersTelExt);	}
	public void setEatersTelExt(String eatersTelExt) {		this.eatersTelExt = checkSet(eatersTelExt);	}
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
	public String[] getDiseaseHistory() {		return diseaseHistory;}
	public void setDiseaseHistory(String[] diseaseHistory) {		this.diseaseHistory = diseaseHistory;	}
	public String[] getLifeHistory() {		return lifeHistory;	}
	public void setLifeHistory(String[] lifeHistory) {		this.lifeHistory = lifeHistory;	}
	
	public String getIsCloseUserInfo() {		return checkGet(isCloseUserInfo);	}
	public void setIsCloseUserInfo(String isCloseUserInfo) {		this.isCloseUserInfo = checkSet(isCloseUserInfo);	}
	
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

	public String getIsHiddenPersonalInfo() {		return "Y";	}
	public String getIsShowOpenPersonalInfo() {		return "Y";	}
	public String getIsShowUserButton() {		return "N";	}
	public String getIsNeedShowPermitKey() {		return "Y";	}
	public String getIsNeedShowDocNo() {		return "Y";	}
	public String getIsShowPreAssessmentPage() {		return "Y";	}
	
	// 調查頁籤資料
	private String medicalPostingDate;
	private String medicalCompleteDate;
	private String inspectPostingDate;
	private String inspectCompleteDate;
	
	public String getMedicalPostingDate() {		return checkGet(medicalPostingDate);	}
	public void setMedicalPostingDate(String medicalPostingDate) {		this.medicalPostingDate = checkSet(medicalPostingDate);	}
	public String getMedicalCompleteDate() {		return checkGet(medicalCompleteDate);	}
	public void setMedicalCompleteDate(String medicalCompleteDate) {		this.medicalCompleteDate = checkSet(medicalCompleteDate);	}
	public String getInspectPostingDate() {		return checkGet(inspectPostingDate);	}
	public void setInspectPostingDate(String inspectPostingDate) {		this.inspectPostingDate = checkSet(inspectPostingDate);	}
	public String getInspectCompleteDate() {		return checkGet(inspectCompleteDate);	}
	public void setInspectCompleteDate(String inspectCompleteDate) {		this.inspectCompleteDate = checkSet(inspectCompleteDate);	}
	
	public Object doQueryOne() throws Exception {
		HFRIN0804F obj = this;
		Hfr0001Db c = (Hfr0001Db)View.getObject(" from Hfr0001Db where id = " + Common.getLong(getId()));
		if(c != null){
			obj.setHfr40001Id(Common.get(c.getHfr4001DbId()));
			obj.setId(Common.get(c.getId()));
			obj.setApplNo(c.getApplNo());
			obj.setHfrType(c.getHfrType());
			obj.setStatus(c.getStatus());
			obj.setAssignMan(c.getAssignMan());
			obj.setNotifierRevDate(c.getNotifierRevDate());
			obj.setNotifierRepDate(c.getNotifierRepDate());
			obj.setOccurDate(c.getOccurDate());
			
			obj.setCaseOwner(c.getCaseOwner());
			obj.setNotifierName(c.getNotifierName());	
			obj.setNotifierTelArea(c.getNotifierTelArea());
			obj.setNotifierTel(c.getNotifierTel());
			obj.setNotifierTelExt(c.getNotifierTelExt());
			obj.setNotifierIdNum(c.getNotifierIdNum());
			obj.setNotifierType(c.getNotifierType());
			obj.setNotifierDeptID(c.getNotifierDeptId());
			obj.setNotifierDept(View.getNotifierDeptName(obj.getNotifierDeptID(), obj.getNotifierType()));
			
			obj.setNotifierArea(c.getNotifierArea());
			obj.setAddress(c.getAddress());
			obj.setNotifierZipCode(c.getNotifierZipCode());
			obj.setNotifierEamil(c.getNotifierEamil());
			obj.setNotifierTitle(c.getNotifierTitle());
			
			obj.setEatersId(c.getEatersId());
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
			
			genHfr0003DbItemSet(c.getHfr0003Dbs());
			genHfr0004DbItemSet(c.getHfr0004Dbs());
			if(c.getHfr0002Dbs()!=null && c.getHfr0002Dbs().size()>0){
				java.util.List<Hfr0002Db> gFood = new java.util.ArrayList<Hfr0002Db>();
				java.util.List<Hfr0002Db> nFood = new java.util.ArrayList<Hfr0002Db>();
				java.util.List<Hfr0002Db> dFood = new java.util.ArrayList<Hfr0002Db>();
				java.util.List<Hfr0002Db> oFood = new java.util.ArrayList<Hfr0002Db>();
				
				for(Object dtlObj : c.getHfr0002Dbs()){
					Hfr0002Db dtl = (Hfr0002Db)dtlObj;
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
				obj.genGHfr0002DbItemSet(gFood);
				obj.genNHfr0002DbItemSet(nFood);
				obj.genDrHfr0002DbItemSet(dFood, "dr");
				obj.genDrHfr0002DbItemSet(oFood, "odr");
			}
			
			obj.setAgainEatingHealthFood(c.getAgainEatingHealthFood());
			obj.setHealthFoodName(c.getHealthFoodName());
			obj.setAgainOtherNonResponse(c.getAgainOtherNonResponse());
			obj.setStopEatingReaction(c.getStopEatingReaction());
			obj.setAgainEatingReaction(c.getAgainEatingReaction());
			
			obj.setMedicalPostingDate(c.getMedicalPostingDate());
			obj.setMedicalCompleteDate(c.getMedicalCompleteDate());
			obj.setInspectPostingDate(c.getInspectPostingDate());
			obj.setInspectCompleteDate(c.getInspectCompleteDate());
			
			java.util.List<Con0001Db> fileList = new java.util.ArrayList<Con0001Db>();
			fileList = ServiceGetter.getInstance().getTcbwService().load(" from Con0001Db where fileKind = 'H' and dbName = 'HFR0001DB' and upLoadId = " + Common.getLong(c.getId()));
			obj.genFileRowItemSet(fileList, "food");
			fileList.clear();
			
			fileList = ServiceGetter.getInstance().getTcbwService().load(" from Con0001Db where fileKind = 'HFRFM' and dbName = 'HFR0001DB' and upLoadId = " + Common.getLong(c.getId()));
			obj.genFileRowItemSet(fileList, "HFRFM");
			fileList.clear();
			
			fileList = ServiceGetter.getInstance().getTcbwService().load(" from Con0001Db where fileKind = 'HFRFI' and dbName = 'HFR0001DB' and upLoadId = " + Common.getLong(c.getId()));
			obj.genFileRowItemSet(fileList, "HFRFI");
			fileList.clear();
			
			// HFR0005_DB，最新一筆需秀在畫面上
			java.util.List<Hfr0005Db> hfr0005DbList = ServiceGetter.getInstance().getTcbwService().load(" from Hfr0005Db where hfr0001Db.id = " + Common.getLong(c.getId()) + " order by id desc ");
			if(hfr0005DbList!=null && hfr0005DbList.size()>0){
				boolean flag = true;
				java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
				for(Hfr0005Db dtl : hfr0005DbList){
					if(flag){
						obj.setHfr0005Id(Common.get(dtl.getId()));
						obj.setPreCompleteDate(dtl.getPreCompleteDate());
						obj.setAssessmentSendDate(dtl.getAssessmentSendDate());
						obj.setAssessmentCompleteDate(dtl.getAssessmentCompleteDate());
						obj.setCaseType(dtl.getCaseType());
						obj.setUnExpectedClassify(dtl.getUnExpectedClassify());
						if(!"".equals(Common.get(dtl.getUnExpectedReason()))){
							String[] t = Common.get(dtl.getUnExpectedReason()).split(",");
							obj.setUnExpectedReason(t);
						}else{
							obj.setUnExpectedReason(null);
						}
						obj.setEvidentiary(dtl.getEvidentiary());
						obj.setPreSeverity(dtl.getPreSeverity());
						obj.setAdministrativeLevel(dtl.getAdministrativeLevel());
						obj.setPreOpinion(dtl.getPreOpinion());
						obj.setPreRemark(dtl.getPreRemark());
						obj.setPreExplain(dtl.getPreExplain());
						obj.setUnPreExplain(dtl.getUnPreExplain());
						
						fileList = ServiceGetter.getInstance().getTcbwService().load(" from Con0001Db where fileKind = 'HFRFO' and dbName = 'HFR0005DB' and upLoadId = " + Common.getLong(dtl.getId()));
						genFileRowItemSet(fileList, "HFRFO");
						fileList.clear();
						flag = false;
					}
					if("Y".equals(Common.get(dtl.getIsClosed()))){
						String[] rowArray = new String[3];
						rowArray[0] = Common.get(dtl.getId());
						rowArray[1] = Common.get(dtl.getPreCompleteDate());
						rowArray[2] = Common.get(dtl.getAssessmentSendDate());
						arrList.add(rowArray);
					}
				}
				obj.setHfr0005DbList(arrList);
				hfr0005DbList.clear();
			}else{
				obj.setHfr0005DbList(null);
				obj.setHfr0005Id("");
			}
			
			// HFR0006_DB
			java.util.List<Hfr0006Db> hfr0006DbList = ServiceGetter.getInstance().getTcbwService().load(" from Hfr0006Db where hfr0001Db.id = " + Common.getLong(c.getId()) + " order by id desc ");
			if(hfr0006DbList!=null && hfr0006DbList.size()>0){
				boolean flag = true;
				java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
				for(Hfr0006Db dtl : hfr0006DbList){
					if(flag){
						obj.setHfr0006Id(Common.get(dtl.getId()));
						obj.setEvaluateDate(dtl.getEvaluateDate());
						obj.setEvaluateCommittee(Common.get(dtl.getEvaluateCommittee()));
						obj.setEvaluateCommitteeName(View.getLookupField("select hfr1001Db.name from Hfr1002Db where id = " + Common.getLong(dtl.getEvaluateCommittee())));
						obj.setUnExpectedClassify(dtl.getUnExpectedClassify());
						if(!"".equals(Common.get(dtl.getUnExpectedReason()))){
							String[] t = Common.get(dtl.getUnExpectedReason()).split(",");
							obj.setSecUnExpectedReason(t);
						}else{
							obj.setSecUnExpectedReason(null);
						}
						obj.setSecEvidentiary(dtl.getEvidentiary());
						obj.setSecSeverity(dtl.getSecSeverity());
						obj.setSecAdministrativeLevel(dtl.getAdministrativeLevel());
						obj.setAssessments(dtl.getAssessments());
						obj.setEvaluateResult(dtl.getEvaluateResult());
						obj.setSecExplain(dtl.getSecExplain());
						obj.setUnSecExplain(dtl.getUnSecExplain());
						
						fileList = ServiceGetter.getInstance().getTcbwService().load(" from Con0001Db where fileKind = 'HFRAS' and dbName = 'HFR0006DB' and upLoadId = " + Common.getLong(dtl.getId()));
						genFileRowItemSet(fileList, "HFRAS");
						flag = false;
					}
					String[] rowArray = new String[4];
					rowArray[0] = Common.get(dtl.getId());
					rowArray[1] = Common.get(dtl.getEvaluateDate());
					rowArray[2] = View.getLookupField("select hfr1001Db.name from Hfr1002Db where id = " + Common.getLong(dtl.getEvaluateCommittee()));
					rowArray[3] = Common.get(dtl.getIsClosed());
					arrList.add(rowArray);
				}
				obj.setHfr0006DbList(arrList);
			}else{
				obj.setHfr0006DbList(null);
				obj.setHfr0006Id("");
			}
			
			// HFR0007_DB
			if(c.getHfr0007Dbs()!=null && c.getHfr0007Dbs().size()>0){
				boolean flag = true;
				java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
				for(Object dtlObj : c.getHfr0007Dbs()){
					Hfr0007Db dtl = (Hfr0007Db)dtlObj;
					if("Y".equals(Common.get(dtl.getIsClosed()))){
						String[] rowArray = new String[3];
						rowArray[0] = Common.get(dtl.getId());
						rowArray[1] = Common.get(dtl.getCommitteeDate());
						rowArray[2] = Common.get(dtl.getRecordDate());
						arrList.add(rowArray);
					}else{
						if(flag){
							obj.setHfr0007Id(Common.get(dtl.getId()));
							obj.setCommitteeMeeting(dtl.getCommitteeMeeting());
							obj.setCommitteeDate(dtl.getCommitteeDate());
							obj.setRecordDate(dtl.getRecordDate());
							obj.setCaseBackDate(dtl.getCaseBackDate());
							obj.setUnExpectedClassify(dtl.getUnExpectedClassify());
							if(!"".equals(Common.get(dtl.getUnExpectedReason()))){
								String[] t = Common.get(dtl.getUnExpectedReason()).split(",");
								obj.setThiUnExpectedReason(t);
							}else{
								obj.setThiUnExpectedReason(null);
							}
							obj.setEvidentiary(dtl.getEvidentiary());
							obj.setThiSeverity(dtl.getThiSeverity());
							obj.setAdministrativeLevel(dtl.getAdministrativeLevel());
							obj.setReEvaluateResult(dtl.getReEvaluateResult());
							obj.setThiExplain(dtl.getThiExplain());
							obj.setUnThiExplain(dtl.getUnThiExplain());
							
							fileList = ServiceGetter.getInstance().getTcbwService().load(" from Con0001Db where fileKind = 'HFRRE' and dbName = 'HFR0007DB' and upLoadId = " + Common.getLong(dtl.getId()));
							genFileRowItemSet(fileList, "HFRRE");
							fileList.clear();
							flag = false;
						}
					}
				}
				obj.setHfr0007DbList(arrList);
				
				// 若沒有未更新完的資料，清空畫面，表示此次為新的複評作業，先清空ID，防止衝突
				if(flag){
					obj.setHfr0007Id("");
				}
			}else{
				obj.setHfr0007DbList(null);
				obj.setHfr0007Id("");
			}
		}
		
		return obj;
	}
	
	public void doUpdateType() throws Exception{
		HFRIN0802F ref = new HFRIN0802F();
		org.springframework.beans.BeanUtils.copyProperties(this, ref, new String[]{""});
		ServiceGetter.getInstance().getTcbwService().getHfrinDao().updateByHFRIN0802F(ref);
		
		setId(ref.getId());
		setErrorMsg(ref.getErrorMsg());
		setIsNeedBackQuery(ref.getIsNeedBackQuery());
	}
}
