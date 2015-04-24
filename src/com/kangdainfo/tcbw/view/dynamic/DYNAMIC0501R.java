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



public class DYNAMIC0501R extends SuperBean
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
	private String q_notifierDept1;//服務機構
	private String q_notifierDept2;//服務機構
	private String q_notifierDept3;//服務機構
	private String[] q_notifierType;//屬性
	private String[] q_eventKind;//不良事件類別
	private String q_medPermit;//許可證字
	private String q_medPermitNumber;//許可證號
	private String q_medCname;//中文品名
	private String q_medPermitFirm;//許可證申請商
	private String q_medFactory;//製造廠
	
	private String q_medCountry;//製造廠國別
	private String q_medModel;//型號
	private String q_medMainCategoryCodel;//醫材主類別
	private String q_medSecCategoryCodel;//醫材次類別
	
	private String q_medNo;//序號
	private String q_medLotNum;//批號
	private String q_medSoftwareVersion;//軟體版本
	private String[] q_badReactionResults;//不良反應結果
	
	private String q_position;//部位
	private String q_symptom;//症狀
	private String[] q_productProblemKind1;//
	private String[] q_productProblemKind2;//
	private String[] q_productProblemKind3;//
	private String[] q_productProblemKind4;//
	private String q_bulletinQuality;//通報品質
	private String q_patientIssuesName;//醫材問題代碼
	private String q_medicalIssuesName;//病人問題代碼
	private String[] q_adverseReactionsResult;//不良反應相關性
	private String[] q_eventSeverity;//事件嚴重程度
	private String[] q_eventClass;//事件等級
	private String q_ncarResult;//NCAR通報結果
	

	//查詢條件
	public String condition()
	{
		String condition="";
		
		
		//案件編號
		if(!"".equals(getQ_applNoS()))
			condition+=" and applNo0001 >="+Common.sqlChar(getQ_applNoS());
		if(!"".equals(getQ_applNoE()))
			condition+=" and applNo0001 <="+Common.sqlChar(getQ_applNoE());
		
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
			condition+=" and status0001 in ("+statusStr+")";
		
		
		//收案日期
		if(!"".equals(getQ_enrolledDateS()))
			condition+=" and enrolledDate0001 >="+Common.sqlChar(getQ_enrolledDateS());
		if(!"".equals(getQ_enrolledDateE()))
			condition+=" and enrolledDate0001 <="+Common.sqlChar(getQ_enrolledDateE());
		
		//通報中心接獲通報日期
		if(!"".equals(getQ_notifierRepDateS()))
			condition+=" and notifierRepDate0001 >="+Common.sqlChar(getQ_notifierRepDateS());
		if(!"".equals(getQ_notifierRepDateE()))
			condition+=" and notifierRepDate0001 <="+Common.sqlChar(getQ_notifierRepDateE());
		
		
		//發現日期
		if(!"".equals(getQ_occurDateS()))
			condition+=" and occurDate0001 >="+Common.sqlChar(getQ_occurDateS());
		if(!"".equals(getQ_occurDateE()))
			condition+=" and occurDate0001 <="+Common.sqlChar(getQ_occurDateE());
		
		
			
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
			condition+=" and caseSource0001 in ("+caseSourceStr+")";
		
		
		//案例國別
		if(!"".equals(getQ_caseSourceOutCountry()))
			condition+=" and caseSourceOutCountry0001="+Common.sqlChar(getQ_caseSourceOutCountry());
		
		
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
			condition+=" and notifierType0001 in ("+notifierTypeStr+")";
		
		//服務機構
		if(!"".equals(getQ_notifierDept1()) || !"".equals(getQ_notifierDept2()) || !"".equals(getQ_notifierDept3()) )
		{
			String notifierDeptStr="";
			
			if(!"".equals(getQ_notifierDept1()))
				notifierDeptStr+=" notifierDept0001 like "+TCBWCommon.likeSqlChar(getQ_notifierDept1());
			
			if(notifierDeptStr.length()>0) notifierDeptStr+=" or ";
			
			if(!"".equals(getQ_notifierDept2()))
				notifierDeptStr+=" notifierDept0001 like "+TCBWCommon.likeSqlChar(getQ_notifierDept2());
			
			if(notifierDeptStr.length()>0) notifierDeptStr+=" or ";
			
			if(!"".equals(getQ_notifierDept3()))
				notifierDeptStr+=" notifierDept0001 like "+TCBWCommon.likeSqlChar(getQ_notifierDept3());
			
			condition+=" and (";
			condition+=notifierDeptStr;
			condition+=" )";
		}	
		
		//不良事件類別
		String eventKindStr="";
		if(null!=getQ_eventKind() && getQ_eventKind().length>0)
		{
			for(int i=0;i<getQ_eventKind().length;i++)
			{
				if(eventKindStr.length()>0)eventKindStr+=",";
				eventKindStr+="'"+getQ_eventKind()[i]+"'";
			}	
		}	
		
		if(eventKindStr.length()>0)
			condition+=" and eventKind0001 in ("+eventKindStr+")";
		

		//許可證字號
		if(!"".equals(getQ_medPermit()))
			condition+=" and medPermit0001="+Common.sqlChar(getQ_medPermit());
		if(!"".equals(getQ_medPermitNumber()))
			condition+=" and medPermitNumber0001="+Common.sqlChar(getQ_medPermitNumber());
		
		//中文品名
		if(!"".equals(getQ_medCname()))
			condition+=" and medCname0001 like "+TCBWCommon.likeSqlChar(getQ_medCname());
		
		//許可證申請商
		if(!"".equals(getQ_medPermitFirm()))
			condition+=" and medPermitFirm0001 like "+TCBWCommon.likeSqlChar(getQ_medCname());
		//製造廠
		if(!"".equals(getQ_medFactory()))
			condition+=" and medFactory0001 like "+TCBWCommon.likeSqlChar(getQ_medFactory());
		
		//製造廠國別
		if(!"".equals(getQ_medCountry()))
			condition+=" and medCountry0001 like "+TCBWCommon.likeSqlChar(getQ_medCountry());
		
		//型號
		if(!"".equals(getQ_medModel()))
			condition+=" and medModel0001="+Common.sqlChar(getQ_medModel());
		
		//醫材主類別
		if(!"".equals(getQ_medMainCategoryCodel()))
			condition+=" and medMainCategoryCode0001="+Common.sqlChar(getQ_medMainCategoryCodel());
		
		//醫材次類別
		if(!"".equals(getQ_medSecCategoryCodel()))
			condition+=" and medSecCategoryCode0001="+Common.sqlChar(getQ_medSecCategoryCodel());
	
		//序號
		if(!"".equals(getQ_medNo()))
			condition+=" and medNo0001="+Common.sqlChar(getQ_medNo());
		
		//批號
		if(!"".equals(getQ_medLotNum()))
			condition+=" and medLotNum0001="+Common.sqlChar(getQ_medLotNum());
		
		//軟體版本
		if(!"".equals(getQ_medSoftwareVersion()))
			condition+=" and medLotNum0001="+Common.sqlChar(getQ_medSoftwareVersion());
		
		//不良反應結果 q_badReactionResults
		String badReactionResultsStr="";
		if(null!=getQ_badReactionResults() && getQ_badReactionResults().length>0)
		{
			for(int i=0;i<getQ_badReactionResults().length;i++)
			{
				if(badReactionResultsStr.length()>0)badReactionResultsStr+=" or ";
				badReactionResultsStr+="badReactionResults0001 like "+TCBWCommon.likeSqlChar(getQ_badReactionResults()[i]);
			}	
		}	
		
		if(badReactionResultsStr.length()>0)
			condition+=" and ("+badReactionResultsStr+")";
		
		//部位
		if(!"".equals(getQ_position()))
			condition+=" and position0002 like "+TCBWCommon.likeSqlChar(getQ_position());
		
		//症狀
		if(!"".equals(getQ_symptom()))
			condition+=" and symptom0002  like"+TCBWCommon.likeSqlChar(getQ_symptom());
		
		
		//產品問題分類
		//器材操作q_productProblemKind1
		String productProblemKind1Str="";
		if(null!=getQ_productProblemKind1() && getQ_productProblemKind1().length>0)
		{
			for(int i=0;i<getQ_productProblemKind1().length;i++)
			{
				if(productProblemKind1Str.length()>0)productProblemKind1Str+=" or ";
				productProblemKind1Str+="productProblemKind10001 like "+TCBWCommon.likeSqlChar(getQ_productProblemKind1()[i]);
			}	
		}	
		if(productProblemKind1Str.length()>0)
			condition+=" and ("+productProblemKind1Str+")";
		
		//環境設施
		String productProblemKind2Str="";
		if(null!=getQ_productProblemKind2() && getQ_productProblemKind2().length>0)
		{
			for(int i=0;i<getQ_productProblemKind2().length;i++)
			{
				if(productProblemKind2Str.length()>0)productProblemKind2Str+=" or ";
				productProblemKind2Str+="productProblemKind20001 like "+TCBWCommon.likeSqlChar(getQ_productProblemKind2()[i]);
			}	
		}	
		if(productProblemKind2Str.length()>0)
			condition+=" and ("+productProblemKind2Str+")";
		
		//人因
		String productProblemKind3Str="";
		if(null!=getQ_productProblemKind3() && getQ_productProblemKind3().length>0)
		{
			for(int i=0;i<getQ_productProblemKind3().length;i++)
			{
				if(productProblemKind3Str.length()>0)productProblemKind3Str+=" or ";
				productProblemKind3Str+="productProblemKind30001 like "+TCBWCommon.likeSqlChar(getQ_productProblemKind3()[i]);
			}	
		}	
		if(productProblemKind3Str.length()>0)
			condition+=" and ("+productProblemKind3Str+")";
		
		//物理特性
		String productProblemKind4Str="";
		if(null!=getQ_productProblemKind4() && getQ_productProblemKind4().length>0)
		{
			for(int i=0;i<getQ_productProblemKind4().length;i++)
			{
				if(productProblemKind4Str.length()>0)productProblemKind3Str+=" or ";
				productProblemKind4Str+="productProblemKind40001 like "+TCBWCommon.likeSqlChar(getQ_productProblemKind4()[i]);
			}	
		}	
		if(productProblemKind4Str.length()>0)
			condition+=" and ("+productProblemKind4Str+")";
		
		
		//通報品質q_bulletinQuality
		if(!"".equals(getQ_bulletinQuality()))
			condition+=" and bulletinQuality="+Common.sqlChar(getQ_bulletinQuality());
		
	
		//病人問題q_medicalIssuesName
		if(!"".equals(getQ_medicalIssuesName()))
		{	
		   List med1005code = getMed1005Code(getQ_medicalIssuesName());//輸入的查詢條件先到設定檔查詢
		   if(null != med1005code && med1005code.size() > 0) 
		   {
			  condition += " and (";
			  for(int i = 0; i < med1005code.size(); i++) 
			  {
				Object code = med1005code.get(i);
				condition += " medicalIssues like " + Common.likeSqlChar(Common.get(code));
				
				if(i != med1005code.size() - 1)
				{
					condition += " or";
				}
			  }
			  condition += " )";
		   }
		   else
		   {
			  condition += "and 1=2 ";
		   }	   
		}
	
			
		//醫材問題q_patientIssuesName
        if(getQ_patientIssuesName() != null && !"".equals(getQ_patientIssuesName()))
        {
			List med1006code = getMed1006Code(getQ_patientIssuesName());//輸入的查詢條件先到設定檔查詢
			if(null != med1006code && med1006code.size() > 0) 
			{
				condition += " and (";
				for(int i = 0; i < med1006code.size(); i++) 
				{
					Object code = med1006code.get(i);
					condition += " patientIssues like " + Common.likeSqlChar(Common.get(code));
					if(i != med1006code.size() - 1) 
					{
						condition += " or";
					}
				}
			} 
			else 
			{
				condition += " and (1=2";
			}
			condition += ")";
		}
		
		
		//不良反應相關性q_adverseReactionsResult
		String adverseReactionsResultStr="";
		if(null!=getQ_adverseReactionsResult() && getQ_adverseReactionsResult().length>0)
		{
			for(int i=0;i<getQ_adverseReactionsResult().length;i++)
			{
				if(adverseReactionsResultStr.length()>0)adverseReactionsResultStr+=",";
				adverseReactionsResultStr+="'"+getQ_adverseReactionsResult()[i]+"'";
			}	
		}	
		if(adverseReactionsResultStr.length()>0)
			condition+=" and adverseReactionsResult0007 in ("+adverseReactionsResultStr+")";
		
		//事件嚴重程度q_eventSeverity
		String eventSeverityStr="";
		if(null!=getQ_eventSeverity() && getQ_eventSeverity().length>0)
		{
			for(int i=0;i<getQ_eventSeverity().length;i++)
			{
				if(eventSeverityStr.length()>0)eventSeverityStr+=",";
				eventSeverityStr+="'"+getQ_eventSeverity()[i]+"'";
			}	
		}	
		if(eventSeverityStr.length()>0)
			condition+=" and eventSeverity0007  in ("+eventSeverityStr+")";
		
		//NCAR通報結果q_ncarResult
		if(!"".equals(getQ_ncarResult()))
			condition+=" and ncarResult="+Common.sqlChar(getQ_ncarResult());
		
		
		
		//事件等級q_eventClass
		String eventClassStr="";
		if(null!=getQ_eventClass() && getQ_eventClass().length>0)
		{
			for(int i=0;i<getQ_eventClass().length;i++)
			{
				if(eventClassStr.length()>0)eventClassStr+=",";
				eventClassStr+="'"+getQ_eventClass()[i]+"'";
			}	
		}	
		if(eventClassStr.length()>0)
			condition+=" and eventClass0001 in ("+eventClassStr+")";
		
		return condition;
		
	}
	
	//取得醫材問題代碼code
	private List getMed1006Code(String name) 
	{
		List code = null;
		String sql = "select med1006.code from Med1006Db med1006 where med1006.name like " + Common.likeSqlChar(name);
		code = ServiceGetter.getInstance().getTcbwService().load(sql);
		return code;
	}
	
	//取得病人問題代碼code
	private List getMed1005Code(String name) {
		List code = null;
		String sql = "select med1005.code from Med1005Db med1005 where med1005.name like " + Common.likeSqlChar(name);
		code = ServiceGetter.getInstance().getTcbwService().load(sql);
		return code;
	}
		
	//匯出Xls
	public void exportXls(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
        //表頭中文
		java.util.Map<String,String>  titleMap=TCBWCommon.getMap("select field,fieldName from Export0002Db where export0001Db.code like 'med01%'");
		//多筆表頭中文
		java.util.Map<String,String>  manyTitleMap=TCBWCommon.getMap("select manyField,fieldName from Export0002Db where export0001Db.code like 'med01%' and isMany='Y' ");
		
		//全部代碼中文
		java.util.Map<String,String>  codeNameMap=TCBWCommon.getMap("select commonCodeKind.codeKindId+codeId,codeName from CommonCode");
		codeNameMap.put("YNY","是");codeNameMap.put("YNN","否");
		codeNameMap.put("EventsSources1","由醫療人員轉知");codeNameMap.put("EventsSources2","衛生單位得知");
		codeNameMap.put("EventsSources3","廠商");codeNameMap.put("EventsSources4","由民眾主動告知");
		codeNameMap.put("NotifierType01","民眾");codeNameMap.put("NotifierType02","廠商");
		codeNameMap.put("NotifierType03","醫療人員");codeNameMap.put("NotifierType04","衛生單位");
		codeNameMap.put("EventKind1","不良反應");codeNameMap.put("EventKind2","不良品");
		codeNameMap.put("AdverseReactionsResult1","≦2分：存疑");codeNameMap.put("AdverseReactionsResult2","3-5分：可能相關");
		codeNameMap.put("AdverseReactionsResult3","6-8分：極有可能相關");codeNameMap.put("AdverseReactionsResult4","≧9：確定相關");
		
		codeNameMap.put("EventSeveritymild","輕度 (無需治療、停止使用器材或技術即可)");codeNameMap.put("EventSeveritymoderate","中度 (需治療、導致住院或延長住院時間至少一天以上)");
		codeNameMap.put("EventSeverityserious","重度 (導致死亡、危及生命、需加護病房治療或需七天以上才能復原、造成永久性殘疾或先天性畸形)");codeNameMap.put("EventSeverityunable","無法評估");
		codeNameMap.put("NarResult1","通報NCAR");codeNameMap.put("NarResult2","不通報NCAR");
		codeNameMap.put("CommentOpinion1","先行結案，並持續受理通報。需注意觀察後續類似通報情形");codeNameMap.put("CommentOpinion2","應聯繫原始通報者，取得事件詳細說明。");
		codeNameMap.put("CommentOpinion3","應聯繫該醫材許可證持有商，取得事件詳細說明。");
		
		//判斷是否有map
		java.util.Map<String,String>  isMap=TCBWCommon.getMap("select field,codeName from Export0002Db where export0001Db.code like 'med01%' and codeName is not null and codeName <> 'NULL' ");
		
		//判斷是否為多筆map
		java.util.Map<String,String>  manyNameMap=TCBWCommon.getMap("select field,manyName from Export0002Db where export0001Db.code like 'med01%' and isMany='Y' ");
		
		//
		java.util.Map<String,String>  fieldNameMap=TCBWCommon.getMap("select field,manyField from Export0002Db where export0001Db.code like 'med01%' and isMany='Y' ");
		
		
		
		//Excel檔案路徑
		File outputFile = new File(System.getProperty("java.io.tmpdir")+File.separator+"DYNAMIC0501R_"+Datetime.getHHMMSS()+ ".xls");
		//建立Excel檔案
		WritableWorkbook workbook = Workbook.createWorkbook(outputFile);
		
		//建立sheet1頁籤
		WritableSheet sheet1 = workbook.createSheet("醫療器材不良事件通報", 0);
		
		
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
                   hql+=" from dynamicView05 ";
                   hql+=" where 1=1 ";
                   hql+=condition();  
                   hql+=" order by applNo0001 ";
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
						  
						  if("productProblemKind10001".equals(getCode()[y])||
							 "productProblemKind20001".equals(getCode()[y])||
							 "productProblemKind30001".equals(getCode()[y])||
							 "productProblemKind40001".equals(getCode()[y])  
						     )
						  {
							  productProblemKind="Y";//判斷是否有子頁籤
	
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
       			  
       			  if(null!=manyMap.get("Med0002DbSheet"))
       			  {
       				//建立sheet2頁籤 
       				WritableSheet sheet2=workbook.createSheet("案件通報-不良事件描述", 1);	
               	    createMed0002DbSheet(sheet2,manyMap.get("Med0002DbSheet"),manyTitleMap);
       			  }
       			  
       			  if(null!=manyMap.get("Med0003DbSheet"))
     			  {
     				//建立sheet3頁籤
     				WritableSheet sheet3=workbook.createSheet("案件通報-相關檢查", 2);	
             	    createMed0003DbSheet(sheet3,manyMap.get("Med0003DbSheet"),manyTitleMap);
     			  }
       			  
       			  if(null!=manyMap.get("Med0004DbSheet"))
   			      {
   				    //建立sheet4頁籤
   				    WritableSheet sheet4=workbook.createSheet("案件通報-併用醫材", 3);	
           	        createMed0004DbSheet(sheet4,manyMap.get("Med0004DbSheet"),manyTitleMap);
   			      }
       			
       			  if(null!=manyMap.get("Med0005DbSheet"))
 			      {
 				    //建立sheet5頁籤
 				    WritableSheet sheet5=workbook.createSheet("案件通報-併用藥品", 4);	
         	        createMed0005DbSheet(sheet5,manyMap.get("Med0005DbSheet"),manyTitleMap);
 			      }
       		   }
        	   
    		   //建立sheet6頁籤
    		   if("Y".equals(productProblemKind))
    		   {	  
				   WritableSheet sheet6=workbook.createSheet("案件通報-產品問題分類",5);	
      	           createProductProblemKindSheet(sheet6);
    		   }
    		   
    		   
 
    		   //建立sheet7頁籤
    		   if(null!=manyMap.get("patientIssuesSheet"))
    		   {	  
				   WritableSheet sheet7=workbook.createSheet("案件通報-醫材問題",6);	
				   patientIssuesSheet(sheet7);
    		   }
    		   
    		   //建立shee8頁籤
    		   if(null!=manyMap.get("medicalIssuesSheet"))
    		   {	  
				   WritableSheet sheet8=workbook.createSheet("案件通報-病人問題",7);	
				   medicalIssuesSheet(sheet8);
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
	public void createMed0002DbSheet(WritableSheet sheet2,String title,java.util.Map<String,String> titleMap) throws Exception
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
		

 	   String hql0001=" (select id from dynamicView05 where 1=1 "+condition()+")";

 	   String med0002Hql="  select b.applNo,"+field;
 	          med0002Hql+=" from Med0002_Db a,Med0001_Db b";
 	          med0002Hql+=" where a.med0001_id=b.id ";
 	          med0002Hql+=" and a.med0001_id  in "+hql0001;
 	          med0002Hql+=" order by b.applNo ";       
 	          
 	   System.out.println("med0002Hql=="+med0002Hql);
 	          
 	  java.util.List list0002 = ServiceGetter.getInstance().getTcbwService().getNativeSqlQuery().load(med0002Hql);
 	   
 	  if (list0002!=null && list0002.size()>0) 			
	  {
 		  int e=1;
 		  for (int j=0; j<list0002.size(); j++) 
    	  {
			   Object x[] = (Object[])list0002.get(j);
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
	public void createMed0003DbSheet(WritableSheet sheet3,String title,java.util.Map<String,String> titleMap) throws Exception
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
		

 	   String hql0001=" (select id from dynamicView05 where 1=1 "+condition()+")";

 	   String med0003Hql="  select b.applNo,"+field;
 	          med0003Hql+=" from Med0003_Db a,Med0001_Db b";
 	          med0003Hql+=" where a.med0001_id=b.id ";
 	          med0003Hql+=" and a.med0001_id  in "+hql0001;
 	          med0003Hql+=" order by b.applNo ";       
 	          
 	   System.out.println("med0003Hql=="+med0003Hql);
 	          
 	  java.util.List list0003 = ServiceGetter.getInstance().getTcbwService().getNativeSqlQuery().load(med0003Hql);
 	   
 	  if (list0003!=null && list0003.size()>0) 			
	  {
 		  int e=1;
 		  for (int j=0; j<list0003.size(); j++) 
    	  {
			   Object x[] = (Object[])list0003.get(j);
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
	public void createMed0004DbSheet(WritableSheet sheet4,String title,java.util.Map<String,String> titleMap) throws Exception
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
		

 	   String hql0001=" (select id from dynamicView05 where 1=1 "+condition()+")";

 	   String med0004Hql="  select b.applNo,"+field;
 	          med0004Hql+=" from Med0004_Db a,Med0001_Db b";
 	          med0004Hql+=" where a.med0001_id=b.id ";
 	          med0004Hql+=" and a.med0001_id  in "+hql0001;
 	          med0004Hql+=" order by b.applNo ";       
 	          
 	   System.out.println("med0004Hql=="+med0004Hql);
 	          
 	  java.util.List list0004 = ServiceGetter.getInstance().getTcbwService().getNativeSqlQuery().load(med0004Hql);
 	   
 	  if (list0004!=null && list0004.size()>0) 			
	  {
 		  int e=1;
 		  
 		 java.util.Map<String, String> medpkidMap = TCBWCommon.getCommonCodeMap("MEDPKID");//許可證-字
 		  
 		  for (int j=0; j<list0004.size(); j++) 
    	  {
			   Object x[] = (Object[])list0004.get(j);
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
	public void createMed0005DbSheet(WritableSheet sheet5,String title,java.util.Map<String,String> titleMap) throws Exception
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
		

 	   String hql0001=" (select id from dynamicView05 where 1=1 "+condition()+")";

 	   String med0005Hql="  select b.applNo,"+field;
 	          med0005Hql+=" from Med0005_Db a,Med0001_Db b";
 	          med0005Hql+=" where a.med0001_id=b.id ";
 	          med0005Hql+=" and a.med0001_id  in "+hql0001;
 	          med0005Hql+=" order by b.applNo ";       
 	          
 	   System.out.println("med0005Hql=="+med0005Hql);
 	          
 	  java.util.List list0005 = ServiceGetter.getInstance().getTcbwService().getNativeSqlQuery().load(med0005Hql);
 	   
 	  if (list0005!=null && list0005.size()>0) 			
	  {
 		  int e=1;
 		  
 		  java.util.Map<String, String> formulationMap = TCBWCommon.getCommonCodeMap("DRGFLN");//劑型
 		  java.util.Map<String, String> drgApproachMap  = TCBWCommon.getCommonCodeMap("DRG0304");//給藥途徑
 		  java.util.Map<String, String> doseMap  = TCBWCommon.getCommonCodeMap("DRG0305");//劑量
 		  java.util.Map<String, String> frequencyMap = TCBWCommon.getCommonCodeMap("DRG0306");//頻率
 		
 		  for (int j=0; j<list0005.size(); j++) 
    	  {
			   Object x[] = (Object[])list0005.get(j);
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
	
	//醫材問題
	public void patientIssuesSheet(WritableSheet sheet7) throws Exception
	{
		//設定字行
		WritableFont Font10 = new WritableFont(WritableFont.createFont("標楷體"),12);
		WritableCellFormat detailCenter = new  WritableCellFormat(Font10);
		
		//medicalIssues
 	   String hql0001= " select applno0001,patientIssues ";
 	          hql0001+=" from dynamicView05 where 1=1 "+condition();
 	          hql0001+=" order by applno0001";
 	   System.out.println("hql0001=="+hql0001);
 	          
 	   java.util.List list001 = ServiceGetter.getInstance().getTcbwService().getNativeSqlQuery().load(hql0001);
 	   
 	   if (list001!=null && list001.size()>0) 			
	   {
 		  int f=1; 
 		 
 		  java.util.Map<String,String>  patientIssuesMap=TCBWCommon.getMap("select code,name from Med1006Db ");

 	
		  sheet7.addCell(new Label(0,0,"案件編號",detailCenter));
		  sheet7.setColumnView(0,20);
		  sheet7.addCell(new Label(1,0,"醫材問題代碼",detailCenter));	
		  sheet7.setColumnView(1,40);
		  sheet7.addCell(new Label(2,0,"醫材問題名稱",detailCenter));
		  sheet7.setColumnView(2,40);
		  
 		  for (int j=0; j<list001.size(); j++) 
    	  {
			   Object x[] = (Object[])list001.get(j);
			     
			   String[] str=null;

			   if(!"".equals(Common.get(x[1])))
			   {
			      str=Common.get(x[1]).split(",");
			      for(int i=0;i<str.length;i++)
			      {
			    	sheet7.addCell(new Label(0,f,Common.get(x[0]),detailCenter));  
			    	sheet7.addCell(new Label(1,f,str[i],detailCenter));
			    	sheet7.addCell(new Label(2,f,patientIssuesMap.get(str[i]),detailCenter));
			    	f++;
			      }	
			   }

	
    	    }
	    }

	}
	
	//醫材問題
	public void medicalIssuesSheet(WritableSheet sheet8) throws Exception
	{
		//設定字行
		WritableFont Font10 = new WritableFont(WritableFont.createFont("標楷體"),12);
		WritableCellFormat detailCenter = new  WritableCellFormat(Font10);
		
		String hql0001="  select applno0001,medicalIssues ";
 	          hql0001+=" from dynamicView05 where 1=1 "+condition();
 	          hql0001+=" order by applno0001";
 	   System.out.println("hql0001=="+hql0001);
 	          
 	   java.util.List list001 = ServiceGetter.getInstance().getTcbwService().getNativeSqlQuery().load(hql0001);
 	   
 	   if (list001!=null && list001.size()>0) 			
	   {
 		  int f=1; 
 		 
 		
 		  java.util.Map<String,String>  medicalIssuesMap=TCBWCommon.getMap("select code,name from Med1005Db ");
 	
 		  sheet8.addCell(new Label(0,0,"案件編號",detailCenter));
 		  sheet8.setColumnView(0,20);
 		  sheet8.addCell(new Label(1,0,"醫材問題代碼",detailCenter));
 		  sheet8.setColumnView(1,40);
 		  sheet8.addCell(new Label(2,0,"醫材問題名稱",detailCenter));
 		  sheet8.setColumnView(2,40);

 		  for (int j=0; j<list001.size(); j++) 
    	  {
			   Object x[] = (Object[])list001.get(j);
			     
			   String[] str=null;

			   if(!"".equals(Common.get(x[1])))
			   {
			      str=Common.get(x[1]).split(",");
			      for(int i=0;i<str.length;i++)
			      {
			    	sheet8.addCell(new Label(0,f,Common.get(x[0]),detailCenter));  
			    	sheet8.addCell(new Label(1,f,str[i],detailCenter));
			    	sheet8.addCell(new Label(2,f,medicalIssuesMap.get(str[i]),detailCenter));
			    	f++;
			      }	
			   }

	
    	    }
	    }

	}
	
	public void createProductProblemKindSheet(WritableSheet sheet6) throws Exception
	{
		//設定字行
		WritableFont Font10 = new WritableFont(WritableFont.createFont("標楷體"),12);
		WritableCellFormat detailCenter = new  WritableCellFormat(Font10);
 	   
		String hql0001="  select applno0001,productProblemKind10001,productProblemKind20001, ";
 	          hql0001+="  productProblemKind30001,productProblemKind40001 ";
 	          hql0001+="  from dynamicView05 where 1=1 "+condition();
 	          hql0001+=" order by applno0001";
 	   System.out.println("hql0001=="+hql0001);
 	          
 	   java.util.List list001 = ServiceGetter.getInstance().getTcbwService().getNativeSqlQuery().load(hql0001);
 	   
 	   if (list001!=null && list001.size()>0) 			
	   {
 		  int f=1; 
 		 
 		
 		  java.util.Map<String, String> medOperatingMap = TCBWCommon.getCommonCodeMap("medOperating"); //
 		  java.util.Map<String, String> medFacilityMap = TCBWCommon.getCommonCodeMap("medFacility"); //
 		  java.util.Map<String, String> medPeoFactorMap = TCBWCommon.getCommonCodeMap("medPeoFactor"); //
 		  java.util.Map<String, String> medPhysicalMap = TCBWCommon.getCommonCodeMap("medPhysical"); //
 		  
		  sheet6.addCell(new Label(0,0,"案件編號",detailCenter));
		  sheet6.addCell(new Label(1,0,"產品問題大類",detailCenter));	
		  sheet6.addCell(new Label(2,0,"產品問題小類",detailCenter));
		  sheet6.setColumnView(0,20);
		  sheet6.setColumnView(1,40);
		  sheet6.setColumnView(2,40);

 		  for (int j=0; j<list001.size(); j++) 
    	  {
			   Object x[] = (Object[])list001.get(j);
			     
			   String[] str=null;

			   if(!"".equals(Common.get(x[1])))
			   {
			      str=Common.get(x[1]).split(",");
			      for(int i=0;i<str.length;i++)
			      {
			    	sheet6.addCell(new Label(0,f,Common.get(x[0]),detailCenter));  
			    	sheet6.addCell(new Label(1,f,"器材操作",detailCenter));
			    	sheet6.addCell(new Label(2,f,medOperatingMap.get(str[i]),detailCenter));
			    	f++;
			      }	
			   }
			   
			   if(!"".equals(Common.get(x[2])))
			   {
			      str=Common.get(x[2]).split(",");
			      for(int i=0;i<str.length;i++)
			      {
			    	sheet6.addCell(new Label(0,f,Common.get(x[0]),detailCenter));  
			    	sheet6.addCell(new Label(1,f,"環境設施",detailCenter));
			    	sheet6.addCell(new Label(2,f,medFacilityMap.get(str[i]),detailCenter));
			    	f++;
			      }	
			   }
			   
			   if(!"".equals(Common.get(x[3])))
			   {
			      str=Common.get(x[3]).split(",");
			      for(int i=0;i<str.length;i++)
			      {
			    	sheet6.addCell(new Label(0,f,Common.get(x[0]),detailCenter));  
			    	sheet6.addCell(new Label(1,f,"人因",detailCenter));
			    	sheet6.addCell(new Label(2,f,medPeoFactorMap.get(str[i]),detailCenter));
			    	f++;
			      }	
			   }
			   
			   if(!"".equals(Common.get(x[4])))
			   {
			      str=Common.get(x[4]).split(",");
			      for(int i=0;i<str.length;i++)
			      {
			    	sheet6.addCell(new Label(0,f,Common.get(x[0]),detailCenter));  
			    	sheet6.addCell(new Label(1,f,"物理特性",detailCenter));
			    	sheet6.addCell(new Label(2,f,medPhysicalMap.get(str[i]),detailCenter));
			    	f++;
			      }	
			   }
	
    	    }
	    }

	}
	
	//存檔
	public void doSaveOrUpdate() throws Exception
	{
		try
		{
          Variant0001Db obj = (Variant0001Db)View.getObject(" from Variant0001Db where name="+Common.sqlChar(variantName)+" and kind='med01' ");
		
		  if(obj == null) 
		    obj = new Variant0001Db();
	
		  obj.setKind("med01");
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
		DYNAMIC0501R obj = this;

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
		
        String hql=" from Export0001Db where code like 'med01%'";
		
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
        Variant0001Db c=(Variant0001Db)View.getObject("from Variant0001Db where kind='med01' and name="+Common.sqlChar(getVariantName()));
		
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
	public String getQ_applNoS() {return checkGet(q_applNoS);}
	public void setQ_applNoS(String qApplNoS) {q_applNoS = checkSet(qApplNoS);}
	public String getQ_applNoE() {return checkGet(q_applNoE);}
	public void setQ_applNoE(String qApplNoE) {q_applNoE = checkSet(qApplNoE);}
	public String[] getQ_status() {return checkGet(q_status);}
	public void setQ_status(String[] qStatus) {q_status = checkSet(qStatus);}
	
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

	public String getQ_enrolledDateS() {return checkGet(q_enrolledDateS);}
	public void setQ_enrolledDateS(String qEnrolledDateS) {q_enrolledDateS = checkSet(qEnrolledDateS);}
	public String getQ_enrolledDateE() {return checkGet(q_enrolledDateE);}
	public void setQ_enrolledDateE(String qEnrolledDateE) {q_enrolledDateE = checkSet(qEnrolledDateE);}
	public String getQ_occurDateS() {return checkGet(q_occurDateS);}
	public void setQ_occurDateS(String qOccurDateS) {q_occurDateS = checkSet(qOccurDateS);}
	public String getQ_occurDateE() {return checkGet(q_occurDateE);}
	public void setQ_occurDateE(String qOccurDateE) {q_occurDateE = checkSet(qOccurDateE);}
	public String[] getQ_caseSource() {return checkGet(q_caseSource);}
	public void setQ_caseSource(String[] qCaseSource) {q_caseSource = checkSet(qCaseSource);}
	public String getQ_caseSourceOutCountry() {return checkGet(q_caseSourceOutCountry);}
	public void setQ_caseSourceOutCountry(String qCaseSourceOutCountry) {q_caseSourceOutCountry = checkSet(qCaseSourceOutCountry);}
	public String getQ_notifierDept1() {return checkGet(q_notifierDept1);}
	public void setQ_notifierDept1(String qNotifierDept1) {q_notifierDept1 = checkSet(qNotifierDept1);}
	public String getQ_notifierDept2() {return checkGet(q_notifierDept2);}
	public void setQ_notifierDept2(String qNotifierDept2) {q_notifierDept2 = checkSet(qNotifierDept2);}
	public String getQ_notifierDept3() {return checkGet(q_notifierDept3);}
	public void setQ_notifierDept3(String qNotifierDept3) {q_notifierDept3 = checkSet(qNotifierDept3);}
	public String[] getQ_notifierType() {return checkGet(q_notifierType);}
	public void setQ_notifierType(String[] qNotifierType) {q_notifierType = checkSet(qNotifierType);}
	public String[] getQ_eventKind() {return checkGet(q_eventKind);}
	public void setQ_eventKind(String[] qEventKind) {q_eventKind = checkSet(qEventKind);}
	public String getQ_medPermit() {return checkGet(q_medPermit);}
	public void setQ_medPermit(String qMedPermit) {q_medPermit = checkSet(qMedPermit);}
	public String getQ_medPermitNumber() {	return checkGet(q_medPermitNumber);}
	public void setQ_medPermitNumber(String qMedPermitNumber) {q_medPermitNumber = checkSet(qMedPermitNumber);}
	public String getQ_medCname() {return checkGet(q_medCname);}
	public void setQ_medCname(String qMedCname) {q_medCname = checkSet(qMedCname);}
	public String getQ_medPermitFirm() {return checkGet(q_medPermitFirm);}
	public void setQ_medPermitFirm(String qMedPermitFirm) {q_medPermitFirm = checkSet(qMedPermitFirm);}
	public String getQ_medFactory() {return checkGet(q_medFactory);}
	public void setQ_medFactory(String qMedFactory) {q_medFactory = checkSet(qMedFactory);}
	public String getQ_medCountry() {return checkGet(q_medCountry);}
	public void setQ_medCountry(String qMedCountry) {q_medCountry = checkSet(qMedCountry);}
	public String getQ_medModel() {return checkGet(q_medModel);}
	public void setQ_medModel(String qMedModel) {q_medModel = checkSet(qMedModel);}
	public String getQ_medMainCategoryCodel() {return checkGet(q_medMainCategoryCodel);}
	public void setQ_medMainCategoryCodel(String qMedMainCategoryCodel) {q_medMainCategoryCodel = checkSet(qMedMainCategoryCodel);}
	public String getQ_medSecCategoryCodel() {return checkGet(q_medSecCategoryCodel);}
	public void setQ_medSecCategoryCodel(String qMedSecCategoryCodel) {q_medSecCategoryCodel = checkSet(qMedSecCategoryCodel);}
	public String getQ_medNo() {return checkGet(q_medNo);}
	public void setQ_medNo(String qMedNo) {q_medNo = checkSet(qMedNo);}
	public String getQ_medLotNum() {return checkGet(q_medLotNum);}
	public void setQ_medLotNum(String qMedLotNum) {q_medLotNum = checkSet(qMedLotNum);}
	public String getQ_medSoftwareVersion() {return checkGet(q_medSoftwareVersion);}
	public void setQ_medSoftwareVersion(String qMedSoftwareVersion) {q_medSoftwareVersion = checkSet(qMedSoftwareVersion);}
	public String[] getQ_badReactionResults() {return checkGet(q_badReactionResults);}
	public void setQ_badReactionResults(String[] qBadReactionResults) {q_badReactionResults = checkSet(qBadReactionResults);}

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

	public String[] getQ_productProblemKind1() {
		return checkGet(q_productProblemKind1);
	}

	public void setQ_productProblemKind1(String[] qProductProblemKind1) {
		q_productProblemKind1 = checkSet(qProductProblemKind1);
	}

	public String[] getQ_productProblemKind2() {
		return checkGet(q_productProblemKind2);
	}

	public void setQ_productProblemKind2(String[] qProductProblemKind2) {
		q_productProblemKind2 = checkSet(qProductProblemKind2);
	}

	public String[] getQ_productProblemKind3() {
		return checkGet(q_productProblemKind3);
	}

	public void setQ_productProblemKind3(String[] qProductProblemKind3) {
		q_productProblemKind3 = checkSet(qProductProblemKind3);
	}

	public String[] getQ_productProblemKind4() {
		return checkGet(q_productProblemKind4);
	}

	public void setQ_productProblemKind4(String[] qProductProblemKind4) {
		q_productProblemKind4 = checkSet(qProductProblemKind4);
	}

	public String getQ_bulletinQuality() {
		return checkGet(q_bulletinQuality);
	}

	public void setQ_bulletinQuality(String qBulletinQuality) {
		q_bulletinQuality = checkSet(qBulletinQuality);
	}


	public String getQ_patientIssuesName() {
		return checkGet(q_patientIssuesName);
	}

	public void setQ_patientIssuesName(String qPatientIssuesName) {
		q_patientIssuesName = checkSet(qPatientIssuesName);
	}

	public String getQ_medicalIssuesName() {
		return checkGet(q_medicalIssuesName);
	}

	public void setQ_medicalIssuesName(String qMedicalIssuesName) {
		q_medicalIssuesName = checkSet(qMedicalIssuesName);
	}

	public String[] getQ_adverseReactionsResult() {
		return checkGet(q_adverseReactionsResult);
	}

	public void setQ_adverseReactionsResult(String[] qAdverseReactionsResult) {
		q_adverseReactionsResult = checkSet(qAdverseReactionsResult);
	}

	public String[] getQ_eventClass() {
		return checkGet(q_eventClass);
	}

	public void setQ_eventClass(String[] qEventClass) {
		q_eventClass = checkSet(qEventClass);
	}

	public String[] getQ_eventSeverity() {
		return checkGet(q_eventSeverity);
	}

	public void setQ_eventSeverity(String[] qEventSeverity) {
		q_eventSeverity = checkSet(qEventSeverity);
	}

	public String getQ_ncarResult() {
		return checkGet(q_ncarResult);
	}

	public void setQ_ncarResult(String qNcarResult) {
		q_ncarResult = checkSet(qNcarResult);
	}

	public String[] getManyCode() {
		return checkGet(manyCode);
	}

	public void setManyCode(String[] manyCode) {
		this.manyCode = checkSet(manyCode);
	}

	
	
   
	

}
