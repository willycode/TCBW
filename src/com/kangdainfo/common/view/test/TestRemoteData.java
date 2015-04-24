package com.kangdainfo.common.view.test;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.kangdainfo.common.model.bo.CommonCode;
import com.kangdainfo.common.model.bo.CommonCodeKind;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.web.scripting.AjaxUtil;

public class TestRemoteData {

	public static void main(String[] args) {
		java.util.Map<String, Object> params = new java.util.HashMap<String, Object>();
		params.put("q", "TIT");
		
		String jsonString = Common.get(AjaxUtil.connectTo("http://localhost:8080/pojo/ext/jsonCodeKind.jsp", "UTF-8", params));
		JSONObject json = JSONObject.fromObject(jsonString);
		
		CommonCodeKind obj = (CommonCodeKind) JSONObject.toBean(json, CommonCodeKind.class);
		if (obj!=null) {
			System.out.println(obj.getCodeKindId() + " -- " + obj.getCodeKindName());
		
			JSONArray arrObj = json.getJSONArray("commonCodes");
			
			if (arrObj!=null && arrObj.size()>0) {
				//jdk 1.4 以下寫法
				java.util.Iterator it = arrObj.iterator();
				while (it.hasNext()) {
					JSONObject dtlObj = (JSONObject) it.next();
					CommonCode dtl = (CommonCode) JSONObject.toBean(dtlObj, CommonCode.class);
					System.out.println("\t"+dtl.getCodeId() + " -- " + dtl.getCodeName());
				}
				
				//jdk1.5 以上寫法
				for (Object dtlObj : arrObj) {
					CommonCode dtl = (CommonCode) JSONObject.toBean((JSONObject) dtlObj, CommonCode.class);
					System.out.println("\t"+dtl.getCodeId() + " -- " + dtl.getCodeName());
				}
				
			}
		
		}
	}
}
