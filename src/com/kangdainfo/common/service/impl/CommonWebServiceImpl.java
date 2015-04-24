package com.kangdainfo.common.service.impl;

import java.io.File;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

import org.apache.axis.encoding.Base64;
import org.apache.commons.io.FileUtils;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.service.CommonWebService;
import com.kangdainfo.common.util.Common;

public class CommonWebServiceImpl implements CommonWebService {
	
	private String revUser;
	private String revPwd;
	public String getRevUser() {return Common.get(revUser);	}
	public void setRevUser(String revUser) {this.revUser = revUser;	}
	public String getRevPwd() {return Common.get(revPwd);	}
	public void setRevPwd(String revPwd) {this.revPwd = revPwd;	}

	public String getRevData(String s1, String s2, String s3) {
		return getWSRevData(s1, s2, s3, null);
	}
	
	/**
	 * 取得資料
	 * @param s1 = 帳號
	 * @param s2 = 密碼
	 * @param s3 = HQL
	 * @param s4 = Excludes
	 * @return
	 */
	public String getWSRevData(String s1, String s2, String s3, String s4) {
		if(Common.get(s1).equals(getRevUser()) && Common.get(s2).equals(getRevPwd())){
			java.util.List list = ServiceGetter.getInstance().getCommonService().load(s3);
			if (list!=null && list.size()>0) {
				JsonConfig jsonConfig=new JsonConfig();
				jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
				if (s4!=null && Common.get(s4).length()>0){
					jsonConfig.setExcludes(s4.split(","));
				}				
				return JSONArray.fromObject(list,jsonConfig).toString();
			}
		}
		return null;
	}	
	
	public String getRevDataObject(String s1, String s2, String s3) {
		return getWSRevDataObject(s1, s2, s3, null);
	}	

	public String getWSRevDataObject(String s1, String s2, String s3, String s4) {
		if(Common.get(s1).equals(getRevUser()) && Common.get(s2).equals(getRevPwd())){
			Object o = ServiceGetter.getInstance().getCommonService().getObject(s3);
			if (o!=null) {
				JsonConfig jsonConfig=new JsonConfig();
				jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);				
				if (s4!=null && Common.get(s4).length()>0){
					jsonConfig.setExcludes(s4.split(","));
				}					
				return JSONObject.fromObject(o,jsonConfig).toString();
			}
		}
		return null;
	}
	
	public String getCommonFileContent(String s1, String s2, String s3) {
		if(Common.get(s1).equals(getRevUser()) && Common.get(s2).equals(getRevPwd()) && !"".equals(Common.get(s3))){
			System.out.println(s3);
			try {
				String filestoreLocation = ServiceGetter.getInstance().getMyServletContext().getServletContext().getInitParameter("filestoreLocation");
		    	String[] arrFileName = s3.split(":;:"); 
		    	File sFile = null;
		    	if (arrFileName.length>1) {
		    		File dir = new File(filestoreLocation+File.separator+arrFileName[0]);        		
		            if (dir.isDirectory()) {        	
		                String[] children = dir.list();
		                for (int i=0; i<children.length; i++) {            	
		                	sFile = new File(dir, children[i]);
		                }
		            }   		
		    	}
		        if(sFile!=null && sFile.exists()){	 
		        	return Base64.encode(FileUtils.readFileToByteArray(sFile));
		        }			
			} catch (Exception e) {
				e.printStackTrace();
			}			
		}
		return null;
	}
	
}
