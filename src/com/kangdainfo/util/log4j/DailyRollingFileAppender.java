package com.kangdainfo.util.log4j;

import org.apache.log4j.Layout;

import java.io.IOException;
import java.io.File;

public class DailyRollingFileAppender extends org.apache.log4j.DailyRollingFileAppender {

	public DailyRollingFileAppender() {
		super();
	}
	public DailyRollingFileAppender(Layout layout, String filename, String datePattern) throws IOException {
		super(layout,filename,datePattern);
	}
	
	public void setFile(String fileName) {
		super.setFile(fileName);
		File file = new File(fileName);
		File parent = file.getParentFile();
		if(parent!=null) {
			parent.mkdirs();
		}
	}
	
}
