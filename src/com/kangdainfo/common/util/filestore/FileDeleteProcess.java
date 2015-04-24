package com.kangdainfo.common.util.filestore;

import java.io.File;
import java.util.*;

import org.apache.log4j.Logger;

import com.kangdainfo.common.util.Common;

public class FileDeleteProcess {

	static Logger logger = Logger.getLogger(FileDeleteProcess.class);

	static Timer mainTimer = new Timer();

	static Hashtable<String, TimerTask> taskTable = new Hashtable<String, TimerTask>();

	static public void scheduleDeleteFile(File file, long afterms) {

		String taskIndex = file.getAbsolutePath();

		TimerTask task = (TimerTask) taskTable.get(taskIndex);
		if (task != null) {
			task.cancel();
		}
		task = new DeleteFileTimerTask(file);
		taskTable.put(taskIndex, task);
		mainTimer.schedule(task, afterms);
		logger.debug("scheuld delete file:" + file.getAbsolutePath()
				+ " after " + afterms + " ms");
	}

	static public void scheduleDeleteFile(File file, Date date) {

		String taskIndex = file.getAbsolutePath();

		TimerTask task = (TimerTask) taskTable.get(taskIndex);
		if (task != null) {
			task.cancel();
		}

		task = new DeleteFileTimerTask(file);
		taskTable.put(taskIndex, task);
		mainTimer.schedule(task, date);

		logger.debug("scheuld delete file:" + file.getAbsolutePath() + " at "
				+ date);
	}

	/*
	 * 取得下一個整小時時間
	 */
	static public Date getNextHourDate(int nexthour) {

		Calendar cal = GregorianCalendar.getInstance();

		int hour = cal.get(Calendar.HOUR);
		if (nexthour <= hour) {
			cal.add(Calendar.DATE, 1);
		}
		cal.set(Calendar.HOUR, nexthour);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		return cal.getTime();
	}

	static public boolean cancelFileDelete(File file) {
		String taskIndex = file.getAbsolutePath();
		TimerTask task = (TimerTask) taskTable.get(taskIndex);
		if (task != null) {
			return task.cancel();
		}
		return false;
	}

	static class DeleteFileTimerTask extends TimerTask {

		File targetFile;

		DeleteFileTimerTask(File file) {
			targetFile = file;
		}
        public void run() {
            logger.debug("do delete file:"+targetFile.getAbsolutePath());            
            if(targetFile.exists()){
            	
            	if(targetFile.isDirectory()){
            		Common.RemoveDirectory(targetFile);
            	}else{
	                if(!targetFile.delete()){
	                    targetFile.deleteOnExit();
	                }else{
	                    logger.debug("do delete file:"+targetFile.getAbsolutePath()+" success.");                    
	                }
            	}
            }
            taskTable.remove(targetFile.getAbsolutePath());
        }
	}

}
