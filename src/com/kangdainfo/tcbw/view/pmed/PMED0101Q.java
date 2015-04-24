package com.kangdainfo.tcbw.view.pmed;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.model.bo.CommonUser;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Med0001Db;
import com.kangdainfo.tcbw.model.bo.Med9001Db;
import com.kangdainfo.tcbw.util.TCBWCommon;


public class PMED0101Q extends SuperBean
{
	private String doType;
	public String getDoType() {return checkGet(doType);}
	public void setDoType(String doType) {this.doType = checkSet(doType);}
	
	String caseType ;
	public String getCaseType() {return checkGet(caseType);}
	public void setCaseType(String caseType) {this.caseType = checkSet(caseType);}
	
	private String q_applNoS;
	private String q_applNoE;
	private String q_monitorNo;
	private String q_status;
	private String q_permitKey;
	private String q_permitNo;
	private String q_chProduct;
	private String q_applicationName;
	private String q_medMainCategory;
	private String q_medSecCategory;
	private String q_monitorSDate;
	private String q_monitorEDate;
	private String q_trans;//是否為歷史移轉資料
	
	
	public void setQ_permitKey(String q_permitKey) {
		this.q_permitKey = checkSet(q_permitKey);
	}
	public String getQ_applNoS() {
		return checkGet(q_applNoS);
	}
	public void setQ_applNoS(String q_applNoS) {
		this.q_applNoS = checkSet(q_applNoS);
	}
	
	public String getQ_applNoE() {
		return checkGet(q_applNoE);
	}
	public void setQ_applNoE(String q_applNoE) {
		this.q_applNoE = checkSet(q_applNoE);
	}
	
	public String getQ_monitorNo() {
		return checkGet(q_monitorNo);
	}
	public void setQ_monitorNo(String q_monitorNo) {
		this.q_monitorNo = checkSet(q_monitorNo);
	}
	
	public String getQ_status() {
		return checkGet(q_status);
	}
	public void setQ_status(String q_status) {
		this.q_status = checkSet(q_status);
	}
	public String getQ_permitKey() {
		return checkGet(q_permitKey);
	}
	public void setQ_permit(String q_permitKey) {
		this.q_permitKey = checkSet(q_permitKey);
	}
	
	public String getQ_permitNo() {
		return checkGet(q_permitNo);
	}
	public void setQ_permitNo(String q_permitNo) {
		this.q_permitNo = checkSet(q_permitNo);
	}
	
	public String getQ_chProduct() {
		return checkGet(q_chProduct);
	}
	public void setQ_chProduct(String q_chProduct) {
		this.q_chProduct = checkSet(q_chProduct);
	}
	
	public String getQ_applicationName() {
		return checkGet(q_applicationName);
	}
	public void setQ_applicationName(String q_applicationName) {
		this.q_applicationName = checkSet(q_applicationName);
	}
	public String getQ_medMainCategory() {
		return checkGet(q_medMainCategory);
	}
	public void setQ_medMainCategory(String q_medMainCategory) {
		this.q_medMainCategory = checkSet(q_medMainCategory);
	}
	public String getQ_medSecCategory() {
		return checkGet(q_medSecCategory);
	}
	public void setQ_medSecCategory(String q_medSecCategory) {
		this.q_medSecCategory = checkSet(q_medSecCategory);
	}
	public String getQ_monitorSDate() {
		return checkGet(q_monitorSDate);
	}
	public void setQ_monitorSDate(String q_monitorSDate) {
		this.q_monitorSDate = checkSet(q_monitorSDate);
	}
	public String getQ_monitorEDate() {
		return checkGet(q_monitorEDate);
	}
	public void setQ_monitorEDate(String q_monitorEDate) {
		this.q_monitorEDate = checkSet(q_monitorEDate);
	}
	
	public String getQ_trans() {
		return checkGet(q_trans);
	}
	public void setQ_trans(String q_trans) {
		this.q_trans = checkSet(q_trans);
	}
	@Override
	public Object doQueryOne() throws Exception {
		return null;
	}

	@Override
	public Object doQueryAll() throws Exception 
	{		
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		
		String hql = " from Med9001Db where 1=1 ";				
		
		CommonUser c = ServiceGetter.getInstance().getAuthenticationService().getCurrentUser();
		//廠商:同廠商的人都能看到相同廠商登錄案件
//		if("02".equals(c.getCommonDepartment().getDepartmentCode())){
//			String compreNo = View.getLookupField("select compegno from Con1005Db where id="+Common.getLong(c.getUserJob()));
//			hql += " and a.appID ="+Common.sqlChar(compreNo);		
//		}	
		
		if(!"".equals(getQ_applNoS()))
			hql += " and applNo >= " + Common.sqlChar(getQ_applNoS());
		if(!"".equals(getQ_applNoE()))
			hql += " and applNo <= " + Common.sqlChar(getQ_applNoE());
		if(!"".equals(getQ_monitorNo()))
			hql += " and monitorNo = " + Common.sqlChar(getQ_monitorNo());
		if(!"".equals(getQ_status()))
			hql += " and status = " + Common.sqlChar(getQ_status());		
		if(!"".equals(getQ_permitKey()))
			hql += " and permitKey = " + Common.sqlChar(getQ_permitKey());
		if(!"".equals(getQ_permitNo()))
			hql += " and permitNo = " + Common.sqlChar(getQ_permitNo());
		if(!"".equals(getQ_chProduct()))
			hql += " and chProduct like " + Common.sqlChar("%"+getQ_chProduct()+"%");
		if(!"".equals(getQ_applicationName()))
			hql += " and applicationName = " + Common.sqlChar(getQ_applicationName());
		if(!"".equals(getQ_medMainCategory()))
			hql += " and medMainCategory = " + Common.sqlChar(getQ_medMainCategory());
		if(!"".equals(getQ_medSecCategory()))
			hql += " and medSecCategory = " + Common.sqlChar(getQ_medSecCategory());
		if(!"".equals(getQ_monitorSDate()))
			hql += " and monitorDate >= " + Common.sqlChar(getQ_monitorSDate());
		if(!"".equals(getQ_monitorEDate()))
			hql += " and monitorDate <= " + Common.sqlChar(getQ_monitorEDate());
		
		//是否為歷史移轉資料：Y=是；N=否
		if("Y".equals(getQ_trans())) {
			hql += " and trans = 'Y'";
		}
		if("N".equals(getQ_trans())) {
			hql += " and isnull(trans,'') = ''";
		}

		System.out.println("[TCBW]-[SDRG0101Q]-[QUERYALL] : " + hql);
		
		java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(hql);
//		java.util.List objList = null;
		this.processCurrentPageAttribute(objList!=null?objList.size():0);
		
		if (getTotalRecord() > 0) 
		{
			if (getState().indexOf("query")<0) 
				ServiceGetter.getInstance().getTcbwService().getHibernateTemplate().clear();
			
			objList = ServiceGetter.getInstance().getTcbwService().load( hql + " order by id desc", this.getRecordStart(), this.getPageSize());
			
			if(objList!=null && objList.size()>0)
			{
				java.util.Map<String, String> statusMap = TCBWCommon.getCommonCodeMap("MEDRPSTATUS");
				java.util.Map<String, String> pkidMap = TCBWCommon.getCommonCodeMap("MEDPKID");
//				java.util.Map<String, String> healthMap = TCBWCommon.getMap("select id,unionName from Con1003Db");
//				java.util.Map<String, String> ecsrMap = TCBWCommon.getCommonCodeMap("DRGRECSR");
				
				for(Object dtlObj : objList)
				{
					Med9001Db dtl = (Med9001Db)dtlObj;
					
					String[] rowArray = new String[8];
					rowArray[0] = Common.get(dtl.getId());
					rowArray[1] = Common.get(dtl.getApplNo());
					rowArray[2] = Common.get(statusMap.get(dtl.getStatus()));

					rowArray[3] = Common.get(!"".equals(Common.get(dtl.getPermitKey()))?(pkidMap.get(dtl.getPermitKey())+"-"+dtl.getPermitNo()):"");
					rowArray[4] = Common.get(dtl.getChProduct());
					rowArray[5] = Common.get(dtl.getApplicationName());
					rowArray[6] = Common.get(dtl.getMonitorSDate())+"-"+Common.get(dtl.getMonitorEDate());
					rowArray[7] = Common.get(dtl.getApplNo());
//					rowArray[8] = Common.get(dtl.getPostNo());
//					rowArray[9] = !"".equals(Common.get(dtl.getMsgsource()))?ecsrMap.get(Common.get(dtl.getMsgsource())):"";
//					rowArray[10] = Common.get(dtl.getRecycledeadline());
//					rowArray[11] = !"".equals(Common.get(dtl.getHealthbureau1()))?healthMap.get(Common.get(dtl.getHealthbureau1())):"";
//					
//					rowArray[12] =  "<div class='discolor'><a href='sdrg0101f.jsp?id="+Common.get(dtl.getId())+"&caseType="+getCaseType()+"'>"+"明細"+"</a></div>";

					arrList.add(rowArray);
				}
				objList.clear();
				statusMap.clear();
				pkidMap.clear();
//				healthMap.clear();
//				ecsrMap.clear();
			}
		}
		return arrList;
	}

	@Override
	public void doCreate() throws Exception {
		
	}

	@Override
	public void doUpdate() throws Exception {
		
	}

	@Override
	public void doDelete() throws Exception {
		
	}

}
