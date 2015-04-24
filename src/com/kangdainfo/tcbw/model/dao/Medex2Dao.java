package com.kangdainfo.tcbw.model.dao;

import com.kangdainfo.persistence.BaseDao;
import com.kangdainfo.tcbw.model.bo.Med5002Db;
import com.kangdainfo.tcbw.model.bo.Med5003Db;
import com.kangdainfo.tcbw.view.medex.MEDEX5102F;
import com.kangdainfo.tcbw.view.medex.MEDEX5201F;


public interface Medex2Dao extends BaseDao{
	
	public void updateByMedEX5102F(MEDEX5102F ref) throws Exception;
	public void saveOrUpdateMed5002Db(java.util.List<Med5002Db> saveList,java.util.List<Med5002Db> updateList,java.util.List<Med5002Db> deleteList) throws Exception; 
	public void saveOrUpdateMed5003Db(java.util.List<Med5003Db> saveList,java.util.List<Med5003Db> updateList,java.util.List<Med5003Db> deleteList) throws Exception; 
	public void updateSendByMEDEX5201F(MEDEX5201F ref)throws Exception;

	public void updateByAutoReUpdateMedEX5102F(MEDEX5102F ref,String auto) throws Exception ;
}
