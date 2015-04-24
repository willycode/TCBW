package com.kangdainfo.tcbw.util;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.tcbw.model.bo.Hfr1001Db;

public class COSUT001F extends SuperBean{
	
	private String id;
	private String committee;
	private String sex;
	private String idNum;
	private String nameField, idField, idValue;
    private String temp_other;		//暫存名稱
	private String temp_id = "";	//暫存id
	
	public String getId() {
		return checkGet(id);
	}

	public void setId(String id) {
		this.id = checkSet(id);
	}

	public String getCommittee() {
		return checkGet(committee);
	}

	public void setCommittee(String committee) {
		this.committee = checkSet(committee);
	}

	public String getSex() {
		return checkGet(sex);
	}

	public void setSex(String sex) {
		this.sex = checkSet(sex);
	}
	
	public String getIdNum() {
		return checkGet(idNum);
	}

	public void setIdNum(String idNum) {
		this.idNum = checkSet(idNum);
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
		String hql = " from Hfr1001Db where 1 = 1 ";
		if(!"".equals(getCommittee()))
			hql += " and name like " + Common.sqlChar("%" + getCommittee() + "%");
	    if(!"".equals(getSex()))
			hql += " and sex = " + Common.sqlChar(getSex());
		if(!"".equals(getIdNum()))
			hql += " and idNum = " + Common.sqlChar(TCBWCommon.getEncodeString(getIdNum()));
		
		this.processCurrentPageAttribute(ServiceGetter.getInstance().getTcbwService().loadCount(hql));
		if (getTotalRecord() > 0){
			if (getState().indexOf("query")<0) 
				ServiceGetter.getInstance().getCommonService().getHibernateTemplate().clear();
			java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(hql + " order by id", this.getRecordStart(), this.getPageSize());
			if (objList != null && objList.size() > 0) {
				for(Object dtlObj : objList) {				
					Hfr1001Db dtl = (Hfr1001Db)dtlObj;
					String[] rowArray = new String[5];
					rowArray[0] = Common.get(dtl.getId());											
					rowArray[1] = Common.get(dtl.getName());						
					rowArray[2] = dtl.getSex().equals("0")?"女":dtl.getSex().equals("1")?"男":"";						
					rowArray[3] = Common.get(TCBWCommon.getDecodeString(dtl.getIdNum()));	
					rowArray[4] = Common.get(dtl.getEmail());
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


