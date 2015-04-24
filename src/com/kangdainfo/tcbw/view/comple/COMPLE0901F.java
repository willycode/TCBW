package com.kangdainfo.tcbw.view.comple;

import org.apache.commons.collections.set.ListOrderedSet;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.log4j.Logger;
import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.Datetime;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Con0001Db;
import com.kangdainfo.tcbw.model.bo.Drg0003Db;
import com.kangdainfo.tcbw.model.bo.Hfr0001Db;
import com.kangdainfo.tcbw.model.bo.Hfr0002Db;
import com.kangdainfo.tcbw.model.bo.Hfr0003Db;
import com.kangdainfo.tcbw.model.bo.Hfr0004Db;
import com.kangdainfo.tcbw.model.bo.Hfr0005Db;
import com.kangdainfo.tcbw.model.bo.Hfr0006Db;
import com.kangdainfo.tcbw.model.bo.Hfr0007Db;
import com.kangdainfo.tcbw.model.bo.Hfr4001Db;
import com.kangdainfo.tcbw.util.TCBWCommon;
import com.kangdainfo.tcbw.view.hfrin.HFRIN0102Q;

public class COMPLE0901F extends HFRIN0102Q{

	private String id;//序號	NUMERIC(19,0)	
	private String tabId ;
	private String toUpdate;  //檔案上傳後維持在修改模式
	
	private String hfr05Update; //是否新增修改hfr05
	private String hfr06Update; //是否新增修改hfr06
	private String hfr07Update; //是否新增修改hfr07
	
	private String q_isQuery;
	private String q_id;
	private String q_applNoS;
	private String q_applNoE;
	private String q_occurDateS;
	private String q_occurDateE;
	private String q_notifierRevDateS;
	private String q_notifierRevDateE;
	private String q_notifierRepDateS;
	private String q_notifierRepDateE;
	private String q_informedSources;
	private String q_permitKey;
	private String q_permitNo;
	private String q_productName;
	private String q_ingredient;
	private String q_buySource;
	private String q_unExpectedClassify;
	private String q_unExpectedReason;
	private String q_evidentiary;
	private String q_thiSeverity;
	private String q_administrativeLevel;
	private String q_status;


	@Override
	public Object doQueryOne() throws Exception {
		COMPLE0901F obj = this;
		Hfr0001Db c = (Hfr0001Db)View.getObject(" from Hfr0001Db where id = " + Common.getLong(getId()));		
		if(c != null){
			if("1".equals(c.getHfrType())){
				//簡表
				setHfrType1Case(c, obj);
			}else{
				//一般表
				setHfrType2Case(c, obj);				
			}
			//obj.setHfr40001Id(Common.get(c.getHfr4001DbId()));
			
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
			
			// HFR0005_DB
			Hfr0005Db hfr05 = (Hfr0005Db) View.getObject(" from Hfr0005Db where hfr0001Db.id = " + Common.getLong(c.getId()) + " order by id desc ");
			
			if(hfr05!=null){
				obj.setHfr0005Id(Common.get(hfr05.getId()));
				obj.setPreCompleteDate(hfr05.getPreCompleteDate());
				obj.setAssessmentSendDate(hfr05.getAssessmentSendDate());
				obj.setAssessmentCompleteDate(hfr05.getAssessmentCompleteDate());
				obj.setCaseType(hfr05.getCaseType());
				obj.setUnExpectedClassify(hfr05.getUnExpectedClassify());
				
				if(!"".equals(Common.get(hfr05.getUnExpectedReason()))){
					String[] t = Common.get(hfr05.getUnExpectedReason()).split(",");
					obj.setUnExpectedReason(t);
				}else{
					obj.setUnExpectedReason(null);
				}
				obj.setEvidentiary(hfr05.getEvidentiary());
				obj.setPreSeverity(hfr05.getPreSeverity());
				obj.setAdministrativeLevel(hfr05.getAdministrativeLevel());
				obj.setPreOpinion(hfr05.getPreOpinion());
				obj.setPreRemark(hfr05.getPreRemark());
				obj.setPreExplain(hfr05.getPreExplain());
				obj.setUnPreExplain(hfr05.getUnPreExplain());
				obj.setHfr0005DbList(null);
				fileList = ServiceGetter.getInstance().getTcbwService().load(" from Con0001Db where fileKind = 'HFRFO' and dbName = 'HFR0005DB' and upLoadId = " + Common.getLong(hfr05.getId()));
				genFileRowItemSet(fileList, "HFRFO");
				fileList.clear();
			}else{
				obj.setHfr0005DbList(null);
				obj.setHfr0005Id("");
			}
			
			// HFR0006_DB
			obj.setHfr0006Id("");
			java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
			if(c.getHfr0006Dbs()!=null && c.getHfr0006Dbs().size()>0){
				for(Object dtlObj : c.getHfr0006Dbs()){
					Hfr0006Db dtl = (Hfr0006Db)dtlObj;
					
					String[] rowArray = new String[4];
					rowArray[0] = Common.get(dtl.getId());
					rowArray[1] = Common.get(dtl.getEvaluateDate());
					rowArray[2] = View.getLookupField("select hfr1001Db.name from Hfr1002Db where id = " + Common.getLong(dtl.getEvaluateCommittee()));
					rowArray[3] = Common.get("N");
					arrList.add(rowArray);
				}
			}
			obj.setHfr0006DbList(arrList);
			
			// HFR0007_DB
            Hfr0007Db hfr07 = (Hfr0007Db) View.getObject(" from Hfr0007Db where hfr0001Db.id = " + Common.getLong(c.getId()) + " order by id desc ");			
			if(hfr07!=null){
				obj.setHfr0007Id(Common.get(hfr07.getId()));
				obj.setCommitteeMeeting(hfr07.getCommitteeMeeting());
				obj.setCommitteeDate(hfr07.getCommitteeDate());
				obj.setRecordDate(hfr07.getRecordDate());
				obj.setCaseBackDate(hfr07.getCaseBackDate());
				obj.setThiUnExpectedClassify(hfr07.getUnExpectedClassify());
				if(!"".equals(Common.get(hfr07.getUnExpectedReason()))){
					String[] t = Common.get(hfr07.getUnExpectedReason()).split(",");
					obj.setThiUnExpectedReason(t);
				}else{
					obj.setThiUnExpectedReason(null);
				}
				
				obj.setThiEvidentiary(hfr07.getEvidentiary());
				obj.setThiSeverity(hfr07.getThiSeverity());
				obj.setThiAdministrativeLevel(hfr07.getAdministrativeLevel());
				obj.setReEvaluateResult(hfr07.getReEvaluateResult());
				obj.setThiExplain(hfr07.getThiExplain());
				obj.setUnThiExplain(hfr07.getUnThiExplain());
				
				fileList = ServiceGetter.getInstance().getTcbwService().load(" from Con0001Db where fileKind = 'HFRRE' and dbName = 'HFR0007DB' and upLoadId = " + Common.getLong(hfr07.getId()));
				genFileRowItemSet(fileList, "HFRRE");
				fileList.clear();
			}			
		}
		this.setState("queryOneSuccess");
		return obj;
	}
	
	public void setHfrType1Case(Hfr0001Db c, COMPLE0901F obj) throws Exception{
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
	
	public void setHfrType2Case(Hfr0001Db c, COMPLE0901F obj) throws Exception{
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
		if(c.getHfr0003Dbs() != null && c.getHfr0003Dbs().size()>0){
			genHfr0003DbItemSet(c.getHfr0003Dbs());
		}
		if(c.getHfr0004Dbs() != null && c.getHfr0004Dbs().size()>0){		
			genHfr0004DbItemSet(c.getHfr0004Dbs());
		}
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

	@Override
	public Object doQueryAll() throws Exception {		
		if (!"".equals(getQ_isQuery())){
			setQ_id("");
		}
		
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		String hql = " from Hfr0002Db where 1 = 1 and type in ('N','G') and trans = 'Y'";
		if(!"".equals(getQ_id())){
			hql += " and id = " + Common.getLong(getQ_id());
		}else{
			if(!"".equals(getQ_applNoS())) 
				hql += " and hfr0001Db.applNo >= " +  Common.sqlChar(getQ_applNoS());
			if(!"".equals(getQ_applNoE())) 
				hql += " and hfr0001Db.applNo <= " + Common.sqlChar(getQ_applNoE());
			
			if(!"".equals(getQ_occurDateS())) 
				hql += " and hfr0001Db.occurDate >= " + Common.sqlChar(getQ_occurDateS());
			if(!"".equals(getQ_occurDateE()))
				hql += " and hfr0001Db.occurDate <= " + Common.sqlChar(getQ_occurDateE());
			//收案日期
			if(!"".equals(getQ_notifierRevDateS())) 
				hql += " and hfr0001Db.enrolledDate >= " + Common.sqlChar(getQ_notifierRevDateS());
			if(!"".equals(getQ_notifierRevDateE())) 
				hql += " and hfr0001Db.enrolledDate <= " + Common.sqlChar(getQ_notifierRevDateE());
			
			if(!"".equals(getQ_notifierRepDateS())) 
				hql += " and hfr0001Db.notifierRepDate >= " + Common.sqlChar(getQ_notifierRepDateS());
			if(!"".equals(getQ_notifierRepDateE())) 
				hql += " and hfr0001Db.notifierRepDate <= " + Common.sqlChar(getQ_notifierRepDateE());
			
			if(!"".equals(getQ_informedSources())) 
				hql += " and hfr0001Db.informedSources = " + Common.sqlChar(getQ_informedSources());
			
			if(!"".equals(getQ_permitKey())) 
				hql += " and permitKey = " + Common.sqlChar(getQ_permitKey());
			
			if(!"".equals(getQ_permitNo())) 
				hql += " and permitNo = " + Common.sqlChar(getQ_permitNo());
			
			if(!"".equals(getQ_productName())) 
				hql += " and (chProduct like " + Common.sqlChar("%" + getQ_productName() + "%")  + " or enProduct like " + Common.sqlChar("%" + getQ_productName() + "%") + ")";
		
			if(!"".equals(getQ_ingredient())) 
				hql += " and ingredient like " + Common.sqlChar("%" + getQ_ingredient() + "%");
		
			if(!"".equals(getQ_buySource())) 
				hql += " and buySource = " + Common.sqlChar(getQ_buySource());
			
			if(!"".equals(getQ_unExpectedClassify())) 
				hql += " and hfr0001Db.unExpectedClassify = " + Common.sqlChar(getQ_unExpectedClassify());
			
			if(!"".equals(getQ_unExpectedReason())) 
				hql += " and hfr0001Db.unExpectedReason = " + Common.sqlChar(getQ_unExpectedReason());
			
			if(!"".equals(getQ_evidentiary())) 
				hql += " and hfr0001Db.evidentiary = " + Common.sqlChar(getQ_evidentiary());
			
			if(!"".equals(getQ_thiSeverity())) 
				hql += " and hfr0001Db.recentlySeverity = " + Common.sqlChar(getQ_thiSeverity());
			
			if(!"".equals(getQ_administrativeLevel())) 
				hql += " and hfr0001Db.administrativeLevel = " + Common.sqlChar(getQ_administrativeLevel());
			
			if(!"".equals(getQ_status())) 
				hql += " and hfr0001Db.status = " + Common.sqlChar(getQ_status());	

		}
		
		System.out.println("[TCBW]-[COMPLE0901F]-[食品-doQueryAll] : " + hql);	
		this.processCurrentPageAttribute(ServiceGetter.getInstance().getTcbwService().loadCount(hql));
		if (getTotalRecord() > 0){
			if (getState().indexOf("query")<0) 
				ServiceGetter.getInstance().getCommonService().getHibernateTemplate().clear();
			java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(hql + " order by id desc", this.getRecordStart(), this.getPageSize());
			if (objList != null && objList.size() > 0) {
				java.util.Map<String, String> statusMap = TCBWCommon.getCommonCodeMap("FCS");
				java.util.Map<String, String> ResMap = TCBWCommon.getCommonCodeMap("HFRURCR");
				for(Object dtlObj : objList) {				
					Hfr0002Db dtl = (Hfr0002Db)dtlObj;
					String[] rowArray = new String[8];
					rowArray[0] = Common.get(dtl.getHfr0001Db().getId());
					rowArray[1] = Common.get(dtl.getHfr0001Db().getApplNo());
					rowArray[2] = Common.formatYYYMMDD(Common.get(dtl.getHfr0001Db().getNotifierRepDate()),4);
					rowArray[3] = Common.get(dtl.getChProduct());
					rowArray[4] = "Y".equals(dtl.getHfr0001Db().getUnHealthIsYes())?"是":"N".equals(dtl.getHfr0001Db().getUnHealthIsYes())?"否":"";//unHealthBrief
					rowArray[5] = ResMap.get(dtl.getHfr0001Db().getUnReactionResults());
					if(dtl.getHfr0001Db().getHfr0007Dbs()!=null && dtl.getHfr0001Db().getHfr0007Dbs().size()>0){
						for(Object dObj : dtl.getHfr0001Db().getHfr0007Dbs()){
							Hfr0007Db d = (Hfr0007Db)dObj;
							rowArray[6] = "1".equals(d.getReEvaluateResult())?"結案":"2".equals(d.getReEvaluateResult())?"重新評估":"";
						}
					}else{
						rowArray[6] = "";
					}
					rowArray[7] = statusMap.get(dtl.getHfr0001Db().getStatus());
					arrList.add(rowArray);
				}
				objList.clear();			
			}		
		}
		return arrList;
	}

	@Override
	public void doCreate() throws Exception{}

	@Override
	public void doUpdate() throws Exception{
		Hfr0001Db obj = (Hfr0001Db)View.getObject(" from Hfr0001Db where id = " + Common.getLong(getId()));
		if(obj != null){			
			
			String yyymmdd = Datetime.getYYYMMDD();
			String hhmmss = Datetime.getHHMMSS();		
			
			obj.setOccurDate(getOccurDate());
			obj.setNotifierRevDate(getNotifierRevDate());
			obj.setNotifierName(getNotifierName());
			obj.setNotifierTelExt(getNotifierTelExt());
			obj.setNotifierTelArea(getNotifierTelArea());
			obj.setNotifierTel(getNotifierTel());
			obj.setNotifierIdNum(getNotifierIdNum());
			obj.setNotifierDept(getNotifierDept());
			obj.setAddress(getAddress());
			obj.setNotifierEamil(getNotifierEamil());
			obj.setNotifierType(getNotifierType());
			obj.setNotifierTitle(getNotifierTitle());
			
			obj.setEatersId(getEatersId());
			if("Y".equals(getIsSameNotifier())){
				obj.setEatersName(getNotifierName());
				obj.setEatersIdNum(getNotifierIdNum());
				obj.setEatersTel(getNotifierTel());
				obj.setEatersTelExt(getNotifierTelExt());
				obj.setEatersTelArea(getNotifierTelArea());
			}else{
				obj.setEatersName(getEatersName());
				obj.setEatersIdNum(getEatersIdNum());
				obj.setEatersTel(getEatersTel());
				obj.setEatersTelExt(getNotifierTelExt());
				obj.setEatersTelArea(getNotifierTelArea());
			}
			obj.setEatersSex(getEatersSex());
			obj.setEatersBirthYear(getEatersBirthYear());
			obj.setEatersHight(getEatersHight());
			obj.setEatersWeight(getEatersWeight());
			obj.setAddress(getAddress());
			
			obj.setDrugAllergies(getDrugAllergies());
			obj.setDrugOther(getDrugOther());
			obj.setFoodAllergies(getFoodAllergies());
			obj.setFoodOther(getFoodOther());
			obj.setIsReactionResult(getIsReactionResult());
			obj.setInformedSources(getInformedSources());
			if(getDiseaseHistory()!=null && getDiseaseHistory().length>0){
				StringBuffer sb = new StringBuffer();
				for(String rid : getDiseaseHistory()){
					if(sb.toString().length() > 0){
						sb.append(",");
					}
					sb.append(rid);
				}
				obj.setDiseaseHistory(sb.toString());
			}else{
				obj.setDiseaseHistory("");
			}
			obj.setDiseaseOther(getDiseaseOther());
			
			if(getLifeHistory()!=null && getLifeHistory().length>0){
				StringBuffer sb = new StringBuffer();
				for(String rid : getLifeHistory()){
					if(sb.toString().length() > 0){
						sb.append(",");
					}
					sb.append(rid);
				}
				obj.setLifeHistory(sb.toString());
			}else{
				obj.setLifeHistory("");
			}
			obj.setLifeOther(getLifeOther());
			
			obj.setUnHealthIsYes(getUnHealthIsYes());
			obj.setUnHealthBrief(getUnHealthBrief());
			obj.setUnReactionResults(getUnReactionResults());
			obj.setDeathDate(getDeathDate());
			obj.setDeathResult(getDeathResult());
			obj.setEndangerLife(getEndangerLife());
			obj.setOtherNonResponse(getOtherNonResponse());
			if(getOtherrElevantInformation()!=null && getOtherrElevantInformation().length>0){
				StringBuffer s = new StringBuffer();
				for(String codeId : getOtherrElevantInformation()){
					if(s.toString().length()>0)	s.append(",");
					s.append(codeId);
				}
				obj.setOtherrElevantInformation(s.toString());
			}else{
				obj.setOtherrElevantInformation("");
			}
			obj.setAllergicDescription(getAllergicDescription());
			obj.setOthersDescribeHistory(getOthersDescribeHistory());
			obj.setModifier(getUserID());
			obj.setModifyDate(yyymmdd);
			obj.setModifyTime(hhmmss);
			
			// 通報事件描述
			java.util.Set hfr0003Dbs = obj.getHfr0003Dbs();
			if(hfr0003Dbs == null){
				hfr0003Dbs = new ListOrderedSet();
			}
			java.util.List<Hfr0003Db> d3List = new java.util.ArrayList<Hfr0003Db>();
			try{
				ServiceGetter.getInstance().getTcbwService().getHfrinDao().updateHfr0003Db(obj, hfr0003Dbs, d3List, getHttpRequest(), getDiscriptRow(), discriptFieldName, getUserID(), yyymmdd, hhmmss);
			}catch(Exception e){
				e.printStackTrace();
			}
			if(d3List.size() > 0){
				for(Hfr0003Db dtl : d3List){
					dtl.getHfr0001Db().getHfr0003Dbs().remove(dtl);
					dtl.setHfr0001Db(null);
				}
				ServiceGetter.getInstance().getTcbwService().getHfrinDao().deleteBatch(d3List);
				d3List.clear();
			}
			
			// 醫師診斷及相關檢查及檢驗數據
			java.util.Set hfr0004Dbs = obj.getHfr0004Dbs();
			if(hfr0004Dbs == null){
				hfr0004Dbs = new ListOrderedSet();
			}
			java.util.List<Hfr0004Db> d4List = new java.util.ArrayList<Hfr0004Db>();
			try{
				ServiceGetter.getInstance().getTcbwService().getHfrinDao().updateHfr0004Db(obj, hfr0004Dbs, d4List, getHttpRequest(), getDoctorRow(), doctorFieldName, getUserID(), yyymmdd, hhmmss);
			}catch(Exception e){
				e.printStackTrace();
			}
			if(d4List.size() > 0){
				for(Hfr0004Db dtl : d4List){
					dtl.getHfr0001Db().getHfr0004Dbs().remove(dtl);
					dtl.setHfr0001Db(null);
				}
				ServiceGetter.getInstance().getTcbwService().getHfrinDao().deleteBatch(d4List);
				d4List.clear();
			}
			
			
			// 食藥品資料
			obj.setAgainEatingHealthFood(getAgainEatingHealthFood());
			obj.setHealthFoodName(getHealthFoodName());
			obj.setAgainOtherNonResponse(getAgainOtherNonResponse());
			obj.setStopEatingReaction(getStopEatingReaction());
			obj.setAgainEatingReaction(getAgainEatingReaction());
			
			java.util.Set hfr0002Dbs = obj.getHfr0002Dbs();
			if(hfr0002Dbs == null){
				hfr0002Dbs = new ListOrderedSet();
			}
			java.util.List<Hfr0002Db> d2List = new java.util.ArrayList<Hfr0002Db>();
			
			try{
				ServiceGetter.getInstance().getTcbwService().getHfrinDao().updateHfr0002Db(obj, hfr0002Dbs, d2List, "G", getHttpRequest(), getGFoodRow(), arrGFoodFieldName, getUserID(), yyymmdd, hhmmss);
			}catch(Exception e){
				e.printStackTrace();
			}
			try{
				ServiceGetter.getInstance().getTcbwService().getHfrinDao().updateHfr0002Db(obj, hfr0002Dbs, d2List, "N", getHttpRequest(), getNFoodRow(), arrNFoodFieldName, getUserID(), yyymmdd, hhmmss);
			}catch(Exception e){
				e.printStackTrace();
			}
			try{
				ServiceGetter.getInstance().getTcbwService().getHfrinDao().updateDrugHfr0002Db(obj, hfr0002Dbs, d2List, "D", getHttpRequest(), getDrRow(), arrDrugFieldName, getUserID(), yyymmdd, hhmmss);
			}catch(Exception e){
				e.printStackTrace();
			}
			try{
				ServiceGetter.getInstance().getTcbwService().getHfrinDao().updateDrugHfr0002Db(obj, hfr0002Dbs, d2List, "O", getHttpRequest(), getOdrRow(), arrODrugFieldName, getUserID(), yyymmdd, hhmmss);
			}catch(Exception e){
				e.printStackTrace();
			}
			
			if(d2List.size() > 0){
				for(Hfr0002Db dtl : d2List){
					dtl.getHfr0001Db().getHfr0002Dbs().remove(dtl);
					dtl.setHfr0001Db(null);
				}
				ServiceGetter.getInstance().getTcbwService().getHfrinDao().deleteBatch(d2List);
				d2List.clear();
			}
			// 檔案處理- FOR 調查頁籤-病歷資訊、檢驗資料
			obj.setMedicalPostingDate(getMedicalPostingDate());
			obj.setMedicalCompleteDate(getMedicalCompleteDate());
			obj.setInspectPostingDate(getInspectPostingDate());
			obj.setInspectCompleteDate(getInspectCompleteDate());
			ServiceGetter.getInstance().getTcbwService().getHfrexDao().updateCon0001Db(getHttpRequest(), obj.getId(), "HFR0001DB", "HFRFM", "HFR", getHFRFMFileRow(), arrHFRFMFileFieldName, getUserID(), yyymmdd, hhmmss);
			ServiceGetter.getInstance().getTcbwService().getHfrexDao().updateCon0001Db(getHttpRequest(), obj.getId(), "HFR0001DB", "HFRFI", "HFR", getHFRFIFileRow(), arrHFRFIFileFieldName, getUserID(), yyymmdd, hhmmss);
			try{
				ServiceGetter.getInstance().getTcbwService().getHfrexDao().updateCon0001Db(getHttpRequest(), obj.getId(), "HFR0001DB", "H", "HFR", getFoodFileRow(), arrHFileFieldName, getUserID(), yyymmdd, hhmmss);
			}catch(Exception e){
				e.printStackTrace();
			}
			
			
			obj.setAssignMan(getUserID());			
			obj.setAdditionalDate(yyymmdd);				
			obj.setAdditionalFinshDate(yyymmdd);
			
			obj.setHfr0002Dbs(hfr0002Dbs);
			obj.setHfr0003Dbs(hfr0003Dbs);
			obj.setHfr0004Dbs(hfr0004Dbs);
			
			//初步評估
			if("Y".equals(Common.get(getHfr05Update()))){
				Hfr0005Db hfr05 = null;
				if(!"".equals(Common.get(getHfr0005Id()))){
					hfr05 = (Hfr0005Db)View.getObject(" from Hfr0005Db where id = " + Common.get(getHfr0005Id()));
				}else{
					hfr05 = new Hfr0005Db();
					hfr05.setHfr0001Db(obj);
					hfr05.setCreateDate(getUserID());
					hfr05.setCreateDate(yyymmdd);
					hfr05.setCreateTime(hhmmss);
				}
				hfr05.setPreCompleteDate(getPreCompleteDate());
				hfr05.setAssessmentSendDate(getAssessmentSendDate());
				hfr05.setAssessmentCompleteDate(getAssessmentCompleteDate());
				hfr05.setCaseType(getCaseType());
				hfr05.setUnExpectedClassify(getUnExpectedClassify());
				if(getUnExpectedReason()!=null && getUnExpectedReason().length>0){
					StringBuffer sb = new StringBuffer();
					for(String rid : getUnExpectedReason()){
						if(sb.toString().length() > 0){
							sb.append(",");
						}
						sb.append(rid);
					}
					hfr05.setUnExpectedReason(sb.toString());
				}else{
					hfr05.setUnExpectedReason("");
				}
				hfr05.setEvidentiary(getEvidentiary());
				hfr05.setPreSeverity(getPreSeverity());
				hfr05.setAdministrativeLevel(getAdministrativeLevel());
				hfr05.setPreOpinion(getPreOpinion());
				hfr05.setPreRemark(getPreRemark());
				hfr05.setPreExplain(getPreExplain());
				hfr05.setUnPreExplain(getUnPreExplain());
				hfr05.setModifier(getUserID());
				hfr05.setModifyDate(yyymmdd);
				hfr05.setModifyTime(hhmmss);
				
				try{
					ServiceGetter.getInstance().getTcbwService().getHfrexDao().updateCon0001Db(getHttpRequest(), hfr05.getId(), "HFR0005DB", "HFRFO", "HFR", getHFRFOFileRow(), arrHFRFOFileFieldName, getUserID(), yyymmdd, hhmmss);
				}catch(Exception e){
					e.printStackTrace();
				}
				
				ServiceGetter.getInstance().getTcbwService().getDrginDao().saveOrUpdate(hfr05);	
												
				obj.setUnExpectedClassify(hfr05.getUnExpectedClassify());
				obj.setUnExpectedReason(hfr05.getUnExpectedReason());
				obj.setEvidentiary(hfr05.getEvidentiary());
				obj.setRecentlySeverity(hfr05.getPreSeverity());
				obj.setAdministrativeLevel(hfr05.getAdministrativeLevel());
			}
			
			//預評作業
			if("Y".equals(Common.get(getHfr06Update()))){
				Hfr0006Db hfr06 = null;
				if(!"".equals(Common.get(getHfr0006Id()))){
					hfr06 = (Hfr0006Db)View.getObject(" from Hfr0006Db where id = " + Common.get(getHfr0006Id()));
				}else{
					hfr06 = new Hfr0006Db();
					hfr06.setHfr0001Db(obj);
					hfr06.setCreateDate(getUserID());
					hfr06.setCreateDate(yyymmdd);
					hfr06.setCreateTime(hhmmss);
				}
				hfr06.setEvaluateCommittee(Common.getLong(getEvaluateCommittee()));
				hfr06.setUnExpectedClassify(getSecUnExpectedClassify());
				if(getSecUnExpectedReason()!=null && getSecUnExpectedReason().length>0){
					StringBuffer sb = new StringBuffer();
					for(String rid : getSecUnExpectedReason()){
						if(sb.toString().length() > 0){
							sb.append(",");
						}
						sb.append(rid);
					}
					hfr06.setUnExpectedReason(sb.toString());
				}else{
					hfr06.setUnExpectedReason("");
				}
				hfr06.setEvidentiary(getSecEvidentiary());
				hfr06.setSecSeverity(getSecSeverity());
				hfr06.setAdministrativeLevel(getSecAdministrativeLevel());
				hfr06.setAssessments(getAssessments());
				hfr06.setEvaluateResult(getEvaluateResult());
				hfr06.setSecExplain(getSecExplain());
				hfr06.setUnSecExplain(getUnSecExplain());
				hfr06.setModifier(getUserID());
				hfr06.setModifyDate(yyymmdd);
				hfr06.setModifyTime(hhmmss);
				
				try{
					ServiceGetter.getInstance().getTcbwService().getHfrexDao().updateCon0001Db(getHttpRequest(), hfr06.getId(), "HFR0006DB", "HFRAS", "HFR", getHFRASFileRow(), arrHFRASFieldName, getUserID(), yyymmdd, hhmmss);
				}catch(Exception e){
					e.printStackTrace();
				}			
				
				ServiceGetter.getInstance().getTcbwService().getDrginDao().saveOrUpdate(hfr06);	
				
				obj.setUnExpectedClassify(hfr06.getUnExpectedClassify());
				obj.setUnExpectedReason(hfr06.getUnExpectedReason());
				obj.setEvidentiary(hfr06.getEvidentiary());
				obj.setRecentlySeverity(hfr06.getSecSeverity());
				obj.setAdministrativeLevel(hfr06.getAdministrativeLevel());
			}
			System.out.println("xxx=="+getHfr07Update());
			//複評作業
			if("Y".equals(Common.get(getHfr07Update()))){
				Hfr0007Db hfr07 = null;
				if(!"".equals(Common.get(getHfr0007Id()))){
					hfr07 = (Hfr0007Db)View.getObject(" from Hfr0007Db where id = " + Common.get(getHfr0007Id()));
				}else{
					hfr07 = new Hfr0007Db();
					hfr07.setHfr0001Db(obj);
					hfr07.setCreateDate(getUserID());
					hfr07.setCreateDate(yyymmdd);
					hfr07.setCreateTime(hhmmss);
				}
				System.out.println("xxx=="+hfr07);
				hfr07.setCommitteeMeeting(getCommitteeMeeting());
				hfr07.setCommitteeDate(getCommitteeDate());
				hfr07.setRecordDate(getRecordDate());
				hfr07.setCaseBackDate(getCaseBackDate());
				hfr07.setUnExpectedClassify(getThiUnExpectedClassify());
				if(getThiUnExpectedReason()!=null && getThiUnExpectedReason().length>0){
					StringBuffer sb = new StringBuffer();
					for(String rid : getThiUnExpectedReason()){
						if(sb.toString().length() > 0){
							sb.append(",");
						}
						sb.append(rid);
					}
					hfr07.setUnExpectedReason(sb.toString());
				}else{
					hfr07.setUnExpectedReason("");
				}				
				hfr07.setEvidentiary(getThiEvidentiary());
				hfr07.setThiSeverity(getThiSeverity());
				hfr07.setAdministrativeLevel(getThiAdministrativeLevel());
				hfr07.setReEvaluateResult(getReEvaluateResult());
				hfr07.setThiExplain(getThiExplain());
				hfr07.setUnThiExplain(getUnThiExplain());				
				hfr07.setIsClosed("Y");								
				
				try{
					ServiceGetter.getInstance().getTcbwService().getHfrexDao().updateCon0001Db(getHttpRequest(), hfr07.getId(), "hfr07", "HFRRE", "HFR", getHFRREFileRow(), arrHFRREFieldName, getUserID(), yyymmdd, hhmmss);
				}catch(Exception e){
					e.printStackTrace();
				}				
				
				ServiceGetter.getInstance().getTcbwService().getDrginDao().saveOrUpdate(hfr07);
				
				obj.setUnExpectedClassify(hfr07.getUnExpectedClassify());
				obj.setUnExpectedReason(hfr07.getUnExpectedReason());
				obj.setEvidentiary(hfr07.getEvidentiary());
				obj.setRecentlySeverity(hfr07.getThiSeverity());
				obj.setAdministrativeLevel(hfr07.getAdministrativeLevel());				
			}
			
			ServiceGetter.getInstance().getTcbwService().getHfrinDao().update(obj);			
			setId(Common.get(obj.getId()));
		}else{
			setErrorMsg("查無該案件，請重新操作 !");
			setIsNeedBackQuery("Y");
		}
	}

	public void doDeleteHfr0006Db() throws Exception{
		if(!"".equals(Common.get(getHfr0006Id()))){
			Hfr0006Db hfr06 = (Hfr0006Db)View.getObject(" from Hfr0006Db where id = " + Common.get(getHfr0006Id()));
			if(hfr06 != null){
				ServiceGetter.getInstance().getTcbwService().getHfrinDao().delete(hfr06);
			}
		}		
	}
	
	@Override
	public void doDelete() throws Exception{}
	
	public String getId() {return checkGet(id);}
	public void setId(String id) {this.id = checkSet(id);}	
	public String getTabId() {return checkGet(tabId);}
	public void setTabId(String tabId) {this.tabId = checkSet(tabId);}
	public String getToUpdate() {return checkGet(toUpdate);}
	public void setToUpdate(String toUpdate) {this.toUpdate = checkSet(toUpdate);}
	public String getHfr05Update() {return hfr05Update;}
	public void setHfr05Update(String hfr05Update) {this.hfr05Update = hfr05Update;	}
	public String getHfr06Update() {return hfr06Update;}
	public void setHfr06Update(String hfr06Update) {this.hfr06Update = hfr06Update;	}
	public String getHfr07Update() {return hfr07Update;}
	public void setHfr07Update(String hfr07Update) {this.hfr07Update = hfr07Update;	}
	
	public String getQ_isQuery() {return checkGet(q_isQuery);}
	public void setQ_isQuery(String qIsQuery) {	q_isQuery = checkSet(qIsQuery);	}
	public String getQ_id() {return checkGet(q_id);	}
	public void setQ_id(String qId) {q_id = checkSet(qId);}
	public String getQ_applNoS() {return checkGet(q_applNoS);}
	public void setQ_applNoS(String qApplNoS) {q_applNoS = checkSet(qApplNoS);}
	public String getQ_applNoE() {return checkGet(q_applNoE);}
	public void setQ_applNoE(String qApplNoE) {q_applNoE = checkSet(qApplNoE);}
	public String getQ_occurDateS() {return checkGet(q_occurDateS);	}
	public void setQ_occurDateS(String qOccurDateS) {q_occurDateS = checkSet(qOccurDateS);	}
	public String getQ_occurDateE() {return checkGet(q_occurDateE);	}
	public void setQ_occurDateE(String qOccurDateE) {q_occurDateE = checkSet(qOccurDateE);	}
	public String getQ_notifierRevDateS() {	return checkGet(q_notifierRevDateS);	}
	public void setQ_notifierRevDateS(String qNotifierRevDateS) {	q_notifierRevDateS = checkSet(qNotifierRevDateS);	}
	public String getQ_notifierRevDateE() {	return checkGet(q_notifierRevDateE);	}
	public void setQ_notifierRevDateE(String qNotifierRevDateE) {q_notifierRevDateE = checkSet(qNotifierRevDateE);	}
	public String getQ_notifierRepDateS() {	return checkGet(q_notifierRepDateS);	}
	public void setQ_notifierRepDateS(String qNotifierRepDateS) {	q_notifierRepDateS = checkSet(qNotifierRepDateS);	}
	public String getQ_notifierRepDateE() {	return checkGet(q_notifierRepDateE);	}
	public void setQ_notifierRepDateE(String qNotifierRepDateE) {	q_notifierRepDateE = checkSet(qNotifierRepDateE);	}
	public String getQ_informedSources() {	return checkGet(q_informedSources);	}
	public void setQ_informedSources(String qInformedSources) {	q_informedSources = checkSet(qInformedSources);	}
	public String getQ_permitKey() {	return checkGet(q_permitKey);	}
	public void setQ_permitKey(String qPermitKey) {	q_permitKey = checkSet(qPermitKey);	}
	public String getQ_permitNo() {	return checkGet(q_permitNo);	}
	public void setQ_permitNo(String qPermitNo) {	q_permitNo = checkSet(qPermitNo);	}
	public String getQ_productName() {	return checkGet(q_productName);	}
	public void setQ_productName(String qProductName) {	q_productName = checkSet(qProductName);	}
	public String getQ_ingredient() {	return checkGet(q_ingredient);	}
	public void setQ_ingredient(String qIngredient) {	q_ingredient = checkSet(qIngredient);	}
	public String getQ_buySource() {	return checkGet(q_buySource);	}
	public void setQ_buySource(String qBuySource) {	q_buySource = checkSet(qBuySource);	}
	public String getQ_unExpectedClassify() {	return checkGet(q_unExpectedClassify);	}
	public void setQ_unExpectedClassify(String qUnExpectedClassify) {	q_unExpectedClassify = checkSet(qUnExpectedClassify);	}
	public String getQ_unExpectedReason() {	return checkGet(q_unExpectedReason);	}
	public void setQ_unExpectedReason(String qUnExpectedReason) {	q_unExpectedReason = checkSet(qUnExpectedReason);	}
	public String getQ_evidentiary() {	return checkGet(q_evidentiary);	}
	public void setQ_evidentiary(String qEvidentiary) {	q_evidentiary = checkSet(qEvidentiary);	}
	public String getQ_thiSeverity() {	return checkGet(q_thiSeverity);	}
	public void setQ_thiSeverity(String qThiSeverity) {	q_thiSeverity = checkSet(qThiSeverity);	}
	public String getQ_administrativeLevel() {	return checkGet(q_administrativeLevel);	}
	public void setQ_administrativeLevel(String qAdministrativeLevel) {	q_administrativeLevel = checkSet(qAdministrativeLevel);	}
	public String getQ_status() {	return checkGet(q_status);	}
	public void setQ_status(String qStatus) {	q_status = checkSet(qStatus);	}
	
}
