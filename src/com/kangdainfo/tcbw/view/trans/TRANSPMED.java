package com.kangdainfo.tcbw.view.trans;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.set.ListOrderedSet;



import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.Datetime;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;
import com.kangdainfo.common.util.XlsUtil;
import com.kangdainfo.common.util.filestore.ContentTypeConfiguration;
import com.kangdainfo.tcbw.model.bo.Med7001Db;
import com.kangdainfo.tcbw.model.bo.Med7002Db;
import com.kangdainfo.tcbw.model.bo.Med7005Db;
import com.kangdainfo.tcbw.model.bo.Med9001Db;
import com.kangdainfo.tcbw.model.bo.Med9002Db;
import com.kangdainfo.tcbw.util.TCBWCommon;
import com.kangdainfo.util.ListUtil;




public class TRANSPMED extends SuperBean
{

	
	private String q_filePath;
	private String msg;
	private String people;
	
	
	private StringBuilder transMsg;
	
	String fds[];
	public String[] getFds(){return fds;	}
	public void setFds(String[] s){ fds = s;}
	
	public String getMsg() {return checkGet(msg);}
	public void setMsg(String msg) {this.msg = checkSet(msg);}
	
	public StringBuilder getTransMsg() {return transMsg;}
	public void setTransMsg(StringBuilder transMsg) {this.transMsg = transMsg;}
	public String getQ_filePath() {return this.checkGet(q_filePath);}
	public void setQ_filePath(String qFilePath) {q_filePath = checkSet(qFilePath);}
	
	public String getPeople() {return checkGet(people);}
	public void setPeople(String people) {this.people = checkSet(people);}
	
	public String date(String date)
	{
		if(!"".equals(date))
		{	
		   //System.out.println("xxxx==="+date);
			   
		   String[] str=date.replace("12:00:00","").split(" ");
	    	
		   if(str.length==3)
		   {  
			   date=Common.formatFrontString(str[2],3,'0')+"/"+str[1]+"/"+str[0];
		   }
		}
		return Datetime.getRocDateTransFromYYYYMMDD(date);
	}
	
	public String date2(String date)
	{
		if(!"".equals(date))
		{		   
		   String[] str=date.split("/");
	    	
		   if(str.length==3)
		   {  
			   date=Common.formatFrontString(str[0],3,'0')+"/"+str[1]+"/"+str[2];
		   }
		}
		return Datetime.getRocDateTransFromYYYYMMDD(date);
	}
	
	
	public String chDate(String date)
	{
		String value="";
		
		if(!"".equals(date))
		{	
		  
		   if(date.length()>=7)
		   {  
			   String year=date.substring(0,date.indexOf("年"));
			   value+=Common.formatFrontString(year,3,'0');
			   
			   String mm=date.substring(date.indexOf("年")+1,date.indexOf("月"));
			   value+=Common.formatFrontString(mm,2,'0');
			   
			   String dd=date.substring(date.indexOf("月")+1,date.indexOf("日"));
			   value+=Common.formatFrontString(dd,2,'0');
	
		   }
		}
		return value;
	}
	
	public String dateformatRearString(String date)
	{
		String value="";
		if(!"".equals(date))
		{	
		  value=Common.formatFrontString(Datetime.getRocDateTransFromYYYYMMDD(date),7,'0');
		}
		
		return value;
		
	}
	
	
	public void trans() throws Exception 
	{	

		//permitKey	許可證字
		java.util.Map<String,String>  permitKeyMap=TCBWCommon.getMap("select codeName,codeId from CommonCode where commonCodeKind.codeKindId='MEDPKID' ");
		
		this.transMsg = new StringBuilder();

		if (!"".equals(getQ_filePath())) 
		{
			String[] arrFileName=null;
			arrFileName=getQ_filePath().split(":;:");

			if (arrFileName.length>1) 
			{
				String filestoreLocation = ServiceGetter.getInstance().getMyServletContext().getServletContext().getInitParameter("filestoreLocation");
				File f = new File(filestoreLocation+File.separator+arrFileName[0]+File.separator+arrFileName[1]);
			
				//醫療器材定期安全監視資料.xls
				if (f.isFile() && f.exists() && f.canRead()) 
				{
					Workbook workbook = Workbook.getWorkbook(new FileInputStream(f));
					
					//通過Workbook的getSheet方法選擇第一個工作簿（從0開始）
					Sheet st = workbook.getSheet(0);
					   
					//獲取Sheet表中所包含的總row數
					int rows=st.getRows();
					
					java.util.List<Med9001Db> saveList = new java.util.ArrayList<Med9001Db>();
				
					
					   
					//alter table MED9001_DB ALTER COLUMN  MonitorNo nvarchar(40)
					//alter table MED9001_DB ALTER COLUMN  chProduct nvarchar(100)
					//alter table MED9001_DB ALTER COLUMN  enProduct nvarchar(200)
					//alter table MED9002_DB ALTER COLUMN  reportreceiveno nvarchar(100)
					//alter table MED9001_DB ALTER COLUMN  MonitorNo nvarchar(100)
					for(int i=2;i<rows ;i=i+2)
					{
						
						if(!"".equals(Common.get(chDate(st.getCell(0,i).getContents()))))
						{	
						Med9001Db obj = new Med9001Db();

						obj.setTrans("Y");
						
						//加開此欄位 bulletinDate		A-奇數	PSUR	公告日期
						//公告日期
						obj.setBulletinDate(chDate(st.getCell(0,i).getContents()));
						System.out.println("公告日期===="+obj.getBulletinDate());
						
						//monitorNo	監控文號	varchar	20	B-奇數	PSUR	公告字號
						//公告字號
						obj.setMonitorNo(Common.get(st.getCell(1,i).getContents()));
						System.out.println("公告字號===="+obj.getMonitorNo());

						//permitKey	許可證字	varchar	2   C-奇數	PSUR	許可證字號
						//許可證字號
						String permitKeyStr=Common.get(st.getCell(2,i).getContents());
						
		                permitKeyStr=permitKeyStr.replace("字","");
		                obj.setPermitKey(Common.get(permitKeyMap.get(Common.get(permitKeyStr))));
					    
		                System.out.println("許可證字號===="+obj.getPermitKey());
						
						//permitNo	許可證號	varchar	14							
					    String permitNoStr=Common.get(permitKeyStr);
		                if(permitNoStr.indexOf("第")!=-1 && permitNoStr.indexOf("號")!=-1)
		                {
		                  obj.setPermitNo(permitNoStr.substring(permitNoStr.indexOf("第")+1,permitNoStr.indexOf("號")));
		                  System.out.println("許可證字號===="+obj.getPermitNo());
		                }
		                
		                
						//chProduct	商品名稱中文	varchar	100					D-奇數	PSUR	監視醫材(中文名稱)
					    //監視醫材(中文名稱)
		                obj.setChProduct(Common.get(st.getCell(3,i).getContents()));
					    System.out.println("監視醫材(中文名稱)===="+obj.getChProduct()); 
						
						
						//enProduct	商品名稱英文	varchar	100					D-偶數	PSUR	監視醫材(英文名稱)
					    //監視醫材(英文名稱)
					    obj.setEnProduct(Common.get(st.getCell(3,i+1).getContents()));
					    System.out.println("監視醫材(英文名稱)===="+obj.getEnProduct());
						
						
					    //MED9001_DB	applicationName	許可證持有商	varchar	100					E-奇數	PSUR	醫材廠商
					    //醫材廠商
					    obj.setApplicationName(Common.get(st.getCell(4,i).getContents()));
					    System.out.println("醫材廠商===="+obj.getApplicationName());
						   
					          
					    //MED9001_DB	monitorSDate	監控起日	varchar	7	G-奇數	PSUR	執行起算日
					    //執行起算日
					    obj.setMonitorSDate(Common.get(date(st.getCell(6,i).getContents())));
					    System.out.println("執行起算日===="+obj.getMonitorSDate());
					    
					    
					    //MED9001_DB	monitorEDate	監控迄日	varchar	7	H-奇數	PSUR	期滿日期
					    //期滿日期
					    obj.setMonitorEDate(Common.get(date(st.getCell(7,i).getContents())));
					    System.out.println("期滿日期===="+obj.getMonitorEDate());
					    
					    //MED9001_DB	reportIssuenum	報告繳交期數	numeric	NULL  F-奇數	PSUR	監視期數
					    //監視期數
					    obj.setReportIssuenum(Common.getLong(st.getCell(5,i).getContents()));
					    System.out.println("監視期數===="+obj.getReportIssuenum());

			               
					    //加開此欄位(僅限顯示)		I-奇數	PSUR	評估負責人	
					    //評估負責
					    obj.setAssessPerson(Common.get(st.getCell(8,i+1).getContents()));
					    System.out.println("評估負責人===="+obj.getAssessPerson());
					    
					    //加開此欄位(僅限顯示)		AF-奇數	PSUR	預定提會時間	日期
					    obj.setScheduledDate(Common.get(date(st.getCell(31,i).getContents())));
					    System.out.println("預定提會時間===="+obj.getScheduledDate());
					    
					    
					    //加開此欄位(僅限顯示)		AF-偶數	PSUR	確定提會時間	日期
					    obj.setDetermineDate(Common.get(date(st.getCell(31,i+1).getContents())));
					    System.out.println("確定提會時間===="+obj.getDetermineDate());
					    
			
					    //monitorremark	監控備註	varchar	100			AM-奇數	PSUR	備註
					    //備註
					    obj.setMonitorremark(Common.get(st.getCell(32,i).getContents()));
					    System.out.println("備註===="+obj.getMonitorremark());
					       

					    java.util.Set dtlSet9002 = new ListOrderedSet();
					    
					    
					    //第1期
					    Med9002Db obj9002 = new Med9002Db();
						obj9002.setMed9001Db(obj);
						
						obj9002.setReporttype("01");
						obj9002.setReportIssueno(Common.getLong("1"));
						
						//prehanddate	報告-預計繳交日期		各期定期/總結	J-奇數	PSUR	第01期定期報告-預計繳交日期
						obj9002.setPrehanddate(date(st.getCell(9,i).getContents()));
						//第1期定期安全性報告
					    System.out.println("第1期定期安全性報告(上)===="+date(st.getCell(9,i).getContents()));

					    //handdate	報告-實際繳交日期
					    obj9002.setHanddate(date(st.getCell(9,i+1).getContents()));
					    System.out.println("第1期定期安全性報告(下)===="+date(st.getCell(9,i+1).getContents()));
						
					    //reportreceiveno	報告-報告收文字號    AU-偶數	PSUR	第01期定期報告-公文文號
					    obj9002.setReportreceiveno(st.getCell(42,i+1).getContents());
					    System.out.println("第01期定期報告-公文文號===="+date(st.getCell(42,i+1).getContents()));
					    
					    // assessremark	評估-評估備註	varchar	100		
					    //遲交(1)
					    obj9002.setAssessremark(st.getCell(10,i).getContents());
					    System.out.println("遲交(1)===="+st.getCell(10,i).getContents());
					    
                        dtlSet9002.add(obj9002);	
					    obj.setMed9002Dbs(dtlSet9002);
						saveList.add(obj);	
						
						//第2期
						obj9002 = new Med9002Db();
					    obj9002.setMed9001Db(obj);
					    
					    obj9002.setReporttype("01");
						obj9002.setReportIssueno(Common.getLong("2"));
					    
					    //prehanddate	報告-預計繳交日期		各期定期/總結	J-奇數	PSUR	第02期定期報告-預計繳交日期
					    obj9002.setPrehanddate(date(st.getCell(11,i).getContents()));
						//第2期定期安全性報告
						System.out.println("第2期定期安全性報告(上)===="+date(st.getCell(11,i).getContents()));

						//handdate	報告-實際繳交日期
						obj9002.setHanddate(date(st.getCell(11,i+1).getContents()));
						System.out.println("第2期定期安全性報告(下)===="+date(st.getCell(11,i+1).getContents()));
							
						//reportreceiveno	報告-報告收文字號    AU-偶數	PSUR	第02期定期報告-公文文號
						obj9002.setReportreceiveno(st.getCell(43,i+1).getContents());
						System.out.println("第02期定期報告-公文文號===="+date(st.getCell(43,i+1).getContents()));
						    
						// assessremark	評估-評估備註	varchar	100		
						//遲交(2)
						obj9002.setAssessremark(st.getCell(12,i).getContents());
						System.out.println("遲交(2)===="+st.getCell(12,i).getContents());
						    
						dtlSet9002.add(obj9002);	
						
					    obj.setMed9002Dbs(dtlSet9002);
					       
						saveList.add(obj);
						
					    //第3期
						obj9002 = new Med9002Db();
					    obj9002.setMed9001Db(obj);
					    
					    obj9002.setReporttype("01");
						obj9002.setReportIssueno(Common.getLong("3"));
					    
					    //prehanddate	報告-預計繳交日期		各期定期/總結	J-奇數	PSUR	第03期定期報告-預計繳交日期
					    obj9002.setPrehanddate(date(st.getCell(13,i).getContents()));
						//第1期定期安全性報告
						System.out.println("第3期定期安全性報告(上)===="+date(st.getCell(13,i).getContents()));

						//handdate	報告-實際繳交日期
						obj9002.setHanddate(date(st.getCell(13,i+1).getContents()));
						System.out.println("第3期定期安全性報告(下)===="+date(st.getCell(13,i+1).getContents()));
							
						//reportreceiveno	報告-報告收文字號    AU-偶數	PSUR	第03期定期報告-公文文號
						obj9002.setReportreceiveno(st.getCell(44,i+1).getContents());
						System.out.println("第03期定期報告-公文文號===="+date(st.getCell(44,i+1).getContents()));
						    
						// assessremark	評估-評估備註	varchar	100		
						//遲交(3)
						obj9002.setAssessremark(st.getCell(14,i).getContents());
						System.out.println("遲交(3)===="+st.getCell(14,i).getContents());
						    
						dtlSet9002.add(obj9002);	
						
					    obj.setMed9002Dbs(dtlSet9002);
					       
						saveList.add(obj);
						
		                //第4期
						obj9002 = new Med9002Db();
					    obj9002.setMed9001Db(obj);
					    
					    obj9002.setReporttype("01");
						obj9002.setReportIssueno(Common.getLong("4"));
					    
					    //prehanddate	報告-預計繳交日期		各期定期/總結	J-奇數	PSUR	第04期定期報告-預計繳交日期
					    obj9002.setPrehanddate(date(st.getCell(15,i).getContents()));
						//第1期定期安全性報告
						System.out.println("第4期定期安全性報告(上)===="+date(st.getCell(15,i).getContents()));

						//handdate	報告-實際繳交日期
						obj9002.setHanddate(date(st.getCell(15,i+1).getContents()));
						System.out.println("第4期定期安全性報告(下)===="+date(st.getCell(15,i+1).getContents()));
							
						//reportreceiveno	報告-報告收文字號    AU-偶數	PSUR	第04期定期報告-公文文號
						obj9002.setReportreceiveno(st.getCell(45,i+1).getContents());
						System.out.println("第04期定期報告-公文文號===="+date(st.getCell(45,i+1).getContents()));
						    
						// assessremark	評估-評估備註	varchar	100		
						//遲交(4)
						obj9002.setAssessremark(st.getCell(16,i).getContents());
						System.out.println("遲交(4)===="+st.getCell(16,i).getContents());
						    
						dtlSet9002.add(obj9002);	
						
					    obj.setMed9002Dbs(dtlSet9002);
		       
						saveList.add(obj);

					    //第5期 
					    obj9002 = new Med9002Db();
						obj9002.setMed9001Db(obj);
						
					    obj9002.setReporttype("01");
						obj9002.setReportIssueno(Common.getLong("5"));
						
						//prehanddate	報告-預計繳交日期		各期定期/總結	J-奇數	PSUR	第05期定期報告-預計繳交日期
						obj9002.setPrehanddate(date(st.getCell(17,i).getContents()));
					    //第1期定期安全性報告
					    System.out.println("第5期定期安全性報告(上)===="+date(st.getCell(17,i).getContents()));

						//handdate	報告-實際繳交日期
						obj9002.setHanddate(date(st.getCell(17,i+1).getContents()));
						System.out.println("第5期定期安全性報告(下)===="+date(st.getCell(17,i+1).getContents()));
								
						//reportreceiveno	報告-報告收文字號    AU-偶數	PSUR	第05期定期報告-公文文號
						obj9002.setReportreceiveno(st.getCell(46,i+1).getContents());
						System.out.println("第05期定期報告-公文文號===="+date(st.getCell(46,i+1).getContents()));
							    
						// assessremark	評估-評估備註	varchar	100		
						//遲交(5)
						obj9002.setAssessremark(st.getCell(18,i).getContents());
						System.out.println("遲交(5)===="+st.getCell(18,i).getContents());
							    
						dtlSet9002.add(obj9002);	
							
						obj.setMed9002Dbs(dtlSet9002);  
					       
						saveList.add(obj);
						
						//第6期 
					    obj9002 = new Med9002Db();
						obj9002.setMed9001Db(obj);
						
					    obj9002.setReporttype("01");
						obj9002.setReportIssueno(Common.getLong("6"));
						
						//prehanddate	報告-預計繳交日期		各期定期/總結	J-奇數	PSUR	第06期定期報告-預計繳交日期
						obj9002.setPrehanddate(date(st.getCell(19,i).getContents()));
					    //第1期定期安全性報告
					    System.out.println("第6期定期安全性報告(上)===="+date(st.getCell(19,i).getContents()));

						//handdate	報告-實際繳交日期
						obj9002.setHanddate(date(st.getCell(19,i+1).getContents()));
						System.out.println("第6期定期安全性報告(下)===="+date(st.getCell(19,i+1).getContents()));
								
						//reportreceiveno	報告-報告收文字號    AU-偶數	PSUR	第06期定期報告-公文文號
						obj9002.setReportreceiveno(st.getCell(47,i+1).getContents());
						System.out.println("第06期定期報告-公文文號===="+date(st.getCell(47,i+1).getContents()));
							    
						// assessremark	評估-評估備註	varchar	100		
						//遲交(6)
						obj9002.setAssessremark(st.getCell(20,i).getContents());
						System.out.println("遲交(6)===="+st.getCell(20,i).getContents());
							    
						dtlSet9002.add(obj9002);	
						
						obj.setMed9002Dbs(dtlSet9002);  
					       
						saveList.add(obj);
						
						//總結報告
						obj9002 = new Med9002Db();
						obj9002.setMed9001Db(obj);
						
						obj9002.setReporttype("02");
					    obj9002.setReportIssueno(Common.getLong("0"));
						
						//prehanddate	報告-預計繳交日期		各期定期/總結	J-奇數	PSUR	總結報告-預計繳交日期
						obj9002.setPrehanddate(date(st.getCell(29,i).getContents()));
					    //第1期定期安全性報告
					    System.out.println("總結報告(上)===="+date(st.getCell(29,i).getContents()));

						//handdate	報告-實際繳交日期
						obj9002.setHanddate(date(st.getCell(29,i+1).getContents()));
						System.out.println("總結報告(下)===="+date(st.getCell(29,i+1).getContents()));
								
						//reportreceiveno	報告-報告收文字號    AU-偶數	PSUR	總結報告-公文文號
						obj9002.setReportreceiveno(st.getCell(47,i+1).getContents());
						System.out.println("總結報告-公文文號===="+date(st.getCell(47,i+1).getContents()));
							    
						// assessremark	評估-評估備註	varchar	100		
						//遲交
						obj9002.setAssessremark(st.getCell(20,i).getContents());
						System.out.println("總結報告 遲交===="+st.getCell(20,i).getContents());
							    
						dtlSet9002.add(obj9002);	
						
						obj.setMed9002Dbs(dtlSet9002);  
					       
						saveList.add(obj);
						
						if (saveList!=null && saveList.size()>0) 
						  ServiceGetter.getInstance().getTcbwService().saveBatch(saveList);
						
					}
					}
					 workbook.close();
					 
					 
						
					 transMsg.append("<font color='#0000CC'>PSUR匯入成功</font>").append("<br>");
					 //transMsg.append("<font color='#0000CC'>PSUR新增筆數：</font><font color='red'>").append(saveList.size()).append("</font><br>");
					 //setMsg("匯入成功!");
						//dir.delete();
				}

			}	
			
			
			if (!"".equals(getQ_filePath())) 
			{
				String[] arrFileName1=null;
				arrFileName1=getQ_filePath().split(":;:");

				if (arrFileName1.length>1) 
				{
					String filestoreLocation = ServiceGetter.getInstance().getMyServletContext().getServletContext().getInitParameter("filestoreLocation");
					File f = new File(filestoreLocation+File.separator+arrFileName1[0]+File.separator+arrFileName1[1]);
					File dir = new File(filestoreLocation+File.separator+arrFileName[0]);
					//醫療器材定期安全監視資料.xls
					if (f.isFile() && f.exists() && f.canRead()) 
					{
						Workbook workbook = Workbook.getWorkbook(new FileInputStream(f));
						
						//通過Workbook的getSheet方法選擇第一個工作簿（從0開始）
						Sheet st = workbook.getSheet(1);
						   
						//獲取Sheet表中所包含的總row數
						int rows=st.getRows();
						
						java.util.List<Med9001Db> saveList = new java.util.ArrayList<Med9001Db>();
						
						for(int i=2;i<rows ;i=i+2)
						{
							Med9001Db obj = new Med9001Db();
							
							obj.setTrans("Y");
							
							//加開此欄位 bulletinDate		A-奇數	PSUR	公告日期
							//公告日期
							obj.setBulletinDate(chDate(st.getCell(0,i).getContents()));
							System.out.println("公告日期===="+obj.getBulletinDate());
							
							//monitorNo	監控文號	varchar	20	B-奇數	PSUR	公告字號
							//公告字號
							obj.setMonitorNo(Common.get(st.getCell(1,i).getContents()));
							System.out.println("公告字號===="+obj.getMonitorNo());

							//permitKey	許可證字	varchar	2   C-奇數	PSUR	許可證字號
							//許可證字號
							String permitKeyStr=Common.get(st.getCell(2,i).getContents());
							
			                permitKeyStr=permitKeyStr.replace("字","");
			                obj.setPermitKey(Common.get(permitKeyMap.get(Common.get(permitKeyStr))));
						    
			                System.out.println("許可證字號===="+obj.getPermitKey());
							
							//permitNo	許可證號	varchar	14							
						    String permitNoStr=Common.get(permitKeyStr);
			                if(permitNoStr.indexOf("第")!=-1 && permitNoStr.indexOf("號")!=-1)
			                {
			                  obj.setPermitNo(permitNoStr.substring(permitNoStr.indexOf("第")+1,permitNoStr.indexOf("號")));
			                  System.out.println("許可證字號===="+obj.getPermitNo());
			                }									

			                //chProduct	商品名稱中文	varchar	100					D-奇數	PSUR	監視醫材(中文名稱)
						    //監視醫材(中文名稱)
			                obj.setChProduct(Common.get(st.getCell(3,i).getContents()));
			                System.out.println("監視醫材(中文名稱)===="+obj.getChProduct()); 
						

							//MED9001_DB	applicationName	許可證持有商	varchar	100					E-奇數	PSUR	醫材廠商
						    //醫材廠商
						    obj.setApplicationName(Common.get(st.getCell(4,i).getContents()));
						    System.out.println("醫材廠商===="+obj.getApplicationName());
							   
						          
						    //MED9001_DB	monitorSDate	監控起日	varchar	7	G-奇數	PSUR	執行起算日
						    //執行起算日
						    obj.setMonitorSDate(Common.get(dateformatRearString(st.getCell(5,i).getContents())));
						    System.out.println("執行起算日===="+obj.getMonitorSDate());

						    //MED9001_DB	monitorEDate	監控迄日	varchar	7	H-奇數	PSUR	期滿日期
						    //期滿日期
						    obj.setMonitorEDate(Common.get(dateformatRearString(st.getCell(6,i).getContents())));
						    System.out.println("期滿日期===="+obj.getMonitorEDate());
							
							
						    //加開此欄位(僅限顯示)		I-奇數	PSUR	評估負責人	
						    //評估負責
						    obj.setAssessPerson(Common.get(st.getCell(7,i+1).getContents()));
						    System.out.println("評估負責人===="+obj.getAssessPerson());

							
							java.util.Set dtlSet9002 = new ListOrderedSet();
							
							
							//第1期
						    Med9002Db obj9002 = new Med9002Db();
							obj9002.setMed9001Db(obj);
							
							obj9002.setReporttype("01");
							obj9002.setReportIssueno(Common.getLong("1"));
							
							//prehanddate	報告-預計繳交日期		各期定期/總結	J-奇數	PSUR	第01期定期報告-預計繳交日期
							String prehanddate1=st.getCell(8,i).getContents();
							if("Y".equals(prehanddate1))
							  obj9002.setPrehanddate("Y");
							else
							  obj9002.setPrehanddate(date2(prehanddate1));
							
						    System.out.println("第1次報告應繳交時間===="+obj9002.getPrehanddate());

						    //handdate	報告-實際繳交日期
						    String handdate1=st.getCell(8,i+1).getContents();
						    if("Y".equals(handdate1))
						      obj9002.setHanddate("Y");
							else
						      obj9002.setHanddate(date2(handdate1));
						    
						    System.out.println("第1次實際繳交時間===="+obj9002.getHanddate());
							
							dtlSet9002.add(obj9002);	
						    obj.setMed9002Dbs(dtlSet9002);
							saveList.add(obj);
							
							
							//第2期
						    obj9002 = new Med9002Db();
							obj9002.setMed9001Db(obj);
							
							obj9002.setReporttype("01");
							obj9002.setReportIssueno(Common.getLong("2"));
							
							//prehanddate	報告-預計繳交日期		各期定期/總結	J-奇數	PSUR	第02期定期報告-預計繳交日期
							String prehanddate2=st.getCell(9,i).getContents();
							
							if("Y".equals(prehanddate2))
								obj9002.setPrehanddate("Y");
							else
							    obj9002.setPrehanddate(date2(prehanddate2));
							
						    System.out.println("第2次報告應繳交時間===="+obj9002.getPrehanddate());

						    //handdate	報告-實際繳交日期
						    String handdate2=st.getCell(9,i+1).getContents();
						    if("Y".equals(handdate2))
								obj9002.setHanddate("Y");
						    else
						        obj9002.setHanddate(date2(handdate2));
						    
						    System.out.println("第2次實際繳交時間===="+obj9002.getHanddate());
							
							dtlSet9002.add(obj9002);	
						    obj.setMed9002Dbs(dtlSet9002);
							saveList.add(obj);
							
							
							//第3期
						    obj9002 = new Med9002Db();
							obj9002.setMed9001Db(obj);
							
							obj9002.setReporttype("01");
							obj9002.setReportIssueno(Common.getLong("3"));
							
							//prehanddate	報告-預計繳交日期		各期定期/總結	J-奇數	PSUR	第03期定期報告-預計繳交日期
							String prehanddate3=st.getCell(10,i).getContents();
						    
							if("Y".equals(prehanddate3))
								obj9002.setPrehanddate("Y");
						    else
						        obj9002.setPrehanddate(date2(prehanddate3));
							
						    System.out.println("第3次報告應繳交時間===="+obj9002.getPrehanddate());

						    //handdate	報告-實際繳交日期
						    String handdate3=st.getCell(10,i+1).getContents();
						    
						    if("Y".equals(handdate3))
						    	obj9002.setHanddate("Y");
						    else
						        obj9002.setHanddate(date2(handdate3));
						    
						    System.out.println("第3次實際繳交時間===="+obj9002.getHanddate());
							
							dtlSet9002.add(obj9002);	
						    obj.setMed9002Dbs(dtlSet9002);
							saveList.add(obj);
							
							//第4期
						    obj9002 = new Med9002Db();
							obj9002.setMed9001Db(obj);
							
							obj9002.setReporttype("01");
							obj9002.setReportIssueno(Common.getLong("4"));
							
							//prehanddate	報告-預計繳交日期		各期定期/總結	J-奇數	PSUR	第04期定期報告-預計繳交日期
							String prehanddate4=st.getCell(11,i).getContents();
							if("Y".equals(prehanddate4))
								obj9002.setPrehanddate("Y");
							else
							    obj9002.setPrehanddate(date2(prehanddate4));
						
						    System.out.println("第4次報告應繳交時間===="+obj9002.getPrehanddate());

						    //handdate	報告-實際繳交日期
						    String handdate4=st.getCell(11,i+1).getContents();
						    
						    if("Y".equals(handdate4))
						    	obj9002.setHanddate("Y");
						    else
						        obj9002.setHanddate(date2(handdate4));
						    
						    System.out.println("第4次實際繳交時間===="+obj9002.getHanddate());
							
							dtlSet9002.add(obj9002);	
						    obj.setMed9002Dbs(dtlSet9002);
							saveList.add(obj);
							
							//第5期
						    obj9002 = new Med9002Db();
							obj9002.setMed9001Db(obj);
							
							obj9002.setReporttype("01");
							obj9002.setReportIssueno(Common.getLong("5"));
							
							//prehanddate	報告-預計繳交日期		各期定期/總結	J-奇數	PSUR	第05期定期報告-預計繳交日期
			                String prehanddate5=st.getCell(12,i).getContents();
						    
						    if("Y".equals(prehanddate5))
						    	obj9002.setPrehanddate("Y");
						    else
							    obj9002.setPrehanddate(date2(prehanddate5));
						
						    System.out.println("第5次報告應繳交時間===="+obj9002.getPrehanddate());

						    //handdate	報告-實際繳交日期
			                String handdate5=st.getCell(12,i+1).getContents();
						    
						    if("Y".equals(handdate5))
						    	obj9002.setHanddate("Y");
						    else
						        obj9002.setHanddate(date2(handdate5));
						    
						    System.out.println("第5次實際繳交時間===="+obj9002.getHanddate());
							
							dtlSet9002.add(obj9002);	
						    obj.setMed9002Dbs(dtlSet9002);
							saveList.add(obj);
							

							//第6期
						    obj9002 = new Med9002Db();
							obj9002.setMed9001Db(obj);
							
							obj9002.setReporttype("01");
							obj9002.setReportIssueno(Common.getLong("6"));
							
							//prehanddate	報告-預計繳交日期		各期定期/總結	J-奇數	PSUR	第06期定期報告-預計繳交日期
			                String prehanddate6=st.getCell(13,i).getContents();
						    
						    if("Y".equals(prehanddate6))
						    	obj9002.setPrehanddate("Y");
						    else
							    obj9002.setPrehanddate(date2(prehanddate6));
							//第1期定期安全性報告
						    System.out.println("第6次報告應繳交時間===="+obj9002.getPrehanddate());

						    //handdate	報告-實際繳交日期
			                String handdate6=st.getCell(13,i+1).getContents();
						    
						    if("Y".equals(handdate6))
						    	obj9002.setHanddate("Y");
						    else
						        obj9002.setHanddate(date2(handdate6));
						    
						    System.out.println("第6次實際繳交時間===="+obj9002.getHanddate());
							
							dtlSet9002.add(obj9002);	
						    obj.setMed9002Dbs(dtlSet9002);
							saveList.add(obj);
							
							
							//總結報告
							obj9002 = new Med9002Db();
							obj9002.setMed9001Db(obj);
								
							obj9002.setReporttype("02");
							obj9002.setReportIssueno(Common.getLong("0"));
								
							//prehanddate	報告-預計繳交日期		各期定期/總結	J-奇數	PSUR	第06期定期報告-預計繳交日期
			                String prehanddate=st.getCell(14,i).getContents();
						    
						    if("Y".equals(prehanddate))
						    	obj9002.setPrehanddate("Y");
						    else
							    obj9002.setPrehanddate(date2(prehanddate));
							
							System.out.println("總結報告應繳交時間===="+obj9002.getPrehanddate());

							//handdate	報告-實際繳交日期
			                String handdate=st.getCell(14,i+1).getContents();
						    
						    if("Y".equals(handdate))
						    	obj9002.setHanddate("Y");
						    else
							    obj9002.setHanddate(date2(handdate));
						    
							System.out.println("總結報告實際繳交時間===="+obj9002.getHanddate());
								
							dtlSet9002.add(obj9002);	
							obj.setMed9002Dbs(dtlSet9002);
						    saveList.add(obj);
							
							//加開此欄位(僅限顯示)	AF-奇數	PSUR	預定提會時間	日期
						    obj.setScheduledDate(Common.get(date(st.getCell(15,i).getContents())));
						    System.out.println("預定提會時間===="+obj.getScheduledDate());
						    
						    obj.setClosingDate(Common.get(date(st.getCell(15,i+1).getContents())));
						    System.out.println("提會結案時間===="+obj.getClosingDate());
						    
						    if (saveList!=null && saveList.size()>0) 
							   ServiceGetter.getInstance().getTcbwService().saveBatch(saveList);
						    
						   
						}
						
						transMsg.append("<font color='#0000CC'>PSUR期滿名單匯入成功</font>").append("<br>");
						//transMsg.append("<font color='#0000CC'>PSUR期滿名單新增筆數：</font><font color='red'>").append(saveList.size()).append("</font><br>");
						f.delete();
						dir.delete();
					    
					}
				
				
				}
			}
			
			
			
			
			
			
			
			

		}
	}
	
	
	
	
	
	@Override
	public void doCreate() throws Exception {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void doDelete() throws Exception {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Object doQueryAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Object doQueryOne() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void doUpdate() throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	
	
}
