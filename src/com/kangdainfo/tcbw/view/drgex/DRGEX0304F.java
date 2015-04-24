package com.kangdainfo.tcbw.view.drgex;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.model.bo.*;
import com.kangdainfo.common.util.*;
import com.kangdainfo.tcbw.model.bo.*;
import com.kangdainfo.tcbw.util.TCBWCommon;

/**
*<br>程式目的：藥品療效不等通報批次上傳
*<br>程式代號：drgex0304f
*<br>程式日期：1021114
*<br>程式作者：yuwen
*<br>--------------------------------------------------------
*<br>修改作者　　修改日期　　　修改目的
*<br>--------------------------------------------------------
*/

public class  DRGEX0304F extends SuperBean{


String id;
String applNo;
String notifierRepDate;
String notifierRepDateName;
String notifierRepDateCodeId;


public String getId(){ return checkGet(id); }
public void setId(String s){ id=checkSet(s); }
public String getApplNo(){ return checkGet(applNo); }
public void setApplNo(String s){ applNo=checkSet(s); }
public String getNotifierRepDate(){ return checkGet(notifierRepDate); }
public void setNotifierRepDate(String s){ notifierRepDate=checkSet(s); }
public String getNotifierRepDateName(){ return checkGet(notifierRepDateName); }
public void setNotifierRepDateName(String s){ notifierRepDateName=checkSet(s); }
public String getNotifierRepDateCodeId(){ return checkGet(notifierRepDateCodeId); }
public void setNotifierRepDateCodeId(String s){ notifierRepDateCodeId=checkSet(s); }

javax.servlet.ServletRequest httpRequest;
public javax.servlet.ServletRequest getHttpRequest() {	return httpRequest;	}
public void setHttpRequest(javax.servlet.ServletRequest r) {	this.httpRequest = r;	}

String[] ids;
public String[] getIds() {		return ids;	}
public void setIds(String[] ids) {		this.ids = ids;	}

@Override
public Object doQueryOne() throws Exception {
	// TODO Auto-generated method stub
	return null;
}
@Override
public Object doQueryAll() throws Exception {
	java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();

	String hql = " from Drg6001Db where status = '01' ";
	hql += " and creator = " + Common.sqlChar(TCBWCommon.getCurrentUserId());
	this.processCurrentPageAttribute(ServiceGetter.getInstance().getCommonService().loadCount(hql));
	if (getTotalRecord() > 0) {
		if (getState().indexOf("query")<0) 
			ServiceGetter.getInstance().getCommonService().getHibernateTemplate().clear();
		
		java.util.List objList = ServiceGetter.getInstance().getCommonService().load(hql+" order by id desc", this.getRecordStart(), this.getPageSize());
		if (objList != null && objList.size() > 0) {
			java.util.Map<String, String> statusMap = TCBWCommon.getCommonCodeMap("DRG0310");
			java.util.Iterator it = objList.iterator();
			while (it.hasNext()) {
				Drg6001Db dtl = (Drg6001Db) it.next();
				String rowArray[] = new String[8];
				rowArray[0] = Common.get(dtl.getId());
				rowArray[1] = Common.get(dtl.getApplNo());
				rowArray[2] = Common.get(dtl.getNotifierRevDate());
				rowArray[3] = "";
				if("1".equals(dtl.getConSequence())){
					rowArray[3] = "藥效改變";
				}else if("2".equals(dtl.getConSequence())){
					rowArray[3] = "不良反應發生、強度增強或頻率增加";
				}else if("1,2".equals(dtl.getConSequence())){
					rowArray[3] = "藥效改變及不良反應發生、強度增強或頻率增加";
				}
				rowArray[4] = "";
				rowArray[5] = "";
				rowArray[6] = "";
				if(null != dtl.getDrg6003Dbs() && !dtl.getDrg6003Dbs().isEmpty()){
					for(Object dtl63:dtl.getDrg6003Dbs()){
						Drg6003Db drg63 = (Drg6003Db)dtl63;
						if("02".equals(drg63.getMedType())){
							if(null != drg63.getPermitKey() && !"".equals(drg63.getPermitKey()) && null != drg63.getPermitNo() && !"".equals(drg63.getPermitNo())){
								rowArray[4] =  Common.get(View.getCommonCodeName("DRGPKID", drg63.getPermitKey()))+"第"+Common.get(drg63.getPermitNo())+"號";
							}
							rowArray[5] =  Common.get(drg63.getScientificName());
							rowArray[6] =  Common.get(drg63.getProductName());
						}
					}
				}
				rowArray[7] = Common.get(statusMap.get(dtl.getStatus()));
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
	if(httpRequest!=null && ids!=null&&ids.length>0){
		for(int i=0;i<ids.length;i++){
			Drg6001Db drg61 = (Drg6001Db) View.getObject("from Drg6001Db where id="+ids[i]);
			if(drg61 != null){
				ServiceGetter.getInstance().getTcbwService().getDrgexDao().sendDrg061Db(drg61, "3");
			}
		}
	}
	
}
@Override
public void doDelete() throws Exception {
	// TODO Auto-generated method stub
	
}

}


