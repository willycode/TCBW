package com.kangdainfo.tcbw.view.hfrin;

public interface HFRIN0001F {
	
	public String getHfr40001Id(); 
	public String getDrugOther();
	public String getFoodOther();
	public String getDiseaseOther();
	public String getLifeOther();
	
	public String getNotifierTelArea();
	public String getNotifierTelExt();
	public String getEatersTelArea();
	public String getEatersTelExt();
	public String getNotifierArea();
	public String getNotifierZipCode();
	
	public String getApplNo();
	public String getHfrType();  
	public String getIsSameNotifier();
	public String getCaseOwner();
	public String getNotifierName();
	public String getNotifierTel();
	public String getNotifierType();
	public String getNotifierEamil();
	public String getNotifierRevDate();
	public String getNotifierRepDate();
	public String getEatersName();
	public String getEatersIdNum();
	public String getEatersHight();
	public String getEatersWeight();
	public String getEatersSex();
	public String getEatersAge();
	public String getEatersTel();
	public String getAddress();
	public String getDrugAllergies();
	public String getFoodAllergies();
	public String getIsReactionResult();
	public String getInformedSources();
	public String[] getDiseaseHistory();
	public String[] getLifeHistory();
	
	public String getUnHealthIsYes();
	public String getUnHealthBrief();
	public String getOccurDate();
	public String getOccurredAfter();
	public String getSymptom();
	public String getSeverity();
	public String getSymptomDuration();
	public String getStopEatingReaction();
	public String getAgainEatingReaction();
	public String getIsMedical();
	public String getMedicalDate();
	public String getHospital();
	public String getIsWhileMedicine();
	public String getWestDrugName();
	public String getIsWhileCMedicine();
	public String getEastDrugName();
	public String getIsWhileOther();
	public String getProductName();
	public String getOtherExplainMemo();
	
	public String getIsHiddenPersonalInfo();
	public String getIsShowOpenPersonalInfo();
	public String getIsShowUserButton();
	public String getIsNeedShowPermitKey();
	public String getIsNeedShowDocNo();
	
	// FOR 預評時使用
	public String getIsShowPreAssessmentPage();
	public String getMedicalPostingDate();							// 病歷調閱發文日期
	public String getMedicalCompleteDate();							// 病歷調閱完成日期
	public String getInspectPostingDate();							// 檢驗報告發文日期	
	public String getInspectCompleteDate();							// 檢驗報告完成日期
	
}
