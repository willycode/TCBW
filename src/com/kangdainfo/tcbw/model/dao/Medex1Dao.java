package com.kangdainfo.tcbw.model.dao;

import com.kangdainfo.persistence.BaseDao;
import com.kangdainfo.tcbw.view.medex.MEDEX0104F;
import com.kangdainfo.tcbw.view.medex.MEDEX0201F;



public interface Medex1Dao extends BaseDao
{
	
	public void updateByMedEX0104F(MEDEX0104F ref) throws Exception;
	public void updateSendByMEDEX0201F(MEDEX0201F ref) throws Exception;
	
	public void updateByAutoReUpdateMedEX0104F(MEDEX0104F ref,String auto) throws Exception;
	

}
