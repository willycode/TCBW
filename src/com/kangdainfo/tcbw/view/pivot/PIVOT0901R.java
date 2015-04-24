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


public class PIVOT0901R extends SuperBean
{
	private String q_staType;
	private int type;

	private String q_dateS;
	private String q_dateE;	
	private String q_permitKey;
	private String q_permitNoG;
	private String q_permitNoN;
	private String q_productName;
	// 查詢清單用的欄位
	private String queryId1;				
	private String queryId2;
	private String queryId3;
	
	// 根據統計別取出查詢的欄位
	java.util.Map<String,String> qryDataMap = new java.util.HashMap<String,String>(); 
	public void setQryData(){
		if(qryDataMap.isEmpty()){
			qryDataMap.put("01"," informedSources ");
			qryDataMap.put("02"," notifierTitle ");
			qryDataMap.put("03"," eatersSex ");
			qryDataMap.put("04"," eatersAge ");
			qryDataMap.put("05"," unReactionResults ");
			qryDataMap.put("06"," notifierArea ");
			qryDataMap.put("07"," permitKey, permitNo, chProduct ");
			qryDataMap.put("08"," type ");
			qryDataMap.put("09"," unExpectedClassify ");
			qryDataMap.put("10"," unExpectedReason ");
			qryDataMap.put("11"," evidentiary ");
			qryDataMap.put("12"," recentlySeverity ");
			qryDataMap.put("13"," administrativeLevel ");
		}
	}
	
	// 設定畫面的輸出樣式
	java.util.Map<String,String[]> statMap = new java.util.TreeMap<String, String[]>();
	private void setReports(){
		if (statMap.isEmpty()) {
			statMap.put("01", new String[]{"依通報來源","通報來源","件數"} );
			statMap.put("02", new String[]{"依通報者職稱","通報者職稱","件數"} );
			statMap.put("03", new String[]{"依食用者性別","食用者性別","件數"} );
			statMap.put("04", new String[]{"依食用者年齡","食用者年齡","件數"} );
			statMap.put("05", new String[]{"依非預期反應結果","非預期反應結果","件數"} );
			statMap.put("06", new String[]{"依通報案件縣市別","通報案件縣市別","件數"} );
			statMap.put("07", new String[]{"依通報品項","許可證字號","品名","件數"} );
			statMap.put("08", new String[]{"依食品類別","食品類別","件數"} );
			statMap.put("09", new String[]{"依非預期反應分類","非預期反應分類","件數"} );
			statMap.put("10", new String[]{"依非預期反應原因","非預期反應原因","件數"} );
			statMap.put("11", new String[]{"依證據性","證據性","件數"} );
			statMap.put("12", new String[]{"依嚴重程度","嚴重程度","件數"} );
			statMap.put("13", new String[]{"依行政處置層級","行政處置層級","件數"} );
		}	
	}
	
	// 取得根據統計別區分資料
	public String[] getReports(String staType){
		setReports();  
		return statMap.get(staType);
	}
	
	@Override	
	public Object doQueryAll() throws Exception {		
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		setQryData();
		StringBuilder hql = new StringBuilder();
		if("07".equals(getQ_staType()) || "08".equals(getQ_staType())){
		   hql.append("select "+qryDataMap.get(getQ_staType())+" ,count(distinct applNo+chProduct)");
		}else{
		   hql.append("select "+qryDataMap.get(getQ_staType())+" ,count(distinct applNo)");
		}
		hql.append(" from pivotView09 where 1=1 ");
		hql.append(queryHql());	
		hql.append(" group by "+qryDataMap.get(getQ_staType()));
		hql.append(" order by "+qryDataMap.get(getQ_staType()));
		
		System.out.println("QueryAll SQL : " + hql.toString());
		
		java.util.List list = ServiceGetter.getInstance().getTcbwService().getNativeSqlQuery().load(hql.toString());
		if (list!=null && list.size()>0) 			
		{
			java.util.Map<String, String> pkidMap = TCBWCommon.getCommonCodeMap("HFRPKID"); //許可證字
    		java.util.Map<String, String> nfsMap = TCBWCommon.getCommonCodeMap("HFRNFS");   //通報來源
    		java.util.Map<String, String> nftMap = TCBWCommon.getCommonCodeMap("TITLE");   //通報職稱
    		java.util.Map<String, String> sexMap = new java.util.HashMap<String, String>(); //食用者性別
			sexMap.put("F","女");
			sexMap.put("M","男");
			java.util.Map<String, String> urcrMap = TCBWCommon.getCommonCodeMap("HFRURCR");  //反應結果
			java.util.Map<String, String> ctyMap = TCBWCommon.getCommonCodeMap("CTY");       //縣市別
			java.util.Map<String, String> ftypeMap = new java.util.HashMap<String, String>();//食品類別
			ftypeMap.put("G","健康食品");
			ftypeMap.put("N","核備食品");
			ftypeMap.put("O","一般食品");
			java.util.Map<String, String> fucMap = TCBWCommon.getCommonCodeMap("HFRFUC");    //反應分類
			java.util.Map<String, String> nrsMap = TCBWCommon.getCommonCodeMap("HFRNRS");    //反應原因
			java.util.Map<String, String> fevMap = TCBWCommon.getCommonCodeMap("HFRFEV");    //證據姓
			java.util.Map<String, String> svrMap = TCBWCommon.getCommonCodeMap("HFRSVR");    //嚴重程度
			java.util.Map<String, String> drspMap = TCBWCommon.getCommonCodeMap("HFRDRSP");  //行政處置層級			
			
			for (int j=0; j<list.size(); j++) 
	    	{
					
				if("07".equals(getQ_staType())){
					String rowArray[] = new String[6];	
					Object x[] = (Object[])list.get(j);
					
					rowArray[0] = Common.get(x[0]);
					rowArray[1] = Common.get(x[1]);
					rowArray[2] = Common.get(x[2]);
					rowArray[3] = (!"".equals(Common.get(x[0]))?pkidMap.get(Common.get(x[0])):"")+Common.get(x[1]);
					rowArray[4] = Common.get(x[2]);
					rowArray[5] = Common.get(x[3]);
					
				    arrList.add(rowArray);
				}else{
					String rowArray[] = new String[3];	
					Object x[] = (Object[])list.get(j);
					rowArray[0] = Common.get(x[0]);
					
					String staType ="";
					switch (Common.getInt(getQ_staType())){
					case 1: 
						staType = !"".equals(Common.get(x[0]))?nfsMap.get(Common.get(x[0])):""; //通報來源
						break;
					case 2:	
						staType = !"".equals(Common.get(x[0]))?nftMap.get(Common.get(x[0])):""; //通報者職稱
						break;
					case 3:	
						staType = !"".equals(Common.get(x[0]))?sexMap.get(Common.get(x[0])):""; //食用者性別
						break;
					case 4:	
						staType = Common.get(x[0]);                                             //年齡
						break;
					case 5:	
						staType = !"".equals(Common.get(x[0]))?urcrMap.get(Common.get(x[0])):""; //非預期反應結果
						break;
					case 6:	
						staType = !"".equals(Common.get(x[0]))?ctyMap.get(Common.get(x[0])):""; //通報案件縣市別
						break;					
					case 8:	
						staType = !"".equals(Common.get(x[0]))?ftypeMap.get(Common.get(x[0])):""; //食品類別
						break;
					case 9:	
						staType = !"".equals(Common.get(x[0]))?fucMap.get(Common.get(x[0])):""; //非預期反應分類
						break;
					case 10:	
						staType = !"".equals(Common.get(x[0]))?nrsMap.get(Common.get(x[0])):""; //非預期反應原因
						break;
					case 11:	
						staType = !"".equals(Common.get(x[0]))?fevMap.get(Common.get(x[0])):""; //證據性
						break;
					case 12:	
						staType = !"".equals(Common.get(x[0]))?svrMap.get(Common.get(x[0])):""; //嚴重程度
						break;
					case 13:	
						staType = !"".equals(Common.get(x[0]))?drspMap.get(Common.get(x[0])):""; //行政處置層級
						break;
					}					
					rowArray[1] = Common.get(staType);
					rowArray[2] = Common.get(x[1]);
					
				    arrList.add(rowArray);
				}				
			}
			pkidMap.clear();
    		nfsMap.clear();
    		nftMap.clear();
    		sexMap.clear();
			urcrMap.clear();
			ctyMap.clear();
			ftypeMap.clear();
			fucMap.clear();
			nrsMap.clear();
			fevMap.clear();
			svrMap.clear();
			drspMap.clear();
		}
		return arrList;
	}
	
	public void exportExcelTable(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		
		// 讀取EXCEL樣版
		InputStream st = new FileInputStream(new File(MyServletContext.getInstance().getServletContext().getRealPath("/report/pivot/pivot0901r.xlsx")));

		XSSFWorkbook workbook = new XSSFWorkbook(st);
		
    	StringBuilder hql = new StringBuilder();
    	
    	hql.append(" select distinct applNo, notifierRepYear, notifierRepMonth, informedSources, ");    //0-3
    	hql.append(" notifierTitle, eatersSex, eatersAge, unReactionResults, notifierArea,");  //4-8
    	hql.append(" type, permitKey, permitNo, chProduct, enProduct, ");                      //9-13
    	hql.append(" unExpectedClassify, evidentiary, recentlySeverity, administrativeLevel");  //14-17
    	hql.append(" from pivotView09 where 1=1 ");    	
    	hql.append(queryHql());
    	hql.append(" order by notifierRepYear,notifierRepMonth,applNo ");
    	
    	System.out.println("hql=="+hql.toString());
    	
    	java.util.List list = ServiceGetter.getInstance().getTcbwService().getNativeSqlQuery().load(hql.toString());
		
    	if (list!=null && list.size()>0) 			
		{
    		 
    		java.util.Map<String, String> pkidMap = TCBWCommon.getCommonCodeMap("HFRPKID"); //許可證字
    		java.util.Map<String, String> nfsMap = TCBWCommon.getCommonCodeMap("HFRNFS");   //通報來源
    		java.util.Map<String, String> nftMap = TCBWCommon.getCommonCodeMap("TITLE");   //通報職稱
    		java.util.Map<String, String> sexMap = new java.util.HashMap<String, String>(); //食用者性別
			sexMap.put("F","女");
			sexMap.put("M","男");
			java.util.Map<String, String> urcrMap = TCBWCommon.getCommonCodeMap("HFRURCR");  //反應結果
			java.util.Map<String, String> ctyMap = TCBWCommon.getCommonCodeMap("CTY");       //縣市別
			java.util.Map<String, String> ftypeMap = new java.util.HashMap<String, String>();//食品類別
			ftypeMap.put("G","健康食品");
			ftypeMap.put("N","核備食品");
			ftypeMap.put("O","一般食品");
			java.util.Map<String, String> fucMap = TCBWCommon.getCommonCodeMap("HFRFUC");    //反應分類
			java.util.Map<String, String> nrsMap = TCBWCommon.getCommonCodeMap("HFRNRS");    //反應原因
			java.util.Map<String, String> fevMap = TCBWCommon.getCommonCodeMap("HFRFEV");    //證據姓
			java.util.Map<String, String> svrMap = TCBWCommon.getCommonCodeMap("HFRSVR");    //嚴重程度
			java.util.Map<String, String> drspMap = TCBWCommon.getCommonCodeMap("HFRDRSP");  //行政處置層級
			
    		
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
				
				//通報來源
				XSSFCell a3 = row.createCell(3);										
				a3.setCellValue(!Common.get(x[3]).equals("")?nfsMap.get(Common.get(x[3])):"其他");
				
				//通報者職稱
				XSSFCell a4 = row.createCell(4);										
				a4.setCellValue(!Common.get(x[4]).equals("")?nftMap.get(Common.get(x[4])):"其他");
				
				//食用者性別
				XSSFCell a5 = row.createCell(5);										
				a5.setCellValue(!Common.get(x[5]).equals("")?sexMap.get(Common.get(x[5])):"其他");
				
				//食用者年齡
				XSSFCell a6 = row.createCell(6);										
				a6.setCellValue(!Common.get(x[6]).equals("")?Common.get(x[6]):"其他");
				
				//非預期反應結果
				XSSFCell a7 = row.createCell(7);										
				a7.setCellValue(!Common.get(x[7]).equals("")?urcrMap.get(Common.get(x[7])):"其他");
				
				//通報案件縣市別
				XSSFCell a8 = row.createCell(8);										
				a8.setCellValue(!Common.get(x[8]).equals("")?ctyMap.get(Common.get(x[8])):"其他");
				
				//許可證字號/核備字號
				XSSFCell a9 = row.createCell(9);
				String permitKeyNo = (!"".equals(Common.get(x[10]))?pkidMap.get(Common.get(x[10]))+"字第":"")+(!"".equals(Common.get(x[11]))?Common.get(x[11])+"號":"");				 
				a9.setCellValue(!Common.get(permitKeyNo).equals("")?Common.get(permitKeyNo):"其他");
				
				//品名(中)
				XSSFCell a10 = row.createCell(10);			 
				a10.setCellValue(!Common.get(x[12]).equals("")?Common.get(x[12]):"其他");
				
				//品名(英)
				XSSFCell a11 = row.createCell(11);			 
				a11.setCellValue(!Common.get(x[13]).equals("")?Common.get(x[13]):"其他");
				
				//食品類別
				XSSFCell a12 = row.createCell(12);			 
				a12.setCellValue(!Common.get(x[9]).equals("")?ftypeMap.get(Common.get(x[9])):"其他");
				
				//非預期反應分類
				XSSFCell a13 = row.createCell(13);			 
				a13.setCellValue(!Common.get(x[14]).equals("")?fucMap.get(Common.get(x[14])):"其他");				 
				
				//證據性
				XSSFCell a14 = row.createCell(14);			 
				a14.setCellValue(!Common.get(x[15]).equals("")?fevMap.get(Common.get(x[15])):"其他");
				
				//嚴重程度
				XSSFCell a15 = row.createCell(15);			 
				a15.setCellValue(!Common.get(x[16]).equals("")?svrMap.get(Common.get(x[16])):"其他");
				
				//行政處置層級
				XSSFCell a16 = row.createCell(16);			 
				a16.setCellValue(!Common.get(x[17]).equals("")?drspMap.get(Common.get(x[17])):"其他");
				
				//件數
				XSSFCell a17 = row.createCell(17);			 
				a17.setCellValue(1);
				
				rowCount++; 			 	 
	    	 
	    	}		 
			 
             StringBuilder hql1 = new StringBuilder();
			 
             hql1.append(" select distinct applNo, notifierRepYear, notifierRepMonth, informedSources, ");    //0-3
         	 hql1.append(" notifierTitle, eatersSex, eatersAge, unReactionResults, notifierArea,");  //4-8
         	 hql1.append(" unExpectedClassify, evidentiary, recentlySeverity, administrativeLevel");  //9-13
         	 hql1.append(" from pivotView09 where 1=1 ");			 
			 hql1.append(queryHql());			 
			 hql1.append(" order by notifierRepYear,notifierRepMonth,applNo ");
			 
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
				 a0.setCellValue(!Common.get(x[0]).equals("")?Common.get(x[0]):"其他");
				 
				 //年度
				 XSSFCell a1 = row.createCell(1);										
				 a1.setCellValue(!Common.get(x[1]).equals("")?Common.get(x[1]):"其他");
				
				 //月份
				 XSSFCell a2 = row.createCell(2);										
				 a2.setCellValue(!Common.get(x[2]).equals("")?Common.get(x[2]):"其他");
				
				 //通報來源
				 XSSFCell a3 = row.createCell(3);										
				 a3.setCellValue(!Common.get(x[3]).equals("")?nfsMap.get(Common.get(x[3])):"其他");
				
				 //通報者職稱
				 XSSFCell a4 = row.createCell(4);										
				 a4.setCellValue(!Common.get(x[4]).equals("")?nftMap.get(Common.get(x[4])):"其他");
				
				 //食用者性別
				 XSSFCell a5 = row.createCell(5);										
				 a5.setCellValue(!Common.get(x[5]).equals("")?sexMap.get(Common.get(x[5])):"其他");
				
				 //食用者年齡
				 XSSFCell a6 = row.createCell(6);										
				 a6.setCellValue(!Common.get(x[6]).equals("")?Common.get(x[6]):"其他");
				
				 //非預期反應結果
				 XSSFCell a7 = row.createCell(7);										
				 a7.setCellValue(!Common.get(x[7]).equals("")?urcrMap.get(Common.get(x[7])):"其他");
				
				 //通報案件縣市別
				 XSSFCell a8 = row.createCell(8);										
				 a8.setCellValue(!Common.get(x[8]).equals("")?ctyMap.get(Common.get(x[8])):"其他");				 
				
				 //非預期反應分類
				 XSSFCell a9 = row.createCell(9);			 
				 a9.setCellValue(!Common.get(x[9]).equals("")?fucMap.get(Common.get(x[9])):"其他");				 
				
				 //證據性
				 XSSFCell a10 = row.createCell(10);			 
				 a10.setCellValue(!Common.get(x[10]).equals("")?fevMap.get(Common.get(x[10])):"其他");
				
				 //嚴重程度
				 XSSFCell a11 = row.createCell(11);			 
				 a11.setCellValue(!Common.get(x[11]).equals("")?svrMap.get(Common.get(x[11])):"其他");
				
				 //行政處置層級
				 XSSFCell a12 = row.createCell(12);			 
				 a12.setCellValue(!Common.get(x[12]).equals("")?drspMap.get(Common.get(x[12])):"其他");
				 
				 //件數
				 XSSFCell a13 = row.createCell(13);			 
				 a13.setCellValue(1);

				rowCount1++;
	    	 }
			 
             StringBuilder hql2 = new StringBuilder();
			 
             hql2.append(" select distinct applNo, notifierRepYear, notifierRepMonth, informedSources, ");    //0-3
             hql2.append(" notifierTitle, eatersSex, eatersAge, unReactionResults, notifierArea,");  //4-8
             hql2.append(" unExpectedClassify, unExpectedReason, evidentiary, recentlySeverity, administrativeLevel");  //9-13
             hql2.append(" from pivotView09 where 1=1 ");			 
             hql2.append(queryHql());			 
             hql2.append(" order by notifierRepYear,notifierRepMonth,applNo ");
			 
		     java.util.List list2 = ServiceGetter.getInstance().getTcbwService().getNativeSqlQuery().load(hql2.toString());
				
		     System.out.println("hql2=="+hql2.toString());
			 
			 XSSFSheet sheet2 = workbook.getSheetAt(2);
			 
			 int rowCount2=1;
			 for (int j=0; j<list2.size(); j++) 
	    	 {
				 Object x[] = (Object[])list2.get(j);	
				 XSSFRow row = sheet2.createRow(rowCount2);		
				//案號	年度	月份	通報來源	通報者職稱	食用者性別	食用者年齡	非預期反應結果	通報案件縣市別	非預期反應分類	非預期反應原因	證據性	嚴重程度	行政處置層級	件數
				 //案號
				 XSSFCell a0 = row.createCell(0);										
				 a0.setCellValue(!Common.get(x[0]).equals("")?Common.get(x[0]):"其他");
				 
				 //年度
				 XSSFCell a1 = row.createCell(1);										
				 a1.setCellValue(!Common.get(x[1]).equals("")?Common.get(x[1]):"其他");
				
				 //月份
				 XSSFCell a2 = row.createCell(2);										
				 a2.setCellValue(!Common.get(x[2]).equals("")?Common.get(x[2]):"其他");
				
				 //通報來源
				 XSSFCell a3 = row.createCell(3);										
				 a3.setCellValue(!Common.get(x[3]).equals("")?nfsMap.get(Common.get(x[3])):"其他");
				
				 //通報者職稱
				 XSSFCell a4 = row.createCell(4);										
				 a4.setCellValue(!Common.get(x[4]).equals("")?nftMap.get(Common.get(x[4])):"其他");
				
				 //食用者性別
				 XSSFCell a5 = row.createCell(5);										
				 a5.setCellValue(!Common.get(x[5]).equals("")?sexMap.get(Common.get(x[5])):"其他");
				
				 //食用者年齡
				 XSSFCell a6 = row.createCell(6);										
				 a6.setCellValue(!Common.get(x[6]).equals("")?Common.get(x[6]):"其他");
				
				 //非預期反應結果
				 XSSFCell a7 = row.createCell(7);										
				 a7.setCellValue(!Common.get(x[7]).equals("")?urcrMap.get(Common.get(x[7])):"其他");
				
				 //通報案件縣市別
				 XSSFCell a8 = row.createCell(8);										
				 a8.setCellValue(!Common.get(x[8]).equals("")?ctyMap.get(Common.get(x[8])):"其他");				 
				
				 //非預期反應分類
				 XSSFCell a9 = row.createCell(9);			 
				 a9.setCellValue(!Common.get(x[9]).equals("")?fucMap.get(Common.get(x[9])):"其他");				 
				
				 
				 //非預期反應原因
				 XSSFCell a10 = row.createCell(10);			 
				 a10.setCellValue(!Common.get(x[10]).equals("")?nrsMap.get(Common.get(x[10])):"其他");
					
				 
				 //證據性
				 XSSFCell a11 = row.createCell(11);			 
				 a11.setCellValue(!Common.get(x[11]).equals("")?fevMap.get(Common.get(x[11])):"其他");
				
				 //嚴重程度
				 XSSFCell a12 = row.createCell(12);			 
				 a12.setCellValue(!Common.get(x[12]).equals("")?svrMap.get(Common.get(x[12])):"其他");
				
				 //行政處置層級
				 XSSFCell a13 = row.createCell(13);			 
				 a13.setCellValue(!Common.get(x[13]).equals("")?drspMap.get(Common.get(x[13])):"其他");
				 
				 //件數
				 XSSFCell a14 = row.createCell(14);			 
				 a14.setCellValue(1);

				rowCount2++;
	    	 }
			 
			 st.close();
			 pkidMap.clear();
	    	 nfsMap.clear();
	    	 nftMap.clear();
	   		 sexMap.clear();
			 urcrMap.clear();
			 ctyMap.clear();
			 ftypeMap.clear();
			 fucMap.clear();
			 nrsMap.clear();
			 fevMap.clear();
			 svrMap.clear();
			 drspMap.clear();
		}
    	
		if (list!=null && list.size()>0) 			
		{		 
			// 寫入新檔案		 
			File oFile = new File("pivot0901r_"+Datetime.getHHMMSS()+".xlsx");		 
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
		    	try {
		    		response.sendError(500,"產製錯誤！");
		    	}catch(Exception e) {
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
		
		if(!"".equals(getQ_permitNoG())){
			hql.append(" and type='G' and permitNo=" + Common.sqlChar(getQ_permitNoG()));
		}
		
		if(!"".equals(getQ_permitNoN())){
			hql.append(" and type='N' and permitNo=" + Common.sqlChar(getQ_permitNoN()));
		}
		
		if(!"".equals(getQ_productName()))
			hql.append(" and ( chProduct like "+Common.likeSqlChar(getQ_productName())+ 
					   " or enProduct like "+Common.likeSqlChar(getQ_productName())+" )");		
		
		return hql.toString();
	}	
	
	public String queryOneHql()
	{		
		StringBuilder hql = new StringBuilder();
		switch (Common.getInt(getQ_staType())){
		case 1:
			hql.append(" and informedSources=" + Common.sqlChar(getQueryId1()));
			break;
		case 2:
			hql.append(" and notifierTitle=" + Common.sqlChar(getQueryId1()));
			break;
		case 3:
			hql.append(" and eatersSex=" + Common.sqlChar(getQueryId1()));
			break;
		case 4:			
			hql.append(" and eatersAge=" + Common.sqlChar(getQueryId1()));
			break;
		case 5:
			hql.append(" and unReactionResults=" + Common.sqlChar(getQueryId1()));
			break;
		case 6:
			hql.append(" and notifierArea=" + Common.sqlChar(getQueryId1()));
			break;
		case 7:
			hql.append(" and permitKey=" + Common.sqlChar(getQueryId1()) +
					   " and permitNo="+Common.sqlChar(getQueryId2())+
					   " and chProduct="+Common.sqlChar(getQueryId3()));
			break;
		case 8:
			hql.append(" and type=" + Common.sqlChar(getQueryId1()));
			break;
		case 9:
			hql.append(" and unExpectedClassify=" + Common.sqlChar(getQueryId1()));
			break;
		case 10:
			hql.append(" and unExpectedReason=" + Common.sqlChar(getQueryId1()));
			break;
		case 11:
			hql.append(" and evidentiary=" + Common.sqlChar(getQueryId1()));
			break;
		case 12:
			hql.append(" and recentlySeverity=" + Common.sqlChar(getQueryId1()));
			break;
		case 13:
			hql.append(" and administrativeLevel=" + Common.sqlChar(getQueryId1()));
			break;
		}
		return hql.toString();
	}
	
	public String getQ_dateS() {return checkGet(q_dateS);}
	public void setQ_dateS(String qDateS) {q_dateS = checkSet(qDateS);}
	
	public String getQ_dateE() {return checkGet(q_dateE);}
	public void setQ_dateE(String qDateE) {q_dateE = checkSet(qDateE);}


	public String getQ_permitKey() {return checkGet(q_permitKey);}
	public void setQ_permitKey(String qPermitKey) {q_permitKey = checkSet(qPermitKey);}

    public String getQ_permitNoG() {return checkGet(q_permitNoG);	}
	public void setQ_permitNoG(String q_permitNoG) {this.q_permitNoG = checkSet(q_permitNoG);	}

	public String getQ_permitNoN() {return checkGet(q_permitNoN);	}
	public void setQ_permitNoN(String q_permitNoN) {this.q_permitNoN = checkSet(q_permitNoN);	}

	public String getQ_productName() { return checkGet(q_productName);	}
	public void setQ_productName(String q_productName) { this.q_productName = checkSet(q_productName); }
	
	public String getQ_staType() { return checkGet(q_staType); }
	public void setQ_staType(String q_staType) {		
		this.q_staType = checkSet(q_staType);
		if(!"".equals(this.q_staType)){
			this.setType(Integer.parseInt(this.q_staType));
		}
	}
	public int getType() {		return type;	}
	public void setType(int type) {		this.type = type;	}
	
	public String getQueryId1() {		return checkGet(queryId1);	}
	public void setQueryId1(String queryId1) {		this.queryId1 = checkSet(queryId1);	}
	public String getQueryId2() {		return checkGet(queryId2);	}
	public void setQueryId2(String queryId2) {		this.queryId2 = checkSet(queryId2);	}
	public String getQueryId3() {		return checkGet(queryId3);	}
	public void setQueryId3(String queryId3) {		this.queryId3 = checkSet(queryId3);	}

	@Override
	public void doCreate() throws Exception {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void doDelete() throws Exception {
		// TODO Auto-generated method stub
		
	}	

	@Override
	public Object doQueryOne() throws Exception {
		
		java.util.ArrayList<String[]> arrList2 = new java.util.ArrayList<String[]>();
				
        StringBuilder hql = new StringBuilder();
       
        hql.append(" select distinct applNo, notifierRepDate, permitKey, permitNo, chProduct, unHealthIsYes, unReactionResults, status"); 
    	hql.append(" from pivotView09 where 1=1 ");
    	hql.append(queryHql());	
    	hql.append(queryOneHql());
    	hql.append(" order by applNo ");
    	
    	System.out.println("QueryOne SQL : " + hql.toString());
    	java.util.List list = ServiceGetter.getInstance().getTcbwService().getNativeSqlQuery().load(hql.toString());    	
		if (list!=null && list.size()>0) 			
		{
			java.util.Map<String, String> pkidMap = TCBWCommon.getCommonCodeMap("HFRPKID");  //許可證字
			java.util.Map<String, String> urcrMap = TCBWCommon.getCommonCodeMap("HFRURCR");  //反應結果
			java.util.Map<String, String> fcsMap = TCBWCommon.getCommonCodeMap("FCS");       //狀態			
			
			for (int j=0; j<list.size(); j++) 
	    	{				
				String rowArray[] = new String[7];	
				Object x[] = (Object[])list.get(j);
				
				rowArray[0] = Common.get(x[0]);                                                                 //案件編號
				rowArray[1] = Common.formatYYYMMDD(Common.get(x[1]),4);                                         //通報日期
				rowArray[2] = (!"".equals(Common.get(x[2]))?pkidMap.get(Common.get(x[2])):"")+Common.get(x[3]); //食品字號
				rowArray[3] = Common.get(x[4]);                                                                 //食品品名
				rowArray[4] = (!"".equals(Common.get(x[4]))?("Y".equals(Common.get(x[4]))?"是":"否"):"");       //健康食品未達宣稱之保健功效
				rowArray[5] = !Common.get(x[6]).equals("")?urcrMap.get(Common.get(x[6])):"其他";                //非預期反應結果     
				rowArray[6] = !Common.get(x[7]).equals("")?fcsMap.get(Common.get(x[7])):"其他"; ;               //案件狀態
				
			    arrList2.add(rowArray);
	    	}
			pkidMap.clear();
			urcrMap.clear();
			fcsMap.clear();
		}
		return arrList2;		
	}
	@Override
	public void doUpdate() throws Exception {
		// TODO Auto-generated method stub
		
	}	

}
