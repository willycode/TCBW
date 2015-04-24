package com.kangdainfo.util.lang;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class StringUtil {
	
	public static String limitLength(String s, int maximumLength) {
		if(s!=null) {
			if(s.length()>maximumLength) {
				s = s.substring(0,maximumLength);
			}
		}
		return s;
	}
	
	public static int indexOf(String content, char c, int fromIndex, int nthOccurence) {
		if(nthOccurence==0) {
			return -1;
		}
		int index = fromIndex;
		int start = fromIndex;
		while(true) {
			if(nthOccurence==0) {
				break;
			}
			index = content.indexOf(""+c,start);
			if(index==-1) {
				return -1;
			}
			start = index+1;
			nthOccurence--;
		}
		return index;
	}
	
	public static String[] splitByNewLine(String content) {
		content = content.replaceAll("\r\n", "\n");
		content = content.replaceAll("\r", "\n");
		content = content.replaceAll("\n", "\r\n");
		String lines[] = content.split("\r\n");
		return lines;
	}
	
	public static boolean isNullOrEmpty(String content) {
		return content==null || content.length()==0;
	}
	public static boolean isNullOrTrailingWhiteSpace(String content) {
		return content==null || content.length()==0 || content.trim().length()==0;
	}
	
	
	public static boolean isBetween(String target, String start, String end, boolean includeStartEnd) {
		int compareStart = target.compareTo(start);
		int compareEnd = target.compareTo(end);
		if(includeStartEnd) {
			return compareStart>=0 && compareEnd<=0;
		}
		return compareStart>0 && compareEnd<0;
	}
	
	public static String extractString(String s, int beginIndex, int endIndex) {
		if(s.length()>=endIndex) {
			return s.substring(beginIndex,endIndex);	
		}
		else {
			return null;
		}
	}
	public static String extractFirstLine(String s) {
		if(s==null) {
			return null;
		}
		else {
			int index = -1;
			
			index = s.indexOf('\r');
			if(index!=-1) {
				return s.substring(0,index);
			}
			index = s.indexOf('\n');
			if(index!=-1) {
				return s.substring(0,index);
			}
			return s;
		}
	}
	
	public static String[] split(String s, char c) {
		int separatorLength = 1;
		List<Integer> list = new ArrayList<Integer>();
		int index = 0;
		while(true) {
			index = s.indexOf(c,index);
			if(index==-1) {
				break;
			}
			list.add(index);
			index += separatorLength;
		}
		if(list.size()==0) {
			return null;
		}
		List<String> listOfString = new ArrayList<String>();
		int fromIndex = 0;
		for(int i=0,max_i=list.size(); i < max_i; i++) {
			index = list.get(i);
			String sub = s.substring(fromIndex,index);
			fromIndex = index+separatorLength;
			listOfString.add(sub);			
		}
		String lastSub = s.substring(fromIndex);
		listOfString.add(lastSub);
		return (String[]) listOfString.toArray(new String[listOfString.size()]);
	}
	
	public static String[] removeNullOrEmptyElements(String array[]) {
		if(array==null || array.length==0) {
			return null;
		}
		List list = new ArrayList();
		for(int i=0; i < array.length; i++) {
			if(array[i]!=null && array[i].length()!=0) {
				list.add(array[i]);
			}
		}
		return (String[]) list.toArray(new String[list.size()]);
	}
	public static Integer[] toIntegerArray(String array[]) {
		Integer newArray[] = new Integer[array.length];
		for(int i=0,max_i=array.length; i < max_i; i++) {
			newArray[i] = Integer.valueOf(array[i]);
		}
		return newArray;
	}
	
	public static String toNumberString(double d) {
		return NumberFormat.getNumberInstance().format((Number)d) ;
	}
	
	/**
	 * 由middle-gen抄來的function<br>
	 * 主要用途是將資料庫欄位名稱轉成java變數名稱<br>
	 * @param s
	 * @return
	 */
	public static String columnNameToVariableName(String s) {
		if ("".equals(s)) {
			return s;
		}
		StringBuilder result = new StringBuilder();
		boolean capitalize = true;
		boolean lastCapital = false;
		boolean lastDecapitalized = false;
		String p = null;
		for (int i = 0; i < s.length(); i++) {
			String c = s.substring(i, i + 1);
			if ("_".equals(c) || " ".equals(c)) {
				capitalize = true;
				continue;
			}
			if (c.toUpperCase().equals(c)) {
				if (lastDecapitalized && !lastCapital) {
					capitalize = true;
				}
				lastCapital = true;
			} else {
				lastCapital = false;
			}

			if (capitalize) {
				if (p == null || !p.equals("_")) {
					result.append(c.toUpperCase());
					capitalize = false;
					p = c;
				} else {
					result.append(c.toLowerCase());
					capitalize = false;
					p = c;
				}
			} else {
				result.append(c.toLowerCase());
				lastDecapitalized = true;
				p = c;
			}

		}
		String r = result.toString();
		return r;
	}	
	
	public static void main(String args[]) {
		/*
		String array[] = { "096005000093","096005000094", "096005000095", "096005000096", "096005000097", "096005000098" };
		for(int i=0; i < array.length; i++) {
			boolean isBetween = isBetween(array[i],"096005000093", "096005000098", true);
			System.out.println(array[i] + "=" + isBetween);
		}
		*/
		String array[] = split("hel.lo21..u",'.');
		if(array==null) {
			array = new String[0];
		}
		for(int i=0; i < array.length; i++) {
			System.out.print(array[i] + ",");
		}
		System.out.println("");
		
	}
	

	
	
}
