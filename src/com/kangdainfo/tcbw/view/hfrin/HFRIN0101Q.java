package com.kangdainfo.tcbw.view.hfrin;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.tcbw.model.bo.Hfr0002Db;
import com.kangdainfo.tcbw.model.bo.Hfr0007Db;
import com.kangdainfo.tcbw.util.TCBWCommon;

public class HFRIN0101Q extends SuperBean{

	private String q_isQuery;
	private String q_id;
	private String q_applNoS;
	private String q_applNoE;
	private String q_occurDateS;
	private String q_occurDateE;
	private String q_notifierRevDateS;
	private String q_notifierRevDateE;
	private String q_notifierRepDateS;
	private String q_notifierRepDateE;
	private String q_informedSources;
	private String q_permitKey;
	private String q_permitNo;
	private String q_productName;
	private String q_ingredient;
	private String q_buySource;
	private String q_unExpectedClassify;
	private String q_unExpectedReason;
	private String q_evidentiary;
	private String q_thiSeverity;
	private String q_administrativeLevel;
	private String q_status;
	private String q_isTrans;  	        //是否為歷史移轉資料
	
	public String getQ_isQuery() {
		return checkGet(q_isQuery);
	}

	public void setQ_isQuery(String qIsQuery) {
		q_isQuery = checkSet(qIsQuery);
	}

	public String getQ_id() {
		return checkGet(q_id);
	}

	public void setQ_id(String qId) {
		q_id = checkSet(qId);
	}

	public String getQ_applNoS() {
		return checkGet(q_applNoS);
	}

	public void setQ_applNoS(String qApplNoS) {
		q_applNoS = checkSet(qApplNoS);
	}

	public String getQ_applNoE() {
		return checkGet(q_applNoE);
	}

	public void setQ_applNoE(String qApplNoE) {
		q_applNoE = checkSet(qApplNoE);
	}

	public String getQ_occurDateS() {
		return checkGet(q_occurDateS);
	}

	public void setQ_occurDateS(String qOccurDateS) {
		q_occurDateS = checkSet(qOccurDateS);
	}

	public String getQ_occurDateE() {
		return checkGet(q_occurDateE);
	}

	public void setQ_occurDateE(String qOccurDateE) {
		q_occurDateE = checkSet(qOccurDateE);
	}

	public String getQ_notifierRevDateS() {
		return checkGet(q_notifierRevDateS);
	}

	public void setQ_notifierRevDateS(String qNotifierRevDateS) {
		q_notifierRevDateS = checkSet(qNotifierRevDateS);
	}

	public String getQ_notifierRevDateE() {
		return checkGet(q_notifierRevDateE);
	}

	public void setQ_notifierRevDateE(String qNotifierRevDateE) {
		q_notifierRevDateE = checkSet(qNotifierRevDateE);
	}

	public String getQ_notifierRepDateS() {
		return checkGet(q_notifierRepDateS);
	}

	public void setQ_notifierRepDateS(String qNotifierRepDateS) {
		q_notifierRepDateS = checkSet(qNotifierRepDateS);
	}

	public String getQ_notifierRepDateE() {
		return checkGet(q_notifierRepDateE);
	}

	public void setQ_notifierRepDateE(String qNotifierRepDateE) {
		q_notifierRepDateE = checkSet(qNotifierRepDateE);
	}

	public String getQ_informedSources() {
		return checkGet(q_informedSources);
	}

	public void setQ_informedSources(String qInformedSources) {
		q_informedSources = checkSet(qInformedSources);
	}

	public String getQ_permitKey() {
		return checkGet(q_permitKey);
	}

	public void setQ_permitKey(String qPermitKey) {
		q_permitKey = checkSet(qPermitKey);
	}

	public String getQ_permitNo() {
		return checkGet(q_permitNo);
	}

	public void setQ_permitNo(String qPermitNo) {
		q_permitNo = checkSet(qPermitNo);
	}

	public String getQ_productName() {
		return checkGet(q_productName);
	}

	public void setQ_productName(String qProductName) {
		q_productName = checkSet(qProductName);
	}

	public String getQ_ingredient() {
		return checkGet(q_ingredient);
	}

	public void setQ_ingredient(String qIngredient) {
		q_ingredient = checkSet(qIngredient);
	}

	public String getQ_buySource() {
		return checkGet(q_buySource);
	}

	public void setQ_buySource(String qBuySource) {
		q_buySource = checkSet(qBuySource);
	}

	public String getQ_unExpectedClassify() {
		return checkGet(q_unExpectedClassify);
	}

	public void setQ_unExpectedClassify(String qUnExpectedClassify) {
		q_unExpectedClassify = checkSet(qUnExpectedClassify);
	}

	public String getQ_unExpectedReason() {
		return checkGet(q_unExpectedReason);
	}

	public void setQ_unExpectedReason(String qUnExpectedReason) {
		q_unExpectedReason = checkSet(qUnExpectedReason);
	}

	public String getQ_evidentiary() {
		return checkGet(q_evidentiary);
	}

	public void setQ_evidentiary(String qEvidentiary) {
		q_evidentiary = checkSet(qEvidentiary);
	}

	public String getQ_thiSeverity() {
		return checkGet(q_thiSeverity);
	}

	public void setQ_thiSeverity(String qThiSeverity) {
		q_thiSeverity = checkSet(qThiSeverity);
	}

	public String getQ_administrativeLevel() {
		return checkGet(q_administrativeLevel);
	}

	public void setQ_administrativeLevel(String qAdministrativeLevel) {
		q_administrativeLevel = checkSet(qAdministrativeLevel);
	}

	public String getQ_status() {
		return checkGet(q_status);
	}

	public void setQ_status(String qStatus) {
		q_status = checkSet(qStatus);
	}
	
	public String getQ_isTrans() {
		return checkGet(q_isTrans);
	}

	public void setQ_isTrans(String q_isTrans) {
		this.q_isTrans = checkSet(q_isTrans);
	}

	@Override
	public Object doQueryOne() throws Exception {
		return null;
	}

	@Override
	public Object doQueryAll() throws Exception {		
		if (!"".equals(getQ_isQuery())){
			setQ_id("");
		}
		
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		String hql = " from Hfr0002Db where 1 = 1 and type in ('N','G')";
		if(!"".equals(getQ_id())){
			hql += " and id = " + Common.getLong(getQ_id());
		}else{
			if(!"".equals(getQ_applNoS())) 
				hql += " and hfr0001Db.applNo >= " +  Common.sqlChar(getQ_applNoS());
			if(!"".equals(getQ_applNoE())) 
				hql += " and hfr0001Db.applNo <= " + Common.sqlChar(getQ_applNoE());
			
			if(!"".equals(getQ_occurDateS())) 
				hql += " and hfr0001Db.occurDate >= " + Common.sqlChar(getQ_occurDateS());
			if(!"".equals(getQ_occurDateE()))
				hql += " and hfr0001Db.occurDate <= " + Common.sqlChar(getQ_occurDateE());
			//收案日期
			if(!"".equals(getQ_notifierRevDateS())) 
				hql += " and hfr0001Db.enrolledDate >= " + Common.sqlChar(getQ_notifierRevDateS());
			if(!"".equals(getQ_notifierRevDateE())) 
				hql += " and hfr0001Db.enrolledDate <= " + Common.sqlChar(getQ_notifierRevDateE());
			
			if(!"".equals(getQ_notifierRepDateS())) 
				hql += " and hfr0001Db.notifierRepDate >= " + Common.sqlChar(getQ_notifierRepDateS());
			if(!"".equals(getQ_notifierRepDateE())) 
				hql += " and hfr0001Db.notifierRepDate <= " + Common.sqlChar(getQ_notifierRepDateE());
			
			if(!"".equals(getQ_informedSources())) 
				hql += " and hfr0001Db.informedSources = " + Common.sqlChar(getQ_informedSources());
			
			if(!"".equals(getQ_permitKey())) 
				hql += " and permitKey = " + Common.sqlChar(getQ_permitKey());
			
			if(!"".equals(getQ_permitNo())) 
				hql += " and permitNo = " + Common.sqlChar(getQ_permitNo());
			
			if(!"".equals(getQ_productName())) 
				hql += " and (chProduct like " + Common.sqlChar("%" + getQ_productName() + "%")  + " or enProduct like " + Common.sqlChar("%" + getQ_productName() + "%") + ")";
		
			if(!"".equals(getQ_ingredient())) 
				hql += " and ingredient like " + Common.sqlChar("%" + getQ_ingredient() + "%");
		
			if(!"".equals(getQ_buySource())) 
				hql += " and buySource = " + Common.sqlChar(getQ_buySource());
			
			if(!"".equals(getQ_unExpectedClassify())) 
				hql += " and hfr0001Db.unExpectedClassify = " + Common.sqlChar(getQ_unExpectedClassify());
			
			if(!"".equals(getQ_unExpectedReason())) 
				hql += " and hfr0001Db.unExpectedReason = " + Common.sqlChar(getQ_unExpectedReason());
			
			if(!"".equals(getQ_evidentiary())) 
				hql += " and hfr0001Db.evidentiary = " + Common.sqlChar(getQ_evidentiary());
			
			if(!"".equals(getQ_thiSeverity())) 
				hql += " and hfr0001Db.recentlySeverity = " + Common.sqlChar(getQ_thiSeverity());
			
			if(!"".equals(getQ_administrativeLevel())) 
				hql += " and hfr0001Db.administrativeLevel = " + Common.sqlChar(getQ_administrativeLevel());
			
			if(!"".equals(getQ_status())) 
				hql += " and hfr0001Db.status = " + Common.sqlChar(getQ_status());
			
			if(!"".equals(getQ_isTrans())){
				if("Y".equals(getQ_isTrans())) hql += " and hfr0001Db.trans='Y' ";
				else if ("N".equals(getQ_isTrans())) hql += " and ( hfr0001Db.trans is null or hfr0001Db.trans='' ) ";
			}
		}
		
		System.out.println("[TCBW]-[HFRIN0101Q]-[食品-doQueryAll] : " + hql);	
		this.processCurrentPageAttribute(ServiceGetter.getInstance().getTcbwService().loadCount(hql));
		if (getTotalRecord() > 0){
			if (getState().indexOf("query")<0) 
				ServiceGetter.getInstance().getCommonService().getHibernateTemplate().clear();
			java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(hql + " order by id desc", this.getRecordStart(), this.getPageSize());
			if (objList != null && objList.size() > 0) {
				java.util.Map<String, String> statusMap = TCBWCommon.getCommonCodeMap("FCS");
				java.util.Map<String, String> ResMap = TCBWCommon.getCommonCodeMap("HFRURCR");
				for(Object dtlObj : objList) {				
					Hfr0002Db dtl = (Hfr0002Db)dtlObj;
					String[] rowArray = new String[8];
					rowArray[0] = Common.get(dtl.getHfr0001Db().getId());
					rowArray[1] = Common.get(dtl.getHfr0001Db().getApplNo());
					rowArray[2] = Common.formatYYYMMDD(Common.get(dtl.getHfr0001Db().getNotifierRepDate()),4);
					rowArray[3] = Common.get(dtl.getChProduct());
					rowArray[4] = "Y".equals(dtl.getHfr0001Db().getUnHealthIsYes())?"是":"N".equals(dtl.getHfr0001Db().getUnHealthIsYes())?"否":"";//unHealthBrief
					rowArray[5] = ResMap.get(dtl.getHfr0001Db().getUnReactionResults());
					if(dtl.getHfr0001Db().getHfr0007Dbs()!=null && dtl.getHfr0001Db().getHfr0007Dbs().size()>0){
						for(Object dObj : dtl.getHfr0001Db().getHfr0007Dbs()){
							Hfr0007Db d = (Hfr0007Db)dObj;
							rowArray[6] = "1".equals(d.getReEvaluateResult())?"結案":"2".equals(d.getReEvaluateResult())?"重新評估":"";
						}
					}else{
						rowArray[6] = "";
					}
					rowArray[7] = statusMap.get(dtl.getHfr0001Db().getStatus());
					arrList.add(rowArray);
				}
				objList.clear();			
			}		
		}
		return arrList;
	}

	@Override
	public void doCreate() throws Exception{}

	@Override
	public void doUpdate() throws Exception{}

	@Override
	public void doDelete() throws Exception{}
	
}
