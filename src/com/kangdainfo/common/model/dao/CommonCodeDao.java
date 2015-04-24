package com.kangdainfo.common.model.dao;

import com.kangdainfo.persistence.BaseDao;
import com.kangdainfo.common.model.bo.*;

public interface CommonCodeDao extends BaseDao {
	 /**
     * Save CommonCode to persistence layer.
     * @param obj CommonCode
     */
    public void saveCommonCode(CommonCode obj);

    /**
     * Update CommonCode to persistence layer.
     * @param obj CommonCode
     */
    public void updateCommonCode(CommonCode obj);

    /**
     * Save or update CommonCode to persistence layer.
     * @param obj CommonCode
     */
    public void saveOrUpdateCommonCode(CommonCode obj);

    /**
     * Delete CommonCode from persistence layer.
     * @param obj CommonCode
     */
    public void deleteCommonCode(CommonCode obj);
    
    /**
     * Delete CommonCode from persistence layer.
     * @param id Primary key id.
     */
    public void deleteCommonCode(int id);

    /**
     * Load CommonCode from persistence layer by given id.
     * @param id Primary key id.
     * @return CommonCode
     */
    public CommonCode loadCommonCode(int id);
    
    /**
     * Load CommonCode from persistence layer by given codeKindId, codeId.
     * @param codeKindId
     * @param codeId
     * @return
     */
    public CommonCode getCommonCode(String codeKindId, String codeId);
    
    /**
     * 取類別, 代碼取得代碼名稱
     * @param codeKindId
     * @param codeId
     * @return
     */
    public String getCommonCodeName(String codeKindId, String codeId);
    
    /**
     * 以codeKindId取得一類的代碼List
     * @param codeKindId
     * @return
     */
    public java.util.List<CommonCode> getCommonCodeList(String codeKindId);
    
    /**
     * 以codeKindId取得某一類的代碼Map<br>
     * 當傳入的h為null值是,回傳值為HashMap型態<br>
     * 若需要回傳排序的map,則請new一個並其傳入<br>
     * @param codeKindId
     * @param h
     * @return
     */
    public java.util.Map<String, CommonCode> getCommonCodeMap(String codeKindId, java.util.Map<String, CommonCode> h);
    
    
    public java.util.Map<String,String> getCommonCodeNameMap(String codeKindId, java.util.Map<String,String> h) ;
}
