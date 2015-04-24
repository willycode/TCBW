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

public class DYNAMIC0401R extends SuperBean
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
	private String q_chProduct; //中文品名
	private String q_enProduct; //英文品名
	private String q_permitKey;//許可證字
	private String q_permitNo;//許可證號
	private String q_ingredient; //主成分
	private String q_medModel; //劑型
	private String q_appName; //許可證持有商
	private String q_manufactorName; //製造廠
	private String q_manufactorCountry; //製造廠國別
	private String q_prerecycledateS; //預計完成回收日期起
	private String q_prerecycledateE; //預計完成回收日期迄
	private String q_isabroad; //是否輸出國外
	private String q_postDateS; //發文日期起
	private String q_postDateE; //發文日期迄
	private String q_postNo; //回收文號
	private String[] q_recycleclass; //回收分級
	private String[] q_msgsource; //訊息來源
	private String q_cureapplno; //通報編號
	private String q_quaapplno; //警訊編號
	private String q_recycledeadlineS; //回收期限起
	private String q_recycledeadlineE; //回收期限迄
	private String q_healthbureau; //主管衛生局
	private String q_appRecDateS; //完成回收日期起
	private String q_appRecDateE; //完成回收日期迄
	private String q_apprecyclestorage; //廠商回收-回收品及庫存品處置方式
	private String q_checkcyclestorage; //回收確認-回收品及庫存品處置方式
	private String q_checkhealthbureau; //查核衛生局
	private String q_ischeckmatchnum; //是否與回收報告書所載數量相符
	private String q_anamedicineType; //學名藥/原廠藥
	private String q_anaproduceType; //國產/輸入
	private String q_analotType; //批號範圍
	private String q_anarecyclereason; //回收原因
	private String[] q_anasurvey; //調查結果
	private String[] q_anaprecaution; //預防措施
	
	
	//查詢條件
	public String condition()
	{
		String condition="";
		
		//案件編號
		if(!"".equals(getQ_applNoS()))
			condition+=" and applNo >="+Common.sqlChar(getQ_applNoS());
		if(!"".equals(getQ_applNoE()))
			condition+=" and applNo <="+Common.sqlChar(getQ_applNoE());
		
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
			condition+=" and status in ("+statusStr+")";
		
		//中文品名
		if(!"".equals(getQ_chProduct()))
			condition+=" and chProduct like "+TCBWCommon.likeSqlChar(getQ_chProduct());		
		//英文品名
		if(!"".equals(getQ_enProduct()))
			condition+=" and enProduct like "+TCBWCommon.likeSqlChar(getQ_enProduct());		
		//許可證字
		if(!"".equals(getQ_permitKey()))
			condition+=" and permitKey ="+Common.sqlChar(getQ_permitKey());
		//許可證號
		if(!"".equals(getQ_permitNo()))
			condition+=" and permitNo ="+Common.sqlChar(getQ_permitNo());
		//主成分
		if(!"".equals(getQ_ingredient()))
			condition+=" and ingredient like "+TCBWCommon.likeSqlChar(getQ_ingredient());	
		//劑型
		if(!"".equals(getQ_medModel()))
			condition+=" and medModel ="+Common.sqlChar(getQ_medModel());
		//許可證持有商
		if(!"".equals(getQ_appName()))
			condition+=" and appName like"+TCBWCommon.likeSqlChar(getQ_appName());
		//製造廠
		if(!"".equals(getQ_manufactorName()))
			condition+=" and manufactorName like "+TCBWCommon.likeSqlChar(getQ_manufactorName());
		//製造廠國別
		if(!"".equals(getQ_manufactorCountry()))
			condition+=" and manufactorCountry like "+TCBWCommon.likeSqlChar(getQ_manufactorCountry());
		//預計完成回收日期
		if(!"".equals(getQ_prerecycledateS()))
			condition+=" and prerecycledate >="+Common.sqlChar(getQ_prerecycledateS());
		if(!"".equals(getQ_prerecycledateE()))
			condition+=" and prerecycledate <="+Common.sqlChar(getQ_prerecycledateE());
		//是否輸出國外
		if(!"".equals(getQ_isabroad()))
			condition+=" and isabroad ="+Common.sqlChar(getQ_isabroad());
		//發文日期
		if(!"".equals(getQ_postDateS()))
			condition+=" and postDate >="+Common.sqlChar(getQ_postDateS());
		if(!"".equals(getQ_postDateE()))
			condition+=" and postDate <="+Common.sqlChar(getQ_postDateE());
		//回收文號
		if(!"".equals(getQ_postNo()))
			condition+=" and postNo ="+Common.sqlChar(getQ_postNo());
		//回收分級
		String classStr="";
		if(null!=getQ_recycleclass() && getQ_recycleclass().length>0)
		{
			for(int i=0;i<getQ_recycleclass().length;i++)
			{
				if(classStr.length()>0)  classStr+=",";
				classStr+="'"+getQ_recycleclass()[i]+"'";
			}	
		}		
		if(classStr.length()>0)
			condition+=" and recycleclass in ("+classStr+")";
		//訊息來源
		String msgsourceStr="";
		if(null!=getQ_msgsource() && getQ_msgsource().length>0)
		{
			for(int i=0;i<getQ_msgsource().length;i++)
			{
				if(msgsourceStr.length()>0)  msgsourceStr+=",";
				msgsourceStr+="'"+getQ_msgsource()[i]+"'";
			}	
		}		
		if(msgsourceStr.length()>0)
			condition+=" and msgsource in ("+msgsourceStr+")";
		//通報編號
		if(!"".equals(getQ_cureapplno()))
			condition+=" and cureapplno ="+Common.sqlChar(getQ_cureapplno());
		//警訊編號
		if(!"".equals(getQ_quaapplno()))
			condition+=" and quaapplno ="+Common.sqlChar(getQ_quaapplno());
		//回收期限
		if(!"".equals(getQ_recycledeadlineS()))
			condition+=" and recycledeadline >="+Common.sqlChar(getQ_recycledeadlineS());
		if(!"".equals(getQ_recycledeadlineE()))
			condition+=" and recycledeadline <="+Common.sqlChar(getQ_recycledeadlineE());
		//主管衛生局
		if(!"".equals(getQ_healthbureau()))
			condition+=" and ( healthbureau1 ="+Common.sqlChar(getQ_healthbureau())+
			           " or healthbureau2="+Common.sqlChar(getQ_healthbureau())+" )";
		//完成回收日期
		if(!"".equals(getQ_appRecDateS()))
			condition+=" and appRecDate >="+Common.sqlChar(getQ_appRecDateS());
		if(!"".equals(getQ_appRecDateE()))
			condition+=" and appRecDate <="+Common.sqlChar(getQ_appRecDateE());
		//廠商回收-回收品及庫存品處置方式
		if(!"".equals(getQ_apprecyclestorage()))
			condition+=" and apprecyclestorage ="+Common.sqlChar(getQ_apprecyclestorage());
		//回收確認-回收品及庫存品處置方式
		if(!"".equals(getQ_checkcyclestorage()))
			condition+=" and checkcyclestorage ="+Common.sqlChar(getQ_checkcyclestorage());
		//查核衛生局
		if(!"".equals(getQ_checkhealthbureau()))
			condition+=" and checkhealthbureau ="+Common.sqlChar(getQ_checkhealthbureau());
		//是否與回收報告書所載數量相符
		if(!"".equals(getQ_ischeckmatchnum()))
			condition+=" and ischeckmatchnum ="+Common.sqlChar(getQ_ischeckmatchnum());
		//學名藥/原廠藥
		if(!"".equals(getQ_anamedicineType()))
			condition+=" and anamedicineType ="+Common.sqlChar(getQ_anamedicineType());
		//國產/輸入
		if(!"".equals(getQ_anaproduceType()))
			condition+=" and anaproduceType ="+Common.sqlChar(getQ_anaproduceType());
		//批號範圍
		if(!"".equals(getQ_analotType()))
			condition+=" and analotType ="+Common.sqlChar(getQ_analotType());
		//回收原因
		if(!"".equals(getQ_anarecyclereason()))
			condition+=" and anarecyclereason ="+Common.sqlChar(getQ_anarecyclereason());		
		//調查結果
		if(getQ_anasurvey()!=null && getQ_anasurvey().length>0){			
			boolean flag = true;
			for(String rid : getQ_anasurvey()){	
				if(flag){
					condition += " and ( anasurvey like "+ Common.sqlChar("%" + rid + "%");					
					flag = false;
				}else{
					condition += " or anasurvey like " + Common.sqlChar("%" + rid + "%");
				}
			}
			condition += " )";
		}
		//預防措施
		if(getQ_anaprecaution()!=null && getQ_anaprecaution().length>0){			
			boolean flag = true;
			for(String rid : getQ_anaprecaution()){	
				if(flag){
					condition += " and ( anaprecaution like "+ Common.sqlChar("%" + rid + "%");					
					flag = false;
				}else{
					condition += " or anaprecaution like " + Common.sqlChar("%" + rid + "%");
				}
			}
			condition += " )";
		}
		
		return condition;
		
	}
	

		
	//匯出Xls
	public void exportXls(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
        //表頭中文
		java.util.Map<String,String>  titleMap=TCBWCommon.getMap("select field,fieldName from Export0002Db where export0001Db.code like 'drg04%'");
		//多筆表頭中文
		java.util.Map<String,String>  manyTitleMap=TCBWCommon.getMap("select manyField,fieldName from Export0002Db where export0001Db.code like 'drg04%' and isMany='Y' ");
		
		//全部代碼中文
		java.util.Map<String,String>  codeNameMap=TCBWCommon.getMap("select commonCodeKind.codeKindId+codeId,codeName from CommonCode");
		codeNameMap.put("YNY","是");codeNameMap.put("YNN","否");
		//縣市區域代碼
		java.util.List codelist1 = ServiceGetter.getInstance().getTcbwService().load("select zipcode,zipname from Con1002Db ");
		if (codelist1!=null && codelist1.size()>0) 			
	    {    	   
			for (int j=0; j<codelist1.size(); j++)     	   
			{
			  Object x[] = (Object[])codelist1.get(j);
			  codeNameMap.put("Con1002Db"+x[0],Common.get(x[1]));    	   
			}
	    }
		codelist1.clear();
		//衛生局代碼
		java.util.List codelist2 = ServiceGetter.getInstance().getTcbwService().load("select id,unionName from Con1003Db ");
		if (codelist2!=null && codelist2.size()>0) 			
	    {    	   
			for (int j=0; j<codelist2.size(); j++)     	   
			{
			  Object x[] = (Object[])codelist2.get(j);
			  codeNameMap.put("Con1003Db"+x[0],Common.get(x[1]));    	   
			}
	    }
		codelist2.clear();
		//判斷是否有map
		java.util.Map<String,String>  isMap=TCBWCommon.getMap("select field,codeName from Export0002Db where export0001Db.code like 'drg04%' and codeName is not null and codeName <> 'NULL' ");
		
		//判斷是否為多筆map
		java.util.Map<String,String>  manyNameMap=TCBWCommon.getMap("select field,manyName from Export0002Db where export0001Db.code like 'drg04%' and isMany='Y' ");
		

		//
		java.util.Map<String,String>  fieldNameMap=TCBWCommon.getMap("select field,manyField from Export0002Db where export0001Db.code like 'drg04%' and isMany='Y' ");
		
		
		
		//Excel檔案路徑
		File outputFile = new File(System.getProperty("java.io.tmpdir")+File.separator+"DYNAMIC0401R_"+Datetime.getHHMMSS()+ ".xls");
		//建立Excel檔案
		WritableWorkbook workbook = Workbook.createWorkbook(outputFile);
		
		//建立sheet1頁籤
		WritableSheet sheet1 = workbook.createSheet("重大品質事件廠商主動通報", 0);
		
		
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
		    String hql="  select ";
                   hql+=  field;
                   hql+=  ",'1'";//防止只輸入一個欄位出錯
                   hql+=" from dynamicView04 ";
                   hql+=" where 1=1 ";
                   hql+=condition();    
                  
            System.out.println("hql=="+hql);
                   
            java.util.List list = ServiceGetter.getInstance().getTcbwService().getNativeSqlQuery().load(hql.toString());
        
            if (list!=null && list.size()>0) 			
		    {
               String productProblemKind="N";
               
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
       			  
       			   if(null!=manyMap.get("Drg8005DbSheet"))
       			   {       				
       				   //建立sheet2頁籤        				
       				   WritableSheet sheet2=workbook.createSheet("實際回收清單", 1);               	    
       				   createDrg8005DbSheet(sheet2,manyMap.get("Drg8005DbSheet"),manyTitleMap);
       			   }
       			  
       			   if(null!=manyMap.get("Drg8001DbSheet1"))
     			   {     				
       				   //建立sheet3頁籤     				
       				   WritableSheet sheet3=workbook.createSheet("廠商回收-調查結果", 2);             	    
       				   createDrg8001DbSheet1(sheet3,manyMap.get("Drg8001DbSheet1"),manyTitleMap);
     			   }
       			  
       			   if(null!=manyMap.get("Drg8001DbSheet2"))
   			       {   				    
       				   //建立sheet4頁籤   				    
       				   WritableSheet sheet4=workbook.createSheet("廠商回收-預防措施", 3);           	        
       				   createDrg8001DbSheet2(sheet4,manyMap.get("Drg8001DbSheet2"),manyTitleMap);
   			       }
       			
       			   if(null!=manyMap.get("Drg8004DbSheet1"))
 			       { 				    
       				   //建立sheet5頁籤 				    
       				   WritableSheet sheet5=workbook.createSheet("案件評估-擬辦事項", 4);       	        
       				   createDrg8004DbSheet1(sheet5,manyMap.get("Drg8004DbSheet1"),manyTitleMap);
 			       }
       			
       			   if(null!=manyMap.get("Drg8004DbSheet2"))
 			       { 				    
       				   //建立sheet6頁籤 				    
       				   WritableSheet sheet6=workbook.createSheet("案件分析-調查結果", 5);         	        
       				   createDrg8004DbSheet2(sheet6,manyMap.get("Drg8004DbSheet2"),manyTitleMap);
 			       }
       			
       			   if(null!=manyMap.get("Drg8004DbSheet3"))
			       {				    
       				   //建立sheet7頁籤				    
       				   WritableSheet sheet7=workbook.createSheet("案件分析-預防措施", 6);      	        
       				   createDrg8004DbSheet3(sheet7,manyMap.get("Drg8004DbSheet3"),manyTitleMap);
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
	
	//實際回收清單
	public void createDrg8005DbSheet(WritableSheet sheet2,String title,java.util.Map<String,String> titleMap) throws Exception
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
	 		field+="a."+value[i];	 	  
	 		sheet2.addCell(new Label(i+1,0,titleMap.get(value[i]),detailCenter));
	 	  
	 		//放入表頭字數		  
	 		tmpMaxLength[i+1] = Common.get(titleMap.get(value[i])).length()*2;
	 	}
	 	
	 	String hql0001=" (select id from dynamicView04 where 1=1 "+condition()+")";
 	   
	 	String drg8005Hql="  select b.applNo,"+field;
 	          drg8005Hql+=" from Drg8005_DB a,DRG8001_Db b";
 	          drg8005Hql+=" where a.drg8001_id=b.id ";
 	          drg8005Hql+=" and a.drg8001_id  in "+hql0001;
 	          drg8005Hql+=" order by b.applNo ";         
 	   
 	    System.out.println("drg8005Hql=="+drg8005Hql);	          
 	  
 	    java.util.List listSheet2 = ServiceGetter.getInstance().getTcbwService().getNativeSqlQuery().load(drg8005Hql); 	   
 	  
 	    if (listSheet2!=null && listSheet2.size()>0)  
 	    { 		  
 	    	int e=1;
 	    	java.util.Map<String, String> unitMap = TCBWCommon.getCommonCodeMap("DRGRECUNIT");//單位
 	    	for (int j=0; j<listSheet2.size(); j++)  	  
 	    	{			   
 	    		Object x[] = (Object[])listSheet2.get(j); 	    		
 	    		sheet2.addCell(new Label(0,e,Common.get(x[0]),detailCenter));			   
			   
 	    		if(Common.get(x[0]).length()*2 >= tmpMaxLength[0])			   
 	    		{			      
 	    			tmpMaxLength[0] = Common.get(x[0]).length()*2;			   
 	    		} 	    		
		   
 	    		for(int i=0;i<value.length;i++)			   
 	    		{
 	    			if("appreservesUnit".equals(value[i]) || "appsellunit".equals(value[i]) || "appprerecycleunit".equals(value[i]))
 				 	   sheet2.addCell(new Label(i+1,e,unitMap.get(Common.get(x[i+1])),detailCenter));
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
	
	//廠商回收-調查結果
	public void createDrg8001DbSheet1(WritableSheet sheet3,String title,java.util.Map<String,String> titleMap) throws Exception
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
	
	 	sheet3.addCell(new Label(0,0,"案件編號",detailCenter));
	 	//放入表頭字數
		tmpMaxLength[0] = Common.get("案件編號").length()*2;
	 	
	 	for(int i=0;i<value.length;i++) 
	 	{	 	  
	 		if(field.length()>0)field+=",";	 	  
	 		field+="a."+value[i];	 	  
	 		sheet3.addCell(new Label(i+1,0,titleMap.get(value[i]),detailCenter));
	 	  
	 		//放入表頭字數		  
	 		tmpMaxLength[i+1] = Common.get(titleMap.get(value[i])).length()*2;
	 	}
	 	
	 	String hql0001=" (select id from dynamicView04 where 1=1 "+condition()+")";
 	   
	 	String drg8001Hql="  select a.applNo, a.appsurvey";
	           drg8001Hql+=" from DRG8001_Db a";
	           drg8001Hql+=" where 1=1 ";
	           drg8001Hql+=" and a.id in "+hql0001;
	           drg8001Hql+=" order by a.applNo ";          
 	   
 	    System.out.println("drg8001Hql=="+drg8001Hql);	          
 	  
 	    java.util.List listSheet3 = ServiceGetter.getInstance().getTcbwService().getNativeSqlQuery().load(drg8001Hql); 	   
 	  
 	    if (listSheet3!=null && listSheet3.size()>0)  
 	    { 		  
 	    	int e=1;
 	    	String[] appsurvey =null;
 	    	java.util.Map<String, String> surMap = TCBWCommon.getCommonCodeMap("DRG0109");
 	    	for (int j=0; j<listSheet3.size(); j++)  	  
 	    	{			   
 	    		Object x[] = (Object[])listSheet3.get(j);
 	    		String appsurveyName ="";
 	    		appsurvey = Common.get(x[1]).split(",");
 	    		for(int k=0;k< appsurvey.length;k++)
 	    		{
 	    			sheet3.addCell(new Label(0,e,Common.get(x[0]),detailCenter));
 	    			if(Common.get(x[0]).length()*2 >= tmpMaxLength[0])			   
 	 	    		{			      
 	 	    			tmpMaxLength[0] = Common.get(x[0]).length()*2;			   
 	 	    		}
 	    			
 	    			//轉換中文名稱
 	    			appsurveyName = !"".equals(Common.get(appsurvey[k]))?surMap.get(Common.get(appsurvey[k])):"";
					
 	 	    		sheet3.addCell(new Label(1,e,Common.get(appsurveyName),detailCenter));			 	 
 	 	    		if(Common.get(appsurvey[k]).length()*2 >= tmpMaxLength[1])				 
 	 	    		{				     
 	 	    			tmpMaxLength[1] = Common.get(appsurvey[k]).length()*2;				 
 	 	    		} 	 	    		
 	    			e++; 	    			
 	    		}  	  
 	    	} 		  
 	    	//將最大欄位字數乘2   	      
 	    	for(int i=0; i<tmpMaxLength.length; i++)  		  
 	    	{   	    	 
 	    		sheet3.setColumnView(i, tmpMaxLength[i]*2); 		  
 	    	}	   
 	    }	
	}
	
	//廠商回收-預防措施
	public void createDrg8001DbSheet2(WritableSheet sheet4,String title,java.util.Map<String,String> titleMap) throws Exception
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
	
	 	sheet4.addCell(new Label(0,0,"案件編號",detailCenter));
	 	//放入表頭字數
		tmpMaxLength[0] = Common.get("案件編號").length()*2;
	 	
	 	for(int i=0;i<value.length;i++) 
	 	{	 	  
	 		if(field.length()>0)field+=",";	 	  
	 		field+="a."+value[i];	 	  
	 		sheet4.addCell(new Label(i+1,0,titleMap.get(value[i]),detailCenter));
	 	  
	 		//放入表頭字數		  
	 		tmpMaxLength[i+1] = Common.get(titleMap.get(value[i])).length()*2;
	 	}
	 	
	 	String hql0001=" (select id from dynamicView04 where 1=1 "+condition()+")";
 	   
	 	String drg8001Hql2="  select a.applNo, a.appprecaution";
	 	       drg8001Hql2+=" from DRG8001_Db a";
	 	       drg8001Hql2+=" where 1=1 ";
	 	       drg8001Hql2+=" and a.id in "+hql0001;
	 	       drg8001Hql2+=" order by a.applNo ";         
 	   
 	    System.out.println("drg8001Hql2=="+drg8001Hql2);	          
 	  
 	    java.util.List listSheet4 = ServiceGetter.getInstance().getTcbwService().getNativeSqlQuery().load(drg8001Hql2); 	   
 	  
 	    if (listSheet4!=null && listSheet4.size()>0)  
 	    { 		  
 	    	int e=1;
 	    	String[] appprecaution =null;
 	    	java.util.Map<String, String> surMap = TCBWCommon.getCommonCodeMap("DRG0110");
 	    	for (int j=0; j<listSheet4.size(); j++)  	  
 	    	{			   
 	    		Object x[] = (Object[])listSheet4.get(j);
 	    		String appprecautionName ="";
 	    		appprecaution = Common.get(x[1]).split(",");
 	    		for(int k=0;k< appprecaution.length;k++)
 	    		{
 	    			sheet4.addCell(new Label(0,e,Common.get(x[0]),detailCenter));
 	    			if(Common.get(x[0]).length()*2 >= tmpMaxLength[0])			   
 	 	    		{			      
 	 	    			tmpMaxLength[0] = Common.get(x[0]).length()*2;			   
 	 	    		}
 	    			
 	    			//轉換中文名稱
 	    			appprecautionName = !"".equals(Common.get(appprecaution[k]))?surMap.get(Common.get(appprecaution[k])):"";
					
 	 	    		sheet4.addCell(new Label(1,e,Common.get(appprecautionName),detailCenter));			 	 
 	 	    		if(Common.get(appprecaution[k]).length()*2 >= tmpMaxLength[1])				 
 	 	    		{				     
 	 	    			tmpMaxLength[1] = Common.get(appprecaution[k]).length()*2;				 
 	 	    		} 	 	    		
 	    			e++; 	    			
 	    		}   	  
 	    	} 		  
 	    	//將最大欄位字數乘2   	      
 	    	for(int i=0; i<tmpMaxLength.length; i++)  		  
 	    	{   	    	 
 	    		sheet4.setColumnView(i, tmpMaxLength[i]*2); 		  
 	    	}	   
 	    }	
	}

	//案件評估-擬辦事項
	public void createDrg8004DbSheet1(WritableSheet sheet5,String title,java.util.Map<String,String> titleMap) throws Exception
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
	
		sheet5.addCell(new Label(0,0,"案件編號",detailCenter));
	 	//放入表頭字數
		tmpMaxLength[0] = Common.get("案件編號").length()*2;
	 	
	 	for(int i=0;i<value.length;i++) 
	 	{	 	  
	 		if(field.length()>0)field+=",";	 	  
	 		field+="a."+value[i];	 	  
	 		sheet5.addCell(new Label(i+1,0,titleMap.get(value[i]),detailCenter));
	 	  
	 		//放入表頭字數		  
	 		tmpMaxLength[i+1] = Common.get(titleMap.get(value[i])).length()*2;
	 	}
	 	
	 	String hql0001=" (select id from dynamicView04 where 1=1 "+condition()+")";
 	   
	 	String drg8004Hql="  select b.applNo, a.assessdealWith ";
	 	      drg8004Hql+=" from Drg8004_DB a,DRG8001_Db b";
	 	      drg8004Hql+=" where a.drg8001_id=b.id ";
	 	      drg8004Hql+=" and a.drg8001_id  in "+hql0001;
	 	      drg8004Hql+=" order by b.applNo ";         
 	   
 	    System.out.println("drg8004Hql=="+drg8004Hql);	          
 	  
 	    java.util.List listSheet5 = ServiceGetter.getInstance().getTcbwService().getNativeSqlQuery().load(drg8004Hql); 	   
 	  
 	    if (listSheet5!=null && listSheet5.size()>0)  
 	    { 		  
 	    	int e=1; 		  
 	    	String[] assessdealWith =null;
 	    	java.util.Map<String, String> surMap = TCBWCommon.getCommonCodeMap("DRG0111");
 	    	for (int j=0; j<listSheet5.size(); j++)  	  
 	    	{			   
 	    		Object x[] = (Object[])listSheet5.get(j);
 	    		String assessdealWithName ="";
 	    		assessdealWith = Common.get(x[1]).split(",");
 	    		for(int k=0;k< assessdealWith.length;k++)
 	    		{
 	    			sheet5.addCell(new Label(0,e,Common.get(x[0]),detailCenter));
 	    			if(Common.get(x[0]).length()*2 >= tmpMaxLength[0])			   
 	 	    		{			      
 	 	    			tmpMaxLength[0] = Common.get(x[0]).length()*2;			   
 	 	    		}
 	    			
 	    			//轉換中文名稱
 	    			assessdealWithName = !"".equals(Common.get(assessdealWith[k]))?surMap.get(Common.get(assessdealWith[k])):"";
					
 	 	    		sheet5.addCell(new Label(1,e,Common.get(assessdealWithName),detailCenter));			 	 
 	 	    		if(Common.get(assessdealWith[k]).length()*2 >= tmpMaxLength[1])				 
 	 	    		{				     
 	 	    			tmpMaxLength[1] = Common.get(assessdealWith[k]).length()*2;				 
 	 	    		} 	 	    		
 	    			e++; 	    			
 	    		}   	  
 	    	} 		  
 	    	//將最大欄位字數乘2   	      
 	    	for(int i=0; i<tmpMaxLength.length; i++)  		  
 	    	{   	    	 
 	    		sheet5.setColumnView(i, tmpMaxLength[i]*2); 		  
 	    	}	   
 	    }	
	}
	
	//案件分析-調查結果
	public void createDrg8004DbSheet2(WritableSheet sheet6,String title,java.util.Map<String,String> titleMap) throws Exception
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
	
		sheet6.addCell(new Label(0,0,"案件編號",detailCenter));
	 	//放入表頭字數
		tmpMaxLength[0] = Common.get("案件編號").length()*2;
	 	
	 	for(int i=0;i<value.length;i++) 
	 	{	 	  
	 		if(field.length()>0)field+=",";	 	  
	 		field+="a."+value[i];	 	  
	 		sheet6.addCell(new Label(i+1,0,titleMap.get(value[i]),detailCenter));
	 	  
	 		//放入表頭字數		  
	 		tmpMaxLength[i+1] = Common.get(titleMap.get(value[i])).length()*2;
	 	}
	 	
	 	String hql0001=" (select id from dynamicView04 where 1=1 "+condition()+")";
 	   
	 	String drg8004Hql2="  select b.applNo,"+field;
	 	      drg8004Hql2+=" from Drg8004_DB a,DRG8001_Db b";
	 	      drg8004Hql2+=" where a.drg8001_id=b.id ";
	 	      drg8004Hql2+=" and a.drg8001_id  in "+hql0001;
	 	      drg8004Hql2+=" order by b.applNo ";         
 	   
 	    System.out.println("drg8004Hql2=="+drg8004Hql2);	          
 	  
 	    java.util.List listSheet6 = ServiceGetter.getInstance().getTcbwService().getNativeSqlQuery().load(drg8004Hql2); 	   
 	  
 	    if (listSheet6!=null && listSheet6.size()>0)  
 	    { 		  
 	    	int e=1; 		  
 	    	String[] anasurvey =null;
 	    	java.util.Map<String, String> surMap = TCBWCommon.getCommonCodeMap("DRG0109");
 	    	for (int j=0; j<listSheet6.size(); j++)  	  
 	    	{			   
 	    		Object x[] = (Object[])listSheet6.get(j);
 	    		String anasurveyName ="";
 	    		anasurvey = Common.get(x[1]).split(",");
 	    		for(int k=0;k< anasurvey.length;k++)
 	    		{
 	    			sheet6.addCell(new Label(0,e,Common.get(x[0]),detailCenter));
 	    			if(Common.get(x[0]).length()*2 >= tmpMaxLength[0])			   
 	 	    		{			      
 	 	    			tmpMaxLength[0] = Common.get(x[0]).length()*2;			   
 	 	    		}
 	    			
 	    			//轉換中文名稱
 	    			anasurveyName = !"".equals(Common.get(anasurvey[k]))?surMap.get(Common.get(anasurvey[k])):"";
					
 	 	    		sheet6.addCell(new Label(1,e,Common.get(anasurveyName),detailCenter));			 	 
 	 	    		if(Common.get(anasurvey[k]).length()*2 >= tmpMaxLength[1])				 
 	 	    		{				     
 	 	    			tmpMaxLength[1] = Common.get(anasurvey[k]).length()*2;				 
 	 	    		} 	 	    		
 	    			e++; 	    			
 	    		}  	  
 	    	} 		  
 	    	//將最大欄位字數乘2   	      
 	    	for(int i=0; i<tmpMaxLength.length; i++)  		  
 	    	{   	    	 
 	    		sheet6.setColumnView(i, tmpMaxLength[i]*2); 		  
 	    	}	   
 	    }	
	}
	
	//案件分析-調查結果
	public void createDrg8004DbSheet3(WritableSheet sheet7,String title,java.util.Map<String,String> titleMap) throws Exception
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
	
		sheet7.addCell(new Label(0,0,"案件編號",detailCenter));
	 	//放入表頭字數
		tmpMaxLength[0] = Common.get("案件編號").length()*2;
	 	
	 	for(int i=0;i<value.length;i++) 
	 	{	 	  
	 		if(field.length()>0)field+=",";	 	  
	 		field+="a."+value[i];	 	  
	 		sheet7.addCell(new Label(i+1,0,titleMap.get(value[i]),detailCenter));
	 	  
	 		//放入表頭字數		  
	 		tmpMaxLength[i+1] = Common.get(titleMap.get(value[i])).length()*2;
	 	}
	 	
	 	String hql0001=" (select id from dynamicView04 where 1=1 "+condition()+")";
 	   
	 	String drg8004Hql3="  select b.applNo, a.anaprecaution";
	 	      drg8004Hql3+=" from Drg8004_DB a,DRG8001_Db b";
	 	      drg8004Hql3+=" where a.drg8001_id=b.id ";
	 	      drg8004Hql3+=" and a.drg8001_id  in "+hql0001;
	 	      drg8004Hql3+=" order by b.applNo ";         
 	   
 	    System.out.println("drg8004Hql2=="+drg8004Hql3);	          
 	  
 	    java.util.List listSheet7 = ServiceGetter.getInstance().getTcbwService().getNativeSqlQuery().load(drg8004Hql3); 	   
 	  
 	    if (listSheet7!=null && listSheet7.size()>0)  
 	    { 		  
 	    	int e=1; 		  
 	    	String[] anaprecaution =null;
 	    	java.util.Map<String, String> surMap = TCBWCommon.getCommonCodeMap("DRG0110");
 	    	for (int j=0; j<listSheet7.size(); j++)  	  
 	    	{			   
 	    		Object x[] = (Object[])listSheet7.get(j);
 	    		String anaprecautionName ="";
 	    		anaprecaution = Common.get(x[1]).split(",");
 	    		for(int k=0;k< anaprecaution.length;k++)
 	    		{
 	    			sheet7.addCell(new Label(0,e,Common.get(x[0]),detailCenter));
 	    			if(Common.get(x[0]).length()*2 >= tmpMaxLength[0])			   
 	 	    		{			      
 	 	    			tmpMaxLength[0] = Common.get(x[0]).length()*2;			   
 	 	    		}
 	    			
 	    			//轉換中文名稱
 	    			anaprecautionName = !"".equals(Common.get(anaprecaution[k]))?surMap.get(Common.get(anaprecaution[k])):"";
					
 	 	    		sheet7.addCell(new Label(1,e,Common.get(anaprecautionName),detailCenter));			 	 
 	 	    		if(Common.get(anaprecaution[k]).length()*2 >= tmpMaxLength[1])				 
 	 	    		{				     
 	 	    			tmpMaxLength[1] = Common.get(anaprecaution[k]).length()*2;				 
 	 	    		} 	 	    		
 	    			e++; 	    			
 	    		}  	  
 	    	} 		  
 	    	//將最大欄位字數乘2   	      
 	    	for(int i=0; i<tmpMaxLength.length; i++)  		  
 	    	{   	    	 
 	    		sheet7.setColumnView(i, tmpMaxLength[i]*2); 		  
 	    	}	   
 	    }	
	}
	
	//存檔
	public void doSaveOrUpdate() throws Exception
	{
		try
		{
          Variant0001Db obj = (Variant0001Db)View.getObject(" from Variant0001Db where name="+Common.sqlChar(variantName)+" and kind='drg04' ");
		
		  if(obj == null) 
		    obj = new Variant0001Db();
	
		  obj.setKind("drg04");
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
		DYNAMIC0401R obj = this;

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
		
        String hql=" from Export0001Db where code like 'drg04%' ";
		
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
        Variant0001Db c=(Variant0001Db)View.getObject("from Variant0001Db where kind='drg04' and name="+Common.sqlChar(getVariantName()));
		
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
	public String getQ_chProduct() {
		return checkGet(q_chProduct);
	}
	public void setQ_chProduct(String q_chProduct) {
		this.q_chProduct = checkSet(q_chProduct);
	}
	public String getQ_enProduct() {
		return checkGet(q_enProduct);
	}
	public void setQ_enProduct(String q_enProduct) {
		this.q_enProduct = checkSet(q_enProduct);
	}
	public String getQ_permitKey() {
		return checkGet(q_permitKey);
	}
	public void setQ_permitKey(String q_permitKey) {
		this.q_permitKey = checkSet(q_permitKey);
	}
	public String getQ_permitNo() {
		return checkGet(q_permitNo);
	}
	public void setQ_permitNo(String q_permitNo) {
		this.q_permitNo = checkSet(q_permitNo);
	}
	public String getQ_ingredient() {
		return checkGet(q_ingredient);
	}
	public void setQ_ingredient(String q_ingredient) {
		this.q_ingredient = checkSet(q_ingredient);
	}
	public String getQ_medModel() {
		return checkGet(q_medModel);
	}
	public void setQ_medModel(String q_medModel) {
		this.q_medModel = checkSet(q_medModel);
	}
	public String getQ_appName() {
		return checkGet(q_appName);
	}
	public void setQ_appName(String q_appName) {
		this.q_appName = checkSet(q_appName);
	}
	public String getQ_manufactorName() {
		return checkGet(q_manufactorName);
	}
	public void setQ_manufactorName(String q_manufactorName) {
		this.q_manufactorName = checkSet(q_manufactorName);
	}
	public String getQ_manufactorCountry() {
		return checkGet(q_manufactorCountry);
	}
	public void setQ_manufactorCountry(String q_manufactorCountry) {
		this.q_manufactorCountry = checkSet(q_manufactorCountry);
	}
	public String getQ_prerecycledateS() {
		return checkGet(q_prerecycledateS);
	}
	public void setQ_prerecycledateS(String q_prerecycledateS) {
		this.q_prerecycledateS = checkSet(q_prerecycledateS);
	}
	public String getQ_prerecycledateE() {
		return checkGet(q_prerecycledateE);
	}
	public void setQ_prerecycledateE(String q_prerecycledateE) {
		this.q_prerecycledateE = checkSet(q_prerecycledateE);
	}
	public String getQ_isabroad() {
		return checkGet(q_isabroad);
	}
	public void setQ_isabroad(String q_isabroad) {
		this.q_isabroad = checkSet(q_isabroad);
	}
	public String getQ_postDateS() {
		return checkGet(q_postDateS);
	}
	public void setQ_postDateS(String q_postDateS) {
		this.q_postDateS = checkSet(q_postDateS);
	}
	public String getQ_postDateE() {
		return checkGet(q_postDateE);
	}
	public void setQ_postDateE(String q_postDateE) {
		this.q_postDateE = checkSet(q_postDateE);
	}
	public String getQ_postNo() {
		return checkGet(q_postNo);
	}
	public void setQ_postNo(String q_postNo) {
		this.q_postNo = checkSet(q_postNo);
	}
	public String[] getQ_recycleclass() {
		return q_recycleclass;
	}
	public void setQ_recycleclass(String[] q_recycleclass) {
		this.q_recycleclass = q_recycleclass;
	}
	public String[] getQ_msgsource() {
		return q_msgsource;
	}
	public void setQ_msgsource(String[] q_msgsource) {
		this.q_msgsource = q_msgsource;
	}
	public String getQ_cureapplno() {
		return checkGet(q_cureapplno);
	}
	public void setQ_cureapplno(String q_cureapplno) {
		this.q_cureapplno = checkSet(q_cureapplno);
	}
	public String getQ_quaapplno() {
		return checkGet(q_quaapplno);
	}
	public void setQ_quaapplno(String q_quaapplno) {
		this.q_quaapplno = checkSet(q_quaapplno);
	}
	public String getQ_recycledeadlineS() {
		return checkGet(q_recycledeadlineS);
	}
	public void setQ_recycledeadlineS(String q_recycledeadlineS) {
		this.q_recycledeadlineS = checkSet(q_recycledeadlineS);
	}
	public String getQ_recycledeadlineE() {
		return checkGet(q_recycledeadlineE);
	}
	public void setQ_recycledeadlineE(String q_recycledeadlineE) {
		this.q_recycledeadlineE = checkSet(q_recycledeadlineE);
	}
	public String getQ_healthbureau() {
		return checkGet(q_healthbureau);
	}
	public void setQ_healthbureau(String q_healthbureau) {
		this.q_healthbureau = checkSet(q_healthbureau);
	}
	public String getQ_appRecDateS() {
		return checkGet(q_appRecDateS);
	}
	public void setQ_appRecDateS(String q_appRecDateS) {
		this.q_appRecDateS = checkSet(q_appRecDateS);
	}
	public String getQ_appRecDateE() {
		return checkGet(q_appRecDateE);
	}
	public void setQ_appRecDateE(String q_appRecDateE) {
		this.q_appRecDateE = checkSet(q_appRecDateE);
	}
	public String getQ_apprecyclestorage() {
		return checkGet(q_apprecyclestorage);
	}
	public void setQ_apprecyclestorage(String q_apprecyclestorage) {
		this.q_apprecyclestorage = checkSet(q_apprecyclestorage);
	}
	public String getQ_checkcyclestorage() {
		return checkGet(q_checkcyclestorage);
	}
	public void setQ_checkcyclestorage(String q_checkcyclestorage) {
		this.q_checkcyclestorage = checkSet(q_checkcyclestorage);
	}
	public String getQ_checkhealthbureau() {
		return checkGet(q_checkhealthbureau);
	}
	public void setQ_checkhealthbureau(String q_checkhealthbureau) {
		this.q_checkhealthbureau = checkSet(q_checkhealthbureau);
	}
	public String getQ_ischeckmatchnum() {
		return checkGet(q_ischeckmatchnum);
	}
	public void setQ_ischeckmatchnum(String q_ischeckmatchnum) {
		this.q_ischeckmatchnum = checkSet(q_ischeckmatchnum);
	}
	public String getQ_anamedicineType() {
		return checkGet(q_anamedicineType);
	}
	public void setQ_anamedicineType(String q_anamedicineType) {
		this.q_anamedicineType = checkSet(q_anamedicineType);
	}
	public String getQ_anaproduceType() {
		return checkGet(q_anaproduceType);
	}
	public void setQ_anaproduceType(String q_anaproduceType) {
		this.q_anaproduceType = checkSet(q_anaproduceType);
	}
	public String getQ_analotType() {
		return checkGet(q_analotType);
	}
	public void setQ_analotType(String q_analotType) {
		this.q_analotType = checkSet(q_analotType);
	}
	public String getQ_anarecyclereason() {
		return checkGet(q_anarecyclereason);
	}
	public void setQ_anarecyclereason(String q_anarecyclereason) {
		this.q_anarecyclereason = checkSet(q_anarecyclereason);
	}
	public String[] getQ_anasurvey() {
		return q_anasurvey;
	}
	public void setQ_anasurvey(String[] q_anasurvey) {
		this.q_anasurvey = q_anasurvey;
	}
	public String[] getQ_anaprecaution() {
		return q_anaprecaution;
	}
	public void setQ_anaprecaution(String[] q_anaprecaution) {
		this.q_anaprecaution = q_anaprecaution;
	}
	
}
