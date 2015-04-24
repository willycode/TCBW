package com.kangdainfo.common.util;

import java.sql.*;
import java.util.*;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.owasp.esapi.ESAPI;

import com.kangdainfo.persistence.PersistenceServiceGetter;
import com.kangdainfo.util.database.bo.DbTable;
import com.kangdainfo.util.database.bo.DbTableColumn;
import com.kangdainfo.util.database.bo.DbTableRow;
import com.kangdainfo.util.database.bo.DbTableRowColumn;

/**
 * JDBC 操作函式
 * 
 * @author Kang Da Information
 *
 */
public class Database {
	
	Connection conn = null;
	PreparedStatement preStmt = null;
	Statement stmt = null;
	ResultSet rs=null;		
	String connName;
	
	private int stringTypes[] = new int[] { Types.VARCHAR, Types.CHAR };

	private int dateTypes[] = new int[] { Types.DATE, Types.TIMESTAMP,
			Types.TIME, Types.TIMESTAMP };

	private int numberTypes[] = new int[] { Types.INTEGER, Types.BIGINT,
			Types.SMALLINT, Types.DECIMAL, Types.DOUBLE, Types.FLOAT,
			Types.NUMERIC, Types.REAL, Types.TINYINT };

	public Database(java.sql.Connection conn) {
		this.conn = conn;
	}
	
	public Database(String connName) {
		if (connName!=null && connName.length()>0) {
			this.connName = connName;
			try {
				conn = DriverManager.getConnection("proxool." + connName);				
			} catch (SQLException e) {	
				e.printStackTrace();
			}
		} else {
			this.conn = getConnection();			
		}
	}	
	
	public Database() { 
		conn = getConnection();
	}	

	/**
	 * <br>
	 * <br>目的：取得Connection
	 * <br>參數：無
	 * <br>傳回：傳回Connection
	*/
	public Connection getConnection() {
		if (conn!=null) {
			return conn;
		} else {		
			try {
				if (connName!=null && connName.length()>0) {					
					conn = DriverManager.getConnection("proxool." + connName);	
				} else {
					conn = PersistenceServiceGetter.getInstance().getDataSource().getConnection();
				}
			} 
			catch (SQLException e) 
			{	
				
				
				String methodName =e.getStackTrace()[0].getMethodName();
		        if (methodName.equalsIgnoreCase("checkSimultaneousBuildThrottle") || 
		            methodName.equalsIgnoreCase("quickRefuse"))
		        {
		          try
		          {
		            Thread.sleep(500);
		          }catch( InterruptedException ie){}
		        }
		        
		        e.printStackTrace();
		       
			}			
			return conn;			
		}
	}

	/**
	 * <br>
	 * <br>目的：取得Forward Only Statement
	 * <br>參數：Connection
	 * <br>傳回：傳回Statement
	*/
	public Statement getForwardStatement(Connection conn) {
		try {
			if (conn==null) conn = getConnection();
			stmt = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return stmt;
	}
	
	public Statement getForwardStatement() {
		try {
			if (conn==null) conn = getConnection();
			//if (stmt!=null) stmt.close();
			return conn.createStatement(ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return stmt;
	}	

	/**
	 * <br>
	 * <br>目的：取得Scroll Statement
	 * <br>參數：Connection
	 * <br>傳回：傳回Statement
	*/
	public Statement getScrollStatement(Connection conn){
		try {
			if (conn==null) conn = getConnection();
			//if (stmt!=null) stmt.close();
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			//return conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return stmt;		
	}

	public Statement getScrollStatement(){
		try {
			if (conn==null) conn = getConnection();
			//else return conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return stmt;		
	}
	
	public PreparedStatement getPreparedStatement(String sql){
		try {
			if (conn==null) conn = getConnection();
			//if (stmt!=null) stmt.close();
			return conn.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
  	 * <br>
  	 * <br>目的：取得資料集的裡的欄位名稱　
  	 * <br>參數：ResultSet
  	 * <br>傳回：欄位名稱集合
  	*/	
	public String[] getColumnNames(ResultSet rs) throws SQLException{
		   ResultSetMetaData md = rs.getMetaData();
		   String[] colNames = new String[md.getColumnCount()];
		   for(int i = 0; i < colNames.length; i++){
		      String nomColonne = md.getColumnName(i+1);
		      colNames[i] = nomColonne;
		   }
		   return colNames;
	}		
	
	/**
	 * get TableColumns
	 * 
	 * @param dataSourceName
	 * @param tableName
	 * @return List <DbTableColumn>
	 * @throws Exception
	 */
	public List getTableColumns(String tableName) throws Exception {
		String sql = "SELECT * FROM " + tableName;
		return executeQuery(getTopNSql(2,sql, 1),	true);
	}	
	

	/**
	 * 
	 * @param sql
	 * @param onlyColumn
	 * @return onlyColumn?List<DbTableRowColumn>:List<DbTableRow>
	 * @throws Exception
	 */
	public List executeQuery(String sql,boolean onlyColumn) throws Exception {
		List<Object> datas = new ArrayList<Object>();
		Statement stat = conn.createStatement();
		ResultSet rs = stat.executeQuery(sql);
		ResultSetMetaData meta = rs.getMetaData();
		int col = meta.getColumnCount();
		// ~~~~~~~~~~~~~~~~~~~~~~~~~
		// only query column
		// ~~~~~~~~~~~~~~~~~~~~~~~~~
		if (onlyColumn) {
			String tableName = getTableNameFromSql(sql);
			String pkName = getPrimaryKey(conn, null, null, tableName);
			for (int i = 1; i <= col; i++) {
				String name = meta.getColumnName(i);
				String type = meta.getColumnTypeName(i);
				int typeId = meta.getColumnType(i);
				int precision = meta.getPrecision(i);
				int scale = meta.getScale(i);
				String label = meta.getColumnLabel(i);
				int displaySize = meta.getColumnDisplaySize(i);
				boolean allowNull = (meta.isNullable(i) == 0) ? false : true;
				boolean isPk = (name.equals(pkName)) ? true : false;
				DbTableRowColumn column = new DbTableRowColumn(name, type,
						typeId, label, precision, scale, displaySize,
						allowNull, isPk, null);
				datas.add(column);
			}
		}
		// ~~~~~~~~~~~~~~~~~~~~~~~~~
		// query data
		// ~~~~~~~~~~~~~~~~~~~~~~~~~
		else {
			while (rs.next()) {
				DbTableRow row = new DbTableRow();
				String tableName = getTableNameFromSql(sql);
				String pkName = getPrimaryKey(conn, null, null, tableName);
				for (int i = 1; i <= col; i++) {
					String name = meta.getColumnName(i);
					String type = meta.getColumnTypeName(i);
					int typeId = meta.getColumnType(i);
					int precision = meta.getPrecision(i);
					int scale = meta.getScale(i);
					String label = meta.getColumnLabel(i);
					int displaySize = meta.getColumnDisplaySize(i);
					boolean allowNull = (meta.isNullable(i) == 0) ? false
							: true;
					boolean isPk = (name.equals(pkName)) ? true : false;
					Object value = rs.getObject(i);
					DbTableRowColumn column = new DbTableRowColumn(name, type,
							typeId, label, precision, scale, displaySize,
							allowNull, isPk, value);
					row.addColumn(column);
				}
				datas.add(row);
			}
		}
		rs.close();
		stat.close();		
		return datas;
	}	
	
	public String getTopNSql(int dbType, String sql, int nRow) {
		if (dbType==1) {
			String ss = "SELECT ";
			sql = sql.substring(0, ss.length()) + " TOP " + nRow + " "
					+ sql.substring(ss.length());
		} else if (dbType==2) {
			sql += " FETCH FIRST " + nRow + " ROWS ONLY ";
		}
		return sql;
	}	
	
	public String getTableNameFromSql(String sql) {
		String ary[] = sql.toUpperCase().split(" ");
		int idx = ArrayUtils.indexOf(ary, "FROM");
		return ary[idx + 1];
	}	
	
	/**
  	 * <br>
  	 * <br>目的：執行查詢sql　
  	 * <br>參數：String sql, List valueList
  	 * <br>傳回：傳回ResultSet
  	*/
	public ResultSet querySQL(String sql, java.util.List valueList) throws Exception{
		if (conn==null) conn = getConnection();	
		PreparedStatement pstmt = conn.prepareStatement(sql,ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY);
		if(valueList != null && valueList.size() > 0)
			for(int i=0;i<valueList.size();i++){
				int index = i + 1;
				Object value = valueList.get(i);
				if(value instanceof String){
					pstmt.setString(index, Common.get(value));
				} else if(value instanceof Integer){
					pstmt.setInt(index, Common.getInt(value));
				} else if(value instanceof java.sql.Date){
					pstmt.setDate(index, (java.sql.Date) value);
				} else if(value instanceof Double){
					pstmt.setDouble(index, Common.getNumeric(value));
				} else if(value instanceof Long){
					pstmt.setLong(index, Common.getLong(value));
				} else if(value instanceof Long){
					pstmt.setTimestamp(index, (Timestamp) value);
				} else if(value instanceof Long){
					pstmt.setTime(index, (Time) value);
				} else {
					throw new Exception("PreparedStatement type is not correct");
				}
			}
		
		return pstmt.executeQuery();		
		//return getForwardStatement().executeQuery(sql);
	}
	
	/**
  	 * <br>
  	 * <br>目的：執行可更新的查詢sql　
  	 * <br>參數：無
  	 * <br>傳回：傳回ResultSet
  	*/
	public ResultSet queryUpdateSQL(String sql) throws Exception{
		if (conn==null) conn = getConnection();		
		PreparedStatement pstmt = conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
		return pstmt.executeQuery(sql);				
	}
	
	/**
     * <br>
     * <br>目的：執行查詢sql　
     * <br>參數：無
     * <br>傳回：傳回ResultSet
    */
    public ResultSet querySQL(String sql, boolean scrollable) throws Exception{
		if (conn==null) conn = getConnection();	
		PreparedStatement pstmt = null;
		sql = ESAPI.encoder().encodeForHTML(sql);
        if(scrollable){        	
        	pstmt = conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        }else{
        	pstmt = conn.prepareStatement(sql,ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY);
        }        
        return pstmt.executeQuery();
    }       	
    
	/**
  	 * <br>
  	 * <br>目的：執行sql　
  	 * <br>參數：無
  	 * <br>傳回：傳回ResultSet
  	*/
	public void excuteSQL(String[] sql) throws Exception{
		try {
			if (conn==null) conn = getConnection();
			conn.setAutoCommit(false);
			//stmt = getForwardStatement(conn);
			stmt = getForwardStatement();
			for(int i=0; i<sql.length; i++){
			    if(!"".equals(sql[i].toString()))
			        stmt.executeUpdate(sql[i]);
			}
			stmt.close();
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			System.out.println("excuteSQL Exception = "+sql[0]);
			throw new Exception(e);			
		} 	
	}	
	
	/**
  	 * <br>
  	 * <br>目的：執行查詢sql(可捲動)　
  	 * <br>參數：無
  	 * <br>傳回：傳回ResultSet
  	*/
	//會有SQL Injection 改由使用querySQLByPrepareStatement此方法
	/*
	public ResultSet querySQLByScroll(String sql) throws Exception{
		//conn = getConnection();
		if (conn==null) conn = getConnection();
		stmt = getScrollStatement(conn);
		rs = stmt.executeQuery(ESAPI.encoder().encodeForHTML(sql));
		return rs;
	}
	*/	
	
	/**
  	 * <br>
  	 * <br>目的：執行查詢sql by prepareStatement(可捲動)
  	 * <br>參數：無
  	 * <br>傳回：傳回ResultSet
  	*/
	public ResultSet querySQLByPrepareStatement(String sql,List parameter) throws Exception{
		//conn = getConnection();
		if (conn==null) conn = getConnection();
		//stmt = getScrollStatement(conn);
		preStmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		if(parameter != null && parameter.size() > 0 ){
			for(int i = 0 ; i < parameter.size() ; i ++)
				preStmt.setObject(i+1, parameter.get(i));
		}
		rs = preStmt.executeQuery();
		return rs;
	}
	
	/**
	 * <br>
	 * <br>目的：結束Connection, Statement, ResultSet
	 * <br>參數：Connection, Statement, ResultSet
	 * <br>傳回：無
	*/
	public void closeAll(){
		try {
			if (rs!=null){ rs.close(); }
			if (stmt!=null){ stmt.close(); }
			if (conn!=null){ conn.close(); }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//有些時候自動編號的位數有限,必須先清空原來的資料方能使Key不重覆, 就必須使用到以下的函式
	public void setAutoCommit(boolean sFlag) throws SQLException {
		try {
			if ( conn != null ) {
				conn.setAutoCommit(sFlag);
			}
		} catch (SQLException sqle) {	      
			throw new SQLException("SQLException: Unable to setAutoCommit properties.\n"+sqle);
		}
	}
		  
	public void doCommit() throws Exception {
		if ( conn != null ) {		
			try {	
				conn.commit();
			} catch (SQLException sqle) {
				conn.rollback();	  	
				throw new SQLException("SQLException: Unable to commit sqlConnection.\n"+sqle);
			}
		}
	}
	
	public void doRollback() throws Exception {
		try {
			if ( conn != null ) {
				conn.rollback();
			}			
		} catch (SQLException sqle) {				  	
			throw new SQLException("SQLException: Unable to rollback transaction.\n"+sqle);
		}
	}
	
	/**
	 * 此函式沒有交易控制,需得自己維護整個交易,使用時要慬慎
	 */
	public void exeSQL(String[] sql) throws Exception{
		if (conn == null) conn = getConnection();
		try {
			//stmt = getForwardStatement(conn);
			//stmt = getForwardStatement();
			stmt = conn.createStatement();
			for(int i=0; i<sql.length; i++){
			    if(!"".equals(sql[i].toString()))
			        stmt.executeUpdate(sql[i]);
			}			
			stmt.close();
		} catch (Exception e) {
			System.out.println("excuteSQL Exception = "+sql[0]);
			throw new Exception(e);			
		} 	
	}	
	
	/**
	 * 此函式沒有交易控制,需得自己維護整個交易,使用時要慬慎
	 */
	public void exeSQL(String sSQL) throws Exception {
		if (conn == null) conn = getConnection();
		try {
			if (stmt==null) stmt = conn.createStatement();
			stmt.executeUpdate(sSQL);
		} catch (Exception sqle) {	
			System.out.println("excuteSQL Exception = "+sSQL);					
			throw new Exception(sqle);
		}		
	}	
	
    public String getLookupField(String sSQL, java.util.List valueList) {
    	String rStr = "";    		
    	try {
    		ResultSet rs = querySQL(sSQL, valueList);
    		if (rs.next()){
    			rStr = Common.get(rs.getString(1));
    		}
    		rs.getStatement().close();
    		rs.close();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return rStr;
    }	
    
    public String getLookupField(String sSQL, String s, java.util.List valueList) {
    	String rStr = "";    		
    	try {
    		ResultSet rs = querySQL(sSQL, valueList);
    		if (rs.next()){
    			rStr = Common.get(rs.getString(1));
    		} else {
    			rStr = s;
    		}
    		rs.getStatement().close();
    		rs.close();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return rStr;
    }
    
    public String getOption(String sql, String selectedOne, java.util.List valueList) {
        String rtnStr = "<option value=''>請選擇</option>";
        try {
        	ResultSet rs = querySQL(sql, valueList);
            while (rs.next()) {
                String id = rs.getString(1);
                String name = rs.getString(2);
                
                rtnStr = rtnStr + "<option value='" + id + "' ";
                if (selectedOne!= null && selectedOne.equals(id)) {
                    rtnStr = rtnStr + " selected ";
                }
                rtnStr = rtnStr + ">" + name + "</option>\n";
            }
        } catch (Exception ex) {
            rtnStr = "<option value=''>查詢錯誤</option>";
            ex.printStackTrace();
        }
        return rtnStr;
    }    
    
	/**
  	 * <br>
  	 * <br>目的：取得資料集的裡的Table名稱及其欄位資訊
  	 * <br>參數：schema, tableName
  	 * <br>傳回：欄位名稱集合
  	*/	
	public java.util.List<DbTable> getTables(String schema, String tableName) throws Exception {
		List<DbTable> tables = new ArrayList<DbTable>();
		DatabaseMetaData meta = conn.getMetaData();
		ResultSet data = meta.getTables(conn.getCatalog(), schema, tableName, new String[] { "TABLE" });
		while (data.next()) {
			String catalog = data.getString(1);
			String theSchema = data.getString(2);
			String name = data.getString(3);
			String pk = getPrimaryKey(conn, catalog, theSchema, name);
			DbTable table = new DbTable(catalog, theSchema, name, pk);
						
			java.util.List<DbTableRowColumn> colsList = new java.util.ArrayList<DbTableRowColumn>();			
			ResultSet cols = meta.getColumns(catalog, theSchema, name, null);
			while (cols.next()) {
				DbTableRowColumn col = new DbTableRowColumn();
				col.setName(cols.getString("COLUMN_NAME"));
				col.setTypeId(cols.getInt("DATA_TYPE"));
				col.setType(cols.getString("TYPE_NAME"));
				col.setDisplaySize(cols.getInt("COLUMN_SIZE"));
				col.setPrecision(cols.getInt("DECIMAL_DIGITS"));
				col.setScale(cols.getInt("NUM_PREC_RADIX"));
				col.setDescription(cols.getString("REMARKS"));
				col.setAllowNull(Common.get(cols.getString("IS_NULLABLE")).equals("YES"));		
				colsList.add(col);
			}
			cols.getStatement().close();
			cols.close();			
			table.setColumns(colsList);
			
			tables.add(table);
		}
		data.close();		
		return tables;
	}	
	
	private String getPrimaryKey(Connection conn, String catalog,
			String schema, String name) throws Exception {
		DatabaseMetaData meta = conn.getMetaData();
		ResultSet set = meta.getPrimaryKeys(catalog, schema, name);
		String pk = null;
		while (set.next() && pk == null) {
			pk = (String) set.getObject(4);
		}
		set.getStatement().close();
		set.close();
		return pk;
	}    
	
	/**
	 * exportDatas
	 * 
	 * @return sql
	 * @throws Exception
	 */
	public String exportDatas(String schema) throws Exception {
		StringBuffer sb = new StringBuffer();
		// get all table
		List<DbTable> tables = getTables(schema, null);
		for (DbTable table : tables) {
			String tableName = table.getName();
			sb.append("-- " + tableName + "\r\n"); // add comment
			// sb.append("delete from " + tableName + ";\r\n");
			sb.append(exportData(tableName)); // export table
			sb.append("\r\n");
		}
		return sb.toString();
	}

	/**
	 * export Data to Sql
	 * 
	 * @return sql
	 * @throws Exception
	 */
	public String exportData(String tableName) throws Exception {
		StringBuffer sb = new StringBuffer();
		// get table data
		List<DbTableRow> datas = getTableData(tableName);
		if (datas == null || datas.isEmpty()) {
			List<DbTableColumn> columns = getTableColumns(tableName);
			sb.append("INSERT INTO " + tableName + " (");
			for (DbTableColumn column : columns) {
				String name = column.getName();
				sb.append(name + ", ");
			}
			sb.setLength(sb.length() - 2); // trim last char ', '
			sb.append(") VALUES (");
			for (DbTableColumn column : columns) {
				int typeId = column.getTypeId();
				sb.append(toSqlString(typeId, "?") + ", ");
			}
			sb.setLength(sb.length() - 2); // trim last char ', '
			sb.append("); \r\n");
		} else {
			for (DbTableRow row : datas) {
				// create sql
				sb.append("INSERT INTO " + tableName + " (");
				// add header
				for (DbTableRowColumn column : row.getColumns()) {
					String name = column.getName();
					sb.append(name + ", ");
				}
				// add body
				sb.setLength(sb.length() - 2); // trim last char ', '
				sb.append(") VALUES (");
				for (DbTableRowColumn column : row.getColumns()) {
					int typeId = column.getTypeId();
					Object value = column.getValue();
					sb.append(toSqlString(typeId, value) + ", ");
				}
				sb.setLength(sb.length() - 2); // trim last char ', '
				sb.append("); \r\n");
			}
		}
		return sb.toString();
	}

	/**
	 * get TableData
	 * 
	 * @param tableName
	 * @return List<ResultSetMetaData>
	 * @throws Exception
	 */
	public List<DbTableRow> getTableData(String tableName) throws Exception {
		//return getTableData(tableName);
		String sql = "SELECT * FROM " + tableName;
		return executeQuery(sql, false);		
	}

	/**
	 * get TableData
	 * 
	 * @param dataSourceName
	 * @param tableName
	 * @return List<ResultSetMetaData>
	 * @throws Exception
	 */
	public List getTableData(String dataSourceName, String tableName)
			throws Exception {
		String sql = "SELECT * FROM " + tableName;
		return executeQuery(sql, false);
	}	
	/**
	 * to Sql String
	 * 
	 * @param typeId
	 * @param value
	 * @return sql
	 */
	public String toSqlString(int typeId, Object value) {
		String ret = "";
		if (value == null) {
			ret = "null";
		} else if (isString(typeId)) {
			ret = "'" + value.toString() + "'";
		} else if (isDate(typeId)) {
			ret = "'" + value.toString() + "'";
			ret = "TIMESTAMP(" + ret + ")"; // ibm-db2
		} else if (isNumber(typeId)) {
			ret = value.toString();
		}
		return ret;
	}

	public boolean isString(int typeId) {
		return (ArrayUtils.indexOf(stringTypes, typeId) > -1) ? true : false;
	}

	public boolean isDate(int typeId) {
		return (ArrayUtils.indexOf(dateTypes, typeId) > -1) ? true : false;
	}

	public boolean isNumber(int typeId) {
		return (ArrayUtils.indexOf(numberTypes, typeId) > -1) ? true : false;
	}	
	
}
