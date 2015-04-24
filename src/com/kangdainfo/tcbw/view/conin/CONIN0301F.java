package com.kangdainfo.tcbw.view.conin;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.model.bo.CommonUser;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;

public class CONIN0301F extends SuperBean{

	private String q_userId;
	private String q_userName;
	
	private String id;
	private String userId;
	private String userName;

	
	public String getQ_userId() {return checkGet(q_userId);}
	public void setQ_userId(String qUserId) {q_userId = checkSet(qUserId);}
	public String getQ_userName() {return checkGet(q_userName);}
	public void setQ_userName(String qUserName) {q_userName = checkSet(qUserName);}
	
	public String getId() {return checkGet(id);}
	public void setId(String id) {this.id = checkSet(id);}
	public String getUserId() {return checkGet(userId);}
	public void setUserId(String userId) {this.userId = checkSet(userId);}
	public String getUserName() {return checkGet(userName);}
	public void setUserName(String userName) {this.userName = checkSet(userName);}
	
	@Override
	public Object doQueryOne() throws Exception {
		CONIN0301F obj = this;
		CommonUser c = (CommonUser)View.getObject(" from CommonUser where id = " + Common.getInt(getId()));
		
		if(c != null){
			obj.setUserId(Common.get(c.getUserId()));
			obj.setUserName(Common.get(c.getUserName()));
			obj.setEditID(Common.get(c.getEditId()));
			obj.setEditDate(Common.get(c.getEditDate()));
		}
		
		return obj;
	}
	
	
	@Override
	public Object doQueryAll() throws Exception {

		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		String hql = " from CommonUser where 1 = 1";
		
		if (!"".equals(getQ_userId()))
			hql += " and userId = " + Common.sqlChar(getQ_userId()) ;
		if (!"".equals(getQ_userName()))
			hql += " and userName like " + Common.sqlChar("%"+getQ_userName()+"%") ;
		
		this.processCurrentPageAttribute(ServiceGetter.getInstance().getTcbwService().loadCount(hql));
		if (getTotalRecord() > 0) {
			if (getState().indexOf("query")<0) 
				ServiceGetter.getInstance().getTcbwService().getHibernateTemplate().clear();
			
			java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(hql + " order by id ", this.getRecordStart(),this.getPageSize());
			if (objList!=null && objList.size()>0) {
				for(Object dtlObj : objList){
					CommonUser dtl = (CommonUser)dtlObj;
					
					String[] rowArray = new String[3];
					rowArray[0] = Common.get(dtl.getId());
					rowArray[1] = Common.get(dtl.getUserId());
					rowArray[2] = Common.get(dtl.getUserName());
					arrList.add(rowArray);
				}
				objList.clear();
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
