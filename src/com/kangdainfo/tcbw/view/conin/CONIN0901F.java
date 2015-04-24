package com.kangdainfo.tcbw.view.conin;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.model.bo.CommonAuth;
import com.kangdainfo.common.model.bo.CommonGroup;
import com.kangdainfo.common.model.bo.CommonUser;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;

public class CONIN0901F extends SuperBean{
	
	private String id;
	private String groupName;
	private String groupDesc;
	private String subSystem;

	private String q_id;
	private String q_groupName;
	private String q_groupDesc;
	private String q_isQuery;
	
	public String getId(){ return checkGet(id); }
	public void setId(String s){ id=checkSet(s); }
	public String getGroupName(){ return checkGet(groupName); }
	public void setGroupName(String s){ groupName=checkSet(s); }
	public String getGroupDesc(){ return checkGet(groupDesc); }
	public void setGroupDesc(String s){ groupDesc=checkSet(s); }
	public String getSubSystem() {		return checkGet(subSystem);	}
	public void setSubSystem(String subSystem) {		this.subSystem = checkSet(subSystem);	}
	
	public String getQ_id() {		return checkGet(q_id);	}
	public void setQ_id(String q_id) {		this.q_id = checkSet(q_id);	}
	public String getQ_groupName(){ return checkGet(q_groupName); }
	public void setQ_groupName(String s){ q_groupName=checkSet(s); }
	public String getQ_groupDesc(){ return checkGet(q_groupDesc); }
	public void setQ_groupDesc(String s){ q_groupDesc=checkSet(s); }
	public String getQ_isQuery() {		return checkGet(q_isQuery);	}
	public void setQ_isQuery(String q_isQuery) {		this.q_isQuery = checkSet(q_isQuery);	}
	
	@Override
	public Object doQueryOne() throws Exception {
		CONIN0901F obj = this;
		CommonGroup c = (CommonGroup)ServiceGetter.getInstance().getCommonService().getCommonGroupDao().load(CommonGroup.class, Integer.parseInt(getId()));
		if (c != null) {
	        obj.setGroupName(c.getGroupName());
	        obj.setGroupDesc(c.getGroupDesc());
	        obj.setSubSystem(c.getSubSystem());
	        obj.setEditID(c.getEditId());
	        obj.setEditDate(c.getEditDate());
	        obj.setEditTime(c.getEditTime());
		} else {
			throw new Exception("查無該筆資料！");
		}
		return obj;
	}

	@Override
	public Object doQueryAll() throws Exception {
		if (!"".equals(getQ_isQuery())){
			setQ_id("");
		}
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		String hql = " from CommonGroup where 1 = 1";
		if (!"".equals(getQ_id())) {
			hql += " and id = " + Common.getLong(getQ_id());
		} else {
			if (!"".equals(getQ_groupName()))
				hql += " and groupName like " + Common.sqlChar(getQ_groupName()) ;
			if (!"".equals(getQ_groupDesc()))
				hql += " and groupDesc like " + Common.sqlChar(getQ_groupDesc()) ;
		}
		
		this.processCurrentPageAttribute(ServiceGetter.getInstance().getTcbwService().loadCount(hql));
		if (getTotalRecord() > 0) {
			if (getState().indexOf("query")<0) 
				ServiceGetter.getInstance().getTcbwService().getHibernateTemplate().clear();
			
			java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(hql + " order by id ", this.getRecordStart(),this.getPageSize());
			if (objList!=null && objList.size()>0) {
				for(Object dtlObj : objList){
					CommonGroup dtl = (CommonGroup)dtlObj;
					
					String[] rowArray = new String[3];
					rowArray[0] = Common.get(dtl.getId());
					rowArray[1] = Common.get(dtl.getGroupName());
					rowArray[2] = Common.get(dtl.getGroupDesc());
					arrList.add(rowArray);
				}
				objList.clear();
			}
		}
		return arrList;
	}

	@Override
	public void doCreate() throws Exception {
		CommonGroup obj = null;
		java.util.List list = ServiceGetter.getInstance().getCommonService().getCommonGroupDao().loadCommonGroupsByGroupName(getGroupName());
		if (list!=null && list.size()>0) {
			obj = (CommonGroup) list.get(0);  	 
		}
		
		if (obj!=null && obj.getGroupName().equals(getGroupName())) {
			throw new Exception("該筆資料己重複，請重新輸入！");
		} else {
			obj = new CommonGroup();
			obj.setGroupName(getGroupName());
			obj.setGroupDesc(getGroupDesc());
			obj.setSubSystem(getSubSystem());
			obj.setEditId(getEditID());
			obj.setEditDate(getEditDate());
			obj.setEditTime(getEditTime());
		    ServiceGetter.getInstance().getCommonService().getCommonGroupDao().saveCommonGroup(obj);
		    setId(Common.get(obj.getId()));		
		}
	}

	@Override
	public void doUpdate() throws Exception {
		CommonGroup obj = (CommonGroup)View.getObject(" from CommonGroup where id = " + Common.getLong(getId()));
		if (obj != null) {
			obj.setGroupName(getGroupName());
			obj.setGroupDesc(getGroupDesc());
			obj.setSubSystem(getSubSystem());
			obj.setEditId(getEditID());
			obj.setEditDate(getEditDate());
			obj.setEditTime(getEditTime());
		    ServiceGetter.getInstance().getCommonService().getCommonGroupDao().updateCommonGroup(obj);
		} else {
			throw new Exception("查無該筆資料，請重新輸入！");
		}
	}

	@Override
	public void doDelete() throws Exception {
		java.util.List<CommonAuth> authList = ServiceGetter.getInstance().getTcbwService().load(" from CommonAuth where commonGroup.id = " + Common.getInt(getId()));
		java.util.List<CommonUser> userList = ServiceGetter.getInstance().getTcbwService().load(" from CommonUserGroup where commonGroup.id = " + Common.getInt(getId()));
		
		if(authList!=null && authList.size()>0){
			throw new Exception("身分別名稱已被賦與身分別權限,不允許刪除!!");
		}else if(userList!=null && userList.size()>0){
			throw new Exception("身分別名稱已被賦與使用者,不允許刪除!!");
		}else{
			ServiceGetter.getInstance().getCommonService().getCommonGroupDao().deleteCommonGroup(Common.getInt(getId()));
		}
	}

}
