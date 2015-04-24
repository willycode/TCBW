package com.kangdainfo.common.model.bo;


import java.util.Set;

public class CommonSystem {

	private int id;
	private String code;
	private String name;
	private String defaultPage;
	private String simpleCode;
	private Set subSystems;

	public int getId() { return id; }
	public void setId(int id) { this.id = id; }
	public String getCode() { return code; }
	public void setCode(String code) { this.code = code; }
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	public String getDefaultPage() { return defaultPage; }
	public void setDefaultPage(String defaultPage) { this.defaultPage = defaultPage; }
	public Set getSubSystems() { return subSystems; }
	public void setSubSystems(Set subSystems) { this.subSystems = subSystems; }
	
	public String getSimpleCode() {
		if (simpleCode != null) {
			return simpleCode;
		} else {
			return code.replaceAll("tast\\.", "");
		}
	}

	public void setSimpleCode(String simpleCode) {
		this.simpleCode = simpleCode;
	}

}