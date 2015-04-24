package com.kangdainfo.tcbw.view.conin;

import org.apache.commons.lang.StringEscapeUtils;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.Datetime;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Con0003Db;

public class CONIN0601F extends SuperBean{

	private String id;
	private String systemType;
	private String formCode;
	private String dbID;
	private String applNo;
	private String userID;
	private String userName;
	private String ip;
	private String lockDate;
	private String lockTime;
	private String notifier;
	
	private String q_isQuery;
	private String q_id;
	private String q_systemType;
	private String q_formID;
	private String q_applNo;
	private String q_userID;
	private String q_userName;
	private String q_notifier;
	
	public String getId() {
		return checkGet(id);
	}

	public void setId(String id) {
		this.id = checkSet(id);
	}

	public String getSystemType() {
		return checkGet(systemType);
	}

	public void setSystemType(String systemType) {
		this.systemType = checkSet(systemType);
	}

	public String getFormCode() {
		return checkGet(formCode);
	}

	public void setFormCode(String formCode) {
		this.formCode = checkSet(formCode);
	}

	
	public String getDbID() {
		return checkGet(dbID);
	}

	public void setDbID(String dbID) {
		this.dbID = checkSet(dbID);
	}
	
	public String getApplNo() {
		return checkGet(applNo);
	}

	public void setApplNo(String applNo) {
		this.applNo = checkSet(applNo);
	}

	public String getUserID() {
		return checkGet(userID);
	}

	public void setUserID(String userID) {
		this.userID = checkSet(userID);
	}

	public String getUserName() {
		return checkGet(userName);
	}

	public void setUserName(String userName) {
		this.userName = checkSet(userName);
	}

	public String getIp() {
		return checkGet(ip);
	}

	public void setIp(String ip) {
		this.ip = checkSet(ip);
	}

	public String getLockDate() {
		return checkGet(lockDate);
	}

	public void setLockDate(String lockDate) {
		this.lockDate = checkSet(lockDate);
	}

	public String getLockTime() {
		return checkGet(lockTime);
	}

	public void setLockTime(String lockTime) {
		this.lockTime = checkSet(lockTime);
	}

	public String getNotifier() {
		return checkGet(notifier);
	}

	public void setNotifier(String notifier) {
		this.notifier = checkSet(notifier);
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

	public String getQ_systemType() {
		return checkGet(q_systemType);
	}

	public void setQ_systemType(String qSystemType) {
		q_systemType = checkSet(qSystemType);
	}

	public String getQ_formID() {
		return checkGet(q_formID);
	}

	public void setQ_formID(String qFormID) {
		q_formID = checkSet(qFormID);
	}

	public String getQ_applNo() {
		return checkGet(q_applNo);
	}

	public void setQ_applNo(String qApplNo) {
		q_applNo = checkSet(qApplNo);
	}

	public String getQ_userID() {
		return checkGet(q_userID);
	}

	public void setQ_userID(String qUserID) {
		q_userID = checkSet(qUserID);
	}

	public String getQ_userName() {
		return checkGet(q_userName);
	}

	public void setQ_userName(String qUserName) {
		q_userName = checkSet(qUserName);
	}
	
	public String getQ_notifier() {
		return checkGet(q_notifier);
	}

	public void setQ_notifier(String qNotifier) {
		q_notifier = checkSet(qNotifier);
	}

	@Override
	public Object doQueryOne() throws Exception {
		CONIN0601F obj = this;
		Con0003Db c = (Con0003Db)View.getObject("from Con0003Db where id = " + Common.getLong(getId()));		
		if(c != null){
			obj.setId(Common.get(c.getId()));
			obj.setSystemType(Common.get(c.getSystemType()));
			obj.setFormCode(Common.get(c.getFormCode()));
			obj.setDbID(Common.get(c.getDbID()));
			obj.setApplNo(Common.get(c.getApplNo()));
			obj.setUserID(Common.get(c.getUserID()));
			obj.setUserName(Common.get(c.getUserName()));
			obj.setIp(Common.get(c.getIp()));
			obj.setLockDate(Common.get(c.getLockDate()));
			obj.setLockTime(Common.get(c.getLockTime()));
			obj.setNotifier(Common.get(c.getNotifier()));
		}
		return obj;
	}

	@Override
	public Object doQueryAll() throws Exception {
		if (!"".equals(getQ_isQuery())){
			setQ_id("");
		}
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		String hql = " from Con0003Db where 1 = 1 ";
		if(!"".equals(getQ_id())){
			hql += " and id = " + Common.getLong(getQ_id());
		} else {
			if(!"".equals(getQ_systemType()))
				hql += " and systemType = " + Common.sqlChar(getQ_systemType());
			if(!"".equals(getQ_formID()))
				hql += " and formID = " + Common.sqlChar(getQ_formID());
			if(!"".equals(getQ_applNo()))
				hql += " and applNo like " + Common.sqlChar("%" + getQ_applNo() + "%");
			if(!"".equals(getQ_userID()))
				hql += " and userID like " + Common.sqlChar("%" + getQ_userID() + "%");
			if(!"".equals(getQ_userName()))
				hql += " and userName like " + Common.sqlChar("%" + getQ_userName() + "%");
			if(!"".equals(getQ_notifier()))
				hql += " and notifier like " + Common.sqlChar("%" + getQ_notifier() + "%");
		}
		this.processCurrentPageAttribute(ServiceGetter.getInstance().getTcbwService().loadCount(hql));
		if (getTotalRecord() > 0){
			if (getState().indexOf("query")<0) 
				ServiceGetter.getInstance().getCommonService().getHibernateTemplate().clear();
			java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(hql + " order by id", this.getRecordStart(), this.getPageSize());
			if (objList != null && objList.size() > 0) {
				//使用map先將資料撈出來
//				java.util.Map<String, String> dpdKindMap = TCBWCommon.getCommonCodeMap("COSDPD");
				
				for(Object dtlObj : objList) {				
					Con0003Db dtl = (Con0003Db)dtlObj;
					String[] rowArray = new String[9];
					rowArray[0] = Common.get(dtl.getId());
					rowArray[1] = Common.get(dtl.getSystemType());
					rowArray[2] = Common.get(dtl.getFormCode());
					rowArray[3] = Common.get(dtl.getApplNo());
					rowArray[4] = Common.get(dtl.getUserName());
					rowArray[5] = Common.get(dtl.getIp());
					rowArray[6] = Common.formatYYYMMDD(dtl.getLockDate(),4);
					rowArray[7] = Common.formatHHMMSS(dtl.getLockTime());
					rowArray[8] = Common.get(dtl.getNotifier());
					arrList.add(rowArray);
				}
				objList.clear();
			}
		
		}
		return arrList;
	}
	
	protected String[][] getInsertCheckSQL(){	
		String[][] checkSQLArray = new String[1][4];
		checkSQLArray[0][0] = "select count(*) from Con0003Db where formCode = " + Common.sqlChar(getFormCode()) + " and id != " + Common.getLong(getId());
	 	checkSQLArray[0][1] = ">";
		checkSQLArray[0][2] = "0";
		checkSQLArray[0][3] = "該表單別已存在，請重新輸入！";
		return checkSQLArray;
	}

	@Override
	public void doCreate() throws Exception {
		Con0003Db obj = new Con0003Db();
		obj.setSystemType(systemType);
		obj.setFormCode(formCode);
		obj.setDbID(Common.getLong(dbID));
		obj.setApplNo(applNo);
		obj.setUserID(userID);
		obj.setUserName(userName);
		obj.setIp(ip);
		obj.setLockDate(lockDate);
		obj.setLockTime(lockTime);
		obj.setNotifier(notifier);
		obj.setModifyDate(getEditDate());
		obj.setModifyTime(getEditTime());
		ServiceGetter.getInstance().getTcbwService().save(obj);
		setId(Common.get(obj.getId()));
	}
	
	protected String[][] getUpdateCheckSQL(){	
		String[][] checkSQLArray = new String[1][4];
		checkSQLArray[0][0] = "select count(*) from Con0003Db where formCode = " + Common.sqlChar(getFormCode()) + " and id != " + Common.getLong(getId());
	 	checkSQLArray[0][1] = ">";
		checkSQLArray[0][2] = "0";
		checkSQLArray[0][3] = "該表單別已存在，請重新輸入！";
		return checkSQLArray;		
	}

	@Override
	public void doUpdate() throws Exception {
		Con0003Db obj = (Con0003Db)View.getObject(" from Con0003Db where id = " + Common.getLong(getId()));
		if(obj != null){
			obj.setSystemType(systemType);
			obj.setFormCode(formCode);
			obj.setDbID(Common.getLong(dbID));
			obj.setApplNo(applNo);
			obj.setUserID(userID);
			obj.setUserName(userName);
			obj.setIp(ip);
			obj.setLockDate(lockDate);
			obj.setLockTime(lockTime);
			obj.setNotifier(notifier);
			obj.setModifyDate(getEditDate());
			obj.setModifyTime(getEditTime());
			ServiceGetter.getInstance().getTcbwService().update(obj);
			setId(Common.get(obj.getId()));
		}
	}
	
  	/**
  	 * <br>
  	 * <br>目的：刪除
  	 * <br>參數：無
  	 * <br>傳回：無
  	*/		
	public void delete(){
		try {
			if (!beforeExecCheck(getDeleteCheckSQL())){
				setStateDeleteError();
			}else{
				setEditDate(Datetime.getYYYMMDD());
				setEditTime(Datetime.getHHMMSS());
				doDelete();
				setStateDeleteSuccess();
				setErrorMsg("解除完成");	
			}				
		} catch (Exception e) {
			e.printStackTrace();
			setStateDeleteError();
			if (e.getMessage()!=null && e.getMessage().length()<200) setErrorMsg(StringEscapeUtils.escapeJavaScript(e.getMessage()));
			else setErrorMsg("發生未預期的錯誤,解除失敗!若問題持續,請洽詢系統管理者或相關承辦人員！");			
		}
	}
	
	@Override
	public void doDelete() throws Exception {
		Con0003Db obj = (Con0003Db)View.getObject(" from Con0003Db where id = " + Common.getLong(getId()));
		if (obj != null) {
			ServiceGetter.getInstance().getTcbwService().delete(obj);
			setId("");
		}else{
			throw new Exception("查無資料，無法解除，請重新操作 !");
		}
	}

}
