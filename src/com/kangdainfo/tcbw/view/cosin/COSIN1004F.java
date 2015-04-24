package com.kangdainfo.tcbw.view.cosin;

import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Cos0008Db;

public class COSIN1004F extends SuperBean {

	private String cos0008DbId;
	private String preResult;
	private String leftCaseReason;
	private String isCompleteYn;	
	private String dIsContactYn;	
    private String timingLev;	
    private String previousNotify;	
    private String isComplaintYn;	
    private String dIsOtherDeptYn;
    private String reactionLev;
    
	public String getCos0008DbId() {		return checkGet(cos0008DbId);	}
	public void setCos0008DbId(String cos0008DbId) {		this.cos0008DbId = checkSet(cos0008DbId);	}
	public String getPreResult() {		return checkGet(preResult);	}
	public void setPreResult(String preResult) {		this.preResult = checkSet(preResult);	}
	public String getLeftCaseReason() {		return checkGet(leftCaseReason);	}
	public void setLeftCaseReason(String leftCaseReason) {		this.leftCaseReason = checkSet(leftCaseReason);	}
	public String getIsCompleteYn() {		return checkGet(isCompleteYn);	}
	public void setIsCompleteYn(String isCompleteYn) {		this.isCompleteYn = checkSet(isCompleteYn);	}
	public String getdIsContactYn() {		return checkGet(dIsContactYn);	}
	public void setdIsContactYn(String dIsContactYn) {		this.dIsContactYn = checkSet(dIsContactYn);	}
	public String getTimingLev() {		return checkGet(timingLev);	}
	public void setTimingLev(String timingLev) {		this.timingLev = checkSet(timingLev);	}
	public String getPreviousNotify() {		return checkGet(previousNotify);	}
	public void setPreviousNotify(String previousNotify) {		this.previousNotify = checkSet(previousNotify);	}
	public String getIsComplaintYn() {		return checkGet(isComplaintYn);	}
	public void setIsComplaintYn(String isComplaintYn) {		this.isComplaintYn = checkSet(isComplaintYn);	}
	public String getdIsOtherDeptYn() {		return checkGet(dIsOtherDeptYn);	}
	public void setdIsOtherDeptYn(String dIsOtherDeptYn) {		this.dIsOtherDeptYn = checkSet(dIsOtherDeptYn);	}
	public String getReactionLev() {		return checkGet(reactionLev);	}
	public void setReactionLev(String reactionLev) {		this.reactionLev = checkSet(reactionLev);	}

	@Override
	public Object doQueryOne() throws Exception {
		COSIN1004F obj = this;
		
		Cos0008Db cos0008Db = (Cos0008Db)View.getObject(" from Cos0008Db where id = " + Common.getLong(getCos0008DbId()));
		if(cos0008Db != null){
			obj.setPreResult(cos0008Db.getPreResult());
			obj.setLeftCaseReason(cos0008Db.getLeftCaseReason());
			obj.setIsCompleteYn(cos0008Db.getIsCompleteYn());
			obj.setdIsContactYn(cos0008Db.getIsContactYn());
			obj.setReactionLev(cos0008Db.getReactionLev());
			obj.setTimingLev(cos0008Db.getTimingLev());
			obj.setPreviousNotify(cos0008Db.getPreviousNotify());
			obj.setIsComplaintYn(cos0008Db.getIsComplaintYn());
			obj.setdIsOtherDeptYn(cos0008Db.getIsOtherDeptYn());
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
