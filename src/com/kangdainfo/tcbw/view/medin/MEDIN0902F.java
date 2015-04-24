package com.kangdainfo.tcbw.view.medin;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.Datetime;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Med0001Db;
import com.kangdainfo.tcbw.util.TCBWCommon;


public class MEDIN0902F extends MEDIN0901F
{
	private String id;//序號	NUMERIC(19,0)
	private String reCalibrationReason;//再校正理由
	private String isRCEnd;//系統是否已完成再校正流程
	
	//將案件流程改為案件處理階段：
	public void doReCalibration() throws Exception 
	{
		ServiceGetter.getInstance().getTcbwService().getMedin1Dao().updateBydoReCalibrationMedIN0902F(this);
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
	
	public String getReCalibrationReason() {
		return checkGet(reCalibrationReason);
	}

	public void setReCalibrationReason(String reCalibrationReason) {
		this.reCalibrationReason = checkSet(reCalibrationReason);
	}


	public String getIsRCEnd() {
		return checkGet(isRCEnd);
	}


	public void setIsRCEnd(String isRCEnd) {
		this.isRCEnd = checkSet(isRCEnd);
	}


}
