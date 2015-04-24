package com.kangdainfo.tcbw.model.bo;

/**
 * 回傳時的一個中介訊息物件
 * @author shark
 *
 */
public class MsgObject {

	String returnMsg;	
	java.util.Map<String, ?> returnMap;
	Double[] returnValue;
	
	public String getReturnMsg() {
		return returnMsg;
	}
	public void setReturnMsg(String returnMsg) {
		this.returnMsg = returnMsg;
	}
	public java.util.Map<String, ?> getReturnMap() {
		return returnMap;
	}
	public void setReturnMap(java.util.Map<String, ?> returnMap) {
		this.returnMap = returnMap;
	}
	public Double[] getReturnValue() {
		return returnValue;
	}
	public void setReturnValue(Double[] returnValue) {
		this.returnValue = returnValue;
	}
		
}
