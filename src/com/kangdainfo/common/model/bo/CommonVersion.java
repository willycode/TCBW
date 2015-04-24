package com.kangdainfo.common.model.bo;

import java.io.Serializable;

public class CommonVersion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1052363511469287391L;
	
	private Integer id;
	private String versionDescription;
	private String bitYN;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}	
	public String getBitYN() {
		return bitYN;
	}
	public void setBitYN(String bitYN) {
		this.bitYN = bitYN;
	}
	public String getVersionDescription() {
		return versionDescription;
	}
	public void setVersionDescription(String versionDescription) {
		this.versionDescription = versionDescription;
	}
	
	

}
