package com.kangdainfo.tcbw.view.cosin;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Cos0001Db;

public class COSIN0702F extends COSIN0701F {
	
	public Object doQueryOne() throws Exception {
		COSIN0702F obj = this;
		Cos0001Db c = (Cos0001Db)View.getObject(" from Cos0001Db where id = " + Common.getLong(getId()));
		if(c != null){
			setBaseData(obj, c);					
			setDisPageData(obj, c);					
			setProcessPage(obj, c, true);
		}
		return obj;
	}
	
	public void doUpdateType() throws Exception{
		ServiceGetter.getInstance().getTcbwService().getCosinDao().updateByCOSIN0702F(this);
	}

}
