package com.kangdainfo.common.util.report;

import java.util.Hashtable;

public class ReportWordEnvironment extends Hashtable{

	static public final String EVN_REPORT_FORMATE = "ReportFormate";
	static public final String ENV_WORD_FILE = "WordFile";
	static public final String ENV_SUBWORD_FILE = "SubWordFile";
	static public final String EVN_WORD_FILE_LIST = "WordFileList";
	static public final String EVN_WORD_TABLE_MODEL = "WordTableModel";
	
	static public final String VAL_FORMAT_DOC = "DOC";
	static public final String VAL_FORMAT_DOCX = "DOCX";
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String getFormat() {
		return (String)get(EVN_REPORT_FORMATE);
	}
	public void setFormat(String format) {
		put(EVN_REPORT_FORMATE,format);
	}
	public String getWordFile() {
		return (String)get(ENV_WORD_FILE);
	}
	public void setWordFile(String jasperFile) {
		put(ENV_WORD_FILE,jasperFile);
	}
	public String getSubWordFile() {
		return (String)get(ENV_SUBWORD_FILE);
	}
	public void setSubWordFile(String jasperFile) {
		put(ENV_SUBWORD_FILE,jasperFile);
	}
	public java.util.List getWordFileList() {
		return (java.util.List) get(EVN_WORD_FILE_LIST);
	}
	public void setWordFileList(java.util.List wordFileList) {
		put(EVN_WORD_FILE_LIST,wordFileList);
	}	
	public Object[] getWordTableModel() {
		return (Object[]) get(EVN_WORD_TABLE_MODEL);
	}
	public void setWordTableModel(Object[] wordTableModel) {
		put(EVN_WORD_TABLE_MODEL,wordTableModel);
	}		
}
