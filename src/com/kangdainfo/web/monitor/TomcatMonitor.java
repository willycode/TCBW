package com.kangdainfo.web.monitor;

import java.io.File;

import org.apache.log4j.Logger;

import com.kangdainfo.util.lang.runtime.Exec;

public class TomcatMonitor implements Runnable {

	protected Logger logger = Logger.getLogger(this.getClass());
	
	public void run() {
		
		Exec exec = Exec.getInstance();
		
		logger.info("going to shutdown tomcat...");
		
		doAction(false,"SHUTDOWN");
		
		try {
			Thread.sleep(10*1000);
		} catch (InterruptedException e) {
			logger.error(e);
		}
		
		exec.destroy("SHUTDOWN");
		
		
		logger.info("going to startup tomcat...");
		
		doAction(true,"STARTUP");

	}

	
	public void doAction(boolean doStartup, String processName) {		
		try {
			Exec exec = Exec.getInstance();
			String tomcat_home = "C:/app/tomcat5-8080/apache-tomcat-5.5.20-tast/";
			String command = "catalina.bat ";
			//String command = "";
			if(doStartup) {
				command += "run";
			}
			else {
				command += "stop";
			}
			logger.info(command);
			exec.run(processName,new File(tomcat_home + "bin/"), command, true);
		} catch (Exception e) {
			logger.error("",e);
		}
	}
	
}
