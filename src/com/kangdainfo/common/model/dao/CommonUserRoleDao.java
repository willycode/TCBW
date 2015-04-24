package com.kangdainfo.common.model.dao;

import com.kangdainfo.persistence.BaseDao;
import com.kangdainfo.common.model.bo.*;

public interface CommonUserRoleDao extends BaseDao {
	 /**
     * Save CommonUserRole to persistence layer.
     * @param obj CommonUserRole
     */
    public void saveCommonUserRole(CommonUserRole obj);

    /**
     * Update CommonUserRole to persistence layer.
     * @param obj CommonUserRole
     */
    public void updateCommonUserRole(CommonUserRole obj);

    /**
     * Save or update CommonUserRole to persistence layer.
     * @param obj CommonUserRole
     */
    public void saveOrUpdateCommonUserRole(CommonUserRole obj);

    /**
     * Delete CommonUserRole from persistence layer.
     * @param obj CommonUserRole
     */
    public void deleteCommonUserRole(CommonUserRole obj);
    
    /**
     * Delete CommonUserRole from persistence layer.
     * @param id Primary key id.
     */
    public void deleteCommonUserRole(int id);

    /**
     * Load CommonUserRole from persistence layer by given id.
     * @param id Primary key id.
     * @return CommonUserRole
     */
    public CommonUserRole loadCommonUserRole(int id);
}
