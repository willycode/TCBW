package com.kangdainfo.tcbw.view.comple;

import java.util.HashMap;
import java.util.Map;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.Datetime;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;
import com.kangdainfo.persistence.PersistenceServiceGetter;
import com.kangdainfo.tcbw.model.bo.Con0001Db;
import com.kangdainfo.tcbw.model.bo.Drg0001Db;
import com.kangdainfo.tcbw.model.bo.Drg0002Db;
import com.kangdainfo.tcbw.model.bo.Drg0003Db;
import com.kangdainfo.tcbw.model.bo.Drg0004Db;
import com.kangdainfo.tcbw.model.bo.Drg1001Db;
import com.kangdainfo.tcbw.util.TCBWCommon;
import com.kangdainfo.tcbw.view.drgin.DRGIN0102F;

public class COMPLE0101F extends SuperBean{
	
	private String id;//序號	NUMERIC(19,0)	
	private String tabId ;
	private String toUpdate;  //檔案上傳後維持在修改模式
	
	private String applNo;	//案件號碼	VARCHAR(11)
	private String status;	//案件狀態	VARCHAR(2)
	private String chargeMan;	//作業人員	VARCHAR(50)
	//基本資料------------------------------------------------------------------
	private String occurDate;	//發生日期	VARCHAR(7)
	private String notifier;
	private String notifierRevDate;	//通報者接獲日期	VARCHAR(7)
	private String notifierRepDate;	//通報中心接獲通報日期	VARCHAR(7)
	private String enrolledDate;    //收案日期	VARCHAR(7)
	private String notifierSource;	//通報來源	VARCHAR(2)
	private String notifierName;	//通報者姓名	NVARCHAR(20)
	private String notifierUserID; //通報者ID  VARCHAR(50)
	private String notifierDeptID;	//通報者服務機構ID	VARCHAR(20)
	private String notifierDept;	//通報者服務機構	NVARCHAR(50)
	private String notifierTel;	//通報者電話	VARCHAR(10)
	private String notifierTelArea;	//通報者電話區碼	VARCHAR(2)
	private String notifierTelExt;	//通報者電話分機   VARCHAR(3)
	private String notifierCounty;	//通報者電話	VARCHAR(2)
	private String notifierZipCode;	//通報者電話	VARCHAR(5)
	private String notifierAddress;	//通報者地址	NVARCHAR(100)
	private String notifierEmail;	//通報者電子信箱	VARCHAR(50)
	private String notifierType;	//通報者屬性	VARCHAR(2)
	private String notifierTitle;	//通報者職稱	VARCHAR(2)
	
	//不良藥品資料--------------------------------------------------------------
	private String permitKey;	//許可證字	VARCHAR(14)
	private String permitNo;	//許可證號	VARCHAR(14)
	private String chProduct; 	//商品名稱中文	NVARCHAR(100)
	private String enProduct; 	//商品名稱英文	VARCHAR(100)
	private String ingredient;	//有效成分名稱	NVARCHAR(50)
	private String content;	//單位含量	NVARCHAR(20)
	private String medModel;	//劑型	VARCHAR(2)
	private String medModelOther;	//劑型(描述)	NVARCHAR(50)
	private String medPackage;	//包裝形式	VARCHAR(2)
	private String medPackageOther;	//包裝形式(描述)	NVARCHAR(50)
	private String applicationID;	//藥商/申請商(統編)	VARCHAR(10)
	private String applicationName;	//藥商/申請商	NVARCHAR(50)
	private String manufactorName;	//製造商/製造廠	NVARCHAR(50)
	private String manufactorCountry;	//製造商/製造廠國別	NVARCHAR(50)
	private String manufactorNo;	//製造批號	VARCHAR(11)
	private String manufactorDate;	//製造日期	VARCHAR(7)
	private String expirationDate;	//保存期限	VARCHAR(7)
	private String storage;	//儲存環境	VARCHAR(2)
	private String storageOther;	//儲存環境(描述)	NVARCHAR(50)
	private String isFindYn;	//是否一經拆封即發現本不良品缺陷	VARCHAR(1)
	private String isSingleYn;	//本次通報事件是否為單一個案	VARCHAR(1)
	private String sameNum;	//同批號件數	VARCHAR(6)
	private String diffNum;	//不同批號件數	VARCHAR(6)
	private String isHarmYn;	//是否已對人體健康產生危害	VARCHAR(1)
	private String isUsedYn;	//是否為病人使用後發現不良品，向醫療人員反應 VARCHAR(1)
	private String evenContactYn;	//是否已連絡廠商	VARCHAR(1)
	private String dealWith;	//後續處理	VARCHAR(2)
	private String isContactYn;	//是否提供聯絡資訊供廠商後續調查評估	VARCHAR(1)
	private String defectDesc;	//不良品缺陷描述  VARCHAR(1000)
	private String firstResult;	//不良品原因初評	VARCHAR(2)
	private String firstRemark;	//不良品原因初評(描述)	NVARCHAR(50)
	
	//不良品缺陷-----------------------------------------------------------------
	private String[] mainCode;
	private String[] subCode;
	private String[] otherDescribe;	
	private String commonUser;
	javax.servlet.ServletRequest httpRequest;
	public javax.servlet.ServletRequest getHttpRequest() {	return httpRequest;	}
	public void setHttpRequest(javax.servlet.ServletRequest r) {	this.httpRequest = r;	}
	
	//案件分級 -------------------------------------------------------------------
	private String drg03Update; //是否新增修改drg03
	private String drg03id; //初評資料檔ID
	private String assessDate03;	//初評日期	VARCHAR(7)
	private String assessMan03;	//初評人員	VARCHAR(50)
	private String firstResult03;	//不良品風險評估結果	VARCHAR(2) 名稱與登錄相同+1區隔..
	private String notifierAging03;	//通報時效	VARCHAR(1)
	private String notifierQuality03;	//通報品質	VARCHAR(1)
	private String intervalDays03;	//間隔天數	VARCHAR(3)
	private String remark03;	//備註	NVARCHAR(100)
	
	private String hisData1; //歷年本藥品之通報件數
	private String hisData2; //歷年本藥品同此次瑕疵之通報件數
	private String hisData3; //一年內本藥品之通報件數
	private String hisData4; //一年內本藥品同此次瑕疵之通報件數
	private String hisData5; //一年內本藥品同此次瑕疵之高風險通報件數
	private String hisData6; //一年內本藥品同此次瑕疵案件
	private String hisData7; //一年內本藥品同此次瑕疵之高風險案件	
	private String hisApplNoY; //一年內本藥品同此次瑕疵案件之同批號的各案件編號
	private String hisApplNoN; //一年內本藥品同此次瑕疵案件之不同批號的各案件編號
	
	//分級確認 -------------------------------------------------------------------
	private String drg04Update; //是否新增修改drg03
	private String drg04id; //初評資料檔ID
	private String gradeMan04;	//分級確認人員	VARCHAR(50)
	private String gradeDate04;	//分級確認日期	VARCHAR(7)
	private String drgLev04;	//風險等級	VARCHAR(1)
	
	private String q_isQuery;
	private String q_id;				//ID
	private String q_applNoS;			//案件編號S
	private String q_applNoE;			//案件編號E
	private String q_occurDateS;		//發生日期S
	private String q_occurDateE;		//發生日期E
	private String q_notifierRepDateS;	//通報日期S
	private String q_notifierRepDateE;	//通報日期E	
	private String q_enrolledDateS;	    //收案日期S
	private String q_enrolledDateE;	    //收案日期E
	private String q_notifierSource;  	//通報來源
	private String q_notifierDept;		//通報者服務機構
	private String q_notifierType;		//通報單位
	private String q_permitNo;			//許可證字號
	private String q_chProduct;			//藥品品名
	private String q_ingredient;		//有效成份名稱
	private String q_applicationName;	//申請商
	private String q_manufactorName;	//製造廠
	private String q_status;			//案件狀態
	private String[] q_drgLev;			//風險等級-drg0004Db
	private String[] q_mainCode;		//不良品缺陷
	private String[] q_subCode;			
	private String q_permitKey;	


	@Override
	public Object doQueryOne() throws Exception {
		COMPLE0101F obj = this;		
		Drg0001Db c = (Drg0001Db) View.getObject(" from Drg0001Db where id=" + Common.getInt(obj.getId()));		
		System.out.println("[TCBW]-[COMPLE0101F]-[doQueryOne] : " + obj.getId());		
		if (c!=null) {
			obj.setApplNo(Common.get(c.getApplNo()));
			obj.setStatus(Common.get(c.getStatus()));			
			//基本資料------------------------------------------------------------------	
			obj.setNotifier(Common.get(obj.getNotifier()));
			obj.setOccurDate(Common.get(c.getOccurDate()));		
			obj.setNotifierRepDate(Common.get(c.getNotifierRepDate()));		
			obj.setNotifierRevDate(Common.get(c.getNotifierRevDate()));
			obj.setEnrolledDate(Common.get(c.getEnrolledDate()));
			obj.setNotifierSource(Common.get(c.getNotifierSource()));			
			obj.setNotifierName(Common.get(c.getNotifierName()));
			obj.setNotifierUserID(Common.get(c.getNotifierUserID()));			
			obj.setNotifierDept(Common.get(c.getNotifierDept()));
			obj.setNotifierDeptID(Common.get(c.getNotifierDeptID()));
			obj.setNotifierTel(Common.get(c.getNotifierTel()));
			obj.setNotifierTelArea(Common.get(c.getNotifierTelArea()));	
			obj.setNotifierTelExt(Common.get(c.getNotifierTelExt()));	
			obj.setNotifierCounty(Common.get(c.getNotifierCounty()));	
			obj.setNotifierZipCode(Common.get(c.getNotifierZipCode()));	
			obj.setNotifierAddress(Common.get(c.getNotifierAddress()));		
			obj.setNotifierEmail(Common.get(c.getNotifierEmail()));		
			obj.setNotifierType(Common.get(c.getNotifierType()));
			obj.setNotifierTitle(Common.get(c.getNotifierTitle()));	
			//不良藥品資料--------------------------------------------------------------	
			obj.setApplNo(Common.get(c.getApplNo()));
			obj.setPermitKey(Common.get(c.getPermitKey()));
			obj.setPermitNo(Common.get(c.getPermitNo()));
			obj.setChProduct(Common.get(c.getChProduct()));
			obj.setEnProduct(Common.get(c.getEnProduct()));
			obj.setIngredient(Common.get(c.getIngredient()));		
			obj.setContent(Common.get(c.getContent()));		
			obj.setMedModel(Common.get(c.getMedModel()));		
			obj.setMedModelOther(Common.get(c.getMedModelOther()));
			obj.setMedPackage(Common.get(c.getMedPackage()));		
			obj.setMedPackageOther(Common.get(c.getMedPackageOther()));
			obj.setApplicationID(Common.get(c.getApplicationID()));
			obj.setApplicationName(Common.get(c.getApplicationName()));		
			obj.setManufactorName(Common.get(c.getManufactorName()));		
			obj.setManufactorNo(Common.get(c.getManufactorNo()));
			obj.setManufactorCountry(Common.get(c.getManufactorCountry()));	
			obj.setManufactorDate(Common.get(c.getManufactorDate()));		
			obj.setExpirationDate(Common.get(c.getExpirationDate()));		
			obj.setStorage(Common.get(c.getStorage()));		
			obj.setStorageOther(Common.get(c.getStorageOther()));		
			obj.setIsFindYn(Common.get(c.getIsFindYn()));		
			obj.setIsSingleYn(Common.get(c.getIsSingleYn()));		
			obj.setSameNum(Common.get(c.getSameNum()));
			obj.setDiffNum(Common.get(c.getDiffNum()));		
			obj.setIsHarmYn(Common.get(c.getIsHarmYn()));
			obj.setIsUsedYn(Common.get(c.getIsUsedYn()));
			obj.setEvenContactYn(Common.get(c.getEvenContactYn()));		
			obj.setDealWith(Common.get(c.getDealWith()));		
			obj.setIsContactYn(Common.get(c.getIsContactYn()));
			obj.setDefectDesc(Common.get(c.getDefectDesc()));
			obj.setFirstResult(Common.get(c.getFirstResult()));		
			obj.setFirstRemark(Common.get(c.getFirstRemark()));			
			String drg02Hql = " from Drg0002Db where 1=1 and drg0001Db.id="+Common.get(getId());
			java.util.List objList = ServiceGetter.getInstance().getCommonService().load(drg02Hql+" order by id asc");
			String[] mainCode = new String[objList.size()];
	        String[] subCode = new String[100];
	        String subCodeList ="";  //案件分級查詢用
			if(objList!=null && objList.size()>0)
			{
				java.util.Iterator it = objList.iterator();
				int i =0,k =0;
				while (it.hasNext()) 
				{					
					Drg0002Db o = (Drg0002Db) it.next();					
					mainCode[i] = String.valueOf( o.getMainCode() );
					String[] subList = o.getSubCode().split(",");
					for(int j=0; j<subList.length; j++){
						subCodeList += "subCode like ('%"+subList[j]+"%') or ";
						if(Common.get(subList[j]) != ""){
							subCode[k] = subList[j];
							k++;
						}
					}
					i++;
				}
			}
			if(subCodeList.length() > 3) subCodeList = subCodeList.substring(0, subCodeList.length()-3);
			obj.setMainCode(mainCode);
			obj.setSubCode(subCode);
			
			String drg03Hql = " from Drg0003Db where applNo="+Common.sqlChar(c.getApplNo())+" order by id desc";			
			Drg0003Db drg03 = (Drg0003Db) View.getObject(drg03Hql);
			if(drg03 != null){
				 obj.setDrg03id(Common.get(drg03.getId()));
			     obj.setAssessMan03(Common.get(drg03.getAssessMan()));
			     obj.setAssessDate03(Common.get(drg03.getAssessDate()));
			     obj.setFirstResult03(Common.get(drg03.getFirstResult()));
			     obj.setNotifierAging03(Common.get(drg03.getNotifierAging()));
			     obj.setNotifierQuality03(Common.get(drg03.getNotifierQuality()));
			     obj.setIntervalDays03(Common.get(drg03.getIntervalDays()));
			     obj.setRemark03(Common.get(drg03.getRemark()));			     
			     
			     //歷年本藥品之通報件數
			     String hql1 = " from Drg0001Db where permitKey="+Common.sqlChar(getPermitKey())+" and permitNo="+Common.sqlChar(getPermitNo())+
			                   " and applNo is not null and applNo <> '' and ( status in ('25','30','40','41','42','43','50','90') or applNo="+Common.sqlChar(c.getApplNo())+") ";
			     int hisData1 = ServiceGetter.getInstance().getCommonService().loadCount(hql1);
			     obj.setHisData1(Common.get(hisData1));
	            
			     //歷年本藥品同此次瑕疵之通報件數
			     if(subCodeList!=null&&!"".equals(subCodeList)){
				     String hql2 = " from Drg0001Db where permitKey="+Common.sqlChar(getPermitKey())+" and permitNo="+Common.sqlChar(getPermitNo())+
				                   " and applNo is not null and applNo <> '' and ( status in ('25','30','40','41','42','43','50','90') or applNo="+Common.sqlChar(c.getApplNo())+") "+
				                   " and id in ( select distinct drg0001Db.id from Drg0002Db where ( "+subCodeList+" ))";
				     int hisData2 = ServiceGetter.getInstance().getCommonService().loadCount(hql2);
				     obj.setHisData2(Common.get(hisData2));
			     }
			     
			     //一年內本藥品之通報件數
			     String hql3 = " from Drg0001Db where permitKey="+Common.sqlChar(getPermitKey())+" and permitNo="+Common.sqlChar(getPermitNo())+
			                   " and applNo is not null and applNo <> '' and ( status in ('25','30','40','41','42','43','50','90') or applNo="+Common.sqlChar(c.getApplNo())+") "+
			                   " and enrolledDate >="+Common.sqlChar(Datetime.getDateSubtraction("y",1,Datetime.getYYYMMDD()));
	             int hisData3 = ServiceGetter.getInstance().getCommonService().loadCount(hql3);
	             obj.setHisData3(Common.get(hisData3));
			     
	             
	             //一年內本藥品同此次瑕疵之通報件數
	             if(subCodeList!=null&&!"".equals(subCodeList)){
				     String hql4 = " from Drg0001Db where permitKey="+Common.sqlChar(getPermitKey())+" and permitNo="+Common.sqlChar(getPermitNo())+
				                   " and applNo is not null and applNo <> '' and ( status in ('25','30','40','41','42','43','50','90') or applNo="+Common.sqlChar(c.getApplNo())+") "+
				                   " and enrolledDate >="+Common.sqlChar(Datetime.getDateSubtraction("y",1,Datetime.getYYYMMDD()))+
				                   " and id in ( select distinct drg0001Db.id from Drg0002Db where ( "+subCodeList+" ))";
		             int hisData4 = ServiceGetter.getInstance().getCommonService().loadCount(hql4);
		             obj.setHisData4(Common.get(hisData4));
	             }
	             
	             //一年內本藥品同此次瑕疵之高風險通報件數
	             if(subCodeList!=null&&!"".equals(subCodeList)){
				     String hql5 = " from Drg0001Db where permitKey="+Common.sqlChar(getPermitKey())+" and permitNo="+Common.sqlChar(getPermitNo())+
				                   " and applNo is not null and applNo <> '' and ( status in ('25','30','40','41','42','43','50','90') or applNo="+Common.sqlChar(c.getApplNo())+") "+
				                   " and applNo in (select b.applNo from Drg0004Db b where b.drgLev in ('02','03'))"+
		                           " and enrolledDate >="+Common.sqlChar(Datetime.getDateSubtraction("y",1,Datetime.getYYYMMDD()))+
		                           " and id in ( select distinct drg0001Db.id from Drg0002Db where ( "+subCodeList+" ))";
		             int hisData5 = ServiceGetter.getInstance().getCommonService().loadCount(hql5);
		             obj.setHisData5(Common.get(hisData5));
	             }
	             
	             //一年內本藥品同此次瑕疵案件
	             String hql6 = " select manufactorNo,count(applNo) from Drg0001Db where permitKey="+Common.sqlChar(getPermitKey())+" and permitNo="+Common.sqlChar(getPermitNo())+
	                           " and applNo is not null and applNo <> '' and ( status in ('25','30','40','41','42','43','50','90') or applNo="+Common.sqlChar(c.getApplNo())+") "+
	                           " and enrolledDate >="+Common.sqlChar(Datetime.getDateSubtraction("y",1,Datetime.getYYYMMDD()))+
	                           " group by manufactorNo";
	             java.util.List hql6List = ServiceGetter.getInstance().getCommonService().load(hql6);
	             String hisData6 ="";
			     if(hql6List!=null && hql6List.size()>0)				
			     {
					for (int i=0; i<hql6List.size(); i++) {
						Object[] o = (Object[]) hql6List.get(i);
						hisData6 += "["+o[0]+"] : "+"<input class=\"field_RO\" type=\"text\" size=\"3\" value=\""+o[1]+"\" >"+"件 ,";
					}	    	 
			     }
			     if(hisData6 != "") hisData6 = hisData6.substring(0,hisData6.length()-1);
			     else hisData6 = "<input class=\"field_RO\" type=\"text\" size=\"3\" value=\"0\" >"+"件"; 
			     obj.setHisData6(hisData6);
			     
	             //一年內本藥品同此次瑕疵之高風險案件
			     if(subCodeList!=null&&!"".equals(subCodeList)){
		             String hql7 = " select manufactorNo,count(applNo) from Drg0001Db where permitKey="+Common.sqlChar(getPermitKey())+" and permitNo="+Common.sqlChar(getPermitNo())+
		                           " and applNo is not null and applNo <> '' and ( status in ('25','30','40','41','42','43','50','90') or applNo="+Common.sqlChar(c.getApplNo())+") "+
		                           " and enrolledDate >="+Common.sqlChar(Datetime.getDateSubtraction("y",1,Datetime.getYYYMMDD()))+
		                           " and applNo in (select b.applNo from Drg0004Db b where b.drgLev in ('02','03'))"+
		                           " and id in ( select distinct drg0001Db.id from Drg0002Db where ( "+subCodeList+" ))"+
		                           " group by manufactorNo"; 
		             java.util.List hql7List = ServiceGetter.getInstance().getCommonService().load(hql7);
		             String hisData7 ="";
		             if(hql7List!=null && hql7List.size()>0)
		             {	
		            	 for (int i=0; i<hql7List.size(); i++) {		
		            		 Object[] o = (Object[]) hql7List.get(i);	
		            		 hisData7 += "["+o[0]+"] : "+"<input class=\"field_RO\" type=\"text\" size=\"3\" value=\""+o[1]+"\" >"+"件 ,";
		            	 }
		             }
		             if(hisData7 != "") hisData7 = hisData7.substring(0,hisData7.length()-1);
		             else hisData7 = "<input class=\"field_RO\" type=\"text\" size=\"3\" value=\"0\" >"+"件";  		
		             obj.setHisData7(hisData7);
			     }
			     
	             
	     		 //取得風險等級
	     		 String drgLev = View.getLookupField("select drgLev from Drg0004Db where applNo="+Common.sqlChar(obj.getApplNo()));
	             //B級案件
	             if("04".equals(drgLev)){
	            	if(subCodeList!=null&&!"".equals(subCodeList)){
		             	//一年內本藥品同此次瑕疵案件「同批號的各案件編號、不同批號的各案件編號」      
		             	String sql8 = " select manufactorNo,applNo from Drg0001Db where permitKey="+Common.sqlChar(getPermitKey())+" and permitNo="+Common.sqlChar(getPermitNo())+ 
		             	              " and applNo is not null and applNo <> '' and ( status in ('25','30','40','41','42','43','50','90') or applNo="+Common.sqlChar(c.getApplNo())+") "+
		             	              " and enrolledDate >="+Common.sqlChar(Datetime.getDateSubtraction("y",1,Datetime.getYYYMMDD()))+                   
		             	              " and id in ( select distinct drg0001Db.id from Drg0002Db where ( "+subCodeList+" ))";		
		             	java.util.List objList2 = ServiceGetter.getInstance().getCommonService().load(sql8+" order by id asc");		
		             	String hisApplNoY="",hisApplNoN="";		
		             	if(objList2!=null && objList2.size()>0)		
		             	{			
		             		java.util.Iterator it = objList2.iterator();			
		             		while (it.hasNext())			
		             		{				
		             			Object[] itobj = (Object[])it.next();				
		             			if(Common.get(itobj[0]).equals(getManufactorNo())) hisApplNoY = itobj[1]+",";
		             			else hisApplNoN = itobj[1]+",";
		             		}	
		             	}		
		             	if(hisApplNoY!="") hisApplNoY = hisApplNoY.substring(0,hisApplNoY.length()-1);		
		             	if(hisApplNoN!="") hisApplNoN = hisApplNoN.substring(0,hisApplNoN.length()-1);		
		             	obj.setHisApplNoY(hisApplNoY);        
		             	obj.setHisApplNoN(hisApplNoN);
	            	}
	             }			
			}
			
			String drg04Hql = " from Drg0004Db where applNo="+Common.sqlChar(c.getApplNo());			
			Drg0004Db drg04 = (Drg0004Db) View.getObject(drg04Hql);
			if (c!=null) {
				obj.setDrg04id(Common.get(drg04.getId()));
			    obj.setDrgLev04(Common.get(drg04.getDrgLev()));
			    obj.setGradeDate04(Common.get(drg04.getGradeDate()));
			}			
		}
		this.setState("queryOneSuccess");
		return obj;	
	}

	@Override
	public Object doQueryAll() throws Exception {
		if (!"".equals(getQ_isQuery())){
			setQ_id("");
		}
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		String hql = " from Drg0001Db as a where 1 = 1 and trans = 'Y'";

		if(!"".equals(getQ_id())){
			hql += " and id = " + Common.getLong(getQ_id());
		}else{
			if(!"".equals(getQ_applNoS())) 
				hql += " and applNo >= " +  Common.sqlChar(getQ_applNoS());
			if(!"".equals(getQ_applNoE())) 
				hql += " and applNo <= " + Common.sqlChar(getQ_applNoE());
			
			if(!"".equals(getQ_occurDateS())) 
				hql += " and occurDate >= " + Common.sqlChar(getQ_occurDateS());
			if(!"".equals(getQ_occurDateE()))
				hql += " and occurDate <= " + Common.sqlChar(getQ_occurDateE());
			
			if(!"".equals(getQ_enrolledDateS())) 
				hql += " and enrolledDate >= " + Common.sqlChar(getQ_enrolledDateS());
			if(!"".equals(getQ_enrolledDateE())) 
				hql += " and enrolledDate <= " + Common.sqlChar(getQ_enrolledDateE());
			
			if(!"".equals(getQ_notifierRepDateS())) 
				hql += " and notifierRepDate >= " + Common.sqlChar(getQ_notifierRepDateS());
			if(!"".equals(getQ_notifierRepDateE())) 
				hql += " and notifierRepDate <= " + Common.sqlChar(getQ_notifierRepDateE());
			
			if(!"".equals(getQ_notifierSource())) 
				hql += " and notifierSource = " + Common.sqlChar(getQ_notifierSource());
			
			if(!"".equals(getQ_notifierDept())) 
				hql += " and notifierDept like " + Common.sqlChar("%" + getQ_notifierDept() + "%");

			if(!"".equals(getQ_permitKey())) 
				hql += " and permitKey = " + Common.sqlChar(getQ_permitKey());
			
			if(!"".equals(getQ_permitNo())) 
				hql += " and permitNo = " + Common.sqlChar(getQ_permitNo());
			
			if(!"".equals(getQ_chProduct())) 
				hql += " and (chProduct like " + Common.sqlChar("%" + getQ_chProduct() + "%") + " or enProduct like " + Common.sqlChar("%" + getQ_chProduct() + "%") + ")";
			
			if(!"".equals(getQ_ingredient())) 
				hql += " and ingredient like " + Common.sqlChar("%" + getQ_ingredient() + "%");
			if(!"".equals(getQ_notifierType()))
				hql += " and notifierType = " + Common.sqlChar(getQ_notifierType());
			if(!"".equals(getQ_applicationName())) 
				hql += " and applicationName like " + Common.sqlChar("%" + getQ_applicationName() + "%");
			
			if(!"".equals(getQ_status())) 
				hql += " and status = " + Common.sqlChar(getQ_status());
			
			if(!"".equals(getQ_manufactorName())) 
				hql += " and manufactorName like " + Common.sqlChar("%" + getQ_manufactorName() + "%");
			//風險等級
			if(getQ_drgLev() != null){
				String temp = "";
				for(String a:getQ_drgLev()){
					temp +="'" + a + "',";
				}
				hql += " and a.applNo in (select applNo from Drg0004Db where drgLev in (" +  temp.substring(0, temp.length()-1) + "))";
			}
		
			String subCodeList ="";
			String mainCodeList ="";
			if(!"".equals(Common.get(getQ_mainCode())) || !"".equals(Common.get(getQ_subCode()))){
				if(!"".equals(Common.get(getQ_mainCode()))){
					for(int j=0;j<getQ_mainCode().length;j++){
						if(!"".equals(Common.get(mainCodeList))) mainCodeList += ",";
						mainCodeList += Common.sqlChar(getQ_mainCode()[j]);
					}
				}
				if(!"".equals(Common.get(getQ_subCode()))){
					for(int k=0;k<getQ_subCode().length;k++){
						if(k!=0) subCodeList += " or ";	
						else subCodeList += " ( ";
						subCodeList += " b.subCode like " + Common.sqlChar("%"+getQ_subCode()[k]+"%");			
					}
				}
				if(subCodeList == ""){				
					hql += " and a.id in ( select b.drg0001Db.id from Drg0002Db b where b.mainCode in ("+mainCodeList+"))";
				}else{
					hql += " and a.id in ( select b.drg0001Db.id from Drg0002Db b where b.mainCode in ("+mainCodeList+") and "+subCodeList+" or b.subCode='' ))";
				}
			}			
		}
		System.out.println("[TCBW]-[DRGIN0101Q]-[不良品-QUERYALL] : " + hql);
		
		this.processCurrentPageAttribute(ServiceGetter.getInstance().getTcbwService().loadCount(hql));
		if (getTotalRecord() > 0){
			if (getState().indexOf("query")<0) 
				ServiceGetter.getInstance().getCommonService().getHibernateTemplate().clear();
			java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(hql + " order by id DESC", this.getRecordStart(), this.getPageSize());
			if (objList != null && objList.size() > 0) {
				//使用map先將資料撈出來
				java.util.Map<String, String> statusMap = TCBWCommon.getCommonCodeMap("DRGST1");
				java.util.Map<String, String> dpdMap = TCBWCommon.getCommonCodeMap("DRG0105"); //不良品主代碼				
				java.util.Map<String, String> subMap = TCBWCommon.getMap("select dpdKind,dpdKindName from Drg1001Db"); //缺陷子代碼
				
				for(Object dtlObj : objList) {				
					Drg0001Db dtl = (Drg0001Db)dtlObj;	
					
					String hql2 = " from Drg0002Db where 1=1 and drg0001Db.id="+Common.getLong(dtl.getId());
					java.util.List drg02List = ServiceGetter.getInstance().getCommonService().load(hql2+" order by id asc");
					String dpdList ="";
					if(drg02List!=null && drg02List.size()>0)
					{
						java.util.Iterator it = drg02List.iterator();
						while (it.hasNext()) 
						{					
							Drg0002Db o = (Drg0002Db) it.next();
							if(dpdList.length()>0) dpdList += ", ";
							dpdList += dpdMap.get(o.getMainCode())+":";
							
							if(!"".equals(Common.get(o.getSubCode()))){
								String subCodeList ="";
								String[] subCode = Common.get(o.getSubCode()).split(",");
								for(int j=0; j<subCode.length; j++){
									if(subCodeList.length()>0) subCodeList += ",";
									subCodeList += subMap.get(subCode[j]);
									if("ZZ".equals(subCode[j].substring(2))){
										subCodeList += (":"+o.getOtherDescribe());										
									}
								}
								dpdList += subCodeList;
							}else{							
								dpdList += o.getOtherDescribe();
							}
						}
					}
					drg02List.clear();					
					if(dpdList != "") dpdList = Common.get(dpdList).substring(0,Common.get(dpdList).length());
					
					String[] rowArray = new String[13];
					
					rowArray[0] = Common.get(dtl.getId());											
					rowArray[1] = Common.get(dtl.getApplNo());
					rowArray[2] = Common.formatYYYMMDD(Common.get(dtl.getNotifierRepDate()),4);
					rowArray[3] = Common.get(View.getCommonCodeName("DRGPKID", dtl.getPermitKey()))+"-"+Common.get(dtl.getPermitNo());
					rowArray[4] = Common.get(dtl.getChProduct());
					rowArray[5] = Common.get(dtl.getEnProduct());
					rowArray[6] = Common.get(dtl.getIngredient());
					rowArray[7] = Common.get(dtl.getApplicationName());
					rowArray[8] = Common.get(dtl.getManufactorName());
					rowArray[9] = Common.get(dtl.getManufactorNo());
					rowArray[10] = Common.get(dpdList);
					rowArray[11] = Common.get(statusMap.get(dtl.getStatus()));
					//rowArray[12] =  "<input type='button' class='toolbar_default' name='btn_Data' value='明　細' onClick=\"listContainerRowClick('"+dtl.getId()+"');queryOne('" + dtl.getId() + "');\"> ";
					arrList.add(rowArray);
				}
				objList.clear();
			}		
		}
		return arrList;
	}

	@Override
	public void doCreate() throws Exception {}

	@Override
	public void doUpdate() throws Exception {
		
		Drg0001Db obj = (Drg0001Db)View.getObject(" from Drg0001Db where id = " + Common.getLong(getId()));

        if(obj != null)
		{        	
        	java.util.List<Drg0002Db> saveListDrg0002Db = new java.util.ArrayList<Drg0002Db>();	
    		java.util.List<Drg0002Db> updateListDrg0002Db = new java.util.ArrayList<Drg0002Db>();
    		java.util.List<Drg0002Db> deleteListDrg0002Db = new java.util.ArrayList<Drg0002Db>();
    		java.util.List<Drg0002Db> drg0002DbList =null ;
    		java.util.List<Drg0002Db> drg0002DbList3 =null ;
    		java.util.Map<String,Drg0002Db> drg0002DbMap=null;
    		String yyymmdd = Datetime.getYYYMMDD();
    		String hhmmss = Datetime.getHHMMSS();
    		
    		//基本資料------------------------------------------------------------------
			obj.setOccurDate(Common.get(getOccurDate()));		
			obj.setNotifierRevDate(Common.get(getNotifierRevDate()));
			obj.setNotifierRepDate(Common.get(getNotifierRepDate()));
			obj.setEnrolledDate(Common.get(getEnrolledDate()));
			obj.setNotifierSource(Common.get(getNotifierSource()));
			obj.setNotifierName(Common.get(getNotifierName()));
			obj.setNotifierUserID(Common.get(getNotifierUserID()));			
			obj.setNotifierDept(Common.get(getNotifierDept()));
			obj.setNotifierDeptID(Common.get(getNotifierDeptID()));
			obj.setNotifierTel(Common.get(getNotifierTel()));
			obj.setNotifierTelArea(Common.get(getNotifierTelArea()));	
			obj.setNotifierTelExt(Common.get(getNotifierTelExt()));	
			obj.setNotifierCounty(Common.get(getNotifierCounty()));	
			obj.setNotifierZipCode(Common.get(getNotifierZipCode()));	
			obj.setNotifierAddress(Common.get(getNotifierAddress()));		
			obj.setNotifierEmail(Common.get(getNotifierEmail()));		
			obj.setNotifierType(Common.get(getNotifierType()));
			obj.setNotifierTitle(Common.get(getNotifierTitle()));
    		
        	//不良藥品資料--------------------------------------------------------------
			obj.setPermitKey(Common.get(getPermitKey()));
			obj.setPermitNo(Common.get(getPermitNo()));
			obj.setChProduct(getChProduct());
			obj.setEnProduct(getEnProduct());
			obj.setIngredient(Common.get(getIngredient()));
			obj.setContent(Common.get(getContent()));
			obj.setMedModel(Common.get(getMedModel()));
			obj.setMedModelOther(Common.get(getMedModelOther()));
			obj.setMedPackage(Common.get(getMedPackage()));
			obj.setMedPackageOther(Common.get(getMedPackageOther()));
			obj.setApplicationID(Common.get(getApplicationID()));
			obj.setApplicationName(Common.get(getApplicationName()));
			obj.setManufactorName(Common.get(getManufactorName()));
			obj.setManufactorNo(Common.get(getManufactorNo()));
			obj.setManufactorDate(Common.get(getManufactorDate()));
			obj.setExpirationDate(Common.get(getExpirationDate()));
			obj.setStorage(Common.get(getStorage()));
			obj.setStorageOther(Common.get(getStorageOther()));
			obj.setIsFindYn(Common.get(getIsFindYn()));
			obj.setIsSingleYn(Common.get(getIsSingleYn()));
			obj.setSameNum(Common.get(getSameNum()));
			obj.setDiffNum(Common.get(getDiffNum()));
			obj.setIsHarmYn(Common.get(getIsHarmYn()));
			obj.setIsUsedYn(Common.get(getIsUsedYn()));
			obj.setEvenContactYn(Common.get(getEvenContactYn()));
			obj.setDealWith(Common.get(getDealWith()));
			obj.setIsContactYn(Common.get(getIsContactYn()));
			obj.setFirstResult(Common.get(getFirstResult()));
			obj.setFirstRemark(Common.get(getFirstRemark()));			
			obj.setChargeMan(Common.get(getUserID()));
			
			obj.setModifier(getUserID());
			obj.setModifyDate(yyymmdd);
			obj.setModifyTime(hhmmss);
			
			if(null != getMainCode()){
				Map<String, String> subMap = new HashMap<String, String>();
				drg0002DbList = ServiceGetter.getInstance().getCommonService().load("from Drg0002Db where drg0001Db.id="+Common.getLong(getId()));	
				drg0002DbMap= new java.util.HashMap<String,Drg0002Db>();
				for(Drg0002Db obj1:drg0002DbList)
				{
					drg0002DbMap.put(Common.get(obj1.getMainCode()),obj1);			
				}   
				
				if(drg0002DbList!=null) drg0002DbList.clear();

				for(int i=0;i<getMainCode().length;i++){
					String mainCode = getMainCode()[i];
					String subCodeStr ="";
					for(int j=0;j<getSubCode().length;j++){
						if(getSubCode()[j].substring(0,2).equals(mainCode)){
							if(subCodeStr.length() > 0){
								subCodeStr += ",";
							}
							subCodeStr += getSubCode()[j];
						}
					}
					subMap.put(mainCode, subCodeStr);
				}
				
				if(null != subMap && !subMap.isEmpty()){
					StringBuilder dtlKey = new StringBuilder("-2,-1");		
					for(String mainCode:subMap.keySet()){
						Drg0002Db drg02 = drg0002DbMap.get(mainCode);
						if(null == drg02){
							drg02 = new Drg0002Db();
							drg02.setDrg0001Db(obj);
						}
						//主代碼				
						drg02.setMainCode(Common.get(mainCode));
						//子代碼						
						drg02.setSubCode(subMap.get(mainCode));			                     
						//說明描述
						java.util.List codeList = ServiceGetter.getInstance().getCommonService().load("select codeId, codeName from CommonCode where commonCodeKind.codeKindId='DRG0105' and isStop='N' order by codeId");
						if (codeList!=null && codeList.size()>0) {
							for(int i=0 ; i< codeList.size();i++){
								Object[] o = (Object[]) codeList.get(i);
								if(Common.get(o[0]).equals(mainCode)){
									drg02.setOtherDescribe(Common.get(getOtherDescribe()[i]));
									break;
								}
							}
						}	
						if (null != drg02.getId() && drg02.getId() > 0){
							dtlKey.append(",").append(drg02.getId());
							updateListDrg0002Db.add(drg02);					
						}else{
							saveListDrg0002Db.add(drg02);
						}
					}
					deleteListDrg0002Db.addAll(ServiceGetter.getInstance().getCommonService().load(" from Drg0002Db where drg0001Db.id=" + Common.getLong(obj.getId()) + " and id not in (" + dtlKey.toString() + ")"));
				}else{
					//刪除資料				
					drg0002DbList3 = ServiceGetter.getInstance().getCommonService().load("from Drg0002Db where drg0001Db.id="+Common.getLong(getId()));
					deleteListDrg0002Db.addAll(drg0002DbList3);
					if(drg0002DbList3 != null) drg0002DbList3.clear();
				}
			}		

			ServiceGetter.getInstance().getTcbwService().getDrginDao().saveOrUpdate(obj);
			ServiceGetter.getInstance().getTcbwService().getDrginDao().saveOrUpdateDrg0002Db(saveListDrg0002Db, updateListDrg0002Db, deleteListDrg0002Db);
			setId(Common.get(obj.getId()));
			
			if(saveListDrg0002Db != null) saveListDrg0002Db.clear();
			if(updateListDrg0002Db != null) updateListDrg0002Db.clear();
			if(deleteListDrg0002Db != null) deleteListDrg0002Db.clear();
			
			if("Y".equals(Common.get(getDrg03Update()))){
				Drg0003Db drg03 = null;
				if(!"".equals(Common.get(getDrg03id()))){
					drg03 = (Drg0003Db)View.getObject(" from Drg0003Db where id = " + Common.get(getDrg03id()));
				}else{
					drg03 = new Drg0003Db();    
				}
				drg03.setApplNo(obj.getApplNo());
				drg03.setAssessDate(Common.get(getAssessDate03()));
				drg03.setAssessMan(getUserID());
				drg03.setFirstResult(Common.get(getFirstResult03()));
				drg03.setNotifierAging(Common.get(getNotifierAging03()));
				drg03.setNotifierQuality(Common.get(getNotifierQuality03()));
				drg03.setIntervalDays(Common.get(getIntervalDays03()));
				drg03.setRemark(Common.get(getRemark03()));
				drg03.setModifier(getUserID());
				drg03.setModifyDate(yyymmdd);
				drg03.setModifyTime(hhmmss);				
				ServiceGetter.getInstance().getTcbwService().getDrginDao().saveOrUpdate(drg03);			
			}
			
			if("Y".equals(Common.get(getDrg04Update()))){
				Drg0004Db drg04 = null;
				if(!"".equals(Common.get(getDrg04id()))){
					drg04 = (Drg0004Db)View.getObject(" from Drg0004Db where id = " + Common.get(getDrg04id()));
				}else{
					drg04 = new Drg0004Db();    
				}
				drg04.setApplNo(obj.getApplNo());
				drg04.setDrgLev(Common.get(getDrgLev04()));
				drg04.setGradeDate(Common.get(getGradeDate04()));
				drg04.setModifier(getUserID());
				drg04.setModifyDate(yyymmdd);
				drg04.setModifyTime(hhmmss);				
				ServiceGetter.getInstance().getTcbwService().getDrginDao().saveOrUpdate(drg04);			
			}
			
		}	
	}

	@Override
	public void doDelete() throws Exception {}
	
	
	public static String getCheckBoxOption2(String className, String mainCode, String subCode, String otherDescribe, String sql, String[] selectedCheckBox1, String[] selectedCheckBox2 ,String id) {

    	StringBuilder sb = new StringBuilder();  	
    	java.util.List list = PersistenceServiceGetter.getInstance().getHibernateTemplate().find(sql);
    	String otherDescribeValue ="";
    	if (list!=null && list.size()>0) {    		
    		int j=0;
        	for(int i=0 ; i<list.size();i++){
        		Object[] o = (Object[]) list.get(i);
        		//放置說明的value
        		otherDescribeValue = View.getLookupField(" select otherDescribe from Drg0002Db where drg0001Db.id="+Common.getLong(id) +" and mainCode="+Common.sqlChar(Common.get(o[0])));
        		sb.append(o[1]).append("：");
        		sb.append("<input style=\"display:none\" class=\"" ).append( "field" ).append( "\" type=\"checkbox\" name=\"" ).append( mainCode ).append( "\" value=\"" ).append( o[0] ).append( "\"");
        		if (selectedCheckBox1!=null && selectedCheckBox1.length>0) {
        			for (j=0; j<selectedCheckBox1.length; j++) {
        				if(Common.get(o[0]).equals(selectedCheckBox1[j])) sb.append(" checked");
        			}
        		}
        		sb.append(">");
        		//sb.append("  onClick=\"updateSubCode(this);\" >").append(o[1]+"：").append(" ").append("<br>");
        		String otherDescribeType ="hidden";
        		String showType ="1"; //描述說明顯示方式
        		
        		//第2層 
        		java.util.List list2 = PersistenceServiceGetter.getInstance().getHibernateTemplate().find("select dpdKind,dpdKindName from Drg1001Db where substring(dpdKind,1,2)='"+o[0]+"' and isStop='N' order by dpdKind");
        		if (list2!=null && list2.size()>0) {
        			int l=0;
        			for(int k=0 ; k<list2.size(); k++){
        				Object[] o2 = (Object[]) list2.get(k);
        				sb.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
        				sb.append("<input class=\"" ).append( className ).append( "\" type=\"checkbox\" name=\"" ).append( subCode ).append( "\" value=\"" ).append( o2[0] ).append( "\"");
                		//若勾選"顏色異常"，需提示"若為雙/三腔軟袋於操作前發現顏色異常，請詳加描述隔膜及外袋之完整性"
        				if("0101".equals(Common.get(o2[0]))){
        					sb.append(" onclick=\"alertAbnormalColor(this);\"");
        				}        					
        				if (selectedCheckBox2!=null && selectedCheckBox2.length>0) {
                			for (l=0; l<selectedCheckBox2.length; l++) {
                				if(Common.get(o2[0]).equals(selectedCheckBox2[l])) sb.append("checked");
                			}
                		}
                		sb.append("  onClick=\"updateMainCode(this);\" ");
                		sb.append(">").append(o2[1]).append(" ");
                		//其他選項
                		if("ZZ".equals(Common.get(o2[0]).substring(2,4))){
                			otherDescribeType = "text";                			
                		}
        			}
        		}else{
        			//其他說明
        			otherDescribeType = "text";
        			showType="2";
        		}
        		if("2".equals(showType)){
        		    sb.append("請描述：&nbsp;&nbsp;<input class=\"").append( className ).append( "\" type=\"").append(otherDescribeType).append( "\" size=100 maxlength=100 name=\"" ).append( otherDescribe ).append( "\"");
        		    sb.append("id=\"otherDescribe").append(o[0]).append("\"").append("value=\"" ).append(otherDescribeValue).append("\"").append("onChange=\"updateMainCode2(this);\"").append("><br>");
        		}else{
        			sb.append("<input class=\"" ).append( className ).append( "\" type=\"").append(otherDescribeType).append( "\" name=\"" ).append( otherDescribe ).append( "\"");
        			sb.append("id=\"otherDescribe").append(o[0]).append("\"").append("value=\"" ).append(otherDescribeValue).append("\"").append("onChange=\"updateSubCodeZZ(this);\"").append("><br>");
        		}
        	}    		
    	}
        return sb.toString();    
    }
	
    public static String getCheckBoxOption(String className, String mainCode, String subCode, String sql, String[] selectedCheckBox1, String[] selectedCheckBox2) {
    	StringBuilder sb = new StringBuilder();  	
    	java.util.List list = PersistenceServiceGetter.getInstance().getHibernateTemplate().find(sql);
    	String otherDescribeValue ="";
    	if (list!=null && list.size()>0) {    		
    		int j=0;
        	for(int i=0 ; i<list.size();i++){
        		Object[] o = (Object[]) list.get(i);
        		sb.append("<input class=\"" ).append( className ).append( "\" type=\"checkbox\" name=\"" ).append( mainCode ).append( "\" value=\"" ).append( o[0] ).append( "\"");
        		if (selectedCheckBox1!=null && selectedCheckBox1.length>0) {
        			for (j=0; j<selectedCheckBox1.length; j++) {
        				if(Common.get(o[0]).equals(selectedCheckBox1[j])) sb.append(" checked");
        			}
        		}
        		sb.append(" id='"+o[0]+"' onclick='checkChildrenSubCode(this);'");
        		sb.append(">").append(o[1]).append(" ").append("<br>");        		
        		//第2層 
        		java.util.List list2 = PersistenceServiceGetter.getInstance().getHibernateTemplate().find("select dpdKind,dpdKindName from Drg1001Db where substring(dpdKind,1,2)='"+o[0]+"' and isStop='N' order by dpdKind");
        		if (list2!=null && list2.size()>0) {
        			int l=0;
        			sb.append("&nbsp;&nbsp;&nbsp;");
        			for(int k=0 ; k<list2.size(); k++){
        				Object[] o2 = (Object[]) list2.get(k);
        				sb.append("&nbsp;&nbsp;");
        				sb.append("<input class=\"" ).append( className ).append( "\" type=\"checkbox\" name=\"" ).append( subCode ).append( "\" value=\"" ).append( o2[0] ).append( "\"");
                		if (selectedCheckBox2!=null && selectedCheckBox2.length>0) {
                			for (l=0; l<selectedCheckBox2.length; l++) {
                				if(Common.get(o2[0]).equals(selectedCheckBox2[l])) sb.append("checked");
                			}
                		}
                		sb.append(" id='"+o2[0]+"' onclick='checkParentMainCode(this);'");
                		sb.append(">").append(o2[1]).append(" ");
        			}
        			sb.append("<br>");
        		}
        	}    		
    	}
        return sb.toString();    	
    }
    
    public String getAddFile(String systemType) throws Exception 
	{
		StringBuilder sb = new StringBuilder(1024); 
		String ststus = View.getLookupField("select status from Drg0001Db where id="+Common.getLong(getId()));
		String hql = " from Con0001Db where fileKind='DRG' and dbName='Drg0001Db' and upLoadId="+Common.getLong(getId());
		if("1".equals(systemType))
		    hql += " and systemType like 'DRG01%'";  //附件上傳
		else if ("2".equals(systemType))
			hql += " and systemType = 'DRG010002'";  //案件評估
        
		java.util.List objList = ServiceGetter.getInstance().getCommonService().load(hql+" order by id asc");

		if(objList!=null && objList.size()>0)
		{
			java.util.Iterator it = objList.iterator();
			int i=0;
			while (it.hasNext()) 
			{
				Con0001Db o = (Con0001Db) it.next();
				String attFile = Common.get(o.getFileRoute())+":;:"+Common.get(o.getFileName());
				
				sb.append("<tr>\n");
				sb.append("<td style='text-align:center'>").append((i+1)).append("</td>");
				sb.append("<td  style='text-align:left'>");
				sb.append("<a href='#' class='text_link_b' onClick=\"downLoadFile('DRG','").append(attFile).append("')\"\">");
				sb.append(o.getFileName());
				sb.append("</a></td>\n");				
				sb.append("<td >").append(o.getFileExplan()).append("</td>");
				
				sb.append("<td style='text-align:center'>");
				if( "1".equals(systemType) || 
						("2".equals(systemType) && "41,42".indexOf(Common.get(ststus)) != -1)){		
					sb.append("<a class=\"text_link_b\" onclick=\"deleteFileSimple("+o.getId()+");\">").append("刪除檔案</a>");	
				}
				sb.append("</td>\n");
			
				sb.append("</tr>\n");
				
				i++;
			}
		}
		return sb.toString(); 
	}
    
	public String getId() {return checkGet(id);}
	public void setId(String id) {this.id = checkSet(id);}	
	public String getTabId() {return checkGet(tabId);}
	public void setTabId(String tabId) {this.tabId = checkSet(tabId);}
	public String getToUpdate() {return checkGet(toUpdate);}
	public void setToUpdate(String toUpdate) {this.toUpdate = checkSet(toUpdate);}
	
	public String getDrg03Update() {return checkGet(drg03Update);}
	public void setDrg03Update(String drg03Update) {this.drg03Update = checkSet(drg03Update);}
	
	public String getApplNo() {
		return checkGet(applNo);
	}
	public void setApplNo(String applNo) {
		this.applNo = checkSet(applNo);
	}
	public String getStatus() {
		return checkGet(status);
	}
	public void setStatus(String status) {
		this.status = checkSet(status);
	}
	public String getChargeMan() {
		return checkGet(chargeMan);
	}
	public void setChargeMan(String chargeMan) {
		this.chargeMan = checkSet(chargeMan);
	}
	public String getNotifierUserID() {
		return checkGet(notifierUserID);
	}
	public void setNotifierUserID(String notifierUserID) {
		this.notifierUserID = checkSet(notifierUserID);
	}
	public String getOccurDate() {
		return checkGet(occurDate);
	}
	public void setOccurDate(String occurDate) {
		this.occurDate = checkSet(occurDate);
	}
	public String getNotifierRevDate() {
		return checkGet(notifierRevDate);
	}
	public void setNotifierRevDate(String notifierRevDate) {
		this.notifierRevDate = checkSet(notifierRevDate);
	}
	public String getNotifierRepDate() {
		return checkGet(notifierRepDate);
	}
	public void setNotifierRepDate(String notifierRepDate) {
		this.notifierRepDate = checkSet(notifierRepDate);
	}	
	public String getNotifierSource() {
		return checkGet(notifierSource);
	}
	public void setNotifierSource(String notifierSource) {
		this.notifierSource = checkSet(notifierSource);
	}
	public String getNotifierName() {
		return checkGet(notifierName);
	}
	public void setNotifierName(String notifierName) {
		this.notifierName = checkSet(notifierName);
	}
	public String getNotifierDept() {
		return checkGet(notifierDept);
	}
	public void setNotifierDept(String notifierDept) {
		this.notifierDept = checkSet(notifierDept);
	}
	public String getNotifierTel() {
		return checkGet(notifierTel);
	}
	public void setNotifierTel(String notifierTel) {
		this.notifierTel = checkSet(notifierTel);
	}
	public String getNotifierAddress() {
		return checkGet(notifierAddress);
	}
	public void setNotifierAddress(String notifierAddress) {
		this.notifierAddress = checkSet(notifierAddress);
	}
	public String getNotifierEmail() {
		return checkGet(notifierEmail);
	}
	public void setNotifierEmail(String notifierEmail) {
		this.notifierEmail = checkSet(notifierEmail);
	}
	public String getNotifierType() {
		return checkGet(notifierType);
	}
	public void setNotifierType(String notifierType) {
		this.notifierType = checkSet(notifierType);
	}
	public String getNotifierTitle() {
		return checkGet(notifierTitle);
	}
	public void setNotifierTitle(String notifierTitle) {
		this.notifierTitle = checkSet(notifierTitle);
	}
	public String getPermitKey() {
		return checkGet(permitKey);
	}
	public void setPermitKey(String permitKey) {
		this.permitKey = checkSet(permitKey);
	}
	public String getPermitNo() {
		return checkGet(permitNo);
	}
	public void setPermitNo(String permitNo) {
		this.permitNo = checkSet(permitNo);
	}
	public String getChProduct() {
		return checkGet(chProduct);
	}
	public void setChProduct(String chProduct) {
		this.chProduct = checkSet(chProduct);
	}
	public String getEnProduct() {
		return get(enProduct);
	}
	public void setEnProduct(String enProduct) {
		this.enProduct = checkSet(enProduct);
	}
	public String getIngredient() {
		return checkGet(ingredient);
	}
	public void setIngredient(String ingredient) {
		this.ingredient = checkSet(ingredient);
	}
	public String getContent() {
		return checkGet(content);
	}
	public void setContent(String content) {
		this.content = checkSet(content);
	}
	public String getMedModel() {
		return checkGet(medModel);
	}
	public void setMedModel(String medModel) {
		this.medModel = checkSet(medModel);
	}
	public String getMedModelOther() {
		return checkGet(medModelOther);
	}
	public void setMedModelOther(String medModelOther) {
		this.medModelOther = checkSet(medModelOther);
	}
	public String getMedPackage() {
		return checkGet(medPackage);
	}
	public void setMedPackage(String medPackage) {
		this.medPackage = checkSet(medPackage);
	}
	public String getMedPackageOther() {
		return checkGet(medPackageOther);
	}
	public void setMedPackageOther(String medPackageOther) {
		this.medPackageOther = checkSet(medPackageOther);
	}	
	public String getApplicationID() {
		return checkGet(applicationID);
	}
	public void setApplicationID(String applicationID) {
		this.applicationID = checkSet(applicationID);
	}
	public String getApplicationName() {
		return checkGet(applicationName);
	}
	public void setApplicationName(String applicationName) {
		this.applicationName = checkSet(applicationName);
	}
	public String getManufactorName() {
		return checkGet(manufactorName);
	}
	public void setManufactorName(String manufactorName) {
		this.manufactorName = checkSet(manufactorName);
	}
	public String getManufactorNo() {
		return checkGet(manufactorNo);
	}
	public void setManufactorNo(String manufactorNo) {
		this.manufactorNo = checkSet(manufactorNo);
	}
	public String getManufactorDate() {
		return checkGet(manufactorDate);
	}
	public void setManufactorDate(String manufactorDate) {
		this.manufactorDate = checkSet(manufactorDate);
	}
	public String getExpirationDate() {
		return checkGet(expirationDate);
	}
	public void setExpirationDate(String expirationDate) {
		this.expirationDate = checkSet(expirationDate);
	}
	public String getStorage() {
		return checkGet(storage);
	}
	public void setStorage(String storage) {
		this.storage = checkSet(storage);
	}
	public String getStorageOther() {
		return checkGet(storageOther);
	}
	public void setStorageOther(String storageOther) {
		this.storageOther = checkSet(storageOther);
	}
	public String getIsFindYn() {
		return checkGet(isFindYn);
	}
	public void setIsFindYn(String isFindYn) {
		this.isFindYn = checkSet(isFindYn);
	}
	public String getIsSingleYn() {
		return checkGet(isSingleYn);
	}
	public void setIsSingleYn(String isSingleYn) {
		this.isSingleYn = checkSet(isSingleYn);
	}
	public String getSameNum() {
		return checkGet(sameNum);
	}
	public void setSameNum(String sameNum) {
		this.sameNum = checkSet(sameNum);
	}
	public String getDiffNum() {
		return checkGet(diffNum);
	}
	public void setDiffNum(String diffNum) {
		this.diffNum = checkSet(diffNum);
	}
	public String getIsHarmYn() {
		return checkGet(isHarmYn);
	}
	public void setIsHarmYn(String isHarmYn) {
		this.isHarmYn = checkSet(isHarmYn);
	}
	public String getEvenContactYn() {
		return checkGet(evenContactYn);
	}
	public void setEvenContactYn(String evenContactYn) {
		this.evenContactYn = checkSet(evenContactYn);
	}
	public String getDealWith() {
		return checkGet(dealWith);
	}
	public void setDealWith(String dealWith) {
		this.dealWith = checkSet(dealWith);
	}
	public String getIsContactYn() {
		return checkGet(isContactYn);
	}
	public void setIsContactYn(String isContactYn) {
		this.isContactYn = checkSet(isContactYn);
	}
	public String getFirstResult() {
		return checkGet(firstResult);
	}
	public void setFirstResult(String firstResult) {
		this.firstResult = checkSet(firstResult);
	}
	public String getFirstRemark() {
		return checkGet(firstRemark);
	}
	public void setFirstRemark(String firstRemark) {
		this.firstRemark = checkSet(firstRemark);
	}		
	public String[] getMainCode() {
		return mainCode;
	}
	public void setMainCode(String[] mainCode) {
		this.mainCode = mainCode;
	}
	public String[] getSubCode() {
		return subCode;
	}
	public void setSubCode(String[] subCode) {
		this.subCode = subCode;
	}
	public String[] getOtherDescribe() {
		return otherDescribe;
	}
	public void setOtherDescribe(String[] otherDescribe) {
		this.otherDescribe = otherDescribe;
	}
	public String getNotifierDeptID() {
		return checkGet(notifierDeptID);
	}
	public void setNotifierDeptID(String notifierDeptID) {
		this.notifierDeptID = checkSet(notifierDeptID);
	}
	public String getNotifierTelArea() {
		return checkGet(notifierTelArea);
	}
	public void setNotifierTelArea(String notifierTelArea) {
		this.notifierTelArea = checkSet(notifierTelArea);
	}
	public String getNotifierTelExt() {
		return checkGet(notifierTelExt);
	}
	public void setNotifierTelExt(String notifierTelExt) {
		this.notifierTelExt = checkSet(notifierTelExt);
	}
	public String getNotifierCounty() {
		return checkGet(notifierCounty);
	}
	public void setNotifierCounty(String notifierCounty) {
		this.notifierCounty = checkSet(notifierCounty);
	}
	public String getNotifierZipCode() {
		return checkGet(notifierZipCode);
	}
	public void setNotifierZipCode(String notifierZipCode) {
		this.notifierZipCode = checkSet(notifierZipCode);
	}
	public String getManufactorCountry() {
		return checkGet(manufactorCountry);
	}
	public void setManufactorCountry(String manufactorCountry) {
		this.manufactorCountry = checkSet(manufactorCountry);
	}
	public String getCommonUser() {
		return checkGet(commonUser);
	}
	public void setCommonUser(String commonUser) {
		this.commonUser = checkSet(commonUser);
	}
	public String getIsUsedYn() {
		return checkGet(isUsedYn);
	}
	public void setIsUsedYn(String isUsedYn) {
		this.isUsedYn = checkSet(isUsedYn);
	}
	public String getDefectDesc() {
		return checkGet(defectDesc);
	}
	public void setDefectDesc(String defectDesc) {
		this.defectDesc = checkSet(defectDesc);
	}
	public String getNotifier() {
		return checkGet(notifier);
	}
	public void setNotifier(String notifier) {
		this.notifier = checkSet(notifier);
	}
	public String getEnrolledDate() {
		return checkGet(enrolledDate);
	}
	public void setEnrolledDate(String enrolledDate) {
		this.enrolledDate = checkSet(enrolledDate);
	}
	public String getDrg03id() {return checkGet(drg03id);}
	public void setDrg03id(String drg03id) {this.drg03id = checkSet(drg03id);}
	public String getAssessDate03() {return checkGet(assessDate03);}
	public void setAssessDate03(String assessDate03) {this.assessDate03 = checkSet(assessDate03);}
	public String getAssessMan03() {return checkGet(assessMan03);}
	public void setAssessMan03(String assessMan03) {this.assessMan03 = checkSet(assessMan03);}
	public String getFirstResult03() {return checkGet(firstResult03);}
	public void setFirstResult03(String firstResult03) {this.firstResult03 = checkSet(firstResult03);}
	public String getNotifierAging03() {return checkGet(notifierAging03);}
	public void setNotifierAging03(String notifierAging03) {this.notifierAging03 = checkSet(notifierAging03);}
	public String getNotifierQuality03() {return checkGet(notifierQuality03);}
	public void setNotifierQuality03(String notifierQuality03) {this.notifierQuality03 = checkSet(notifierQuality03);}
	public String getIntervalDays03() {return checkGet(intervalDays03);}
	public void setIntervalDays03(String intervalDays03) {this.intervalDays03 = checkSet(intervalDays03);}
	public String getRemark03() {return checkGet(remark03);}
	public void setRemark03(String remark03) {this.remark03 = checkSet(remark03);}

	public String getHisData1() {return checkGet(hisData1);}
	public void setHisData1(String hisData1) {this.hisData1 = checkSet(hisData1);}
	public String getHisData2() {return checkGet(hisData2);}
	public void setHisData2(String hisData2) {this.hisData2 = checkSet(hisData2);}
	public String getHisData3() {return checkGet(hisData3);}
	public void setHisData3(String hisData3) {this.hisData3 = checkSet(hisData3);}
	public String getHisData4() {return checkGet(hisData4);}
	public void setHisData4(String hisData4) {this.hisData4 = checkSet(hisData4);}
	public String getHisData5() {return checkGet(hisData5);}
	public void setHisData5(String hisData5) {this.hisData5 = checkSet(hisData5);}
	public String getHisData6() {return get(hisData6);}
	public void setHisData6(String hisData6) {this.hisData6 = set(hisData6);}
	public String getHisData7() {return get(hisData7);}
	public void setHisData7(String hisData7) {this.hisData7 = set(hisData7);}	
	public String getHisApplNoY() {return checkGet(hisApplNoY);}
	public void setHisApplNoY(String hisApplNoY) {this.hisApplNoY = checkSet(hisApplNoY);}
	public String getHisApplNoN() {return checkGet(hisApplNoN);}
	public void setHisApplNoN(String hisApplNoN) {this.hisApplNoN = checkSet(hisApplNoN);}	
		
	public String getDrg04Update() {return checkGet(drg04Update);	}
	public void setDrg04Update(String drg04Update) {this.drg04Update = checkSet(drg04Update);	}
	public String getDrg04id() {return checkGet(drg04id);	}
	public void setDrg04id(String drg04id) {this.drg04id = checkSet(drg04id);	}
	public String getGradeMan04() {return checkGet(gradeMan04);	}
	public void setGradeMan04(String gradeMan04) {this.gradeMan04 = checkSet(gradeMan04);	}
	public String getGradeDate04() {return checkGet(gradeDate04);	}
	public void setGradeDate04(String gradeDate04) {this.gradeDate04 = checkSet(gradeDate04);	}
	public String getDrgLev04() {return checkGet(drgLev04);	}
	public void setDrgLev04(String drgLev04) {this.drgLev04 = checkSet(drgLev04);	}
	
	public String getQ_isQuery() {
		return checkGet(q_isQuery);
	}	
	public void setQ_isQuery(String qIsQuery) {
		q_isQuery = checkSet(qIsQuery);
	}	
	public String getQ_id() {
		return checkGet(q_id);
	}
	public void setQ_id(String q_id) {
		this.q_id = checkSet(q_id);
	}
	public String getQ_applNoS() {
		return checkGet(q_applNoS);
	}
	public void setQ_applNoS(String q_applNoS) {
		this.q_applNoS = checkSet(q_applNoS);
	}	
	public String getQ_applNoE() {
		return checkGet(q_applNoE);
	}
	public void setQ_applNoE(String q_applNoE) {
		this.q_applNoE = checkSet(q_applNoE);
	}
	public String getQ_occurDateS() {
		return checkGet(q_occurDateS);
	}
	public void setQ_occurDateS(String q_occurDateS) {
		this.q_occurDateS = checkSet(q_occurDateS);
	}	
	public String getQ_occurDateE() {
		return checkGet(q_occurDateE);
	}
	public void setQ_occurDateE(String q_occurDateE) {
		this.q_occurDateE = checkSet(q_occurDateE);
	}
	public String getQ_enrolledDateS() {
		return checkGet(q_enrolledDateS);
	}
	public void setQ_enrolledDateS(String q_enrolledDateS) {
		this.q_enrolledDateS = checkSet(q_enrolledDateS);
	}
	public String getQ_enrolledDateE() {
		return checkGet(q_enrolledDateE);
	}
	public void setQ_enrolledDateE(String q_enrolledDateE) {
		this.q_enrolledDateE = checkSet(q_enrolledDateE);
	}
	public String getQ_notifierRepDateS() {
		return checkGet(q_notifierRepDateS);
	}
	public void setQ_notifierRepDateS(String q_notifierRepDateS) {
		this.q_notifierRepDateS = checkSet(q_notifierRepDateS);
	}	
	public String getQ_notifierRepDateE() {
		return checkGet(q_notifierRepDateE);
	}
	public void setQ_notifierRepDateE(String q_notifierRepDateE) {
		this.q_notifierRepDateE = checkSet(q_notifierRepDateE);
	}
	public String getQ_notifierSource() {
		return checkGet(q_notifierSource);
	}
	public void setQ_notifierSource(String q_notifierSource) {
		this.q_notifierSource = checkSet(q_notifierSource);
	}
	public String getQ_notifierType() {
		return checkGet(q_notifierType);
	}
	public void setQ_notifierType(String qNotifierType) {
		q_notifierType = checkSet(qNotifierType);
	}
	public String getQ_permitNo() {
		return checkGet(q_permitNo);
	}
	public void setQ_permitNo(String q_permitNo) {
		this.q_permitNo = checkSet(q_permitNo);
	}
	public String getQ_chProduct() {
		return checkGet(q_chProduct);
	}
	public void setQ_chProduct(String q_chProduct) {
		this.q_chProduct = checkSet(q_chProduct);
	}
	public String getQ_ingredient() {
		return checkGet(q_ingredient);
	}
	public void setQ_ingredient(String q_ingredient) {
		this.q_ingredient = checkSet(q_ingredient);
	}
	public String getQ_applicationName() {
		return checkGet(q_applicationName);
	}
	public void setQ_applicationName(String q_applicationName) {
		this.q_applicationName = checkSet(q_applicationName);
	}
	public String getQ_manufactorName() {
		return checkGet(q_manufactorName);
	}
	public void setQ_manufactorName(String q_manufactorName) {
		this.q_manufactorName = checkSet(q_manufactorName);
	}
	public String getQ_status() {
		return checkGet(q_status);
	}
	public void setQ_status(String q_status) {
		this.q_status = checkSet(q_status);
	}
	public String[] getQ_drgLev() {
		return q_drgLev;
	}
	public void setQ_drgLev(String[] q_drgLev) {
		this.q_drgLev = q_drgLev;
	}	
	public String getQ_permitKey() {
		return checkGet(q_permitKey);
	}
	public void setQ_permitKey(String qPermitKey) {
		q_permitKey = checkSet(qPermitKey);
	}	
	public String getQ_notifierDept() {
		return checkGet(q_notifierDept);
	}
	public void setQ_notifierDept(String qNotifierDept) {
		q_notifierDept = checkSet(qNotifierDept);
	}	
	public String[] getQ_mainCode() {
		return q_mainCode;
	}
	public void setQ_mainCode(String[] qMainCode) {
		q_mainCode = qMainCode;
	}
	public String[] getQ_subCode() {
		return q_subCode;
	}
	public void setQ_subCode(String[] qSubCode) {
		q_subCode = qSubCode;
	}

}
