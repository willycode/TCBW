package com.kangdainfo.tcbw.view.hfrin;

import java.util.Map;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import org.apache.commons.lang.StringEscapeUtils;
import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.model.bo.CommonCode;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Con0001Db;
import com.kangdainfo.tcbw.model.bo.Hfr0001Db;
import com.kangdainfo.tcbw.model.bo.Hfr0002Db;
import com.kangdainfo.tcbw.model.bo.Hfr0003Db;
import com.kangdainfo.tcbw.model.bo.Hfr0004Db;
import com.kangdainfo.tcbw.model.bo.Hfr0005Db;
import com.kangdainfo.tcbw.model.bo.Hfr0006Db;
import com.kangdainfo.tcbw.model.bo.Hfr0007Db;
import com.kangdainfo.tcbw.model.bo.Hfr4003Db;
import com.kangdainfo.tcbw.model.bo.Hfr4004Db;
import com.kangdainfo.tcbw.util.TCBWCommon;

public class HFRIN0102Q extends HFRIN0802F implements HFRIN0002F{
	
	private String tabId;	
	private String hfr40001Id;	
	
	public String getHfr40001Id() {return checkGet(hfr40001Id);}
	public void setHfr40001Id(String hfr40001Id) {this.hfr40001Id = checkSet(hfr40001Id);}
	public String getTabId() {return checkGet(tabId);}
	public void setTabId(String tabId) {this.tabId = checkSet(tabId);}
	
	//一般表單
	private String notifierIdNum;
	private String notifierDept;
	private String notifierTitle;
	private String eatersId;
	private String isSameNotifier;
	private String eatersBirthYear;
	private String unReactionResults;
	private String deathDate;
	private String deathResult;
	private String endangerLife;
	private String otherNonResponse;
	private String[] otherrElevantInformation;
	private String allergicDescription;
	private String othersDescribeHistory;
	private String againEatingHealthFood;
	private String healthFoodName;
	private String againOtherNonResponse;
	private String notifierDeptID;
	
	private String isCloseUserInfo;		//是否遮蔽個資
	
	public String getNotifierIdNum() {		return checkGet(notifierIdNum);	}
	public void setNotifierIdNum(String notifierIdNum) {		this.notifierIdNum = checkSet(notifierIdNum);	}
	public String getNotifierDept() {		return checkGet(notifierDept);	}
	public void setNotifierDept(String notifierDept) {		this.notifierDept = checkSet(notifierDept);	}
	public String getNotifierTitle() {		return checkGet(notifierTitle);	}
	public void setNotifierTitle(String notifierTitle) {		this.notifierTitle = checkSet(notifierTitle);	}
	public String getEatersId() {		return checkGet(eatersId);	}
	public void setEatersId(String eatersId) {		this.eatersId = checkSet(eatersId);	}
	public String getIsSameNotifier() {		return checkGet(isSameNotifier);	}
	public void setIsSameNotifier(String isSameNotifier) {		this.isSameNotifier = checkSet(isSameNotifier);	}
	public String getEatersBirthYear() {		return checkGet(eatersBirthYear);	}
	public void setEatersBirthYear(String eatersBirthYear) {		this.eatersBirthYear = checkSet(eatersBirthYear);	}
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
	public String getAgainEatingHealthFood() {		return checkGet(againEatingHealthFood);	}
	public void setAgainEatingHealthFood(String againEatingHealthFood) {		this.againEatingHealthFood = checkSet(againEatingHealthFood);	}
	public String getHealthFoodName() {		return checkGet(healthFoodName);	}
	public void setHealthFoodName(String healthFoodName) {		this.healthFoodName = checkSet(healthFoodName);	}
	public String getAgainOtherNonResponse() {		return checkGet(againOtherNonResponse);	}
	public void setAgainOtherNonResponse(String againOtherNonResponse) {		this.againOtherNonResponse = checkSet(againOtherNonResponse);	}
	public String getNotifierDeptID() {		return checkGet(notifierDeptID);	}
	public void setNotifierDeptID(String notifierDeptID) {		this.notifierDeptID = checkSet(notifierDeptID);	}
	
	public final String[] discriptFieldName = {"discriptDate", "position", "symptom", "severity", "doResponse"};
	public final String[] doctorFieldName = {"doctorDate", "bloodIndex", "liverIndex", "renalIndex"};
	
	public String getIsCloseUserInfo() {return checkGet(isCloseUserInfo);}
	public void setIsCloseUserInfo(String isCloseUserInfo) {this.isCloseUserInfo = checkSet(isCloseUserInfo);}
	
	//時間表單
	private String enrolledDateHis;			//收案日期
	private String additionalDateHis;		//通知補件日期
	private String additionalFinshDateHis;	//通知補件日期	
	private String medicalPostingDateHis;	//病歷調閱發文日期
	private String medicalCompleteDateHis;	//病歷調閱完成日期
	private String inspectPostingDateHis;	//檢驗報告發文日期
	private String inspectCompleteDateHis;	//檢驗報告完成日期
	private String preCompleteDateHis;		//初評完成日期
	private String assessmentSendDateHis;	//預評送交日期
	private String assessmentCompleteDateHis;//預評完成日期?
	private String committeeDateHis;		//委員會召開日期
	private String recordDateHis;			//會議紀錄發文日期
	private String caseBackDateHis;			//案件回饋日期
	private String reEvaluateResultHis;		//複評結果

	public String getEnrolledDateHis() {return checkGet(enrolledDateHis);}
	public void setEnrolledDateHis(String enrolledDateHis) {this.enrolledDateHis = checkSet(enrolledDateHis);}
	public String getAdditionalDateHis() {return checkGet(additionalDateHis);}
	public void setAdditionalDateHis(String additionalDateHis) {this.additionalDateHis = checkSet(additionalDateHis);}
	public String getAdditionalFinshDateHis() {return checkGet(additionalFinshDateHis);}
	public void setAdditionalFinshDateHis(String additionalFinshDateHis) {this.additionalFinshDateHis = checkSet(additionalFinshDateHis);}
	public String getMedicalPostingDateHis() {return checkGet(medicalPostingDateHis);}
	public void setMedicalPostingDateHis(String medicalPostingDateHis) {this.medicalPostingDateHis = checkSet(medicalPostingDateHis);}
	public String getMedicalCompleteDateHis() {return checkGet(medicalCompleteDateHis);}
	public void setMedicalCompleteDateHis(String medicalCompleteDateHis) {this.medicalCompleteDateHis = checkSet(medicalCompleteDateHis);}
	public String getInspectPostingDateHis() {return checkGet(inspectPostingDateHis);}
	public void setInspectPostingDateHis(String inspectPostingDateHis) {this.inspectPostingDateHis = checkSet(inspectPostingDateHis);}
	public String getInspectCompleteDateHis() {return checkGet(inspectCompleteDateHis);}
	public void setInspectCompleteDateHis(String inspectCompleteDateHis) {this.inspectCompleteDateHis = checkSet(inspectCompleteDateHis);}
	public String getPreCompleteDateHis() {return checkGet(preCompleteDateHis);}
	public void setPreCompleteDateHis(String preCompleteDateHis) {this.preCompleteDateHis = checkSet(preCompleteDateHis);}
	public String getAssessmentSendDateHis() {return checkGet(assessmentSendDateHis);}
	public void setAssessmentSendDateHis(String assessmentSendDateHis) {this.assessmentSendDateHis = checkSet(assessmentSendDateHis);}
	public String getCommitteeDateHis() {return checkGet(committeeDateHis);}
	public void setCommitteeDateHis(String committeeDateHis) {this.committeeDateHis = checkSet(committeeDateHis);}
	public String getRecordDateHis() {return checkGet(recordDateHis);}
	public void setRecordDateHis(String recordDateHis) {this.recordDateHis = checkSet(recordDateHis);}
	public String getCaseBackDateHis() {return checkGet(caseBackDateHis);}
	public void setCaseBackDateHis(String caseBackDateHis) {this.caseBackDateHis = checkSet(caseBackDateHis);}
	public String getReEvaluateResultHis() {return checkGet(reEvaluateResultHis);}
	public void setReEvaluateResultHis(String reEvaluateResultHis) {this.reEvaluateResultHis = checkSet(reEvaluateResultHis);}
	public String getAssessmentCompleteDateHis() {return checkGet(assessmentCompleteDateHis);}
	public void setAssessmentCompleteDateHis(String assessmentCompleteDateHis) {this.assessmentCompleteDateHis = checkSet(assessmentCompleteDateHis);}
	
	
	//歷史通報Query
	private String q_notifierRepDateSHis;
	private String q_notifierRepDateEHis;
	private String q_unReactionResultsHis;
	private String q_productHis;

	public String getQ_notifierRepDateSHis() {return checkGet(q_notifierRepDateSHis);}
	public void setQ_notifierRepDateSHis(String qNotifierRepDateSHis) {q_notifierRepDateSHis = checkSet(qNotifierRepDateSHis);}
	public String getQ_notifierRepDateEHis() {return checkGet(q_notifierRepDateEHis);}
	public void setQ_notifierRepDateEHis(String qNotifierRepDateEHis) {q_notifierRepDateEHis = checkSet(qNotifierRepDateEHis);}
	public String getQ_unReactionResultsHis() {return checkGet(q_unReactionResultsHis);}
	public void setQ_unReactionResultsHis(String qUnReactionResultsHis) {q_unReactionResultsHis = checkSet(qUnReactionResultsHis);}
	public String getQ_productHis() {return checkGet(q_productHis);}
	public void setQ_productHis(String qProductHis) {q_productHis = checkSet(qProductHis);}
	
	@Override
	public Object doQueryOne() throws Exception {
		HFRIN0102Q obj = this;
		Hfr0001Db c = (Hfr0001Db)View.getObject(" from Hfr0001Db where id = " + Common.getLong(getId()));
		if(c != null){
			if("1".equals(c.getHfrType())){
				//簡表
				setHfrType1Case(c, obj);
			}else{
				//一般表
				setHfrType2Case(c, obj);				
			}
			obj.setHfr40001Id(Common.get(c.getHfr4001DbId()));
			
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
			
			//時間表單
			setTimeForm(c);
			
			// HFR0005_DB
			if(c.getHfr0005Dbs()!=null && c.getHfr0005Dbs().size()>0){
				setHFR0005Form(c, fileList);
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
							obj.setUnExpectedReason(t);
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
				setHFR0007Form(c, fileList);
			}else{
				obj.setHfr0007DbList(null);
				obj.setHfr0007Id("");
			}
			fileList.clear();
		}
		return obj;
	}
	
	public void setHfrType1Case(Hfr0001Db c, HFRIN0102Q obj) throws Exception{
		obj.setId(Common.get(c.getId()));
		obj.setApplNo(c.getApplNo());
		obj.setHfrType(c.getHfrType());
		obj.setStatus(c.getStatus());
		obj.setCaseOwner(c.getCaseOwner());
		obj.setAssignMan(c.getAssignMan());
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
		if(c.getHfr0002Dbs()!=null && c.getHfr0002Dbs().size()>0){
			java.util.List<Hfr0002Db> gFood = new java.util.ArrayList<Hfr0002Db>();
			java.util.List<Hfr0002Db> nFood = new java.util.ArrayList<Hfr0002Db>();
			for(Object dtlObj : c.getHfr0002Dbs()){
				Hfr0002Db dtl = (Hfr0002Db)dtlObj;
				if("G".equals(Common.get(dtl.getType()))){
					gFood.add(dtl);
				}else{
					nFood.add(dtl);
				}
			}
			obj.genGHfr0002DbItemSet(gFood);
			obj.genNHfr0002DbItemSet(nFood);
		}else{
			obj.genGHfr0002DbItemSet(null);
			obj.genNHfr0002DbItemSet(null);
		}
		obj.setMedicalPostingDate(c.getMedicalPostingDate());
		obj.setMedicalCompleteDate(c.getMedicalCompleteDate());
		obj.setInspectPostingDate(c.getInspectPostingDate());
		obj.setInspectCompleteDate(c.getInspectCompleteDate());
	}
	
	public void setHfrType2Case(Hfr0001Db c, HFRIN0102Q obj) throws Exception{
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
		obj.setNotifierDeptID(c.getNotifierDept());
		String deptName = "";
		if("02".equals(obj.getNotifierType())){
			deptName = View.getLookupField("select name from Con1005Db where id = " + Common.getLong(obj.getNotifierDept()));
		}else if("03".equals(obj.getNotifierType())){
			deptName = View.getLookupField("select medAgencyName from Con1009Db where id = " + Common.getLong(obj.getNotifierDept()));
		}else if("04".equals(obj.getNotifierType())){
			deptName = View.getLookupField("select unionName from Con1003Db where id = " + Common.getLong(obj.getNotifierDept()));
		}
		obj.setNotifierDept(deptName);		
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
		
	}
	
	public Object doQueryHistory() throws Exception {
		String hql = getHqlHistory();
		
		System.out.println("[TCBW]-[HFRIN0102Q]-[食品-doQueryHistory] : " + hql);		
		System.out.println("報表類別： " + getHfrType());
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(hql);
		this.processCurrentPageAttribute(objList!=null?objList.size():0);
		if (getTotalRecord() > 0) {
			if (getState().indexOf("query")<0) 
				ServiceGetter.getInstance().getCommonService().getHibernateTemplate().clear();
			objList = ServiceGetter.getInstance().getTcbwService().load(hql + " order by id", this.getRecordStart(), this.getPageSize());
			if (objList != null && objList.size() > 0) {
				java.util.Map<String, String> ResMap = TCBWCommon.getCommonCodeMap("HFRURCR");
				for(Object dtlObj : objList) {				
					Hfr0002Db dtl = (Hfr0002Db)dtlObj;
					String[] rowArray = new String[7];
					rowArray[0] = Common.get(dtl.getHfr0001Db().getId());
					rowArray[1] = Common.formatYYYMMDD(Common.get(dtl.getHfr0001Db().getNotifierRevDate()),4);
					rowArray[2] = Common.get(dtl.getHfr0001Db().getApplNo());
					rowArray[3] = Common.get(dtl.getChProduct());
					rowArray[4] = "Y".equals(dtl.getHfr0001Db().getUnHealthIsYes())?"是":"N".equals(dtl.getHfr0001Db().getUnHealthIsYes())?"否":"";					
					rowArray[5] = ResMap.get(dtl.getHfr0001Db().getUnReactionResults());
					rowArray[6] = "附件";
					arrList.add(rowArray);
				}
				objList.clear();			
			}	
		}
		return arrList;
	}
	
	public String getFUROption(String className, String checkBoxFieldName, String selectedCheckBox){
    	StringBuilder sb = new StringBuilder();
    	java.util.Map<String, String> list = new java.util.TreeMap<String, String>(); 
    	ServiceGetter.getInstance().getCommonService().getCommonCodeDao().getCommonCodeNameMap("HFRURCR", list);
    	if(list!=null && list.size()>0){
    		for(Map.Entry<String, String> dtl : list.entrySet()){
    			sb.append("<input class=\"" ).append( className ).append( "\" type=\"radio\" name=\"" ).append( checkBoxFieldName ).append( "\" value=\"" ).append( dtl.getKey() ).append( "\"");
    			if(selectedCheckBox != null && !"".equals(selectedCheckBox)){
        				if(Common.get(dtl.getKey()).equals(selectedCheckBox)) sb.append(" checked");
    			}
    			sb.append(">").append(dtl.getValue());
    			sb.append("<br>");
    		}
    	}
        return sb.toString();    	
    }
	
	public DefaultTableModel getHistoryDefaultTableModel() throws Exception
	{
		String hql = getHqlHistory();
		
		DefaultTableModel model = new javax.swing.table.DefaultTableModel();
		java.util.List objList = new java.util.ArrayList<String[]>();
    	String[] cols = new String[]{"NO","NotifierRepDate","ApplNo","ChProduct","UnHealth","UnReaction"};
    	
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
	    objList = ServiceGetter.getInstance().getTcbwService().load(hql + " order by id");
    	if(objList!=null && objList.size()>0){
    		int i = 1;
			java.util.Map<String, String> ResMap = TCBWCommon.getCommonCodeMap("HFRURCR");
			for(Object dtlObj : objList) {				
				Hfr0002Db dtl = (Hfr0002Db)dtlObj;
				String[] rowArray = new String[cols.length];
				rowArray[0] = String.valueOf(i++);
				rowArray[1] = Common.formatYYYMMDD(Common.get(dtl.getHfr0001Db().getNotifierRevDate()),4);
				rowArray[2] = Common.get(dtl.getHfr0001Db().getApplNo());
				rowArray[3] = Common.get(dtl.getChProduct());
				rowArray[4] = "Y".equals(dtl.getHfr0001Db().getUnHealthIsYes())?"是":"N".equals(dtl.getHfr0001Db().getUnHealthIsYes())?"否":"";
				rowArray[5] = ResMap.get(dtl.getHfr0001Db().getUnReactionResults());
				arrList.add(rowArray);
			}
    	}
		if (arrList!=null && arrList.size()>0) 
		{
			String[][] rs = new String[0][0];
			rs = (String[][])arrList.toArray(rs);
			model.setDataVector(rs, cols);
		}	
		return model;   
	}
	
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
    	String hql = "from Hfr0001Db where 1=1 ";
		
		if(!"".equals(getId()))
			hql += "and id = " + Common.getLong(getId());
		if(!"".equals(getApplNo()))
			hql += "and applNo = " + Common.sqlChar(getApplNo());
		
		java.util.List list = ServiceGetter.getInstance().getCommonService().load(hql);
		if (list!=null && list.size()>0) {
			
			for (int i=0; i<list.size(); i++) {
				Hfr0001Db obj = (Hfr0001Db) list.get(i);
				Object rowArray[]=new Object[cols.length];
				rowArray[0]=Common.get(obj.getId());
				rowArray[1]=Common.get(obj.getApplNo());
				rowArray[2]=Common.get(obj.getStatus());
				rowArray[3]=Common.formatYYYMMDD(obj.getOccurDate(),2); 
				
				if(("Y".equals(Common.get(getIsCloseUserInfo()))) && (!"".equals(Common.get(getNotifierName())))) {
					rowArray[4]="●●●";
				} else if("N".equals(Common.get(getIsCloseUserInfo()))) {
					rowArray[4]=Common.get(obj.getNotifierName());
				} else {
					rowArray[4]=Common.get(obj.getNotifierName());
				}
				
				if(("Y".equals(Common.get(getIsCloseUserInfo()))) && (!"".equals(Common.get(getNotifierTel())))) {
					rowArray[5]="●●●●●●●●";
				} else if("N".equals(Common.get(getIsCloseUserInfo()))) {
					rowArray[5]=Common.get(obj.getNotifierTel());
				} else {
					rowArray[5]=Common.get(obj.getNotifierTel());
				}
				
				if(("Y".equals(Common.get(getIsCloseUserInfo()))) && (!"".equals(Common.get(getAddress())))) {
					rowArray[6]="●●●●●●●●●●●●●●●●";
				} else if("N".equals(Common.get(getIsCloseUserInfo()))) {
					rowArray[6]=Common.get(obj.getAddress());
				} else {
					rowArray[6]=Common.get(obj.getAddress());
				}
				
				if(("Y".equals(Common.get(getIsCloseUserInfo()))) && (!"".equals(Common.get(getNotifierEamil())))) {
					rowArray[7]="●●●●●●●●●●●●●●●●●●●";
				} else if("N".equals(Common.get(getIsCloseUserInfo()))) {
					rowArray[7]=Common.get(obj.getNotifierEamil());
				} else {
					rowArray[7]=Common.get(obj.getNotifierEamil());
				}
				
				if(("Y".equals(Common.get(getIsCloseUserInfo()))) && (!"".equals(Common.get(getEatersName())))) {
					rowArray[8]="●●●";
				} else if("N".equals(Common.get(getIsCloseUserInfo()))) {
					rowArray[8]=Common.get(obj.getEatersName());
				} else {
					rowArray[8]=Common.get(obj.getEatersName());
				}
				
				if(("Y".equals(Common.get(getIsCloseUserInfo()))) && (!"".equals(Common.get(getEatersHight())))) {
					rowArray[9]="●●●";
				} else if("N".equals(Common.get(getIsCloseUserInfo()))) {
					rowArray[9]=Common.get(obj.getEatersHight());
				} else {
					rowArray[9]=Common.get(obj.getEatersHight());
				}
				
				if(("Y".equals(Common.get(getIsCloseUserInfo()))) && (!"".equals(Common.get(getEatersWeight())))) {
					rowArray[10]="●●●";
				} else if("N".equals(Common.get(getIsCloseUserInfo()))) {
					rowArray[10]=Common.get(obj.getEatersWeight());
				} else {
					rowArray[10]=Common.get(obj.getEatersWeight());
				}
				
				if(("Y".equals(Common.get(getIsCloseUserInfo()))) && (!"".equals(Common.get(getEatersAge())))) {
					rowArray[11]="●●";
				} else if("N".equals(Common.get(getIsCloseUserInfo()))) {
					rowArray[11]=Common.get(obj.getEatersAge());
				} else {
					rowArray[11]=Common.get(obj.getEatersAge());
				}
				
				if("M".equals(Common.get(obj.getEatersSex()))) {
					rowArray[12]="■男　□女";
				} else if("F".equals(Common.get(obj.getEatersSex()))) {
					rowArray[12]="□男　■女";
				} else {
					rowArray[12]="□男　□女";
				}
				
				if(("Y".equals(Common.get(getIsCloseUserInfo()))) && (!"".equals(Common.get(getIsCloseUserInfo())))) {
					rowArray[13]="●●●●●●●●";
				} else if("N".equals(Common.get(getIsCloseUserInfo()))) {
					rowArray[13]=Common.get(obj.getEatersTel());
				} else {
					rowArray[13]=Common.get(obj.getEatersTel());
				}
				
				if(("Y".equals(Common.get(getIsCloseUserInfo()))) && (!"".equals(Common.get(getEatersIdNum())))) {
					rowArray[14]="●●●●●●●●●●";
				} else if("N".equals(Common.get(getIsCloseUserInfo()))) {
					rowArray[14]=Common.get(obj.getEatersIdNum());
				} else {
					rowArray[14]=Common.get(obj.getEatersIdNum());
				}
				
				
				if("Y".equals(obj.getUnHealthIsYes())) {
					rowArray[15]="■是(請簡述)" + Common.get(obj.getUnHealthBrief()) + "　□否";
					rowArray[16]=Common.get(obj.getUnHealthBrief());
				} else if("N".equals(obj.getUnHealthIsYes())) {
					rowArray[15]="□是(請簡述)____________________■否";
					rowArray[16]=Common.get(obj.getUnHealthBrief());
				}  else {
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
  			  	
  			    if(obj.getHfr0002Dbs()!=null && obj.getHfr0002Dbs().size()>0)
				{
					java.util.Iterator it2 = obj.getHfr0002Dbs().iterator();
					rowArray[33] = new JRTableModelDataSource(getSRSubModel01(it2));
				}
  			    
  			    if(obj.getHfr0002Dbs()!=null && obj.getHfr0002Dbs().size()>0)
				{
					java.util.Iterator it2 = obj.getHfr0002Dbs().iterator();
					rowArray[34] = new JRTableModelDataSource(getSRSubModel02(it2));
				}
  			   
  			    rowArray[35] = "產品名稱(中、英文)";
  			    
  			    if(obj.getHfr0002Dbs()!=null && obj.getHfr0002Dbs().size()>0)
				{
					java.util.Iterator it2 = obj.getHfr0002Dbs().iterator();
					rowArray[36] = new JRTableModelDataSource(getSRSubModel03(it2));
				}
  			  	
  			  	rowArray[37] = "產品名稱(中、英文)";
  			  	
  			   if(obj.getHfr0002Dbs()!=null && obj.getHfr0002Dbs().size()>0)
				{
					java.util.Iterator it2 = obj.getHfr0002Dbs().iterator();
					rowArray[38] = new JRTableModelDataSource(getSRSubModel04(it2));
				}
			  	
			  	rowArray[39] = "食用方式";
			  	
			  	if(obj.getHfr0002Dbs()!=null && obj.getHfr0002Dbs().size()>0)
				{
					java.util.Iterator it2 = obj.getHfr0002Dbs().iterator();
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
			Hfr0002Db hfr0002Db = (Hfr0002Db)it2.next();
			String[] rowArray= new String[col02.length];
			
			rowArray[0]=Common.get(hfr0002Db.getChProduct());
			rowArray[1]=Common.get(hfr0002Db.getEnProduct());
			rowArray[2]=getPermitKey(Common.get(hfr0002Db.getPermitKey())) + "字";
			rowArray[3]="第" + Common.get(hfr0002Db.getPermitNo()) + "號";
			//只填入許可證字為無"規"的
			if("H".equals(hfr0002Db.getPermitKey()) || "Q".equals(hfr0002Db.getPermitKey())) {
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
			Hfr0002Db hfr0002Db = (Hfr0002Db)it2.next();
			String[] rowArray= new String[col02.length];
			
			rowArray[0]=Common.get(hfr0002Db.getChProduct());
			rowArray[1]=Common.get(hfr0002Db.getEnProduct());
			rowArray[2]=getPermitKey(Common.get(hfr0002Db.getPermitKey())) + "字";
			rowArray[3]="第" + Common.get(hfr0002Db.getPermitNo()) + "號";
			//只填入許可證字為有"規"的
			if("J".equals(hfr0002Db.getPermitKey()) || "R".equals(hfr0002Db.getPermitKey())) {
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
			Hfr0002Db hfr0002Db = (Hfr0002Db)it2.next();
			String[] rowArray= new String[col02.length];
			
			rowArray[0]=Common.get(hfr0002Db.getChProduct());
			rowArray[1]=Common.get(hfr0002Db.getEnProduct());
			//只填入一般食品
			if("N".equals(hfr0002Db.getType())) {
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

		while(it2.hasNext())
		{
			Hfr0002Db hfr0002Db = (Hfr0002Db)it2.next();
			String[] rowArray= new String[col02.length];
			
			if(!"".equals(Common.get(hfr0002Db.getDoseDay()))) {
				rowArray[0]=Common.get(hfr0002Db.getDoseDay()) + "日";
			} else {
				rowArray[0]="____日";
			}
			
			if(!"".equals(Common.get(hfr0002Db.getDoseNum()))) {
				rowArray[1]=Common.get(hfr0002Db.getDoseNum()) + "次；";
			} else {
				rowArray[1]="____次；";
			}

			if(!"".equals(Common.get(hfr0002Db.getFrequency()))) {
				rowArray[2]="一次" + Common.get(hfr0002Db.getFrequency()) + "顆　或";
			} else {
				rowArray[2]="一次____顆　或";
			}
			
			if(!"".equals(Common.get(hfr0002Db.getFrequencyUnit()))) {
				rowArray[3]="一次" + Common.get(hfr0002Db.getFrequencyUnit()) + "ml";
			} else {
				rowArray[3]="一次____ml";
			}

			//只填入一般食品
			if("N".equals(hfr0002Db.getType())) {
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
			Hfr0002Db hfr0002Db = (Hfr0002Db)it2.next();
			String[] rowArray= new String[col02.length];
			
			if(!"".equals(Common.get(hfr0002Db.getEdibleDateS()))) {
				rowArray[0]=Common.formatYYYMMDD(hfr0002Db.getEdibleDateS()) + "　~　";
			} else {
				rowArray[0]="___年___月___日　~　";
			}
			
			if(!"".equals(Common.get(hfr0002Db.getEdibleDateE()))) {
				rowArray[1]=Common.get(hfr0002Db.getEdibleDateE());
			} else {
				rowArray[1]="___年___月___日";
			}
			//只填入一般食品
			if("N".equals(hfr0002Db.getType())) {
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
    	
    	String hql = "from Hfr0001Db where 1=1 ";
		
		if(!"".equals(getId()))
			hql += "and id = " + Common.getLong(getId());
		if(!"".equals(getApplNo()))
			hql += "and applNo = " + Common.sqlChar(getApplNo());
		
		java.util.List list = ServiceGetter.getInstance().getCommonService().load(hql);
		
		if (list!=null && list.size()>0) 
		{
			for (int i=0; i<list.size(); i++) 
			{
				Hfr0001Db obj = (Hfr0001Db) list.get(i);
				//String rowArray[]=new String[cols.length];
				Object[] rowArray= new Object[cols.length];
				
				rowArray[0]=Common.get(obj.getId());
				rowArray[1]=Common.get(obj.getApplNo());
				rowArray[2]=Common.get(obj.getStatus());
				rowArray[3]=Common.formatYYYMMDD(obj.getOccurDate(),2); 
				rowArray[4]=Common.formatYYYMMDD(obj.getNotifierRevDate(),2); 
				rowArray[5]=Common.formatYYYMMDD(obj.getNotifierRepDate(),2); 
				
				if(("Y".equals(Common.get(getIsCloseUserInfo()))) && (!"".equals(Common.get(getNotifierName())))) {
					rowArray[6]="●●●";
				} else if("N".equals(Common.get(getIsCloseUserInfo()))) {
					rowArray[6]=Common.get(obj.getNotifierName());
				} else {
					rowArray[6]=Common.get(obj.getNotifierName());
				}
				
				
				if(("Y".equals(Common.get(getIsCloseUserInfo()))) && (!"".equals(Common.get(getNotifierDept())))) {
					rowArray[7]="●●●●●●●●●●●●";
				} else if("N".equals(Common.get(getIsCloseUserInfo()))) {
					rowArray[7]=Common.get(obj.getNotifierDept());
				} else {
					rowArray[7]=Common.get(obj.getNotifierDept());
				}
				
				if(("Y".equals(Common.get(getIsCloseUserInfo()))) && (!"".equals(Common.get(getNotifierTel())))) {
					rowArray[8]="●●●●●●●●";
				} else if("N".equals(Common.get(getIsCloseUserInfo()))) {
					rowArray[8]=Common.get(obj.getNotifierTel());
				} else {
					rowArray[8]=Common.get(obj.getNotifierTel());
				}
				
				if(("Y".equals(Common.get(getIsCloseUserInfo()))) && (!"".equals(Common.get(getAddress())))) {
					rowArray[9]="●●●●●●●●●●●●●●●";
				} else if("N".equals(Common.get(getIsCloseUserInfo()))) {
					rowArray[9]=Common.get(obj.getAddress());
				} else {
					rowArray[9]=Common.get(obj.getAddress());
				}
				
				if(("Y".equals(Common.get(getIsCloseUserInfo()))) && (!"".equals(Common.get(getNotifierEamil())))) {
					rowArray[10]="●●●●●●●●●●●●●●●";
				} else if("N".equals(Common.get(getIsCloseUserInfo()))) {
					rowArray[10]=Common.get(obj.getNotifierEamil());
				} else {
					rowArray[10]=Common.get(obj.getNotifierEamil());
				}
				
				if(("Y".equals(Common.get(getIsCloseUserInfo()))) && (!"".equals(Common.get(getNotifierIdNum())))) {
					rowArray[11]="●●●●●●●●●●●";
				} else if("N".equals(Common.get(getIsCloseUserInfo()))) {
					rowArray[11]=Common.get(obj.getNotifierIdNum());
				} else {
					rowArray[11]=Common.get(obj.getNotifierIdNum());
				}
				
				
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
				
				if(("Y".equals(Common.get(getIsCloseUserInfo()))) && (!"".equals(Common.get(getEatersId())))) {
					rowArray[14]="●●●●●";
				} else if("N".equals(Common.get(getIsCloseUserInfo()))) {
					rowArray[14]=Common.get(obj.getEatersId());
				} else {
					rowArray[14]=Common.get(obj.getEatersId());
				}
				
				if(("Y".equals(Common.get(getIsCloseUserInfo()))) && (!"".equals(Common.get(getEatersName())))) {
					rowArray[15]="●●●";
				} else if("N".equals(Common.get(getIsCloseUserInfo()))) {
					rowArray[15]=Common.get(obj.getEatersName());
				} else {
					rowArray[15]=Common.get(obj.getEatersName());
				}
				
				if(("Y".equals(Common.get(getIsCloseUserInfo()))) && (!"".equals(Common.get(getEatersHight())))) {
					rowArray[16]="●●●";
				} else if("N".equals(Common.get(getIsCloseUserInfo()))) {
					rowArray[16]=Common.get(obj.getEatersHight());
				} else {
					rowArray[16]=Common.get(obj.getEatersHight());
				}
				
				if(("Y".equals(Common.get(getIsCloseUserInfo()))) && (!"".equals(Common.get(getEatersWeight())))) {
					rowArray[17]="●●●";
				} else if("N".equals(Common.get(getIsCloseUserInfo()))) {
					rowArray[17]=Common.get(obj.getEatersWeight());
				} else {
					rowArray[17]=Common.get(obj.getEatersWeight());
				}
				
				
				if("M".equals(Common.get(obj.getEatersSex()))) {
					rowArray[18]="■男　　□女";
				} else if("F".equals(Common.get(obj.getEatersSex()))) {
					rowArray[18]="□男　　■女";
				} else {
					rowArray[18]="□男　　□女";
				}
				
				if(("Y".equals(Common.get(getIsCloseUserInfo()))) && (!"".equals(Common.get(getEatersBirthYear())))) {
					rowArray[19]="●●●";
				} else if("N".equals(Common.get(getIsCloseUserInfo()))) {
					rowArray[19]=Common.get(obj.getEatersBirthYear());
				} else {
					rowArray[19]=Common.get(obj.getEatersBirthYear());
				}
				
				if(("Y".equals(Common.get(getIsCloseUserInfo()))) && (!"".equals(Common.get(getEatersBirthYear())))) {
					rowArray[19]="●●●";
				} else if("N".equals(Common.get(getIsCloseUserInfo()))) {
					rowArray[19]=Common.get(obj.getEatersBirthYear());
				} else {
					rowArray[19]=Common.get(obj.getEatersBirthYear());
				}
				
				if(("Y".equals(Common.get(getIsCloseUserInfo()))) && (!"".equals(Common.get(getEatersIdNum())))) {
					rowArray[20]="●●●●●●●●●●";
				} else if("N".equals(Common.get(getIsCloseUserInfo()))) {
					rowArray[20]=Common.get(obj.getEatersIdNum());
				} else {
					rowArray[20]=Common.get(obj.getEatersIdNum());
				}
				
				
				if("Y".equals(obj.getUnHealthIsYes())) {
					rowArray[21]="■是(請簡述)　□否";
					rowArray[22]=Common.get(obj.getUnHealthBrief());
				} else if("N".equals(obj.getUnHealthIsYes())) {
					rowArray[21]="□是(請簡述)　■否";
					rowArray[22]=Common.get(obj.getUnHealthBrief());
				} else {
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
			  	} else if("N".equals(Common.get(obj.getIsReactionResult()))) {
			  		rowArray[32]="□是　　■否";
			  	} else {
			  		rowArray[32]="□是　　□否";
			  	}
  			  	rowArray[33]=Common.get(obj.getEatersTel());
  			  	
  			  	
  			    if(obj.getHfr0002Dbs()!=null && obj.getHfr0002Dbs().size()>0)
				{
					java.util.Iterator it2 = obj.getHfr0002Dbs().iterator();
					rowArray[34] = new JRTableModelDataSource(getGRSubModel01(it2));
				}
  			    if(obj.getHfr0002Dbs()!=null && obj.getHfr0002Dbs().size()>0)
				{
					java.util.Iterator it2 = obj.getHfr0002Dbs().iterator();
					rowArray[35] = new JRTableModelDataSource(getGRSubModel02(it2));
				}
  			    if(obj.getHfr0002Dbs()!=null && obj.getHfr0002Dbs().size()>0)
				{
					java.util.Iterator it2 = obj.getHfr0002Dbs().iterator();
					rowArray[36] = new JRTableModelDataSource(getGRSubModel03(it2));
				}
  			    if(obj.getHfr0002Dbs()!=null && obj.getHfr0002Dbs().size()>0)
				{
					java.util.Iterator it2 = obj.getHfr0002Dbs().iterator();
					rowArray[37] = new JRTableModelDataSource(getGRSubModel04(it2));
				}
  			   if(obj.getHfr0002Dbs()!=null && obj.getHfr0002Dbs().size()>0)
				{
					java.util.Iterator it2 = obj.getHfr0002Dbs().iterator();
					rowArray[38] = new JRTableModelDataSource(getGRSubModel05(it2));
				}
  			   if(obj.getHfr0002Dbs()!=null && obj.getHfr0002Dbs().size()>0)
				{
					java.util.Iterator it2 = obj.getHfr0002Dbs().iterator();
					while(it2.hasNext())
					{
						//判斷許可證字有"規"或無"規"
		  			    String permitKey = "";
		  			    String permitNo = "";
		  			    String permitKey1 = "";
					    String permitNo1 = "";
					    Hfr0002Db hfr0002db = (Hfr0002Db) it2.next();;
		  			    if((("H".equals(hfr0002db.getPermitKey())) || ("Q".equals(hfr0002db.getPermitKey())))) {
		  				  permitKey = getPermitKey(Common.get(hfr0002db.getPermitKey()));
		  				  permitNo = Common.get(hfr0002db.getPermitNo());
		  			  	} else {
		  			  		permitKey = "";
		  			  		permitNo = "";
		  			  	}
		  			    
		  			    if((("J".equals(hfr0002db.getPermitKey())) || ("R".equals(hfr0002db.getPermitKey())))) {
		  			    	permitKey1=getPermitKey(Common.get(hfr0002db.getPermitKey()));
		  			    	permitNo1=Common.get(hfr0002db.getPermitNo());
		  			  	} else {
		  			  		permitKey1="";
		  			  		permitNo1="";
		  			  	}
		 			    rowArray[39] = "22.健康食品\n(" + permitKey + "字號\n第" + permitNo + "號)";
		  			    rowArray[40] = "23.健康食品\n(" + permitKey1 + "字號\n第" + permitNo1 + "號)";
					}
				} else {
					rowArray[39] = "22.健康食品\n_____字號\n第_____號";
					rowArray[40] = "23.健康食品\n_____字號\n第_____號";
				}

  			    rowArray[41] = "24.非衛生署核淮為健康食品之錠、膠劑型食品";
  			    rowArray[42] = "25.併用藥品(西藥、中藥)";
  			    rowArray[43] = "26.併用其他錠、膠劑型食品";
    			if(obj.getHfr0003Dbs()!=null && obj.getHfr0003Dbs().size()>0)
   				{
   					java.util.Iterator it2 = obj.getHfr0003Dbs().iterator();
   					rowArray[44] = new JRTableModelDataSource(getGRSubModel06(it2));
   				}
      			if(obj.getHfr0004Dbs()!=null && obj.getHfr0004Dbs().size()>0)
   				{
   					java.util.Iterator it2 = obj.getHfr0004Dbs().iterator();
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

		while(it2.hasNext())
		{
			Hfr0002Db hfr0002Db = (Hfr0002Db)it2.next();
			String[] rowArray= new String[col02.length];
			
			rowArray[0]=Common.get(hfr0002Db.getChProduct());
			rowArray[1]=Common.get(hfr0002Db.getIngredient());
			rowArray[2]=Common.get(hfr0002Db.getContent());
			rowArray[3]=Common.get(hfr0002Db.getDoseNum());
			rowArray[4]=Common.get(hfr0002Db.getDoseDay());
			rowArray[5]=Common.get(hfr0002Db.getFrequency());
			rowArray[6]=Common.get(hfr0002Db.getFrequencyUnit());
			rowArray[7]=Common.formatYYYMMDD(hfr0002Db.getEdibleDateS());
			rowArray[8]=Common.formatYYYMMDD(hfr0002Db.getEdibleDateE());
			rowArray[9]=getBuySource(Common.get(hfr0002Db.getBuySource()));
			
			if("H".equals(hfr0002Db.getPermitKey()) || "Q".equals(hfr0002Db.getPermitKey())) {
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

		while(it2.hasNext())
		{
			Hfr0002Db hfr0002Db = (Hfr0002Db)it2.next();
			String[] rowArray= new String[col02.length];
			
			rowArray[0]=Common.get(hfr0002Db.getChProduct());
			rowArray[1]=Common.get(hfr0002Db.getIngredient());
			rowArray[2]=Common.get(hfr0002Db.getContent());
			rowArray[3]=Common.get(hfr0002Db.getDoseNum());
			rowArray[4]=Common.get(hfr0002Db.getDoseDay());
			rowArray[5]=Common.get(hfr0002Db.getFrequency());
			rowArray[6]=Common.get(hfr0002Db.getFrequencyUnit());
			rowArray[7]=Common.formatYYYMMDD(hfr0002Db.getEdibleDateS());
			rowArray[8]=Common.formatYYYMMDD(hfr0002Db.getEdibleDateE());
			rowArray[9]=getBuySource(Common.get(hfr0002Db.getBuySource()));

			if("J".equals(hfr0002Db.getPermitKey()) || "R".equals(hfr0002Db.getPermitKey())) {
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

		while(it2.hasNext())
		{
			Hfr0002Db hfr0002Db = (Hfr0002Db)it2.next();
			String[] rowArray= new String[col02.length];
			
			rowArray[0]=Common.get(hfr0002Db.getChProduct());
			rowArray[1]=Common.get(hfr0002Db.getIngredient());
			rowArray[2]=Common.get(hfr0002Db.getContent());
			rowArray[3]=Common.get(hfr0002Db.getDoseNum());
			rowArray[4]=Common.get(hfr0002Db.getDoseDay());
			rowArray[5]=Common.get(hfr0002Db.getFrequency());
			rowArray[6]=Common.get(hfr0002Db.getFrequencyUnit());
			rowArray[7]=Common.formatYYYMMDD(hfr0002Db.getEdibleDateS());
			rowArray[8]=Common.formatYYYMMDD(hfr0002Db.getEdibleDateE());
			rowArray[9]=getBuySource(Common.get(hfr0002Db.getBuySource()));
			
			if("N".equals(hfr0002Db.getType())) {
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

		while(it2.hasNext())
		{
			Hfr0002Db hfr0002Db = (Hfr0002Db)it2.next();
			String[] rowArray= new String[col02.length];
			
			rowArray[0]=Common.get(hfr0002Db.getChProduct());
			rowArray[1]=Common.get(hfr0002Db.getEnProduct());
			rowArray[2]=Common.get(hfr0002Db.getContent());
			rowArray[3]=Common.get(hfr0002Db.getMedModel());
			rowArray[4]=Common.get(hfr0002Db.getDoseNum());
			rowArray[5]=Common.get(hfr0002Db.getDoseDay());
			rowArray[6]=Common.get(hfr0002Db.getFrequency());
			rowArray[7]=Common.get(hfr0002Db.getFrequencyUnit());
			rowArray[8]=Common.formatYYYMMDD(hfr0002Db.getEdibleDateS());
			rowArray[9]=Common.formatYYYMMDD(hfr0002Db.getEdibleDateE());
			rowArray[10]=Common.get(hfr0002Db.getBrand());
			rowArray[11]=Common.get(hfr0002Db.getPermitKey());
			
			if("D".equals(hfr0002Db.getType())) {
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

		while(it2.hasNext())
		{
			Hfr0002Db hfr0002Db = (Hfr0002Db)it2.next();
			String[] rowArray= new String[col02.length];
			
			rowArray[0]=Common.get(hfr0002Db.getChProduct());
			rowArray[1]=Common.get(hfr0002Db.getEnProduct());
			rowArray[2]=Common.get(hfr0002Db.getContent());
			rowArray[3]=Common.get(hfr0002Db.getMedModel());
			rowArray[4]=Common.get(hfr0002Db.getDoseNum());
			rowArray[5]=Common.get(hfr0002Db.getDoseDay());
			rowArray[6]=Common.get(hfr0002Db.getFrequency());
			rowArray[7]=Common.get(hfr0002Db.getFrequencyUnit());
			rowArray[8]=Common.formatYYYMMDD(hfr0002Db.getEdibleDateS());
			rowArray[9]=Common.formatYYYMMDD(hfr0002Db.getEdibleDateE());
			rowArray[10]=Common.get(hfr0002Db.getBrand());
			rowArray[11]=Common.get(hfr0002Db.getPermitKey());
			
			if("O".equals(hfr0002Db.getType())) {
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
			Hfr0003Db hfr0003Db = (Hfr0003Db)it2.next();
			String[] rowArray= new String[col02.length];
			
			rowArray[0]=Common.formatYYYMMDD(Common.get(hfr0003Db.getDiscriptDate()), 2);
			rowArray[1]=Common.get(hfr0003Db.getPosition());
			rowArray[2]=Common.get(hfr0003Db.getSymptom());
			rowArray[3]=Common.get(hfr0003Db.getSeverity());
			rowArray[4]=Common.get(hfr0003Db.getDoResponse());
			
			
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
			Hfr0004Db hfr0004Db = (Hfr0004Db)it2.next();
			String[] rowArray= new String[col02.length];
			
			rowArray[0]=Common.formatYYYMMDD(Common.get(hfr0004Db.getDoctorDate()), 2);
			rowArray[1]=Common.get(hfr0004Db.getBloodIndex());
			rowArray[2]=Common.get(hfr0004Db.getLiverIndex());
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
	
	
	public String genHfr0003DbItemSet(java.util.Set dtlSet) throws Exception{
	    if(dtlSet!=null && dtlSet.size()>0){
	    	StringBuilder sb = new StringBuilder(1024);
	    	for(Object dtlObj : dtlSet){
	    		Hfr0003Db dtl = (Hfr0003Db)dtlObj;
	    		sb.append("addDiscript('tabDiscript'");
	    		sb.append(",").append(Common.sqlChar(dtl.getDiscriptDate()));
	    		sb.append(",").append(Common.sqlChar(dtl.getPosition()));
	    		sb.append(",").append(Common.sqlChar(dtl.getSymptom()));
	    		sb.append(",").append(Common.sqlChar(dtl.getSeverity()));
	    		sb.append(",").append(Common.sqlChar(dtl.getDoResponse()));
				sb.append(",'").append(dtl.getId()!=null?dtl.getId():"").append("'");
				sb.append(");\n");
	    	}
	    	this.setDiscriptSBuilder(sb.toString());	
	    }else if(getHttpRequest()!=null && getDiscriptRow()!=null && getDiscriptRow().length>0) {
			String v = "";
			StringBuilder sb = new StringBuilder(1024);
			for(int i=0; i<getDiscriptRow().length; i++){
				String rid = getDiscriptRow()[i];
				
				sb.append("addDiscript('tabDiscript'");
				for(int j=0; j<discriptFieldName.length; j++){				
					v = Common.escapeReturnChar(StringEscapeUtils.escapeHtml(getHttpRequest().getParameter(discriptFieldName[j] + rid)), true);				
					sb.append(",").append(Common.sqlChar(v));				
				}
				sb.append(",'").append(rid).append("'");
				sb.append(");\n");
			}
			this.setDiscriptSBuilder(sb.toString());
	    }
	    return "";
	}
	
	public String genHfr0004DbItemSet(java.util.Set dtlSet) throws Exception{
	    if(dtlSet!=null && dtlSet.size()>0){
	    	StringBuilder sb = new StringBuilder(1024);
	    	for(Object dtlObj : dtlSet){
	    		Hfr0004Db dtl = (Hfr0004Db)dtlObj;
	    		sb.append("addDoctor('tabDoctor'");
	    		sb.append(",").append(Common.sqlChar(dtl.getDoctorDate()));
	    		sb.append(",").append(Common.sqlChar(dtl.getBloodIndex()));
	    		sb.append(",").append(Common.sqlChar(dtl.getLiverIndex()));
	    		sb.append(",").append(Common.sqlChar(dtl.getRenalIndex()));
				sb.append(",'").append(dtl.getId()!=null?dtl.getId():"").append("'");
				sb.append(");\n");
	    	}
	    	this.setDoctorSBuilder(sb.toString());
	    }else if(getHttpRequest()!=null && getDiscriptRow()!=null && getDiscriptRow().length>0) {
			String v = "";
			StringBuilder sb = new StringBuilder(1024);
			for(int i=0; i<getDoctorRow().length; i++){
				String rid = getDoctorRow()[i];
				
				sb.append("addDoctor('tabDoctor'");
				for(int j=0; j<doctorFieldName.length; j++){				
					v = Common.escapeReturnChar(StringEscapeUtils.escapeHtml(getHttpRequest().getParameter(doctorFieldName[j] + rid)), true);				
					sb.append(",").append(Common.sqlChar(v));				
				}
				sb.append(",'").append(rid).append("'");
				sb.append(");\n");
			}
			this.setDoctorSBuilder(sb.toString());
	    }
	    return "";
	}
	
	public String genDrHfr0002DbItemSet(java.util.List<Hfr0002Db> dtlList, String type) throws Exception{
	    if(dtlList!=null && dtlList.size()>0){
	    	StringBuilder sb = new StringBuilder(1024);        	
	    	for(Hfr0002Db dtl : dtlList){
	    		sb.append("addDrugRow").append(type).append("('tabDrug" + type + "'");
	    		sb.append(",").append(Common.sqlChar(dtl.getChProduct()));
	    		sb.append(",").append(Common.sqlChar(dtl.getEnProduct()));
	    		sb.append(",").append(Common.sqlChar(dtl.getContent()));
	    		sb.append(",").append(Common.sqlChar(dtl.getMedModel()));
	    		sb.append(",").append(Common.sqlChar(dtl.getDoseNum()));
	    		sb.append(",").append(Common.sqlChar(dtl.getDoseDay()));
	    		sb.append(",").append(Common.sqlChar(dtl.getFrequency()));
	    		sb.append(",").append(Common.sqlChar(dtl.getFrequencyUnit()));
	    		sb.append(",").append(Common.sqlChar(dtl.getEdibleDateS()));
	    		sb.append(",").append(Common.sqlChar(dtl.getEdibleDateE()));
	    		sb.append(",").append(Common.sqlChar(dtl.getBrand()));
	    		sb.append(",").append(Common.sqlChar(dtl.getPermitNo()));
				sb.append(",'").append(dtl.getId()!=null?dtl.getId():"").append("'");
				sb.append(");\n");
	    	}
	    	if("dr".equals(type)){
	    		this.setDrugSBuilder(sb.toString());
	    	}else if("odr".equals(type)){
	    		this.setODrugSBuilder(sb.toString());
	    	}
	    }else{
	    	// 應該不會用到，暫不寫
	    }
	    return "";
	}
	
	public void setTimeForm(Hfr0001Db c) throws Exception{
		this.setAdditionalFinshDateHis(c.getAdditionalFinshDate());
		this.setEnrolledDateHis(c.getEnrolledDate());
		this.setAdditionalDateHis(c.getAdditionalDate());
		this.setMedicalPostingDateHis(c.getMedicalPostingDate());
		this.setMedicalCompleteDateHis(c.getMedicalCompleteDate());
		this.setInspectPostingDateHis(c.getInspectPostingDate());
		this.setInspectCompleteDateHis(c.getInspectCompleteDate());
		this.setInspectCompleteDateHis(c.getInspectCompleteDate());
		this.setReEvaluateResultHis("Y".equals(c.getIsReactionResult())?"是":"N".equals(c.getIsReactionResult())?"否":"");	//是否告知案件評估結果
		Hfr0005Db hfr05 = (Hfr0005Db)View.getObject(" from Hfr0005Db where hfr0001Db.id = " + Common.getLong(c.getId()) + " order by id desc");
		if(hfr05 != null){
			this.setPreCompleteDateHis(hfr05.getPreCompleteDate());
			this.setAssessmentSendDateHis(hfr05.getAssessmentSendDate());
			this.setAssessmentCompleteDateHis(hfr05.getAssessmentCompleteDate());
		}else{
			this.setPreCompleteDateHis("");
			this.setAssessmentSendDateHis("");
			this.setAssessmentCompleteDateHis("");
		}
		Hfr0007Db hfr07 = (Hfr0007Db)View.getObject(" from Hfr0007Db where hfr0001Db.id = " + Common.getLong(c.getId()) + " order by id desc");
		if(hfr07 != null){
			this.setCommitteeDateHis(hfr07.getCommitteeDate());
			this.setRecordDateHis(hfr07.getRecordDate());
			this.setCaseBackDateHis(hfr07.getCaseBackDate());
		}else{
			this.setCommitteeDateHis("");
			this.setRecordDateHis("");
			this.setCaseBackDateHis("");
		}
	}

	// HFR0005_DB，最新一筆需秀在畫面上
	public void setHFR0005Form(Hfr0001Db c, java.util.List<Con0001Db> fileList) throws Exception{
		java.util.List<Hfr0005Db> hfr0005DbList = ServiceGetter.getInstance().getTcbwService().load(" from Hfr0005Db where hfr0001Db.id = " + Common.getLong(c.getId()) + " order by id desc ");
		if(hfr0005DbList!=null && hfr0005DbList.size()>0){
			boolean flag = true;
			java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
			for(Hfr0005Db dtl : hfr0005DbList){
				if(flag){
					this.setHfr0005Id(Common.get(dtl.getId()));
					this.setPreCompleteDate(dtl.getPreCompleteDate());
					this.setAssessmentSendDate(dtl.getAssessmentSendDate());
					this.setAssessmentCompleteDate(dtl.getAssessmentCompleteDate());
					this.setCaseType(dtl.getCaseType());
					this.setUnExpectedClassify(dtl.getUnExpectedClassify());
					
					if(!"".equals(Common.get(dtl.getUnExpectedReason()))){
						String[] t = Common.get(dtl.getUnExpectedReason()).split(",");
						this.setUnExpectedReason(t);
					}else{
						this.setUnExpectedReason(null);
					}
					this.setEvidentiary(dtl.getEvidentiary());
					this.setPreSeverity(dtl.getPreSeverity());
					this.setAdministrativeLevel(dtl.getAdministrativeLevel());
					this.setPreOpinion(dtl.getPreOpinion());
					this.setPreRemark(dtl.getPreRemark());
					this.setPreExplain(dtl.getPreExplain());
					this.setUnPreExplain(dtl.getUnPreExplain());
					
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
			this.setHfr0005DbList(arrList);
			hfr0005DbList.clear();
		}else{
			this.setHfr0005DbList(null);
			this.setHfr0005Id("");
		}
	}
	
	public void setHFR0007Form(Hfr0001Db c, java.util.List<Con0001Db> fileList) throws Exception{
		
		java.util.List<Hfr0007Db> hfr0007DbList = ServiceGetter.getInstance().getTcbwService().load(" from Hfr0007Db where hfr0001Db.id = " + Common.getLong(c.getId()) + " order by id desc ");
		if(hfr0007DbList!=null && hfr0007DbList.size()>0){
			boolean flag = true;
			java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
			for(Hfr0007Db dtl : hfr0007DbList){
				if(flag){
					this.setHfr0007Id(Common.get(dtl.getId()));
					this.setCommitteeMeeting(dtl.getCommitteeMeeting());
					this.setCommitteeDate(dtl.getCommitteeDate());
					this.setRecordDate(dtl.getRecordDate());
					this.setCaseBackDate(dtl.getCaseBackDate());
					this.setThiUnExpectedClassify(dtl.getUnExpectedClassify());
					if(!"".equals(Common.get(dtl.getUnExpectedReason()))){
						String[] t = Common.get(dtl.getUnExpectedReason()).split(",");
						this.setThiUnExpectedReason(t);
					}else{
						this.setThiUnExpectedReason(null);
					}
					
					this.setThiEvidentiary(dtl.getEvidentiary());
					this.setThiSeverity(dtl.getThiSeverity());
					this.setThiAdministrativeLevel(dtl.getAdministrativeLevel());
					this.setReEvaluateResult(dtl.getReEvaluateResult());
					this.setThiExplain(dtl.getThiExplain());
					this.setUnThiExplain(dtl.getUnThiExplain());
					
					fileList = ServiceGetter.getInstance().getTcbwService().load(" from Con0001Db where fileKind = 'HFRRE' and dbName = 'HFR0007DB' and upLoadId = " + Common.getLong(dtl.getId()));
					genFileRowItemSet(fileList, "HFRRE");
					fileList.clear();
					flag = false;
				}
				String[] rowArray = new String[3];
				rowArray[0] = Common.get(dtl.getId());
				rowArray[1] = Common.get(dtl.getCommitteeDate());
				rowArray[2] = Common.get(dtl.getRecordDate());
				arrList.add(rowArray);
			}
			this.setHfr0007DbList(arrList);	
		}else{
			this.setHfr0007Id("");
			this.setHfr0007DbList(null);	
		}
		
		/*
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
					this.setHfr0007Id(Common.get(dtl.getId()));
					this.setCommitteeMeeting(dtl.getCommitteeMeeting());
					this.setCommitteeDate(dtl.getCommitteeDate());
					this.setRecordDate(dtl.getRecordDate());
					this.setCaseBackDate(dtl.getCaseBackDate());
					this.setThiUnExpectedClassify(dtl.getUnExpectedClassify());
					this.setThiUnExpectedReason(dtl.getUnExpectedReason());
					this.setThiEvidentiary(dtl.getEvidentiary());
					this.setThiSeverity(dtl.getThiSeverity());
					this.setThiAdministrativeLevel(dtl.getAdministrativeLevel());
					this.setReEvaluateResult(dtl.getReEvaluateResult());
					fileList = ServiceGetter.getInstance().getTcbwService().load(" from Con0001Db where fileKind = 'HFRRE' and dbName = 'HFR0007DB' and upLoadId = " + Common.getLong(dtl.getId()));
					genFileRowItemSet(fileList, "HFRRE");
					fileList.clear();
					flag = false;
				}
			}
		}
		this.setHfr0007DbList(arrList);		
		// 若沒有未更新完的資料，清空畫面，表示此次為新的複評作業，先清空ID，防止衝突
		if(flag){
			this.setHfr0007Id("");
		}
		*/
	}
	
	public String getHqlHistory(){
		String permit1 = "", permit2 = "";
		String hql = "from Hfr0002Db where hfr0001Db.id = " + Common.getLong(getId());
		java.util.List objList = ServiceGetter.getInstance().getCommonService().load(hql);
		if(objList == null)
			return null;
		for(Object dtlObj : objList){
			Hfr0002Db dtl = (Hfr0002Db)dtlObj;
			if(!"".equals(Common.get(dtl.getPermitKey())))
				permit1 += Common.sqlChar(dtl.getPermitKey()+dtl.getPermitNo()) + ",";
			else
				permit2 += Common.sqlChar(dtl.getPermitNo()) + ",";
		}
		objList.clear();
		
		hql = "from Hfr0002Db where hfr0001Db.applNo is not null and hfr0001Db.notifierRevDate <= " + Common.sqlChar(getNotifierRepDate());
		if(!"".equals(permit1) && !"".equals(permit2)){
			hql += " and ((permitKey is not null and permitKey||permitNo in("+permit1.substring(0, permit1.length()-1)+"))";
			hql += " or (permitKey is null and permitNo in("+permit2.substring(0, permit2.length()-1)+")))";
		}else if(!"".equals(permit1)){
			hql += " and (permitKey is not null and permitKey||permitNo in("+permit1.substring(0, permit1.length()-1)+"))";
		}else if(!"".equals(permit2)){
			hql += " and (permitKey is null and permitNo in("+permit2.substring(0, permit2.length()-1)+"))";
		}
		
		if(!"".equals(getQ_notifierRepDateSHis()))
			hql += " and hfr0001Db.notifierRepDate >= " + Common.sqlChar(getQ_notifierRepDateSHis());
		if(!"".equals(getQ_notifierRepDateEHis()))
			hql += " and hfr0001Db.notifierRevDate <= " + Common.sqlChar(getQ_notifierRepDateEHis());
		if(!"".equals(getQ_unReactionResultsHis()))
			hql += " and hfr0001Db.unReactionResults = " + Common.sqlChar(getQ_unReactionResultsHis());
		if(!"".equals(getQ_productHis()))
			hql += " and (chProduct like " + Common.sqlChar("%" + getQ_productHis() + "%") + " or enProduct like " + Common.sqlChar("%" + getQ_productHis() + "%") + ")";
		
		return hql;
	}
	
}
	