package com.kangdainfo.tcbw.model.dao;

import com.kangdainfo.persistence.BaseDao;
import com.kangdainfo.tcbw.model.bo.Hfr0001Db;
import com.kangdainfo.tcbw.model.bo.Hfr0002Db;
import com.kangdainfo.tcbw.model.bo.Hfr0003Db;
import com.kangdainfo.tcbw.model.bo.Hfr0004Db;
import com.kangdainfo.tcbw.view.hfrin.HFRIN0402F;
import com.kangdainfo.tcbw.view.hfrin.HFRIN0403F;
import com.kangdainfo.tcbw.view.hfrin.HFRIN0502F;
import com.kangdainfo.tcbw.view.hfrin.HFRIN0503F;
import com.kangdainfo.tcbw.view.hfrin.HFRIN0602F;
import com.kangdainfo.tcbw.view.hfrin.HFRIN0603F;
import com.kangdainfo.tcbw.view.hfrin.HFRIN0702F;
import com.kangdainfo.tcbw.view.hfrin.HFRIN0802F;

public interface HfrinDao extends BaseDao {

	public void insertByHFRIN0402F(HFRIN0402F ref) throws Exception;
	
	public void updateByHFRIN0402F(HFRIN0402F ref) throws Exception;
	
	public void deleteByHFRIN0402F(HFRIN0402F ref) throws Exception;
	
	public void insertByHFRIN0403F(HFRIN0403F ref) throws Exception;
	
	public void updateByHFRIN0403F(HFRIN0403F ref) throws Exception;
	
	public void deleteByHFRIN0403F(HFRIN0403F ref) throws Exception;
	
	public void updateByHFRIN0502F(HFRIN0502F ref) throws Exception;
	
	public void updateHfr0002Db( Hfr0001Db obj, java.util.Set hfr0002Dbs, java.util.List<Hfr0002Db> dList, String type, 
			 					 javax.servlet.ServletRequest httpRequest, String[] HFR0002DbRow, String[] arrHFR0002DbFieldName, 
			 					 String userId, String yyymmdd, String hhmmss ) throws Exception;
	
	public void updateHFR4001DbStatus(Hfr0001Db obj) throws Exception ;
	
	public void generateHFRCaseSendEmail(Hfr0001Db obj, String logName) throws Exception;
	
	public void updateByHFRIN0503F(HFRIN0503F ref) throws Exception;
	
	public void updateDrugHfr0002Db( Hfr0001Db obj, java.util.Set hfr0002Dbs, java.util.List<Hfr0002Db> dList, String type, 
				 					 javax.servlet.ServletRequest httpRequest, String[] HFR0002DbRow, String[] arrHFR0002DbFieldName, 
				 					 String userId, String yyymmdd, String hhmmss ) throws Exception;
	
	public void updateHfr0003Db( Hfr0001Db obj, java.util.Set hfr0003Dbs, java.util.List<Hfr0003Db> dList, javax.servlet.ServletRequest httpRequest, 
			 					 String[] HFR0003DbRow, String[] arrHFR0003DbFieldName, 
			 					 String userId, String yyymmdd, String hhmmss ) throws Exception;

	public void updateHfr0004Db( Hfr0001Db obj, java.util.Set hfr0004Dbs, java.util.List<Hfr0004Db> dList, javax.servlet.ServletRequest httpRequest, 
			 					 String[] HFR0004DbRow, String[] arrHFR0004DbFieldName, 
			 					 String userId, String yyymmdd, String hhmmss ) throws Exception;

	public void updateByHFRIN0602F(HFRIN0602F ref) throws Exception;
	
	public void updateByHFRIN0603F(HFRIN0603F ref) throws Exception;
	
	public void updateByHFRIN0702F(HFRIN0702F ref) throws Exception;
	
	public void updateByHFRIN0802F(HFRIN0802F ref) throws Exception;
	
	
}
