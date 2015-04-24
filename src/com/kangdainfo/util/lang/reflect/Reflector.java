package com.kangdainfo.util.lang.reflect;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import java.util.regex.Pattern;


/**
 * The reflection utility class.
 * 
 * @author bear huang
 * @version 1.0
 */
public class Reflector {
    public static final int COPY_MODE_NORMAL = 0;

    public static final int COPY_MODE_UNION = 1;

    public static final int COPY_MODE_INTERSECTION = 2;

    public static final int COPY_MODE_DIFFERENCE = 3;

    /**
     * Constructor.
     */
    private Reflector() {
    }

    /**
     * Get property value of target object.
     * 
     * @param target Target object.
     * @param ref Reference (property).
     * @return Object value.
     * @throws ReflectorException
     * @throws IllegalAccessException Illegal access exception in Field.get().
     * @throws InvocationTargetException Invocation exception when calling
     *             accessor or mutator.
     */
    public static Object getPropertyValue(Object target, String ref)
            throws ReflectorException, IllegalAccessException,
            InvocationTargetException {
        Class dest = target.getClass();
        Object child = target;
        Object ret = null;
        boolean isFound = false;

        if(dest == null) {
            throw new ReflectorException(ReflectorException.ERROR_NULL_CLASS);
        }

        String[] splittedRef = ref.split("\\.");
        int refIndex = 0;

        Field[] fields = getAllFields(dest);

        for(int i = 0; i < fields.length; i++) {
            if(fields[i].getName().equals(splittedRef[refIndex])) {
                if(refIndex < splittedRef.length - 1) {
                    refIndex++;
                    if(Modifier.isPublic(fields[i].getModifiers())) {
                        child = fields[i].get(target);
                        dest = child.getClass();
                    } else {
                        if(hasAccessor(dest, fields[i].getName())) {
                            child = invokeAccessor(child, fields[i].getName());
                            if(child == null) {
                                throw new ReflectorException(
                                        ReflectorException.ERROR_CANNOT_GET_FIELD_CHILD
                                                + fields[i].getName());
                            }

                            dest = child.getClass();
                        }
                    }

                    fields = getAllFields(dest);
                    // reset i. the i will be plus by for loop, so set i as -1.
                    i = -1;
                } else { // refIndex == splittedRef.length - 1
                    if(Modifier.isPublic(fields[i].getModifiers())) {
                        ret = fields[i].get(child);
                    } else {
                        if(hasAccessor(dest, fields[i].getName())) {
                            ret = invokeAccessor(child, fields[i].getName());
                        }
                    }

                    isFound = true;
                    break;
                }
            }
        }

        if(!isFound) {
            throw new ReflectorException(ReflectorException.ERROR_NO_THIS_FIELD
                    + splittedRef[refIndex]);
        }

        return ret;
    }

    /**
     * Get target object property values by reference condition.
     * 
     * @param target Target object.
     * @param refCondition Reference condition which is regular expression.
     * @return List List of field values.
     * @throws ReflectorException
     * @throws IllegalAccessException Illegal access exception in Field.get().
     * @throws InvocationTargetException Invocation exception when calling
     *             accessor or mutator.
     */
    public static List getPropertyValues(Object target, String refCondition)
            throws ReflectorException, IllegalAccessException,
            InvocationTargetException {
        Class dest = target.getClass();

        if(dest == null) {
            throw new ReflectorException(ReflectorException.ERROR_NULL_CLASS);
        }

        Field[] fields = getAllFields(dest);
        List ret = new Vector();
        int i;

        for(i = 0; i < fields.length; i++) {
            if(Pattern.compile(refCondition).matcher(fields[i].getName())
                    .find()) {
                if(Modifier.isPublic(fields[i].getModifiers())) {
                    ret.add(fields[i].get(target));
                } else {
                    if(hasAccessor(dest, fields[i].getName())) {
                        Object field = invokeAccessor(target, fields[i]
                                .getName());
                        // if(field != null)
                        ret.add(field);
                    }
                }
            }
        }

        return ret;
    }

    /**
     * Get all property values in target object of primitive type.
     * 
     * @param target Target object.
     * @return List of wrapped primitive type values.
     * @throws ReflectorException
     * @throws IllegalAccessException Illegal access exception in Field.get().
     * @throws InvocationTargetException Invocation exception when calling
     *             accessor or mutator.
     */
    public static List getPrimitivePropertyValues(Object target)
            throws ReflectorException, IllegalAccessException,
            InvocationTargetException {
        List retTmp, ret = new Vector();

        retTmp = getPropertyValues(target, ".");

        for(int i = 0; i < retTmp.size(); i++) {
            if(isPrimitiveType(retTmp.get(i))) {
                ret.add(retTmp.get(i));
            }
        }

        return ret;
    }

    /**
     * Is primitive type wrapped object (include String)?
     * 
     * @param type The object you want to test.
     * @return true or false.
     */
    public static boolean isPrimitiveType(Object type) {
        if(type instanceof Boolean || type instanceof Character
                || type instanceof Byte || type instanceof Short
                || type instanceof Integer || type instanceof Long
                || type instanceof Float || type instanceof Double
                || type instanceof Void || type instanceof String) {
            return true;
        }

        return false;
    }

    /**
     * Has accessor or not in class and field name you give.
     * 
     * @param dest Target class.
     * @param fieldName Field name.
     * @return true or false.
     * @throws ReflectorException
     */
    public static boolean hasAccessor(Class dest, String fieldName)
            throws ReflectorException {
        return (getAccessor(dest, fieldName) == null ? false : true);
    }

    /**
     * Call accessor of given field.
     * 
     * @param dest Target object.
     * @param fieldName Field name.
     * @return The value return by accessor.
     * @throws ReflectorException
     * @throws IllegalAccessException Illegal access exception in Field.get().
     * @throws InvocationTargetException Invocation exception when calling
     *             accessor or mutator.
     */
    public static Object invokeAccessor(Object dest, String fieldName)
            throws ReflectorException, IllegalAccessException,
            InvocationTargetException {
        Method accessor = getAccessor(dest.getClass(), fieldName);

        if(accessor == null) {
            throw new ReflectorException(ReflectorException.ERROR_NO_ACCESSOR
                    + fieldName);
        }

        return accessor.invoke(dest, null);
    }

    /**
     * Get accessor.
     * 
     * @param destClass Target class.
     * @param fieldName Field name.
     * @return Accessor method.
     * @throws ReflectorException
     */
    public static Method getAccessor(Class destClass, String fieldName)
            throws ReflectorException {
        Method[] methods = destClass.getMethods();
        Class type = getFieldType(destClass, fieldName);

        String prefix = getAccessorPrefix(type);

        for(int i = 0; i < methods.length && type != null; i++) {
            if(methods[i].getName().equalsIgnoreCase(prefix + fieldName)) {
                if(methods[i].getParameterTypes().length == 0) {
                    return methods[i];
                } else {
                    continue;
                }
            }
        }

        return null;
    }

    /**
     * Get accessor method prefix.
     * 
     * @param type Field type class.
     * @return Method prefix.
     */
    private static String getAccessorPrefix(Class type) {
        String prefix;
        if(type.equals(Boolean.class)) {
            prefix = "is";
        } else {
            prefix = "get";
        }
        return prefix;
    }

    /**
     * Get field type class.
     * 
     * @param destClass Target class.
     * @param fieldName Field name.
     * @return Type class.
     * @throws ReflectorException
     * @throws SecurityException
     */
    public static Class getFieldType(Class destClass, String fieldName)
            throws ReflectorException, SecurityException {
        Field field = getField(destClass, fieldName);

        if(field == null) {
            throw new ReflectorException(ReflectorException.ERROR_NO_THIS_FIELD);
        }

        return field.getType();
    }

    /**
     * Get target object property names by given reference condition.
     * 
     * @param target Target object.
     * @param refCondition Reference condition which is regular expression.
     * @return List of field names.
     * @throws ReflectorException
     * @throws IllegalAccessException Illegal access exception in Field.get().
     * @throws InvocationTargetException
     * @throws IllegalArgumentException
     * @throws SecurityException
     */
    public static List getPropertyNames(Object target, String refCondition)
            throws ReflectorException, IllegalAccessException,
            InvocationTargetException, IllegalArgumentException,
            SecurityException {
        Class dest = target.getClass();

        if(dest == null) {
            throw new ReflectorException(ReflectorException.ERROR_NULL_CLASS);
        }

        Field[] fields = getAllFields(dest);
        List ret = new Vector();
        int i;

        for(i = 0; i < fields.length; i++) {
            if(Pattern.compile(refCondition).matcher(fields[i].getName())
                    .find()) {
                if(Modifier.isPublic(fields[i].getModifiers())) {
                    ret.add(fields[i].getName());
                } else {
                    if(hasAccessor(dest, fields[i].getName())) {
                        ret.add(fields[i].getName());
                    }
                }
            }
        }

        return ret;
    }

    /**
     * Get target object all property names of primitive type.
     * 
     * @param target Target object.
     * @return List of wrapped primitive type field names.
     * @throws ReflectorException
     * @throws IllegalAccessException Illegal access exception in Field.get().
     * @throws InvocationTargetException Invocation exception when calling
     *             accessor or mutator.
     */
    public static List getPrimitivePropertyNames(Object target)
            throws ReflectorException, IllegalAccessException,
            InvocationTargetException {
        List retTmp, retName, ret = new Vector();

        retTmp = getPropertyValues(target, ".");
        retName = getPropertyNames(target, ".");

        for(int i = 0; i < retTmp.size(); i++) {
            if(isPrimitiveType(retTmp.get(i))) {
                ret.add(retName.get(i));
            }
        }

        return ret;
    }

    /**
     * Get all field, include extended (super) field.
     * 
     * @param dest Target class.
     * @return Field array.
     */
    public static Field[] getAllFields(Class dest) {
        Field[] ret;
        List classes = new Vector();
        List tmpRet = new Vector();

        Class classRef = dest;

        classes.add(classRef);
        while(classRef.getSuperclass() != null) {
            classes.add(classRef.getSuperclass());
            classRef = classRef.getSuperclass();
        }

        int classIndex = 0;
        for(int i = 0; i < classes.size(); i++) {
            classRef = (Class)classes.get(classIndex++);
            Field[] fields = classRef.getDeclaredFields();
            for(int j = 0; j < fields.length; j++) {
                tmpRet.add(fields[j]);
            }
        }

        ret = new Field[tmpRet.size()];
        for(int i = 0; i < ret.length; i++) {
            ret[i] = (Field)tmpRet.get(i);
        }

        return ret;
    }
    public static Field[] getAllFields2(Class destClass) {
        Field fields[] = destClass.getDeclaredFields();
        Class superClass = destClass.getSuperclass();
        Field superFields[] = superClass.getDeclaredFields();
        Field allFields[] = new Field[fields.length + superFields.length];
        int i = 0;
        for(; i < fields.length; i++) {
            allFields[i] = fields[i];
        }
        for(int j = 0; j < superFields.length; j++, i++) {
            allFields[i] = superFields[j];
        }
        return allFields;
    }
    
    /**
     * Set property value to target object.
     * 
     * @param target Target object.
     * @param ref Reference (property).
     * @param value Value object.
     * @throws ReflectorException
     * @throws IllegalAccessException Illegal access exception in Field.get().
     * @throws InvocationTargetException Invocation exception when calling
     *             accessor or mutator.
     */
    public static void setPropertyValue(Object target, String ref, Object value)
            throws ReflectorException, IllegalAccessException,
            InvocationTargetException {
        Class dest = target.getClass();
        Object child = target;
        Object ret = null;
        boolean isFound = false;

        if(dest == null) {
            throw new ReflectorException(ReflectorException.ERROR_NULL_CLASS);
        }

        String[] splittedRef = ref.split("\\.");
        int refIndex = 0;

        Field[] fields = getAllFields(dest);

        for(int i = 0; i < fields.length; i++) {
            if(fields[i].getName().equals(splittedRef[refIndex])) {
                if(refIndex < splittedRef.length - 1) {
                    refIndex++;
                    if(Modifier.isPublic(fields[i].getModifiers())) {
                        child = fields[i].get(target);
                        dest = child.getClass();
                    } else {
                        if(hasAccessor(dest, fields[i].getName())) {
                            child = invokeAccessor(child, fields[i].getName());
                            if(child == null) {
                                throw new ReflectorException(
                                        ReflectorException.ERROR_CANNOT_GET_FIELD_CHILD
                                                + fields[i].getName());
                            }

                            dest = child.getClass();
                        }
                    }

                    fields = getAllFields(dest);
                    // reset i. the i will be plus by for loop, so set i as -1.
                    i = -1;
                } else { // refIndex == splittedRef.length - 1
                    if(Modifier.isPublic(fields[i].getModifiers())) {
                        fields[i].set(child, value);
                    } else {
                        if(hasMutator(dest, fields[i].getName())) {
                            invokeMutator(child, fields[i].getName(), value);
                        }
                    }

                    isFound = true;
                    break;
                }
            }
        }

        if(!isFound) {
            throw new ReflectorException(ReflectorException.ERROR_NO_THIS_FIELD
                    + splittedRef[refIndex]);
        }

        return;
    }

    /**
     * Has mutator or not in class and field name you give.
     * 
     * @param dest Target class.
     * @param fieldName Field name.
     * @return true or false.
     * @throws ReflectorException
     */
    public static boolean hasMutator(Class dest, String fieldName)
            throws ReflectorException {
        return (getMutator(dest, fieldName) == null ? false : true);
    }

    /**
     * Call mutator of given field.
     * 
     * @param dest Target object.
     * @param fieldName Field name.
     * @param value The value object you want to pass to mutator.
     * @return Result of invoke().
     * @throws ReflectorException
     * @throws IllegalAccessException Illegal access exception in Field.get().
     * @throws InvocationTargetException Invocation exception when calling
     *             accessor or mutator.
     */
    public static Object invokeMutator(Object dest, String fieldName,
            Object value) throws ReflectorException, IllegalAccessException,
            InvocationTargetException {
        Method mutator = getMutator(dest.getClass(), fieldName);

        if(mutator == null) {
            throw new ReflectorException(ReflectorException.ERROR_NO_MUTATOR
                    + fieldName);
        }

        Object[] param = new Object[1];
        param[0] = value;

        return mutator.invoke(dest, param);
    }

    /**
     * Get mutator.
     * 
     * @param destClass Target class.
     * @param fieldName Field name.
     * @return Mutator.
     * @throws ReflectorException
     */
    public static Method getMutator(Class destClass, String fieldName)
            throws ReflectorException {
        Method[] methods = destClass.getMethods();
        Class type = getFieldType(destClass, fieldName);
        String prefix = getMutatorPrefix();

        for(int i = 0; i < methods.length && type != null; i++) {
            if(methods[i].getName().equalsIgnoreCase(prefix + fieldName)) {
                Class[] parameters = methods[i].getParameterTypes();
                if(parameters.length != 1 || !parameters[0].equals(type)) {
                    continue;
                } else {
                    return methods[i];
                }
            }
        }

        return null;
    }

    /**
     * Get mutator method prefix.
     * 
     * @return Mutator prefix.
     */
    private static String getMutatorPrefix() {
        return "set";
    }

    /**
     * Get field from target class.
     * 
     * @param target Target class.
     * @param fieldName Field name.
     * @return Field.
     */
    public static Field getField(Class target, String fieldName) {
        Field[] fields = getAllFields(target);
        for(int i = 0; i < fields.length; i++) {
            if(fields[i].getName().equals(fieldName)) {
                return fields[i];
            }
        }

        return null;
    }

    /**
     * Copy properties from src to target by given mode.
     * 
     * @param target Target object.
     * @param src Source object.
     * @throws ReflectorException
     * @throws IllegalAccessException Illegal access exception in Field.get().
     * @throws InvocationTargetException Invocation exception when calling
     *             accessor or mutator.
     * @throws InstantiationException
     * @note Copy properties by mode when property is instance of Array, Map,
     *       Collection.
     */
    public static void copyProperties(Object target, Object src)
            throws ReflectorException, IllegalAccessException,
            InvocationTargetException, InstantiationException {
        copyProperties(target, src, COPY_MODE_NORMAL);
    }

    /**
     * Copy properties from src to target by given mode.
     * 
     * @param target Target object.
     * @param src Source object.
     * @param mode Copy mode.
     * @throws ReflectorException
     * @throws IllegalAccessException Illegal access exception in Field.get().
     * @throws InvocationTargetException Invocation exception when calling
     *             accessor or mutator.
     * @throws InstantiationException
     * @note Copy properties by mode when property is instance of Array, Map,
     *       Collection. Target object must not be null.
     */
    public static void copyProperties(Object target, Object src, int mode)
            throws ReflectorException, IllegalAccessException,
            InvocationTargetException, InstantiationException {
        if(src == null) {
            return;
        }

        List srcPropertyNames = getPropertyNames(src, ".");
        List targetPropertyNames = getPropertyNames(target, ".");

        for(int i = 0; i < targetPropertyNames.size(); i++) {
            String targetProperty = (String)targetPropertyNames.get(i);
            if(!srcPropertyNames.contains(targetProperty)) {
                continue;
            }

            Class targetFieldType = getFieldType(target.getClass(),
                    targetProperty);
            // the same property name but not the same type...
            if(!getFieldType(src.getClass(), targetProperty).equals(
                    targetFieldType)) {
                continue;
            }

            if(targetFieldType.isArray() && mode != COPY_MODE_NORMAL) {
                Object[] targetArray = (Object[])getPropertyValue(target,
                        targetProperty);
                Object[] srcArray = (Object[])getPropertyValue(src,
                        targetProperty);
                Object[] finalArray = null;

                if(mode == COPY_MODE_UNION) {
                    finalArray = ContainerUtils.unionArray(targetArray,
                            srcArray, targetFieldType);
                } else if(mode == COPY_MODE_INTERSECTION) {
                    finalArray = ContainerUtils.intersectionArray(targetArray,
                            srcArray, targetFieldType);
                } else if(mode == COPY_MODE_DIFFERENCE) {
                    finalArray = ContainerUtils.differenceArray(targetArray,
                            srcArray, targetFieldType);
                }

                setPropertyValue(target, targetProperty, finalArray);
            } else if(Map.class.isAssignableFrom(targetFieldType)
                    && mode != COPY_MODE_NORMAL) {
                Map targetMap = (Map)getPropertyValue(target, targetProperty);
                Map srcMap = (Map)getPropertyValue(src, targetProperty);
                Map finalMap = null;
                if(mode == COPY_MODE_UNION) {
                    finalMap = ContainerUtils.unionMap(targetMap, srcMap);
                } else if(mode == COPY_MODE_INTERSECTION) {
                    finalMap = ContainerUtils
                            .intersectionMap(targetMap, srcMap);
                } else if(mode == COPY_MODE_DIFFERENCE) {
                    finalMap = ContainerUtils.differenceMap(targetMap, srcMap);
                }

                setPropertyValue(target, targetProperty, finalMap);
            } else if(Collection.class.isAssignableFrom(targetFieldType)
                    && mode != COPY_MODE_NORMAL) {
                Collection targetCollection = (Collection)getPropertyValue(
                        target, targetProperty);
                Collection srcCollection = (Collection)getPropertyValue(src,
                        targetProperty);
                Collection finalCollection = null;

                if(mode == COPY_MODE_UNION) {
                    finalCollection = ContainerUtils.unionCollection(
                            targetCollection, srcCollection);
                } else if(mode == COPY_MODE_INTERSECTION) {
                    finalCollection = ContainerUtils.intersectionCollection(
                            targetCollection, srcCollection);
                } else if(mode == COPY_MODE_DIFFERENCE) {
                    finalCollection = ContainerUtils.differenceCollection(
                            targetCollection, srcCollection);
                }

                setPropertyValue(target, targetProperty, finalCollection);
            } else {
                setPropertyValue(target, targetProperty, getPropertyValue(src,
                        targetProperty));
            }
        }

        return;
    }
}

class ContainerUtils {
    /**
     * Difference array.
     * 
     * @param arrayA Array A.
     * @param arrayB Array B.
     * @param elementFieldType Field type of array element.
     * @return Result of A - B.
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public static Object[] differenceArray(Object[] arrayA, Object[] arrayB,
            Class elementFieldType) throws InstantiationException,
            IllegalAccessException {
        if(arrayB == null) {
            // A - null = A;
            return arrayA;
        }

        if(arrayA == null) {
            // null - B = null;
            return null;
        }

        List listA = new ArrayList();
        for(int j = 0; j < arrayA.length; j++) {
            listA.add(arrayA[j]);
        }

        List listB = new ArrayList();
        for(int j = 0; j < arrayB.length; j++) {
            listB.add(arrayB[j]);
        }

        Collection finalArrayList = differenceCollection(listA, listB);

        Object finalArray = Array.newInstance(elementFieldType
                .getComponentType(), finalArrayList.size());
        System.arraycopy(finalArrayList.toArray(), 0, finalArray, 0,
                finalArrayList.size());

        return (Object[])finalArray;
    }

    /**
     * Intersection array.
     * 
     * @param arrayA Array A.
     * @param arrayB Array B.
     * @param elementFieldType Field type of array element.
     * @return Result of A ^ B.
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public static Object[] intersectionArray(Object[] arrayA, Object[] arrayB,
            Class elementFieldType) throws InstantiationException,
            IllegalAccessException {
        if(arrayB == null || arrayA == null) {
            // A ^ null = null ^ B = null ^ null = null
            return null;
        }

        List listA = new ArrayList();
        for(int j = 0; j < arrayA.length; j++) {
            listA.add(arrayA[j]);
        }

        List listB = new ArrayList();
        for(int j = 0; j < arrayB.length; j++) {
            listB.add(arrayB[j]);
        }

        Collection finalArrayList = intersectionCollection(listA, listB);

        Object finalArray = Array.newInstance(elementFieldType
                .getComponentType(), finalArrayList.size());
        System.arraycopy(finalArrayList.toArray(), 0, finalArray, 0,
                finalArrayList.size());

        return (Object[])finalArray;
    }

    /**
     * Union array.
     * 
     * @param arrayA Array A.
     * @param arrayB Array B.
     * @param elementFieldType Field type of array element.
     * @return Result of A \/ B.
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @note The result will not contain the same object in array.
     */
    public static Object[] unionArray(Object[] arrayA, Object[] arrayB,
            Class elementFieldType) throws IllegalAccessException,
            InstantiationException {
        if(arrayB == null) {
            // A \/ null = A
            return arrayA;
        }

        if(arrayA == null) {
            // null \/ B = B
            return arrayB;
        }

        List listA = new ArrayList();
        for(int j = 0; j < arrayA.length; j++) {
            listA.add(arrayA[j]);
        }

        List listB = new ArrayList();
        for(int j = 0; j < arrayB.length; j++) {
            listB.add(arrayB[j]);
        }

        Collection finalArrayList = unionCollection(listA, listB);

        Object finalArray = Array.newInstance(elementFieldType
                .getComponentType(), finalArrayList.size());
        System.arraycopy(finalArrayList.toArray(), 0, finalArray, 0,
                finalArrayList.size());

        return (Object[])finalArray;
    }

    /**
     * Difference collection.
     * 
     * @param collectionA Collection A.
     * @param collectionB Collection B.
     * @return Result of A - B.
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @note Result will not contain the same object. if you want it contain the
     *       same object in collection, see Jakarta Commons Collection.
     */
    public static Collection differenceCollection(Collection collectionA,
            Collection collectionB) throws IllegalAccessException,
            InstantiationException {
        if(collectionB == null) {
            // A - null = A
            return collectionA;
        }

        if(collectionA == null) {
            // null - B = null;
            return null;
        }

        Collection finalCollection = (Collection)collectionA.getClass()
                .newInstance();
        finalCollection.addAll(collectionA);
        Iterator i = collectionB.iterator();
        while(i.hasNext()) {
            finalCollection.remove(i.next());
        }

        return finalCollection;
    }

    /**
     * Intersection collection.
     * 
     * @param collectionA Collection A.
     * @param collectionB Collection B.
     * @return Result of A ^ B.
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @note Result will not contain the same object. if you want it contain the
     *       same object in collection, see Jakarta Commons Collection.
     */
    public static Collection intersectionCollection(Collection collectionA,
            Collection collectionB) throws IllegalAccessException,
            InstantiationException {
        if(collectionB == null || collectionA == null) {
            // A ^ null = null ^ B = null ^ null = null
            return null;
        }

        Collection finalCollection = (Collection)collectionA.getClass()
                .newInstance();
        Set elts = new HashSet(collectionA);
        elts.retainAll(collectionB);
        Iterator i = elts.iterator();
        while(i.hasNext()) {
            finalCollection.add(i.next());
        }

        return finalCollection;
    }

    /**
     * Union collection.
     * 
     * @param collectionA Collection A.
     * @param collectionB Collection B.
     * @return Result of A \/ B.
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @note Result will not contain the same object. if you want it contain the
     *       same object in collection, see Jakarta Commons Collection.
     */
    public static Collection unionCollection(Collection collectionA,
            Collection collectionB) throws IllegalAccessException,
            InstantiationException {
        if(collectionB == null) {
            // A \/ null = A
            return collectionA;
        }
        if(collectionA == null) {
            // null \/ B = B
            return collectionB;
        }

        Collection finalCollection = (Collection)collectionA.getClass()
                .newInstance();
        Set elts = new HashSet(collectionA);
        elts.addAll(collectionB);
        Iterator i = elts.iterator();
        while(i.hasNext()) {
            finalCollection.add(i.next());
        }

        return finalCollection;
    }

    /**
     * Difference map.
     * 
     * @param mapA Map A.
     * @param mapB Map B.
     * @return Result of A - B.
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @note for example: A: "1"->"a", "2"->"b" B: "3"->"c", "1"->"d" A - B =>
     *       "2"->"b".
     */
    public static Map differenceMap(Map mapA, Map mapB)
            throws IllegalAccessException, InstantiationException {
        if(mapB == null) {
            // A - null = A
            return mapA;
        }

        if(mapA == null) {
            // null - B = null
            return null;
        }

        Map finalMap = (Map)mapA.getClass().newInstance();
        Set keyA = new HashSet(mapA.keySet());
        Set keyB = new HashSet(mapB.keySet());
        Set elts = (Set)differenceCollection(keyA, keyB);
        Iterator i = elts.iterator();
        while(i.hasNext()) {
            Object key = i.next();
            finalMap.put(key, mapA.get(key));
        }

        return finalMap;
    }

    /**
     * Intersection map.
     * 
     * @param mapA Map A.
     * @param mapB Map B.
     * @return Result of A ^ B.
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @note If A and B contains the same key, value of A will give up. for
     *       example: A: "1"->"a", "2"->"b" B: "3"->"c", "1"->"d" A ^ B =>
     *       "1"->"d".
     */
    public static Map intersectionMap(Map mapA, Map mapB)
            throws IllegalAccessException, InstantiationException {
        if(mapB == null || mapA == null) {
            // A ^ null = null ^ B = null ^ null = null
            return null;
        }

        Map finalMap = (Map)mapA.getClass().newInstance();
        Set elts = new HashSet(mapA.keySet());
        elts.retainAll(mapB.keySet());
        Iterator i = elts.iterator();
        while(i.hasNext()) {
            Object key = i.next();
            if(mapB.containsKey(key)) {
                finalMap.put(key, mapB.get(key));
            } else {
                finalMap.put(key, mapA.get(key));
            }
        }

        return finalMap;
    }

    /**
     * Union map.
     * 
     * @param mapA Map A.
     * @param mapB Map B.
     * @return Result of A \/ B.
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @note If A and B contains the same key, value of A will give up. for
     *       example: A: "1"->"a", "2"->"b" B: "3"->"c", "1"->"d" A \/ B =>
     *       "1"->"d", "2"->"b", "3"->"c".
     */
    public static Map unionMap(Map mapA, Map mapB)
            throws IllegalAccessException, InstantiationException {
        if(mapB == null) {
            // A \/ null = A
            return mapA;
        }
        if(mapA == null) {
            // null \/ B = B
            return mapB;
        }

        Map finalMap = (Map)mapA.getClass().newInstance();
        Set elts = new HashSet(mapA.keySet());
        elts.addAll(mapB.keySet());
        Iterator i = elts.iterator();
        while(i.hasNext()) {
            Object key = i.next();
            if(mapB.containsKey(key)) {
                finalMap.put(key, mapB.get(key));
            } else {
                finalMap.put(key, mapA.get(key));
            }
        }

        return finalMap;
    }
}
