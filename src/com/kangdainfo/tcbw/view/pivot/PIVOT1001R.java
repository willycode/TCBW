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


public class PIVOT1001R extends SuperBean
{

	private String q_dateS;
	private String q_dateE;	
	
	
	private String q_permitKey;
	private String q_permitNo;	
	private String q_manufactorName;
	private String q_productName;
	
	
	public void exportExcelTable(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		
		// 讀取EXCEL樣版
		InputStream st = new FileInputStream(new File(MyServletContext.getInstance().getServletContext().getRealPath("/report/pivot/pivot1001r.xlsx")));

		XSSFWorkbook workbook = new XSSFWorkbook(st);
		
    	StringBuilder hql = new StringBuilder();
    	
    	hql.append(" select applNo, notifierRepYear, notifierRepMonth, ");          //0-2
    	hql.append(" permitKey, permitNo, manufactorName, chProduct,");             //3-6
    	hql.append(" notifierArea, notifierSource, traffickWay, isDamageYn,");      //7-10
    	hql.append(" cosType, ingredient, mainCode, subCode"); //11-16
    	hql.append(" from pivotView10 where cosType='1' ");
    	
    	hql.append(queryHql());		
    	java.util.List list = ServiceGetter.getInstance().getTcbwService().getNativeSqlQuery().load(hql.toString());
		
    	System.out.println("hql=="+hql.toString());

		if (list!=null && list.size()>0) 			
		{
			 java.util.Map<String, String> pkidMap = TCBWCommon.getCommonCodeMap("CPT");  //許可證字	
			 java.util.Map<String, String> ctyMap = TCBWCommon.getCommonCodeMap("CTY");   //地區
			 java.util.Map<String, String> cisMap = TCBWCommon.getCommonCodeMap("CIS");	  //通報來源
			 java.util.Map<String, String> cspMap = TCBWCommon.getCommonCodeMap("CSP");	  //販售通路
			 java.util.Map<String, String> dpdMap = TCBWCommon.getCommonCodeMap("COSDPD");	  //不良品缺陷(大)			 
			 java.util.Map<String, String> subCodeMap = TCBWCommon.getMap("select dpdKind,dpdKindName from Cos1001Db"); //不良品缺陷(小)
			 java.util.Map<String, String> cctMap = TCBWCommon.getCommonCodeMap("CCT");	  //不良事件類別
			 java.util.Map<String, String> cciMap = TCBWCommon.getCommonCodeMap("CCI");	  //化粧品項目
			 java.util.Map<String, String> cacMap = TCBWCommon.getCommonCodeMap("CAC");	  //不良反應結果
			 java.util.Map<String, String> cosarMap = TCBWCommon.getCommonCodeMap("COSAR");	  //不良反應症狀			 
			 java.util.Map<String, String> isYnMap = new java.util.HashMap<String, String>();
			 isYnMap.put("Y","是");
			 isYnMap.put("N","否");

			 
			 // 處理工作SHEET
			 XSSFSheet sheet = workbook.getSheetAt(0);

			 int rowCount=1;
			 for (int j=0; j<list.size(); j++) 
	    	 {
				Object x[] = (Object[])list.get(j);	
				XSSFRow row = sheet.createRow(rowCount);		
				//化粧品品名										

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
				//國內製造廠/國外產品進口(代理)商
				XSSFCell a5 = row.createCell(5);										
				a5.setCellValue(!Common.get(x[5]).equals("")?Common.get(x[5]):"其他");
				//化粧品品名
				XSSFCell a6 = row.createCell(6);										
				a6.setCellValue(!Common.get(x[6]).equals("")?Common.get(x[6]):"其他");
				//地區
				XSSFCell a7 = row.createCell(7);										
				a7.setCellValue(!Common.get(x[7]).equals("")?ctyMap.get(Common.get(x[7])):"其他");				
				//通報來源				
				XSSFCell a8 = row.createCell(8);										
				a8.setCellValue(!Common.get(x[8]).equals("")?cisMap.get(Common.get(x[8])):"其他");
				//販售通路
				XSSFCell a9 = row.createCell(9);										
				a9.setCellValue(!Common.get(x[9]).equals("")?cspMap.get(Common.get(x[9])):"其他");
				//是否有損害消費者健康之事實
				XSSFCell a10 = row.createCell(10);										
				a10.setCellValue(!Common.get(x[10]).equals("")?isYnMap.get(Common.get(x[10])):"其他");
				//不良事件類別
				XSSFCell a11 = row.createCell(11);										
				a11.setCellValue(!Common.get(x[11]).equals("")?cctMap.get(Common.get(x[11])):"其他");
				//化粧品項目
				XSSFCell a12 = row.createCell(12);										
				a12.setCellValue(!Common.get(x[12]).equals("")?cciMap.get(Common.get(x[12])):"其他");
				//不良品分類(大)
				XSSFCell a13 = row.createCell(13);										
				a13.setCellValue(!Common.get(x[13]).equals("")?dpdMap.get(Common.get(x[13])):"其他");
				//不良品分類(小)
				XSSFCell a14 = row.createCell(14);										
				a14.setCellValue(!Common.get(x[14]).equals("")?subCodeMap.get(Common.get(x[14])):"其他");
				//件數
				XSSFCell a15 = row.createCell(15);										
				a15.setCellValue(1);
				
				rowCount++;	    	 
	    	 }
			 
			 StringBuilder hql1 = new StringBuilder();
		    	
			 hql1.append(" select applNo, notifierRepYear, notifierRepMonth, ");          //0-2
			 hql1.append(" permitKey, permitNo, manufactorName, chProduct,");             //3-6
			 hql1.append(" notifierArea, notifierSource, traffickWay, isDamageYn,");      //7-10
			 hql1.append(" cosType, ingredient, adverseCondition, nonSeriousAR ");          //11-14
			 hql1.append(" from pivotView10 where cosType='2' ");	    	
			 hql1.append(queryHql());			
		    	
		     System.out.println("hql1=="+hql1.toString());		    	
		     java.util.List list1 = ServiceGetter.getInstance().getTcbwService().getNativeSqlQuery().load(hql1.toString());				 
		     
		     XSSFSheet sheet1 = workbook.getSheetAt(1);		 
		     int rowCount1=1;
				 
		     for (int j=0; j<list1.size(); j++)		    
		     {		    	 
		    	 Object x[] = (Object[])list1.get(j);				
		    	 XSSFRow row = sheet1.createRow(rowCount1);		    	 
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
				 //國內製造廠/國外產品進口(代理)商
				 XSSFCell a5 = row.createCell(5);										
				 a5.setCellValue(!Common.get(x[5]).equals("")?Common.get(x[5]):"其他");
				 //化粧品品名
				 XSSFCell a6 = row.createCell(6);										
				 a6.setCellValue(!Common.get(x[6]).equals("")?Common.get(x[6]):"其他");
				 //地區
				 XSSFCell a7 = row.createCell(7);										
				 a7.setCellValue(!Common.get(x[7]).equals("")?ctyMap.get(Common.get(x[7])):"其他");				
				 //通報來源				
				 XSSFCell a8 = row.createCell(8);										
				 a8.setCellValue(!Common.get(x[8]).equals("")?cisMap.get(Common.get(x[8])):"其他");
				 //販售通路
				 XSSFCell a9 = row.createCell(9);										
				 a9.setCellValue(!Common.get(x[9]).equals("")?cspMap.get(Common.get(x[9])):"其他");
				 //是否有損害消費者健康之事實
				 XSSFCell a10 = row.createCell(10);										
				 a10.setCellValue(!Common.get(x[10]).equals("")?isYnMap.get(Common.get(x[10])):"其他");
				 //不良事件類別
				 XSSFCell a11 = row.createCell(11);										
				 a11.setCellValue(!Common.get(x[11]).equals("")?cctMap.get(Common.get(x[11])):"其他");
				 //化粧品項目
				 XSSFCell a12 = row.createCell(12);										
				 a12.setCellValue(!Common.get(x[12]).equals("")?cciMap.get(Common.get(x[12])):"其他");
				 //不良反應結果
				 XSSFCell a13 = row.createCell(13);										
				 a13.setCellValue(!Common.get(x[13]).equals("")?cacMap.get(Common.get(x[13])):"其他");
				 //不良反應症狀
				 XSSFCell a14 = row.createCell(14);										
				 a14.setCellValue(!Common.get(x[14]).equals("")?cosarMap.get(Common.get(x[14])):"其他");
				 //件數
				 XSSFCell a15 = row.createCell(15);					
		    	 a15.setCellValue(1);					
				 
				 rowCount1++;		    	 
		    	 
		     }
			 
			 StringBuilder hql2 = new StringBuilder();
		    	
			 hql2.append(" select distinct applNo, notifierRepYear, notifierRepMonth, ");          //0-2
			 hql2.append(" permitKey, permitNo, manufactorName, chProduct,");             //3-6
			 hql2.append(" notifierArea, notifierSource, traffickWay, isDamageYn,");      //7-10		    	
			 hql2.append(" cosType, ingredient "); //11-12	    	
			 hql2.append(" from pivotView10 where 1=1 ");		    	
			 hql2.append(queryHql());			
		    	
		     System.out.println("hql2=="+hql2.toString());		    	
		     java.util.List list2 = ServiceGetter.getInstance().getTcbwService().getNativeSqlQuery().load(hql2.toString());				 
		     
		     XSSFSheet sheet2 = workbook.getSheetAt(2);		 
		     int rowCount2=1;
				 
		     for (int j=0; j<list2.size(); j++)		    
		     {		    	 
		    	 Object x[] = (Object[])list2.get(j);				
		    	 XSSFRow row = sheet2.createRow(rowCount2);		    	 
		    	
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
				 //國內製造廠/國外產品進口(代理)商
				 XSSFCell a5 = row.createCell(5);										
				 a5.setCellValue(!Common.get(x[5]).equals("")?Common.get(x[5]):"其他");
				 //化粧品品名
				 XSSFCell a6 = row.createCell(6);										
				 a6.setCellValue(!Common.get(x[6]).equals("")?Common.get(x[6]):"其他");
				 //地區
				 XSSFCell a7 = row.createCell(7);										
				 a7.setCellValue(!Common.get(x[7]).equals("")?ctyMap.get(Common.get(x[7])):"其他");				
				 //通報來源				
				 XSSFCell a8 = row.createCell(8);										
				 a8.setCellValue(!Common.get(x[8]).equals("")?cisMap.get(Common.get(x[8])):"其他");
				 //販售通路
				 XSSFCell a9 = row.createCell(9);										
				 a9.setCellValue(!Common.get(x[9]).equals("")?cspMap.get(Common.get(x[9])):"其他");
				 //是否有損害消費者健康之事實
				 XSSFCell a10 = row.createCell(10);										
				 a10.setCellValue(!Common.get(x[10]).equals("")?isYnMap.get(Common.get(x[10])):"其他");				
				 //不良事件類別
				 XSSFCell a11 = row.createCell(11);										
				 a11.setCellValue(!Common.get(x[11]).equals("")?cctMap.get(Common.get(x[11])):"其他");
				 //化粧品項目
				 XSSFCell a12 = row.createCell(12);										
				 a12.setCellValue(!Common.get(x[12]).equals("")?cciMap.get(Common.get(x[12])):"其他");				 
				 //件數
				 XSSFCell a13 = row.createCell(13);										
				 a13.setCellValue(1);					
				 
				 rowCount2++;		    	 
		    	 
		     }
		     StringBuilder hql3 = new StringBuilder();
		    	
		     hql3.append(" select distinct applNo, notifierRepYear, notifierRepMonth, ");          //0-2
		     hql3.append(" permitKey, permitNo, manufactorName, chProduct,");             //3-6
		     hql3.append(" notifierArea, notifierSource, traffickWay, isDamageYn,");      //7-10		    	
		     hql3.append(" cosType, ingredient "); //11-12	    	
		     hql3.append(" from pivotView10 where cosType='1' ");		    	
		     hql3.append(queryHql());
		     hql3.append(" union select distinct applNo, notifierRepYear, notifierRepMonth,");
		     hql3.append(" permitKey, permitNo, manufactorName, chProduct,");
		     hql3.append(" notifierArea, notifierSource, traffickWay, isDamageYn,");		    	
		     hql3.append(" cosType, ingredient ");    	
		     hql3.append(" from pivotView10 where cosType='2' and foreignApplNo='' ");		    	
		     hql3.append(queryHql());
		     
		     System.out.println("hql3=="+hql3.toString());		    	
		     java.util.List list3 = ServiceGetter.getInstance().getTcbwService().getNativeSqlQuery().load(hql3.toString());				 
		     
		     XSSFSheet sheet3 = workbook.getSheetAt(3);		 
		     int rowCount3=1;
				 
		     for (int j=0; j<list3.size(); j++)		    
		     {		    	 
		    	 Object x[] = (Object[])list3.get(j);				
		    	 XSSFRow row = sheet3.createRow(rowCount3);		    	 
		    	
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
				 //國內製造廠/國外產品進口(代理)商
				 XSSFCell a5 = row.createCell(5);										
				 a5.setCellValue(!Common.get(x[5]).equals("")?Common.get(x[5]):"其他");
				 //化粧品品名
				 XSSFCell a6 = row.createCell(6);										
				 a6.setCellValue(!Common.get(x[6]).equals("")?Common.get(x[6]):"其他");
				 //地區
				 XSSFCell a7 = row.createCell(7);										
				 a7.setCellValue(!Common.get(x[7]).equals("")?ctyMap.get(Common.get(x[7])):"其他");				
				 //通報來源				
				 XSSFCell a8 = row.createCell(8);										
				 a8.setCellValue(!Common.get(x[8]).equals("")?cisMap.get(Common.get(x[8])):"其他");
				 //販售通路
				 XSSFCell a9 = row.createCell(9);										
				 a9.setCellValue(!Common.get(x[9]).equals("")?cspMap.get(Common.get(x[9])):"其他");
				 //是否有損害消費者健康之事實
				 XSSFCell a10 = row.createCell(10);										
				 a10.setCellValue(!Common.get(x[10]).equals("")?isYnMap.get(Common.get(x[10])):"其他");				
				 //不良事件類別
				 XSSFCell a11 = row.createCell(11);										
				 a11.setCellValue(!Common.get(x[11]).equals("")?cctMap.get(Common.get(x[11])):"其他");
				 //化粧品項目
				 XSSFCell a12 = row.createCell(12);										
				 a12.setCellValue(!Common.get(x[12]).equals("")?cciMap.get(Common.get(x[12])):"其他");				 
				 //件數
				 XSSFCell a13 = row.createCell(13);										
				 a13.setCellValue(1);					
				 
				 rowCount3++;		    	 
		    	 
		     }		     
			 st.close();
			 pkidMap.clear();
			 ctyMap.clear();
			 cisMap.clear();
			 cspMap.clear();
			 dpdMap.clear();
			 subCodeMap.clear();
			 cctMap.clear();
			 cciMap.clear();
			 cacMap.clear();
			 cosarMap.clear();
			 isYnMap.clear();
		}
		 		
		if (list!=null && list.size()>0) 			
		{
		 // 寫入新檔案
		 File oFile = new File("pivot1001r_"+Datetime.getHHMMSS()+".xlsx");
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
			hql.append(" and cast(substring(notifierRepDate,1,5)as int) >=" + getQ_dateS());
	    	
		if(!"".equals(getQ_dateE()))
			hql.append(" and cast(substring(notifierRepDate,1,5)as int)  <=" + getQ_dateE());
    	
		if(!"".equals(getQ_permitKey()))
			hql.append(" and permitKey=" + Common.sqlChar(getQ_permitKey()));
		
		if(!"".equals(getQ_permitNo()))
			hql.append(" and permitNo=" + Common.sqlChar(getQ_permitNo()));
		
		if(!"".equals(getQ_manufactorName()))
			hql.append(" and manufactorName like "+Common.likeSqlChar(getQ_manufactorName()));
		
		if(!"".equals(getQ_productName()))
			hql.append(" and ( chProduct like "+Common.likeSqlChar(getQ_productName())+ 
					   " or enProduct like "+Common.likeSqlChar(getQ_productName())+" )");		
		
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
