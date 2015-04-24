package com.kangdainfo.tcbw.view.medin;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Con1015Db;
import com.kangdainfo.tcbw.model.bo.Med2001Db;
import com.kangdainfo.tcbw.util.TCBWCommon;

public class MEDIN5203F extends SuperBean{
	
	private String id;
	private String userid;
	private String username;
	private String nameField, idField, idValue;
    private String temp_other;		//暫存名稱
	private String temp_id = "";	//暫存id
	
	private String id1;
	
	private String q_code;
	private String q_formCode;
	
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
		
		String hql = " from Con1015Db ";
		hql += " where con1014Db.code = " + Common.sqlChar(getQ_code());   
		hql += " and con1014Db.con1007Db.formCode = " + Common.sqlChar(getQ_formCode());
		hql += " and competence like " + TCBWCommon.likeSqlChar("1");
		
		if(!"".equals(getUserid()))
			hql += " and commonUser.userId like " + Common.sqlChar("%" + getUserid() + "%");
	    
		if(!"".equals(getUsername()))
			hql += " and commonUser.userName like " + Common.sqlChar("%" + getUsername() + "%");
		
		this.processCurrentPageAttribute(ServiceGetter.getInstance().getTcbwService().loadCount(hql));
		
		if (getTotalRecord() > 0)
		{
			if (getState().indexOf("query")<0) 
				ServiceGetter.getInstance().getCommonService().getHibernateTemplate().clear();
			
			java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(hql + " order by id", this.getRecordStart(), this.getPageSize());
			
			if (objList != null && objList.size() > 0) 
			{
				for(Object dtlObj : objList) 
				{				
					Con1015Db dtl = (Con1015Db)dtlObj;
					String[] rowArray = new String[5];
					rowArray[0] = Common.get(dtl.getId());	
					rowArray[1] = Common.get(dtl.getCon1014Db().getCode());	
					rowArray[2] = Common.get(dtl.getCon1014Db().getName());	
					rowArray[3] = Common.get(dtl.getCommonUser().getUserId());						
					rowArray[4] = Common.get(dtl.getCommonUser().getUserName());						
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
	public void doUpdate() throws Exception
	{
		Med2001Db obj = (Med2001Db)View.getObject(" from Med2001Db where id = " + Common.getLong(getId1()));
		if(obj!=null)
		{
			obj.setWorkers(getUserid());
		}	
		ServiceGetter.getInstance().getTcbwService().update(obj);
	}

	@Override
	public void doDelete() throws Exception{}
	
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

	public String getId1() {
		return checkGet(id1);
	}

	public void setId1(String id1) {
		this.id1 = checkSet(id1);
	}

	public String getQ_code() {
		return checkGet(q_code);
	}

	public void setQ_code(String qCode) {
		q_code = checkSet(qCode);
	}

	public String getQ_formCode() {
		return checkGet(q_formCode);
	}

	public void setQ_formCode(String qFormCode) {
		q_formCode = checkSet(qFormCode);
	}
	
	
	
	
}


