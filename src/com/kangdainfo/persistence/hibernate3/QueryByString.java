package com.kangdainfo.persistence.hibernate3;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

public class QueryByString extends BaseQuery {

	public int loadCount(final String queryString) {
    	Number number = ((Number)getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
                Query query = session.createQuery("select count(*) " + queryString);
                return query.uniqueResult();
         }
        }));
    	if(number!=null) {
    		return number.intValue();
    	}
    	return 0;
    }
    public List load(final String queryString) {
        return getHibernateTemplate().executeFind(new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                Query query = session.createQuery(queryString);
                return query.list();
            }
        });
    }


    public List load(final String queryString, final int startAt, final int size) {
        return getHibernateTemplate().executeFind(new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                Query query = session.createQuery(queryString);
                query.setFirstResult(startAt);
                query.setMaxResults(size);
                return query.list();
            }
        });
    }
    public List load(final String queryString, final int startAt,
            final int size, final String orderBy, final boolean isAscending) {

        return getHibernateTemplate().executeFind(new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                HQL hql = new HQL(queryString);
                hql = hql.appendOrderBy(orderBy, isAscending);
                Query query = session.createQuery(hql.toString());
                query.setFirstResult(startAt);
                query.setMaxResults(size);
                return query.list();
            }
        });
    }
}
