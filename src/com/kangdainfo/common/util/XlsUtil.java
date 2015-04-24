package com.kangdainfo.common.util;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.*; 
import jxl.write.*; 

import org.apache.poi.hssf.usermodel.*;

/**
 * Retrieve Excel File Utility
 *
 * @version 1.0, May 2, 2006
 * @author  clive.chang
 * @since   TAJK Project
 */

public class XlsUtil {
    
    private String xlsFileName;
    public void setXlsFileName(String s) { xlsFileName = Common.set(s); }
    public String getXlsFileName() { return Common.get(xlsFileName); }    
    
    public XlsUtil() {}
    
    public java.io.File genXlsFile(List<String> list, String seperator) throws Exception {
    	Map<String, List<Object[]>> rs = new java.util.HashMap<String, List<Object[]>>();
    	if (list!=null && list.size()>0) {
    		List<Object[]> dataList = new java.util.ArrayList<Object[]>();
    		for (int i=0; i<list.size(); i++) {
    			String s = list.get(i);
    			if (s!=null && s.length()>0) {
    				dataList.add(s.split(seperator!=null?seperator:","));		
    			}
    		}
    		rs.put("Sheet1", dataList);
    	}
    	return genXlsFile(rs);
    }        
    public java.io.File genXlsFile(List<Object[]> list) throws Exception {
    	Map<String, List<Object[]>> rs = new java.util.HashMap<String, List<Object[]>>();
    	rs.put("Sheet1", list);
    	return genXlsFile(rs);
    }  
    
    public java.io.File genXlsFile(List<Object[]> list, List<Object[]> list2) throws Exception {
    	Map<String, List<Object[]>> rs = new java.util.HashMap<String, List<Object[]>>();
    	rs.put("Sheet1", list);
    	rs.put("Sheet2", list2);
    	return genXlsFile(rs);
    }    
    public java.io.File genXlsFile(Map<String, List<Object[]>> rs) throws Exception {
    	if (rs!=null && rs.size()>0) {
    		int rowIdx=0,colIdx=0, j=0;
    		java.io.File f = new File(System.getProperty("java.io.tmpdir")+File.separator+(getXlsFileName().equals("")?Common.getVMID()+".xls":getXlsFileName()));    		
    		WritableWorkbook wb = Workbook.createWorkbook(f);
    		int sheetNumber = 0;
    		for (String key : rs.keySet()) {
    			WritableSheet sheet = wb.createSheet(key, sheetNumber); 
    			List<Object[]> list = rs.get(key);    	    				
    			if (list!=null && list.size()>0){ 
    				for (int i = 0 ; i < list.size() ; i ++){
    					Object[] row = list.get(i);				    							
    					for (j = 0 ; j < row.length ; j ++){
    						WritableCell cell = sheet.getWritableCell(colIdx+j,rowIdx+i);
    						if (row[j]!=null) {
    							WritableCell updateCell = null;
    							if (row[j] instanceof java.lang.String) {
    								if (cell.getCellFormat()!=null)	updateCell = new Label(colIdx+j, rowIdx+i, Common.get(row[j]),cell.getCellFormat());
    								else updateCell = new jxl.write.Label(colIdx+j, rowIdx+i, Common.get(row[j]));
    							} else if (row[j] instanceof java.lang.Number) {					    
    								if (cell.getCellFormat()!=null) updateCell = new jxl.write.Number(colIdx+j, rowIdx+i, Common.getNumeric(row[j]), cell.getCellFormat());						
    								else updateCell = new jxl.write.Number(colIdx+j, rowIdx+i, Common.getNumeric(row[j]));
    							} else if (row[j] instanceof java.lang.Boolean) {
    								if (cell.getCellFormat()!=null) updateCell = new jxl.write.Boolean(colIdx+j, rowIdx+i,(java.lang.Boolean)row[j], cell.getCellFormat());
    								else updateCell = new jxl.write.Boolean(colIdx+j, rowIdx+i,(java.lang.Boolean)row[j]);
    							} else if (row[j] instanceof java.util.Date) {
    								if (cell.getCellFormat()!=null) updateCell = new jxl.write.DateTime(colIdx+j, rowIdx+i, (java.util.Date)row[j], cell.getCellFormat());
    								else updateCell = new jxl.write.DateTime(colIdx+j, rowIdx+i, (java.util.Date)row[j]);
    							} else {
    								if (cell.getCellFormat()!=null)	updateCell = new jxl.write.Label(colIdx+j, rowIdx+i, Common.get(row[j]), cell.getCellFormat());
    								else updateCell = new jxl.write.Label(colIdx+j, rowIdx+i, Common.get(row[j]));
    							}					
    							try {
    								sheet.addCell(updateCell);
    							}  catch (Exception e) {
    								System.out.println(row[j]);
    								e.printStackTrace();
    							}						
    						}  						
    					}				
    				}
    			}
    			sheetNumber++;
    		}
    		wb.write();			
			wb.close();
			return f;
    	}
    	return null;
    }
    
    
    /**
     *
     * @param      fileName       the excel file path.
     * @param	  sheetNumber    the excel sheet number, from 0 ~ n.
     * @param	  includeHeader  include first column or not. True/False.
     * @param	  intArraySize   the size of inner array.
     * @return     the <code>ArrayList</code> object.
     * @exception  Exception if excel file does not found.
     *
     */
    public List<String[]> getJExcelModel(String fileName, int sheetNumber, boolean includeHeader, Integer intArraySize) throws Exception {
        setXlsFileName(fileName);
        List<String[]> objList=new java.util.ArrayList<String[]>();
        try {
            Workbook workbook = Workbook.getWorkbook(new File(fileName));
            Sheet s = workbook.getSheet(sheetNumber);
            Cell[] row = null;
            String rowArray[];
            for (int i = 0 ; i < s.getRows() ; i++) {
                row = s.getRow(i);
                if (row!=null && row.length > 0) {
                    rowArray = new String[intArraySize!=null?intArraySize:row.length];
                    for (int j = 0; j < rowArray.length; j++) {
                    	if (row.length>j) rowArray[j] = Common.get(row[j].getContents()).replaceAll("'", "").replaceAll("\"", "");
                    	else rowArray[j] = "";                    	
                        //if (j>=row.length) rowArray[j] = "";
                        //else rowArray[j] = Common.get(row[j].getContents()).replaceAll("'", "").replaceAll("\"", "");
                    }
                    if (includeHeader) {
                        objList.add(rowArray);
                    } else if (i>0) {
                        objList.add(rowArray);
                    }
                }
            }
            workbook.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return objList;
    }
    
    public List<String[]> getJExcelModel(String fileName, String sheetName, boolean includeHeader, Integer intArraySize) throws Exception {
        setXlsFileName(fileName);
        List<String[]> objList=new java.util.ArrayList<String[]>();
        try {
            Workbook workbook = Workbook.getWorkbook(new File(fileName));
            Sheet s = workbook.getSheet(sheetName);
            Cell[] row = null;
            String rowArray[];
            for (int i = 0 ; i < s.getRows() ; i++) {
                row = s.getRow(i);
                if (row!=null && row.length > 0) {
                    rowArray = new String[intArraySize!=null?intArraySize:row.length];
                    for (int j = 0; j < rowArray.length; j++) {
                        if (row.length>j) rowArray[j] = Common.get(row[j].getContents()).replaceAll("'", "").replaceAll("\"", "");
                        else rowArray[j] = "";
                    }
                    if (includeHeader) {
                        objList.add(rowArray);
                    } else if (i>0) {
                        objList.add(rowArray);
                    }
                }
            }
            workbook.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return objList;
    }
    
    public List<String[]> getJExcelModel(String fileName, int sheetNumber, boolean includeHeader) throws Exception {
    	return getJExcelModel(fileName, sheetNumber, includeHeader, null);
    }
    
    
    /**
    *
    * @param      fileName       the excel file path.
    * @param	  sheetNumber    the excel sheet number, from 0 ~ n.
    * @param	  includeHeader  include first column or not. True/False.
    * @param	  intArraySize   the size of inner array.
    * @return     the <code>ArrayList</code> object.
    * @exception  Exception if excel file does not found.
    *
    */
   public List<Object[]> getPOIModel(String fileName, int sheetNumber, boolean includeHeader, Integer intArraySize) throws Exception {
       setXlsFileName(fileName);
       List<Object[]> objList=new java.util.ArrayList<Object[]>();
       try {
    	   HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(fileName));
    	   HSSFSheet sheet = wb.getSheetAt(sheetNumber);
    	   HSSFCell cell = null;    	   
    	   HSSFRow row = null;
    	   for (int i=0 ; i<sheet.getPhysicalNumberOfRows();i++){
    		   row = sheet.getRow(i);
    		   if (row!=null) {
    			   Object[] rowArray = new Object[intArraySize!=null?intArraySize:row.getPhysicalNumberOfCells()];
    			   for (int j = 0; j < rowArray.length; j++) {
    				   cell = row.getCell(j);
    				   if (j>=row.getPhysicalNumberOfCells()) rowArray[j] = "";
    				   else if (cell != null && cell.getCellType()==HSSFCell.CELL_TYPE_STRING && cell.getRichStringCellValue()!= null && !"".equals(Common.get(cell.getRichStringCellValue()))){
    					   rowArray[j] = cell.getRichStringCellValue().getString();
    				   }
    			   }
    			   if (includeHeader) {
    				   objList.add(rowArray);
    			   } else if (i>0) {
    				   objList.add(rowArray);
    			   }				
    		   }			
    	   }	
       } catch(Exception e) {
           e.printStackTrace();
       }
       return objList;
   }
   public List<String[]> getPOIModelFormatToString(String fileName, int sheetNumber, boolean includeHeader, Integer intArraySize) throws Exception {
	   List<Object[]> list = getPOIModel(fileName, sheetNumber, includeHeader, intArraySize);
	   if(list!=null){
		   List<String[]> resultList = new ArrayList<String[]>();
		   for(Object[] objArr : list){
			   if(objArr != null){
				   String[] strArr = new String[objArr.length];
				   for(int i = 0 ; i < objArr.length ; i ++){
					   strArr[i] = objArr[i]!=null?String.valueOf(objArr[i]):null;
				   }
				   resultList.add(strArr);
			   }
		   }
		   return resultList;
	   }
	   return null;
   }
    public String genSQLScript(String excelFilePath, int sheetNumber) throws Exception {
        try {
            StringBuffer sb = new StringBuffer(5000).append("");
            Workbook workbook = Workbook.getWorkbook(new File(excelFilePath));
            Sheet s = workbook.getSheet(sheetNumber);
            String tableName = s.getName();
            Cell[] row = null;
            for (int i = 0 ; i < s.getRows() ; i++) {
                row = s.getRow(i);
                if (row!=null && row.length > 0) {
                    if (i==0) {
                        sb.append("create table ").append(tableName).append("( ");
                    } else {
                        sb.append("INSERT INTO " ).append( tableName ).append( " VALUES (");
                    }
                    for (int j = 0; j < row.length; j++) {
                        if (i==0) {
                            if (j==0) sb.append(Common.get(row[j].getContents()) ).append( " varchar(255) ");
                            else sb.append(",").append(Common.get(row[j].getContents()) ).append( " varchar(255) ");
                        } else {
                            if (j==0) sb.append(Common.sqlChar((row[j].getContents())));
                            else sb.append(", ").append( Common.sqlChar(row[j].getContents()));
                        }
                    }
                    sb.append(");\n\n ");
                }
            }
            workbook.close();
            return sb.toString();
        } catch(Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
        }
        return "";
    }
    
    public String genPOISQLScript(String excelFilePath, int sheetNumber) throws Exception {
        HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(excelFilePath));
        String tableName =  wb.getSheetName(sheetNumber);
        HSSFSheet sheet = wb.getSheet(tableName);
        HSSFCell cell = null;
        try {
            StringBuffer sb = new StringBuffer(5000).append("");
            int rowCount = sheet.getPhysicalNumberOfRows();
            int colCount = sheet.getRow(0).getPhysicalNumberOfCells();
            int j = 0;
            for(int i = 0; i < rowCount; i++) {
                if (i==0) {
                    sb.append("create table ").append(tableName).append("( ");
                } else {
                    sb.append("INSERT INTO " ).append( tableName ).append( " VALUES (");
                }
                HSSFRow row = sheet.getRow(i);
                for(j=0; j<colCount; j++) {
                	cell = row.getCell(j);
                    String v = "";
                    if (cell!=null) {
                        switch(row.getCell(j).getCellType()) {
                        case HSSFCell.CELL_TYPE_NUMERIC:
                            v = row.getCell(j).getNumericCellValue() + "";
                            break;
                            
                        case HSSFCell.CELL_TYPE_STRING:
                            v = cell.getRichStringCellValue().getString();
                            break;
                            
                        case HSSFCell.CELL_TYPE_FORMULA:
                            v = cell.getRichStringCellValue().getString();
                            if ("".equals(Common.get(v))) {
                                try {
                                    v = row.getCell(j).getNumericCellValue()+"";
                                } catch (Exception e) {
                                    v = "";
                                    e.printStackTrace();
                                }
                            }
                            break;
                            
                        case HSSFCell.CELL_TYPE_BLANK:
                            v = cell.getRichStringCellValue().getString();
                            break;
                        default:
                            v = row.getCell(j).toString();
                        }                    	
                    }

                    
                    if (i==0) {
                        if (j==0) sb.append(Common.get(v) ).append( " varchar(255) ");
                        else sb.append(",").append(Common.get(v) ).append( " varchar(255) ");
                    } else {
                        if (j==0) sb.append(Common.sqlChar((v)));
                        else sb.append(", ").append( Common.sqlChar(v));
                    }
                }
                sb.append(");\n\n ");
            }
            return sb.toString();
        } catch (Exception e) {
            System.out.println("A POI error has occured.");
            e.printStackTrace();
        }
        return "";
    }    
    
    public String[][] toArray(String excelFilePath, int sheetNumber, int intArraySize) throws Exception {
        try {
            Workbook workbook = Workbook.getWorkbook(new File(excelFilePath));
            Sheet s = workbook.getSheet(sheetNumber);            
            Cell[] row = null;
            java.util.HashMap<Integer,String[]> m = new java.util.HashMap<Integer,String[]>();   
            for (int i = 0 ; i < s.getRows() ; i++) {
                row = s.getRow(i);
                if (row!=null && row.length>=intArraySize) {  		            	
                    String[] rowArray = new String[intArraySize];	            	
                    for (int j = 0; j < intArraySize; j++) {
                    	if (row.length>j) Common.get(row[j].getContents());
                    	else rowArray[j] = "";                        
                    	//if (j>=row.length) rowArray[j] = "";
                        //else rowArray[j] = Common.get(row[j].getContents());
                    }
                    m.put(i,rowArray);
                }
            }            
            workbook.close();
            String[][] arrData = new String[m.size()][intArraySize];
            for (int i=0; i<m.size(); i++) {
                arrData[i] = (String[])m.get(i);
            }
            return arrData;
        } catch(Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
        }
        return null;
    }    
    /**
     * 取得EXCEL的數據，其為Map物件，Map裡面有key值為master與detail兩個list，list中存入多個Map物件，每個Map的key值為該欄位的名稱，value為值
     * @param srcFile excel檔案來源路徑
     * @return Map or null or 空值物件
     */
    public Map<String,List<Map<String,String>>> getMasterDetailExcel(String srcFile){
    	try{
    		Map<String,List<Map<String,String>>> resultMap = new java.util.HashMap<String,List<Map<String,String>>>();
    		List<Map<String,String>> masterList = new ArrayList<Map<String,String>>();
    		List tempList = null;
    		
    		File xlsFile = new File(srcFile);
    		String fileExt = Common.getFileExtension(xlsFile);
    		int sheetIndex = 1;//從1以後的sheet皆為detail
    		//分辨是excel2003或是2007以後的版本
    		if("xls".equals(fileExt)){
    			List mList = getJExcelModel(srcFile, 0, true);
    			//size一定要大於1，因為第一行為表頭，其餘才是資料，如果小於1則無資料
    			if(mList!=null && mList.size() > 1){
    				tempList = getListFromExcel(mList);
    				if(tempList!=null)masterList.addAll(tempList);
    			}
    			Workbook workbook = Workbook.getWorkbook(new File(srcFile));
    			Sheet[] sheets = workbook.getSheets();
    			if(sheets != null && sheets.length > sheetIndex){
	    			for(int i = 1 ; i < sheets.length ; i ++){
	    				List dList = getJExcelModel(srcFile, i, true);
	    				tempList = getListFromExcel(dList);
	    				if(tempList!=null)resultMap.put("detail"+i, tempList);
	    			}
    			}
    			workbook.close();
    		}else if("xlsx".equals(fileExt)){
    			List mList = getPOIModelFormatToString(srcFile, 0, true, null);
    			if(mList!=null && mList.size() > 1){
    				tempList = getListFromExcel(mList);
    				if(tempList!=null)masterList.addAll(tempList);
    			}
    			FileInputStream fis = null;
    			try{
    			fis = new FileInputStream(srcFile);
    			HSSFWorkbook workbook = new HSSFWorkbook();
    			while(true){
    				try{
	    				HSSFSheet sheet = workbook.getSheetAt(sheetIndex);
	    				List dList = getPOIModelFormatToString(srcFile, sheetIndex, true ,null);
	    				tempList = getListFromExcel(dList);
	    				if(tempList!=null)resultMap.put("detail"+sheetIndex, tempList);
	    				sheetIndex ++;
    				}catch(IndexOutOfBoundsException ioobe){
    					break;
    				}
    			}
    			}catch(IOException ioe){throw ioe;}
    			catch(IndexOutOfBoundsException ioobe){throw ioobe;}
    			finally{
    				if(fis!=null)fis.close();
    			}
    		}
			resultMap.put("master", masterList);
			return resultMap;
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return null;
    }
    
    private List<Map<String, String>> getListFromExcel(List mList) {
    	if(mList != null && mList.size() > 0){
    		List<Map<String,String>> resultList = new ArrayList<Map<String,String>>();
    		String[] header = (String[])mList.get(0);
    		for(int i = 1 ; i < mList.size() ; i ++){
    			Map<String,String> dataMap = new HashMap<String, String>(); 
    			String[] data = (String[])mList.get(i);
    			for(int j = 0 ; j < header.length ; j ++){    				
    				if(j < data.length)dataMap.put(header[j], data[j]);
    				else dataMap.put(header[j], "");
    			}
    			resultList.add(dataMap);
    		}
    		return resultList;
    	}
		return null;
	}
	private void testImport()throws Exception{
//    	XlsUtil obj = new XlsUtil();
//    	String filePath = "C:/projects/藥品不良品通報匯入格式.xls";
//    	Map map = obj.getMasterDetailExcel(filePath);
//    	System.out.println(map);
    }
    public static void main(String[] args) throws Exception {
    	XlsUtil obj = new XlsUtil();
    	obj.testImport();
    }
}
