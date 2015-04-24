package com.kangdainfo.tcbw.util;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.model.bo.CommonAuditLogHistory;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.tcbw.model.bo.Con1015Db;

/**
*<br>程式目的：改派POP視窗
*<br>程式代號：popChargeMan
*<br>程式日期：1001109
*<br>程式作者：yuwen
*<br>--------------------------------------------------------
*<br>修改作者　　修改日期　　　修改目的
*<br>
*<br>--------------------------------------------------------
*/

public class ChargeManSearch extends SuperBean{
String id ; 
String q_userid;
String q_username;
String q_code;
String q_formCode;

public String getId() {	return checkGet(id);}
public void setId(String s) {this.id = checkSet(s);}

public String getQ_userid() {return checkGet(q_userid);}
public void setQ_userid(String s) {this.q_userid = checkSet(s);}
public String getQ_username() {return checkGet(q_username);}
public void setQ_username(String s) {this.q_username = checkSet(s);}
public String getQ_code() {return checkGet(q_code);}
public void setQ_code(String s) {q_code = checkSet(s);}
public String getQ_formCode() {return checkGet(q_formCode);}
public void setQ_formCode(String s) {q_formCode = checkSet(s);}

@Override
public Object doQueryOne() throws Exception {
	// TODO Auto-generated method stub
	return null;
}
@Override
public Object doQueryAll() throws Exception {
	java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
	String hql = " from Con1015Db ";
	hql += " where con1014Db.code = " + Common.sqlChar(getQ_code());   
	hql += " and con1014Db.con1007Db.formCode = " + Common.sqlChar(getQ_formCode());
	hql += " and competence like '%1%'";
	
	if(!"".equals(getQ_userid()))
		hql += " and commonUser.userId = " + Common.sqlChar(getQ_userid());
    
	if(!"".equals(getQ_username()))
		hql += " and commonUser.userName like " + Common.likeSqlCharEnd(getQ_username(),true);

	System.out.println(hql);
	this.processCurrentPageAttribute(ServiceGetter.getInstance().getTcbwService().loadCount(hql));

	if (getTotalRecord() > 0) {
		if (getState().indexOf("query")<0) 
			ServiceGetter.getInstance().getCommonService().getHibernateTemplate().clear();
		java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(hql + " order by id", this.getRecordStart(), this.getPageSize());
		
		if (objList != null && objList.size() > 0){
			for(Object dtlObj : objList) 
			{				
				Con1015Db dtl = (Con1015Db)dtlObj;
				String[] rowArray = new String[5];
				rowArray[0] = Common.get(dtl.getId());		
				rowArray[1] = Common.get(dtl.getCommonUser().getUserId());						
				rowArray[2] = Common.get(dtl.getCommonUser().getUserName());						
				arrList.add(rowArray);
			}
		}
	}
	return arrList;
}
@Override
public void doCreate() throws Exception {
	// TODO Auto-generated method stub
	
}
@Override
public void doUpdate() throws Exception {
	// TODO Auto-generated method stub
	
}
@Override
public void doDelete() throws Exception {
	// TODO Auto-generated method stub
	
}

}


