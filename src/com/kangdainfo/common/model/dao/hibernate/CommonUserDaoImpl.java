package com.kangdainfo.common.model.dao.hibernate;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.model.bo.*;
import com.kangdainfo.common.model.dao.CommonUserDao;
import com.kangdainfo.common.util.*;
import com.kangdainfo.persistence.hibernate3.BaseDaoImpl;
import com.kangdainfo.persistence.hibernate3.query.params.QueryParam;
import com.kangdainfo.persistence.hibernate3.query.params.QueryParams;

public class CommonUserDaoImpl extends BaseDaoImpl implements CommonUserDao {
	
    public void saveCommonUser(CommonUser obj) {
    	save(obj);
    }

    public void updateCommonUser(CommonUser obj) {
	    update(obj);
    }

    public void saveOrUpdateCommonUser(CommonUser obj) {
        saveOrUpdate(obj);
    }
	
	public void deleteCommonUser(CommonUser obj) {
		delete(CommonUserRole.class,obj.getId());
		delete(obj);
    }
    
    public void deleteCommonUser(int id) {
		String hql = "from CommonUserGroup where commonUser.id=" + id;
		java.util.List list = this.load(hql);
		if (list!=null && list.size()>0) this.getHibernateTemplate().deleteAll(load(hql));		
    	delete(CommonUserRole.class,id);
	    delete(CommonUser.class,id);
    }    
    
    public CommonUser loadCommonUser(int id) {
    	return (CommonUser) load(CommonUser.class,id);
    }
    
	public CommonUser getCommonUserByUserId(String userId) {
		if (Validate.checkSpecialChar(userId)==false) {
			String hql = "from CommonUser where userId=" + Common.sqlChar(userId);			
			return (CommonUser) getObject(hql);
		}
		return null;
	}    

	public CommonUser saveOrGetVerifiedUser(String userId, String userPwd) throws Exception {
		if (Validate.checkSpecialChar(userId)==false && Validate.checkSpecialChar(userPwd)==false) {			
			String hql = "from CommonUser where userId=" + Common.sqlChar(userId) + 
				" and userPwd=" + Common.sqlChar(Common.getDigestString(userPwd,"SHA-1")) + " and (isStop is null or isStop!='Y') ";			
			CommonUser user = (CommonUser) getObject(hql);
			if (user!=null) return user;
			
			else {
				com.kangdainfo.tcbw.util.FdaAdUtil adUtil = new com.kangdainfo.tcbw.util.FdaAdUtil(userId, userPwd);				
				if (adUtil.isConnected() && adUtil.getFdaUser()!=null) {
					hql = "from CommonUser where userId=" + Common.sqlChar(userId);
					user = (CommonUser) getObject(hql);				
					if (user==null) {
						return null;
					} else {
						return user;
					}
				}
			}
		}
		return null;
	}
	
	public CommonUser saveOrGetVerifiedUser(String userId) throws Exception 
	{
		if (Validate.checkSpecialChar(userId)==false ) 
		{			
			String hql = "from CommonUser where userId=" + Common.sqlChar(userId) + 
				 " and (isStop is null or isStop!='Y') ";
			
			CommonUser user = (CommonUser) getObject(hql);
			
			if (user!=null) 
			{	
				return user;
			}
			else 
			{
				com.kangdainfo.tcbw.util.FdaAdUtil adUtil = new com.kangdainfo.tcbw.util.FdaAdUtil(userId);	
				
				if (adUtil.isConnected() && adUtil.getFdaUser()!=null) 
				{
					hql = "from CommonUser where userId=" + Common.sqlChar(userId);
					user = (CommonUser) getObject(hql);						
					return user;					
				}
			}
		}
		return null;
	}
	
	public CommonUser reLoginGetVerifiedUser(String userId) throws Exception {
		if (Validate.checkSpecialChar(userId)==false) {			
			String hql = "from CommonUser where userId=" + Common.sqlChar(userId) + 
				         " and (isStop is null or isStop!='Y') ";			
			CommonUser user = (CommonUser) getObject(hql);
			if (user!=null) return user;
		}
		return null;
	}

	public void saveCommonUser(CommonUser obj, int[] groupIds) throws Exception {
		if (obj!=null) {
			save(obj);
			
			CommonUserRole role = new CommonUserRole();
			role.setId(obj.getId());
			role.setRoleId(obj.getRoleId());
			role.setCommonUser(obj);			
			role.setCommonGroup(obj.getCommonGroup());
			save(role);
			
			if (groupIds!=null && groupIds.length>0) {
				
				for (int i=0; i<groupIds.length; i++) {
					CommonUserGroup userGroup = new CommonUserGroup();
					userGroup.setCommonUser(obj);
					
					CommonGroup group = new CommonGroup();
					group.setId(groupIds[i]);
					
					userGroup.setCommonGroup(group);
					userGroup.setEditId(obj.getEditId());
					userGroup.setEditDate(obj.getEditDate());
					userGroup.setEditTime(obj.getEditTime());
					
					save(userGroup);
				}
			}
		}
	}

	public void updateCommonUser(CommonUser obj, int[] groupIds) throws Exception {
		if (obj!=null && obj.getId()!=null) {
			update(obj);
			
			boolean isUpdate = true;
			CommonUserRole role = (CommonUserRole) View.getObject("from CommonUserRole where id=" + obj.getId());
			if (role==null) {
				role = new CommonUserRole();
				role.setId(obj.getId());
				role.setCommonUser(obj);
				isUpdate = false;
			}
			role.setRoleId(obj.getRoleId());
			role.setCommonGroup(obj.getCommonGroup());	
			if (isUpdate) update(role);
			else save(role);
			
			if (groupIds!=null && groupIds.length>0) {
							
				java.util.Map<Integer, String> h = new java.util.HashMap<Integer,String>();
				String hql = "from CommonUserGroup where commonUser.id=" + obj.getId();
				java.util.List list = this.load(hql);				
				if (list!=null && list.size()>0) {
					for (int i=0; i<list.size(); i++) {
						CommonUserGroup g = (CommonUserGroup) list.get(i);
						h.put(g.getCommonGroup().getId(), "Y");
					}
				}
				
				StringBuilder sb = new StringBuilder().append("-99");
				for (int i=0; i<groupIds.length; i++) {
					sb.append(",").append(groupIds[i]);					
					if (!h.containsKey(groupIds[i])) {
						h.put(groupIds[i], "Y");
						
						CommonUserGroup userGroup = new CommonUserGroup();
						userGroup.setCommonUser(obj);
						
						CommonGroup group = new CommonGroup();
						group.setId(groupIds[i]);
						
						userGroup.setCommonGroup(group);
						userGroup.setEditId(obj.getEditId());
						userGroup.setEditDate(obj.getEditDate());
						userGroup.setEditTime(obj.getEditTime());						
						save(userGroup);						
					}
				}
				if (!sb.toString().equals("-99")) {
					hql = "from CommonUserGroup where commonUser.id=" + obj.getId() + " and commonGroup.id not in (" + sb.toString() + ") ";
					list = this.load(hql);	
					if (list!=null && list.size()>0) {
						this.deleteBatch(list);							
					}					
				}
			} else {
				String hql = "from CommonUserGroup where commonUser.id=" + obj.getId();
				java.util.List list = this.load(hql);	
				if (list!=null && list.size()>0) this.deleteBatch(list);
			}
		}		
	}
	public String buildCheckBoxTree(String treeID, String treeName, String checkboxName, String checkboxPrefix, String jsFunctionName, String sysId, String groupID, CommonUserRole userRole, boolean bIncludeAll, boolean bUrl, boolean bRootCheckBox) throws Exception {
		CommonUserGroup[] g = null;
		
		StringBuilder sb = new StringBuilder(1024).append("");
		if (Common.get(treeName).equals("")) treeName = "功能選單";
		
		//建立根節點
		sb.append(treeID).append(".add(");					
		sb.append(sysId).append(",-1,'");		
		if (bRootCheckBox) {
			sb.append("<input type=checkbox id=").append(checkboxPrefix).append(sysId).append(" name=").append(checkboxName).append(" class=checkbox onclick=");
			sb.append(jsFunctionName).append("(this,\"").append(treeName).append("\") value=").append(sysId).append(">");			
		}		
		sb.append(treeName).append("'");
		if (bUrl) sb.append(",'dTreeForm.jsp?sid=").append(sysId).append("&fid=-1'");
		sb.append(");\n");

		CommonUser[] ur = null;		
		if (bIncludeAll) {
			if (userRole!=null) {
				//可能會用到userRole?
				//if (userRole.getRoleId()!=null && userRole.getRoleId()>2) ur = ServiceGetter.getInstance().getCommonService().getCommonUserDao().getAllNode();
				ur = getAllNode(groupID);
			} else {
				ur = getAllNode(groupID);
			}			
		} else {
			if (groupID!=null && !"".equals(Common.get(groupID))) {
				g = getUserGroup(groupID);
				ur = getCommonUser(g);
			}
		}
		String pidPrefix = "99999";
		CommonDepartment[] dept = ServiceGetter.getInstance().getCommonService().getCommonDepartmentDao().getAllNode();
		if (dept!=null && dept.length>0) {
			for (int i=0; i<dept.length; i++) {
				sb.append(treeID).append(".add(");	
				sb.append(pidPrefix+dept[i].getId()).append(",");
				//全部都連結在根結點
				sb.append(sysId).append(",");
				sb.append("'<input type=checkbox id=").append(checkboxPrefix).append(pidPrefix).append(dept[i].getId()).append(" name=").append(checkboxName).append(" class=checkbox onclick=").append(jsFunctionName).append("(this,\"").append(Common.escapeJavaScript(dept[i].getFullName())).append("\") value=").append(pidPrefix+dept[i].getId()).append(">");
				sb.append(dept[i].getFullName());				
				sb.append("',");
				sb.append("''");
				sb.append(");\n");
			}
		}
		//id, pid, name, url, title, target, icon, iconOpen, open
		if (ur!=null && ur.length>0) {
			for (int i=0; i<ur.length; i++) {
				sb.append(treeID).append(".add(");	
				sb.append(ur[i].getId()).append(",");
				//要連到父結點-依據commondepartment分類
				sb.append(pidPrefix+ur[i].getCommonDepartment().getId()).append(",");
				
				boolean isAuthorize = false;
				String cssClass = "";
				/*應該是不需要依據權限加上任何CSS
				if (g!=null && g.length>0) {
					for(int j=0;j<g.length;j++){
						if(g[j].getCommonUser().getId().equals(ur[i].getId())) {
							isAuthorize = true;
							cssClass = "\"auth" + g[j].get() + "\"";
							break;
						}	
					}					
				}	
				*/	
				sb.append("'<input type=checkbox id=").append(checkboxPrefix).append(ur[i].getId()).append(" name=").append(checkboxName).append(" class=checkbox onclick=").append(jsFunctionName).append("(this,\"").append(Common.escapeJavaScript(ur[i].getUserName())).append("\") value=").append(ur[i].getId()).append(">");
				if (isAuthorize) sb.append("<span class=").append(cssClass).append(">").append(Common.escapeJavaScript(ur[i].getUserName())).append("</span>");
				else sb.append(ur[i].getUserName());				
				sb.append("',");

				//似乎不需要用到
				/*if (bUrl) sb.append("'").append("dTreeForm.jsp?sid=").append(ur[i].getId()).append("&fid=").append(ur[i].getId()).append("'");
				else sb.append("''");*/
				sb.append("''");

				sb.append(");\n");
			}
			return sb.toString();
		}		
		return sb.toString();
	}

	public CommonUser[] getAllNode() throws Exception {
		return getAllNode(null);
	}
	public CommonUser[] getAllNode(String groupID) throws Exception{		
		java.util.List list = getHibernateTemplate().find("from " + CommonUser.class.getCanonicalName() + " where 1=1 and inORout = 'in' order by id");
		int i = 0;
		if (list!=null && list.size()>0) {
			CommonUser refObj[] = null;
			int length = list.size();
			if(Validate.checkInt(groupID)){
				refObj = getCommonUser(getUserGroup(groupID));
				if(refObj != null){
					for(int j = 0 ; j < list.size() ; j ++){
						CommonUser user = (CommonUser)list.get(j);
						for(CommonUser userRef : refObj){
							if(user.getId().equals(userRef.getId())){
								list.remove(j);
								j--;length --;
							}
						}
					}
				}
			}
			CommonUser obj[] = new CommonUser[length];
			for (i=0; i<list.size(); i++) {
				CommonUser user = (CommonUser) list.get(i);
				if(user != null)
					obj[i] = user;
			}
			return obj;
		}
		return null;
	}
	
	public CommonUserGroup[] getUserGroup(String groupID) throws Exception {
		if (groupID!=null && Validate.checkInt(groupID)) {
			this.getHibernateTemplate().clear();
			String hql = "from " + CommonUserGroup.class.getCanonicalName() + " where commonGroup.id=" + groupID ;
			java.util.List list = this.getHibernateTemplate().find(hql);
			if (list!=null && list.size()>0) {
				CommonUserGroup[] objList = new CommonUserGroup[list.size()];
				for (int i=0; i<list.size(); i++){
					objList[i] = (CommonUserGroup) list.get(i);
				}
				return objList;
			}
		}
		return null;
	}
	public CommonUser[] getCommonUser(CommonUserGroup[] commonUserGroup) throws Exception {
		if (commonUserGroup!=null && commonUserGroup.length>0) {
			java.util.Map<String, CommonUser> h = new java.util.LinkedHashMap<String, CommonUser>();			
			for (int i=0; i<commonUserGroup.length; i++) {
				CommonUser ur = commonUserGroup[i].getCommonUser();
				if("in".equals(ur.getInORout()))
					h.put(ur.getId().toString(), ur);				
			}
			if (h!=null && h.size()>0) {
				return h.values().toArray(new CommonUser[0]);			
			}
		}
		return null;
	}
	public void updateUserGroup(String optype, String groupId, String[] treeId, String authType, String editId) throws Exception {
		if (optype!=null && optype.length()>0) {
			int i = 0;
			if("add".equals(optype) && treeId!=null && treeId.length>0){				
				for (i=0; i<treeId.length; i++) {			
					CommonUserGroup userGroup = new CommonUserGroup();
					CommonUser user = (CommonUser)this.getObject("from "+CommonUser.class.getCanonicalName()+" where id = " + treeId[i]);
					CommonGroup group = (CommonGroup)this.getObject("from "+CommonGroup.class.getCanonicalName()+" where id = " + groupId);
					if(user != null && group != null){
						userGroup.setCommonGroup(group);
						userGroup.setCommonUser(user);
						userGroup.setEditId(editId);
						userGroup.setEditDate(Datetime.getYYYMMDD());
						userGroup.setEditTime(Datetime.getHHMMSS());
						this.save(userGroup);
					}										
				}
			}else if("remove".equals(optype) && treeId!=null && treeId.length>0){				
				for (i=0; i<treeId.length; i++) {
					CommonUserGroup objUserGroup = getCommonUserGroup(groupId, treeId[i]);
					if(objUserGroup!=null)delete(objUserGroup);				
				}
			}
		}		
	}

	private CommonUserGroup getCommonUserGroup(String groupID, String treeId) {
		QueryParams q = new QueryParams();
		q.add(new QueryParam("and","commonGroup.id","=",groupID));
		q.add(new QueryParam("and","commonUser.id","=",treeId));
		java.util.List list = this.loadObjectsByParams(CommonUserGroup.class, q);
		if (list!=null && list.size()>0) {
			return (CommonUserGroup) list.get(0);
		}
		return null;
	}
}
