package com.kangdainfo.util.io.configFile;

import java.util.Set;
import java.util.HashSet;

public class ConfigLine {
	
	private Set configSet;
	private String line;
	private String key;

	public Set getConfigSet() { return configSet; }
	public void setConfigSet(Set configSet) { this.configSet = configSet; }
	public String getKey() { return key; }
	public void setKey(String key) { this.key = key; }
	public String getLine() { return line; }
	public void setLine(String line) { this.line = line; }
	
	public ConfigLine() {
		this.configSet = new HashSet();
	}
	
	public void addConfigValue(String configValue) {
		this.configSet.add(configValue);
	}
	
	public String[] getConfigValues() {
		return (String[]) this.configSet.toArray(new String[configSet.size()]);
	}


	public void populate() {
		populate(line);
	}
	private void populate(String lineString) {
		int beginIndex = lineString.indexOf("${");
		int endIndex = lineString.indexOf("}");
		if(beginIndex!=-1 && endIndex!=-1 && endIndex > beginIndex) {
			String configValue = lineString.substring(beginIndex+2,endIndex);
			addConfigValue(configValue);
			if(endIndex+1>=lineString.length()) {
				return;
			}
			String lineString2 = lineString.substring(endIndex+1);
			populate(lineString2);
		}
	}
	
	public boolean hasConfigValues() {
		return configSet.size()>0;
	}

	
	public static String replace(String text, String key, String value) {
		int beginIndex = text.indexOf("${");
		int endIndex = text.indexOf("}");
		String newText = text;
		if(beginIndex!=-1 && endIndex!=-1 && endIndex > beginIndex) {
			String configValue = text.substring(beginIndex+2,endIndex);
			if(configValue.equals(key)) {
				newText = text.substring(0, beginIndex) + value;
			}
			else {
				newText = text.substring(0,endIndex+1);
			}
			if(endIndex+1>=text.length()) {
				return newText;
			}			
			String lineString2 = text.substring(endIndex+1);
			newText += replace(lineString2,key,value);
		}
		return newText;
	}
	

}
