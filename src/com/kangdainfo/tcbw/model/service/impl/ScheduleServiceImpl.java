package com.kangdainfo.tcbw.model.service.impl;

import com.kangdainfo.tcbw.model.service.ScheduleService;

public class ScheduleServiceImpl implements ScheduleService {
	
	//藥品不良品廠商回覆稽催作業
	private com.kangdainfo.tcbw.model.dao.ScheduleDao drg0101ScheduleDao;

	public com.kangdainfo.tcbw.model.dao.ScheduleDao getDrg0101ScheduleDao() {
		return drg0101ScheduleDao;
	}
	public void setDrg0101ScheduleDao(com.kangdainfo.tcbw.model.dao.ScheduleDao drg0101ScheduleDao) {
		this.drg0101ScheduleDao = drg0101ScheduleDao;
	}
	
	//化粧品不良品廠商回覆稽催作業
	private com.kangdainfo.tcbw.model.dao.ScheduleDao cos0201ScheduleDao;

	public com.kangdainfo.tcbw.model.dao.ScheduleDao getCos0201ScheduleDao() {
		return cos0201ScheduleDao;
	}
	public void setCos0201ScheduleDao(com.kangdainfo.tcbw.model.dao.ScheduleDao cos0201ScheduleDao) {
		this.cos0201ScheduleDao = cos0201ScheduleDao;
	}
	
	//化粧品不良反應廠商回覆稽催作業
	private com.kangdainfo.tcbw.model.dao.ScheduleDao cos0301ScheduleDao;

	public com.kangdainfo.tcbw.model.dao.ScheduleDao getCos0301ScheduleDao() {
		return cos0301ScheduleDao;
	}
	public void setCos0301ScheduleDao(com.kangdainfo.tcbw.model.dao.ScheduleDao cos0301ScheduleDao) {
		this.cos0301ScheduleDao = cos0301ScheduleDao;
	}
	
	//醫療器材不良反應案件評估稽催作業
	private com.kangdainfo.tcbw.model.dao.ScheduleDao med0101ScheduleDao;

	public com.kangdainfo.tcbw.model.dao.ScheduleDao getMed0101ScheduleDao() {
		return med0101ScheduleDao;
	}
	public void setMed0101ScheduleDao(com.kangdainfo.tcbw.model.dao.ScheduleDao med0101ScheduleDao) {
		this.med0101ScheduleDao = med0101ScheduleDao;
	}
	
	//醫療器材不良品案件初評稽催作業
	private com.kangdainfo.tcbw.model.dao.ScheduleDao med0201ScheduleDao;

	public com.kangdainfo.tcbw.model.dao.ScheduleDao getMed0201ScheduleDao() {
		return med0201ScheduleDao;
	}
	public void setMed0201ScheduleDao(com.kangdainfo.tcbw.model.dao.ScheduleDao med0201ScheduleDao) {
		this.med0201ScheduleDao = med0201ScheduleDao;
	}
	
	//醫療器材不良品案件複評稽催作業
	private com.kangdainfo.tcbw.model.dao.ScheduleDao med0202ScheduleDao;

	public com.kangdainfo.tcbw.model.dao.ScheduleDao getMed0202ScheduleDao() {
		return med0202ScheduleDao;
	}
	public void setMed0202ScheduleDao(com.kangdainfo.tcbw.model.dao.ScheduleDao med0202ScheduleDao) {
		this.med0202ScheduleDao = med0202ScheduleDao;
	}
	
	//醫療器材不良品廠商回覆稽催作業
	private com.kangdainfo.tcbw.model.dao.ScheduleDao med0203ScheduleDao;

	public com.kangdainfo.tcbw.model.dao.ScheduleDao getMed0203ScheduleDao() {
		return med0203ScheduleDao;
	}
	public void setMed0203ScheduleDao(com.kangdainfo.tcbw.model.dao.ScheduleDao med0203ScheduleDao) {
		this.med0203ScheduleDao = med0203ScheduleDao;
	}
	
	//化粧品不良品轉知TFDA作業
	private com.kangdainfo.tcbw.model.dao.ScheduleDao cos0202ScheduleDao;

	public com.kangdainfo.tcbw.model.dao.ScheduleDao getCos0202ScheduleDao() {
		return cos0202ScheduleDao;
	}
	public void setCos0202ScheduleDao(com.kangdainfo.tcbw.model.dao.ScheduleDao cos0202ScheduleDao) {
		this.cos0202ScheduleDao = cos0202ScheduleDao;
	}
	
	//藥品通報案件統計作業
	private com.kangdainfo.tcbw.model.dao.ScheduleDao drg0102ScheduleDao;

	public com.kangdainfo.tcbw.model.dao.ScheduleDao getDrg0102ScheduleDao() {
		return drg0102ScheduleDao;
	}
	public void setDrg0102ScheduleDao(com.kangdainfo.tcbw.model.dao.ScheduleDao drg0102ScheduleDao) {
		this.drg0102ScheduleDao = drg0102ScheduleDao;
	}
	
	//醫療器材定期安全監視表報告繳交通知(到期前)作業
	private com.kangdainfo.tcbw.model.dao.ScheduleDao med0501ScheduleDao;

	public com.kangdainfo.tcbw.model.dao.ScheduleDao getMed0501ScheduleDao() {
		return med0501ScheduleDao;
	}
	public void setMed0501ScheduleDao(com.kangdainfo.tcbw.model.dao.ScheduleDao med0501ScheduleDao) {
		this.med0501ScheduleDao = med0501ScheduleDao;
	}
	
	//醫療器材定期安全監視表報告繳交通知(到期當日)作業
	private com.kangdainfo.tcbw.model.dao.ScheduleDao med0502ScheduleDao;

	public com.kangdainfo.tcbw.model.dao.ScheduleDao getMed0502ScheduleDao() {
		return med0502ScheduleDao;
	}
	public void setMed0502ScheduleDao(com.kangdainfo.tcbw.model.dao.ScheduleDao med0502ScheduleDao) {
		this.med0502ScheduleDao = med0502ScheduleDao;
	}
	
	//醫療器材定期安全監視表報告繳交通知(逾期後)作業
	private com.kangdainfo.tcbw.model.dao.ScheduleDao med0503ScheduleDao;

	public com.kangdainfo.tcbw.model.dao.ScheduleDao getMed0503ScheduleDao() {
		return med0503ScheduleDao;
	}
	public void setMed0503ScheduleDao(com.kangdainfo.tcbw.model.dao.ScheduleDao med0503ScheduleDao) {
		this.med0503ScheduleDao = med0503ScheduleDao;
	}
	
}
