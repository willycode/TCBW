package com.kangdainfo.tcbw.view.vmed;


import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Med7001Db;


public class VMED0501F extends SuperBean
{
	String q_applNoS;//案件編號起
	String q_applNoE;//案件編號迄
	String q_publDept, q_publDeptName, q_publDeptCodeId;//發布單位
	String q_situation;//警訊類別
	String q_medMainCategory, q_medMainCategoryName, q_medMainCategoryCodeId;//醫材主類別
	String q_chProduct;//產品名稱
	String q_publDateS, q_publDateE;//發布日期起迄

	public String getQ_applNoS(){ return checkGet(q_applNoS); }
	public void setQ_applNoS(String s){ q_applNoS=checkSet(s); }
	public String getQ_applNoE(){ return checkGet(q_applNoE); }
	public void setQ_applNoE(String s){ q_applNoE=checkSet(s); }
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
	public String getQ_publDateS(){ return checkGet(q_publDateS); }
	public void setQ_publDateS(String s){ q_publDateS=checkSet(s); }
	public String getQ_publDateE(){ return checkGet(q_publDateE); }
	public void setQ_publDateE(String s){ q_publDateE=checkSet(s); }
	
	
	@Override
	public Object doQueryOne() throws Exception {
		
		VMED0501F obj = this;
		return obj;
	}

	@Override
	public Object doQueryAll() throws Exception 
	{		
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();

		String hql = " from Med7001Db where status ='30' ";
		
		if (!"".equals(getQ_applNoS()))
			hql += " and applNo >= " + Common.sqlChar(getQ_applNoS());
		if (!"".equals(getQ_applNoE()))
			hql += " and applNo <= " + Common.sqlChar(getQ_applNoE());
		if (!"".equals(getQ_publDeptCodeId()))
			hql += " and publDept=" + Common.sqlChar(getQ_publDeptCodeId());
		if (!"".equals(getQ_situation()))
			hql += " and situation=" + Common.sqlChar(getQ_situation());
		if (!"".equals(getQ_medMainCategory()))
			hql += " and medMainCategory=" + Common.sqlChar(getQ_medMainCategory());
		if (!"".equals(getQ_chProduct()))
			hql += " and chProduct like " + Common.likeSqlChar(getQ_chProduct());
		if (!"".equals(getQ_publDateS()))
			hql += " and publDate >= " + Common.sqlChar(getQ_publDateS());
		if (!"".equals(getQ_publDateE()))
			hql += " and publDate <= " + Common.sqlChar(getQ_publDateE());
	
		
		System.out.println("VMED0501F====="+hql);
		
		this.processCurrentPageAttribute(ServiceGetter.getInstance().getCommonService().loadCount(hql));
		
		if (getTotalRecord() > 0) 
		{
			if (getState().indexOf("query")<0) 
				ServiceGetter.getInstance().getCommonService().getHibernateTemplate().clear();
			
			java.util.List objList = ServiceGetter.getInstance().getCommonService().load(hql+" order by id desc", this.getRecordStart(), this.getPageSize());
			if (objList != null && objList.size() > 0) {
				java.util.Iterator it = objList.iterator();
				while (it.hasNext()) {
					Med7001Db o = (Med7001Db) it.next();
					String rowArray[] = new String[7];
					rowArray[0] = Common.get(o.getId());
					rowArray[1] = Common.get(o.getApplNo());
					rowArray[2] = View.getCommonCodeName("CONPUBLDEPT",o.getPublDept());
					rowArray[3] = View.getCommonCodeName("CONWARNING",o.getSituation());
					rowArray[4] = View.getCommonCodeName("MEDMCT",o.getMedMainCategory());
					rowArray[5] = Common.get(o.getChProduct());
					rowArray[6] = Common.get(o.getPublDate());
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
