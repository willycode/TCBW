package com.kangdainfo.tcbw.view.medin;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.Datetime;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Med0001Db;
import com.kangdainfo.tcbw.util.TCBWCommon;


public class MEDIN0903F extends MEDIN0901F
{
	private String id;//序號	NUMERIC(19,0)
	private String enforceCloseReason;//強制結案理由
	private String isEnd;//是否已完成強制結案動作
	
	//將案件強制結案：
	public void enforceClose() throws Exception 
	{
		ServiceGetter.getInstance().getTcbwService().getMedin1Dao().updateByenforceCloseMedIN0903F(this);
	}


	@Override
	public Object doQueryOne() throws Exception {
		return null;
	}

	@Override
	public Object doQueryAll() throws Exception 
	{		
		return null;
	}

	@Override
	public void doCreate() throws Exception {
		
	}

	@Override
	public void doUpdate() throws Exception {
		
	}

	@Override
	public void doDelete() throws Exception {
		
	}

	public String getId() {
		return checkGet(id);
	}

	public void setId(String id) {
		this.id = checkSet(id);
	}
	
	public String getEnforceCloseReason() {
		return checkGet(enforceCloseReason);
	}

	public void setEnforceCloseReason(String enforceCloseReason) {
		this.enforceCloseReason = checkSet(enforceCloseReason);
	}


	public String getIsEnd() {
		return checkGet(isEnd);
	}


	public void setIsEnd(String isEnd) {
		this.isEnd = checkSet(isEnd);
	}


}
