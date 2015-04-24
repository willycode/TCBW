package com.kangdainfo.web.monitor;

import org.apache.log4j.Logger;

public class SystemShutdownServiceImpl implements SystemShutdownService {

	protected Logger logger = Logger.getLogger(this.getClass());
	
	public void shutdown() {
		
		logger.info("shutting down...");
		
		try {
			Thread.sleep(1*1000);
		} catch (InterruptedException e) {
			logger.error(e);
		}
		
		System.exit(0);
		
	}
}
