package com.kangdainfo.tcbw.util;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.model.bo.CommonAuditLogHistory;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.Datetime;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Con1009Db;

/**
*<br>程式目的：醫院POP視窗
*<br>程式代號：popProdModelNo
*<br>程式日期：1001109
*<br>程式作者：yuwen
*<br>--------------------------------------------------------
*<br>修改作者　　修改日期　　　修改目的
*<br>
*<br>--------------------------------------------------------
*/

public class Con1009DbSearch extends SuperBean{
String id ; 
String q_engageKind;
String q_medAgencyKind;
String q_con19Ids;

public String getId() {	return checkGet(id);}
public void setId(String s) {this.id = checkSet(s);}

public String getQ_engageKind() {return checkGet(q_engageKind);}
public void setQ_engageKind(String s) {this.q_engageKind = checkSet(s);}
public String getQ_medAgencyKind() {return checkGet(q_medAgencyKind);}
public void setQ_medAgencyKind(String s) {this.q_medAgencyKind = checkSet(s);}
public String getQ_con19Ids() {return checkGet(q_con19Ids);}
public void setQ_con19Ids(String s) {this.q_con19Ids = checkSet(s);}

@Override
public Object doQueryOne() throws Exception {
	// TODO Auto-generated method stub
	return null;
}
@Override
public Object doQueryAll() throws Exception {
	java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
	String hql = " from Con1009Db where (endDate is null or endDate = '' or endDate < " + Common.sqlChar(Datetime.getYYYMMDD())+")";
	if(null != getQ_engageKind() && !"".equals(getQ_engageKind())){
		hql += " and engageKind =" + Common.sqlChar(getQ_engageKind());
	}
	if(null != getQ_medAgencyKind() && !"".equals(getQ_medAgencyKind())){
		hql += " and medAgencyKind =" + Common.sqlChar(getQ_medAgencyKind());
	}
	List list = ServiceGetter.getInstance().getCommonService().load(hql);
	
	this.setPageSize(list.size());
	this.processCurrentPageAttribute(list.size());

	if (getTotalRecord() > 0) {
		if (getState().indexOf("query")<0) 
			ServiceGetter.getInstance().getCommonService().getHibernateTemplate().clear();
	
		if (list != null && list.size() > 0) {
			java.util.Map<String, String> engMap = TCBWCommon.getCommonCodeMap("MEDENG");
			java.util.Map<String, String> kindMap = TCBWCommon.getCommonCodeMap("MEDKIND");
			java.util.Map<String, String> btnMap = new java.util.HashMap<String, String>();
			if(null != getQ_con19Ids() && !"".equals(getQ_con19Ids())){
				for(String q_con19Id : getQ_con19Ids().split(",")){
					btnMap.put(q_con19Id,q_con19Id);
				}
			}
			Iterator it = list.iterator();
			while (it.hasNext()) {
				Con1009Db o = (Con1009Db) it.next();
				String rowArray[] = new String[7];	
				rowArray[0] = Common.get(o.getId());
				if(!btnMap.isEmpty()){
					rowArray[1] = btnMap.containsKey(Common.get(o.getId()))?"Y":"N";
				}else{
					rowArray[1] ="N";
				}
				rowArray[2] = Common.get(o.getMedAgencyName());
				rowArray[3] = !"".equals(Common.get(o.getEngageKind()))?engMap.get(Common.get(o.getEngageKind())):"";
				rowArray[4] = !"".equals(Common.get(o.getMedAgencyKind()))?kindMap.get(Common.get(o.getMedAgencyKind())):"";
				rowArray[5] = Common.get(o.getAgencyAddress());
				rowArray[6] = Common.get(o.getAreaTel());
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


