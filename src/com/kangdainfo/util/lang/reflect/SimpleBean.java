package com.kangdainfo.util.lang.reflect;

import java.lang.reflect.InvocationTargetException;
import java.util.TreeMap;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import java.beans.PropertyDescriptor;

public class SimpleBean {

	private boolean treatEmptyStringAsNull=false;
	private boolean trimTrailingWhiteSpaces=false;
	private TreeMap types;
	private TreeMap values;
	private TreeMap simpleValues;
	private Object originalObject;


	public SimpleBean() {
		this.types = new TreeMap();
		this.values = new TreeMap();
		this.simpleValues = new TreeMap();			
	}
	public SimpleBean(Object originalObject) {
		this();
		this.originalObject = originalObject;	
	}
	
	public void parse()  {
		PropertyDescriptor pd[] = PropertyUtils.getPropertyDescriptors(originalObject);
		for (int i = 0; i < pd.length; i++) {
			if(pd[i].getReadMethod()==null) {
				continue; // property is not accessible
			}
			String propertyName = pd[i].getName();
			if(propertyName.equals("class")) {
				continue;
			}
			Class propertyType = pd[i].getPropertyType();
			types.put(propertyName, propertyType);
			
			Object value = null;
			Object simpleValue = null;
			try {
				value = PropertyUtils.getProperty(originalObject, propertyName);
				simpleValue = PropertyUtils.getSimpleProperty(originalObject, propertyName);
			} 
			catch (Exception e) {
				throw new RuntimeException("Error retrieving propertyName[" + propertyName + "]",e);
			}
			
			if(propertyType==String.class && value!=null) {
				String value_ = (String) value;
				String simpleValue_ = (String) simpleValue;
				if(trimTrailingWhiteSpaces) {
					value = value_.trim();
					simpleValue = simpleValue_.trim();
				}
				if(treatEmptyStringAsNull) {
					if(value_.length()==0) {
						value = null;
					}
					if(simpleValue_.length()==0) {
						simpleValue = null;
					}
				}
			}
			
			values.put(propertyName, value);
			simpleValues.put(propertyName, simpleValue);
		}		
	}
	
	public Class getPropertyClassType(String propertyName) {
		return (Class) types.get(propertyName);
	}
	public Object getPropertyValue(String propertyName) {
		return values.get(propertyName);
	}
	public Object getPropertySimpleValue(String propertyName) {
		return simpleValues.get(propertyName);
	}	
	
	public boolean containsPropertyName(String propertyName) {
		return types.keySet().contains(propertyName);
	}
	public String[] getPropertyNames() {
		return (String[]) types.keySet().toArray(new String[types.size()]);
	}

	public Object getOriginalObject() { return originalObject; }
	public void setOriginalObject(Object originalObject) { this.originalObject = originalObject; }
	public boolean isTreatEmptyStringAsNull() { return treatEmptyStringAsNull; }
	public void setTreatEmptyStringAsNull(boolean treatEmptyStringAsNull) { this.treatEmptyStringAsNull = treatEmptyStringAsNull; }
	public boolean isTrimTrailingWhiteSpaces() { return trimTrailingWhiteSpaces; }
	public void setTrimTrailingWhiteSpaces(boolean trimTrailingWhiteSpaces) { this.trimTrailingWhiteSpaces = trimTrailingWhiteSpaces; }
	

	
	
}
