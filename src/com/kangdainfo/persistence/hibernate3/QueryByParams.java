package com.kangdainfo.persistence.hibernate3;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.kangdainfo.persistence.hibernate3.query.params.QueryHelper;
import com.kangdainfo.persistence.hibernate3.query.params.QueryParams;

public class QueryByParams extends BaseQuery {

	public int loadObjectsCountByParams(final Class objClass, final QueryParams params) {
		return ((Number) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
				Query query = QueryHelper.createCountQuery(session,objClass, params);
				return query.uniqueResult();
			}
		})).intValue();
	}
	
	public List loadObjectsByParams(final Class objClass, final QueryParams params) {
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
				Query query = QueryHelper.createQuery(session, objClass, params);
				return query.list();
			}
		});
	}
	    
	public List loadObjectsByParams(final Class objClass, final QueryParams params, final String orderBy, final boolean isAscending) {
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
				Query query = QueryHelper.createQuery(session, objClass,params, orderBy, isAscending);
				return query.list();
			}
		});
	}
	    
	public List loadObjectsByParams(final Class objClass, final QueryParams params, final int startAt, 
			final int size, final String orderBy, final boolean isAscending) {
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
				Query query = QueryHelper.createQuery(session, objClass, params, startAt, size, orderBy, isAscending);
				return query.list();
			}
		});
	}


		
}
