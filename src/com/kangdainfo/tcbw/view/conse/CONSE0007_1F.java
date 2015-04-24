package com.kangdainfo.tcbw.view.conse;

import java.util.List;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.model.bo.CommonGroup;
import com.kangdainfo.common.model.bo.CommonUser;
import com.kangdainfo.common.model.bo.CommonUserGroup;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.Datetime;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Con1005Db;
import com.kangdainfo.tcbw.model.bo.Con1006Db;
import com.kangdainfo.tcbw.model.bo.Drg0002Db;
import com.kangdainfo.tcbw.model.bo.Drg5002Db;

public class CONSE0007_1F extends CONSE0007F{

	private String detailid;
	private String commonUser;
	private String[] formType;	
	private String usernameid;
	private String username;
	private String usertel;
	private String useremail;
	private String isECP;
	private String isAdmin;

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
	
	public String getIsECP() {
		return checkGet(isECP);
	}

	public void setIsECP(String isECP) {
		this.isECP = checkSet(isECP);
	}

	public String getIsAdmin() {
		return checkGet(isAdmin);
	}

	public void setIsAdmin(String isAdmin) {
		this.isAdmin = checkSet(isAdmin);
	}

	@Override
	public Object doQueryOne() throws Exception {
		CONSE0007_1F obj = this;
		Con1006Db c = (Con1006Db)View.getObject("from Con1006Db where id = " + Common.getLong(getDetailid()));		
		if(c != null){
			obj.setId(Common.get(c.getCon1005Db().getId()));
			obj.setDetailid(Common.get(c.getId()));
			obj.setCommonUser(Common.get(c.getCommonUser().getId()));
			if(!"".equals(Common.get(c.getFormType())))
	    		this.formType = c.getFormType().split(",");
	    	else
	    		this.formType = null;
			obj.setIsECP(Common.get(c.getIsECP()));
			obj.setIsAdmin(Common.get(c.getIsAdmin()));
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
		String hql = " from Con1006Db where 1 = 1 and con1005Db.id = " + getId();

		this.processCurrentPageAttribute(ServiceGetter.getInstance().getTcbwService().loadCount(hql));
		if (getTotalRecord() > 0){
			if (getState().indexOf("query")<0) 
				ServiceGetter.getInstance().getCommonService().getHibernateTemplate().clear();
			java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(hql + " order by id", this.getRecordStart(), this.getPageSize());
			if (objList != null && objList.size() > 0) {
				for(Object dtlObj : objList) {	
					Con1006Db dtl = (Con1006Db)dtlObj;
					String[] rowArray = new String[7];
					rowArray[0] = Common.get(dtl.getId());											
					rowArray[1] = Common.get(dtl.getCommonUser().getUserId());
					rowArray[2] = Common.get(dtl.getCommonUser().getUserName());
					rowArray[3] = Common.get(dtl.getCommonUser().getUserTel());
					rowArray[4] = Common.get(dtl.getCommonUser().getUserEmail());
					rowArray[5] = Common.get(dtl.getFormType());
					rowArray[6] = Common.get(dtl.getIsECP());
					
					arrList.add(rowArray);
				}
				objList.clear();
			}
		
		}
		return arrList;
	}

	@Override
	public void doCreate() throws Exception {
		Con1006Db obj = new Con1006Db();
		Con1005Db con1005 = (Con1005Db)View.getObject(" from Con1005Db where id = " + Common.getLong(getId()));
		CommonUser user = (CommonUser)View.getObject(" from CommonUser where id = " + Common.getLong(getCommonUser()));
		obj.setCon1005Db(con1005);
		obj.setCommonUser(user);
	    String tempFormType = "";
	    java.util.List<CommonUserGroup> saveList = new java.util.ArrayList<CommonUserGroup>();
		for(String o: getFormType()){
			tempFormType += o + ",";
			//增加權限
			CommonGroup group = getCommonGroup(o);					
			if(group != null){			
				CommonUserGroup userGroup = new CommonUserGroup();			
				userGroup.setCommonGroup(group);			
				userGroup.setCommonUser(user);			
				userGroup.setEditId("SYS");			
				userGroup.setEditDate(Datetime.getYYYMMDD());			
				userGroup.setEditTime(Datetime.getHHMMSS());
				saveList.add(userGroup);				
			}
		}
		if(!"".equals(tempFormType))
			tempFormType = tempFormType.substring(0, tempFormType.length()-1);
		obj.setFormType(tempFormType);
		obj.setIsECP(getIsECP());
		obj.setIsAdmin(getIsAdmin());
	    obj.setCreator(getEditID());
	    obj.setCreateDate(getEditDate());
	    obj.setCreateTime(getEditTime());
	    obj.setModifier(getEditID());
	    obj.setModifyDate(getEditDate());
	    obj.setModifyTime(getEditTime());
	    if(saveList.size()>0) ServiceGetter.getInstance().getTcbwService().saveBatch(saveList);
		ServiceGetter.getInstance().getTcbwService().save(obj);
		setDetailid(Common.get(obj.getId()));		
	}

	@Override
	public void doUpdate() throws Exception {
		Con1006Db obj = (Con1006Db)View.getObject(" from Con1006Db where id = " + Common.getLong(getDetailid()));
		if(obj != null){
			Con1005Db con1005 = (Con1005Db)View.getObject(" from Con1005Db where id = " + Common.getLong(getId()));
			CommonUser user = (CommonUser)View.getObject(" from CommonUser where id = " + Common.getLong(getCommonUser()));
			obj.setCon1005Db(con1005);
			obj.setCommonUser(user);
		    String tempFormType = "";

		    //先刪除原權限
		    java.util.List<CommonUserGroup> deleteList = new java.util.ArrayList<CommonUserGroup>();		    
		    String hql = "select id,groupName from CommonGroup where groupName in ('藥品不良品-廠商','藥品療效不等-廠商','化粧品不良品-廠商','化粧品不良反應-廠商'," +
		    		     "'醫療器材不良品-廠商','醫療器材定期安全監視-廠商','醫療器材國內外安全警訊-廠商','國內外藥品品質警訊-廠商','藥品重大品質事件-廠商')";
			java.util.List objList = ServiceGetter.getInstance().getCommonService().load(hql);        
			if(objList!=null && objList.size()>0)
			{
				java.util.Iterator it = objList.iterator();
				while (it.hasNext()) 
				{					
					Object[] o = (Object[])it.next();
					boolean isDelete = true;
					for(String o1: getFormType()){
						CommonGroup group = getCommonGroup(o1);
						if(group != null && Common.get(group.getId()).equals(Common.get(o[0])))
							isDelete = false;
					}
					if(isDelete){
						CommonUserGroup obj2 = (CommonUserGroup)View.getObject(" from CommonUserGroup where commonUser.id="+user.getId()+" and commonGroup.id="+Common.getLong(o[0]));                    
						if(obj2 != null) deleteList.add(obj2);
					}					
				}
			}
			if(deleteList != null){
				ServiceGetter.getInstance().getTcbwService().deleteAndSave(deleteList, null);
				deleteList.clear();
			}		
		    
			for(String o: getFormType()){
				tempFormType += o + ",";				
				//增加權限
				CommonGroup group = getCommonGroup(o);							
				if(group != null){
					CommonUserGroup obj2 = (CommonUserGroup)View.getObject(" from CommonUserGroup where commonUser.id="+user.getId()+" and commonGroup.id="+group.getId());
					if(obj2==null){				
						CommonUserGroup userGroup = new CommonUserGroup();				
						userGroup.setCommonGroup(group);					
						userGroup.setCommonUser(user);					
						userGroup.setEditId("SYS");					
						userGroup.setEditDate(Datetime.getYYYMMDD());					
						userGroup.setEditTime(Datetime.getHHMMSS());					
						ServiceGetter.getInstance().getTcbwService().save(userGroup);
					}
				}
			}			
			
			if(!"".equals(tempFormType))
				tempFormType = tempFormType.substring(0, tempFormType.length()-1);
			obj.setFormType(tempFormType);
			obj.setIsECP(getIsECP());
			obj.setIsAdmin(getIsAdmin());
		    obj.setModifier(getEditID());
		    obj.setModifyDate(getEditDate());
		    obj.setModifyTime(getEditTime());
			ServiceGetter.getInstance().getTcbwService().update(obj);
			setDetailid(Common.get(obj.getId()));
		}
	}

	@Override
	public void doDelete() throws Exception {
		Con1006Db obj = (Con1006Db)View.getObject(" from Con1006Db where id = " + Common.getLong(getDetailid()));
		if(obj != null){
			CommonUser user = (CommonUser)View.getObject(" from CommonUser where id = " + Common.getLong(obj.getCommonUser().getId()));
			//先刪除原權限
		    java.util.List<CommonUserGroup> deleteList = new java.util.ArrayList<CommonUserGroup>();
		    String hql = "select id,groupName from CommonGroup where groupName in ('藥品不良品-廠商','藥品療效不等-廠商','化粧品不良品-廠商','化粧品不良反應-廠商'," +
    		             "'醫療器材不良品-廠商','醫療器材定期安全監視-廠商','醫療器材國內外安全警訊-廠商','國內外藥品品質警訊-廠商','藥品重大品質事件-廠商')";
			java.util.List objList = ServiceGetter.getInstance().getCommonService().load(hql);	        
			if(objList!=null && objList.size()>0)
			{
				java.util.Iterator it = objList.iterator();
				while (it.hasNext()) 
				{					
					Object[] o = (Object[])it.next();
					CommonUserGroup obj2 = (CommonUserGroup)View.getObject(" from CommonUserGroup where commonUser.id="+user.getId()+" and commonGroup.id="+Common.getLong(o[0]));
                    if(obj2 != null) deleteList.add(obj2);
				}
			}
			if(deleteList != null){
				ServiceGetter.getInstance().getTcbwService().deleteAndSave(deleteList, null);
				deleteList.clear();
			}
			ServiceGetter.getInstance().getTcbwService().delete(obj);
			setDetailid("");
		}else{
			throw new Exception("查無資料，無法刪除，請重新操作 !");
		}
	}
	
	public CommonGroup getCommonGroup(String formCode) throws Exception {
		CommonGroup group = null;
		if(!"".equals(Common.get(formCode))){			
			if("DRG01".equals(Common.get(formCode))){
				group = (CommonGroup)View.getObject(" from CommonGroup where groupName='藥品不良品-廠商'");	
			}else if("DRG02".equals(Common.get(formCode))){
				group = (CommonGroup)View.getObject(" from CommonGroup where groupName='藥品療效不等-廠商'");	
			}else if("COS02".equals(Common.get(formCode))){
				group = (CommonGroup)View.getObject(" from CommonGroup where groupName='化粧品不良品-廠商'");	
			}else if("COS03".equals(Common.get(formCode))){
				group = (CommonGroup)View.getObject(" from CommonGroup where groupName='化粧品不良反應-廠商'");	
			}else if("MED02".equals(Common.get(formCode))){
				group = (CommonGroup)View.getObject(" from CommonGroup where groupName='醫療器材不良品-廠商'");	
			}else if("MED05".equals(Common.get(formCode))){
				group = (CommonGroup)View.getObject(" from CommonGroup where groupName='醫療器材定期安全監視-廠商'");	
			}else if("MED06".equals(Common.get(formCode))){
				group = (CommonGroup)View.getObject(" from CommonGroup where groupName='醫療器材國內外安全警訊-廠商'");	
			}else if("DRG03".equals(Common.get(formCode))){
				group = (CommonGroup)View.getObject(" from CommonGroup where groupName='國內外藥品品質警訊-廠商'");	
			}else if("DRG04".equals(Common.get(formCode))){
				group = (CommonGroup)View.getObject(" from CommonGroup where groupName='藥品重大品質事件-廠商'");	
			}
			return group;
		}
		return group;
	}

}
