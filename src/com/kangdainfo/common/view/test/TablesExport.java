package com.kangdainfo.common.view.test;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import com.kangdainfo.common.util.*;


public class TablesExport {

	String[] tables = {"COMMON_Department","COMMON_GROUP",			
			"COMMON_USER","COMMON_USERROLE",
			"COMMON_DTREE","COMMON_AUTH",
			"COMMON_CODEKIND","COMMON_CODE",
			"COMMON_NEWS"};
	public String exportString() {
		StringBuilder sb = new StringBuilder();		
		for (int i=0; i<tables.length; i++) {
			sb.append("db2 export to ");
			sb.append(tables[i]);
			sb.append(" of ixf messages msgs.txt SELECT * FROM ");
			sb.append(tables[i]);
			sb.append("\n");
			
			//sb.append("db2 export to ");
			//sb.append(tables[i]);
			//sb.append(" of ixf messages msgs.txt SELECT * FROM ");
			//sb.append(tables[i]);
			//sb.append("\n");			
		} 
		return sb.toString();
	}
	
	public String importString() {
		StringBuilder sb = new StringBuilder();		
		for (int i=0; i<tables.length; i++) {
			sb.append("db2 import from ");
			sb.append(tables[i]);
			sb.append(" of ixf messages msgs.txt CREATE into ");
			sb.append(tables[i]);
			sb.append("\n");			
		}
		//REPLACE_CREATE
		return sb.toString();
	}
	
	public String dropTableString() {
		StringBuilder sb = new StringBuilder();		
		for (int i=(tables.length-1); i>=0; i--) {
			sb.append("drop table ");
			sb.append(tables[i]);	
			sb.append(";\n");
		}
		return sb.toString();		
	}
	
	public String deleteTableString() {
		StringBuilder sb = new StringBuilder();		
		for (int i=(tables.length-1); i>=0; i--) {
			sb.append("delete from ");
			sb.append(tables[i]);	
			sb.append(";\n");
		}
		return sb.toString();		
	}
	
	public void dropTables() {
		Database db = new Database();
		try {
			db.setAutoCommit(false);
    		int i=0;
			for (i=(tables.length-1); i>=0; i--) {
				db.exeSQL("drop table " + tables[i]);
			}
			db.doCommit();
			System.out.println("drop tables process done.");
		} catch (Exception e) {
			e.printStackTrace();
			try {
				db.doRollback();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} finally {
			db.closeAll();
		}
	}
	
	public void doUpdateProcess() {
		//String[] tables = {"TAST_Department","FAS_GBGT_Department","ACL_USER","TAST_DepartmentUser"};
		Database db = new Database();
		try {
			db.setAutoCommit(false);
    		int i=0, j=0;
			for (i=(tables.length-1); i>=0; i--) {
				//db.exeSQL("delete from " + tables[i]);
				//db.exeSQL("drop table " + tables[i]);
				try {							
					//resetId = db.getLookupField("select max(id)+1 from " + tables[i]);
		    		ResultSet rs = db.querySQL("select * from " + tables[i],null);		
					boolean tabHasIdentity = false; 
					String priKeyColName = "Id";
		    		ResultSetMetaData colRs = rs.getMetaData();
					for(j=1; j<=colRs.getColumnCount(); j++) {
						if(colRs.isAutoIncrement(j)) {
							priKeyColName = colRs.getColumnName(j);
							tabHasIdentity = true;
							break;
						}
					}	
					if(tabHasIdentity) {
			    		rs = db.querySQL("select max(" + priKeyColName + ")+1 from " + tables[i],null);
			    		String resetId = "1";
			    		String sql = "";
			    		if (rs.next()){
			    			resetId = Common.get(rs.getString(1));
							if (!"".equals(Common.get(resetId)) && Common.getInt(resetId)>0) {
								sql = "ALTER TABLE " + tables[i] + " ALTER COLUMN " + priKeyColName + " RESTART WITH " + resetId;
								//db.exeSQL(sql);
								//System.out.println("Good " + i +" : "+tables[i]);
							} else {
								sql = "ALTER TABLE " + tables[i] + " ALTER COLUMN " + priKeyColName + " RESTART WITH 1 ";
				    			//db.exeSQL(sql);
				    			//System.out.println("Good1 " + i +" : "+tables[i]);							
							}
			    		} else {
			    			sql = "ALTER TABLE " + tables[i] + " ALTER COLUMN " + priKeyColName + " RESTART WITH " + resetId;
			    			//db.exeSQL(sql);
			    			//System.out.println("Good1 " + i +" : "+tables[i]);			    			
			    		}
			    		System.out.println(sql+";");
			    		rs.close();
					}					
		    		

				} catch (Exception e) {												
					System.out.println("Error : "+tables[i]);					
					e.printStackTrace();
					continue;
				}				
			}				
			db.doCommit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				db.doRollback();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} finally {
			db.closeAll();
		}		
	}
	
	public void genCreateTableScript(String xlsFileName, int sheetNumber) throws Exception {
		XlsUtil xls = new XlsUtil();
		java.util.List<String[]> list = xls.getJExcelModel(xlsFileName, sheetNumber, true);
		if (list!=null && list.size()>2) {
			int j = 0;			
			StringBuilder sb = new StringBuilder();
			String[] t = list.get(1);
			
			sb.append("/** ").append(Common.get(t[1])).append(" **/\n");
			sb.append("create table ").append(Common.get(t[0])).append("(\n");			
			for (int i=3; i<list.size(); i++) {
				String[] o = list.get(i);							
				TemplateData obj = new TemplateData(o);
				if (!"".equals(obj.toSQL())) {
					sb.append(obj.toSQL());
				} else break;				
			}			
			sb.append(");\r\n");
			System.out.println(sb.toString());
		}		
	}
	public void genCreateTableScript(String xlsFileName, String sheetName) throws Exception {
		XlsUtil xls = new XlsUtil();
		java.util.List<String[]> list = xls.getJExcelModel(xlsFileName, sheetName, true, null);
		if (list!=null && list.size()>2) {			
			StringBuilder sb = new StringBuilder();
			String[] t = list.get(1);
			
			sb.append("/** ").append(Common.get(t[1])).append(" **/\n");
			sb.append("create table ").append(Common.get(t[0])).append("(\n");			
			for (int i=3; i<list.size(); i++) {
				String[] o = list.get(i);							
				TemplateData obj = new TemplateData(o);
				if (!"".equals(obj.toSQL())) {
					sb.append(obj.toSQL());
				} else break;				
			}			
			sb.append(");\r\n");
			System.out.println(sb.toString());
		}
		
	}	
	
	
	public static void main(String[] args) throws Exception {
		TablesExport obj = new TablesExport();
		//obj.doUpdateProcess();
		//obj.genCreateTableScript("E:\\KangDa\\TBGV\\05_工作區\\04_系統設計\\檔案架構\\Table_Schema\\ITT.xls", "ITT0043_DB");
		//System.out.println(obj.importString());
	}
}
