package com.kangdainfo.tcbw.view.medin;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.tcbw.model.bo.Med2001Db;
import com.kangdainfo.tcbw.util.TCBWCommon;

public class MEDIN5301Q extends SuperBean{
	
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
	private String[] q_notifierSource;  //通報來源
	private String q_patientId;//病人識別代碼
	private String q_caseSource;		//案例來源
	private String q_fdaNum;			//核準文號
	private String q_fdaOptions;
	private String q_medPermit;			//許可證字
	private String q_medPermitNumber;	//許可證號
	private String q_medTestMedical;	//醫材品名
	private String q_medFactory;		//製造廠
	private String q_badReactionResults;//不良反應結果
	private String[] q_status;			//案件狀態
	
	private String q_notifyDept;//通報單位
	private String q_approvedUnits;//列管單位
	private String q_approvedUnitsOther;//列管單位其他
	private String q_medMainCategory;//醫材主類別
	private String q_medSecCategory;//醫材次類別
	private String q_trans;//是否為歷史移轉資料

	
	@Override
	public Object doQueryOne() throws Exception 
	{
		MEDIN5301Q obj = this;

		return obj;
	}

	@Override
	public Object doQueryAll() throws Exception 
	{
		
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		
		String hql = " from Med2001Db med2001 where 1 = 1 ";
		
		//要查全部，不再排除案件編號是null的資料
		//hql += " and med2001.applNo is not null ";
		if(!"".equals(getQ_applNoS())) 
			hql += "and med2001.applNo >= " +  Common.sqlChar(getQ_applNoS()) + "and med2001.applNo <= " + Common.sqlChar(getQ_applNoE());
		if(!"".equals(getQ_occurDateS())) 
			hql += "and med2001.occurDate >= " + Common.sqlChar(getQ_occurDateS()) + "and med2001.occurDate <= " + Common.sqlChar(getQ_occurDateE());
		if(!"".equals(getQ_notifierRevDateS())) 
			hql += "and med2001.notifierRevDate >= " + Common.sqlChar(getQ_notifierRevDateS()) + "and med2001.notifierRevDate <= " + Common.sqlChar(getQ_notifierRevDateE());
		if(!"".equals(getQ_notifierRepDateS())) 
			hql += "and med2001.notifierRepDate >= " + Common.sqlChar(getQ_notifierRepDateS()) + "and med2001.notifierRepDate <= " + Common.sqlChar(getQ_notifierRepDateE());
		if(!"".equals(getQ_caseSource())) 
			hql += "and med2001.caseSource  = " + Common.sqlChar(getQ_caseSource());
		if(!"".equals(getQ_medTestMedical())) 
			hql += "and med2001.medTestMedical like " + Common.sqlChar("%" + getQ_medTestMedical() + "%");
		if(!"".equals(getQ_medFactory())) 
			hql += "and med2001.medFactory like" + Common.sqlChar("%" + getQ_medFactory() + "%");
		if(!"".equals(getQ_badReactionResults()))
			hql += "and med2001.badReactionResults = " + Common.sqlChar(getQ_badReactionResults());
		//通報單位
		if(getQ_notifyDept() != null && !"".equals(getQ_notifyDept()))
			hql += " and med2001.notifierDept like " + Common.likeSqlChar(getQ_notifyDept());
		//病人識別代碼
		if(!"".equals(getQ_patientId())) 
			hql += "and med2001.patientId = " + Common.sqlChar(getQ_patientId());
		//列管單位
		if(getQ_approvedUnits() != null && !"".equals(getQ_approvedUnits())){
			if("3".equals(getQ_approvedUnits()))
				hql += " and med2001.approvedUnitsOther like " + Common.likeSqlChar(getQ_approvedUnitsOther());
			else
				hql += " and med2001.approvedUnits = " + Common.sqlChar(getQ_approvedUnits());
		}
		//醫材主類別
		if(getQ_medMainCategory() != null && !"".equals(getQ_medMainCategory()))
			hql += " and med2001.medMainCategoryCode = " + Common.sqlChar(getQ_medMainCategory());
		//醫材次類別
		if(getQ_medSecCategory() != null && !"".equals(getQ_medSecCategory()))
			hql += " and med2001.medSecCategoryCode = " + Common.sqlChar(getQ_medSecCategory());

		if(!"".equals(getQ_medPermit())) 
			hql += "and med2001.medPermit = " + Common.sqlChar(getQ_medPermit());
			
		if(!"".equals(getQ_medPermitNumber())) 
			hql += "and med2001.medPermitNumber = " + Common.sqlChar(getQ_medPermitNumber());
		//案件狀態
		if(getQ_status() != null && getQ_status().length > 0) {
			hql += "and med2001.status = ";
			int j = 0;
			while(j < getQ_status().length) {
				hql += Common.sqlChar(getQ_status()[j]);
				j++;
				if(j < getQ_status().length) {
					hql += " or med2001.status = ";
				}
			}
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
			
			java.util.List objList = ServiceGetter.getInstance().getTcbwService().load("select med2001 " + hql + " order by med2001.id desc", this.getRecordStart(), this.getPageSize());
			
			if (objList != null && objList.size() > 0) 
			{
				java.util.Map<String, String> statusMap = TCBWCommon.getCommonCodeMap("MEDSTATUS1");
				java.util.Map<String, String> medPermitMap = TCBWCommon.getCommonCodeMap("MEDPKID");
				java.util.Map<String, String> medMainCategoryMap = TCBWCommon.getCommonCodeMap("MEDMCT");
				java.util.Map<String, String> badReactionResultsMap = TCBWCommon.getCommonCodeMap("MEDAD1");
				for(Object dtlObj : objList) 
				{				
					Med2001Db dtl = (Med2001Db)dtlObj;
					String[] badList = Common.get(dtl.getBadReactionResults()).split(",");
					

					String[] rowArray = new String[12];
					rowArray[0] = Common.get(dtl.getId());											
					rowArray[1] = Common.get(dtl.getApplNo());
					rowArray[2] = Common.get(dtl.getNotifierRevDate());
					rowArray[3] = Common.get(dtl.getFdaNum());				//核準文號
					rowArray[4] = Common.get(dtl.getApprovedUnits());				//列管單位
					
					if(!"".equals(Common.get(dtl.getMedPermit())))
						rowArray[5] = Common.get(medPermitMap.get(dtl.getMedPermit()))+"字第"+ Common.get(dtl.getMedPermitNumber())+"號";	
					else
						rowArray[5] ="";
					rowArray[6] = Common.get(dtl.getMedTestMedical());
					rowArray[7] = Common.get(dtl.getMedFactory());
					rowArray[8] = Common.get(medMainCategoryMap.get(dtl.getMedMainCategory()));
					rowArray[9] = "";
					if(!"".equals(badList) && badList.length > 0) {
						for(int j =0;j < badList.length; j++) {
							rowArray[9] += badReactionResultsMap.get(badList[j]);
						}
						
					} else {
						rowArray[9] = "";
					}
					rowArray[10] = Common.get(statusMap.get(dtl.getStatus()));
					rowArray[11] = Common.get("明細");
					arrList.add(rowArray);
				}
				objList.clear();
			}
		
		}
		return arrList;
	}
	


	@Override
	public void doCreate() throws Exception 
	{

		
	}
	


	@Override
	public void doUpdate() throws Exception 
	{

	}

	@Override
	public void doDelete() throws Exception 
	{

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

	public String getQ_medTestMedical() {
		return checkGet(q_medTestMedical);
	}

	public void setQ_medTestMedical(String q_medTestMedical) {
		this.q_medTestMedical = checkSet(q_medTestMedical);
	}
	
	public String getQ_medFactory() {
		return checkGet(q_medFactory);
	}

	public void setQ_medFactory(String q_medFactory) {
		this.q_medFactory = checkSet(q_medFactory);
	}

	public String getQ_badReactionResults() {
		return checkGet(q_badReactionResults);
	}

	public void setQ_badReactionResults(String q_badReactionResults) {
		this.q_badReactionResults = checkSet(q_badReactionResults);
	}

	public String[] getQ_notifierSource() {
		return checkGet(q_notifierSource);
	}

	public void setQ_notifierSource(String[] qNotifierSource) {
		q_notifierSource = checkSet(qNotifierSource);
	}

	public String getQ_fdaNum() {
		return checkGet(q_fdaNum);
	}

	public void setQ_fdaNum(String qFdaNum) {
		q_fdaNum = checkSet(qFdaNum);
	}

	public String getQ_fdaOptions() {
		return checkGet(q_fdaOptions);
	}

	public void setQ_fdaOptions(String qFdaOptions) {
		q_fdaOptions = checkSet(qFdaOptions);
	}

	public String[] getQ_status() {
		return checkGet(q_status);
	}

	public void setQ_status(String[] qStatus) {
		q_status = checkSet(qStatus);
	}

	public String getQ_notifyDept() {
		return checkGet(q_notifyDept);
	}

	public void setQ_notifyDept(String q_notifyDept) {
		this.q_notifyDept = checkSet(q_notifyDept);
	}

	public String getQ_approvedUnits() {
		return checkGet(q_approvedUnits);
	}

	public void setQ_approvedUnits(String q_approvedUnits) {
		this.q_approvedUnits = checkSet(q_approvedUnits);
	}

	public String getQ_approvedUnitsOther() {
		return checkGet(q_approvedUnitsOther);
	}

	public void setQ_approvedUnitsOther(String q_approvedUnitsOther) {
		this.q_approvedUnitsOther = checkSet(q_approvedUnitsOther);
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
	
	public void setQ_patientId(String q_patientId) {
		this.q_patientId = checkSet(q_patientId);
	}

	public String getQ_patientId() {
		return checkGet(q_patientId);
	}

	public String getQ_trans() {
		return checkGet(q_trans);
	}

	public void setQ_trans(String q_trans) {
		this.q_trans = checkSet(q_trans);
	}

	

	

	
}
