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
import com.kangdainfo.tcbw.model.bo.Drg0002Db;
import com.kangdainfo.tcbw.model.bo.Export0001Db;
import com.kangdainfo.tcbw.model.bo.Export0002Db;
import com.kangdainfo.tcbw.model.bo.Variant0001Db;
import com.kangdainfo.tcbw.model.bo.Variant0002Db;
import com.kangdainfo.tcbw.util.TCBWCommon;

public class DYNAMIC0101R extends SuperBean
{
	private String id;
	private String[] code;//前端匯出欄位
	private String[] manyCode;//前端匯出欄位
    private String htmlValue;//前端HTML
    private String codeValue;//
    
    private String variantName;//變式名稱
	private String variantExplain;//變式說明
	private String applNoS;//案件編號起
	private String applNoE;//案件編號迄
	private String[] status;//案件狀態
	private String[] notifierType;//屬性
	private String enrolledDateS;//收案日期
	private String enrolledDateE;//收案日期
	
	private String notifierRepDateS;//通報中心接獲通報日期
	private String notifierRepDateE;//通報中心接獲通報日期
	
	private String occurDateS;//發現日期
	private String occurDateE;//發現日期
	
	private String notifierDept1;//服務機構
	private String notifierDept2;//服務機構
	private String notifierDept3;//服務機構
	
	private String permitKey;//許可證字
	private String permitNo;//許可證號
	private String chProduct;//中文品名
	private String enProduct;//英文品名
	private String ingredient1;//有效成分名稱
	private String ingredient2;//有效成分名稱
	private String ingredient3;//有效成分名稱
	private String medModel;//劑型
	private String applicationName;//藥商
	private String manufactorName;//製造廠
	private String manufactorCountry;//製造廠國別
	private String manufactorNo;//製造批號
	private String[] mainCode;//不良品缺陷描述
	private String[] firstResult;//不良品原因初評
	private String intervalDaysS;//間隔天數起
	private String intervalDaysE;//間隔天數迄
	private String beforeOrLater;//產品製造
	private String[] drgLevDrg0004;//不良品風險評估結果
	
	private String medicineType;//學名藥/原廠藥
	private String produceType;//國產/輸入
	private String lotType;//批號範圍
	private String defect;//廠商不良品缺陷
	private String survey;//廠商調查結果
	private String precaution;//廠商預防措施
	
	private String[] drgLevDrg0008;//風險等級 
	
	//查詢條件
	public String condition()
	{
		String condition="";
		
		//案件編號
		if(!"".equals(getApplNoS()) && !"".equals(getApplNoE())) 
			condition += " and applNo0001 >= " +  Common.sqlChar(getApplNoS())+ " and applNo0001 <= " + Common.sqlChar(getApplNoE());
		else if(!"".equals(getApplNoS()) && "".equals(getApplNoE()))
			condition += " and applNo0001 like " + Common.sqlChar("%"+getApplNoS()+"%");
		else if("".equals(getApplNoS()) && !"".equals(getApplNoE()))
			condition += " and applNo0001 like " + Common.sqlChar("%"+getApplNoE()+"%");
		
		//案件狀態
		String statusStr="";
		if(null!=getStatus() && getStatus().length>0)
		{
			for(int i=0;i<getStatus().length;i++)
			{
				if(statusStr.length()>0)statusStr+=",";
				statusStr+="'"+getStatus()[i]+"'";
			}	
		}	
		
		if(statusStr.length()>0)
			condition+=" and status0001 in ("+statusStr+")";
		
		
		//收案日期
		if(!"".equals(getEnrolledDateS()))
			condition+=" and enrolledDate0001 >="+Common.sqlChar(getEnrolledDateS());
		if(!"".equals(getEnrolledDateE()))
			condition+=" and enrolledDate0001 <="+Common.sqlChar(getEnrolledDateE());
		
		//通報中心接獲通報日期
		if(!"".equals(getNotifierRepDateS()))
			condition+=" and notifierRepDate0001 >="+Common.sqlChar(getNotifierRepDateS());
		if(!"".equals(getNotifierRepDateE()))
			condition+=" and notifierRepDate0001 <="+Common.sqlChar(getNotifierRepDateE());
		
		
		//發現日期
		if(!"".equals(getOccurDateS()))
			condition+=" and occurDate0001 >="+Common.sqlChar(getOccurDateS());
		if(!"".equals(getOccurDateE()))
			condition+=" and occurDate0001 <="+Common.sqlChar(getOccurDateE());
		
		//服務機構
		if(!"".equals(getNotifierDept1()) || !"".equals(getNotifierDept2()) || !"".equals(getNotifierDept3()) )
		{
			String notifierDeptStr="";
			
			if(!"".equals(getNotifierDept1()))
				notifierDeptStr+=" notifierDept0001 like "+TCBWCommon.likeSqlChar(getNotifierDept1());
			
			if(notifierDeptStr.length()>0) notifierDeptStr+=" or ";
			
			if(!"".equals(getNotifierDept2()))
				notifierDeptStr+=" notifierDept0001 like "+TCBWCommon.likeSqlChar(getNotifierDept2());
			
			if(notifierDeptStr.length()>0) notifierDeptStr+=" or ";
			
			if(!"".equals(getNotifierDept3()))
				notifierDeptStr+=" notifierDept0001 like "+TCBWCommon.likeSqlChar(getNotifierDept3());
			
			condition+=" and (";
			condition+=notifierDeptStr;
			condition+=" )";
		}	
			
		
		//許可證字號
		if(!"".equals(getPermitKey()))
			condition+=" and permitKey0001="+Common.sqlChar(getPermitKey());
		if(!"".equals(getPermitNo()))
			condition+=" and permitNo0001="+Common.sqlChar(getPermitNo());
		
		//屬性
		String notifierTypeStr="";
		if(null!=getNotifierType() && getNotifierType().length>0)
		{
			for(int i=0;i<getNotifierType().length;i++)
			{
				if(notifierTypeStr.length()>0)notifierTypeStr+=",";
				notifierTypeStr+="'"+getNotifierType()[i]+"'";
			}	
		}	
		
		if(notifierTypeStr.length()>0)
			condition+=" and notifierType0001 in ("+notifierTypeStr+")";
		
	    //中文品名
		if(!"".equals(getChProduct()))
			condition+=" and chProduct0001 like "+TCBWCommon.likeSqlChar(getChProduct());
		//英文品名
		if(!"".equals(getEnProduct()))
			condition+=" and enProduct0001 like "+TCBWCommon.likeSqlChar(getEnProduct());
		
		//有效成分名稱
		if(!"".equals(getIngredient1()) || !"".equals(getIngredient2()) || !"".equals(getIngredient3()) )
		{
			String ingredientStr="";
			
			if(!"".equals(getIngredient1()))
				ingredientStr+=" notifierDept0001 like "+TCBWCommon.likeSqlChar(getIngredient1());
			
			if(ingredientStr.length()>0) ingredientStr+=" or ";
			
			if(!"".equals(getIngredient2()))
				ingredientStr+=" notifierDept0001 like "+TCBWCommon.likeSqlChar(getIngredient2());
			
			if(ingredientStr.length()>0) ingredientStr+=" or ";
			
			if(!"".equals(getIngredient3()))
				ingredientStr+=" notifierDept0001 like "+TCBWCommon.likeSqlChar(getIngredient3());
			
			condition+=" and (";
			condition+=ingredientStr;
			condition+=" )";
		}	
	
		//劑型
		if(!"".equals(getMedModel()))
			condition+=" and medModel0001="+Common.sqlChar(getMedModel());
		
		//藥商
		if(!"".equals(getApplicationName()))
			condition+=" and applicationName0001 like "+TCBWCommon.likeSqlChar(getApplicationName());
		
		//製造廠
		if(!"".equals(getManufactorName()))
			condition+=" and manufactorName0001 like "+TCBWCommon.likeSqlChar(getManufactorName());
		
		//製造廠國別
		if(!"".equals(getManufactorCountry()))
			condition+=" and manufactorCountry0001 like "+TCBWCommon.likeSqlChar(getManufactorCountry());
		
		//製造批號
		if(!"".equals(getManufactorNo()))
			condition+=" and manufactorNo0001="+Common.sqlChar(getManufactorNo());
		
		//不良品缺陷描述	
		String mainCodeStr="";
		if(null!=getMainCode() && getMainCode().length>0)
		{
			for(int i=0;i<getMainCode().length;i++)
			{
				if(mainCodeStr.length()>1) mainCodeStr+=" or ";
				mainCodeStr+=" mainCode0002 like "+TCBWCommon.likeSqlChar(getMainCode()[i]);
			}	
		}	
		
		if(mainCodeStr.length()>0)
			condition+=" and ("+mainCodeStr+")";
		
		
		//不良品風險評估結果drg0004db drglev   
		String drglev0004Str="";
		if(null!=getDrgLevDrg0004() && getDrgLevDrg0004().length>0)
		{
			for(int i=0;i<getDrgLevDrg0004().length;i++)
			{
				if(drglev0004Str.length()>0)drglev0004Str+=",";
				drglev0004Str+="'"+getDrgLevDrg0004()[i]+"'";
			}	
		}	
		
		if(drglev0004Str.length()>0)
			condition+=" and drglev0004 in ("+drglev0004Str+")";
		
		
		//風險等級   drgin0114f drg0008db drgLev   
		String drglev0008Str="";
		if(null!=getDrgLevDrg0008() && getDrgLevDrg0008().length>0)
		{
			for(int i=0;i<getDrgLevDrg0008().length;i++)
			{
				if(drglev0008Str.length()>0)drglev0008Str+=",";
				drglev0008Str+="'"+getDrgLevDrg0008()[i]+"'";
			}	
		}	
		
		if(drglev0008Str.length()>0)
			condition+=" and drglev0008 in ("+drglev0008Str+")";
		
		
		//不良品原因初評 drg0001db 
		String firstResultStr="";
		if(null!=getFirstResult() && getFirstResult().length>0)
		{
			for(int i=0;i<getFirstResult().length;i++)
			{
				if(firstResultStr.length()>0)firstResultStr+=",";
				firstResultStr+="'"+getFirstResult()[i]+"'";
			}	
		}	
		
		if(firstResultStr.length()>0)
			condition+=" and firstResult0001 in ("+firstResultStr+")";
		
		
		//間隔天數
		if(!"".equals(getIntervalDaysS()))
			condition+=" and intervalDays0003 >="+Common.sqlChar(getIntervalDaysS());
		if(!"".equals(getIntervalDaysE()))
			condition+=" and intervalDays0003 <="+Common.sqlChar(getIntervalDaysE());
		
		//產品製造
		if(!"".equals(getBeforeOrLater()))
			condition+=" and beforeOrLater0005="+Common.sqlChar(getBeforeOrLater());

		//學名藥/原廠藥
		if(!"".equals(getMedicineType()))
			condition+=" and medicineType0006="+Common.sqlChar(getMedicineType());
		
		//國產/輸入
		if(!"".equals(getProduceType()))
			condition+=" and produceType0006="+Common.sqlChar(getProduceType());
		
		//批號範圍
		if(!"".equals(getLotType()))
			condition+=" and lotType0006="+Common.sqlChar(getLotType());
		
		
		//廠商不良品缺陷
		if(!"".equals(getDefect()))
			condition+=" and lotType0006="+Common.sqlChar(getLotType());
		
		//廠商調查結果
		if(!"".equals(getSurvey()))
			condition+=" and defect0006="+Common.sqlChar(getSurvey());
		
		//廠商預防措施
		if(!"".equals(getPrecaution()))
			condition+=" and precaution0006="+Common.sqlChar(getPrecaution());
		
		return condition;
		
	}
	

		
	//匯出Xls
	public void exportXls(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
	    //表頭中文
		java.util.Map<String,String>  titleMap=TCBWCommon.getMap("select field,fieldName from Export0002Db where export0001Db.code like 'drg01%'");
		//多筆表頭中文
		java.util.Map<String,String>  manyTitleMap=TCBWCommon.getMap("select manyField,fieldName from Export0002Db where export0001Db.code like 'drg01%' and isMany='Y' ");
		//全部代碼中文
		java.util.Map<String,String>  codeNameMap=TCBWCommon.getMap("select commonCodeKind.codeKindId+codeId,codeName from CommonCode");
		codeNameMap.put("YNY","是");codeNameMap.put("YNN","否");
		codeNameMap.put("Aging1","時效佳");codeNameMap.put("Aging2","時效待加強");
		codeNameMap.put("Quality1","Excellent");codeNameMap.put("Quality2","Good");codeNameMap.put("Quality3","Fair");
		codeNameMap.put("ProduceType1","國產");codeNameMap.put("ProduceType2","進口");
		//判斷是否有map
		java.util.Map<String,String>  isMap=TCBWCommon.getMap("select field,codeName from Export0002Db where export0001Db.code like 'drg01%' and codeName is not null and codeName <> 'NULL' ");
		//判斷是否為多筆map
		java.util.Map<String,String>  manyNameMap=TCBWCommon.getMap("select field,manyName from Export0002Db where export0001Db.code like 'drg01%' and isMany='Y' ");		
		java.util.Map<String,String>  fieldNameMap=TCBWCommon.getMap("select field,manyField from Export0002Db where export0001Db.code like 'drg01%' and isMany='Y' ");
		//不良品缺陷
		java.util.Map<String, String> dpdMap = TCBWCommon.getCommonCodeMap("DRGDPD"); //不良品缺陷(主)
		java.util.Map<String, String> subMap = TCBWCommon.getMap("select dpdKind,dpdKindName from Drg1001Db"); //不良品缺陷(子)
		
		//Excel檔案路徑
		File outputFile = new File(System.getProperty("java.io.tmpdir")+File.separator+"DYNAMIC0101R_"+Datetime.getHHMMSS()+ ".xls");
		//建立Excel檔案
		WritableWorkbook workbook = Workbook.createWorkbook(outputFile);
		//建立sheet1頁籤
		WritableSheet sheet1 = workbook.createSheet("藥品不良品通報", 0);		
		
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
				
				sheet1.addCell(new Label(i,0,titleMap.get(getCode()[i])));
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
                   hql+=  ", '1'";//防止只輸入一個欄位出錯
                   hql+=" from dynamicView01 ";
                   hql+=" where 1=1 ";
                   hql+=condition();
                   hql+=" order by applNo0001 ";
                  
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
					  //不良品缺陷額外處理					  
					  else if ("id0001".equals(getCode()[y])){
						  java.util.List drg02List = ServiceGetter.getInstance().getCommonService().load("from Drg0002Db where drg0001Db.id="+Common.getLong(Common.get(x[y]))+" order by mainCode asc");
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
										for(int k=0; k<subCode.length; k++){
											if(subCodeList.length()>0) subCodeList += ",";
											subCodeList += subMap.get(subCode[k]);
											if("ZZ".equals(subCode[k].substring(2))){
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
							value = Common.get(dpdList);
					  }
					  else{
						  value=Common.get(x[y]);						 
					  }	  
					  
					  sheet1.addCell(new Label(y,j+1,value,detailCenter));
					  
					  //塞入資料最大字數
					  if(Common.get(Common.get(value)).length()*2 >= tmpMaxLength[y])
  					  {
  						tmpMaxLength[y] = Common.get(value).length()*2; 
  					  }
					  
					  //不良品缺陷(需在寫入sheet2)				   
					  if( j==0  && "id0001".equals(getCode()[y]))
					  {						  
						  if(null!=manyMap.get("DetailSheet2"))      					
	       				  {       						   
	        				  manyMap.put("DetailSheet2",manyMap.get("DetailSheet2")+","+"id0001");       				
	       				  }       					   
	        			  else       					   
	        			  {       				
	        				  manyMap.put("DetailSheet2","mainCode0002");       					   
	        			  }
					  }
				  } 
	    	   }
        	   
        	   //將最大欄位字數乘2
        	   for(int i=0; i<tmpMaxLength.length; i++)
       		   {
       			 sheet1.setColumnView(i, tmpMaxLength[i]*2);
      		   }        	   

        	   if (getManyCode()!=null && getManyCode().length>0 || manyMap.size() > 0 ) 
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
       			  
       			   if(null!=manyMap.get("DetailSheet2"))
       			   {       				
       				   //建立sheet2頁籤        				
       				   WritableSheet sheet2 = workbook.createSheet("通報資料區-不良品缺陷描述", 1);              	    
       				   createDetailSheet2(sheet2);
       			   }
       			  
       			   if(null!=manyMap.get("DetailSheet3"))
     			   {     				
       				   //建立sheet3頁籤     				
       				   WritableSheet sheet3 = workbook.createSheet("廠商回覆-不良品缺陷", 2);             	    
       				   createDetailSheet3(sheet3,manyMap.get("DetailSheet3"),manyTitleMap);
     			   }
       			  
       			   if(null!=manyMap.get("DetailSheet4"))
   			       {   				    
       				   //建立sheet4頁籤   				    
       				   WritableSheet sheet4 = workbook.createSheet("廠商回覆-廠商調查結果", 3);         	        
       				   createDetailSheet4(sheet4,manyMap.get("DetailSheet4"),manyTitleMap);
   			       }
       			
       			   if(null!=manyMap.get("DetailSheet5"))
 			       { 				    
       				   //建立sheet5頁籤 				    
       				   WritableSheet sheet5 = workbook.createSheet("廠商回覆-廠商預防措施", 4);       	        
       				   createDetailSheet5(sheet5,manyMap.get("DetailSheet5"),manyTitleMap);
 			       }
       			
       			   if(null!=manyMap.get("DetailSheet6"))
 			       { 				    
       				   //建立sheet6頁籤 				    
       				   WritableSheet sheet6 = workbook.createSheet("案件評估區-擬辦事項", 5);       	        
       				   createDetailSheet6(sheet6,manyMap.get("DetailSheet6"),manyTitleMap);
 			       }
       			
       			   if(null!=manyMap.get("DetailSheet7"))
			       {				    
       				   //建立sheet7頁籤				    
       				   WritableSheet sheet7 = workbook.createSheet("案件分析-不良品缺陷", 6);      	        
       				   createDetailSheet7(sheet7,manyMap.get("DetailSheet7"),manyTitleMap);
			       } 
       			   
       			   if(null!=manyMap.get("DetailSheet8"))
			       {				    
    				   //建立sheet8頁籤				    
       				   WritableSheet sheet8 = workbook.createSheet("案件分析-廠商調查結果", 7);     	        
    				   createDetailSheet8(sheet8,manyMap.get("DetailSheet8"),manyTitleMap);
			       }
       			   
       			   if(null!=manyMap.get("DetailSheet9"))
			       {				    
    				   //建立sheet9頁籤				    
       				   WritableSheet sheet9 = workbook.createSheet("案件分析-廠商預防措施", 8);     	        
    				   createDetailSheet9(sheet9,manyMap.get("DetailSheet9"),manyTitleMap);    				   
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
	
	public void createDetailSheet2(WritableSheet sheet2) throws Exception
	{	   
	   String subHql2= " select a.applNo,b.mainCode,b.subCode,b.otherDescribe ";
 	          subHql2+=" from DRG0001_DB a left join DRG0002_Db b on a.id=b.DRG0001_ID where 1=1";
 	          subHql2+=" and a.id in ( select id0001 from dynamicView01 where 1=1 "+condition()+")";
 	          subHql2+=" order by a.applNo ";
 	          
 	   System.out.println("subHql2=="+subHql2);
 	          
 	   java.util.List list002 = ServiceGetter.getInstance().getTcbwService().getNativeSqlQuery().load(subHql2);
 	   
 	   if (list002!=null && list002.size()>0) 			
	   { 	
 		  //設定字行
 		  WritableFont Font10 = new WritableFont(WritableFont.createFont("標楷體"),12);
 		  WritableCellFormat detailCenter = new  WritableCellFormat(Font10);
 		 
 		  int e=1; 		 
 		  java.util.Map<String, String> mainMap = TCBWCommon.getCommonCodeMap("DRGDPD"); //不良品缺陷(主)			
 		  java.util.Map<String, String> subMap = TCBWCommon.getMap("select dpdKind,dpdKindName from Drg1001Db"); //缺陷子代碼
 		 
 		  sheet2.addCell(new Label(0,0,"案件編號",detailCenter));
		  sheet2.addCell(new Label(1,0,"不良品缺陷",detailCenter));	
		  sheet2.addCell(new Label(2,0,"不良品缺陷描述",detailCenter));
		  sheet2.setColumnView(0,20);
		  sheet2.setColumnView(1,40);
		  sheet2.setColumnView(2,80);
		

 		  for (int j=0; j<list002.size(); j++)
    	  {
			   Object x[] = (Object[])list002.get(j);
			     
			   String[] subCodeList=null;
			   //有子類別
			   if(!"".equals(Common.get(x[2])))
			   {
			       subCodeList=Common.get(x[2]).split(",");
			       for(int i=0;i<subCodeList.length;i++)
			       {
			    	   sheet2.addCell(new Label(0,e,Common.get(x[0]),detailCenter));			        	
			    	   sheet2.addCell(new Label(1,e,mainMap.get(Common.get(x[1])),detailCenter));		        	
			    	   if(!"".equals(Common.get(subCodeList[i]))){			    		   
			        		if("ZZ".equals(Common.get(subCodeList[i]).substring(2))){
			        			sheet2.addCell(new Label(2,e,subMap.get(subCodeList[i])+":"+Common.get(x[3]),detailCenter));	
			        		}else{
			        			sheet2.addCell(new Label(2,e,subMap.get(subCodeList[i]),detailCenter));	
			        		}			        				        	
			    	   }			    	
			    	   e++;
			       }	
			   }
			   //沒有子類別
			   else{
				   sheet2.addCell(new Label(0,e,Common.get(x[0]),detailCenter));			        	
		    	   sheet2.addCell(new Label(1,e,mainMap.get(Common.get(x[1])),detailCenter));
		    	   sheet2.addCell(new Label(2,e,Common.get(x[3]),detailCenter));
		    	   e++;
			   } 
    	    }
	    }
	}

	//廠商回覆-不良品缺陷
	public void createDetailSheet3(WritableSheet sheet3,String title,java.util.Map<String,String> titleMap) throws Exception
	{
		//設定字行
		WritableFont Font10 = new WritableFont(WritableFont.createFont("標楷體"),12);
		WritableCellFormat detailCenter = new  WritableCellFormat(Font10);
		
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
	 		sheet3.addCell(new Label(i+1,0,titleMap.get(value[i]),detailCenter));	 	  
	 		//放入表頭字數		  
	 		tmpMaxLength[i+1] = Common.get(titleMap.get(value[i])).length()*2;
	 	}

	 	String subHql3="  select a.applNo0001 ,a.defect0006 ";
	 	       subHql3+=" from dynamicView01 a where 1=1 ";
	 	       subHql3+= condition();
	 	       subHql3+=" order by a.applNo0001 ";
  
        System.out.println("subHql3=="+subHql3);	          
 	  
 	    java.util.List listSheet3 = ServiceGetter.getInstance().getTcbwService().getNativeSqlQuery().load(subHql3); 	   
 	  
 	    if (listSheet3!=null && listSheet3.size()>0)  
 	    { 		  
 	    	int e=1; 		  
 	    	
 	    	java.util.Map<String, String> drg0108Map = TCBWCommon.getCommonCodeMap("DRG0108");
 	    	for (int j=0; j<listSheet3.size(); j++)  	  
 	    	{			   
 	    		Object x[] = (Object[])listSheet3.get(j);
 	    		
 	    		String[] defect = null;
 	    		if(!"".equals(Common.get(x[1]))){
 	    			defect = Common.get(x[1]).split(",");
 	 	    		for(int k=0;k< defect.length;k++)
 	 	    		{
 	 	    			sheet3.addCell(new Label(0,e,Common.get(x[0]),detailCenter));
 	 	    			if(Common.get(x[0]).length()*2 >= tmpMaxLength[0])			   
 	 	 	    		{			      
 	 	 	    			tmpMaxLength[0] = Common.get(x[0]).length()*2;			   
 	 	 	    		}
 	 	 	    		sheet3.addCell(new Label(1,e,drg0108Map.get(Common.get(defect[k])),detailCenter));			 	 
 	 	 	    		if(Common.get(defect[k]).length()*2 >= tmpMaxLength[1])				 
 	 	 	    		{				     
 	 	 	    			tmpMaxLength[1] = Common.get(defect[k]).length()*2;				 
 	 	 	    		} 	 	    		
 	 	    			e++; 	    			
 	 	    		}
 	    		} 	    		  	  
 	    	} 		  
 	    	//將最大欄位字數乘2   	      
 	    	for(int i=0; i<tmpMaxLength.length; i++)  		  
 	    	{   	    	 
 	    		sheet3.setColumnView(i, tmpMaxLength[i]*2); 		  
 	    	}	   
 	    }	
	}
	
	//廠商回覆-廠商調查結果
	public void createDetailSheet4(WritableSheet sheet4,String title,java.util.Map<String,String> titleMap) throws Exception
	{	
		//設定字行
		WritableFont Font10 = new WritableFont(WritableFont.createFont("標楷體"),12);
		WritableCellFormat detailCenter = new  WritableCellFormat(Font10);
		
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
	 		sheet4.addCell(new Label(i+1,0,titleMap.get(value[i]),detailCenter));	 	  
	 		//放入表頭字數		  
	 		tmpMaxLength[i+1] = Common.get(titleMap.get(value[i])).length()*2;
	 	}

	 	String subHql4="  select a.applNo0001 ,a.survey0006 ";
	 	       subHql4+=" from dynamicView01 a where 1=1 ";
	 	       subHql4+= condition();
	 	       subHql4+=" order by a.applNo0001 ";
  
        System.out.println("subHql4=="+subHql4);	          
 	  
 	    java.util.List listSheet4 = ServiceGetter.getInstance().getTcbwService().getNativeSqlQuery().load(subHql4); 	   
 	  
 	    if (listSheet4!=null && listSheet4.size()>0)  
 	    { 		  
 	    	int e=1; 		  
 	    	
 	    	java.util.Map<String, String> drg0109Map = TCBWCommon.getCommonCodeMap("DRG0109");
 	    	for (int j=0; j<listSheet4.size(); j++)  	  
 	    	{			   
 	    		Object x[] = (Object[])listSheet4.get(j);
 	    		
 	    		String[] survey = null;
 	    		if(!"".equals(Common.get(x[1]))){
 	    			survey = Common.get(x[1]).split(",");
 	 	    		for(int k=0;k< survey.length;k++)
 	 	    		{
 	 	    			sheet4.addCell(new Label(0,e,Common.get(x[0]),detailCenter));
 	 	    			if(Common.get(x[0]).length()*2 >= tmpMaxLength[0])			   
 	 	 	    		{			      
 	 	 	    			tmpMaxLength[0] = Common.get(x[0]).length()*2;			   
 	 	 	    		}
 	 	 	    		sheet4.addCell(new Label(1,e,drg0109Map.get(Common.get(survey[k])),detailCenter));			 	 
 	 	 	    		if(Common.get(survey[k]).length()*2 >= tmpMaxLength[1])				 
 	 	 	    		{				     
 	 	 	    			tmpMaxLength[1] = Common.get(survey[k]).length()*2;				 
 	 	 	    		} 	 	    		
 	 	    			e++; 	    			
 	 	    		}
 	    		} 	    		  	  
 	    	} 		  
 	    	//將最大欄位字數乘2   	      
 	    	for(int i=0; i<tmpMaxLength.length; i++)  		  
 	    	{   	    	 
 	    		sheet4.setColumnView(i, tmpMaxLength[i]*2); 		  
 	    	}	   
 	    }	
	}
	
	//廠商回覆-預防措施
	public void createDetailSheet5(WritableSheet sheet5,String title,java.util.Map<String,String> titleMap) throws Exception
	{		
		//設定字行
		WritableFont Font10 = new WritableFont(WritableFont.createFont("標楷體"),12);
		WritableCellFormat detailCenter = new  WritableCellFormat(Font10);
		
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
	 		sheet5.addCell(new Label(i+1,0,titleMap.get(value[i]),detailCenter));	 	  
	 		//放入表頭字數		  
	 		tmpMaxLength[i+1] = Common.get(titleMap.get(value[i])).length()*2;
	 	}
	 	
	 	String subHql5="  select a.applNo0001 ,a.precaution0006 ";
	 	       subHql5+=" from dynamicView01 a where 1=1 ";
	 	       subHql5+= condition();
	 	       subHql5+=" order by a.applNo0001 ";
  
        System.out.println("subHql5=="+subHql5);	          
 	  
 	    java.util.List listSheet5 = ServiceGetter.getInstance().getTcbwService().getNativeSqlQuery().load(subHql5); 	   
 	  
 	    if (listSheet5!=null && listSheet5.size()>0)  
 	    { 		  
 	    	int e=1; 		  
 	    	
 	    	java.util.Map<String, String> drg0110Map = TCBWCommon.getCommonCodeMap("DRG0110");
 	    	for (int j=0; j<listSheet5.size(); j++)  	  
 	    	{			   
 	    		Object x[] = (Object[])listSheet5.get(j);
 	    		
 	    		String[] precaution = null;
 	    		if(!"".equals(Common.get(x[1]))){
 	    			precaution = Common.get(x[1]).split(",");
 	 	    		for(int k=0;k< precaution.length;k++)
 	 	    		{
 	 	    			sheet5.addCell(new Label(0,e,Common.get(x[0]),detailCenter));
 	 	    			if(Common.get(x[0]).length()*2 >= tmpMaxLength[0])			   
 	 	 	    		{			      
 	 	 	    			tmpMaxLength[0] = Common.get(x[0]).length()*2;			   
 	 	 	    		}
 	 	 	    		sheet5.addCell(new Label(1,e,drg0110Map.get(Common.get(precaution[k])),detailCenter));			 	 
 	 	 	    		if(Common.get(precaution[k]).length()*2 >= tmpMaxLength[1])				 
 	 	 	    		{				     
 	 	 	    			tmpMaxLength[1] = Common.get(precaution[k]).length()*2;				 
 	 	 	    		} 	 	    		
 	 	    			e++; 	    			
 	 	    		}
 	    		} 	    		  	  
 	    	} 		  
 	    	//將最大欄位字數乘2   	      
 	    	for(int i=0; i<tmpMaxLength.length; i++)  		  
 	    	{   	    	 
 	    		sheet5.setColumnView(i, tmpMaxLength[i]*2); 		  
 	    	}	   
 	    }	
	}
	
	//案件評估區-擬辦事項
	public void createDetailSheet6(WritableSheet sheet6,String title,java.util.Map<String,String> titleMap) throws Exception
	{	
		//設定字行
		WritableFont Font10 = new WritableFont(WritableFont.createFont("標楷體"),12);
		WritableCellFormat detailCenter = new  WritableCellFormat(Font10);
		
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
	 		sheet6.addCell(new Label(i+1,0,titleMap.get(value[i]),detailCenter));	 	  
	 		//放入表頭字數		  
	 		tmpMaxLength[i+1] = Common.get(titleMap.get(value[i])).length()*2;
	 	}
	 	
	 	String subHql6="  select a.applNo0001 , a.dealWith0008 ";
	 	       subHql6+=" from dynamicView01 a where 1=1 ";
	 	       subHql6+= condition();
	 	       subHql6+=" order by a.applNo0001 ";
  
        System.out.println("subHql6=="+subHql6);	          
 	  
 	    java.util.List listSheet6 = ServiceGetter.getInstance().getTcbwService().getNativeSqlQuery().load(subHql6); 	   
 	  
 	    if (listSheet6!=null && listSheet6.size()>0)  
 	    { 		  
 	    	int e=1; 		  
 	    	
 	    	java.util.Map<String, String> drg0111Map = TCBWCommon.getCommonCodeMap("DRG0111");
 	    	for (int j=0; j<listSheet6.size(); j++)  	  
 	    	{			   
 	    		Object x[] = (Object[])listSheet6.get(j);
 	    		
 	    		String[] dealWith = null;
 	    		if(!"".equals(Common.get(x[1]))){
 	    			dealWith = Common.get(x[1]).split(",");
 	 	    		for(int k=0;k< dealWith.length;k++)
 	 	    		{
 	 	    			sheet6.addCell(new Label(0,e,Common.get(x[0]),detailCenter));
 	 	    			if(Common.get(x[0]).length()*2 >= tmpMaxLength[0])			   
 	 	 	    		{			      
 	 	 	    			tmpMaxLength[0] = Common.get(x[0]).length()*2;			   
 	 	 	    		}
 	 	 	    		sheet6.addCell(new Label(1,e,drg0111Map.get(Common.get(dealWith[k])),detailCenter));			 	 
 	 	 	    		if(Common.get(dealWith[k]).length()*2 >= tmpMaxLength[1])				 
 	 	 	    		{				     
 	 	 	    			tmpMaxLength[1] = Common.get(dealWith[k]).length()*2;				 
 	 	 	    		} 	 	    		
 	 	    			e++; 	    			
 	 	    		}
 	    		} 	    		  	  
 	    	} 		  
 	    	//將最大欄位字數乘2   	      
 	    	for(int i=0; i<tmpMaxLength.length; i++)  		  
 	    	{   	    	 
 	    		sheet6.setColumnView(i, tmpMaxLength[i]*2); 		  
 	    	}	   
 	    }	
	}
	
	//案件分析-不良品缺陷
	public void createDetailSheet7(WritableSheet sheet7,String title,java.util.Map<String,String> titleMap) throws Exception
	{		
		//設定字行
		WritableFont Font10 = new WritableFont(WritableFont.createFont("標楷體"),12);
		WritableCellFormat detailCenter = new  WritableCellFormat(Font10);
		
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
	 		sheet7.addCell(new Label(i+1,0,titleMap.get(value[i]),detailCenter));	 	  
	 		//放入表頭字數		  
	 		tmpMaxLength[i+1] = Common.get(titleMap.get(value[i])).length()*2;
	 	}
	 	
	 	String subHql7="  select a.applNo0001 , a.defect0009 ";
	 	       subHql7+=" from dynamicView01 a where 1=1 ";
	 	       subHql7+= condition();
	 	       subHql7+=" order by a.applNo0001 ";
  
        System.out.println("subHql7=="+subHql7);	          
 	  
 	    java.util.List listSheet7 = ServiceGetter.getInstance().getTcbwService().getNativeSqlQuery().load(subHql7); 	   
 	  
 	    if (listSheet7!=null && listSheet7.size()>0)  
 	    { 		  
 	    	int e=1; 		  
 	    	
 	    	java.util.Map<String, String> drg0108Map = TCBWCommon.getCommonCodeMap("DRG0108");
 	    	for (int j=0; j<listSheet7.size(); j++)  	  
 	    	{			   
 	    		Object x[] = (Object[])listSheet7.get(j);
 	    		
 	    		String[] defect = null;
 	    		if(!"".equals(Common.get(x[1]))){
 	    			defect = Common.get(x[1]).split(",");
 	 	    		for(int k=0;k< defect.length;k++)
 	 	    		{
 	 	    			sheet7.addCell(new Label(0,e,Common.get(x[0]),detailCenter));
 	 	    			if(Common.get(x[0]).length()*2 >= tmpMaxLength[0])			   
 	 	 	    		{			      
 	 	 	    			tmpMaxLength[0] = Common.get(x[0]).length()*2;			   
 	 	 	    		}
 	 	 	    		sheet7.addCell(new Label(1,e,drg0108Map.get(Common.get(defect[k])),detailCenter));			 	 
 	 	 	    		if(Common.get(defect[k]).length()*2 >= tmpMaxLength[1])				 
 	 	 	    		{				     
 	 	 	    			tmpMaxLength[1] = Common.get(defect[k]).length()*2;				 
 	 	 	    		} 	 	    		
 	 	    			e++; 	    			
 	 	    		}
 	    		} 	    		  	  
 	    	} 		  
 	    	//將最大欄位字數乘2   	      
 	    	for(int i=0; i<tmpMaxLength.length; i++)  		  
 	    	{   	    	 
 	    		sheet7.setColumnView(i, tmpMaxLength[i]*2); 		  
 	    	}	   
 	    }	
	}
	
	//案件分析-調查結果
	public void createDetailSheet8(WritableSheet sheet8,String title,java.util.Map<String,String> titleMap) throws Exception
	{		
		//設定字行
		WritableFont Font10 = new WritableFont(WritableFont.createFont("標楷體"),12);
		WritableCellFormat detailCenter = new  WritableCellFormat(Font10);
		
		String[] value=null;
	 	 
	 	if(title.length()>0) 
	 		value=title.split(",");
	 	
	 	//欄位大小暫存檔
		int[] tmpMaxLength = new int[value.length+1];	
		sheet8.addCell(new Label(0,0,"案件編號",detailCenter));
	 	//放入表頭字數
		tmpMaxLength[0] = Common.get("案件編號").length()*2;
	 	
	 	for(int i=0;i<value.length;i++) 
	 	{  
	 		sheet8.addCell(new Label(i+1,0,titleMap.get(value[i]),detailCenter));	 	  
	 		//放入表頭字數		  
	 		tmpMaxLength[i+1] = Common.get(titleMap.get(value[i])).length()*2;
	 	}

	 	String subHql8="  select a.applNo0001 ,a.survey0009 ";
	 	       subHql8+=" from dynamicView01 a where 1=1 ";
	 	       subHql8+= condition();
	 	       subHql8+=" order by a.applNo0001 ";
  
        System.out.println("subHql8=="+subHql8);	          
 	  
 	    java.util.List listSheet8 = ServiceGetter.getInstance().getTcbwService().getNativeSqlQuery().load(subHql8); 	   
 	  
 	    if (listSheet8!=null && listSheet8.size()>0)  
 	    { 		  
 	    	int e=1; 		  
 	    	
 	    	java.util.Map<String, String> drg0109Map = TCBWCommon.getCommonCodeMap("DRG0109");
 	    	for (int j=0; j<listSheet8.size(); j++)  	  
 	    	{			   
 	    		Object x[] = (Object[])listSheet8.get(j);
 	    		
 	    		String[] survey = null;
 	    		if(!"".equals(Common.get(x[1]))){
 	    			survey = Common.get(x[1]).split(",");
 	 	    		for(int k=0;k< survey.length;k++)
 	 	    		{
 	 	    			sheet8.addCell(new Label(0,e,Common.get(x[0]),detailCenter));
 	 	    			if(Common.get(x[0]).length()*2 >= tmpMaxLength[0])			   
 	 	 	    		{			      
 	 	 	    			tmpMaxLength[0] = Common.get(x[0]).length()*2;			   
 	 	 	    		}
 	 	 	    		sheet8.addCell(new Label(1,e,drg0109Map.get(Common.get(survey[k])),detailCenter));			 	 
 	 	 	    		if(Common.get(survey[k]).length()*2 >= tmpMaxLength[1])				 
 	 	 	    		{				     
 	 	 	    			tmpMaxLength[1] = Common.get(survey[k]).length()*2;				 
 	 	 	    		} 	 	    		
 	 	    			e++; 	    			
 	 	    		}
 	    		} 	    		  	  
 	    	} 		  
 	    	//將最大欄位字數乘2   	      
 	    	for(int i=0; i<tmpMaxLength.length; i++)  		  
 	    	{   	    	 
 	    		sheet8.setColumnView(i, tmpMaxLength[i]*2); 		  
 	    	}	   
 	    }	
	}
	
	//案件分析-預防措施
	public void createDetailSheet9(WritableSheet sheet9,String title,java.util.Map<String,String> titleMap) throws Exception
	{		
		//設定字行
		WritableFont Font10 = new WritableFont(WritableFont.createFont("標楷體"),12);
		WritableCellFormat detailCenter = new  WritableCellFormat(Font10);
		
		String[] value=null;
	 	 
	 	if(title.length()>0) 
	 		value=title.split(",");
	 	
	 	//欄位大小暫存檔
		int[] tmpMaxLength = new int[value.length+1];	
		sheet9.addCell(new Label(0,0,"案件編號",detailCenter));
	 	//放入表頭字數
		tmpMaxLength[0] = Common.get("案件編號").length()*2;
	 	
	 	for(int i=0;i<value.length;i++) 
	 	{  
	 		sheet9.addCell(new Label(i+1,0,titleMap.get(value[i]),detailCenter));	 	  
	 		//放入表頭字數		  
	 		tmpMaxLength[i+1] = Common.get(titleMap.get(value[i])).length()*2;
	 	}
	 	
	 	String subHql9="  select a.applNo0001 ,a.precaution0009 ";
	 	       subHql9+=" from dynamicView01 a where 1=1 ";
	 	       subHql9+= condition();
	 	       subHql9+=" order by a.applNo0001 ";
  
        System.out.println("subHql9=="+subHql9);	          
 	  
 	    java.util.List listSheet9 = ServiceGetter.getInstance().getTcbwService().getNativeSqlQuery().load(subHql9); 	   
 	  
 	    if (listSheet9!=null && listSheet9.size()>0)  
 	    { 		  
 	    	int e=1; 		  
 	    	
 	    	java.util.Map<String, String> drg0110Map = TCBWCommon.getCommonCodeMap("DRG0110");
 	    	for (int j=0; j<listSheet9.size(); j++)  	  
 	    	{			   
 	    		Object x[] = (Object[])listSheet9.get(j);
 	    		
 	    		String[] precaution = null;
 	    		if(!"".equals(Common.get(x[1]))){
 	    			precaution = Common.get(x[1]).split(",");
 	 	    		for(int k=0;k< precaution.length;k++)
 	 	    		{
 	 	    			sheet9.addCell(new Label(0,e,Common.get(x[0]),detailCenter));
 	 	    			if(Common.get(x[0]).length()*2 >= tmpMaxLength[0])			   
 	 	 	    		{			      
 	 	 	    			tmpMaxLength[0] = Common.get(x[0]).length()*2;			   
 	 	 	    		}
 	 	 	    		sheet9.addCell(new Label(1,e,drg0110Map.get(Common.get(precaution[k])),detailCenter));			 	 
 	 	 	    		if(Common.get(precaution[k]).length()*2 >= tmpMaxLength[1])				 
 	 	 	    		{				     
 	 	 	    			tmpMaxLength[1] = Common.get(precaution[k]).length()*2;				 
 	 	 	    		} 	 	    		
 	 	    			e++; 	    			
 	 	    		}
 	    		} 	    		  	  
 	    	} 		  
 	    	//將最大欄位字數乘2   	      
 	    	for(int i=0; i<tmpMaxLength.length; i++)  		  
 	    	{   	    	 
 	    		sheet9.setColumnView(i, tmpMaxLength[i]*2); 		  
 	    	}	   
 	    }	
	}
	
	//存檔
	public void doSaveOrUpdate() throws Exception
	{
		try
		{
          Variant0001Db obj = (Variant0001Db)View.getObject(" from Variant0001Db where name="+Common.sqlChar(variantName)+" and kind='drg01' ");
		
		  if(obj == null) 
		    obj = new Variant0001Db();
	
		  obj.setKind("drg01");
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
		DYNAMIC0101R obj = this;

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
		
        String hql=" from Export0001Db where code like 'drg01%' ";
		
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
        Variant0001Db c=(Variant0001Db)View.getObject("from Variant0001Db where kind='drg01' and name="+Common.sqlChar(getVariantName()));
		
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
	public String[] getManyCode() {return checkGet(manyCode);}
	public void setManyCode(String[] manyCode) {this.manyCode = checkSet(manyCode);}
	public String getApplNoS() {return checkGet(applNoS);}
	public void setApplNoS(String applNoS) {this.applNoS = checkSet(applNoS);}
	public String getApplNoE() {return checkGet(applNoE);}
	public void setApplNoE(String applNoE) {this.applNoE = checkSet(applNoE);}
	public String getHtmlValue() {return htmlValue;}
	public void setHtmlValue(String htmlValue) {this.htmlValue = htmlValue;}
	public String[] getStatus() {return checkGet(status);}
	public void setStatus(String[] status) {this.status = checkSet(status);}
	public String[] getNotifierType() {return checkGet(notifierType);}
	public void setNotifierType(String[] notifierType) {this.notifierType = checkSet(notifierType);}
	public String getEnrolledDateS() {return checkGet(enrolledDateS);}
	public void setEnrolledDateS(String enrolledDateS) {this.enrolledDateS = checkSet(enrolledDateS);}
	public String getEnrolledDateE() {return checkGet(enrolledDateE);}
	public void setEnrolledDateE(String enrolledDateE) {this.enrolledDateE = checkSet(enrolledDateE);}
	public String getNotifierRepDateS() {return checkGet(notifierRepDateS);}
	public void setNotifierRepDateS(String notifierRepDateS) {this.notifierRepDateS = checkSet(notifierRepDateS);}
	public String getNotifierRepDateE() {return checkGet(notifierRepDateE);}
	public void setNotifierRepDateE(String notifierRepDateE) {this.notifierRepDateE = checkSet(notifierRepDateE);}
	public String getOccurDateS() {return checkGet(occurDateS);}
	public void setOccurDateS(String occurDateS) {this.occurDateS = checkSet(occurDateS);}
	public String getOccurDateE() {return checkGet(occurDateE);}
	public void setOccurDateE(String occurDateE) {this.occurDateE = checkSet(occurDateE);}
	public String getNotifierDept1() {return checkGet(notifierDept1);}
	public void setNotifierDept1(String notifierDept1) {this.notifierDept1 = checkSet(notifierDept1);}
	public String getNotifierDept2() {return checkGet(notifierDept2);}
	public void setNotifierDept2(String notifierDept2) {this.notifierDept2 = checkSet(notifierDept2);}
	public String getNotifierDept3() {return checkGet(notifierDept3);}
	public void setNotifierDept3(String notifierDept3) {this.notifierDept3 = checkSet(notifierDept3);}
	public String getPermitKey() {return checkGet(permitKey);}
	public void setPermitKey(String permitKey) {this.permitKey = checkSet(permitKey);}
	public String getPermitNo() {return checkGet(permitNo);}
	public void setPermitNo(String permitNo) {this.permitNo = checkSet(permitNo);}
	public String getEnProduct() {return checkGet(enProduct);}
	public void setEnProduct(String enProduct) {this.enProduct = checkSet(enProduct);}
	public String getChProduct() {return checkGet(chProduct);}
	public void setChProduct(String chProduct) {this.chProduct = checkSet(chProduct);}
	public String getIngredient1() {return checkGet(ingredient1);}
	public void setIngredient1(String ingredient1) {this.ingredient1 = checkSet(ingredient1);}
	public String getIngredient2() {return checkGet(ingredient2);}
	public void setIngredient2(String ingredient2) {this.ingredient2 = checkSet(ingredient2);}
	public String getIngredient3() {return checkGet(ingredient3);}
	public void setIngredient3(String ingredient3) {this.ingredient3 = checkSet(ingredient3);}
	public String getMedModel() {return checkGet(medModel);}
	public void setMedModel(String medModel) {this.medModel = checkSet(medModel);}
	public String getApplicationName() {return checkGet(applicationName);}
	public void setApplicationName(String applicationName) {this.applicationName = checkSet(applicationName);}
	public String getManufactorName() {return checkGet(manufactorName);}
	public void setManufactorName(String manufactorName) {this.manufactorName = checkSet(manufactorName);}
	public String getManufactorCountry() {return checkGet(manufactorCountry);}
	public void setManufactorCountry(String manufactorCountry) {this.manufactorCountry = checkSet(manufactorCountry);}
	public String getManufactorNo() {return checkGet(manufactorNo);}
	public void setManufactorNo(String manufactorNo) {this.manufactorNo = checkSet(manufactorNo);}
	public String[] getMainCode() {return checkGet(mainCode);}
	public void setMainCode(String[] mainCode) {this.mainCode = checkSet(mainCode);}
	public String[] getFirstResult() {return checkGet(firstResult);}
	public void setFirstResult(String[] firstResult) {this.firstResult = checkSet(firstResult);}
	
	public String getIntervalDaysS() {return checkGet(intervalDaysS);}
	public void setIntervalDaysS(String intervalDaysS) {this.intervalDaysS = checkSet(intervalDaysS);}
	public String getIntervalDaysE() {return checkGet(intervalDaysE);}
	public void setIntervalDaysE(String intervalDaysE) {this.intervalDaysE = checkSet(intervalDaysE);}
	
	public String getBeforeOrLater() {return checkGet(beforeOrLater);}
	public void setBeforeOrLater(String beforeOrLater) {this.beforeOrLater = checkSet(beforeOrLater);}
	
	public String[] getDrgLevDrg0004() {return checkGet(drgLevDrg0004);}
	public void setDrgLevDrg0004(String[] drgLevDrg0004) {this.drgLevDrg0004 = checkSet(drgLevDrg0004);}

	
	
	public String[] getDrgLevDrg0008() {return checkGet(drgLevDrg0008);}

	public void setDrgLevDrg0008(String[] drgLevDrg0008) {this.drgLevDrg0008 = checkSet(drgLevDrg0008);}

	public String getMedicineType() {return checkGet(medicineType);}
	public void setMedicineType(String medicineType) {this.medicineType = checkSet(medicineType);}
	public String getProduceType() {return checkGet(produceType);}
	public void setProduceType(String produceType) {this.produceType = checkSet(produceType);}
	public String getLotType() {return checkGet(lotType);}
	public void setLotType(String lotType) {this.lotType = checkSet(lotType);}
	public String getDefect() {return checkGet(defect);}
	public void setDefect(String defect) {this.defect = checkSet(defect);}
	public String getSurvey() {return checkGet(survey);}
	public void setSurvey(String survey) {this.survey = checkSet(survey);}
	public String getPrecaution() {return checkGet(precaution);}
	public void setPrecaution(String precaution) {this.precaution = checkSet(precaution);}

	public String getCodeValue() {return checkGet(codeValue);}
	public void setCodeValue(String codeValue) {this.codeValue = checkSet(codeValue);}
	

	
}
