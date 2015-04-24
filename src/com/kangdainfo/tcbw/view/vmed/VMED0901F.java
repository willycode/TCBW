package com.kangdainfo.tcbw.view.vmed;


import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Med7001Db;
import com.kangdainfo.tcbw.model.bo.Med7005Db;


public class VMED0901F extends SuperBean
{
	String q_applNoS;//案件編號起
	String q_applNoE;//案件編號迄
	String q_publDept, q_publDeptName, q_publDeptCodeId;//發布單位
	String q_situation;//警訊類別
	String q_medMainCategory, q_medMainCategoryName, q_medMainCategoryCodeId;//醫材主類別
	String q_chProduct;//產品名稱
	String q_publDateS, q_publDateE;//發布日期起迄
	
	String q_publdateS, q_publdateE;//公告日期起迄
	String q_ispublnews;//是否發佈新聞稿
	String q_ispublconsumer;//是否發佈消費者知識服務網
	String q_isfdaweb;//是否發佈署網
	String q_lamp;//燈號


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

	
	public String getQ_publdateS() {return checkGet(q_publdateS);}
	public void setQ_publdateS(String qPubldateS) {q_publdateS = checkSet(qPubldateS);}
	public String getQ_publdateE() {return checkGet(q_publdateE);}
	public void setQ_publdateE(String qPubldateE) {q_publdateE = checkSet(qPubldateE);}
	public String getQ_ispublnews() {return checkGet(q_ispublnews);}
	public void setQ_ispublnews(String qIspublnews) {q_ispublnews = checkSet(qIspublnews);}
	public String getQ_ispublconsumer() {return checkGet(q_ispublconsumer);}
	public void setQ_ispublconsumer(String qIspublconsumer) {q_ispublconsumer = checkSet(qIspublconsumer);}
	public String getQ_isfdaweb() {return checkGet(q_isfdaweb);}
	public void setQ_isfdaweb(String qIsfdaweb) {q_isfdaweb = checkSet(qIsfdaweb);}
	public String getQ_lamp() {return checkGet(q_lamp);}
	public void setQ_lamp(String qLamp) {q_lamp = checkSet(qLamp);}
	
	
	@Override
	public Object doQueryOne() throws Exception {
		
		VMED0901F obj = this;
		return obj;
	}

	@Override
	public Object doQueryAll() throws Exception 
	{		
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();

		String hql = " from Med7005Db where med7001Db.status='50'  ";
		

		if (!"".equals(getQ_applNoS()))
			hql += " and med7001Db.applNo >= " + Common.sqlChar(getQ_applNoS());
		if (!"".equals(getQ_applNoE()))
			hql += " and med7001Db.applNo <= " + Common.sqlChar(getQ_applNoE());
		if (!"".equals(getQ_publDeptCodeId()))
			hql += " and med7001Db.publDept=" + Common.sqlChar(getQ_publDeptCodeId());
		if (!"".equals(getQ_situation()))
			hql += " and med7001Db.situation=" + Common.sqlChar(getQ_situation());
		if (!"".equals(getQ_medMainCategory()))
			hql += " and med7001Db.medMainCategory=" + Common.sqlChar(getQ_medMainCategory());
		if (!"".equals(getQ_chProduct()))
			hql += " and med7001Db.chProduct like " + Common.likeSqlChar(getQ_chProduct());
		if (!"".equals(getQ_publDateS()))
			hql += " and med7001Db.publDate >= " + Common.sqlChar(getQ_publDateS());
		if (!"".equals(getQ_publDateE()))
			hql += " and med7001Db.publDate <= " + Common.sqlChar(getQ_publDateE());
	
		if (!"".equals(getQ_publdateS()))
			hql += " and publdate >= " + Common.sqlChar(getQ_publdateS());
		if (!"".equals(getQ_publdateE()))
			hql += " and publdate <= " + Common.sqlChar(getQ_publdateE());
		
		if (!"".equals(getQ_ispublnews()))
			hql += " and ispublnews = " + Common.sqlChar(getQ_ispublnews());
		
		if (!"".equals(getQ_ispublconsumer()))
			hql += " and ispublconsumer = " + Common.sqlChar(getQ_ispublconsumer());
		
		if (!"".equals(getQ_isfdaweb()))
			hql += " and isfdaweb = " + Common.sqlChar(getQ_isfdaweb());
		
		if (!"".equals(getQ_lamp()))
			hql += " and lamp = " + Common.sqlChar(getQ_lamp());

		System.out.println("VMED0901F====="+hql);
		
		this.processCurrentPageAttribute(ServiceGetter.getInstance().getCommonService().loadCount(hql));
		
		if (getTotalRecord() > 0) 
		{
			if (getState().indexOf("query")<0) 
				ServiceGetter.getInstance().getCommonService().getHibernateTemplate().clear();
			
			java.util.List objList = ServiceGetter.getInstance().getCommonService().load(hql+" order by id desc", this.getRecordStart(), this.getPageSize());
			if (objList != null && objList.size() > 0) 
			{
				java.util.Iterator it = objList.iterator();
				
				while (it.hasNext()) 
				{
					Med7005Db o = (Med7005Db) it.next();
					String rowArray[] = new String[7];
					rowArray[0] = Common.get(o.getMed7001Db().getId());
					rowArray[1] = Common.get(o.getMed7001Db().getApplNo());
					rowArray[2] = View.getCommonCodeName("CONPUBLDEPT",o.getMed7001Db().getPublDept());
					rowArray[3] = View.getCommonCodeName("CONWARNING",o.getMed7001Db().getSituation());
					rowArray[4] = View.getCommonCodeName("MEDMCT",o.getMed7001Db().getMedMainCategory());
					rowArray[5] = Common.get(o.getMed7001Db().getChProduct());
					rowArray[6] = Common.get(o.getMed7001Db().getPublDate());
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
