package com.kangdainfo.tcbw.model.dao;

import java.util.List;

import com.kangdainfo.persistence.BaseDao;
import com.kangdainfo.tcbw.model.bo.Drg4001Db;
import com.kangdainfo.tcbw.model.bo.Drg5002Db;
import com.kangdainfo.tcbw.model.bo.Drg6001Db;
import com.kangdainfo.tcbw.view.drgex.DRGEX0102F;
import com.kangdainfo.tcbw.view.drgex.DRGEX0105F;
import com.kangdainfo.tcbw.view.drgex.DRGEX0107F;
import com.kangdainfo.tcbw.view.drgex.DRGEX0109F;
import com.kangdainfo.tcbw.view.drgex.DRGEX0302F;

public interface DrgexDao extends BaseDao{
	//登錄暫存
	public void updateByDrgEX0102F(DRGEX0102F ref) throws Exception; 	
	public void saveOrUpdateDrg5002Db(java.util.List<Drg5002Db> saveList,java.util.List<Drg5002Db> updateList,java.util.List<Drg5002Db> deleteList) throws Exception; 
	//批次送出
	public void updateSendApplNoByDRGEX0105F(DRGEX0105F ref)throws Exception;
	//批號確認(廠商回覆)
	public void checkNoByDrgEX0107F(DRGEX0107F ref)throws Exception;
	//CAPA評估完成(廠商回覆)
	public void doReplyByDrgEX0109F(DRGEX0109F ref)throws Exception;
	//主動補件
	public void autoReUpdateByDrgEX0102F(DRGEX0102F ref)throws Exception;
	
	//登錄暫存
	public DRGEX0302F updateByDrgEX0302F(DRGEX0302F ref) throws Exception; 
	
	//療效不等更新案件狀態
	public void sendDrg061Db(Drg6001Db drg61, String updateType);
	
	//療效不等複製案件
	public void copyToInDrgData(Drg6001Db drg61);
	
	public Object setFileValue(Object newTable, Object oldTable) throws Exception;
	
	public String getMaxRevisionByDrg6001Db(Long drg4001Id);
	
	public void sendDrgMailToChargeMan(Drg4001Db drg41, String mailBody, List<String> attFilePathList);
	
}
