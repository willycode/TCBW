package com.kangdainfo.common.view.sys.ap;


import java.sql.ResultSet;
import java.sql.SQLException;

import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.Database;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.persistence.PersistenceServiceGetter;

public class SYSAP501F extends SuperBean {
	
	String npbTable;
	String graTable;
	String sqlCmd;
	String isUpdate;
	
	public void setNpbTable(String s){npbTable=checkSet(s);}
	public String getNpbTable() { return checkGet(npbTable); }

	public void setGraTable(String s) { graTable=checkSet(s); }
	public String getGraTable() { return checkGet(graTable); }
	
	public void setSqlCmd(String s) { sqlCmd=Common.get(s); }
	public String getSqlCmd() { return Common.get(sqlCmd); }
	
	public void setIsUpdate(String s){isUpdate=checkSet(s);}
	public String getIsUpdate() {return checkGet(isUpdate); }
	
	public String getTableContent() throws SQLException {
		Database db = new Database(PersistenceServiceGetter.getInstance().getJdbcTemplate().getDataSource().getConnection());
		StringBuffer sbHTML = new StringBuffer("");		
		try {
			String sql = "";
			if (!"".equals(getSqlCmd()) && !"".equals(getIsUpdate())) {
				String[] arrSQL = getSqlCmd().split(";");
				//sql = getSqlCmd();
				db.setAutoCommit(false);
				db.exeSQL(arrSQL);
				db.doCommit();			
				sbHTML.append("SQL指令執行完成！");
			} else {			
				if (!"".equals(getSqlCmd()))
					sql = getSqlCmd();
				if (!"".equals(getGraTable()))
					sql="select * from " + getGraTable();
				if (!"".equals(getNpbTable()))
					sql="select * from " + getNpbTable();
				if (sql.length()>10) {
					ResultSet rs = db.querySQL(sql, true);
					String[] colNames = db.getColumnNames(rs);
					
					sbHTML.append("<table class=\"table_form\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\">");
					sbHTML.append("<thead id=\"listTHEAD\">");
					sbHTML.append("<tr>");
					sbHTML.append("    <th class=\"listTH\" ><a class=\"text_link_w\" >No.</a></th>");				
					for(int i=0;i<colNames.length;i++){					
						//item.put(colNames[j],Common.get(rs.getString(colNames[j])));
						sbHTML.append("    <th class=\"listTH\"><a class=\"text_link_w\" onClick=\"return sortTable('listTBODY',").append(i).append(",false);\" href=\"#\">").append(colNames[i]).append("</a></th>");					
					}					
					sbHTML.append("</tr>");	
					sbHTML.append("</thead>");
					
					processCurrentPageAttribute(rs);
					if (getTotalRecord() > 0) {
						int count = getRecordStart();
						int end = getRecordEnd();
						sbHTML.append("<tbody id=\"listTBODY\">");					
						do {
							if (count > end) break;	
							sbHTML.append(" <tr class='listTR'>");
							sbHTML.append(" <td nowrap class='listTD' >").append(count).append(".</td> ");
							for(int i=0;i<colNames.length;i++){
								sbHTML.append(" <td nowrap class='listTD' style='text-align:left'>").append(Common.get(rs.getString(colNames[i]))).append("</td> ");							
							}
							sbHTML.append(" </tr> ");
							count++;
						} while (rs.next());
						sbHTML.append("</tbody>");
					}
					sbHTML.append("</table>");	
				}
			}			
		} catch (Exception e) {			
			e.printStackTrace();
			try{
				db.doRollback();
			}catch(Exception e1) {
				e1.printStackTrace();
			}
			return "";
		}
		return sbHTML.toString();		
	}
	
	
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