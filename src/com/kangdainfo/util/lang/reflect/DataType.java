package com.kangdainfo.util.lang.reflect;

public class DataType {

	private Object data;
	private int type;
	
	public DataType() {
		this.type = Type.UNDEFINED;
	}
	
	public interface Type {
		int UNDEFINED = -1;
		int NULL = 0;
		int STRING = 1;
		int PRIMITIVE = 2;
		int PRIMITIVE_WRAPPER = 3;
		int JAVA_DATE_UTIL = 4;
		int LIST = 5;
		int SET = 6;
		int MAP = 7;
		int BUSINESS_OBJECT = 8;
	}

	public Object getData() { return data; }
	public void setData(Object data) { this.data = data; }
	public int getType() { return type; }
	public void setType(int type) { this.type = type; }
	
	
	
}
