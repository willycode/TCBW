package com.kangdainfo.tcbw.view.prcond;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.Database;
import com.kangdainfo.common.util.Datetime;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Con3001Db;
import com.kangdainfo.tcbw.util.TCBWCommon;
import com.kangdainfo.web.listener.MyServletContext;



public class PROCOND0301Q extends SuperBean
{

	private String id;

	private String q_projType;//專案類別
	private String q_dateS;//年月
	private String q_dateE;//年月
    private String q_projName;//專案名稱
    private String q_projid;//專案名稱
    private String q_datakindDRG[];
    private String q_datakindMED[];
    
	
	@Override
	public Object doQueryOne() throws Exception 
	{
		PROCOND0301Q obj = this;
		
		return obj;
	}

	@Override
	public Object doQueryAll() throws Exception 
	{
		
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		
		String hql=hql();
		
		System.out.println("hql==="+hql);
		
		java.util.List objList = ServiceGetter.getInstance().getTcbwService().getNativeSqlQuery().load(hql.toString());
			
		if (objList != null && objList.size() > 0) 
		{
			java.util.Map<String, String> drgpkidMap = TCBWCommon.getCommonCodeMap("DRGPKID");
			java.util.Map<String, String> medpkidMap = TCBWCommon.getCommonCodeMap("MEDPKID");
	
        	for (int j=0; j<objList.size(); j++) 
	    	{
				Object x[] = (Object[])objList.get(j);
				String[] rowArray = new String[8];
				rowArray[0] = Common.get(x[0]);//年度									
				rowArray[1] = Common.get(x[1]);//月份
				rowArray[2] = Common.get(x[2]);//資料日期
				rowArray[3] = Common.get(x[3]);//資料類別
				rowArray[4] = Common.get(x[4]);//案件編號
				
				if(Common.get(x[9]).indexOf("drg")!=-1)
					rowArray[5]=(Common.get(drgpkidMap.get(x[5]))+Common.get(x[6]));
				
				if(Common.get(x[9]).indexOf("med")!=-1)
					rowArray[5]=(Common.get(medpkidMap.get(x[5]))+Common.get(x[6]));
				
				rowArray[6] = Common.get(x[7]);//中文品名
				rowArray[7] = Common.get(x[8]);//許可證持有商
				arrList.add(rowArray);
			}
			objList.clear();
		}
		
		return arrList;
	}
	
	
	public void exportExcelTable(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		
		// 讀取EXCEL樣版
		InputStream st = new FileInputStream(new File(MyServletContext.getInstance().getServletContext().getRealPath("/report/prcond/prcond0301q.xlsx")));

		XSSFWorkbook workbook = new XSSFWorkbook(st);
		
		String hql=hql();
    	
		//年度、月份、資料日期、案件編號
		hql+=" order by year,month,notifierRepDate,applNo ";
		
    	java.util.List list = ServiceGetter.getInstance().getTcbwService().getNativeSqlQuery().load(hql.toString());
		
    	System.out.println("hql=="+hql.toString());

		if (list!=null && list.size()>0) 			
		{
			 java.util.Map<String, String> drgpkidMap = TCBWCommon.getCommonCodeMap("DRGPKID");
			 java.util.Map<String, String> medpkidMap = TCBWCommon.getCommonCodeMap("MEDPKID");
			 
			 // 處理工作SHEET
			 XSSFSheet sheet = workbook.getSheetAt(0);

			 int rowCount=1;
			 for (int j=0; j<list.size(); j++) 
	    	 {
				Object x[] = (Object[])list.get(j);	
				XSSFRow row = sheet.createRow(rowCount);		
				
				XSSFCell a0 = row.createCell(0);										
				a0.setCellValue(rowCount);
				
				//年度	
				XSSFCell a1 = row.createCell(1);										
				a1.setCellValue(Common.get(x[0]));

				//月份
				XSSFCell a2 = row.createCell(2);										
				a2.setCellValue(Common.get(x[1]));
				
				//資料日期
				XSSFCell a3 = row.createCell(3);										
				a3.setCellValue(Common.get(x[2]));
				
				//資料類別
				XSSFCell a4 = row.createCell(4);										
				a4.setCellValue(Common.get(x[3]));
				
				//案件編號
				XSSFCell a5 = row.createCell(5);										
				a5.setCellValue(Common.get(x[4]));
				
				//許可證字號
				XSSFCell a6 = row.createCell(6);	
				
				if(Common.get(x[9]).indexOf("drg")!=-1)
				  a6.setCellValue(Common.get(drgpkidMap.get(x[5]))+Common.get(x[6]));
			
				if(Common.get(x[9]).indexOf("med")!=-1)
				  a6.setCellValue(Common.get(medpkidMap.get(x[5]))+Common.get(x[6]));
					
					
				//中文品名
				XSSFCell a7 = row.createCell(7);										
				a7.setCellValue(Common.get(x[7]));
				
				//許可證持有商
				XSSFCell a8 = row.createCell(8);										
				a8.setCellValue(Common.get(x[8]));
				
				XSSFCell a9 = row.createCell(9);										
				a9.setCellValue("1");
				
				rowCount++;
	    	 }
	
			
		 }
		

		 Con3001Db c = (Con3001Db)View.getObject("from Con3001Db where id = " + Common.getLong(getQ_projid()));		

		 if(c!=null)
		 {
			 
			 Database db = new Database("MLMS");
			 
			 if(!"".equals(Common.get(c.getPermitKey())))
			 {
				 XSSFSheet sheet1 = workbook.getSheetAt(1);
				 
				 java.util.List<Object> parameter = new java.util.ArrayList<Object>();
				 
				 String view=" select LICID,APPRID,CONVERT(varchar(12),APPRDATE,112) APPRDATE,MODITEM from VW_ForADR_MOD";
				        view+=" where LICID= ? ";
				 
				 parameter.add(Common.get(c.getPermitKey())+Common.get(c.getPermitNo()));      
				        
				 System.out.println("view==="+view);
				        
				 //java.util.List viewlist = ServiceGetter.getInstance().getTcbwService().getNativeSqlQuery().load(view);
				 
				 ResultSet rs = db.querySQLByPrepareStatement(view, parameter);
				 
				 int rowCount1=1;

				 while (rs.next())
		    	 {
			
					XSSFRow row1 = sheet1.createRow(rowCount1);		
					
					XSSFCell a0 = row1.createCell(0);										
					a0.setCellValue(rowCount1);
					
					XSSFCell a1 = row1.createCell(1);										
					a1.setCellValue(Common.get(rs.getString("APPRID")));//核准文號
					
					XSSFCell a2 = row1.createCell(2);//核准日期								
					a2.setCellValue(Common.get(Datetime.getRocDateFromYYYYMMDD(Common.get(rs.getString("APPRDATE")))));
					
					XSSFCell a3 = row1.createCell(3);//變更項目									
					a3.setCellValue(Common.get(rs.getString("MODITEM")));
					
					rowCount1++;
		    	 }
			 }	 
		 }
		 
		 
		 st.close();
		 
		 // 寫入新檔案
		 File oFile = new File("prcond0301q_"+Datetime.getHHMMSS()+".xlsx");
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
	
	public String hql()
	{
		String hql = " select year,month,notifierRepDate,type, ";
	           hql +=" applNo,permitKey,permitNo,chProduct,applicationName,typeCode ";
	           hql +=" from PRCOND0301QView ";
	           hql +=" where 1=1 and applNo <>'' and applNo is not null";
	       
	    if(!"".equals(getQ_datakindDRG()) && null != getQ_datakindDRG()) {
	    	String condition = "";
	    	for(String o : getQ_datakindDRG()) {
	    		if(condition.length() > 0) condition+=",";
	    		condition+= Common.sqlChar(o);
	    	}
	    	hql += " and typeCode in (" + condition + ")";
	    } else if(!"".equals(getQ_datakindMED()) && null != getQ_datakindMED()) {
	    	String condition = "";
	    	for(String o : getQ_datakindMED()) {
	    		if(condition.length() > 0) condition+=",";
	    		condition+= Common.sqlChar(o);
	    	}
	    	hql += " and typeCode in (" + condition + ")";
	    } else if("drg".equals(getQ_projType())) {
	    	hql +="  and  typeCode in ('drg01','drg02','drg03','drg04') ";
	    } else if("med".equals(getQ_projType())) {       
			   hql +="  and  typeCode in ('med01','med02') ";
	    }
//	    
//	    if(!"".equals(getQ_datakindMED()) && null != getQ_datakindMED()) {
//	    	String condition = "";
//	    	for(String o : getQ_datakindMED()) {
//	    		if(condition.length() > 0) condition+=",";
//	    		condition+= Common.sqlChar(o);
//	    	}
//	    	hql += " and typeCode in (" + condition + ")";
//	    } else {
//	    	hql +="  and  typeCode in ('med01','med02') ";
//	    }
//	    if("drg".equals(getQ_projType()))       
//		   hql +="  and  typeCode in ('drg01','drg02','drg03','drg04') ";
	
//	    if("med".equals(getQ_projType()))       
//		   hql +="  and  typeCode in ('med01','med02') ";
	
	    if(!"".equals(getQ_dateS()))
		   hql +="  and cast(substring(notifierRepDate,1,5)as int) >=" + getQ_dateS();
	
	    if(!"".equals(getQ_dateE()))
		   hql +=" and cast(substring(notifierRepDate,1,5)as int) <=" + getQ_dateE();
	
	
	    Con3001Db c = (Con3001Db)View.getObject("from Con3001Db where id = " + Common.getLong(getQ_projid()));		
	
	    if(c!=null)
	    {
	    	
		  if(!"".equals(Common.get(c.getPermitKey())))
		  {
			hql +=" and permitKey=" + Common.sqlChar(c.getPermitKey());
		  }
		
		  if(!"".equals(Common.get(c.getPermitNo())))
		  {
			hql +=" and permitNo=" + Common.sqlChar(c.getPermitNo());
		  }
		
		  if("".equals(Common.get(c.getPermitKey())))
		  {
			  if(!"".equals(Common.get(c.getChProduct())))
		      {
			     hql +=" and chProduct=" + Common.sqlChar(c.getChProduct());
		      }
		  }
		  
		  if(!"".equals(Common.get(c.getApplicationId())))
		  {
			hql +=" and applicationID=" + Common.sqlChar(c.getApplicationId());
		  }
		  
		  if(!"".equals(Common.get(c.getMedSecCategory())))
		  {
			hql +=" and medSecCategoryCode=" + Common.sqlChar(c.getMedSecCategory());
		  }
		  
	    }	
		return hql;
		
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

	}
	
	
	
	public String getId() {
		return checkGet(id);
	}

	public void setId(String id) {
		this.id = checkSet(id);
	}

	public String getQ_projType() {
		return checkGet(q_projType);
	}

	public void setQ_projType(String qProjType) {
		q_projType = checkSet(qProjType);
	}



	public String getQ_dateS() {
		return checkGet(q_dateS);
	}

	public void setQ_dateS(String qDateS) {
		q_dateS = checkSet(qDateS);
	}

	public String getQ_dateE() {
		return checkGet(q_dateE);
	}

	public void setQ_dateE(String qDateE) {
		q_dateE = checkSet(qDateE);
	}

	public String getQ_projName() {
		return checkGet(q_projName);
	}

	public void setQ_projName(String qProjName) {
		q_projName = checkSet(qProjName);
	}

	public String getQ_projid() {
		return checkGet(q_projid);
	}

	public void setQ_projid(String qProjid) {
		q_projid = checkSet(qProjid);
	}

	public String[] getQ_datakindDRG() {
		return q_datakindDRG;
	}

	public void setQ_datakindDRG(String[] q_datakindDRG) {
		this.q_datakindDRG = q_datakindDRG;
	}

	public String[] getQ_datakindMED() {
		return q_datakindMED;
	}

	public void setQ_datakindMED(String[] q_datakindMED) {
		this.q_datakindMED = q_datakindMED;
	}



	

}
