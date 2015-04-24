package com.kangdainfo.common.model.dao.hibernate;

import java.util.List;

import com.kangdainfo.common.model.bo.CommonCode;
import com.kangdainfo.common.model.dao.CommonCodeDao;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.persistence.hibernate3.BaseDaoImpl;

public class CommonCodeDaoImpl extends BaseDaoImpl implements CommonCodeDao {
	
    public void saveCommonCode(CommonCode obj) {
    	save(obj);
    }

    public void updateCommonCode(CommonCode obj) {
	    update(obj);
    }

    public void saveOrUpdateCommonCode(CommonCode obj) {
        saveOrUpdate(obj);
    }
	
	public void deleteCommonCode(CommonCode obj) {
		delete(obj);
    }
    
    public void deleteCommonCode(int id) {
	    delete(CommonCode.class,id);
    }    
    
    public CommonCode loadCommonCode(int id) {
    	return (CommonCode) load(CommonCode.class,id);
    }
    
    public CommonCode getCommonCode(String codeKindId, String codeId) {
    	return (CommonCode) getObject("from " + CommonCode.class.getCanonicalName() + " where commonCodeKind.codeKindId=? and codeId=?", new Object[]{codeKindId, codeId});
    }
    
    public String getCommonCodeName(String codeKindId, String codeId) {
    	CommonCode obj = getCommonCode(codeKindId, codeId);
    	if (obj!=null && obj.getCodeName()!=null) return obj.getCodeName();
    	else return Common.get(codeId);
    }
    
    public java.util.List<CommonCode> getCommonCodeList(String codeKindId) {
    	return getHibernateTemplate().find("from " + CommonCode.class.getCanonicalName() + " where (isStop is null or isStop = '' or isStop <> 'Y') and commonCodeKind.codeKindId=? order by codeId", new Object[]{codeKindId});
    }
    
    public java.util.Map<String, CommonCode> getCommonCodeMap(String codeKindId, java.util.Map<String, CommonCode> h) {
    	if (h==null) h = new java.util.HashMap<String, CommonCode>();
    	java.util.List<CommonCode> list = getCommonCodeList(codeKindId);
    	if (list!=null && list.size()>0) {
    		for (int i=0; i<list.size(); i++) {
    			CommonCode obj = list.get(i);
    			h.put(obj.getCodeId(), obj);
    		}
    	}    	
    	return h; 
    }    
    
    public java.util.Map<String,String> getCommonCodeNameMap(String codeKindId, java.util.Map<String,String> h) {
    	if (h==null) h = new java.util.HashMap<String,String>();
    	java.util.List<CommonCode> list = getCommonCodeList(codeKindId);
    	if (list!=null && list.size()>0) {
    		for (int i=0; i<list.size(); i++) {
    			CommonCode obj = list.get(i);
    			h.put(obj.getCodeId(), obj.getCodeName());
    		}
    	}    	
    	return h; 
    }

}
