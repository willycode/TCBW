package com.kangdainfo.common.model.dao;

import com.kangdainfo.common.model.bo.CommonDtree;
import com.kangdainfo.persistence.BaseDao;

public interface CommonDtreeDao extends BaseDao {
	public void saveDtreeNode(CommonDtree obj) throws Exception;
	public void updateDtreeNode(CommonDtree obj) throws Exception;
	public void deleteDtreeNode(Long id) throws Exception;	
	
	/**
	 * 取得某個子系統(e.g. 概預算為系統編號為1,總會系統為2)的所有Menu節點  
	 * @param sysid
	 * @return TreeNode[]
	 * @throws Exception
	 */
	public CommonDtree[] getAllNode(int sysid) throws Exception;
	
	/**
	 * 取得某個子系統(e.g. 概預算為系統編號為1,總會系統為2)的第一層Menu節點  
	 * @param sysid
	 * @return TreeNode[]
	 * @throws Exception
	 */	
	public CommonDtree[] getRootNode(int sysid) throws Exception;
	
	/**
	 * 用id取得某一節點
	 * @param id
	 * @return TreeNode
	 * @throws Exception
	 */
	public CommonDtree getNode(Long id) throws Exception;
		
	public CommonDtree[] getChildNodes(Long ids[]) throws Exception;	
	public CommonDtree[] getParentNodes(Long ids[]) throws Exception;
	public void initial() throws Exception;
	public void close() throws Exception;	
}
