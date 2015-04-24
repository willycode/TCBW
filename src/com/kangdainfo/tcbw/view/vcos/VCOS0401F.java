package com.kangdainfo.tcbw.view.vcos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.format.Font;

import org.apache.poi.hssf.util.HSSFColor;
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
import com.kangdainfo.tcbw.model.bo.Cos7001Db;
import com.kangdainfo.tcbw.model.bo.Med7001Db;
import com.kangdainfo.tcbw.model.bo.Med7002Db;
import com.kangdainfo.tcbw.model.bo.Med7005Db;
import com.kangdainfo.tcbw.util.TCBWCommon;
import com.kangdainfo.web.listener.MyServletContext;
public class VCOS0401F extends SuperBean
{
	private String q_id;
    private String applNo;
    
    private String q_applNoS;
	private String q_applNoE;
    private String q_prodtype;
    private String q_chProduct;
    private String q_dataRevDateS;
    private String q_dataRevDateE;
    private String q_publDept;
    private String q_publDeptCodeId;
    private String q_publDeptName;
    private String q_publCountry;
    private String q_manufactorCountry;
    private String q_ingredient;
    private String q_brand;
    private String[] q_situation;
    
    private String q_publDateS;
    private String q_publDateE;
    private String[] q_subjecttype;   
    private String q_lotNo;
    private String q_status;
    private String q_isImport;
    
    private String[] q_lamp;
    
	private String caseType;
	private String formType;
    
	
	
	
	@Override
	public Object doQueryOne() throws Exception {
		return null;
	}
	
	public String getHql()
	{
		String hql="";
		
		if(!"".equals(getQ_applNoS())) 
			hql += " and applNo >= " +  Common.sqlChar(getQ_applNoS());
		if(!"".equals(getQ_applNoE())) 
			hql += " and applNo <= " + Common.sqlChar(getQ_applNoE());
		if(!"".equals(getQ_dataRevDateS())) 
			hql += " and dataRevDate >= " +  Common.sqlChar(getQ_dataRevDateS());
		if(!"".equals(getQ_dataRevDateE())) 
			hql += " and dataRevDate <= " + Common.sqlChar(getQ_dataRevDateE());
		if(!"".equals(getQ_publDateS())) 
			hql += " and publDate >= " +  Common.sqlChar(getQ_publDateS());
	    if(!"".equals(getQ_publDateE())) 
			hql += " and publDate <= " + Common.sqlChar(getQ_publDateE());			
		if(!"".equals(getQ_status())) 
			hql += " and status = " + Common.sqlChar(getQ_status());			
		if(!"".equals(getQ_chProduct())) 
			hql += " and chProduct like " + Common.likeSqlChar(getQ_chProduct());
		if(!"".equals(getQ_publDeptCodeId())) 
			hql += " and publDept = " + Common.sqlChar(getQ_publDeptCodeId());
		if(!"".equals(getQ_ingredient())) 
			hql += " and ingredient = " + Common.sqlChar(getQ_ingredient());
		if(!"".equals(getQ_brand())) 
			hql += " and brand like " + Common.likeSqlChar( getQ_brand());		
		
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
			hql+=" and situation in ("+situationStr+")";
		
		String subjecttypeStr="";
		if(null!=getQ_subjecttype() && getQ_subjecttype().length>0)
		{
			for(int i=0;i<getQ_subjecttype().length;i++)
			{
				if(subjecttypeStr.length()>0)subjecttypeStr+=",";
				subjecttypeStr+="'"+getQ_subjecttype()[i]+"'";
			}	
		}	
		
		if(subjecttypeStr.length()>0)
			hql+=" and subjecttype in ("+subjecttypeStr+")";
		
	
		if(!"".equals(getQ_lotNo())) 
			hql += " and lotNo like " + Common.likeSqlChar(getQ_lotNo());
		
		if(!"".equals(getQ_isImport())) 
			hql += " and isImport = " + Common.sqlChar(getQ_isImport());
		
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
			hql+=" and lamp in ("+lampStr+")";
		
		return hql;
		
	}
	
	
	@Override
	public java.util.ArrayList<String[]> doQueryAll() throws Exception 
	{		
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		String hql = " from Cos7001Db where 1=1  ";
		
		if(null != getQ_id() && !"".equals(getQ_id()))
		{
			hql+=" and id= "+Common.sqlChar(getQ_id());
		}
	    
		hql +=Common.get(getHql());
		

		java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(hql + " order by applNo desc,id desc");
		
		if (objList != null && objList.size() > 0) 
		{
			ServiceGetter.getInstance().getCommonService().getCommonUserDao().getHibernateTemplate().clear();
			
			java.util.Iterator it = objList.iterator();
			
			java.util.Map<String, String> IngredientMap = TCBWCommon.getCommonCodeMap("CCI");
			java.util.Map<String, String> SituationMap = TCBWCommon.getCommonCodeMap("CONWARNING");
			java.util.Map<String, String> SubjecttypeMap = TCBWCommon.getCommonCodeMap("COSSJTYPE");
			java.util.Map<String, String> LampMap = TCBWCommon.getCommonCodeMap("CONLAMP");
			
			while (it.hasNext()) 
			{
				Cos7001Db o = (Cos7001Db) it.next();
				String rowArray[] = new String[10];
				rowArray[0]=Common.get(o.getId());
				rowArray[1]=Common.get(o.getApplNo());//案件編號
				rowArray[2]=Common.get(o.getChProduct());//品名
				rowArray[3]=Common.get(o.getDataRevDate());//資料收集日期
				rowArray[4]=Common.get(IngredientMap.get(o.getIngredient()));//化妝品項目
			    rowArray[5]=Common.get(SituationMap.get(o.getSituation()));//狀況
				rowArray[6]=Common.get(o.getPublDate());//發佈日期
				rowArray[7]=Common.get(SubjecttypeMap.get(o.getSubjecttype()));//訊息主題
				rowArray[8]=Common.get(o.getIsImport());//產品是否進口
				rowArray[9]=Common.get(LampMap.get(o.getLamp()));//燈號
				arrList.add(rowArray);
			}
		}
		return arrList;
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
	
	public void exportExcelTable(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		// 讀取EXCEL樣版
		InputStream st = new FileInputStream(new File(MyServletContext.getInstance().getServletContext().getRealPath("/report/vcos/VCOS0401F.xlsx")));

		XSSFWorkbook workbook = new XSSFWorkbook(st);

		StringBuilder hql = new StringBuilder();
		
		hql.append(" from Cos7001Db where 1=1 ");
		
		hql.append(Common.get(getHql()));
		
		java.util.List list = ServiceGetter.getInstance().getTcbwService().load(hql.toString());
		
		System.out.println("hql=="+hql.toString());

		if (list!=null && list.size()>0) 			
		{
			
			java.util.Map<String, String> SituationMap = TCBWCommon.getCommonCodeMap("CONWARNING");
			java.util.Map<String, String> LampMap = TCBWCommon.getCommonCodeMap("CONLAMP");
			java.util.Map<String, String> PublDeptMap = TCBWCommon.getCommonCodeMap("CONPUBLDEPT");
			java.util.Map<String, String> PublDeptChMap = TCBWCommon.getCommonCodeEngMap("CONPUBLDEPT");
			java.util.Map<String, String> aftereffectMap = TCBWCommon.getCommonCodeMap("DRGEFFECT");
			java.util.Map<String, String> ingredientMap = TCBWCommon.getCommonCodeMap("CCI");
			java.util.Map<String, String> subjectMap = TCBWCommon.getCommonCodeMap("COSSJTYPE");
			
			
			 XSSFCellStyle styleRow1 = workbook.createCellStyle();

			 
			 styleRow1.setBorderBottom((short) 1);
			 styleRow1.setBorderTop((short) 1);
			 styleRow1.setBorderLeft((short) 1);
			 styleRow1.setBorderRight((short) 1);
			 styleRow1.setWrapText(true); // 自動換行
			
			 XSSFCellStyle styleRow2 = workbook.createCellStyle();
			 styleRow2.setBorderBottom((short) 1);
			 styleRow2.setBorderTop((short) 1);
			 styleRow2.setBorderLeft((short) 1);
			 styleRow2.setBorderRight((short) 1);
			 
			 XSSFFont font = workbook.createFont();
			 font.setBoldweight(font.BOLDWEIGHT_BOLD); // 粗細體
			 font.setFontName("標楷體");//字體大小
		     font.setFontHeightInPoints((short) 20);
		     
			 XSSFFont font1 = workbook.createFont();
			 font.setBoldweight(font.BOLDWEIGHT_NORMAL); // 粗細體
			 font.setFontName("標楷體");//字體大小
		     font.setFontHeightInPoints((short) 14);
			 
			 
			 styleRow1.setFont(font1); // 設定字體
			 styleRow1.setAlignment(XSSFCellStyle.ALIGN_CENTER); // 水平置中
			 styleRow1.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER); // 垂直置中
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
				Cos7001Db cos7001Db = (Cos7001Db)list.get(j);	
				
				row = sheet.createRow(rowCount+2);	
			
				//編號類別
				XSSFCell a0 = row.createCell(0);										
				a0.setCellValue(rowCount);
				a0.setCellStyle(styleRow1);
				
				//產品類別
				XSSFCell a1 = row.createCell(1);										
				a1.setCellValue(Common.get("化粧品"));
				a1.setCellStyle(styleRow1);
				
				//品名
				XSSFCell a2 = row.createCell(2);										
				a2.setCellValue(Common.get(cos7001Db.getChProduct()));
				a2.setCellStyle(styleRow1);
				
				//資料收集日期
				XSSFCell a3 = row.createCell(3);										
				a3.setCellValue(Common.get(cos7001Db.getDataRevDate()));
				a3.setCellStyle(styleRow1);
				
				//發佈單位
				XSSFCell a4 = row.createCell(4);										
				a4.setCellValue(PublDeptMap.get(Common.get(cos7001Db.getPublDept())));
				a4.setCellStyle(styleRow1);
				
				//發佈單位(國家)
				XSSFCell a5 = row.createCell(5);										
				a5.setCellValue(PublDeptChMap.get(Common.get(cos7001Db.getPublDept())));				
				a5.setCellStyle(styleRow1);
				
				//產地
				XSSFCell a6 = row.createCell(6);										
				a6.setCellValue(Common.get(cos7001Db.getManufactorCountry()));
				a6.setCellStyle(styleRow1);
				
				//化粧品項目
				XSSFCell a7 = row.createCell(7);										
				a7.setCellValue(ingredientMap.get(Common.get(cos7001Db.getIngredient())));
				a7.setCellStyle(styleRow1);
				
				//品牌/廠商
				XSSFCell a8 = row.createCell(8);										
				a8.setCellValue(Common.get(cos7001Db.getBrand()));
				a8.setCellStyle(styleRow1);
				
				//狀況
				XSSFCell a9 = row.createCell(9);										
				a9.setCellValue(Common.get(SituationMap.get(cos7001Db.getSituation())));
				a9.setCellStyle(styleRow1);
				
				//發佈日期
				XSSFCell a10 = row.createCell(10);										
				a10.setCellValue(Common.get(cos7001Db.getPublDate()));
				a10.setCellStyle(styleRow1);
				
				//訊息主題
				XSSFCell a11 = row.createCell(11);										
				a11.setCellValue(subjectMap.get(Common.get(cos7001Db.getSubjecttype())));
				a11.setCellStyle(styleRow1);
				
				//資訊內容摘要
				XSSFCell a12 = row.createCell(12);										
				a12.setCellValue(Common.get(cos7001Db.getContextsummary()));
				a12.setCellStyle(styleRow1);
				
				//產品批號
				XSSFCell a13 = row.createCell(13);										
				a13.setCellValue(Common.get(cos7001Db.getLotNo()));
				a13.setCellStyle(styleRow1);
				
				//資料來源
				XSSFCell a14 = row.createCell(14);										
				a14.setCellValue(Common.get(cos7001Db.getDatasourWebSite()));
				a14.setCellStyle(styleRow1);
				
				//產品是否進口
				XSSFCell a15 = row.createCell(15);										
				a15.setCellValue(Common.get(cos7001Db.getIsImport()));
				a15.setCellStyle(styleRow1);
				
				//是否發佈新聞稿	
				XSSFCell a16 = row.createCell(16);										
				a16.setCellValue(Common.get(cos7001Db.getIspublnews()));
				a16.setCellStyle(styleRow1);
				
				//是否發佈消費者知識服務網
				XSSFCell a17 = row.createCell(17);										
				a17.setCellValue(Common.get(cos7001Db.getIspublconsumer()));
				a17.setCellStyle(styleRow1);
				
				//消費者知識服務網上架日期	
				XSSFCell a18 = row.createCell(18);										
				a18.setCellValue(Common.get(cos7001Db.getPublconsumerDate()));
				a18.setCellStyle(styleRow1);
				
				//燈號	
				XSSFCell a19 = row.createCell(19);										
				a19.setCellValue(Common.get(LampMap.get(cos7001Db.getLamp())));
				a19.setCellStyle(styleRow1);
				
				//查詢單位		
				XSSFCell a20 = row.createCell(20);										
				a20.setCellValue(View.getOneCodeName((Common.get(cos7001Db.getSearchdept()))));
				a20.setCellStyle(styleRow1);
				
				//許可證號碼	
				XSSFCell a21 = row.createCell(21);										
				a21.setCellValue(Common.get(cos7001Db.getRemark()));
				a21.setCellStyle(styleRow1);
				
				//後續處理
				XSSFCell a22 = row.createCell(22);										
				a22.setCellValue(aftereffectMap.get(Common.get(cos7001Db.getAftereffect())));
				a22.setCellStyle(styleRow1);
				
				rowCount++;
	    	 }
				
			 st.close();
		 }
		 
		 // 寫入新檔案
		 File oFile = new File("VCOS0401_"+Datetime.getHHMMSS()+".xlsx");
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
		        oFile.delete();
		  } 
		
		
	}
	
	public String getApplNo() {return checkGet(applNo);}	
	public void setApplNo(String applNo) {this.applNo = checkSet(applNo);}	
	public String getQ_id() {return checkGet(q_id);}
	public void setQ_id(String q_id) {this.q_id = checkSet(q_id);}	
	public String getQ_applNoS() {return checkGet(q_applNoS);}
	public void setQ_applNoS(String q_applNoS) {this.q_applNoS = checkSet(q_applNoS);}
	public String getQ_applNoE() {return checkGet(q_applNoE);}
	public void setQ_applNoE(String q_applNoE) {this.q_applNoE = checkSet(q_applNoE);	}
	public String getQ_prodtype() {return checkGet(q_prodtype);}
	public void setQ_prodtype(String q_prodtype) {this.q_prodtype = checkSet(q_prodtype);}
	public String getQ_chProduct() {return checkGet(q_chProduct);}
	public void setQ_chProduct(String q_chProduct) {this.q_chProduct = checkSet(q_chProduct);}
	public String getQ_dataRevDateS() {	return checkGet(q_dataRevDateS);}
	public void setQ_dataRevDateS(String q_dataRevDateS) {this.q_dataRevDateS = checkSet(q_dataRevDateS);}
	public String getQ_dataRevDateE() {return checkGet(q_dataRevDateE);	}
	public void setQ_dataRevDateE(String q_dataRevDateE) {this.q_dataRevDateE = checkSet(q_dataRevDateE);}
	public String getQ_publDept() {return checkGet(q_publDept);}
	public void setQ_publDept(String q_publDept) {this.q_publDept = checkSet(q_publDept);}
	public String getQ_publDeptCodeId() {return checkGet(q_publDeptCodeId);}
	public void setQ_publDeptCodeId(String q_publDeptCodeId) {this.q_publDeptCodeId = checkSet(q_publDeptCodeId);}
	public String getQ_publDeptName() {return checkGet(q_publDeptName);}
	public void setQ_publDeptName(String q_publDeptName) {this.q_publDeptName = checkSet(q_publDeptName);}
	public String getQ_publCountry() {return checkGet(q_publCountry);}
	public void setQ_publCountry(String q_publCountry) {this.q_publCountry = checkSet(q_publCountry);}
	public String getQ_manufactorCountry() {return checkGet(q_manufactorCountry);}
	public void setQ_manufactorCountry(String q_manufactorCountry) {this.q_manufactorCountry = checkSet(q_manufactorCountry);}
	public String getQ_ingredient() {return checkGet(q_ingredient);}
	public void setQ_ingredient(String q_ingredient) {this.q_ingredient = checkSet(q_ingredient);}
	public String getQ_brand() {return checkGet(q_brand);}
	public void setQ_brand(String q_brand) {this.q_brand = checkSet(q_brand);}
	public String[] getQ_situation() {return checkGet(q_situation);}
	public void setQ_situation(String[] q_situation) {this.q_situation = checkSet(q_situation);}
	public String getQ_publDateS() {return checkGet(q_publDateS);}
	public void setQ_publDateS(String q_publDateS) {	this.q_publDateS = checkSet(q_publDateS);}
	public String getQ_publDateE() {return checkGet(q_publDateE);}
	public void setQ_publDateE(String q_publDateE) {this.q_publDateE = checkSet(q_publDateE);}
	public String[] getQ_subjecttype() {return checkGet(q_subjecttype);}
	public void setQ_subjecttype(String[] q_subjecttype) {this.q_subjecttype = checkSet(q_subjecttype);}
	public String getQ_lotNo() {return checkGet(q_lotNo);}
	public void setQ_lotNo(String q_lotNo) {this.q_lotNo = checkSet(q_lotNo);}	
	public String getQ_status() {return checkGet(q_status);}
	public void setQ_status(String q_status) {this.q_status = checkSet(q_status);}	
	public String getQ_isImport() {return checkGet(q_isImport);}
	public void setQ_isImport(String qIsImport) {q_isImport = checkSet(qIsImport);}
	public String[] getQ_lamp() {return checkGet(q_lamp);}
	public void setQ_lamp(String[] qLamp) {q_lamp = checkSet(qLamp);}
	public String getCaseType() {return checkGet(caseType);}
	public void setCaseType(String caseType) {this.caseType = checkSet(caseType);}	
	public String getFormType() {return checkGet(formType);}
	public void setFormType(String formType) {this.formType = checkSet(formType);}
}
