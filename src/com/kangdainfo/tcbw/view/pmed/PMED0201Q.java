package com.kangdainfo.tcbw.view.pmed;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.model.bo.CommonUser;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Hfr0002Db;
import com.kangdainfo.tcbw.model.bo.Med0001Db;
import com.kangdainfo.tcbw.model.bo.Med9001Db;
import com.kangdainfo.tcbw.model.bo.Med9002Db;
import com.kangdainfo.tcbw.util.TCBWCommon;


public class PMED0201Q extends SuperBean
{
	private String doType;
	public String getDoType() {return checkGet(doType);}
	public void setDoType(String doType) {this.doType = checkSet(doType);}
	
	String caseType ;
	public String getCaseType() {return checkGet(caseType);}
	public void setCaseType(String caseType) {this.caseType = checkSet(caseType);}
	
	
	
	private String id;
	private String id2;
	private String q_applNoS;	//案號起號
	private String q_applNoE;	//案號迄號
	private String q_permitKey;	//許可證字軌
	private String q_permitNo;	//許可證字號
	private String q_monitorSDate;	//監控期間起日
	private String q_monitorEDate;	//監控期間迄日
	private String q_reporttype;	//報告類別
	private String[] q_handstatus;	//繳交狀態
	private String q_prehanddateS;	//預計繳交日期起日
	private String q_prehanddateE;	//預計繳交日期迄日
	private String q_handdateS;	//實際繳交日期起日
	private String q_handdateE;	//實際繳交日期迄日
	private String q_reportreceiveno;	//報告收文字號
	private String q_noticereupdateNo;	//通知補件文號
	private String q_reupdateNo;	//補件文號
	private String q_assessresult;	//評估結果
	
	
	
	public String getId() {
		return checkGet(id);
	}
	public void setId(String id) {
		this.id = checkSet(id);
	}
	
	public String getId2() {
		return checkGet(id2);
	}
	public void setId2(String id2) {
		this.id2 = checkSet(id2);
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
	
	public String getQ_assessresult() {
		return checkGet(q_assessresult);
	}
	public void setQ_assessresult(String q_assessresult) {
		this.q_assessresult = checkSet(q_assessresult);
	}
	
	public String getQ_reporttype() {
		return checkGet(q_reporttype);
	}
	public void setQ_reporttype(String q_reporttype) {
		this.q_reporttype = checkSet(q_reporttype);
	}
	public String[] getQ_handstatus() {
		return checkGet(q_handstatus);
	}
	public void setQ_handstatus(String[] q_handstatus) {
		this.q_handstatus = checkSet(q_handstatus);
	}
	public String getQ_prehanddateS() {
		return checkGet(q_prehanddateS);
	}
	public void setQ_prehanddateS(String q_prehanddateS) {
		this.q_prehanddateS = checkSet(q_prehanddateS);
	}
	public String getQ_prehanddateE() {
		return checkGet(q_prehanddateE);
	}
	public void setQ_prehanddateE(String q_prehanddateE) {
		this.q_prehanddateE = checkSet(q_prehanddateE);
	}
	public String getQ_handdateS() {
		return checkGet(q_handdateS);
	}
	public void setQ_handdateS(String q_handdateS) {
		this.q_handdateS = checkSet(q_handdateS);
	}
	public String getQ_handdateE() {
		return checkGet(q_handdateE);
	}
	public void setQ_handdateE(String q_handdateE) {
		this.q_handdateE = checkSet(q_handdateE);
	}
	public String getQ_reportreceiveno() {
		return checkGet(q_reportreceiveno);
	}
	public void setQ_reportreceiveno(String q_reportreceiveno) {
		this.q_reportreceiveno = checkSet(q_reportreceiveno);
	}
	public String getQ_noticereupdateNo() {
		return checkGet(q_noticereupdateNo);
	}
	public void setQ_noticereupdateNo(String q_noticereupdateNo) {
		this.q_noticereupdateNo = checkSet(q_noticereupdateNo);
	}
	public String getQ_reupdateNo() {
		return checkGet(q_reupdateNo);
	}
	public void setQ_reupdateNo(String q_reupdateNo) {
		this.q_reupdateNo = checkSet(q_reupdateNo);
	}
	public void setQ_permitKey(String q_permitKey) {
		this.q_permitKey = checkSet(q_permitKey);
	}
	@Override
	public Object doQueryOne() throws Exception {
		return null;
	}

	@Override
	public Object doQueryAll() throws Exception 
	{		
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		
		String compegno=View.getLookupField(" select con1005Db.compegno from Con1006Db where commonUser.userId="+ Common.sqlChar(TCBWCommon.getCurrentUserId()));
		
		String hql = "select distinct b from Med9001Db a left join a.med9002Dbs b left join b.med9003Dbs c where 1=1 and a.id=b.med9001Db.id and b.handstatus = '01' and a.applNo <> ''";				
		
		//廠商:同廠商的人都能看到相同廠商登錄案件
		if(!"in".equals(TCBWCommon.getCurrentInORout())){
			hql += " and a.applicationID= " + Common.sqlChar(compegno);
		}
		
		CommonUser c = ServiceGetter.getInstance().getAuthenticationService().getCurrentUser();
		
		if(!"".equals(getQ_applNoS()))
			hql += " and applNo >= " + Common.sqlChar(getQ_applNoS());
		if(!"".equals(getQ_applNoE()))
			hql += " and applNo <= " + Common.sqlChar(getQ_applNoE());		
		if(!"".equals(getQ_permitKey()))
			hql += " and permitKey = " + Common.sqlChar(getQ_permitKey());
		if(!"".equals(getQ_permitNo()))
			hql += " and permitNo = " + Common.sqlChar(getQ_permitNo());
		if(!"".equals(getQ_monitorSDate()))
			hql += " and monitorDate >= " + Common.sqlChar(getQ_monitorSDate());
		if(!"".equals(getQ_monitorEDate()))
			hql += " and monitorDate <= " + Common.sqlChar(getQ_monitorEDate());
		if(!"".equals(getQ_reporttype()))
			hql += " and b.reporttype = " + Common.sqlChar(getQ_reporttype());
		if(null!=getQ_handstatus() && getQ_handstatus().length > 0) {
			hql += " and b.handstatus in(";
			for(int i = 0; i < getQ_handstatus().length; i++) {
				hql += Common.get(getQ_handstatus()[i]);
				if(i < getQ_handstatus().length - 1) {
					hql += ",";
				}
			}
			hql += ")";
		}
		if(!"".equals(getQ_prehanddateS()))
			hql += " and b.prehanddate >= " + Common.get(getQ_prehanddateS());
		if(!"".equals(getQ_prehanddateE()))
			hql += " and b.prehanddate <= " + Common.get(getQ_prehanddateE());
		if(!"".equals(getQ_handdateS()))
			hql += " and b.handdate >= " + Common.get(getQ_handdateS());
		if(!"".equals(getQ_handdateE()))
			hql += " and b.handdate <= " + Common.get(getQ_handdateE());
		if(!"".equals(getQ_reportreceiveno()))
			hql += " and b.reportreceiveno = " + Common.get(getQ_reportreceiveno());
		if(!"".equals(getQ_noticereupdateNo()))
			hql += " and c.noticereupdateNo = " + Common.get(getQ_noticereupdateNo());
		if(!"".equals(getQ_reupdateNo()))
			hql += " and c.reupdateNo = " + Common.get(getQ_reupdateNo());
		if(!"".equals(getQ_assessresult()))
			hql += " and b.assessresult = " + Common.get(getQ_assessresult());
		
			hql +="order by prehanddate,reporttype asc";
		System.out.println("[TCBW]-[PMED0201Q]-[QUERYALL] : " + hql);
		
		java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(hql);
		this.processCurrentPageAttribute(objList!=null?objList.size():0);
		
		if (getTotalRecord() > 0) 
		{
			if (getState().indexOf("query")<0) 
				ServiceGetter.getInstance().getTcbwService().getHibernateTemplate().clear();
			
			objList = ServiceGetter.getInstance().getTcbwService().load( hql + " order by b.prehanddate", this.getRecordStart(), this.getPageSize());
			
			if(objList!=null && objList.size()>0)
			{
				java.util.Map<String, String> reporttypeMap = TCBWCommon.getCommonCodeMap("MEDRPTYPE");//報告類別
				java.util.Map<String, String> pkidMap = TCBWCommon.getCommonCodeMap("MEDPKID");//許可證字軌
				java.util.Map<String, String> handstatusMap = TCBWCommon.getCommonCodeMap("MEDHANDTYPE");//報告繳交狀態

				
				for(Object dtlObj : objList)
				{
					Med9002Db dtl = (Med9002Db)dtlObj;
					
					String[] rowArray = new String[11];
					
					rowArray[0] = Common.get(dtl.getMed9001Db().getId());
					rowArray[1] = Common.get(dtl.getId());
					rowArray[2] = Common.get(dtl.getMed9001Db().getApplNo());
					rowArray[3] = Common.get(!"".equals(Common.get(dtl.getMed9001Db().getPermitKey()))?(pkidMap.get(dtl.getMed9001Db().getPermitKey())+"-"+dtl.getMed9001Db().getPermitNo()):"");
					rowArray[4] = Common.get(dtl.getMed9001Db().getChProduct());
					
					if("0".equals(Common.get(dtl.getReportIssueno()))) {
						rowArray[5] = "";
					} else {
						rowArray[5] = Common.get(dtl.getReportIssueno());
					}

					rowArray[6] = Common.get(reporttypeMap.get(dtl.getReporttype()));
					rowArray[7] = "";
					if(dtl.getHandstatus() != null && !"".equals(dtl.getHandstatus())) {
						String[] handstatus = dtl.getHandstatus().split(",");
						for(int i = 0; i < handstatus.length; i++) {
							rowArray[7] += Common.get(handstatusMap.get(handstatus[i]));
						}
					}
					rowArray[8] = Common.get(dtl.getPrehanddate());
					rowArray[9] = Common.get(dtl.getHanddate());
					rowArray[10] = Common.get(dtl.getMed9001Db().getApplNo());
					arrList.add(rowArray);
				}
				objList.clear();
				reporttypeMap.clear();
				pkidMap.clear();
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
