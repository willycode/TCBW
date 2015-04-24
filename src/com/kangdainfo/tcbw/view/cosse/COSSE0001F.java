package com.kangdainfo.tcbw.view.cosse;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Cos1001Db;
import com.kangdainfo.tcbw.util.TCBWCommon;

public class COSSE0001F extends SuperBean{

	private String id;
	private String dpdKindB;
	private String dpdKindS;
	private String dpdKindName;
	private String isStop;
	
	private String q_isQuery;
	private String q_id;
	private String q_dpdKindB;
	private String q_dpdKindS;
	private String q_dpdKindName;
	private String q_isStop;	
	
	public String getId() {
		return checkGet(id);
	}

	public void setId(String id) {
		this.id = checkSet(id);
	}

	public String getDpdKindB() {
		return checkGet(dpdKindB);
	}

	public void setDpdKindB(String dpdKindB) {
		this.dpdKindB = checkSet(dpdKindB);
	}

	public String getDpdKindS() {
		return checkGet(dpdKindS);
	}

	public void setDpdKindS(String dpdKindS) {
		this.dpdKindS = checkSet(dpdKindS);
	}

	public String getDpdKindName() {
		return checkGet(dpdKindName);
	}

	public void setDpdKindName(String dpdKindName) {
		this.dpdKindName = checkSet(dpdKindName);
	}

	public String getIsStop() {
		return checkGet(isStop);
	}

	public void setIsStop(String isStop) {
		this.isStop = checkSet(isStop);
	}

	public String getQ_isQuery() {
		return checkGet(q_isQuery);
	}

	public void setQ_isQuery(String qIsQuery) {
		q_isQuery = checkSet(qIsQuery);
	}

	public String getQ_id() {
		return checkGet(q_id);
	}

	public void setQ_id(String qId) {
		q_id = checkSet(qId);
	}

	public String getQ_dpdKindB() {
		return checkGet(q_dpdKindB);
	}

	public void setQ_dpdKindB(String qDpdKindB) {
		q_dpdKindB = checkSet(qDpdKindB);
	}

	public String getQ_dpdKindS() {
		return checkGet(q_dpdKindS);
	}

	public void setQ_dpdKindS(String qDpdKindS) {
		q_dpdKindS = checkSet(qDpdKindS);
	}

	public String getQ_dpdKindName() {
		return checkGet(q_dpdKindName);
	}

	public void setQ_dpdKindName(String qDpdKindName) {
		q_dpdKindName = checkSet(qDpdKindName);
	}

	public String getQ_isStop() {
		return checkGet(q_isStop);
	}

	public void setQ_isStop(String qIsStop) {
		q_isStop = checkSet(qIsStop);
	}

	@Override
	public Object doQueryOne() throws Exception {
		COSSE0001F obj = this;
		Cos1001Db c = (Cos1001Db)View.getObject("from Cos1001Db where id = " + Common.getLong(getId()));		
		if(c != null){
			obj.setId(Common.get(c.getId()));
			obj.setDpdKindB(c.getCdpCode());
			obj.setDpdKindS(c.getDpdKind());
			obj.setDpdKindName(c.getDpdKindName());
			obj.setIsStop(c.getIsStop());
			obj.setEditID(c.getModifier());
			obj.setEditDate(c.getModifyDate());		
		}
		return obj;
	}

	@Override
	public Object doQueryAll() throws Exception {
		if (!"".equals(getQ_isQuery())){
			setQ_id("");
		}
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		String hql = " from Cos1001Db where 1 = 1 ";
		if(!"".equals(getQ_id())){
			hql += " and id = " + Common.getLong(getQ_id());
		}else{
			if(!"".equals(getQ_dpdKindB()))
				hql += " and cdpCode = " + Common.sqlChar(getQ_dpdKindB());
			if(!"".equals(getQ_dpdKindS()))
				hql += " and dpdKind = " + Common.sqlChar(getQ_dpdKindS());
			if(!"".equals(getQ_dpdKindName()))
				hql += " and dpdKindName like " + Common.sqlChar("%" + getQ_dpdKindName() + "%");
			if(!"".equals(getQ_isStop()))
				hql += " and isStop = " + Common.sqlChar(getQ_isStop());
		}
		System.out.println("HQL: " + hql);
		
		this.processCurrentPageAttribute(ServiceGetter.getInstance().getTcbwService().loadCount(hql));
		if (getTotalRecord() > 0){
			if (getState().indexOf("query")<0) 
				ServiceGetter.getInstance().getCommonService().getHibernateTemplate().clear();
			java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(hql + " order by id", this.getRecordStart(), this.getPageSize());
			if (objList != null && objList.size() > 0) {
				java.util.Map<String, String> dpdKindMap = TCBWCommon.getCommonCodeMap("COSDPD");
				
				for(Object dtlObj : objList) {				
					Cos1001Db dtl = (Cos1001Db)dtlObj;
					String[] rowArray = new String[5];
					rowArray[0] = Common.get(dtl.getId());											
					rowArray[1] = Common.get(dpdKindMap.get(dtl.getCdpCode()));
					rowArray[2] = Common.get(dtl.getDpdKind());
					rowArray[3] = Common.get(dtl.getDpdKindName());		
					rowArray[4] = Common.get(dtl.getIsStop()).equals("Y")?"是":Common.get(dtl.getIsStop()).equals("N")?"否":"";
					arrList.add(rowArray);
				}
				objList.clear();
				dpdKindMap.clear();
			}
		
		}
		return arrList;
	}
	
	protected String[][] getInsertCheckSQL(){	
		String[][] checkSQLArray = new String[1][4];
	 	checkSQLArray[0][0] = "select count(*) from Cos1001Db where cdpCode = " + Common.sqlChar(getDpdKindB()) + " and dpdKind = " + Common.sqlChar(getDpdKindS());
		checkSQLArray[0][1] = ">";
		checkSQLArray[0][2] = "0";
		checkSQLArray[0][3] = "該不良品缺陷代碼已存在，請重新輸入！";
		return checkSQLArray;
	}

	@Override
	public void doCreate() throws Exception {
		Cos1001Db obj = new Cos1001Db();
		obj.setCdpCode(getDpdKindB());
	    obj.setDpdKind(getDpdKindS());
	    obj.setDpdKindName(getDpdKindName());
	    obj.setIsStop(getIsStop());
	    obj.setCreator(getEditID());
	    obj.setCreateDate(getEditDate());
	    obj.setCreateTime(getEditTime());
	    obj.setModifier(getEditID());
	    obj.setModifyDate(getEditDate());
	    obj.setModifyTime(getEditTime());
		ServiceGetter.getInstance().getTcbwService().save(obj);
		setId(Common.get(obj.getId()));
	}
	
	protected String[][] getUpdateCheckSQL(){	
		String[][] checkSQLArray = new String[1][4];	 	
		checkSQLArray[0][0] = "select count(*) from Cos1001Db where cdpCode = " + Common.sqlChar(getDpdKindB()) + " and dpdKind = " + Common.sqlChar(getDpdKindS()) + " and id != " + Common.getLong(getId()); 	
	 	checkSQLArray[0][1] = ">";
		checkSQLArray[0][2] = "0";
		checkSQLArray[0][3] = "該不良品缺陷代碼已存在，請重新輸入！";	
		return checkSQLArray;		
	}

	@Override
	public void doUpdate() throws Exception {
		Cos1001Db obj = (Cos1001Db)View.getObject(" from Cos1001Db where id = " + Common.getLong(getId()));
		if(obj != null){	
			obj.setCdpCode(getDpdKindB());
		    obj.setDpdKind(getDpdKindS());
		    obj.setDpdKindName(getDpdKindName());
		    obj.setIsStop(getIsStop());
		    obj.setModifier(getEditID());
		    obj.setModifyDate(getEditDate());
		    obj.setModifyTime(getEditTime());
			ServiceGetter.getInstance().getTcbwService().update(obj);
			setId(Common.get(obj.getId()));
		}
	}

	@Override
	public void doDelete() throws Exception {
		Cos1001Db obj = (Cos1001Db)View.getObject(" from Cos1001Db where id = " + Common.getLong(getId()));
		if(obj != null){
			ServiceGetter.getInstance().getTcbwService().delete(obj);
			setId("");
		}else{
			throw new Exception("查無資料，無法刪除，請重新操作 !");
		}
	}

}
