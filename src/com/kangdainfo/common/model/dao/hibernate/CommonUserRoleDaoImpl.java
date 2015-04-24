package com.kangdainfo.common.model.dao.hibernate;

import com.kangdainfo.common.model.bo.CommonUserRole;
import com.kangdainfo.common.model.dao.CommonUserRoleDao;
import com.kangdainfo.persistence.hibernate3.BaseDaoImpl;

public class CommonUserRoleDaoImpl extends BaseDaoImpl implements CommonUserRoleDao {
	
    public void saveCommonUserRole(CommonUserRole obj) {
    	save(obj);
    }

    public void updateCommonUserRole(CommonUserRole obj) {
	    update(obj);
    }

    public void saveOrUpdateCommonUserRole(CommonUserRole obj) {
        saveOrUpdate(obj);
    }
	
	public void deleteCommonUserRole(CommonUserRole obj) {
		delete(obj);
    }
    
    public void deleteCommonUserRole(int id) {
	    delete(CommonUserRole.class,id);
    }    
    
    public CommonUserRole loadCommonUserRole(int id) {
    	return (CommonUserRole) load(CommonUserRole.class,id);
    }

}
