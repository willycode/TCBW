package com.kangdainfo.util.database.bo;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Table
 * 
 * @author catty.liao
 * @version 1.0, Created on 2006/6/28
 */
public class DbTable implements Serializable {

	public DbTable() {
	}

	public DbTable(String cat, String schem, String name) {
		this.cat = cat;
		this.schem = schem;
		this.name = name;
		this.description = "";
		this.pk = "";
		this.pkDescription = "";
	}

	public DbTable(String cat, String schem, String name, String pk) {
		this.cat = cat;
		this.schem = schem;
		this.name = name;
		this.description = "";
		this.pk = pk;
		this.pkDescription = "";
	}

	public DbTable(String cat, String schem, String name, String description,
			String pk, String pkDescription) {
		this.cat = cat;
		this.schem = schem;
		this.name = name;
		this.description = description;
		this.pk = pk;
		this.pkDescription = pkDescription;
	}

	private String cat;

	private String schem;

	private String name;

	private String description;

	private String pk;

	private String pkDescription;

	private List<DbTableRowColumn> columns;

	public String getCat() {
		return cat;
	}

	public void setCat(String cat) {
		this.cat = cat;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSchem() {
		return schem;
	}

	public void setSchem(String schem) {
		this.schem = schem;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPk() {
		return pk;
	}

	public void setPk(String pk) {
		this.pk = pk;
	}

	public String getPkDescription() {
		return pkDescription;
	}

	public void setPkDescription(String pkDescription) {
		this.pkDescription = pkDescription;
	}

	public List<DbTableRowColumn> getColumns() {
		return columns;
	}

	public void setColumns(List<DbTableRowColumn> columns) {
		this.columns = columns;
	}

	public String toString() {
		ToStringBuilder sb = new ToStringBuilder(this).append("cat", getCat())
				.append("schem", getSchem()).append("name", getName());
		return sb.toString();
	}

}
