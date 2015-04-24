package com.kangdainfo.util.lang.reflect.converter;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import com.kangdainfo.util.lang.reflect.ClassUtil;

public class JSONObjectConverter {

	private Object businessObject;
	private Object convertedObject;
	private BusinessObjectConverter businessObjectConverter;
	private DataTypeConverter dataTypeConverter;
	private StringConverter stringConverter;
	
	private JSONObjectConverter() {
		this.businessObjectConverter = new BusinessObjectConverter();
		this.dataTypeConverter = new DataTypeConverter();
		this.stringConverter = new StringConverter();
	}
	
	public JSONObjectConverter(Object businessObject) {
		this();
		this.businessObject = businessObject;
		
		assertBusinessObject();
		Set traversedBos = new HashSet();
		
		this.convertedObject = businessObjectConverter.parse(businessObject,null,traversedBos);
		this.convertedObject = dataTypeConverter.parse(convertedObject);
	}

	public Object getBusinessObject() { return businessObject; }
	public Object getConvertedObject() { return convertedObject; }	

	private void assertBusinessObject() {
		if(ClassUtil.isInstanceOfString(businessObject) || 
		   ClassUtil.isInstanceOfPrimitive(businessObject) || 
		   ClassUtil.isInstanceOfPrimitiveWrapper(businessObject) || 
		   ClassUtil.isInstanceOfJavaUtilDate(businessObject)
		) 
		{
			throw new RuntimeException("This is not a business object");
		}
	}
	
	public JSONObject getJSONObject() {
		return (JSONObject) getJSONObject(convertedObject);
	}
	private Object getJSONObject(Object obj) {
		JSONObject jSONObject = new JSONObject();
		if(obj==null) {
			return obj;
		}
		if(ClassUtil.isInstanceOfString(obj)) {
			return obj;	
		}
		else if(ClassUtil.isInstanceOfPrimitive(obj)) {
			return obj;	
		}
		else if(ClassUtil.isInstanceOfPrimitiveWrapper(obj)) {
			return obj;	
		}
		else if(ClassUtil.isInstanceOfList(obj) || ClassUtil.isInstanceOfSet(obj)) {
			List list = (List) obj;
			JSONArray jSONArray = new JSONArray();
			for(int i=0; i < list.size(); i++) {
				Object businessObject2 = list.get(i);
				jSONArray.add(getJSONObject(businessObject2));
			}
			return jSONArray;
		}
		else if(ClassUtil.isInstanceOfMap(obj)) {
			Map map = (Map) obj;
			String keys[] = (String[]) map.keySet().toArray(new String[map.size()]);
			for(int i=0; i < keys.length; i++) {
				String key = keys[i];
				Object value = map.get(key);
				setJsonObjectValue(jSONObject, key, getJSONObject(value));
			}
		}
		return jSONObject;
	}
	private void setJsonObjectValue(JSONObject jSONObject, String key, Object value) {
		try {
			jSONObject.put(key, value);	
		} catch (JSONException e) {
			throw new RuntimeException("Error setting property value of [" + key + "]",e);
		}			
	}
	


	public String toString() {
		return stringConverter.toString(convertedObject, businessObject.getClass().getSimpleName());
	}

	


	

}
