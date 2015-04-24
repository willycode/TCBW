package com.kangdainfo.util.lang.exception;

public class ErrorMsg extends MyResourceBundle {

	private ErrorMsg() {
		super();
	}
	public ErrorMsg(String key) {
		super(key);
	}	


	public static String convertToString(Exception e) {
		return convertToString(e,"\r\n");
	}
	public static String convertToString(Exception e, String lineSeparator) {
		StringBuffer sb = new StringBuffer();
		if(e!=null) {
			sb.append(e.getMessage()).append(lineSeparator);
			StackTraceElement element[] = e.getStackTrace();
			for(int i=0,max_i=element.length; i < max_i; i++) {
				sb.append(element[i]).append(lineSeparator);
			}
		}
		return sb.toString();
	}
	
	
}
