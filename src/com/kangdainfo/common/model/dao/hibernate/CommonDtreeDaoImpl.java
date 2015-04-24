package com.kangdainfo.common.model.dao.hibernate;

import com.kangdainfo.common.model.bo.CommonDtree;
import com.kangdainfo.common.model.dao.CommonDtreeDao;
import com.kangdainfo.persistence.hibernate3.*;

public class CommonDtreeDaoImpl extends BaseDaoImpl implements CommonDtreeDao {
	
	public void initial() throws Exception {}
	public void close() throws Exception {}	

	public void saveDtreeNode(CommonDtree obj) throws Exception {
		getHibernateTemplate().save(obj);		
	}

	public void updateDtreeNode(CommonDtree obj) throws Exception {
		getHibernateTemplate().update(obj);		
	}
	
	public void deleteDtreeNode(Long id) throws Exception {
		CommonDtree[] obj = getChildNodes(new Long[]{id});
		if (obj!=null && obj.length>0) {
			for (int i=0; i<obj.length; i++) {
				this.delete(obj[i]);
				//getHibernateTemplate().bulkUpdate("delete from " + CommonDtree.class.getCanonicalName() + " where id = ?",obj[i].getId());			
			}		
		}
		getHibernateTemplate().bulkUpdate("delete from " + CommonDtree.class.getCanonicalName() + " where id = "+id);
	}		
	
	public CommonDtree[] getAllNode(int sysid) throws Exception {
		java.util.List list = getHibernateTemplate().find("from " + CommonDtree.class.getCanonicalName() + " where sysid = ? order by sorted, pid, id",sysid);
		int i = 0;
		if (list!=null && list.size()>0) {
			CommonDtree obj[] = new CommonDtree[list.size()];
			for (i=0; i<list.size(); i++) {
				obj[i] = (CommonDtree) list.get(i); 				
			}
			return obj;
		}
		return null;
	}	
	
	
	private java.util.Vector<CommonDtree> getParent(Long id, java.util.Vector<CommonDtree> objList) throws Exception {
		java.util.List list = getHibernateTemplate().find("from " + CommonDtree.class.getCanonicalName() + " where id=?", id);
		if (list!=null && list.size()>0) {
			for (int i=0; i<list.size(); i++) {
				CommonDtree obj = (CommonDtree) list.get(i); 
				objList.add(obj);
				objList = getParent(obj.getPid(), objList);
			}
		}			
		return objList;		
	}
	
	private java.util.Vector<CommonDtree> getChildren(Long pid, java.util.Vector<CommonDtree> objList) throws Exception {
		java.util.List list = getHibernateTemplate().find("from " + CommonDtree.class.getCanonicalName() + " where pid=? order by sorted, pid, id", pid);
		if (list!=null && list.size()>0) {
			for (int i=0; i<list.size(); i++) {
				CommonDtree obj = (CommonDtree) list.get(i); 
				objList.add(obj); 
				objList = getChildren(obj.getId(), objList);
			}
		}			
		return objList;
	}
	
	
	public CommonDtree[] getParentNodes(Long[] ids) throws Exception {
		if (ids!=null && ids.length>0) {
			java.util.Vector<CommonDtree> objList = new java.util.Vector<CommonDtree>();			
			for (int i=0; i<ids.length; i++) {
				objList = getParent(ids[i], objList);
			}
			CommonDtree tns[] = new CommonDtree[objList.size()];
			for (int i=0; i<objList.size(); i++) {
				tns[i] = objList.get(i);
			}
			return tns;
		}
		return null;
	}

	public CommonDtree[] getChildNodes(Long[] ids) throws Exception {
		if (ids!=null && ids.length>0) {
			java.util.Vector<CommonDtree> objList = new java.util.Vector<CommonDtree>();			
			for (int i=0; i<ids.length; i++) {
				objList = getChildren(ids[i], objList);
			}
			CommonDtree tns[] = new CommonDtree[objList.size()];
			for (int i=0; i<objList.size(); i++) {
				tns[i] = objList.get(i);
			}
			//return (DtreeNode[])objList.toArray();
			return tns;

		}
		return null;
	}


	public CommonDtree getNode(Long id) throws Exception {
		return (CommonDtree) getHibernateTemplate().get(CommonDtree.class,id);
	}
	
	public CommonDtree[] getRootNode(int sysid) throws Exception {
		java.util.List objList = getHibernateTemplate().find("from com.kangdainfo.project.tast.util.dtree.model.bo.DtreeNode where sysid = ? and (pid = ? or isNull(pid)) order by sorted, pid, id",new Object[]{sysid, sysid});
		CommonDtree tns[] = new CommonDtree[objList.size()];
		for (int i=0; i<objList.size(); i++) {
			tns[i] = (CommonDtree)objList.get(i);
		}
		return null;
	}
	

}
