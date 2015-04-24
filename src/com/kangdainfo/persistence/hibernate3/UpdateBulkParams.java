package com.kangdainfo.persistence.hibernate3;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.kangdainfo.util.DateUtil;

public class UpdateBulkParams {

	private Logger logger = Logger.getLogger(this.getClass());
	
	private Class updateClass;
	private List updateSets;
	private List updateConditions;
	
	public UpdateBulkParams() {
		this.updateSets = new ArrayList();
		this.updateConditions = new ArrayList();
	}
	public UpdateBulkParams(Class updateClass) {
		this();
		this.updateClass = updateClass;
	}
	
	public void addUpdateSet(String fieldName, Object fieldValue){
		updateSets.add(new UpdateSet(fieldName,fieldValue));
	}
	public void addUpdateCondition(String fieldName, Object fieldValue, String condition){
		updateConditions.add(new UpdateCondition(fieldName,fieldValue,condition));
	}	
	

	public class UpdateSet {
		
		private String name;
		private Object value;
		
		public UpdateSet(String name, Object value) {
			this.name = name;
			this.value = value;
		}
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public Object getValue() {
			return value;
		}
		public void setValue(Object value) {
			this.value = value;
		}
	}
	private class UpdateCondition {
		
		private String name;
		private Object value;
		private String condition;
		
		public UpdateCondition(String name, Object value, String condition) {
			this.name = name;
			this.value = value;
			this.condition = condition;
		}
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public Object getValue() {
			return value;
		}
		public void setValue(Object value) {
			this.value = value;
		}
		public String getCondition() {
			return condition;
		}
		public void setCondition(String condition) {
			this.condition = condition;
		}
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		String alias = " a ";
		sb.append("update " + updateClass.getName() + " " + alias + " set ");
		for(int i=0; i < updateSets.size(); i++) {
			UpdateSet updateSet = (UpdateSet) updateSets.get(i);
			String name = updateSet.getName();
			Object value = updateSet.getValue();
			if(value instanceof Integer) {
				sb.append(alias + "." + name + "=" + value); 				
			}
			else if(value instanceof java.util.Date) {
				sb.append(alias + "." + name + "=" +  "'" + DateUtil.parseToSqlDateString((java.util.Date)value) + "'"); 				
			}
			else {
				sb.append(alias + "." + name + "=" + "'" + value + "'"); 
			}
			if(i!=updateSets.size()-1) {
				sb.append(",");
			}
		}
		sb.append(" where ");
		for(int i=0; i < updateConditions.size(); i++) {
			UpdateCondition updateCondition = (UpdateCondition) updateConditions.get(i);
			String name = updateCondition.getName();
			Object value = updateCondition.getValue();
			String condition = updateCondition.getCondition();
			if(value instanceof Integer) {
				sb.append(alias + "." + name + condition +  value); 
			}
			else {
				sb.append(alias + "." + name + condition + "'" + value + "'"); 
			}
			if(i!=updateConditions.size()-1) {
				sb.append(" and ");
			}
		}
		logger.info(sb.toString());
		return sb.toString();
	}
	

	
}
