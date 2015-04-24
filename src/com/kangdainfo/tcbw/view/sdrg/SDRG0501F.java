package com.kangdainfo.tcbw.view.sdrg;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.Datetime;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Drg8001Db;
import com.kangdainfo.tcbw.model.bo.Drg8003Db;
import com.kangdainfo.tcbw.model.bo.Drg8004Db;


public class SDRG0501F extends SDRG0401F{
	
	//Drg8004Db
	private String q_assessextendate;	 //案件評估-調查報告展延日期
	private String assessextendate;	     //案件評估-調查報告展延日期
	private String assessdate;	         //案件評估-評估日期
	private String assessrecyclereason;	 //案件評估-本案原由
	private String assesssurveyresult;	 //案件評估-清查結果
	private String assesssurveyreport;	 //案件評估-調查報告
	private String assessprecaution;	 //案件評估-預防矯正措施及改善時程
	private String[] assessdealWith;	     //案件評估-擬辦事項
	private String assessresult;	     //案件評估-評估結果

	//案件狀態(Drg8004Db)
	private String status2;

	public void doUpdate0501() throws Exception{
		ServiceGetter.getInstance().getTcbwService().getSdrgDao().updateBySdrg0501F(this);
	}
    
	public Object doQueryOne0501() throws Exception {
		SDRG0501F obj = this;	
		
		Drg8004Db c = (Drg8004Db) View.getObject(" from Drg8004Db where drg8001Db.id=" + Common.getInt(obj.getId()));
		
		//System.out.println("[TCBW]-[SDRG0501F]-[doQueryOne]- Drg8001Db.id : " + obj.getId());
		
		if (c!=null) {			
			obj.setStatus2(c.getStatus());
			obj.setQ_assessextendate(Common.get(c.getAssessextendate()));	         //案件評估-調查報告展延日期
			obj.setAssessdate(Common.get(c.getAssessdate()));	                     //案件評估-評估日期
			obj.setAssessrecyclereason(Common.get(c.getAssessrecyclereason()));	     //案件評估-本案原由
			obj.setAssesssurveyresult(Common.get(c.getAssesssurveyresult()));	     //案件評估-清查結果
			obj.setAssesssurveyreport(Common.get(c.getAssesssurveyreport()));	     //案件評估-調查報告
			obj.setAssessprecaution(Common.get(c.getAssessprecaution()));	         //案件評估-預防矯正措施及改善時程
			String[] assessdealWith = null;
			if(c.getAssessdealWith() != null && c.getAssessdealWith().length()>0){
				assessdealWith = new String[c.getAssessdealWith().length()];
				assessdealWith = c.getAssessdealWith().split(",");
			}
			obj.setAssessdealWith(assessdealWith);	                                 //案件評估-擬辦事項
			obj.setAssessresult(Common.get(c.getAssessresult()));	                 //案件評估-評估結果		
			
		}
		this.setState("queryOneSuccess");
		return obj;
	}
	
	//回收期限展延
	public void doExtension0501() throws Exception{
		SDRG0501F obj = this;		
		Drg8004Db drg8004 = (Drg8004Db) View.getObject(" from Drg8004Db where drg8001Db.id=" + Common.getInt(obj.getId()));
		if (drg8004!=null) {			
			drg8004.setAssessextendate(Common.get(obj.getAssessextendate()));	          //申請展延日期
			drg8004.setModifier(obj.getUserID());
			drg8004.setModifyDate(Datetime.getYYYMMDD());
			drg8004.setModifyTime(Datetime.getHHMMSS());
			ServiceGetter.getInstance().getTcbwService().update(drg8004);			
		}
	}

	
	public String getQ_assessextendate() {
		return checkGet(q_assessextendate);
	}

	public void setQ_assessextendate(String q_assessextendate) {
		this.q_assessextendate = checkSet(q_assessextendate);
	}

	public String getAssessextendate() {
		return checkGet(assessextendate);
	}

	public void setAssessextendate(String assessextendate) {
		this.assessextendate = checkSet(assessextendate);
	}

	public String getAssessdate() {
		return checkGet(assessdate);
	}

	public void setAssessdate(String assessdate) {
		this.assessdate = checkSet(assessdate);
	}

	public String getAssessrecyclereason() {
		return checkGet(assessrecyclereason);
	}

	public void setAssessrecyclereason(String assessrecyclereason) {
		this.assessrecyclereason = checkSet(assessrecyclereason);
	}

	public String getAssesssurveyresult() {
		return checkGet(assesssurveyresult);
	}

	public void setAssesssurveyresult(String assesssurveyresult) {
		this.assesssurveyresult = checkSet(assesssurveyresult);
	}

	public String getAssesssurveyreport() {
		return checkGet(assesssurveyreport);
	}

	public void setAssesssurveyreport(String assesssurveyreport) {
		this.assesssurveyreport = checkSet(assesssurveyreport);
	}

	public String getAssessprecaution() {
		return checkGet(assessprecaution);
	}

	public void setAssessprecaution(String assessprecaution) {
		this.assessprecaution = checkSet(assessprecaution);
	}

	public String[] getAssessdealWith() {
		return checkGet(assessdealWith);
	}

	public void setAssessdealWith(String[] assessdealWith) {
		this.assessdealWith = checkSet(assessdealWith);
	}

	public String getAssessresult() {
		return checkGet(assessresult);
	}

	public void setAssessresult(String assessresult) {
		this.assessresult = checkSet(assessresult);
	}
	
	public String getStatus2() {
		return checkGet(status2);
	}
	public void setStatus2(String status2) {
		this.status2 = checkSet(status2);
	}
}
