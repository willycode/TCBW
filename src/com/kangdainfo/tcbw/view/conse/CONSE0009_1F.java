package com.kangdainfo.tcbw.view.conse;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.model.bo.CommonUser;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Con1009Db;
import com.kangdainfo.tcbw.model.bo.Con1010Db;

public class CONSE0009_1F extends CONSE0009F{

	private String detailid;
	private String commonUser;
	private String[] formType;	
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

	public String[] getFormType() {
		return formType;
	}

	public void setFormType(String[] formType) {
		this.formType = formType;
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
	public Object doQueryOne() throws Exception {
		CONSE0009_1F obj = this;
		Con1010Db c = (Con1010Db)View.getObject("from Con1010Db where id = " + Common.getLong(getDetailid()));		
		if(c != null){
			obj.setId(Common.get(c.getCon1009Db().getId()));
			obj.setMedAgencyName(c.getCon1009Db().getMedAgencyName());
			obj.setDetailid(Common.get(c.getId()));
			obj.setCommonUser(Common.get(c.getCommonUser().getId()));
			if(!"".equals(Common.get(c.getFormType())))
	    		this.formType = c.getFormType().split(",");
	    	else
	    		this.formType = null;
			obj.setUsernameid(c.getCommonUser().getUserId());
			obj.setUsername(c.getCommonUser().getUserName());
			obj.setUsertel(Common.get(c.getCommonUser().getUserTel()));
			obj.setUseremail(Common.get(c.getCommonUser().getUserEmail()));
			obj.setEditID(c.getModifier());
			obj.setEditDate(c.getModifyDate());		
		}
		return obj;
	}

	@Override
	public Object doQueryAll() throws Exception {
		if (!"".equals(getQ_isQuery())){
			setQ_id("");
		}
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		String hql = " from Con1010Db where 1 = 1 and con1009Db.id = " + getId();

		this.processCurrentPageAttribute(ServiceGetter.getInstance().getTcbwService().loadCount(hql));
		if (getTotalRecord() > 0){
			if (getState().indexOf("query")<0) 
				ServiceGetter.getInstance().getCommonService().getHibernateTemplate().clear();
			java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(hql + " order by id", this.getRecordStart(), this.getPageSize());
			if (objList != null && objList.size() > 0) {
				for(Object dtlObj : objList) {	
					Con1010Db dtl = (Con1010Db)dtlObj;
					String[] rowArray = new String[6];
					rowArray[0] = Common.get(dtl.getId());											
					rowArray[1] = Common.get(dtl.getCommonUser().getUserId());
					rowArray[2] = Common.get(dtl.getCommonUser().getUserName());
					rowArray[3] = Common.get(dtl.getCommonUser().getUserTel());
					rowArray[4] = Common.get(dtl.getCommonUser().getUserEmail());
					rowArray[5] = Common.get(dtl.getFormType());
					arrList.add(rowArray);
				}
				objList.clear();
			}
		
		}
		return arrList;
	}

	@Override
	public void doCreate() throws Exception {
		Con1010Db obj = new Con1010Db();
		Con1009Db con1009 = (Con1009Db)View.getObject(" from Con1009Db where id = " + Common.getLong(getId()));
		CommonUser user = (CommonUser)View.getObject(" from CommonUser where id = " + Common.getLong(getCommonUser()));
		obj.setCon1009Db(con1009);
		obj.setCommonUser(user);
	    String tempFormType = "";
		for(String o: getFormType()){
			tempFormType += o + ",";
		}
		if(!"".equals(tempFormType))
			tempFormType = tempFormType.substring(0, tempFormType.length()-1);
		obj.setFormType(tempFormType);
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
	public void doUpdate() throws Exception {
		Con1010Db obj = (Con1010Db)View.getObject(" from Con1010Db where id = " + Common.getLong(getDetailid()));
		if(obj != null){
			Con1009Db con1009 = (Con1009Db)View.getObject(" from Con1009Db where id = " + Common.getLong(getId()));
			CommonUser user = (CommonUser)View.getObject(" from CommonUser where id = " + Common.getLong(getCommonUser()));
			obj.setCon1009Db(con1009);
			obj.setCommonUser(user);
		    String tempFormType = "";
			for(String o: getFormType()){
				tempFormType += o + ",";
			}
			if(!"".equals(tempFormType))
				tempFormType = tempFormType.substring(0, tempFormType.length()-1);
			obj.setFormType(tempFormType);
		    obj.setModifier(getEditID());
		    obj.setModifyDate(getEditDate());
		    obj.setModifyTime(getEditTime());
			ServiceGetter.getInstance().getTcbwService().update(obj);
			setDetailid(Common.get(obj.getId()));
		}
	}

	@Override
	public void doDelete() throws Exception {
		Con1010Db obj = (Con1010Db)View.getObject(" from Con1010Db where id = " + Common.getLong(getDetailid()));
		if(obj != null){
			ServiceGetter.getInstance().getTcbwService().delete(obj);
			setDetailid("");
		}else{
			throw new Exception("查無資料，無法刪除，請重新操作 !");
		}
	}

}
