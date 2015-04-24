package com.kangdainfo.common.model.dao;

import com.kangdainfo.persistence.BaseDao;
import com.kangdainfo.common.model.bo.*;

public interface CommonNewsDao extends BaseDao {
	 /**
     * Save CommonNews to persistence layer.
     * @param obj CommonNews
     */
    public void saveCommonNews(CommonNews obj);

    /**
     * Update CommonNews to persistence layer.
     * @param obj CommonNews
     */
    public void updateCommonNews(CommonNews obj);

    /**
     * Save or update CommonNews to persistence layer.
     * @param obj CommonNews
     */
    public void saveOrUpdateCommonNews(CommonNews obj);

    /**
     * Delete CommonNews from persistence layer.
     * @param obj CommonNews
     */
    public void deleteCommonNews(CommonNews obj);
    
    /**
     * Delete CommonNews from persistence layer.
     * @param id Primary key id.
     */
    public void deleteCommonNews(int id);

    /**
     * Load CommonNews from persistence layer by given id.
     * @param id Primary key id.
     * @return CommonNews
     */
    public CommonNews loadCommonNews(int id);
}
