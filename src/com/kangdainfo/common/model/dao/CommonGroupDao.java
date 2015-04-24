package com.kangdainfo.common.model.dao;

import com.kangdainfo.persistence.BaseDao;
import com.kangdainfo.common.model.bo.*;
import java.util.*;


public interface CommonGroupDao extends BaseDao {
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	//
	//	CommonGroup base function
	//
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    /**
     * Save CommonGroup to persistence layer.
     * @param obj CommonGroup
     */
    public void saveCommonGroup(CommonGroup obj);

    /**
     * Update CommonGroup to persistence layer.
     * @param obj CommonGroup
     */
    public void updateCommonGroup(CommonGroup obj);

    /**
     * Save or update CommonGroup to persistence layer.
     * @param obj CommonGroup
     */
    public void saveOrUpdateCommonGroup(CommonGroup obj);

    /**
     * Delete CommonGroup from persistence layer.
     * @param obj CommonGroup
     */
    public void deleteCommonGroup(CommonGroup obj);
    
    /**
     * Delete CommonGroup from persistence layer.
     * @param id Primary key id.
     */
    public void deleteCommonGroup(int id);

    public void deleteAllCommonGroups();
    
    
    /**
     * Load CommonGroup from persistence layer by given id.
     * @param id Primary key id.
     * @return CommonGroup
     */
    public CommonGroup loadCommonGroup(int id);

	/**
     * Load All CommonGroup(s) from persistence layer.
     * @return List of CommonGroup.
     */
    public List loadAllCommonGroups();

    /**
     * Load All CommonGroup(s) from persistence layer.
     * @param startAt Query start at.
     * @param size The maximum size you want get.
     * @param orderBy Order by which property.
     * @param isAscending Asceding or not.
     * @return List of CommonGroup.
     */
    public List loadAllCommonGroups(
        int startAt, int size, String orderBy, boolean isAscending);

    /**
     * Load All CommonGroup(s) count from persistence layer.
     * @return Count of all CommonGroup.
     */
    public int loadAllCommonGroupsCount();


	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	//
	//	load CommonGroup by Each Field
	//
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    /**
     * Load CommonGroup(s) from persistence layer by given id.
     * @param id Property id.
     * @return List of CommonGroup.
     */
    public List loadCommonGroupsById(java.lang.Integer id);

    /**
     * Load CommonGroup(s) from persistence layer by given id and range.
     * @param id Property id.
     * @param startAt Query start at.
     * @param size The maximum size you want get.
     * @param orderBy Order by which property.
     * @param isAscending Asceding or not.
     * @return List of CommonGroup.
     */
    public List loadCommonGroupsById(java.lang.Integer id,
        int startAt, int size, String orderBy, boolean isAscending);

    /**
     * Load CommonGroup(s) count from persistence layer by given id.
     * @param id Property id.
     * @return Count of all CommonGroup.
     */
    public int loadCommonGroupsCountById(java.lang.Integer id);

	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	//
	//	load CommonGroup by Each Field
	//
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    /**
     * Load CommonGroup(s) from persistence layer by given groupName.
     * @param groupName Property groupName.
     * @return List of CommonGroup.
     */
    public List loadCommonGroupsByGroupName(java.lang.String groupName);
}
