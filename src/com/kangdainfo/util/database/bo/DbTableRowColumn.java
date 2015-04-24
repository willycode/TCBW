package com.kangdainfo.util.database.bo;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * DbTableRowColumn
 * 
 * @author catty.liao
 * @version 1.0, Created on 2006/6/28
 */
public class DbTableRowColumn extends DbTableColumn implements Serializable {

	private Object value;

	public DbTableRowColumn() {
	}

	public DbTableRowColumn(String name, String type, int typeId, String label,
			int precision, int scale, int displaySize, boolean allowNull,
			boolean pk, Object value) {
		this.name = name;
		this.type = type;
		this.typeId = typeId;
		this.label = label;
		this.precision = precision;
		this.scale = scale;
		this.displaySize = displaySize;
		this.allowNull = allowNull;
		this.pk = pk;
		this.value = value;
	}

	public DbTableRowColumn(DbTableColumn column) {
		this.name = column.getName();
		this.type = column.getType();
		this.typeId = column.getTypeId();
		this.label = column.getLabel();
		this.precision = column.getPrecision();
		this.scale = column.getScale();
		this.displaySize = column.getDisplaySize();
		this.allowNull = column.isAllowNull();
		this.pk = column.isPk();
		this.value = null;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public String toString() {
		ToStringBuilder sb = new ToStringBuilder(this)
				.append("name", getName()).append("type", getType()).append(
						"value", getValue()).append("allowNull", isAllowNull())
				.append("pk", isPk());
		return sb.toString();
	}

}
