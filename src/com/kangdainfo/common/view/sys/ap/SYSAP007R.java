package com.kangdainfo.common.view.sys.ap;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.model.bo.CommonAuditLogHistory;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;

/**
*<br>程式目的：AuditLog查詢
*<br>程式代號：sysap006r
*<br>程式日期：0990317
*<br>程式作者：shark
*<br>--------------------------------------------------------
*<br>修改作者　　修改日期　　　修改目的
*<br>--------------------------------------------------------
*/

public class  SYSAP007R extends SuperBean{


String id;
String logDate;
String logTime;
String userId;
String userName;
String methodName;
String userIP;
String entity;
String progCode;
String progName;

String q_logDate, q_logDateE;
String q_logTime, q_logTimeE;
String q_userId;
String q_userName;
String q_methodName;
String q_userIP;
String q_entity;
String q_progCode, q_progName;

public String getId(){ return checkGet(id); }
public void setId(String s){ id=checkSet(s); }
public String getLogDate(){ return checkGet(logDate); }
public void setLogDate(String s){ logDate=checkSet(s); }
public String getLogTime(){ return checkGet(logTime); }
public void setLogTime(String s){ logTime=checkSet(s); }
public String getUserId(){ return checkGet(userId); }
public void setUserId(String s){ userId=checkSet(s); }
public String getUserName(){ return checkGet(userName); }
public void setUserName(String s){ userName=checkSet(s); }
public String getMethodName(){ return checkGet(methodName); }
public void setMethodName(String s){ methodName=checkSet(s); }
public String getUserIP(){ return checkGet(userIP); }
public void setUserIP(String s){ userIP=checkSet(s); }
public String getEntity(){ return checkGet(entity); }
public void setEntity(String s){ entity=checkSet(s); }
public String getProgCode() {return checkGet(progCode);}
public void setProgCode(String s) {this.progCode = checkSet(s);}
public String getProgName() {return checkGet(progName);}
public void setProgName(String s) {this.progName = checkSet(s);}

public String getQ_progCode() {return checkGet(q_progCode);}
public void setQ_progCode(String s) {q_progCode = checkSet(s);}
public String getQ_progName() {return checkGet(q_progName);}
public void setQ_progName(String s) {q_progName = checkSet(s);}

public String getQ_logDate(){ return checkGet(q_logDate); }
public void setQ_logDate(String s){ q_logDate=checkSet(s); }
public String getQ_logDateE(){ return checkGet(q_logDateE); }
public void setQ_logDateE(String s){ q_logDateE=checkSet(s); }
public String getQ_logTime(){ return checkGet(q_logTime); }
public void setQ_logTime(String s){ q_logTime=checkSet(s); }
public String getQ_logTimeE(){ return checkGet(q_logTimeE); }
public void setQ_logTimeE(String s){ q_logTimeE=checkSet(s); }

public String getQ_userId(){ return checkGet(q_userId); }
public void setQ_userId(String s){ q_userId=checkSet(s); }
public String getQ_userName(){ return checkGet(q_userName); }
public void setQ_userName(String s){ q_userName=checkSet(s); }
public String getQ_methodName(){ return checkGet(q_methodName); }
public void setQ_methodName(String s){ q_methodName=checkSet(s); }
public String getQ_userIP(){ return checkGet(q_userIP); }
public void setQ_userIP(String s){ q_userIP=checkSet(s); }
public String getQ_entity(){ return checkGet(q_entity); }
public void setQ_entity(String s){ q_entity=checkSet(s); }


@Override
public void doCreate() throws Exception {}

@Override
public void doUpdate() throws Exception {}

@Override
public void doDelete() throws Exception {}


/**
 * <br>
 * <br>目的：依主鍵查詢單一資料
 * <br>參數：無
 * <br>傳回：傳回本物件
*/

public SYSAP007R  doQueryOne() throws Exception {
	SYSAP007R obj = this;
	CommonAuditLogHistory c = (CommonAuditLogHistory) View.getObject("from CommonAuditLogHistory where id=" + Common.sqlChar(getId()));
	if (c!=null) {
        obj.setId(c.getId().toString());
        obj.setLogDate(c.getLogDate());
        obj.setLogTime(Common.formatHHMMSS(c.getLogTime()));
        obj.setUserId(c.getUserId());
        obj.setUserName(c.getUserName());
        obj.setMethodName(c.getMethodName());
        obj.setUserIP(c.getUserIp());
        obj.setEntity(c.getEntity());
        obj.setProgCode(c.getProgCode());
        obj.setProgName(c.getProgName());
	} else throw new Exception("查無該筆資料！");
	return obj;
}


/**
 * <br>
 * <br>目的：依查詢欄位查詢多筆資料
 * <br>參數：無
 * <br>傳回：傳回ArrayList
*/

public java.util.ArrayList<String[]> doQueryAll() throws Exception {
	java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();

	String hql = " from CommonAuditLogHistory where 1=1 ";
	if (!"".equals(getQ_logDate()))
		hql += " and logDate>=" + Common.sqlChar(getQ_logDate());
	if (!"".equals(getQ_logDateE()))
		hql += " and logDate<=" + Common.sqlChar(getQ_logDateE());	
	if (!"".equals(getQ_logTime()))
		hql += " and logTime>=" + Common.sqlChar(getQ_logTime());
	if (!"".equals(getQ_logTimeE()))
		hql += " and logTime<=" + Common.sqlChar(getQ_logTimeE());	
	if (!"".equals(getQ_userId()))
		hql += " and userId like " + Common.sqlChar("%"+getQ_userId()+"%");
	if (!"".equals(getQ_userName()))
		hql += " and userName like " + Common.sqlChar("%"+getQ_userName()+"%");
	if (!"".equals(getQ_methodName()))
		hql += " and methodName like " + Common.sqlChar("%"+getQ_methodName()+"%");
	if (!"".equals(getQ_userIP()))
		hql += " and userIp like " + Common.sqlChar(getQ_userIP()+"%");
	if (!"".equals(getQ_entity()))
		hql += " and entity like " + Common.sqlChar("%"+getQ_entity()+"%");
	if (!"".equals(getQ_progCode()))
		hql += " and progCode like " + Common.sqlChar(getProgCode()+"%");
	if (!"".equals(getQ_progName()))
		hql += " and progName like " + Common.sqlChar("%"+getProgName()+"%");
	
	this.processCurrentPageAttribute(ServiceGetter.getInstance().getCommonService().loadCount(hql));
	if (getTotalRecord() > 0) {
		if (getState().indexOf("query")<0) 
			ServiceGetter.getInstance().getCommonService().getHibernateTemplate().clear();
		
		java.util.List objList = ServiceGetter.getInstance().getCommonService().load(hql+" order by logDate desc, logTime desc", this.getRecordStart(), this.getPageSize());
		if (objList != null && objList.size() > 0) {
			java.util.Iterator it = objList.iterator();
			while (it.hasNext()) {
				CommonAuditLogHistory o = (CommonAuditLogHistory) it.next();
				String rowArray[] = new String[9];
				rowArray[0] = Common.get(o.getId());
				rowArray[1] = Common.get(o.getLogDate());
				rowArray[2] = Common.formatHHMMSS(o.getLogTime());
				rowArray[3] = Common.get(o.getProgCode());
				rowArray[4] = Common.get(o.getProgName());
				rowArray[5] = Common.get(o.getUserId());
				rowArray[6] = Common.get(o.getMethodName());
				rowArray[7] = Common.get(o.getUserIp());
				rowArray[8] = Common.FormatStr(Common.alignString(Common.checkGet(o.getEntity()).indexOf(".")>0?Common.get(o.getEntity()).substring(o.getEntity().lastIndexOf(".")+1):Common.get(o.getEntity()),18,""),"N");
				arrList.add(rowArray);					
			}
		}
	}
	return arrList;
}

}


