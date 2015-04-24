package com.kangdainfo.util.lang.reflect;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.Map;

public class ClassUtil {

	public static boolean isString(Class thisClass) {
		return thisClass == String.class;
	}
	public static boolean isPrimitive(Class thisClass) {
		return thisClass.isPrimitive();
	}
	public static boolean isPrimitiveWrapper(Class thisClass) {
		return 
		thisClass == Integer.class || thisClass == Short.class || thisClass == Long.class ||
		thisClass == Double.class || thisClass == Float.class || thisClass == Boolean.class ||
		thisClass == Character.class; 
	}
	public static boolean isJavaUtilDate(Class thisClass) {
		return thisClass == Date.class;
	}
	public static boolean isList(Class thisClass) {
		return isInterfaceOf(thisClass, List.class);
	}
	public static boolean isSet(Class thisClass) {
		return isInterfaceOf(thisClass, Set.class);
	}
	public static boolean isMap(Class thisClass) {
		return isInterfaceOf(thisClass, Map.class);
	}
	
	public static boolean isInterfaceOf(Class thisClass, Class comparedInterfaceClass) {
		if(thisClass==comparedInterfaceClass) {
			return true;
		}
		Class classes[] = thisClass.getInterfaces();
		for(int i=0; i < classes.length; i++) {
			if(classes[i]==comparedInterfaceClass) {
				return true;
			}
		}
		return false;		
	}
	
	public static boolean isInstanceOfString(Object obj) {
		return obj instanceof String;
	}
	public static boolean isInstanceOfPrimitive(Object obj) {
		return isPrimitive(obj.getClass());
	}
	public static boolean isInstanceOfPrimitiveWrapper(Object obj) {
		return isPrimitiveWrapper(obj.getClass());
	}
	public static boolean isInstanceOfJavaUtilDate(Object obj) {
		return obj instanceof Date;
	}
	public static boolean isInstanceOfList(Object obj) {
		return obj instanceof List;
	}
	public static boolean isInstanceOfSet(Object obj) {
		return obj instanceof Set;
	}
	public static boolean isInstanceOfMap(Object obj) {
		return obj instanceof Map;
	}	


	public static boolean isInstanceOfString(Object obj, Class theClass) {
		return theClass==null? isString(obj.getClass()) : isString(theClass);
	}
	public static boolean isInstanceOfPrimitive(Object obj, Class theClass) {
		return theClass==null? isPrimitive(obj.getClass()) : isPrimitive(theClass);
	}
	public static boolean isInstanceOfPrimitiveWrapper(Object obj, Class theClass) {
		return theClass==null? isPrimitiveWrapper(obj.getClass()) : isPrimitiveWrapper(theClass);
	}
	public static boolean isInstanceOfJavaUtilDate(Object obj, Class theClass) {
		return theClass==null? isJavaUtilDate(obj.getClass()) : isJavaUtilDate(theClass);
	}
	public static boolean isInstanceOfList(Object obj, Class theClass) {
		return theClass==null? isList(obj.getClass()) : isList(theClass);
	}
	public static boolean isInstanceOfSet(Object obj, Class theClass) {
		return theClass==null? isSet(obj.getClass()) : isSet(theClass);
	}
	public static boolean isInstanceOfMap(Object obj, Class theClass) {
		return theClass==null? isMap(obj.getClass()) : isMap(theClass);
	}
	

	public static boolean isNull(Object obj, Class theClass) {
		if(theClass==null && obj==null) {
			return true;
		}
		return false;
	}
	public static boolean isOther(Object obj, Class theClass) {
		if(isNull(obj,theClass)) {
			return true;
		}
		if(theClass==null) {
			theClass = obj.getClass();
		}
		if(isString(theClass)) return false;
		else if(isPrimitive(theClass)) return false;
		else if(isPrimitiveWrapper(theClass)) return false;
		else if(isJavaUtilDate(theClass)) return false;
		else if(isList(theClass)) return false;
		else if(isSet(theClass)) return false;
		else if(isMap(theClass)) return false;
		
		return true;	
	}
	
	
	
}
