package com.kangdainfo.tcbw.view.hfrin;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Con0001Db;
import com.kangdainfo.tcbw.model.bo.Hfr4001Db;
import com.kangdainfo.tcbw.model.bo.Hfr4002Db;

public class HFRIN0402F extends HFRIN0401F implements HFRIN0001F {
	
	private String isSameNotifier;
	private String caseOwner;
	private String notifierName;
	private String notifierTel;
	private String notifierType;
	private String notifierEamil;
	private String notifierRevDate;
	private String notifierRepDate;
	private String eatersName;
	private String eatersIdNum;
	private String eatersHight;
	private String eatersWeight;
	private String eatersSex;
	private String eatersAge;
	private String eatersTel;
	private String address;
	private String drugAllergies;
	private String drugOther;
	private String foodAllergies;
	private String foodOther;
	private String[] diseaseHistory;
	private String diseaseOther;
	private String[] lifeHistory;
	private String lifeOther;
	private String isReactionResult;
	private String informedSources;
	private String notifierTelArea;						// 電話區碼
	private String notifierTelExt;						// 電話分機
	private String eatersTelArea;
	private String eatersTelExt;
	private String notifierArea;						// 縣市
	private String notifierZipCode;						// 郵遞區號	
	
	public String getIsSameNotifier() {		return checkGet(isSameNotifier);	}
	public void setIsSameNotifier(String isSameNotifier) {		this.isSameNotifier = checkSet(isSameNotifier);	}
	public String getCaseOwner() {		return checkGet(caseOwner);	}
	public void setCaseOwner(String caseOwner) {		this.caseOwner = checkSet(caseOwner);	}
	public String getNotifierName() {		return checkGet(notifierName);	}
	public void setNotifierName(String notifierName) {		this.notifierName = checkSet(notifierName);	}
	public String getNotifierTel() {		return checkGet(notifierTel);	}
	public void setNotifierTel(String notifierTel) {		this.notifierTel = checkSet(notifierTel);	}
	public String getNotifierType() {		return checkGet(notifierType);	}
	public void setNotifierType(String notifierType) {		this.notifierType = checkSet(notifierType);	}
	public String getNotifierEamil() {		return checkGet(notifierEamil);	}
	public void setNotifierEamil(String notifierEamil) {		this.notifierEamil = checkSet(notifierEamil);	}
	public String getNotifierRevDate() {		return checkGet(notifierRevDate);	}
	public void setNotifierRevDate(String notifierRevDate) {		this.notifierRevDate = checkSet(notifierRevDate);	}
	public String getNotifierRepDate() {		return checkGet(notifierRepDate);	}
	public void setNotifierRepDate(String notifierRepDate) {		this.notifierRepDate = checkSet(notifierRepDate);	}
	public String getEatersName() {		return checkGet(eatersName);	}
	public void setEatersName(String eatersName) {		this.eatersName = checkSet(eatersName);	}
	public String getEatersIdNum() {		return checkGet(eatersIdNum);	}
	public void setEatersIdNum(String eatersIdNum) {		this.eatersIdNum = checkSet(eatersIdNum);	}
	public String getEatersHight() {		return checkGet(eatersHight);	}
	public void setEatersHight(String eatersHight) {		this.eatersHight = checkSet(eatersHight);	}
	public String getEatersWeight() {		return checkGet(eatersWeight);	}
	public void setEatersWeight(String eatersWeight) {		this.eatersWeight = checkSet(eatersWeight);	}
	public String getEatersSex() {		return checkGet(eatersSex);	}
	public void setEatersSex(String eatersSex) {		this.eatersSex = checkSet(eatersSex);	}
	public String getEatersAge() {		return checkGet(eatersAge);	}
	public void setEatersAge(String eatersAge) {		this.eatersAge = checkSet(eatersAge);	}
	public String getEatersTel() {		return checkGet(eatersTel);	}
	public void setEatersTel(String eatersTel) {		this.eatersTel = checkSet(eatersTel);	}
	public String getAddress() {		return checkGet(address);	}
	public void setAddress(String address) {		this.address = checkSet(address);	}
	public String getDrugAllergies() {		return checkGet(drugAllergies);	}
	public void setDrugAllergies(String drugAllergies) {		this.drugAllergies = checkSet(drugAllergies);	}
	public String getFoodAllergies() {		return checkGet(foodAllergies);	}
	public void setFoodAllergies(String foodAllergies) {		this.foodAllergies = checkSet(foodAllergies);	}
	public String[] getDiseaseHistory() {		return diseaseHistory;	}
	public void setDiseaseHistory(String[] diseaseHistory) {		this.diseaseHistory = diseaseHistory;	}
	public String[] getLifeHistory() {		return lifeHistory;	}
	public void setLifeHistory(String[] lifeHistory) {		this.lifeHistory = lifeHistory;	}
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
	private String occurDate;
	private String occurredAfter;
	private String symptom;
	private String severity;
	private String symptomDuration;
	private String stopEatingReaction;
	private String againEatingReaction;
	private String isMedical;
	private String medicalDate;
	private String hospital;
	private String isWhileMedicine;
	private String westDrugName;
	private String isWhileCMedicine;
	private String eastDrugName;
	private String isWhileOther;
	private String productName;
	private String otherExplainMemo;
	
	public String getUnHealthIsYes() {		return checkGet(unHealthIsYes);	}
	public void setUnHealthIsYes(String unHealthIsYes) {		this.unHealthIsYes = checkSet(unHealthIsYes);	}
	public String getUnHealthBrief() {		return checkGet(unHealthBrief);	}
	public void setUnHealthBrief(String unHealthBrief) {		this.unHealthBrief = checkSet(unHealthBrief);	}
	public String getOccurDate() {		return checkGet(occurDate);	}
	public void setOccurDate(String occurDate) {		this.occurDate = checkSet(occurDate);	}
	public String getOccurredAfter() {		return checkGet(occurredAfter);	}
	public void setOccurredAfter(String occurredAfter) {		this.occurredAfter = checkSet(occurredAfter);	}
	public String getSymptom() {		return checkGet(symptom);	}
	public void setSymptom(String symptom) {		this.symptom = checkSet(symptom);	}
	public String getSeverity() {		return checkGet(severity);	}
	public void setSeverity(String severity) {		this.severity = checkSet(severity);	}
	public String getSymptomDuration() {		return checkGet(symptomDuration);	}
	public void setSymptomDuration(String symptomDuration) {		this.symptomDuration = checkSet(symptomDuration);	}
	public String getStopEatingReaction() {		return checkGet(stopEatingReaction);	}
	public void setStopEatingReaction(String stopEatingReaction) {		this.stopEatingReaction = checkSet(stopEatingReaction);	}
	public String getAgainEatingReaction() {		return checkGet(againEatingReaction);	}
	public void setAgainEatingReaction(String againEatingReaction) {		this.againEatingReaction = checkSet(againEatingReaction);	}
	public String getIsMedical() {		return checkGet(isMedical);	}
	public void setIsMedical(String isMedical) {		this.isMedical = checkSet(isMedical);	}
	public String getMedicalDate() {		return checkGet(medicalDate);	}
	public void setMedicalDate(String medicalDate) {		this.medicalDate = checkSet(medicalDate);	}
	public String getHospital() {		return checkGet(hospital);	}
	public void setHospital(String hospital) {		this.hospital = checkSet(hospital);	}
	public String getIsWhileMedicine() {		return checkGet(isWhileMedicine);	}
	public void setIsWhileMedicine(String isWhileMedicine) {		this.isWhileMedicine = checkSet(isWhileMedicine);	}
	public String getWestDrugName() {		return checkGet(westDrugName);	}
	public void setWestDrugName(String westDrugName) {		this.westDrugName = checkSet(westDrugName);	}
	public String getIsWhileCMedicine() {		return checkGet(isWhileCMedicine);	}
	public void setIsWhileCMedicine(String isWhileCMedicine) {		this.isWhileCMedicine = checkSet(isWhileCMedicine);	}
	public String getEastDrugName() {		return checkGet(eastDrugName);	}
	public void setEastDrugName(String eastDrugName) {		this.eastDrugName = checkSet(eastDrugName);	}
	public String getIsWhileOther() {		return checkGet(isWhileOther);	}
	public void setIsWhileOther(String isWhileOther) {		this.isWhileOther = checkSet(isWhileOther);	}
	public String getProductName() {		return checkGet(productName);	}
	public void setProductName(String productName) {		this.productName = checkSet(productName);	}
	public String getOtherExplainMemo() {		return checkGet(otherExplainMemo);	}
	public void setOtherExplainMemo(String otherExplainMemo) {		this.otherExplainMemo = checkSet(otherExplainMemo);	}
	
	public String getIsHiddenPersonalInfo() {		return "N";	}
	public String getIsShowOpenPersonalInfo() {		return "N";	}
	public String getIsShowUserButton() {		return "Y";	}
	public String getIsNeedShowPermitKey() {		return "N";		}
	public String getIsNeedShowDocNo() {		return "N";		}
	
	// FOR 預評時使用 - 因為介面緣故傳回空值即可	
	public String getIsShowPreAssessmentPage() {		return "N";	}
	public String getMedicalPostingDate() {		return "";	}
	public String getMedicalCompleteDate() {		return "";	}
	public String getInspectPostingDate() {		return "";	}
	public String getInspectCompleteDate() {		return "";	}
	
	@Override
	public Object doQueryOne() throws Exception {
		HFRIN0402F obj = this;
		Hfr4001Db c = (Hfr4001Db)View.getObject(" from Hfr4001Db where id = " + Common.getLong(getId()));
		if(c != null){
			obj.setId(Common.get(c.getId()));
			obj.setApplNo(c.getApplNo());
			obj.setHfrType(c.getHfrType());
			obj.setStatus(c.getStatus());
			obj.setCaseOwner(c.getCaseOwner());
			obj.setNotifierName(c.getNotifierName());
			obj.setNotifierRevDate(c.getNotifierRevDate());
			obj.setNotifierRepDate(c.getNotifierRepDate());
			obj.setNotifierType(c.getNotifierType());
			obj.setNotifierTelArea(c.getNotifierTelArea());
			obj.setNotifierTel(c.getNotifierTel());
			obj.setNotifierTelExt(c.getNotifierTelExt());
			obj.setNotifierEamil(c.getNotifierEamil());
			obj.setInformedSources(c.getInformedSources());
			
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
			obj.setEatersIdNum(c.getEatersIdNum());
			obj.setEatersHight(c.getEatersHight());
			obj.setEatersWeight(c.getEatersWeight());
			obj.setEatersSex(c.getEatersSex());
			obj.setEatersAge(c.getEatersAge());
			obj.setEatersTelArea(c.getEatersTelArea());
			obj.setEatersTel(c.getEatersTel());
			obj.setEatersTelExt(c.getEatersTelExt());
			obj.setNotifierArea(c.getNotifierArea());
			obj.setNotifierZipCode(c.getNotifierZipCode());
			obj.setAddress(c.getAddress());
			obj.setDrugAllergies(c.getDrugAllergies());
			obj.setDrugOther(c.getDrugOther());
			obj.setFoodAllergies(c.getFoodAllergies());
			obj.setFoodOther(c.getFoodOther());
			obj.setIsReactionResult(c.getIsReactionResult());
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
			obj.setOccurDate(c.getOccurDate());
			obj.setOccurredAfter(c.getOccurredAfter());
			obj.setSymptom(c.getSymptom());
			obj.setSeverity(c.getSeverity());
			obj.setSymptomDuration(c.getSymptomDuration());
			obj.setStopEatingReaction(c.getStopEatingReaction());
			obj.setAgainEatingReaction(c.getAgainEatingReaction());
			obj.setIsMedical(c.getIsMedical());
			obj.setMedicalDate(c.getMedicalDate());
			obj.setHospital(c.getHospital());
			obj.setIsWhileMedicine(c.getIsWhileMedicine());
			obj.setWestDrugName(c.getWestDrugName());
			obj.setIsWhileCMedicine(c.getIsWhileCMedicine());
			obj.setEastDrugName(c.getEastDrugName());
			obj.setIsWhileOther(c.getIsWhileOther());
			obj.setProductName(c.getProductName());
			obj.setOtherExplainMemo(c.getOtherExplainMemo());
			
			if(c.getHfr4002Dbs()!=null && c.getHfr4002Dbs().size()>0){
				java.util.List<Hfr4002Db> gFood = new java.util.ArrayList<Hfr4002Db>();
				java.util.List<Hfr4002Db> nFood = new java.util.ArrayList<Hfr4002Db>();
				for(Object dtlObj : c.getHfr4002Dbs()){
					Hfr4002Db dtl = (Hfr4002Db)dtlObj;
					if("G".equals(Common.get(dtl.getType()))){
						gFood.add(dtl);
					}else{
						nFood.add(dtl);
					}
				}
				obj.genGHfr4002DbItemSet(gFood);
				obj.genNHfr4002DbItemSet(nFood);
			}else{
				obj.genGHfr4002DbItemSet(null);
				obj.genNHfr4002DbItemSet(null);
			}
			
			java.util.List<Con0001Db> fileList = ServiceGetter.getInstance().getTcbwService().load(" from Con0001Db where fileKind = 'H' and dbName = 'HFR4001DB' and upLoadId = " + Common.getLong(obj.getId()));
			if(fileList!=null && fileList.size()>0){
				obj.genFileRowItemSet(fileList, "food");				
			}
		}
		return obj;
	}

	public void doInsert()throws Exception{
		ServiceGetter.getInstance().getTcbwService().getHfrinDao().insertByHFRIN0402F(this);
	}
	
	public void doUpdateType() throws Exception{
		ServiceGetter.getInstance().getTcbwService().getHfrinDao().updateByHFRIN0402F(this);
	}
	
	public void doDelete() throws Exception{
		ServiceGetter.getInstance().getTcbwService().getHfrinDao().deleteByHFRIN0402F(this);
	}
	
}
