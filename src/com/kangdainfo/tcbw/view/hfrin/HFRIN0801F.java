package com.kangdainfo.tcbw.view.hfrin;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.tcbw.model.bo.Con0001Db;
import com.kangdainfo.tcbw.model.bo.Hfr0001Db;
import com.kangdainfo.tcbw.model.bo.Hfr0002Db;

public class HFRIN0801F extends HFRIN0701F {
	
	private String hfr0007Id;
	private String committeeMeeting;
	private String committeeDate;
	private String recordDate;
	private String caseBackDate;
	private String thiUnExpectedClassify;
	private String[] thiUnExpectedReason;
	private String thiEvidentiary;
	private String thiSeverity;
	private String thiAdministrativeLevel;
	private String reEvaluateResult;
	private String thiExplain;
	private String unThiExplain;
	
	public String getHfr0007Id() {		return checkGet(hfr0007Id);	}
	public void setHfr0007Id(String hfr0007Id) {		this.hfr0007Id = checkSet(hfr0007Id);	}
	public String getCommitteeMeeting() {		return checkGet(committeeMeeting);	}
	public void setCommitteeMeeting(String committeeMeeting) {		this.committeeMeeting = checkSet(committeeMeeting);	}
	public String getCommitteeDate() {		return checkGet(committeeDate);	}
	public void setCommitteeDate(String committeeDate) {		this.committeeDate = checkSet(committeeDate);	}
	public String getRecordDate() {		return checkGet(recordDate);	}
	public void setRecordDate(String recordDate) {		this.recordDate = checkSet(recordDate);	}
	public String getCaseBackDate() {		return checkGet(caseBackDate);	}
	public void setCaseBackDate(String caseBackDate) {		this.caseBackDate = checkSet(caseBackDate);	}
	public String getThiSeverity() {		return checkGet(thiSeverity);	}	
	public void setThiSeverity(String thiSeverity) {		this.thiSeverity = checkSet(thiSeverity);	}
	public String getReEvaluateResult() {		return checkGet(reEvaluateResult);	}
	public void setReEvaluateResult(String reEvaluateResult) {		this.reEvaluateResult = checkSet(reEvaluateResult);	}
	public String getThiUnExpectedClassify() {		return checkGet(thiUnExpectedClassify);	}
	public void setThiUnExpectedClassify(String thiUnExpectedClassify) {		this.thiUnExpectedClassify = checkSet(thiUnExpectedClassify);	}
	public String getThiEvidentiary() {		return checkGet(thiEvidentiary);	}
	public void setThiEvidentiary(String thiEvidentiary) {		this.thiEvidentiary = checkSet(thiEvidentiary);	}
	public String getThiAdministrativeLevel() {		return checkGet(thiAdministrativeLevel);	}
	public void setThiAdministrativeLevel(String thiAdministrativeLevel) {		this.thiAdministrativeLevel = checkSet(thiAdministrativeLevel);	}
	public String getThiExplain() {		return checkGet(thiExplain);	}
	public void setThiExplain(String thiExplain) {		this.thiExplain = checkSet(thiExplain);	}
	public String[] getThiUnExpectedReason() {		return checkGet(thiUnExpectedReason);	}
	public void setThiUnExpectedReason(String[] thiUnExpectedReason) {		this.thiUnExpectedReason = checkSet(thiUnExpectedReason);	}
	public String getUnThiExplain() {		return checkGet(unThiExplain);	}
	public void setUnThiExplain(String unThiExplain) {		this.unThiExplain = checkSet(unThiExplain);	}

	private java.util.ArrayList hfr0007DbList;
	public java.util.ArrayList getHfr0007DbList() {		return hfr0007DbList;	}
	public void setHfr0007DbList(java.util.ArrayList hfr0007DbList) {		this.hfr0007DbList = hfr0007DbList;	}

	private String[] HFRREFileRow;
	public String[] getHFRREFileRow() {		return checkGet(HFRREFileRow);	}
	public void setHFRREFileRow(String[] hFRREFileRow) {		HFRREFileRow = checkSet(hFRREFileRow);	}

	private String HFRREFileRowSBuilder;
	public String getHFRREFileRowSBuilder() {		return get(HFRREFileRowSBuilder);	}
	public void setHFRREFileRowSBuilder(String hFRREFileRowSBuilder) {		HFRREFileRowSBuilder = checkSet(hFRREFileRowSBuilder);	}

	public final String[] arrHFRREFieldName = {"HFRREFileName", "HFRREFileNameRoute", "HFRREFileExplan"};
	
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
	    	if("food".equals(fileType)){
	    		this.setFoodFileSBuilder(sb.toString());
	    	}else if("HFRFO".equals(fileType)){
	    		this.setHFRFOFileRowSBuilder(sb.toString());
	    	}else if("HFRFM".equals(fileType)){
	    		this.setHFRFMFileRowSBuilder(sb.toString());
	    	}else if("HFRFI".equals(fileType)){
	    		this.setHFRFIFileRowSBuilder(sb.toString());
	    	}else if("HFRAS".equals(fileType)){
	    		this.setHFRASFileRowSBuilder(sb.toString());
	    	}else if("HFRRE".equals(fileType)){
	    		this.setHFRREFileRowSBuilder(sb.toString());
	    	}
	    }
		return "";
	}

	public Object doQueryAll() throws Exception {
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		String hql = " select distinct a from Hfr0001Db a " +
					 " left join a.hfr0002Dbs b where a.status = '40' ";
		
		if(!"".equals(getQ_applNo()))
			hql += " and a.applNo = " + Common.sqlChar(getQ_applNo());
		if(!"".equals(getQ_notifierRepDateS()))
			hql += " and a.notifierRepDate >= " + Common.sqlChar(getQ_notifierRepDateS());
		if(!"".equals(getQ_notifierRepDateE()))
			hql += " and a.notifierRepDate <= " + Common.sqlChar(getQ_notifierRepDateE());
		if(!"".equals(getQ_permitKey()))
			hql += " and b.permitKey = " + Common.sqlChar(getQ_permitKey());
		if(!"".equals(getQ_permitNo()))
			hql += " and b.permitNo = " + Common.sqlChar(getQ_permitNo());
		if(!"".equals(getQ_product()))
			hql += " and b.chProduct like " + Common.sqlChar("%" + getQ_product() + "%");
		if(!"".equals(getQ_assignMan()))
			hql += " and a.assignMan = " + Common.sqlChar(getQ_assignMan());
		System.out.println("[TCBW]-[HFRIN0801F]-[QUERYALL] : " + hql + " order by a.id ");
		
		// 103.01.27 - 不分頁修正
		java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(hql);
		if(objList!=null && objList.size()>0){
			java.util.Map<String, String> FCSMap = ServiceGetter.getInstance().getCommonService().getCommonCodeDao().getCommonCodeNameMap("FCS", null);
			java.util.Map<String, String> HFRPKIDMap = ServiceGetter.getInstance().getCommonService().getCommonCodeDao().getCommonCodeNameMap("HFRPKID", null);
			java.util.Map<String, String> hfrUserMap = new java.util.HashMap<String, String>();
			java.util.List userList = ServiceGetter.getInstance().getTcbwService().load("select userId, userName from CommonUser where inORout = 'in'");
			if(userList!=null && userList.size()>0){
				for(Object dtlObj : userList){
					Object[] dtl = (Object[])dtlObj;
					hfrUserMap.put(Common.get(dtl[0]), Common.get(dtl[1]));
				}
				userList.clear();
			}
			for(Object dtlObj : objList){
				Hfr0001Db dtl = (Hfr0001Db)dtlObj;
				String[] rowArray = new String[8];
				rowArray[0] = Common.get(dtl.getId());
				rowArray[1] = Common.get(dtl.getApplNo());
				rowArray[2] = Common.get(dtl.getNotifierRepDate());
				
				rowArray[3] = "";
				rowArray[4] = "";					
				if(dtl.getHfr0002Dbs()!=null && dtl.getHfr0002Dbs().size()>0){
					boolean flag = true;
					for(Object dObj : dtl.getHfr0002Dbs()){
						if(flag){
							Hfr0002Db d = (Hfr0002Db)dObj;
							rowArray[3] = ( Common.get(HFRPKIDMap.get(Common.get(d.getPermitKey()))).equals("")?Common.get(d.getPermitKey()):Common.get(HFRPKIDMap.get(Common.get(d.getPermitKey()))) ) + Common.get(d.getPermitNo());
							rowArray[4] = Common.get(d.getChProduct());
				
							flag = false;
						}else{
							break;
						}
					}
				}
				rowArray[5] = Common.get(FCSMap.get(Common.get(dtl.getStatus()))).equals("")?Common.get(dtl.getStatus()):Common.get(FCSMap.get(Common.get(dtl.getStatus())));
				rowArray[6] = Common.get(hfrUserMap.get(Common.get(dtl.getAssignMan()))).equals("")?Common.get(dtl.getAssignMan()):Common.get(hfrUserMap.get(Common.get(dtl.getAssignMan())));
				rowArray[7] = Common.get(dtl.getHfrType());
				arrList.add(rowArray);
			}
			
			objList.clear();
			FCSMap.clear();
			HFRPKIDMap.clear();
			hfrUserMap.clear();
		}
		return arrList;
	}
}
