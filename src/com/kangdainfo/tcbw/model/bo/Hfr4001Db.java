package com.kangdainfo.tcbw.model.bo;

import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;

public class Hfr4001Db implements java.io.Serializable {

	private Long id;
	private String hfrType;
	private String applNo;
	private String status;
	private String occurDate;
	private String notifierRevDate;
	private String notifierRepDate;
	private String notifierName;
	private String notifierDept;
	private String notifierDeptId;
	private String notifierTelArea;
	private String notifierTelExt;
	private String notifierTel;
	private String notifierArea;
	private String notifierZipCode;
	private String address;
	private String notifierEamil;
	private String notifierIdNum;
	private String notifierType;
	private String notifierTitle;
	private String eatersId;
	private String eatersName;
	private String eatersHight;
	private String eatersWeight;
	private String eatersAge;
	private String eatersSex;
	private String eatersTelArea;
	private String eatersTelExt;
	private String eatersTel;
	private String eatersBirthYear;
	private String eatersIdNum;
	private String unHealthIsYes;
	private String unHealthBrief;
	private String unReactionResults;
	private String deathDate;
	private String deathResult;
	private String endangerLife;
	private String otherNonResponse;
	private String otherrElevantInformation;
	private String allergicDescription;
	private String othersDescribeHistory;
	private String occurredAfter;
	private String symptom;
	private String severity;
	private String symptomDuration;
	private String stopEatingReaction;
	private String againEatingReaction;
	private String againEatingHealthFood;
	private String healthFoodName;
	private String againOtherNonResponse;
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
	private String drugAllergies;
	private String foodAllergies;
	private String diseaseHistory;
	private String lifeHistory;
	private String isReactionResult;
	private String informedSources;
	private String drugOther;
	private String foodOther;
	private String diseaseOther;
	private String lifeOther;
	
	private String reactionremark;
	
	private String eventsymptom;
	private String eventdesc;
	private String eventdeal;
	private String drexamdesc;
	
	
	private String reactionnewlymed;
	private String seadoctordesc;
	
	private String caseOwner;
    private String creator;
    private String createDate;
    private String createTime;
    private String modifier;
    private String modifyDate;
    private String modifyTime;
	
	private Set hfr4002Dbs;
	private Set hfr4003Dbs;
	private Set hfr4004Dbs;
	
	public Hfr4001Db(){
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getHfrType() {
		return hfrType;
	}

	public void setHfrType(String hfrType) {
		this.hfrType = hfrType;
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

	public String getNotifierTitle() {
		return notifierTitle;
	}

	public void setNotifierTitle(String notifierTitle) {
		this.notifierTitle = notifierTitle;
	}

	public String getEatersId() {
		return eatersId;
	}

	public void setEatersId(String eatersId) {
		this.eatersId = eatersId;
	}

	public String getEatersName() {
		return eatersName;
	}

	public void setEatersName(String eatersName) {
		this.eatersName = eatersName;
	}

	public String getEatersHight() {
		return eatersHight;
	}

	public void setEatersHight(String eatersHight) {
		this.eatersHight = eatersHight;
	}

	public String getEatersWeight() {
		return eatersWeight;
	}

	public void setEatersWeight(String eatersWeight) {
		this.eatersWeight = eatersWeight;
	}

	public String getEatersAge() {
		return eatersAge;
	}

	public void setEatersAge(String eatersAge) {
		this.eatersAge = eatersAge;
	}

	public String getEatersSex() {
		return eatersSex;
	}

	public void setEatersSex(String eatersSex) {
		this.eatersSex = eatersSex;
	}

	public String getEatersTel() {
		return eatersTel;
	}

	public void setEatersTel(String eatersTel) {
		this.eatersTel = eatersTel;
	}

	public String getEatersBirthYear() {
		return eatersBirthYear;
	}

	public void setEatersBirthYear(String eatersBirthYear) {
		this.eatersBirthYear = eatersBirthYear;
	}	
	
	public String getNotifierIdNum() {
		return notifierIdNum;
	}

	public void setNotifierIdNum(String notifierIdNum) {
		this.notifierIdNum = notifierIdNum;
	}

	public String getEatersIdNum() {
		return eatersIdNum;
	}

	public void setEatersIdNum(String eatersIdNum) {
		this.eatersIdNum = eatersIdNum;
	}

	public String getUnHealthIsYes() {
		return unHealthIsYes;
	}

	public void setUnHealthIsYes(String unHealthIsYes) {
		this.unHealthIsYes = unHealthIsYes;
	}

	public String getUnHealthBrief() {
		return unHealthBrief;
	}

	public void setUnHealthBrief(String unHealthBrief) {
		this.unHealthBrief = unHealthBrief;
	}

	public String getUnReactionResults() {
		return unReactionResults;
	}

	public void setUnReactionResults(String unReactionResults) {
		this.unReactionResults = unReactionResults;
	}

	public String getDeathDate() {
		return deathDate;
	}

	public void setDeathDate(String deathDate) {
		this.deathDate = deathDate;
	}

	public String getDeathResult() {
		return deathResult;
	}

	public void setDeathResult(String deathResult) {
		this.deathResult = deathResult;
	}

	public String getEndangerLife() {
		return endangerLife;
	}

	public void setEndangerLife(String endangerLife) {
		this.endangerLife = endangerLife;
	}

	public String getOtherNonResponse() {
		return otherNonResponse;
	}

	public void setOtherNonResponse(String otherNonResponse) {
		this.otherNonResponse = otherNonResponse;
	}

	public String getOtherrElevantInformation() {
		return otherrElevantInformation;
	}

	public void setOtherrElevantInformation(String otherrElevantInformation) {
		this.otherrElevantInformation = otherrElevantInformation;
	}

	public String getAllergicDescription() {
		return allergicDescription;
	}

	public void setAllergicDescription(String allergicDescription) {
		this.allergicDescription = allergicDescription;
	}

	public String getOthersDescribeHistory() {
		return othersDescribeHistory;
	}

	public void setOthersDescribeHistory(String othersDescribeHistory) {
		this.othersDescribeHistory = othersDescribeHistory;
	}

	public String getOccurredAfter() {
		return occurredAfter;
	}

	public void setOccurredAfter(String occurredAfter) {
		this.occurredAfter = occurredAfter;
	}

	public String getSymptom() {
		return symptom;
	}

	public void setSymptom(String symptom) {
		this.symptom = symptom;
	}

	public String getSeverity() {
		return severity;
	}

	public void setSeverity(String severity) {
		this.severity = severity;
	}

	public String getSymptomDuration() {
		return symptomDuration;
	}

	public void setSymptomDuration(String symptomDuration) {
		this.symptomDuration = symptomDuration;
	}

	public String getStopEatingReaction() {
		return stopEatingReaction;
	}

	public void setStopEatingReaction(String stopEatingReaction) {
		this.stopEatingReaction = stopEatingReaction;
	}

	public String getAgainEatingReaction() {
		return againEatingReaction;
	}

	public void setAgainEatingReaction(String againEatingReaction) {
		this.againEatingReaction = againEatingReaction;
	}

	public String getAgainEatingHealthFood() {
		return againEatingHealthFood;
	}

	public void setAgainEatingHealthFood(String againEatingHealthFood) {
		this.againEatingHealthFood = againEatingHealthFood;
	}

	public String getHealthFoodName() {
		return healthFoodName;
	}

	public void setHealthFoodName(String healthFoodName) {
		this.healthFoodName = healthFoodName;
	}

	public String getAgainOtherNonResponse() {
		return againOtherNonResponse;
	}

	public void setAgainOtherNonResponse(String againOtherNonResponse) {
		this.againOtherNonResponse = againOtherNonResponse;
	}

	public String getIsMedical() {
		return isMedical;
	}

	public void setIsMedical(String isMedical) {
		this.isMedical = isMedical;
	}

	public String getMedicalDate() {
		return medicalDate;
	}

	public void setMedicalDate(String medicalDate) {
		this.medicalDate = medicalDate;
	}

	public String getHospital() {
		return hospital;
	}

	public void setHospital(String hospital) {
		this.hospital = hospital;
	}

	public String getIsWhileMedicine() {
		return isWhileMedicine;
	}

	public void setIsWhileMedicine(String isWhileMedicine) {
		this.isWhileMedicine = isWhileMedicine;
	}

	public String getWestDrugName() {
		return westDrugName;
	}

	public void setWestDrugName(String westDrugName) {
		this.westDrugName = westDrugName;
	}

	public String getIsWhileCMedicine() {
		return isWhileCMedicine;
	}

	public void setIsWhileCMedicine(String isWhileCMedicine) {
		this.isWhileCMedicine = isWhileCMedicine;
	}

	public String getEastDrugName() {
		return eastDrugName;
	}

	public void setEastDrugName(String eastDrugName) {
		this.eastDrugName = eastDrugName;
	}

	public String getIsWhileOther() {
		return isWhileOther;
	}

	public void setIsWhileOther(String isWhileOther) {
		this.isWhileOther = isWhileOther;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getOtherExplainMemo() {
		return otherExplainMemo;
	}

	public void setOtherExplainMemo(String otherExplainMemo) {
		this.otherExplainMemo = otherExplainMemo;
	}

	public String getDrugAllergies() {
		return drugAllergies;
	}

	public void setDrugAllergies(String drugAllergies) {
		this.drugAllergies = drugAllergies;
	}

	public String getFoodAllergies() {
		return foodAllergies;
	}

	public void setFoodAllergies(String foodAllergies) {
		this.foodAllergies = foodAllergies;
	}

	public String getDiseaseHistory() {
		return diseaseHistory;
	}

	public void setDiseaseHistory(String diseaseHistory) {
		this.diseaseHistory = diseaseHistory;
	}

	public String getLifeHistory() {
		return lifeHistory;
	}

	public void setLifeHistory(String lifeHistory) {
		this.lifeHistory = lifeHistory;
	}

	public String getIsReactionResult() {
		return isReactionResult;
	}

	public void setIsReactionResult(String isReactionResult) {
		this.isReactionResult = isReactionResult;
	}
	
	public String getInformedSources() {
		return informedSources;
	}

	public void setInformedSources(String informedSources) {
		this.informedSources = informedSources;
	}

	public String getDrugOther() {
		return drugOther;
	}

	public void setDrugOther(String drugOther) {
		this.drugOther = drugOther;
	}

	public String getFoodOther() {
		return foodOther;
	}

	public void setFoodOther(String foodOther) {
		this.foodOther = foodOther;
	}

	public String getDiseaseOther() {
		return diseaseOther;
	}

	public void setDiseaseOther(String diseaseOther) {
		this.diseaseOther = diseaseOther;
	}

	public String getLifeOther() {
		return lifeOther;
	}

	public void setLifeOther(String lifeOther) {
		this.lifeOther = lifeOther;
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

	public String getEatersTelArea() {
		return eatersTelArea;
	}

	public void setEatersTelArea(String eatersTelArea) {
		this.eatersTelArea = eatersTelArea;
	}

	public String getEatersTelExt() {
		return eatersTelExt;
	}

	public void setEatersTelExt(String eatersTelExt) {
		this.eatersTelExt = eatersTelExt;
	}

	public String getCaseOwner() {
		return caseOwner;
	}

	public void setCaseOwner(String caseOwner) {
		this.caseOwner = caseOwner;
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

	public Set getHfr4002Dbs() {
		return hfr4002Dbs;
	}

	public void setHfr4002Dbs(Set hfr4002Dbs) {
		this.hfr4002Dbs = hfr4002Dbs;
	}

	public Set getHfr4003Dbs() {
		return hfr4003Dbs;
	}

	public void setHfr4003Dbs(Set hfr4003Dbs) {
		this.hfr4003Dbs = hfr4003Dbs;
	}

	public Set getHfr4004Dbs() {
		return hfr4004Dbs;
	}

	public void setHfr4004Dbs(Set hfr4004Dbs) {
		this.hfr4004Dbs = hfr4004Dbs;
	}

	
	
	public String getReactionremark() {
		return reactionremark;
	}

	public void setReactionremark(String reactionremark) {
		this.reactionremark = reactionremark;
	}

	public String getReactionnewlymed() {
		return reactionnewlymed;
	}

	public void setReactionnewlymed(String reactionnewlymed) {
		this.reactionnewlymed = reactionnewlymed;
	}
	
	

	public String getSeadoctordesc() {
		return seadoctordesc;
	}

	public void setSeadoctordesc(String seadoctordesc) {
		this.seadoctordesc = seadoctordesc;
	}
	
	public String getEventsymptom() {
		return eventsymptom;
	}

	public void setEventsymptom(String eventsymptom) {
		this.eventsymptom =eventsymptom;
	}

	public String getEventdesc() {
		return eventdesc;
	}

	public void setEventdesc(String eventdesc) {
		this.eventdesc = eventdesc;
	}

	public String getEventdeal() {
		return eventdeal;
	}

	public void setEventdeal(String eventdeal) {
		this.eventdeal = eventdeal;
	}

	public String getDrexamdesc() {
		return drexamdesc;
	}

	public void setDrexamdesc(String drexamdesc) {
		this.drexamdesc = drexamdesc;
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this)
						.append("id", getId())
						.toString();
	}
	
	
}
