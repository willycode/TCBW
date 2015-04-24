package com.kangdainfo.util;

import org.apache.log4j.Logger;
import org.apache.log4j.MDC;

public class Profiling {

	private static Logger logger = Logger.getLogger(Profiling.class);
	
	public static long markCheckPointByGroup(Object group) {
		
		String className = Profiling.class.getName() + "_" + group;
		Long time1 = (Long) MDC.get(className);
		
		Long time2 = System.currentTimeMillis();
		MDC.put(className, time2);
		
		if(time1==null) {
			return 0;
		}
		else {
			return time2.longValue()-time1.longValue();
		}
	}
	public static long markCheckPointByGroup(String message, Object group) {	
		long duration = markCheckPointByGroup(group);
		logger.info("["+Thread.currentThread().getId()+ "] [" + duration + "] : " + message);
		return duration;
	}
	public static long markCheckPoint(String message) {
		return markCheckPointByGroup(message,null);
	}
	
}
