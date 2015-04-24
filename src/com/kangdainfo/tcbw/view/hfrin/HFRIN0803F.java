package com.kangdainfo.tcbw.view.hfrin;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Con0001Db;
import com.kangdainfo.tcbw.model.bo.Hfr0007Db;

public class HFRIN0803F extends SuperBean {
	
	private String id;
	private String committeeMeeting;
	private String committeeDate;
	private String recordDate;
	private String caseBackDate;
	private String unExpectedClassify;
	private String[] unExpectedReason;
	private String evidentiary;
	private String thiSeverity;
	private String administrativeLevel;
	private String reEvaluateResult;
	private String thiExplain;
	private String unThiExplain;
	
	public String getId() {		return checkGet(id);	}
	public void setId(String id) {		this.id = checkSet(id);	}
	public String getCommitteeMeeting() {		return checkGet(committeeMeeting);	}
	public void setCommitteeMeeting(String committeeMeeting) {		this.committeeMeeting = checkSet(committeeMeeting);	}
	public String getCommitteeDate() {		return checkGet(committeeDate);	}
	public void setCommitteeDate(String committeeDate) {		this.committeeDate = checkSet(committeeDate);	}
	public String getRecordDate() {		return checkGet(recordDate);	}
	public void setRecordDate(String recordDate) {		this.recordDate = checkSet(recordDate);	}
	public String getCaseBackDate() {		return checkGet(caseBackDate);	}
	public void setCaseBackDate(String caseBackDate) {		this.caseBackDate = checkSet(caseBackDate);	}
	public String getUnExpectedClassify() {		return checkGet(unExpectedClassify);	}
	public void setUnExpectedClassify(String unExpectedClassify) {		this.unExpectedClassify = checkSet(unExpectedClassify);	}
	public String getThiSeverity() {		return checkGet(thiSeverity);	}	
	public void setThiSeverity(String thiSeverity) {		this.thiSeverity = checkSet(thiSeverity);	}
	public String getReEvaluateResult() {		return checkGet(reEvaluateResult);	}
	public void setReEvaluateResult(String reEvaluateResult) {		this.reEvaluateResult = checkSet(reEvaluateResult);	}
	public String getEvidentiary() {		return checkGet(evidentiary);	}
	public void setEvidentiary(String evidentiary) {		this.evidentiary = checkSet(evidentiary);	}
	public String getAdministrativeLevel() {		return checkGet(administrativeLevel);	}
	public void setAdministrativeLevel(String administrativeLevel) {		this.administrativeLevel = checkSet(administrativeLevel);	}
	public String getThiExplain() {		return checkGet(thiExplain);	}
	public void setThiExplain(String thiExplain) {		this.thiExplain = checkSet(thiExplain);	}
	public String[] getUnExpectedReason() {		return checkGet(unExpectedReason);	}
	public void setUnExpectedReason(String[] unExpectedReason) {		this.unExpectedReason = checkSet(unExpectedReason);	}
	public String getUnThiExplain() {		return checkGet(unThiExplain);	}
	public void setUnThiExplain(String unThiExplain) {		this.unThiExplain = checkSet(unThiExplain);	}

	private String HFRREFileRowSBuilder;
	public String getHFRREFileRowSBuilder() {		return get(HFRREFileRowSBuilder);	}
	public void setHFRREFileRowSBuilder(String hFRREFileRowSBuilder) {		HFRREFileRowSBuilder = checkSet(hFRREFileRowSBuilder);	}
	
	public void genFileRowItemSet(java.util.List<Con0001Db> dtlList, String fileType) throws Exception{
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
	    	this.setHFRREFileRowSBuilder(sb.toString());
	    }
	}

	@Override
	public Object doQueryOne() throws Exception {
		HFRIN0803F obj = this;
		Hfr0007Db c = (Hfr0007Db)View.getObject(" from Hfr0007Db where id = " + Common.getLong(getId()));
		if(c != null){
			obj.setId(Common.get(c.getId()));
			obj.setCommitteeMeeting(c.getCommitteeMeeting());
			obj.setCommitteeDate(c.getCommitteeDate());
			obj.setRecordDate(c.getRecordDate());
			obj.setCaseBackDate(c.getCaseBackDate());
			obj.setUnExpectedClassify(c.getUnExpectedClassify());
			if(!"".equals(Common.get(c.getUnExpectedReason()))){
				String[] t = Common.get(c.getUnExpectedReason()).split(",");
				obj.setUnExpectedReason(t);
			}else{
				obj.setUnExpectedReason(null);
			}
			
			obj.setEvidentiary(c.getEvidentiary());
			obj.setThiSeverity(c.getThiSeverity());
			obj.setAdministrativeLevel(c.getAdministrativeLevel());
			obj.setReEvaluateResult(c.getReEvaluateResult());
			obj.setThiExplain(c.getThiExplain());
			obj.setUnThiExplain(c.getUnThiExplain());
			
			java.util.List<Con0001Db> fileList = ServiceGetter.getInstance().getTcbwService().load(" from Con0001Db where fileKind = 'HFRRE' and dbName = 'HFR0007DB' and upLoadId = " + Common.getLong(c.getId()));
			genFileRowItemSet(fileList, "HFRRE");
			fileList.clear();
		}
		return obj;
	}

	@Override
	public Object doQueryAll() throws Exception {
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
