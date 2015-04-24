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

public class DYNAMIC0801R extends SuperBean
{
	private String id;
	private String[] code;//前端匯出欄位
	private String[] manyCode;//前端匯出欄位
    private String htmlValue;//前端HTML
    private String codeValue;//
    private String variantName;//變式名稱
	private String variantExplain;//變式說明
	
	private String q_applNoS;//案件編號起
	private String q_applNoE;//案件編號迄
	private String[] q_status;//案件狀態
	private String q_publDept;//發佈單位
	private String[] q_situation;//警訊類別
	private String q_medMainCategory;	//醫材主類別
	private String q_chProduct;	//產品名稱
	private String[] q_recycleclass;	//國外回收等級
	private String q_dataRevDateS;	//資料收集日期起
	private String q_dataRevDateE;	//資料收集日期迄
	private String q_publDateS;	//發佈日期起
	private String q_publDateE;	//發佈日期迄
	private String q_manufactorName;	//製造廠
	private String q_contextsummary;	//警訊摘要
	private String q_productlotNo;	//產品型號及批號
	private String q_effectarea;	//影響地區
	private String q_permitKey02;   //許可證字
	private String q_permitNo02;    //許可證號
	private String q_chProduct02;   //中文品名
	private String q_enProduct02;   //英文品名
	private String q_applicationName02;	//許可證持有商
	private String q_manufactorName02;	//製造商/製造廠
	private String q_manufactorCountry02;	//製造商/製造廠國別
	private String q_replydate02S;	//回覆日期起
	private String q_replydate02E;	//回覆日期迄
	private String q_iswarning02;	//本許可證是否為警訊內容產品
	private String q_iseffectinternal02;	//是否國內有受影響產品
	private String q_medModel02;	//醫材型號
	private String q_lotNo02;	//醫材批號
	private String q_isrecycle;	//是否涉及回收
	private String q_istranslate;	//是否摘譯
	private String q_publdate05S;	//公告日期起
	private String q_publdate05E;	//公告日期迄
	private String q_ispublnews05;	//是否發佈新聞稿
	private String q_ispublconsumer05;	//是否發佈消費者知識服務網
	private String q_isfdaweb05;	//是否發佈署網
	private String[] q_lamp05;	//燈號
	private String q_fdareceiveNo;	//fda收文文號
	private String q_posesummary;	//回文摘要
	private String q_completereceiveno;	//廠商回收完成字號
	
	
	//查詢條件
	public String condition()
	{
		String condition="";
		
		//案件編號
		if(!"".equals(getQ_applNoS()))
			condition+=" and applNo01 >="+Common.sqlChar(getQ_applNoS());
		if(!"".equals(getQ_applNoE()))
			condition+=" and applNo01 <="+Common.sqlChar(getQ_applNoE());
		
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
			condition+=" and status01 in ("+statusStr+")";
		//發佈單位
		if(!"".equals(getQ_publDept()))
			condition+=" and publDept01 ="+Common.sqlChar(getQ_publDept());
		//警訊類別
		String situationStr="";
		if(null!=getQ_situation() && getQ_situation().length>0)
		{
			for(int i=0;i<getQ_situation().length;i++)
			{
				if(situationStr.length()>0)situationStr+=",";
				situationStr+="'"+getQ_situation()[i]+"'";
			}	
		}
		if(situationStr.length()>0)
			condition+=" and situation01 in ("+situationStr+")";
		//醫材主類別
		if(!"".equals(getQ_medMainCategory()))
			condition+=" and medMainCategory01 ="+Common.sqlChar(getQ_medMainCategory());
		//產品名稱
		if(!"".equals(getQ_chProduct()))
		    condition+=" and chProduct01 like "+TCBWCommon.likeSqlChar(getQ_chProduct());
		//國外回收等級
		String recycleclassStr="";
		if(null!=getQ_recycleclass() && getQ_recycleclass().length>0)
		{
			for(int i=0;i<getQ_recycleclass().length;i++)
			{
				if(recycleclassStr.length()>0)recycleclassStr+=",";
				recycleclassStr+="'"+getQ_recycleclass()[i]+"'";
			}	
		}
		if(recycleclassStr.length()>0)
			condition+=" and recycleclass01 in ("+recycleclassStr+")";
		//資料收集日期
		if(!"".equals(getQ_dataRevDateS()))
			condition+=" and dataRevDate01 >="+Common.sqlChar(getQ_dataRevDateS());
		if(!"".equals(getQ_dataRevDateE()))
			condition+=" and dataRevDate01 <="+Common.sqlChar(getQ_dataRevDateE());
		//發佈日期
		if(!"".equals(getQ_publDateS()))
			condition+=" and publDate01 >="+Common.sqlChar(getQ_publDateS());
		if(!"".equals(getQ_publDateE()))
			condition+=" and publDate01 <="+Common.sqlChar(getQ_publDateE());
		//製造廠
		if(!"".equals(getQ_manufactorName()))
		    condition+=" and manufactorName01 like "+TCBWCommon.likeSqlChar(getQ_manufactorName());
		//警訊摘要
		if(!"".equals(getQ_contextsummary()))
		    condition+=" and contextsummary01 like "+TCBWCommon.likeSqlChar(getQ_contextsummary());
		//產品型號及批號
		if(!"".equals(getQ_productlotNo()))
		    condition+=" and productlotNo01 like "+TCBWCommon.likeSqlChar(getQ_productlotNo());
		//影響地區
		if(!"".equals(getQ_effectarea()))
		    condition+=" and effectarea01 like "+TCBWCommon.likeSqlChar(getQ_effectarea());
		//許可證字號
		if(!"".equals(getQ_permitKey02()))
			condition+=" and permitKey02 ="+Common.sqlChar(getQ_permitKey02());
		if(!"".equals(getQ_permitNo02()))
			condition+=" and permitNo02 ="+Common.sqlChar(getQ_permitNo02());
//		if(!"".equals(getQ_permitKey02()) && !"".equals(getQ_permitNo02())){
//			condition+=" and permitKeyNo02 = "+Common.sqlChar(getQ_permitKey02()+getQ_permitNo02());
//		}else if ("".equals(getQ_permitKey02()) && !"".equals(getQ_permitNo02())){
//			condition+=" and permitKeyNo02 like "+Common.sqlChar("__"+getQ_permitNo02());
//		}else if (!"".equals(getQ_permitKey02()) && "".equals(getQ_permitNo02())){
//			condition+=" and permitKeyNo02 like "+Common.sqlChar(getQ_permitKey02()+"______");
//		}
		//中文品名
		if(!"".equals(getQ_chProduct02()))
		    condition+=" and chProduct02 like "+TCBWCommon.likeSqlChar(getQ_chProduct02());
		//英文品名
		if(!"".equals(getQ_enProduct02()))
		    condition+=" and enProduct02 like "+TCBWCommon.likeSqlChar(getQ_enProduct02());
		//許可證持有商
		if(!"".equals(getQ_applicationName02()))
		    condition+=" and applicationName02 like "+TCBWCommon.likeSqlChar(getQ_applicationName02());
		//製造廠
		if(!"".equals(getQ_manufactorName02()))
		    condition+=" and manufactorName02 like "+TCBWCommon.likeSqlChar(getQ_manufactorName02());
		//製造廠國別
		if(!"".equals(getQ_manufactorCountry02()))
		    condition+=" and manufactorCountry02 like "+TCBWCommon.likeSqlChar(getQ_manufactorCountry02());
		//回覆日期
		if(!"".equals(getQ_replydate02S()))
			condition+=" and replydate02 >="+Common.sqlChar(getQ_replydate02S());
		if(!"".equals(getQ_replydate02E()))
			condition+=" and replydate02 <="+Common.sqlChar(getQ_replydate02E());
		//本許可證是否為警訊內容產品
		if(!"".equals(getQ_iswarning02()))
			condition+=" and iswarning02 ="+Common.sqlChar(getQ_iswarning02());
		//是否國內有受影響產品
		if(!"".equals(getQ_iseffectinternal02()))
			condition+=" and iseffectinternal02 ="+Common.sqlChar(getQ_iseffectinternal02());
		//醫材型號
		if(!"".equals(getQ_medModel02()))
			condition+=" and medModel02 ="+Common.sqlChar(getQ_medModel02());
		//醫材批號
		if(!"".equals(getQ_lotNo02()))
			condition+=" and lotNo02 ="+Common.sqlChar(getQ_lotNo02());
		//是否涉及回收
		if(!"".equals(getQ_isrecycle()))
			condition+=" and isrecycle01 ="+Common.sqlChar(getQ_isrecycle());
		//是否摘譯
		if(!"".equals(getQ_istranslate()))
			condition+=" and istranslate01 ="+Common.sqlChar(getQ_istranslate());
		//公告日期
		if(!"".equals(getQ_publdate05S()))
			condition+=" and publdate05 >="+Common.sqlChar(getQ_publdate05S());
		if(!"".equals(getQ_publdate05E()))
			condition+=" and publdate05 <="+Common.sqlChar(getQ_publdate05E());
		//是否發佈新聞稿
		if(!"".equals(getQ_ispublnews05()))
			condition+=" and ispublnews05 ="+Common.sqlChar(getQ_ispublnews05());
		//是否發佈消費者知識服務網
		if(!"".equals(getQ_ispublconsumer05()))
			condition+=" and ispublconsumer05 ="+Common.sqlChar(getQ_ispublconsumer05());
		//是否發佈署網
		if(!"".equals(getQ_isfdaweb05()))
			condition+=" and isfdaweb05 ="+Common.sqlChar(getQ_isfdaweb05());
		//燈號
		String lampStr="";
		if(null!=getQ_lamp05() && getQ_lamp05().length>0)
		{
			for(int i=0;i<getQ_lamp05().length;i++)
			{
				if(lampStr.length()>0)lampStr+=",";
				lampStr+="'"+getQ_lamp05()[i]+"'";
			}	
		}
		if(lampStr.length()>0)
			condition+=" and lamp05 in ("+lampStr+")";
		//fda收文文號
		if(!"".equals(getQ_fdareceiveNo()))
			condition+=" and fdareceiveNo01 ="+Common.sqlChar(getQ_fdareceiveNo());
		//回文摘要
		if(!"".equals(getQ_posesummary()))
		    condition+=" and posesummary01 like "+TCBWCommon.likeSqlChar(getQ_posesummary());
		//廠商回收完成字號
		if(!"".equals(getQ_completereceiveno()))
			condition+=" and completereceiveno01 ="+Common.sqlChar(getQ_completereceiveno());
		
		return condition;		
	}
	

		
	//匯出Xls
	public void exportXls(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
        //表頭中文
		java.util.Map<String,String>  titleMap=TCBWCommon.getMap("select field,fieldName from Export0002Db where export0001Db.code like 'med06%'");
		//多筆表頭中文
		java.util.Map<String,String>  manyTitleMap=TCBWCommon.getMap("select manyField,fieldName from Export0002Db where export0001Db.code like 'med06%' and isMany='Y' ");
		
		//全部代碼中文
		java.util.Map<String,String>  codeNameMap=TCBWCommon.getMap("select commonCodeKind.codeKindId+codeId,codeName from CommonCode");
		codeNameMap.put("YNY","是");codeNameMap.put("YNN","否");		
		
		//判斷是否有map
		java.util.Map<String,String>  isMap=TCBWCommon.getMap("select field,codeName from Export0002Db where export0001Db.code like 'med06%' and codeName is not null and codeName <> 'NULL' ");
		
		//判斷是否為多筆map
		java.util.Map<String,String>  manyNameMap=TCBWCommon.getMap("select field,manyName from Export0002Db where export0001Db.code like 'med06%' and isMany='Y' ");

		//欄位名稱map
		java.util.Map<String,String>  fieldNameMap=TCBWCommon.getMap("select field,manyField from Export0002Db where export0001Db.code like 'med06%' and isMany='Y' ");		
		
		//Excel檔案路徑
		File outputFile = new File(System.getProperty("java.io.tmpdir")+File.separator+"DYNAMIC0801R_"+Datetime.getHHMMSS()+ ".xls");
		//建立Excel檔案
		WritableWorkbook workbook = Workbook.createWorkbook(outputFile);
		
		//建立sheet1頁籤
		WritableSheet sheet1 = workbook.createSheet("醫療器材國內外安全警訊監控表", 0);
		
		
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
				//取得查詢欄位
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
		    String hql="  select distinct ";
                   hql+=  field;
                   hql+=  ",'1'";//防止只輸入一個欄位出錯
                   hql+=" from dynamicView08 ";
                   hql+=" where 1=1 ";
                   hql+=condition();    
                   hql+=" order by applNo01";
            System.out.println("hql=="+hql);
                   
            java.util.List list = ServiceGetter.getInstance().getTcbwService().getNativeSqlQuery().load(hql.toString());             
            
            if (list!=null && list.size()>0) 			
		    {               
               java.util.Map<String,String> manyMap = new java.util.HashMap<String,String>();
          
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
					  //許可證字號額外處理代碼					  
					  else if ("permitKeyNo0201".equals(getCode()[y]))
					  {
						  String[] permitKeyList = Common.get(x[y]).split(",");						
						  for(int k=0; k< permitKeyList.length; k++)
						  {								
							  if(Common.get(permitKeyList[k]) != "")
							  {
								  if(Common.get(permitKeyList[k]).length()>=2)
								  {
									  if(value.length()>0) value +=","; 
									  value += codeNameMap.get("MEDPKID"+Common.get(permitKeyList[k]).substring(0,2))+Common.get(permitKeyList[k]).substring(2);
								  }
								  else
								  {
									  value += Common.get(permitKeyList[k]);
								  }
							  }							
						  }					  
					  }
					  else
					  {
						  //System.out.println("getCode()[y])==="+getCode()[y]);
						  //System.out.println("xx==="+x[y]);
						  
						  if("chProduct0201".equals(getCode()[y]))
						  {
							  if(!"".equals(Common.get(x[y])))
							  {
								  value=Common.get(x[y]).substring(0,Common.get(x[y]).length()-1);
							  }	  
						  }
						  else if("applicationName0201".equals(getCode()[y]))
						  {
							  if(!"".equals(Common.get(x[y])))
							  {
								  value=Common.get(x[y]).substring(0,Common.get(x[y]).length()-1);
							  }	  
						  }
						  else
						  {
							  value=Common.get(x[y]);
						  }
						  
					  }	  
					  
					  sheet1.addCell(new Label(y,j+1,value,detailCenter));
					  
					  //塞入資料最大字數
					  if(Common.get(Common.get(value)).length()*2 >= tmpMaxLength[y])
  					  {
  						tmpMaxLength[y] = Common.get(value).length()*2; 
  					  }					  
					  
					  // 許可證字號,中文品名,許可證持有商例外處理(需在寫入sheet2)				   
					  if( j==0  && ("permitKeyNo0201".equals(getCode()[y]) || "chProduct0201".equals(getCode()[y]) || "applicationName0201".equals(getCode()[y])))
					  {
						   
						  String manyField ="";
						  if("permitKeyNo0201".equals(getCode()[y])){
							  manyNameMap.put(getCode()[y], "DetailSheet");
							  manyNameMap.put(getCode()[y], "DetailSheet");
							  fieldNameMap.put(getCode()[y],"permitKey02");
							  fieldNameMap.put(getCode()[y],"permitNo02");
							  manyTitleMap.put("permitKey02", "許可證字");
							  manyTitleMap.put("permitNo02", "許可證號");
							  manyField = "permitKey02,permitNo02";
						  }else if("chProduct0201".equals(getCode()[y])){
							  manyNameMap.put(getCode()[y], "DetailSheet");
							  fieldNameMap.put(getCode()[y], "chProduct02");
							  manyTitleMap.put("chProduct02", "商品名稱中文");
							  manyField = "chProduct02";
						  }else{
							  manyNameMap.put(getCode()[y], "DetailSheet");
							  fieldNameMap.put(getCode()[y], "applicationName02");
							  manyTitleMap.put("applicationName02", "許可證持有商");
							  manyField = "applicationName02";
						  }
						  if(null!=manyMap.get("DetailSheet"))      					
	       				  {    
	        				  manyMap.put("DetailSheet",manyMap.get("DetailSheet")+","+manyField); 
	       				  }       					   
	        			  else       					   
	        			  {   
	        				  manyMap.put("DetailSheet",manyField);       					   
	        			  }
					  }
				  } 
	    	   }
        	   
        	   //將最大欄位字數乘2
        	   for(int i=0; i<tmpMaxLength.length; i++)
       		   {       			 
        		   sheet1.setColumnView(i, tmpMaxLength[i]*2);
      		   }
        	   if (getManyCode()!=null && getManyCode().length>0 || manyMap.size() > 0) 
       		   {      		
        		   
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
           		   }
        		   //建立sheet2頁籤        				
       			   WritableSheet sheet2=workbook.createSheet("明細清單", 1);               	    
       			   createDetailSheet(sheet2,manyMap.get("DetailSheet"),manyTitleMap);      			   
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
			//if (outputFile!=null && outputFile.exists()) outputFile.delete();
		}
		
	}
	
	//醫材資料,廠商回覆,廠商確認(DetelSheet)
	public void createDetailSheet(WritableSheet sheet2,String title,java.util.Map<String,String> titleMap) throws Exception
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
		

	 	String hql2="  select applNo01, ";
               hql2+=  field;
               hql2+=" from dynamicView08 ";
               hql2+=" where 1=1 ";
               hql2+=condition();     
               hql2+=" order by applNo01 ";
 	   System.out.println("Hql2=="+hql2);
 	          
 	  java.util.List list0003 = ServiceGetter.getInstance().getTcbwService().getNativeSqlQuery().load(hql2);
 	   
 	  if (list0003!=null && list0003.size()>0) 			
	  {
 		  int e=1; 		 
 		  java.util.Map<String, String> pkidMap = TCBWCommon.getCommonCodeMap("MEDPKID");//單位 		
 		  java.util.Map<String, String> isYnMap = new java.util.HashMap<String, String>();		
 		  isYnMap.put("Y","是");		
 		  isYnMap.put("N","否");
 		  for (int j=0; j<list0003.size(); j++) 
    	  {
			   Object x[] = (Object[])list0003.get(j);
			   sheet2.addCell(new Label(0,e,Common.get(x[0]),detailCenter));
			   
			   if(Common.get(x[0]).length()*2 >= tmpMaxLength[0])
			   {
			      tmpMaxLength[0] = Common.get(x[0]).length()*2; 
			   }
			   
			   for(int i=0;i<value.length;i++) 
			   {
				   if("permitKey02".equals(value[i]))					   
 				 	   sheet2.addCell(new Label(i+1,e,pkidMap.get(Common.get(x[i+1])),detailCenter));
				   else if ("iswarning02".equals(value[i]) || "iseffectinternal02".equals(value[i]))
					   sheet2.addCell(new Label(i+1,e,isYnMap.get(Common.get(x[i+1])),detailCenter));
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
          Variant0001Db obj = (Variant0001Db)View.getObject(" from Variant0001Db where name="+Common.sqlChar(variantName)+" and kind='med06' ");
		
		  if(obj == null) 
		    obj = new Variant0001Db();
	
		  obj.setKind("med06");
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
		DYNAMIC0801R obj = this;

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
		
        String hql=" from Export0001Db where code like 'med06%' ";
		
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
						
						
						sb.append("<td width='33%'>");
						
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
	
					   sb.append("</td>");
							
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
	@Override
	public void doDelete() throws Exception 
	{
        Variant0001Db c=(Variant0001Db)View.getObject("from Variant0001Db where kind='med06' and name="+Common.sqlChar(getVariantName()));
		
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
	public String getId() {return checkGet(id);}
	public void setId(String id) {this.id = checkSet(id);}
	public String getVariantName() {return checkGet(variantName);}
	public void setVariantName(String variantName) {this.variantName = checkSet(variantName);}
	public String getVariantExplain() {return checkGet(variantExplain);}
	public void setVariantExplain(String variantExplain) {this.variantExplain = checkSet(variantExplain);}
	public String[] getCode() {return checkGet(code);}
	public void setCode(String[] code) {this.code = checkSet(code);}
	public String getCodeValue() {return checkGet(codeValue);}
	public void setCodeValue(String codeValue) {this.codeValue = checkSet(codeValue);}
	public String getHtmlValue() {return htmlValue;}
	public void setHtmlValue(String htmlValue) {this.htmlValue = htmlValue;}
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
	public String getQ_publDept() {
		return checkGet(q_publDept);
	}
	public void setQ_publDept(String q_publDept) {
		this.q_publDept = checkSet(q_publDept);
	}
	public String[] getQ_situation() {
		return checkGet(q_situation);
	}
	public void setQ_situation(String[] q_situation) {
		this.q_situation = checkSet(q_situation);
	}
	public String getQ_medMainCategory() {
		return checkGet(q_medMainCategory);
	}
	public void setQ_medMainCategory(String q_medMainCategory) {
		this.q_medMainCategory = checkSet(q_medMainCategory);
	}
	public String getQ_chProduct() {
		return checkGet(q_chProduct);
	}
	public void setQ_chProduct(String q_chProduct) {
		this.q_chProduct = checkSet(q_chProduct);
	}
	public String[] getQ_recycleclass() {
		return checkGet(q_recycleclass);
	}
	public void setQ_recycleclass(String[] q_recycleclass) {
		this.q_recycleclass = checkSet(q_recycleclass);
	}
	public String getQ_dataRevDateS() {
		return checkGet(q_dataRevDateS);
	}
	public void setQ_dataRevDateS(String q_dataRevDateS) {
		this.q_dataRevDateS = checkSet(q_dataRevDateS);
	}
	public String getQ_dataRevDateE() {
		return checkGet(q_dataRevDateE);
	}
	public void setQ_dataRevDateE(String q_dataRevDateE) {
		this.q_dataRevDateE = checkSet(q_dataRevDateE);
	}
	public String getQ_publDateS() {
		return checkGet(q_publDateS);
	}
	public void setQ_publDateS(String q_publDateS) {
		this.q_publDateS = checkSet(q_publDateS);
	}
	public String getQ_publDateE() {
		return checkGet(q_publDateE);
	}
	public void setQ_publDateE(String q_publDateE) {
		this.q_publDateE = checkSet(q_publDateE);
	}
	public String getQ_manufactorName() {
		return checkGet(q_manufactorName);
	}
	public void setQ_manufactorName(String q_manufactorName) {
		this.q_manufactorName = checkSet(q_manufactorName);
	}
	public String getQ_contextsummary() {
		return checkGet(q_contextsummary);
	}
	public void setQ_contextsummary(String q_contextsummary) {
		this.q_contextsummary = checkSet(q_contextsummary);
	}
	public String getQ_productlotNo() {
		return checkGet(q_productlotNo);
	}
	public void setQ_productlotNo(String q_productlotNo) {
		this.q_productlotNo = checkSet(q_productlotNo);
	}
	public String getQ_effectarea() {
		return checkGet(q_effectarea);
	}
	public void setQ_effectarea(String q_effectarea) {
		this.q_effectarea = checkSet(q_effectarea);
	}
	public String getQ_permitKey02() {
		return checkGet(q_permitKey02);
	}
	public void setQ_permitKey02(String q_permitKey02) {
		this.q_permitKey02 = checkSet(q_permitKey02);
	}
	public String getQ_permitNo02() {
		return checkGet(q_permitNo02);
	}
	public void setQ_permitNo02(String q_permitNo02) {
		this.q_permitNo02 = checkSet(q_permitNo02);
	}
	public String getQ_chProduct02() {
		return checkGet(q_chProduct02);
	}
	public void setQ_chProduct02(String q_chProduct02) {
		this.q_chProduct02 = checkSet(q_chProduct02);
	}
	public String getQ_enProduct02() {
		return checkGet(q_enProduct02);
	}
	public void setQ_enProduct02(String q_enProduct02) {
		this.q_enProduct02 = checkSet(q_enProduct02);
	}
	public String getQ_applicationName02() {
		return checkGet(q_applicationName02);
	}
	public void setQ_applicationName02(String q_applicationName02) {
		this.q_applicationName02 = checkSet(q_applicationName02);
	}
	public String getQ_manufactorName02() {
		return checkGet(q_manufactorName02);
	}
	public void setQ_manufactorName02(String q_manufactorName02) {
		this.q_manufactorName02 = checkSet(q_manufactorName02);
	}
	public String getQ_manufactorCountry02() {
		return checkGet(q_manufactorCountry02);
	}
	public void setQ_manufactorCountry02(String q_manufactorCountry02) {
		this.q_manufactorCountry02 = checkSet(q_manufactorCountry02);
	}
	public String getQ_replydate02S() {
		return checkGet(q_replydate02S);
	}
	public void setQ_replydate02S(String q_replydate02S) {
		this.q_replydate02S = checkSet(q_replydate02S);
	}
	public String getQ_replydate02E() {
		return checkGet(q_replydate02E);
	}
	public void setQ_replydate02E(String q_replydate02E) {
		this.q_replydate02E = checkSet(q_replydate02E);
	}
	public String getQ_iswarning02() {
		return checkGet(q_iswarning02);
	}
	public void setQ_iswarning02(String q_iswarning02) {
		this.q_iswarning02 = checkSet(q_iswarning02);
	}
	public String getQ_iseffectinternal02() {
		return checkGet(q_iseffectinternal02);
	}
	public void setQ_iseffectinternal02(String q_iseffectinternal02) {
		this.q_iseffectinternal02 = checkSet(q_iseffectinternal02);
	}
	public String getQ_medModel02() {
		return checkGet(q_medModel02);
	}
	public void setQ_medModel02(String q_medModel02) {
		this.q_medModel02 = checkSet(q_medModel02);
	}
	public String getQ_lotNo02() {
		return checkGet(q_lotNo02);
	}
	public void setQ_lotNo02(String q_lotNo02) {
		this.q_lotNo02 = checkSet(q_lotNo02);
	}
	public String getQ_isrecycle() {
		return checkGet(q_isrecycle);
	}
	public void setQ_isrecycle(String q_isrecycle) {
		this.q_isrecycle = checkSet(q_isrecycle);
	}
	public String getQ_istranslate() {
		return checkGet(q_istranslate);
	}
	public void setQ_istranslate(String q_istranslate) {
		this.q_istranslate = checkSet(q_istranslate);
	}
	public String getQ_publdate05S() {
		return checkGet(q_publdate05S);
	}
	public void setQ_publdate05S(String q_publdate05S) {
		this.q_publdate05S = checkSet(q_publdate05S);
	}
	public String getQ_publdate05E() {
		return checkGet(q_publdate05E);
	}
	public void setQ_publdate05E(String q_publdate05E) {
		this.q_publdate05E = checkSet(q_publdate05E);
	}
	public String getQ_ispublnews05() {
		return checkGet(q_ispublnews05);
	}
	public void setQ_ispublnews05(String q_ispublnews05) {
		this.q_ispublnews05 = checkSet(q_ispublnews05);
	}
	public String getQ_ispublconsumer05() {
		return checkGet(q_ispublconsumer05);
	}
	public void setQ_ispublconsumer05(String q_ispublconsumer05) {
		this.q_ispublconsumer05 = checkSet(q_ispublconsumer05);
	}
	public String getQ_isfdaweb05() {
		return checkGet(q_isfdaweb05);
	}
	public void setQ_isfdaweb05(String q_isfdaweb05) {
		this.q_isfdaweb05 = checkSet(q_isfdaweb05);
	}
	public String[] getQ_lamp05() {
		return checkGet(q_lamp05);
	}
	public void setQ_lamp05(String[] q_lamp05) {
		this.q_lamp05 = checkSet(q_lamp05);
	}
	public String getQ_fdareceiveNo() {
		return checkGet(q_fdareceiveNo);
	}
	public void setQ_fdareceiveNo(String q_fdareceiveNo) {
		this.q_fdareceiveNo = checkSet(q_fdareceiveNo);
	}
	public String getQ_posesummary() {
		return checkGet(q_posesummary);
	}
	public void setQ_posesummary(String q_posesummary) {
		this.q_posesummary = checkSet(q_posesummary);
	}
	public String getQ_completereceiveno() {
		return checkGet(q_completereceiveno);
	}
	public void setQ_completereceiveno(String q_completereceiveno) {
		this.q_completereceiveno = checkSet(q_completereceiveno);
	}	
}
