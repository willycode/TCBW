package com.kangdainfo.tcbw.util;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.crypto.spec.SecretKeySpec;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.model.bo.*;
import com.kangdainfo.common.util.BeanUtil;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.Datetime;
import com.kangdainfo.common.util.EncodeDES;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Con0001Db;
import com.kangdainfo.tcbw.model.bo.Con0002Db;
import com.kangdainfo.tcbw.model.bo.Con0004Db;
import com.kangdainfo.tcbw.model.bo.Con1001Db;
import com.kangdainfo.tcbw.model.bo.Con1008Db;
import com.kangdainfo.tcbw.model.bo.Con1011Db;
import com.kangdainfo.tcbw.model.bo.Con1015Db;
import com.kangdainfo.tcbw.model.bo.Cos1001Db;
import com.kangdainfo.tcbw.model.bo.Hfr4002Db;
import com.kangdainfo.tcbw.model.bo.Hfr4004Db;
import com.kangdainfo.tcbw.model.bo.Med0001Db;
import com.kangdainfo.tcbw.model.bo.Med1005Db;
import com.kangdainfo.tcbw.model.bo.Med1006Db;
import com.kangdainfo.tcbw.model.bo.Med2001Db;



public class TCBWCommon 
{

	private TCBWCommon() 
	{
		//avoid instantiation...
	}


	/**
	 * 如果傳入字串為空值,以defaultValue取代
	 * @param str
	 * @param defaultValue
	 * @return
	 */
	public static String isNull(String str, String defaultValue){
		return isEmpty(str)?defaultValue:str;
	}
	
	/**
	 * 判斷是否是空或null值
	 * @param str
	 * @param defaultValue
	 * @return
	 */
	public static boolean isEmpty(String str){
		return (str==null || str.trim().equals(""))?true:false;
	}
	
	/**
	 * 取得CommonCode的Map
	 * @param codeKind
	 * @return Map
	 */
	public static java.util.Map<String,String> getCommonCodeMap(String codeKind)
	{
		java.util.Map<String, String> cccMap = new java.util.HashMap<String, String>();
		java.util.Map<String, CommonCode> h = ServiceGetter.getInstance().getCommonService().getCommonCodeDao().getCommonCodeMap(codeKind, null);
		
		for (String key : h.keySet()) 
		{
			cccMap.put(key, Common.get(h.get(key).getCodeName()));			
		}
		return cccMap;
	}
	
	public static java.util.Map<String,String> getCommonCodeEngMap(String codeKind)
	{
		java.util.Map<String, String> cccMap = new java.util.HashMap<String, String>();
		java.util.Map<String, CommonCode> h = ServiceGetter.getInstance().getCommonService().getCommonCodeDao().getCommonCodeMap(codeKind, null);
		
		for (String key : h.keySet()) 
		{
			cccMap.put(key, Common.get(h.get(key).getCodeEngName()));			
		}
		return cccMap;
	}
	public static java.util.Map<String,CommonCode> getCommonCodeObjectMap(String codeKind)
	{
		return ServiceGetter.getInstance().getCommonService().getCommonCodeDao().getCommonCodeMap(codeKind, null);
	}	
	
	/**
	 * LIKE SQL CHAR
	 */
	public static String likeSqlChar(String s){
		return likeSqlChar(s,false);
	}
	public static String likeSqlChar(String s, boolean isUpper){
		return Common.sqlChar("%"+s+"%",isUpper);
	}	
	/**
	 * 病人問題代碼設定檔
	 * @param codeKind
	 * @return
	 */
	public static java.util.Map<String,String> getCodeNameMED1005()throws Exception{
		List list = ServiceGetter.getInstance().getTcbwService().getMedin1Dao().queryMED1005DB();
		Map<String,String> resultMap = new HashMap<String,String>();
		if(list != null){
			for(Object o : list){
				Med1005Db med1005 = (Med1005Db)o;
				resultMap.put(med1005.getCode(), med1005.getName());
			}
		}
		return resultMap;
	} 
	/**
	 * 醫材問題代碼設定檔
	 * @param codeKind
	 * @return
	 */
	public static java.util.Map<String,String> getCodeNameMED1006()throws Exception{
		List list = ServiceGetter.getInstance().getTcbwService().getMedin1Dao().queryMED1006DB();
		Map<String,String> resultMap = new HashMap<String,String>();
		if(list != null){
			for(Object o : list){
				Med1006Db med1006 = (Med1006Db)o;
				resultMap.put(med1006.getCode(), med1006.getName());
			}
		}
		return resultMap;
	} 
	public static java.util.Map<String,String> getCodeNameAdverseReactionsResult()throws Exception{
		Map<String,String> resultMap = new HashMap<String,String>();
		resultMap.put("1", "≦2分：存疑");
		resultMap.put("2", "3-5分：可能相關");
		resultMap.put("3", "6-8分：極有可能相關");
		resultMap.put("4", "≧9：確定相關");
		return resultMap;
	}
	public static java.util.Map<String,String> getCodeNameNCTRResult()throws Exception{
		Map<String,String> resultMap = new HashMap<String,String>();
		resultMap.put("1", "通報");
		resultMap.put("2", "不通報");
		return resultMap;
	}
	
	/**
	 * 取得所屬部門depNo清單
	 * @param depNo
	 * @param withSqlChar : 是否要用雙引號包起來
	 * @return String (    'depNo1','depNo2',...     )
	 */
	public static String getSubDepNo(String deptCode, boolean withSqlChar){
		StringBuilder sb = new StringBuilder();		
		try {				
			CommonDepartment dept = (CommonDepartment) ServiceGetter.getInstance().getCommonService().getCommonDepartmentDao().getCommonDepartment(deptCode);
			if(dept!=null){					
				CommonDepartment[] dt = ServiceGetter.getInstance().getCommonService().getCommonDepartmentDao().getChildNodes(dept.getId());
				if (dt!=null && dt.length>0) {
					if (withSqlChar) sb.append(Common.sqlChar(deptCode));
					else sb.append(Common.get(deptCode));					
					for (int i=0; i<dt.length; i++) {						
						if (Common.get(dt[i].getShortCode()).length()>0) {			
							if (withSqlChar) sb.append(",").append(Common.sqlChar(dt[i].getShortCode()));
							else sb.append(",").append(Common.get(dt[i].getShortCode()));
						}
					}
					return sb.toString();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();				
		}		
		if (withSqlChar) return Common.sqlChar(deptCode);
		else return deptCode;
	}   
	
	//將二進制轉換成16進制
	public static String parseByte2HexStr(byte buf[]) {  
        StringBuffer sb = new StringBuffer();  
        for (int i = 0; i < buf.length; i++) {  
                String hex = Integer.toHexString(buf[i] & 0xFF);  
                if (hex.length() == 1) {  
                        hex = '0' + hex;  
                }  
                sb.append(hex.toUpperCase());  
        }  
        return sb.toString();  
	}
	//將16進制轉換成二進制
	public static byte[] parseHexStr2Byte(String hexStr) {  
	        if (hexStr.length() < 1)  
	                return null;  
	        byte[] result = new byte[hexStr.length()/2];  
	        for (int i = 0;i< hexStr.length()/2; i++) {  
	                int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16);  
	                int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16);  
	                result[i] = (byte) (high * 16 + low);  
	        }  
	        return result;  
	}  
	public static String getEncodeString(String srcText) {
		SecretKeySpec privateKey = new SecretKeySpec("/kangdainfo*423021357fas".getBytes(), "TripleDES");
		EncodeDES obj = new EncodeDES(privateKey);
		byte[] srcEncodeByte = obj.encode(srcText.getBytes());
		
		return parseByte2HexStr(srcEncodeByte);
	}
	
	public static String getDecodeString(String encodedText) {
		SecretKeySpec privateKey = new SecretKeySpec("/kangdainfo*423021357fas".getBytes(), "TripleDES");
		EncodeDES obj = new EncodeDES(privateKey);		
		return new String(obj.decode(parseHexStr2Byte(encodedText)));
	}
	
	public static String[] getCon1001DbMail(String systemType, String formCode, String mailId){
		String hql = " from Con1001Db where systemType = " + Common.sqlChar(systemType);
		if(!"".equals(Common.get(formCode))){
			hql += " and formID = (select max(id) from Con1007Db where formCode = " + Common.sqlChar(formCode) + ")";
		}
		hql += " and mailID = " + Common.sqlChar(mailId);
		
		Con1001Db con1001Db = (Con1001Db)View.getObject(hql);
		if(con1001Db != null){
			String[] r = new String[2];
			r[0] = Common.get(con1001Db.getTitle()); 
			r[1] = Common.get(con1001Db.getMailBody());
			return r;
		}
		return null;
	}
	
	public static void setMailbackup(String sysType, String formid, String title, String mailbody, String applno, String state, String user, String fileData) {
		Con0002Db con0002 = new Con0002Db();
		con0002.setSystemType(sysType);
		con0002.setFormID(formid);
		con0002.setTitle(title);
		con0002.setMailBody(mailbody);
		con0002.setApplNo(applno);
		con0002.setState(state);
		con0002.setCreator(user);
		con0002.setCreateDate(Datetime.getYYYMMDD());
		con0002.setCreateTime(Datetime.getHHMMSS());
		
		ServiceGetter.getInstance().getTcbwService().save(con0002);

		if(!"".equals(Common.get(fileData)))
		{			
			String filestoreLocation = ServiceGetter.getInstance().getMyServletContext().getServletContext().getInitParameter(sysType);
			java.util.List<Con0001Db> saveCon0001DbList = new java.util.ArrayList<Con0001Db>();
			String[] tempFilePath = fileData.substring(0, fileData.length()-1).split("\\|");
			for(String o: tempFilePath)
			{				
				String[] arrFileName = o.split(":;:");				
				if(arrFileName!=null && arrFileName.length>0)
				{	
					//將檔案移入Con0002資料夾
				    File meddir = new File(filestoreLocation);
				    if(!meddir.isDirectory())
				    {
				    	meddir.mkdir();
				    }	
					
			        File medFileDir = new File(filestoreLocation+"\\"+"CON0002");
					
					//判斷資料夾是否存在，若不存在則建立
					if(!medFileDir.isDirectory())
					{
						medFileDir.mkdir();
					}	
					
					java.io.File oldFile = new java.io.File(filestoreLocation + File.separator + arrFileName[0] + File.separator + arrFileName[1]);
					java.io.File newPath = new java.io.File(filestoreLocation + File.separator + "CON0002"+java.io.File.separator + arrFileName[0]);
					System.out.println("oldFile: " + oldFile);
					System.out.println("newPath: " + newPath);

					try {
						copyto(oldFile, newPath, true);
					} catch (IOException e) {
						e.printStackTrace();
					}					
					
					Con0001Db con0001 = new Con0001Db();
					con0001.setFileKind(sysType);
					con0001.setSystemType("CON0002");
					con0001.setUpLoadId(con0002.getId());
					con0001.setFileRoute("CON0002:;:"+arrFileName[0]);
					con0001.setFileName(arrFileName[1]);
					con0001.setFileExplan("信件夾檔");
					con0001.setDbName("Con0002Db");
					con0001.setCreator(user);
					con0001.setCreateDate(Datetime.getYYYMMDD());
					con0001.setCreateTime(Datetime.getHHMMSS());
					saveCon0001DbList.add(con0001);
				}									
			}
			ServiceGetter.getInstance().getTcbwService().saveBatch(saveCon0001DbList);
		}		
	}
	
	public static void addocuments(String applNo,String notifyDate,String notifyBody,String user) 
	{
		Con0004Db con0004 = new Con0004Db();
		con0004.setApplNo(applNo);
		con0004.setNotifyDate(notifyDate);
		con0004.setNotifyBody(notifyBody);
		con0004.setCreator(user);
		con0004.setCreateDate(Datetime.getYYYMMDD());
		con0004.setCreateTime(Datetime.getHHMMSS());
		ServiceGetter.getInstance().getTcbwService().save(con0004);
	}
	
	/**
     * 目的-組合動態顯示檢核欄位javascript alertStr
     * 無分頁籤
     * 參數：formcode
     * 回傳：
     **/
	public static String getCheckFiled(String formcode)
	{
		return getCheckFiled(formcode,"");
	}
	
	
    /**
     * 目的-組合動態顯示檢核欄位javascript alertStr
     * 參數：formcode
     * 回傳：
     **/
	public static String getCheckFiled(String formcode,String tabId)
	{
		String hql = " from Con1008Db where con1007Db.formCode = " + Common.sqlChar(formcode);
		       hql +=" and  ( isRequired <> 'N' ";
		       hql +=" or  isDate <> 'N' ";
		       hql +=" or  isNum <> 'N' )";
		       
		       if(!"".equals(tabId))
		         hql +=" and  tabId="+Common.sqlChar(tabId);
		       
		       hql +=" and  isLength is not null ";
		       hql +=" order by id ";
		
		StringBuilder sb = new StringBuilder();
		
		java.util.ArrayList<Con1008Db> con1008Db = (ArrayList<Con1008Db>)ServiceGetter.getInstance().getTcbwService().load(hql);
		
		for(Con1008Db o : con1008Db)
		{
			sb.append("if(isObj(document.all.item('").append(o.getName()).append("')))").append("\n  {\n");
			
			if("T".equals(o.getFieldType()))
			{
			  if(!"N".equals(o.getIsRequired()))
			  {
				sb.append("	      alertStr += checkEmpty(form1.").append(o.getName()).append(", \""+o.getChName()+"\");\n");
			  }
			
			  if("Y".equals(o.getIsDate()))
			  {
				sb.append("	   if(form1.").append(o.getName()).append(".value != \"\")\n");
				sb.append("	      alertStr += checkDate(form1.").append(o.getName()).append(", \""+o.getChName()+"\");\n");
			  }
			
			  if(!"".equals(Common.get(o.getIsLength())) && Integer.valueOf(o.getIsLength()) > 0)
			  {
				sb.append("	   if(form1.").append(o.getName()).append(".value != \"\")\n");
				sb.append("	      alertStr += checkLen(form1.").append(o.getName()).append(", \""+o.getChName()+"\", \""+o.getIsLength()+"\");\n");
			  }
			
			  if("Y".equals(o.getIsNum()))
			  {
				sb.append("	   if(form1.").append(o.getName()).append(".value != \"\")\n");
				sb.append("	      alertStr += checkNumber(form1.").append(o.getName()).append(", \""+o.getChName()+"\");\n");
			  }
			}
			else if("R".equals(o.getFieldType()))
			{
				if(!"N".equals(o.getIsRequired()))
				{
					sb.append("	      alertStr += checkRadioButton(form1.").append(o.getName()).append(", \""+o.getChName()+"\");\n");
				}
			}
			else if("C".equals(o.getFieldType()))
			{
				if(!"N".equals(o.getIsRequired()))
				{
					sb.append("	      alertStr += checkButton(form1.").append(o.getName()).append(", \""+o.getChName()+"\");\n");
				}
			}
			
			sb.append("  }\n");
		}
		return sb.toString();
	}
	
	  /**
     * 目的- 顯示完整性
     * 參數： formcode
     * 回傳：
     **/
	public static String getIsComplete(String formcode)
	{
		String hql = " from Con1008Db where con1007Db.formCode = " + Common.sqlChar(formcode)+ " order by id";
		StringBuilder sb = new StringBuilder();
		java.util.ArrayList<Con1008Db> con1008Db = (ArrayList<Con1008Db>)ServiceGetter.getInstance().getTcbwService().load(hql);
		for(Con1008Db o : con1008Db)
		{
			sb.append("if(isObj(document.all.item('").append(o.getName()).append("')))").append("\n  {\n");
			
			
			if("Y".equals(o.getIsComplete()))
			{
				sb.append("	  if(form1.").append(o.getName()).append(".value == \"\")\n");
				sb.append("   {\n");
				sb.append("	      document.all.item('").append(o.getName()).append("').title='完整性欄位'; \n") ;
				sb.append("	      document.all.item('").append(o.getName()).append("').style.backgroundColor='#F7FA00'; \n") ;
				sb.append("   }");
			}
			

			sb.append("  }\n");
		}
		return sb.toString();
	}
	
    /**
     * 目的-組合必填欄位的*號javascript
     * 參數：formcode
     * 回傳：
     **/
	public static String getRequiredFiled(String formcode){
		String hql = " from Con1008Db where con1007Db.formCode = " + Common.sqlChar(formcode)+ " and isRequired in('S','Y') order by id";
		StringBuilder sb = new StringBuilder();
		java.util.ArrayList<Con1008Db> con1008Db = (ArrayList<Con1008Db>)ServiceGetter.getInstance().getTcbwService().load(hql);
		for(Con1008Db o : con1008Db){
			sb.append("	document.getElementById(\"").append(o.getName()).append("\").innerHTML=\"<font color='red'>*</font>\"\n");
		}
		return sb.toString();
	}
	
	//案件取號
	public static String getApplNo(String formType,String kind,String year){
		return getApplNo(formType,kind,year, false);
	}
	public static String getApplNo(String formType,String kind,String year, boolean isMonth)
	{
		String hql="  from Con1011Db where formType="+Common.sqlChar(formType);
		       hql+=" and kind="+Common.sqlChar(kind);
		       hql+=" and year="+Common.sqlChar(year);
		       
		Con1011Db  c = (Con1011Db)View.getObject(hql);
	    
		String applNo="";
		
		String hqlMap="select formType+kind,kindName from Con1011Db";
		java.util.Map<String,String> h=getMap(hqlMap);
		
		int num=0;
		
		if(c!=null)
		{
			applNo+=Common.get(c.getFormType().substring(0,1));
			applNo+=Common.get(c.getKind());
			applNo+=Common.get(c.getYear());
			if(isMonth)	applNo += Datetime.getMM();
			num=Common.getInt(c.getNum()+1);
			applNo+=Common.formatFrontZero(Common.get(num),4);
			
			c.setNum(num);
			ServiceGetter.getInstance().getTcbwService().update(c);
			
			return applNo;
		}	
		else
		{
			Con1011Db con1011Db=new Con1011Db();
			con1011Db.setFormType(formType);
			con1011Db.setKind(kind);
			con1011Db.setKindName(h.get(formType+kind));
			con1011Db.setYear(Datetime.getYYY());
			con1011Db.setNum(1);
			con1011Db.setCreator("system");
			con1011Db.setCreateDate(Datetime.getYYYMMDD());
			con1011Db.setCreateTime(Datetime.getHHMMSS());
			
			ServiceGetter.getInstance().getTcbwService().save(con1011Db);
			
			applNo+=Common.get(formType.substring(0,1));
			applNo+=Common.get(kind);
			applNo+=Common.get(Datetime.getYYY());
			if(isMonth)	applNo += Datetime.getMM();
			num=Common.getInt(1);
			applNo+=Common.formatFrontZero(Common.get(num),4);
			
			return applNo;
		}	
	    

	
	}

	
	//取出自動分派人員
	//formCode=通報表編號
	//code=流程角色代碼
	public static String getUserID(String formCode, String code, String db)
	{
		String userId = "";
		
		String hql = " from Con1015Db ";
		hql += " where con1014Db.code = " + Common.sqlChar(code);   
		hql += " and con1014Db.con1007Db.formCode = " + Common.sqlChar(formCode);
		hql += " and competence like " + likeSqlChar("1");
		
		java.util.List list = ServiceGetter.getInstance().getCommonService().load(hql);
		
		if(list!=null && list.size()>0)
		{
			java.util.Map<String, Integer> h = new java.util.HashMap<String, Integer>();

			for(int i=0; i<list.size(); i++)
			{
				Con1015Db obj = (Con1015Db) list.get(i);
				
				String hql1 = " select count(*)"; 
				hql1 += " from " + db ;
				if(Common.get(db).indexOf("Cos") != -1){
					hql1 += " where chargeMan =" + Common.sqlChar(obj.getCommonUser().getUserId());
					hql1 += " group by chargeMan ";
				}else if(Common.get(db).indexOf("Drg") != -1){
					hql1 += " where chargeMan =" + Common.sqlChar(obj.getCommonUser().getUserId());
					hql1 += " group by chargeMan ";
				}else{
					hql1 += " where workers =" + Common.sqlChar(obj.getCommonUser().getUserId());
					hql1 += " group by workers ";
				}
				
				String num = View.getLookupField(hql1);
				if("".equals(Common.get(num)))
				{
				   num = "0";
				}
				h.put(Common.get(obj.getCommonUser().getUserId()), Integer.parseInt(num));
			}
			
			if(h.size() > 0)
			{
				int value = 0;

				for(Map.Entry<String, Integer> dtl : h.entrySet())
				{
					if(dtl.getValue() <= value)
					{
						userId = dtl.getKey();
					}
					value = dtl.getValue();
				}
			}
		}  
		return userId;
	}
	
	//判斷人員是否有改派權限
	//formCode=通報表編號
	//code=流程角色代碼
	//回傳 Y 代表有改派權限
	public static String getCompetence(String formCode, String code, String userID,String competence)
	{
		String hql = " from Con1015Db ";
        hql += " where con1014Db.code = "+ Common.sqlChar(code);   
        hql += " and con1014Db.con1007Db.formCode = " + Common.sqlChar(formCode);
        hql += " and competence like " + TCBWCommon.likeSqlChar(competence);
        hql += " and commonUser.userId = " + Common.sqlChar(userID);
        
        Con1015Db  c =(Con1015Db)View.getObject(hql);
        if(c != null)
        {
        	return "Y";
        }	
        else
        {
        	return null;
        }	
	}
	
	//判斷第三方專家
	public static String getThirdParty(String formCode, String code, String userID,String competence)
	{
		String hql = " from Con1015Db ";
        hql += " where con1014Db.code = "+ Common.sqlChar(code);   
        hql += " and competence like " + TCBWCommon.likeSqlChar(competence);
        hql += " and commonUser.userId = " + Common.sqlChar(userID);
        
        Con1015Db  c =(Con1015Db)View.getObject(hql);
        if(c != null)
        {
        	return "Y";
        }	
        else
        {
        	return null;
        }	
	}
	
	
	// 丟入 hql回傳map格式
	public static java.util.Map<String,String> getMap(String hql) 
	{
		java.util.Map<String,String> h = new java.util.HashMap<String,String>();
		    	
		 java.util.List list = ServiceGetter.getInstance().getCommonService().load(hql);
		    	 	
		 if (list!=null && list.size()>0) 	
		 {
		    for (int i=0; i<list.size(); i++) 
		    {
		    	Object[] o = (Object[]) list.get(i);
		    	h.put(Common.get(o[0]), Common.get(o[1]));
		    }
		 }  	
		    	
		 return h; 
	}
	
	//取得登入者的ID
	public static String getCurrentUserId() {
		CommonUser user = ServiceGetter.getInstance().getAuthenticationService().getCurrentUser();
		if (user!=null) {
			return user.getUserId();
		}
		return "";
	}
	
	//取得登入者的類別(外部或內部)
	public static String getCurrentInORout() {
		CommonUser user = ServiceGetter.getInstance().getAuthenticationService().getCurrentUser();
		if (user!=null) {
			return user.getInORout();
		}
		return "";
	}
	
    /**
     * 目的 - 拿取服務單位名稱
     * 參數：notifierType 通報者屬性,notifierDeptID 服務機構ID
     * 回傳：
     **/
	public static String getNotifierDeptName(String notifierType,String notifierDeptID) {		
		String notifierDeptName ="";
		if (notifierType!=null) {
			if("02".equals(notifierType)){//廠商
				notifierDeptName = View.getLookupField("select name from Con1005Db where id="+Common.getLong(notifierDeptID));
			}
			else if("03".equals(notifierType)){ //醫事機構
				notifierDeptName = View.getLookupField("select medAgencyName from Con1009Db where id="+Common.getLong(notifierDeptID));
			}
			else if("04".equals(notifierType)){ //衛生單位
				notifierDeptName = View.getLookupField("select unionName from Con1003Db where id="+Common.getLong(notifierDeptID));
			}
			else{
				notifierDeptName="";
			}
			return notifierDeptName;
		}
		return notifierDeptName;
	}
	/**
	 * 轉換國內/國外代碼為中文
	 * @return
	 */
	public static java.util.Map<String,String> getCaseSourceName()
	{
		java.util.Map<String, String> resultMap = new java.util.HashMap<String, String>();
		resultMap.put("in", "國內");
		resultMap.put("out", "國外");
		return resultMap;
	}


	public static String transMED0001CaseType(Med0001Db dtl) {
		String result = "";
		if(dtl != null){
			if("1".equals(dtl.getEventKind())){
				result = getCaseSourceName().get(dtl.getCaseSource());
				if("Z0".equals(dtl.getMedPermit()))result = "專案進口";
			}
			if("2".equals(dtl.getEventKind()))
				result = "不良品";
		}
		return result;
	}
	public static String transMED2001CaseType(Med2001Db dtl) {
		String result = "";
		if(dtl != null){
			result = "臨床試驗不良事件";
		}
		return result;
	}


	public static Map<String, String> getCodeNameApprovedUnitsMap() {
		java.util.Map<String, String> resultMap = new java.util.HashMap<String, String>();
		resultMap.put("1", "醫事司");
		resultMap.put("2", "食品藥物管理署");
		resultMap.put("3", "其他");
		return resultMap;
	}

	public static Object getMaxObjectById(Set detailSet) {
		List list = new ArrayList(detailSet);
		return getMaxObjectById(list);
	}
	public static Object getMaxObjectById(List detailList) {
		List list = new ArrayList(detailList);
		class IdComparator implements Comparator  {
		    public int compare(Object o1, Object o2) {
		      return -BeanUtil.getPropertyByBeanUtils(o1, "id").compareTo(BeanUtil.getPropertyByBeanUtils(o2, "id"));
		    }
		}
		Collections.sort(list,new IdComparator());
		if(list != null && list.size() > 0)
			return list.get(0);
		return null;
	}
	/**
	 * for Cos0003_db 
	 * 依據大類(mainCode)查詢小類(subCode)的名稱
	 * 大類對應table = COSDPD
	 * @param mainCode
	 * @return
	 */
	public static String getCos0003SubCode(String mainCode, String subCode){
		String result = "";
		if(mainCode != null && !"".equals(mainCode) && subCode != null && !"".equals(subCode)){
			Object o = ServiceGetter.getInstance().getTcbwService().getObject(" from Cos1001Db where cdpCode = " + Common.sqlChar(mainCode) 
					+ " and dpdKind = " + Common.sqlChar(subCode) + " and isStop = 'N'");
			if(o != null)
				result = Common.get(((Cos1001Db)o).getDpdKindName()); 
		}
		return result;
	}
	/**
	 * 停用後不良反應是否減輕,再使用是否出現同樣反應 (isMitigateYn,isRecurYn) 代碼
	 * @return
	 */
	public static Map<String, String> getCodeNameIsMitigateYnAndIsRecurYnMap() {
		java.util.Map<String, String> resultMap = new java.util.HashMap<String, String>();
		resultMap.put("Y", "是");
		resultMap.put("N", "否");
		resultMap.put("U", "無法得知");
		return resultMap;
	}
	/**
	 * 後續處置 (dealWith) 代碼
	 * @return
	 */
	public static Map<String, String> getCodeNameDealWithMap() {
		java.util.Map<String, String> resultMap = new java.util.HashMap<String, String>();
		resultMap.put("Y", "是");
		resultMap.put("N", "否");
		return resultMap;
	}
	
	/**
	 * 
	 * @return
	 */
	public static Map<String, String> getIsAgainMap() 
	{
		java.util.Map<String, String> resultMap = new java.util.HashMap<String, String>();
		resultMap.put("F", "失敗");
		resultMap.put("S", "成功");
		resultMap.put("A", "重發成功");
		return resultMap;
	}
	
	 /**
     * 目的- 顯示藥證不同
     * 參數： formcode, rowName(有detail使用)
     * 回傳：
     **/
	public static String getIsMlmsField(String formcode, String rowName)
	{
		StringBuilder sb = new StringBuilder();
		Map<String, String> mlmsMap =  new HashMap<String, String>();
		String hql = " from Con1008Db where isMlms ='Y' and con1007Db.formCode = " + Common.sqlChar(formcode)+ " order by id";
		java.util.ArrayList<Con1008Db> con18List = (ArrayList<Con1008Db>)ServiceGetter.getInstance().getTcbwService().load(hql);
		if(null != con18List && !con18List.isEmpty()){
			if(null != rowName && !"".equals(rowName)){
				sb.append(" var drgRow=document.getElementsByName('").append(rowName).append("');");
				sb.append(" if(drgRow!=null && drgRow.length>0) { ");
				sb.append(" 	for (var i=0; i<drgRow.length; i++) { ");
				sb.append(" 		var id = drgRow[i].value;		");
				for(Con1008Db o : con18List){
					sb.append(" 	var fd = document.all.item('").append(o.getName()).append("'+id); ");
					sb.append(" 	if (isObj(fd)) { ");
					sb.append("			var mlmsData = getMLMSValue(getObjectValue('permitKey'+id),getObjectValue('permitNo'+id),'").append(o.getName()).append("');");
					sb.append(" 		if (fd.value != mlmsData ) { ");
					sb.append("	      		document.all.item('").append(o.getName()).append("'+id).style.backgroundColor='#00A002'; \n");
					sb.append(" 		} ");
					sb.append(" 	} ");
				}
				sb.append(" 	} ");
				sb.append(" } ");
			}else{
				for(Con1008Db o : con18List){
					sb.append("	  var mlmsData = getMLMSValue(getObjectValue('permitKey'),getObjectValue('permitNo'),'").append(o.getName()).append("');");
					sb.append("	  if(form1.").append(o.getName()).append(".value != mlmsData)\n");
					sb.append("   {\n");
					sb.append("	      document.all.item('").append(o.getName()).append("').style.backgroundColor='#00A002'; \n") ;
					sb.append("   }");
				}
			}
		}
		return sb.toString();
	}
	/**
	 * 依據tableName與id檢測是否有資料
	 * @param id
	 * @param tableName
	 * @return
	 */
	public static boolean hasDataByTableName(String id , String tableName){
		if(id != null && !"".equals(id) && tableName != null && !"".equals(tableName)){
			String hql = " from " + tableName + " where id = " + Common.sqlChar(id);
			return hasDataByTableName(hql);
		}
		return false;
	}
	public static boolean hasDataByTableName(String hql){
		boolean result = false;
		try{
			Object o = View.getObject(hql);
			if(o != null)result = true;				
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 
	 * @return
	 */
	public static Map<String, String> getCapitalMap() 
	{
		java.util.Map<String, String> resultMap = new java.util.HashMap<String, String>();
		resultMap.put("CONIN0101F", "使用者維護(內部)");
		resultMap.put("CONIN0202F", "使用者維護(外部)");
		return resultMap;
	}
	
	/**
	 *  判斷身分證是否正確
	 */
	public static boolean checkIdNum(String idNum)
	{
		if (!idNum.matches("[a-zA-Z]\\d{9}")){
		  return false;
	    }	       
	    else{
	         idNum=idNum.toUpperCase();       //轉大寫	         
	         char first = idNum.charAt(0);    //取出第一個字母	         
	         /*A-Z的對應數字*/
	         int idum[] = {10,11,12,13,14,15,16,17,34,18,19,20,21
	                            ,22,35,23,24,25,26,27,28,29,32,30,31,33};	         
	         /*轉成11碼的字串,'A'=65；
	            substring:從index:1開始取String*/
	         idNum = idum[first - 'A']+idNum.substring(1);       

	         /*把第一碼放到sum中,'0'=48*/
	         int sum = idNum.charAt(0)-'0';
	         
	         /*取2-10的總合*/
	         for(int i=1;i<10;i++)
	               sum +=idNum.charAt(i)*(10-i);

	         /*10-加總的尾數 = 第11碼*/
	         int checksum = (10-(sum%10))%10;

	         if (checksum == idNum.charAt(10)-'0'){
	        	 return true;
	         }else{
	        	 return false; 
	         }        
	    }		
	}
	
	//複製檔案
    public static void copyto(java.io.File srcFile, java.io.File dstFile, boolean isDelete)throws java.io.IOException 
    {
    	System.out.println("srcFile="+srcFile);
    	System.out.println("dstFile="+dstFile);
    	if (!srcFile.isFile())
            throw new java.io.IOException("copy時來源錯誤!!!");     
        if (!dstFile.exists())        
        	dstFile.mkdirs();
        
        java.io.File newFile = new java.io.File(dstFile.getAbsolutePath()+java.io.File.separator+srcFile.getName());
        // 以下為複製作業
        OutputStream os = null;
        InputStream is = null;        
        try{
        	byte b[] = new byte[(int)srcFile.length()];
        	if (srcFile.isFile())
        	{
        		is = new FileInputStream(srcFile);
        	    os = new FileOutputStream(newFile);
        	    int read = 0;
        	    while ((read = is.read(b)) != -1)
        	    {
        	        os.write(b,0,read);
        	    }
        	}
        }catch(Exception x){
        	System.out.println("error:"+x);
        }finally{
            os.close();
            is.close();
            if(isDelete) Common.RemoveDirectory(srcFile.getParentFile());
        }       
    }
    
    public static boolean validateHFR4002Db(Hfr4002Db dtl) throws Exception {
		boolean r = false;	
		if(!"".equals(Common.get(dtl.getPermitKey())) || !"".equals(Common.get(dtl.getPermitNo())) || !"".equals(Common.get(dtl.getChProduct())) || !"".equals(Common.get(dtl.getEnProduct())) || 
				!"".equals(Common.get(dtl.getBuySource())) || !"".equals(Common.get(dtl.getIngredient())) || !"".equals(Common.get(dtl.getDoseDay())) || 
				!"".equals(Common.get(dtl.getDoseNum())) || !"".equals(Common.get(dtl.getFrequency())) || !"".equals(Common.get(dtl.getFrequencyUnit())) || 
				!"".equals(Common.get(dtl.getEdibleDateS())) || !"".equals(Common.get(dtl.getEdibleDateE()))){
			r = true;
		}
		return r;
	}
	
    public static boolean validateDrugHFR4002Db(Hfr4002Db dtl) throws Exception {
		boolean r = false;	
		if(!"".equals(Common.get(dtl.getChProduct())) || !"".equals(Common.get(dtl.getEnProduct())) || !"".equals(Common.get(dtl.getContent())) || !"".equals(Common.get(dtl.getMedModel())) || 
				!"".equals(Common.get(dtl.getDoseDay())) || !"".equals(Common.get(dtl.getDoseNum())) || !"".equals(Common.get(dtl.getFrequency())) || !"".equals(Common.get(dtl.getFrequencyUnit())) || 
				!"".equals(Common.get(dtl.getEdibleDateS())) || !"".equals(Common.get(dtl.getEdibleDateE())) || !"".equals(Common.get(dtl.getBrand())) || !"".equals(Common.get(dtl.getPermitNo()))){
			r = true;
		}
		return r;
	}
    
    public static boolean validateHFR4004DB(Hfr4004Db dtl) throws Exception {
    	boolean r = false;
    	if(!"".equals(Common.get(dtl.getDoctorDate())) || !"".equals(Common.get(dtl.getBloodIndex())) || !"".equals(Common.get(dtl.getLiverIndex()))){
			r = true;
		}
    	return r;
    }
    
    public static boolean validateCON0001DB(Con0001Db con0001Db) throws Exception {
    	boolean r = false;
    	if(!"".equals(Common.get(con0001Db.getFileName())) || !"".equals(Common.get(con0001Db.getFileRoute())) || !"".equals(Common.get(con0001Db.getFileExplan()))){
			r = true;
		}
    	return r;
    }
    
    
	 /**
     * 目的- 藥品不良品,療效不等顯示上一版次不同(主檔)
     * 參數： formcode, rowName(有detail使用)
     * 回傳：
     **/
	public static String getRevisionField(String formcode, String rowName, String dbType, String revision, String applNo)
	{
		StringBuilder sb = new StringBuilder();
		//上次版本>1 才執行
		if(Common.getInt(revision)>=1){			
			String hql = " from Con1008Db where con1007Db.formCode = " + Common.sqlChar(formcode)+ " and tabId like '%EX%' order by id";
			java.util.ArrayList<Con1008Db> con18List = (ArrayList<Con1008Db>)ServiceGetter.getInstance().getTcbwService().load(hql);
			if(null != con18List && !con18List.isEmpty()){
				if(null != rowName && !"".equals(rowName)){					
					sb.append(" var drgRow=document.getElementsByName('").append(rowName).append("');");
					sb.append(" if(drgRow!=null && drgRow.length>0) { ");					
					sb.append(" 	for (var i=0; i<drgRow.length; i++) { ");
					sb.append(" 		var id = drgRow[i].value;		");
					sb.append(" 		var medType = document.all.item('medType'+id).value;");
					sb.append(" 		if(medType=='01' || medType=='02'){");
					for(Con1008Db o : con18List){
						sb.append(" 	   var fd = document.all.item('").append(o.getName()).append("'+id); ");
						sb.append(" 	   if (isObj(fd)) { ");
						sb.append("			   var revisionData = getRevisionValue('"+dbType+"','"+revision+"','"+o.getName()+"','"+applNo+"',medType);");
						sb.append(" 		   if (fd.value != revisionData ) { ");
						sb.append("	      		   document.all.item('").append(o.getName()).append("'+id).style.backgroundColor='#00A002'; \n") ;
						sb.append(" 		   } ");
						sb.append(" 	   } ");
					}
					sb.append(" 	    } ");
					sb.append(" 	} ");
					sb.append(" } ");
				}else{
					for(Con1008Db o : con18List){
						sb.append("if(isObj(document.all.item('").append(o.getName()).append("'))){");
						sb.append("var revisionData = getRevisionValue('"+dbType+"','"+revision+"','"+o.getName()+"','"+applNo+"');");						
	                    if("T".equals(Common.get(o.getFieldType()))){
	                    	sb.append("if(form1.").append(o.getName()).append(".value != revisionData)\n");
							sb.append("{");
	                        sb.append("	  document.all.item('").append(o.getName()).append("').style.backgroundColor='#00A002'; \n") ;
	                        sb.append("}");
						}
						else if("R".equals(Common.get(o.getFieldType()))){						
							sb.append("var colLen = document.all.item('").append(o.getName()).append("').length; ");						
							sb.append("for(var i=0;i<colLen;i++){");
							sb.append("   if(document.all.item('").append(o.getName()).append("')[i].value == revisionData ");
							sb.append("       && document.all.item('").append(o.getName()).append("')[i].checked==false )");
							sb.append("   {");
							sb.append("       document.all.item('").append(o.getName()).append("')[i].style.backgroundColor='#00A002';");
							sb.append("   }");
							sb.append("}");
						}					
						else if("C".equals(Common.get(o.getFieldType()))){							
							sb.append("var colLen = document.all.item('").append(o.getName()).append("').length; ");						
							sb.append("for(var i=0;i<colLen;i++){");
							sb.append("   var idColor = new Boolean(true);");
							sb.append("   var revisionValue = revisionData.split(\",\");");
							sb.append("   for(var j=0;j<revisionValue;j++){");
							sb.append("       if(document.all.item('").append(o.getName()).append("')[i].checked==false )");
							sb.append("       {");
							sb.append("           if(document.all.item('").append(o.getName()).append("')[i].value == revisionValue[j]){");
							sb.append("              idColor = true;");
							sb.append("              break;");
							sb.append("           }else{");
							sb.append("              idColor = false;");
							sb.append("           }");
							sb.append("       }");
							sb.append("       else");
							sb.append("       {");
							sb.append("           if(document.all.item('").append(o.getName()).append("')[i].value == revisionValue[j]){");
							sb.append("              idColor = false;");
							sb.append("              break;");
							sb.append("           }else{");
							sb.append("              idColor = true;");
							sb.append("           }");
							sb.append("       }");
							sb.append("   }");
							sb.append("   if(idColor==true) document.all.item('").append(o.getName()).append("')[i].style.backgroundColor='#00A002';");
							sb.append("}");
						}
	                    sb.append("}");
					}
				}				
			}			
		}			
		return sb.toString();
	}
    
}
