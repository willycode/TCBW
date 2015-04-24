package com.kangdainfo.tcbw.model.dao.hibernate;

import org.apache.log4j.Logger;

import com.kangdainfo.tcbw.model.dao.ScheduleDao;

public abstract class AbstractScheduleDaoImpl implements ScheduleDao {

	protected Logger logger = Logger.getLogger(this.getClass());
	private String scheduleName;
	public String getScheduleName() {
		return scheduleName;
	}

	public void setScheduleName(String scheduleName) {
		this.scheduleName = scheduleName;
	}
	
	public void parseScheduleAndSaveLog() {
		try {
			executeSchedule();
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	public abstract com.kangdainfo.tcbw.model.bo.MsgObject executeSchedule() throws Exception;	
}
