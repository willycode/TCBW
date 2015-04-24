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
import com.kangdainfo.tcbw.util.TCBWCommon;
import com.kangdainfo.tcbw.view.sdrg.SDRG0101F;


public class PMED0101F extends SuperBean
{
	private String id;//序號	NUMERIC(19,0)	
	private String doType;	
	private String caseType ;
	private String tabId ;
	private String isUpload;
	private String updateType;
	
	private String applNo;				//案號
	private String monitorNo;			//監控文號
    private String status;              //案件狀態
    private String statusCh;			//案件狀態中文
	private String permitKey;			//許可證字
	private String permitNo;			//許可證號
	private String chProduct;			//商品名稱中文
	private String enProduct;			//商品名稱英文
	private String medapprovedate;		//許可證核准日期
	private String medEffectiveDate;	//許可證有效日期
	private String applicationID;		//許可證持有商統編
	private String applicationName;		//許可證持有商
	private String manufactorName;		//製造商/製造廠
	private String manufactorCountry;	//製造商/製造廠國別
	private String medclass;			//醫材級數	
	private String medMainCategory;		//醫材主類別
	private String medSecCategory;		//醫材次類別
	private String medModel;			//醫材型號
	private String medeffect;			//醫材效能
	private String monitorSDate;		//監控起日
	private String monitorEDate;		//監控迄日
	private String reportIssuenum;		//報告繳交期數
	private String intervalmonth;		//各期間隔(月)
	private String monitorremark;		//監控備註
	
	private String changeTabValue;
	//報告繳交清單----------------------------------------------------------------
	private String reportIssueno[];
	private String prehanddate[];
	private String reporttype[];
	private String handstatus[];
	private String med9002DbType[];
	private String med9002DbTypeId[];
	
	javax.servlet.ServletRequest httpRequest;
	public javax.servlet.ServletRequest getHttpRequest() {	return httpRequest;	}
	public void setHttpRequest(javax.servlet.ServletRequest r) {	this.httpRequest = r;	}
	
	// FOR 新增登入該頁面時，自動新增一筆資料
	public void doInsert()throws Exception
	{		
		CommonUser c = ServiceGetter.getInstance().getAuthenticationService().getCurrentUser();
		if(c == null)
		{
			c = new CommonUser();
			CommonDepartment d = new CommonDepartment();
			d.setShortCode("01");
			c.setCommonDepartment(d);
			System.out.println("[TCBW]-[PMED0101F]-[新增]-[無法辨別登入的使用者]");
		}

		Med9001Db obj = new Med9001Db();
		obj.setStatus("00");
		obj.setMonitorSDate("");
		obj.setMonitorEDate("");
		obj.setCreator(c.getUserId());
		obj.setCreateDate(Datetime.getYYYMMDD());
		obj.setCreateTime(Datetime.getHHMMSS());
		
		ServiceGetter.getInstance().getTcbwService().save(obj);
		setId(Common.get(obj.getId()));
	}
	
	public void doUpdateType() throws Exception{
		ServiceGetter.getInstance().getTcbwService().getPmedDao().updateByPmed0101F(this);
	}	
	
	//產生報告繳交清單
	public void doGenerateList() throws Exception{
//		java.util.List<Med9002Db> saveListMed9002Db = new java.util.ArrayList<Med9002Db>();	
//		java.util.List<Med9002Db> updateListMed9002Db = new java.util.ArrayList<Med9002Db>();
//		java.util.List<Med9002Db> deleteListMed9002Db = new java.util.ArrayList<Med9002Db>();
//		java.util.List<Med9002Db> med9002DbList =null ;	
//		java.util.Map<String,Med9002Db> med9002DbMap=null;
		
		PMED0101F obj = this;		
		java.util.List objList = ServiceGetter.getInstance().getCommonService().load(" from Med9002Db where med9001Db.id=" + Common.getInt(obj.getId()));
		
		CommonUser commonuser = ServiceGetter.getInstance().getAuthenticationService().getCurrentUser();
		if(commonuser == null)
		{
			commonuser = new CommonUser();
			CommonDepartment d = new CommonDepartment();
			d.setShortCode("01");
			commonuser.setCommonDepartment(d);
			System.out.println("[TCBW]-[PMED0101F]-[新增]-[無法辨別登入的使用者]");
		}
		
		if(objList != null && objList.size() > 0) {
			//判斷是否有已繳交資料，並刪除未繳交資料
			for(Object dtlObj:objList) {
				Med9002Db dtl = (Med9002Db)dtlObj;
				if(!"".equals(dtl.getHanddate())) {
					ServiceGetter.getInstance().getTcbwService().delete(dtl);
				}
			}
		}
		
		boolean isOver = true;	//判斷是否已超過監控期間，預設為true，一旦第i筆超過，則產生以監控期間迄日為繳交日期的總結報告，並將isOver改為false
		for(int i = 0; i < Common.getInt(obj.getReportIssuenum()); i++) {
			
				
			String nextDate = Datetime.getDateSubtraction("m", Common.getInt(obj.getIntervalmonth())*-1*(i+1),obj.getMonitorSDate());//下一期起始日
			//nextDate = Common.get(Common.getInt(nextDate) - 1);	
			if((Common.getInt(obj.getMonitorEDate()) >= Common.getInt(nextDate)) || i == Common.getInt(obj.getReportIssuenum())-1) {
				Med9002Db med9002db = new Med9002Db();
				Med9001Db master01 = new Med9001Db();
				master01.setId(Common.getLong(obj.getId()));
				med9002db.setMed9001Db(master01);
				
				med9002db.setReportIssueno(Common.getLong(i+1));	//期數
				med9002db.setReporttype("01");	//報告類別
				
				med9002db.setPrehanddate(Datetime.getDateSubtraction("", 1, nextDate));		//繳交日期				
				med9002db.setHandstatus("01");	//繳交狀態-預設為待繳交
					
				med9002db.setCreator(commonuser.getUserId());
				med9002db.setCreateDate(Datetime.getYYYMMDD());
				med9002db.setCreateTime(Datetime.getHHMMSS());
				ServiceGetter.getInstance().getTcbwService().save(med9002db);
			}
			if((Common.getInt(obj.getMonitorEDate()) <= Common.getInt(nextDate) || i == Common.getInt(obj.getReportIssuenum())-1) && isOver) {	//下一期日期不得大於監控迄日
					Med9002Db med9002db = new Med9002Db();
					Med9001Db master01 = new Med9001Db();
					master01.setId(Common.getLong(obj.getId()));
					med9002db.setMed9001Db(master01);
					med9002db.setReportIssueno(Common.getLong(""));	//期數
					med9002db.setPrehanddate(Common.get(obj.getMonitorEDate()));		//繳交日期
					med9002db.setReporttype("02");
					med9002db.setHandstatus("01");	//繳交狀態-預設為待繳交
						
					med9002db.setCreator(commonuser.getUserId());
					med9002db.setCreateDate(Datetime.getYYYMMDD());
					med9002db.setCreateTime(Datetime.getHHMMSS());
					ServiceGetter.getInstance().getTcbwService().save(med9002db);
					isOver = false;
			}
		}
	}
	
	public String getMed9002DbItemSet() throws Exception 
	{
		StringBuilder sb = new StringBuilder(1024); 
		
		if(!"".equals(getId())) {
			String hql = " from Med9002Db where 1=1 and med9001Db.id="+Common.get(getId());
	        
			System.out.println(hql);
			
	        java.util.List objList = ServiceGetter.getInstance().getCommonService().load(hql+" order by prehanddate,reporttype asc");
		
			if(objList!=null && objList.size()>0)
			{
				java.util.Iterator it = objList.iterator();
				int reportissueno = 0;
				while (it.hasNext()) 
				{
					reportissueno++;
					Med9002Db o = (Med9002Db) it.next();
					sb.append("addMed9002Db('").append(Common.get(o.getId())).append("'");
					sb.append(",'").append(o.getHandstatus()).append("'");//繳交狀態
					
					if("01".equals(o.getReporttype())) {
						sb.append(",'").append(reportissueno).append("'");//若為定期報告，期數由系統自動給
					} else {
						sb.append(",''");//總結報告不須給予期數
					}
					sb.append(",'").append(o.getPrehanddate()).append("'");//繳交日期
					sb.append(",'").append(o.getReporttype()).append("');\n");//報告類別
				}
			}
		}
		
		return sb.toString(); 
	}
	
	public void saveOrUpdateMed9002Db(java.util.List<Med9002Db> saveList,java.util.List<Med9002Db> updateList,java.util.List<Med9002Db> deleteList) 
	throws Exception
	{
		ServiceGetter.getInstance().getTcbwService().deleteAndSave(deleteList, saveList);
		ServiceGetter.getInstance().getTcbwService().updateBatch(updateList);
	}
	
	
	
	@Override
	public Object doQueryOne() throws Exception {
		PMED0101F obj = this;	
		
		Med9001Db c = (Med9001Db) View.getObject(" from Med9001Db where id=" + Common.getInt(obj.getId()));
		
		System.out.println("[TCBW]-[PMED0101F]-[doQueryOne]- Med9001Db.id : " + obj.getId());
		java.util.Map<String, String> statusMap = TCBWCommon.getCommonCodeMap("MEDRPSTATUS");

		if (c!=null) {
			obj.setApplNo(Common.get(c.getApplNo()));
			obj.setMonitorNo(Common.get(c.getMonitorNo()));
			obj.setStatus(Common.get(c.getStatus()));
			obj.setStatusCh(Common.get(statusMap.get(c.getStatus())));
			obj.setPermitKey(Common.get(c.getPermitKey()));
        	obj.setPermitNo(Common.get(c.getPermitNo()));
			obj.setChProduct(Common.get(c.getChProduct()));
        	obj.setEnProduct(Common.get(c.getEnProduct())); 
        	obj.setMedapprovedate(Common.get(c.getMedapprovedate()));
        	obj.setMedEffectiveDate(Common.get(c.getMedEffectiveDate()));
        	obj.setApplicationID(Common.get(c.getApplicationID()));//許可證持有商統編
        	obj.setApplicationName(Common.get(c.getApplicationName()));
        	obj.setManufactorName(Common.get(c.getManufactorName()));
        	obj.setManufactorCountry(Common.get(c.getManufactorCountry()));
        	obj.setMedclass(Common.get(c.getMedclass()));
        	obj.setMedMainCategory(Common.get(c.getMedMainCategory()));
        	obj.setMedSecCategory(Common.get(c.getMedSecCategory()));
        	obj.setMedModel(Common.get(c.getMedModel()));
        	obj.setMedeffect(Common.get(c.getMedeffect()));
        	obj.setMonitorSDate(Common.get(c.getMonitorSDate()));
        	obj.setMonitorEDate(Common.get(c.getMonitorEDate()));
        	obj.setReportIssuenum(Common.get(c.getReportIssuenum()));
        	obj.setIntervalmonth(Common.get(c.getIntervalmonth()));
        	obj.setMonitorremark(Common.get(c.getMonitorremark()));
		}
		return obj;
	}

	@Override
	public Object doQueryAll() throws Exception 
	{		
		return null;
	}

	@Override
	public void doCreate() throws Exception {
		
	}

	@Override
	public void doUpdate() throws Exception {
		
	}

	@Override
	public void doDelete() throws Exception {
		System.out.println("[PMED0101F]-[doDelete]" + Common.getLong(getId()));
		Med9001Db obj = (Med9001Db)View.getObject(" from Med9001Db where id = " + Common.getLong(getId()));
		if(obj != null){
			deleteCon0001DbByPmed0101(obj.getId());//刪除檔案上傳資料
			ServiceGetter.getInstance().getTcbwService().delete(obj);
		}	
		
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
	
	//附件(監控附件)
	public String getAddFileMed0402() throws Exception 
	{
		StringBuilder sb = new StringBuilder(1024); 
		
		String ststus = View.getLookupField("select status from Med9001Db where id="+Common.getLong(getId()));
		
		String hql = " from Con0001Db where fileKind='MED' and systemType like 'MED050001%' and dbName='MED9001DB' and upLoadId="+Common.getLong(getId());
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
				if(Common.getInt(ststus) <= 10){				
					sb.append("<a class=\"text_link_b\" onclick=\"deleteFileSimple("+o.getId()+");\">").append("刪除檔案</a>");	
				}
				sb.append("</td>\n");
				
				i++;
			}
		}
		return sb.toString(); 
	}
	
	public String getId() {		return id;	}
	public void setId(String id) {		this.id = id;	}
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
	
	public String getApplNo() {
		return checkGet(applNo);
	}
	public void setApplNo(String applNo) {
		this.applNo = checkSet(applNo);
	}

	public String getMonitorNo() {
		return checkGet(monitorNo);
	}
	public void setMonitorNo(String monitorNo) {
		this.monitorNo = checkSet(monitorNo);
	}
	public String getStatus() {
		return checkGet(status);
	}
	public void setStatus(String status) {
		this.status = checkSet(status);
	}
	
	public String getStatusCh() {
		return checkGet(statusCh);
	}
	public void setStatusCh(String statusCh) {
		this.statusCh = checkSet(statusCh);
	}
	
	public String getPermitKey() {
		return checkGet(permitKey);
	}
	public void setPermitKey(String permitKey) {
		this.permitKey = checkSet(permitKey);
	}
	
	public String getPermitNo() {
		return checkGet(permitNo);
	}
	public void setPermitNo(String permitNo) {
		this.permitNo = checkSet(permitNo);
	}
	
	public String getChProduct() {
		return checkGet(chProduct);
	}
	public void setChProduct(String chProduct) {
		this.chProduct = checkSet(chProduct);
	}
	
	public String getEnProduct() {
		return checkGet(enProduct);
	}
	public void setEnProduct(String enProduct) {
		this.enProduct = checkSet(enProduct);
	}
	
	public String getMedapprovedate() {
		return checkGet(medapprovedate);
	}
	public void setMedapprovedate(String medapprovedate) {
		this.medapprovedate = checkSet(medapprovedate);
	}
	public String getMedEffectiveDate() {
		return checkGet(medEffectiveDate);
	}
	public void setMedEffectiveDate(String medEffectiveDate) {
		this.medEffectiveDate = checkSet(medEffectiveDate);
	}
	public String getApplicationID() {
		return checkGet(applicationID);
	}
	public void setApplicationID(String applicationID) {
		this.applicationID = checkSet(applicationID);
	}
	public String getApplicationName() {
		return checkGet(applicationName);
	}
	public void setApplicationName(String applicationName) {
		this.applicationName = checkSet(applicationName);
	}
	public String getManufactorName() {
		return checkGet(manufactorName);
	}
	public void setManufactorName(String manufactorName) {
		this.manufactorName = checkSet(manufactorName);
	}
	public String getManufactorCountry() {
		return checkGet(manufactorCountry);
	}
	public void setManufactorCountry(String manufactorCountry) {
		this.manufactorCountry = checkSet(manufactorCountry);
	}
	public String getMedclass() {
		return checkGet(medclass);
	}
	public void setMedclass(String medclass) {
		this.medclass = checkSet(medclass);
	}
	public String getMedMainCategory() {
		return checkGet(medMainCategory);
	}
	public void setMedMainCategory(String medMainCategory) {
		this.medMainCategory = checkSet(medMainCategory);
	}
	
	public String getMedSecCategory() {
		return checkGet(medSecCategory);
	}
	public void setMedSecCategory(String medSecCategory) {
		this.medSecCategory = checkSet(medSecCategory);
	}
	
	public String getMedModel() {
		return checkGet(medModel);
	}
	public void setMedModel(String medModel) {
		this.medModel = checkSet(medModel);
	}
	public String getMedeffect() {
		return checkGet(medeffect);
	}
	public void setMedeffect(String medeffect) {
		this.medeffect = checkSet(medeffect);
	}
	public String getMonitorSDate() {
		return checkGet(monitorSDate);
	}
	public void setMonitorSDate(String monitorSDate) {
		this.monitorSDate = checkSet(monitorSDate);
	}
	public String getMonitorEDate() {
		return checkGet(monitorEDate);
	}
	public void setMonitorEDate(String monitorEDate) {
		this.monitorEDate = checkSet(monitorEDate);
	}
	public String getReportIssuenum() {
		return checkGet(reportIssuenum);
	}
	public void setReportIssuenum(String reportIssuenum) {
		this.reportIssuenum = checkSet(reportIssuenum);
	}
	public String getIntervalmonth() {
		return checkGet(intervalmonth);
	}
	public void setIntervalmonth(String intervalmonth) {
		this.intervalmonth = checkSet(intervalmonth);
	}
	public String getMonitorremark() {
		return checkGet(monitorremark);
	}
	public void setMonitorremark(String monitorremark) {
		this.monitorremark = checkSet(monitorremark);
	}
	public String getChangeTabValue() {
		return checkGet(changeTabValue);
	}
	public void setChangeTabValue(String changeTabValue) {
		this.changeTabValue = checkSet(changeTabValue);
	}
	
	public String[] getReportIssueno() {
		return reportIssueno;
	}

	public void setReportIssueno(String[] reportIssueno) {
		this.reportIssueno = reportIssueno;
	}
	
	public String[] getPrehanddate() {
		return prehanddate;
	}

	public void setPrehanddate(String[] prehanddate) {
		this.prehanddate = prehanddate;
	}
	
	public String[] getReporttype() {
		return reporttype;
	}

	public void setReporttype(String[] reporttype) {
		this.reporttype = reporttype;
	}
	
	public String[] getHandstatus() {
		return checkGet(handstatus);
	}
	public void setHandstatus(String[] handstatus) {
		this.handstatus = checkSet(handstatus);
	}
	public String[] getMed9002DbType() {
		return med9002DbType;
	}

	public void setMed9002DbType(String[] med9002DbType) {
		this.med9002DbType = med9002DbType;
	}

	public String[] getMed9002DbTypeId() {
		return med9002DbTypeId;
	}

	public void setMed9002DbTypeId(String[] med9002DbTypeId) {
		this.med9002DbTypeId = med9002DbTypeId;
	}

}
