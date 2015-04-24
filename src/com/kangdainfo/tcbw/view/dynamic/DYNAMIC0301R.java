package com.kangdainfo.tcbw.view.dynamic;



import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.collections.set.ListOrderedSet;
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



public class DYNAMIC0301R extends SuperBean
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
	private String q_dataRevDateS;//資料收集日期起
	private String q_dataRevDateE;//資料收集日期迄
	private String q_publDept;//發佈單位
	private String[] q_situation;//狀況
	private String q_publDateS;//發佈日期起
	private String q_publDateE;//發佈日期迄
	private String q_chProduct;//商品名
	private String q_scientificName;//學名
	private String q_druggist;//廠商
	private String q_manufactorName;//製造廠
	private String q_permitKey;//國內許可證
	private String q_permitNo;//國內許可證
	private String q_applicationName;//國內許可證持有商
	private String q_lotNo;//產品批號
	private String[] q_qualitywarningtype;//品質異常型態
	private String[] q_recycleType;//回收型態
	private String q_iswarning;//回收型態
	
	private String[] q_lamp;//燈號
	private String q_aftereffect;//後續處理

	private String q_isImport;//產品是否進口
	private String q_nonImportReason;//產品未進口原因

	
	
	//查詢條件
	public String condition()
	{
		String condition="";
		
		//案件編號
		if(!"".equals(getQ_applNoS()))
			condition+=" and applNo7001 >="+Common.sqlChar(getQ_applNoS());
		if(!"".equals(getQ_applNoE()))
			condition+=" and applNo7001 <="+Common.sqlChar(getQ_applNoE());
		
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
			condition+=" and status7001 in ("+statusStr+")";
		
		//資料收集日期
		if(!"".equals(getQ_dataRevDateS()))
			condition+=" and dataRevDate7001 >="+Common.sqlChar(getQ_dataRevDateS());
		if(!"".equals(getQ_dataRevDateE()))
			condition+=" and dataRevDate7001 <="+Common.sqlChar(getQ_dataRevDateE());
		
		//發佈單位
		if(!"".equals(getQ_publDept()))
			condition+=" and publDept7001="+Common.sqlChar(getQ_publDept());
		
		//狀況
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
			condition+=" and situation7001 in ("+situationStr+")";
		
		//發佈日期
		if(!"".equals(getQ_publDateS()))
			condition+=" and publDate7001 >="+Common.sqlChar(getQ_publDateS());
		if(!"".equals(getQ_publDateE()))
			condition+=" and publDate7001 <="+Common.sqlChar(getQ_publDateE());
		
		//商品名
		if(!"".equals(getQ_chProduct()))
			condition+=" and chProduct7001 like "+TCBWCommon.likeSqlChar(getQ_chProduct());
		
		//學名
		if(!"".equals(getQ_scientificName()))
			condition+=" and scientificName7001 like "+TCBWCommon.likeSqlChar(getQ_scientificName());
		
		//學名
		if(!"".equals(getQ_scientificName()))
			condition+=" and scientificName7001 like "+TCBWCommon.likeSqlChar(getQ_scientificName());
		
		//製造廠
		if(!"".equals(getQ_manufactorName()))
			condition+=" and manufactorName7001 like "+TCBWCommon.likeSqlChar(getQ_manufactorName());
		
		//國內許可證
		if(!"".equals(getQ_permitKey()))
			condition+=" and permitKey7003 like "+TCBWCommon.likeSqlChar(getQ_permitKey());
		
		if(!"".equals(getQ_permitNo()))
			condition+=" and permitNo7003 like "+TCBWCommon.likeSqlChar(getQ_permitNo());
		
		//國內許可證持有商
		if(!"".equals(getQ_applicationName()))
			condition+=" and applicationName7003 like "+TCBWCommon.likeSqlChar(getQ_applicationName());
		
		//產品批號
		if(!"".equals(getQ_lotNo()))
			condition+=" and lotNo7001 like "+TCBWCommon.likeSqlChar(getQ_lotNo());
		
		//品質異常型態
		String qualitywarningtypeStr="";
		if(null!=getQ_qualitywarningtype() && getQ_qualitywarningtype().length>0)
		{
			for(int i=0;i<getQ_qualitywarningtype().length;i++)
			{
				if(qualitywarningtypeStr.length()>0)qualitywarningtypeStr+=",";
				qualitywarningtypeStr+="'"+getQ_qualitywarningtype()[i]+"'";
			}	
		}	
		
		if(qualitywarningtypeStr.length()>0)
			condition+=" and qualitywarningtype7001 in ("+qualitywarningtypeStr+")";
		
		//回收型態
		String recycleTypeStr="";
		if(null!=getQ_recycleType() && getQ_recycleType().length>0)
		{
			for(int i=0;i<getQ_recycleType().length;i++)
			{
				if(recycleTypeStr.length()>0)recycleTypeStr+=",";
				recycleTypeStr+="'"+getQ_recycleType()[i]+"'";
			}	
		}	
		
		if(recycleTypeStr.length()>0)
			condition+=" and recycleType7001 in ("+recycleTypeStr+")";
		
		
		//是否草擬紅綠燈初稿
		if(!"".equals(getQ_iswarning()))
			condition+=" and iswarning7001="+Common.sqlChar(getQ_iswarning());
		
		//燈號
		String lampStr="";
		if(null!=getQ_lamp() && getQ_lamp().length>0)
		{
			for(int i=0;i<getQ_lamp().length;i++)
			{
				if(lampStr.length()>0)lampStr+=",";
				lampStr+="'"+getQ_lamp()[i]+"'";
			}	
		}	
		
		if(lampStr.length()>0)
			condition+=" and lamp7001 in ("+lampStr+")";
		
		
		//後續處理
		if(!"".equals(getQ_aftereffect()))
			condition+=" and aftereffect7001="+Common.sqlChar(getQ_aftereffect());
		
		
		//產品是否進口
		if(!"".equals(getQ_isImport()))
			condition+=" and isImport7002 like "+TCBWCommon.likeSqlChar(getQ_isImport());
		
		//產品未進口原因
		if(!"".equals(getQ_nonImportReason()))
			condition+=" and nonImportReason7002 like "+TCBWCommon.likeSqlChar(getQ_nonImportReason());
		
		
		return condition;
		
	}
	
	//匯出Xls
	public void exportXls(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
        //表頭中文
		java.util.Map<String,String>  titleMap=TCBWCommon.getMap("select field,fieldName from Export0002Db where export0001Db.code like 'drg03%'");
		//多筆表頭中文
		java.util.Map<String,String>  manyTitleMap=TCBWCommon.getMap("select manyField,fieldName from Export0002Db where export0001Db.code like 'drg03%' and isMany='Y' ");
		
		//全部代碼中文
		java.util.Map<String,String>  codeNameMap=TCBWCommon.getMap("select commonCodeKind.codeKindId+codeId,codeName from CommonCode");
		codeNameMap.put("YNY","是");codeNameMap.put("YNN","否");
		
		//判斷是否有map
		java.util.Map<String,String>  isMap=TCBWCommon.getMap("select field,codeName from Export0002Db where export0001Db.code like 'drg03%' and codeName is not null and codeName <> 'NULL' ");
		
		//判斷是否為多筆map
		java.util.Map<String,String>  manyNameMap=TCBWCommon.getMap("select field,manyName from Export0002Db where export0001Db.code like 'drg03%' and isMany='Y' ");
		
		//
		java.util.Map<String,String>  fieldNameMap=TCBWCommon.getMap("select field,manyField from Export0002Db where export0001Db.code like 'drg03%' and isMany='Y' ");
		
		//Excel檔案路徑
		File outputFile = new File(System.getProperty("java.io.tmpdir")+File.separator+"DYNAMIC0301R_"+Datetime.getHHMMSS()+ ".xls");
		//建立Excel檔案
		WritableWorkbook workbook = Workbook.createWorkbook(outputFile);
		//建立sheet1頁籤
		WritableSheet sheet1 = workbook.createSheet("國內外藥品品質警訊", 0);
	
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
                   hql+=" from dynamicView03 ";
                   hql+=" where 1=1 ";
                   
                   hql+=condition();    
                  
                   
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
						  
						  if("qualitywarningtype7001".equals(getCode()[y]) )
						  {
							  String kind1=Common.get(x[y]);
							  String[] str1=null;
							  String kindName="";
							  
							  if(kind1.length()>0)
							  {
								  str1=kind1.split(",");
								  for(int a=0;a<str1.length;a++)
								  {
									  if(kindName.length()>0) kindName+=",";
									  kindName+=codeNameMap.get(isMap.get(getCode()[y])+Common.get(str1[a]));
								  }	  
							  }	 
							  value=kindName;
						  }
						  
						  if("warningmedModel7001".equals(getCode()[y]) )
						  {
							  String kind1=Common.get(x[y]);
							  String[] str1=null;
							  String kindName="";
							  
							  if(kind1.length()>0)
							  {
								  str1=kind1.split(",");
								  for(int a=0;a<str1.length;a++)
								  {
									  if(kindName.length()>0) kindName+=",";
									  kindName+=codeNameMap.get(isMap.get(getCode()[y])+Common.get(str1[a]));
								  }	  
							  }	 
							  value=kindName;
						  }
						  
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
       			  
       			  if(null!=manyMap.get("warningmedModelSheet"))
       			  {
       				//建立sheet2頁籤 =劑型
       				//WritableSheet sheet2=workbook.createSheet("基本資料-劑型", 1);	
       				//warningmedModelSheet(sheet2);
       			  }
       			  
     
       			  if(null!=manyMap.get("Drg7003DbSheet"))
     			  {
       				//建立sheet3頁籤===國內許可證
         		    WritableSheet sheet3=workbook.createSheet("基本資料-國內許可證", 2);	
         		    createDrg7003DbSheet(sheet3,manyMap.get("Drg7003DbSheet"),manyTitleMap);
     			  }
       			  
       			  if(null!=manyMap.get("Drg7002DbSheet"))
  			      {
       				//建立sheet4頁籤
       				WritableSheet sheet4 = workbook.createSheet("廠商回覆", 3);	
          		    //廠商回覆
          		    createDrg7002DbSheet(sheet4,manyMap.get("Drg7002DbSheet"),manyTitleMap);
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
	

	//基本資料-劑型
	public void warningmedModelSheet(WritableSheet sheet2) throws Exception
	{

 	   String drg7001Hql="  select a.applNo7001,a.warningmedModel7001";
 	          drg7001Hql+=" from dynamicView03 a";
 	          drg7001Hql+=" where 1=1 ";
 	          drg7001Hql+= condition();
 	          drg7001Hql+=" order by a.applNo7001 ";
       
 	   java.util.List list7001 = ServiceGetter.getInstance().getTcbwService().getNativeSqlQuery().load(drg7001Hql);
 	   
 	   System.out.println("drg7001Hql=="+drg7001Hql);
 	   
 	   //設定字行
	   WritableFont Font10 = new WritableFont(WritableFont.createFont("標楷體"),12);
	   WritableCellFormat detailCenter = new  WritableCellFormat(Font10);
 	   
 	   sheet2.addCell(new Label(0,0,"案件編號",detailCenter));
	   sheet2.addCell(new Label(1,0,"劑型",detailCenter));	
	   sheet2.setColumnView(0,20);
	   sheet2.setColumnView(1,30);
	
 	   
 	   if (list7001!=null && list7001.size()>0) 			
	   {
 		  int e=1; 

 		 
 		  java.util.Map<String, String> warningmedModeMap = TCBWCommon.getCommonCodeMap("DRGFORM");
 		
 		  for (int j=0; j<list7001.size(); j++) 
    	  {
			   Object x[] = (Object[])list7001.get(j);
			     
			   String[] str=null;
			  
			   if(!"".equals(Common.get(x[1])))
			   {
			       str=Common.get(x[1]).split(",");
			       
			       for(int i=0;i<str.length;i++)
			       {
			        	sheet2.addCell(new Label(0,e,Common.get(x[0]),detailCenter));
			        	sheet2.addCell(new Label(1,e,Common.get(warningmedModeMap.get(str[i])),detailCenter));
			    		e++;
			       }	
			   }

    	    }
	    }

	}
	
	
	
	//createDrg7003DbSheet
	public void createDrg7003DbSheet(WritableSheet sheet3,String title,java.util.Map<String,String> titleMap) throws Exception
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
		  tmpMaxLength[i+1] = Common.get(Common.get(titleMap.get(value[i]))).length()*2;
	 	}	  
		

 	   String hql0001=" (select id from dynamicView03 where 1=1 "+condition()+")";

 	   String drg7003Hql="  select b.applNo,"+field;
 	          drg7003Hql+=" from Drg7003_Db a,Drg7001_Db b";
 	          drg7003Hql+=" where a.drg7001_id=b.id ";
 	          drg7003Hql+=" and b.id  in "+hql0001;
 	          drg7003Hql+=" order by b.applNo ";
 	   
 	   System.out.println("drg7003Hql=="+drg7003Hql);
 	          
 	   java.util.List list7003 = ServiceGetter.getInstance().getTcbwService().getNativeSqlQuery().load(drg7003Hql);
 	   
 	   if (list7003!=null && list7003.size()>0) 			
	   {
 		  int e=1;
 		  
 		  java.util.Map<String, String> permitKeyMap = TCBWCommon.getCommonCodeMap("DRGPKID");
 		  
 		  for (int j=0; j<list7003.size(); j++) 
    	  {
			   Object x[] = (Object[])list7003.get(j);
			   
			   sheet3.addCell(new Label(0,e,Common.get(x[0]),detailCenter));
			   
			   if(Common.get(x[0]).length()*2 >= tmpMaxLength[0])
			   {
			      tmpMaxLength[0] = Common.get(x[0]).length()*2; 
			   }
			   
			   for(int i=0;i<value.length;i++) 
			   {
				 if("permitKey".equals(value[i]))
					 sheet3.addCell(new Label(i+1,e,Common.get(permitKeyMap.get(x[i+1])),detailCenter));
				 else
			 	     sheet3.addCell(new Label(i+1,e,Common.get(x[i+1]),detailCenter));
			 	 
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
   	    	 sheet3.setColumnView(i, tmpMaxLength[i]*2);
 		  }
 		  
	   }

	}
	
	
	//廠商回覆
	public void createDrg7002DbSheet(WritableSheet sheet4,String title,java.util.Map<String,String> titleMap) throws Exception
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
		  tmpMaxLength[i+1] = Common.get(Common.get(titleMap.get(value[i]))).length()*2;
	 	}	  
		

 	   String hql0001=" (select id from dynamicView03 where 1=1 "+condition()+")";

 	   String drg7002Hql="  select b.applNo,"+field;
	          drg7002Hql+=" from Drg7002_Db a,Drg7001_Db b";
	          drg7002Hql+=" where a.drg7001_id=b.id ";
	          drg7002Hql+=" and a.drg7001_id  in "+hql0001;
	          drg7002Hql+=" order by b.applNo ";       
 	          
 	   System.out.println("drg7002Hql=="+drg7002Hql);
 	          
 	   java.util.List list7002 = ServiceGetter.getInstance().getTcbwService().getNativeSqlQuery().load(drg7002Hql);
 	   
 	   if (list7002!=null && list7002.size()>0) 			
	   {
 		  int e=1;
 		  
 		  java.util.Map<String,String>  codeNameMap=new java.util.HashMap<String,String>();
		  
		  codeNameMap.put("Y","是");codeNameMap.put("N","否");
		
		  java.util.Map<String,String>  nonImportReasonMap=TCBWCommon.getCommonCodeMap("DRGNIMREASON");
		  
		  
 		  for (int j=0; j<list7002.size(); j++) 
    	  {
			   Object x[] = (Object[])list7002.get(j);
			   
			   sheet4.addCell(new Label(0,e,Common.get(x[0]),detailCenter));
			   
			   if(Common.get(x[0]).length()*2 >= tmpMaxLength[0])
			   {
			      tmpMaxLength[0] = Common.get(x[0]).length()*2; 
			   }
			   
			   for(int i=0;i<value.length;i++) 
			   {
				   

				 if("isImport".equals(value[i]))
			 	    sheet4.addCell(new Label(i+1,e,codeNameMap.get(x[i+1]),detailCenter));
				 else if("nonImportReason".equals(value[i]))
					sheet4.addCell(new Label(i+1,e,nonImportReasonMap.get(x[i+1]),detailCenter));
				 else
					sheet4.addCell(new Label(i+1,e,Common.get(x[i+1]),detailCenter));
					 
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
   	    	 sheet4.setColumnView(i, tmpMaxLength[i]*2);
 		  }
 		  
	   }

	}

	//存檔
	public void doSaveOrUpdate() throws Exception
	{
		try
		{
          Variant0001Db obj = (Variant0001Db)View.getObject(" from Variant0001Db where name="+ Common.sqlChar(variantName)+" and kind='drg03' ");
		
		  if(obj == null) 
		    obj = new Variant0001Db();
	
		  obj.setKind("drg03");
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
		DYNAMIC0301R obj = this;

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
		
        String hql=" from Export0001Db where code like 'drg03%'";
		
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
						
						System.out.println(export0002Db.getField());
						System.out.println(export0002Db.getIsMany());
						
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

	public String getQ_publDept() {
		return checkGet(q_publDept);
	}

	public void setQ_publDept(String qPublDept) {
		q_publDept = checkSet(qPublDept);
	}

	public String[] getQ_situation() {
		return checkGet(q_situation);
	}

	public void setQ_situation(String[] qSituation) {
		q_situation = checkSet(qSituation);
	}

	public String getQ_publDateS() {
		return checkGet(q_publDateS);
	}

	public void setQ_publDateS(String qPublDateS) {
		q_publDateS = checkSet(qPublDateS);
	}

	public String getQ_publDateE() {
		return checkGet(q_publDateE);
	}

	public void setQ_publDateE(String qPublDateE) {
		q_publDateE = checkSet(qPublDateE);
	}

	public String getQ_chProduct() {
		return checkGet(q_chProduct);
	}

	public void setQ_chProduct(String qChProduct) {
		q_chProduct = checkSet(qChProduct);
	}

	public String getQ_scientificName() {
		return checkGet(q_scientificName);
	}

	public void setQ_scientificName(String qScientificName) {
		q_scientificName = checkSet(qScientificName);
	}

	

	public String getQ_manufactorName() {
		return checkGet(q_manufactorName);
	}

	public void setQ_manufactorName(String qManufactorName) {
		q_manufactorName = checkSet(qManufactorName);
	}

	public String getQ_druggist() {
		return checkGet(q_druggist);
	}

	public void setQ_druggist(String qDruggist) {
		q_druggist = checkSet(qDruggist);
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

	public String getQ_lotNo() {
		return checkGet(q_lotNo);
	}

	public void setQ_lotNo(String qLotNo) {
		q_lotNo = checkSet(qLotNo);
	}

	public String[] getQ_qualitywarningtype() {
		return checkGet(q_qualitywarningtype);
	}

	public void setQ_qualitywarningtype(String[] qQualitywarningtype) {
		q_qualitywarningtype = checkSet(qQualitywarningtype);
	}

	public String[] getQ_recycleType() {
		return checkGet(q_recycleType);
	}

	public void setQ_recycleType(String[] qRecycleType) {
		q_recycleType = checkSet(qRecycleType);
	}

	public String getQ_iswarning() {
		return checkGet(q_iswarning);
	}

	public void setQ_iswarning(String qIswarning) {
		q_iswarning = checkSet(qIswarning);
	}

	public String[] getQ_lamp() {
		return checkGet(q_lamp);
	}

	public void setQ_lamp(String[] qLamp) {
		q_lamp = checkSet(qLamp);
	}

	public String getQ_aftereffect() {
		return checkGet(q_aftereffect);
	}

	public void setQ_aftereffect(String qAftereffect) {
		q_aftereffect = checkSet(qAftereffect);
	}

	public String getQ_isImport() {
		return checkGet(q_isImport);
	}

	public void setQ_isImport(String qIsImport) {
		q_isImport = checkSet(qIsImport);
	}

	public String getQ_nonImportReason() {
		return checkGet(q_nonImportReason);
	}

	public void setQ_nonImportReason(String qNonImportReason) {
		q_nonImportReason = checkSet(qNonImportReason);
	}

	
	
   
	

}
