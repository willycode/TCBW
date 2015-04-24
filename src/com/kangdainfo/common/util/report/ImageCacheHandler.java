package com.kangdainfo.common.util.report;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public class ImageCacheHandler {

	
	static Timer timeOutTimer = new Timer();
	
	TimeOutTask task;
	long timeout;
	
	HashMap<String,byte[]> imageMap;
	public ImageCacheHandler(HashMap<String,byte[]> imageMap){
		this(imageMap,60000);
	}
	
	public ImageCacheHandler(HashMap<String,byte[]> imageMap,long timeout){
		this.imageMap = imageMap;
		task = new TimeOutTask();
		this.timeout = timeout;
		timeOutTimer.schedule(task,timeout);
	}
	
	
	public HashMap<String,byte[]> getImageMap(){
		return imageMap;
	}

	
	public void clearNow(){
		task.cancel();
		task.doClear();
	}
	
	class TimeOutTask extends TimerTask{
		
		boolean isFinish = false;
		
		public void run(){
			doClear();
		}
		
		public void doClear(){
			if(isFinish) return;
			synchronized(imageMap){
				imageMap.clear();
				isFinish = true;
			}
		}
	}
	
	
	

}
