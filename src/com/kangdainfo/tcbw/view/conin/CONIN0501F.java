
package com.kangdainfo.tcbw.view.conin;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Con1007Db;
import com.kangdainfo.tcbw.model.bo.Con1014Db;
import com.kangdainfo.tcbw.util.TCBWCommon;

public class CONIN0501F extends SuperBean{

	private String id;
	private String sysKind;		//系統別
	private String con1007_id;	//通報表單名稱
	private String code;	//流程角色代碼
	private String name;	//流程角色名稱
	private String isStop;
	
	private String q_isQuery;
	private String q_id;
	private String q_sysKind;	//系統別
	private String q_con1007_id;
	private String q_code;		//流程角色代碼
	private String q_name; 		//流程角色名稱
	private String q_isStop;	
	
	public String getId() {
		return checkGet(id);
	}

	public void setId(String id) {
		this.id = checkSet(id);
	}

	public String getSysKind() {
		return checkGet(sysKind);
	}

	public void setSysKind(String sysKind) {
		this.sysKind = checkSet(sysKind);
	}

	public String getCon1007_id() {
		return checkGet(con1007_id);
	}

	public void setCon1007_id(String con1007_id) {
		this.con1007_id = checkSet(con1007_id);
	}
	
	public String getCode() {
		return checkGet(code);
	}

	public void setCode(String code) {
		this.code = checkSet(code);
	}
	
	public String getName() {
		return checkGet(name);
	}

	public void setName(String name) {
		this.name = checkSet(name);
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
	
	public String getQ_con1007_id() {
		return checkGet(q_con1007_id);
	}

	public void setQ_con1007_id(String qCon1007Id) {
		q_con1007_id = checkSet(qCon1007Id);
	}
	
	public String getQ_sysKind() {
		return checkGet(q_sysKind);
	}

	public void setQ_sysKind(String qSysKind) {
		q_sysKind = checkSet(qSysKind);
	}
	
	public String getQ_code() {
		return checkGet(q_code);
	}

	public void setQ_code(String qCode) {
		q_code = checkSet(qCode);
	}
	
	public String getQ_name() {
		return checkGet(q_name);
	}

	public void setQ_name(String qName) {
		q_name = checkSet(qName);
	}

	public String getQ_isStop() {
		return checkGet(q_isStop);
	}

	public void setQ_isStop(String qIsStop) {
		q_isStop = checkSet(qIsStop);
	}

	@Override
	public Object doQueryOne() throws Exception {
		CONIN0501F obj = this;
		Con1014Db c = (Con1014Db)View.getObject("from Con1014Db where id = " + Common.getLong(getId()));		
		if(c != null){
			obj.setId(Common.get(c.getId()));
			obj.setSysKind(c.getSysKind());
			obj.setCon1007_id(Common.get(c.getCon1007Db().getId()));
			obj.setCode(c.getCode()); //流程角色代碼
			obj.setName(c.getName()); //流程角色名稱
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
		String hql = " from Con1014Db where 1 = 1 ";
		if(!"".equals(getQ_id())){
			hql += " and id = " + Common.getLong(getQ_id());
		}else{
			if(!"".equals(getQ_sysKind()))
				hql += " and sysKind = " + Common.sqlChar(getQ_sysKind());
			if(!"".equals(getQ_con1007_id()))
				hql += " and con1007Db.id = " + Common.sqlChar(getQ_con1007_id());
			if(!"".equals(getQ_code()))
				hql += " and code = " + Common.sqlChar(getQ_code());
			if(!"".equals(getQ_name()))
				hql += " and name like " + Common.sqlChar("%" + getQ_name() + "%");
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
				java.util.Map<String, String> sysKindMap = TCBWCommon.getCommonCodeMap("SYS");
				
				for(Object dtlObj : objList) {				
					Con1014Db dtl = (Con1014Db)dtlObj;
					String[] rowArray = new String[6];
					rowArray[0] = Common.get(dtl.getId());											
					rowArray[1] = Common.get(sysKindMap.get(dtl.getSysKind()));
					rowArray[2] = Common.get(dtl.getCon1007Db().getFormdName());
					rowArray[3] = Common.get(dtl.getCode());
					rowArray[4] = Common.get(dtl.getName());
					rowArray[5] = Common.get(dtl.getIsStop()).equals("Y")?"是":Common.get(dtl.getIsStop()).equals("N")?"否":"";
					arrList.add(rowArray);
				}
				objList.clear();
				sysKindMap.clear();
			}
		
		}
		return arrList;
	}
	
	protected String[][] getInsertCheckSQL(){	
		String[][] checkSQLArray = new String[1][4];
	 	checkSQLArray[0][0] = "select count(*) from Con1014Db where code = " + Common.sqlChar(getCode()) + "and con1007_id = " + Common.sqlChar(getCon1007_id());
		checkSQLArray[0][1] = ">";
		checkSQLArray[0][2] = "0";
		checkSQLArray[0][3] = "該流程角色代碼已存在，請重新輸入！";
		return checkSQLArray;
	}

	@Override
	public void doCreate() throws Exception {
		Con1014Db obj = new Con1014Db();
	    obj.setSysKind(getSysKind());
	    
		Con1007Db con1007db = new Con1007Db();
	    con1007db.setId(Common.getLong(getCon1007_id()));
	    obj.setCon1007Db(con1007db);
	    
	    obj.setCode(code); //流程角色代碼
	    obj.setName(name); //流程角色名稱
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
		checkSQLArray[0][0] = "select count(*) from Con1014Db where code = " + Common.sqlChar(getCode()) + " and id != " + Common.getLong(getId()) + "and con1007_id = " + Common.sqlChar(getCon1007_id()); 	
	 	checkSQLArray[0][1] = ">";
		checkSQLArray[0][2] = "0";
		checkSQLArray[0][3] = "該流程角色代碼已存在，請重新輸入！";	
		return checkSQLArray;		
	}

	@Override
	public void doUpdate() throws Exception {
		Con1014Db obj = (Con1014Db)View.getObject(" from Con1014Db where id = " + Common.getLong(getId()));
		if(obj != null){		
		    obj.setSysKind(getSysKind());
		    
			Con1007Db con1007db = new Con1007Db();
		    con1007db.setId(Common.getLong(getCon1007_id()));
		    obj.setCon1007Db(con1007db);
		    
		    obj.setCode(getCode()); //流程角色代碼
		    obj.setName(getName()); //流程角色名稱
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
		Con1014Db obj = (Con1014Db)View.getObject(" from Con1014Db where id = " + Common.getLong(getId()));
		if(obj != null){
			ServiceGetter.getInstance().getTcbwService().delete(obj);
			setId("");
		}else{
			throw new Exception("查無資料，無法刪除，請重新操作 !");
		}
	}

}
