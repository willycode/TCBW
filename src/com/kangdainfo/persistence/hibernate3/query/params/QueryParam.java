package com.kangdainfo.persistence.hibernate3.query.params;

import org.apache.log4j.Logger;

import com.kangdainfo.persistence.hibernate3.QueryCondition;

/**
 * QueryParam
 * 
 * @author catty.liao
 * @version 1.0, Created on 2006/8/23
 */
public class QueryParam {

	private Logger logger = Logger.getLogger(this.getClass());

	private String bindType;

	private String name;

	private String safeName;

	private String compare;

	private Object values[];

	public QueryParam() {
	}

	public QueryParam(String bindType, String name, String compare,
			Object values[]) {
		this.bindType = bindType;
		this.name = name;
		this.compare = compare;
		this.values = values;
	}

	public QueryParam(String bindType, String name, String compare, Object value) {
		this.bindType = bindType;
		this.name = name;
		this.compare = compare;
		this.values = new Object[] { value };
	}

	public String getCompare() {
		return compare;
	}

	public void setCompare(String compare) {
		this.compare = compare;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSafeName() {
		return safeName;
	}

	public void setSafeName(String safeName) {
		this.safeName = safeName;
	}

	public String getBindType() {
		return bindType;
	}

	public void setBindType(String bindType) {
		this.bindType = bindType;
	}

	public Object[] getValues() {
		return values;
	}

	public void setValues(Object[] values) {
		this.values = values;
	}

	public String toString() {
		return toSql();
	}

	/**
	 * to Sql
	 * 
	 * @return string
	 */
	public String toSql() {
		return toSql(QueryHelper.DEFAULT_ALIAS);
	}

	/**
	 * to Sql
	 * 
	 * @param alias
	 * @return string
	 */
	public String toSql(String alias) {
		alias = alias.trim();
		if (alias.trim().length() > 0) {
			if (!alias.endsWith("."))
				alias = alias.concat(".");
		}
		if (compare.equals(QueryCondition.BETWEEN)
				|| compare.equals(QueryCondition.NOT_BETWEEN)) {
			return " " + bindType + " " + alias + name + " " + compare + " :"
					+ safeName + "0 and " + ":" + safeName + "1";
		} else if (compare.equals(QueryCondition.IN)
				|| compare.equals(QueryCondition.NOT_IN)) {
			return " " + bindType + " " + alias + name + " " + compare + " (:"
					+ safeName + ")";
		} else if (compare.equals(QueryCondition.LIKE)
				|| compare.equals(QueryCondition.NOT_LIKE)) {
			return " " + bindType + " " + alias + name + " " + compare + " (:"
					+ safeName + ")";
		} else if (compare.equals(QueryCondition.IS_NULL)) {
			return " " + bindType + " ( " + alias + name + " " + compare
					+ " or " + alias + name + " = '' )";
		} else if (compare.equals(QueryCondition.IS_NOT_NULL)) {
			return " " + bindType + " ( " + alias + name + " " + compare
					+ " or " + alias + name + " != '' )";
		} else {
			return " " + bindType + " " + alias + name + compare + ":"
					+ safeName;
		}
	}

	/**
	 * clone
	 */
	public Object clone() {
		QueryParam queryParam = new QueryParam();
		queryParam.setBindType(this.bindType);
		queryParam.setCompare(this.compare);
		queryParam.setName(this.name);
		queryParam.setSafeName(this.safeName);
		if (this.values != null && this.values.length > 0) {
			Object newValues[] = new Object[this.values.length];
			for (int i = 0; i < this.values.length; i++) {
				newValues[i] = this.values[i];
			}
			queryParam.setValues(newValues);
		}
		return queryParam;
	}

}
