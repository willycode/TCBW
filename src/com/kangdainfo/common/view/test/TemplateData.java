package com.kangdainfo.common.view.test;

import com.kangdainfo.common.util.Common;

public class TemplateData {
	private String fieldName; //0-英文名稱
	private String fieldChineseName; //1-中文名稱
	private String fieldType; //2-型態
	private String fieldLength; //3-長度
	private String fieldNotNull; //4-not null
	private String fieldDesc; //5-內容說明
	private String fieldOldTable; //6-來源資料檔
	private String fieldOldField; //7-來源欄位
	private String fieldOldType; //8-型態
	private String fieldOldLength; //9-長度
	private String fieldConvert; //10-轉檔處理說明　
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getFieldChineseName() {
		return fieldChineseName;
	}
	public void setFieldChineseName(String fieldChineseName) {
		this.fieldChineseName = fieldChineseName;
	}
	public String getFieldType() {
		return fieldType;
	}
	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}
	public String getFieldLength() {
		return fieldLength;
	}
	public void setFieldLength(String fieldLength) {
		this.fieldLength = fieldLength;
	}
	public String getFieldNotNull() {
		return fieldNotNull;
	}
	public void setFieldNotNull(String fieldNotNull) {
		this.fieldNotNull = fieldNotNull;
	}
	public String getFieldDesc() {
		return fieldDesc;
	}
	public void setFieldDesc(String fieldDesc) {
		this.fieldDesc = fieldDesc;
	}
	public String getFieldOldTable() {
		return fieldOldTable;
	}
	public void setFieldOldTable(String fieldOldTable) {
		this.fieldOldTable = fieldOldTable;
	}
	public String getFieldOldField() {
		return fieldOldField;
	}
	public void setFieldOldField(String fieldOldField) {
		this.fieldOldField = fieldOldField;
	}
	public String getFieldOldType() {
		return fieldOldType;
	}
	public void setFieldOldType(String fieldOldType) {
		this.fieldOldType = fieldOldType;
	}
	public String getFieldOldLength() {
		return fieldOldLength;
	}
	public void setFieldOldLength(String fieldOldLength) {
		this.fieldOldLength = fieldOldLength;
	}
	public String getFieldConvert() {
		return fieldConvert;
	}
	public void setFieldConvert(String fieldConvert) {
		this.fieldConvert = fieldConvert;
	}
	
    public TemplateData() {}
    	
	
	public TemplateData(String[] data) {
        if (data.length>=11) {
        	this.fieldName = data[0]; //0-英文名稱
        	this.fieldChineseName = data[1]; //1-中文名稱        	
        	this.fieldType = data[2]; //2-型態
        	this.fieldLength = data[3]; //3-長度
        	this.fieldNotNull = data[4]; //4-not null
        	this.fieldDesc = data[5]; //5-內容說明
        	this.fieldOldTable = data[6]; //6-來源資料檔
        	this.fieldOldField = data[7]; //7-來源欄位
        	this.fieldOldType = data[8]; //8-型態
        	this.fieldOldLength = data[9]; //9-長度
        	this.fieldConvert = data[10]; //10-轉檔處理說明　
        }
	}	
	
    public String toSQL1() {
        if (!"".equals(Common.get(fieldName)) && !"".equals(fieldType) ) {
            StringBuilder sb = new StringBuilder();
            sb.append(fieldName).append("\t\t").append("");
            if (fieldName.toLowerCase().equals("id")) {
                sb.append("ID   BIGINT IDENTITY(1,1)    NOT NULL PRIMARY KEY");
            } else {
                if (Common.getInt(fieldType)==9) {
                    double numLen = Common.getNumeric(fieldLength);
                    if (numLen>9) {
                        sb.append("NUMERIC(").append(Common.MoneyFormat(numLen,1).replace(".", ",")).append(")\t");
                    } else {
                        sb.append("INTEGER").append("\t");
                    }
                } else {
                	int len = Common.getInt(fieldLength);
                	if (len>20 && len<50) len = 50;                	
                	else if (len>50) len = len * 3;                	                	
                    sb.append("VARCHAR(").append(Common.MoneyFormat(len)).append(")\t");
                }
                if ("Y".equals(Common.get(fieldNotNull))) sb.append(" NOT NULL");
            }
            sb.append(",");
            sb.append(" /* \t").append(Common.get(fieldChineseName)).append("\t");
            sb.append(Common.get(fieldDesc)).append("\t");
            sb.append(Common.get(fieldOldTable)).append("\t");
            sb.append(Common.get(fieldOldField)).append("\t");
            sb.append(Common.get(fieldOldType)).append("\t");
            sb.append(Common.get(fieldOldLength)).append("\t");
            sb.append(Common.get(fieldConvert)).append("\t").append(" */");
            sb.append("\r\n");
            return sb.toString();
        }
        return "";
    }	
	
    public String toSQL() {
        if (!"".equals(Common.get(fieldName)) && !"".equals(fieldType) ) {
            StringBuilder sb = new StringBuilder();
            sb.append(Common.formatRearString(fieldName,30,' '));
            if (fieldName.toLowerCase().equals("id")) {
                sb.append(Common.formatRearString("BIGINT IDENTITY(1,1) NOT NULL PRIMARY KEY",45,' '));
            } else {
            	String sType = "";
                if (Common.getInt(fieldType)==9) {
                    double numLen = Common.getNumeric(fieldLength);
                    if (numLen>9) {
                        sType = "NUMERIC(" + Common.MoneyFormat(numLen,1).replace(".", ",") + ")";
                    } else {
                        sType = "INTEGER";
                    }
                } else {
                	int len = Common.getInt(fieldLength);
                	if (len>20 && len<50) len = 50;                	
                	else if (len>50) len = len * 3;                	                	
                    sType = "VARCHAR(" + Common.MoneyFormat(len) + ")";
                }
                if ("Y".equals(Common.get(fieldNotNull))) sType += " NOT NULL";
                
                sb.append(Common.formatRearString(sType, 45, ' '));
            }
            sb.append(",");
            sb.append(" /* \t").append(Common.get(fieldChineseName)).append("\t");
            sb.append(Common.get(fieldDesc)).append("\t");
            sb.append(Common.get(fieldOldTable)).append("\t");
            sb.append(Common.get(fieldOldField)).append("\t");
            sb.append(Common.get(fieldOldType)).append("\t");
            sb.append(Common.get(fieldOldLength)).append("\t");
            sb.append(Common.get(fieldConvert)).append("\t").append(" */");
            sb.append("\r\n");
            return sb.toString();
        }
        return "";
    }
}
