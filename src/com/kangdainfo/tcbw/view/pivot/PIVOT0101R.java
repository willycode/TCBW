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


public class PIVOT0101R extends SuperBean
{

	private String q_dateS;
	private String q_dateE;

	
	
	private String q_permitKey;
	private String q_permitNo;
	private String q_applicationName;
	private String q_manufactorName;
	
	public void exportExcelTable(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		
		// 讀取EXCEL樣版
		 InputStream st = new FileInputStream(new File(MyServletContext.getInstance().getServletContext().getRealPath("/report/pivot/pivot0101r.xlsx")));

		 XSSFWorkbook workbook = new XSSFWorkbook(st);
		
    	StringBuilder hql = new StringBuilder();
    	
    	hql.append(" select applNo,notifierRepYear,notifierRepMonth, ");
    	hql.append(" permitKey,permitNo,applicationName,manufactorName, ");
    	hql.append(" engageKind,notifierSource,medModel, ");
    	hql.append(" manufactorCountry,mainCode,subCode, ");
    	hql.append(" drgLev,notifierAging,notifierQuality,produceType, ");
    	hql.append(" chProduct,enProduct ");
    	hql.append(" from pivotView01 where 1=1 ");
    	
    	hql.append(queryHql());
		
    	hql.append(" order by applNo,mainCode");
    	
    	java.util.List list = ServiceGetter.getInstance().getTcbwService().getNativeSqlQuery().load(hql.toString());
		
    	System.out.println("hql=="+hql.toString());

		if (list!=null && list.size()>0) 			
		{
			 java.util.Map<String, String> pkidMap = TCBWCommon.getCommonCodeMap("DRGPKID");
			 java.util.Map<String, String> nfsMap = TCBWCommon.getCommonCodeMap("DRGNFS");
			 java.util.Map<String, String> flnMap = TCBWCommon.getCommonCodeMap("DRGFLN");
			 java.util.Map<String, String> mainCodeMap = TCBWCommon.getCommonCodeMap("DRG0105");			 
			 java.util.Map<String, String> agingMap = new java.util.HashMap<String, String>();
			 agingMap.put("1","時效佳");
			 agingMap.put("2","時效待加強功");
			 java.util.Map<String, String> qualityMap = new java.util.HashMap<String, String>();
			 qualityMap.put("1","Excellent");
			 qualityMap.put("2","Good");
			 qualityMap.put("3","Fair");
			 
			 java.util.Map<String, String> subCodeMap = TCBWCommon.getMap("select dpdKind,dpdKindName from Drg1001Db");

			 
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
				
				//藥商名稱
				XSSFCell a6 = row.createCell(6);
				if(!"".equals(Common.get(x[5])))
				  a6.setCellValue(Common.get(x[5]));
				else
				  a6.setCellValue("其他");
				
				//製造商
				XSSFCell a7 = row.createCell(7);	
				if(!"".equals(Common.get(x[6])))
				  a7.setCellValue(Common.get(x[6]));
				else
				  a7.setCellValue("其他");
				
				//醫療機構類別
				XSSFCell a8 = row.createCell(8);	
				if(!"".equals(Common.get(x[7])))
				  a8.setCellValue(Common.get(x[7]));
				else
				  a8.setCellValue("其他");
				
				//通報來源
				XSSFCell a9 = row.createCell(9);
				if(!"".equals(Common.get(x[8])))
				  a9.setCellValue(nfsMap.get(Common.get(x[8])));
				else
				  a9.setCellValue("其他");
				
				//劑型
				XSSFCell a10 = row.createCell(10);		
				if(!"".equals(Common.get(x[9])))
				  a10.setCellValue(flnMap.get(Common.get(x[9])));
				else
				  a10.setCellValue("其他");
				
				//製造廠國別
				XSSFCell a11 = row.createCell(11);	
				if(!"".equals(Common.get(x[10])))
				  a11.setCellValue(Common.get(x[10]));
				else
				  a11.setCellValue("其他");
				
				
				//國產/輸入
				XSSFCell a12 = row.createCell(12);
				String inorout=Common.get(x[10]);
			
				if("台灣".equals(inorout) || "臺灣".equals(inorout) || "TAIWAN".equals(inorout))
				{
				  a12.setCellValue("國產");
				}
				else if(!"".equals(inorout))
				{
				  a12.setCellValue("輸入");
				}
				else
				{	
				  a12.setCellValue("其他");
				}
				
				//不良品缺陷大類
				XSSFCell a13 = row.createCell(13);
				if(!"".equals(Common.get(x[11])))
				  a13.setCellValue(mainCodeMap.get(Common.get(x[11])));
				else
				  a13.setCellValue("其他");
					
				
				//不良品缺陷小類
				XSSFCell a14 = row.createCell(14);	
				if(!"".equals(Common.get(x[12])))
				  a14.setCellValue(subCodeMap.get(Common.get(x[12])));
				else
				  a14.setCellValue("其他");
				
				//高風險非高風險
				XSSFCell a15 = row.createCell(15);	
				if(!"".equals(Common.get(x[13])))
				{	
				  if("02".equals(Common.get(x[13])) || "03".equals(Common.get(x[13])) )	
				    a15.setCellValue("高風險");
				  else
					a15.setCellValue("非高風險");
				}
				else
				{
				  a15.setCellValue("其他");
				}
				//通報時效
				XSSFCell a16 = row.createCell(16);	
				if(!"".equals(Common.get(x[14])))
				  a16.setCellValue(agingMap.get(Common.get(x[14])));
				else
				  a16.setCellValue("其他");
				
				//通報品質
				XSSFCell a17 = row.createCell(17);	
				if(!"".equals(Common.get(x[15])))
				  a17.setCellValue(qualityMap.get(Common.get(x[15])));
				else
				  a17.setCellValue("其他");
				
				//件數
				XSSFCell a18 = row.createCell(18);	
				a18.setCellValue("1");
				
				//許可證字號
				XSSFCell a19 = row.createCell(19);										
				String pkid="";
				
				if(!"".equals(Common.get(x[3])))
				  pkid+=Common.get(pkidMap.get(x[3]))+"字";
				
				if(!"".equals(Common.get(x[4])))
					pkid+="第"+Common.get(x[4])+"號";
					
				if(!"".equals(Common.get(pkid)))
				  a19.setCellValue(pkid);
				else
				  a19.setCellValue("其他");
				
				//中文品名
				XSSFCell a20 = row.createCell(20);
				if(!"".equals(Common.get(x[17])))
				  a20.setCellValue(Common.get(x[17]));
				else
				  a20.setCellValue("其他");
				
				//英文品名
				XSSFCell a21 = row.createCell(21);
				if(!"".equals(Common.get(x[18])))
				  a21.setCellValue(Common.get(x[18]));
				else
				  a21.setCellValue("其他");
				
				
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
				
			 StringBuilder hql1 = new StringBuilder();
			 
			 hql1.append(" select distinct applNo,notifierRepYear,notifierRepMonth, ");
			 hql1.append(" permitKey,permitNo,applicationName,manufactorName, ");
			 hql1.append(" engageKind,notifierSource,medModel, ");
			 hql1.append(" manufactorCountry, ");
			 hql1.append(" drgLev,notifierAging,notifierQuality,produceType, ");
			 hql1.append(" chProduct,enProduct ");
			 hql1.append(" from pivotView01 where 1=1 ");
			 
			 hql1.append(queryHql());
			 
			 hql.append(" order by applNo");
			 
		     java.util.List list1 = ServiceGetter.getInstance().getTcbwService().getNativeSqlQuery().load(hql1.toString());
				
		     System.out.println("hql1=="+hql1.toString());
			 
			 XSSFSheet sheet1 = workbook.getSheetAt(1);
			 
			 int rowCount1=1;
			 for (int j=0; j<list1.size(); j++) 
	    	 {
				Object x[] = (Object[])list1.get(j);	
				XSSFRow row = sheet1.createRow(rowCount1);		
				
				XSSFCell a0 = row.createCell(0);										
				a0.setCellValue(rowCount1);
				
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
				
				//藥商名稱
				XSSFCell a6 = row.createCell(6);
				if(!"".equals(Common.get(x[5])))
				  a6.setCellValue(Common.get(x[5]));
				else
				  a6.setCellValue("其他");
				
				//製造商
				XSSFCell a7 = row.createCell(7);	
				if(!"".equals(Common.get(x[6])))
				  a7.setCellValue(Common.get(x[6]));
				else
				  a7.setCellValue("其他");
				
				//醫療機構類別
				XSSFCell a8 = row.createCell(8);										
				if(!"".equals(Common.get(x[7])))
				  a8.setCellValue(Common.get(x[7]));
				else
				  a8.setCellValue("其他");
				
				//通報來源
				XSSFCell a9 = row.createCell(9);
				if(!"".equals(Common.get(x[8])))
				  a9.setCellValue(nfsMap.get(Common.get(x[8])));
				else
				  a9.setCellValue("其他");
				
				//劑型
				XSSFCell a10 = row.createCell(10);										
				if(!"".equals(Common.get(x[9])))
				  a10.setCellValue(flnMap.get(Common.get(x[9])));
				else
				  a10.setCellValue("其他");
				
				//製造廠國別
				XSSFCell a11 = row.createCell(11);										
				if(!"".equals(Common.get(x[10])))
				  a11.setCellValue(Common.get(x[10]));
				else
				  a11.setCellValue("其他");
				
				//國產/輸入
				XSSFCell a12 = row.createCell(12);	
				
				String inorout=Common.get(x[10]);
				
				if("台灣".equals(inorout) || "臺灣".equals(inorout) || "TAIWAN".equals(inorout))
				{
				  a12.setCellValue("國產");
				}
				else if(!"".equals(inorout))
				{
				  a12.setCellValue("輸入");
				}
				else
				{	
				  a12.setCellValue("其他");
				}
			
	
				//高風險非高風險
				XSSFCell a13 = row.createCell(13);										
				if(!"".equals(Common.get(x[11])))
				{	
				  if("02".equals(Common.get(x[11])) || "03".equals(Common.get(x[11])) )	
					 a13.setCellValue("高風險");
				  else
					 a13.setCellValue("非高風險");
				}
				else
				{	
				  a13.setCellValue("其他");
				}
				
				//通報時效
				XSSFCell a14 = row.createCell(14);										
				if(!"".equals(Common.get(x[12])))
				  a14.setCellValue(agingMap.get(Common.get(x[12])));
				else
				  a14.setCellValue("其他");
				
				//通報品質
				XSSFCell a15 = row.createCell(15);	
				if(!"".equals(Common.get(x[13])))
				  a15.setCellValue(qualityMap.get(Common.get(x[13])));
				else
				  a15.setCellValue("其他");
				
				//件數
				XSSFCell a16 = row.createCell(16);	
				a16.setCellValue("1");
				
				
				//許可證字號
				XSSFCell a17 = row.createCell(17);										
				String pkid="";
				
				if(!"".equals(Common.get(x[3])))
				  pkid+=Common.get(pkidMap.get(x[3]))+"字";
				
				if(!"".equals(Common.get(x[4])))
					pkid+="第"+Common.get(x[4])+"號";
					
				if(!"".equals(Common.get(pkid)))
				  a17.setCellValue(pkid);
				else
				  a17.setCellValue("其他");
				
				//中文品名
				XSSFCell a18 = row.createCell(18);
				if(!"".equals(Common.get(x[15])))
				  a18.setCellValue(Common.get(x[15]));
				else
				  a18.setCellValue("其他");
				
				//英文品名
				XSSFCell a19 = row.createCell(19);
				if(!"".equals(Common.get(x[16])))
				  a19.setCellValue(Common.get(x[16]));
				else
				  a19.setCellValue("其他");
				
				rowCount1++;
	    	 }
			 rowCount1 = rowCount1+100;
			 //月份 塞滿12個月			 
			 for (int j=1; j< 13; j++) 
	    	 {
				 XSSFRow row = sheet1.createRow(rowCount1);
				 XSSFCell a3 = row.createCell(3);
				 a3.setCellValue(Common.formatFrontZero(Common.get(j),2));				 
				 rowCount1++;
	    	 }
			 
			 
			 st.close();
		 }
		 
		 // 寫入新檔案
		 File oFile = new File("pivot0101r_"+Datetime.getHHMMSS()+".xlsx");
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
			hql.append(" and cast(substring(notifierRepDate,1,5)as int) >=" + getQ_dateS());
	    	
		if(!"".equals(getQ_dateE()))
			hql.append(" and cast(substring(notifierRepDate,1,5)as int)  <=" + getQ_dateE());
		 
		if(!"".equals(getQ_permitKey()))
			hql.append(" and permitKey=" + Common.sqlChar(getQ_permitKey()));
		
		if(!"".equals(getQ_permitKey()))
			hql.append(" and permitNo=" + getQ_permitNo());
		
		if(!"".equals(getQ_applicationName()))
			hql.append(" and applicationName like "+Common.likeSqlChar(getQ_applicationName()));
		
		if(!"".equals(getQ_manufactorName()))
			hql.append(" and manufactorName like "+Common.likeSqlChar(getQ_manufactorName()));
		
		
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
