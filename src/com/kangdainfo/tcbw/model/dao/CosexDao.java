package com.kangdainfo.tcbw.model.dao;

import com.kangdainfo.persistence.BaseDao;
import com.kangdainfo.tcbw.model.bo.Cos4001Db;
import com.kangdainfo.tcbw.model.bo.Cos4002Db;
import com.kangdainfo.tcbw.view.cosex.COSEX0102F;
import com.kangdainfo.tcbw.view.cosex.COSEX0104F;
import com.kangdainfo.tcbw.view.cosex.COSEX0201F;

public interface CosexDao extends BaseDao {

	public void updateDoCopyCos4001Db(Cos4001Db obj, String userId, String yyymmdd, String hhmmss) throws Exception;
	
	public void insertByCOSEX0102F(COSEX0102F ref) throws Exception;
	
	public void updateByCOSEX0102F(COSEX0102F ref, boolean isAutoSave) throws Exception;
	
	public void deleteByCOSEX0102F(COSEX0102F ref) throws Exception;
	
	public void updateByAutoCOSEX0102F(COSEX0102F ref) throws Exception;
	
	public void updateCos4004Db(Cos4002Db cos4002Db, javax.servlet.ServletRequest httpRequest, String[] COS4004DbRow, String[] arrCOS4004DbFieldName, String userId, String yyymmdd, String hhmmss) throws Exception;
	
	public void updateCos4005Db(Cos4002Db cos4002Db, javax.servlet.ServletRequest httpRequest, String[] COS4005DbRow, String[] arrCOS4005DbFieldName, String userId, String yyymmdd, String hhmmss) throws Exception;
	
	public void updateCon0001Db(javax.servlet.ServletRequest httpRequest, Long uploadId, String fileType, String fileStoreType, String[] fileRow, String dbName, String[] fileField, String userId, String yyymmdd, String hhmmss)throws Exception;
	
	public void updateCaseReply(COSEX0104F ref) throws Exception;
	
	public void updateSendByCOSEX0201F(COSEX0201F ref) throws Exception;
}
