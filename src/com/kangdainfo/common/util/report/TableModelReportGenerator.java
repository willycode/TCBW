package com.kangdainfo.common.util.report;

import java.util.HashMap;

import javax.swing.table.TableModel;

import org.apache.log4j.Logger;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;

public class TableModelReportGenerator extends ReportGenerator {

	protected TableModel model;
	static private Logger logger = Logger.getLogger(TableModelReportGenerator.class);
	
	public TableModelReportGenerator(TableModelReportEnvironment env) {
		super(env);
	}
	
	private void loadEnvironment(){
		model = ((TableModelReportEnvironment)env).getTableModel();
		
		StringBuffer msg = new StringBuffer();
		msg.append("Environment[");
		msg.append("TableModel:").append(model).append(",");
		msg.append("]");
		logger.debug(msg.toString());		
	}	

	protected JasperPrint getJasperPrint(java.util.Map parms) throws Exception {
		loadEnvironment();		
		if(model==null) throw new IllegalArgumentException("TableModel is null");				
		JRDataSource ds = getDataSource();
		parms.put(JRParameter.REPORT_DATA_SOURCE ,ds);
		JasperPrint print = JasperFillManager.fillReport(jasperFile,parms,ds);
		return print;
	}
	protected java.util.List getJasperPrintList(java.util.Map parms) throws Exception {
		loadEnvironment();
		java.util.List list = new java.util.ArrayList();		
		java.util.List TableModelList = ((TableModelReportEnvironment)env).getTableModelList();		
		if (TableModelList == null ){
			if(model==null) throw new IllegalArgumentException("TableModel is null");				
			JRDataSource ds = getDataSource();
			parms.put(JRParameter.REPORT_DATA_SOURCE ,ds);
			JasperPrint print = JasperFillManager.fillReport(jasperFile,parms,ds);
			list.add(print);
		}else{			
			for (int i = 0; i < jasperFileList.size(); i++) {
				String jasperFilePath = (String)jasperFileList.get(i);
				java.util.Map p = parms;
				if (jasperParamList!=null && jasperParamList.size()>i) {
					p = (java.util.Map) jasperParamList.get(i);
					if (p!=null) {
						for (Object key : parms.keySet()) {
							if (p.get(key)==null) p.put(key, parms.get(key));
						}						
					} else {
						p = parms;
					}
				}				
				TableModel modelTemp = (TableModel)TableModelList.get(i);			
				JRTableModelDataSource ds = new JRTableModelDataSource(modelTemp);
				parms.put(JRParameter.REPORT_DATA_SOURCE ,ds);
				JasperPrint print = JasperFillManager.fillReport(jasperFilePath,p,ds);
				list.add(print);
			}			
		}
		return list;
	}	
	protected JRDataSource getDataSource() throws Exception{
		JRTableModelDataSource ds = new JRTableModelDataSource(model);
		return ds;
	}
	

}
