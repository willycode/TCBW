package com.kangdainfo.common.model.dao;

import com.kangdainfo.persistence.*;
import com.kangdainfo.common.model.bo.*;

public interface CommonDepartmentDao extends BaseDao {
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	//
	//	CommonDepartment base function
	//
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    /**
     * Save CommonDepartment to persistence layer.
     * @param obj CommonDepartment
     */
    public void saveCommonDepartment(CommonDepartment obj);

    /**
     * Update CommonDepartment to persistence layer.
     * @param obj CommonDepartment
     */
    public void updateCommonDepartment(CommonDepartment obj);

    /**
     * Save or update CommonDepartment to persistence layer.
     * @param obj CommonDepartment
     */
    public void saveOrUpdateCommonDepartment(CommonDepartment obj);

    /**
     * Delete CommonDepartment from persistence layer.
     * @param obj CommonDepartment
     */
    public void deleteCommonDepartment(CommonDepartment obj);
    
    /**
     * Delete CommonDepartment from persistence layer.
     * @param id Primary key id.
     */
    public void deleteCommonDepartment(int id);

    public void deleteAllCommonDepartments();
    
	/**
	 * 當傳入的deptMap為null值是,回傳值為HashMap型態<br>
	 * 若需要回傳排序的map,則請new一個並其傳入<br>
	 * @param deptMap
	 * @param useFullCodeKey = 預設的map key是shortCode, 若要使用fullCode則請傳入true用
	 * @return
	 */
    public java.util.Map<String, CommonDepartment> getDeptMap(java.util.Map<String, CommonDepartment> deptMap, boolean useFullCodeKey);
    
    /**
     * 取得機關名稱集合<br>
     * @param deptMap = 當傳入的deptMap為null值是,回傳值為HashMap型態<br>
     * @param useFullCode = 預設的map key是shortCode, 若要使用fullCode則請傳入true用
     * @return
     */
    public java.util.Map<String, String> getDeptNameMap(java.util.Map<String, String> deptMap, boolean useFullCode);
    
    /*
     * 
     */
    public java.util.Map<String, CommonDepartment> getDeptOrderByShortCode(java.util.Map<String, CommonDepartment> deptMap, boolean useFullCodeKey);
    
	/**
	 * 刪除某個機關及其下所有附屬機關
	 * @param id
	 * @throws Exception
	 */
	public void deleteCommonDepartmentNode(int id) throws Exception;	
	
	/**
	 * 取得所有機關  
	 * 
	 * @return CommonDepartment[]
	 * @throws Exception
	 */
	public CommonDepartment[] getAllNode() throws Exception;
	
	/**
	 * 取得某個機關的所有下屬機關
	 * @param id
	 * @return CommonDepartment[]
	 * @throws Exception
	 */	
	public CommonDepartment[] getChildNodes(int id) throws Exception;
	
	/**
	 * <br>取得某個機關的所有下屬機關
	 * <br>includeAllDescendant為false, 則只取下一層
	 * 
	 * @param id
	 * @param includeAllDescendant
	 * @return
	 * @throws Exception
	 */
	public CommonDepartment[] getChildNodes(int id, boolean includeAllDescendant) throws Exception;
	
	
	/**
	 * 取得某個機關的所有上層機關 
	 * @param id
	 * @return CommonDepartment[]
	 * @throws Exception
	 */
	public CommonDepartment[] getParentNodes(int id) throws Exception;
	
	/**
	 * <br>取得某個機關的所有上層機關
	 * <br>includeAllAscendant為false, 則只取到上一層  
	 * @param id
	 * @param includeAllAscendant
	 * @return
	 * @throws Exception
	 */
	public CommonDepartment[] getParentNodes(int id, boolean includeAllAscendant) throws Exception;
	
	
	/**
	 * 用id取得機關資料
	 * @param id
	 * @return CommonDepartment
	 * @throws Exception
	 */
	public CommonDepartment getCommonDepartment(int id) throws Exception;
	
	/**
	 * 用fullCode取得機關資料
	 * @param fullCode
	 * @return CommonDepartment
	 * @throws Exception
	 */
	public CommonDepartment getCommonDepartment(String fullCode) throws Exception;	
	
	/**
	 * 用fullCode[]取得機關資料
	 * @param List
	 * @return
	 */
	public java.util.List loadDeptsByFullCodes(String[] depts);	    
	
	/**
	 * 機關代碼匯入作業 (由人事行政局PEMIS2000)
	 * @param srcFile = TXT或XLS
	 * @return
	 * @throws Exception
	 */
	public int importCommonDepartmentFromCPA(java.io.File srcFile) throws Exception;
	
	/**
	 * 以簡碼取得發證單位
	 * @param shortCode
	 * @return
	 */
	public CommonDepartment getIssueCommonDepartment(String shortCode);
	
}
