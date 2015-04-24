package com.kangdainfo.tcbw.view.conse;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Con1007Db;
import com.kangdainfo.tcbw.util.TCBWCommon;

public class CONSE0002F extends SuperBean{

	private String id;
	private String formCodeB;
	private String formCodeS;
	private String formdName;
	private String isStop;
	
	private String q_isQuery;
	private String q_id;
	private String q_formCodeB;
	private String q_formCodeS;
	private String q_formdName;
	private String q_isStop;	
	
	public String getId() {
		return checkGet(id);
	}

	public void setId(String id) {
		this.id = checkSet(id);
	}

	public String getFormCodeB() {
		return checkGet(formCodeB);
	}

	public void setFormCodeB(String formCodeB) {
		this.formCodeB = checkSet(formCodeB);
	}

	public String getFormCodeS() {
		return checkGet(formCodeS);
	}

	public void setFormCodeS(String formCodeS) {
		this.formCodeS = checkSet(formCodeS);
	}

	public String getFormdName() {
		return checkGet(formdName);
	}

	public void setFormdName(String formdName) {
		this.formdName = checkSet(formdName);
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

	public String getQ_formCodeB() {
		return checkGet(q_formCodeB);
	}

	public void setQ_formCodeB(String qFormCodeB) {
		q_formCodeB = checkSet(qFormCodeB);
	}

	public String getQ_formCodeS() {
		return checkGet(q_formCodeS);
	}

	public void setQ_formCodeS(String qFormCodeS) {
		q_formCodeS = checkSet(qFormCodeS);
	}

	public String getQ_formdName() {
		return checkGet(q_formdName);
	}

	public void setQ_formdName(String qFormdName) {
		q_formdName = checkSet(qFormdName);
	}

	public String getQ_isStop() {
		return checkGet(q_isStop);
	}

	public void setQ_isStop(String qIsStop) {
		q_isStop = checkSet(qIsStop);
	}

	@Override
	public Object doQueryOne() throws Exception {
		CONSE0002F obj = this;
		Con1007Db c = (Con1007Db)View.getObject("from Con1007Db where id = " + Common.getLong(getId()));		
		if(c != null){
			obj.setId(Common.get(c.getId()));
			obj.setFormCodeB(c.getFormCode().substring(0, 3));
			obj.setFormCodeS(c.getFormCode().substring(3));
			obj.setFormdName(c.getFormdName());
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
		String hql = " from Con1007Db where 1 = 1 ";
		if(!"".equals(getQ_id())){
			hql += " and id = " + Common.getLong(getQ_id());
		}else{
			if(!"".equals(getQ_formCodeB()))
				hql += " and substring(formCode,1,3) = " + Common.sqlChar( getQ_formCodeB());
			if(!"".equals(getQ_formCodeS()))
				hql += " and substring(formCode,4,2) = " + Common.sqlChar( getQ_formCodeS());
			if(!"".equals(getQ_formdName()))
				hql += " and formdName like " + Common.sqlChar("%" + getQ_formdName() + "%");
			if(!"".equals(getQ_isStop()))
				hql += " and isStop = " + Common.sqlChar(getQ_isStop());
		}
		this.processCurrentPageAttribute(ServiceGetter.getInstance().getTcbwService().loadCount(hql));
		if (getTotalRecord() > 0){
			if (getState().indexOf("query")<0) 
				ServiceGetter.getInstance().getCommonService().getHibernateTemplate().clear();
			java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(hql + " order by id", this.getRecordStart(), this.getPageSize());
			if (objList != null && objList.size() > 0) {
				//使用map先將資料撈出來
				java.util.Map<String, String> sysMap = TCBWCommon.getCommonCodeMap("SYS");
				
				for(Object dtlObj : objList) {				
					Con1007Db dtl = (Con1007Db)dtlObj;
					String[] rowArray = new String[5];
					rowArray[0] = Common.get(dtl.getId());											
					rowArray[1] = Common.get(sysMap.get(dtl.getFormCode().substring(0, 3)));
					rowArray[2] = Common.get(dtl.getFormCode().substring(3));
					rowArray[3] = Common.get(dtl.getFormdName());		
					rowArray[4] = Common.get(dtl.getIsStop()).equals("Y")?"是":Common.get(dtl.getIsStop()).equals("N")?"否":"";
					arrList.add(rowArray);
				}
				objList.clear();
			}
		
		}
		return arrList;
	}
	
	protected String[][] getInsertCheckSQL(){	
		String[][] checkSQLArray = new String[1][4];
	 	checkSQLArray[0][0] = "select count(*) from Con1007Db where formCode = " + Common.sqlChar(getFormCodeB() + getFormCodeS());
		checkSQLArray[0][1] = ">";
		checkSQLArray[0][2] = "0";
		checkSQLArray[0][3] = "該通報表單代碼已存在，請重新輸入！";
		return checkSQLArray;
	}

	@Override
	public void doCreate() throws Exception {
		Con1007Db obj = new Con1007Db();
	    obj.setFormCode(getFormCodeB() + getFormCodeS());
	    obj.setFormdName(getFormdName());
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
		checkSQLArray[0][0] = "select count(*) from Con1007Db where formCode = " + Common.sqlChar(getFormCodeB() + getFormCodeS()) + " and id != " + Common.getLong(getId()); 	
	 	checkSQLArray[0][1] = ">";
		checkSQLArray[0][2] = "0";
		checkSQLArray[0][3] = "該通報表單代碼已存在，請重新輸入！";	
		return checkSQLArray;		
	}

	@Override
	public void doUpdate() throws Exception {
		Con1007Db obj = (Con1007Db)View.getObject(" from Con1007Db where id = " + Common.getLong(getId()));
		if(obj != null){		
		    obj.setFormCode(getFormCodeB() + getFormCodeS());
		    obj.setFormdName(getFormdName());
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
		Con1007Db obj = (Con1007Db)View.getObject(" from Con1007Db where id = " + Common.getLong(getId()));
		System.out.println("ID: " + getId());
		if(obj != null){
			ServiceGetter.getInstance().getTcbwService().delete(obj);
			setId("");
		}else{
			throw new Exception("查無資料，無法刪除，請重新操作 !");
		}
	}

}
