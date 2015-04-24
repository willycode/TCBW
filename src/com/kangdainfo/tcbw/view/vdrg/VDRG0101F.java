package com.kangdainfo.tcbw.view.vdrg;

import java.util.Map;
import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.model.bo.*;
import com.kangdainfo.common.util.*;
import com.kangdainfo.tcbw.model.bo.*;

/**
*<br>程式目的：國內外藥品品質警訊警訊登錄作業
*<br>程式代號：vdrg0101q
*<br>程式日期：1030424
*<br>程式作者：yuwen
*<br>--------------------------------------------------------
*<br>修改作者　　修改日期　　　修改目的
*<br>--------------------------------------------------------
*/

public class VDRG0101F extends SuperBean{


String id;

String q_applNoS, q_applNoE;
String q_dataRevDateS, q_dataRevDateE;
String q_status;
String q_publDateS, q_publDateE;
String q_publDept;
String q_publDeptName;
String q_publDeptCodeId;
String q_istransfer;
String[] q_situation;
String q_chProduct;
String q_scientificName;
String q_druggist;
String q_permitKey;
String q_permitNo;
String q_manufactorName;
String q_applicationName;
String q_lotNo;
String[] q_qualitywarningtype;
String q_isImport;
String[] q_recycleType;
String[] q_lamp;
String q_aftereffect;
String q_iswarning;

public String getId(){ return checkGet(id); }
public void setId(String s){ id=checkSet(s); }

public String getQ_applNoS(){ return checkGet(q_applNoS); }
public void setQ_applNoS(String s){ q_applNoS=checkSet(s); }
public String getQ_applNoE(){ return checkGet(q_applNoE); }
public void setQ_applNoE(String s){ q_applNoE=checkSet(s); }
public String getQ_dataRevDateS(){ return checkGet(q_dataRevDateS); }
public void setQ_dataRevDateS(String s){ q_dataRevDateS=checkSet(s); }
public String getQ_dataRevDateE(){ return checkGet(q_dataRevDateE); }
public void setQ_dataRevDateE(String s){ q_dataRevDateE=checkSet(s); }
public String getQ_status(){ return checkGet(q_status); }
public void setQ_status(String s){ q_status=checkSet(s); }
public String getQ_publDateS(){ return checkGet(q_publDateS); }
public void setQ_publDateS(String s){ q_publDateS=checkSet(s); }
public String getQ_publDateE(){ return checkGet(q_publDateE); }
public void setQ_publDateE(String s){ q_publDateE=checkSet(s); }
public String getQ_publDept(){ return checkGet(q_publDept); }
public void setQ_publDept(String s){ q_publDept=checkSet(s); }
public String getQ_publDeptName(){ return checkGet(q_publDeptName); }
public void setQ_publDeptName(String s){ q_publDeptName=checkSet(s); }
public String getQ_publDeptCodeId(){ return checkGet(q_publDeptCodeId); }
public void setQ_publDeptCodeId(String s){ q_publDeptCodeId=checkSet(s); }
public String getQ_istransfer(){ return checkGet(q_istransfer); }
public void setQ_istransfer(String s){ q_istransfer=checkSet(s); }
public String[] getQ_situation(){ return q_situation; }
public void setQ_situation(String[] s){ q_situation = s; }
public String getQ_chProduct(){ return checkGet(q_chProduct); }
public void setQ_chProduct(String s){ q_chProduct=checkSet(s); }
public String getQ_scientificName(){ return checkGet(q_scientificName); }
public void setQ_scientificName(String s){ q_scientificName=checkSet(s); }
public String getQ_druggist(){ return checkGet(q_druggist); }
public void setQ_druggist(String s){ q_druggist=checkSet(s); }
public String getQ_permitKey(){ return checkGet(q_permitKey); }
public void setQ_permitKey(String s){ q_permitKey=checkSet(s); }
public String getQ_permitNo(){ return checkGet(q_permitNo); }
public void setQ_permitNo(String s){ q_permitNo=checkSet(s); }
public String getQ_manufactorName(){ return checkGet(q_manufactorName); }
public void setQ_manufactorName(String s){ q_manufactorName=checkSet(s); }
public String getQ_applicationName(){ return checkGet(q_applicationName); }
public void setQ_applicationName(String s){ q_applicationName=checkSet(s); }
public String getQ_lotNo(){ return checkGet(q_lotNo); }
public void setQ_lotNo(String s){ q_lotNo=checkSet(s); }
public String[] getQ_qualitywarningtype() {return checkGet(q_qualitywarningtype);}
public void setQ_qualitywarningtype(String[] qQualitywarningtype) {q_qualitywarningtype = checkSet(qQualitywarningtype);}
public String getQ_isImport(){ return checkGet(q_isImport); }
public void setQ_isImport(String s){ q_isImport=checkSet(s); }
public String[] getQ_recycleType(){ return q_recycleType; }
public void setQ_recycleType(String[] s){ q_recycleType=s; }
public String[] getQ_lamp(){ return q_lamp; }
public void setQ_lamp(String[] s){ q_lamp=s; }
public String getQ_aftereffect(){ return checkGet(q_aftereffect); }
public void setQ_aftereffect(String s){ q_aftereffect=checkSet(s); }
public String getQ_iswarning(){ return checkGet(q_iswarning); }
public void setQ_iswarning(String s){ q_iswarning=checkSet(s); }

@Override
public Object doQueryOne() throws Exception {
	VDRG0101F obj = this;
	return obj;
}
@Override
public Object doQueryAll() throws Exception {
	java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();

	String hql = " from Drg7001Db where status='00' ";
	if (!"".equals(getQ_applNoS()))
		hql += " and applNo >= " + Common.sqlChar(getQ_applNoS());
	if (!"".equals(getQ_applNoE()))
		hql += " and applNo <= " + Common.sqlChar(getQ_applNoE());
	if (!"".equals(getQ_dataRevDateS()))
		hql += " and dataRevDate >= " + Common.sqlChar(getQ_dataRevDateS());
	if (!"".equals(getQ_dataRevDateE()))
		hql += " and dataRevDate <= " + Common.sqlChar(getQ_dataRevDateE());
	if (!"".equals(getQ_status()))
		hql += " and status=" + Common.sqlChar(getQ_status());
	if (!"".equals(getQ_publDateS()))
		hql += " and publDate >= " + Common.sqlChar(getQ_publDateS());
	if (!"".equals(getQ_publDateE()))
		hql += " and publDate <= " + Common.sqlChar(getQ_publDateE());
	if (!"".equals(getQ_publDept()))
		hql += " and publDept=" + Common.sqlChar(getQ_publDept());
	if (!"".equals(getQ_istransfer()))
		hql += " and istransfer=" + Common.sqlChar(getQ_istransfer());
	if (null != getQ_situation() && !"".equals(getQ_situation())){
		String q_situationStr = "";
		for(String q_situation : getQ_situation()){
			if(q_situationStr.length() > 0) q_situationStr += ",";
			q_situationStr += Common.sqlChar(q_situation);
		}
		hql += " and situation in (" + q_situationStr + ")";
	}
	if (!"".equals(getQ_chProduct()))
		hql += " and chProduct like " + Common.likeSqlChar(getQ_chProduct());
	if (!"".equals(getQ_scientificName()))
		hql += " and scientificName like " + Common.likeSqlChar(getQ_scientificName());
	if (!"".equals(getQ_druggist()))
		hql += " and druggist like " + Common.likeSqlChar(getQ_druggist());
	if (!"".equals(getQ_permitKey()))
		hql += " and id in (select drg7001Db.id from Drg7003Db where permitKey=" + Common.sqlChar(getQ_permitKey())+")";
	if (!"".equals(getQ_permitNo()))
		hql += " and id in (select drg7001Db.id from Drg7003Db where permitNo=" + Common.sqlChar(getQ_permitNo())+")";
	if (!"".equals(getQ_manufactorName()))
		hql += " and manufactorName like " + Common.likeSqlChar(getQ_manufactorName());
	if (!"".equals(getQ_applicationName()))
		hql += " and id in (select drg7001Db.id from Drg7003Db where applicationName like " + Common.likeSqlChar(getQ_applicationName())+")";
	if (!"".equals(getQ_lotNo()))
		hql += " and lotNo=" + Common.sqlChar(getQ_lotNo());
	
	//if (!"".equals(getQ_qualitywarningtype()))
		//hql += " and qualitywarningtype=" + Common.sqlChar(getQ_qualitywarningtype());
	
	if(!"".equals(Common.get(getQ_qualitywarningtype())))
	{
		hql +=" and (";
		for(int i=0;i<getQ_qualitywarningtype().length;i++)
		{
			if(i!=0) 
				hql += " or ";
			hql += " qualitywarningtype like " + Common.likeSqlChar(getQ_qualitywarningtype()[i]);					
		}
		hql += ")";
	}
	
	
	if (!"".equals(getQ_isImport()))
		hql += " and id in (select drg7001Db.id from Drg7002Db where isImport=" + Common.sqlChar(getQ_isImport())+")";
	
	if (null != getQ_recycleType() && !"".equals(getQ_recycleType())){
		String q_recycleTypeStr = "";
		for(String q_recycleType : getQ_recycleType()){
			if(q_recycleTypeStr.length() > 0) q_recycleTypeStr += ",";
			q_recycleTypeStr += Common.sqlChar(q_recycleType);
		}
		hql += " and recycleType in (" + q_recycleTypeStr + ")";
	}
	if (null != getQ_lamp() && !"".equals(getQ_lamp())){
		String q_lampStr = "";
		for(String q_lamp : getQ_lamp()){
			if(q_lampStr.length() > 0) q_lampStr += ",";
			q_lampStr += Common.sqlChar(q_lamp);
		}
		hql += " and lamp in (" + q_lampStr + ")";
	}
	if (!"".equals(getQ_aftereffect()))
		hql += " and aftereffect=" + Common.sqlChar(getQ_aftereffect());
	if (!"".equals(getQ_iswarning()))
		hql += " and iswarning=" + Common.sqlChar(getQ_iswarning());
	this.processCurrentPageAttribute(ServiceGetter.getInstance().getCommonService().loadCount(hql));
	
	System.out.println("hql===="+hql);
	
	if (getTotalRecord() > 0) {
		if (getState().indexOf("query")<0) 
			ServiceGetter.getInstance().getCommonService().getHibernateTemplate().clear();
		
		java.util.List objList = ServiceGetter.getInstance().getCommonService().load(hql+" order by id desc", this.getRecordStart(), this.getPageSize());
		if (objList != null && objList.size() > 0) {
			java.util.Iterator it = objList.iterator();
			while (it.hasNext()) {
				Drg7001Db o = (Drg7001Db) it.next();
				String rowArray[] = new String[8];
				rowArray[0] = Common.get(o.getId());
				rowArray[1] = Common.get(o.getApplNo());
				rowArray[2] = Common.get(o.getDataRevDate());
				rowArray[3] = View.getCommonCodeName("CONPUBLDEPT", Common.get(o.getPublDept()));
				rowArray[4] = View.getCommonCodeName("CONWARNING", Common.get(o.getSituation()));
				rowArray[5] = Common.get(o.getChProduct());
				rowArray[6] = View.getCommonCodeName("DRGRECTP", Common.get(o.getRecycleType()));
				rowArray[7] = Common.get(o.getEventDesc());
				arrList.add(rowArray);					
			}
		}
	}
	return arrList;
}

@Override
public void doCreate() throws Exception {}

@Override
public void doUpdate() throws Exception {}

@Override
public void doDelete() throws Exception {}

}


