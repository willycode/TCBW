package com.kangdainfo.util.lang.reflect.converter;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.kangdainfo.util.DateUtil;
import com.kangdainfo.util.TaiwanDateUtil;
import com.kangdainfo.util.TimeUtil;
import com.kangdainfo.util.lang.reflect.ClassUtil;
import com.kangdainfo.util.lang.reflect.DataType;

public class DataTypeConverter {

	public Object parse(Object obj) {		
		if(obj!=null && obj instanceof DataType) {
			
			DataType dataType = (DataType) obj;
			Object data = dataType.getData();
			int type = dataType.getType();
			
			if(data==null) {
				if(type==DataType.Type.STRING || type==DataType.Type.PRIMITIVE || type==DataType.Type.PRIMITIVE_WRAPPER) {
					return "";
				}
				else if(type==DataType.Type.JAVA_DATE_UTIL) {
					Map map = new TreeMap();
					map.put("whole", "");
					map.put("date", "");
					map.put("time", "");			
					return map;
				}
			}
			else {
				if(type==DataType.Type.STRING || type==DataType.Type.PRIMITIVE || type==DataType.Type.PRIMITIVE_WRAPPER) {
					return data;
				}
				else if(type==DataType.Type.JAVA_DATE_UTIL) {
					Map map = new TreeMap();
					map.put("whole", "");
					map.put("date", TaiwanDateUtil.parseToString((Date) data));
					map.put("time", TimeUtil.parseToString((Date) data, TimeUtil.FRIENDLY_TO_THE_SECOND_FORMAT));			
					return map;
				}
			}
		}		
		else if(ClassUtil.isInstanceOfList(obj) || ClassUtil.isInstanceOfSet(obj)) {
			List list = (List) obj;
			for(int i=0; i < list.size(); i++) {
				list.set(i, parse(list.get(i)));
			}
			return list;
		}
		else if(ClassUtil.isInstanceOfMap(obj)) {
			Map map = (Map) obj;
			String keys[] = (String[]) map.keySet().toArray(new String[map.size()]);
			for(int i=0; i < keys.length; i++) {
				String key = keys[i];
				Object value = map.get(key);
				map.put(key, parse(value));
			}
			return map;
		}
		return obj;
	}
	
}
