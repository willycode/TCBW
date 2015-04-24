package com.kangdainfo.util.lang.reflect;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.DynaProperty;
import org.apache.log4j.Logger;

import com.kangdainfo.util.DateUtil;
import com.kangdainfo.util.TaiwanDateUtil;

public class PropertiesCopier {

	private static Logger logger = Logger.getLogger(PropertiesCopier.class);

	public static void copy(Object dest, Object orig)
			throws IllegalArgumentException, InvocationTargetException,
			IllegalAccessException {
		if (dest == null) {
			throw new IllegalArgumentException("No destination bean specified");
		}
		if (orig == null) {
			throw new IllegalArgumentException("No origin bean specified");
		}
		if (orig instanceof DynaBean) {
			DynaProperty origDescriptors[] = ((DynaBean) orig).getDynaClass()
					.getDynaProperties();
			for (int i = 0; i < origDescriptors.length; i++) {
				String name = origDescriptors[i].getName();
				Object value = ((DynaBean) orig).get(name);
				BeanUtils.copyProperty(dest, name, value);
			}
		} else if (orig instanceof Map) {
			Iterator names = ((Map) orig).keySet().iterator();
			while (names.hasNext()) {
				String name = (String) names.next();
				Object value = ((Map) orig).get(name);
				BeanUtils.copyProperty(dest, name, value);
			}
		} else /* if (not a DynaBean or a Map) */
		{
			copyObject(dest, orig);
		}
	}

	public static void copyObject(Object dest, Object orig) {
		if (dest == null) {
			throw new RuntimeException("dest object is null!");
		}
		if (orig == null) {
			throw new RuntimeException("orig object is null!");
		}
		SimpleBean destSimpleBean = new SimpleBean(dest);
		SimpleBean origSimpleBean = new SimpleBean(orig);
		origSimpleBean.setTrimTrailingWhiteSpaces(true);
		origSimpleBean.setTreatEmptyStringAsNull(true);
		destSimpleBean.parse();
		origSimpleBean.parse();
		String propertyNames[] = destSimpleBean.getPropertyNames();
		for (int i = 0; i < propertyNames.length; i++) {
			String propertyName = propertyNames[i];
			if (origSimpleBean.containsPropertyName(propertyName)) {
				Class destPropertyType = destSimpleBean
						.getPropertyClassType(propertyName);
				Class origPropertyType = origSimpleBean
						.getPropertyClassType(propertyName);
				Object origPropertyValue = origSimpleBean
						.getPropertyValue(propertyName);
				if (origPropertyValue == null) {
					continue; // no need to copy null value
				}
				try {
					copyProperty(dest, propertyName, origPropertyValue,
							destPropertyType, origPropertyType);
				} catch (Exception e) {
					logger.debug("set dest." + propertyName + " = "
							+ origPropertyValue + " faliure");
				}
			}
		}

	}

	private static void copyProperty(Object dest, String propertyName,
			Object origPropertyValue, Class destPropertyType,
			Class origPropertyType) throws IllegalAccessException,
			InvocationTargetException {
		if (destPropertyType == origPropertyType) { // same type, no class
			// conversion required
			BeanUtils.copyProperty(dest, propertyName, origPropertyValue);
		} else {
			if (origPropertyType == String.class
					&& destPropertyType == Date.class) {
				// is TwDate Text ??
				if (TaiwanDateUtil.validate((String) origPropertyValue)) {
					Date originalDate = TaiwanDateUtil
							.parseToDate((String) origPropertyValue);
					BeanUtils.copyProperty(dest, propertyName, originalDate);
				}
				// is AcDate Text??
				else {
					Date originalDate = DateUtil
							.parseToDate((String) origPropertyValue);
					BeanUtils.copyProperty(dest, propertyName, originalDate);
				}
			} else if (destPropertyType == String.class
					&& origPropertyType == Date.class) {
				// to Tw Date Text
				// String originalDateString = DateUtil.parseToString((Date)
				// origPropertyValue);
				String originalDateString = TaiwanDateUtil
						.parseToString((Date) origPropertyValue);
				BeanUtils.copyProperty(dest, propertyName, originalDateString);
			} else if (destPropertyType.getSuperclass() == Number.class
					&& origPropertyType == String.class) {
				if (origPropertyValue != null) {
					String value = origPropertyValue.toString();
					if (value.indexOf(",") > -1) {
						// it may be Money type
						value = value.replaceAll(",", "");
					}
					BeanUtils.copyProperty(dest, propertyName, value);
				}
			} else {
				BeanUtils.copyProperty(dest, propertyName, origPropertyValue);
			}
		}
	}

}
