/**
 * Copyright (c) 2000-2005 Kangda, Inc All Rights Reserved.
 */
package com.kangdainfo.util.lang.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 * ReflectionUtil
 * 
 * @author catty.liao
 * @version 1.0, Created on 2006/9/15
 */
public class ReflectUtil {

	private static Logger logger = Logger.getLogger(ReflectUtil.class);

	/**
	 * get FieldType
	 * 
	 * @param className
	 * @param fieldName
	 * @return Class
	 */
	public static Class getFieldType(String className, String fieldName) {
		Class fieldClass = null;
		try {
			Class objClass = Class.forName(className);
			Field field = getField(objClass, fieldName);
			if (field != null) {
				fieldClass = field.getType();
			}
		} catch (Exception e) {
			// logger.debug(e);
		}
		return fieldClass;
	}

	/**
	 * get Fields
	 * 
	 * @param objClass
	 * @return List
	 */
	public static List getFields(Class objClass) {
		List list = new ArrayList();
		getFields(objClass, list);
		return list;
	}

	private static void getFields(Class objClass, List list) {
		Field fields[] = objClass.getFields();
		for (int i = 0, max = fields.length; i < max; i++) {
			if (!list.contains(fields[i]))
				list.add(fields[i]);
		}
		fields = objClass.getDeclaredFields();
		for (int i = 0, max = fields.length; i < max; i++) {
			if (!list.contains(fields[i]))
				list.add(fields[i]);
		}
		if (objClass.getSuperclass() != null) {
			getFields(objClass.getSuperclass(), list);
		}
	}

	/**
	 * get Methods
	 * 
	 * @param objClass
	 * @return List
	 */
	public static List getMethods(Class objClass) {
		List list = new ArrayList();
		getMethods(objClass, list);
		return list;
	}

	private static void getMethods(Class objClass, List list) {
		Method methods[] = objClass.getMethods();
		for (int i = 0, max = methods.length; i < max; i++) {
			if (!list.contains(methods[i]))
				list.add(methods[i]);
		}
		methods = objClass.getDeclaredMethods();
		for (int i = 0, max = methods.length; i < max; i++) {
			if (!list.contains(methods[i]))
				list.add(methods[i]);
		}
		if (objClass.getSuperclass() != null) {
			getMethods(objClass.getSuperclass(), list);
		}
	}

	/**
	 * get Field
	 * 
	 * @param objClass
	 * @param fieldName
	 * @return Field
	 * @throws Exception
	 */
	public static Field getField(Class objClass, String fieldName)
			throws Exception {
		int index = fieldName.indexOf('.');
		if (index != -1) {
			String fieldName1 = fieldName;
			String fieldNameRest = null;
			fieldName1 = fieldName.substring(0, index);
			if (index + 1 < fieldName.length()) {
				fieldNameRest = fieldName.substring(index + 1);
			}
//			logger.info("fieldName1=" + fieldName1);
//			logger.info("fieldNameRest=" + fieldNameRest);
			Field field = getField2(objClass, fieldName1);
			Class fieldClassType = field.getType();
			return getField(fieldClassType, fieldNameRest);
		} else {
			return getField2(objClass, fieldName);
		}
	}

	private static Field getField2(Class objClass, String fieldName) {
		Field field = null;
		try {
			field = objClass.getDeclaredField(fieldName);
		} catch (Exception e) {
		}
		if (field == null) {
			try {
				field = objClass.getField(fieldName);
			} catch (Exception e) {
			}
		}
		if (field == null) {
			List fields = getFields(objClass);
			for (int i = 0, max = fields.size(); i < max; i++) {
				Field ff = (Field) fields.get(i);
				if (ff.getName().equals(fieldName)) {
					field = ff;
					break;
				}
			}
		}
		return field;
	}

	/**
	 * get Method
	 * 
	 * @param objClass
	 * @param methodName
	 * @param argClass
	 * @return Method
	 * @throws Exception
	 */
	public static Method getMethod(Class objClass, String methodName,
			Class argClass) throws Exception {
		Class argClasses[] = (argClass == null) ? null
				: new Class[] { argClass };
		return getMethod(objClass, methodName, argClasses);
	}

	/**
	 * get Method
	 * 
	 * @param objClass
	 * @param methodName
	 * @param argClasses
	 * @return
	 * @throws Exception
	 */
	public static Method getMethod(Class objClass, String methodName,
			Class argClasses[]) throws Exception {
		Method method = null;
		try {
			method = objClass.getDeclaredMethod(methodName, argClasses);
		} catch (Exception e) {
		}
		if (method == null) {
			try {
				method = objClass.getMethod(methodName, argClasses);
			} catch (Exception e) {
				// logger.debug(e);
			}
		}
		return method;
	}

	public static Method getGetterMethod(Class objClass, String fieldName) {
		Method method = null;
		try {
			String methodName = "get" + StringUtils.capitalize(fieldName);
			method = getMethod(objClass, methodName, new Class[0]);
			if (method == null) {
				methodName = "is" + StringUtils.capitalize(fieldName);
				method = getMethod(objClass, methodName, new Class[0]);
			}
		} catch (Exception e) {
			// logger.debug(e);
		}
		return method;
	}

	public static Method getSetterMethod(Class objClass, String fieldName) {
		Method method = null;
		try {
			String methodName = "set" + StringUtils.capitalize(fieldName);
			method = getMethod(objClass, methodName, new Class[0]);
		} catch (Exception e) {
			// logger.debug(e);
		}
		return method;
	}

	/**
	 * invoke Getter Method
	 * 
	 * @param obj
	 * @param fieldName
	 * @return Object
	 */
	public static Object invokeGetMethod(Object obj, String fieldName) {
		Class objClass = obj.getClass();
		Object result = null;
		try {
			Method method = getGetterMethod(objClass, fieldName);
			result = method.invoke(obj, null);
		} catch (Exception e) {
			// logger.debug(e);
		}
		return result;
	}

	/**
	 * invoke Setter Method
	 * 
	 * @param obj
	 * @param fieldName
	 * @param arg
	 * @return Object
	 */
	public static Object invokeSetMethod(Object obj, String fieldName,
			Object arg) {
		Class objClass = obj.getClass();
		Object result = null;
		try {
			String methodName = "set" + StringUtils.capitalize(fieldName);
			Method method = getMethod(objClass, methodName, arg.getClass());
			result = method.invoke(obj, new Object[] { arg });
		} catch (Exception e) {
			// logger.debug(e);
		}
		return result;
	}

	/**
	 * invoke Setter Method
	 * 
	 * @param obj
	 * @param fieldName
	 * @param argClass
	 * @param arg
	 * @return Object
	 */
	public static Object invokeSetMethod(Object obj, String fieldName,
			Class argClass, Object arg) {
		Class objClass = obj.getClass();
		Object result = null;
		try {
			String methodName = "set" + StringUtils.capitalize(fieldName);
			Method method = getMethod(objClass, methodName, argClass);
			result = method.invoke(obj, new Object[] { arg });
		} catch (Exception e) {
			// logger.debug(e);
		}
		return result;
	}

	/**
	 * invoke Method
	 * 
	 * @param target
	 *            Class
	 * @param target
	 *            Class Instance
	 * @param methodName
	 * @param arg
	 * @return
	 */
	public static Object invokeMethod(Class targetClass, Object obj,
			String methodName, Object arg) {
		Object result = null;
		try {
			Class argClasses[] = null;
			if (arg != null)
				argClasses = new Class[] { arg.getClass() };
			Method method = getMethod(targetClass, methodName, argClasses);
			result = method.invoke(obj, new Object[] { arg });
		} catch (Exception e) {
			// logger.debug(e);
		}
		return result;
	}

	/**
	 * invoke Method
	 * 
	 * @param obj
	 * @param methodName
	 * @param arg
	 * @return Object
	 */
	public static Object invokeMethod(Object obj, String methodName, Object arg) {
		Class objClass = obj.getClass();
		Object result = null;
		try {
			Class argClasses[] = null;
			if (arg != null)
				argClasses = new Class[] { arg.getClass() };
			Method method = getMethod(objClass, methodName, argClasses);
			result = method.invoke(obj, new Object[] { arg });
		} catch (Exception e) {
			// logger.debug(e);
		}
		return result;
	}

	/**
	 * invoke Method
	 * 
	 * @param obj
	 * @param methodName
	 * @param args
	 * @return
	 */
	public static Object invokeMethod(Object obj, String methodName,
			Object args[]) {
		Class objClass = obj.getClass();
		Object result = null;
		try {
			Class argClasses[] = null;
			if (args != null && args.length > 0) {
				argClasses = new Class[args.length];
				for (int i = 0, max = args.length; i < max; i++) {
					argClasses[i] = (args[i] == null) ? null : args[i]
							.getClass();
				}
			}
			Method method = getMethod(objClass, methodName, argClasses);
			result = method.invoke(obj, args);
		} catch (Exception e) {
			// logger.debug(e);
		}
		return result;
	}

	/**
	 * invoke Static Method
	 * 
	 * @param objClass
	 * @param methodName
	 * @param args
	 * @return
	 */
	public static Object invokeStaticMethod(Class objClass, String methodName,
			Object args[]) {
		Object result = null;
		try {
			Class argClasses[] = null;
			if (args != null && args.length > 0) {
				argClasses = new Class[args.length];
				for (int i = 0, max = args.length; i < max; i++) {
					argClasses[i] = (args[i] == null) ? null : args[i]
							.getClass();
				}
			}
			Method method = getMethod(objClass, methodName, argClasses);
			result = method.invoke(null, args);
		} catch (Exception e) {
			// logger.debug(e);
		}
		return result;
	}
}
