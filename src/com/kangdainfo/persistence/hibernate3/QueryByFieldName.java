package com.kangdainfo.persistence.hibernate3;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

public class QueryByFieldName extends BaseQuery {

    public int loadCount(final Class c, final String fieldName, final Object fieldValue) {
        return ((Number)getHibernateTemplate().execute(new HibernateCallback() {
             public Object doInHibernate(Session session) throws HibernateException {
                 HQL hql = new HQL(getQueryString(c,fieldName));
                 hql = hql.appendSelectCount();
                 Query query = session.createQuery(hql.toString());
                 setQuery(query,fieldName,fieldValue);
 	             return query.uniqueResult();
          }
         })).intValue();
     }

    public List load(final Class c, final String fieldName, final Object fieldValue) {
    	return load(c,fieldName,fieldValue,null,null,null,null);
    }

    public List load(final Class c, final String fieldName, final Object fieldValue,
    		final String orderBy, final boolean isAscending) {

    	return load(c,fieldName,fieldValue,null,null,orderBy,isAscending);
    }
    
    public List load(final Class c, final String fieldName, final Object fieldValue,
    		final Integer startAt, final Integer size, final String orderBy, final Boolean isAscending) {

    	return getHibernateTemplate().executeFind(new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                StringBuffer sb = new StringBuffer(getQueryString(c,fieldName));
                if(orderBy!=null) {
                	String orderByString = getOrderByString("obj",orderBy,isAscending);
                	sb.append(orderByString);
                }
                Query query = session.createQuery(sb.toString());
                setQuery(query,fieldName,fieldValue);
                if(startAt!=null && size!=null) {
	                query.setFirstResult(startAt);
	                query.setMaxResults(size);
                }
                return query.list();
            }
        });
    }
    private String getQueryString(Class c, String fieldName) {
    	return "from "+c.getName()+" obj where obj."+fieldName+" = :"+fieldName+" ";
    }
    
    
    public Integer loadMaximumByFieldName(final Class c, final String fieldName) {
        return ((Integer)getHibernateTemplate().execute(new HibernateCallback() {
             public Object doInHibernate(Session session) throws HibernateException {
                 HQL hql = new HQL("select max(obj."+fieldName+") from "+c.getName()+" obj");
                 Query query = session.createQuery(hql.toString());
 	             return query.uniqueResult();
          }
         }));
     } 
    public List loadDistinctByFieldName(final Class c, final String fieldName) {
        return getHibernateTemplate().executeFind(new HibernateCallback() {
             public Object doInHibernate(Session session) throws HibernateException {
                 HQL hql = new HQL("select distinct(obj."+fieldName+") from "+c.getName()+" obj");
                 Query query = session.createQuery(hql.toString());
 	             return query.list();
          }
         });
     }    




    
}
