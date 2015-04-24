package com.kangdainfo.tcbw.view.medin;


import java.util.Map;
import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.tcbw.model.bo.Med2001Db;
import com.kangdainfo.tcbw.util.TCBWCommon;


public class MEDIN5303F extends MEDIN0101F
{
	
	private String id2;//序號	NUMERIC(19,0)
	private String q_notifierRepDateS;//通報日期	
	private String q_notifierRepDateE;//通報日期
	private String q_badReactionResults;//不良反應
	
    

	@Override
	public Object doQueryAll() throws Exception 
	{		

		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		
		String hql = " from Med2001Db med2001 where 1=1 ";
		hql += " and (";
		hql += " (med2001.medPermit = ( select medPermit from  Med2001Db where ";
		hql += " id=" + getId() + " )";
		hql += " and med2001.medPermitNumber = ( select medPermitNumber from  Med2001Db where ";
		hql += " id=" + getId() + " )";
		hql += " and med2001.medPermit is not null and med2001.medPermitNumber is not null )";
		//如果是字是Z0或Z5，則改比中文品名
		hql += " or (med2001.medPermit in ('Z0','Z5') and med2001.medTestMedical = (select medTestMedical from Med2001Db where id = " + Common.sqlChar(getId()) + "))";
		hql += ")";
		hql += " or  fdaNum in ( select fdaNum from  Med2001Db where ";
	    hql += " id="+getId()+" )";
	    //通報日期起
		if(getQ_notifierRepDateS() != null && !"".equals(getQ_notifierRepDateS()))
			hql += " and med2001.notifierRepDate >= " + Common.sqlChar(getQ_notifierRepDateS());
		//通報日期迄
		if(getQ_notifierRepDateE() != null && !"".equals(getQ_notifierRepDateE()))
			hql += " and med2001.notifierRepDate <= " + Common.sqlChar(getQ_notifierRepDateE());
		//原始不良反應
		if(getQ_badReactionResults() != null && !"".equals(getQ_badReactionResults()))
			hql += " and med2001.badReactionResults = " + Common.sqlChar(getQ_badReactionResults());
		
	    this.processCurrentPageAttribute(ServiceGetter.getInstance().getTcbwService().loadCount(hql));
		
		System.out.println("[TCBW]-[MEDIN5303F]-[QUERYALL] : " + hql);
		
		if (getTotalRecord() > 0)
		{
			if (getState().indexOf("query")<0) 
				ServiceGetter.getInstance().getCommonService().getHibernateTemplate().clear();
			
			java.util.List objList = ServiceGetter.getInstance().getTcbwService().load("select med2001 " + hql + " order by med2001.id", this.getRecordStart(), this.getPageSize());
			
			if (objList != null && objList.size() > 0) 
			{
				//init codeName map
				Map ApprovedUnitsMap = TCBWCommon.getCodeNameApprovedUnitsMap();
				Map MEDAD2Map = TCBWCommon.getCommonCodeMap("MEDAD2");
				Map NCTRResultMap = TCBWCommon.getCodeNameNCTRResult();
				Map MEDEVCMap = TCBWCommon.getCommonCodeMap("MEDEVC");
				for(Object dtlObj : objList) 
				{				
					Med2001Db dtl = (Med2001Db)dtlObj;
					String[] rowArray = new String[10];
					rowArray[0] = Common.get(dtl.getId());
					setId2(Common.get(dtl.getId()));
					rowArray[1] = Common.get(dtl.getNotifierRevDate());//通報日期
					rowArray[2] = Common.get(dtl.getApplNo());//案件編號
					rowArray[3] = Common.get(getApprovedUnits(dtl,ApprovedUnitsMap));//列管單位
					rowArray[4] = Common.get(dtl.getMedTestMedical());//醫材品名
					rowArray[5] = Common.get(dtl.getMedFactory());//製造廠
					rowArray[6] = Common.get(dtl.getMedMainCategory());//醫材主類別
					rowArray[7] = Common.get(MEDAD2Map.get(dtl.getBadReactionResults()));//不良反應結果
					rowArray[8] = Common.get(dtl.getNotifierDept());//通報單位
					rowArray[9] = Common.get("附件");//附件
	
					arrList.add(rowArray);
				}
				objList.clear();
			}
		
		}
		return arrList;
	}

	private String getApprovedUnits(Med2001Db dtl , Map ApprovedUnitsMap) {
		String approvedUnits = "";
		approvedUnits = Common.get(ApprovedUnitsMap.get(dtl.getApprovedUnits()));
		if("3".equals(dtl.getApprovedUnits()))
			approvedUnits += ":" + Common.get(dtl.getApprovedUnitsOther());
		return approvedUnits;
	}

	@Override
	public Object doQueryOne() throws Exception 
	{

		
		MEDIN5303F obj = this;
		
		
		
		return obj;
	}
	
	

	@Override
	public void doDelete() throws Exception {
		
	}
	
	@Override
	public void doCreate() throws Exception 
	{
		
	}
	
	public String getId2() {
		return checkGet(id2);
	}

	public void setId2(String id2) {
		this.id2 = checkSet(id2);
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

	public String getQ_badReactionResults() {
		return checkGet(q_badReactionResults);
	}

	public void setQ_badReactionResults(String qBadReactionResults) {
		q_badReactionResults = checkSet(qBadReactionResults);
	}


}
