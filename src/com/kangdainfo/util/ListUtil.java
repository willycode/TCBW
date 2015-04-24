package com.kangdainfo.util;

import java.util.ArrayList;
import java.util.List;

public class ListUtil {

	public static List reverse(List list) {
		List reversedList = new ArrayList();
		for(int i=list.size()-1;i>=0;i--) {
			reversedList.add(list.get(i));
		}
		return reversedList;
	}
	
	public static Object getFirstObject(java.util.Collection list) {
		if (list!=null && list.size()>0) {
			return list.iterator().next();
		}
		return null;
	}	
	
}
