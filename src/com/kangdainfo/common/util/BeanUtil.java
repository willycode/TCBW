/*
 * Copyright 15/06/2004 - <a href="http://www.liber.ufpe.br">Liber - UFPE</a>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package com.kangdainfo.common.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.BeanWrapperImpl;


/**
 * Classe com metodos uteis para o tratamento de beans ou Strings.
 *
 * @author <a href="mailto:jackganzha@dev.java.net">Marcos Silva Pereira</a>
 *
 * @since 15/06/2004
 * @version $Id: BeanUtil.java,v 1.3 2005/01/24 22:54:15 jackganzha Exp $
 */
public final class BeanUtil {

    /**
     *
     */
    public static final Class[] IS_NOT_FROM = new Class[] {
            Class.class, Collection.class, Map.class, String.class,
            Calendar.class, Date.class, StringBuffer.class, Number.class,
            Boolean.class, Character.class, Collection.class, Map.class
        };

    private BeanUtil() {

        // avoid instantiation...
    }

    /**
     * @param attributes
     * @param obj
     *
     * @return Object with the properties in attributes
     *
     * @see org.springframework.beans.BeanWrapper
     * @see BeanWrapperImpl
     * @see org.springframework.beans.BeanWrapper#setPropertyValues(
     *     java.util.Map)
     */
    public static Object populate(Map attributes, Object obj) {

        new BeanWrapperImpl(obj).setPropertyValues(attributes);

        return obj;

    }

    /**
     * @param attributes
     * @param clazz
     *
     * @return
     *
     * @see BeanUtil#populate(Map, Object)
     */
    public static Object populate(Map attributes, Class clazz) {

        try {

            Object instance = clazz.newInstance();

            return BeanUtil.populate(attributes, instance);

        } catch (InstantiationException e) {

            StringBuffer buffer = new StringBuffer();
            buffer.append("Could not populate a bean from class ");
            buffer.append(clazz.getName()).append(". ");
            buffer.append("This have a public empty contructor?");

            throw new RuntimeException(buffer.toString(), e);

        } catch (IllegalAccessException e) {

            StringBuffer buffer = new StringBuffer();
            buffer.append("Could not populate a bean from class ");
            buffer.append(clazz.getName()).append(". ");
            buffer.append("This class have a public contructor?");

            throw new RuntimeException(buffer.toString(), e);

        }

    }

    /**
     *
     * @param attributes
     * @param className
     *
     * @return
     *
     * @see BeanUtil#populate(Map, Class)
     */
    public static Object populate(Map attributes, String className) {

        Class clazz;

        try {

            clazz = Class.forName(className);

            return BeanUtil.populate(attributes, clazz);

        } catch (ClassNotFoundException ex) {

            throw new RuntimeException(ex);

        }

    }
 
    /**
     *
     * @param propertyName
     * @param propertyValue
     * @param beanTarget
     *
     * @return
     *
     * @see org.springframework.beans.BeanWrapper
     * @see BeanWrapperImpl
     * @see org.springframework.beans.BeanWrapper#setPropertyValue(
     *     java.lang.String, java.lang.Object)
     */
    public static Object setProperty(String propertyName, Object propertyValue,
        Object beanTarget) {

        new BeanWrapperImpl(beanTarget).setPropertyValue(propertyName,
            propertyValue);

        return beanTarget;

    }

    /**
     * @param propertyName The property name
     * @param beanSource Bean source for property
     *
     * @return Returns the property to beanSource
     *
     * @see org.springframework.beans.BeanWrapper
     * @see BeanWrapperImpl
     * @see org.springframework.beans.BeanWrapper#setPropertyValue(
     *     java.lang.String, java.lang.Object)
     */
    public static Object getProperty(String propertyName, Object beanSource) {

        return new BeanWrapperImpl(beanSource).getPropertyValue(propertyName);

    }

    /**
     *
     * @param bean
     * @return
     * @throws IntrospectionException
     */
    public static PropertyDescriptor[] getPropertyDescriptors(Object bean)
        throws IntrospectionException {

        BeanInfo info = Introspector.getBeanInfo(bean.getClass());
        PropertyDescriptor[] descriptors = info.getPropertyDescriptors();

        return descriptors;

    }

    /**
     * This method use reflection to navigate in object and get value from nested
     * properties based on the depth parameter. Use JavaBeans conventions to get
     * properties names and its values. So, if you have classes like the following:
     * <pre>
     * class PlainClass {
     *  private int num;
     *  //sets and gets
     * }
     * 
     * class Composite {
     *  private String name;
     *  private PlainClass plain;
     *  // sets and gets
     * }</pre>
     * 
     * The name of "num" attribute from PlainClass navigating from Composite is
     * "plain.num" and this will be the key in map. The depth parameter points out
     * as far as the method must retrieve properties. In the Composite class, name
     * have depth 1 and num have depth 2 and so on. depth parameter also avoid a 
     * StackOverFlowError if the bean have itself relationship.
     * 
     * @param source object from the properties are retrieved
     * @param depth as far as to retrieve properties.
     * @return
     * @throws IntrospectionException
     */
    public static Map<String, Object> getDeepAttributes(Object source, int depth)
        throws IntrospectionException {

        Map<String, Object> attributes = new HashMap<String, Object>();
        goingUnder(attributes, source, "", depth);

        return attributes;

    }

    /**
     *
     * @param source
     * @param classes
     *
     * @return Returns <code>true</code> if the source object is a instance from
     *     some class in classes.
     */
    private static boolean isAssignableFromAny(Object source, Class<Object>[] classes) {
        boolean result = false;
        Class sourceClass = source.getClass();
        for (int i = 0; i < classes.length; i++) {
            if (classes[i].isAssignableFrom(sourceClass)) {
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     *
     * @param attributes
     * @param source
     * @param name
     * @param depth
     * @throws IntrospectionException
     */
    private static void goingUnder(Map<String, Object> attributes, Object source, String name,
        int depth) throws IntrospectionException {

        if (depth <= 0) {

            return;
        }

        PropertyDescriptor[] properties = getPropertyDescriptors(source);

        for (int i = 0; i < properties.length; i++) {

            String localName = properties[i].getName();

            Object value = getProperty(localName, source);

            if (value != null) {

                String tempName = dealWithAttributeName(name, localName);

                Class<? extends Object> vClass = value.getClass();

                if (!isAssignableFromAny(value, IS_NOT_FROM)) {

                    goingUnder(attributes, value, tempName, depth - 1);

                }

                if (!vClass.isAssignableFrom(Class.class)) {

                    attributes.put(tempName, value);

                }

            }

        }

    }

    /**
     * @param name
     * @param localName
     *
     * @return name for attribute
     */
    private static String dealWithAttributeName(String name, String localName) {

        String tempName = name;
        tempName = ((tempName != null) && !tempName.equals(""))
            ? tempName.concat(".").concat(localName) : localName;

        return tempName;

    }
    
    public static void setProperty(Object o,String propertyName,Object value) throws Exception {
        getWriteMethod(o.getClass(),propertyName).invoke(o.getClass(),value);
    }
    
    public static Object getProperty(Object o,String propertyName) throws Exception {
        return getReadMethod(o.getClass(),propertyName).invoke(o);
    }
    public static String[] getPropertyNames(Object o) throws IntrospectionException {
        PropertyDescriptor[] pds = Introspector.getBeanInfo(o.getClass()).getPropertyDescriptors();
        String[] propertyNames = new String[pds.length];

        for(int i = 0; i < pds.length; i++) {
            propertyNames[i] = pds[i].getName();
        }

        return propertyNames;
    }
    
    public static Class getPropertyType(Object o,String propertyName) throws Exception {
        PropertyDescriptor[] pds = Introspector.getBeanInfo(o.getClass()).getPropertyDescriptors();

        for(int i = 0; i < pds.length; i++) {
            if(pds[i].getName().equals(propertyName)) {
                return pds[i].getPropertyType();
            }
        }

        throw new Exception("Property not found.");
    }
    
    public static Method getReadMethod(Class<? extends Object> examineClass,String propertyName) throws Exception {
        PropertyDescriptor[] pds = Introspector.getBeanInfo(examineClass).getPropertyDescriptors();

        for(int i = 0; i < pds.length; i++) {
            if(pds[i].getName().equals(propertyName)) {
                return pds[i].getReadMethod();
            }
        }

        throw new Exception("Property not found.");
    }
    
    public static Method getWriteMethod(Class<? extends Object> examineClass,String propertyName) throws Exception {
        PropertyDescriptor[] pds = Introspector.getBeanInfo(examineClass).getPropertyDescriptors();

        for(int i = 0; i < pds.length; i++) {
            if(pds[i].getName().equals(propertyName)) {
                return pds[i].getWriteMethod();
            }
        }

        throw new Exception("Property not found.");
    }     
    
    /** This method takes a JavaBean and generates a standard toString() type result for it.
     * @param o JavaBean object to stringinate
     * @return STRINGIATION! Stringingating the countryside. Stringinating all the peasants.
     */    
    public static String beanToString(Object o) {
        StringBuffer result = new StringBuffer();

        if(o == null) {
            return "--- null";
        }

        result.append("--- begin");
        result.append(o.getClass().getName());
        result.append(" hash: ");
        result.append(o.hashCode());
        result.append("\r\n");

        try {
            PropertyDescriptor[] pds = Introspector.getBeanInfo(o.getClass()).getPropertyDescriptors();

            for(int pdi = 0; pdi < pds.length; pdi++) {
                try {
                    result.append("Property: " + pds[pdi].getName() + " Value: " + pds[pdi].getReadMethod().invoke(o));
                } catch(IllegalAccessException iae) {
                    result.append("Property: " + pds[pdi].getName() + " (Illegal Access to Value) ");
                } catch(InvocationTargetException iae) {
                    result.append("Property: " + pds[pdi].getName() + " (InvocationTargetException) " + iae.toString());
                } catch(Exception e) {
                    result.append("Property: " + pds[pdi].getName() + " (Other Exception )" + e.toString());
                }

                result.append("\r\n");
            }
        } catch(IntrospectionException ie) {
            result.append("Introspection Exception: " + ie.toString());
            result.append("\r\n");
        }

        result.append("--- end ");
        result.append(o.getClass().getName());
        result.append(" hash: ");
        result.append(o.hashCode());
        result.append("\n");

        return result.toString();
    }    
    
    /** This method takes 2 JavaBeans of the same type and copies the properties of one bean to the other.
     * Any attempts that have an IllegalAccessException will be ignored. This will also NOT recurse into nested bean
     * results. References to existing beanage will be includes. Try using .clone() for that stuff.
     * @param from Source Bean
     * @param to Desitnation Bean
     */
    public static void copyBeanToBean(Object from,Object to) throws InvocationTargetException,IntrospectionException {
        PropertyDescriptor[] pds = Introspector.getBeanInfo(from.getClass()).getPropertyDescriptors();

        for(int i = 0; i < pds.length; i++) {
            try {
                if(pds[i].getName().equals("class")) {
                    continue;
                }
                Object[] value = {pds[i].getReadMethod().invoke(from)};
                if (pds[i].getWriteMethod()!=null) pds[i].getWriteMethod().invoke(to,value);
            } catch(IllegalAccessException iae) {
                //Im just going to ignore any properties I don't have access too.
            }
        }
    } 
    
    /**
     * This method takes 2 JavaBeans of the same type and copies the properties of one bean to the other.
     * <br>
     * @param from
     * @param to
     * @param ignoreFields
     * @throws InvocationTargetException
     * @throws IntrospectionException
     */
    public static void copyBeanToBean(Object from,Object to, String[] ignoreFields) throws InvocationTargetException,IntrospectionException {
    	if (from!=null && to!=null) {
            PropertyDescriptor[] pds = Introspector.getBeanInfo(from.getClass()).getPropertyDescriptors();
            for(int i = 0; i < pds.length; i++) {
                try {
                	boolean flag = false;            	
                	if (ignoreFields!=null && ignoreFields.length>0) {
                		for (int j=0; j<ignoreFields.length; j++) {
                			if (pds[i].getName().equals(ignoreFields[j])) flag = true;
                		}
                	}
                    if(pds[i].getName().equals("class") || flag==true) continue;
                    Object[] value = {pds[i].getReadMethod().invoke(from)};
                    if (pds[i].getWriteMethod()!=null) pds[i].getWriteMethod().invoke(to,value);
                } catch(IllegalAccessException iae) {
                	iae.printStackTrace();
                } catch (Exception e) {
                	e.printStackTrace();
                }
            }    		
    	}
    }    
    
    /** This method takes a JavaBean reset type.
     * @param o JavaBean object
     */    
    public static void beanResetType(Object o) {
        try {
            PropertyDescriptor[] pds = Introspector.getBeanInfo(o.getClass()).getPropertyDescriptors();

            for(int pdi = 0; pdi < pds.length; pdi++) {
            	String type = Common.get(pds[pdi].getPropertyType());
            	if(type.equals("class java.lang.Integer")){
            		pds[pdi].setValue(pds[pdi].getName(), Common.getInt(pds[pdi].getValue(pds[pdi].getName())));
            	} else if(type.equals("class java.lang.Long")){
            		pds[pdi].setValue(pds[pdi].getName(), Common.getLong(pds[pdi].getValue(pds[pdi].getName())));
            	} else if(type.equals("class java.lang.Double")){
            		pds[pdi].setValue(pds[pdi].getName(), Common.getNumeric(pds[pdi].getValue(pds[pdi].getName())));
            	}
            }
        } catch(Exception e) {
           e.printStackTrace();
        }
    }    
    
    public static String getPropertyByBeanUtils(Object bean, String name){
    	try{
    		return BeanUtils.getProperty(bean,name);
    	}catch(Exception e){
    	}
    	return "";
    }
    
}