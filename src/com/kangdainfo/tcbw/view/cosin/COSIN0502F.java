package com.kangdainfo.tcbw.view.cosin;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Cos0001Db;
import com.kangdainfo.tcbw.model.bo.Cos0006Db;

public class COSIN0502F extends COSIN0501F {
	
	private String recentlyMeasure;						// FOR 判斷最新一筆不良品分類的採取措施
	private String dept1;								// FOR 選取的衛生單位		
	private String isCloseUserInfo;		//列印時是否遮蔽個資
	
	public String getIsCloseUserInfo() {		return checkGet(isCloseUserInfo);	}
	public void setIsCloseUserInfo(String isCloseUserInfo) {		this.isCloseUserInfo = checkSet(isCloseUserInfo);	}
	public String getRecentlyMeasure() {		return checkGet(recentlyMeasure);	}
	public void setRecentlyMeasure(String recentlyMeasure) {		this.recentlyMeasure = checkSet(recentlyMeasure);	}
	public String getDept1() {		return checkGet(dept1);	}
	public void setDept1(String dept1) {		this.dept1 = checkSet(dept1);	}
	
	public Object doQueryOne() throws Exception {
		COSIN0502F obj = this;
		Cos0001Db c = (Cos0001Db)View.getObject(" from Cos0001Db where id = " + Common.getLong(getId()));
		if(c != null){
			
			setBaseData(obj, c);
			setAssessPageData(obj, c);
			setDisPageData(obj, c);
			
			if("1".equals(Common.get(c.getCosType()))){
				Cos0006Db cos0006Db = (Cos0006Db)View.getObject(" from Cos0006Db where applNo = " + Common.sqlChar(c.getApplNo()) + " order by id desc ");
				if(cos0006Db != null){
					obj.setRecentlyMeasure(cos0006Db.getMeasure());
				}
			}else{
				obj.setRecentlyMeasure("");
			}
		}
		return obj;
	}
	
	public void doUpdateType() throws Exception{
		ServiceGetter.getInstance().getTcbwService().getCosinDao().updateByCOSIN0502F(this);
	}

}
