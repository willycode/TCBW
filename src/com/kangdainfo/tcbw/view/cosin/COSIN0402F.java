package com.kangdainfo.tcbw.view.cosin;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Cos0001Db;

public class COSIN0402F extends COSIN0401F {
	
	private String usernameid; 
	private String commonUser;
	private String isCloseUserInfo;
	
	public String getUsernameid() {		return checkGet(usernameid);	}
	public void setUsernameid(String usernameid) {		this.usernameid = checkSet(usernameid);	}
	public String getCommonUser() {		return checkGet(commonUser);	}
	public void setCommonUser(String commonUser) {		this.commonUser = checkSet(commonUser);	}
	public String getIsCloseUserInfo() {		return checkGet(isCloseUserInfo);	}
	public void setIsCloseUserInfo(String isCloseUserInfo) {		this.isCloseUserInfo = checkSet(isCloseUserInfo);	}

	@Override
	public Object doQueryOne() throws Exception {
		COSIN0402F obj = this;
		
		Cos0001Db c = (Cos0001Db)View.getObject(" from Cos0001Db where id = " + Common.getLong(getId()));
		if(c != null){
			setBaseData(this, c);
		}
		return obj;
	}
	
	public void doUpdateType() throws Exception{
		ServiceGetter.getInstance().getTcbwService().getCosinDao().updateByCOSIN0402F(this);
	}
	
	public void doUnAcceptCase() throws Exception{
		ServiceGetter.getInstance().getTcbwService().getCosinDao().updateByCOSIN0402FDoUnAcceptCase(this);
	}
	
	
	
	
}
