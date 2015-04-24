package com.kangdainfo.tcbw.view.drgin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.set.ListOrderedSet;
import org.apache.commons.lang.StringUtils;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.Datetime;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Drg4004Db;
import com.kangdainfo.tcbw.model.bo.Drg4007Db;
import com.kangdainfo.tcbw.model.bo.Drg4008Db;
import com.kangdainfo.tcbw.model.bo.Drg4011Db;
import com.kangdainfo.tcbw.model.bo.Drg6001Db;


public class DRGIN0310F extends SuperBean{
	
	private String id;
	private String assessDesc;
	private String isClose;
	private String actionType;

	public String getId() {return checkGet(id);}
	public void setId(String s) {this.id = checkSet(s);}
	public String getAssessDesc() {return checkGet(assessDesc);}
	public void setAssessDesc(String s) {this.assessDesc = checkSet(s);}
	public String getIsClose() {return checkGet(isClose);}
	public void setIsClose(String s) {this.isClose = checkSet(s);}
	public String getActionType() {return checkGet(actionType);}
	public void setActionType(String s) {this.actionType = checkSet(s);}
	
	
	@Override
	public Object doQueryOne() throws Exception {
		DRGIN0310F obj = this;
		if(null != getId() && !"".equals(getId())){
			Drg4007Db drg47 = (Drg4007Db) ServiceGetter.getInstance().getTcbwService().getObject(" from Drg4007Db where id = " + getId());
			if(null != drg47){
				obj.setAssessDesc(drg47.getAssessDesc());
				obj.setIsClose(drg47.getIsClose());
			}
		}
		return obj;
	}

	@Override
	public Object doQueryAll() throws Exception {		
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		Map<Long,Drg4008Db> drg48Map = new HashMap<Long,Drg4008Db>();
		
		String hql = " from Drg4011Db where 1 = 1 ";
		if(null != getId() && !"".equals(getId())){
			hql += " and drg4007Db.id = " + Common.sqlChar(getId());
		}
		java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(hql);
		this.processCurrentPageAttribute(objList!=null?objList.size():0);
		if (getTotalRecord() > 0) 
		{
			if (getState().indexOf("query")<0) 
				ServiceGetter.getInstance().getTcbwService().getHibernateTemplate().clear();
			
			if(objList!=null && objList.size()>0)
			{
				for(Object dtlObj : objList)
				{
					Drg4011Db dtl = (Drg4011Db)dtlObj;
					String[] rowArray = new String[7];
					rowArray[0] = Common.get(dtl.getId());
					rowArray[1] = Common.get(dtl.getDrg4001Db().getApplNo());
					rowArray[2] = Common.get(dtl.getDrg4001Db().getNotifierRepDate());
					rowArray[3] = "";
					rowArray[4] = "";
					rowArray[5] = "";
					rowArray[6] = "";
					Drg4004Db drg04 = (Drg4004Db) View.getObject(" from Drg4004Db where applNo=" + Common.sqlChar(dtl.getApplNo()));
					if(null != drg04){
						if("Y".equals(drg04.getMedNti())){
							rowArray[3] = "NTI Drugs";
						}
						if(null != drg04.getMedAtcCode() && !"".equals(drg04.getMedAtcCode())){
							rowArray[3] += "藥理治療分類(ATC code)："+drg04.getMedAtcCode();
						}

						if(StringUtils.contains(drg04.getConSequence(), "1")) {
							rowArray[4] += "藥效改變";
						}
						if(StringUtils.contains(drg04.getConSequence(), "2")) {
							rowArray[4] += "不良反應發生、強度增強或頻率增加";
							rowArray[5] =  Common.get(View.getCommonCodeName("DRG0308", drg04.getBadReactionLev()));
						}
						rowArray[6] = Common.get(View.getCommonCodeName("DRG2RKL", drg04.getAssessResult()));
					}
					arrList.add(rowArray);
				}
			}
		}
		return arrList;
	}

	@Override
	public void doCreate() throws Exception {
		
	}

	@Override
	public void doUpdate() throws Exception {
		List updList = new ArrayList();
		Drg4007Db drg47 = null;
		if(null != getId() && !"".equals(getId())){
			drg47 = (Drg4007Db) ServiceGetter.getInstance().getTcbwService().getObject(" from Drg4007Db where id = " + getId());
		}
		drg47.setAssessDate(Datetime.getYYYMMDD());
		drg47.setAssessMan(getUserID());
		drg47.setAssessDesc(getAssessDesc());
		drg47.setModifier(getUserID());
		drg47.setModifyDate(Datetime.getYYYMMDD());
		drg47.setModifyTime(Datetime.getHHMMSS());
		if("2".equals(getActionType())){
			drg47.setIsClose("Y");
			if(null != drg47.getDrg4011Dbs() && !drg47.getDrg4011Dbs().isEmpty()){
				java.util.Set dtlSet = new ListOrderedSet();
				for(Object dtlObj:drg47.getDrg4011Dbs()){
					Drg4011Db drg41 = (Drg4011Db)dtlObj;
					if(null != drg41.getDrg4001Db()){
						drg41.getDrg4001Db().setStatus("90");
						drg41.getDrg4001Db().setChargeMan(null);
						//歷程紀錄
						ServiceGetter.getInstance().getTcbwService().getConinDao().saveCon2001Db("DRG2",drg41.getDrg4001Db().getId(), drg41.getDrg4001Db().getApplNo(),"90", "複評作業 - 結案", getUserID());
					}
					dtlSet.add(drg41);
					if(null !=drg41.getDrg4001Db()){
						//同步更新外部案件狀態
						Drg6001Db drg61 = (Drg6001Db)ServiceGetter.getInstance().getTcbwService().getObject(" from Drg6001Db where drg4001Id = " + drg41.getDrg4001Db().getId()+" order by revision desc ");
						if(null != drg61){
							drg61.setStatus(drg41.getDrg4001Db().getStatus());
							updList.add(drg61);
						}
					}
				}
				drg47.setDrg4011Dbs(dtlSet);
			}
		}
		ServiceGetter.getInstance().getCommonService().update(drg47);
		
		if(null != updList && !updList.isEmpty()){
			ServiceGetter.getInstance().getCommonService().updateBatch(updList);
		}
		if("2".equals(getActionType())){
			this.setErrorMsg("評估完成");
		}else{
			this.setErrorMsg("修改完成");
		}
	}

	@Override
	public void doDelete() throws Exception {}
}
