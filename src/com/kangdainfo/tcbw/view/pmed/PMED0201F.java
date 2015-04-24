package com.kangdainfo.tcbw.view.pmed;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.model.bo.CommonDepartment;
import com.kangdainfo.common.model.bo.CommonUser;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.Datetime;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Con0001Db;
import com.kangdainfo.tcbw.model.bo.Drg8001Db;
import com.kangdainfo.tcbw.model.bo.Med0001Db;
import com.kangdainfo.tcbw.model.bo.Med4001Db;
import com.kangdainfo.tcbw.model.bo.Med4002Db;
import com.kangdainfo.tcbw.model.bo.Med9001Db;
import com.kangdainfo.tcbw.model.bo.Med9002Db;
import com.kangdainfo.tcbw.model.bo.Med9003Db;
import com.kangdainfo.tcbw.util.TCBWCommon;
import com.kangdainfo.tcbw.view.sdrg.SDRG0101F;


public class PMED0201F extends SuperBean
{
	private String id;
	private String id2;//序號	NUMERIC(19,0)	
	private String doType;	
	private String caseType;
	private String tabId;
	private String isUpload;
	private String updateType;
	private String status;              //案件狀態
	
	private String reportIssueno;		//報告-期數
	private String reporttype;			//報告-報告類別
	private String handstatus;			//報告-繳交狀態
	private String prehanddate;			//報告-預計繳交日期
	private String handdate;			//報告-實際繳交日期
	private String monitorsdateR;		//報告-本次報告監控期間起日
	private String monitoredateR;		//報告-本次報告監控期間迄日
	private String reportreceiveno;		//報告-報告收文字號
	private String reportsummary;		//報告-報告摘要
	private String inDefCaseNum;		//國內嚴重不良反應案件件數
	private String inUnDefCaseNum;		//國內非嚴重不良反應案件件數
	private String abDefCaseNum;		//國外嚴重不良反應案件件數
	private String abUnDefCaseNum;		//國外非嚴重不良反應案件件數
	private String scienceNum;			//國內外學術期刊文獻及研討會報告件數
	private String reportremark;		//報告-報告備註
	private String reportupdatedate;	//報告-修改日期
	private String reportupdateman;		//報告-修改人員
	
	//評估頁籤-補件資訊
	private String noticereupdatedate;		//報告-通知補件日期
	private String noticereupdateNo;		//報告-通知補件文號
	private String noticereupdatesummary;	//報告-通知補件摘要
	private String reupdatedate;			//報告-補件日期
	private String reupdateNo;				//報告-補件文號
	private String reupdatesummary;			//報告-補件摘要

	
	private String med9001Id;			//主檔id
	
	private String changeTabValue;
	//報告繳交清單----------------------------------------------------------------
//	private String reportIssueno[];
//	private String prehanddate[];
//	private String reporttype[];
//	private String med9002DbType[];
//	private String med9002DbTypeId[];
	
	javax.servlet.ServletRequest httpRequest;
	public javax.servlet.ServletRequest getHttpRequest() {	return httpRequest;	}
	public void setHttpRequest(javax.servlet.ServletRequest r) {	this.httpRequest = r;	}
	
	
	public void doUpdateType() throws Exception{
		ServiceGetter.getInstance().getTcbwService().getPmedDao().updateByPmed0201F(this);
	}	
	
	public void doReupdate() throws Exception{
		ServiceGetter.getInstance().getTcbwService().getPmedDao().updateByPmed0201F(this);
	}	
	

	public void saveOrUpdateMed9002Db(java.util.List<Med9002Db> saveList,java.util.List<Med9002Db> updateList,java.util.List<Med9002Db> deleteList) 
	throws Exception
	{
		ServiceGetter.getInstance().getTcbwService().deleteAndSave(deleteList, saveList);
		ServiceGetter.getInstance().getTcbwService().updateBatch(updateList);
	}
	
	
	
	@Override
	public Object doQueryOne() throws Exception {
		PMED0201F obj = this;	
		
		Med9002Db c = (Med9002Db) View.getObject(" from Med9002Db where id=" + Common.getInt(obj.getId2()));
		Med9003Db med9003 = (Med9003Db)View.getObject(" from Med9003Db where med9002Db.id = " + Common.getLong(obj.getId2()));
		
		System.out.println("[TCBW]-[PMED0201F]-[doQueryOne]- Med9002Db.id : " + obj.getId2());
		java.util.Map<String, String> reporttypeMap = TCBWCommon.getCommonCodeMap("MEDRPTYPE");
		java.util.Map<String, String> handstatusMap = TCBWCommon.getCommonCodeMap("MEDHANDTYPE");
		
		if (c!=null) {
			obj.setCaseType(getCaseType());
			
			obj.setReportIssueno(Common.get(c.getReportIssueno()));
			obj.setReporttype(Common.get(c.getReporttype()));
			obj.setHandstatus(Common.get(c.getHandstatus()));
			obj.setPrehanddate(Common.get(c.getPrehanddate()));
			obj.setHanddate(Common.get(c.getHanddate()));
			
			if(!"".equals(c.getMonitorsdate())) {
				obj.setMonitorsdateR(Common.get(c.getMonitorsdate()));
			} else {
				obj.setMonitorsdateR(Common.get(c.getMed9001Db().getMonitorSDate()));
			}
			
			if(!"".equals(c.getMonitoredate())) {
				obj.setMonitoredateR(Common.get(c.getMonitoredate()));
			} else {
				obj.setMonitoredateR(Common.get(c.getPrehanddate()));
			}
			
			obj.setReportreceiveno(Common.get(c.getReportreceiveno()));
			obj.setReportsummary(Common.get(c.getReportsummary()));
			obj.setInDefCaseNum(Common.get(c.getInDefCaseNum()));
			obj.setInUnDefCaseNum(Common.get(c.getInUnDefCaseNum()));
			obj.setAbDefCaseNum(Common.get(c.getAbDefCaseNum()));
			obj.setAbUnDefCaseNum(Common.get(c.getAbUnDefCaseNum()));
			obj.setScienceNum(Common.get(c.getScienceNum()));
			obj.setReportremark(Common.get(c.getReportremark()));
			obj.setReportupdatedate(Common.get(c.getModifyDate()));
			obj.setReportupdateman(Common.get(c.getModifier()));
//			obj.setAssessdate(Common.get(c.getAssessdate()));
//			obj.setAssesssummary(Common.get(c.getAssesssummary()));
//			obj.setAssessresult(Common.get(c.getAssessresult()));
//			obj.setAssessresultdesc(Common.get(c.getAssessresultdesc()));
//			obj.setAssessremark(Common.get(c.getAssessremark()));
			
			obj.setStatus(Common.get(c.getMed9001Db().getStatus()));
			
			obj.setMed9001Id(Common.get(c.getMed9001Db().getId()));
			if(med9003!=null) {
				obj.setNoticereupdateNo(Common.get(med9003.getNoticereupdateNo()));
				obj.setNoticereupdatedate(Common.get(med9003.getNoticereupdatedate()));
				obj.setNoticereupdatesummary(Common.get(med9003.getNoticereupdatesummary()));
				obj.setReupdateNo(Common.get(med9003.getReupdateNo()));
				obj.setReupdatedate(Common.get(med9003.getReupdatedate()));
				obj.setReupdatesummary(Common.get(med9003.getReupdatesummary()));
			}
			
			
			
		}
		reporttypeMap.clear();
		handstatusMap.clear();
		return obj;

	}
	
	//報告附件
	public String getAddFileMed050002() throws Exception 
	{
		StringBuilder sb = new StringBuilder(1024); 
		
//		String status = View.getLookupField("select status from Med9002Db where id="+Common.getLong(getId2()));
		
		String hql = " from Con0001Db where fileKind='MED' and systemType like 'MED050002' and dbName='MED9001DB' and upLoadId="+Common.sqlChar(getId2());
		java.util.List objList = ServiceGetter.getInstance().getCommonService().load(hql+" order by id asc");
		if(objList!=null && objList.size()>0)
		{
			java.util.Iterator it = objList.iterator();
			int i=0;
			while (it.hasNext()) 
			{
				Con0001Db o = (Con0001Db) it.next();
				String attFile = Common.get(o.getFileRoute())+":;:"+Common.get(o.getFileName());
				String prop="";
			    prop=prop+"toolbar=no;location=no,directories=no,menubar=no,status=no,scrollbars=yes,resizable=yes,";
			    prop=prop+"width=450,";
			    prop=prop+"height=160";
				
				sb.append("<tr>\n");
				sb.append("<td style='text-align:center'>").append((i+1)).append("</td>");
				sb.append("<td style='text-align:left'>");			
				sb.append("<a class=\"text_link_b\" href=\"../../downloadFileSimple?fileType=MED&fileID=").append(attFile).append("\">");
				sb.append(o.getFileName());
				sb.append("</a></td>\n");				
				sb.append("<td >").append(o.getFileExplan()).append("</td>");
				sb.append("<td style='text-align:center'>");			
				sb.append("<a class=\"text_link_b\" onclick=\"deleteFileSimple("+o.getId()+");\">").append("刪除檔案</a>");	
				sb.append("</td>\n");
				
				i++;
			}
		}
		return sb.toString(); 
	}
	
	//通知補件附件
	public String getAddFileMed050003() throws Exception 
	{
		StringBuilder sb = new StringBuilder(1024); 
		
//		String status = View.getLookupField("select status from Med9002Db where id="+Common.getLong(getId2()));
		
		String hql = " from Con0001Db where fileKind='MED' and systemType like 'MED050003' and dbName='MED9002DB' and upLoadId="+Common.sqlChar(getId2());
		java.util.List objList = ServiceGetter.getInstance().getCommonService().load(hql+" order by id asc");
		if(objList!=null && objList.size()>0)
		{
			java.util.Iterator it = objList.iterator();
			int i=0;
			while (it.hasNext()) 
			{
				Con0001Db o = (Con0001Db) it.next();
				String attFile = Common.get(o.getFileRoute())+":;:"+Common.get(o.getFileName());
				String prop="";
			    prop=prop+"toolbar=no;location=no,directories=no,menubar=no,status=no,scrollbars=yes,resizable=yes,";
			    prop=prop+"width=450,";
			    prop=prop+"height=160";
				
				sb.append("<tr>\n");
				sb.append("<td style='text-align:center'>").append((i+1)).append("</td>");
				sb.append("<td style='text-align:left'>");			
				sb.append("<a class=\"text_link_b\" href=\"../../downloadFileSimple?fileType=MED&fileID=").append(attFile).append("\">");
				sb.append(o.getFileName());
				sb.append("</a></td>\n");				
				sb.append("<td >").append(o.getFileExplan()).append("</td>");
				sb.append("<td style='text-align:center'>");			
				sb.append("<a class=\"text_link_b\" onclick=\"deleteFileSimple("+o.getId()+");\">").append("刪除檔案</a>");	
				sb.append("</td>\n");
				
				i++;
			}
		}
		return sb.toString(); 
	}
	
	//補件附件
	public String getAddFileMed050004() throws Exception 
	{
		StringBuilder sb = new StringBuilder(1024); 
		
//		String status = View.getLookupField("select status from Med9002Db where id="+Common.getLong(getId2()));
		
		String hql = " from Con0001Db where fileKind='MED' and systemType like 'MED050004' and dbName='MED9002DB' and upLoadId="+Common.sqlChar(getId2());
		java.util.List objList = ServiceGetter.getInstance().getCommonService().load(hql+" order by id asc");
		if(objList!=null && objList.size()>0)
		{
			java.util.Iterator it = objList.iterator();
			int i=0;
			while (it.hasNext()) 
			{
				Con0001Db o = (Con0001Db) it.next();
				String attFile = Common.get(o.getFileRoute())+":;:"+Common.get(o.getFileName());
				String prop="";
			    prop=prop+"toolbar=no;location=no,directories=no,menubar=no,status=no,scrollbars=yes,resizable=yes,";
			    prop=prop+"width=450,";
			    prop=prop+"height=160";
				
				sb.append("<tr>\n");
				sb.append("<td style='text-align:center'>").append((i+1)).append("</td>");
				sb.append("<td style='text-align:left'>");			
				sb.append("<a class=\"text_link_b\" href=\"../../downloadFileSimple?fileType=MED&fileID=").append(attFile).append("\">");
				sb.append(o.getFileName());
				sb.append("</a></td>\n");				
				sb.append("<td >").append(o.getFileExplan()).append("</td>");
				sb.append("<td style='text-align:center'>");			
				sb.append("<a class=\"text_link_b\" onclick=\"deleteFileSimple("+o.getId()+");\">").append("刪除檔案</a>");	
				sb.append("</td>\n");
				
				i++;
			}
		}
		return sb.toString(); 
	}

	@Override
	public Object doQueryAll() throws Exception 
	{		
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		
		String hql = "select distinct b from Med9001Db a left join a.med9002Dbs b left join b.med9003Dbs c where 1=1 and a.id=b.med9001Db.id";				
		hql += " and b.med9001Db.id = " + Common.getInt(getId());
		
		CommonUser c = ServiceGetter.getInstance().getAuthenticationService().getCurrentUser();
		//廠商:同廠商的人都能看到相同廠商登錄案件
//		if("02".equals(c.getCommonDepartment().getDepartmentCode())){
//			String compreNo = View.getLookupField("select compegno from Con1005Db where id="+Common.getLong(c.getUserJob()));
//			hql += " and a.appID ="+Common.sqlChar(compreNo);		
//		}	
		
		
		System.out.println("[TCBW]-[PMED0201F]-[QUERYALL] : " + hql);
		
		java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(hql);
		this.processCurrentPageAttribute(objList!=null?objList.size():0);
		
		if (getTotalRecord() > 0) 
		{
			if (getState().indexOf("query")<0) 
				ServiceGetter.getInstance().getTcbwService().getHibernateTemplate().clear();
			
			objList = ServiceGetter.getInstance().getTcbwService().load( hql + " order by b.prehanddate,reporttype");
			
			if(objList!=null && objList.size()>0)
			{
				java.util.Map<String, String> reporttypeMap = TCBWCommon.getCommonCodeMap("MEDRPTYPE");//報告類別
				java.util.Map<String, String> pkidMap = TCBWCommon.getCommonCodeMap("MEDPKID");//許可證字軌
				java.util.Map<String, String> handstatusMap = TCBWCommon.getCommonCodeMap("MEDHANDTYPE");//報告繳交狀態

				
				for(Object dtlObj : objList)
				{
					Med9002Db dtl = (Med9002Db)dtlObj;
					
					String[] rowArray = new String[10];
					
					rowArray[0] = Common.get(dtl.getId());
					
					if("0".equals(Common.get(dtl.getReportIssueno()))) {
						rowArray[1] = "";
					} else {
						rowArray[1] = Common.get(dtl.getReportIssueno());
					}
					rowArray[2] = Common.get(reporttypeMap.get(dtl.getReporttype()));
					rowArray[3] = Common.get(dtl.getReportreceiveno());
					rowArray[4] = Common.get(dtl.getReportsummary());
					rowArray[5] = Common.get(dtl.getPrehanddate());
					rowArray[6] = Common.get(dtl.getHanddate());
					rowArray[7] = "";
					if(dtl.getHandstatus() != null && !"".equals(dtl.getHandstatus())) {
						String[] handstatus = dtl.getHandstatus().split(",");
						for(int i = 0; i < handstatus.length; i++) {
							rowArray[7] += Common.get(handstatusMap.get(handstatus[i]));
						}
					}
					rowArray[8] = Common.get(dtl.getMed9001Db().getApplNo());
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
	public String deleteCon0001DbByPmed0101(Long id) throws Exception {
		//刪除全部檔案
		String hql = " from Con0001Db where fileKind='MED' and systemType like 'MED05%' and dbName='MED9001DB' and upLoadId="+Common.getLong(id);		
		java.util.List objList = ServiceGetter.getInstance().getCommonService().load(hql+" order by id asc");		
		if(objList!=null && objList.size()>0)		
		{			
			java.util.Iterator it = objList.iterator();			
			while (it.hasNext())			
			{				
				Con0001Db o = (Con0001Db) it.next();				
				ServiceGetter.getInstance().getTcbwService().getConinDao().deleteCon0001Db(o.getId());			
			}		
		}		
		return null;
	}
	
	public String getId() {
		return checkGet(id);
	}
	public void setId(String id) {
		this.id = checkSet(id);
	}
	public String getId2() {		return id2;	}
	public void setId2(String id2) {		this.id2 = id2;	}
	public String getDoType() {return checkGet(doType);}
	public void setDoType(String doType) {this.doType = checkSet(doType);}
	public String getCaseType() {return checkGet(caseType);}
	public void setCaseType(String caseType) {this.caseType = checkSet(caseType);}
	public String getTabId() {return checkGet(tabId);}
	public void setTabId(String tabId) {this.tabId = checkSet(tabId);}
	public String getUpdateType() {		return checkGet(updateType);	}
	public void setUpdateType(String updateType) {		this.updateType = checkSet(updateType);	}	
	public String getIsUpload() {	return checkGet(isUpload);	}
	public void setIsUpload(String isUpload) {	this.isUpload = checkSet(isUpload);	}
	
	public String getStatus() {
		return checkGet(status);
	}
	public void setStatus(String status) {
		this.status = checkSet(status);
	}
	public String getReportIssueno() {
		return checkGet(reportIssueno);
	}
	public void setReportIssueno(String reportIssueno) {
		this.reportIssueno = checkSet(reportIssueno);
	}
	public String getReporttype() {
		return checkGet(reporttype);
	}
	public void setReporttype(String reporttype) {
		this.reporttype = checkSet(reporttype);
	}
	public String getHandstatus() {
		return checkGet(handstatus);
	}
	public void setHandstatus(String handstatus) {
		this.handstatus = checkSet(handstatus);
	}
	public String getPrehanddate() {
		return checkGet(prehanddate);
	}
	public void setPrehanddate(String prehanddate) {
		this.prehanddate = checkSet(prehanddate);
	}
	public String getHanddate() {
		return checkGet(handdate);
	}
	public void setHanddate(String handdate) {
		this.handdate = checkSet(handdate);
	}
	public String getMonitorsdateR() {
		return checkGet(monitorsdateR);
	}
	public void setMonitorsdateR(String monitorsdateR) {
		this.monitorsdateR = checkSet(monitorsdateR);
	}
	public String getMonitoredateR() {
		return checkGet(monitoredateR);
	}
	public void setMonitoredateR(String monitoredateR) {
		this.monitoredateR = checkSet(monitoredateR);
	}
	public String getReportreceiveno() {
		return checkGet(reportreceiveno);
	}
	public void setReportreceiveno(String reportreceiveno) {
		this.reportreceiveno = checkSet(reportreceiveno);
	}
	public String getReportsummary() {
		return checkGet(reportsummary);
	}
	public void setReportsummary(String reportsummary) {
		this.reportsummary = checkSet(reportsummary);
	}
	public String getInDefCaseNum() {
		return checkGet(inDefCaseNum);
	}
	public void setInDefCaseNum(String inDefCaseNum) {
		this.inDefCaseNum = checkSet(inDefCaseNum);
	}
	public String getInUnDefCaseNum() {
		return checkGet(inUnDefCaseNum);
	}
	public void setInUnDefCaseNum(String inUnDefCaseNum) {
		this.inUnDefCaseNum = checkSet(inUnDefCaseNum);
	}
	public String getAbDefCaseNum() {
		return checkGet(abDefCaseNum);
	}
	public void setAbDefCaseNum(String abDefCaseNum) {
		this.abDefCaseNum = checkSet(abDefCaseNum);
	}
	public String getAbUnDefCaseNum() {
		return checkGet(abUnDefCaseNum);
	}
	public void setAbUnDefCaseNum(String abUnDefCaseNum) {
		this.abUnDefCaseNum = checkSet(abUnDefCaseNum);
	}
	public String getScienceNum() {
		return checkGet(scienceNum);
	}
	public void setScienceNum(String scienceNum) {
		this.scienceNum = checkSet(scienceNum);
	}
	public String getReportremark() {
		return checkGet(reportremark);
	}
	public void setReportremark(String reportremark) {
		this.reportremark = checkSet(reportremark);
	}
	public String getReportupdatedate() {
		return checkGet(reportupdatedate);
	}
	public void setReportupdatedate(String reportupdatedate) {
		this.reportupdatedate = checkSet(reportupdatedate);
	}
	public String getReportupdateman() {
		return checkGet(reportupdateman);
	}
	public void setReportupdateman(String reportupdateman) {
		this.reportupdateman = checkSet(reportupdateman);
	}
	
	public String getNoticereupdatedate() {
		return checkGet(noticereupdatedate);
	}
	public void setNoticereupdatedate(String noticereupdatedate) {
		this.noticereupdatedate = checkSet(noticereupdatedate);
	}
	public String getNoticereupdateNo() {
		return checkGet(noticereupdateNo);
	}
	public void setNoticereupdateNo(String noticereupdateNo) {
		this.noticereupdateNo = checkSet(noticereupdateNo);
	}
	public String getNoticereupdatesummary() {
		return checkGet(noticereupdatesummary);
	}
	public void setNoticereupdatesummary(String noticereupdatesummary) {
		this.noticereupdatesummary = checkSet(noticereupdatesummary);
	}
	public String getReupdatedate() {
		return checkGet(reupdatedate);
	}
	public void setReupdatedate(String reupdatedate) {
		this.reupdatedate = checkSet(reupdatedate);
	}
	public String getReupdateNo() {
		return checkGet(reupdateNo);
	}
	public void setReupdateNo(String reupdateNo) {
		this.reupdateNo = checkSet(reupdateNo);
	}
	public String getReupdatesummary() {
		return checkGet(reupdatesummary);
	}
	public void setReupdatesummary(String reupdatesummary) {
		this.reupdatesummary = checkSet(reupdatesummary);
	}
	//	public String getAssessdate() {
//		return checkGet(assessdate);
//	}
//	public void setAssessdate(String assessdate) {
//		this.assessdate = checkSet(assessdate);
//	}
//	public String getAssesssummary() {
//		return checkGet(assesssummary);
//	}
//	public void setAssesssummary(String assesssummary) {
//		this.assesssummary = checkSet(assesssummary);
//	}
//	public String getAssessresult() {
//		return checkGet(assessresult);
//	}
//	public void setAssessresult(String assessresult) {
//		this.assessresult = checkSet(assessresult);
//	}
//	public String getAssessresultdesc() {
//		return checkGet(assessresultdesc);
//	}
//	public void setAssessresultdesc(String assessresultdesc) {
//		this.assessresultdesc = checkSet(assessresultdesc);
//	}
//	public String getAssessremark() {
//		return checkGet(assessremark);
//	}
//	public void setAssessremark(String assessremark) {
//		this.assessremark = checkSet(assessremark);
//	}
	public String getChangeTabValue() {
		return checkGet(changeTabValue);
	}
	public void setChangeTabValue(String changeTabValue) {
		this.changeTabValue = checkSet(changeTabValue);
	}
	public String getMed9001Id() {
		return checkGet(med9001Id);
	}
	public void setMed9001Id(String med9001Id) {
		this.med9001Id = checkSet(med9001Id);
	}
	
	

}
