package com.kangdainfo.util.database.bo;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * DbTableColumn
 * 
 * @author catty.liao
 * @version 1.0, Created on 2006/6/28
 */
public class DbTableColumn implements Serializable {

	public DbTableColumn() {
	}

	// no pk
	public DbTableColumn(String name, String type, String label, int precision,
			int scale, int displaySize, boolean allowNull) {
		this.name = name;
		this.type = type;
		this.typeId = 0;
		this.label = label;
		this.precision = precision;
		this.scale = scale;
		this.displaySize = displaySize;
		this.allowNull = allowNull;
		this.pk = false;
	}
	// no pk
	public DbTableColumn(String name, String type, int typeId, String label,
			int precision, int scale, int displaySize, boolean allowNull) {
		this.name = name;
		this.type = type;
		this.typeId = typeId;
		this.label = label;
		this.precision = precision;
		this.scale = scale;
		this.displaySize = displaySize;
		this.allowNull = allowNull;
		this.pk = false;
	}
	
	// has pk
	public DbTableColumn(String name, String type, String label, int precision,
			int scale, int displaySize, boolean allowNull, boolean pk) {
		this.name = name;
		this.type = type;
		this.typeId = 0;
		this.label = label;
		this.precision = precision;
		this.scale = scale;
		this.displaySize = displaySize;
		this.allowNull = allowNull;
		this.pk = pk;
	}
	
	// has pk
	public DbTableColumn(String name, String type, int typeId, String label,
			int precision, int scale, int displaySize, boolean allowNull,
			boolean pk) {
		this.name = name;
		this.type = type;
		this.typeId = typeId;
		this.label = label;
		this.precision = precision;
		this.scale = scale;
		this.displaySize = displaySize;
		this.allowNull = allowNull;
		this.pk = pk;
	}

	protected String name = "";

	protected String type = "";

	protected int typeId = 0;

	protected String label = "";

	protected int precision = 0;

	protected int scale = 0;

	protected int displaySize = 0;

	protected boolean allowNull = false;

	protected boolean pk = false;

	protected String description = "" ;

	public int getDisplaySize() {
		return displaySize;
	}

	public void setDisplaySize(int displaySize) {
		this.displaySize = displaySize;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrecision() {
		return precision;
	}

	public void setPrecision(int precision) {
		this.precision = precision;
	}

	public int getScale() {
		return scale;
	}

	public void setScale(int scale) {
		this.scale = scale;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isAllowNull() {
		return allowNull;
	}

	public void setAllowNull(boolean allowNull) {
		this.allowNull = allowNull;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public boolean isPk() {
		return pk;
	}

	public void setPk(boolean pk) {
		this.pk = pk;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String toString() {
		ToStringBuilder sb = new ToStringBuilder(this)
				.append("name", getName()).append("type", getType()).append(
						"label", getLabel()).append("displaySize",
						getDisplaySize()).append("allowNull", isAllowNull())
				.append("pk", isPk());
		return sb.toString();
	}
}
