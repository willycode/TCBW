package com.kangdainfo.tcbw.view.conin;

import java.util.Map;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.model.bo.CommonGroup;
import com.kangdainfo.common.model.bo.CommonUser;
import com.kangdainfo.common.model.bo.CommonUserGroup;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.Datetime;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;

public class CONIN0302F extends CONIN0301F{

	private String id;
	private String strAuth;
	private String strAuthed;
	
	public String getId() {	return checkGet(id);}
	public void setId(String id) {	this.id = checkSet(id);}
	public String getStrAuth() {		return strAuth;	}
	public void setStrAuth(String strAuth) {		this.strAuth = strAuth;	}
	public String getStrAuthed() {		return strAuthed;	}
	public void setStrAuthed(String strAuthed) {		this.strAuthed = strAuthed;	}
	
	String[] subSystem;
	public String[] getSubSystem() {	return subSystem;	}
	public void setSubSystem(String[] subSystem) {		this.subSystem = subSystem;	}
	
	String jsMsg;
	String optype;
	public String getOptype() {return optype;}
	public void setOptype(String s) {optype = s;}	
	public String getJsMsg() {return Common.get(jsMsg);	}
	public void setJsMsg(String jsMsg) {this.jsMsg = Common.set(jsMsg);	}
	
	String[] auth;
	String[] authed;
	public void setAuth(String[] s) {auth = s;}
	public String[] getAuth() {return auth;}
	public void setAuthed(String[] s) {authed = s;}
	public String[] getAuthed() {return authed;}	

	@Override
	public Object doQueryOne() throws Exception {
		CONIN0302F obj = this;
		
		CommonUser c = (CommonUser)View.getObject(" from CommonUser where id = " + Common.getInt(getId()));

		if(c != null){
			
			// 子系統別資料
			String authSubSystem = "";
			String userSubSystem = View.getLookupField("select subSystem from CommonUser where userId="+Common.sqlChar(getUserID()));

			String[] subSystems = Common.get(userSubSystem).split(",");
			if(subSystems!=null && subSystems.length>0){
				for(String s : subSystems){
					if(authSubSystem.length() > 0){
						authSubSystem += ",";
					}
					authSubSystem += Common.sqlChar(s);
				}
			}
			
			obj.setId(Common.get(c.getId()));
			obj.setSubSystem(subSystems);
			obj.setEditID(c.getEditId());
			obj.setEditDate(c.getEditDate());

			
			// 可授權資料
			String authhql = " from CommonGroup where subSystem in (" + authSubSystem + ") order by id ";
			java.util.List authList = ServiceGetter.getInstance().getTcbwService().load(authhql);
			StringBuffer sb1 = new StringBuffer();
			String[] authObj = new String[authList.size()];
			if(authList!=null && authList.size()>0){
				int i=0;
				for(Object dtlObj : authList){
					CommonGroup dtl = (CommonGroup)dtlObj;
					sb1.append("<option value='").append(Common.get(dtl.getId())).append("'>")
					   .append(Common.get(dtl.getGroupName())).append("</option>");
					authObj[i] = Common.get(dtl.getId());
					i++;
				}
				
				authList.clear();
			}else{
				sb1.append("<option value=''>")
				   .append("登入者未有子系統別資料，請先建立").append("</option>");
			}
			obj.setStrAuth(sb1.toString());

			// 已授權資料
			String authedhql = " from CommonUserGroup where commonUser.id = " + Common.getInt(c.getId());
			java.util.List authedList = ServiceGetter.getInstance().getTcbwService().load(authedhql);			
			if(authedList!=null && authedList.size()>0){
				StringBuffer sb2 = new StringBuffer();
				for(Object dtlObj : authedList){
					boolean noUpdate = true;
					CommonUserGroup dtl2 = (CommonUserGroup)dtlObj;				
					if(dtl2.getCommonGroup() != null){
						sb2.append("<option value='").append(Common.get(dtl2.getCommonGroup().getId())).append("'");
						if (authObj!=null && authObj.length>0) {            			
							for (int j=0; j< authObj.length; j++) {
								if(Common.get(dtl2.getCommonGroup().getId()).equals(authObj[j])){
									noUpdate = false;
									break;
								}   			
							}            		
						}
						if(noUpdate) sb2.append("disabled");
						sb2.append(">").append(Common.get(dtl2.getCommonGroup().getGroupDesc())).append("</option>");
					}
				}
				obj.setStrAuthed(sb2.toString());
				authedList.clear();
			}
		}else{
			obj.setErrorMsg("未能取得登入者資料，無法進行授權動作!");
		}
		return obj;
	}


	public void updateCommonSysDeptUser() {
		if (!"".equals(getId()) &&  optype!=null && optype.length()>0) {		
			try {
				CommonUser user = (CommonUser) View.getObject("from CommonUser where id="+getId());			
				if("add".equals(optype) && auth!=null && auth.length>0){	
					if (user!=null) {
						boolean isUpdate = true;		
						String yyyMMdd = Datetime.getYYYMMDD();
						String hhMMss = Datetime.getHHMMSS();
						for (int i=0; i<auth.length; i++) {
							isUpdate = true;
							CommonUserGroup obj = (CommonUserGroup) View.getObject("from CommonUserGroup where commonGroup.id="+Common.sqlChar(auth[i])+" and commonUser.id="+getId());
							if (obj==null) {
								obj = new CommonUserGroup();
								isUpdate = false;
							}
							obj.setCommonUser(user);
							
							CommonGroup group = (CommonGroup)View.getObject("from CommonGroup where id="+Common.sqlChar(auth[i]));						
							obj.setCommonGroup(group);
							obj.setEditId(getUserID());
							obj.setEditDate(yyyMMdd);
							obj.setEditTime(hhMMss);							

							if (isUpdate) ServiceGetter.getInstance().getTcbwService().update(obj);
							else ServiceGetter.getInstance().getTcbwService().save(obj);
						}					
					}
					this.setJsMsg("資料新增/修改成功");
				}else if("remove".equals(optype) && authed!=null && authed.length>0){							
					String hql = "delete from  CommonUserGroup a where a.commonGroup.id=? "+" and a.commonUser.id="+getId();
					for (int i=0; i<authed.length; i++) {
						ServiceGetter.getInstance().getCommonService().getCommonUserDao().getHibernateTemplate().bulkUpdate(hql,Integer.parseInt(authed[i]));
					}			
					this.setJsMsg("已成功刪除資料");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}		
	}
	
	@Override
	public Object doQueryAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
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
