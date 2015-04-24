package com.kangdainfo.common.model.dao;

import com.kangdainfo.persistence.BaseDao;
import com.kangdainfo.common.model.bo.*;

public interface CommonCodeKindDao extends BaseDao {
	 /**
     * Save CommonCodeKind to persistence layer.
     * @param obj CommonCodeKind
     */
    public void saveCommonCodeKind(CommonCodeKind obj);

    /**
     * Update CommonCodeKind to persistence layer.
     * @param obj CommonCodeKind
     */
    public void updateCommonCodeKind(CommonCodeKind obj);

    /**
     * Save or update CommonCodeKind to persistence layer.
     * @param obj CommonCodeKind
     */
    public void saveOrUpdateCommonCodeKind(CommonCodeKind obj);

    /**
     * Delete CommonCodeKind from persistence layer.
     * @param obj CommonCodeKind
     */
    public void deleteCommonCodeKind(CommonCodeKind obj);
    
    /**
     * Delete CommonCodeKind from persistence layer.
     * @param id Primary key id.
     */
    public void deleteCommonCodeKind(int id);

    /**
     * Load CommonCodeKind from persistence layer by given id.
     * @param id Primary key id.
     * @return CommonCodeKind
     */
    public CommonCodeKind loadCommonCodeKind(int id);
}
