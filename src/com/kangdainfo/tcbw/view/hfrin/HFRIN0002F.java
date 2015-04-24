package com.kangdainfo.tcbw.view.hfrin;

public interface HFRIN0002F {
	
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
	public String getNotifierRevDate();
	public String getNotifierRepDate();
	public String getOccurDate();
	public String getIsSameNotifier();
	public String getCaseOwner();
	public String getNotifierName();
	public String getNotifierTel();
	public String getNotifierIdNum();
	public String getNotifierEamil();
	public String getNotifierType();
	public String getNotifierDeptID();
	public String getNotifierDept();
	public String getNotifierTitle();
	public String getEatersId();
	public String getEatersName();
	public String getEatersSex();
	public String getEatersBirthYear();
	public String getEatersHight();
	public String getEatersWeight();
	public String getAddress();
	public String getEatersTel();
	public String getEatersIdNum();
	public String getDrugAllergies();
	public String getFoodAllergies();
	public String getIsReactionResult();
	public String getInformedSources();
	public String[] getDiseaseHistory();
	public String[] getLifeHistory();
	
	public String getUnHealthIsYes();
	public String getUnHealthBrief();
	public String getUnReactionResults();
	public String getDeathDate();
	public String getDeathResult();
	public String getEndangerLife();
	public String getOtherNonResponse();
	public String[] getOtherrElevantInformation();
	public String getAllergicDescription();
	public String getOthersDescribeHistory();
	
	public String getAgainEatingHealthFood();
	public String getHealthFoodName();
	public String getAgainOtherNonResponse();
	public String getStopEatingReaction();
	public String getAgainEatingReaction();
	
	public String getHFRURCRRadioOption(String className, String checkBoxFieldName, String selectedOne, String deathDate, String deathReason, String eReason, String oReason);
	public String getFORCheckBoxOption(String className, String checkBoxFieldName, String[] selectedCheckBox, String allergicDescription, String othersDescribeHistory);
	
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
