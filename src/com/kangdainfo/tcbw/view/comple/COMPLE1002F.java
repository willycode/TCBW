package com.kangdainfo.tcbw.view.comple;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Cos0001Db;

public class COMPLE1002F extends COMPLE1001F {
	
	// 列印時是否遮蔽個資
	private String isCloseUserInfo;				
	public String getIsCloseUserInfo() {		return checkGet(isCloseUserInfo);	}
	public void setIsCloseUserInfo(String isCloseUserInfo) {		this.isCloseUserInfo = checkSet(isCloseUserInfo);	}
	
	public String ch1;
	public String ch2;
	public String ch3;
	public String ch4;
	public String ch5;
	public String ch6;
	
	public String getCh1() {		return checkGet(ch1);	}
	public void setCh1(String ch1) {		this.ch1 = checkSet(ch1);	}
	public String getCh2() {		return checkGet(ch2);	}
	public void setCh2(String ch2) {		this.ch2 = checkSet(ch2);	}
	public String getCh3() {		return checkGet(ch3);	}
	public void setCh3(String ch3) {		this.ch3 = checkSet(ch3);	}
	public String getCh4() {		return checkGet(ch4);	}
	public void setCh4(String ch4) {		this.ch4 = checkSet(ch4);	}
	public String getCh5() {		return checkGet(ch5);	}
	public void setCh5(String ch5) {		this.ch5 = checkSet(ch5);	}
	public String getCh6() {		return checkGet(ch6);	}
	public void setCh6(String ch6) {		this.ch6 = checkSet(ch6);	}
	
	public Object doQueryOne() throws Exception {
		COMPLE1002F obj = this;
		
		Cos0001Db c = (Cos0001Db)View.getObject(" from Cos0001Db where id = " + Common.getLong(getId()));
		if(c != null){
			setBaseData(obj, c);							
			setAssessPageData(obj, c, true);
			setDisPageData(obj, c);									
			setProcessPage(obj, c, false, true);
			setEndCasePage(obj, c);
		}
		return obj;
	}
	
	public void doUpdateType() throws Exception {
		ServiceGetter.getInstance().getTcbwService().getCosinDao().updateByCOMPLE1002F(this);
	}
	
	

}
