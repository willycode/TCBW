package com.kangdainfo.tcbw.model.dao;

public interface NativeSqlQuery {
	
//	public void setSessionFactory(SessionFactory sessionFactory);
//	public SessionFactory getSessionFactory();
	
	public java.util.List load(String sql);
	
}
