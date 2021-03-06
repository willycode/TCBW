package com.kangdainfo.tcbw.util;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.tcbw.model.bo.Con1001Db;

public class COSUT002F extends SuperBean{
	
	private String id;
	private String nameField, idField, idValue;
    private String temp_other;		//暫存名稱
	private String temp_id = "";	//暫存id
	private String manyMailID;
	

	public String getManyMailID() {
		return checkGet(manyMailID);
	}

	public void setManyMailID(String manyMailID) {
		this.manyMailID = checkSet(manyMailID);
	}

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
	public Object doQueryAll() throws Exception 
	{
		// TODO Auto-generated method stub
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		String hql = " from Con1001Db where 1 = 1 ";
		
		if("".equals(getNameField()))
			return null;
		else
			hql += " and systemType = " + Common.sqlChar(getNameField());
		
		if(!"".equals(getManyMailID()))
		{
			String splitString = getManyMailID();
			String mailID="";
			
			String[] mailIDs = null;
			if(getManyMailID()!=null)
				mailIDs = splitString.split(",");
			
			for(int i=0;i<mailIDs.length;i++)
			{
				mailID+="'"+mailIDs[i]+"',";
			}	
			
			hql += " and mailID in ("+mailID.substring(0, mailID.length()-1)+")";
		}
		
		this.processCurrentPageAttribute(ServiceGetter.getInstance().getTcbwService().loadCount(hql));
		if (getTotalRecord() > 0){
			if (getState().indexOf("query")<0) 
				ServiceGetter.getInstance().getCommonService().getHibernateTemplate().clear();
			java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(hql + " order by id", this.getRecordStart(), this.getPageSize());
			if (objList != null && objList.size() > 0) 
			{
				for(Object dtlObj : objList) 
				{				
					Con1001Db dtl = (Con1001Db)dtlObj;
					String[] rowArray = new String[4];
					rowArray[0] = Common.get(dtl.getFormID());											
					rowArray[1] = Common.get(dtl.getTitle());									
					rowArray[2] = Common.get(dtl.getMailBody());
					rowArray[3] = Common.get(dtl.getMailID());
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


