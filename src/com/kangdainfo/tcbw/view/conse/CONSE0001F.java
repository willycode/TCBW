package com.kangdainfo.tcbw.view.conse;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Con1001Db;
import com.kangdainfo.tcbw.util.TCBWCommon;

public class CONSE0001F extends SuperBean{

	private String id;
	private String systemType;
	private String formID;
	private String mailID;
	private String title;
	private String mailBody;
	
	private String q_isQuery;
	private String q_id;
	private String q_systemType;
	private String q_formID;
	private String q_mailID;
	private String q_title;
	private String q_mailBody;
	
	
	
	@Override
	public Object doQueryOne() throws Exception {
		CONSE0001F obj = this;
		Con1001Db c = (Con1001Db)View.getObject("from Con1001Db where id = " + Common.getLong(getId()));		
		if(c != null){
			obj.setId(Common.get(c.getId()));
			obj.setSystemType(c.getSystemType());
			obj.setFormID(c.getFormID());
		    obj.setMailID(c.getMailID());
			obj.setTitle(c.getTitle());
			obj.setMailBody(c.getMailBody());
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
		String hql = " from Con1001Db where 1 = 1 ";
		
		if(!"".equals(getQ_id()))
		{
			hql += " and id = " + Common.getLong(getQ_id());
		}
		else
		{
			if(!"".equals(getQ_systemType()))
				hql += " and systemType = " + Common.sqlChar( getQ_systemType());
		    if(!"".equals(getQ_formID()))
				hql += " and formID = " + Common.sqlChar(getQ_formID());
			if(!"".equals(getQ_title()))
				hql += " and title like " + Common.sqlChar("%" + getQ_title() + "%");
			if(!"".equals(getQ_mailBody()))
				hql += " and mailBody like " + Common.sqlChar("%" + getQ_mailBody() + "%");
			if(!"".equals(getQ_mailID()))
				hql += " and mailID like " + Common.sqlChar("%" + getQ_mailID() + "%");
		}
		
		this.processCurrentPageAttribute(ServiceGetter.getInstance().getTcbwService().loadCount(hql));
		if (getTotalRecord() > 0)
		{
			if (getState().indexOf("query")<0) 
				ServiceGetter.getInstance().getCommonService().getHibernateTemplate().clear();
			java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(hql + " order by id", this.getRecordStart(), this.getPageSize());
			if (objList != null && objList.size() > 0) 
			{
				//使用map先將資料撈出來
				java.util.Map<String, String> sysMap = TCBWCommon.getCommonCodeMap("SYS");

				java.util.Map<String, String> formIDMap =TCBWCommon.getMap("select id,formdName from Con1007Db");
				
				for(Object dtlObj : objList) 
				{				
					Con1001Db dtl = (Con1001Db)dtlObj;
					String[] rowArray = new String[5];
					rowArray[0] = Common.get(dtl.getId());											
					rowArray[1] = Common.get(sysMap.get(dtl.getSystemType()));						
					rowArray[2] = Common.get(formIDMap.get(dtl.getFormID()));
					rowArray[3] = Common.get(dtl.getMailID());
					rowArray[4] = Common.get(dtl.getTitle());			
					arrList.add(rowArray);
				}
				objList.clear();
			}
		
		}
		return arrList;
	}
	
	protected String[][] getInsertCheckSQL(){	
		String[][] checkSQLArray = new String[1][4];
	 	checkSQLArray[0][0] = "select count(*) from Con1001Db where mailID = " + Common.sqlChar(getMailID());
		checkSQLArray[0][1] = ">";
		checkSQLArray[0][2] = "0";
		checkSQLArray[0][3] = "該表單編號已存在，請重新輸入！";
		return checkSQLArray;
	}

	@Override
	public void doCreate() throws Exception {
		Con1001Db obj = new Con1001Db();
	    obj.setSystemType(getSystemType());
	    obj.setFormID(getFormID());
	    obj.setMailID(getMailID());
	    obj.setTitle(getTitle());
	    obj.setMailBody(getMailBody());
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
		checkSQLArray[0][0] = "select count(*) from Con1001Db where mailID = " + Common.sqlChar(getMailID()) + " and id != " + Common.getLong(getId()); 	
	 	checkSQLArray[0][1] = ">";
		checkSQLArray[0][2] = "0";
		checkSQLArray[0][3] = "該表單編號已存在，請重新輸入！";		
		return checkSQLArray;		
	}

	@Override
	public void doUpdate() throws Exception {
		Con1001Db obj = (Con1001Db)View.getObject(" from Con1001Db where id = " + Common.getLong(getId()));
		if(obj != null){		
		    obj.setSystemType(getSystemType());
		    obj.setFormID(getFormID());
		    obj.setMailID(getMailID());
		    obj.setTitle(getTitle());
		    obj.setMailBody(getMailBody());
		    obj.setModifier(getEditID());
		    obj.setModifyDate(getEditDate());
		    obj.setModifyTime(getEditTime());
			ServiceGetter.getInstance().getTcbwService().update(obj);
			setId(Common.get(obj.getId()));
		}
	}

	@Override
	public void doDelete() throws Exception {
		Con1001Db obj = (Con1001Db)View.getObject(" from Con1001Db where id = " + Common.getLong(getId()));
		if(obj != null){
			ServiceGetter.getInstance().getTcbwService().delete(obj);
			setId("");
		}else{
			throw new Exception("查無資料，無法刪除，請重新操作 !");
		}
	}

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

	public String getFormID() {
		return checkGet(formID);
	}

	public void setFormID(String formID) {
		this.formID = checkSet(formID);
	}

	public String getTitle() {
		return checkGet(title);
	}

	public void setTitle(String title) {
		this.title = checkSet(title);
	}

	public String getMailBody() {
		return get(mailBody);
	}

	public void setMailBody(String mailBody) {
		this.mailBody = mailBody;
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

	public String getQ_title() {
		return checkGet(q_title);
	}

	public void setQ_title(String qTitle) {
		q_title = checkSet(qTitle);
	}

	public String getQ_mailBody() {
		return checkGet(q_mailBody);
	}

	public void setQ_mailBody(String qMailBody) {
		q_mailBody = checkSet(qMailBody);
	}

	public String getMailID() {
		return checkGet(mailID);
	}

	public void setMailID(String mailID) {
		this.mailID = checkSet(mailID);
	}

	public String getQ_mailID() {
		return checkGet(q_mailID);
	}

	public void setQ_mailID(String qMailID) {
		q_mailID = checkSet(qMailID);
	}
	
	
	
}
