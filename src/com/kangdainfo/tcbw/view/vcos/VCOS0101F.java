package com.kangdainfo.tcbw.view.vcos;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Cos7001Db;
public class VCOS0101F extends SuperBean
{
	private String q_id;
    private String applNo;
    private String q_applNoS;
	private String q_applNoE;
    private String q_prodtype;
    private String q_chProduct;
    private String q_dataRevDateS;
    private String q_dataRevDateE;
    private String q_publDept;
    private String q_publDeptCodeId;
    private String q_publDeptName;
    private String q_publCountry;
    private String q_manufactorCountry;
    private String q_ingredient;
    private String q_brand;
    private String q_situation;
    private String q_publDateS;
    private String q_publDateE;
    private String q_subjecttype;   
    private String q_lotNo;
    private String q_status;
	private String caseType;
	private String formType;
    
	public String getApplNo() {return checkGet(applNo);}	
	public void setApplNo(String applNo) {this.applNo = checkSet(applNo);}	
	public String getQ_id() {return checkGet(q_id);}
	public void setQ_id(String q_id) {this.q_id = checkSet(q_id);}	
	public String getQ_applNoS() {return checkGet(q_applNoS);}
	public void setQ_applNoS(String q_applNoS) {this.q_applNoS = checkSet(q_applNoS);}
	public String getQ_applNoE() {return checkGet(q_applNoE);}
	public void setQ_applNoE(String q_applNoE) {this.q_applNoE = checkSet(q_applNoE);	}
	public String getQ_prodtype() {return checkGet(q_prodtype);}
	public void setQ_prodtype(String q_prodtype) {this.q_prodtype = checkSet(q_prodtype);}
	public String getQ_chProduct() {return checkGet(q_chProduct);}
	public void setQ_chProduct(String q_chProduct) {this.q_chProduct = checkSet(q_chProduct);}
	public String getQ_dataRevDateS() {	return checkGet(q_dataRevDateS);}
	public void setQ_dataRevDateS(String q_dataRevDateS) {this.q_dataRevDateS = checkSet(q_dataRevDateS);}
	public String getQ_dataRevDateE() {return checkGet(q_dataRevDateE);	}
	public void setQ_dataRevDateE(String q_dataRevDateE) {this.q_dataRevDateE = checkSet(q_dataRevDateE);}
	public String getQ_publDept() {return checkGet(q_publDept);}
	public void setQ_publDept(String q_publDept) {this.q_publDept = checkSet(q_publDept);}
	public String getQ_publDeptCodeId() {return checkGet(q_publDeptCodeId);}
	public void setQ_publDeptCodeId(String q_publDeptCodeId) {this.q_publDeptCodeId = checkSet(q_publDeptCodeId);}
	public String getQ_publDeptName() {return checkGet(q_publDeptName);}
	public void setQ_publDeptName(String q_publDeptName) {this.q_publDeptName = checkSet(q_publDeptName);}
	public String getQ_publCountry() {return checkGet(q_publCountry);}
	public void setQ_publCountry(String q_publCountry) {this.q_publCountry = checkSet(q_publCountry);}
	public String getQ_manufactorCountry() {return checkGet(q_manufactorCountry);}
	public void setQ_manufactorCountry(String q_manufactorCountry) {this.q_manufactorCountry = checkSet(q_manufactorCountry);}
	public String getQ_ingredient() {return checkGet(q_ingredient);}
	public void setQ_ingredient(String q_ingredient) {this.q_ingredient = checkSet(q_ingredient);}
	public String getQ_brand() {return checkGet(q_brand);}
	public void setQ_brand(String q_brand) {this.q_brand = checkSet(q_brand);}
	public String getQ_situation() {return checkGet(q_situation);}
	public void setQ_situation(String q_situation) {this.q_situation = checkSet(q_situation);}
	public String getQ_publDateS() {return checkGet(q_publDateS);}
	public void setQ_publDateS(String q_publDateS) {	this.q_publDateS = checkSet(q_publDateS);}
	public String getQ_publDateE() {return checkGet(q_publDateE);}
	public void setQ_publDateE(String q_publDateE) {this.q_publDateE = checkSet(q_publDateE);}
	public String getQ_subjecttype() {return checkGet(q_subjecttype);}
	public void setQ_subjecttype(String q_subjecttype) {this.q_subjecttype = checkSet(q_subjecttype);}
	public String getQ_lotNo() {return checkGet(q_lotNo);}
	public void setQ_lotNo(String q_lotNo) {this.q_lotNo = checkSet(q_lotNo);}	
	public String getQ_status() {return checkGet(q_status);}
	public void setQ_status(String q_status) {this.q_status = checkSet(q_status);}	
	public String getCaseType() {return checkGet(caseType);}
	public void setCaseType(String caseType) {this.caseType = checkSet(caseType);}	
	public String getFormType() {return checkGet(formType);}
	public void setFormType(String formType) {this.formType = checkSet(formType);}
	@Override
	public Object doQueryOne() throws Exception {
		return null;
	}
	@Override
	public java.util.ArrayList<String[]> doQueryAll() throws Exception 
	{		
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		String hql = " from Cos7001Db where 1=1 and status='00' ";
		if(null != getQ_id() && !"".equals(getQ_id())){
			hql+=" and id= "+Common.sqlChar(getQ_id());
		}
	    if(!"".equals(getQ_applNoS())) 
				hql += " and applNo >= " +  Common.sqlChar(getQ_applNoS());
		if(!"".equals(getQ_applNoE())) 
				hql += " and applNo <= " + Common.sqlChar(getQ_applNoE());
		 if(!"".equals(getQ_dataRevDateS())) 
				hql += " and dataRevDate >= " +  Common.sqlChar(getQ_dataRevDateS());
		if(!"".equals(getQ_dataRevDateE())) 
				hql += " and dataRevDate <= " + Common.sqlChar(getQ_dataRevDateE());
		if(!"".equals(getQ_publDateS())) 
			hql += " and publDate >= " +  Common.sqlChar(getQ_publDateS());
	    if(!"".equals(getQ_publDateE())) 
			hql += " and publDate <= " + Common.sqlChar(getQ_publDateE());			
		if(!"".equals(getQ_status())) 
				hql += " and status = " + Common.sqlChar(getQ_status());			
		if(!"".equals(getQ_chProduct())) 
				hql += " and chProduct like " + Common.likeSqlChar(getQ_chProduct());
		if(!"".equals(getQ_publDeptCodeId())) 
				hql += " and publDept = " + Common.sqlChar(getQ_publDeptCodeId());
		if(!"".equals(getQ_ingredient())) 
			hql += " and ingredient = " + Common.sqlChar(getQ_ingredient());
		if(!"".equals(getQ_brand())) 
			hql += " and brand like " + Common.likeSqlChar( getQ_brand());		
		if(!"".equals(getQ_situation())) 
			hql += " and situation = " + Common.sqlChar(getQ_situation());		
		if(!"".equals(getQ_subjecttype())) 
			hql += " and subjecttype = " + Common.sqlChar(getQ_subjecttype());
		if(!"".equals(getQ_lotNo())) 
			hql += " and lotNo like " + Common.likeSqlChar(getQ_lotNo());		
		
		java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(hql + " order by id desc");
			if (objList != null && objList.size() > 0) {
			ServiceGetter.getInstance().getCommonService().getCommonUserDao().getHibernateTemplate().clear();
				java.util.Iterator it = objList.iterator();
				while (it.hasNext()) {
					Cos7001Db o = (Cos7001Db) it.next();
					String rowArray[] = new String[8];
					rowArray[0]=Common.get(o.getId());
					rowArray[1]=Common.get(o.getApplNo());
					rowArray[2]=Common.get(o.getChProduct());
					rowArray[3]=Common.get(o.getDataRevDate());
					rowArray[4]=Common.get(View.getCommonCodeName("CCI", o.getIngredient()));
					rowArray[5]=Common.get(View.getCommonCodeName("CONWARNING", o.getSituation()));
					rowArray[6]=Common.get(o.getPublDate());
					rowArray[7]=Common.get(View.getCommonCodeName("COSSJTYPE", o.getSubjecttype()));					
					arrList.add(rowArray);
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
