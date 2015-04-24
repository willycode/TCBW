package com.kangdainfo.common.util.report;

import java.sql.Connection;

import javax.swing.table.TableModel;

public class TableModelReportEnvironment extends ReportEnvironment {
	static public final String ENV_TABLE_MODEL = "TableModel";
	static public final String ENV_TABLE_MODEL_LIST = "TableModelList";
	private static final long serialVersionUID = 1L;

	public TableModel getTableModel(){return (TableModel)get(ENV_TABLE_MODEL);}	
	public void setTableModel(TableModel model){put(ENV_TABLE_MODEL,model);	}
	public void setTableModelList(java.util.List models){put(ENV_TABLE_MODEL_LIST,models);	}	
	public java.util.List getTableModelList(){return (java.util.List)get(ENV_TABLE_MODEL_LIST);}	

}
