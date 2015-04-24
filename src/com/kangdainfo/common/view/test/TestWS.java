package com.kangdainfo.common.view.test;

import java.net.URL;

import javax.xml.namespace.QName;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.Base64;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.BeanUtils;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.model.bo.CommonCode;
import com.kangdainfo.common.model.bo.CommonCodeKind;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.persistence.PersistenceServiceGetter;

public class TestWS {
	

	public void testAxisClient() throws Exception {
		Service axisService = new Service();				
		Call call = (Call) axisService.createCall();
		call.setTargetEndpointAddress("http://localhost:8080/pojo/xws/CommonWebService");					
		call.setOperationName(new QName("getRevData", "getRevData"));
		Object r = call.invoke(new String[]{"", "","from CommonCodeKind where codeKindId='MSG' order by codeKindId"});					
		String jsonString = Common.get(r);        
		if (jsonString!=null && jsonString.length()>0) {
			System.out.println(jsonString);
			
			JSONArray list = JSONArray.fromObject(jsonString);
			if (list!=null && list.size()>0) {
				String[] ignoreFields = new String[]{"id","commonCodeKind","commonCodes"};
				for (Object o : list) {
					JSONObject x = JSONObject.fromObject(o);	
					CommonCodeKind src = (CommonCodeKind) JSONObject.toBean(x, CommonCodeKind.class);
					if (src!=null && !"".equals(Common.get(src.getCodeKindId())) && !"".equals(Common.get(src.getCodeKindName()))) {						
						CommonCodeKind dest = new CommonCodeKind();
						BeanUtils.copyProperties(src, dest, ignoreFields);
						java.util.Map<String, CommonCode> h = new java.util.HashMap<String, CommonCode>();						
						java.util.List<CommonCode> saveList = new java.util.ArrayList<CommonCode>();
						if (dest.getCommonCodes()!=null && dest.getCommonCodes().size()>0) {
							for (Object dtlObj : dest.getCommonCodes()) {
								CommonCode dtl = (CommonCode) dtlObj;
								h.put(dtl.getCodeId(), dtl);
							}
						}
						if (src.getCommonCodes()!=null && src.getCommonCodes().size()>0) {
							for (Object dtlObj : src.getCommonCodes()) {						
								CommonCode dtl = (CommonCode) JSONObject.toBean(JSONObject.fromObject(dtlObj), CommonCode.class);
								CommonCode destDtl = new CommonCode();
								BeanUtils.copyProperties(dtl, destDtl, ignoreFields);
								destDtl.setCommonCodeKind(dest);
								saveList.add(destDtl);
							}					
						}							
						System.out.println(dest.getCodeKindId() + " - " + dest.getCodeKindName() + " : " + saveList.size());
						
					}					
				}
			}		
		}        
	}	
	
	
	public void testXFire() throws Exception {
		org.codehaus.xfire.client.Client client = new org.codehaus.xfire.client.Client(new URL("http://localhost:5800/common_pojo/xws/CommonWebService?wsdl"));
        Object[] results = client.invoke("getRevData", new Object[] {"changeit", "changeit","from CommonCodeKind where codeKindId='MSG' order by codeKindId"});        
        String jsonString = Common.get(results[0]);
        
		if (jsonString!=null && jsonString.length()>0) {
			JSONArray list = JSONArray.fromObject(jsonString);
			if (list!=null && list.size()>0) {
				String[] ignoreFields = new String[]{"id","commonCodeKind","commonCodes"};
				for (Object o : list) {
					JSONObject x = JSONObject.fromObject(o);	
					CommonCodeKind src = (CommonCodeKind) JSONObject.toBean(x, CommonCodeKind.class);
					if (src!=null && !"".equals(Common.get(src.getCodeKindId())) && !"".equals(Common.get(src.getCodeKindName()))) {						
						CommonCodeKind dest = new CommonCodeKind();
						BeanUtils.copyProperties(src, dest, ignoreFields);
						java.util.Map<String, CommonCode> h = new java.util.HashMap<String, CommonCode>();						
						java.util.List<CommonCode> saveList = new java.util.ArrayList<CommonCode>();
						if (dest.getCommonCodes()!=null && dest.getCommonCodes().size()>0) {
							for (Object dtlObj : dest.getCommonCodes()) {
								CommonCode dtl = (CommonCode) dtlObj;
								h.put(dtl.getCodeId(), dtl);
							}
						}
						System.out.println(dest.getCodeKindId() + " - " + dest.getCodeKindName());
						if (src.getCommonCodes()!=null && src.getCommonCodes().size()>0) {
							for (Object dtlObj : src.getCommonCodes()) {						
								CommonCode dtl = (CommonCode) JSONObject.toBean(JSONObject.fromObject(dtlObj), CommonCode.class);
								CommonCode destDtl = new CommonCode();
								BeanUtils.copyProperties(dtl, destDtl, ignoreFields);
								destDtl.setCommonCodeKind(dest);
								saveList.add(destDtl);
								
								System.out.println(dtl.getCodeId() + " -- " + dtl.getCodeName());
								
								PersistenceServiceGetter.getInstance().getJdbcTemplate().getDataSource().getConnection();
								
							}					
						}	
						
						//System.out.println(dest.getCodeKindId() + " - " + dest.getCodeKindName() + " : " + saveList.size());
						
					}					
				}
			}				
		}    		
	}
	
	
	public void testWSFile() throws Exception {
		org.codehaus.xfire.client.Client client = new org.codehaus.xfire.client.Client(new URL("http://localhost:5800/common_pojo/xws/CommonWebService?wsdl"));
        Object[] results = client.invoke("getCommonFileContent", new Object[] {"", "","f4620e54abded833_54362284_12e042bcf3f_-8000:;:100年國定假期表.doc"});
        
        byte[] arrBytes = Base64.decode(Common.get(results[0]));
		if (arrBytes!=null && arrBytes.length>0) {
			FileUtils.writeByteArrayToFile(new java.io.File("d:/test.doc"), arrBytes);
		} else {
			System.out.println("NULL");
			System.out.println(results[0]);
		}		
	}	
	
	public void testAxisWS() throws Exception {
		Service axisService = new Service();				
		Call call = (Call) axisService.createCall();
		//call.setTargetEndpointAddress("http://172.16.32.169:8080/pojo/ext/Datetime.jws");
		call.setTargetEndpointAddress("http://localhost:8080/pojo/ext/Datetime.jws");
		call.setOperationName(new QName("Datetime", "getYYYMMDD"));
		Object r = call.invoke(new String[]{});					
		System.out.println(Common.get(r)); 
	}		
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		long timeStart = System.currentTimeMillis();
		System.out.println(String.valueOf(new java.util.Date(timeStart)));		
		TestWS obj = new TestWS();
		//obj.testXFire();
		obj.testAxisClient();
		
		//obj.testWSFile();
		obj.testAxisWS();
		
		long timeEnd = System.currentTimeMillis();
		System.out.println(String.valueOf(new java.util.Date(timeEnd)));	
		
	}

}
