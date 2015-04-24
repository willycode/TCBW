package com.kangdainfo.tcbw.view.hfrin;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.tcbw.model.bo.Con0001Db;
import com.kangdainfo.tcbw.model.bo.Hfr0001Db;
import com.kangdainfo.tcbw.model.bo.Hfr0002Db;

public class HFRIN0601F extends HFRIN0501F {
	
	private String q_applNo;
	
	public String getQ_applNo() {		return checkGet(q_applNo);	}
	public void setQ_applNo(String q_applNo) {		this.q_applNo = checkSet(q_applNo);	}
	
	// HFR0005_DB
	private String hfr0005Id;
	private String preCompleteDate;
	private String assessmentSendDate;
	private String assessmentCompleteDate;
	private String caseType;
	private String unExpectedClassify;
	private String[] unExpectedReason;
	private String evidentiary;
	private String preSeverity;
	private String administrativeLevel;
	private String preOpinion;
	private String preRemark;
	private String preExplain;
	private String unPreExplain;
	
	public String getHfr0005Id() {		return checkGet(hfr0005Id);	}
	public void setHfr0005Id(String hfr0005Id) {		this.hfr0005Id = checkSet(hfr0005Id);	}
	public String getPreCompleteDate() {		return checkGet(preCompleteDate);	}
	public void setPreCompleteDate(String preCompleteDate) {		this.preCompleteDate = checkSet(preCompleteDate);	}
	public String getAssessmentSendDate() {		return checkGet(assessmentSendDate);	}
	public void setAssessmentSendDate(String assessmentSendDate) {		this.assessmentSendDate = checkSet(assessmentSendDate);	}
	public String getAssessmentCompleteDate() {		return checkGet(assessmentCompleteDate);	}
	public void setAssessmentCompleteDate(String assessmentCompleteDate) {		this.assessmentCompleteDate = checkSet(assessmentCompleteDate);	}
	public String getCaseType() {		return checkGet(caseType);	}
	public void setCaseType(String caseType) {		this.caseType = checkSet(caseType);	}
	public String getUnExpectedClassify() {		return checkGet(unExpectedClassify);	}
	public void setUnExpectedClassify(String unExpectedClassify) {		this.unExpectedClassify = checkSet(unExpectedClassify);	}
	public String[] getUnExpectedReason() {		return checkGet(unExpectedReason);	}
	public void setUnExpectedReason(String[] unExpectedReason) {	this.unExpectedReason = checkSet(unExpectedReason);	}
	public String getEvidentiary() {		return checkGet(evidentiary);	}
	public void setEvidentiary(String evidentiary) {		this.evidentiary = checkSet(evidentiary);	}
	public String getPreSeverity() {		return checkGet(preSeverity);	}
	public void setPreSeverity(String preSeverity) {		this.preSeverity = checkSet(preSeverity);	}
	public String getAdministrativeLevel() {		return checkGet(administrativeLevel);	}
	public void setAdministrativeLevel(String administrativeLevel) {		this.administrativeLevel = checkSet(administrativeLevel);	}
	public String getPreOpinion() {		return checkGet(preOpinion);	}
	public void setPreOpinion(String preOpinion) {		this.preOpinion = checkSet(preOpinion);	}
	public String getPreRemark() {		return checkGet(preRemark);	}
	public void setPreRemark(String preRemark) {		this.preRemark = checkSet(preRemark);	}
	public String getPreExplain() {		return checkGet(preExplain);	}
	public void setPreExplain(String preExplain) {		this.preExplain = checkSet(preExplain);	}
	public String getUnPreExplain() {		return checkGet(unPreExplain);	}
	public void setUnPreExplain(String unPreExplain) {		this.unPreExplain = checkSet(unPreExplain);	}

	private java.util.ArrayList hfr0005DbList;
	public java.util.ArrayList getHfr0005DbList() {		return hfr0005DbList;	}
	public void setHfr0005DbList(java.util.ArrayList hfr0005DbList) {		this.hfr0005DbList = hfr0005DbList;	}

	private String[] HFRFOFileRow;
	private String[] HFRFMFileRow;
	private String[] HFRFIFileRow;
	
	public String[] getHFRFOFileRow() {		return checkGet(HFRFOFileRow);	}
	public void setHFRFOFileRow(String[] hFRFOFileRow) {		HFRFOFileRow = checkSet(hFRFOFileRow);	}
	public String[] getHFRFMFileRow() {		return checkGet(HFRFMFileRow);	}
	public void setHFRFMFileRow(String[] hFRFMFileRow) {		HFRFMFileRow = checkSet(hFRFMFileRow);	}
	public String[] getHFRFIFileRow() {		return checkGet(HFRFIFileRow);	}
	public void setHFRFIFileRow(String[] hFRFIFileRow) {		HFRFIFileRow = checkSet(hFRFIFileRow);	}

	private String HFRFOFileRowSBuilder;
	private String HFRFMFileRowSBuilder;
	private String HFRFIFileRowSBuilder;
	
	public String getHFRFOFileRowSBuilder() {		return get(HFRFOFileRowSBuilder);	}
	public void setHFRFOFileRowSBuilder(String hFRFOFileRowSBuilder) {		HFRFOFileRowSBuilder = checkSet(hFRFOFileRowSBuilder);	}
	public String getHFRFMFileRowSBuilder() {		return get(HFRFMFileRowSBuilder);	}
	public void setHFRFMFileRowSBuilder(String hFRFMFileRowSBuilder) {		HFRFMFileRowSBuilder = checkSet(hFRFMFileRowSBuilder);	}
	public String getHFRFIFileRowSBuilder() {		return get(HFRFIFileRowSBuilder);	}
	public void setHFRFIFileRowSBuilder(String hFRFIFileRowSBuilder) {		HFRFIFileRowSBuilder = checkSet(hFRFIFileRowSBuilder);	}

	public final String[] arrHFRFOFileFieldName = {"HFRFOFileName", "HFRFOFileNameRoute", "HFRFOFileExplan"};
	public final String[] arrHFRFMFileFieldName = {"HFRFMFileName", "HFRFMFileNameRoute", "HFRFMFileExplan"};
	public final String[] arrHFRFIFileFieldName = {"HFRFIFileName", "HFRFIFileNameRoute", "HFRFIFileExplan"};
	
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
	    	}
	    }
		return "";
	}
	
	@Override
	public Object doQueryAll() throws Exception {
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		String hql = " select distinct a from Hfr0001Db a left join a.hfr0002Dbs b where a.status = '20' ";
		
		if(!"".equals(getQ_applNo()))
			hql += " and a.applNo = " + Common.sqlChar(getQ_applNo());
		if(!"".equals(getQ_notifierRepDateS()))
			hql += " and a.notifierRepDate >= " + Common.sqlChar(getQ_notifierRepDateS());
		if(!"".equals(getQ_notifierRepDateE()))
			hql += " and a.notifierRevDate <= " + Common.sqlChar(getQ_notifierRepDateE());
		if(!"".equals(getQ_permitKey()))
			hql += " and b.permitKey = " + Common.sqlChar(getQ_permitKey());
		if(!"".equals(getQ_permitNo()))
			hql += " and b.permitNo = " + Common.sqlChar(getQ_permitNo());
		if(!"".equals(getQ_product()))
			hql += " and b.chProduct like " + Common.sqlChar("%" + getQ_product() + "%");
		if(!"".equals(getQ_assignMan()))
			hql += " and a.assignMan = " + Common.sqlChar(getQ_assignMan());
		System.out.println("[TCBW]-[HFRIN0601F]-[QUERYALL] : " + hql + " order by a.id ");
		
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
	
	@Override
	public Object doQueryOne() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void doCreate() throws Exception {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void doUpdate() throws Exception {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void doDelete() throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
