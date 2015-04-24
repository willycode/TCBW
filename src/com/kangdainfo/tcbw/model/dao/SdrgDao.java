package com.kangdainfo.tcbw.model.dao;

import com.kangdainfo.persistence.BaseDao;
import com.kangdainfo.tcbw.view.sdrg.SDRG0101F;
import com.kangdainfo.tcbw.view.sdrg.SDRG0201F;
import com.kangdainfo.tcbw.view.sdrg.SDRG0301F;
import com.kangdainfo.tcbw.view.sdrg.SDRG0401F;
import com.kangdainfo.tcbw.view.sdrg.SDRG0501F;
import com.kangdainfo.tcbw.view.sdrg.SDRG0601F;

public interface SdrgDao extends BaseDao{
	//登錄
	public void updateBySdrg0101F(SDRG0101F ref) throws Exception; 
	
	//登錄審核
	public void updateBySdrg0201F(SDRG0201F ref) throws Exception; 
	
	//回收/回覆
	public void updateBySdrg0301F(SDRG0301F ref) throws Exception; 
	
	//回收確認
	public void updateBySdrg0401F(SDRG0401F ref) throws Exception;
	
	//案件評估
	public void updateBySdrg0501F(SDRG0501F ref) throws Exception;
	
	//案件分析
	public void updateBySdrg0601F(SDRG0601F ref) throws Exception; 
	
}
