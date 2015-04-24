package com.kangdainfo.tcbw.util;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.model.bo.CommonAuditLogHistory;
import com.kangdainfo.common.model.bo.CommonUser;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.SuperBean;

/**
*<br>程式目的：通報者POP視窗
*<br>程式代號：popUser
*<br>程式日期：1021115
*<br>程式作者：yuwen
*<br>--------------------------------------------------------
*<br>修改作者　　修改日期　　　修改目的
*<br>
*<br>--------------------------------------------------------
*/

public class UserSearch extends SuperBean{
	
String id ; 

String q_userName;
String q_userEmail;
String q_userJob;

public String getId() {	return checkGet(id);}
public void setId(String s) {this.id = checkSet(s);}



public String getQ_userName() {return checkGet(q_userName);}
public void setQ_userName(String s) {this.q_userName = checkSet(s);}

public String getQ_userEmail() {return checkGet(q_userEmail);}
public void setQ_userEmail(String s) {this.q_userEmail = checkSet(s);}

public String getQ_userJob() {return checkGet(q_userJob);}
public void setQ_userJob(String s) {this.q_userJob = checkSet(s);}

@Override
public Object doQueryOne() throws Exception {
	// TODO Auto-generated method stub
	return null;
}
@Override
public Object doQueryAll() throws Exception {
	java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
	String hql = " from CommonUser where isStop <> 'Y' ";
	if(null != getQ_userName() && !"".equals(getQ_userName())){
		hql += " and userName = " + Common.sqlChar(getQ_userName());
	}
	if(null != getQ_userEmail() && !"".equals(getQ_userEmail())){
		hql += " and userEmail = " + Common.sqlChar(getQ_userEmail());
	}
	if(null != getQ_userJob() && !"".equals(getQ_userJob())){
		hql += " and userJob like " + Common.likeSqlChar(getQ_userJob());
	}
	
	this.processCurrentPageAttribute(ServiceGetter.getInstance().getCommonService().loadCount(hql));
	if (getTotalRecord() > 0) {
		if (getState().indexOf("query")<0) 
			ServiceGetter.getInstance().getCommonService().getHibernateTemplate().clear();
		
		List objList = ServiceGetter.getInstance().getCommonService().load(hql+" order by userId desc", this.getRecordStart(), this.getPageSize());
		if (objList != null && !objList.isEmpty()) {
			Iterator it = objList.iterator();
			while (it.hasNext()) {
				CommonUser o = (CommonUser) it.next();
				String rowArray[] = new String[17];	
				rowArray[0] = Common.get(o.getId());
				rowArray[1] = Common.get(o.getUserName());		//姓名				
				rowArray[2] = Common.get(TCBWCommon.getNotifierDeptName(o.getCommonDepartment().getDepartmentCode(),o.getUserJob()));	
				rowArray[3] = Common.get(o.getUserEmail());		//e-mail
				rowArray[4] = Common.get(o.getJobTitle());		//職稱
				rowArray[5] = Common.get(o.getUserTel());		//電話
				rowArray[6] = Common.get(o.getUserFax());		//傳真
				rowArray[7] = Common.get(o.getUserMobile());	//手機
				rowArray[8] = Common.get(o.getUserAddr());		//地址
				rowArray[9] = Common.get(o.getCommonDepartment().getDepartmentCode());	//屬性
				rowArray[10] = Common.get(o.getUserId());		//使用者id
				rowArray[11] = Common.get(o.getUserTelArea());
				rowArray[12] = Common.get(o.getUserTelExt());
				rowArray[13] = Common.get(o.getUserFaxArea());
				rowArray[14] = Common.get(o.getUserCounty());
				rowArray[15] = Common.get(o.getUserZipCode());
				rowArray[16] = Common.get(o.getUserJob());		//服務機構ID
				
				
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


