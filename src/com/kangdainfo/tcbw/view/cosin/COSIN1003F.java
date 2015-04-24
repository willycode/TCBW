package com.kangdainfo.tcbw.view.cosin;

import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Cos0006Db;

public class COSIN1003F extends SuperBean {
	
	private String cos0006DbId;
	private String seqNo;
	private String firstResult;
	private String leaveCaseReason;
//	private String determineMan;
//	private String summary;
//	private String dDealWith;
	private String nonDefective;
	private String dMarked;
	private String dUnMarked;
	private String dMarkedIncomplete;
	private String dMarkedFalse;
	private String dLawlessIng;
	private String dLawlessIngOther;
	private String dExteriorError;
	private String dExteriorErrorOther;
	private String dPackageError;
	private String dPackageErrorOther;
	private String dExpired;
	private String dExpiredOther;
	private String dOthers;
	private String dOthersDesc;
	private String[] measure;
	
	public String getCos0006DbId() {		return checkGet(cos0006DbId);	}
	public void setCos0006DbId(String cos0006DbId) {		this.cos0006DbId = checkSet(cos0006DbId);	}
	public String getSeqNo() {		return checkGet(seqNo);	}
	public void setSeqNo(String seqNo) {		this.seqNo = checkSet(seqNo);	}
	public String getFirstResult() {		return checkGet(firstResult);	}
	public void setFirstResult(String firstResult) {		this.firstResult = checkSet(firstResult);	}
	public String getLeaveCaseReason() {		return checkGet(leaveCaseReason);	}
	public void setLeaveCaseReason(String leaveCaseReason) {		this.leaveCaseReason = checkSet(leaveCaseReason);	}
	//	public String getDetermineMan() {		return checkGet(determineMan);	}
//	public void setDetermineMan(String determineMan) {		this.determineMan = checkSet(determineMan);	}
//	public String getSummary() {		return checkGet(summary);	}
//	public void setSummary(String summary) {		this.summary = checkSet(summary);	}
//	public String getdDealWith() {		return checkGet(dDealWith);	}
//	public void setdDealWith(String dDealWith) {		this.dDealWith = checkSet(dDealWith);	}
	public String getNonDefective() {		return checkGet(nonDefective);	}
	public void setNonDefective(String nonDefective) {		this.nonDefective = checkSet(nonDefective);	}
	public String getdMarked() {		return checkGet(dMarked);	}
	public void setdMarked(String dMarked) {		this.dMarked = checkSet(dMarked);	}
	public String getdUnMarked() {		return checkGet(dUnMarked);	}
	public void setdUnMarked(String dUnMarked) {		this.dUnMarked = checkSet(dUnMarked);	}
	public String getdMarkedIncomplete() {		return checkGet(dMarkedIncomplete);	}
	public void setdMarkedIncomplete(String dMarkedIncomplete) {		this.dMarkedIncomplete = checkSet(dMarkedIncomplete);	}
	public String getdMarkedFalse() {		return checkGet(dMarkedFalse);	}
	public void setdMarkedFalse(String dMarkedFalse) {		this.dMarkedFalse = checkSet(dMarkedFalse);	}
	public String getdLawlessIng() {		return checkGet(dLawlessIng);	}
	public void setdLawlessIng(String dLawlessIng) {		this.dLawlessIng = checkSet(dLawlessIng);	}
	public String getdLawlessIngOther() {		return checkGet(dLawlessIngOther);	}
	public void setdLawlessIngOther(String dLawlessIngOther) {		this.dLawlessIngOther = checkSet(dLawlessIngOther);	}
	public String getdExteriorError() {		return checkGet(dExteriorError);	}
	public void setdExteriorError(String dExteriorError) {		this.dExteriorError = checkSet(dExteriorError);	}
	public String getdExteriorErrorOther() {		return checkGet(dExteriorErrorOther);	}
	public void setdExteriorErrorOther(String dExteriorErrorOther) {		this.dExteriorErrorOther = checkSet(dExteriorErrorOther);	}
	public String getdPackageError() {		return checkGet(dPackageError);	}
	public void setdPackageError(String dPackageError) {		this.dPackageError = checkSet(dPackageError);	}
	public String getdPackageErrorOther() {		return checkGet(dPackageErrorOther);	}
	public void setdPackageErrorOther(String dPackageErrorOther) {		this.dPackageErrorOther = checkSet(dPackageErrorOther);	}
	public String getdExpired() {		return checkGet(dExpired);	}
	public void setdExpired(String dExpired) {		this.dExpired = checkSet(dExpired);}
	public String getdExpiredOther() {		return checkGet(dExpiredOther);	}
	public void setdExpiredOther(String dExpiredOther) {		this.dExpiredOther = checkSet(dExpiredOther);	}
	public String getdOthers() {		return checkGet(dOthers);	}
	public void setdOthers(String dOthers) {		this.dOthers = checkSet(dOthers);	}
	public String getdOthersDesc() {		return checkGet(dOthersDesc);	}
	public void setdOthersDesc(String dOthersDesc) {		this.dOthersDesc = checkSet(dOthersDesc);	}
	public String[] getMeasure() {		return measure;	}
	public void setMeasure(String[] measure) {		this.measure = measure;	}

	@Override
	public Object doQueryOne() throws Exception {
		COSIN1003F obj = this;
		Cos0006Db cos0006Db = (Cos0006Db)View.getObject(" from Cos0006Db where id = " + Common.getLong(getCos0006DbId()));
		if(cos0006Db != null){
			obj.setCos0006DbId(Common.get(cos0006Db.getId()));
			obj.setFirstResult(cos0006Db.getFirstResult());
			obj.setLeaveCaseReason(cos0006Db.getLeaveCaseReason());
			obj.setNonDefective(cos0006Db.getNonDefective());
			obj.setdMarked(cos0006Db.getMarked());
			
			obj.setdLawlessIng(cos0006Db.getLawlessIng());
			obj.setdLawlessIngOther(cos0006Db.getLawlessIngOther());
			obj.setdExteriorError(cos0006Db.getExteriorError());
			obj.setdExteriorErrorOther(cos0006Db.getExteriorErrorOther());
			obj.setdPackageError(cos0006Db.getPackageError());
			obj.setdPackageErrorOther(cos0006Db.getPackageErrorOther());
			obj.setdExpired(cos0006Db.getExpired());
			obj.setdExpiredOther(cos0006Db.getExpiredOther());
			obj.setdOthers(cos0006Db.getOthers());
			obj.setdOthersDesc(cos0006Db.getOthersDesc());
			if(Common.get(cos0006Db.getMeasure()).length() > 0){
				String[] tmp = Common.get(cos0006Db.getMeasure()).split(",");
				obj.setMeasure(tmp);
			}else{
				obj.setMeasure(null);
			}
		}else{
			obj.setCos0006DbId("");
			obj.setErrorMsg("查無該筆歷史資料");
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
