package com.kangdainfo.persistence;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateOperations;
import javax.sql.DataSource;

public class PersistenceServiceGetter {

    private static PersistenceServiceGetter instance;

    public PersistenceServiceGetter() {
        if(PersistenceServiceGetter.instance != null) {
            throw new RuntimeException(
                    this.getClass().getName()
                            + "is designed to be a Singleton, the instance already exist:"
                            + PersistenceServiceGetter.instance);
        }
        PersistenceServiceGetter.instance = this;
    }
    

    public static PersistenceServiceGetter getInstance() {
        return instance;
    }    
    
    private JdbcTemplate jdbcTemplate;
    private HibernateOperations hibernateTemplate;
    private BaseDao baseDao;
    private BaseDaoService baseDaoService;
    private DataSource dataSource;
    //private org.apache.commons.dbcp.BasicDataSource dataSource;
    
	public HibernateOperations getHibernateTemplate() { return hibernateTemplate; }
	public void setHibernateTemplate(HibernateOperations hibernateTemplate) { this.hibernateTemplate = hibernateTemplate; }
	public JdbcTemplate getJdbcTemplate() { return jdbcTemplate; }
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate; }
	public BaseDao getBaseDao() { return baseDao; }
	public void setBaseDao(BaseDao baseDao) { this.baseDao = baseDao; }
	public DataSource getDataSource() { return dataSource; }
	public void setDataSource(DataSource dataSource) { this.dataSource = dataSource; }
	public BaseDaoService getBaseDaoService() { return baseDaoService; }
	public void setBaseDaoService(BaseDaoService baseDaoService) { this.baseDaoService = baseDaoService; }
    

}
