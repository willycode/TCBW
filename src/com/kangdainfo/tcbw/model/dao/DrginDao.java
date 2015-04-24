package com.kangdainfo.tcbw.model.dao;

import com.kangdainfo.persistence.BaseDao;
import com.kangdainfo.tcbw.model.bo.Drg0001Db;
import com.kangdainfo.tcbw.model.bo.Drg0002Db;
import com.kangdainfo.tcbw.model.bo.Drg4001Db;
import com.kangdainfo.tcbw.model.bo.Drg4004Db;
import com.kangdainfo.tcbw.view.drgin.DRGIN0101F;
import com.kangdainfo.tcbw.view.drgin.DRGIN0102F;
import com.kangdainfo.tcbw.view.drgin.DRGIN0102Q;
import com.kangdainfo.tcbw.view.drgin.DRGIN0106F;
import com.kangdainfo.tcbw.view.drgin.DRGIN0109F;
import com.kangdainfo.tcbw.view.drgin.DRGIN0112F;
import com.kangdainfo.tcbw.view.drgin.DRGIN0114F;
import com.kangdainfo.tcbw.view.medin.MEDIN5202F;



public interface DrginDao extends BaseDao{
	//修改(登錄)
	public void updateByDrgIN0102F(DRGIN0102F ref) throws Exception; 	
	public void saveOrUpdateDrg0002Db(java.util.List<Drg0002Db> saveList,java.util.List<Drg0002Db> updateList,java.util.List<Drg0002Db> deleteList) throws Exception;
	
	//退件(審核)
	public void backPiecesDrgByIN0102F(DRGIN0102F ref) throws Exception;	
	
	//撤案(審核)
	public void dismissalByDrgIN0102F(DRGIN0102F ref) throws Exception;
	
	//受理(審核)
	public void acceptedByDrgIN0102F(Drg0001Db obj,String user) throws Exception;
	
	//批次受理(審核)
	public void acceptedByDrgIN0101F(DRGIN0101F ref) throws Exception;
	
	//修改(分級)
	public void updateByDrgIN0106F(DRGIN0106F ref) throws Exception; 
	
	//通知補件(分級)
	public void backForMailByDrgIN0106F(DRGIN0106F ref) throws Exception; 
	
	//退件補件(分級)
	public void backPiecesByDrgIN0106F(DRGIN0106F ref) throws Exception; 
	
	//案件初評(分級)
	public void doAccessByDrgIN0106F(DRGIN0106F ref) throws Exception;
	
	//案件發文(分級)
	public void doPostDocByDrgIN0106F(DRGIN0106F ref) throws Exception;
	
	//修改(分級確認)
	public void updateByDrgIN0109F(DRGIN0109F ref) throws Exception; 
	
	//分級確認完成
	public void gradeByDrgIN0109F(DRGIN0109F ref) throws Exception; 
	
	//補件(分級確認)
	public void backAccessByDrgIN0109F(DRGIN0109F ref) throws Exception;
	
	//修改(評估)
	public void updateByDrgIN0112F(DRGIN0112F ref) throws Exception;
	
	//發文(評估)
	public void doPostNoByDrgIN0112F(DRGIN0112F ref) throws Exception;
	
	//評估完成(評估)
	public void doAssessByDrgIN0112F(DRGIN0112F ref) throws Exception;
	
	//再評估完成(評估)
	public void doReAssessByDrgIN0112F(DRGIN0112F ref) throws Exception;
	
	//廠商補件(評估)
	public void doBackByDrgIN0112F(DRGIN0112F ref) throws Exception;	
	
	//修改(分析)
	public void updateByDrgIN0114F(DRGIN0114F ref) throws Exception;
	
	//分析完成(分析)
	public void doAnalyByDrgIN0114F(DRGIN0114F ref) throws Exception;
	
	//解除鎖定
	public void doDeleteCon0003Db(Drg0001Db obj) throws Exception;
	
	//判斷是否該提報諮議會
	public String chkIsCouncilYnByDrg44(Drg4001Db drg41, Drg4004Db drg44, String category);
	
	public void closedCaseToPDFByDrg(Drg4001Db drg41);
	
	//重新校正(評估,查詢)
	public void doCorrectionByDrgIN0112F(DRGIN0112F ref) throws Exception;
	
	//重新評估(查詢)
	public void doReAssessByDrgIN0102Q(DRGIN0102Q ref) throws Exception;
	
}
