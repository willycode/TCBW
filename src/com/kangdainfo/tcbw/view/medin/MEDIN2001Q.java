package com.kangdainfo.tcbw.view.medin;


import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.tcbw.model.bo.Med0001Db;
import com.kangdainfo.tcbw.util.TCBWCommon;


public class MEDIN2001Q extends SuperBean
{
	
	private String q_isQuery;
	private String q_id;				//ID
	private String q_applNoS;			//案件編號S
	private String q_applNoE;			//案件編號E
	private String q_occurDateS;		//發生日期S
	private String q_occurDateE;		//發生日期E
	private String q_notifierRevDateS;	//通報日期S
	private String q_notifierRevDateE;	//通報日期E
	private String q_notifierRepDateS;	//收案日期S
	private String q_notifierRepDateE;	//收案日期E
	private String q_caseSource;		//案例來源
	private String[] q_eventKind;       //事件類別	
	private String q_medPermit;			//許可證字
	private String q_medPermitNumber;	//許可證號
	private String q_badReactionPatientCode;//病人識別代碼
	private String q_medCname;			//醫材品名
	private String q_medPermitFirm;		//申請商
	private String q_medFactory;		//製造廠
	private String q_medMainCategory;	//醫材主類別
	private String q_medSecCategory;	//醫材次類別
	private String[] q_badReactionResults;//不良反應結果
	
	private String q_drugEventsSources;//通報來源
	private String q_notifyDept;//通報單位
	private String q_ncarResult;//NCAR通報狀況
	private String[] q_eventClass;//不良品事件等級
	private String q_caseStatus_eventType_1;//案件狀態-不良反應
    private String q_caseStatus_eventType_2;//案件狀態-不良品
    private String q_trans;//是否為歷史移轉資料

	@Override
	public Object doQueryOne() throws Exception 
	{
		MEDIN2001Q obj = this;
		return obj;
	}

	@Override
	public Object doQueryAll() throws Exception 
	{
		
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		
		//String hql = " from Med0001Db med0001 left join med0001.med0007Dbs med0007 left join med0001.med0008Dbs med0008 left join med0001.med0009Dbs med0009 where 1 = 1 ";

		String hql = " from Med0001Db med0001  where 1 = 1 ";
		
		if(!"".equals(getQ_applNoS())) 
			hql += " and med0001.applNo >= " +  Common.sqlChar(getQ_applNoS()) ;
		
		if(!"".equals(getQ_applNoE())) 
			hql +=  " and med0001.applNo <= " + Common.sqlChar(getQ_applNoE());
			
		if(!"".equals(getQ_occurDateS())) 
			hql += " and med0001.occurDate >= " + Common.sqlChar(getQ_occurDateS());
		
		if(!"".equals(getQ_occurDateE())) 
			hql += " and med0001.occurDate <= " + Common.sqlChar(getQ_occurDateE());
			
		if(!"".equals(getQ_notifierRevDateS())) 
			hql += " and med0001.notifierRevDate >= " + Common.sqlChar(getQ_notifierRevDateS());
		
		if(!"".equals(getQ_notifierRevDateE())) 
			hql +=" and med0001.notifierRevDate <= " + Common.sqlChar(getQ_notifierRevDateE());
			
		if(!"".equals(getQ_notifierRepDateS())) 
			hql += " and med0001.notifierRepDate >= " + Common.sqlChar(getQ_notifierRepDateS());
			
		if(!"".equals(getQ_notifierRepDateE())) 
			hql += " and med0001.notifierRepDate <= " + Common.sqlChar(getQ_notifierRepDateE());
		
		if(!"".equals(getQ_caseSource())) 
			hql += "and med0001.caseSource  = " + Common.sqlChar(getQ_caseSource());
			
		if(!"".equals(getQ_medPermit())) 
			hql += "and med0001.medPermit = " + Common.sqlChar(getQ_medPermit());
			
		if(!"".equals(getQ_medPermitNumber())) 
			hql += "and med0001.medPermitNumber = " + Common.sqlChar(getQ_medPermitNumber());
		
		if(!"".equals(getQ_badReactionPatientCode())) 
			hql += "and med0001.badReactionPatientCode = " + Common.sqlChar(getQ_badReactionPatientCode());
			
		if(!"".equals(getQ_medCname())) 
			hql += "and med0001.medCname like " + Common.sqlChar("%" + getQ_medCname() + "%");
			
		if(!"".equals(getQ_medPermitFirm())) 
			hql += "and med0001.medPermitFirm like " + Common.sqlChar("%" + getQ_medPermitFirm() + "%");
			
		if(!"".equals(getQ_medFactory())) 
			hql += "and med0001.medFactory like" + Common.sqlChar("%" + getQ_medFactory() + "%");
			
		//醫材主類別
		if(getQ_medMainCategory() != null && !"".equals(getQ_medMainCategory()))
			hql += " and med0001.medMainCategory = " + Common.sqlChar(getQ_medMainCategory());
		//醫材次類別
		if(getQ_medSecCategory() != null && !"".equals(getQ_medSecCategory()))
			hql += " and med0001.medSecCategory = " + Common.sqlChar(getQ_medSecCategory());
			
		if(!"".equals(Common.get(getQ_eventKind())))
		{
			hql +=" and (";
			for(int i=0;i<getQ_eventKind().length;i++)
			{
				if(i!=0) 
					hql += " or ";
				hql += " med0001.eventKind = " + Common.sqlChar(getQ_eventKind()[i]);					
			}
			hql += ")";
		}
		if(!"".equals(Common.get(getQ_badReactionResults())))
		{
			hql +=" and (";
			for(int i=0;i<getQ_badReactionResults().length;i++)
			{
				if(i!=0) 
					hql += " or ";
				hql += " med0001.badReactionResults = " + Common.sqlChar(getQ_badReactionResults()[i]);					
			}
			hql += ")";
		}
		//通報來源 format = 01 ?
		if(getQ_drugEventsSources() != null && !"".equals(getQ_drugEventsSources()) && getQ_drugEventsSources().length() > 1)
			hql += " and med0001.drugEventsSources = " + Common.sqlChar(getQ_drugEventsSources().substring(1,2));
		//通報單位
		if(getQ_notifyDept() != null && !"".equals(getQ_notifyDept()))
			hql += " and med0001.notifierDept like " + Common.likeSqlChar(getQ_notifyDept());
		
		if(getQ_ncarResult() != null && !"".equals(getQ_ncarResult()))
		{
			hql += " and med0001.ncarResult = " + Common.sqlChar(getQ_ncarResult());
		}
		
		if(getQ_eventClass() != null && getQ_eventClass().length > 0)
		{
			hql += " and med0001.eventClass in (";
			for(int i = 0 ; i < getQ_eventClass().length ; i ++)
				if(getQ_eventClass()[i] != null && !"".equals(getQ_eventClass()[i]))
				{
					hql += Common.sqlChar(getQ_eventClass()[i]) ;
					hql += (i < getQ_eventClass().length -1)?",":"";
				}
			hql += ")";
		}
		
		/*
		//NCAR通報狀況
		if(getQ_ncarResult() != null && !"".equals(getQ_ncarResult())){
			hql += " and ( med0007.ncarResult = " + Common.sqlChar(getQ_ncarResult());
			hql += " or med0008.ncarResult = " + Common.sqlChar(getQ_ncarResult());
			hql += " or med0009.ncarResult = " + Common.sqlChar(getQ_ncarResult()) + ")";
		}
		
		//不良品事件等級
		if(getQ_eventClass() != null && getQ_eventClass().length > 0)
		{
			hql += " and (med0008.eventClass in (";
			for(int i = 0 ; i < getQ_eventClass().length ; i ++)
				if(getQ_eventClass()[i] != null && !"".equals(getQ_eventClass()[i])){
					hql += Common.sqlChar(getQ_eventClass()[i]) ;
					hql += (i < getQ_eventClass().length -1)?",":"";
				}
			hql += ")";
			hql += " or med0009.eventClass in (";
			for(int i = 0 ; i < getQ_eventClass().length ; i ++)
				if(getQ_eventClass()[i] != null && !"".equals(getQ_eventClass()[i])){
					hql += Common.sqlChar(getQ_eventClass()[i]) ;
					hql += (i < getQ_eventClass().length -1)?",":"";
				}
			hql += ")";
			hql += ")";
		}
		*/
		
		//案件狀態
		if((getQ_caseStatus_eventType_1() != null && !"".equals(getQ_caseStatus_eventType_1()))
				|| getQ_caseStatus_eventType_2() != null && !"".equals(getQ_caseStatus_eventType_2())){
			hql += " and (";
			//案件狀態-不良反應
			if(getQ_caseStatus_eventType_1() != null && !"".equals(getQ_caseStatus_eventType_1()))
				hql += "(med0001.eventKind = '1' and med0001.status = " + Common.sqlChar(getQ_caseStatus_eventType_1()) + ")";
			//案件狀態-不良品
			if(getQ_caseStatus_eventType_2() != null && !"".equals(getQ_caseStatus_eventType_2())){
				if(getQ_caseStatus_eventType_1() != null && !"".equals(getQ_caseStatus_eventType_1()))
					hql += " or " ;
				hql += "(med0001.eventKind = '2' and med0001.status = " + Common.sqlChar(getQ_caseStatus_eventType_2()) + ")";
			}
			hql += " )";
		}
		//是否為歷史移轉資料：Y=是；N=否
		if("Y".equals(getQ_trans())) {
			hql += " and trans = 'Y'";
		}
		if("N".equals(getQ_trans())) {
			hql += " and isnull(trans,'') = ''";
		}
		System.out.println("HQL: " + hql);
		
		this.processCurrentPageAttribute(ServiceGetter.getInstance().getTcbwService().loadCount(hql));
		
		if (getTotalRecord() > 0)
		{
			if (getState().indexOf("query")<0) 
				ServiceGetter.getInstance().getCommonService().getHibernateTemplate().clear();
			java.util.List objList = ServiceGetter.getInstance().getTcbwService().load("select med0001 " + hql + " order by med0001.id desc", this.getRecordStart(), this.getPageSize());
			
			if (objList != null && objList.size() > 0) 
			{
				//使用map先將資料撈出來
				java.util.Map<String, String> medPermitMap = TCBWCommon.getCommonCodeMap("MEDPKID");
				java.util.Map<String, String> status2Map = TCBWCommon.getCommonCodeMap("MEDSTATUS2");
				java.util.Map<String, String> status3Map = TCBWCommon.getCommonCodeMap("MEDSTATUS3");
				
				for(Object dtlObj : objList) 
				{				
					Med0001Db dtl = (Med0001Db)dtlObj;

					String[] rowArray = new String[12];
					rowArray[0] = Common.get(dtl.getId());											
					rowArray[1] = Common.get(dtl.getApplNo());
					rowArray[2] = Common.get(dtl.getNotifierRevDate());
					
					if("1".equals(dtl.getEventKind()))
					   rowArray[3] = Common.get("不良反應");
					else
					   rowArray[3] = Common.get("不良品");
					
					rowArray[4] = Common.get(medPermitMap.get(dtl.getMedPermit()))+"字第"+ Common.get(dtl.getMedPermitNumber())+"號";
					rowArray[5] = Common.get(dtl.getMedCname());
					rowArray[6] = Common.get(dtl.getMedPermitFirm());
					rowArray[7] = Common.get(dtl.getMedFactory());
					rowArray[8] = Common.get(dtl.getMedMainCategory());
					rowArray[9] = Common.get(dtl.getBadReactionResults());
					
					if("1".equals(dtl.getEventKind()))
					  rowArray[10] = Common.get(status2Map.get(dtl.getStatus()));
					else
					  rowArray[10] = Common.get(status3Map.get(dtl.getStatus()));
					
					rowArray[11] = Common.get("明細");
					arrList.add(rowArray);
				}
				objList.clear();
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

	public String getQ_isQuery() {
		return checkGet(q_isQuery);
	}

	public void setQ_isQuery(String qIsQuery) {
		q_isQuery = checkSet(qIsQuery);
	}
	
	public String getQ_id() {
		return checkGet(q_id);
	}

	public void setQ_id(String q_id) {
		this.q_id = checkSet(q_id);
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

	public String getQ_occurDateS() {
		return checkGet(q_occurDateS);
	}

	public void setQ_occurDateS(String q_occurDateS) {
		this.q_occurDateS = checkSet(q_occurDateS);
	}
	
	public String getQ_occurDateE() {
		return checkGet(q_occurDateE);
	}

	public void setQ_occurDateE(String q_occurDateE) {
		this.q_occurDateE = checkSet(q_occurDateE);
	}

	public String getQ_notifierRevDateS() {
		return checkGet(q_notifierRevDateS);
	}

	public void setQ_notifierRevDateS(String q_notifierRevDateS) {
		this.q_notifierRevDateS = checkSet(q_notifierRevDateS);
	}
	
	public String getQ_notifierRevDateE() {
		return checkGet(q_notifierRevDateE);
	}

	public void setQ_notifierRevDateE(String q_notifierRevDateE) {
		this.q_notifierRevDateE = checkSet(q_notifierRevDateE);
	}

	public String getQ_notifierRepDateS() {
		return checkGet(q_notifierRepDateS);
	}

	public void setQ_notifierRepDateS(String q_notifierRepDateS) {
		this.q_notifierRepDateS = checkSet(q_notifierRepDateS);
	}
	
	public String getQ_notifierRepDateE() {
		return checkGet(q_notifierRepDateE);
	}

	public void setQ_notifierRepDateE(String q_notifierRepDateE) {
		this.q_notifierRepDateE = checkSet(q_notifierRepDateE);
	}

	public String getQ_caseSource() {
		return checkGet(q_caseSource);
	}

	public void setQ_caseSource(String q_caseSource) {
		this.q_caseSource = checkSet(q_caseSource);
	}

	

	public String[] getQ_eventKind() {
		return q_eventKind;
	}

	public void setQ_eventKind(String[] qEventKind) {
		q_eventKind = qEventKind;
	}

	public String getQ_medPermit() {
		return checkGet(q_medPermit);
	}

	public void setQ_medPermit(String q_medPermit) {
		this.q_medPermit = checkSet(q_medPermit);
	}
	
	public String getQ_medPermitNumber() {
		return checkGet(q_medPermitNumber);
	}

	public void setQ_medPermitNumber(String q_medPermitNumber) {
		this.q_medPermitNumber = checkSet(q_medPermitNumber);
	}

	public String getQ_medCname() {
		return checkGet(q_medCname);
	}

	public void setQ_medCname(String q_medCname) {
		this.q_medCname = checkSet(q_medCname);
	}

	public String getQ_medPermitFirm() {
		return checkGet(q_medPermitFirm);
	}

	public void setQ_medPermitFirm(String q_medPermitFirm) {
		this.q_medPermitFirm = checkSet(q_medPermitFirm);
	}
	
	public String getQ_medFactory() {
		return checkGet(q_medFactory);
	}

	public void setQ_medFactory(String q_medFactory) {
		this.q_medFactory = checkSet(q_medFactory);
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

	public String[] getQ_badReactionResults() {
		return checkGet(q_badReactionResults);
	}

	public void setQ_badReactionResults(String[] qBadReactionResults) {
		q_badReactionResults = checkSet(qBadReactionResults);
	}

	public String getQ_drugEventsSources() {
		return checkGet(q_drugEventsSources);
	}

	public void setQ_drugEventsSources(String q_drugEventsSources) {
		this.q_drugEventsSources = checkSet(q_drugEventsSources);
	}

	public String getQ_notifyDept() {
		return checkGet(q_notifyDept);
	}

	public void setQ_notifyDept(String q_notifyDept) {
		this.q_notifyDept = checkSet(q_notifyDept);
	}

	public String getQ_ncarResult() {
		return checkGet(q_ncarResult);
	}

	public void setQ_ncarResult(String q_ncarResult) {
		this.q_ncarResult = checkSet(q_ncarResult);
	}

	public String[] getQ_eventClass() {
		return checkGet(q_eventClass);
	}

	public void setQ_eventClass(String[] q_eventClass) {
		this.q_eventClass = checkSet(q_eventClass);
	}

	public String getQ_caseStatus_eventType_1() {
		return checkGet(q_caseStatus_eventType_1);
	}

	public void setQ_caseStatus_eventType_1(String q_caseStatus_eventType_1) {
		this.q_caseStatus_eventType_1 = checkSet(q_caseStatus_eventType_1);
	}

	public String getQ_caseStatus_eventType_2() {
		return checkGet(q_caseStatus_eventType_2);
	}

	public void setQ_caseStatus_eventType_2(String q_caseStatus_eventType_2) {
		this.q_caseStatus_eventType_2 = checkSet(q_caseStatus_eventType_2);
	}

	public void setQ_badReactionPatientCode(String q_badReactionPatientCode) {
		this.q_badReactionPatientCode = checkSet(q_badReactionPatientCode);
	}

	public String getQ_badReactionPatientCode() {
		return checkGet(q_badReactionPatientCode);
	}

	public String getQ_trans() {
		return checkGet(q_trans);
	}

	public void setQ_trans(String q_trans) {
		this.q_trans = checkSet(q_trans);
	}

	
	
}
