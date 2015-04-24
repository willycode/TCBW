package com.kangdainfo.common.model.dao.hibernate;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.model.bo.*;
import com.kangdainfo.common.model.dao.CommonAuthDao;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.Datetime;
import com.kangdainfo.common.util.Validate;
import com.kangdainfo.common.util.View;
import com.kangdainfo.persistence.hibernate3.BaseDaoImpl;
import com.kangdainfo.persistence.hibernate3.query.params.*;
import com.kangdainfo.tcbw.util.TCBWCommon;

public class CommonAuthDaoImpl extends BaseDaoImpl implements CommonAuthDao {
	
    public void saveCommonAuth(CommonAuth obj) {
    	save(obj);
    }

    public void updateCommonAuth(CommonAuth obj) {
	    update(obj);
    }

    public void saveOrUpdateCommonAuth(CommonAuth obj) {
        saveOrUpdate(obj);
    }
	
	public void deleteCommonAuth(CommonAuth obj) {
		delete(obj);
    }
    
    public void deleteCommonAuth(int id) {
	    delete(CommonAuth.class,id);
    }    
    
    public CommonAuth loadCommonAuth(int id) {
    	return (CommonAuth) load(CommonAuth.class,id);
    }
    
	public CommonAuth getCommonAuth(String groupID, String treeId) {
		QueryParams q = new QueryParams();
		q.add(new QueryParam("and","commonGroup.id","=",groupID));
		q.add(new QueryParam("and","commonDtree.id","=",treeId));
		java.util.List list = this.loadObjectsByParams(CommonAuth.class, q);
		if (list!=null && list.size()>0) {
			return (CommonAuth) list.get(0);
		}
		return null;
	}
	
	public CommonAuth getCommonAuth(CommonUserRole userRole, String treeId) {
		if (userRole!=null && treeId!=null) {
			Integer[] gids = null;		
			String hql = "from " + CommonUserGroup.class.getCanonicalName() + " where commonUser.id=" + userRole.getId();
			java.util.List list =  load(hql);
			if (list!=null && list.size()>0) {
				gids = new Integer[list.size()];
				for (int i=0; i<list.size(); i++) {
					CommonUserGroup g = (CommonUserGroup) list.get(i);
					gids[i] = g.getCommonGroup().getId();			
				}
			}
			if (gids!=null && gids.length>0) {
				QueryParams q = new QueryParams();
				q.add(new QueryParam("and","commonGroup.id","in",gids));
				q.add(new QueryParam("and","commonDtree.id","=",treeId));
				list = this.loadObjectsByParams(CommonAuth.class, q, "auth", false);
				if (list!=null && list.size()>0) {
					return (CommonAuth) list.get(0);
				}				
			}			
		}
		return null;
	}	
	
	
	public void updateAuth(String optype, String groupId, String[] treeId, String authType, String editId) throws Exception {
		if (optype!=null && optype.length()>0) {
			int i = 0;
			if("add".equals(optype) && treeId!=null && treeId.length>0){				
				for (i=0; i<treeId.length; i++) {					
					CommonAuth objAuth = getCommonAuth(groupId, treeId[i]);
					if (objAuth==null) objAuth = new CommonAuth();
					CommonGroup objGroup = new CommonGroup();
					objGroup.setId(Integer.parseInt(groupId));					
					objAuth.setCommonGroup(objGroup);
					CommonDtree tree = new CommonDtree();
					tree.setId(Long.parseLong(treeId[i]));
					objAuth.setCommonDtree(tree);
					objAuth.setAuth(Integer.parseInt(authType));
					objAuth.setEditId(editId);
					objAuth.setEditDate(Datetime.getYYYMMDD());
					objAuth.setEditTime(Datetime.getHHMMSS());
					this.saveOrUpdateCommonAuth(objAuth);					
				}
			}else if("remove".equals(optype) && treeId!=null && treeId.length>0){				
				for (i=0; i<treeId.length; i++) {
					CommonAuth objAuth = getCommonAuth(groupId, treeId[i]);
					deleteCommonAuth(objAuth);				
				}
			}
		}		
	}

	public CommonAuth[] getPermission(String groupID) throws Exception {
		if (groupID!=null && Validate.checkInt(groupID)) {
			this.getHibernateTemplate().clear();
			String hql = "from " + CommonAuth.class.getCanonicalName() + " where commonGroup.id=" + groupID + " order by commonDtree.sorted, commonDtree.id ";
			java.util.List list = this.getHibernateTemplate().find(hql);
			if (list!=null && list.size()>0) {
				CommonAuth[] objList = new CommonAuth[list.size()];
				for (int i=0; i<list.size(); i++){
					objList[i] = (CommonAuth) list.get(i);
				}
				return objList;
			}
		}
		return null;
	}	
	
	/**
	 * key = 功能代號<br>
	 * obj[0] = 權限 - int 型態<br>
	 * obj[1] = 功能選單物件 - CommonDtree<br> 
	 * @param sysId
	 * @param userRole
	 * @return
	 * @throws Exception
	 */
	public java.util.Map<String, Object[]> getPermissionMap(String sysId, CommonUserRole userRole) throws Exception {		
		CommonAuth[] auth = getPermission(sysId, userRole);
		return getPermissionMap(auth);
	}
	
	/**
	 * key = 功能代號<br>
	 * obj[0] = 權限 - int 型態<br>
	 * obj[1] = 功能選單物件 - CommonDtree<br> 
	 * @param sysId
	 * @param userRole
	 * @return
	 * @throws Exception
	 */	
	public java.util.Map<String, Object[]> getPermissionMap(CommonAuth[] auth) throws Exception {
		if (auth!=null && auth.length>0) {
			java.util.Map<String, Object[]> h = new java.util.HashMap<String, Object[]>();
			for (int i=0; i<auth.length; i++) {
				CommonAuth authObj = auth[i];				
				CommonDtree dtree = authObj.getCommonDtree();	
				String key = Common.get(dtree.getBtnRead());
				if (!"".equals(key)) {
					Object[] o = new Object[2];
					o[0] = authObj.getAuth();
					o[1] = dtree;					
					h.put(key, o);
				}
			}
			return h;
		}	
		return null;
	}	
	
	public CommonAuth[] getPermission(String sysId, CommonUserRole userRole) throws Exception {
		if (userRole!=null && userRole.getId()!=null) {
			if (userRole.isUserLevel()) {
				this.getHibernateTemplate().clear();
				StringBuilder sb = new StringBuilder().append("-99");		
				String hql = "from " + CommonUserGroup.class.getCanonicalName() + " where commonUser.id=" + userRole.getId();
				java.util.List list =  load(hql);
				if (list!=null && list.size()>0) {
					for (int i=0; i<list.size(); i++) {
						CommonUserGroup g = (CommonUserGroup) list.get(i);
						sb.append(",").append(g.getCommonGroup().getId());			
					}
				}				
				hql = "from " + CommonAuth.class.getCanonicalName() + " where commonDtree.sysid=" + Common.getInt(sysId) + " and commonGroup.id in (" + sb.toString() + ") order by commonDtree.sorted, commonDtree.id ";
				list = this.getHibernateTemplate().find(hql);
				if (list!=null && list.size()>0) {
					java.util.Map<Long, CommonAuth> h = new java.util.LinkedHashMap<Long, CommonAuth>();				
					for (int i=0; i<list.size(); i++){					
						CommonAuth auth = (CommonAuth) list.get(i);
						
						CommonAuth authed = h.get(auth.getCommonDtree().getId());
						if (authed==null) {
							h.put(auth.getCommonDtree().getId(), auth);
						} else if (auth.getAuth()>authed.getAuth()) {
							h.put(auth.getCommonDtree().getId(), auth);
						}
					}
					if (h!=null && h.size()>0) {
						return h.values().toArray(new CommonAuth[0]);					
					}						
				}				
			} else {				
				java.util.List list = getHibernateTemplate().find("from " + CommonDtree.class.getCanonicalName() + " where sysid = ? order by sorted, pid, id",Common.getInt(sysId));				
				if (list!=null && list.size()>0) {					
					CommonAuth[] auths = new CommonAuth[list.size()];
					for (int i=0; i<list.size(); i++) { 	
						CommonAuth auth = new CommonAuth();
						auth.setAuth(2);
						auth.setCommonDtree((CommonDtree) list.get(i));	
						auths[i] = auth;							
					}
					return auths;
				}			
			}
		}
		return null;
	}	
	
	
	public CommonDtree[] getPermissionCommonDtree(CommonAuth[] permission) throws Exception {
		if (permission!=null && permission.length>0) {
			java.util.Map<String, CommonDtree> h = this.getPermissionCommonDtreeMap(permission);
			if (h!=null && h.size()>0) {
				return h.values().toArray(new CommonDtree[0]);			
			}
		}
		return null;
	}
	
	public java.util.Map<String, CommonDtree> getPermissionCommonDtreeMap(CommonAuth[] permission) throws Exception {
		if (permission!=null && permission.length>0) {
			java.util.Map<String, CommonDtree> h = new java.util.LinkedHashMap<String, CommonDtree>();			
			for (int i=0; i<permission.length; i++) {
				CommonDtree dt = permission[i].getCommonDtree();
				h.put(dt.getId().toString(), dt);				
			}
			return h;
		}
		return null;
	}
	
	public CommonDtree[] getPermissionCommonDtree(Integer groupID) throws Exception {
		if (groupID!=null) {
			CommonAuth[] objList = getPermission(groupID.toString());
			if (objList!=null && objList.length>0) {
				CommonDtree[] objDtree = new CommonDtree[objList.length];
				for (int i=0; i<objList.length; i++){
					objDtree[i] = objList[i].getCommonDtree();
				}
				return objDtree;
			}
		}
		return null;
	}	
	
	public CommonDtree[] getPermissionCommonDtree(String sysId, CommonUserRole userRole) throws Exception {
		return getPermissionCommonDtree(getPermission(sysId, userRole));
	}	
	
	public java.util.Map<String, CommonDtree> getPermissionCommonDtreeMap(String sysId, CommonUserRole userRole) throws Exception {
		return getPermissionCommonDtreeMap(getPermission(sysId, userRole));
	}

	public String buildCheckBoxTree(String treeID, String treeName, String checkboxName, String checkboxPrefix, String jsFunctionName, String sysId, String groupID, CommonUserRole userRole, boolean bIncludeAll, boolean bUrl, boolean bRootCheckBox) throws Exception {
		CommonAuth[] p = null;
		
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

		CommonDtree[] dt = null;		
		if (bIncludeAll) {
			if (userRole!=null) {
				if (userRole.getRoleId()!=null && userRole.getRoleId()>2) dt = ServiceGetter.getInstance().getCommonService().getCommonDtreeDao().getAllNode(Integer.parseInt(sysId));
				else if (userRole.getId()!=null) dt = this.getPermissionCommonDtree(sysId, userRole);
			} else {
				dt = ServiceGetter.getInstance().getCommonService().getCommonDtreeDao().getAllNode(Integer.parseInt(sysId));
			}			
		} else {
			if (groupID!=null && !"".equals(Common.get(groupID))) {
				p = getPermission(groupID);
				dt = getPermissionCommonDtree(p);
			}
		}
		
		//id, pid, name, url, title, target, icon, iconOpen, open
		if (dt!=null && dt.length>0) {
			for (int i=0; i<dt.length; i++) {
				sb.append(treeID).append(".add(");	
				sb.append(dt[i].getId()).append(",");
				
				if (dt[i].getPid()!=null) sb.append(dt[i].getPid()).append(",");
				else sb.append(sysId).append(",");
				
				boolean isAuthorize = false;
				String cssClass = "";
				if (p!=null && p.length>0) {
					for(int j=0;j<p.length;j++){
						if(p[j].getCommonDtree().getId().equals(dt[i].getId())) {
							isAuthorize = true;
							cssClass = "\"auth" + p[j].getAuth() + "\"";
							break;
						}	
					}					
				}				
				sb.append("'<input type=checkbox id=").append(checkboxPrefix).append(dt[i].getId()).append(" name=").append(checkboxName).append(" class=checkbox onclick=").append(jsFunctionName).append("(this,\"").append(Common.escapeJavaScript(dt[i].getName())).append("\") value=").append(dt[i].getId()).append(">");
				if (isAuthorize) sb.append("<span class=").append(cssClass).append(">").append(Common.escapeJavaScript(dt[i].getName())).append("</span>");
				else sb.append(dt[i].getName());				
				sb.append("',");

				if (bUrl) sb.append("'").append("dTreeForm.jsp?sid=").append(dt[i].getId()).append("&fid=").append(dt[i].getPid()).append("'");
				else sb.append("''");

				sb.append(");\n");
			}
			return sb.toString();
		}		
		
		return sb.toString();
	}
	
	public String buildCheckBoxTree(String treeID, String treeName, String checkboxName, String checkboxPrefix, String jsFunctionName, String sysId, CommonUserRole userRole, boolean bIncludeAll, boolean bUrl, boolean bRootCheckBox) throws Exception {
		CommonAuth[] p = null;
		
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

		CommonDtree[] dt = null;
		
		if (bIncludeAll) dt = ServiceGetter.getInstance().getCommonService().getCommonDtreeDao().getAllNode(Integer.parseInt(sysId));
		else if (userRole!=null && userRole.getId()!=null) {	
			p = this.getPermission(sysId, userRole);
			dt = getPermissionCommonDtree(p);			
		}
		
		//id, pid, name, url, title, target, icon, iconOpen, open
		if (dt!=null && dt.length>0) {
			for (int i=0; i<dt.length; i++) {
				sb.append(treeID).append(".add(");	
				sb.append(dt[i].getId()).append(",");
				
				if (dt[i].getPid()!=null) sb.append(dt[i].getPid()).append(",");
				else sb.append(sysId).append(",");
				
				boolean isAuthorize = false;
				String cssClass = "";
				if (p!=null && p.length>0) {
					for(int j=0;j<p.length;j++){
						if(p[j].getCommonDtree().getId().equals(dt[i].getId())) {
							isAuthorize = true;
							cssClass = "\"auth" + p[j].getAuth() + "\"";
							break;
						}	
					}					
				}				
				sb.append("'<input type=checkbox id=").append(checkboxPrefix).append(dt[i].getId()).append(" name=").append(checkboxName).append(" class=checkbox onclick=").append(jsFunctionName).append("(this,\"").append(Common.escapeJavaScript(dt[i].getName())).append("\") value=").append(dt[i].getId()).append(">");
				if (isAuthorize) sb.append("<span class=").append(cssClass).append(">").append(Common.escapeJavaScript(dt[i].getName())).append("</span>");
				else sb.append(dt[i].getName());				
				sb.append("',");

				if (bUrl) sb.append("'").append("dTreeForm.jsp?sid=").append(dt[i].getId()).append("&fid=").append(dt[i].getPid()).append("'");
				else sb.append("''");

				sb.append(");\n");
			}
			return sb.toString();
		}		
		
		return sb.toString();
	}
	

	public String buildAuthorizeMenu(String treeID, String treeName, String sysId, CommonUserRole userRole) {
		return buildAuthorizeMenu(treeID, treeName, sysId, userRole, null);
		/**
		try {
			StringBuilder sb = new StringBuilder(1024);	
			if (Common.get(treeName).equals("")) treeName = "功能選單";			
			//建立根節點
			sb.append(treeID).append(".add(");					
			sb.append(sysId).append(",-1,'").append(treeName).append("');\n");
			
			CommonDtree[] dt = null;			
			if (userRole!=null && userRole.getRoleId()>=3) {				
				dt = ServiceGetter.getInstance().getCommonService().getCommonDtreeDao().getAllNode(Integer.parseInt(sysId));				
			} else if (userRole!=null){
				//dt = getPermissionCommonDtree(getPermission(""+GBGTRole.getCommonGroup().getId()));				
				//dt = getPermissionCommonDtree(userRole.getCommonGroup().getId());
				dt = getPermissionCommonDtree(sysId, userRole);
			}
			
			//id, pid, name, url, title, target, icon, iconOpen, open
			if (dt!=null && dt.length>0) {
				for (int i=0; i<dt.length; i++) {
					sb.append(treeID).append(".add(").append(dt[i].getId()).append(",");
					
					if (dt[i].getPid()!=null) sb.append(dt[i].getPid()).append(",");
					else sb.append(sysId).append(",");
					
					sb.append(Common.sqlChar(dt[i].getName())).append(",'");										
					if (Common.get(dt[i].getUrl()).indexOf('?')!=-1) {
						sb.append(Common.get(dt[i].getUrl())).append("&progID=").append(dt[i].getId());
					} else {
						sb.append(Common.get(dt[i].getUrl())).append("?progID=").append(dt[i].getId());
					}
					sb.append("',");					
					
					sb.append(Common.sqlChar(dt[i].getTitle())).append(",");
					sb.append(Common.sqlChar(dt[i].getTarget())).append(",");
					sb.append(Common.sqlChar(dt[i].getIcon())).append(",");
					sb.append(Common.sqlChar(dt[i].getIconOpen()));
					sb.append(Common.get(dt[i].getOpened()).equals("Y")?",true":"");
					sb.append(");\n");
				}				
			}
			if (userRole!=null && userRole.getRoleId()>=3) {
				sb.append(treeID).append(".add(-1024," + sysId + ",'系統名稱、程式維護','dTreeFrame.jsp?progID=X');\n");
			}			
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
		**/
	}		
	
	
	
	public String buildAuthorizeMenu(String treeID, String treeName, String sysId, CommonUserRole userRole, String fixedLink) 
	{
	  try 
	  {
		    init();
			
		    CommonAuth[] p = null;
			
			StringBuilder sb = new StringBuilder(1024);	
			if (Common.get(treeName).equals("")) treeName = "功能選單";			
			//建立根節點
			sb.append(treeID).append(".add(");					
			sb.append(sysId).append(",-1,'").append(treeName).append("');\n");
			
			
			CommonDtree[] dt = null;			
			if (userRole!=null && userRole.getRoleId()>=3) 
			{				
				dt = ServiceGetter.getInstance().getCommonService().getCommonDtreeDao().getAllNode(Integer.parseInt(sysId));				
			} 
			else if (userRole!=null)
			{
				p = getPermission(sysId, userRole);
				dt = this.getPermissionCommonDtree(p); //getPermissionCommonDtree(sysId, userRole);
			}
			
			String name="";
			
			//id, pid, name, url, title, target, icon, iconOpen, open
			if (dt!=null && dt.length>0) 
			{
				for (int i=0; i<dt.length; i++) 
				{
                    if(!"".equals(Common.get(dt[i].getCode())))
					   name=dtreeNameNum(Common.get(dt[i].getCode()),dt[i].getName());
                    else
                       name=Common.get(dt[i].getName());
                    	
					sb.append(treeID).append(".add(").append(dt[i].getId()).append(",");
					
					if (dt[i].getPid()!=null) sb.append(dt[i].getPid()).append(",");
					else sb.append(sysId).append(",");
					//sb.append(Common.sqlChar(dt[i].getName())).append(",'");
					
					if (fixedLink!=null) 
					{
						boolean isAuthorize = false;
						String cssClass = "";
						if (p!=null && p.length>0)
                        {
							for(int j=0;j<p.length;j++)
							{
								if(p[j].getCommonDtree().getId().equals(dt[i].getId())) 
								{
									isAuthorize = true;
									cssClass = "\"auth" + p[j].getAuth() + "\"";
									break;
								}	
							}					
						}		
										
						if (isAuthorize) 
							sb.append("'<span class=").append(cssClass).append(">").append(Common.get(name)).append("</span>','");
						else sb.append(Common.sqlChar(name)).append(",'");
						
						
						if (!"".equals(Common.get(fixedLink))) 
						{
							if (Common.get(fixedLink).indexOf('?')!=-1) 
							{
								sb.append(Common.get(fixedLink)).append("&progID=").append(dt[i].getId());
							} 
							else 
							{
								sb.append(Common.get(fixedLink)).append("?progID=").append(dt[i].getId());
							}							
						}							
					} 
					else 
					{
						sb.append(Common.sqlChar(name)).append(",'");
						if (Common.get(dt[i].getUrl()).indexOf('?')!=-1) 
						{
							sb.append(Common.get(dt[i].getUrl())).append("&progID=").append(dt[i].getId());
						} 
						else 
						{
							sb.append(Common.get(dt[i].getUrl())).append("?progID=").append(dt[i].getId());
						}						
					}
					sb.append("',");					
					
					sb.append(Common.sqlChar(dt[i].getTitle())).append(",");
					sb.append(Common.sqlChar(dt[i].getTarget())).append(",");
					sb.append(Common.sqlChar(dt[i].getIcon())).append(",");
					sb.append(Common.sqlChar(dt[i].getIconOpen()));
					sb.append(Common.get(dt[i].getOpened()).equals("Y")?",true":"");
					sb.append(");\n");
				}				
			}
			if ("".equals(Common.get(fixedLink)) && userRole!=null && userRole.getRoleId()>=3) {
				sb.append(treeID).append(".add(-1024," + sysId + ",'系統名稱、程式維護','dTreeFrame.jsp?progID=X');\n");
			}			
			return sb.toString();
		  } 
	      catch (Exception e) 
		  {
			e.printStackTrace();
		  }
		return "";
	}	
	
	public String buildAuthorizeMenu(String treeID, String treeName, String sysId, String groupID, String isAdmin) {
		CommonGroup g = new CommonGroup();
		g.setId(Integer.parseInt(groupID));
		
		CommonUserRole r = new CommonUserRole();
		r.setCommonGroup(g);
		
		if (isAdmin!=null && isAdmin.equals("Y")) r.setRoleId(3);			
		else r.setRoleId(1);

		return buildAuthorizeMenu(treeID, treeName, sysId, r);
	}
	
	java.util.Map<String,String> med0001DbNum = null;
	java.util.Map<String,String> med0001DbNum1 = null;
	java.util.Map<String,String> med0001DbNum2 = null;
	java.util.Map<String,String> med4001DbNum = null;
	
	java.util.Map<String,String> med2001DbNum = null;
	java.util.Map<String,String> med5001DbNum = null;
	
	java.util.Map<String,String> hfr4001DbNum = null;
	java.util.Map<String,String> hfr0001DbNum = null;
	
	java.util.Map<String,String> cos4001DbNum = null;
	java.util.Map<String,String> cos0001DbNum = null;
	java.util.Map<String,String> cos0001DbNum1 = null;
	java.util.Map<String,String> cos0001DbNum2 = null;
	
	java.util.Map<String,String> drg5001DbNum = null;
	java.util.Map<String,String> drg0001DbNum = null;
	java.util.Map<String,String> drg0001DbNum1 = null;
	java.util.Map<String,String> drg0001DbNum2 = null;
	java.util.Map<String,String> drg0001DbNum3 = null;
	java.util.Map<String,String> drg0001DbNum4 = null;
	
	java.util.Map<String, String> drgex0301 = null;
	java.util.Map<String, String> drgin0301YNum = null;
	java.util.Map<String, String> drgin0301NNum = null;
	java.util.Map<String, String> drgin03031Num = null;
	java.util.Map<String, String> drgin03032Num = null;
	java.util.Map<String, String> drgin0304Num = null;
	java.util.Map<String, String> drgin0308Num = null;
	
	java.util.Map<String, String> vdrg0201ONum = null;
	java.util.Map<String, String> vdrg0201INum = null;
	java.util.Map<String, String> vdrg0301Num = null;	
	
	java.util.Map<String, String> vcos0301Num = null;
	
	java.util.Map<String, String> drg8001DbNum1 = null;
	java.util.Map<String, String> drg8001DbNum2 = null;
	java.util.Map<String, String> drg8001DbNum3 = null;
	java.util.Map<String, String> drg8001DbNum4 = null;
	
	java.util.Map<String, String> pmed0201ONum = null;
	java.util.Map<String, String> pmed0201INum = null;
	java.util.Map<String, String> pmed0301Num1 = null;
	java.util.Map<String, String> pmed0301Num2 = null;
	
	
	java.util.Map<String, String> vmed7001DbNum = null;
	
	java.util.Map<String, String> vmed1201fNum = null;
	java.util.Map<String, String> vmed0901fNum = null;
	java.util.Map<String, String> vmedCompeyNoNum = null;

	
	public void  init() 	
	{		

		String shortCode="";//單位別
		String compey="";//廠商ID
		String userId="";//使用者ID
		String compeyNo="";//廠商統編
        CommonUser c = ServiceGetter.getInstance().getAuthenticationService().getCurrentUser();
		if(c != null)
		{
			shortCode=c.getCommonDepartment().getShortCode();
			userId=c.getUserId();
			//判斷是否為廠商
			if("02".equals(shortCode))
			{			  
				compey=View.getLookupField("select con1005Db.id from Con1006Db where commonUser.userId="+Common.sqlChar(c.getUserId()));
				compeyNo=View.getLookupField("select con1005Db.compegno from Con1006Db where commonUser.userId="+Common.sqlChar(c.getUserId()));
			}	
		}
		
		// 內部 > > 醫療器材不良事件通報
		med0001DbNum = TCBWCommon.getMap("select status,COUNT(*) from Med0001Db where eventKind <>''  group by status");
		med0001DbNum1 = TCBWCommon.getMap("select status,COUNT(*) from Med0001Db where eventKind='1' group by status");
		med0001DbNum2 = TCBWCommon.getMap("select status,COUNT(*) from Med0001Db where eventKind='2' group by status");	
		
		
		
		//外部 > > 案件通報 > > 醫療器材不良事件通報
		if("02".equals(shortCode))
		{	
	 	   med4001DbNum = TCBWCommon.getMap("select status,COUNT(*) from Med4001Db where eventKind <>'' and notifierDeptID <> ''  and notifierDeptID="+Common.sqlChar(compey)+" group by status");
		}
		else
		{
		   med4001DbNum = TCBWCommon.getMap("select status,COUNT(*) from Med4001Db where eventKind <>'' and inOrOutcreator="+Common.sqlChar(userId)+" group by status");
		}	
		
		
		// 內部 > > 醫療器材臨床試驗不良事件通報
		med2001DbNum = TCBWCommon.getMap("select status, COUNT(*) from Med2001Db group by status");	
		
		//外部 > > 案件通報 > > 醫療器材臨床試驗不良事件通報
		if("02".equals(shortCode))
		{	
		  med5001DbNum = TCBWCommon.getMap("select status,COUNT(*) from Med5001Db where  notifierDeptID <> '' and notifierDeptID="+Common.sqlChar(compey)+" group by status");
		}
		else
		{
		  med5001DbNum = TCBWCommon.getMap("select status,COUNT(*) from Med5001Db where inOrOutcreator="+Common.sqlChar(userId)+"  group by status");
		}	
		
		// 食品
		if("02".equals(shortCode)){
			hfr4001DbNum = TCBWCommon.getMap(" select status, COUNT(*) from Hfr4001Db " +
											 " where notifierDept = " + Common.sqlChar(compey) +
			//								 " and notifierDept <> '' " + 
											 " group by status ");
		}else{
			hfr4001DbNum = TCBWCommon.getMap(" select status, COUNT(*) from Hfr4001Db " +
					 						 " where (caseOwner = " + Common.sqlChar(userId) + " or creator = " + Common.sqlChar(userId) + ") " + 
											 " group by status ");
		}
		hfr0001DbNum = TCBWCommon.getMap(" select status, COUNT(*) from Hfr0001Db group by status ");
		
		// 化粧品
		if("02".equals(shortCode)){
			cos4001DbNum = TCBWCommon.getMap(" select status, COUNT(*) from Cos4001Db where notifierDept = " + Common.sqlChar(compey) + 
			//		                         " and notifierDept <> '' " + 
											 " group by status ");
		}else{
			cos4001DbNum = TCBWCommon.getMap(" select status, COUNT(*) from Cos4001Db where (caseOwner = " + Common.sqlChar(userId) + " or creator = " + Common.sqlChar(userId) + ")" +    
			 								 " group by status ");
		}
		cos0001DbNum = TCBWCommon.getMap(" select status, COUNT(*) from Cos0001Db group by status ");
		cos0001DbNum1 = TCBWCommon.getMap(" select status, COUNT(*) from Cos0001Db where cosType = '1' group by status ");
		cos0001DbNum2 = TCBWCommon.getMap(" select status, COUNT(*) from Cos0001Db where cosType = '2' group by status ");
		
		// 外部 > > 藥品不良品通報
		if("02".equals(shortCode))
		{	
		   drg5001DbNum = TCBWCommon.getMap("select status,COUNT(*) from Drg5001Db where notifierDeptID="+Common.sqlChar(compey)+" and notifierDeptID <> ''" +
                                            " and ( id in ( select max(id) from Drg5001Db where drg0001Id is not null  group by drg0001Id )"+
                                            "  or id in ( select id from Drg5001Db where (applNo is null or applNo = '') and drg0001Id is null ) ) group by status ");
		}
		else
		{
		   drg5001DbNum = TCBWCommon.getMap("select status,COUNT(*) from Drg5001Db where ( creator="+Common.sqlChar(userId)+" or notifierUserID="+Common.sqlChar(userId)+" ) " +
                                            " and ( id in ( select max(id) from Drg5001Db where drg0001Id is not null  group by drg0001Id )"+
                                            "  or id in ( select id from Drg5001Db where (applNo is null or applNo = '') and drg0001Id is null ) ) group by status ");
		}	
		
		// 內部 > > 藥品不良品通報
		drg0001DbNum = TCBWCommon.getMap("select status, COUNT(*) from Drg0001Db group by status");
		drg0001DbNum1 = TCBWCommon.getMap("select status, COUNT(*) from Drg0001Db where status in ('10','11') and isComplete='Y' group by status");
		drg0001DbNum2 = TCBWCommon.getMap("select status, COUNT(*) from Drg0001Db where status in ('10','11') and isComplete='N' group by status");
		drg0001DbNum3 = TCBWCommon.getMap("select status, COUNT(*) from Drg0001Db a where a.status='30' and a.applNo in ( select applNo from Drg0004Db where drgLev in ('01','02','03') ) group by status");
		drg0001DbNum4 = TCBWCommon.getMap("select status, COUNT(*) from Drg0001Db a where a.status='30' and a.applNo in ( select applNo from Drg0004Db where drgLev in ('04','05') ) group by status");
		
		//外部 >> 藥品療效不等通報
		if("02".equals(shortCode))
		{
		   drgex0301 = TCBWCommon.getMap(" select status,COUNT(*) from Drg6001Db where notifierDept="+Common.sqlChar(compey)+" and notifierDept <> '' " +
				                         " and ( id in ( select max(id) from Drg6001Db where drg4001Id != null  group by drg4001Id )"+
					                     "  or id in ( select id from Drg6001Db where (applNo is null or applNo = '') and drg4001Id is null ) ) group by status ");
		}
		else
		{
		   drgex0301 = TCBWCommon.getMap(" select status,COUNT(*) from Drg6001Db where ( creator="+Common.sqlChar(userId)+" or notifierUserID="+Common.sqlChar(userId)+" )" +
                                         " and ( id in ( select max(id) from Drg6001Db where drg4001Id != null  group by drg4001Id )"+
                                         "  or id in ( select id from Drg6001Db where (applNo is null or applNo = '') and drg4001Id is null ) ) group by status ");
		}	
		
		//內部 >> 藥品療效不等通報 >>案件審核
		drgin0301YNum = TCBWCommon.getMap(" select status, COUNT(*) from Drg4001Db where status in ('10','11') and isComplete = 'Y' group by status ");
		drgin0301NNum = TCBWCommon.getMap(" select status, COUNT(*) from Drg4001Db where status in ('10','11') and isComplete = 'N' group by status ");
		
		//內部 >> 藥品療效不等通報 >>案件分級
		drgin03031Num = TCBWCommon.getMap(" select status, COUNT(*) from Drg4001Db where status in ('20') group by status ");
		drgin03032Num = TCBWCommon.getMap(" select status, COUNT(*) from Drg4001Db where status in ('21','22','23') group by status ");
		
		//內部 >> 藥品療效不等通報 >>案件初評
		drgin0304Num = TCBWCommon.getMap(" select status, COUNT(*) from Drg4001Db where status in ('30') group by status ");
		
		//內部 >> 藥品療效不等通報 >>案件複評
		drgin0308Num = TCBWCommon.getMap(" select status, COUNT(*) from Drg4001Db where status in ('40','50') group by status ");
		
		//外部 >> 國內外藥品品質警訊  >>廠商回覆
		vdrg0201ONum = TCBWCommon.getMap(" select status, COUNT(*) from Drg7001Db where status = '10' and id in (select drg7001Db.id from Drg7003Db) " +
				" and id in (select drg7001Db.id from Drg7003Db where applicationId in (select con1005Db.compegno from Con1006Db where commonUser.userId="+Common.sqlChar(TCBWCommon.getCurrentUserId())+")) group by status ");
		
		//內部 >> 國內外藥品品質警訊  >>廠商回覆
		vdrg0201INum = TCBWCommon.getMap(" select status, COUNT(*) from Drg7001Db where status = '10' and id in (select drg7001Db.id from Drg7003Db) group by status ");
		
		//內部 >> 國內外藥品品質警訊  >>警訊評估
		vdrg0301Num = TCBWCommon.getMap(" select status, COUNT(*) from Drg7001Db where status = '10' group by status ");
		
		//內部 >>  國內外化妝品安全資訊監測訊息  >>警訊評估
		vcos0301Num = TCBWCommon.getMap(" select status, COUNT(*) from Cos7001Db where status = '10' group by status ");
		
		//重大品質事件廠商主動通報
		if("02".equals(shortCode))
		{			
			drg8001DbNum1 = TCBWCommon.getMap("select status,COUNT(*) from Drg8001Db where appID="+Common.sqlChar(compeyNo)+" group by status");
		}
		else
		{
			drg8001DbNum1 = TCBWCommon.getMap("select status,COUNT(*) from Drg8001Db where 1=1 group by status");
		}
		
		//內部 > > 重大品質事件廠商主動通報 > > 廠商回收 > > 回收中
		if("02".equals(shortCode))
		{
			drg8001DbNum2 = TCBWCommon.getMap("select status,COUNT(*) from Drg8001Db where ( status='20' or id in (select drg8001Db.id from Drg8004Db where status='40') ) and appID="+Common.sqlChar(compeyNo)+" group by status");
		}
		else
		{
			drg8001DbNum2 = TCBWCommon.getMap("select status,COUNT(*) from Drg8001Db where ( status='20' or id in (select drg8001Db.id from Drg8004Db where status='40') ) group by status");
		}
		//內部 > > 重大品質事件廠商主動通報 > > 案件評估
		drg8001DbNum3 = TCBWCommon.getMap("select status,COUNT(*) from Drg8001Db where id in (select drg8001Db.id from Drg8004Db where status in ('40','50')) group by status");
		//內部 > > 重大品質事件廠商主動通報 > > 案件分析
		drg8001DbNum4 = TCBWCommon.getMap("select status,COUNT(*) from Drg8001Db where id in (select drg8001Db.id from Drg8004Db where status='60') group by status");

		
		//外部 > > 醫療器材定期安全監視 > > 報告繳交(外部)
		pmed0201ONum = TCBWCommon.getMap("select handstatus,COUNT(*) from Med9002Db where med9001Db.id in (select id from Med9001Db where applicationID=" + Common.sqlChar(compeyNo)+") and handstatus = '01' and med9001Db.applNo <> '' group by handstatus");
		//內部 > > 醫療器材定期安全監視 > > 報告繳交
		pmed0201INum = TCBWCommon.getMap("select handstatus,COUNT(*) from Med9002Db where handstatus = '01' and med9001Db.applNo <> '' group by handstatus");
		//內部 > > 醫療器材定期安全監視 > > 報告評估 > > 評估中
		pmed0301Num1 = TCBWCommon.getMap("select handstatus,COUNT(*) from Med9002Db where handstatus = '02' group by handstatus");
		//內部 > > 醫療器材定期安全監視 > > 報告評估 > > 補件中
		pmed0301Num2 = TCBWCommon.getMap("select handstatus,COUNT(*) from Med9002Db where handstatus = '03' group by handstatus");
		
		
		//內部 > > 醫療器材國內外安全警訊監控 > > 醫療器材國內外安全警訊監控
		vmed1201fNum = TCBWCommon.getMap("select isrecycle,COUNT(*) from Med7001Db where isrecycle='Y' group by isrecycle");

		//內部 > > 醫療器材國內外安全警訊監控 > >
		vmed7001DbNum = TCBWCommon.getMap("select status, COUNT(*) from Med7001Db group by status");
		
        String hql = " select status,count(*) from Med7001Db where status in ('20','40') ";
		       hql += " and id in (select med7001Db.id from Med7002Db where applicationID= " + Common.sqlChar(compeyNo) + ")";
		
		vmedCompeyNoNum = TCBWCommon.getMap(hql+" group by status");

		vmed0901fNum = TCBWCommon.getMap("select med7001Db.status,COUNT(*) from Med7005Db  group by status");
	}
	
	public String dtreeNameNum(String code, String dtreeName)
	{
		String name = dtreeName;

		
		//外部 > > 案件通報 > > 醫療器材不良事件通報 > > 待上傳
		if("MEDEX0001".equals(code))
		{
            int num1 = 0;
			if(med4001DbNum.get("01") != null)
				num1 = Integer.parseInt(med4001DbNum.get("01"));

			name = dtreeName + "( "+ num1 + " )";
		}
		
		//外部 > > 案件通報 > > 醫療器材不良事件通報 > > 補件
		if("MEDEX0002".equals(code))
		{
            int num1 = 0;
			if(med4001DbNum.get("30") != null)
				num1 = Integer.parseInt(med4001DbNum.get("30"));

			int num2 = 0;
			if(med4001DbNum.get("02") != null)
				num2=Integer.parseInt(med4001DbNum.get("02"));
			
			name = dtreeName + "( "+ String.valueOf(num1+num2)  + " )";
		}
		
		//外部 > > 案件通報 > > 醫療器材不良事件通報 > > 暫存
		if("MEDEX0003".equals(code))
		{
            int num1 = 0;
			if(med4001DbNum.get("00") != null)
				num1 = Integer.parseInt(med4001DbNum.get("00"));
			name = dtreeName + "( "+ num1 + " )";
		}
	
		// 內部 > > 醫療器材不良事件通報 > > 不良反應案件審核 > > 審核中
		if("MED010001".equals(code))
		{
			int num1 = 0;
			if(med0001DbNum.get("10") != null)
				num1 = Integer.parseInt(med0001DbNum.get("10"));
			
			int num2 = 0;
			if(med0001DbNum.get("11") != null)
				num2=Integer.parseInt(med0001DbNum.get("11"));
			
			int num3 = 0;
			if(med0001DbNum2.get("10") != null)
				num3 = Integer.parseInt(med0001DbNum2.get("10"));
			
			int num4 = 0;
			if(med0001DbNum2.get("11") != null)
				num4=Integer.parseInt(med0001DbNum2.get("11"));

			name = dtreeName + "( "+String.valueOf(num1+num2-num3-num4) + " )";
		}
		
		// 內部 > > 醫療器材不良事件通報 > > 不良反應案件審核 > > 處理中
		if("MED010002".equals(code))
		{
            int num1 = 0;
			if(med0001DbNum1.get("20") != null)
				num1 = Integer.parseInt(med0001DbNum1.get("20"));

			name = dtreeName + "( "+ num1 + " )";
		}
		
		// 內部 > > 醫療器材不良事件通報 > > 不良反應案件審核 > > 補件中
		if("MED010003".equals(code))
		{
            int num1 = 0;
			if(med0001DbNum1.get("30") != null)
				num1 = Integer.parseInt(med0001DbNum1.get("30"));

			name = dtreeName + "( "+ num1 + " )";
		}
		

		// 內部 > > 醫療器材不良事件通報 > > 不良反應案件審核 > > 轉送評估中
		if("MED010004".equals(code))
		{
            int num1 = 0;
			if(med0001DbNum1.get("40") != null)
				num1 = Integer.parseInt(med0001DbNum1.get("40"));

			name = dtreeName + "( "+ num1 + " )";
		}
		
		// 內部 > > 醫療器材不良事件通報 > > 不良反應案件評估 > > 評估中
		if("MED010005".equals(code))
		{
            int num1 = 0;
			if(med0001DbNum1.get("70") != null)
				num1 = Integer.parseInt(med0001DbNum1.get("70"));

			name = dtreeName + "( "+ num1 + " )";
		}
		
		// 內部 > > 醫療器材不良事件通報 > > 不良反應案件評估 > > 評估確認中
		if("MED010006".equals(code))
		{
            int num1 = 0;
			if(med0001DbNum1.get("71") != null)
				num1 = Integer.parseInt(med0001DbNum1.get("71"));

			name = dtreeName + "( "+ num1 + " )";
		}
		
		// 內部 > > 醫療器材不良事件通報 > > 不良品案件審核 > > 審核中
		if("MED020001".equals(code))
		{
			int num1 = 0;
			if(med0001DbNum.get("10") != null)
				num1 = Integer.parseInt(med0001DbNum.get("10"));
			
			int num2 = 0;
			if(med0001DbNum.get("11") != null)
				num2=Integer.parseInt(med0001DbNum.get("11"));
			
			int num3 = 0;
			if(med0001DbNum1.get("10") != null)
				num3 = Integer.parseInt(med0001DbNum1.get("10"));
			
			int num4 = 0;
			if(med0001DbNum1.get("11") != null)
				num4=Integer.parseInt(med0001DbNum1.get("11"));
			
			name = dtreeName + "( "+String.valueOf(num1+num2-num3-num4) + " )";
		}
		
		// 內部 > > 醫療器材不良事件通報 > > 不良品案件審核 > > 處理中
		if("MED020002".equals(code))
		{
			int num1 = 0;
			if(med0001DbNum2.get("20") != null)
			  num1 = Integer.parseInt(med0001DbNum2.get("20"));

			name = dtreeName + "( "+ num1 + " )";
		}
		
		// 內部 > > 醫療器材不良事件通報 > > 不良品案件審核 > > 補件中
		if("MED020003".equals(code))
		{
			int num1 = 0;
			if(med0001DbNum2.get("30") != null)
			  num1 = Integer.parseInt(med0001DbNum2.get("30"));

			name = dtreeName + "( "+ num1 + " )";
		}
		
		// 內部 > > 醫療器材不良事件通報 > > 不良品案件審核 > > 轉送評估中(初評)
		if("MED020004".equals(code))
		{
			int num1 = 0;
			if(med0001DbNum2.get("40") != null)
			  num1 = Integer.parseInt(med0001DbNum2.get("40"));

			name = dtreeName + "( "+ num1 + " )";
		}
		
		// 內部 > > 醫療器材不良事件通報 > > 不良品案件審核 > > 廠商通知中
		if("MED020005".equals(code))
		{
			int num1 = 0;
			if(med0001DbNum2.get("50") != null)
			  num1 = Integer.parseInt(med0001DbNum2.get("50"));

			name = dtreeName + "( "+ num1 + " )";
		}
		
		// 內部 > > 醫療器材不良事件通報 > > 不良品案件審核 > > 廠商回覆中
		if("MED020006".equals(code))
		{
			int num1 = 0;
			if(med0001DbNum2.get("51") != null)
			  num1 = Integer.parseInt(med0001DbNum2.get("51"));

			name = dtreeName + "( "+ num1 + " )";
		}
		
		// 內部 > > 醫療器材不良事件通報 > > 不良品案件審核 > > 轉送評估中(複評)
		if("MED020007".equals(code))
		{
			int num1 = 0;
			if(med0001DbNum2.get("60") != null)
			  num1 = Integer.parseInt(med0001DbNum2.get("60"));

			name = dtreeName + "( "+ num1 + " )";
		}
		
		// 內部 > > 醫療器材不良事件通報 > > 不良品案件初評 > > 評估中
		if("MED020008".equals(code))
		{
			int num1 = 0;
			if(med0001DbNum2.get("70") != null)
			  num1 = Integer.parseInt(med0001DbNum2.get("70"));

			name = dtreeName + "( "+ num1 + " )";
		}
		
		// 內部 > > 醫療器材不良事件通報 > > 不良品案件初評 > > 評估確認中
		if("MED020009".equals(code))
		{
			int num1 = 0;
			if(med0001DbNum2.get("71") != null)
			  num1 = Integer.parseInt(med0001DbNum2.get("71"));

			name = dtreeName + "( "+ num1 + " )";
		}
		
		// 內部 > > 醫療器材不良事件通報 > > 不良品案件複評 > > 評估中
		if("MED020010".equals(code))
		{
			int num1 = 0;
			if(med0001DbNum2.get("75") != null)
			  num1 = Integer.parseInt(med0001DbNum2.get("75"));

			name = dtreeName + "( "+ num1 + " )";
		}
		
		// 內部 > > 醫療器材不良事件通報 > > 不良品案件複評 > > 評估確認中
		if("MED020011".equals(code))
		{
			int num1 = 0;
			if(med0001DbNum2.get("76") != null)
			  num1 = Integer.parseInt(med0001DbNum2.get("76"));

			name = dtreeName + "( "+ num1 + " )";
		}
		
		//外部 > > 案件通報 > > 醫療器材臨床試驗不良事件通報 > > 待上傳
		if("MEDEX0004".equals(code))
		{
            int num1 = 0;
			if(med5001DbNum.get("01") != null)
				num1 = Integer.parseInt(med5001DbNum.get("01"));

			name = dtreeName + "( "+ num1 + " )";
		}
		
		//外部 > > 案件通報 > > 醫療器材臨床試驗不良事件通報 > > 補件
		if("MEDEX0005".equals(code))
		{
            int num1 = 0;
			if(med5001DbNum.get("40") != null)
				num1 = Integer.parseInt(med5001DbNum.get("40"));

			int num2 = 0;
			if(med5001DbNum.get("02") != null)
				num2=Integer.parseInt(med5001DbNum.get("02"));

			name = dtreeName + "( "+ String.valueOf(num1+num2)  + " )";
		}
		
		//外部 > > 案件通報 > > 醫療器材臨床試驗不良事件通報 > > 暫存
		if("MEDEX0006".equals(code))
		{
            int num1 = 0;
			if(med5001DbNum.get("00") != null)
				num1 = Integer.parseInt(med5001DbNum.get("00"));

			name = dtreeName + "( "+ num1 + " )";
		}
		
		//內部 > > 醫療器材臨床試驗不良事件通報 > > 案件審核 > > 審核中
		if("MED030001".equals(code))
		{
			int num1 = 0;
			if(med2001DbNum.get("10") != null)
				num1 = Integer.parseInt(med2001DbNum.get("10"));
			
			int num2 = 0;
			if(med2001DbNum.get("20") != null)
				num2=Integer.parseInt(med2001DbNum.get("20"));
			
			name = dtreeName + "( "+String.valueOf(num1+num2) + " )";

		}
		
		//內部 > > 醫療器材臨床試驗不良事件通報 > > 案件審核 > > 處理中
		if("MED030002".equals(code))
		{
			int num1 = 0;
			if(med2001DbNum.get("30") != null)
			  num1 = Integer.parseInt(med2001DbNum.get("30"));

			name = dtreeName + "( "+ num1 + " )";
		}
		
		//內部 > > 醫療器材臨床試驗不良事件通報 > > 案件審核 > > 補件中
		if("MED030003".equals(code))
		{
			int num1 = 0;
			if(med2001DbNum.get("40") != null)
			  num1 = Integer.parseInt(med2001DbNum.get("40"));

			name = dtreeName + "( "+ num1 + " )";
		}
		
		// 外部 > > 食品分預期反應通報 > > 案件登錄> > 待上傳
		if("HFR010001".equals(code)){
			int num1 = 0;
			if(hfr4001DbNum.get("01") != null)
				num1 = Integer.parseInt(hfr4001DbNum.get("01"));
			name = dtreeName + "( "+ num1 + " )";
		}
		
		// 外部 > > 食品分預期反應通報 > > 案件登錄> > 暫存
		if("HFR010001O".equals(code)){
			int num1 = 0;
			if(hfr4001DbNum.get("00") != null)
				num1 = Integer.parseInt(hfr4001DbNum.get("00"));
			name = dtreeName + "( "+ num1 + " )";
		}
		
		// 內部 > > 食品分預期反應通報 > > 審核作業
		if("HFR010002".equals(code)){
			int num1 = 0;
			if(hfr0001DbNum.get("10") != null)
				num1 = Integer.parseInt(hfr0001DbNum.get("10"));
			name = dtreeName + "( "+ num1 + " )";
		}
		
		// 內部 > > 食品分預期反應通報 > > 初評作業
		if("HFR010003".equals(code)){
			int num1 = 0;
			if(hfr0001DbNum.get("20") != null)
				num1 = Integer.parseInt(hfr0001DbNum.get("20"));
			name = dtreeName + "( "+ num1 + " )";
		}
		
		// 內部 > > 食品分預期反應通報 > > 預評作業
		if("HFR010004".equals(code)){
			int num1 = 0;
			if(hfr0001DbNum.get("30") != null)
				num1 = Integer.parseInt(hfr0001DbNum.get("30"));
			name = dtreeName + "( "+ num1 + " )";
		}
		
		// 內部 > > 食品分預期反應通報 > > 複評作業
		if("HFR010005".equals(code)){
			int num1 = 0;
			if(hfr0001DbNum.get("40") != null)
				num1 = Integer.parseInt(hfr0001DbNum.get("40"));
			name = dtreeName + "( "+ num1 + " )";
		}
		
		// 待上傳
		if("COS010001".equals(code)){
			int num1 = 0;
			if(cos4001DbNum.get("01") != null)
				num1 = Integer.parseInt(cos4001DbNum.get("01"));
			name = dtreeName + "( "+ num1 + " )";
		}
		
		// 補件中
		if("COS010002".equals(code)){
			int num1 = 0;
			if(cos4001DbNum.get("30") != null)
				num1 = Integer.parseInt(cos4001DbNum.get("30"));
			name = dtreeName + "( "+ num1 + " )";
		}
		
		// 暫存
		if("COS010001O".equals(code)){
			int num1 = 0;
			if(cos4001DbNum.get("00") != null)
				num1 = Integer.parseInt(cos4001DbNum.get("00"));
			name = dtreeName + "( "+ num1 + " )";
		}
		
		// 審核
		if("COS010003".equals(code)){
			int num1 = 0;
			if(cos0001DbNum.get("10") != null)
				num1 = Integer.parseInt(cos0001DbNum.get("10"));
			name = dtreeName + "( "+ num1 + " )";
		}
		
		// 不良品-分類中
		if("COS020004".equals(code)){
			int num1 = 0;
			if(cos0001DbNum1.get("20") != null)
				num1 = Integer.parseInt(cos0001DbNum1.get("20"));
			name = dtreeName + "( "+ num1 + " )";
		}
		
		// 不良品-補件中
		if("COS020005".equals(code)){
			int num1 = 0;
			if(cos0001DbNum1.get("30") != null)
				num1 = Integer.parseInt(cos0001DbNum1.get("30"));
			name = dtreeName + "( "+ num1 + " )";
		}
		
		// 不良品-評估中
		if("COS020006".equals(code)){
			int num1 = 0;
			if(cos0001DbNum1.get("60") != null)
				num1 = Integer.parseInt(cos0001DbNum1.get("60"));
			name = dtreeName + "( "+ num1 + " )";
		}
		
		// 不良品-處理中
		if("COS020007".equals(code)){
			int num1 = 0;
			if(cos0001DbNum1.get("40") != null)
				num1 = Integer.parseInt(cos0001DbNum1.get("40"));
			name = dtreeName + "( "+ num1 + " )";
		}
		
		// 不良品-追蹤中
		if("COS020008".equals(code)){
			int num1 = 0;
			if(cos0001DbNum1.get("50") != null)
				num1 = Integer.parseInt(cos0001DbNum1.get("50"));
			name = dtreeName + "( "+ num1 + " )";
		}
		
		// 不良品-結案中
		if("COS020009".equals(code)){
			int num1 = 0;
			if(cos0001DbNum1.get("70") != null)
				num1 = Integer.parseInt(cos0001DbNum1.get("70"));
			name = dtreeName + "( "+ num1 + " )";
		}
		
		// 不良反應-分類中
		if("COS030004".equals(code)){
			int num1 = 0;
			if(cos0001DbNum2.get("20") != null)
				num1 = Integer.parseInt(cos0001DbNum2.get("20"));
			name = dtreeName + "( "+ num1 + " )";
		}
		
		// 不良反應-補件中
		if("COS030005".equals(code)){
			int num1 = 0;
			if(cos0001DbNum2.get("30") != null)
				num1 = Integer.parseInt(cos0001DbNum2.get("30"));
			name = dtreeName + "( "+ num1 + " )";
		}
		
		// 不良反應-處理中
		if("COS030006".equals(code)){
			int num1 = 0;
			if(cos0001DbNum2.get("40") != null)
				num1 = Integer.parseInt(cos0001DbNum2.get("40"));
			name = dtreeName + "( "+ num1 + " )";
		}
		
		// 不良反應-追蹤中
		if("COS030007".equals(code)){
			int num1 = 0;
			if(cos0001DbNum2.get("50") != null)
				num1 = Integer.parseInt(cos0001DbNum2.get("50"));
			name = dtreeName + "( "+ num1 + " )";
		}
		
		// 不良反應-結案中
		if("COS030008".equals(code)){
			int num1 = 0;
			if(cos0001DbNum2.get("70") != null)
				num1 = Integer.parseInt(cos0001DbNum2.get("70"));
			name = dtreeName + "( "+ num1 + " )";
		}
		// 外部 > > 藥品不良品通報 > > 待上傳
		if("DRG010001O".equals(code)){
			int num1=0;
			if(drg5001DbNum.get("01") != null)
				num1 = Integer.parseInt(drg5001DbNum.get("01"));
			name = dtreeName + "( "+String.valueOf(num1) + " )";
		}
		
		// 外部 > > 藥品不良品通報 > > 待補件
		if("DRG010002O".equals(code)){
			int num1=0,num2=0,num3=0;
			if(drg5001DbNum.get("02") != null)
				num1 = Integer.parseInt(drg5001DbNum.get("02"));
			if(drg5001DbNum.get("22") != null)
				num2 = Integer.parseInt(drg5001DbNum.get("22"));
			if(drg5001DbNum.get("23") != null)
				num3 = Integer.parseInt(drg5001DbNum.get("23"));
			name = dtreeName + "( "+String.valueOf(num1+num2+num3) + " )";
		}
		
		// 外部 > > 藥品不良品通報 > > 暫存
		if("DRG010003O".equals(code)){
			int num1=0;
			if(drg5001DbNum.get("00") != null)
				num1 = Integer.parseInt(drg5001DbNum.get("00"));
			name = dtreeName + "( "+String.valueOf(num1) + " )";
		}
		
		// 內部 > > 藥品不良品通報 > > 案件審核 > > 完整案件
		if("DRG010001".equals(code)){
			int num1=0,num2 = 0;
			if(drg0001DbNum1.get("10") != null)
				num1 = Integer.parseInt(drg0001DbNum1.get("10"));
			if(drg0001DbNum1.get("11") != null)
				num2 = Integer.parseInt(drg0001DbNum1.get("11"));
			name = dtreeName + "( "+String.valueOf(num1+num2) + " )";
		}
		
		// 內部 > > 藥品不良品通報 > > 案件審核 > > 不完整案件
		if("DRG010002".equals(code)){
			int num1=0,num2 = 0;
			if(drg0001DbNum2.get("10") != null)
				num1 = Integer.parseInt(drg0001DbNum2.get("10"));
			if(drg0001DbNum2.get("11") != null)
				num2 = Integer.parseInt(drg0001DbNum2.get("11"));
			name = dtreeName + "( "+String.valueOf(num1+num2) + " )";
		}
		
		//  內部 > > 藥品不良品通報 > > 案件分級 > > 一般案件
		if("DRG010003".equals(code)){
			int num1=0;
			if(drg0001DbNum.get("20") != null)
				num1 = Integer.parseInt(drg0001DbNum.get("20"));
			name = dtreeName + "( "+num1+ " )";
		}
		
		//  內部 > > 藥品不良品通報 > > 案件分級 > > 補件案件
		if("DRG010004".equals(code)){
			int num1=0,num2=0,num3=0,num4=0;
			if(drg0001DbNum.get("21") != null)
				num1 = Integer.parseInt(drg0001DbNum.get("21"));
			if(drg0001DbNum.get("22") != null)
				num2 = Integer.parseInt(drg0001DbNum.get("22"));
			if(drg0001DbNum.get("23") != null)
				num3 = Integer.parseInt(drg0001DbNum.get("23"));
			if(drg0001DbNum.get("24") != null)
				num4 = Integer.parseInt(drg0001DbNum.get("24"));
			name = dtreeName + "( "+String.valueOf(num1+num2+num3+num4)+ " )";
		}
		
		//  內部 > > 藥品不良品通報 > > 案件分級 > > 待發文
		if("DRG010005".equals(code)){
			int num1=0;
			if(drg0001DbNum.get("25") != null)
				num1 = Integer.parseInt(drg0001DbNum.get("25"));
			name = dtreeName + "( "+num1+" )";
		}
		
		//  內部 > > 藥品不良品通報 > > 分級確認 > > A級、A+級
		if("DRG010006".equals(code)){
			int num1=0;
			if(drg0001DbNum3.get("30") != null)
				num1 = Integer.parseInt(drg0001DbNum3.get("30"));
			name = dtreeName + "( "+num1+" )";
		}
		
		//  內部 > > 藥品不良品通報 > > 分級確認 > > B級、C級
		if("DRG010007".equals(code)){
			int num1=0;
			if(drg0001DbNum4.get("30") != null)
				num1 = Integer.parseInt(drg0001DbNum4.get("30"));
			name = dtreeName + "( "+num1+" )";
		}
		
		//  內部 > > 藥品不良品通報 > > 案件評估 > > 待發文
		if("DRG010008".equals(code)){
			int num1=0;
			if(drg0001DbNum.get("40") != null)
				num1 = Integer.parseInt(drg0001DbNum.get("40"));
			name = dtreeName + "( "+num1+" )";
		}
		
		//  內部 > > 藥品不良品通報 > > 案件評估 > > 交付中
		if("DRG010009".equals(code)){
			int num1=0;
			if(drg0001DbNum.get("41") != null)
				num1 = Integer.parseInt(drg0001DbNum.get("41"));
			name = dtreeName + "( "+num1+" )";
		}
		
		//  內部 > > 藥品不良品通報 > > 案件評估 > > 交付中
		if("DRG010010".equals(code)){
			int num1=0;
			if(drg0001DbNum.get("42") != null)
				num1 = Integer.parseInt(drg0001DbNum.get("42"));
			name = dtreeName + "( "+num1+" )";
		}
		
		//  內部 > > 藥品不良品通報 > > 案件分析 
		if("DRG010011".equals(code)){
			int num1=0;
			if(drg0001DbNum.get("50") != null)
				num1 = Integer.parseInt(drg0001DbNum.get("50"));
			name = dtreeName + "( "+num1+" )";
		}
		
	    //  內部 > > 藥品不良品通報 > > 案件評估 > > 再評估
		if("DRG010012".equals(code)){
			int num1=0;
			if(drg0001DbNum.get("43") != null)
				num1 = Integer.parseInt(drg0001DbNum.get("43"));
			name = dtreeName + "( "+num1+" )";
		}
		
		// 外部 > > 藥品療效不等通報 > > 待上傳
		if("DRG020001O".equals(code)){
			int num1=0;
			if(drgex0301.get("01") != null)
				num1 = Integer.parseInt(drgex0301.get("01"));
			name = dtreeName + "( "+String.valueOf(num1) + " )";
		}
		
		// 外部 > > 藥品療效不等通報 > > 待補件
		if("DRG020002O".equals(code)){
			int num1=0,num2=0,num3=0;
			if(drgex0301.get("02") != null)
				num1 = Integer.parseInt(drgex0301.get("02"));
			if(drgex0301.get("21") != null)
				num2 = Integer.parseInt(drgex0301.get("21"));
			if(drgex0301.get("22") != null)
				num3 = Integer.parseInt(drgex0301.get("22"));
			name = dtreeName + "( "+String.valueOf(num1+num2+num3) + " )";
		}
		
		// 外部 > > 藥品療效不等通報 > > 待上傳
		if("DRG020003O".equals(code)){
			int num1=0;
			if(drgex0301.get("00") != null)
				num1 = Integer.parseInt(drgex0301.get("00"));
			name = dtreeName + "( "+String.valueOf(num1) + " )";
		}
		
		// 內部 > > 藥品療效不等通報 > > 案件審核 > > 完整案件
		if("DRG020001".equals(code)){
			int num1=0,num2 = 0;
			if(drgin0301YNum.get("10") != null)
				num1 = Integer.parseInt(drgin0301YNum.get("10"));
			if(drgin0301YNum.get("11") != null)
				num2 = Integer.parseInt(drgin0301YNum.get("11"));
			name = dtreeName + "( "+String.valueOf(num1+num2) + " )";
		}
		
		// 內部 > > 藥品療效不等通報 > > 案件審核 > > 不完整案件
		if("DRG020002".equals(code)){
			int num1=0,num2 = 0;
			if(drgin0301NNum.get("10") != null)
				num1 = Integer.parseInt(drgin0301NNum.get("10"));
			if(drgin0301NNum.get("11") != null)
				num2 = Integer.parseInt(drgin0301NNum.get("11"));
			name = dtreeName + "( "+String.valueOf(num1+num2) + " )";
		}
		
		// 內部 > > 藥品療效不等通報 > > 案件分級 > > 一般案件
		if("DRG020003".equals(code)){
			int num1=0;
			if(drgin03031Num.get("20") != null)
				num1 = Integer.parseInt(drgin03031Num.get("20"));
			name = dtreeName + "( "+String.valueOf(num1) + " )";
		}
		
		// 內部 > > 藥品療效不等通報 > > 案件分級 > > 補件案件
		if("DRG020004".equals(code)){
			int num1=0,num2 = 0,num3 = 0;
			if(drgin03032Num.get("21") != null)
				num3 = Integer.parseInt(drgin03032Num.get("21"));
			if(drgin03032Num.get("22") != null)
				num1 = Integer.parseInt(drgin03032Num.get("22"));
			if(drgin03032Num.get("23") != null)
				num2 = Integer.parseInt(drgin03032Num.get("23"));
			name = dtreeName + "( "+String.valueOf(num1+num2+num3) + " )";
		}
		
		//內部 >> 藥品療效不等通報 >>案件初評
		if("DRG020005".equals(code)){
			int num1=0;
			if(drgin0304Num.get("30") != null)
				num1 = Integer.parseInt(drgin0304Num.get("30"));
			name = dtreeName + "( "+String.valueOf(num1) + " )";
		}

		// 內部 > > 藥品療效不等通報 > > 案件複評
		if("DRG020006".equals(code)){
			int num1=0,num2 = 0;
			if(drgin0308Num.get("40") != null)
				num1 = Integer.parseInt(drgin0308Num.get("40"));
			if(drgin0308Num.get("50") != null)
				num2 = Integer.parseInt(drgin0308Num.get("50"));
			name = dtreeName + "( "+String.valueOf(num1+num2) + " )";
		}
		
		// 內部 > > 藥品療效不等通報 > > 案件複評
		if("DRG020006".equals(code)){
			int num1=0,num2 = 0;
			if(drgin0308Num.get("40") != null)
				num1 = Integer.parseInt(drgin0308Num.get("40"));
			if(drgin0308Num.get("50") != null)
				num2 = Integer.parseInt(drgin0308Num.get("50"));
			name = dtreeName + "( "+String.valueOf(num1+num2) + " )";
		}
		
		// 外部 > > 國內外藥品品質警訊 > > 廠商回覆
		if("DRG030001".equals(code)){
			int num1=0;
			if(vdrg0201ONum.get("10") != null)
				num1 = Integer.parseInt(vdrg0201ONum.get("10"));
			name = dtreeName + "( "+String.valueOf(num1) + " )";
		}
		
		// 內部 > > 國內外藥品品質警訊 > > 廠商回覆
		if("DRG030002".equals(code)){
			int num1=0;
			if(vdrg0201INum.get("10") != null)
				num1 = Integer.parseInt(vdrg0201INum.get("10"));
			name = dtreeName + "( "+String.valueOf(num1) + " )";
		}
		
		// 內部 > > 國內外藥品品質警訊 > > 警訊評估
		if("DRG030003".equals(code)){
			int num1=0;
			if(vdrg0301Num.get("10") != null)
				num1 = Integer.parseInt(vdrg0301Num.get("10"));
			name = dtreeName + "( "+String.valueOf(num1) + " )";
		}
		
		// 內部 > > 國內外化妝品安全資訊監測訊息 > > 警訊評估
		if("COS040003".equals(code)){
			int num1=0;
			if(vcos0301Num.get("10") != null)
				num1 = Integer.parseInt(vcos0301Num.get("10"));
			name = dtreeName + "( "+String.valueOf(num1) + " )";
		}
		
		// 內部 > > 重大品質事件廠商主動通報 > > 案件審核
		if("SDRG010001".equals(code)){
			int num1=0;
			if(drg8001DbNum1.get("10") != null)
				num1 = Integer.parseInt(drg8001DbNum1.get("10"));
			name = dtreeName + "( "+String.valueOf(num1) + " )";
		}
		// 內部 > > 重大品質事件廠商主動通報 > > 廠商回收 > > 回收中
		if("SDRG010002".equals(code)){
			int num1=0,num2=0,num3=0,num4=0,num5=0;
			if(drg8001DbNum2.get("20") != null)
				num1 = Integer.parseInt(drg8001DbNum2.get("20"));
			if(drg8001DbNum2.get("21") != null)
				num2 = Integer.parseInt(drg8001DbNum2.get("21"));
			if(drg8001DbNum2.get("30") != null)
				num3 = Integer.parseInt(drg8001DbNum2.get("30"));
			if(drg8001DbNum2.get("31") != null)
				num4 = Integer.parseInt(drg8001DbNum2.get("31"));
			if(drg8001DbNum2.get("40") != null)
				num5 = Integer.parseInt(drg8001DbNum2.get("40"));
			name = dtreeName + "( "+String.valueOf(num1+num2+num3+num4+num5) + " )";
		}
		// 內部 > > 重大品質事件廠商主動通報 > > 廠商回收 > > 審核中
		if("SDRG010003".equals(code)){
			int num1=0;
			if(drg8001DbNum1.get("21") != null)
				num1 = Integer.parseInt(drg8001DbNum1.get("21"));
			name = dtreeName + "( "+String.valueOf(num1) + " )";
		}
		// 外部 > > 重大品質事件廠商主動通報 > > 回收確認 > > 回收確認中(廠商)
		if("SDRG010004".equals(code)){
			int num1=0;
			if(drg8001DbNum1.get("30") != null)
				num1 = Integer.parseInt(drg8001DbNum1.get("30"));
			name = dtreeName + "( "+String.valueOf(num1) + " )";
		}
		// 外部 > > 重大品質事件廠商主動通報 > > 回收確認 > > 回收確認中(衛生局)
		if("SDRG010005".equals(code)){
			int num1=0;
			if(drg8001DbNum1.get("30") != null)
				num1 = Integer.parseInt(drg8001DbNum1.get("30"));
			name = dtreeName + "( "+String.valueOf(num1) + " )";
		}
		// 內部 > > 重大品質事件廠商主動通報 > > 回收確認 > > 審核中
		if("SDRG010006".equals(code)){
			int num1=0;
			if(drg8001DbNum1.get("31") != null)
				num1 = Integer.parseInt(drg8001DbNum1.get("31"));
			name = dtreeName + "( "+String.valueOf(num1) + " )";
		}
		// 內部 > > 重大品質事件廠商主動通報 > > 案件評估
		if("SDRG010007".equals(code)){
			int num1=0,num2=0,num3=0,num4=0,num5=0,num6=0;
			if(drg8001DbNum3.get("20") != null)
				num1 = Integer.parseInt(drg8001DbNum3.get("20"));
			if(drg8001DbNum3.get("21") != null)
				num2 = Integer.parseInt(drg8001DbNum3.get("21"));
			if(drg8001DbNum3.get("30") != null)
				num3 = Integer.parseInt(drg8001DbNum3.get("30"));
			if(drg8001DbNum3.get("31") != null)
				num4 = Integer.parseInt(drg8001DbNum3.get("31"));
			if(drg8001DbNum3.get("40") != null)
				num5 = Integer.parseInt(drg8001DbNum3.get("40"));
			if(drg8001DbNum3.get("50") != null)
				num6 = Integer.parseInt(drg8001DbNum3.get("50"));
			name = dtreeName + "( "+String.valueOf(num1+num2+num3+num4+num5+num6) + " )";
		}
		// 內部 > > 重大品質事件廠商主動通報 > > 案件分析		
		if("SDRG010008".equals(code)){
			int num1=0,num2=0,num3=0,num4=0,num5=0;
			if(drg8001DbNum4.get("20") != null)
				num1 = Integer.parseInt(drg8001DbNum4.get("20"));
			if(drg8001DbNum4.get("21") != null)
				num2 = Integer.parseInt(drg8001DbNum4.get("21"));
			if(drg8001DbNum4.get("30") != null)
				num3 = Integer.parseInt(drg8001DbNum4.get("30"));
			if(drg8001DbNum4.get("31") != null)
				num4 = Integer.parseInt(drg8001DbNum4.get("31"));
			if(drg8001DbNum4.get("60") != null)
				num5 = Integer.parseInt(drg8001DbNum4.get("60"));
			name = dtreeName + "( "+String.valueOf(num1+num2+num3+num4+num5) + " )";
		}
		
		// 外部 > > 醫療器材定期安全監視 > > 報告繳交(外部)
		if("PMED010001".equals(code)){
			int num1=0;
			if(pmed0201ONum.get("01") != null)
				num1 = Integer.parseInt(pmed0201ONum.get("01"));
			name = dtreeName + "( "+String.valueOf(num1) + " )";
		}
		
		// 內部 > > 醫療器材定期安全監視 > > 報告繳交
		if("PMED010002".equals(code)){
			int num1=0;
			if(pmed0201INum.get("01") != null)
				num1 = Integer.parseInt(pmed0201INum.get("01"));
			name = dtreeName + "( "+String.valueOf(num1) + " )";
		}
		
		// 內部 > > 醫療器材定期安全監視 > > 報告評估 > > 評估中
		if("PMED010003".equals(code)){
			int num1=0;
			if(pmed0301Num1.get("02") != null)
				num1 = Integer.parseInt(pmed0301Num1.get("02"));
			name = dtreeName + "( "+String.valueOf(num1) + " )";
		}
		
		// 內部 > > 醫療器材定期安全監視 > > 報告評估 > > 補件中
		if("PMED010004".equals(code)){
			int num1=0;
			if(pmed0301Num2.get("03") != null)
				num1 = Integer.parseInt(pmed0301Num2.get("03"));
			name = dtreeName + "( "+String.valueOf(num1) + " )";
		}
		
		
		// 內部 > >  醫療器材國內外安全警訊監控 > > 廠商確認
		if("MED060001".equals(code))
		{
			int num1=0;
			if(vmed7001DbNum.get("10") != null)
				num1 = Integer.parseInt(vmed7001DbNum.get("10"));
			name = dtreeName + "( "+String.valueOf(num1) + " )";
		}
		
		
		//內部 > > 醫療器材國內外安全警訊監控 > > 廠商回覆(外部)
		if("MED060002".equals(code))
		{
			int num1=0;
			if(vmedCompeyNoNum.get("20")!=null)
				num1 = Integer.parseInt(vmedCompeyNoNum.get("20"));
			
			name = dtreeName + "( "+String.valueOf(num1) + " )";
		}
		
		
		//內部 > > 醫療器材國內外安全警訊監控 > > 廠商回覆清單
		if("MED060003".equals(code))
		{
			int num1=0;
			if(vmed7001DbNum.get("20") != null)
				num1 = Integer.parseInt(vmed7001DbNum.get("20"));
			name = dtreeName + "( "+String.valueOf(num1) + " )";
		}
		
		//內部 > > 醫療器材國內外安全警訊監控 > > 警訊摘譯 > > 摘譯中
		if("MED060004".equals(code))
		{
			int num1=0;
			if(vmed7001DbNum.get("30") != null)
				num1 = Integer.parseInt(vmed7001DbNum.get("30"));
			name = dtreeName + "( "+String.valueOf(num1) + " )";
		}
		
		//內部 > > 醫療器材國內外安全警訊監控 > > 警訊摘譯 > > 複審中
		if("MED060005".equals(code))
		{
			int num1=0;
			if(vmed7001DbNum.get("31") != null)
				num1 = Integer.parseInt(vmed7001DbNum.get("31"));
			name = dtreeName + "( "+String.valueOf(num1) + " )";
		}
		
		//內部 > > 醫療器材國內外安全警訊監控 > > 警訊摘譯 > > 摘譯確認中(外部)
		if("MED060006".equals(code))
		{
			int num1=0;
			if(vmedCompeyNoNum.get("40")!=null)
				num1 = Integer.parseInt(vmedCompeyNoNum.get("40"));
			name = dtreeName + "( "+String.valueOf(num1) + " )";
		}
		
		//內部 > > 醫療器材國內外安全警訊監控 > > 警訊摘譯 > > 摘譯確認中
		if("MED060007".equals(code))
		{
			int num1=0;
			if(vmed7001DbNum.get("40") != null)
				num1 = Integer.parseInt(vmed7001DbNum.get("40"));
			name = dtreeName + "( "+String.valueOf(num1) + " )";
		}
		
		//內部 > > 醫療器材國內外安全警訊監控 > > 警訊公告
		if("MED060008".equals(code))
		{
			int num1=0;
			if(vmed0901fNum.get("50") != null)
				num1 = Integer.parseInt(vmed0901fNum.get("50"));
			name = dtreeName + "( "+String.valueOf(num1) + " )";
		}
		
		//內部 > > 醫療器材國內外安全警訊監控 > > 警訊公告改版 > > 改版中
		if("MED060009".equals(code))
		{
			int num1=0;
			if(vmed0901fNum.get("60") != null)
				num1 = Integer.parseInt(vmed0901fNum.get("60"));
			name = dtreeName + "( "+String.valueOf(num1) + " )";
		}
		
		//內部 > > 醫療器材國內外安全警訊監控 > > 警訊公告改版 > > 複審中
		if("MED060010".equals(code))
		{
			int num1=0;
			if(vmed0901fNum.get("61") != null)
				num1 = Integer.parseInt(vmed0901fNum.get("61"));
			name = dtreeName + "( "+String.valueOf(num1) + " )";
		}
		
		//內部 > > 醫療器材國內外安全警訊監控 > > 醫療器材回收
		if("MED060011".equals(code))
		{
			int num1=0;
			if(vmed1201fNum.get("Y") != null)
				num1 = Integer.parseInt(vmed1201fNum.get("Y"));
			name = dtreeName + "( "+String.valueOf(num1) + " )";
		}
		
		
		
		return name;
	}
	    

}
