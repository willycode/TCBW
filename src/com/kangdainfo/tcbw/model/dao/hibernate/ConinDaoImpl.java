package com.kangdainfo.tcbw.model.dao.hibernate;

import java.io.File;

import javax.servlet.ServletContext;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.model.bo.CommonGroup;
import com.kangdainfo.common.model.bo.CommonUser;
import com.kangdainfo.common.model.bo.CommonUserGroup;
import com.kangdainfo.common.model.bo.CommonUserRole;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.Datetime;
import com.kangdainfo.common.util.View;
import com.kangdainfo.persistence.hibernate3.BaseDaoImpl;
import com.kangdainfo.tcbw.model.bo.Con0001Db;
import com.kangdainfo.tcbw.model.bo.Con2001Db;
import com.kangdainfo.tcbw.model.dao.ConinDao;

public class ConinDaoImpl extends BaseDaoImpl implements ConinDao{

	@Override
	public void updateConinCommonUser(CommonUser obj, int[] groupIds) throws Exception {
		if (obj!=null && obj.getId()!=null) {
			update(obj);
			
			boolean isUpdate = true;
			CommonUserRole role = (CommonUserRole) View.getObject(" from CommonUserRole where id = " + Common.getInt(obj.getId()));
			if (role == null) {
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
				String hql = " from CommonUserGroup where commonUser.id = " + Common.getInt(obj.getId());
				java.util.List list = load(hql);				
				if (list!=null && list.size()>0) {
					for (int i=0; i<list.size(); i++) {
						CommonUserGroup g = (CommonUserGroup) list.get(i);
						if(g.getCommonGroup() != null){
							h.put(g.getCommonGroup().getId(), "Y");
						}
					}
				}
				
				StringBuilder sb = new StringBuilder().append("-99");
				for(int i=0; i<groupIds.length; i++){
					sb.append(",").append(groupIds[i]);				
					
					if (!h.containsKey(groupIds[i])) {
						h.put(groupIds[i], "Y");
						
						CommonUserGroup userGroup = new CommonUserGroup();
						userGroup.setCommonUser(obj);
						
						CommonGroup group = (CommonGroup)View.getObject(" from CommonGroup where id = " + groupIds[i]);
						if(group == null){
							group = new CommonGroup(); 
							group.setId(groupIds[i]);
						}
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
				String hql = " from CommonUserGroup where commonUser.id = " + obj.getId();
				java.util.List list = this.load(hql);	
				if (list!=null && list.size()>0) this.deleteBatch(list);
			}
		}
		
	}
	
	public void saveCon2001Db(String systemType, Long formID, 
			String applNo,String state, String procDesc, String userId) 
	{
		
		String yyyMMdd = Datetime.getYYYMMDD();
		String hhMMss = Datetime.getHHMMSS();	
		
		Con2001Db obj = new Con2001Db();

		obj.setApplNo(applNo);
		obj.setSystemType(systemType);
		obj.setFormID(formID);
		obj.setProcessDate(yyyMMdd);
		obj.setProcessTime(hhMMss);
		obj.setState(state);	
        obj.setProcessDesc(procDesc);
        obj.setUserID(userId);
		obj.setCreator(userId);
		obj.setCreateDate(yyyMMdd);
		obj.setCreateTime(hhMMss);		
		
		save(obj);
		
	}
	
	public void deleteCon0001Db(Long id) {
		Con0001Db obj = (Con0001Db)View.getObject(" from Con0001Db where id="+Common.getLong(id));
		if(null != obj){
			String hql =" from Con0001Db where id <>"+Common.getLong(id)+" and fileRoute="+Common.sqlChar(obj.getFileRoute())+" and fileName="+Common.sqlChar(obj.getFileName()); 
			int count = ServiceGetter.getInstance().getCommonService().loadCount(hql);

			//此檔案沒有其他使用的話 就進行刪除
			if (count == 0) {
				String[] arrFileName=obj.getFileRoute().split(":;:");
				if (arrFileName.length>1) {
	        		arrFileName[0] = Common.isValidChildFilePath(arrFileName[0]);
	        		arrFileName[1] = Common.isValidChildFilePath(arrFileName[1]);
			        ServletContext context = ServiceGetter.getInstance().getMyServletContext().getServletContext();
					File filestoreLocation = new File(context.getInitParameter(obj.getFileKind()));					
					Common.RemoveDirectory(new File(filestoreLocation+File.separator+arrFileName[0]+File.separator+arrFileName[1]+File.separator+obj.getFileName()));	
					
					boolean isDelete = true;
					File dir = new File(filestoreLocation+File.separator+arrFileName[0]+File.separator+arrFileName[1]);
					if(dir.isDirectory()){			            
        				String[] children = dir.list();
                        if(children.length > 0){
                        	isDelete = false;
                        }
			        }
					if(isDelete){
						Common.RemoveDirectory(dir); //資料夾為空一併刪除
					}

				}
			}				
			delete(obj);
		}
		
	}
	
}
