package com.kangdainfo.persistence.hibernate3.query.params;

import org.apache.log4j.Logger;

/**
 * 
 * QueryHqlParam
 * 
 * @author catty
 * @version 1.0, Created on 2007/3/13
 */
public class QueryHqlParam extends QueryParam {

	private Logger logger = Logger.getLogger(this.getClass());

	private String hql;

	private String names[];

	private Object values[];

	public QueryHqlParam() {
	}

	public QueryHqlParam(String hql, String names[], Object values[]) {
		this.hql = hql;
		this.names = names;
		this.values = values;
		// no used, set default value
		setBindType("");
		setName("");
		setCompare("");
	}

	public String getHql() {
		return hql;
	}

	public void setHql(String hql) {
		this.hql = hql;
	}

	public Object[] getValues() {
		return values;
	}

	public void setValues(Object[] values) {
		this.values = values;
	}

	public String[] getNames() {
		return names;
	}

	public void setNames(String[] names) {
		this.names = names;
	}

	/**
	 * to Sql
	 * 
	 * @param alias
	 * @return string
	 */
	public String toSql(String alias) {
		return getHql();
	}

	public String getSafeParameterName(QueryParam qp) {
		return "";
	}

	/**
	 * clone
	 */
	public Object clone() {
		QueryHqlParam queryParam = new QueryHqlParam();
		queryParam.setHql(this.hql);
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
