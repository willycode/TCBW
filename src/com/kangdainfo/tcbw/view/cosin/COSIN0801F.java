package com.kangdainfo.tcbw.view.cosin;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.tcbw.model.bo.Cos0001Db;
import com.kangdainfo.tcbw.model.bo.Cos0003Db;
import com.kangdainfo.tcbw.model.bo.Cos0007Db;
import com.kangdainfo.tcbw.model.bo.Cos1001Db;

public class COSIN0801F extends COSIN0401F {
	
	// 程式設定
	private String q_type;
	public String getQ_type() {		return checkGet(q_type);	}
	public void setQ_type(String q_type) {		this.q_type = checkSet(q_type);	}

	// COS0007_DB
	private String cos0007DbId;
	private String assessMan;
	private String assessDate;
	private String assessResult;
	private String assessRemark;
	private String isNeedShowCos0007DbPage;
	private java.util.ArrayList cos0007DbList;

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
	public String getIsNeedShowCos0007DbPage() {		return checkGet(isNeedShowCos0007DbPage);	}
	public void setIsNeedShowCos0007DbPage(String isNeedShowCos0007DbPage) {		this.isNeedShowCos0007DbPage = checkSet(isNeedShowCos0007DbPage);	}
	public java.util.ArrayList getCos0007DbList() {		return cos0007DbList;	}
	public void setCos0007DbList(java.util.ArrayList cos0007DbList) {		this.cos0007DbList = cos0007DbList;	}

	// COS0006_DB
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

	// COS0008_DB
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
	
	private String num1;
	private String num2;
	private String isHasHistory;								// FOR 不良品判斷是否須顯示歷史流程資料
	
	private java.util.ArrayList cos0006DbList;
	private java.util.ArrayList cos0008DbList;
	
	public String getNum1() {		return checkGet(num1);	}
	public void setNum1(String num1) {		this.num1 = checkSet(num1);	}
	public String getNum2() {		return checkGet(num2);	}
	public void setNum2(String num2) {		this.num2 = checkSet(num2);	}
	public String getIsHasHistory() {		return checkGet(isHasHistory);	}
	public void setIsHasHistory(String isHasHistory) {		this.isHasHistory = checkSet(isHasHistory);	}
	
	public java.util.ArrayList getCos0006DbList() {		return cos0006DbList;	}
	public void setCos0006DbList(java.util.ArrayList cos0006DbList) {		this.cos0006DbList = cos0006DbList;	}
	public java.util.ArrayList getCos0008DbList() {		return cos0008DbList;	}
	public void setCos0008DbList(java.util.ArrayList cos0008DbList) {		this.cos0008DbList = cos0008DbList;	}
	
	public void setAssessPageData(COSIN0801F obj, Cos0001Db c) throws Exception {
		setAssessPageData(obj, c, false);
	}
	
	// 評估頁籤資料
	public void setAssessPageData(COSIN0801F obj, Cos0001Db c, boolean isSpecialFlow) throws Exception {
		java.util.List<Cos0007Db> cos0007DbList = ServiceGetter.getInstance().getTcbwService().load(" from Cos0007Db where applNo = " + Common.sqlChar(c.getApplNo()));
		if(cos0007DbList!=null && cos0007DbList.size()>0){
			boolean flag = true;
			java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
			for(Cos0007Db dtl : cos0007DbList){
				if(isSpecialFlow){										// FOR 修正作業用
					obj.setCos0007DbId(Common.get(dtl.getId()));
					obj.setAssessResult(dtl.getAssessResult());
					obj.setAssessRemark(dtl.getAssessRemark());
					flag = false;
				}
				if(!"".equals(Common.get(dtl.getAssessDate()))){
					String[] rowArray = new String[3];
					rowArray[0] = Common.get(dtl.getId());
					rowArray[1] = Common.get(dtl.getAssessDate());
					rowArray[2] = Common.get(dtl.getAssessResult()).equals("1")?"不良品":Common.get(dtl.getAssessResult()).equals("2")?"非不良品":Common.get(dtl.getAssessResult()); 
					arrList.add(rowArray);
				}else{
					if(flag){
						obj.setCos0007DbId(Common.get(dtl.getId()));
						obj.setAssessResult(dtl.getAssessResult());
						obj.setAssessRemark(dtl.getAssessRemark());
						flag = false;
					}
				}
			}
			
			if(flag){
				obj.setCos0007DbId("");
			}
			obj.setCos0007DbList(arrList);
			obj.setIsNeedShowCos0007DbPage("Y");					// FOR 後續引用的頁籤用
		}else{
			obj.setCos0007DbId("");
			obj.setCos0007DbList(null);
			
			if(isSpecialFlow){
				obj.setIsNeedShowCos0007DbPage("Y");
			}else{
				obj.setIsNeedShowCos0007DbPage("N");
			}
			
		}	
	}
	
	public Object doQueryAll() throws Exception {
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		
		String hql = " from Cos0001Db where status = '60' and cosType = '1' ";
		if(!"".equals(getQ_applNo()))
			hql += " and applNo = " + Common.sqlChar(getQ_applNo());
		if(!"".equals(getQ_notifierRepDateS()))
			hql += " and notifierRepDate >= " + Common.sqlChar(getQ_notifierRepDateS());
		if(!"".equals(getQ_notifierRepDateE()))
			hql += " and notifierRepDate <= " + Common.sqlChar(getQ_notifierRepDateE());
		if(!"".equals(getQ_permitKey()))
			hql += " and permitKey = " + Common.sqlChar(getQ_permitKey());
		if(!"".equals(getQ_permitNo()))
			hql += " and permitNo = " + Common.sqlChar(getQ_permitNo());
		if(!"".equals(getQ_chProduct()))
			hql += " and chProduct like " + Common.sqlChar("%" + getQ_chProduct() + "%");
		if(!"".equals(getQ_manufactorName()))
			hql += " and manufactorName like " + Common.sqlChar("%" + getQ_manufactorName() + "%");
		if(!"".equals(getQ_chargeMan()))
			hql += " and chargeMan = " + Common.sqlChar(getQ_chargeMan());
		
		if(getQ_subCode()!=null && getQ_subCode().length>0){
			StringBuffer sb = new StringBuffer();
			sb.append("(");
			
			boolean flag = true;
			for(String rid : getQ_subCode()){	
				if(flag){
					sb.append(" subCode like " + Common.sqlChar("%" + rid + "%"));
					flag = false;
				}else{
					sb.append(" or subCode like " + Common.sqlChar("%" + rid + "%"));
				}
			}
			sb.append(")");
			hql += " and id in (select distinct cos0001Db.id from Cos0003Db where " + sb.toString() + ") ";
		}
		System.out.println("[TCBW]-[COSIN0801F]-[QUERYALL] : " + hql + " order by id ");
		
		java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(hql + " order by id desc");
		if (objList != null && objList.size() > 0) {
			java.util.Map<String, String> CPTMap = ServiceGetter.getInstance().getCommonService().getCommonCodeDao().getCommonCodeNameMap("CPT", null);
			java.util.Map<String, String> CCSMap = ServiceGetter.getInstance().getCommonService().getCommonCodeDao().getCommonCodeNameMap("CCS", null);
			
			java.util.Map<String, String> subCodeNameMap = new java.util.HashMap<String, String>();
			java.util.List<Cos1001Db> cos1001DbList = ServiceGetter.getInstance().getTcbwService().load(" from Cos1001Db where isStop = 'N'");
			if(cos1001DbList!=null && cos1001DbList.size()>0){
				for(Cos1001Db cos1001Db : cos1001DbList){
					subCodeNameMap.put(Common.get(cos1001Db.getDpdKind()), Common.get(cos1001Db.getDpdKindName()));
				}
				cos1001DbList.clear();
			}
			
			java.util.Map<String, String> cosUserMap = new java.util.HashMap<String, String>();
			java.util.List userList = ServiceGetter.getInstance().getTcbwService().load("select userId, userName from CommonUser where inORout = 'in'");
			if(userList!=null && userList.size()>0){
				for(Object dtlObj : userList){
					Object[] dtl = (Object[])dtlObj;
					cosUserMap.put(Common.get(dtl[0]), Common.get(dtl[1]));
				}
				userList.clear();
			}
			
			for(Object dtlObj : objList) {				
				Cos0001Db dtl = (Cos0001Db)dtlObj;
				
				String[] rowArray = new String[10];
				rowArray[0] = Common.get(dtl.getId());
				rowArray[1] = Common.get(dtl.getApplNo());
				rowArray[2] = Common.get(dtl.getNotifierRepDate());
				rowArray[3] = "";
				if(!"".equals(Common.get(dtl.getPermitKey())) || !"".equals(Common.get(dtl.getPermitNo()))){
					rowArray[3] = (Common.get(CPTMap.get(Common.get(dtl.getPermitKey()))).equals("")?Common.get(dtl.getPermitKey()):Common.get(CPTMap.get(Common.get(dtl.getPermitKey())))) + 
								  " 字第 " + Common.get(dtl.getPermitNo()) + " 號";
				}
				rowArray[4] = Common.get(dtl.getChProduct());
				rowArray[5] = Common.get(dtl.getEnProduct());
				rowArray[6] = Common.get(dtl.getManufactorName());
				
				rowArray[7] = "";
				if(dtl.getCos0003Dbs()!=null && dtl.getCos0003Dbs().size()>0){
					StringBuffer sb = new StringBuffer();
					for(Object cos0003DbObj : dtl.getCos0003Dbs()){
						Cos0003Db cos0003Db = (Cos0003Db)cos0003DbObj;
						if(sb.toString().length() > 0){
							sb.append("、");
						}
						sb.append(Common.get(subCodeNameMap.get(Common.get(cos0003Db.getSubCode()))));
					}
					rowArray[7] = sb.toString();
				}
				
				rowArray[8] = Common.get(CCSMap.get(Common.get(dtl.getStatus()))).equals("")?Common.get(dtl.getStatus()):Common.get(CCSMap.get(Common.get(dtl.getStatus())));
				rowArray[9] = Common.get(cosUserMap.get(Common.get(dtl.getChargeMan()))).equals("")?Common.get(dtl.getChargeMan()):Common.get(cosUserMap.get(Common.get(dtl.getChargeMan())));
				arrList.add(rowArray);
			}
			objList.clear();
			
			CPTMap.clear();
			CCSMap.clear();
			subCodeNameMap.clear();
			cosUserMap.clear();
		}
		return arrList;
		
	}
	
	

}
