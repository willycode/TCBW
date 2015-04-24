package com.kangdainfo.common.model.bo;


import org.apache.commons.lang.builder.ToStringBuilder;

public class SubSystem extends CommonSystem {

	public SubSystem() {
	}

	public SubSystem(int id, String code, String name, String defaultPage) {
		setId(id);
		setCode(code);
		setName(name);
		setDefaultPage(defaultPage);
	}

	public String toString() {
		ToStringBuilder tsb = new ToStringBuilder(this);
		tsb.append("id", getId());
		tsb.append("code", getCode());
		tsb.append("name", getName());
		return tsb.toString();
	}
}