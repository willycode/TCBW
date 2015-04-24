package com.kangdainfo.tcbw.model.dao;

import com.kangdainfo.persistence.BaseDao;
import com.kangdainfo.tcbw.model.bo.Cos0001Db;
import com.kangdainfo.tcbw.view.comple.COMPLE1002F;
import com.kangdainfo.tcbw.view.cosin.COSIN0302F;
import com.kangdainfo.tcbw.view.cosin.COSIN0402F;
import com.kangdainfo.tcbw.view.cosin.COSIN0502F;
import com.kangdainfo.tcbw.view.cosin.COSIN0602F;
import com.kangdainfo.tcbw.view.cosin.COSIN0702F;
import com.kangdainfo.tcbw.view.cosin.COSIN0802F;
import com.kangdainfo.tcbw.view.cosin.COSIN0902F;
import com.kangdainfo.tcbw.view.cosin.COSIN1002F;

public interface CosinDao extends BaseDao {

	public void insertByCOSIN0302F(COSIN0302F ref) throws Exception;
	
	public void updateByCOSIN0302F(COSIN0302F ref) throws Exception;
	
	public void deleteByCOSIN0302F(COSIN0302F ref) throws Exception;
	
	public void updateByCOSIN0402F(COSIN0402F ref) throws Exception;
	
	public void updateByCOSIN0402FDoUnAcceptCase(COSIN0402F ref) throws Exception;
	
	public void updateByCOSIN0502F(COSIN0502F ref) throws Exception;
	
	public void updateByCOSIN0602F(COSIN0602F ref) throws Exception;
	
	public void updateByCOSIN0602FAdditionCase(COSIN0602F ref) throws Exception;
	
	public void updateByCOSIN0602FReDisCase(COSIN0602F ref) throws Exception;
	
	public void updateByCOSIN0702F(COSIN0702F ref) throws Exception;
	
	public void updateByCOSIN0802F(COSIN0802F ref) throws Exception;
	
	public void updateByCOSIN0902F(COSIN0902F ref) throws Exception;
	
	public void updateByCOSIN1002F(COSIN1002F ref) throws Exception;
	
	public void updateByCOMPLE1002F(COMPLE1002F ref) throws Exception;
	
	public void updateAdditionalCase(Cos0001Db obj, String userId) throws Exception;
	
	public void updateCos4001DbStatus(Cos0001Db obj) throws Exception;
	
	
	
}
