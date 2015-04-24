package com.kangdainfo.util.lang;

public class NumberUtil {

	public static boolean isInteger(String s) {
		try {
			Integer.parseInt(s);
			return true;
		}
		catch(Exception e) {
			return false;
		}
	}
	
}
