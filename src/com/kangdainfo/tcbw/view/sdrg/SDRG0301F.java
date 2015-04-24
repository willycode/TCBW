package com.kangdainfo.tcbw.view.sdrg;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.model.bo.CommonDepartment;
import com.kangdainfo.common.model.bo.CommonUser;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.Datetime;

import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Con0001Db;
import com.kangdainfo.tcbw.model.bo.Con1004Db;
import com.kangdainfo.tcbw.model.bo.Con1015Db;
import com.kangdainfo.tcbw.model.bo.Drg8001Db;
import com.kangdainfo.tcbw.model.bo.Drg8002Db;
import com.kangdainfo.tcbw.model.bo.Drg8003Db;
import com.kangdainfo.tcbw.model.bo.Drg8004Db;
import com.kangdainfo.tcbw.model.bo.Drg8005Db;
import com.kangdainfo.tcbw.util.TCBWCommon;

public class SDRG0301F extends SDRG0201F{
	
	//Drg8001Db
	private String appRecDate;	            //廠商完成回收日期
	private String appRecMan;	            //廠商回收-作業人員
	private String apprecyclestorage;	    //廠商回收-回收品及庫存品處置方式
	private String apprecyclestoragedesc;	//廠商回收-回收品及庫存品處置方式說明
	private String appprepunishdate;	    //廠商回收-預計處置日期
	private String appreplydate;	        //廠商回覆-回覆日期
	private String appmedicineType;	        //廠商回覆-學名藥/原廠藥
	private String appproduceType;	        //廠商回覆-國產/輸入
	private String applotType;	            //廠商回覆-批號範圍
	private String apprecyclereason;	    //廠商回覆-回收原因
	private String apprecyclersdesc;	    //廠商回覆-回收原因說明
	private String[] appsurvey;	            //廠商回覆-調查結果
	private String appsurveyOther;       	//廠商回覆-調查結果說明
	private String[] appprecaution;        	//廠商回覆-預防措施
	private String appprecautionOther;      //廠商回覆-預防措施說明
	private String appsurveyresult;	        //廠商回覆-清查結果
	private String appsurveyreport;	        //廠商回覆-調查報告
	private String appprecautiontime;	    //廠商回覆-預防矯正措施及改善時程
	private String appcheckmanudate;	    //廠商回覆-查廠日期
	private String appcheckmanuresult;	    //廠商回覆-查廠查核結果
	
	//Drg8003Db
	private String extendate;	            //申請展延日期
	private String extenreason;             //展延理由
	private String extendeadline;	        //展延後回收期限
	
	//實際回收清單List  Drg8005Db
	private String applotNo[];              //廠商回收-批號
	private String appreservesNum[];        //廠商回收-庫存量
	private String appreservesUnit[];       //廠商回收-庫存量單位
	private String appsellnum[];            //廠商回收-銷售數量
	private String appsellunit[];           //廠商回收-銷售數量單位
	private String appprerecyclenum[];      //廠商回收-回收數量
	private String appprerecycleunit[];     //廠商回收-回收數量單位
	private String appktotalNum[];          //廠商回收-總數量
	private String drg8005DbType[];
	private String drg8005DbTypeId[];
	
	//案件狀態(Drg8004Db)
	private String status2;
	
	//展延權限
	private String showExtension;
	
	public void doUpdate0301() throws Exception{
		ServiceGetter.getInstance().getTcbwService().getSdrgDao().updateBySdrg0301F(this);
	}
	
	//回收期限展延
	public void doExtension0301() throws Exception{
		SDRG0301F obj = this;		
		Drg8003Db drg8003 = (Drg8003Db) View.getObject(" from Drg8003Db where drg8001Db.id=" + Common.getInt(obj.getId()));
		if (drg8003!=null) {			
			drg8003.setExtendate(Common.get(obj.getExtendate()));	                //申請展延日期
			drg8003.setExtenreason(Common.get(obj.getExtenreason()));               //展延理由
			drg8003.setExtendeadline(Common.get(obj.getExtendeadline()));           //展延後回收期限
			drg8003.setModifier(obj.getUserID());
			drg8003.setModifyDate(Datetime.getYYYMMDD());
			drg8003.setModifyTime(Datetime.getHHMMSS());
			ServiceGetter.getInstance().getTcbwService().update(drg8003);			
		}else{
			Drg8001Db c = (Drg8001Db) View.getObject(" from Drg8001Db where id=" + Common.getInt(obj.getId()));
			if(c != null){			
				Drg8003Db master = new Drg8003Db();			
				master.setDrg8001Db(c);
				master.setExtendate(Common.get(obj.getExtendate()));	                //申請展延日期
				master.setExtenreason(Common.get(obj.getExtenreason()));               //展延理由
				master.setExtendeadline(Common.get(obj.getExtendeadline()));           //展延後回收期限
				master.setCreator(obj.getUserID());
				master.setCreateDate(Datetime.getYYYMMDD());
				master.setCreateTime(Datetime.getHHMMSS());				
				ServiceGetter.getInstance().getTcbwService().save(master);
			}
		}
	}
    
	public Object doQueryOne0301() throws Exception {
		SDRG0301F obj = this;	
		
		Drg8001Db c = (Drg8001Db) View.getObject(" from Drg8001Db where id=" + Common.getInt(obj.getId()));
		
		//System.out.println("[TCBW]-[SDRG0301F]-[doQueryOne]- Drg8001Db.id : " + obj.getId());
		
		if (c!=null) {
			
			obj.setStatus(Common.get(c.getStatus()));
			obj.setAppRecDate(Common.get(c.getAppRecDate()));	                        //廠商完成回收日期
			obj.setAppRecMan(Common.get(c.getAppRecMan()));	                            //廠商回收-作業人員
			obj.setApprecyclestorage(Common.get(c.getApprecyclestorage()));	            //廠商回收-回收品及庫存品處置方式
			obj.setApprecyclestoragedesc(Common.get(c.getApprecyclestoragedesc()));	    //廠商回收-回收品及庫存品處置方式說明
			obj.setAppprepunishdate(Common.get(c.getAppprepunishdate()));	            //廠商回收-預計處置日期
			obj.setAppreplydate(Common.get(c.getAppreplydate()));	                    //廠商回收-回覆日期
			obj.setAppmedicineType(Common.get(c.getAppmedicineType()));	                //廠商回收-學名藥/原廠藥
			obj.setAppproduceType(Common.get(c.getAppproduceType()));	                //廠商回收-國產/輸入
			obj.setApplotType(Common.get(c.getApplotType()));	                        //廠商回收-批號範圍
			obj.setApprecyclereason(Common.get(c.getApprecyclereason()));	            //廠商回收-回收原因
			obj.setApprecyclersdesc(Common.get(c.getApprecyclersdesc()));	            //廠商回收-回收原因說明
			String[] appsurvey = null;
			if(c.getAppsurvey() != null && c.getAppsurvey().length()>0){
				appsurvey = new String[c.getAppsurvey().length()];
				appsurvey = c.getAppsurvey().split(",");				
			}
			obj.setAppsurvey(appsurvey);	                                            //廠商回收-調查結果
			obj.setAppsurveyOther(Common.get(c.getAppsurveyOther()));        	        //廠商回收-調查結果說明
			String[] appprecaution = null;
			if(c.getAppprecaution() != null && c.getAppprecaution().length()>0){
				appprecaution = new String[c.getAppprecaution().length()];
				appprecaution = c.getAppprecaution().split(",");				
			}
			obj.setAppprecaution(appprecaution);        	                            //廠商回收-預防措施
			obj.setAppprecautionOther(Common.get(c.getAppprecautionOther()));        	//廠商回收-預防措施說明
			obj.setAppsurveyresult(Common.get(c.getAppsurveyresult()));	                //廠商回收-清查結果
			obj.setAppsurveyreport(Common.get(c.getAppsurveyreport()));	                //廠商回收-調查報告
			obj.setAppprecautiontime(Common.get(c.getAppprecautiontime()));	            //廠商回收-預防矯正措施及改善時程
			obj.setAppcheckmanudate(Common.get(c.getAppcheckmanudate()));	            //廠商回收-查廠日期
			obj.setAppcheckmanuresult(Common.get(c.getAppcheckmanuresult()));	        //廠商回收-查廠查核結果			
			
			
			if(c.getDrg8003Dbs() != null && c.getDrg8003Dbs().size()>0){
				for (Object dtlObj : c.getDrg8003Dbs()) {
					Drg8003Db dtl = (Drg8003Db) dtlObj; 
					obj.setExtendate(Common.get(dtl.getExtendate()));	                //申請展延日期
					obj.setExtenreason(Common.get(dtl.getExtenreason()));               //展延理由
					obj.setExtendeadline(Common.get(dtl.getExtendeadline()));           //展延後回收期限					
				}
			}			
			//Drg8004Db - Status
			String ststus2 = View.getLookupField("select status from Drg8004Db where drg8001Db.id="+Common.getLong(obj.getId()));
			obj.setStatus2(ststus2);
			
		}
		this.setState("queryOneSuccess");
		return obj;
	}
	
	public String getDrg8005DbItemSet() throws Exception 
	{
		StringBuilder sb = new StringBuilder(1024); 
		if(!"".equals(Common.get(getId()))){		
			String hql = " from Drg8005Db where 1=1 and drg8001Db.id="+Common.get(getId());
			java.util.List objList = ServiceGetter.getInstance().getCommonService().load(hql+" order by id asc");		
			if(objList!=null && objList.size()>0){			
				java.util.Iterator it = objList.iterator();			
				while (it.hasNext()) {				
					Drg8005Db o = (Drg8005Db) it.next();				
					sb.append("addDrg8005Db('").append(Common.get(o.getId())).append("'");				
					sb.append(",'").append(o.getLotNo()).append("'");                 //廠商回收-批號				
					sb.append(",'").append(o.getAppreservesNum()).append("'");        //廠商回收-庫存量				
					sb.append(",'").append(o.getAppreservesUnit()).append("'");       //廠商回收-庫存量單位				
					sb.append(",'").append(o.getAppsellnum()).append("'");            //廠商回收-銷售數量				
					sb.append(",'").append(o.getAppsellunit()).append("'");           //廠商回收-銷售數量單位
					sb.append(",'").append(o.getAppprerecyclenum()).append("'");      //廠商回收-回收數量
					sb.append(",'").append(o.getAppprerecycleunit()).append("'");     //廠商回收-回收數量單位
					sb.append(",'").append(o.getAppktotalNum()).append("');\n");      //廠商回收-總數量
				}
			}
		}
		return sb.toString(); 
	}
	
	//檢核衛生局有無修改權限 && 檢核有無展延權限
	public  String checkHealthbureauUpdate() throws Exception
	{	  
		SDRG0301F obj = this;
		String returnStr ="";
		CommonUser c = ServiceGetter.getInstance().getAuthenticationService().getCurrentUser();		
		if(c != null){		
			String shortCode=c.getCommonDepartment().getShortCode();				
			String userId=c.getUserId();
			//判斷是否為衛生單位			
			if("04".equals(shortCode)){
				Drg8001Db drg01 = (Drg8001Db) View.getObject(" from Drg8001Db where id=" + Common.getInt(getId()));
				if(obj != null){
					String hql="  from Con1004Db where 1=1 "+
                               "  and  commonUser.userId="+Common.sqlChar(userId)+
                               "  and  formType like "+Common.sqlChar("%DRG04%")+
	                           "  and ( con1003Db.id="+Common.sqlChar(drg01.getHealthbureau1());			
					if(!"".equals(Common.get(drg01.getHealthbureau2()))){				
						hql += " or con1003Db.id="+Common.sqlChar(drg01.getHealthbureau2())+" )";		
					}else{			
						hql += " )";		
					}					
					Con1004Db  con1004 = (Con1004Db)View.getObject(hql);			
					if(con1004!=null) returnStr="Y";
					else returnStr="N";
				}							
			}else if (!"02".equals(shortCode)){
				obj.setShowExtension("Y");
				returnStr="Y";
			}else{
				returnStr="Y";
			}			
		}
		return returnStr;
	}
	
	//附件(實際回收清單)
	public String getAddFileDrg0405() throws Exception 
	{
		StringBuilder sb = new StringBuilder(1024);
		
		String ststus = View.getLookupField("select status from Drg8001Db where id="+Common.getLong(getId()));
		
		String hql = " from Con0001Db where fileKind='DRG' and systemType like 'DRG040005' and dbName='Drg8001Db' and upLoadId="+Common.getLong(getId());
		java.util.List objList = ServiceGetter.getInstance().getCommonService().load(hql+" order by id asc");
		if(objList!=null && objList.size()>0)
		{
			java.util.Iterator it = objList.iterator();
			int i=0;
			while (it.hasNext()) 
			{
				Con0001Db o = (Con0001Db) it.next();
				String attFile = Common.get(o.getFileRoute())+":;:"+Common.get(o.getFileName());
				String prop="";
			    prop=prop+"toolbar=no;location=no,directories=no,menubar=no,status=no,scrollbars=yes,resizable=yes,";
			    prop=prop+"width=450,";
			    prop=prop+"height=160";	
			    
				sb.append("<tr>\n");
				sb.append("<td style='text-align:center'>").append((i+1)).append("</td>");
				sb.append("<td style='text-align:left'>");			
				sb.append("<a class=\"text_link_b\" href=\"../../downloadFileSimple?fileType=DRG&fileID=").append(attFile).append("\">");
				sb.append(o.getFileName());
				sb.append("</a></td>\n");				
				sb.append("<td >").append(o.getFileExplan()).append("</td>");				
				sb.append("<td style='text-align:center'>");
				if(Common.getInt(ststus) <= 21){				
					sb.append("<a class=\"text_link_b\" onclick=\"deleteFileSimple("+o.getId()+");\">").append("刪除檔案</a>");	
				}
				sb.append("</a></td>\n");
				
				i++;
			}
		}
		return sb.toString(); 
	}
	
	//附件(回收報告書)
	public String getAddFileDrg0406() throws Exception 
	{
		StringBuilder sb = new StringBuilder(1024);
		
		String ststus = View.getLookupField("select status from Drg8001Db where id="+Common.getLong(getId()));
		
		String hql = " from Con0001Db where fileKind='DRG' and systemType like 'DRG040006' and dbName='Drg8001Db' and upLoadId="+Common.getLong(getId());
		java.util.List objList = ServiceGetter.getInstance().getCommonService().load(hql+" order by id asc");
		if(objList!=null && objList.size()>0)
		{
			java.util.Iterator it = objList.iterator();
			int i=0;
			while (it.hasNext()) 
			{
				Con0001Db o = (Con0001Db) it.next();
				String attFile = Common.get(o.getFileRoute())+":;:"+Common.get(o.getFileName());
				String prop="";
			    prop=prop+"toolbar=no;location=no,directories=no,menubar=no,status=no,scrollbars=yes,resizable=yes,";
			    prop=prop+"width=450,";
			    prop=prop+"height=160";
				
				sb.append("<tr>\n");
				sb.append("<td style='text-align:center'>").append((i+1)).append("</td>");
				sb.append("<td style='text-align:left'>");			
				sb.append("<a class=\"text_link_b\" href=\"../../downloadFileSimple?fileType=DRG&fileID=").append(attFile).append("\">");
				sb.append(o.getFileName());
				sb.append("</a></td>\n");				
				sb.append("<td >").append(o.getFileExplan()).append("</td>");				
				sb.append("<td style='text-align:center'>");
				if(Common.getInt(ststus) <= 21){				
					sb.append("<a class=\"text_link_b\" onclick=\"deleteFileSimple("+o.getId()+");\">").append("刪除檔案</a>");	
				}
				sb.append("</a></td>\n");
				
				i++;
			}
		}
		return sb.toString(); 
	}
	
	//附件(調查報告)
	public String getAddFileDrg0407() throws Exception 
	{
		StringBuilder sb = new StringBuilder(1024); 
		
		String ststus2 = View.getLookupField("select status from Drg8004Db where drg8001Db.id="+Common.getLong(getId()));
		
		String hql = " from Con0001Db where fileKind='DRG' and systemType like 'DRG040007' and dbName='Drg8001Db' and upLoadId="+Common.getLong(getId());
		java.util.List objList = ServiceGetter.getInstance().getCommonService().load(hql+" order by id asc");
		if(objList!=null && objList.size()>0)
		{
			java.util.Iterator it = objList.iterator();
			int i=0;
			while (it.hasNext()) 
			{
				Con0001Db o = (Con0001Db) it.next();
				String attFile = Common.get(o.getFileRoute())+":;:"+Common.get(o.getFileName());
				String prop="";
			    prop=prop+"toolbar=no;location=no,directories=no,menubar=no,status=no,scrollbars=yes,resizable=yes,";
			    prop=prop+"width=450,";
			    prop=prop+"height=160";
				
				sb.append("<tr>\n");
				sb.append("<td style='text-align:center'>").append((i+1)).append("</td>");
				sb.append("<td style='text-align:left'>");			
				sb.append("<a class=\"text_link_b\" href=\"../../downloadFileSimple?fileType=DRG&fileID=").append(attFile).append("\">");
				sb.append(o.getFileName());
				sb.append("</a></td>\n");				
				sb.append("<td >").append(o.getFileExplan()).append("</td>");
				
				sb.append("<td style='text-align:center'>");
				if(Common.getInt(ststus2) == 40){				
					sb.append("<a class=\"text_link_b\" onclick=\"deleteFileSimple("+o.getId()+");\">").append("刪除檔案</a>");	
				}
				sb.append("</a></td>\n");
				
				i++;
			}
		}
		return sb.toString(); 
	}
	
	//附件(查廠報告書)
	public String getAddFileDrg0408() throws Exception 
	{
		StringBuilder sb = new StringBuilder(1024); 
		
		String ststus2 = View.getLookupField("select status from Drg8004Db where drg8001Db.id="+Common.getLong(getId()));
		
		String hql = " from Con0001Db where fileKind='DRG' and systemType like 'DRG040008' and dbName='Drg8001Db' and upLoadId="+Common.getLong(getId());
		java.util.List objList = ServiceGetter.getInstance().getCommonService().load(hql+" order by id asc");
		if(objList!=null && objList.size()>0)
		{
			java.util.Iterator it = objList.iterator();
			int i=0;
			while (it.hasNext()) 
			{
				Con0001Db o = (Con0001Db) it.next();
				String attFile = Common.get(o.getFileRoute())+":;:"+Common.get(o.getFileName());
				String prop="";
			    prop=prop+"toolbar=no;location=no,directories=no,menubar=no,status=no,scrollbars=yes,resizable=yes,";
			    prop=prop+"width=450,";
			    prop=prop+"height=160";
				
				sb.append("<tr>\n");
				sb.append("<td style='text-align:center'>").append((i+1)).append("</td>");
				sb.append("<td style='text-align:left'>");			
				sb.append("<a class=\"text_link_b\" href=\"../../downloadFileSimple?fileType=DRG&fileID=").append(attFile).append("\">");
				sb.append(o.getFileName());
				sb.append("</a></td>\n");				
				sb.append("<td >").append(o.getFileExplan()).append("</td>");				
				sb.append("<td style='text-align:center'>");
				if(Common.getInt(ststus2) == 40){				
					sb.append("<a class=\"text_link_b\" onclick=\"deleteFileSimple("+o.getId()+");\">").append("刪除檔案</a>");	
				}
				sb.append("</a></td>\n");
				
				i++;
			}
		}
		return sb.toString(); 
	}

	public String getAppRecDate() {
		return checkGet(appRecDate);
	}
	public void setAppRecDate(String appRecDate) {
		this.appRecDate = checkSet(appRecDate);
	}
	public String getAppRecMan() {
		return checkGet(appRecMan);
	}
	public void setAppRecMan(String appRecMan) {
		this.appRecMan = checkSet(appRecMan);
	}
	public String getApprecyclestorage() {
		return checkGet(apprecyclestorage);
	}
	public void setApprecyclestorage(String apprecyclestorage) {
		this.apprecyclestorage = checkSet(apprecyclestorage);
	}
	public String getApprecyclestoragedesc() {
		return checkGet(apprecyclestoragedesc);
	}
	public void setApprecyclestoragedesc(String apprecyclestoragedesc) {
		this.apprecyclestoragedesc = checkSet(apprecyclestoragedesc);
	}
	public String getAppprepunishdate() {
		return checkGet(appprepunishdate);
	}
	public void setAppprepunishdate(String appprepunishdate) {
		this.appprepunishdate = checkSet(appprepunishdate);
	}
	public String getAppreplydate() {
		return checkGet(appreplydate);
	}
	public void setAppreplydate(String appreplydate) {
		this.appreplydate = checkSet(appreplydate);
	}
	public String getAppmedicineType() {
		return checkGet(appmedicineType);
	}
	public void setAppmedicineType(String appmedicineType) {
		this.appmedicineType = checkSet(appmedicineType);
	}
	public String getAppproduceType() {
		return checkGet(appproduceType);
	}
	public void setAppproduceType(String appproduceType) {
		this.appproduceType = checkSet(appproduceType);
	}
	public String getApplotType() {
		return checkGet(applotType);
	}
	public void setApplotType(String applotType) {
		this.applotType = checkSet(applotType);
	}
	public String getApprecyclereason() {
		return checkGet(apprecyclereason);
	}
	public void setApprecyclereason(String apprecyclereason) {
		this.apprecyclereason = checkSet(apprecyclereason);
	}
	public String getApprecyclersdesc() {
		return checkGet(apprecyclersdesc);
	}
	public void setApprecyclersdesc(String apprecyclersdesc) {
		this.apprecyclersdesc = checkSet(apprecyclersdesc);
	}
	public String[] getAppsurvey() {
		return appsurvey;
	}
	public void setAppsurvey(String[] appsurvey) {
		this.appsurvey = appsurvey;
	}
	public String getAppsurveyOther() {
		return checkGet(appsurveyOther);
	}
	public void setAppsurveyOther(String appsurveyOther) {
		this.appsurveyOther = checkSet(appsurveyOther);
	}
	public String[] getAppprecaution() {
		return appprecaution;
	}
	public void setAppprecaution(String[] appprecaution) {
		this.appprecaution = appprecaution;
	}
	public String getAppprecautionOther() {
		return checkGet(appprecautionOther);
	}
	public void setAppprecautionOther(String appprecautionOther) {
		this.appprecautionOther = checkSet(appprecautionOther);
	}
	public String getAppsurveyresult() {
		return checkGet(appsurveyresult);
	}
	public void setAppsurveyresult(String appsurveyresult) {
		this.appsurveyresult = checkSet(appsurveyresult);
	}
	public String getAppsurveyreport() {
		return checkGet(appsurveyreport);
	}
	public void setAppsurveyreport(String appsurveyreport) {
		this.appsurveyreport = checkSet(appsurveyreport);
	}
	public String getAppprecautiontime() {
		return checkGet(appprecautiontime);
	}
	public void setAppprecautiontime(String appprecautiontime) {
		this.appprecautiontime = checkSet(appprecautiontime);
	}
	public String getAppcheckmanudate() {
		return checkGet(appcheckmanudate);
	}
	public void setAppcheckmanudate(String appcheckmanudate) {
		this.appcheckmanudate = checkSet(appcheckmanudate);
	}
	public String getAppcheckmanuresult() {
		return checkGet(appcheckmanuresult);
	}
	public void setAppcheckmanuresult(String appcheckmanuresult) {
		this.appcheckmanuresult = checkSet(appcheckmanuresult);
	}
	public String getExtendate() {
		return checkGet(extendate);
	}
	public void setExtendate(String extendate) {
		this.extendate = checkSet(extendate);
	}
	public String getExtenreason() {
		return checkGet(extenreason);
	}
	public void setExtenreason(String extenreason) {
		this.extenreason = checkSet(extenreason);
	}
	public String getExtendeadline() {
		return checkGet(extendeadline);
	}
	public void setExtendeadline(String extendeadline) {
		this.extendeadline = checkSet(extendeadline);
	}

	public String[] getApplotNo() {
		return applotNo;
	}

	public void setApplotNo(String[] applotNo) {
		this.applotNo = applotNo;
	}

	public String[] getAppreservesNum() {
		return appreservesNum;
	}

	public void setAppreservesNum(String[] appreservesNum) {
		this.appreservesNum = appreservesNum;
	}

	public String[] getAppreservesUnit() {
		return appreservesUnit;
	}

	public void setAppreservesUnit(String[] appreservesUnit) {
		this.appreservesUnit = appreservesUnit;
	}

	public String[] getAppsellnum() {
		return appsellnum;
	}

	public void setAppsellnum(String[] appsellnum) {
		this.appsellnum = appsellnum;
	}

	public String[] getAppsellunit() {
		return appsellunit;
	}

	public void setAppsellunit(String[] appsellunit) {
		this.appsellunit = appsellunit;
	}

	public String[] getAppprerecyclenum() {
		return appprerecyclenum;
	}

	public void setAppprerecyclenum(String[] appprerecyclenum) {
		this.appprerecyclenum = appprerecyclenum;
	}

	public String[] getAppprerecycleunit() {
		return appprerecycleunit;
	}

	public void setAppprerecycleunit(String[] appprerecycleunit) {
		this.appprerecycleunit = appprerecycleunit;
	}
	
	public String[] getAppktotalNum() {
		return appktotalNum;
	}

	public void setAppktotalNum(String[] appktotalNum) {
		this.appktotalNum = appktotalNum;
	}

	public String[] getDrg8005DbType() {
		return drg8005DbType;
	}

	public void setDrg8005DbType(String[] drg8005DbType) {
		this.drg8005DbType = drg8005DbType;
	}

	public String[] getDrg8005DbTypeId() {
		return drg8005DbTypeId;
	}

	public void setDrg8005DbTypeId(String[] drg8005DbTypeId) {
		this.drg8005DbTypeId = drg8005DbTypeId;
	}
	
	public String getStatus2() {
		return checkGet(status2);
	}
	public void setStatus2(String status2) {
		this.status2 = checkSet(status2);
	}

	public String getShowExtension() {
		return checkGet(showExtension);
	}

	public void setShowExtension(String showExtension) {
		this.showExtension = checkSet(showExtension);
	}
	
	
}
