package com.kangdainfo.tcbw.model.dao;

public interface ScheduleDao {
	
	public String getScheduleName();
	public void setScheduleName(String scheduleName);
	
	public void parseScheduleAndSaveLog();
	
	/**
	 * 執行排程
	 */
	public com.kangdainfo.tcbw.model.bo.MsgObject executeSchedule() throws Exception;	
}
