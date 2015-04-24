package com.kangdainfo.tcbw.view.hfrin;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Con0001Db;
import com.kangdainfo.tcbw.model.bo.Hfr0006Db;

public class HFRIN0703F extends SuperBean {
	
	private String id;
	private String evaluateDate;
	private String evaluateCommittee;
	private String unExpectedClassify;
	private String[] unExpectedReason;
	private String evidentiary;
	private String secSeverity;
	private String administrativeLevel;
	private String assessments;
	private String evaluateResult;
	private String secExplain;
	private String unSecExplain;
	
	public String getId() {		return checkGet(id);	}
	public void setId(String id) {		this.id = checkSet(id);	}
	public String getEvaluateDate() {		return checkGet(evaluateDate);	}
	public void setEvaluateDate(String evaluateDate) {		this.evaluateDate = checkSet(evaluateDate);	}
	public String getEvaluateCommittee() {		return checkGet(evaluateCommittee);	}
	public void setEvaluateCommittee(String evaluateCommittee) {		this.evaluateCommittee = checkSet(evaluateCommittee);	}
	public String getUnExpectedClassify() {		return checkGet(unExpectedClassify);	}
	public void setUnExpectedClassify(String unExpectedClassify) {		this.unExpectedClassify = checkSet(unExpectedClassify);	}
	public String getEvidentiary() {		return checkGet(evidentiary);	}
	public void setEvidentiary(String evidentiary) {		this.evidentiary = checkSet(evidentiary);	}
	public String getSecSeverity() {		return checkGet(secSeverity);	}
	public void setSecSeverity(String secSeverity) {		this.secSeverity = checkSet(secSeverity);	}
	public String getAdministrativeLevel() {		return checkGet(administrativeLevel);	}
	public void setAdministrativeLevel(String administrativeLevel) {		this.administrativeLevel = checkSet(administrativeLevel);	}
	public String getAssessments() {		return checkGet(assessments);	}
	public void setAssessments(String assessments) {		this.assessments = checkSet(assessments);	}
	public String getEvaluateResult() {		return checkGet(evaluateResult);	}
	public void setEvaluateResult(String evaluateResult) {		this.evaluateResult = checkSet(evaluateResult);}
	public String getSecExplain() {		return checkGet(secExplain);	}
	public void setSecExplain(String secExplain) {		this.secExplain = checkSet(secExplain);	}
	public String[] getUnExpectedReason() {		return checkGet(unExpectedReason);	}
	public void setUnExpectedReason(String[] unExpectedReason) {		this.unExpectedReason = checkSet(unExpectedReason);	}
	public String getUnSecExplain() {		return checkGet(unSecExplain);	}
	public void setUnSecExplain(String unSecExplain) {		this.unSecExplain = checkSet(unSecExplain);	}

	private String HFRASFileRowSBuilder;
	public String getHFRASFileRowSBuilder() {		return get(HFRASFileRowSBuilder);	}
	public void setHFRASFileRowSBuilder(String hFRASFileRowSBuilder) {		HFRASFileRowSBuilder = checkSet(hFRASFileRowSBuilder);	}
	
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
	    	this.setHFRASFileRowSBuilder(sb.toString());
	    }
	}
	
	@Override
	public Object doQueryOne() throws Exception {
		HFRIN0703F obj = this;
		Hfr0006Db c = (Hfr0006Db)View.getObject(" from Hfr0006Db where id = " + Common.getLong(getId()));
		if(c != null){
			String name = View.getLookupField("select hfr1001Db.name from Hfr1002Db where id = " + Common.getLong(c.getEvaluateCommittee()));
			obj.setEvaluateDate(c.getEvaluateDate());
			obj.setEvaluateCommittee(name);
			obj.setUnExpectedClassify(c.getUnExpectedClassify());
			if(!"".equals(Common.get(c.getUnExpectedReason()))){
				String[] t = Common.get(c.getUnExpectedReason()).split(",");
				obj.setUnExpectedReason(t);
			}else{
				obj.setUnExpectedReason(null);
			}
			
			obj.setEvidentiary(c.getEvidentiary());
			obj.setSecSeverity(c.getSecSeverity());
			obj.setAdministrativeLevel(c.getAdministrativeLevel());
			obj.setAssessments(c.getAssessments());
			obj.setEvaluateResult(c.getEvaluateResult());
			obj.setSecExplain(c.getSecExplain());
			obj.setUnSecExplain(c.getUnSecExplain());
			
			java.util.List<Con0001Db> fileList = ServiceGetter.getInstance().getTcbwService().load(" from Con0001Db where fileKind = 'HFRAS' and dbName = 'HFR0006DB' and upLoadId = " + Common.getLong(c.getId()));
			genFileRowItemSet(fileList, "HFRAS");
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
