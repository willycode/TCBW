package com.kangdainfo.tcbw.view.conse;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.model.bo.CommonUser;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Con1012Db;
import com.kangdainfo.tcbw.model.bo.Con1013Db;

public class CONSE0010_1F extends CONSE0010F{

	private String detailid;
	private String commonUser;
	private String usernameid;
	private String username;
	private String usertel;
	private String useremail;

	public String getDetailid() {
		return checkGet(detailid);
	}

	public void setDetailid(String detailid) {
		this.detailid = checkSet(detailid);
	}

	public String getCommonUser() {
		return checkGet(commonUser);
	}

	public void setCommonUser(String commonUser) {
		this.commonUser = checkSet(commonUser);
	}
	
	public String getUsernameid() {
		return checkGet(usernameid);
	}

	public void setUsernameid(String usernameid) {
		this.usernameid = checkSet(usernameid);
	}

	public String getUsername() {
		return checkGet(username);
	}

	public void setUsername(String username) {
		this.username = checkSet(username);
	}

	public String getUsertel() {
		return checkGet(usertel);
	}

	public void setUsertel(String usertel) {
		this.usertel = checkSet(usertel);
	}

	public String getUseremail() {
		return checkGet(useremail);
	}

	public void setUseremail(String useremail) {
		this.useremail = checkSet(useremail);
	}

	@Override
	public Object doQueryOne() throws Exception 
	{
		CONSE0010_1F obj = this;
		Con1013Db c = (Con1013Db)View.getObject("from Con1013Db where id = " + Common.getLong(getDetailid()));		
		
		if(c != null)
		{
			obj.setId(Common.get(c.getCon1012Db().getId()));
			obj.setDetailid(Common.get(c.getId()));
			
			obj.setCommonUser("".equals(Common.get(c.getCommonUser()))?"":Common.get(c.getCommonUser().getId()));
			
			obj.setUsernameid("".equals(Common.get(c.getCommonUser()))?"":c.getCommonUser().getUserId());
			obj.setUsername("".equals(Common.get(c.getCommonUser()))?c.getName():c.getCommonUser().getUserName());
			obj.setUseremail("".equals(Common.get(c.getCommonUser()))?c.getEmail():Common.get(c.getCommonUser().getUserEmail()));
			
			//obj.setUsertel(Common.get(c.getCommonUser().getUserTel()));

			obj.setEditID(c.getModifier());
			obj.setEditDate(c.getModifyDate());		
		}
		return obj;
	}

	@Override
	public Object doQueryAll() throws Exception 
	{
		if (!"".equals(getQ_isQuery())){
			setQ_id("");
		}
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		String hql = " from Con1013Db where 1 = 1 and con1012Db.id = " + getId();

		this.processCurrentPageAttribute(ServiceGetter.getInstance().getTcbwService().loadCount(hql));
		if (getTotalRecord() > 0){
			if (getState().indexOf("query")<0) 
				ServiceGetter.getInstance().getCommonService().getHibernateTemplate().clear();
			java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(hql + " order by id", this.getRecordStart(), this.getPageSize());
			if (objList != null && objList.size() > 0) {
				for(Object dtlObj : objList) {	
					Con1013Db dtl = (Con1013Db)dtlObj;
					String[] rowArray = new String[4];
					rowArray[0] = Common.get(dtl.getId());		
					rowArray[1] = Common.get("".equals(Common.get(dtl.getCommonUser()))?"":dtl.getCommonUser().getUserId());
					rowArray[2] = Common.get("".equals(Common.get(dtl.getCommonUser()))?dtl.getName():dtl.getCommonUser().getUserName());
					rowArray[3] = Common.get("".equals(Common.get(dtl.getCommonUser()))?dtl.getEmail():dtl.getCommonUser().getUserEmail());
					arrList.add(rowArray);
				}
				objList.clear();
			}
		
		}
		return arrList;
	}

	@Override
	public void doCreate() throws Exception 
	{
		Con1013Db obj = new Con1013Db();
		Con1012Db con1012 = (Con1012Db)View.getObject(" from Con1012Db where id = " + Common.getLong(getId()));
		CommonUser user = (CommonUser)View.getObject(" from CommonUser where id = " + Common.getLong(getCommonUser()));
		obj.setCon1012Db(con1012);
		obj.setCommonUser(user);
	    
		if("".equals(Common.get(user)))
		{	
		  obj.setEmail(getUseremail());
		  obj.setName(getUsername());
		}

	    obj.setCreator(getEditID());
	    obj.setCreateDate(getEditDate());
	    obj.setCreateTime(getEditTime());
	    obj.setModifier(getEditID());
	    obj.setModifyDate(getEditDate());
	    obj.setModifyTime(getEditTime());
		ServiceGetter.getInstance().getTcbwService().save(obj);
		setDetailid(Common.get(obj.getId()));
	}

	@Override
	public void doUpdate() throws Exception 
	{
		Con1013Db obj = (Con1013Db)View.getObject(" from Con1013Db where id = " + Common.getLong(getDetailid()));
		if(obj != null)
		{
			Con1012Db con1012 = (Con1012Db)View.getObject(" from Con1012Db where id = " + Common.getLong(getId()));
			CommonUser user = (CommonUser)View.getObject(" from CommonUser where id = " + Common.getLong(getCommonUser()));
			obj.setCon1012Db(con1012);
			obj.setCommonUser(user);
			
			if("".equals(Common.get(user)))
			{	
			  obj.setEmail(getUseremail());
			  obj.setName(getUsername());
			}
			
		    obj.setModifier(getEditID());
		    obj.setModifyDate(getEditDate());
		    obj.setModifyTime(getEditTime());
			ServiceGetter.getInstance().getTcbwService().update(obj);
			setDetailid(Common.get(obj.getId()));
		}
	}

	@Override
	public void doDelete() throws Exception {
		Con1013Db obj = (Con1013Db)View.getObject(" from Con1013Db where id = " + Common.getLong(getDetailid()));
		if(obj != null){
			ServiceGetter.getInstance().getTcbwService().delete(obj);
			setDetailid("");
		}else{
			throw new Exception("查無資料，無法刪除，請重新操作 !");
		}
	}

}
