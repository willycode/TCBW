package com.kangdainfo.common.model.dao.hibernate;

import com.kangdainfo.common.model.bo.CommonCodeKind;
import com.kangdainfo.common.model.dao.CommonCodeKindDao;
import com.kangdainfo.persistence.hibernate3.BaseDaoImpl;

public class CommonCodeKindDaoImpl extends BaseDaoImpl implements
		CommonCodeKindDao {

    public void saveCommonCodeKind(CommonCodeKind obj) {
    	save(obj);
    }

    public void updateCommonCodeKind(CommonCodeKind obj) {
	    update(obj);
    }

    public void saveOrUpdateCommonCodeKind(CommonCodeKind obj) {
        saveOrUpdate(obj);
    }
	
	public void deleteCommonCodeKind(CommonCodeKind obj) {
		delete(obj);
    }
    
    public void deleteCommonCodeKind(int id) {
	    delete(CommonCodeKind.class,id);
    }    
    
    public CommonCodeKind loadCommonCodeKind(int id) {
    	return (CommonCodeKind) load(CommonCodeKind.class,id);
    }

}
