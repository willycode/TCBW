package com.kangdainfo.tcbw.view.hfrin;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Con0001Db;
import com.kangdainfo.tcbw.model.bo.Hfr0005Db;

public class HFRIN0604F extends SuperBean{
	
	private String id;
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
	
	public String getId() {		return checkGet(id);	}
	public void setId(String id) {		this.id = checkSet(id);	}
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
	public String[] getUnExpectedReason() {		return checkGet(unExpectedReason);	}
	public void setUnExpectedReason(String[] unExpectedReason) {		this.unExpectedReason = checkSet(unExpectedReason);	}
	public String getUnPreExplain() {		return checkGet(unPreExplain);	}
	public void setUnPreExplain(String unPreExplain) {		this.unPreExplain = checkSet(unPreExplain);	}

	private String HFRFOFileRowSBuilder;
	public String getHFRFOFileRowSBuilder() {		return get(HFRFOFileRowSBuilder);	}
	public void setHFRFOFileRowSBuilder(String hFRFOFileRowSBuilder) {		HFRFOFileRowSBuilder = checkSet(hFRFOFileRowSBuilder);	}
	
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
	    	this.setHFRFOFileRowSBuilder(sb.toString());
	    }
	}

	@Override
	public Object doQueryOne() throws Exception {
		HFRIN0604F obj = this;
		Hfr0005Db c = (Hfr0005Db)View.getObject(" from Hfr0005Db where id = " + Common.getLong(getId()));
		if(c != null){
			obj.setId(Common.get(c.getId()));
			obj.setPreCompleteDate(c.getPreCompleteDate());
			obj.setAssessmentSendDate(c.getAssessmentSendDate());
			obj.setAssessmentCompleteDate(c.getAssessmentCompleteDate());
			obj.setCaseType(c.getCaseType());
			obj.setUnExpectedClassify(c.getUnExpectedClassify());
			if(!"".equals(Common.get(c.getUnExpectedReason()))){
				String[] t = Common.get(c.getUnExpectedReason()).split(",");
				obj.setUnExpectedReason(t);
			}else{
				obj.setUnExpectedReason(null);
			}
			obj.setEvidentiary(c.getEvidentiary());
			obj.setPreSeverity(c.getPreSeverity());
			obj.setAdministrativeLevel(c.getAdministrativeLevel());
			obj.setPreOpinion(c.getPreOpinion());
			obj.setPreRemark(c.getPreRemark());
			obj.setPreExplain(c.getPreExplain());
			obj.setUnPreExplain(c.getUnPreExplain());
			
			java.util.List<Con0001Db> fileList = ServiceGetter.getInstance().getTcbwService().load(" from Con0001Db where fileKind = 'HFRFO' and dbName = 'HFR0005DB' and upLoadId = " + Common.getLong(c.getId()));
			genFileRowItemSet(fileList, "HFRFO");
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
