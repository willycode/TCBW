package com.kangdainfo.util.lang.reflect.converter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import com.kangdainfo.util.lang.reflect.ClassUtil;
import com.kangdainfo.util.lang.reflect.DataType;
import com.kangdainfo.util.lang.reflect.SimpleBean;

public class BusinessObjectConverter {
	
	public Object parse(Object obj, Class classType, Set traversedBos) {
		
		if(ClassUtil.isNull(obj, classType)) {
			return parseNull(obj);
		}
		else if(ClassUtil.isInstanceOfString(obj,classType)) {
			return parseString(obj);
		}
		else if(ClassUtil.isInstanceOfPrimitive(obj,classType)) {
			return parsePrimitive(obj,classType);	
		}
		else if(ClassUtil.isInstanceOfPrimitiveWrapper(obj,classType)) {
			return parsePrimitiveWrapper(obj,classType);		
		}
		else if(ClassUtil.isInstanceOfJavaUtilDate(obj,classType)) {
			return parseJavaUtilDate(obj);
		}
		else if(ClassUtil.isInstanceOfList(obj,classType) || ClassUtil.isInstanceOfSet(obj,classType)) {
			return parseListOrSet(obj,traversedBos);
		}
		else if(ClassUtil.isInstanceOfMap(obj,classType)) {
			return parseMap(obj,traversedBos);
		}
		else {
			if(obj!=null) {
				return parseBusinessObject(obj,traversedBos);				
			}
		}
		return null;
	}

	private DataType parseNull(Object obj) {
		DataType dataType = new DataType();
		dataType.setType(DataType.Type.NULL);
		dataType.setData(obj);
		return dataType;
	}
	private DataType parseString(Object obj) {
		DataType dataType = new DataType();
		dataType.setType(DataType.Type.STRING);
		dataType.setData(obj);
		return dataType;
	}
	private DataType parsePrimitive(Object obj, Class classType) {
		DataType dataType = new DataType();
		if(classType!=null && obj==null && classType==Boolean.TYPE) {
			dataType.setData(new Boolean(false));
		}
		else {
			dataType.setData(obj);
		}
		dataType.setType(DataType.Type.PRIMITIVE);
		return dataType;
	}
	private DataType parsePrimitiveWrapper(Object obj, Class classType) {
		DataType dataType = parsePrimitive(obj,classType);
		dataType.setType(DataType.Type.PRIMITIVE_WRAPPER);
		return dataType;	
	}
	private DataType parseJavaUtilDate(Object obj) {
		DataType dataType = new DataType();
		dataType.setType(DataType.Type.JAVA_DATE_UTIL);
		dataType.setData(obj);
		return dataType;		
	}
	
	private Object parseListOrSet(Object obj,Set traversedBos) {
		if(obj!=null) {
			Object array[] = ((Collection) obj).toArray();
			List list = new ArrayList(); 
			for(int j=0; j < array.length; j++) {
				Object nestedObject = array[j];
				Object subObject = parse(nestedObject,null,traversedBos);
				if(subObject!=null) {
					list.add(subObject);				
				}
			}
			return list;
		}
		return obj;
	}
	private Object parseMap(Object obj,Set traversedBos) {
		if(obj!=null) {					
			Map propertyMap = (Map) obj;
			Map map = new TreeMap();
			Object mapKeys[] = propertyMap.keySet().toArray();
			for(int j=0; j < mapKeys.length; j++) {
				Object mapKey = mapKeys[j];
				Object mapValue = propertyMap.get(mapKey);
				Object subObject = parse(mapValue,null,traversedBos);
				map.put(mapKey, subObject);					
			}
			return map;
		}
		return obj;
	}
	

	private Object parseBusinessObject(Object obj, Set traversedBos) {
		traversedBos.add(obj);
		
		SimpleBean simpleBean = new SimpleBean(obj);
		simpleBean.parse();
		String propertyNames[] = simpleBean.getPropertyNames();
		
		Map map = new TreeMap();
		for(int i=0; i < propertyNames.length ; i++) {
			String propertyName = propertyNames[i];
			Class classType = simpleBean.getPropertyClassType(propertyName);
			Object propertyValue = simpleBean.getPropertyValue(propertyName);
//System.out.println("classType=" + classType);
//System.out.println("propertyName=" + propertyName);			
//System.out.println("propertyValue=" + propertyValue);	
			if(!ClassUtil.isNull(propertyValue, classType) && ClassUtil.isOther(propertyValue, classType) && traversedBos.contains(propertyValue)) {
//System.out.println("skiping=" + "true");	
				continue;
			}
			Object subObj = parse(propertyValue,classType,traversedBos);
//System.out.println("subObj=" + subObj);
			if(subObj!=null) {
				map.put(propertyName, subObj);
			}
		}
		return map;
	}
	
	
}
