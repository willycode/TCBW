package com.kangdainfo.persistence.hibernate3;

import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.HibernateOperations;

import com.kangdainfo.persistence.BaseDaoService;
import com.kangdainfo.persistence.hibernate3.query.params.QueryParams;


/**
 * The <code>DaoManager</code>
 *
 * @author : airy.huang
 * @version : 1.0 ,Date : 20:42:11 2004/9/25
 */
public class BaseDaoImpl extends BaseQuery implements BaseDaoService {

	protected Logger logger = Logger.getLogger(this.getClass());

	private QueryByString queryByString;
	private QueryAll queryAll;
	private QueryByFieldName queryByFieldName;
	private QueryByParameterList queryByParameterList;
	private QueryByParams queryByParams;

	public BaseDaoImpl() {
		queryByString = new QueryByString();
		queryAll = new QueryAll();
		queryByFieldName = new QueryByFieldName();
		queryByParameterList = new QueryByParameterList();
		queryByParams = new QueryByParams();
		queryByParameterList.setQueryAll(queryAll);
	}
	public void setHibernateTemplate(HibernateOperations hibernateTemplate) {
		super.setHibernateTemplate(hibernateTemplate);
		queryByString.setHibernateTemplate(hibernateTemplate);
		queryAll.setHibernateTemplate(hibernateTemplate);
		queryByFieldName.setHibernateTemplate(hibernateTemplate);
		queryByParameterList.setHibernateTemplate(hibernateTemplate);
		queryByParams.setHibernateTemplate(hibernateTemplate);
	}

    public Object load(Class c, int id) { return getHibernateTemplate().get(c,new Integer(id)); }
    public Object load(Class c, long id) { return getHibernateTemplate().get(c,new Long(id)); }
	public void save(Object obj) {
		getHibernateTemplate().save(obj);
	}
	public void save(Collection collection) {
		if (collection!=null && collection.size()>0) {
			for (Object bean : collection) {
				getHibernateTemplate().save(bean);
			}			
		}
	}	
	public void saveBatch(Collection list) {
		save(list);
	}
	public void saveBatch(List list, int batchSize) {
		for(int i=0; i < list.size(); i++) {
			getHibernateTemplate().save(list.get(i));
			if(i%batchSize==0 || i==list.size()-1){
				getHibernateTemplate().flush();
				getHibernateTemplate().clear();
			}		
		}
	}	
	public void updateBatch(Collection collection) {
		if (collection!=null && collection.size()>0) {
			for (Object bean : collection) {
				getHibernateTemplate().update(bean);
			}			
		}
	}
	public void saveOrUpdateAll(Collection list) {
		if (list!=null && list.size()>0) {
			for (Object bean : list) {
				getHibernateTemplate().saveOrUpdate(bean);
			}
		}
	}

	public void update(Object obj) { getHibernateTemplate().update(obj); }
	public void saveOrUpdate(Object obj) { getHibernateTemplate().saveOrUpdate(obj); }
	public void delete(Object obj) { getHibernateTemplate().delete(obj); }

	public void delete(Class c, int id) {
		Object obj = load(c,id);
		getHibernateTemplate().delete(obj);
	}
	public void delete(Class c, long id) {
		Object obj = load(c,id);
		getHibernateTemplate().delete(obj);
	}	
	public void deleteAll(Class c) {
		List list = loadAll(c);
		getHibernateTemplate().deleteAll(list);
	}

	public int updateBulk(UpdateBulkParams updateBulkParams) {
		String sql = updateBulkParams.toString();
		return getHibernateTemplate().bulkUpdate(sql);
	}
	public int bulkUpdate(String sql) {
		return getHibernateTemplate().bulkUpdate(sql);
	}


	public int loadCount(String queryString) { return queryByString.loadCount(queryString); }
    public List load(String queryString) { return queryByString.load(queryString); }
    public List load(String queryString, int startAt, int size) { return queryByString.load(queryString, startAt, size); }
	public List load(String queryString, int startAt, int size, String orderBy, boolean isAscending) { return queryByString.load(queryString, startAt, size, orderBy, isAscending); }


	public int loadAllCount(Class c) { return queryAll.loadAllCount(c); }
	public List loadAll(Class c) { return queryAll.loadAll(c); }
    public List loadAll(Class c, String orderBy, boolean isAscending) { return queryAll.loadAll(c, orderBy, isAscending); }
	public List loadAll(Class c, int startAt, int size, String orderBy, boolean isAscending) { return queryAll.loadAll(c, startAt, size, orderBy, isAscending); }


	public int loadCount(Class c, String fieldName, Object fieldValue) { return queryByFieldName.loadCount(c, fieldName, fieldValue) ; }
	public List load(Class c, String fieldName, Object fieldValue){ return queryByFieldName.load(c, fieldName, fieldValue) ; }
	public List load(Class c, String fieldName, Object fieldValue, String orderBy, boolean isAscending) { return queryByFieldName.load(c, fieldName, fieldValue, orderBy, isAscending) ; }
	public List load(Class c, String fieldName, Object fieldValue, int startAt, int size, String orderBy, boolean isAscending) { return queryByFieldName.load(c, fieldName, fieldValue, startAt, size, orderBy, isAscending) ; }
	public Integer loadMaximumByFieldName(Class c, String fieldName) { return queryByFieldName.loadMaximumByFieldName(c, fieldName) ; }
	public List loadDistinctByFieldName(Class c, String fieldName) { return queryByFieldName.loadDistinctByFieldName(c, fieldName) ; }


	public int loadCount(Class c, String fieldName, Object fieldValue[], boolean inFieldValue) { return queryByParameterList.loadCount(c, fieldName, fieldValue, inFieldValue) ;}
    public List load(Class c, String fieldName, Object fieldValue[], boolean inFieldValue) { return queryByParameterList.load(c, fieldName, fieldValue, inFieldValue) ;}
	public List load(Class c, String fieldName, Object fieldValue[], boolean inFieldValue,String orderBy, boolean isAscending) { return queryByParameterList.load(c, fieldName, fieldValue, inFieldValue, orderBy, isAscending) ;}
    public List load(Class c, String fieldName, Object fieldValue[], boolean inFieldValue, String customizedOrderBy) {return queryByParameterList.load(c, fieldName, fieldValue, inFieldValue, customizedOrderBy); }
	public List load(Class c, String fieldName, Object fieldValue[], boolean inFieldValue, int startAt, int size, String orderBy, boolean isAscending) { return queryByParameterList.load(c, fieldName, fieldValue, inFieldValue, startAt, size, orderBy, isAscending); }
	public List load(Class c, String fieldName, Object fieldValue[], boolean inFieldValue, int startAt, int size, String customizedOrderBy)	 { return queryByParameterList.load(c, fieldName, fieldValue, inFieldValue, startAt, size, customizedOrderBy); }

	public int loadObjectsCountByParams(Class objClass, QueryParams params) { return queryByParams.loadObjectsCountByParams(objClass, params);}
    public List loadObjectsByParams(Class objClass, QueryParams params) { return queryByParams.loadObjectsByParams(objClass, params);}
    public List loadObjectsByParams(Class objClass, QueryParams params,String orderBy, boolean isAscending) { return queryByParams.loadObjectsByParams(objClass, params, orderBy, isAscending);}
	public List loadObjectsByParams(Class objClass, QueryParams params, int startAt, int size, String orderBy, boolean isAscending) { return queryByParams.loadObjectsByParams(objClass, params, startAt, size, orderBy, isAscending);}


	public List loadByCriteria(DetachedCriteria criteria) {
		return getHibernateTemplate().findByCriteria(criteria);
	}

    public Object getObject(String sql) {
        try {
        	List list = this.load(sql, 0, 1);
        	if (list!=null && list.size()>0) {
        		return list.get(0);
        	}
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    	return null;
    }
    
    public Object getObject(String hql, Object[] paramArrayOfObject) {
        try {        	
        	List list = getHibernateTemplate().find(hql, paramArrayOfObject);
        	if (list!=null && list.size()>0) {
        		return list.get(0);
        	}
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    	return null;
    }    

    public Object[] getObjects(String sql) {
        try {
        	List list = this.load(sql, 0, 1);
        	if (list!=null && list.size()>0) {
        		return (Object[])list.get(0);
        	}
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    	return null;
    }
    public Object[] getObjects(String hql, Object[] paramArrayOfObject) {
        try {
        	List list = getHibernateTemplate().find(hql, paramArrayOfObject);
        	if (list!=null && list.size()>0) {
        		return (Object[])list.get(0);
        	}
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    	return null;
    }    
    
    
	public void update(String sql, Object[] o) { this.getHibernateTemplate().bulkUpdate(sql, o); }	
	
	public void deleteAndSave(java.util.Collection delList, java.util.Collection saveList) throws Exception {
		if (delList!=null && delList.size()>0) {
			for (Object bean : delList) {
				getHibernateTemplate().delete(bean);
			}
			this.getHibernateTemplate().flush();
			this.getHibernateTemplate().clear();			
		}
		if (saveList!=null && saveList.size()>0) this.save(saveList);
	}
	
	public void deleteAndSave(String delSQL, java.util.Collection saveList) throws Exception {
		bulkUpdate(delSQL);
		if (saveList!=null && saveList.size()>0) this.save(saveList);		
	}	
	
	public void deleteBatch(java.util.Collection delList) throws Exception {
		if (delList!=null && delList.size()>0) {
			this.getHibernateTemplate().deleteAll(delList);
		}		
	}
	
	public void bulkUpdate(String[] arrSQL) throws Exception {
		if (arrSQL!=null && arrSQL.length>0) {
			for (String sql : arrSQL) getHibernateTemplate().bulkUpdate(sql);
		}
	}	    

}
