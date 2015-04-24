package com.kangdainfo.util.uid;

public class UniqueId {

	private Object uid;
	
	public UniqueId(Object uid) {
		this.uid = uid;
	}
	
	public String toString() {
		return uid.toString();
	}
	
	public String getTextValue() {
		return uid.toString();
	}
	
}
