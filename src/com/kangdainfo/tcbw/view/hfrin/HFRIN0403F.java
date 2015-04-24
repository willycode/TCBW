package com.kangdainfo.tcbw.view.hfrin;

import java.util.Map;
import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Con0001Db;
import com.kangdainfo.tcbw.model.bo.Hfr4001Db;
import com.kangdainfo.tcbw.model.bo.Hfr4002Db;

public class HFRIN0403F extends HFRIN0401F implements HFRIN0002F {

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

	public String getIsHiddenPersonalInfo() {		return "N";	}
	public String getIsShowOpenPersonalInfo() {		return "N";	}
	public String getIsShowUserButton() {		return "Y";	}
	public String getIsNeedShowPermitKey() {		return "N";	}
	public String getIsNeedShowDocNo() {		return "N";	}
	public String getIsShowPreAssessmentPage() {		return "N";	}
	public String getMedicalPostingDate() {		return "";	}
	public String getMedicalCompleteDate() {	return "";	}
	public String getInspectPostingDate() {		return "";	}
	public String getInspectCompleteDate() {		return "";	}
	
	public void doInsert()throws Exception{
		ServiceGetter.getInstance().getTcbwService().getHfrinDao().insertByHFRIN0403F(this);
	}
	
	public Object doQueryOne() throws Exception {
		HFRIN0403F obj = this;
		Hfr4001Db c = (Hfr4001Db)View.getObject(" from Hfr4001Db where id = " + Common.getLong(getId()));
		if(c != null){
			obj.setId(Common.get(c.getId()));
			obj.setApplNo(c.getApplNo());
			obj.setHfrType(c.getHfrType());
			obj.setStatus(c.getStatus());
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
				this.genFileRowItemSet(fileList, "food");
			}
		}
		return  obj;
	}
	
	public void doDelete() throws Exception{
		ServiceGetter.getInstance().getTcbwService().getHfrinDao().deleteByHFRIN0403F(this);
	}
	
	public void doUpdateType() throws Exception{
		ServiceGetter.getInstance().getTcbwService().getHfrinDao().updateByHFRIN0403F(this);
	}
	
	// 非預期反應結果
	public String getHFRURCRRadioOption(String className, String checkBoxFieldName, String selectedOne, String deathDate, String deathReason, String eReason, String oReason){
    	StringBuilder sb = new StringBuilder();
    	java.util.Map<String, String> list = new java.util.TreeMap<String, String>(); 
    	ServiceGetter.getInstance().getCommonService().getCommonCodeDao().getCommonCodeNameMap("HFRURCR", list);
    	if(list!=null && list.size()>0){
    		for(Map.Entry<String, String> dtl : list.entrySet()){
    			sb.append("<input class=\"" ).append( className ).append( "\" type=\"radio\" name=\"" ).append( checkBoxFieldName ).append( "\" value=\"" ).append( dtl.getKey() ).append( "\"");
    			if(Common.get(dtl.getKey()).equals(Common.get(selectedOne))){
    				sb.append(" checked");
    			}
    			sb.append(">").append(dtl.getValue());
    			
    			if("1".equals(Common.get(dtl.getKey()))){
					sb.append("，").append(View.getPopCalendar(className, "deathDate", deathDate))
					  .append("，死亡原因 : ").append("<input class=\"").append(className).append("\" type=\"text\" name=\"deathResult\" size=25 maxlength=50 value=\"").append(deathReason).append("\">")
					  .append("，或危急生命 : ").append("<input class=\"").append(className).append("\" type=\"text\" name=\"endangerLife\" size=25 maxlength=50 value=\"").append(eReason).append("\">");
				}else if("7".equals(Common.get(dtl.getKey()))){
					sb.append(" ").append("<input class=\"").append(className).append("\" type=\"text\" name=\"otherNonResponse\" size=30 maxlength=50 value=\"").append(oReason).append("\">");
				}
    			sb.append("<br>");
    		}
    	}
        return sb.toString();    	
    }
	
	public String getFORCheckBoxOption(String className, String checkBoxFieldName, String[] selectedCheckBox, String allergicDescription, String othersDescribeHistory){
		StringBuilder sb = new StringBuilder();
    	java.util.Map<String, String> list = new java.util.TreeMap<String, String>(); 
    	ServiceGetter.getInstance().getCommonService().getCommonCodeDao().getCommonCodeNameMap("HFRFOR", list);
    	if(list!=null && list.size()>0){
    		for(Map.Entry<String, String> dtl : list.entrySet()){
    			sb.append("<input class=\"" ).append( className ).append( "\" type=\"checkbox\" name=\"" ).append( checkBoxFieldName ).append( "\" value=\"" ).append( dtl.getKey() ).append( "\"");
    			if(selectedCheckBox!=null && selectedCheckBox.length>0){
    				for(int j=0; j<selectedCheckBox.length; j++) {
        				if(Common.get(dtl.getKey()).equals(selectedCheckBox[j])) sb.append(" checked");
        			}
    			}
    			sb.append(">").append(dtl.getValue());
    			
    			if("1".equals(Common.get(dtl.getKey()))){
    				sb.append("，").append("<input class=\"").append(className).append("\" type=\"text\" name=\"allergicDescription\" size=40 maxlength=50 value=\"").append(allergicDescription).append("\">");
				}else if("6".equals(Common.get(dtl.getKey()))){
					sb.append(" ").append("<input class=\"").append(className).append("\" type=\"text\" name=\"othersDescribeHistory\" size=40 maxlength=50 value=\"").append(othersDescribeHistory).append("\">");
				}
    			sb.append("<br>");
    		}
    	}
    	return sb.toString();
	}
	
}
