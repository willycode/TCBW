package com.kangdainfo.web.monitor;

import org.apache.log4j.Logger;

public class StandaloneMonitorServiceImpl implements StandaloneMonitorService {

	protected Logger logger = Logger.getLogger(this.getClass());
	
	public void restart_tomcat() {
		logger.info("spawning separate thread to restart tomcat......");
		
		Thread tomcatMonitorThread = new Thread(new TomcatMonitor());
		tomcatMonitorThread.start();
		
	}
	public boolean isAlive() {
		logger.info(StandaloneMonitorServiceImpl.class.getName() + " is alive");
		return true;
	}
	
	
}
