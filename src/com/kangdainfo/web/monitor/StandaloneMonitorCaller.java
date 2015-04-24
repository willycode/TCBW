package com.kangdainfo.web.monitor;

import org.apache.log4j.Logger;

import com.kangdainfo.util.lang.runtime.Exec;
import com.kangdainfo.web.util.WebApplicationClasspath;

public class StandaloneMonitorCaller {

	protected Logger logger = Logger.getLogger(this.getClass());
	
	public StandaloneMonitorCaller() {
	}
	
	public void invokeCallee() {		
		try {
			String classpath = new WebApplicationClasspath().getClassPath();
			Exec exec = Exec.getInstance();
			String appName = StandaloneMonitorCallee.class.getName();
			String command = "java ";
			command += " -classpath " + classpath;
			command += " " + appName;
			exec.run(this.getClass().getName(),null, command, true);
		} catch (Exception e) {
			logger.error("",e);
		}
	}
	
}
