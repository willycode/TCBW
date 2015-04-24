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



public class DYNAMIC0201R extends SuperBean
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
	private String scientificName;//學名
	private String productName;//商品名
	private String applicationName;//申請商
	private String manufactorName;//製造廠
	private String manufactorNo;//批號
	private String medModel;//劑型

	private String[] conSequenceDrg4001;//通報事件後果
	private String[] conSequenceDrg4004;//通報事件後果
	private String medNti;//藥品成分-NTI Drugs
	private String medAtcCode;//藥品成分-藥理治療分類
	private String[] assessResult;//療效不等評估結果
	private String isCouncilYn;//是否提報諮議會
	private String intervalDaysS;//間隔天數
	private String intervalDaysE;//間隔天數
	
	private String notifyDateS;//發信日期
	private String notifyDateE;//發信日期
	
	//查詢條件
	public String condition()
	{
		String condition="";
		
		
		
		//案件編號
		if(!"".equals(getApplNoS()) && !"".equals(getApplNoE())) 
			condition += " and applNo4001 >= " +  Common.sqlChar(getApplNoS())+ " and applNo4001 <= " + Common.sqlChar(getApplNoE());
		else if(!"".equals(getApplNoS()) && "".equals(getApplNoE()))
			condition += " and applNo4001 like " + Common.sqlChar("%"+getApplNoS()+"%");
		else if("".equals(getApplNoS()) && !"".equals(getApplNoE()))
			condition += " and applNo4001 like " + Common.sqlChar("%"+getApplNoE()+"%");
		
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
			condition+=" and status4001 in ("+statusStr+")";
		
		
		//收案日期
		if(!"".equals(getEnrolledDateS()))
			condition+=" and enrolledDate4001 >="+Common.sqlChar(getEnrolledDateS());
		if(!"".equals(getEnrolledDateE()))
			condition+=" and enrolledDate4001 <="+Common.sqlChar(getEnrolledDateE());
		
		//通報中心接獲通報日期
		if(!"".equals(getNotifierRepDateS()))
			condition+=" and notifierRepDate4001 >="+Common.sqlChar(getNotifierRepDateS());
		if(!"".equals(getNotifierRepDateE()))
			condition+=" and notifierRepDate4001 <="+Common.sqlChar(getNotifierRepDateE());
		
		//發現日期
		if(!"".equals(getOccurDateS()))
			condition+=" and occurDate4001 >="+Common.sqlChar(getOccurDateS());
		if(!"".equals(getOccurDateE()))
			condition+=" and occurDate4001 <="+Common.sqlChar(getOccurDateE());
		
		//服務機構
		if(!"".equals(getNotifierDept1()) || !"".equals(getNotifierDept2()) || !"".equals(getNotifierDept3()) )
		{
			String notifierDeptStr="";
			
			if(!"".equals(getNotifierDept1()))
				notifierDeptStr+=" notifierDept4001 like "+TCBWCommon.likeSqlChar(getNotifierDept1());
			
			if(notifierDeptStr.length()>0) notifierDeptStr+=" or ";
			
			if(!"".equals(getNotifierDept2()))
				notifierDeptStr+=" notifierDept4001 like "+TCBWCommon.likeSqlChar(getNotifierDept2());
			
			if(notifierDeptStr.length()>0) notifierDeptStr+=" or ";
			
			if(!"".equals(getNotifierDept3()))
				notifierDeptStr+=" notifierDept4001 like "+TCBWCommon.likeSqlChar(getNotifierDept3());
			
			condition+=" and (";
			condition+=notifierDeptStr;
			condition+=" )";
		}	
		

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
			condition+=" and notifierType4001 in ("+notifierTypeStr+")";
		
		//許可證字號
		if(!"".equals(getPermitKey()))
			condition+=" and permitKey400302="+Common.sqlChar(getPermitKey());
		if(!"".equals(getPermitNo()))
			condition+=" and permitNo400302="+Common.sqlChar(getPermitNo());
		
		//學名
		if(!"".equals(getScientificName()))
			condition+=" and scientificName400302="+Common.sqlChar(getScientificName());
		
		//商品名
		if(!"".equals(getProductName()))
			condition+=" and productName400302="+Common.sqlChar(getProductName());
		
		//申請商
		if(!"".equals(getApplicationName()))
			condition+=" and applicationName400302="+Common.sqlChar(getApplicationName());
		
		//製造廠
		if(!"".equals(getManufactorName()))
			condition+=" and manufactorName400302="+Common.sqlChar(getManufactorName());
	
		//批號
		if(!"".equals(getManufactorNo()))
			condition+=" and manufactorName400302="+Common.sqlChar(getManufactorNo());
		
		//劑型
		if(!"".equals(getMedModel()))
			condition+=" and medModel400302="+Common.sqlChar(getMedModel());
	
		//通報事件後果
		String conSequenceDrg4001Str="";
		if(null!=getConSequenceDrg4001() && getConSequenceDrg4001().length>0)
		{
			for(int i=0;i<getConSequenceDrg4001().length;i++)
			{
				if(conSequenceDrg4001Str.length()>0)conSequenceDrg4001Str+=",";
				conSequenceDrg4001Str+="'"+getConSequenceDrg4001()[i]+"'";
			}	
		}	
		
		if(conSequenceDrg4001Str.length()>0)
			condition+=" and conSequence4001 in ("+conSequenceDrg4001Str+")";
		
		
		//藥品成分-NTI Drugs
		if(!"".equals(getMedNti()))
			condition+=" and medNti4004="+Common.sqlChar(getMedNti());
	
		//藥品成分-藥理治療分類
		if(!"".equals(getMedAtcCode()))
			condition+=" and medAtcCode4004="+Common.sqlChar(getMedAtcCode());
		
		//療效不等評估結果
		String assessResultStr="";
		if(null!=getAssessResult() && getAssessResult().length>0)
		{
			for(int i=0;i<getAssessResult().length;i++)
			{
				if(assessResultStr.length()>0)assessResultStr+=",";
				assessResultStr+="'"+getAssessResult()[i]+"'";
			}	
		}	
		
		if(assessResultStr.length()>0)
			condition+=" and assessResult4004 in ("+assessResultStr+")";
		
		//是否提報諮議會
		if(!"".equals(getIsCouncilYn()))
			condition+=" and isCouncilYn4004="+Common.sqlChar(getIsCouncilYn());
		
		//間隔天數
		if(!"".equals(getIntervalDaysS()))
			condition+=" and intervalDays4004 >="+Common.sqlChar(getIntervalDaysS());
		if(!"".equals(getIntervalDaysE()))
			condition+=" and intervalDays4004 <="+Common.sqlChar(getIntervalDaysE());
		
		
		//通報事件後果
		String conSequenceDrg4004Str="";
		if(null!=getConSequenceDrg4004() && getConSequenceDrg4004().length>0)
		{
			for(int i=0;i<getConSequenceDrg4004().length;i++)
			{
				if(conSequenceDrg4004Str.length()>0)conSequenceDrg4004Str+=",";
				conSequenceDrg4004Str+="'"+getConSequenceDrg4004()[i]+"'";
			}	
		}	
		
		if(conSequenceDrg4004Str.length()>0)
			condition+=" and conSequence4004 in ("+conSequenceDrg4004Str+")";
	
		
		if(!"".equals(getIntervalDaysS()))
			condition+=" and intervalDays4009 >="+Common.sqlChar(getIntervalDaysS());
		if(!"".equals(getIntervalDaysE()))
			condition+=" and intervalDays4009 <="+Common.sqlChar(getIntervalDaysE());
		
		
		return condition;
		
	}
	
	//匯出Xls
	public void exportXls(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
        //表頭中文
		java.util.Map<String,String>  titleMap=TCBWCommon.getMap("select field,fieldName from Export0002Db where export0001Db.code like 'drg02%'");
		//多筆表頭中文
		java.util.Map<String,String>  manyTitleMap=TCBWCommon.getMap("select manyField,fieldName from Export0002Db where export0001Db.code like 'drg02%' and isMany='Y' ");
		
		//全部代碼中文
		java.util.Map<String,String>  codeNameMap=TCBWCommon.getMap("select commonCodeKind.codeKindId+codeId,codeName from CommonCode");
		codeNameMap.put("YNY","是");codeNameMap.put("YNN","否");
		//通報事件後果
		codeNameMap.put("conSequence40011","藥效改變");
		codeNameMap.put("conSequence40012","不良反應發生");
		//性別
		codeNameMap.put("SEXM","男");codeNameMap.put("SEXF","女");
		//藥效改變狀況
		codeNameMap.put("effectChangeDesc1","增強");
		codeNameMap.put("effectChangeDesc2","減弱");
		//病人恢復原藥或轉換同成分藥品其症狀是否改善
		codeNameMap.put("isImproveYnY","是");codeNameMap.put("isImproveYnN","否");codeNameMap.put("isImproveYn0","未知");
		//通報時效
		codeNameMap.put("notifierAging1","時效佳");codeNameMap.put("notifierAging2","時效待加強");
		//通報品質
		codeNameMap.put("notifierQuality1","Excellent");codeNameMap.put("notifierQuality2","Good");codeNameMap.put("notifierQuality3","Fair");
		
		//判斷是否有map
		java.util.Map<String,String>  isMap=TCBWCommon.getMap("select field,codeName from Export0002Db where export0001Db.code like 'drg02%' and codeName is not null and codeName <> 'NULL' ");
		
		//判斷是否為多筆map
		java.util.Map<String,String>  manyNameMap=TCBWCommon.getMap("select field,manyName from Export0002Db where export0001Db.code like 'drg02%' and isMany='Y' ");
		
		//
		java.util.Map<String,String>  fieldNameMap=TCBWCommon.getMap("select field,manyField from Export0002Db where export0001Db.code like 'drg02%' and isMany='Y' ");
		
		//Excel檔案路徑
		File outputFile = new File(System.getProperty("java.io.tmpdir")+File.separator+"DYNAMIC0201R_"+Datetime.getHHMMSS()+ ".xls");
		//建立Excel檔案
		WritableWorkbook workbook = Workbook.createWorkbook(outputFile);
		//建立sheet1頁籤
		WritableSheet sheet1 = workbook.createSheet("藥品療效不等通報", 0);
	
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
                   hql+=" from dynamicView02 ";
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
       			  
       			  if(null!=manyMap.get("Drg4001DbSheet"))
       			  {
       				//建立sheet2頁籤 ===案件通報-通報事件後果
       				WritableSheet sheet2=workbook.createSheet("案件通報-通報事件後果", 1);	
               	    createDrg4001DbSheet(sheet2);
       			  }
       			  
       			  if(null!=manyMap.get("Drg4004DbSheet"))
     			  {
       				//建立sheet3頁籤===案件分級-通報事件後果
         		    WritableSheet sheet3=workbook.createSheet("案件分級-通報事件後果", 2);	
             	    createDrg4004DbSheet(sheet3);
     			  }
       			  
       			  if(null!=manyMap.get("Drg4002DbSheet"))
    			  {
            	    //建立sheet4頁籤
            		WritableSheet sheet4 = workbook.createSheet("相關檢查資料", 3);	
            	    //相關檢查資料
             	    createDrg4002DbSheet(sheet4,manyMap.get("Drg4002DbSheet"),manyTitleMap);
    			  }
       			  
       			  if(null!=manyMap.get("Drg4003DbSheet"))
  			      {
       				//建立sheet5頁籤
       				WritableSheet sheet5 = workbook.createSheet("其他併用藥品資料的各欄位", 4);	
          		    //其他併用藥品資料的各欄位
          		    createDrg4003DbSheet(sheet5,manyMap.get("Drg4003DbSheet"),manyTitleMap);
  			      }
       			  
       			  if(null!=manyMap.get("Drg4005DbSheet"))
			      {
     				//建立sheet6頁籤
       				WritableSheet sheet6 = workbook.createSheet("諮議會評估資料", 5);	
        		    //諮議會評估資料
        		    createDrg4005DbSheet(sheet6,manyMap.get("Drg4005DbSheet"),manyTitleMap);
			      }
       			  
       			  if(null!=manyMap.get("Drg4009DbSheet"))
			      {
    				//建立sheet7頁籤
       				WritableSheet sheet7 = workbook.createSheet("藥品療效不等醫院調查資料檔", 6);		
       		        //藥品療效不等醫院調查資料檔
       				createDrg4009DbSheet(sheet7,manyMap.get("Drg4009DbSheet"),manyTitleMap);
			      }
       			  
       			  if(null!=manyMap.get("Drg4006DbSheet"))
			      {
    				//建立sheet8頁籤
       				WritableSheet sheet8 = workbook.createSheet("廠商回覆", 7);		
       		        //廠商回覆	
       				createDrg4006DbSheet(sheet8,manyMap.get("Drg4006DbSheet"),manyTitleMap);
			      }
       			  
       			  if(null!=manyMap.get("Drg4007DbSheet"))
			      {
   				    //建立sheet9頁籤
       				WritableSheet sheet9 = workbook.createSheet("藥品療效不等品質調查資料檔	", 8);		
       				//藥品療效不等品質調查資料檔	
       				createDrg4007DbSheet(sheet9,manyMap.get("Drg4007DbSheet"),manyTitleMap);
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
	

	//案件通報-通報事件後果
	public void createDrg4001DbSheet(WritableSheet sheet2) throws Exception
	{

 	   String drg4001Hql="  select a.applNo4001,a.conSequence4001";
 	          drg4001Hql+=" from dynamicView02 a";
 	          drg4001Hql+=" where 1=1 ";
 	          drg4001Hql+= condition();
 	          drg4001Hql+=" order by a.applNo4001 ";
       
 	   java.util.List list4001 = ServiceGetter.getInstance().getTcbwService().getNativeSqlQuery().load(drg4001Hql);	   
 	    
 	   //設定字行	 
 	   WritableFont Font10 = new WritableFont(WritableFont.createFont("標楷體"),12);		
 	   WritableCellFormat detailCenter = new  WritableCellFormat(Font10);
 	   
 	   sheet2.addCell(new Label(0,0,"案件編號",detailCenter));
	   sheet2.addCell(new Label(1,0,"通報事件後果",detailCenter));	
	   sheet2.setColumnView(0,20);
	   sheet2.setColumnView(1,80);
	
 	   
 	   if (list4001!=null && list4001.size()>0) 			
	   {
 		  int e=1; 

 		  for (int j=0; j<list4001.size(); j++) 
    	  {
			   Object x[] = (Object[])list4001.get(j);
			     
			   String[] str=null;
			   //通報事件後果
			   if(!"".equals(Common.get(x[1])))
			   {
			       str=Common.get(x[1]).split(",");
			       for(int i=0;i<str.length;i++)
			       {
			        	sheet2.addCell(new Label(0,e,Common.get(x[0]),detailCenter));
			        	if("1".equals(str[i]))
			    		  sheet2.addCell(new Label(1,e,"藥效改變",detailCenter));
			        	else if("2".equals(str[i]))
				    	  sheet2.addCell(new Label(1,e,"不良反應發生",detailCenter));
			    		e++;
			       }	
			   }
			     
			  
			     
    	    }
	    }

	}
	
	//案件分級-通報事件後果
	public void createDrg4004DbSheet(WritableSheet sheet3) throws Exception
	{

 	   String hql0001=" (select applNo4001 from dynamicView02 where 1=1 "+condition()+")";

 	   String drg4004Hql="  select a.applNo,a.conSequence";
 	          drg4004Hql+=" from Drg4004_Db a";
 	          drg4004Hql+=" where a.applNo in "+hql0001;
 	          drg4004Hql+=" and a.id=(select max(b.id) from Drg4004_Db b where b.applNo=a.applNo )";
 	          drg4004Hql+=" order by a.applNo ";
 	   
 	   System.out.println("drg4004Hql=="+drg4004Hql);
 	          
 	   java.util.List list4004 = ServiceGetter.getInstance().getTcbwService().getNativeSqlQuery().load(drg4004Hql);
 	   
 		
 	   //設定字行	 
 	   WritableFont Font10 = new WritableFont(WritableFont.createFont("標楷體"),12);		
 	   WritableCellFormat detailCenter = new  WritableCellFormat(Font10);
 	   sheet3.addCell(new Label(0,0,"案件編號",detailCenter));
	   sheet3.addCell(new Label(1,0,"調查結果",detailCenter));	
	   sheet3.setColumnView(0,20);
	   sheet3.setColumnView(1,80);
		 
 	   
 	   if (list4004!=null && list4004.size()>0) 			
	   {
 		  int e=1; 
 		  
 

 		  for (int j=0; j<list4004.size(); j++) 
    	  {
			   Object x[] = (Object[])list4004.get(j);
			     
			   String[] str=null;
			   
			   //通報事件後果
			   if(!"".equals(Common.get(x[1])))
			   {
			      str=Common.get(x[1]).split(",");
			      for(int i=0;i<str.length;i++)
			      {
			    	sheet3.addCell(new Label(0,e,Common.get(x[0]),detailCenter));
			    	if("1".equals(str[i]))
			    		sheet3.addCell(new Label(1,e,"藥效改變",detailCenter));
			        else if("2".equals(str[i]))
				    	sheet3.addCell(new Label(1,e,"不良反應發生、強度增強或頻率增加",detailCenter));
			    	e++;
			      }	
			   }
			      
			  
			     
    	    }
	    }

	}
	
	//相關檢查資料
	public void createDrg4002DbSheet(WritableSheet sheet4,String title,java.util.Map<String,String> titleMap) throws Exception
	{

		String field="";
		String[] value=null;
	 	 
	 	if(title.length()>0) 
	 	   value=title.split(",");
	 	
	 	//欄位大小暫存檔
		int[] tmpMaxLength = new int[value.length+1];
	
		//設定字行	 
	 	WritableFont Font10 = new WritableFont(WritableFont.createFont("標楷體"),12);		
	 	WritableCellFormat detailCenter = new  WritableCellFormat(Font10);
	 	
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
		

 	   String hql0001=" (select id from dynamicView02 where 1=1 "+condition()+")";

 	   String drg4002Hql="  select b.applNo,"+field;
 	          drg4002Hql+=" from Drg4002_Db a,Drg4001_Db b";
 	          drg4002Hql+=" where a.drg4001_id=b.id ";
 	          drg4002Hql+=" and b.id  in "+hql0001;
 	          drg4002Hql+=" order by b.applNo ";
 	   
 	   System.out.println("drg4002Hql=="+drg4002Hql);
 	          
 	  java.util.List list4002 = ServiceGetter.getInstance().getTcbwService().getNativeSqlQuery().load(drg4002Hql);
 	   
 	  if (list4002!=null && list4002.size()>0) 			
	  {
 		  int e=1;
 		  for (int j=0; j<list4002.size(); j++) 
    	  {
			   Object x[] = (Object[])list4002.get(j);
			   sheet4.addCell(new Label(0,e,Common.get(x[0]),detailCenter));
			   
			   if(Common.get(x[0]).length()*2 >= tmpMaxLength[0])
			   {
			      tmpMaxLength[0] = Common.get(x[0]).length()*2; 
			   }
			   
			   for(int i=0;i<value.length;i++) 
			   {
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
	
	
	//其他併用藥品資料的各欄位
	public void createDrg4003DbSheet(WritableSheet sheet5,String title,java.util.Map<String,String> titleMap) throws Exception
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
		  tmpMaxLength[i+1] = Common.get(Common.get(titleMap.get(value[i]))).length()*2;
	 	}	  
		

 	   String hql0001=" (select id from dynamicView02 where 1=1 "+condition()+")";

 	   String drg4003Hql="  select b.applNo,"+field;
	          drg4003Hql+=" from Drg4003_Db a,Drg4001_Db b";
	          drg4003Hql+=" where a.drg4001_id=b.id and a.medType='03' ";
	          drg4003Hql+=" and a.drg4001_id  in "+hql0001;
	          drg4003Hql+=" order by b.applNo ";       
 	          
 	   System.out.println("drg4003Hql=="+drg4003Hql);
 	          
 	  java.util.List list4003 = ServiceGetter.getInstance().getTcbwService().getNativeSqlQuery().load(drg4003Hql);
 	   
 	  if (list4003!=null && list4003.size()>0) 			
	  {
 		  java.util.Map<String, String> pkidMap = TCBWCommon.getCommonCodeMap("DRGPKID"); //許可證字
 		  java.util.Map<String, String> flnMap = TCBWCommon.getCommonCodeMap("DRGFLN");   //劑型
 		  java.util.Map<String, String> drg0304Map = TCBWCommon.getCommonCodeMap("DRG0304");  //給藥途徑
 		  java.util.Map<String, String> drg0306Map = TCBWCommon.getCommonCodeMap("DRG0306");  //頻率

 		  int e=1;
 		  for (int j=0; j<list4003.size(); j++) 
    	  {
			   Object x[] = (Object[])list4003.get(j);
			   sheet5.addCell(new Label(0,e,Common.get(x[0]),detailCenter));
			   
			   if(Common.get(x[0]).length()*2 >= tmpMaxLength[0])
			   {
			      tmpMaxLength[0] = Common.get(x[0]).length()*2; 
			   }
			   
			   for(int i=0;i<value.length;i++) 
			   {
				   if("permitKey".equals(value[i]))					   
 				 	   sheet5.addCell(new Label(i+1,e,pkidMap.get(Common.get(x[i+1])),detailCenter));
				   else if ("medModel".equals(value[i]))
					   sheet5.addCell(new Label(i+1,e,flnMap.get(Common.get(x[i+1])),detailCenter));
				   else if ("medPath".equals(value[i]))
					   sheet5.addCell(new Label(i+1,e,drg0304Map.get(Common.get(x[i+1])),detailCenter));
				   else if ("frequency".equals(value[i]))
					   sheet5.addCell(new Label(i+1,e,drg0306Map.get(Common.get(x[i+1])),detailCenter));
				   else
					   sheet5.addCell(new Label(i+1,e,Common.get(x[i+1]),detailCenter));			 	 
			 	 
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
   	    	 sheet5.setColumnView(i, tmpMaxLength[i]*2);
 		  }
 		  
	   }

	}
	
	
	
	//諮議會評估資料
	public void createDrg4005DbSheet(WritableSheet sheet6,String title,java.util.Map<String,String> titleMap) throws Exception
	{

		//設定字行	 
	 	WritableFont Font10 = new WritableFont(WritableFont.createFont("標楷體"),12);		
	 	WritableCellFormat detailCenter = new  WritableCellFormat(Font10);
		
		String field="";
		String[] value=null;
	 	 
	 	if(title.length()>0) 
	 	   value=title.split(",");
	 	
	 	//欄位大小暫存檔
		int[] tmpMaxLength = new int[value.length+2];
	
		sheet6.addCell(new Label(0,0,"許可證字",detailCenter));
		sheet6.addCell(new Label(1,0,"許可證號",detailCenter));
		
	 	//放入表頭字數
		tmpMaxLength[0] = Common.get("許可證字").length()*2;
		tmpMaxLength[1] = Common.get("許可證號").length()*2;
		
		
	 	for(int i=0;i<value.length;i++) 
	 	{
	 	  if(field.length()>0)field+=",";

	 	  field+="c."+value[i];
	 	  sheet6.addCell(new Label(i+2,0,titleMap.get(value[i]),detailCenter));

	 	  //放入表頭字數
		  tmpMaxLength[i+2] = Common.get(Common.get(titleMap.get(value[i]))).length()*2;
	 	}	  
		

 	   String hql0001=" (select id from dynamicView02 where 1=1 "+condition()+")";

 	   String drg4005Hql="  select c.permitKey2,c.permitNo2, ";
              drg4005Hql+= field;
              drg4005Hql+=" from DRG4001_DB a,DRG4008_DB b,DRG4005_DB c";
              drg4005Hql+=" where a.id=b.DRG4001_ID";
              drg4005Hql+=" and b.DRG4005_ID=c.id";
              drg4005Hql+=" and b.id  in "+hql0001;
              drg4005Hql+=" group by c.permitKey2,c.permitNo2,c.assessDate,c.assessResult,c.assessDesc ";
 	   
 	   System.out.println("drg4005Hql=="+drg4005Hql);
 	          
 	  java.util.List list4005 = ServiceGetter.getInstance().getTcbwService().getNativeSqlQuery().load(drg4005Hql);
 	   
 	  if (list4005!=null && list4005.size()>0) 			
	  {
 		  int e=1;
 		  for (int j=0; j<list4005.size(); j++) 
    	  {
			   Object x[] = (Object[])list4005.get(j);
			   sheet6.addCell(new Label(0,e,Common.get(x[0]),detailCenter));
			   sheet6.addCell(new Label(1,e,Common.get(x[1]),detailCenter));
			   
			   if(Common.get(x[0]).length()*2 >= tmpMaxLength[0])
			   {
			      tmpMaxLength[0] = Common.get(x[0]).length()*2; 
			   }
			   
			   if(Common.get(x[1]).length()*2 >= tmpMaxLength[1])
			   {
			      tmpMaxLength[1] = Common.get(x[1]).length()*2; 
			   }
			   
			   for(int i=0;i<value.length;i++) 
			   {
			 	 sheet6.addCell(new Label(i+2,e,Common.get(x[i+2]),detailCenter));
			 	 
			 	 if(Common.get(x[i+2]).length() >= tmpMaxLength[i+2])
				 {
				    tmpMaxLength[i+2] = Common.get(x[i+2]).length(); 
				 }
			   }
			   
               e++;
    	  }
 		  
 		  //將最大欄位字數乘2
   	      for(int i=0; i<tmpMaxLength.length; i++)
  		  {
   	    	 
   	    	 sheet6.setColumnView(i, tmpMaxLength[i]*2);
 		  }
 		  
	   }

	}
	
	//藥品療效不等醫院調查資料檔
	public void createDrg4009DbSheet(WritableSheet sheet7,String title,java.util.Map<String,String> titleMap) throws Exception
	{

		//設定字行	 
	 	WritableFont Font10 = new WritableFont(WritableFont.createFont("標楷體"),12);		
	 	WritableCellFormat detailCenter = new  WritableCellFormat(Font10);
		
		String field="";
		String[] value=null;
	 	 
	 	if(title.length()>0) 
	 	   value=title.split(",");
	 	
	 	//欄位大小暫存檔
		int[] tmpMaxLength = new int[value.length+2];
	
		sheet7.addCell(new Label(0,0,"許可證字",detailCenter));
		sheet7.addCell(new Label(1,0,"許可證號",detailCenter));
		
	 	//放入表頭字數
		tmpMaxLength[0] = Common.get("許可證字").length()*2;
		tmpMaxLength[1] = Common.get("許可證號").length()*2;
		
		
	 	for(int i=0;i<value.length;i++) 
	 	{
	 	  if(field.length()>0)field+=",";

	 	  field+="d."+value[i];
	 	  sheet7.addCell(new Label(i+2,0,titleMap.get(value[i]),detailCenter));

	 	  //放入表頭字數
		  tmpMaxLength[i+2] = Common.get(Common.get(titleMap.get(value[i]))).length()*2;
	 	}	  
		

 	   String hql0001=" (select id from dynamicView02 where 1=1 "+condition()+")";

 	   String drg4009Hql="  select c.permitKey2, c.permitNo2,"+field;
              drg4009Hql+=" from DRG4001_DB a,DRG4008_DB b,DRG4005_DB c,DRG4009_DB d";
              drg4009Hql+=" where a.id=b.DRG4001_ID";
              drg4009Hql+=" and b.DRG4005_ID=c.id ";
              drg4009Hql+=" and c.id=d.DRG4005_ID ";
              drg4009Hql+=" and b.id  in "+hql0001;
              drg4009Hql+=" group by c.permitKey2,c.permitNo2,d.notifyDate,d.ingredient,d.replyDate,d.medAgencyName, ";
              drg4009Hql+=" d.isEffectChange,d.isBrandChange,d.noBrandChange,d.beforeBrand,d.afterBrand,d.comment ";
       
      System.out.println("drg4009Hql=="+drg4009Hql);
 	          
 	  java.util.List list4009 = ServiceGetter.getInstance().getTcbwService().getNativeSqlQuery().load(drg4009Hql);
 	   
 	  if (list4009!=null && list4009.size()>0) 			
	  {
 		  int e=1;
 		  for (int j=0; j<list4009.size(); j++) 
    	  {
			   Object x[] = (Object[])list4009.get(j);
			   sheet7.addCell(new Label(0,e,Common.get(x[0]),detailCenter));
			   sheet7.addCell(new Label(1,e,Common.get(x[1]),detailCenter));
			   
			   if(Common.get(x[0]).length()*2 >= tmpMaxLength[0])
			   {
			      tmpMaxLength[0] = Common.get(x[0]).length()*2; 
			   }
			   
			   if(Common.get(x[1]).length()*2 >= tmpMaxLength[1])
			   {
			      tmpMaxLength[1] = Common.get(x[1]).length()*2; 
			   }
			   
	
			   for(int i=0;i<value.length;i++) 
			   {
			 	 sheet7.addCell(new Label(i+2,e,Common.get(x[i+2]),detailCenter));
			 	 
			 	 if(Common.get(x[i+2]).length()*2 >= tmpMaxLength[i+2])
				 {
				    tmpMaxLength[i+2] = Common.get(x[i+2]).length()*2; 
				 }
			   }
			   
               e++;
    	  }
 		  
 		  //將最大欄位字數乘2
   	      for(int i=0; i<tmpMaxLength.length; i++)
  		  {
   	    	 sheet7.setColumnView(i, tmpMaxLength[i]*2);
 		  }
 		  
	   }

	}
	
	//廠商回覆		
	public void createDrg4006DbSheet(WritableSheet sheet8,String title,java.util.Map<String,String> titleMap) throws Exception
	{

		//設定字行	 
	 	WritableFont Font10 = new WritableFont(WritableFont.createFont("標楷體"),12);		
	 	WritableCellFormat detailCenter = new  WritableCellFormat(Font10);
		
		String field="";
		String[] value=null;
	 	 
	 	if(title.length()>0) 
	 	   value=title.split(",");
	 	
	 	//欄位大小暫存檔
		int[] tmpMaxLength = new int[value.length+2];
	
		sheet8.addCell(new Label(0,0,"許可證字",detailCenter));
		sheet8.addCell(new Label(1,0,"許可證號",detailCenter));
		
	 	//放入表頭字數
		tmpMaxLength[0] = Common.get("許可證字").length()*2;
		tmpMaxLength[1] = Common.get("許可證號").length()*2;
		
		
	 	for(int i=0;i<value.length;i++) 
	 	{
	 	  if(field.length()>0)field+=",";

	 	  field+="c."+value[i];
	 	  sheet8.addCell(new Label(i+2,0,titleMap.get(value[i]),detailCenter));

	 	  //放入表頭字數
		  tmpMaxLength[i+2] = Common.get(Common.get(titleMap.get(value[i]))).length()*2;
	 	}	  
		

 	   String hql0001=" (select id from dynamicView02 where 1=1 "+condition()+")";

       String drg4006Hql="  select c.permitKey2,c.permitNo2,"+field;
 	          drg4006Hql+=" from DRG4001_DB a,DRG4010_DB b,DRG4006_DB c";
 	          drg4006Hql+=" where a.id=b.DRG4001_ID ";
 	          drg4006Hql+=" and b.DRG4006_ID=c.id ";
 	          drg4006Hql+=" and b.id  in "+hql0001;
 	          drg4006Hql+=" group by c.permitKey2,c.permitNo2,c.replyDate,";
 	          drg4006Hql+=" c.Review1,c.Review2,c.Review3,c.Review4,c.Review5,c.Review6";
              
      System.out.println("drg4006Hql=="+drg4006Hql);
 	          
 	  java.util.List list4006 = ServiceGetter.getInstance().getTcbwService().getNativeSqlQuery().load(drg4006Hql);
 	   
 	  if (list4006!=null && list4006.size()>0) 			
	  {
 		  int e=1;
 		  for (int j=0; j<list4006.size(); j++) 
    	  {
			   Object x[] = (Object[])list4006.get(j);
			   sheet8.addCell(new Label(0,e,Common.get(x[0]),detailCenter));
			   sheet8.addCell(new Label(1,e,Common.get(x[1]),detailCenter));
			   
			   if(Common.get(x[0]).length()*2 >= tmpMaxLength[0])
			   {
			      tmpMaxLength[0] = Common.get(x[0]).length()*2; 
			   }
			   
			   if(Common.get(x[1]).length()*2 >= tmpMaxLength[1])
			   {
			      tmpMaxLength[1] = Common.get(x[1]).length()*2; 
			   }
			   
	
			   for(int i=0;i<value.length;i++) 
			   {
			 	 sheet8.addCell(new Label(i+2,e,Common.get(x[i+2]),detailCenter));
			 	 
			 	 if(Common.get(x[i+2]).length()*2 >= tmpMaxLength[i+2])
				 {
				    tmpMaxLength[i+2] = Common.get(x[i+2]).length()*2; 
				 }
			   }
			   
               e++;
    	  }
 		  
 		  //將最大欄位字數乘2
   	      for(int i=0; i<tmpMaxLength.length; i++)
  		  {
   	    	 sheet8.setColumnView(i, tmpMaxLength[i]*2);
 		  }
 		  
	   }

	}

	
	

	//藥品療效不等品質調查資料檔				
	public void createDrg4007DbSheet(WritableSheet sheet9,String title,java.util.Map<String,String> titleMap) throws Exception
	{
		//設定字行	 
	 	WritableFont Font10 = new WritableFont(WritableFont.createFont("標楷體"),12);		
	 	WritableCellFormat detailCenter = new  WritableCellFormat(Font10);
		
		String field="";
		String[] value=null;
	 	 
	 	if(title.length()>0) 
	 	   value=title.split(",");
	 	
	 	//欄位大小暫存檔
		int[] tmpMaxLength = new int[value.length+2];
	
		sheet9.addCell(new Label(0,0,"許可證字",detailCenter));
		sheet9.addCell(new Label(1,0,"許可證號",detailCenter));
		
	 	//放入表頭字數
		tmpMaxLength[0] = Common.get("許可證字").length()*2;
		tmpMaxLength[1] = Common.get("許可證號").length()*2;
		
		
	 	for(int i=0;i<value.length;i++) 
	 	{
	 	  if(field.length()>0)field+=",";

	 	  field+="c."+value[i];
	 	  sheet9.addCell(new Label(i+2,0,titleMap.get(value[i]),detailCenter));

	 	  //放入表頭字數
		  tmpMaxLength[i+2] = Common.get(Common.get(titleMap.get(value[i]))).length()*2;
	 	}	  
		

 	   String hql0001=" (select id from dynamicView02 where 1=1 "+condition()+")";
 
 	   String drg4007Hql="  select c.permitKey2,c.permitNo2,"+field;
	          drg4007Hql+=" from DRG4001_DB a,DRG4011_DB b,DRG4007_DB c";
	          drg4007Hql+=" where a.id=b.DRG4001_ID ";
	          drg4007Hql+=" and b.DRG4007_ID=c.id";
	          drg4007Hql+=" and b.id  in "+hql0001;
	          drg4007Hql+=" group by c.permitKey2,c.permitNo2,c.assessDate,c.assessDesc ";        
 	          
      System.out.println("drg4007Hql=="+drg4007Hql);
      
 	  java.util.List list4007 = ServiceGetter.getInstance().getTcbwService().getNativeSqlQuery().load(drg4007Hql);

 	  if (list4007!=null && list4007.size()>0) 			
	  {
 		  int e=1;
 		  for (int j=0; j<list4007.size(); j++) 
    	  {
			   Object x[] = (Object[])list4007.get(j);
			   sheet9.addCell(new Label(0,e,Common.get(x[0]),detailCenter));
			   sheet9.addCell(new Label(1,e,Common.get(x[1]),detailCenter));
			   
			   if(Common.get(x[0]).length()*2 >= tmpMaxLength[0])
			   {
			      tmpMaxLength[0] = Common.get(x[0]).length()*2; 
			   }
			   
			   if(Common.get(x[1]).length()*2 >= tmpMaxLength[1])
			   {
			      tmpMaxLength[1] = Common.get(x[1]).length()*2; 
			   }
			   
	
			   for(int i=0;i<value.length;i++) 
			   {
			 	 sheet9.addCell(new Label(i+2,e,Common.get(x[i+2]),detailCenter));
			 	 
			 	 if(Common.get(x[i+2]).length()*2 >= tmpMaxLength[i+2])
				 {
				    tmpMaxLength[i+2] = Common.get(x[i+2]).length()*2; 
				 }
			   }
			   
               e++;
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
          Variant0001Db obj = (Variant0001Db)View.getObject(" from Variant0001Db where name="+ Common.sqlChar(variantName)+" and kind='drg02' ");
		
		  if(obj == null) 
		    obj = new Variant0001Db();
	
		  obj.setKind("drg02");
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
		DYNAMIC0201R obj = this;

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
		
        String hql=" from Export0001Db where code like 'drg02%'";
		
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
	public String getMedModel() {return checkGet(medModel);}
	public void setMedModel(String medModel) {this.medModel = checkSet(medModel);}
	public String getApplicationName() {return checkGet(applicationName);}
	public void setApplicationName(String applicationName) {this.applicationName = checkSet(applicationName);}
	public String getManufactorName() {return checkGet(manufactorName);}
	public void setManufactorName(String manufactorName) {this.manufactorName = checkSet(manufactorName);}
	public String getManufactorNo() {return checkGet(manufactorNo);}
	public void setManufactorNo(String manufactorNo) {this.manufactorNo = checkSet(manufactorNo);}
	public String getScientificName() {return checkGet(scientificName);}
	public void setScientificName(String scientificName) {this.scientificName = checkSet(scientificName);}
	public String getProductName() {return checkGet(productName);}
	public void setProductName(String productName) {this.productName = checkSet(productName);}
	public String getCodeValue() {return checkGet(codeValue);}
	public void setCodeValue(String codeValue) {this.codeValue = checkSet(codeValue);}
	public String getMedNti() {return checkGet(medNti);}
	public void setMedNti(String medNti) {this.medNti = checkSet(medNti);}
	public String getMedAtcCode() {return checkGet(medAtcCode);}
	public void setMedAtcCode(String medAtcCode) {this.medAtcCode = checkSet(medAtcCode);}
	public String getIsCouncilYn() {return checkGet(isCouncilYn);}
	public void setIsCouncilYn(String isCouncilYn) {this.isCouncilYn = checkSet(isCouncilYn);}
	public String getIntervalDaysS() {return checkGet(intervalDaysS);}
	public void setIntervalDaysS(String intervalDaysS) {this.intervalDaysS = checkSet(intervalDaysS);}
	public String getIntervalDaysE() {return checkGet(intervalDaysE);}
	public void setIntervalDaysE(String intervalDaysE) {this.intervalDaysE = checkSet(intervalDaysE);}
	public String[] getConSequenceDrg4001() {return checkGet(conSequenceDrg4001);}
	public void setConSequenceDrg4001(String[] conSequenceDrg4001) {this.conSequenceDrg4001 = checkSet(conSequenceDrg4001);}
	public String[] getConSequenceDrg4004() {return checkGet(conSequenceDrg4004);}
	public void setConSequenceDrg4004(String[] conSequenceDrg4004) {this.conSequenceDrg4004 = checkSet(conSequenceDrg4004);}
	public String[] getAssessResult() {return checkGet(assessResult);}
	public void setAssessResult(String[] assessResult) {this.assessResult = checkSet(assessResult);}
	public String[] getManyCode() {return checkGet(manyCode);}
	public void setManyCode(String[] manyCode) {this.manyCode = checkSet(manyCode);}

	public String getNotifyDateS() {
		return checkGet(notifyDateS);
	}

	public void setNotifyDateS(String notifyDateS) {
		this.notifyDateS = checkSet(notifyDateS);
	}

	public String getNotifyDateE() {
		return checkGet(notifyDateE);
	}

	public void setNotifyDateE(String notifyDateE) {
		this.notifyDateE = checkSet(notifyDateE);
	}

	
	
   
	

}
