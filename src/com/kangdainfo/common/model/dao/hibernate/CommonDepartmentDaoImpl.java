package com.kangdainfo.common.model.dao.hibernate;

import java.util.List;

import org.apache.commons.io.FileUtils;

import com.kangdainfo.common.model.bo.CommonDepartment;
import com.kangdainfo.common.model.dao.CommonDepartmentDao;
import com.kangdainfo.common.util.*;
import com.kangdainfo.persistence.hibernate3.BaseDaoImpl;

public class CommonDepartmentDaoImpl extends BaseDaoImpl implements
		CommonDepartmentDao {

	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	//
	//	CommonDepartment base function
	//
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public void saveCommonDepartment(CommonDepartment obj) {
    	save(obj);
    }

    public void saveOrUpdateCommonDepartment(CommonDepartment obj) {
        saveOrUpdate(obj);
    }
	
	public void deleteCommonDepartment(CommonDepartment obj) {
		delete(obj);
    }
    
    public void deleteCommonDepartment(int id) {
	    delete(CommonDepartment.class,id);
    }
    
    public void deleteAllCommonDepartments() {
    	deleteAll(CommonDepartment.class);
    }
    
    public CommonDepartment loadCommonDepartment(int id) {
    	return (CommonDepartment) load(CommonDepartment.class,id);
    }

    public List loadAllCommonDepartments() {
    	return loadAll(CommonDepartment.class);
	}
    
	public java.util.Map<String, CommonDepartment> getDeptMap(java.util.Map<String, CommonDepartment> deptMap, boolean useFullCode) {
		if (deptMap==null) deptMap = new java.util.HashMap<String, CommonDepartment>();
		java.util.List list = getHibernateTemplate().find("from CommonDepartment order by fullCode");
		if (list!=null && list.size()>0) {
			for (Object o: list) {
				CommonDepartment objDept = (CommonDepartment) o;
				if (useFullCode) deptMap.put(objDept.getFullCode(), objDept);
				else deptMap.put(Common.get(objDept.getShortCode()), objDept);
			}
		}
		return deptMap;
	}		
	
	public java.util.Map<String, String> getDeptNameMap(java.util.Map<String, String> deptMap, boolean useFullCode) {
		if (deptMap==null) deptMap = new java.util.HashMap<String, String>();
		java.util.List list = getHibernateTemplate().find("from CommonDepartment order by fullCode");
		if (list!=null && list.size()>0) {
			for (Object o: list) {
				CommonDepartment objDept = (CommonDepartment) o;
				if (useFullCode) deptMap.put(objDept.getFullCode(), objDept.getDepartment());
				else deptMap.put(Common.get(objDept.getShortCode()), objDept.getDepartment());
			}
		}
		return deptMap;
	}
	
	public java.util.Map<String, CommonDepartment> getDeptOrderByShortCode(java.util.Map<String, CommonDepartment> deptMap, boolean useFullCode) {
		if (deptMap==null) deptMap = new java.util.HashMap<String, CommonDepartment>();
		java.util.List list = getHibernateTemplate().find("from CommonDepartment order by shortCode");
		if (list!=null && list.size()>0) {
			for (Object o: list) {
				CommonDepartment objDept = (CommonDepartment) o;
				if (useFullCode) deptMap.put(objDept.getFullCode(), objDept);
				else deptMap.put(Common.get(objDept.getShortCode()), objDept);
			}
		}
		return deptMap;
	}
	
	
	
	public void updateCommonDepartment(CommonDepartment obj) {
		try {		
			updateChildren(obj);			
			update(obj);	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void updateChildren(CommonDepartment parent) throws Exception {
		java.util.List list = getHibernateTemplate().find("from " + CommonDepartment.class.getCanonicalName() + " where parentId=? order by fullCode", parent.getId());
		if (list!=null && list.size()>0) {
			for (int i=0; i<list.size(); i++) {
				CommonDepartment obj = (CommonDepartment) list.get(i);
				obj.setFullName(parent.getFullName()+"-"+obj.getDepartment());
				update(obj);
				updateChildren(obj);
			}
		}
	}
	
	public void deleteCommonDepartmentNode(int id) throws Exception {
		CommonDepartment[] obj = getChildNodes(id);
		if (obj!=null && obj.length>0) {
			for (int i=0; i<obj.length; i++) {
				getHibernateTemplate().bulkUpdate("delete from " + CommonDepartment.class.getCanonicalName() + " where id = ? ",obj[i].getId());			
			}			
		}
		getHibernateTemplate().bulkUpdate("delete from " + CommonDepartment.class.getCanonicalName() + " where id = ? ",id);
	}	

	public CommonDepartment[] getAllNode() throws Exception {
		java.util.List list = getHibernateTemplate().find("from " + CommonDepartment.class.getCanonicalName() + " order by fullCode");
		int i = 0;
		if (list!=null && list.size()>0) {
			CommonDepartment obj[] = new CommonDepartment[list.size()];
			for (i=0; i<list.size(); i++) {
				obj[i] = (CommonDepartment) list.get(i); 				
			}
			return obj;
		}
		return null;
	}

	public CommonDepartment getCommonDepartment(int id) throws Exception {		
		return loadCommonDepartment(id);
	}
			
	public CommonDepartment getCommonDepartment(String fullOrShortCode) throws Exception {				
		java.util.List list = getHibernateTemplate().find("from " + CommonDepartment.class.getCanonicalName() + " where fullCode=? or shortCode=? ", new Object[]{Common.formatRearZero(fullOrShortCode,10), fullOrShortCode});
		if (list!=null && list.size()>0) {
			return (CommonDepartment) list.get(0);
		}
		return null;
	}	

	private java.util.Vector<CommonDepartment> getParent(int id, java.util.Vector<CommonDepartment> objList, boolean includeAllAscendant) throws Exception {
		java.util.List list = getHibernateTemplate().find("from " + CommonDepartment.class.getCanonicalName() + " where id=? order by fullCode", id);
		if (list!=null && list.size()>0) {
			for (int i=0; i<list.size(); i++) {
				CommonDepartment obj = (CommonDepartment) list.get(i); 
				objList.add(obj);
				if (includeAllAscendant) objList = getParent(obj.getParentId(), objList, includeAllAscendant);
			}
		}			
		return objList;
	}			
	
	private java.util.Vector<CommonDepartment> getChildren(int id, java.util.Vector<CommonDepartment> objList, boolean includeAllDescendant) throws Exception {
		java.util.List list = getHibernateTemplate().find("from " + CommonDepartment.class.getCanonicalName() + " where parentId=? order by fullCode", id);
		if (list!=null && list.size()>0) {
			for (int i=0; i<list.size(); i++) {
				CommonDepartment obj = (CommonDepartment) list.get(i); 
				objList.add(obj); 
				if (includeAllDescendant) objList = getChildren(obj.getId(), objList, includeAllDescendant);
			}
		}			
		return objList;
	}	
	
	public CommonDepartment[] getChildNodes(int id) throws Exception {
		return getChildNodes(id, true);
	}
	
	public CommonDepartment[] getChildNodes(int id, boolean includeAllDescendant) throws Exception {
		java.util.Vector<CommonDepartment> objList = new java.util.Vector<CommonDepartment>();			
		objList = getChildren(id, objList, includeAllDescendant);
		if (objList!=null && objList.size()>0) {
			CommonDepartment tns[] = new CommonDepartment[objList.size()];
			for (int i=0; i<objList.size(); i++) {
				tns[i] = objList.get(i);
			}
			return tns;				
		}		
		return null;
	}	

	public CommonDepartment[] getParentNodes(int id) throws Exception {
		return getParentNodes(id, true);
	}
	
	public CommonDepartment[] getParentNodes(int id, boolean includeAllAscendant) throws Exception {
		java.util.Vector<CommonDepartment> objList = new java.util.Vector<CommonDepartment>();			
		objList = getParent(id, objList, includeAllAscendant);
		if (objList!=null && objList.size()>0) {
			CommonDepartment tns[] = new CommonDepartment[objList.size()];
			for (int i=0; i<objList.size(); i++) {
				tns[i] = objList.get(i);
			}
			return tns;				
		}
		return null;
	}	
	
	public List loadDeptsByFullCodes(String[] depts) {
		if (depts!=null && depts.length==2) {
			StringBuilder sb = new StringBuilder();
			sb.append("from " + CommonDepartment.class.getCanonicalName() + " d where d.fullCode between ? and ? ");
			List queryList = getHibernateTemplate().find(sb.toString(), new Object[]{depts[0],depts[1]});
			return queryList;			
		} else {
			return null;
		}
	}    

	public int importCommonDepartmentFromCPA(java.io.File srcFile) throws Exception {
		if (srcFile!=null && srcFile.exists() && srcFile.isFile()) {
			String ext = Common.getFileExtension(srcFile);			
			if (ext.equals("xls") || ext.equals("txt")) {
				java.util.Map<String, CommonDepartment> h = new java.util.HashMap<String, CommonDepartment>();
				java.util.Map<String, CommonDepartment> m = new java.util.HashMap<String, CommonDepartment>();
				java.util.List list = this.load("from CommonDepartment order by fullCode");			
				if (list!=null && list.size()>0) {
					for (int i=0; i<list.size(); i++) {
						CommonDepartment obj = (CommonDepartment) list.get(i);
						h.put(obj.getFullCode(), obj);
					}				
				}			
				java.util.List<CommonDepartment> updateList = new java.util.ArrayList<CommonDepartment>();
				java.util.List<CommonDepartment> saveList = new java.util.ArrayList<CommonDepartment>();
				
				//0.機關代碼
				//1.機關名稱
				//2.郵遞區號
				//3.機關地址
				//4.機關電話
				//5.主管機關代碼
				//6.主管機關名稱
				//7.機關傳真
				//8.生效日期
				//9.裁撤生效日期
				//10.機關層級
				if (ext.equals("txt")) list = FileUtils.readLines(srcFile, "UTF-8");
				else {
					XlsUtil xls = new XlsUtil();
					list = xls.getJExcelModel(srcFile.getPath(), 0, false, 11);
				}
				if (list!=null && list.size()>0) {			
					boolean isUpdate = true;
					for (int i=0; i<list.size(); i++) {
						isUpdate = true;
						String[] arrField = ext.equals("txt")?Common.get(list.get(i)).split(","):(String[])list.get(i);
						if (i>0 && arrField.length>9) {												
							String key = arrField[0];
							CommonDepartment obj = h.get(key);
							if (obj==null) {
								obj = new CommonDepartment();
								isUpdate = false;
							}
							obj.setDepartmentCode(key);
							obj.setDepartment(Common.get(arrField[1]).replaceAll("　", ""));
							obj.setZip(Common.get(arrField[2]));
							obj.setAddress(Common.get(arrField[3]));
							obj.setPhone(Common.get(arrField[4]));					
							obj.setPreFullCode(Common.get(arrField[5]));						
							if (key.equals(obj.getPreFullCode())) obj.setPreFullCode("");						
							obj.setFax(Common.get(arrField[7]));
							obj.setLevel(Common.getInt(arrField[10]));						
							obj.setFullCode(key);
							obj.setFullName(Common.get(arrField[1]).replaceAll("　", ""));
							
							if (isUpdate) updateList.add(obj);
							else saveList.add(obj);
							
							m.put(key, obj);
						}
					}	
					if (saveList!=null && saveList.size()>0) this.saveBatch(saveList);
					if (updateList!=null && updateList.size()>0) this.updateBatch(updateList);
				}
				saveList.clear();
				updateList.clear();
				
				for (String key : m.keySet()) h.put(key, m.get(key));			
				for (String key : h.keySet()) {
					CommonDepartment obj = h.get(key);				
					if (!"".equals(Common.get(obj.getPreFullCode())) && h.get(obj.getPreFullCode())!=null) {
						obj.setParentId(h.get(obj.getPreFullCode()).getId());
						updateList.add(obj);
					}
				}
				if (updateList!=null && updateList.size()>0) this.updateBatch(updateList);
				
				return m.size();				
			}
		}			
		return 0;
	}	
	
	public CommonDepartment getIssueCommonDepartment(String shortCode) {				
		try {
			CommonDepartment ref = getCommonDepartment(shortCode);
			if (ref!=null) {
				if (ref.getLevel()!=null && ref.getLevel().intValue()==1) {
					return ref;
				} else {
					return getCommonDepartment(Common.formatRearZero(ref.getFullCode().substring(0,4),10));					
				}				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
