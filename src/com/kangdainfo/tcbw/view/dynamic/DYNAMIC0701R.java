package com.kangdainfo.tcbw.view.dynamic;



import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.set.ListOrderedSet;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.Source;
import jxl.Workbook;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.Datetime;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;
import com.kangdainfo.common.util.filestore.ContentTypeConfiguration;
import com.kangdainfo.tcbw.model.bo.Export0001Db;
import com.kangdainfo.tcbw.model.bo.Export0002Db;
import com.kangdainfo.tcbw.model.bo.Variant0001Db;
import com.kangdainfo.tcbw.model.bo.Variant0002Db;
import com.kangdainfo.tcbw.util.TCBWCommon;



public class DYNAMIC0701R extends SuperBean
{
	private String id;
	private String[] code;//前端匯出欄位
	private String[] manyCode;//前端匯出欄位
    private String htmlValue;//前端HTML
    private String codeValue;//
    
    private String variantName;//變式名稱
	private String variantExplain;//變式說明
	
	//med9001
	private String q_applNoS;//案件編號
	private String q_applNoE;//案件編號
	private String[] q_status;//案件狀態
	private String q_monitorNo;//監控文號
	private String q_permitKey;//許可證字
	private String q_permitNo;//許可證號
	private String q_chProduct;//商品名稱中文
	private String q_applicationName;//許可證持有商

	private String q_medMainCategory;//醫材主類別
	private String q_medSecCategory;//醫材次類別
	private String q_monitorSDate;//監控起日
	private String q_monitorEDate;//監控迄日

	//med9002
	private String[] q_reporttype;//報告-報告類別
	private String[] q_handstatus;//報告-繳交狀態
	private String q_prehanddateS;//報告-預計繳交日期
	private String q_prehanddateE;//報告-預計繳交日期
	private String q_handdateS;//報告-實際繳交日期
	private String q_handdateE;//報告-實際繳交日期
	private String q_reportreceiveno;//報告-報告收文字號
	private String[] q_assessresult;//評估-評估結果

	//med9003
	private String q_noticereupdateNo;//報告-通知補件文號
	private String q_reupdateNo;//報告-補件文號


	
	
	//查詢條件
	public String condition()
	{
		String condition="";
		
		//案件編號
		if(!"".equals(getQ_applNoS()))
			condition+=" and applNo9001 >="+Common.sqlChar(getQ_applNoS());
		if(!"".equals(getQ_applNoE()))
			condition+=" and applNo9001 <="+Common.sqlChar(getQ_applNoE());
		
		//案件狀態
		String statusStr="";
		if(null!=getQ_status() && getQ_status().length>0)
		{
			for(int i=0;i<getQ_status().length;i++)
			{
				if(statusStr.length()>0)statusStr+=",";
				statusStr+="'"+getQ_status()[i]+"'";
			}	
		}	
		
		if(statusStr.length()>0)
			condition+=" and status9001 in ("+statusStr+")";
		
		//監控文號
		if(!"".equals(getQ_monitorNo()))
			condition+=" and monitorNo9001="+Common.sqlChar(getQ_monitorNo());
		
		//許可證字號
		if(!"".equals(getQ_permitKey()))
			condition+=" and permitKey9001="+Common.sqlChar(getQ_permitKey());
		if(!"".equals(getQ_permitNo()))
			condition+=" and permitNo9001="+Common.sqlChar(getQ_permitNo());
		
		//中文品名
		if(!"".equals(getQ_chProduct()))
			condition+=" and chProduct9001 like "+TCBWCommon.likeSqlChar(getQ_chProduct());
		
		//許可證持有商
		if(!"".equals(getQ_applicationName()))
			condition+=" and applicationName9001 like "+TCBWCommon.likeSqlChar(getQ_applicationName());
		
		//醫材主類別
		if(!"".equals(getQ_medMainCategory()))
			condition+=" and medMainCategory9001="+Common.sqlChar(getQ_medMainCategory());
		
		//醫材次類別
		if(!"".equals(getQ_medSecCategory()))
			condition+=" and medSecCategory9001="+Common.sqlChar(getQ_medSecCategory());
		
		//監控期間
		if(!"".equals(getQ_monitorSDate()))
			condition+=" and monitorSDate9001 >="+Common.sqlChar(getQ_monitorSDate());
		if(!"".equals(getQ_monitorEDate()))
			condition+=" and monitorEDate9001 <="+Common.sqlChar(getQ_monitorEDate());
		
		//報告類別
		String reporttypeStr="";
		if(null!=getQ_reporttype() && getQ_reporttype().length>0)
		{
			for(int i=0;i<getQ_reporttype().length;i++)
			{
				if(reporttypeStr.length()>0)reporttypeStr+=",";
				reporttypeStr+="'"+getQ_reporttype()[i]+"'";
			}	
		}	
		
		if(reporttypeStr.length()>0)
			condition+=" and reporttype9002 in ("+reporttypeStr+")";
		
		
		
		//繳交狀態
		String handstatusStr="";
		if(null!=getQ_handstatus() && getQ_handstatus().length>0)
		{
			for(int i=0;i<getQ_handstatus().length;i++)
			{
				if(handstatusStr.length()>0)handstatusStr+=",";
				handstatusStr+="'"+getQ_handstatus()[i]+"'";
			}	
		}	
		
		if(handstatusStr.length()>0)
			condition+=" and handstatus9002 in ("+handstatusStr+")";
		

		//預計繳交日期
		if(!"".equals(getQ_prehanddateS()))
			condition+=" and prehanddate9002 >="+Common.sqlChar(getQ_prehanddateS());
		if(!"".equals(getQ_prehanddateE()))
			condition+=" and prehanddate9002 <="+Common.sqlChar(getQ_prehanddateE());
		
		//實際繳交日期
		if(!"".equals(getQ_handdateS()))
			condition+=" and handdate9002 >="+Common.sqlChar(getQ_handdateS());
		if(!"".equals(getQ_handdateE()))
			condition+=" and handdate9002 <="+Common.sqlChar(getQ_handdateE());
		
		//報告收文字號
		if(!"".equals(getQ_reportreceiveno()))
			condition+=" and reportreceiveno9002 ="+Common.sqlChar(getQ_reportreceiveno());
		
		//通知補件文號
		if(!"".equals(getQ_noticereupdateNo()))
			condition+=" and noticereupdateNo9003 ="+Common.sqlChar(getQ_noticereupdateNo());
		
		
		//補件文號
		if(!"".equals(getQ_reupdateNo()))
			condition+=" and reupdateNo9003 ="+Common.sqlChar(getQ_reupdateNo());
		
		
		//評估結果
		String assessresultStr="";
		if(null!=getQ_assessresult() && getQ_assessresult().length>0)
		{
			for(int i=0;i<getQ_assessresult().length;i++)
			{
				if(assessresultStr.length()>0)assessresultStr+=",";
				assessresultStr+="'"+getQ_assessresult()[i]+"'";
			}	
		}	
		
		if(assessresultStr.length()>0)
			condition+=" and assessresult9002 in ("+assessresultStr+")";
		
		
		
		
		return condition;
		
	}
	
	//匯出Xls
	public void exportXls(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
        //表頭中文
		java.util.Map<String,String>  titleMap=TCBWCommon.getMap("select field,fieldName from Export0002Db where export0001Db.code like 'med03%'");
		//多筆表頭中文
		java.util.Map<String,String>  manyTitleMap=TCBWCommon.getMap("select manyField,fieldName from Export0002Db where export0001Db.code like 'med03%' and isMany='Y' ");
		
		//全部代碼中文
		java.util.Map<String,String>  codeNameMap=TCBWCommon.getMap("select commonCodeKind.codeKindId+codeId,codeName from CommonCode");
		codeNameMap.put("YNY","是");codeNameMap.put("YNN","否");

		
		//判斷是否有map
		java.util.Map<String,String>  isMap=TCBWCommon.getMap("select field,codeName from Export0002Db where export0001Db.code like 'med03%' and codeName is not null and codeName <> 'NULL' ");
		
		//判斷是否為多筆map
		java.util.Map<String,String>  manyNameMap=TCBWCommon.getMap("select field,manyName from Export0002Db where export0001Db.code like 'med03%' and isMany='Y' ");
		
		//
		java.util.Map<String,String>  fieldNameMap=TCBWCommon.getMap("select field,manyField from Export0002Db where export0001Db.code like 'med03%' and isMany='Y' ");
		
		//Excel檔案路徑
		File outputFile = new File(System.getProperty("java.io.tmpdir")+File.separator+"DYNAMIC0701R_"+Datetime.getHHMMSS()+ ".xls");
		//建立Excel檔案
		WritableWorkbook workbook = Workbook.createWorkbook(outputFile);
		//建立sheet1頁籤
		WritableSheet sheet1 = workbook.createSheet("醫療器材定期安全監視通報", 0);
	
		//設定字行
		WritableFont Font10 = new WritableFont(WritableFont.createFont("標楷體"),12);
		WritableCellFormat detailCenter = new  WritableCellFormat(Font10);
		//detailCenter.setVerticalAlignment(VerticalAlignment.CENTRE);
		//detailCenter.setAlignment(jxl.format.Alignment.CENTRE);
		
		//取得前端勾選匯出欄位
		String field="";
		//欄位大小暫存檔
		int[] tmpMaxLength = null;
		

		if (getCode()!=null && getCode().length>0) 
		{
			//宣告陣列長度
			tmpMaxLength = new int[getCode().length];
			
			for (int i=0; i<getCode().length; i++) 
			{
				field+=getCode()[i]+",";
				sheet1.addCell(new Label(i,0,titleMap.get(getCode()[i]),detailCenter));
				
				//放入表頭字數
				tmpMaxLength[i] = Common.get(Common.get(titleMap.get(getCode()[i]))).length()*2;
			}
		}
		
		if(field.length()>0)
		{
			//去除最後field,
		    field=field.substring(0, field.length()-1);
		
		    //將欄位塞入查詢View
		    String hql="  select ";
                   hql+=  field;
                   hql+=  ",'1'";//防止只輸入一個欄位出錯
                   hql+=" from dynamicView07 ";
                   hql+=" where 1=1 ";
                   
                   hql+=condition(); 
                   
                   hql+=" group by applNo9001,status9001,monitorNo9001,permitKey9001, ";
                   hql+=" permitNo9001,chProduct9001,enProduct9001,medapprovedate9001,medEffectiveDate9001,";
                   hql+=" applicationName9001,manufactorName9001,manufactorCountry9001,";
                   hql+=" medclass9001,medMainCategory9001,medSecCategory9001,medModel9001,";
                   hql+=" monitorSDate9001,monitorEDate9001,reportIssuenum9001,intervalmonth9001,monitorremark9001";
                   hql+=" order by applNo9001 ";
            System.out.println("hql=="+hql);
                   
            java.util.List list = ServiceGetter.getInstance().getTcbwService().getNativeSqlQuery().load(hql.toString());
        
            if (list!=null && list.size()>0) 			
		    {
               
        	   for (int j=0; j<list.size(); j++) 
	    	   {
				  Object x[] = (Object[])list.get(j);
				 
				  for(int y=0;y<getCode().length;y++)
				  {
					  String value="";
					  //判斷是否有代碼檔
					  if(null!=isMap.get(getCode()[y]) && !"".equals(isMap.get(getCode()[y])))
					  {
						  //轉換中文名稱
						  value=codeNameMap.get(isMap.get(getCode()[y])+Common.get(x[y]));
					  }
					  else
					  {
						  value=Common.get(x[y]);
					  }	  
					  
					  sheet1.addCell(new Label(y,j+1,value,detailCenter));
					  
					  //塞入資料最大字數
					  if(Common.get(Common.get(value)).length()*2 >= tmpMaxLength[y])
  					  {
  						tmpMaxLength[y] = Common.get(value).length()*2; 
  					  }
				  } 
	    	   }
        	   
        	   //將最大欄位字數乘2
        	   for(int i=0; i<tmpMaxLength.length; i++)
       		   {
       			 sheet1.setColumnView(i, tmpMaxLength[i]*2);
      		   }
        	   
        	   
        	   java.util.Map<String,String> manyMap = new java.util.HashMap<String,String>();
        	   
        	   if (getManyCode()!=null && getManyCode().length>0) 
       		   {
        		   
       			  for (int i=0; i<getManyCode().length; i++) 
       			  {
       				 String tmp="";

       				 if(null!=manyNameMap.get(getManyCode()[i]))
       				 {
       					if(null!=manyMap.get(manyNameMap.get(getManyCode()[i])))
       					{	
       					  tmp+=manyMap.get(manyNameMap.get(getManyCode()[i]))+","+fieldNameMap.get(getManyCode()[i]);
       					  manyMap.put(manyNameMap.get(getManyCode()[i]),tmp);
       					}
       					else
       					{
       					  manyMap.put(manyNameMap.get(getManyCode()[i]),fieldNameMap.get(getManyCode()[i]));
       					}	
       				 }
       			  }
       			  
       			  if(null!=manyMap.get("Sheet1"))
       			  {
       				//建立sheet2頁籤
       				WritableSheet sheet2=workbook.createSheet("報告資料", 1);	
               	    createSheet1(sheet2,manyMap.get("Sheet1"),manyTitleMap);
       			  }
    
       		   }

		    }
		}
		
		workbook.write();			
		workbook.close();
		

		
		OutputStream os = null;
		FileInputStream is = null;	        
		try
		{
			String ct = ContentTypeConfiguration.getContentType(outputFile.getName());
			if (ct != null) 
			{
	           response.setContentType(ct);
			}		        	
			response.setHeader("Cache-control","");
			response.setContentLength((int)outputFile.length());	        
			response.setHeader("Content-Disposition", "attachment; filename=\"" + outputFile.getName() + "\"");	
			
			byte b[] = new byte[(int)outputFile.length()];
			if (outputFile.isFile()) 
			{
				response.flushBuffer();
				is = new FileInputStream(outputFile);
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
				response.sendError(500,"Template產製錯誤！");
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
			if (outputFile!=null && outputFile.exists()) outputFile.delete();
		}
		
	}
	

	//相關檢查
	public void createSheet1(WritableSheet sheet2,String title,java.util.Map<String,String> titleMap) throws Exception
	{
		//設定字行
		WritableFont Font10 = new WritableFont(WritableFont.createFont("標楷體"),12);
		WritableCellFormat detailCenter = new  WritableCellFormat(Font10);
		
		String field="";
		String[] value=null;
	 	 
	 	if(title.length()>0) 
	 	   value=title.split(",");
	 	
	 	//欄位大小暫存檔
		int[] tmpMaxLength = new int[value.length+1];
	
		sheet2.addCell(new Label(0,0,"案件編號",detailCenter));
	 	//放入表頭字數
		tmpMaxLength[0] = Common.get("案件編號").length()*2;
	 	
	 	for(int i=0;i<value.length;i++) 
	 	{
	 	  if(field.length()>0)field+=",";

	 	  field+=value[i];
	 	  sheet2.addCell(new Label(i+1,0,titleMap.get(value[i]),detailCenter));

	 	  //放入表頭字數
		  tmpMaxLength[i+1] = Common.get(titleMap.get(value[i])).length()*2;
	 	}	  
		

 	   String med2003Hql="  select applNo,"+field;
 	          med2003Hql+=" from dynamicView07_1 where 1=1 ";
 	          
 	         //報告類別
 	 		String reporttypeStr="";
 	 		if(null!=getQ_reporttype() && getQ_reporttype().length>0)
 	 		{
 	 			for(int i=0;i<getQ_reporttype().length;i++)
 	 			{
 	 				if(reporttypeStr.length()>0)reporttypeStr+=",";
 	 				reporttypeStr+="'"+getQ_reporttype()[i]+"'";
 	 			}	
 	 		}	
 	 		
 	 		if(reporttypeStr.length()>0)
 	 		   med2003Hql+=" and reporttype in ("+reporttypeStr+")";
 	          
 	 		
 	 	    //繳交狀態
 			String handstatusStr="";
 			if(null!=getQ_handstatus() && getQ_handstatus().length>0)
 			{
 				for(int i=0;i<getQ_handstatus().length;i++)
 				{
 					if(handstatusStr.length()>0)handstatusStr+=",";
 					handstatusStr+="'"+getQ_handstatus()[i]+"'";
 				}	
 			}	
 			
 			if(handstatusStr.length()>0)
 			   med2003Hql+=" and handstatus in ("+handstatusStr+")";
 	 		
 			//預計繳交日期
 			if(!"".equals(getQ_prehanddateS()))
 				med2003Hql+=" and prehanddate >="+Common.sqlChar(getQ_prehanddateS());
 			if(!"".equals(getQ_prehanddateE()))
 				med2003Hql+=" and prehanddate <="+Common.sqlChar(getQ_prehanddateE());
 			
 			//實際繳交日期
 			if(!"".equals(getQ_handdateS()))
 				med2003Hql+=" and handdate >="+Common.sqlChar(getQ_handdateS());
 			if(!"".equals(getQ_handdateE()))
 				med2003Hql+=" and handdate <="+Common.sqlChar(getQ_handdateE());
 	 		
 			//報告收文字號
 			if(!"".equals(getQ_reportreceiveno()))
 				med2003Hql+=" and reportreceiveno ="+Common.sqlChar(getQ_reportreceiveno());
 			
 			//通知補件文號
 			if(!"".equals(getQ_noticereupdateNo()))
 				med2003Hql+=" and noticereupdateNo9 ="+Common.sqlChar(getQ_noticereupdateNo());
 			
 			//補件文號
 			if(!"".equals(getQ_reupdateNo()))
 				med2003Hql+=" and reupdateNo ="+Common.sqlChar(getQ_reupdateNo());
 			
 			
 			//評估結果
 			String assessresultStr="";
 			if(null!=getQ_assessresult() && getQ_assessresult().length>0)
 			{
 				for(int i=0;i<getQ_assessresult().length;i++)
 				{
 					if(assessresultStr.length()>0)assessresultStr+=",";
 					assessresultStr+="'"+getQ_assessresult()[i]+"'";
 				}	
 			}	
 			
 			if(assessresultStr.length()>0)
 				med2003Hql+=" and assessresult in ("+assessresultStr+")";
 			
 			
 	        med2003Hql+=" order by applNo ";       
 	          
 	   System.out.println("med2003Hql=="+med2003Hql);
 	          
 	   java.util.List list2003 = ServiceGetter.getInstance().getTcbwService().getNativeSqlQuery().load(med2003Hql);
 	   
 	   if (list2003!=null && list2003.size()>0) 			
	   {
 		  java.util.Map<String, String> reporttypeMap = TCBWCommon.getCommonCodeMap("MEDRPTYPE");//報告-報告類別
 		  java.util.Map<String, String> handstatusMap = TCBWCommon.getCommonCodeMap("MEDHANDTYPE");//報告-繳交狀態
 		  java.util.Map<String, String> assessresultMap = TCBWCommon.getCommonCodeMap("MEDASREPORT");//評估-評估結果
 		

 		  int e=1;
 		  for (int j=0; j<list2003.size(); j++) 
    	  {
			   Object x[] = (Object[])list2003.get(j);
			   
			   sheet2.addCell(new Label(0,e,Common.get(x[0]),detailCenter));
			   
			   if(Common.get(x[0]).length()*2 >= tmpMaxLength[0])
			   {
			      tmpMaxLength[0] = Common.get(x[0]).length()*2; 
			   }
			   
			   for(int i=0;i<value.length;i++) 
			   {
				 if("reporttype".equals(value[i]))
					sheet2.addCell(new Label(i+1,e,reporttypeMap.get(Common.get(x[i+1])),detailCenter)); 
				 else if("handstatus".equals(value[i]))
					sheet2.addCell(new Label(i+1,e,handstatusMap.get(Common.get(x[i+1])),detailCenter));
				 else if("assessresult".equals(value[i]))
					sheet2.addCell(new Label(i+1,e,assessresultMap.get(Common.get(x[i+1])),detailCenter)); 
				 else
				    sheet2.addCell(new Label(i+1,e,Common.get(x[i+1]),detailCenter));
			 	 
			 	 if(Common.get(x[i+1]).length()*2 >= tmpMaxLength[i+1])
				 {
				     tmpMaxLength[i+1] = Common.get(x[i+1]).length()*2; 
				 }
			   }
			   
               e++;
    	  }
 		  
 		  //將最大欄位字數乘2
   	      for(int i=0; i<tmpMaxLength.length; i++)
  		  {
   	    	 sheet2.setColumnView(i, tmpMaxLength[i]*2);
 		  }
 		  
	   }

	}
	
	//存檔
	public void doSaveOrUpdate() throws Exception
	{
		try
		{
          Variant0001Db obj = (Variant0001Db)View.getObject(" from Variant0001Db where name="+ Common.sqlChar(variantName)+" and kind='med03' ");
		
		  if(obj == null) 
		    obj = new Variant0001Db();
	
		  obj.setKind("med03");
		  obj.setName(variantName);
		  obj.setExplain(variantExplain);
		
		  String field1="";
		
		  if (getCode()!=null && getCode().length>0) 
		  {
			for (int i=0; i<getCode().length; i++) 
			{
				//取得查詢欄位
				field1+=getCode()[i]+":";
			}
		  }
		  
		  if (getManyCode()!=null && getManyCode().length>0) 
		  {
			for (int i=0; i<getManyCode().length; i++) 
			{
				//取得查詢欄位
				field1+=getManyCode()[i]+":";
			}
		  }
		  
		  
		
		  if("".equals(Common.get(obj.getCreator())))
		  {
			obj.setCreator(getUserID());
			obj.setCreateDate(Datetime.getYYYMMDD());
			obj.setCreateTime(Datetime.getHHMMSS());
		  }	
		  else
		  {	
		    obj.setModifier(getUserID());
		    obj.setModifyDate(Datetime.getYYYMMDD());
		    obj.setModifyTime(Datetime.getHHMMSS());
		  }
		
		  
		  java.util.Set dtlSet = new ListOrderedSet();
		  
		  Variant0002Db variant0002 = (Variant0002Db)View.getObject(" from Variant0002Db where variant0001Db.id = " + Common.getLong(obj.getId()));
			
		  if(variant0002 == null) 
			 variant0002 = new Variant0002Db();

		   variant0002.setVariant0001Db(obj);
		
		   
		   if(field1.length()>0) field1=field1.substring(0, field1.length()-1);
		
		   //匯出來欄位
		   variant0002.setCode(field1);
		   //查詢條件與查詢值
		   variant0002.setContent(getField2());
		
		   if("".equals(Common.get(variant0002.getCreator())))
		   {
			  variant0002.setCreator(getUserID());
			  variant0002.setCreateDate(Datetime.getYYYMMDD());
			  variant0002.setCreateTime(Datetime.getHHMMSS());
		   }	
		   else
		   {	
			  variant0002.setModifier(getUserID());
			  variant0002.setModifyDate(Datetime.getYYYMMDD());
			  variant0002.setModifyTime(Datetime.getHHMMSS());
		   }

		   dtlSet.add(variant0002);
		   
		   obj.setVariant0002Dbs(dtlSet);	
		   
	       ServiceGetter.getInstance().getTcbwService().saveOrUpdate(obj);

		   setId(Common.get(obj.getId()));
		    
		   //setState("queryOne");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	//儲存查詢條件與查詢值
	public String getField2() throws Exception
	{
		String field2="";
		java.util.Map<String, String> allMap = new java.util.HashMap<String, String>();
		java.util.Map<String, String> checkboxMap = new java.util.HashMap<String, String>();
		java.util.Map<String, String> radioMap = new java.util.HashMap<String, String>();
		
		if(!"".equals(Common.get(getHtmlValue()))){		
			String[] class1 = getHtmlValue().split(";");
			for(int i=0;i<class1.length;i++){
				String[] class2 = class1[i].split(":");
				for(int j=0;j<class2.length;j++){
					if(class2[0].equals("text") || class2[0].equals("select-one")){
						allMap.put(class2[1], class2[2]);
					}else if(class2[0].equals("radio")){
						radioMap.put(class2[1], class2[2]);
					}else if(class2[0].equals("checkbox")){
						String checkboxValue="";
						if(null!=checkboxMap.get(class2[1])){	    			   
							checkboxValue+=checkboxMap.get(class2[1])+"、"+class2[2];			    		   
						}		    		   
						else{		    			   
							checkboxValue+=class2[2];
			    		}		    	
						checkboxMap.put(class2[1],checkboxValue);
					}else{
						System.out.println("Type 錯誤:"+class2[0]);
					}
				}
			}
		}
		for (Object key : allMap.keySet()) 
		{
			if(null!=allMap.get(key))
			   field2+=("text:"+key + ":" + allMap.get(key)+":");
        }
		
		for (Object key : checkboxMap.keySet()) 
		{
			if(null!=checkboxMap.get(key))
			  field2+=("checkbox:"+key + ":" + checkboxMap.get(key)+":");
        }
		
		for (Object key : radioMap.keySet()) 
		{
			if(null!=radioMap.get(key))
			  field2+=("radio:"+key + ":" + radioMap.get(key)+":");
        }
		
		return field2;	
	}
	
	
	
	@Override
	public Object doQueryOne() throws Exception 
	{	 
		DYNAMIC0701R obj = this;

		Variant0002Db c=(Variant0002Db)View.getObject("from Variant0002Db where variant0001Db.id="+Common.getLong(id));
		
		if(c!=null)
		{
			obj.setCodeValue(c.getCode());
		}	
		
		return obj;
	}

	//載入匯出欄位
	public String exportField(String code) throws Exception 
	{
		StringBuilder sb = new StringBuilder(); 	
		
        String hql=" from Export0001Db where code like 'med03%'";
		
		java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(hql + " order by id");
		
		if (objList != null && objList.size() > 0) 
		{
			int i=1;
			for(Object dtlObj : objList) 
			{				
				Export0001Db export0001Db = (Export0001Db)dtlObj;
				
				sb.append("<tr>");
				sb.append("<td nowrap class='td_form' width='15%' > ");
				sb.append("<input class='field_Q' type='checkbox'  ");
				sb.append("checkbox-group='");
				sb.append(i);
				sb.append("'  main='true'/>");
				sb.append(export0001Db.getName()+":");
				sb.append("</td>");
				sb.append("<td  width='85%'  class='td_form_white'>");
				
				sb.append("<table width='100%' >");
				
				if(export0001Db.getExport0002Dbs()!=null 
						&& export0001Db.getExport0002Dbs().size()>0)
				{
					java.util.Iterator it1 = export0001Db.getExport0002Dbs().iterator();
		
					int x=1;
					
					while(it1.hasNext())
					{
						
						if(x%3==1)
							  sb.append("<tr>");
								
						sb.append("<td width='33%' >");
						
						Export0002Db export0002Db = (Export0002Db)it1.next();
						if("Y".equals(export0002Db.getIsMany()))
						{	
						  sb.append("<input name='manyCode'  ");
						}
						else
						{
						  sb.append("<input name='code'  ");
						}	
						
						sb.append(" class='field_Q'  ");
						sb.append(" type='checkbox' ");
						sb.append(" id='");
						sb.append(export0002Db.getField());
						sb.append("'");
						
						if(code.indexOf(export0002Db.getField())!=-1)
						{
							sb.append(" checked ");
						}
						
						
						sb.append(" value='");
						sb.append(export0002Db.getField());
						sb.append("' checkbox-group='");
						sb.append(i);
						sb.append("' />");
						sb.append(export0002Db.getFieldName());
						
						//if("Y".equals(export0002Db.getException())) sb.append(" <br> ");
								
						if(x%3==0 && x==export0001Db.getExport0002Dbs().size())
					        sb.append("</tr>");
						
						x++;
						
					}
				}
				
				sb.append("</table>");
				
				sb.append("</td>");
				sb.append("</tr>");
				
				i++;
			}
		}
		return sb.toString();
		
    }
	
	@Override
	public void doDelete() throws Exception 
	{
		Variant0001Db c=(Variant0001Db)View.getObject("from Variant0001Db where kind='drg02' and name="+Common.sqlChar(getVariantName()));
			
		if(c!=null)
		{
			ServiceGetter.getInstance().getTcbwService().delete(c);
			setId("");
		}	
		else
		{
			 throw new Exception("無資料，無法刪除，請重新操作 !");
		}
	}
	
	@Override
	public Object doQueryAll() throws Exception 
	{		
		return null;
	}

	@Override
	public void doCreate() throws Exception 
	{
		
		
	}

	@Override
	public void doUpdate() throws Exception 
	{
		
		
		
	}

	
	
	
	public String getId() {return checkGet(id);}
	public void setId(String id) {this.id = checkSet(id);}
	public String getVariantName() {return checkGet(variantName);}
	public void setVariantName(String variantName) {this.variantName = checkSet(variantName);}
	public String getVariantExplain() {return checkGet(variantExplain);}
	public void setVariantExplain(String variantExplain) {this.variantExplain = checkSet(variantExplain);}
	public String[] getCode() {return checkGet(code);}
	public void setCode(String[] code) {this.code = checkSet(code);}
	public String getHtmlValue() {return htmlValue;}
	public void setHtmlValue(String htmlValue) {this.htmlValue = htmlValue;}
	public String getCodeValue() {return checkGet(codeValue);}
	public void setCodeValue(String codeValue) {this.codeValue = checkSet(codeValue);}
	public String[] getManyCode() {return checkGet(manyCode);}
	public void setManyCode(String[] manyCode) {this.manyCode = checkSet(manyCode);}

	public String getQ_applNoS() {
		return checkGet(q_applNoS);
	}

	public void setQ_applNoS(String qApplNoS) {
		q_applNoS = checkSet(qApplNoS);
	}

	public String getQ_applNoE() {
		return checkGet(q_applNoE);
	}

	public void setQ_applNoE(String qApplNoE) {
		q_applNoE = checkSet(qApplNoE);
	}

	public String[] getQ_status() {
		return checkGet(q_status);
	}

	public void setQ_status(String[] qStatus) {
		q_status = checkSet(qStatus);
	}

	public String getQ_monitorNo() {
		return checkGet(q_monitorNo);
	}

	public void setQ_monitorNo(String qMonitorNo) {
		q_monitorNo = checkSet(qMonitorNo);
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

	public String getQ_chProduct() {
		return checkGet(q_chProduct);
	}

	public void setQ_chProduct(String qChProduct) {
		q_chProduct = checkSet(qChProduct);
	}

	public String getQ_medMainCategory() {
		return checkGet(q_medMainCategory);
	}

	public void setQ_medMainCategory(String qMedMainCategory) {
		q_medMainCategory = checkSet(qMedMainCategory);
	}

	public String getQ_medSecCategory() {
		return checkGet(q_medSecCategory);
	}

	public void setQ_medSecCategory(String qMedSecCategory) {
		q_medSecCategory = checkSet(qMedSecCategory);
	}

	public String getQ_monitorSDate() {
		return checkGet(q_monitorSDate);
	}

	public void setQ_monitorSDate(String qMonitorSDate) {
		q_monitorSDate = checkSet(qMonitorSDate);
	}

	public String getQ_monitorEDate() {
		return checkGet(q_monitorEDate);
	}

	public void setQ_monitorEDate(String qMonitorEDate) {
		q_monitorEDate = checkSet(qMonitorEDate);
	}

	public String getQ_applicationName() {
		return checkGet(q_applicationName);
	}

	public void setQ_applicationName(String qApplicationName) {
		q_applicationName = checkSet(qApplicationName);
	}

	public String[] getQ_reporttype() {
		return checkGet(q_reporttype);
	}

	public void setQ_reporttype(String[] qReporttype) {
		q_reporttype = checkSet(qReporttype);
	}

	public String[] getQ_handstatus() {
		return checkGet(q_handstatus);
	}

	public void setQ_handstatus(String[] qHandstatus) {
		q_handstatus = checkSet(qHandstatus);
	}

	public String getQ_prehanddateS() {
		return checkGet(q_prehanddateS);
	}

	public void setQ_prehanddateS(String qPrehanddateS) {
		q_prehanddateS = checkSet(qPrehanddateS);
	}

	public String getQ_prehanddateE() {
		return checkGet(q_prehanddateE);
	}

	public void setQ_prehanddateE(String qPrehanddateE) {
		q_prehanddateE = checkSet(qPrehanddateE);
	}

	public String getQ_handdateS() {
		return checkGet(q_handdateS);
	}

	public void setQ_handdateS(String qHanddateS) {
		q_handdateS = checkSet(qHanddateS);
	}

	public String getQ_handdateE() {
		return checkGet(q_handdateE);
	}

	public void setQ_handdateE(String qHanddateE) {
		q_handdateE = checkSet(qHanddateE);
	}

	public String getQ_reportreceiveno() {
		return checkGet(q_reportreceiveno);
	}

	public void setQ_reportreceiveno(String qReportreceiveno) {
		q_reportreceiveno = checkSet(qReportreceiveno);
	}

	

	public String[] getQ_assessresult() {
		return checkGet(q_assessresult);
	}

	public void setQ_assessresult(String[] qAssessresult) {
		q_assessresult = checkSet(qAssessresult);
	}

	public String getQ_noticereupdateNo() {
		return checkGet(q_noticereupdateNo);
	}

	public void setQ_noticereupdateNo(String qNoticereupdateNo) {
		q_noticereupdateNo = checkSet(qNoticereupdateNo);
	}

	public String getQ_reupdateNo() {
		return checkGet(q_reupdateNo);
	}

	public void setQ_reupdateNo(String qReupdateNo) {
		q_reupdateNo = checkSet(qReupdateNo);
	}

	
	
   
	

}
