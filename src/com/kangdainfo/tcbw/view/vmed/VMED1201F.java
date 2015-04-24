package com.kangdainfo.tcbw.view.vmed;


import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Med7001Db;
import com.kangdainfo.tcbw.model.bo.Med7005Db;
import com.kangdainfo.tcbw.util.TCBWCommon;


public class VMED1201F extends SuperBean
{
	String q_applNoS;//案件編號起
	String q_applNoE;//案件編號迄
	String q_medMainCategory, q_medMainCategoryName, q_medMainCategoryCodeId;//醫材主類別
	String q_chProduct;//產品名稱
	String q_publDateS, q_publDateE;//發布日期起迄
	String q_chProduct7002;//中文品名
	
	String q_fdareceiveNo;//fda收文文號
	String q_posesummary;//回文摘要
	String q_completereceiveno;//廠商回收完成字號


	public String getQ_applNoS(){ return checkGet(q_applNoS); }
	public void setQ_applNoS(String s){ q_applNoS=checkSet(s); }
	public String getQ_applNoE(){ return checkGet(q_applNoE); }
	public void setQ_applNoE(String s){ q_applNoE=checkSet(s); }
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
	public String getQ_chProduct7002() {return checkGet(q_chProduct7002);}
	public void setQ_chProduct7002(String qChProduct7002) {q_chProduct7002 = checkSet(qChProduct7002);}
	public String getQ_fdareceiveNo() {return checkGet(q_fdareceiveNo);}
	public void setQ_fdareceiveNo(String qFdareceiveNo) {q_fdareceiveNo = checkSet(qFdareceiveNo);}
	public String getQ_posesummary() {return checkGet(q_posesummary);}
	public void setQ_posesummary(String qPosesummary) {q_posesummary = checkSet(qPosesummary);}
	public String getQ_completereceiveno() {return checkGet(q_completereceiveno);}
	public void setQ_completereceiveno(String qCompletereceiveno) {q_completereceiveno = checkSet(qCompletereceiveno);}
	
	
	
	@Override
	public Object doQueryOne() throws Exception {
		
		VMED1201F obj = this;
		return obj;
	}

	@Override
	public Object doQueryAll() throws Exception 
	{		
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();

		String hql = " from Med7001Db where  isrecycle='Y' ";
		
		if (!"".equals(getQ_applNoS()))
			hql += " and applNo >= " + Common.sqlChar(getQ_applNoS());
		if (!"".equals(getQ_applNoE()))
			hql += " and applNo <= " + Common.sqlChar(getQ_applNoE());
		if (!"".equals(getQ_medMainCategory()))
			hql += " and medMainCategory=" + Common.sqlChar(getQ_medMainCategory());
		if (!"".equals(getQ_chProduct()))
			hql += " and chProduct like " + Common.likeSqlChar(getQ_chProduct());
		if (!"".equals(getQ_publDateS()))
			hql += " and publDate >= " + Common.sqlChar(getQ_publDateS());
		if (!"".equals(getQ_publDateE()))
			hql += " and publDate <= " + Common.sqlChar(getQ_publDateE());
		if (!"".equals(getQ_fdareceiveNo()))
			hql += " and fdareceiveNo=" + Common.sqlChar(getQ_fdareceiveNo());
		if (!"".equals(getQ_posesummary()))
			hql += " and posesummary like " + Common.likeSqlChar(getQ_posesummary());
		if (!"".equals(getQ_completereceiveno()))
			hql += " and completereceiveno=" + Common.sqlChar(getQ_completereceiveno());

		System.out.println("VMED1201F====="+hql);
		
		this.processCurrentPageAttribute(ServiceGetter.getInstance().getCommonService().loadCount(hql));
		
		if (getTotalRecord() > 0) 
		{
			if (getState().indexOf("query")<0) 
				ServiceGetter.getInstance().getCommonService().getHibernateTemplate().clear();
			
			java.util.List objList = ServiceGetter.getInstance().getCommonService().load(hql+" order by id desc", this.getRecordStart(), this.getPageSize());
			if (objList != null && objList.size() > 0) 
			{
				java.util.Iterator it = objList.iterator();
				java.util.Map<String, String> medMainCategoryMap = TCBWCommon.getCommonCodeMap("MEDMCT");
				
				while (it.hasNext()) 
				{
					Med7001Db o = (Med7001Db) it.next();
					String rowArray[] = new String[8];
					rowArray[0] = Common.get(o.getId());
					rowArray[1] = Common.get(o.getApplNo());
					rowArray[2] = Common.get(medMainCategoryMap.get(o.getMedMainCategory()));
					rowArray[3] = Common.get(o.getChProduct());
					rowArray[4] = Common.get(o.getPublDate());
					rowArray[5] = Common.get(o.getFdareceiveDate());//fda收文日期
					rowArray[6] = Common.get(o.getPostdate());//FDA回文日期
					rowArray[7] = Common.get(o.getCompleterecycledate());//廠商回收完成日期
					
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
