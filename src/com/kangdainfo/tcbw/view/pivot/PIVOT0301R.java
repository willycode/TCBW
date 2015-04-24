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


public class PIVOT0301R extends SuperBean
{

	private String q_dateS;
	private String q_dateE;
	
	private String q_permitKey;
	private String q_permitNo;
	private String q_applicationName;

	
	
	public void exportExcelTable(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		
		// 讀取EXCEL樣版
		InputStream st = new FileInputStream(new File(MyServletContext.getInstance().getServletContext().getRealPath("/report/pivot/pivot0301r.xlsx")));

		XSSFWorkbook workbook = new XSSFWorkbook(st);
		
    	StringBuilder hql = new StringBuilder();

    	hql.append(" select distinct applno, publDate, publDateYear, ");
    	hql.append(" publDateMonth, publDept, situation, recycleType,");
    	hql.append(" permitKey,permitNo,applicationName");
    	hql.append(" from pivotView03 where 1=1 ");
    	
    	hql.append(queryHql());		
    	
    	//案件編號、許可證字號
    	hql.append(" order by applNo,permitKey,permitNo");
    	
    	java.util.List list = ServiceGetter.getInstance().getTcbwService().getNativeSqlQuery().load(hql.toString());
		
    	System.out.println("hql=="+hql.toString());

		if (list!=null && list.size()>0) 			
		{
			 java.util.Map<String, String> conpublDeptMap = TCBWCommon.getCommonCodeMap("CONPUBLDEPT");//發布單位			 
			 java.util.Map<String, String> conwarningMap = TCBWCommon.getCommonCodeMap("CONWARNING");//狀況
			 java.util.Map<String, String> drgformMap = TCBWCommon.getCommonCodeMap("DRGFORM");//劑型
			 java.util.Map<String, String> drgwntpMap = TCBWCommon.getCommonCodeMap("DRGWNTP");//品質異常型態
			 java.util.Map<String, String> drgrectpMap = TCBWCommon.getCommonCodeMap("DRGRECTP");//回收型態
			 java.util.Map<String, String> pkidMap = TCBWCommon.getCommonCodeMap("DRGPKID");
			 
			 // 處理工作SHEET
			 XSSFSheet sheet = workbook.getSheetAt(0);

			 int rowCount=1;
			 for (int j=0; j<list.size(); j++) 
	    	 {
				Object x[] = (Object[])list.get(j);	
				XSSFRow row = sheet.createRow(rowCount);		
				
				//案號
				XSSFCell a0 = row.createCell(0);										
				a0.setCellValue(Common.get(x[0]));
				
				//年度
				XSSFCell a1 = row.createCell(1);
				if(!"".equals(Common.get(x[2])))
				  a1.setCellValue(Common.get(x[2]));
				else
				  a1.setCellValue("其他");				
				//月份
				XSSFCell a2 = row.createCell(2);	
				if(!"".equals(Common.get(x[3])))
				a2.setCellValue(Common.get(x[3]));
				else
				a2.setCellValue("其他");				
				//發布單位
				XSSFCell a3 = row.createCell(3);
				if(!"".equals(Common.get(x[4])))
				a3.setCellValue(conpublDeptMap.get(Common.get(x[4])));
				else
				a3.setCellValue("其他");				
				//狀況
				XSSFCell a4 = row.createCell(4);	
				if(!"".equals(Common.get(x[5])))
				a4.setCellValue(conwarningMap.get(Common.get(x[5])));
				else
				a4.setCellValue("其他");				
				//回收型態
				XSSFCell a5 = row.createCell(5);
				if(!"".equals(Common.get(x[6])))
				a5.setCellValue(drgrectpMap.get(Common.get(x[6])));
				else
				a5.setCellValue("其他");				
				//許可證字
				XSSFCell a6 = row.createCell(6);
				if(!"".equals(Common.get(x[7])))
				a6.setCellValue(pkidMap.get(Common.get(x[7])));
				else
				a6.setCellValue("其他");				
				//許可證號
				XSSFCell a7 = row.createCell(7);
				if(!"".equals(Common.get(x[8])))
				a7.setCellValue(Common.get(x[8]));
				else
				a7.setCellValue("其他");				
				//許可證持有商
				XSSFCell a8 = row.createCell(8);	
				if(!"".equals(Common.get(x[9])))
				a8.setCellValue(Common.get(x[9]));
				else
				a8.setCellValue("其他");				
				//件數
				XSSFCell a9 = row.createCell(9);	
				a9.setCellValue("1");				
				//許可證字號
				XSSFCell a10 = row.createCell(10);										
				String pkid="";
				if(!"".equals(Common.get(x[7])))
				  pkid+=Common.get(pkidMap.get(x[7]))+"字";
				if(!"".equals(Common.get(x[8])))
					pkid+="第"+Common.get(x[8])+"號";
					
				if(!"".equals(Common.get(pkid)))
				a10.setCellValue(pkid);
				else
				a10.setCellValue("其他");
				
				rowCount++;	    	 
	    	 }		
			 
			 //sheet2  案件明細(依案件)
             StringBuilder hql1 = new StringBuilder();
			 
             hql1.append(" select distinct applno, publDate, publDateYear, ");
             hql1.append(" publDateMonth, publDept, situation, ");
             hql1.append(" recycleType ");
             hql1.append(" from pivotView03 where 1=1 ");

			 hql1.append(queryHql());
			 
			 hql.append(" order by applno");
			 
		     java.util.List list1 = ServiceGetter.getInstance().getTcbwService().getNativeSqlQuery().load(hql1.toString());
				
		     System.out.println("hql1=="+hql1.toString());
			 
			 XSSFSheet sheet1 = workbook.getSheetAt(1);
			 
			 int rowCount1=1;
			
			 for (int j=0; j<list1.size(); j++) 
	    	 {
				Object x[] = (Object[])list1.get(j);	
				XSSFRow row = sheet1.createRow(rowCount1);		
				
				//案號
				XSSFCell a0 = row.createCell(0);										
				a0.setCellValue(Common.get(x[0]));				
				//年度
				XSSFCell a1 = row.createCell(1);
				if(!"".equals(Common.get(x[2])))
				  a1.setCellValue(Common.get(x[2]));
				else
				  a1.setCellValue("其他");				
				//月份
				XSSFCell a2 = row.createCell(2);	
				if(!"".equals(Common.get(x[3])))
				a2.setCellValue(Common.get(x[3]));
				else
				a2.setCellValue("其他");				
				//發布單位
				XSSFCell a3 = row.createCell(3);
				if(!"".equals(Common.get(x[4])))
				a3.setCellValue(conpublDeptMap.get(Common.get(x[4])));
				else
				a3.setCellValue("其他");				
				//狀況
				XSSFCell a4 = row.createCell(4);	
				if(!"".equals(Common.get(x[5])))
				a4.setCellValue(conwarningMap.get(Common.get(x[5])));
				else
				a4.setCellValue("其他");				
				//回收型態
				XSSFCell a5 = row.createCell(5);
				if(!"".equals(Common.get(x[6])))
				a5.setCellValue(drgrectpMap.get(Common.get(x[6])));
				else
				a5.setCellValue("其他");					
				//件數
				XSSFCell a6 = row.createCell(6);	
				a6.setCellValue("1");
				
				rowCount1++;
	    	 }
			 
			 //sheet3  案件明細(依劑型)
             StringBuilder hql2 = new StringBuilder();
			 
             hql2.append(" select distinct applno, publDate, publDateYear, ");
             hql2.append(" publDateMonth, publDept, situation, ");
             hql2.append(" recycleType, warningmedModel ");
             hql2.append(" from pivotView03 where 1=1 ");

			 hql2.append(queryHql());
			 
			 hql2.append(" order by applno");
			 
		     java.util.List list2 = ServiceGetter.getInstance().getTcbwService().getNativeSqlQuery().load(hql2.toString());
				
		     System.out.println("hql2=="+hql2.toString());
			 
			 XSSFSheet sheet2 = workbook.getSheetAt(2);
			 
			 int rowCount2=1;
			
			 for (int j=0; j<list2.size(); j++) 
	    	 {
				Object x[] = (Object[])list2.get(j);	
				XSSFRow row = sheet2.createRow(rowCount2);		
				
				//案號
				XSSFCell a0 = row.createCell(0);										
				a0.setCellValue(Common.get(x[0]));
				//年度
				XSSFCell a1 = row.createCell(1);
				if(!"".equals(Common.get(x[2])))
				  a1.setCellValue(Common.get(x[2]));
				else
				  a1.setCellValue("其他");				
				//月份
				XSSFCell a2 = row.createCell(2);	
				if(!"".equals(Common.get(x[3])))
				a2.setCellValue(Common.get(x[3]));
				else
				a2.setCellValue("其他");				
				//發布單位
				XSSFCell a3 = row.createCell(3);
				if(!"".equals(Common.get(x[4])))
				a3.setCellValue(conpublDeptMap.get(Common.get(x[4])));
				else
				a3.setCellValue("其他");				
				//狀況
				XSSFCell a4 = row.createCell(4);	
				if(!"".equals(Common.get(x[5])))
				a4.setCellValue(conwarningMap.get(Common.get(x[5])));
				else
				a4.setCellValue("其他");				
				//回收型態
				XSSFCell a5 = row.createCell(5);
				if(!"".equals(Common.get(x[6])))
				a5.setCellValue(drgrectpMap.get(Common.get(x[6])));
				else
				a5.setCellValue("其他");				
				//劑型
				XSSFCell a6 = row.createCell(6);
				if(!"".equals(Common.get(x[7])))
				a6.setCellValue(drgformMap.get(Common.get(x[7])));
				else
				a6.setCellValue("其他");
				//件數
				XSSFCell a7 = row.createCell(7);	
				a7.setCellValue("1");
				
				rowCount2++;
	    	 }
			 
			 //sheet3 案件明細(依品質異常型態)
             StringBuilder hql3 = new StringBuilder();
			 
             hql3.append(" select distinct applno, publDate, publDateYear, ");
             hql3.append(" publDateMonth, publDept, situation, ");
             hql3.append(" recycleType, qualitywarningtype ");
             hql3.append(" from pivotView03 where 1=1 ");

			 hql3.append(queryHql());
			 
			 hql3.append(" order by applno");
			 
		     java.util.List list3 = ServiceGetter.getInstance().getTcbwService().getNativeSqlQuery().load(hql3.toString());
				
		     System.out.println("hql3=="+hql3.toString());
			 
			 XSSFSheet sheet3 = workbook.getSheetAt(3);
			 
			 int rowCount3=1;
			
			 for (int j=0; j<list3.size(); j++) 
	    	 {
				Object x[] = (Object[])list3.get(j);	
				XSSFRow row = sheet3.createRow(rowCount3);		
				
				//案號
				XSSFCell a0 = row.createCell(0);										
				a0.setCellValue(Common.get(x[0]));
				//年度
				XSSFCell a1 = row.createCell(1);
				if(!"".equals(Common.get(x[2])))
				  a1.setCellValue(Common.get(x[2]));
				else
				  a1.setCellValue("其他");				
				//月份
				XSSFCell a2 = row.createCell(2);	
				if(!"".equals(Common.get(x[3])))
				a2.setCellValue(Common.get(x[3]));
				else
				a2.setCellValue("其他");				
				//發布單位
				XSSFCell a3 = row.createCell(3);
				if(!"".equals(Common.get(x[4])))
				a3.setCellValue(conpublDeptMap.get(Common.get(x[4])));
				else
				a3.setCellValue("其他");				
				//狀況
				XSSFCell a4 = row.createCell(4);	
				if(!"".equals(Common.get(x[5])))
				a4.setCellValue(conwarningMap.get(Common.get(x[5])));
				else
				a4.setCellValue("其他");				
				//回收型態
				XSSFCell a5 = row.createCell(5);
				if(!"".equals(Common.get(x[6])))
				a5.setCellValue(drgrectpMap.get(Common.get(x[6])));
				else
				a5.setCellValue("其他");	
				//品質異常型態
				XSSFCell a6 = row.createCell(6);
				if(!"".equals(Common.get(x[7])))
				a6.setCellValue(drgwntpMap.get(Common.get(x[7])));
				else
				a6.setCellValue("其他");			
				//件數
				XSSFCell a7 = row.createCell(7);	
				a7.setCellValue("1");
				
				rowCount3++;
	    	 }
			 
			 
			 
			 st.close();
			
		}
		 		
		if (list!=null && list.size()>0) 			
		{
		 // 寫入新檔案
		 File oFile = new File("pivot0301r_"+Datetime.getHHMMSS()+".xlsx");
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
		}else{
			throw new Exception("查無資料！");
		}
		
		
	}
	
	public String queryHql()
	{
		
		StringBuilder hql = new StringBuilder();
		

		if(!"".equals(getQ_dateS()))
			hql.append(" and cast(substring(publDate,1,5)as int) >=" + getQ_dateS());
	    	
		if(!"".equals(getQ_dateE()))
			hql.append(" and cast(substring(publDate,1,5)as int)  <=" + getQ_dateE());
		
    	
		if(!"".equals(getQ_permitKey()))
			hql.append(" and permitKey=" + Common.sqlChar(getQ_permitKey()));
		
		if(!"".equals(getQ_permitNo()))
			hql.append(" and permitNo=" + Common.sqlChar(getQ_permitNo()));
		
		if(!"".equals(getQ_applicationName()))
			hql.append(" and applicationName like "+Common.likeSqlChar(getQ_applicationName()));
		
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
