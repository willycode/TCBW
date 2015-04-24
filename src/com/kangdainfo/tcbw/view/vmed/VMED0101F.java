package com.kangdainfo.tcbw.view.vmed;


import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Med7001Db;
/**
*<br>程式目的：醫療器材國內外安全警訊監控登錄作業
*<br>程式代號：vmed0101f
*<br>程式日期：1030512
*<br>程式作者：yuwen
*<br>--------------------------------------------------------
*<br>修改作者　　修改日期　　　修改目的
*<br>--------------------------------------------------------
*/


public class VMED0101F extends SuperBean
{
	
	String q_applNoS;
	String q_applNoE;
	String q_status;
	String q_publDept;
	String q_publDeptName;
	String q_publDeptCodeId;
	String q_situation;
	String q_medMainCategory;
	String q_medMainCategoryName;
	String q_medMainCategoryCodeId;
	String q_chProduct;
	String q_recycleclass;
	String q_dataRevDateS, q_dataRevDateE;
	String q_publDateS;
	String q_publDateE;
	String q_manufactorName;
	String q_contextsummary;
	String q_productlotNo;
	String q_effectarea;

	public String getQ_applNoS(){ return checkGet(q_applNoS); }
	public void setQ_applNoS(String s){ q_applNoS=checkSet(s); }
	public String getQ_applNoE(){ return checkGet(q_applNoE); }
	public void setQ_applNoE(String s){ q_applNoE=checkSet(s); }
	public String getQ_status(){ return checkGet(q_status); }
	public void setQ_status(String s){ q_status=checkSet(s); }
	public String getQ_publDept(){ return checkGet(q_publDept); }
	public void setQ_publDept(String s){ q_publDept=checkSet(s); }
	public String getQ_publDeptName(){ return checkGet(q_publDeptName); }
	public void setQ_publDeptName(String s){ q_publDeptName=checkSet(s); }
	public String getQ_publDeptCodeId(){ return checkGet(q_publDeptCodeId); }
	public void setQ_publDeptCodeId(String s){ q_publDeptCodeId=checkSet(s); }
	public String getQ_situation(){ return checkGet(q_situation); }
	public void setQ_situation(String s){ q_situation=checkSet(s); }
	public String getQ_medMainCategory(){ return checkGet(q_medMainCategory); }
	public void setQ_medMainCategory(String s){ q_medMainCategory=checkSet(s); }
	public String getQ_medMainCategoryName(){ return checkGet(q_medMainCategoryName); }
	public void setQ_medMainCategoryName(String s){ q_medMainCategoryName=checkSet(s); }
	public String getQ_medMainCategoryCodeId(){ return checkGet(q_medMainCategoryCodeId); }
	public void setQ_medMainCategoryCodeId(String s){ q_medMainCategoryCodeId=checkSet(s); }
	public String getQ_chProduct(){ return checkGet(q_chProduct); }
	public void setQ_chProduct(String s){ q_chProduct=checkSet(s); }
	public String getQ_recycleclass(){ return checkGet(q_recycleclass); }
	public void setQ_recycleclass(String s){ q_recycleclass=checkSet(s); }
	public String getQ_dataRevDateS(){ return checkGet(q_dataRevDateS); }
	public void setQ_dataRevDateS(String s){ q_dataRevDateS=checkSet(s); }
	public String getQ_dataRevDateE(){ return checkGet(q_dataRevDateE); }
	public void setQ_dataRevDateE(String s){ q_dataRevDateE=checkSet(s); }
	public String getQ_publDateS(){ return checkGet(q_publDateS); }
	public void setQ_publDateS(String s){ q_publDateS=checkSet(s); }
	public String getQ_publDateE(){ return checkGet(q_publDateE); }
	public void setQ_publDateE(String s){ q_publDateE=checkSet(s); }
	public String getQ_manufactorName(){ return checkGet(q_manufactorName); }
	public void setQ_manufactorName(String s){ q_manufactorName=checkSet(s); }
	public String getQ_contextsummary(){ return checkGet(q_contextsummary); }
	public void setQ_contextsummary(String s){ q_contextsummary=checkSet(s); }
	public String getQ_productlotNo(){ return checkGet(q_productlotNo); }
	public void setQ_productlotNo(String s){ q_productlotNo=checkSet(s); }
	public String getQ_effectarea(){ return checkGet(q_effectarea); }
	public void setQ_effectarea(String s){ q_effectarea=checkSet(s); }
	
	@Override
	public Object doQueryOne() throws Exception {
		
		VMED0101F obj = this;
		
		return obj;
	}

	@Override
	public Object doQueryAll() throws Exception 
	{		
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();

		String hql = " from Med7001Db  ";
		if (!"".equals(getQ_applNoS()))
			hql += " and applNo >= " + Common.sqlChar(getQ_applNoS());
		if (!"".equals(getQ_applNoE()))
			hql += " and applNo <= " + Common.sqlChar(getQ_applNoE());
		if (!"".equals(getQ_status()))
			hql += " and status=" + Common.sqlChar(getQ_status());
		if (!"".equals(getQ_publDeptCodeId()))
			hql += " and publDept=" + Common.sqlChar(getQ_publDeptCodeId());
		if (!"".equals(getQ_situation()))
			hql += " and situation=" + Common.sqlChar(getQ_situation());
		if (!"".equals(getQ_medMainCategory()))
			hql += " and medMainCategory=" + Common.sqlChar(getQ_medMainCategory());
		if (!"".equals(getQ_chProduct()))
			hql += " and chProduct like " + Common.likeSqlChar(getQ_chProduct());
		if (!"".equals(getQ_recycleclass()))
			hql += " and recycleclass=" + Common.sqlChar(getQ_recycleclass());
		if (!"".equals(getQ_dataRevDateS()))
			hql += " and dataRevDate >= " + Common.sqlChar(getQ_dataRevDateS());
		if (!"".equals(getQ_dataRevDateE()))
			hql += " and dataRevDate <= " + Common.sqlChar(getQ_dataRevDateE());
		if (!"".equals(getQ_publDateS()))
			hql += " and publDate >= " + Common.sqlChar(getQ_publDateS());
		if (!"".equals(getQ_publDateE()))
			hql += " and publDate <= " + Common.sqlChar(getQ_publDateE());
		if (!"".equals(getQ_manufactorName()))
			hql += " and manufactorName like " + Common.likeSqlChar(getQ_manufactorName());
		if (!"".equals(getQ_contextsummary()))
			hql += " and contextsummary like " + Common.likeSqlChar(getQ_contextsummary());
		if (!"".equals(getQ_productlotNo()))
			hql += " and productlotNo like " + Common.likeSqlChar(getQ_productlotNo());
		if (!"".equals(getQ_effectarea()))
			hql += " and effectarea=" + Common.sqlChar(getQ_effectarea());
		this.processCurrentPageAttribute(ServiceGetter.getInstance().getCommonService().loadCount(hql));
		if (getTotalRecord() > 0) {
			if (getState().indexOf("query")<0) 
				ServiceGetter.getInstance().getCommonService().getHibernateTemplate().clear();
			
			java.util.List objList = ServiceGetter.getInstance().getCommonService().load(hql+" order by id desc", this.getRecordStart(), this.getPageSize());
			if (objList != null && objList.size() > 0) {
				java.util.Iterator it = objList.iterator();
				while (it.hasNext()) {
					Med7001Db o = (Med7001Db) it.next();
					String rowArray[] = new String[8];
					rowArray[0] = Common.get(o.getId());
					rowArray[1] = Common.get(o.getApplNo());
					rowArray[2] = View.getCommonCodeName("MEDQTSTATUS",o.getStatus());
					rowArray[3] = View.getCommonCodeName("CONPUBLDEPT",o.getPublDept());
					rowArray[4] = View.getCommonCodeName("CONWARNING",o.getSituation());
					rowArray[5] = View.getCommonCodeName("MEDMCT",o.getMedMainCategory());
					rowArray[6] = Common.get(o.getChProduct());
					rowArray[7] = Common.get(o.getPublDate());
					arrList.add(rowArray);					
				}
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
