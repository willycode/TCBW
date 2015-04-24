package com.kangdainfo.common.model.dao;

import com.kangdainfo.persistence.BaseDao;
import com.kangdainfo.common.model.bo.*;

public interface CommonUserDao extends BaseDao {
	 /**
     * Save CommonUser to persistence layer.
     * @param obj CommonUser
     */
    public void saveCommonUser(CommonUser obj) throws Exception;
    
    /**
     * 一個使用者可屬於多個角色/群組時用
     * @param obj
     * @param groupIds
     * @throws Exception
     */
    public void saveCommonUser(CommonUser obj, int[] groupIds) throws Exception;

    /**
     * Update CommonUser to persistence layer.
     * @param obj CommonUser
     */
    public void updateCommonUser(CommonUser obj) throws Exception;
    
    /**
     * 一個使用者可屬於多個角色/群組時用
     * @param obj
     * @param groupIds
     * @throws Exception
     */
    public void updateCommonUser(CommonUser obj, int[] groupIds) throws Exception;

    /**
     * Save or update CommonUser to persistence layer.
     * @param obj CommonUser
     */
    public void saveOrUpdateCommonUser(CommonUser obj) throws Exception;

    /**
     * Delete CommonUser from persistence layer.
     * @param obj CommonUser
     */
    public void deleteCommonUser(CommonUser obj) throws Exception;
    
    /**
     * Delete CommonUser from persistence layer.
     * @param id Primary key id.
     */
    public void deleteCommonUser(int id) throws Exception;

    /**
     * Load CommonUser from persistence layer by given id.
     * @param id Primary key id.
     * @return CommonUser
     */
    public CommonUser loadCommonUser(int id);
    
    public CommonUser getCommonUserByUserId(String userId);
    
    public CommonUser saveOrGetVerifiedUser(String userId, String userPwd) throws Exception;
    
    public CommonUser reLoginGetVerifiedUser(String userId) throws Exception;
    
    public CommonUser saveOrGetVerifiedUser(String userId) throws Exception;
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
	 * 取得某個子系統(e.g. 概預算為系統編號為1,總會系統為2)的所有Menu節點  
	 * @return TreeNode[]
	 * @throws Exception
	 */
	public CommonUser[] getAllNode() throws Exception;
	public CommonUser[] getAllNode(String groupID) throws Exception;
	/** 更新群組資料**/
	public void updateUserGroup(String optype,String groupId, String[] treeId, String authType, String editId) throws Exception;
}
