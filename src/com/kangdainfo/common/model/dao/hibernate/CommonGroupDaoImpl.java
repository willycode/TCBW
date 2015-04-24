package com.kangdainfo.common.model.dao.hibernate;

import java.util.*;

import com.kangdainfo.persistence.hibernate3.BaseDaoImpl;
import com.kangdainfo.common.model.bo.*;
import com.kangdainfo.common.model.dao.*;

public class CommonGroupDaoImpl extends BaseDaoImpl implements CommonGroupDao {
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	//
	//	CommonGroup base function
	//
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public void saveCommonGroup(CommonGroup obj) {
    	save(obj);
    }

    public void updateCommonGroup(CommonGroup obj) {
	    update(obj);
    }

    public void saveOrUpdateCommonGroup(CommonGroup obj) {
        saveOrUpdate(obj);
    }
	
	public void deleteCommonGroup(CommonGroup obj) {
		delete(obj);
    }
    
    public void deleteCommonGroup(int id) {
	    delete(CommonGroup.class,id);
    }
    
    public void deleteAllCommonGroups() {
    	deleteAll(CommonGroup.class);
    }
    
    public CommonGroup loadCommonGroup(int id) {
    	return (CommonGroup) load(CommonGroup.class,id);
    }

    public List loadAllCommonGroups() {
    	return loadAll(CommonGroup.class);
	}

    public List loadAllCommonGroups(
        final int startAt, final int size, final String orderBy, final boolean isAscending) {
        return loadAll(CommonGroup.class,startAt,size,orderBy,isAscending);
	}

    public int loadAllCommonGroupsCount() {
    	return loadAllCount(CommonGroup.class);
	}



 

	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	//
	//	get CommonGroup by Each Field
	//
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public List loadCommonGroupsById(final java.lang.Integer id) {
    	return load(CommonGroup.class, CommonGroup.FieldNames.id,  toObject(id));
    }
    		
    public List loadCommonGroupsById(final java.lang.Integer id,
        final int startAt, final int size, final String orderBy, final boolean isAscending) {
        
        return load(CommonGroup.class, CommonGroup.FieldNames.id,  toObject(id),
        startAt,size,orderBy,isAscending);
    }

    public int loadCommonGroupsCountById(final java.lang.Integer id) {
    	return loadCount(CommonGroup.class, CommonGroup.FieldNames.id,  toObject(id));
    }


	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	//
	//	get CommonGroup by Each Field
	//
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public List loadCommonGroupsByGroupName(final java.lang.String groupName) {
    	return load(CommonGroup.class, CommonGroup.FieldNames.groupName,  toObject(groupName));
    }
    		
    public List loadCommonGroupsByGroupName(final java.lang.String groupName,
        final int startAt, final int size, final String orderBy, final boolean isAscending) {
        
        return load(CommonGroup.class, CommonGroup.FieldNames.groupName,  toObject(groupName),
        startAt,size,orderBy,isAscending);
    }

    public int loadCommonGroupsCountByGroupName(final java.lang.String groupName) {
    	return loadCount(CommonGroup.class, CommonGroup.FieldNames.groupName,  toObject(groupName));
    }
}
