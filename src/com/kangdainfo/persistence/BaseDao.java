package com.kangdainfo.persistence;

import java.util.Collection;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.HibernateOperations;

import com.kangdainfo.persistence.hibernate3.UpdateBulkParams;
import com.kangdainfo.persistence.hibernate3.query.params.QueryParams;

public interface BaseDao {

	public HibernateOperations getHibernateTemplate();
	public void setHibernateTemplate(HibernateOperations hibernateTemplate);

	public Object load(Class c, int id);
	public Object load(Class c, long id);
	public void save(Object obj);	
	public void save(Collection collection) ;
	public void saveBatch(java.util.Collection list);
	public void saveBatch(java.util.List list, int batchSize);
	public void updateBatch(Collection list);
	public void saveOrUpdateAll(java.util.Collection list);
	
	public void update(Object obj);
	public void saveOrUpdate(Object obj);
	
	public void delete(Object obj);
	public void delete(Class c, int id);
	public void delete(Class c, long id);
	public void deleteAll(Class c);

	public int updateBulk(UpdateBulkParams updateBulkParams);
	public int bulkUpdate(String sql);
	
	public int loadCount(String queryString);
    public List load(String queryString);
    public List load(String queryString, int startAt, int size);
	public List load(String queryString, int startAt, int size, String orderBy, boolean isAscending);
	
	
	public int loadAllCount(Class c);
	public List loadAll(Class c);
    public List loadAll(Class c, String orderBy, boolean isAscending);
	public List loadAll(Class c, int startAt, int size, String orderBy, boolean isAscending);

	
	public int loadCount(Class c, String fieldName, Object fieldValue);
	public List load(Class c, String fieldName, Object fieldValue);
	public List load(Class c, String fieldName, Object fieldValue, String orderBy, boolean isAscending);
	public List load(Class c, String fieldName, Object fieldValue, int startAt, int size, String orderBy, boolean isAscending);
	public Integer loadMaximumByFieldName(Class c, String fieldName);
	public List loadDistinctByFieldName(Class c, String fieldName);
	
	
	public int loadCount(Class c, String fieldName, Object fieldValue[], boolean inFieldValue);
    public List load(Class c, String fieldName, Object fieldValue[], boolean inFieldValue);
	public List load(Class c, String fieldName, Object fieldValue[], boolean inFieldValue,String orderBy, boolean isAscending);
	public List load(Class c, String fieldName, Object fieldValue[], boolean inFieldValue, String customizedOrderBy);
	public List load(Class c, String fieldName, Object fieldValue[], boolean inFieldValue, int startAt, int size, String orderBy, boolean isAscending);
    public List load(Class c, String fieldName, Object fieldValue[], boolean inFieldValue, int startAt, int size, String customizedOrderBy);
		
		
	public int loadObjectsCountByParams(Class objClass, QueryParams params);
    public List loadObjectsByParams(Class objClass, QueryParams params);
    public List loadObjectsByParams(Class objClass, QueryParams params,String orderBy, boolean isAscending);    
	public List loadObjectsByParams(Class objClass, QueryParams params, int startAt, int size, String orderBy, boolean isAscending);

	public List loadByCriteria(DetachedCriteria criteria);
	
	
	public Object toObject(Object var);
	public Object toObject(int var);
	public Object toObject(long var);
	public Object toObject(short var);
	public Object toObject(double var);
	public Object toObject(float var);
	public Object toObject(boolean var);
	public Object toObject(char var);
	
	public Object getObject(String hql);
	public Object getObject(String hql, Object[] paramArrayOfObject);
	public Object[] getObjects(String hql);
	public Object[] getObjects(String hql, Object[] paramArrayOfObject);
	
	public void update(String sql, Object[] o);
	
	/**
	 * 先執行刪除delList物件的動作, 然後再執行saveList的儲存動作
	 * <br>通常用在生成作業上, 因為要重新生成, 所以需要將原來的東西清空
	 * @param delList
	 * @param saveList
	 * @throws Exception
	 */
	public void deleteAndSave(java.util.Collection delList, java.util.Collection saveList) throws Exception;
	/**
	 * 先執行刪除delSQL物件的動作, 然後再執行saveList的儲存動作
	 * <br>通常用在生成作業上, 因為要重新生成, 所以需要將原來的東西清空
	 * @param delSQL
	 * @param saveList
	 * @throws Exception
	 */
	public void deleteAndSave(String delSQL, java.util.Collection saveList) throws Exception; 		
	
	public void deleteBatch(java.util.Collection delList) throws Exception;
	
	public void bulkUpdate(String[] hql) throws Exception;		


}