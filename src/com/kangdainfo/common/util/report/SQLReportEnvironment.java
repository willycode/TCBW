package com.kangdainfo.common.util.report;

import java.sql.Connection;

public class SQLReportEnvironment extends ReportEnvironment {
	static public final String ENV_USE_DATASOURCE = "UseDataSource";
	static public final String ENV_DATASOURCE_SQL = "DataSourceSQL";
	static public final String ENV_DATASOURCE_SQL_LIST = "DataSourceSQLList";
	static public final String ENV_CONNECTION = "Connection";
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public boolean isUseDataSource(){
		Object uc = this.get(ENV_USE_DATASOURCE);
		if("true".equals(uc)){
			return true;
		}
		return false;
	}
	
	public void setUseDataSource(boolean b){
		this.put(ENV_USE_DATASOURCE,(b)?"true":"false");
	}
		
	public String getDataSourceSQL(){return (String)this.get(ENV_DATASOURCE_SQL);	}	
	public void setDataSourceSQL(String sql){this.put(ENV_DATASOURCE_SQL,sql);	}	
	public java.util.List getDataSourceSQLList(){return (java.util.List)this.get(ENV_DATASOURCE_SQL_LIST);	}	
	public void setDataSourceSQLList(java.util.List sqlList){this.put(ENV_DATASOURCE_SQL_LIST,sqlList);	}		
	
	public Connection getConnection(){	return (Connection)this.get(ENV_CONNECTION);	}	
	public void setConnection(Connection conn){	this.put(ENV_CONNECTION,conn);	}	

}
