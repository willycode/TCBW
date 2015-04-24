package com.kangdainfo.tcbw.util;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Hfr1002Db;
import com.kangdainfo.tcbw.model.bo.Hfr1003Db;

public class COSUT004F extends SuperBean{
	
	private String id;
	private String nameField, idField, idValue;
    private String temp_other;		//暫存名稱
	private String temp_id = "";	//暫存id
	
	public String getId() {
		return checkGet(id);
	}

	public void setId(String id) {
		this.id = checkSet(id);
	}
	
	public String getNameField() {
		return checkGet(nameField);
	}

	public void setNameField(String nameField) {
		this.nameField = checkSet(nameField);
	}

	public String getIdField() {
		return checkGet(idField);
	}

	public void setIdField(String idField) {
		this.idField = checkSet(idField);
	}

	public String getIdValue() {
		return checkGet(idValue);
	}

	public void setIdValue(String idValue) {
		this.idValue = checkSet(idValue);
	}
	
    public String getTemp_id() {
		return checkGet(temp_id);
	}

	public void setTemp_id(String tempId) {
		temp_id = checkSet(tempId);
	}
   public String getTemp_other() {
		return checkGet(temp_other);
	}

	public void setTemp_other(String id) {
		this.id = checkSet(temp_other);
	}
	
	@Override
	public Object doQueryOne() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object doQueryAll() throws Exception {
		// TODO Auto-generated method stub
		Hfr1003Db obj = (Hfr1003Db)View.getObject(" from Hfr1003Db where id = " + Common.getLong(getIdField()));
		if(obj == null)
			return null;
		String meetingDate = obj.getMeetingDate();
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		String hql = " from Hfr1002Db where 1 = 1 and termSdate <= " + Common.sqlChar(meetingDate) + " and termEdate >= "+ Common.sqlChar(meetingDate);
		
		this.processCurrentPageAttribute(ServiceGetter.getInstance().getTcbwService().loadCount(hql));
		if (getTotalRecord() > 0){
			if (getState().indexOf("query")<0) 
				ServiceGetter.getInstance().getCommonService().getHibernateTemplate().clear();
			java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(hql + " order by id", this.getRecordStart(), this.getPageSize());
			if (objList != null && objList.size() > 0) {
				//使用map先將資料撈出來
				java.util.Map<String, String> postLevMap = TCBWCommon.getCommonCodeMap("PL");
				java.util.Map<String, String> unionMap = TCBWCommon.getCommonCodeMap("UI");
				
				for(Object dtlObj : objList) {				
					Hfr1002Db dtl = (Hfr1002Db)dtlObj;
					String[] rowArray = new String[7];
					rowArray[0] = Common.get(dtl.getId());	
					rowArray[1] = Common.get(dtl.getHfr1001Db().getCommitteeNum());			
					rowArray[2] = Common.get(dtl.getHfr1001Db().getName());						
					rowArray[3] = Common.get(postLevMap.get(dtl.getPostLev()));				
					rowArray[4] = Common.get(unionMap.get(dtl.getUnionID()));
					rowArray[5] = Common.get(dtl.getTermSdate());
					rowArray[6] = Common.get(dtl.getTermEdate());
					arrList.add(rowArray);
				}
				objList.clear();
			}
		
		}
		return arrList;
	}

	@Override
	public void doCreate() throws Exception{}

	@Override
	public void doUpdate() throws Exception{}

	@Override
	public void doDelete() throws Exception{}
	
}


