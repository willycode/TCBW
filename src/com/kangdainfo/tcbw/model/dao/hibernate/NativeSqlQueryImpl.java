package com.kangdainfo.tcbw.model.dao.hibernate;

import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.SessionFactoryUtils;

import com.kangdainfo.tcbw.model.dao.NativeSqlQuery;

public class NativeSqlQueryImpl implements NativeSqlQuery{

	private SessionFactory sessionFactory;
	public SessionFactory getSessionFactory() {		return sessionFactory;	}
	public void setSessionFactory(SessionFactory sessionFactory) {		this.sessionFactory = sessionFactory;	}
	
	public java.util.List load(String sql)
	{
		java.util.List r = null;
		try
		{
			Session session = SessionFactoryUtils.getSession(getSessionFactory(), false);
			SQLQuery sqlQuery = session.createSQLQuery(sql);
			if(sqlQuery != null)
			{
				r = sqlQuery.list();
			}
			//session.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
		//	getSessionFactory().close();
		}
		return r;
	}
	


}
