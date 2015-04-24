package com.kangdainfo.tcbw.view.vmed;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.Datetime;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Med7001Db;
import com.kangdainfo.tcbw.model.bo.Med7002Db;
import com.kangdainfo.tcbw.model.bo.Med7003Db;
import com.kangdainfo.tcbw.model.bo.Med7005Db;
import com.kangdainfo.tcbw.util.TCBWCommon;
import com.kangdainfo.web.listener.MyServletContext;


public class VMED1301F extends SuperBean
{
	String q_applNoS;//案件編號起
	String q_applNoE;//案件編號迄
	String q_status;//案件狀態
	String q_publDept, q_publDeptName, q_publDeptCodeId;//發布單位
	String q_situation;//警訊類別
	String q_medMainCategory, q_medMainCategoryName, q_medMainCategoryCodeId;//醫材主類別
	String q_chProduct;//產品名稱
	
	String q_publDateS, q_publDateE;//發布日期起迄
	
	String q_publdateS7005, q_publdateE7005;//公告日期起迄
	
	String q_ispublnews;//是否發佈新聞稿
	String q_ispublconsumer;//是否發佈消費者知識服務網
	String q_isfdaweb;//是否發佈署網
	String q_lamp;//燈號
	
	String q_recycleclass;//國外回收等級	VARCHAR(2)
	String q_dataRevDateS,q_dataRevDateE;//資料收集日期	VARCHAR(7)
	String q_contextsummary;//警訊摘要
	String q_productlotNo;//產品型號及批號
	String q_effectarea;//影響地區
	String q_isrecycle;//是否涉及回收
	String q_istranslate;//是否摘譯

	
	String q_permitKey;//許可證字
	String q_permitNo;//許可證號
	String q_chProduct7002;//中文品名
	String q_applicationName;//許可證持有商
	String q_manufactorName;//製造商/製造廠
	String q_medModel;//醫材型號
	String q_lotNo;//醫材批號

	String restartreason;//重新起案理由
	String changereason;//改版原因
	String id;
	private String q_trans;//是否為歷史移轉資料
	
	
	@Override
	public Object doQueryOne() throws Exception {
		
		VMED1301F obj = this;
		return obj;
	}

	public String getHql()
	{
		String hql="";
		
		if (!"".equals(getQ_applNoS()))
			hql += " and applNo >= " + Common.sqlChar(getQ_applNoS());
		
		if (!"".equals(getQ_applNoE()))
			hql += " and applNo <= " + Common.sqlChar(getQ_applNoE());
		
		if (!"".equals(getQ_status()))
			hql += " and status=" + Common.sqlChar(getQ_status());
		
		if (!"".equals(getQ_publDeptCodeId()))
			hql += " and publDept=" + Common.sqlChar(getQ_publDeptCodeId());
		
		if (!"".equals(getQ_situation()))
			hql += " and situation=" + Common.sqlChar(getQ_situation());
		
		if (!"".equals(getQ_medMainCategory()))
			hql += " and medMainCategory=" + Common.sqlChar(getQ_medMainCategory());
		
		if (!"".equals(getQ_chProduct()))
			hql += " and chProduct like " + Common.likeSqlChar(getQ_chProduct());
		
		if (!"".equals(getQ_publDateS()))
			hql += " and publDate >= " + Common.sqlChar(getQ_publDateS());
		
		if (!"".equals(getQ_publDateE()))
			hql += " and publDate <= " + Common.sqlChar(getQ_publDateE());
		
		//國外回收等級	VARCHAR(2)
		if (!"".equals(getQ_recycleclass()))
			hql += " and recycleclass= " + Common.sqlChar(getQ_recycleclass());
		
		//資料收集日期	
		if (!"".equals(getQ_dataRevDateS()))
			hql += " and id in (select med7001Db.id from Med7005Db where dataRevDate >= " + Common.sqlChar(getQ_dataRevDateS()) + ")";
		
		if (!"".equals(getQ_dataRevDateE()))
			hql += " and id in (select med7001Db.id from Med7005Db where dataRevDate <= " + Common.sqlChar(getQ_dataRevDateE()) + ")";
	
		if (!"".equals(getQ_publdateS7005()))
			hql += " and id in (select med7001Db.id from Med7005Db where publdate >= " + Common.sqlChar(getQ_publdateS7005()) + ")";

		if (!"".equals(getQ_publdateE7005()))
			hql += " and id in (select med7001Db.id from Med7005Db where publdate <= " + Common.sqlChar(getQ_publdateE7005()) + ")";

		
		//警訊摘要
		if (!"".equals(getQ_contextsummary()))
			hql += " and contextsummary like  " + Common.likeSqlChar(getQ_contextsummary());
		
		//製造商/製造廠
		if (!"".equals(getQ_manufactorName()))
			hql += " and manufactorName like  " + Common.likeSqlChar(getQ_manufactorName());
		
		//產品型號及批號
		if (!"".equals(getQ_productlotNo()))
			hql += " and productlotNo like  " + Common.likeSqlChar(getQ_productlotNo());
		
		//影響地區
		if (!"".equals(getQ_effectarea()))
			hql += " and effectarea like  " + Common.likeSqlChar(getQ_effectarea());
		
		//是否涉及回收
		if (!"".equals(getQ_isrecycle()))
			hql += " and isrecycle=" + Common.sqlChar(getQ_isrecycle());
		
		//是否摘譯
		if (!"".equals(getQ_istranslate()))
			hql += " and istranslate=" + Common.sqlChar(getQ_istranslate());
		
		
		if (!"".equals(getQ_permitKey()))
			hql += " and id in (select med7001Db.id from Med7002Db where permitKey= " + Common.sqlChar(getQ_permitKey()) + ")";
		
		if (!"".equals(getQ_permitNo()))
			hql += " and id in (select med7001Db.id from Med7002Db where permitNo= " + Common.sqlChar(getQ_permitNo()) + ")";
		
		if (!"".equals(getQ_applicationName()))
			hql += " and id in (select med7001Db.id from Med7002Db where applicationName like " + Common.likeSqlChar(getQ_applicationName()) + ")";
		
		if (!"".equals(getQ_chProduct()))
			hql += " and id in (select med7001Db.id from Med7002Db where chProduct like " + Common.likeSqlChar(getQ_chProduct()) + ")";
		
		//製造商/製造廠
		if (!"".equals(getQ_manufactorName()))
			hql += " and id in (select med7001Db.id from Med7002Db where manufactorName like " + Common.likeSqlChar(getQ_manufactorName()) + ")";
		
		//醫材型號
		if (!"".equals(getQ_medModel()))
			hql += " and id in (select med7001Db.id from Med7002Db where medModel=" + Common.sqlChar(getQ_medModel()) + ")";
		
		//醫材批號
		if (!"".equals(getQ_lotNo()))
			hql += " and id in (select med7001Db.id from Med7002Db where lotNo=" + Common.sqlChar(getQ_lotNo()) + ")";
		
		
		if (!"".equals(getQ_ispublnews()))
			hql += " and id in (select med7001Db.id from Med7005Db where ispublnews= " + Common.sqlChar(getQ_ispublnews()) + ")";

		
		if (!"".equals(getQ_ispublconsumer()))
			hql += " and id in (select med7001Db.id from Med7005Db where ispublconsumer= " + Common.sqlChar(getQ_ispublconsumer()) + ")";

		if (!"".equals(getQ_isfdaweb()))
			hql += " and id in (select med7001Db.id from Med7005Db where isfdaweb= " + Common.sqlChar(getQ_isfdaweb()) + ")";

		if (!"".equals(getQ_lamp()))
			hql += " and id in (select med7001Db.id from Med7005Db where lamp= " + Common.sqlChar(getQ_lamp()) + ")";

		//是否為歷史移轉資料：Y=是；N=否
		if("Y".equals(getQ_trans())) {
			hql += " and trans = 'Y'";
		}
		if("N".equals(getQ_trans())) {
			hql += " and isnull(trans,'') = ''";
		}
		
		
		return hql;
		
	}
	
	@Override
	public Object doQueryAll() throws Exception 
	{		
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();

		String hql = " from Med7001Db where 1=1  ";
		
		hql += Common.get(getHql());
		
		System.out.println("VMED1301F=doQueryAll="+hql);
		
		this.processCurrentPageAttribute(ServiceGetter.getInstance().getCommonService().loadCount(hql));
		
		if (getTotalRecord() > 0) 
		{
			if (getState().indexOf("query")<0) 
				ServiceGetter.getInstance().getCommonService().getHibernateTemplate().clear();
			
			java.util.List objList = ServiceGetter.getInstance().getCommonService().load(hql+" order by id desc", this.getRecordStart(), this.getPageSize());
			
			if (objList != null && objList.size() > 0) 
			{
				java.util.Map<String, String> publDeptMap = TCBWCommon.getCommonCodeMap("CONPUBLDEPT");
				java.util.Map<String, String> situationMap = TCBWCommon.getCommonCodeMap("CONWARNING");
				java.util.Map<String, String> medMainCategoryMap = TCBWCommon.getCommonCodeMap("MEDMCT");
				java.util.Map<String, String> statusMap = TCBWCommon.getCommonCodeMap("MEDQTSTATUS");
				
				java.util.Iterator it = objList.iterator();
				
				while (it.hasNext()) 
				{
					Med7001Db o = (Med7001Db) it.next();
					String rowArray[] = new String[9];
					rowArray[0] = Common.get(o.getId());
					rowArray[1] = Common.get(o.getApplNo());
					rowArray[2] = Common.get(statusMap.get(o.getStatus()));
					rowArray[3] = Common.get(publDeptMap.get(o.getPublDept()));
					rowArray[4] = Common.get(situationMap.get(o.getSituation()));
					rowArray[5] = Common.get(medMainCategoryMap.get(o.getMedMainCategory()));
					rowArray[6] = Common.get(o.getChProduct());
					rowArray[7] = Common.get(o.getPublDate());
					rowArray[8] = Common.get(o.getIncountrysituation());
					arrList.add(rowArray);					
				}
			}
		}
		return arrList;
	}
	
	//重新起案
	public void doReStatus(String id) throws Exception 
	{
		Med7001Db obj=(Med7001Db)View.getObject("from Med7001Db where id="+id);
		
		if(obj!=null)
		{
			
			if(obj.getMed7002Dbs().size()>0)
			{
				if("N".equals(obj.getIstranslate()))
				  obj.setStatus("20");//廠商回復中
			}
			else
			{
				obj.setStatus("10");//廠商確認中
			}	
			
			obj.setRestartreason(getRestartreason());//重新起案理由
			ServiceGetter.getInstance().getCommonService().update(obj);
			
			//歷程紀錄
			ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("MED06",obj.getId(), obj.getApplNo(), obj.getStatus(), "重新起案理由", TCBWCommon.getCurrentUserId());
		}	
	}
	
	//公告改版
	public void doReNoticerevision(String id) throws Exception 
	{
		Med7001Db obj=(Med7001Db)View.getObject("from Med7001Db where id="+id);
		
		if(obj!=null)
		{
			
			obj.setStatus("60");//公告改版中

			obj.setRestartreason(getChangereason());//改版原因
			ServiceGetter.getInstance().getCommonService().update(obj);
			//歷程紀錄
			ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("MED06",obj.getId(), obj.getApplNo(), obj.getStatus(), "改版原因", TCBWCommon.getCurrentUserId());
		
			if(obj.getMed7003Dbs().size()==0)
			{
				Med7003Db med7003=new Med7003Db();
				Med7001Db med7001=new Med7001Db();
				med7001.setId(obj.getId());
				med7003.setMed7001Db(med7001);
				med7003.setChangedate(Datetime.getYYYMMDD());//改版日期
				med7003.setChangereason(getChangereason());//改版原因
				Med7005Db med7005=(Med7005Db)View.getObject("from Med7005Db where med7001Db.id="+id);
				if(med7005!=null)
				{	
				  med7003.setChangesubject(med7005.getSubject());//改版標題
				  med7003.setChangecontext(med7005.getContext());//改版內容
				  med7003.setChangeremark(med7005.getRemark());//改版備註
				}
				med7003.setChangeVersion("0");//版本
				
				ServiceGetter.getInstance().getCommonService().save(med7003);
			}	
		
			if(obj.getMed7003Dbs().size()>=0)
			{
				Med7003Db med7003=new Med7003Db();
				Med7001Db med7001=new Med7001Db();
				med7001.setId(obj.getId());
				med7003.setMed7001Db(med7001);
				//med7003.setChangedate(Datetime.getYYYMMDD());//改版日期
				med7003.setChangereason(getChangereason());//改版原因
				Med7005Db med7005=(Med7005Db)View.getObject("from Med7005Db where med7001Db.id="+id);
				if(med7005!=null)
				{	
				  med7003.setChangesubject(med7005.getSubject());//改版標題
				  med7003.setChangecontext(med7005.getContext());//改版內容
				  med7003.setChangeremark(med7005.getRemark());//改版備註
				}
				
				if(obj.getMed7003Dbs().size()==0)
				  med7003.setChangeVersion(Common.get(obj.getMed7003Dbs().size()+1));//版本
				else
				  med7003.setChangeVersion(Common.get(obj.getMed7003Dbs().size()));//版本
				
				ServiceGetter.getInstance().getCommonService().save(med7003);
			}	
		
		}	
	}
	
	public void exportExcelTable(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		// 讀取EXCEL樣版
		InputStream st = new FileInputStream(new File(MyServletContext.getInstance().getServletContext().getRealPath("/report/vmed/VMED0201.xlsx")));

		XSSFWorkbook workbook = new XSSFWorkbook(st);
		
		StringBuilder hql = new StringBuilder();
		
		hql.append(" from Med7001Db");
		
		hql.append(Common.get(getHql()));
		
		java.util.List list = ServiceGetter.getInstance().getTcbwService().load(hql.toString());
		
		System.out.println("VMED1301F=exportExcelTable="+hql.toString());

		if (list!=null && list.size()>0) 			
		{
			 java.util.Map<String, String> pkidMap = TCBWCommon.getCommonCodeMap("MEDPKID");
			 java.util.Map<String, String> publDeptMap = TCBWCommon.getCommonCodeMap("CONPUBLDEPT");
			 java.util.Map<String, String> situationMap = TCBWCommon.getCommonCodeMap("CONWARNING");
			 
			 
             XSSFCellStyle styleRow1 = workbook.createCellStyle();
			 //邊框
			 styleRow1.setBorderBottom((short) 1);
			 styleRow1.setBorderTop((short) 1);
			 styleRow1.setBorderLeft((short) 1);
			 styleRow1.setBorderRight((short) 1);
			 styleRow1.setWrapText(true); // 自動換行
			
			 
			 XSSFCellStyle styleRow2 = workbook.createCellStyle();
			 //邊框
			 styleRow2.setBorderBottom((short) 1);
			 styleRow2.setBorderTop((short) 1);
			 styleRow2.setBorderLeft((short) 1);
			 styleRow2.setBorderRight((short) 1);
			 
			 XSSFFont font = workbook.createFont();
			 font.setBoldweight(font.BOLDWEIGHT_BOLD);//粗體
			 font.setFontName("標楷體");//字體
		     font.setFontHeightInPoints((short) 20);//字體大小
			 
			 styleRow2.setFont(font); // 設定字體
			 styleRow2.setAlignment(XSSFCellStyle.ALIGN_CENTER); // 水平置中
			 styleRow2.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER); // 垂直置中
			 
			 
			 // 處理工作SHEET
			 XSSFSheet sheet = workbook.getSheetAt(0);
			 
			 int rowCount=1;
			 
			 XSSFRow row = sheet.createRow(1);	
			 
			 row = sheet.createRow(0);
			 
			 XSSFCell a00 = row.createCell(0);
			 a00.setCellValue("國 際 食 品 藥 物 安 全 資 訊 監 測 訊 息 表_"+Datetime.getYYYMMDD()+"(風管組)");
			 a00.setCellStyle(styleRow2);
			 
			 for (int j=0; j<list.size(); j++) 
	    	 {
				Med7001Db med7001Db = (Med7001Db)list.get(j);	
				
				row = sheet.createRow(rowCount+2);	
				
				//編號類別
				XSSFCell a0 = row.createCell(0);										
				a0.setCellValue(rowCount);
				a0.setCellStyle(styleRow1);
				
				//產品類別
				XSSFCell a1 = row.createCell(1);										
				a1.setCellValue(Common.get("醫療器材"));
				a1.setCellStyle(styleRow1);
				
				//資料收集日期
				XSSFCell a2 = row.createCell(2);										
				a2.setCellValue(Common.get(med7001Db.getDataRevDate()));
				a2.setCellStyle(styleRow1);
				
				//發佈單位
				XSSFCell a3 = row.createCell(3);										
				a3.setCellValue(Common.get(publDeptMap.get(med7001Db.getPublDept())));
				a3.setCellStyle(styleRow1);
				
				//狀況--警訊類別
				XSSFCell a4 = row.createCell(4);										
				a4.setCellValue(Common.get(situationMap.get(med7001Db.getSituation())));
				a4.setCellStyle(styleRow1);
				
				//發佈日期
				XSSFCell a5 = row.createCell(5);										
				a5.setCellValue(Common.get(med7001Db.getPublDate()));
				a5.setCellStyle(styleRow1);
				
				//訊息主題--產品名稱
				XSSFCell a6 = row.createCell(5);										
				a6.setCellValue(Common.get(med7001Db.getChProduct()));
				a6.setCellStyle(styleRow1);
				
				//資訊內容摘要--警訊摘要
				XSSFCell a7 = row.createCell(5);										
				a7.setCellValue(Common.get(med7001Db.getContextsummary()));
				a7.setCellStyle(styleRow1);
				
				//產品批號--產品型號及批號
				XSSFCell a8 = row.createCell(8);										
				a8.setCellValue(Common.get(med7001Db.getProductlotNo()));
				a8.setCellStyle(styleRow1);
				
				//資料來源--原始網頁
				XSSFCell a9 = row.createCell(9);										
				a9.setCellValue(Common.get(med7001Db.getDatasourWebSite()));
				a9.setCellStyle(styleRow1);
				
	            String iseffectinternal="N";//是否國內有受影響產品
	            String permit="";//許可證字號
	            String remark="";//後續處理

				if(med7001Db.getMed7002Dbs().size()>0)
				{
					java.util.Iterator it1 = med7001Db.getMed7002Dbs().iterator();
					
					while(it1.hasNext())
					{
						Med7002Db med7002Db = (Med7002Db)it1.next();
						//只有一筆有Y，則顯示Y
						if("Y".equals(Common.get(med7002Db.getIseffectinternal())))
						  iseffectinternal="Y";
						
						if(permit.length()>0){permit+="、";}
						
						if(!"".equals(Common.get(med7002Db.getPermitKey())))
						   permit+=pkidMap.get(Common.get(med7002Db.getPermitKey()))+"字";
						
						if(!"".equals(Common.get(med7002Db.getPermitNo())))
						   permit+=Common.get(med7002Db.getPermitNo())+"號";
						
						if(remark.length()>0){remark+="、";}
						
						if(!"".equals(Common.get(med7002Db.getApplicationName())))
							remark+=Common.get(med7002Db.getApplicationName());
						
						if(!"".equals(Common.get(med7002Db.getReplysummary())))
							remark+=Common.get(med7002Db.getReplysummary());
						
					}
				}	
				
				//產品是否進口---是否國內有受影響產品
				XSSFCell a10 = row.createCell(10);										
				a10.setCellValue(iseffectinternal);
				a10.setCellStyle(styleRow1);
			
				Med7005Db med7005Db = (Med7005Db)View.getObject("from Med7005Db where med7001Db.id="+med7001Db.getId());
				
				if(med7005Db!=null)
				{	
				  //是否發佈新聞稿
				  XSSFCell a11 = row.createCell(11);										
				  a11.setCellValue(med7005Db.getIspublnews());
				  a11.setCellStyle(styleRow1);
				  
				  //是否發佈消費者知識服務網
				  XSSFCell a12 = row.createCell(12);										
				  a12.setCellValue(med7005Db.getIspublconsumer());
				  a12.setCellStyle(styleRow1);
				  
				  //消費者知識服務網上架日期
				  XSSFCell a13 = row.createCell(13);										
				  a13.setCellValue(med7005Db.getPublconsumerDate());
				  a13.setCellStyle(styleRow1);
				  
				  //燈號
				  XSSFCell a14 = row.createCell(14);										
				  a14.setCellValue(med7005Db.getLamp());
				  a14.setCellStyle(styleRow1);
				}

				//查詢單位
				XSSFCell a15 = row.createCell(15);										
				a15.setCellValue("全國藥物不良反應通報中心");
				a15.setCellStyle(styleRow1);
				
				//備註--許可證字號
				XSSFCell a16 = row.createCell(16);	
				a16.setCellValue(permit);
				a16.setCellStyle(styleRow1);
				
				//後續處理--廠商回復資料(許可證持有商+回復摘要)
				XSSFCell a17 = row.createCell(17);										
				a17.setCellValue(remark);
				a17.setCellStyle(styleRow1);
				 
				rowCount++;
	    	 }
				
			 st.close();
		 }
		 
		 // 寫入新檔案
		 File oFile = new File("報表"+Datetime.getHHMMSS()+".xlsx");
		 FileOutputStream osFile = new FileOutputStream(oFile);
		 workbook.write(osFile);
		 osFile.close();
			
		 // 輸出檔案
		 OutputStream os = null;
		 FileInputStream is = null;
		 try
		 {
		        response.setContentType("application/x-download");
		    	response.setHeader("Cache-control","");
			    response.setContentLength((int)oFile.length());	        
			    response.setHeader("Content-Disposition", "attachment; filename=\"" + oFile.getName() + "\"");	
			    
		    	byte b[] = new byte[(int)oFile.length()];
		    	if (oFile.isFile())
		    	{
		    		response.flushBuffer();
		    	    is = new FileInputStream(oFile);
		    	    os = response.getOutputStream();
		    	    int read = 0;
		    	    while ((read = is.read(b)) != -1)
		    	    {
		    	    	os.write(b,0,read);
		    	    }
		    	}
		  }
		  catch(Exception x)
		  {
		    	try
		    	{
		    		response.sendError(500,"產製錯誤！");
		    	}
		    	catch(Exception e)
		    	{
		    		e.printStackTrace();
		    	}
		  }
		  finally
		  {
		    	if (os!=null) os.close();
		        if (is!=null) is.close();
		  } 
		
		
	}
	
	@Override
	public void doCreate() throws Exception {
		
	}

	@Override
	public void doUpdate() throws Exception {
		
	}

	@Override
	public void doDelete() throws Exception {
		
	}
	
	public String getQ_applNoS(){ return checkGet(q_applNoS); }
	public void setQ_applNoS(String s){ q_applNoS=checkSet(s); }
	public String getQ_applNoE(){ return checkGet(q_applNoE); }
	public void setQ_applNoE(String s){ q_applNoE=checkSet(s); }
	public String getQ_publDept(){ return checkGet(q_publDept); }
	public void setQ_publDept(String s){ q_publDept=checkSet(s); }
	public String getQ_publDeptName(){ return checkGet(q_publDeptName); }
	public void setQ_publDeptName(String s){ q_publDeptName=checkSet(s); }
	public String getQ_publDeptCodeId(){ return checkGet(q_publDeptCodeId); }
	public void setQ_publDeptCodeId(String s){ q_publDeptCodeId=checkSet(s); }
	public String getQ_situation(){ return checkGet(q_situation); }
	public void setQ_situation(String s){ q_situation=checkSet(s); }
	public String getQ_medMainCategory(){ return checkGet(q_medMainCategory); }
	public void setQ_medMainCategory(String s){ q_medMainCategory=checkSet(s); }
	public String getQ_medMainCategoryName(){ return checkGet(q_medMainCategoryName); }
	public void setQ_medMainCategoryName(String s){ q_medMainCategoryName=checkSet(s); }
	public String getQ_medMainCategoryCodeId(){ return checkGet(q_medMainCategoryCodeId); }
	public void setQ_medMainCategoryCodeId(String s){ q_medMainCategoryCodeId=checkSet(s); }
	public String getQ_chProduct(){ return checkGet(q_chProduct); }
	public void setQ_chProduct(String s){ q_chProduct=checkSet(s); }
	public String getQ_publDateS(){ return checkGet(q_publDateS); }
	public void setQ_publDateS(String s){ q_publDateS=checkSet(s); }
	public String getQ_publDateE(){ return checkGet(q_publDateE); }
	public void setQ_publDateE(String s){ q_publDateE=checkSet(s); }
	public String getQ_publdateS7005() {return checkGet(q_publdateS7005);}
	public void setQ_publdateS7005(String qPubldateS7005) {q_publdateS7005 = checkSet(qPubldateS7005);}
	public String getQ_publdateE7005() {return checkGet(q_publdateE7005);}
	public void setQ_publdateE7005(String qPubldateE7005) {q_publdateE7005 = checkSet(qPubldateE7005);}
	public String getQ_ispublnews() {return checkGet(q_ispublnews);}
	public void setQ_ispublnews(String qIspublnews) {q_ispublnews = checkSet(qIspublnews);}
	public String getQ_ispublconsumer() {return checkGet(q_ispublconsumer);}
	public void setQ_ispublconsumer(String qIspublconsumer) {q_ispublconsumer = checkSet(qIspublconsumer);}
	public String getQ_isfdaweb() {return checkGet(q_isfdaweb);}
	public void setQ_isfdaweb(String qIsfdaweb) {q_isfdaweb = checkSet(qIsfdaweb);}
	public String getQ_lamp() {return checkGet(q_lamp);}
	public void setQ_lamp(String qLamp) {q_lamp = checkSet(qLamp);}

	public String getQ_status() {
		return checkGet(q_status);
	}

	public void setQ_status(String qStatus) {
		q_status = checkSet(qStatus);
	}

	public String getQ_recycleclass() {
		return checkGet(q_recycleclass);
	}

	public void setQ_recycleclass(String qRecycleclass) {
		q_recycleclass = checkSet(qRecycleclass);
	}

	
	
	public String getQ_dataRevDateS() {
		return checkGet(q_dataRevDateS);
	}

	public void setQ_dataRevDateS(String qDataRevDateS) {
		q_dataRevDateS = checkSet(qDataRevDateS);
	}

	public String getQ_dataRevDateE() {
		return checkGet(q_dataRevDateE);
	}

	public void setQ_dataRevDateE(String qDataRevDateE) {
		q_dataRevDateE = checkSet(qDataRevDateE);
	}

	public String getQ_contextsummary() {
		return checkGet(q_contextsummary);
	}

	public void setQ_contextsummary(String qContextsummary) {
		q_contextsummary = checkSet(qContextsummary);
	}

	public String getQ_productlotNo() {
		return checkGet(q_productlotNo);
	}

	public void setQ_productlotNo(String qProductlotNo) {
		q_productlotNo = checkSet(qProductlotNo);
	}

	public String getQ_effectarea() {
		return checkGet(q_effectarea);
	}

	public void setQ_effectarea(String qEffectarea) {
		q_effectarea = checkSet(qEffectarea);
	}

	public String getQ_isrecycle() {
		return checkGet(q_isrecycle);
	}

	public void setQ_isrecycle(String qIsrecycle) {
		q_isrecycle = checkSet(qIsrecycle);
	}

	public String getQ_permitKey() {
		return checkGet(q_permitKey);
	}

	public void setQ_permitKey(String qPermitKey) {
		q_permitKey = checkSet(qPermitKey);
	}

	public String getQ_permitNo() {
		return checkGet(q_permitNo);
	}

	public void setQ_permitNo(String qPermitNo) {
		q_permitNo = checkSet(qPermitNo);
	}

	public String getQ_applicationName() {
		return checkGet(q_applicationName);
	}

	public void setQ_applicationName(String qApplicationName) {
		q_applicationName = checkSet(qApplicationName);
	}

	public String getQ_manufactorName() {
		return checkGet(q_manufactorName);
	}

	public void setQ_manufactorName(String qManufactorName) {
		q_manufactorName = checkSet(qManufactorName);
	}

	public String getQ_medModel() {
		return checkGet(q_medModel);
	}

	public void setQ_medModel(String qMedModel) {
		q_medModel = checkSet(qMedModel);
	}

	public String getQ_lotNo() {
		return checkGet(q_lotNo);
	}

	public void setQ_lotNo(String qLotNo) {
		q_lotNo = checkSet(qLotNo);
	}

	public String getQ_istranslate() {
		return checkGet(q_istranslate);
	}

	public void setQ_istranslate(String qIstranslate) {
		q_istranslate = checkSet(qIstranslate);
	}

	public String getQ_chProduct7002() {
		return checkGet(q_chProduct7002);
	}

	public void setQ_chProduct7002(String qChProduct7002) {
		q_chProduct7002 = checkSet(qChProduct7002);
	}

	public String getRestartreason() {
		return checkGet(restartreason);
	}

	public void setRestartreason(String restartreason) {
		this.restartreason = checkSet(restartreason);
	}

	public String getId() {
		return checkGet(id);
	}

	public void setId(String id) {
		this.id = checkSet(id);
	}

	public String getChangereason() {
		return checkGet(changereason);
	}

	public void setChangereason(String changereason) {
		this.changereason = checkSet(changereason);
	}

	public String getQ_trans() {
		return checkGet(q_trans);
	}

	public void setQ_trans(String q_trans) {
		this.q_trans = checkSet(q_trans);
	}

	
	
}
