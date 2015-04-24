package com.kangdainfo.tcbw.util;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.model.bo.CommonUser;

public class COSUT003F extends SuperBean{
	
	private String id;
	private String userid;
	private String username;
	private String nameField, idField, idValue;
    private String temp_other;		//暫存名稱
	private String temp_id = "";	//暫存id
	
	public String getId() {
		return checkGet(id);
	}

	public void setId(String id) {
		this.id = checkSet(id);
	}

	public String getUserid() {
		return checkGet(userid);
	}

	public void setUserid(String userid) {
		this.userid = checkSet(userid);
	}

	public String getUsername() {
		return checkGet(username);
	}

	public void setUsername(String username) {
		this.username = checkSet(username);
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
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		String hql = " from CommonUser where 1 = 1 ";
		if(!"".equals(getUserid()))
			hql += " and userId like " + Common.sqlChar("%" + getUserid() + "%");
	    if(!"".equals(getUsername()))
			hql += " and userName like " + Common.sqlChar("%" + getUsername() + "%");
		
		this.processCurrentPageAttribute(ServiceGetter.getInstance().getTcbwService().loadCount(hql));
		if (getTotalRecord() > 0){
			if (getState().indexOf("query")<0) 
				ServiceGetter.getInstance().getCommonService().getHibernateTemplate().clear();
			java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(hql + " order by id", this.getRecordStart(), this.getPageSize());
			if (objList != null && objList.size() > 0) {
				for(Object dtlObj : objList) {				
					CommonUser dtl = (CommonUser)dtlObj;
					String[] rowArray = new String[14];
					rowArray[0] = Common.get(dtl.getId());											
					rowArray[1] = Common.get(dtl.getUserId());						
					rowArray[2] = Common.get(dtl.getUserName());						
					rowArray[3] = Common.get(dtl.getUserTel());
					rowArray[4] = Common.get(dtl.getUserTelArea());
					rowArray[5] = Common.get(dtl.getUserTelExt());
					rowArray[6] = Common.get(dtl.getUserEmail());
					rowArray[7] = Common.get(dtl.getJobTitle());
					rowArray[8] = Common.get(dtl.getUserAddr());
					rowArray[9] = Common.get(dtl.getUserCounty());
					rowArray[10] = Common.get(dtl.getUserZipCode());
					rowArray[11] = Common.get(dtl.getUserJob());		
					rowArray[12] = Common.get(TCBWCommon.getNotifierDeptName(dtl.getCommonDepartment().getDepartmentCode(),dtl.getUserJob()));
					rowArray[13] = Common.get(dtl.getCommonDepartment().getDepartmentCode());
			        
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


