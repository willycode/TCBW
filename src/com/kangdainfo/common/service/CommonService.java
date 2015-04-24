/**
 * Copyright (c) 2000-2005 Kangda, Inc All Rights Reserved.
 */
package com.kangdainfo.common.service;

import com.kangdainfo.common.model.bo.CommonUser;
import com.kangdainfo.common.model.dao.CommonAuthDao;
import com.kangdainfo.common.model.dao.CommonCodeDao;
import com.kangdainfo.common.model.dao.CommonCodeKindDao;
import com.kangdainfo.common.model.dao.CommonDepartmentDao;
import com.kangdainfo.common.model.dao.CommonDtreeDao;
import com.kangdainfo.common.model.dao.CommonGroupDao;
import com.kangdainfo.common.model.dao.CommonNewsDao;
import com.kangdainfo.common.model.dao.CommonUserDao;
import com.kangdainfo.common.model.dao.CommonUserRoleDao;

public interface CommonService extends com.kangdainfo.persistence.BaseDaoService {
	
	/**
	 * 取得子系統資訊
	 * @return
	 */
	public java.util.List<com.kangdainfo.common.model.bo.SubSystem> getSubSystems();
	public void setSubSystems(java.util.List<com.kangdainfo.common.model.bo.SubSystem> subSystems);
	public com.kangdainfo.common.model.bo.SubSystem getSubSystemById(int sid);
	
	/**
	 * 權限設定資料
	 * @return
	 */
	public CommonAuthDao getCommonAuthDao();	
	public void setCommonAuthDao(CommonAuthDao obj);
	
	
	/**
	 * 取得共用代碼類別資料
	 * @return
	 */
	public CommonCodeKindDao getCommonCodeKindDao();
	public void setCommonCodeKindDao(CommonCodeKindDao obj);
	
	/**
	 * 取得共用代碼資料
	 * @return
	 */
	public CommonCodeDao getCommonCodeDao();
	public void setCommonCodeDao(CommonCodeDao obj);
	
	
	/**
	 * 取得機關代碼資料
	 * @return
	 */
	public CommonDepartmentDao getCommonDepartmentDao();
	public void setCommonDepartmentDao(CommonDepartmentDao obj);
	
	/**
	 * 取得功能選單物件
	 * @return
	 */
	public CommonDtreeDao getCommonDtreeDao();
	public void setCommonDtreeDao(CommonDtreeDao obj);	

	/**
	 * 群組資料
	 * @return
	 */
	public CommonGroupDao getCommonGroupDao();
	public void setCommonGroupDao(CommonGroupDao commonGroupDao);
		
	/**
	 * 最新消息
	 * @return
	 */
	public CommonNewsDao getCommonNewsDao();
	public void setCommonNewsDao(CommonNewsDao obj);	
	
	/**
	 * 取得使用者資料
	 * @return
	 */
	public CommonUserDao getCommonUserDao();
	public void setCommonUserDao(CommonUserDao obj);
	
	/**
	 * 使用者群組和角色資料
	 * @return
	 */
	public CommonUserRoleDao getCommonUserRoleDao();
	public void setCommonUserRoleDao(CommonUserRoleDao obj);	
	

	/**
	 * 使用者忘記密碼時用
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public boolean updateAndResetUserPassword(CommonUser obj) throws Exception;	

	/**
	 * 登入時產生VisitLog
	 * @param obj
	 * @throws Exception
	 */
	public void saveCommonVisitLog(CommonUser obj, String ip, String logAction, String logStatus, String logMsg) throws Exception;
	
	
	public void sendEmail(String subject, String mailBody, java.util.List<javax.mail.internet.InternetAddress> mailList, java.util.List<String> attFilePathList);
	public void sendEmail(String subject, String mailBody, java.util.List<javax.mail.internet.InternetAddress> mailList, java.util.List<String> attFilePathList, boolean isHTML);
	
	/**
	 * 寫入訊息讀取紀錄
	 * @param obj
	 * @param newsId
	 * @throws Exception
	 */
	public void saveCommonNewsReadLog(CommonUser obj, int newsId) throws Exception;
	
}

