package com.kangdainfo.tcbw.util;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.model.bo.CommonAuditLogHistory;
import com.kangdainfo.common.model.bo.CommonUser;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.tcbw.model.bo.Drg6001Db;

/**
*<br>程式目的：案件歷史版次POP視窗
*<br>程式代號：popDrg4001
*<br>程式日期：1021201
*<br>程式作者：yuwen
*<br>--------------------------------------------------------
*<br>修改作者　　修改日期　　　修改目的
*<br>
*<br>--------------------------------------------------------
*/

public class Drg6001DbSearch extends SuperBean{
	
String id ; 

String q_id;

public String getId() {	return checkGet(id);}
public void setId(String s) {this.id = checkSet(s);}



public String getQ_id() {return checkGet(q_id);}
public void setQ_id(String s) {this.q_id = checkSet(s);}

@Override
public Object doQueryOne() throws Exception {
	// TODO Auto-generated method stub
	return null;
}
@Override
public Object doQueryAll() throws Exception {
	java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
	if(null != getQ_id() && !"".equals(getQ_id())){
		String hql = " from Drg6001Db where 1=1 ";
		hql += " and drg4001Id = " + Common.sqlChar(getQ_id());

		this.processCurrentPageAttribute(ServiceGetter.getInstance().getCommonService().loadCount(hql));
		if (getTotalRecord() > 0) {
			if (getState().indexOf("query")<0) 
				ServiceGetter.getInstance().getCommonService().getHibernateTemplate().clear();
			
			List objList = ServiceGetter.getInstance().getCommonService().load(hql+" order by revision desc", this.getRecordStart(), this.getPageSize());
			if (objList != null && !objList.isEmpty()) {
				Iterator it = objList.iterator();
				while (it.hasNext()) {
					Drg6001Db dtl = (Drg6001Db) it.next();
					String rowArray[] = new String[5];	
					rowArray[0] = Common.get(dtl.getId());											
					rowArray[1] = Common.get(dtl.getApplNo());	
					rowArray[2] = Common.get(dtl.getRevision());	
					rowArray[3] = Common.formatYYYMMDD(Common.get(dtl.getCreateDate()),4);
					rowArray[4] = "明細";
					arrList.add(rowArray);	
				}
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


