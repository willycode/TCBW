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



public class DYNAMIC0601R extends SuperBean
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
	private String q_notifierRepDateS;//通報中心接獲通報日期起
	private String q_notifierRepDateE;//通報中心接獲通報日期迄
	private String q_enrolledDateS;//收案日期起
	private String q_enrolledDateE;//收案日期迄
	private String q_occurDateS;//發生日期起
	private String q_occurDateE;//發生日期迄
	private String[] q_caseSource;//案例來源
	private String q_caseSourceOutCountry;//案例國別
	private String q_firmTestNo;//廠商試驗編號
	private String q_fdaNum;//臨床試驗主管機關核准函文號
	private String q_fdaOptions;//核准用途
	private String q_approvedUnits;//核准單位
	private String q_notifierDept1;//服務機構
	private String q_notifierDept2;//服務機構
	private String q_notifierDept3;//服務機構
	private String[] q_notifierType;//屬性
	
	private String[] q_badReactionResults;//不良反應結果
	private String q_position;//部位
	private String q_symptom;//症狀
	private String q_medPermit;//許可證字
	private String q_medPermitNumber;//許可證號
	private String q_medTestMedical;//試驗醫材名稱
	private String q_medMainCategoryCode;//醫材主類別
	private String q_medSecCategoryCode;//醫材次類別
	private String q_medFactory;//製造廠 
	
	private String q_medSupplier;//供應商
	private String q_medModel;//型號
	private String q_medNo;//序號
	private String q_medLotNum;//批號
	private String q_medSea;//試驗醫師評估醫材與SAE之因果關係
	private String q_procedureSea;//驗醫師評估手續程序與SAE之因果關係
	
	//查詢條件
	public String condition()
	{
		String condition="";
		
		//案件編號
		if(!"".equals(getQ_applNoS()))
			condition+=" and applNo2001 >="+Common.sqlChar(getQ_applNoS());
		if(!"".equals(getQ_applNoE()))
			condition+=" and applNo2001 <="+Common.sqlChar(getQ_applNoE());
		
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
			condition+=" and status2001 in ("+statusStr+")";
		
		//通報中心接獲通報日期
		if(!"".equals(getQ_notifierRepDateS()))
			condition+=" and notifierRepDate2001 >="+Common.sqlChar(getQ_notifierRepDateS());
		if(!"".equals(getQ_notifierRepDateE()))
			condition+=" and notifierRepDate2001 <="+Common.sqlChar(getQ_notifierRepDateE());
		
		
		//收案日期
		if(!"".equals(getQ_enrolledDateS()))
			condition+=" and enrolledDate2001 >="+Common.sqlChar(getQ_enrolledDateS());
		if(!"".equals(getQ_enrolledDateE()))
			condition+=" and enrolledDate2001 <="+Common.sqlChar(getQ_enrolledDateE());
		
		//發現日期
		if(!"".equals(getQ_occurDateS()))
			condition+=" and occurDate2001 >="+Common.sqlChar(getQ_occurDateS());
		if(!"".equals(getQ_occurDateE()))
			condition+=" and occurDate2001 <="+Common.sqlChar(getQ_occurDateE());
		
		//案例來源
		String caseSourceStr="";
		if(null!=getQ_caseSource() && getQ_caseSource().length>0)
		{
			for(int i=0;i<getQ_caseSource().length;i++)
			{
				if(caseSourceStr.length()>0)caseSourceStr+=",";
				caseSourceStr+="'"+getQ_caseSource()[i]+"'";
			}	
		}	
		
		if(caseSourceStr.length()>0)
			condition+=" and caseSource2001 in ("+caseSourceStr+")";
		
		
		//案例國別
		if(!"".equals(getQ_caseSourceOutCountry()))
			condition+=" and caseSourceOutCountry2001="+Common.sqlChar(getQ_caseSourceOutCountry());
		
		//廠商試驗編號
		if(!"".equals(getQ_firmTestNo()))
			condition+=" and firmTestNo2001="+Common.sqlChar(getQ_firmTestNo());
		
		//臨床試驗主管機關核准函文號
		if(!"".equals(getQ_fdaNum()))
			condition+=" and fdaNum2001="+Common.sqlChar(getQ_fdaNum());
		
		//核准用途
		if(!"".equals(getQ_fdaOptions()))
			condition+=" and fdaOptions2001="+Common.sqlChar(getQ_fdaOptions());
		
		//核准單位
		if(!"".equals(getQ_approvedUnits()))
			condition+=" and approvedUnits2001="+Common.sqlChar(getQ_approvedUnits());
		
		
		//服務機構
		if(!"".equals(getQ_notifierDept1()) || !"".equals(getQ_notifierDept2()) || !"".equals(getQ_notifierDept3()) )
		{
			String notifierDeptStr="";
			
			if(!"".equals(getQ_notifierDept1()))
				notifierDeptStr+=" notifierDept2001 like "+TCBWCommon.likeSqlChar(getQ_notifierDept1());
			
			if(notifierDeptStr.length()>0) notifierDeptStr+=" or ";
			
			if(!"".equals(getQ_notifierDept2()))
				notifierDeptStr+=" notifierDept2001 like "+TCBWCommon.likeSqlChar(getQ_notifierDept2());
			
			if(notifierDeptStr.length()>0) notifierDeptStr+=" or ";
			
			if(!"".equals(getQ_notifierDept3()))
				notifierDeptStr+=" notifierDept2001 like "+TCBWCommon.likeSqlChar(getQ_notifierDept3());
			
			condition+=" and (";
			condition+=notifierDeptStr;
			condition+=" )";
		}	
		
		//屬性
		String notifierTypeStr="";
		if(null!=getQ_notifierType() && getQ_notifierType().length>0)
		{
			for(int i=0;i<getQ_notifierType().length;i++)
			{
				if(notifierTypeStr.length()>0)notifierTypeStr+=",";
				notifierTypeStr+="'"+getQ_notifierType()[i]+"'";
			}	
		}	
		
		if(notifierTypeStr.length()>0)
			condition+=" and notifierType2001 in ("+notifierTypeStr+")";
		
		
		//不良反應結果
		String badReactionResultsStr="";
		if(null!=getQ_badReactionResults() && getQ_badReactionResults().length>0)
		{
			for(int i=0;i<getQ_badReactionResults().length;i++)
			{
				if(badReactionResultsStr.length()>0)badReactionResultsStr+=",";
				badReactionResultsStr+="'"+getQ_badReactionResults()[i]+"'";
			}	
		}	
		
		if(badReactionResultsStr.length()>0)
			condition+=" and badReactionResults2001 in ("+badReactionResultsStr+")";
		
		//部位
		if(!"".equals(getQ_position()))
			condition+=" and position2002 like "+TCBWCommon.likeSqlChar(getQ_position());
		
		//症狀
		if(!"".equals(getQ_symptom()))
			condition+=" and symptom2002  like"+TCBWCommon.likeSqlChar(getQ_symptom());
		
		
		//許可證字號
		if(!"".equals(getQ_medPermit()))
			condition+=" and medPermit2001="+Common.sqlChar(getQ_medPermit());
		if(!"".equals(getQ_medPermitNumber()))
			condition+=" and medPermitNumber2001="+Common.sqlChar(getQ_medPermitNumber());

		//試驗醫材名稱
		if(!"".equals(getQ_medTestMedical()))
			condition+=" and medTestMedical2001  like"+TCBWCommon.likeSqlChar(getQ_medTestMedical());
		
		//醫材主類別
		if(!"".equals(getQ_medMainCategoryCode()))
			condition+=" and medMainCategory2001="+Common.sqlChar(getQ_medMainCategoryCode());
		
		//醫材次類別
		if(!"".equals(getQ_medSecCategoryCode()))
			condition+=" and medSecCategory2001="+Common.sqlChar(getQ_medSecCategoryCode());
		
		//製造廠
		if(!"".equals(getQ_medFactory()))
			condition+=" and medFactory2001  like"+TCBWCommon.likeSqlChar(getQ_medFactory());
		
		//供應商
		if(!"".equals(getQ_medSupplier()))
			condition+=" and medSupplier2001  like"+TCBWCommon.likeSqlChar(getQ_medSupplier());
		
		//型號
		if(!"".equals(getQ_medModel()))
			condition+=" and medModel2001="+Common.sqlChar(getQ_medModel());
		
		//序號
		if(!"".equals(getQ_medNo()))
			condition+=" and medNo2001="+Common.sqlChar(getQ_medNo());
		
		//批號
		if(!"".equals(getQ_medLotNum()))
			condition+=" and medLotNum2001="+Common.sqlChar(getQ_medLotNum());
		
		//試驗醫師評估醫材與SAE之因果關係
		if(!"".equals(getQ_medSea()))
			condition+=" and medSea2001="+Common.sqlChar(getQ_medSea());
		
		
		//驗醫師評估手續程序與SAE之因果關係
		if(!"".equals(getQ_procedureSea()))
			condition+=" and procedureSea2001="+Common.sqlChar(getQ_procedureSea());
		
		

		return condition;
		
	}
	

		
	//匯出Xls
	public void exportXls(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
        //表頭中文
		java.util.Map<String,String>  titleMap=TCBWCommon.getMap("select field,fieldName from Export0002Db where export0001Db.code like 'med02%'");
		//多筆表頭中文
		java.util.Map<String,String>  manyTitleMap=TCBWCommon.getMap("select manyField,fieldName from Export0002Db where export0001Db.code like 'med02%' and isMany='Y' ");
		
		//全部代碼中文
		java.util.Map<String,String>  codeNameMap=TCBWCommon.getMap("select commonCodeKind.codeKindId+codeId,codeName from CommonCode");
		codeNameMap.put("YNY","是");codeNameMap.put("YNN","否");
		codeNameMap.put("NotifierType01","民眾");codeNameMap.put("NotifierType02","廠商");
		codeNameMap.put("NotifierType03","醫療人員");codeNameMap.put("NotifierType04","衛生單位");
		codeNameMap.put("CaseSourcein","國內");codeNameMap.put("CaseSourceout","國外");
		codeNameMap.put("FdaOptions1","查驗登記用");codeNameMap.put("FdaOptions2","學術研究用");
		codeNameMap.put("ApprovedUnits1","查醫事司登記用");codeNameMap.put("ApprovedUnits2","食品藥物管理署");
		codeNameMap.put("ApprovedUnits3","其他");
		
		//判斷是否有map
		java.util.Map<String,String>  isMap=TCBWCommon.getMap("select field,codeName from Export0002Db where export0001Db.code like 'med02%' and codeName is not null and codeName <> 'NULL' ");
		
		//判斷是否為多筆map
		java.util.Map<String,String>  manyNameMap=TCBWCommon.getMap("select field,manyName from Export0002Db where export0001Db.code like 'med02%' and isMany='Y' ");
		

		//
		java.util.Map<String,String>  fieldNameMap=TCBWCommon.getMap("select field,manyField from Export0002Db where export0001Db.code like 'med02%' and isMany='Y' ");
		
		
		
		//Excel檔案路徑
		File outputFile = new File(System.getProperty("java.io.tmpdir")+File.separator+"DYNAMIC0601R_"+Datetime.getHHMMSS()+ ".xls");
		//建立Excel檔案
		WritableWorkbook workbook = Workbook.createWorkbook(outputFile);
		
		//建立sheet1頁籤
		WritableSheet sheet1 = workbook.createSheet("醫療器材臨床試驗不良事件通報", 0);
		
		
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
                   hql+=" from dynamicView06 ";
                   hql+=" where 1=1 ";
                   hql+=condition();    
                   hql+=" order by applNo2001 ";
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
       			  
       			  if(null!=manyMap.get("Med2002DbSheet"))
       			  {
       				//建立sheet2頁籤 
       				WritableSheet sheet2=workbook.createSheet("案件通報-不良事件描述", 1);	
               	    createMed2002DbSheet(sheet2,manyMap.get("Med2002DbSheet"),manyTitleMap);
       			  }
       			  
       			  if(null!=manyMap.get("Med2003DbSheet"))
     			  {
     				//建立sheet3頁籤
     				WritableSheet sheet3=workbook.createSheet("案件通報-相關檢查", 2);	
             	    createMed2003DbSheet(sheet3,manyMap.get("Med2003DbSheet"),manyTitleMap);
     			  }
       			  
       			  if(null!=manyMap.get("Med2004DbSheet"))
   			      {
   				    //建立sheet4頁籤
   				    WritableSheet sheet4=workbook.createSheet("案件通報-併用醫材", 3);	
           	        createMed2004DbSheet(sheet4,manyMap.get("Med2004DbSheet"),manyTitleMap);
   			      }
       			
       			  if(null!=manyMap.get("Med2005DbSheet"))
 			      {
 				    //建立sheet5頁籤
 				    WritableSheet sheet5=workbook.createSheet("案件通報-併用藥品", 4);	
         	        createMed2005DbSheet(sheet5,manyMap.get("Med2005DbSheet"),manyTitleMap);
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
	
	//不良事件描述
	public void createMed2002DbSheet(WritableSheet sheet2,String title,java.util.Map<String,String> titleMap) throws Exception
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
		

 	   String hql0001=" (select id from dynamicView06 where 1=1 "+condition()+")";

 	   String med2002Hql="  select b.applNo,"+field;
 	          med2002Hql+=" from Med2002_Db a,Med2001_Db b";
 	          med2002Hql+=" where a.med2001_id=b.id ";
 	          med2002Hql+=" and a.med2001_id  in "+hql0001;
 	          med2002Hql+=" order by b.applNo ";       
 	          
 	   System.out.println("med2002Hql=="+med2002Hql);
 	          
 	  java.util.List list2002 = ServiceGetter.getInstance().getTcbwService().getNativeSqlQuery().load(med2002Hql);
 	   
 	  if (list2002!=null && list2002.size()>0) 			
	  {
 		  int e=1;
 		  for (int j=0; j<list2002.size(); j++) 
    	  {
			   Object x[] = (Object[])list2002.get(j);
			   sheet2.addCell(new Label(0,e,Common.get(x[0]),detailCenter));
			   
			   if(Common.get(x[0]).length()*2 >= tmpMaxLength[0])
			   {
			      tmpMaxLength[0] = Common.get(x[0]).length()*2; 
			   }
			   
			   for(int i=0;i<value.length;i++) 
			   {
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
	
	//相關檢查
	public void createMed2003DbSheet(WritableSheet sheet3,String title,java.util.Map<String,String> titleMap) throws Exception
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
		

 	   String hql0001=" (select id from dynamicView06 where 1=1 "+condition()+")";

 	   String med2003Hql="  select b.applNo,"+field;
 	          med2003Hql+=" from Med2003_Db a,Med2001_Db b";
 	          med2003Hql+=" where a.med2001_id=b.id ";
 	          med2003Hql+=" and a.med2001_id  in "+hql0001;
 	          med2003Hql+=" order by b.applNo ";       
 	          
 	   System.out.println("med2003Hql=="+med2003Hql);
 	          
 	  java.util.List list2003 = ServiceGetter.getInstance().getTcbwService().getNativeSqlQuery().load(med2003Hql);
 	   
 	  if (list2003!=null && list2003.size()>0) 			
	  {
 		  int e=1;
 		  for (int j=0; j<list2003.size(); j++) 
    	  {
			   Object x[] = (Object[])list2003.get(j);
			   sheet3.addCell(new Label(0,e,Common.get(x[0]),detailCenter));
			   
			   if(Common.get(x[0]).length()*2 >= tmpMaxLength[0])
			   {
			      tmpMaxLength[0] = Common.get(x[0]).length()*2; 
			   }
			   
			   for(int i=0;i<value.length;i++) 
			   {
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
	
	//相關檢查
	public void createMed2004DbSheet(WritableSheet sheet4,String title,java.util.Map<String,String> titleMap) throws Exception
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
		

 	   String hql0001=" (select id from dynamicView06 where 1=1 "+condition()+")";

 	   String med2004Hql="  select b.applNo,"+field;
 	          med2004Hql+=" from Med2004_Db a,Med2001_Db b";
 	          med2004Hql+=" where a.med2001_id=b.id ";
 	          med2004Hql+=" and a.med2001_id  in "+hql0001;
 	          med2004Hql+=" order by b.applNo ";       
 	          
 	   System.out.println("med2004Hql=="+med2004Hql);
 	          
 	  java.util.List list2004 = ServiceGetter.getInstance().getTcbwService().getNativeSqlQuery().load(med2004Hql);
 	   
 	  if (list2004!=null && list2004.size()>0) 			
	  {
 		  int e=1;
 		  
 		 java.util.Map<String, String> medpkidMap = TCBWCommon.getCommonCodeMap("MEDPKID");//許可證-字
 		  
 		  for (int j=0; j<list2004.size(); j++) 
    	  {
			   Object x[] = (Object[])list2004.get(j);
			   sheet4.addCell(new Label(0,e,Common.get(x[0]),detailCenter));
			   
			   if(Common.get(x[0]).length()*2 >= tmpMaxLength[0])
			   {
			      tmpMaxLength[0] = Common.get(x[0]).length()*2; 
			   }
			   
			   for(int i=0;i<value.length;i++) 
			   {
				 if("permit".equals(value[i]))
			 	   sheet4.addCell(new Label(i+1,e,medpkidMap.get(Common.get(x[i+1])),detailCenter));
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
	
	//相關檢查
	public void createMed2005DbSheet(WritableSheet sheet5,String title,java.util.Map<String,String> titleMap) throws Exception
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
		

 	   String hql0001=" (select id from dynamicView06 where 1=1 "+condition()+")";

 	   String med2005Hql="  select b.applNo,"+field;
 	          med2005Hql+=" from Med2005_Db a,Med2001_Db b";
 	          med2005Hql+=" where a.med2001_id=b.id ";
 	          med2005Hql+=" and a.med2001_id  in "+hql0001;
 	          med2005Hql+=" order by b.applNo ";       
 	          
 	   System.out.println("med2005Hql=="+med2005Hql);
 	          
 	  java.util.List list2005 = ServiceGetter.getInstance().getTcbwService().getNativeSqlQuery().load(med2005Hql);
 	   
 	  if (list2005!=null && list2005.size()>0) 			
	  {
 		  int e=1;
 		  
 		  java.util.Map<String, String> formulationMap = TCBWCommon.getCommonCodeMap("DRGFLN");//劑型
 		  java.util.Map<String, String> drgApproachMap  = TCBWCommon.getCommonCodeMap("DRG0304");//給藥途徑
 		  java.util.Map<String, String> doseMap  = TCBWCommon.getCommonCodeMap("DRG0305");//劑量
 		  java.util.Map<String, String> frequencyMap = TCBWCommon.getCommonCodeMap("DRG0306");//頻率
 		
 		  for (int j=0; j<list2005.size(); j++) 
    	  {
			   Object x[] = (Object[])list2005.get(j);
			   sheet5.addCell(new Label(0,e,Common.get(x[0]),detailCenter));
			   
			   if(Common.get(x[0]).length()*2 >= tmpMaxLength[0])
			   {
			      tmpMaxLength[0] = Common.get(x[0]).length()*2; 
			   }
			   
			   for(int i=0;i<value.length;i++) 
			   {
				 if("formulation".equals(value[i]))
			 	   sheet5.addCell(new Label(i+1,e,formulationMap.get(Common.get(x[i+1])),detailCenter));
				 else if("drgApproach".equals(value[i]))
				   sheet5.addCell(new Label(i+1,e,drgApproachMap.get(Common.get(x[i+1])),detailCenter));
				 else if("dose".equals(value[i]))
				   sheet5.addCell(new Label(i+1,e,doseMap.get(Common.get(x[i+1])),detailCenter));
				 else if("frequency".equals(value[i]))
				   sheet5.addCell(new Label(i+1,e,frequencyMap.get(Common.get(x[i+1])),detailCenter));
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
	

	
	//存檔
	public void doSaveOrUpdate() throws Exception
	{
		try
		{
          Variant0001Db obj = (Variant0001Db)View.getObject(" from Variant0001Db where name="+Common.sqlChar(variantName)+" and kind='med02' ");
		
		  if(obj == null) 
		    obj = new Variant0001Db();
	
		  obj.setKind("med02");
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
		DYNAMIC0601R obj = this;

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
		
        String hql=" from Export0001Db where code like 'med02%' ";
		
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

	public String getQ_notifierRepDateS() {
		return checkGet(q_notifierRepDateS);
	}

	public void setQ_notifierRepDateS(String qNotifierRepDateS) {
		q_notifierRepDateS = checkSet(qNotifierRepDateS);
	}

	public String getQ_notifierRepDateE() {
		return checkGet(q_notifierRepDateE);
	}

	public void setQ_notifierRepDateE(String qNotifierRepDateE) {
		q_notifierRepDateE = checkSet(qNotifierRepDateE);
	}

	public String getQ_enrolledDateS() {
		return checkGet(q_enrolledDateS);
	}

	public void setQ_enrolledDateS(String qEnrolledDateS) {
		q_enrolledDateS = checkSet(qEnrolledDateS);
	}

	public String getQ_enrolledDateE() {
		return checkGet(q_enrolledDateE);
	}

	public void setQ_enrolledDateE(String qEnrolledDateE) {
		q_enrolledDateE = checkSet(qEnrolledDateE);
	}

	public String getQ_occurDateS() {
		return checkGet(q_occurDateS);
	}

	public void setQ_occurDateS(String qOccurDateS) {
		q_occurDateS = checkSet(qOccurDateS);
	}

	public String getQ_occurDateE() {
		return checkGet(q_occurDateE);
	}

	public void setQ_occurDateE(String qOccurDateE) {
		q_occurDateE = checkSet(qOccurDateE);
	}

	public String[] getQ_caseSource() {
		return checkGet(q_caseSource);
	}

	public void setQ_caseSource(String[] qCaseSource) {
		q_caseSource = checkSet(qCaseSource);
	}

	public String getQ_caseSourceOutCountry() {
		return checkGet(q_caseSourceOutCountry);
	}

	public void setQ_caseSourceOutCountry(String qCaseSourceOutCountry) {
		q_caseSourceOutCountry = checkSet(qCaseSourceOutCountry);
	}

	public String getQ_firmTestNo() {
		return checkGet(q_firmTestNo);
	}

	public void setQ_firmTestNo(String qFirmTestNo) {
		q_firmTestNo = checkSet(qFirmTestNo);
	}

	public String getQ_fdaNum() {
		return checkGet(q_fdaNum);
	}

	public void setQ_fdaNum(String qFdaNum) {
		q_fdaNum = checkSet(qFdaNum);
	}

	public String getQ_fdaOptions() {
		return checkGet(q_fdaOptions);
	}

	public void setQ_fdaOptions(String qFdaOptions) {
		q_fdaOptions = checkSet(qFdaOptions);
	}

	public String getQ_approvedUnits() {
		return checkGet(q_approvedUnits);
	}

	public void setQ_approvedUnits(String qApprovedUnits) {
		q_approvedUnits = checkSet(qApprovedUnits);
	}

	public String getQ_notifierDept1() {
		return checkGet(q_notifierDept1);
	}

	public void setQ_notifierDept1(String qNotifierDept1) {
		q_notifierDept1 = checkSet(qNotifierDept1);
	}

	public String getQ_notifierDept2() {
		return checkGet(q_notifierDept2);
	}

	public void setQ_notifierDept2(String qNotifierDept2) {
		q_notifierDept2 = checkSet(qNotifierDept2);
	}

	public String getQ_notifierDept3() {
		return checkGet(q_notifierDept3);
	}

	public void setQ_notifierDept3(String qNotifierDept3) {
		q_notifierDept3 = checkSet(qNotifierDept3);
	}

	public String[] getQ_notifierType() {
		return checkGet(q_notifierType);
	}

	public void setQ_notifierType(String[] qNotifierType) {
		q_notifierType = checkSet(qNotifierType);
	}

	public String[] getQ_badReactionResults() {
		return checkGet(q_badReactionResults);
	}

	public void setQ_badReactionResults(String[] qBadReactionResults) {
		q_badReactionResults = checkSet(qBadReactionResults);
	}

	public String getQ_position() {
		return checkGet(q_position);
	}

	public void setQ_position(String qPosition) {
		q_position = checkSet(qPosition);
	}

	public String getQ_symptom() {
		return checkGet(q_symptom);
	}

	public void setQ_symptom(String qSymptom) {
		q_symptom = checkSet(qSymptom);
	}

	public String getQ_medPermit() {
		return checkGet(q_medPermit);
	}

	public void setQ_medPermit(String qMedPermit) {
		q_medPermit = checkSet(qMedPermit);
	}

	public String getQ_medPermitNumber() {
		return checkGet(q_medPermitNumber);
	}

	public void setQ_medPermitNumber(String qMedPermitNumber) {
		q_medPermitNumber = checkSet(qMedPermitNumber);
	}

	public String getQ_medMainCategoryCode() {
		return checkGet(q_medMainCategoryCode);
	}

	public void setQ_medMainCategoryCode(String qMedMainCategoryCode) {
		q_medMainCategoryCode = checkSet(qMedMainCategoryCode);
	}

	public String getQ_medSecCategoryCode() {
		return checkGet(q_medSecCategoryCode);
	}

	public void setQ_medSecCategoryCode(String qMedSecCategoryCode) {
		q_medSecCategoryCode = checkSet(qMedSecCategoryCode);
	}

	public String getQ_medFactory() {
		return checkGet(q_medFactory);
	}

	public void setQ_medFactory(String qMedFactory) {
		q_medFactory = checkSet(qMedFactory);
	}

	public String getQ_medSupplier() {
		return checkGet(q_medSupplier);
	}

	public void setQ_medSupplier(String qMedSupplier) {
		q_medSupplier = checkSet(qMedSupplier);
	}

	public String getQ_medModel() {
		return checkGet(q_medModel);
	}

	public void setQ_medModel(String qMedModel) {
		q_medModel = checkSet(qMedModel);
	}

	public String getQ_medNo() {
		return checkGet(q_medNo);
	}

	public void setQ_medNo(String qMedNo) {
		q_medNo = checkSet(qMedNo);
	}

	

	public String getQ_medTestMedical() {
		return checkGet(q_medTestMedical);
	}



	public void setQ_medTestMedical(String qMedTestMedical) {
		q_medTestMedical = checkSet(qMedTestMedical);
	}



	public String getQ_medLotNum() {
		return checkGet(q_medLotNum);
	}

	public void setQ_medLotNum(String qMedLotNum) {
		q_medLotNum = checkSet(qMedLotNum);
	}

	public String getQ_medSea() {
		return checkGet(q_medSea);
	}

	public void setQ_medSea(String qMedSea) {
		q_medSea = checkSet(qMedSea);
	}

	public String getQ_procedureSea() {
		return checkGet(q_procedureSea);
	}

	public void setQ_procedureSea(String qProcedureSea) {
		q_procedureSea = checkSet(qProcedureSea);
	}


	

	
	
   
	

}
