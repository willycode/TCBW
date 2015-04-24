package com.kangdainfo.tcbw.view.cosin;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Cos0001Db;

public class COSIN0902F extends COSIN0901F {
	
	// 列印時是否遮蔽個資
	private String isCloseUserInfo;				
	public String getIsCloseUserInfo() {		return checkGet(isCloseUserInfo);	}
	public void setIsCloseUserInfo(String isCloseUserInfo) {		this.isCloseUserInfo = checkSet(isCloseUserInfo);	}
	
	public Object doQueryOne() throws Exception {
		COSIN0902F obj = this;
		
		Cos0001Db c = (Cos0001Db)View.getObject(" from Cos0001Db where id = " + Common.getLong(getId()));
		if(c != null){
			setBaseData(obj, c);	
			setAssessPageData(obj, c);
			setDisPageData(obj, c);					
			setProcessPage(obj, c, false);
			setEndCasePage(obj, c);
		}
		return obj;
	}
	
	public void doUpdateType() throws Exception{
		ServiceGetter.getInstance().getTcbwService().getCosinDao().updateByCOSIN0902F(this);
	}

}
