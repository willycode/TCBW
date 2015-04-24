package com.kangdainfo.tcbw.view.pivot;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.Datetime;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.tcbw.util.TCBWCommon;
import com.kangdainfo.web.listener.MyServletContext;


public class PIVOT0701R extends SuperBean
{

	private String q_dateS;
	private String q_dateE;

	
	
	private String q_permitKey;
	private String q_permitNo;
	private String q_applicationName;
	private String q_manufactorName;
	private String q_chProduct;
	
	public void exportExcelTable(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		
		// 讀取EXCEL樣版
		 InputStream st = new FileInputStream(new File(MyServletContext.getInstance().getServletContext().getRealPath("/report/pivot/pivot0701r.xlsx")));

		 XSSFWorkbook workbook = new XSSFWorkbook(st);
		
    	StringBuilder hql = new StringBuilder();
    	
    	hql.append(" select applNo,handdateYear,handdateMonth,");
    	hql.append(" permitKey,permitNo,applicationName,manufactorName,chProduct");
    	hql.append(" from pivotView07 where 1=1 ");
    	
    	hql.append(queryHql());
		
    	//年度、月份、案件編號
    	hql.append(" order by handdateYear,handdateMonth,applNo");
    	
    	java.util.List list = ServiceGetter.getInstance().getTcbwService().getNativeSqlQuery().load(hql.toString());
		
    	System.out.println("hql=="+hql.toString());

		if (list!=null && list.size()>0) 			
		{
			
			java.util.Map<String, String> pkidMap = TCBWCommon.getCommonCodeMap("MEDPKID");	//許可證字
			
			 // 處理工作SHEET
			 XSSFSheet sheet = workbook.getSheetAt(0);

			 int rowCount=1;
			 for (int j=0; j<list.size(); j++) 
	    	 {
				Object x[] = (Object[])list.get(j);	
				XSSFRow row = sheet.createRow(rowCount);		
				
				XSSFCell a0 = row.createCell(0);										
				a0.setCellValue(rowCount);
				
				//案號
				XSSFCell a1 = row.createCell(1);										
				a1.setCellValue(Common.get(x[0]));
				
				//年度
				XSSFCell a2 = row.createCell(2);										
				if(!"".equals(Common.get(x[1])))
				  a2.setCellValue(Common.get(x[1]));
				else
				  a2.setCellValue("其他");
				
				//月份
				XSSFCell a3 = row.createCell(3);	
				if(!"".equals(Common.get(x[2])))
				  a3.setCellValue(Common.get(x[2]));
				else
				  a3.setCellValue("其他");
				
				//許可證字
				XSSFCell a4 = row.createCell(4);
				if(!"".equals(Common.get(x[3])))
				   a4.setCellValue(pkidMap.get(Common.get(x[3])));
				else
				   a4.setCellValue("其他");
					
				
				//許可證號
				XSSFCell a5 = row.createCell(5);
				if(!"".equals(Common.get(x[4])))
				   a5.setCellValue(Common.get(x[4]));
				else
				   a5.setCellValue("其他");
					
				//申請商
				XSSFCell a6 = row.createCell(6);	
				if(!"".equals(Common.get(x[5])))
				   a6.setCellValue(Common.get(x[5]));
				else
				   a6.setCellValue("其他");
					
				//製造廠
				XSSFCell a7 = row.createCell(7);
				if(!"".equals(Common.get(x[6])))
				   a7.setCellValue(Common.get(x[6]));
				else
				   a7.setCellValue("其他");
					
				//品名
				XSSFCell a8 = row.createCell(8);
				if(!"".equals(Common.get(x[7])))
				   a8.setCellValue(Common.get(x[7]));
				else
				   a8.setCellValue("其他");
					
				//件數
				XSSFCell a9 = row.createCell(9);										
				a9.setCellValue("1");
				
				rowCount++;
	    	 }
			 rowCount = rowCount+100;
			 //月份 塞滿12個月			 
			 for (int j=1; j< 13; j++) 
	    	 {
				 XSSFRow row = sheet.createRow(rowCount);
				 XSSFCell a3 = row.createCell(3);
				 a3.setCellValue(Common.formatFrontZero(Common.get(j),2));				 
				 rowCount++;
	    	 }
				
			 st.close();
		 }
		 
		 // 寫入新檔案
		 File oFile = new File("pivot0701r_"+Datetime.getHHMMSS()+".xlsx");
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
	
	public String queryHql()
	{
		
		StringBuilder hql = new StringBuilder();
		
		if(!"".equals(getQ_dateS()))
			hql.append(" and cast(substring(handdate,1,5)as int) >=" + getQ_dateS());
	    	
		if(!"".equals(getQ_dateE()))
			hql.append(" and cast(substring(handdate,1,5)as int)  <=" + getQ_dateE());
		 
		if(!"".equals(getQ_permitKey()))
			hql.append(" and permitKey=" + Common.sqlChar(getQ_permitKey()));
		
		if(!"".equals(getQ_permitKey()))
			hql.append(" and q_permitNo=" + getQ_permitNo());
		
		if(!"".equals(getQ_applicationName()))
			hql.append(" and applicationName like "+Common.likeSqlChar(getQ_applicationName()));
		
		if(!"".equals(getQ_manufactorName()))
			hql.append(" and manufactorName like "+Common.likeSqlChar(getQ_manufactorName()));
		
		if(!"".equals(getQ_chProduct()))
			hql.append(" and chProduct like "+Common.likeSqlChar(getQ_chProduct()));
		
		return hql.toString();

	}



	public String getQ_dateS() {return checkGet(q_dateS);}
	public void setQ_dateS(String qDateS) {q_dateS = checkSet(qDateS);}
	public String getQ_dateE() {return checkGet(q_dateE);}
	public void setQ_dateE(String qDateE) {q_dateE = checkSet(qDateE);}

	public String getQ_permitKey() {return checkGet(q_permitKey);}
	public void setQ_permitKey(String qPermitKey) {q_permitKey = checkSet(qPermitKey);}

	public String getQ_permitNo() {return checkGet(q_permitNo);}
	public void setQ_permitNo(String qPermitNo) {q_permitNo = checkSet(qPermitNo);}

	public String getQ_applicationName() {return checkGet(q_applicationName);}
	public void setQ_applicationName(String qApplicationName) {q_applicationName = checkSet(qApplicationName);}

	public String getQ_manufactorName() {return checkGet(q_manufactorName);}
	public void setQ_manufactorName(String qManufactorName) {q_manufactorName = checkSet(qManufactorName);}


	public String getQ_chProduct() {return checkGet(q_chProduct);}
	public void setQ_chProduct(String qChProduct) {q_chProduct = checkSet(qChProduct);}

	@Override
	public void doCreate() throws Exception {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void doDelete() throws Exception {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Object doQueryAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Object doQueryOne() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void doUpdate() throws Exception {
		// TODO Auto-generated method stub
		
	}

}
