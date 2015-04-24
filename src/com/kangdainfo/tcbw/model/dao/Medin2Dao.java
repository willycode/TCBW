package com.kangdainfo.tcbw.model.dao;

import com.kangdainfo.persistence.BaseDao;
import com.kangdainfo.tcbw.model.bo.Med0001Db;
import com.kangdainfo.tcbw.model.bo.Med2002Db;
import com.kangdainfo.tcbw.model.bo.Med2003Db;
import com.kangdainfo.tcbw.model.bo.Med5002Db;
import com.kangdainfo.tcbw.model.bo.Med5003Db;
import com.kangdainfo.tcbw.view.hfrex.HFREX0103F;
import com.kangdainfo.tcbw.view.hfrex.HFREX0104F;
import com.kangdainfo.tcbw.view.hfrex.HFREX0105F;
import com.kangdainfo.tcbw.view.medex.MEDEX5201F;
//import com.kangdainfo.tcbw.view.medin.MEDIN5102F;
import com.kangdainfo.tcbw.view.medin.MEDIN0202F;
import com.kangdainfo.tcbw.view.medin.MEDIN5101F;
import com.kangdainfo.tcbw.view.medin.MEDIN5202F;



public interface Medin2Dao extends BaseDao
{
	
	public void updateByMedIN5101F(MEDIN5101F ref) throws Exception;
	
	public void saveOrUpdateMed2002Db(java.util.List<Med2002Db> saveList,java.util.List<Med2002Db> updateList,java.util.List<Med2002Db> deleteList) throws Exception; 
	public void saveOrUpdateMed2003Db(java.util.List<Med2003Db> saveList,java.util.List<Med2003Db> updateList,java.util.List<Med2003Db> deleteList) throws Exception; 
	
	public void updateSendApplNoByMEDEX5201F(MEDEX5201F ref)throws Exception;
	
	public void updateByMedIN5202F(MEDIN5202F ref) throws Exception;
	public void updateByBackPiecesMedIN5202F(MEDIN5202F ref) throws Exception;
	public void updateByDismissalMedIN5202F(MEDIN5202F ref) throws Exception;
	public void updateByCorrectionMedIN5202F(MEDIN5202F ref) throws Exception;
	public void updateByAddocumentsMedIN5202F(MEDIN5202F ref) throws Exception;
	public void updateByDeleteCon0003DbMedIN5202F(MEDIN5202F ref) throws Exception;

	/**
	 * 統計醫材不良反應區間中的案件
	 * @param medPermit
	 * @param medPermitNumber
	 * @param medCname
	 * @throws Exception
	 */
	public int getStatEventKind1(String medPermit,String medPermitNumber,String medCname,String startDate,String endDate) throws Exception;
	/**
	 * 統計醫材不良品區間中的案件
	 * @param medPermit
	 * @param medPermitNumber
	 * @param medCname
	 * @throws Exception
	 */
	public int getStatEventKind2(String medPermit,String medPermitNumber,String medCname,String startDate,String endDate) throws Exception;
}
