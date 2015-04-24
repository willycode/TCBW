package com.kangdainfo.util.lang.reflect.converter;

import java.util.List;
import java.util.Map;

import com.kangdainfo.util.lang.reflect.ClassUtil;

public class StringConverter {

	public static final String NEWLINE = "\r\n";
	public static final String NESTED_ELEMENT_DELIMITER = ".";
	public static final String SEPARATOR  = " : ";
	

	public String toString(Object obj,String prefix) {
		StringBuffer sb = new StringBuffer();
		if(obj==null) {
			return prefix + SEPARATOR + "null" + NEWLINE;
		}
		if(ClassUtil.isInstanceOfString(obj)) {
			return prefix + SEPARATOR + obj.toString() + NEWLINE;
		}
		else if(ClassUtil.isInstanceOfPrimitive(obj)) {
			return prefix + SEPARATOR + obj.toString() + NEWLINE;
		}
		else if(ClassUtil.isInstanceOfPrimitiveWrapper(obj)) {
			return prefix + SEPARATOR + obj.toString() + NEWLINE;
		}
		else if(ClassUtil.isInstanceOfList(obj) || ClassUtil.isInstanceOfSet(obj)) {
			List list = (List) obj;
			for(int i=0; i < list.size(); i++) {
				Object businessObject2 = list.get(i);
				String nestedString = toString(businessObject2,prefix + "[" + i + "]");
				sb.append(nestedString);
			}
		}
		else if(ClassUtil.isInstanceOfMap(obj)) {
			Map map = (Map) obj;
			String keys[] = (String[]) map.keySet().toArray(new String[map.size()]);
			for(int i=0; i < keys.length; i++) {
				String key = keys[i];
				Object value = map.get(key);
				String nestedString = toString(value,prefix + NESTED_ELEMENT_DELIMITER + key);
				sb.append(nestedString);

			}
		}
		return sb.toString();
	}
	
}
