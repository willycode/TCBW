package com.kangdainfo.tcbw.model.dao;

import com.kangdainfo.persistence.BaseDao;
import com.kangdainfo.tcbw.model.bo.Hfr4001Db;
import com.kangdainfo.tcbw.model.bo.Hfr4002Db;
import com.kangdainfo.tcbw.model.bo.Hfr4003Db;
import com.kangdainfo.tcbw.model.bo.Hfr4004Db;
import com.kangdainfo.tcbw.view.hfrex.HFREX0102F;
import com.kangdainfo.tcbw.view.hfrex.HFREX0104F;
import com.kangdainfo.tcbw.view.hfrex.HFREX0201F;

public interface HfrexDao extends BaseDao{
	
	public void updateDoCopyHfr0001Db(Hfr4001Db obj, String userId, String yyymmdd, String hhmmss) throws Exception;
	
	public void insertByHFREX0102F(HFREX0102F ref) throws Exception;
	
	public void updateByHFREX0102F(HFREX0102F ref, boolean isAutoSave) throws Exception;
	
	public void updateHfr4002Db( Hfr4001Db obj, java.util.Set hfr4002Dbs, java.util.List<Hfr4002Db> dList, String type, 
								 javax.servlet.ServletRequest httpRequest, String[] HFR4002DbRow, String[] arrHFR4002DbFieldName, 
								 String userId, String yyymmdd, String hhmmss ) throws Exception;
	
	public void deleteByHFREX0102F(HFREX0102F ref) throws Exception;
	
	public void insertByHFREX0104F(HFREX0104F ref) throws Exception;
	
	public void updateByHFREX0104F(HFREX0104F ref, boolean isAutoSave) throws Exception;
	
	
	public void updateHfr4003Db( Hfr4001Db obj, java.util.Set hfr4003Dbs, java.util.List<Hfr4003Db> dList, javax.servlet.ServletRequest httpRequest, 
								 String[] HFR4003DbRow, String[] arrHFR4003DbFieldName, 
								 String userId, String yyymmdd, String hhmmss ) throws Exception;
	
	public void updateHfr4004Db( Hfr4001Db obj, java.util.Set hfr4004Dbs, java.util.List<Hfr4004Db> dList, javax.servlet.ServletRequest httpRequest, 
			 					 String[] HFR4004DbRow, String[] arrHFR4004DbFieldName, 
			 					 String userId, String yyymmdd, String hhmmss ) throws Exception;
	
	public void updateDrugHfr4002Db( Hfr4001Db obj, java.util.Set hfr4002Dbs, java.util.List<Hfr4002Db> dList, String type, 
			 					 	 javax.servlet.ServletRequest httpRequest, String[] HFR4002DbRow, String[] arrHFR4002DbFieldName, 
			 					 	 String userId, String yyymmdd, String hhmmss ) throws Exception;
	
	public void deleteByHFREX0104F(HFREX0104F ref) throws Exception;
	
	public void updateCon0001Db(javax.servlet.ServletRequest httpRequest, Long uploadId, String dbName, String fileType, String fileStoreType, String[] fileRow, String[] fileField, String userId, String yyymmdd, String hhmmss)throws Exception;
	
	public void updateSendApplNoByHFREX0201F(HFREX0201F ref)throws Exception;
}
