package com.kangdainfo.tcbw.view.cosin;

import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Cos0007Db;

public class COSIN0803F extends SuperBean {

	private String cos0007DbId;
	private String assessMan;
	private String assessDate;
	private String assessResult;
	private String assessRemark;

	public String getCos0007DbId() {		return checkGet(cos0007DbId);	}
	public void setCos0007DbId(String cos0007DbId) {		this.cos0007DbId = checkSet(cos0007DbId);	}
	public String getAssessMan() {	return checkGet(assessMan);	}
	public void setAssessMan(String assessMan) {		this.assessMan = checkSet(assessMan);	}
	public String getAssessDate() {		return checkGet(assessDate);	}
	public void setAssessDate(String assessDate) {		this.assessDate = checkSet(assessDate);	}
	public String getAssessResult() {		return checkGet(assessResult);	}
	public void setAssessResult(String assessResult) {		this.assessResult = checkSet(assessResult);	}
	public String getAssessRemark() {		return checkGet(assessRemark);	}
	public void setAssessRemark(String assessRemark) {		this.assessRemark = checkSet(assessRemark);	}
	
	
	@Override
	public Object doQueryOne() throws Exception {
		COSIN0803F obj = this;
		Cos0007Db cos0007Db = (Cos0007Db)View.getObject(" from Cos0007Db where id = " + Common.getLong(getCos0007DbId()));
		if(cos0007Db != null){
			obj.setCos0007DbId(Common.get(cos0007Db.getId()));
			obj.setAssessDate(cos0007Db.getAssessDate());
			obj.setAssessResult(cos0007Db.getAssessResult());
			obj.setAssessRemark(cos0007Db.getAssessRemark());
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
