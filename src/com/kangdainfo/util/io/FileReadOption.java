package com.kangdainfo.util.io;

public class FileReadOption {

	private Boolean isConfigurationFile;
	private Boolean isIgnoreHashLine;
	private Boolean isLoadByClasspath;
	
	public Boolean getIsConfigurationFile() {
		return isConfigurationFile;
	}
	public void setIsConfigurationFile(Boolean isConfigurationFile) {
		this.isConfigurationFile = isConfigurationFile;
	}
	public Boolean getIsIgnoreHashLine() {
		return isIgnoreHashLine;
	}
	public void setIsIgnoreHashLine(Boolean isIgnoreHashLine) {
		this.isIgnoreHashLine = isIgnoreHashLine;
	}
	public Boolean getIsLoadByClasspath() {
		return isLoadByClasspath;
	}
	public void setIsLoadByClasspath(Boolean isLoadByClasspath) {
		this.isLoadByClasspath = isLoadByClasspath;
	}
	
	
}
