package com.kangdainfo.persistence.hibernate3;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

public class QueryAll extends BaseQuery {

    public int loadAllCount(final Class c) {
    	Number number = ((Number)getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
                HQL hql = new HQL(getQueryString(c));
                hql = hql.appendSelectCount();
                Query query = session.createQuery(hql.toString());
                return query.uniqueResult();
         }
        }));
    	if(number!=null) {
    		return number.intValue();
    	}
    	return 0;
    }
    
    public List loadAll(Class c) {
    	return loadAll(c,null,null,null,null);
	} 

    public List loadAll(Class c, String orderBy, Boolean isAscending) {
    	return loadAll(c,null,null,orderBy,isAscending);
    }
    public List loadAll(final Class c, 
    		final Integer startAt, final Integer size, final String orderBy, final Boolean isAscending) {

        return getHibernateTemplate().executeFind(new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                StringBuffer sb = new StringBuffer(getQueryString(c));
                if(orderBy!=null) {
                	String orderByString = getOrderByString("obj",orderBy,isAscending);
                	sb.append(orderByString);
                }
                Query query = session.createQuery(sb.toString());
                if(startAt!=null && size!=null) {
                	query.setFirstResult(startAt);
                	query.setMaxResults(size);
                }
                return query.list();
            }
        });
    }

    private String getQueryString(Class c) {
    	return "from "+c.getName()+" obj";
    }

    
}
