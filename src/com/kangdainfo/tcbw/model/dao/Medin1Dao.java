package com.kangdainfo.tcbw.model.dao;

import java.util.List;

import com.kangdainfo.persistence.BaseDao;
import com.kangdainfo.tcbw.model.bo.Med0001Db;
import com.kangdainfo.tcbw.model.bo.Med1005Db;
import com.kangdainfo.tcbw.model.bo.Med1006Db;
import com.kangdainfo.tcbw.view.medin.MEDIN0101F;
import com.kangdainfo.tcbw.view.medin.MEDIN0202F;
import com.kangdainfo.tcbw.view.medin.MEDIN0302F;
import com.kangdainfo.tcbw.view.medin.MEDIN0402F;
import com.kangdainfo.tcbw.view.medin.MEDIN0501F;
import com.kangdainfo.tcbw.view.medin.MEDIN0702F;
import com.kangdainfo.tcbw.view.medin.MEDIN0802F;
import com.kangdainfo.tcbw.view.medin.MEDIN0902F;
import com.kangdainfo.tcbw.view.medin.MEDIN0903F;




public interface Medin1Dao extends BaseDao
{
	
    //-醫療器材不良事件通報-----------------------------------------------------------------------------------	
	public void updateByMedIN0101F(MEDIN0101F ref) throws Exception;
	
	public void updateByMedIN0202F(MEDIN0202F ref) throws Exception;
	public void updateByBackPiecesMedIN0202F(MEDIN0202F ref) throws Exception;
	public void updateByDismissalMedIN0202F(MEDIN0202F ref) throws Exception;
	public void updateByCorrectionMedIN0202F(MEDIN0202F ref) throws Exception;
	public void updateByAddocumentsMedIN0202F(MEDIN0202F ref) throws Exception;
	
	public void updateDoCopyMed0001Db(Med0001Db obj) throws Exception;
	
	public void updateBydoTransferMedIN0302F(MEDIN0302F ref) throws Exception;
	public void updateBydoCompletedMedIN0402F(MEDIN0402F ref) throws Exception;
	public void updateBydoCompletedConfirmedMedIN0402F(MEDIN0402F ref) throws Exception;
	public void updateBydoCompletedMedIN0702F(MEDIN0702F ref) throws Exception;
	public void updateBydoCompletedConfirmedMedIN0702F(MEDIN0702F ref) throws Exception;
	public void updateBydoCompletedMedIN0802F(MEDIN0802F ref) throws Exception;
	public void updateBydoCompletedConfirmedMedIN0802F(MEDIN0802F ref) throws Exception;
	public void updateBydoReCalibrationMedIN0902F(MEDIN0902F ref) throws Exception;
	public void updateByenforceCloseMedIN0903F(MEDIN0903F ref) throws Exception;

	public void updateSendByMEDIN0501F(MEDIN0501F ref,String sb) throws Exception;
	
	public List<Med1005Db> queryMED1005DB() throws Exception;
	public List<Med1006Db> queryMED1006DB() throws Exception;
}
