package com.kangdainfo.tcbw.view.hfrin;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.tcbw.model.bo.Con0001Db;
import com.kangdainfo.tcbw.model.bo.Hfr0001Db;
import com.kangdainfo.tcbw.model.bo.Hfr0002Db;

public class HFRIN0701F extends HFRIN0601F {

	// HFR0006_DB
	private String hfr0006Id;
	private String evaluateDate;
	private String evaluateCommittee;
	private String evaluateCommitteeName;
	private String secUnExpectedClassify;
	private String[] secUnExpectedReason;
	private String secEvidentiary;
	private String secSeverity;
	private String secAdministrativeLevel;
	private String assessments;
	private String evaluateResult;
	private String secExplain;
	private String unSecExplain;
	
	public String getHfr0006Id() {		return checkGet(hfr0006Id);	}
	public void setHfr0006Id(String hfr0006Id) {		this.hfr0006Id = checkSet(hfr0006Id);	}
	public String getEvaluateDate() {		return checkGet(evaluateDate);	}
	public void setEvaluateDate(String evaluateDate) {		this.evaluateDate = checkSet(evaluateDate);	}
	public String getEvaluateCommittee() {		return checkGet(evaluateCommittee);	}
	public void setEvaluateCommittee(String evaluateCommittee) {		this.evaluateCommittee = checkSet(evaluateCommittee);	}
	public String getEvaluateCommitteeName() {		return checkGet(evaluateCommitteeName);	}
	public void setEvaluateCommitteeName(String evaluateCommitteeName) {		this.evaluateCommitteeName = checkSet(evaluateCommitteeName);	}
	public String getSecSeverity() {		return checkGet(secSeverity);	}
	public void setSecSeverity(String secSeverity) {		this.secSeverity = checkSet(secSeverity);	}
	public String getAssessments() {		return checkGet(assessments);	}
	public void setAssessments(String assessments) {		this.assessments = checkSet(assessments);	}
	public String getEvaluateResult() {		return checkGet(evaluateResult);	}
	public void setEvaluateResult(String evaluateResult) {		this.evaluateResult = checkSet(evaluateResult);	}
	public String getSecUnExpectedClassify() {		return checkGet(secUnExpectedClassify);	}
	public void setSecUnExpectedClassify(String secUnExpectedClassify) {		this.secUnExpectedClassify = checkSet(secUnExpectedClassify);	}
	public String getSecEvidentiary() {		return checkGet(secEvidentiary);	}
	public void setSecEvidentiary(String secEvidentiary) {		this.secEvidentiary = checkSet(secEvidentiary);	}
	public String getSecAdministrativeLevel() {		return checkGet(secAdministrativeLevel);	}
	public void setSecAdministrativeLevel(String secAdministrativeLevel) {		this.secAdministrativeLevel = checkSet(secAdministrativeLevel);	}
	public String getSecExplain() {		return checkGet(secExplain);	}
	public void setSecExplain(String secExplain) {		this.secExplain = checkSet(secExplain);	}
	public String[] getSecUnExpectedReason() {		return checkGet(secUnExpectedReason);	}
	public void setSecUnExpectedReason(String[] secUnExpectedReason) {		this.secUnExpectedReason = checkSet(secUnExpectedReason);	}
	public String getUnSecExplain() {		return checkGet(unSecExplain);	}
	public void setUnSecExplain(String unSecExplain) {		this.unSecExplain = checkSet(unSecExplain);	}

	private java.util.ArrayList hfr0006DbList;
	public java.util.ArrayList getHfr0006DbList() {		return hfr0006DbList;	}
	public void setHfr0006DbList(java.util.ArrayList hfr0006DbList) {		this.hfr0006DbList = hfr0006DbList;	}

	private String[] HFRASFileRow;
	public String[] getHFRASFileRow() {		return checkGet(HFRASFileRow);	}
	public void setHFRASFileRow(String[] hFRASFileRow) {		HFRASFileRow = checkSet(hFRASFileRow);	}

	private String HFRASFileRowSBuilder;
	public String getHFRASFileRowSBuilder() {		return get(HFRASFileRowSBuilder);	}
	public void setHFRASFileRowSBuilder(String hFRASFileRowSBuilder) {		HFRASFileRowSBuilder = checkSet(hFRASFileRowSBuilder);	}

	public final String[] arrHFRASFieldName = {"HFRASFileName", "HFRASFileNameRoute", "HFRASFileExplan"};
	
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
	    	}
	    }
		return "";
	}
	
	public Object doQueryAll() throws Exception {
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		String hql = " select distinct a from Hfr0001Db a left join a.hfr0002Dbs b " +
					 " where a.status = '30' ";
		
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
		System.out.println("[TCBW]-[HFRIN0701F]-[QUERYALL] : " + hql + " order by a.id ");
		
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
