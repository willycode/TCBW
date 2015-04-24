package com.kangdainfo.tcbw.view.pmed;

import java.util.Iterator;
import java.util.List;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.model.bo.CommonDepartment;
import com.kangdainfo.common.model.bo.CommonUser;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.Datetime;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Con0001Db;
import com.kangdainfo.tcbw.model.bo.Drg8001Db;
import com.kangdainfo.tcbw.model.bo.Drg8002Db;
import com.kangdainfo.tcbw.model.bo.Drg8005Db;
import com.kangdainfo.tcbw.model.bo.Med0001Db;
import com.kangdainfo.tcbw.model.bo.Med4001Db;
import com.kangdainfo.tcbw.model.bo.Med4002Db;
import com.kangdainfo.tcbw.model.bo.Med7001Db;
import com.kangdainfo.tcbw.model.bo.Med9001Db;
import com.kangdainfo.tcbw.model.bo.Med9002Db;
import com.kangdainfo.tcbw.util.TCBWCommon;
import com.kangdainfo.tcbw.view.sdrg.SDRG0101F;


public class PMED0301F extends SuperBean
{
	private String id;
	private String id2;
//	private String id3;
	private String doType;	
	private String caseType;
	private String tabId;
	private String isUpload;
	private String updateType;
	private String status;              //案件狀態
	
	private String monitorSDate0301;	//案件監控期間起日
	
	private String reportIssueno;		//報告-期數
	private String reporttype;			//報告-報告類別
	private String handstatus;			//報告-繳交狀態
	private String prehanddate;			//報告-預計繳交日期
	private String handdate;			//報告-實際繳交日期
	private String reportreceiveno;		//報告-報告收文字號
	private String reportsummary;		//報告-報告摘要
	
	private String outVmedNum;			//本次報告監控期間國外警訊件數
	private String inUnReacNum;			//本次報告監控期間國內不良反應通報件數
	private String inDefProNum;			//本次報告監控期間國內不良品通報件數
	
	private String assessdate;			//評估-評估日期
	private String assesssummary;		//評估-評估摘要
	private String assessresult;		//評估-評估結果
	private String assessresultdesc;	//評估-評估結果說明
	private String assessremark;		//評估-評估備註
	
	private String isNotHand;			//是否有尚未繳交的定期報告
	
	private String med9001Id;			//主檔id

	private String reportType;			//報表類別
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
	
	//暫存、評估完成
	public void doUpdateType() throws Exception{
		ServiceGetter.getInstance().getTcbwService().getPmedDao().updateByPmed0301F(this);
	}	
	
	//補件
	public void doAddocuments() throws Exception{
		ServiceGetter.getInstance().getTcbwService().getPmedDao().updateByDoAddocuments(this);
	}
	
	//列印提會單
	public Object[] getDefaultTableModelForWord() throws Exception
	{
		Object[] rowArray = null;		//word 替換欄位數
		List list = ServiceGetter.getInstance().getCommonService().load(" from Med9002Db where id = " + getId2());
		if(list!=null && list.size() > 0){
			java.util.Map<String, String> pkidMap = TCBWCommon.getCommonCodeMap("MEDPKID");			
			Iterator it = list.iterator();
			while(it.hasNext()){
				Med9002Db o = (Med9002Db) it.next();
				//回收通知函
				if("01".equals(getReportType())){   
					rowArray = new String[15];		//word 替換欄位數
					rowArray[0] = Common.get(o.getMed9001Db().getMonitorEDate().substring(0,3));
					rowArray[1] = Common.get(o.getMed9001Db().getChProduct());		 	 //##2##中文品名
					rowArray[2] = Common.get(o.getMed9001Db().getApplicationName());     //##3##許可證持有商
					rowArray[3] = Common.get(!"".equals(Common.get(o.getMed9001Db().getPermitKey()))?(pkidMap.get(o.getMed9001Db().getPermitKey())+"-"):"")+o.getMed9001Db().getPermitNo();//##4##許可證字號			
					rowArray[4] = Common.get(o.getMed9001Db().getMedeffect());		     //##5##醫材效能					
					rowArray[5] = Common.formatYYYMMDD(Common.get(o.getMed9001Db().getMonitorSDate()), 2);//##6##案件監控期間起日	
					String year = "";
					year = Datetime.getDateDiff("y",Common.get(o.getMed9001Db().getMonitorSDate()), Common.get(o.getMed9001Db().getMonitorEDate()));
					rowArray[6] = year;													//監控期間年數
					rowArray[7] = Common.get(o.getInDefCaseNum());						//國內嚴重不良反應件數
					rowArray[8] = Common.get(o.getInUnDefCaseNum());					//國內非嚴重不良反應件數
					rowArray[9] = Common.get(o.getAbDefCaseNum());						//國外嚴重不良反應件數
					rowArray[10] = Common.get(o.getAbUnDefCaseNum());					//國外非嚴重不良反應件數
					rowArray[11] = Common.get(o.getScienceNum());						//相關國外期刊件數
					rowArray[12] = Common.formatYYYMMDD(o.getMed9001Db().getMonitorEDate(),2);		//案件監控期間迄日
					rowArray[13] = CalInUnReacNum(Common.get(o.getMed9001Db().getMonitorSDate()),Common.get(o.getPrehanddate()),Common.get(o.getMed9001Db().getPermitKey())+Common.get(o.getMed9001Db().getPermitNo()));//國內不良反應通報件數
					rowArray[14] = CalInDefProNum(Common.get(o.getMed9001Db().getMonitorSDate()),Common.get(o.getPrehanddate()),Common.get(o.getMed9001Db().getPermitKey())+Common.get(o.getMed9001Db().getPermitNo()));//國內不良品通報件數
				}
			}
		}
		return rowArray;
	}

	@Override
	public Object doQueryOne() throws Exception {
		PMED0301F obj = this;	
		
		Med9002Db c = (Med9002Db) View.getObject(" from Med9002Db where id=" + Common.getInt(obj.getId2()));
		
		System.out.println("[TCBW]-[PMED0201F]-[doQueryOne]- Med9002Db.id : " + obj.getId2());
		java.util.Map<String, String> reporttypeMap = TCBWCommon.getCommonCodeMap("MEDRPTYPE");
		java.util.Map<String, String> handstatusMap = TCBWCommon.getCommonCodeMap("MEDHANDTYPE");
		
		if (c!=null) {
			obj.setMonitorSDate0301(Common.get(c.getMed9001Db().getMonitorSDate()));//案件監控期間起日
			obj.setReportIssueno(Common.get(c.getReportIssueno()));
			obj.setReporttype(Common.get(c.getReporttype()));
			obj.setHandstatus(Common.get(c.getHandstatus()));
			obj.setPrehanddate(Common.get(c.getPrehanddate()));
			obj.setHanddate(Common.get(c.getHanddate()));
			obj.setReportreceiveno(Common.get(c.getReportreceiveno()));
			obj.setReportsummary(Common.get(c.getReportsummary()));
			
			//計算本次報告監控期間國外警訊件數
			obj.setOutVmedNum(CalOutVmedNum(Common.get(c.getMed9001Db().getMonitorSDate()),Common.get(c.getPrehanddate()),Common.get(c.getMed9001Db().getPermitKey())+Common.get(c.getMed9001Db().getPermitNo())));
			//計算本次報告監控期間國內不良反應通報件數
			obj.setInUnReacNum(CalInUnReacNum(Common.get(c.getMed9001Db().getMonitorSDate()),Common.get(c.getPrehanddate()),Common.get(c.getMed9001Db().getPermitKey())+Common.get(c.getMed9001Db().getPermitNo())));
			//計算本次報告監控期間國內不良品通報件數
			obj.setInDefProNum(CalInDefProNum(Common.get(c.getMed9001Db().getMonitorSDate()),Common.get(c.getPrehanddate()),Common.get(c.getMed9001Db().getPermitKey())+Common.get(c.getMed9001Db().getPermitNo())));
			
			obj.setAssessdate(Common.get(c.getAssessdate()));
			obj.setAssesssummary(Common.get(c.getAssesssummary()));
			obj.setAssessresult(Common.get(c.getAssessresult()));
			obj.setAssessresultdesc(Common.get(c.getAssessresultdesc()));
			obj.setAssessremark(Common.get(c.getAssessremark()));
			
			obj.setStatus(Common.get(c.getMed9001Db().getStatus()));
			
			obj.setMed9001Id(Common.get(c.getMed9001Db().getId()));
			
			obj.setIsNotHand(CalIsNotHand(c.getId()));	//判斷是否有未繳交的定期報告
		}
		reporttypeMap.clear();
		handstatusMap.clear();
		return obj;

	}
	
	//計算本次報告監控期間國外警訊件數
	public String CalOutVmedNum(String sDate,String eDate,String permit) throws Exception 
	{
		String sql = " from Med7001Db a left join a.med7002Dbs b where 1=1 and a.id=b.med7001Db.id";
			   sql += " and publDate >= " + Common.sqlChar(sDate);
			   sql += " and publDate <= " + Common.sqlChar(eDate);
			   sql += " and isnull(applNo,'') <> ''";
			   sql += " and b.permitKey+b.permitNo = " + Common.sqlChar(permit);
		java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(sql);

		if(null!=objList && objList.size() > 0) {
			return Common.get(objList.size());
		}
		else {
			return Common.get(0);
		}
	}
	
	//計算本次報告監控期間國內不良反應通報件數
	public String CalInUnReacNum(String sDate,String eDate,String permit) throws Exception 
	{
		String sql = " from Med0001Db where 1=1 ";
			   sql += " and notifierRepDate >= " + Common.sqlChar(sDate);
			   sql += " and notifierRepDate <= " + Common.sqlChar(eDate);
			   sql += " and isnull(applNo,'') <> ''";
			   sql += " and medPermit+medPermitNumber = " + Common.sqlChar(permit);
			   sql += " and caseSource <> 'out'";
			   sql += " and eventKind='1'";
		java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(sql);

		if(null!=objList && objList.size() > 0) {
			return Common.get(objList.size());
		}
		else {
			return Common.get(0);
		}
	}
	
	//計算本次報告監控期間國內不良品通報件數
	public String CalInDefProNum(String sDate,String eDate,String permit) throws Exception 
	{
		String sql = " from Med0001Db where 1=1 ";
		   	   sql += " and notifierRepDate >= " + Common.sqlChar(sDate);
		   	   sql += " and notifierRepDate <= " + Common.sqlChar(eDate);
		   	   sql += " and isnull(applNo,'') <> ''";
		   	   sql += " and medPermit+medPermitNumber = " + Common.sqlChar(permit);
		   	   sql += " and isnull(caseSource,'')<>'out'";
		   	   sql += " and eventKind='2'";
		java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(sql);

		if(null!=objList && objList.size() > 0) {
			return Common.get(objList.size());
		}
		else {
			return Common.get(0);
		}
	}
	
	//判斷是否有未繳交的定期報告
	public String CalIsNotHand(Long id) throws Exception 
	{
		String sql = " from Med9002Db where 1=1 ";
		sql += " and isnull(handdate,'Y') = 'Y'";
		sql += " and reporttype = '01'";
		sql += " and id <> " + id;
		java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(sql);
		if(null!=objList && objList.size() > 0) {
			return "Y";
		} else {
			return "N";
		}
	}
	
	
	//評估附件
	public String getAddFileMed050005() throws Exception 
	{
		StringBuilder sb = new StringBuilder(1024); 
		
//		String status = View.getLookupField("select status from Med9002Db where id="+Common.getLong(getId2()));
		
		String hql = " from Con0001Db where fileKind='MED' and systemType like 'MED050005' and dbName='MED9002DB' and upLoadId="+Common.sqlChar(getId2());
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
		
		
		System.out.println("[TCBW]-[PMED0301F]-[QUERYALL] : " + hql);
		
		java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(hql);
		this.processCurrentPageAttribute(objList!=null?objList.size():0);
		
		if (getTotalRecord() > 0) 
		{
			if (getState().indexOf("query")<0) 
				ServiceGetter.getInstance().getTcbwService().getHibernateTemplate().clear();
			
			objList = ServiceGetter.getInstance().getTcbwService().load( hql + " order by b.prehanddate,b.reporttype");
			
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
//					rowArray[8] = Common.get(dtl.getMed9001Db().getApplNo());
					rowArray[8] = Common.get(dtl.getHandstatus());
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
//	public String getId3() {		return id3;	}
//	public void setId3(String id3) {		this.id3 = id3;	}
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
	
	public String getMonitorSDate0301() {
		return checkGet(monitorSDate0301);
	}
	public void setMonitorSDate0301(String monitorSDate0301) {
		this.monitorSDate0301 = checkSet(monitorSDate0301);
	}
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
	
	public String getOutVmedNum() {
		return checkGet(outVmedNum);
	}
	public void setOutVmedNum(String outVmedNum) {
		this.outVmedNum = checkSet(outVmedNum);
	}
	public String getInUnReacNum() {
		return checkGet(inUnReacNum);
	}
	public void setInUnReacNum(String inUnReacNum) {
		this.inUnReacNum = checkSet(inUnReacNum);
	}
	public String getInDefProNum() {
		return checkGet(inDefProNum);
	}
	public void setInDefProNum(String inDefProNum) {
		this.inDefProNum = checkSet(inDefProNum);
	}
	public String getAssessdate() {
		return checkGet(assessdate);
	}
	public void setAssessdate(String assessdate) {
		this.assessdate = checkSet(assessdate);
	}
	public String getAssesssummary() {
		return checkGet(assesssummary);
	}
	public void setAssesssummary(String assesssummary) {
		this.assesssummary = checkSet(assesssummary);
	}
	public String getAssessresult() {
		return checkGet(assessresult);
	}
	public void setAssessresult(String assessresult) {
		this.assessresult = checkSet(assessresult);
	}
	public String getAssessresultdesc() {
		return checkGet(assessresultdesc);
	}
	public void setAssessresultdesc(String assessresultdesc) {
		this.assessresultdesc = checkSet(assessresultdesc);
	}
	public String getAssessremark() {
		return checkGet(assessremark);
	}
	public void setAssessremark(String assessremark) {
		this.assessremark = checkSet(assessremark);
	}
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
	public String getIsNotHand() {
		return checkGet(isNotHand);
	}
	public void setIsNotHand(String isNotHand) {
		this.isNotHand = checkSet(isNotHand);
	}
	public String getReportType() {
		return checkGet(reportType);
	}
	public void setReportType(String reportType) {
		this.reportType = checkSet(reportType);
	}
	
	

}
