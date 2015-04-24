package com.kangdainfo.tcbw.model.dao;

import com.kangdainfo.persistence.BaseDao;
import com.kangdainfo.tcbw.view.pmed.PMED0101F;
import com.kangdainfo.tcbw.view.pmed.PMED0201F;
import com.kangdainfo.tcbw.view.pmed.PMED0301F;

public interface PmedDao extends BaseDao{
	//登錄
	public void updateByPmed0101F(PMED0101F ref) throws Exception; 
	
	//報告繳交清單
	public void updateByPmed0201F(PMED0201F ref) throws Exception; 
	
	//報告評估
	public void updateByPmed0301F(PMED0301F ref) throws Exception; 
	
	//補件
	public void updateByDoAddocuments(PMED0301F ref) throws Exception;
	
	//補件回覆
//	public void updateByDoReupdate(PMED0201F ref) throws Exception;
	
	//案件分析
//	public void updateByPmed0601F(PMED0601F ref) throws Exception; 
	
}
