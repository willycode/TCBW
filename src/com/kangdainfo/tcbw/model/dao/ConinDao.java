package com.kangdainfo.tcbw.model.dao;

import com.kangdainfo.common.model.bo.CommonUser;
import com.kangdainfo.persistence.BaseDao;

public interface ConinDao extends BaseDao{

	public void updateConinCommonUser(CommonUser obj, int[] groupIds) throws Exception;
	
	public void saveCon2001Db(String systemType, Long formID, String applNo,String state, String procDesc, String userId);
	
	public void deleteCon0001Db(Long uploadId);
}
