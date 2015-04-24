package com.kangdainfo.web.monitor;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class StandaloneMonitorCallee {

	protected Logger logger = Logger.getLogger(this.getClass());
	
	public static ApplicationContext applicationContext;
	public void run() {
		
		applicationContext = new ClassPathXmlApplicationContext("applicationContext-standalone-monitor.xml");	
		logger.info(StandaloneMonitorCallee.class.getName() + " application context started!");
		while(true) {
			try {
				Thread.sleep(60*60*1000);
			} catch (InterruptedException e) {
				logger.error("",e);
			}
		}
	}
	public static void main(String args[]) {
		StandaloneMonitorCallee standaloneMonitorCallee = new StandaloneMonitorCallee();
		standaloneMonitorCallee.run();
	}
	
}
