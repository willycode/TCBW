package com.kangdainfo.tcbw.view.cosin;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Cos0001Db;
import com.kangdainfo.tcbw.model.bo.Cos0010Db;

public class COSIN0602F extends COSIN0601F {
	
	private String reDisReason;			// 重新分類理由
	private String isCloseUserInfo;		// 列印時是否遮蔽個資
	private String isAdditionalCase;	// 確認是否可以廠商補件
	
	public String getReDisReason() {		return checkGet(reDisReason);	}
	public void setReDisReason(String reDisReason) {		this.reDisReason = checkSet(reDisReason);	}
	public String getIsCloseUserInfo() {		return checkGet(isCloseUserInfo);	}
	public void setIsCloseUserInfo(String isCloseUserInfo) {		this.isCloseUserInfo = checkSet(isCloseUserInfo);	}
	public String getIsAdditionalCase() {return checkGet(isAdditionalCase);}
	public void setIsAdditionalCase(String isAdditionalCase) {this.isAdditionalCase = checkSet(isAdditionalCase);}
	
	public Object doQueryOne() throws Exception {
		COSIN0602F obj = this;
		Cos0001Db c = (Cos0001Db)View.getObject(" from Cos0001Db where id = " + Common.getLong(getId()));
		if(c != null){
			setBaseData(obj, c);	
			setAssessPageData(obj, c);
			setDisPageData(obj, c);					
			setProcessPage(obj, c, false);
			
			// 查詢化粧品不良反應的廠商補件是否全部都完成 
			obj.setIsAdditionalCase("Y");
			Cos0010Db cos = (Cos0010Db)ServiceGetter.getInstance().getTcbwService().getObject(" from Cos0010Db where (isClose is null or isClose = 'N') and applNo = " + Common.sqlChar(obj.getApplNo()));
			if(null != cos){
				obj.setIsAdditionalCase("N");
			}
		}
		return obj;
	}
		
	public void doUpdateType() throws Exception {
		ServiceGetter.getInstance().getTcbwService().getCosinDao().updateByCOSIN0602F(this);
	}
	
	public void updateAdditionalCase() throws Exception {
		ServiceGetter.getInstance().getTcbwService().getCosinDao().updateByCOSIN0602FAdditionCase(this);
	}
	
	public void reDisCase() throws Exception {
		ServiceGetter.getInstance().getTcbwService().getCosinDao().updateByCOSIN0602FReDisCase(this);
	}

}
