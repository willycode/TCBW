package com.kangdainfo.tcbw.view.cosin;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Cos0001Db;

public class COSIN1002F extends COSIN1001F {
	
	private String isCloseUserInfo;		// 列印時是否遮蔽個資
	public String getIsCloseUserInfo() {		return checkGet(isCloseUserInfo);	}
	public void setIsCloseUserInfo(String isCloseUserInfo) {		this.isCloseUserInfo = checkSet(isCloseUserInfo);	}
	
	@Override
	public Object doQueryOne() throws Exception {
		COSIN1002F obj = this;
		Cos0001Db c = (Cos0001Db)View.getObject(" from Cos0001Db where id = " + Common.getLong(getId()));
		if(c != null){
			setBaseData(obj, c);
			setAssessPageData(obj, c);
			setDisPageData(obj, c);
		}
		return obj;
	}
	
	public void doUpdateType() throws Exception{
		ServiceGetter.getInstance().getTcbwService().getCosinDao().updateByCOSIN1002F(this);
	}
	
	public void updateAdditionalCase() throws Exception{
		Cos0001Db cos0001Db = (Cos0001Db)View.getObject(" from Cos0001Db where id = " + Common.getLong(getId()));
		if(cos0001Db != null){
			ServiceGetter.getInstance().getTcbwService().getCosinDao().updateAdditionalCase(cos0001Db, getUserID());
			
			setErrorMsg("補件通知完成 !");
			setIsNeedBackQuery("Y");
		}else{
			setErrorMsg("查無該筆資料，請重新操作 !");
			setIsNeedBackQuery("Y");
		}
	}
	
	

}
