package com.kangdainfo.persistence.hibernate3;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

public class QueryByParameterList extends BaseQuery {

	private QueryAll queryAll;
	
	public void setQueryAll(QueryAll queryAll) { this.queryAll = queryAll; }
	
    public int loadCount(final Class c, final String fieldName, final Object fieldValue[], final boolean inFieldValue) {        
    	if(fieldValue==null || fieldValue.length==0) {
    		if(inFieldValue) { return 0; }
    		else { return queryAll.loadAllCount(c); }
    	}
    	return ((Number)getHibernateTemplate().execute(new HibernateCallback() {
             public Object doInHibernate(Session session) throws HibernateException {
                 HQL hql = new HQL(getQueryString(c,fieldName,inFieldValue));
                 hql = hql.appendSelectCount();
                 Query query = session.createQuery(hql.toString());
                 query.setParameterList(fieldName, fieldValue);  
 	             return query.uniqueResult();
          }
         })).intValue();
     }
    

    public List load(Class c, String fieldName, Object fieldValue[], boolean inFieldValue) {
       	return load_common(c,fieldName,fieldValue,inFieldValue,null,null,null,null);
 	}
    
    public List load(Class c, String fieldName, Object fieldValue[], boolean inFieldValue,
   		 String orderBy, boolean isAscending) {
    	return load_common(c,fieldName,fieldValue,inFieldValue,null,null,orderBy,isAscending);
    }
	public List load(Class c, String fieldName, Object fieldValue[], boolean inFieldValue,
	   		int startAt, int size, String orderBy, boolean isAscending) {
		return load_common(c,fieldName,fieldValue,inFieldValue,startAt,size, orderBy, isAscending);
	}
	
    public List load(Class c, String fieldName, Object fieldValue[], boolean inFieldValue, String customizedOrderBy) {
       	return load_common(c,fieldName,fieldValue,inFieldValue,null,null,customizedOrderBy,null);
    }
	public List load(Class c, String fieldName, Object fieldValue[], boolean inFieldValue,
	   		int startAt, int size, String customizedOrderBy) {
		return load_common(c,fieldName,fieldValue,inFieldValue,startAt,size, customizedOrderBy, null);
	}
	
    private List load_common(final Class c, final String fieldName, final Object fieldValue[], final Boolean inFieldValue,
   		final Integer startAt, final Integer size, final String orderBy, final Boolean isAscending) {

	   	if(fieldValue==null || fieldValue.length==0) 
	   	{
	   		if(inFieldValue) { return new ArrayList(); }
	   		else { 
	   			List list = queryAll.loadAll(c,startAt,size,orderBy,isAscending); 
	   			return list;
	   		}
	   	}
	   	return getHibernateTemplate().executeFind(new HibernateCallback() {
	   		public Object doInHibernate(Session session) throws HibernateException {
	   			StringBuffer sb = new StringBuffer(getQueryString(c,fieldName,inFieldValue));
                if(orderBy!=null) {
                	String orderByString = getOrderByString("obj",orderBy,isAscending);
                	sb.append(orderByString);
                }
	   			Query query = session.createQuery(sb.toString());
	   			query.setParameterList(fieldName, fieldValue);  
	   			if(startAt!=null && size!=null) {
	   				query.setFirstResult(startAt);
	   				query.setMaxResults(size);
	   			}
	   			return query.list();
	   		}
	   	});
	}
   
	private String getQueryString(Class c, String fieldName, boolean inFieldValue) {
	   String not = inFieldValue? "  " : " not ";
	   return "from "+c.getName()+" obj where obj."+fieldName + not +" in (:"+fieldName+") ";
	}

   
   
   
}
