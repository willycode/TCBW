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


public class PIVOT0601R extends SuperBean
{

	private String q_dateS;
	private String q_dateE;
	
	private String q_permitKey;
	private String q_permitNo;
	private String q_applicationName;
	private String q_manufactorName;
	private String q_productName;
	
	
	public void exportExcelTable(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		// 讀取EXCEL樣版
		InputStream st = new FileInputStream(new File(MyServletContext.getInstance().getServletContext().getRealPath("/report/pivot/pivot0601r.xlsx")));
		XSSFWorkbook workbook = new XSSFWorkbook(st);
		
    	StringBuilder hql = new StringBuilder();
    	
    	hql.append(" select applNo, notifierRepYear, notifierRepMonth, permitKey, permitNo,"); //0-4
    	hql.append(" applicationName, manufactorName, productName, approvedUnits");            //5-8 	
    	hql.append(" from pivotView06 where 1=1 ");
    	
    	hql.append(queryHql());	
    	
    	hql.append(" order by applNo");
    	
    	System.out.println("hql=="+hql.toString());
    	java.util.List list = ServiceGetter.getInstance().getTcbwService().getNativeSqlQuery().load(hql.toString());   	

		if (list!=null && list.size()>0) 			
		{
			 java.util.Map<String, String> pkidMap = TCBWCommon.getCommonCodeMap("MEDPKID");	//許可證字
			 java.util.Map<String, String> unitMap = new java.util.HashMap<String, String>();   //列管單位
			 unitMap.put("1","醫事司");
			 unitMap.put("2","食品藥物管理署");
			 unitMap.put("3","其他");
			 
			 // 處理工作SHEET
			 XSSFSheet sheet = workbook.getSheetAt(0);

			 int rowCount=1;
			 for (int j=0; j<list.size(); j++) 
	    	 {				
				 Object x[] = (Object[])list.get(j);	
				 XSSFRow row = sheet.createRow(rowCount);

				 //案號
				 XSSFCell a0 = row.createCell(0);										
				 a0.setCellValue(!Common.get(x[0]).equals("")?Common.get(x[0]):"其他");
				 //年度
				 XSSFCell a1 = row.createCell(1);										
				 a1.setCellValue(!Common.get(x[1]).equals("")?Common.get(x[1]):"其他");
				 //月份
				 XSSFCell a2 = row.createCell(2);										
				 a2.setCellValue(!Common.get(x[2]).equals("")?Common.get(x[2]):"其他");
				 //許可證字
				 XSSFCell a3 = row.createCell(3);										
				 a3.setCellValue(!Common.get(x[3]).equals("")?pkidMap.get(Common.get(x[3])):"其他");
				 //許可證號
				 XSSFCell a4 = row.createCell(4);										
				 a4.setCellValue(!Common.get(x[4]).equals("")?Common.get(x[4]):"其他");
				 //申請商
			 	 XSSFCell a5 = row.createCell(5);										
		 		 a5.setCellValue(!Common.get(x[5]).equals("")?Common.get(x[5]):"其他");
				 //製造廠
				 XSSFCell a6 = row.createCell(6);										
				 a6.setCellValue(!Common.get(x[6]).equals("")?Common.get(x[6]):"其他");
				 //品名
				 XSSFCell a7 = row.createCell(7);										
				 a7.setCellValue(!Common.get(x[7]).equals("")?Common.get(x[7]):"其他");				
				 //列管單位				
				 XSSFCell a8 = row.createCell(8);										
				 a8.setCellValue(!Common.get(x[8]).equals("")?unitMap.get(Common.get(x[8])):"其他");
				 //件數
				 XSSFCell a9 = row.createCell(9);										
				 a9.setCellValue(1);				 

				rowCount++;	    	 
	    	 }
			 rowCount = rowCount+100;
			 //月份 塞滿12個月			 
			 for (int j=1; j< 13; j++) 
	    	 {
				 XSSFRow row = sheet.createRow(rowCount);
				 XSSFCell a2 = row.createCell(2);
				 a2.setCellValue(Common.formatFrontZero(Common.get(j),2));				 
				 rowCount++;
	    	 }
			 
			 st.close();
			 pkidMap.clear();
			 unitMap.clear();
		}
		 		
		if (list!=null && list.size()>0) 			
		{
		 
			// 寫入新檔案		 
			File oFile = new File("pivot0601r_"+Datetime.getHHMMSS()+".xlsx");		 
			FileOutputStream osFile = new FileOutputStream(oFile);		 
			workbook.write(osFile);		 
			osFile.close();		 	
		 
			// 輸出檔案		 
			OutputStream os = null;		 
			FileInputStream is = null;		 
			try{
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
			}catch(Exception x){
		    	try	{
		    		response.sendError(500,"產製錯誤！");
		    	}catch(Exception e){
		    		e.printStackTrace();
		    	}		  
			}finally{
		    	if (os!=null) os.close();
		        if (is!=null) is.close();
		        oFile.delete();
			} 
		}else{
			throw new Exception("查無資料！");
		}		
	}
	
	public String queryHql()
	{
		
		StringBuilder hql = new StringBuilder();
		

		if(!"".equals(getQ_dateS()))
			hql.append(" and cast(substring(notifierRepDate,1,5)as int) >=" + getQ_dateS());
	    	
		if(!"".equals(getQ_dateE()))
			hql.append(" and cast(substring(notifierRepDate,1,5)as int)  <=" + getQ_dateE());
    	
		if(!"".equals(getQ_permitKey()))
			hql.append(" and permitKey=" + Common.sqlChar(getQ_permitKey()));
		
		if(!"".equals(getQ_permitNo()))
			hql.append(" and permitNo=" + Common.sqlChar(getQ_permitNo()));
		
		if(!"".equals(getQ_applicationName()))
			hql.append(" and applicationName like "+Common.likeSqlChar(getQ_applicationName()));
		
		if(!"".equals(getQ_manufactorName()))
			hql.append(" and manufactorName like "+Common.likeSqlChar(getQ_manufactorName()));
		
		if(!"".equals(getQ_productName()))
			hql.append(" and productName like "+Common.likeSqlChar(getQ_productName()));
		
		
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

    public String getQ_productName() { return checkGet(q_productName);	}
	public void setQ_productName(String q_productName) { this.q_productName = checkSet(q_productName); }

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
