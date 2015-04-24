package com.kangdainfo.common.model.dao;

import com.kangdainfo.persistence.BaseDao;
import com.kangdainfo.common.model.bo.*;

public interface CommonAuthDao extends BaseDao {
	 /**
     * Save CommonAuth to persistence layer.
     * @param obj CommonAuth
     */
    public void saveCommonAuth(CommonAuth obj);

    /**
     * Update CommonAuth to persistence layer.
     * @parcm obj CommonAuth
     */
    public void updateCommonAuth(CommonAuth obj);

    /**
     * Save or update CommonAuth to persistence layer.
     * @param obj CommonAuth
     */
    public void saveOrUpdateCommonAuth(CommonAuth obj);

    /**
     * Delete CommonAuth from persistence layer.
     * @param obj CommonAuth
     */
    public void deleteCommonAuth(CommonAuth obj);
    
    /**
     * Delete CommonAuth from persistence layer.
     * @param id Primary key id.
     */
    public void deleteCommonAuth(int id);

    /**
     * Load CommonAuth from persistence layer by given id.
     * @param id Primary key id.
     * @return CommonAuth
     */
    public CommonAuth loadCommonAuth(int id);
    public CommonAuth getCommonAuth(String groupID, String treeId);
    
	/**
	 * key = 功能代號<br>
	 * obj[0] = 權限 - int 型態<br>
	 * obj[1] = 功能選單物件 - CommonDtree<br> 
	 * @param sysId
	 * @param userRole
	 * @return
	 * @throws Exception
	 */    
    public java.util.Map<String, Object[]> getPermissionMap(String sysId, CommonUserRole userRole) throws Exception;
    
	/**
	 * key = 功能代號<br>
	 * obj[0] = 權限 - int 型態<br>
	 * obj[1] = 功能選單物件 - CommonDtree<br> 
	 * @param sysId
	 * @param userRole
	 * @return
	 * @throws Exception
	 */    
    public java.util.Map<String, Object[]> getPermissionMap(CommonAuth[] auth) throws Exception;
    
    /**
     * 一個使用者可設定多個群組時用 : 取得所有群組後, 再以比較大的權限做排序
     * @param userRole
     * @param treeId
     * @return
     */
    public CommonAuth getCommonAuth(CommonUserRole userRole, String treeId);
    
    
	/** 取得某個群組所擁有的權限資料 **/
	public CommonAuth[] getPermission(String groupID) throws Exception;	
	public CommonAuth[] getPermission(String sysId, CommonUserRole userRole) throws Exception;
	public CommonDtree[] getPermissionCommonDtree(CommonAuth[] permission) throws Exception;
	public CommonDtree[] getPermissionCommonDtree(Integer groupID) throws Exception; 
	
	/**
	 * 一個使用者可設定多個群組時用
	 * @param userRole
	 * @return
	 * @throws Exception
	 */
	public CommonDtree[] getPermissionCommonDtree(String sysId, CommonUserRole userRole) throws Exception;
	
	
	/** 更新權限資料**/
	public void updateAuth(String optype,String groupId, String[] treeId, String authType, String editId) throws Exception;
	
	/**
  	 * <br>
  	 * <br>目的：建立權限維護作業中有checkbox的選單 + 權限CSS顏色
  	 * <br>  	
	 * @param treeID
	 * @param treeName
	 * @param checkboxName
	 * @param checkboxPrefix
	 * @param jsFunctionName
	 * @param sysId : 請參考 util.lgt.Constants
	 * @param groupID
	 * @param userRole
	 * @param bIncludeAll
	 * @param bUrl
	 * @param bRootCheckBox
	 * @return 組合後的javascript = String
	 * @throws Exception
	 */
	public String buildCheckBoxTree(String treeID, String treeName, String checkboxName, String checkboxPrefix, String jsFunctionName, String sysId, String groupID, CommonUserRole userRole, boolean bIncludeAll, boolean bUrl, boolean bRootCheckBox) throws Exception;
	
	/**
  	 * <br>
  	 * <br>目的：組出某個群組所擁有的權限樹
  	 * <br>參數：treeID, treeName, sysId, CommonRole
  	 * <br>傳回：組合後的javascript = String
  	 * <br>舉例:buildAuthorizeMenu("d","功能選單","-11",CommonRole)
  	 *  
	 * @param treeID
	 * @param treeName
	 * @param sysId : 請參考 util.lgt.Constants
	 * @param CommonRole
	 * @return
	 */
	public String buildAuthorizeMenu(String treeID, String treeName, String sysId, CommonUserRole userRole);
	public String buildAuthorizeMenu(String treeID, String treeName, String sysId, CommonUserRole userRole, String isFixedLink);
	public String buildAuthorizeMenu(String treeID, String treeName, String sysId, String groupID, String isAdmin);	
	
	
	public java.util.Map<String, CommonDtree> getPermissionCommonDtreeMap(CommonAuth[] permission) throws Exception;
	public java.util.Map<String, CommonDtree> getPermissionCommonDtreeMap(String sysId, CommonUserRole userRole) throws Exception;
	    
}
