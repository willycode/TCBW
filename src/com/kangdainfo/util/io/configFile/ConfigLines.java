package com.kangdainfo.util.io.configFile;

import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;


public class ConfigLines {

	private Map configLineMap;
	private Set configSet;
	
	public ConfigLines() {
		this.configLineMap = new HashMap();
		this.configSet = new HashSet();
	}
	
	public void addConfigLine(ConfigLine configLine) {
		this.configSet.addAll(configLine.getConfigSet());
		this.configLineMap.put(configLine.getKey(),configLine);
	}
	public ConfigLine getConfigLine(String key) {
		return (ConfigLine) configLineMap.get(key);
	}
	
	public void removeConfigLine(String key) {
		this.configLineMap.remove(key);
	}
	public ConfigLine[] getConfigLineArray() {
		return (ConfigLine[]) configLineMap.values().toArray(new ConfigLine[configLineMap.size()]);
	}
	
	public String[] getConfigValues() {
		return (String[]) this.configSet.toArray(new String[configSet.size()]);
	}

	public void addConfigValue(String configValue) {
		this.configSet.add(configValue);
	}
	public void removeConfigValue(String configValue) {
		this.configSet.remove(configValue);
		ConfigLine configLines[] = getConfigLineArray();
		for(int i=0; i < configLines.length; i++) {
			configLines[i].getConfigSet().remove(configValue);
		}
	}

	
	public boolean hasConfigValue(String configValue) {
		return this.configSet.contains(configValue);
	}

	public Set getConfigSet() { return configSet; }
	public void setConfigSet(Set configSet) { this.configSet = configSet; }
	
}
