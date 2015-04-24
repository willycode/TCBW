package com.kangdainfo.util.database.bo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * DbTableRow
 *
 * @author catty.liao
 * @version 1.0, Created on 2006/6/28
 */
public class DbTableRow implements Serializable {

	public DbTableRow() {
	}

	private List<DbTableRowColumn> columns = new ArrayList();

	private Map cache = new HashMap();

	public void addColumn(DbTableRowColumn column) {
            System.out.println("column is "+column);
		columns.add(column);
		cache.put(column.getName(), column);
	}

	public DbTableRowColumn getColumn(String name) {
		return (DbTableRowColumn) cache.get(name);
	}

	public List<DbTableRowColumn> getColumns() {
		return columns;
	}

	public void setColumns(List<DbTableRowColumn> columns) {
		this.columns = columns;
		for (DbTableRowColumn column : columns) {
			cache.put(column.getName(), column);
		}
	}

	public String toString() {
		ToStringBuilder sb = new ToStringBuilder(this).append("column.size",
				columns.size());
		return sb.toString();
	}

}
