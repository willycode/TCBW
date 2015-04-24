package com.kangdainfo.tcbw.view.medin;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Med0001Db;
import com.kangdainfo.tcbw.model.bo.Med0006Db;
import com.kangdainfo.tcbw.util.TCBWCommon;


public class MEDIN0901F extends MEDIN0101F
{
	private String id;//序號	NUMERIC(19,0)
	private String reason;//再評估理由
	
	//將案件流程改為轉送評估作業：不良反應為轉送評估作業；不良品若是A/B級案件為轉送複評作業、若為C級案件為轉送初評作業
	public void doReAssessment() throws Exception 
	{
		Med0001Db med0001Db = (Med0001Db)View.getObject(" from Med0001Db where applNo= " + Common.sqlChar(getApplNo()));
		if(null!=med0001Db) {
			//新增一筆轉送評估檔
			Med0006Db med0006Db=new Med0006Db();
			Med0001Db master =new Med0001Db();
			master.setId(Common.getLong(med0001Db.getId()));
			med0006Db.setMed0001Db(master);
			
		    ServiceGetter.getInstance().getTcbwService().save(med0006Db);
			if("1".equals(med0001Db.getEventKind())) {
				med0001Db.setStatus("40");
				ServiceGetter.getInstance().getTcbwService().update(med0001Db);
				ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("MED01",med0001Db.getId(), med0001Db.getApplNo(),med0001Db.getStatus(),getReason(), getUserID());
			} else if("2".equals(med0001Db.getEventKind()) && ("1".equals(med0001Db.getEventClass()) || ("2".equals(med0001Db.getEventClass())))) {
				med0001Db.setStatus("60");
				ServiceGetter.getInstance().getTcbwService().update(med0001Db);
				ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("MED02",med0001Db.getId(), med0001Db.getApplNo(),med0001Db.getStatus(),getReason(), getUserID());
			} else if("2".equals(med0001Db.getEventKind()) && ("3".equals(med0001Db.getEventClass()))) {
				med0001Db.setStatus("40");
				ServiceGetter.getInstance().getTcbwService().update(med0001Db);
				ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("MED02",med0001Db.getId(), med0001Db.getApplNo(),med0001Db.getStatus(),getReason(), getUserID());
			} 
		}
	}


	@Override
	public Object doQueryOne() throws Exception {
		return null;
	}

	@Override
	public Object doQueryAll() throws Exception 
	{		
		return null;
	}

	@Override
	public void doCreate() throws Exception {
		
	}

	@Override
	public void doUpdate() throws Exception {
		
	}

	@Override
	public void doDelete() throws Exception {
		
	}

	public String getId() {
		return checkGet(id);
	}

	public void setId(String id) {
		this.id = checkSet(id);
	}
	
	public String getReason() {
		return checkGet(reason);
	}

	public void setReason(String reason) {
		this.reason = checkSet(reason);
	}


}
