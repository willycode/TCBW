package com.kangdainfo.common.model.dao.hibernate;

import com.kangdainfo.common.model.bo.CommonNews;
import com.kangdainfo.common.model.dao.CommonNewsDao;
import com.kangdainfo.persistence.hibernate3.BaseDaoImpl;

public class CommonNewsDaoImpl extends BaseDaoImpl implements CommonNewsDao {

    public void saveCommonNews(CommonNews obj) {
    	save(obj);
    }

    public void updateCommonNews(CommonNews obj) {
	    update(obj);
    }

    public void saveOrUpdateCommonNews(CommonNews obj) {
        saveOrUpdate(obj);
    }
	
	public void deleteCommonNews(CommonNews obj) {
		delete(obj);
    }
    
    public void deleteCommonNews(int id) {
	    delete(CommonNews.class,id);
    }    
    
    public CommonNews loadCommonNews(int id) {
    	return (CommonNews) load(CommonNews.class,id);
    }

}
