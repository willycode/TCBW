package com.kangdainfo.tcbw.view.medin;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.tcbw.model.bo.Med0001Db;
import com.kangdainfo.tcbw.util.TCBWCommon;


public class MEDIN0201F extends SuperBean
{
	private String caseType;
	private String id;//序號	NUMERIC(19,0)
	private String doType;
	private String isInOrOutPerson;
    private String inOrOut;//分內外網
    private String talbe3;
    private String talbe4;
    
    private String statusType;

	private String q_applNoS;//案件編號起
	private String q_applNoE;//案件編號訖
	private String q_occurDateS;//通報日期起
	private String q_occurDateE;//通報日期訖
	private String q_medPermit;//懷疑的醫療器材-許可證字號-字
	private String q_medPermitNumber;//懷疑的醫療器材-許可證字號-號
	private String q_fdaNum;//核准文號
	private String q_medTestMedical;//醫材品名
	private String q_status;//狀態
	private String q_workers;//作業人員


	@Override
	public Object doQueryOne() throws Exception {
		return null;
	}

	@Override
	public Object doQueryAll() throws Exception 
	{		

		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		
		String hql = " from Med0001Db where 1 = 1 ";

		if("10".equals(getStatusType()))
		{	
			hql += " and ( status ="+Common.sqlChar(getStatusType());
			hql += " or status ='11' )";
		}
		else
		{
		    hql += " and status ="+Common.sqlChar(getStatusType());
		}
		
		if("1".equals(caseType))
		{
			hql += " and eventKind like '%1%' ";
		}
		else if("2".equals(caseType))
		{
			hql += " and eventKind like '%2%' ";
		}		
		
		
		if((!"".equals(getQ_applNoS())) || !"".equals(getQ_applNoE()))
			hql += " and applNo >= " + Common.sqlChar(getQ_applNoS()) + "and applNo <= " + Common.sqlChar(getQ_applNoE());
		
		if((!"".equals(getQ_occurDateS())) || !"".equals(getQ_occurDateE()))
			hql += " and occurDate >= " + Common.sqlChar(getQ_occurDateS()) + " and occurDate <= " + Common.sqlChar(getQ_occurDateE());
		
		if(!"".equals(getQ_medPermit()))
			hql += "and medPermit = " + Common.sqlChar(getQ_medPermit());
		
		if(!"".equals(getQ_medPermitNumber()))
			hql += "and medPermitNumber = " + Common.sqlChar(getQ_medPermitNumber());
		
		if(!"".equals(getQ_fdaNum()))
			hql += "and fdaNum = " + Common.sqlChar(getQ_fdaNum());
		
		if(getQ_workers() != null && !"".equals(getQ_workers()))
			hql += "and workers like " + Common.likeSqlChar(getQ_workers());
		
		//if(!"".equals(getQ_medTestMedical()))
			//hql += "and medTestMedical = " + Common.sqlChar(getQ_medTestMedical());

		this.processCurrentPageAttribute(ServiceGetter.getInstance().getTcbwService().loadCount(hql));
		
		System.out.println("[TCBW]-[MEDIN0201F]-[QUERYALL] : " + hql);
		
		if (getTotalRecord() > 0)
		{
			if (getState().indexOf("query")<0) 
				ServiceGetter.getInstance().getCommonService().getHibernateTemplate().clear();
			
			//java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(hql + " order by id desc", this.getRecordStart(), this.getPageSize());
			java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(hql + " order by id desc");
			
			if (objList != null && objList.size() > 0) 
			{
				java.util.Map<String, String> status2Map = TCBWCommon.getCommonCodeMap("MEDSTATUS2");
				java.util.Map<String, String> status3Map = TCBWCommon.getCommonCodeMap("MEDSTATUS3");

				java.util.Map<String, String> medPermitMap = TCBWCommon.getCommonCodeMap("MEDPKID");
				
				for(Object dtlObj : objList) 
				{				
					Med0001Db dtl = (Med0001Db)dtlObj;
					String[] rowArray = new String[9];
					rowArray[0] = Common.get(dtl.getId());											
					rowArray[1] = Common.get(dtl.getApplNo());						
					rowArray[2] = Common.get(dtl.getNotifierRevDate());
					
					if(!"".equals(Common.get(dtl.getMedPermit())))
					  rowArray[3] = Common.get(medPermitMap.get(dtl.getMedPermit()))+"字第"+ Common.get(dtl.getMedPermitNumber())+"號";	
					else
					  rowArray[3] ="";
					
					rowArray[4] = Common.get(dtl.getMedCname());
					rowArray[5] = Common.get(dtl.getMedMainCategory());
					

					if("1".indexOf(dtl.getEventKind())!=-1)
					{
						rowArray[6] = Common.get(status2Map.get(dtl.getStatus()));
					}
					else
					{
						rowArray[6] = Common.get(status3Map.get(dtl.getStatus()));
					}

					rowArray[7] = Common.get(dtl.getApplNo());
					rowArray[8] = Common.get(dtl.getWorkers());
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
	
	

	public String getCaseType() {
		return checkGet(caseType);
	}

	public void setCaseType(String caseType) {
		this.caseType = checkSet(caseType);
	}

	public String getId() {
		return checkGet(id);
	}

	public void setId(String id) {
		this.id = checkSet(id);
	}

	public String getDoType() {
		return checkGet(doType);
	}

	public void setDoType(String doType) {
		this.doType = checkSet(doType);
	}

	public String getIsInOrOutPerson() {
		return checkGet(isInOrOutPerson);
	}

	public void setIsInOrOutPerson(String isInOrOutPerson) {
		this.isInOrOutPerson = checkSet(isInOrOutPerson);
	}

	public String getInOrOut() {
		return checkGet(inOrOut);
	}

	public void setInOrOut(String inOrOut) {
		this.inOrOut = checkSet(inOrOut);
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

	public String getQ_medPermit() {
		return checkGet(q_medPermit);
	}

	public void setQ_medPermit(String qMedPermit) {
		q_medPermit = checkSet(qMedPermit);
	}

	public String getQ_medPermitNumber() {
		return checkGet(q_medPermitNumber);
	}

	public void setQ_medPermitNumber(String qMedPermitNumber) {
		q_medPermitNumber = checkSet(qMedPermitNumber);
	}

	public String getQ_fdaNum() {
		return checkGet(q_fdaNum);
	}

	public void setQ_fdaNum(String qFdaNum) {
		q_fdaNum = checkSet(qFdaNum);
	}

	public String getQ_medTestMedical() {
		return checkGet(q_medTestMedical);
	}

	public void setQ_medTestMedical(String qMedTestMedical) {
		q_medTestMedical = checkSet(qMedTestMedical);
	}

	public String getQ_status() {
		return checkGet(q_status);
	}

	public void setQ_status(String qStatus) {
		q_status = checkSet(qStatus);
	}

	public String getTalbe3() {
		return checkGet(talbe3);
	}

	public void setTalbe3(String talbe3) {
		this.talbe3 = checkSet(talbe3);
	}

	public String getTalbe4() {
		return checkGet(talbe4);
	}

	public void setTalbe4(String talbe4) {
		this.talbe4 = checkSet(talbe4);
	}

	public String getStatusType() {
		return checkGet(statusType);
	}

	public void setStatusType(String statusType) {
		this.statusType = checkSet(statusType);
	}

	public String getQ_workers() {
		return checkGet(q_workers);
	}

	public void setQ_workers(String q_workers) {
		this.q_workers = checkSet(q_workers);
	}

}
