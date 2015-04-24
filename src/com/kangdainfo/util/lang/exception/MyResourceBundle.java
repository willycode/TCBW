package com.kangdainfo.util.lang.exception;

import java.util.ArrayList;
import java.util.List;

public class MyResourceBundle {

	private String key;
	private List args;
	
	public String getKey() { return key; }
	public void setKey(String key) { this.key = key; }
	public List getArgs() { return args; }
	public void setArgs(List args) { this.args = args; }
	
	protected MyResourceBundle() {
		this.args = new ArrayList();
	}
	public MyResourceBundle(String key) {
		this();
		this.key = key;
	}	
	
	public MyResourceBundle addArg(String arg) {
		addArg(arg,false);
		return this;
	}
	public MyResourceBundle addArg(String arg, boolean translationRequired) {
		Object argArray[] = new Object[2];
		argArray[0] = arg;
		argArray[1] = new Boolean(translationRequired);
		args.add(argArray);
		return this;
	}
	public MyResourceBundle addArg(MyResourceBundle arg) {
		Object argArray[] = new Object[2];
		argArray[0] = arg;
		argArray[1] = null;
		args.add(argArray);
		return this;
	}	
	
}
