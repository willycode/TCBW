package com.kangdainfo.tcbw.view.conse;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Con1011Db;
import com.kangdainfo.tcbw.util.TCBWCommon;

public class CONSE0005F extends SuperBean{

	private String id;
	private String formType;
	private String kind;
	private String kindName;
	private String year;
	private int num;
	
	private String q_isQuery;
	private String q_id;
	private String q_formType;
	private String q_kind;
	private String q_kindName;
	
	@Override
	public Object doQueryOne() throws Exception {
		CONSE0005F obj = this;
		Con1011Db c = (Con1011Db)View.getObject("from Con1011Db where id = " + Common.getLong(getId()));
		if(c != null){
			obj.setId(Common.get(c.getId()));
			obj.setFormType(Common.get(c.getFormType()));
			obj.setKind(Common.get(c.getKind()));
			obj.setKindName(Common.get(c.getKindName()));
			obj.setYear(Common.get(c.getYear()));
			obj.setNum(Common.get(c.getNum()));
			obj.setEditID(Common.get(c.getModifier()));
			obj.setEditDate(Common.get(c.getModifyDate()));
		}
		return obj;
	}

	@Override
	public Object doQueryAll() throws Exception {
		if (!"".equals(getQ_isQuery())){
			setQ_id("");
		}
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		String hql = " from Con1011Db where 1 = 1 ";
		if(!"".equals(getQ_id())){
			hql += " and id = " + Common.getLong(getQ_id());
		} else {
			if(!"".equals(getQ_formType()))
				hql += " and formType = " + Common.sqlChar(getQ_formType());
			if(!"".equals(getQ_kind()))
				hql += " and kind = " + Common.sqlChar(getQ_kind());
		}
		this.processCurrentPageAttribute(ServiceGetter.getInstance().getTcbwService().loadCount(hql));
		if (getTotalRecord() > 0){
			if (getState().indexOf("query")<0) 
				ServiceGetter.getInstance().getCommonService().getHibernateTemplate().clear();
			java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(hql + " order by id", this.getRecordStart(), this.getPageSize());
			if (objList != null && objList.size() > 0) {
				//使用map先將資料撈出來
				java.util.Map<String, String> casetypeMap = TCBWCommon.getCommonCodeMap("CASETYPE");
				
				for(Object dtlObj : objList) {				
					Con1011Db dtl = (Con1011Db)dtlObj;
					String[] rowArray = new String[5];
					rowArray[0] = Common.get(dtl.getId());
					rowArray[1] = Common.get(dtl.getFormType());
					rowArray[2] = Common.get(dtl.getKind()+"-"+dtl.getKindName());
					rowArray[3] = Common.get(dtl.getYear());
					rowArray[4] = Common.formatFrontZero(Common.get(dtl.getNum()),4);
					arrList.add(rowArray);
				}
				objList.clear();
			}
		
		}
		return arrList;
	}
	
	protected String[][] getInsertCheckSQL(){	
		String[][] checkSQLArray = new String[1][4];
		System.out.println(getFormType());
		checkSQLArray[0][0] = "select count(*) from Con1011Db where formType = " + Common.sqlChar(getFormType()) + "and kind = " + Common.sqlChar(getKind()) + "and year = " + Common.sqlChar(getYear());
		checkSQLArray[0][1] = ">";
		checkSQLArray[0][2] = "0";
		checkSQLArray[0][3] = "該表單別及類別已存在，請重新輸入！";
		return checkSQLArray;
	}

	@Override
	public void doCreate() throws Exception {
		Con1011Db obj = new Con1011Db();
		obj.setFormType(formType);
		obj.setKind(kind);
		obj.setKindName(kindName);
		obj.setYear(year);
		obj.setNum(num);
		obj.setCreator(getEditID());
		obj.setCreateDate(getEditDate());
		obj.setCreateTime(getEditTime());
		//obj.setModifier(getEditID());
		//obj.setModifyDate(getEditDate());
		//obj.setModifyTime(getEditTime());
		ServiceGetter.getInstance().getTcbwService().save(obj);
		setId(Common.get(obj.getId()));
	}
	
	protected String[][] getUpdateCheckSQL(){	
		String[][] checkSQLArray = new String[1][4];
		checkSQLArray[0][0] = "select count(*) from Con1011Db where formType = " + Common.sqlChar(getFormType()) + "and kind = " + Common.sqlChar(getKind()) + " and id != " + Common.getLong(getId()) + "and year = " + Common.sqlChar(getYear());
	 	checkSQLArray[0][1] = ">";
		checkSQLArray[0][2] = "0";
		checkSQLArray[0][3] = "該表單別及類別已存在，請重新輸入！";
		return checkSQLArray;		
	}

	@Override
	public void doUpdate() throws Exception {
		Con1011Db obj = (Con1011Db)View.getObject(" from Con1011Db where id = " + Common.getLong(getId()));
		if(obj != null){
			obj.setFormType(formType);
			obj.setKind(kind);
			obj.setKindName(kindName);
			obj.setYear(year);
			obj.setNum(num);
			obj.setModifier(getEditID());
			obj.setModifyDate(getEditDate());
			obj.setModifyTime(getEditTime());
			ServiceGetter.getInstance().getTcbwService().update(obj);
			setId(Common.get(obj.getId()));
		}
	}
	
	
	@Override
	public void doDelete() throws Exception {
		Con1011Db obj = (Con1011Db)View.getObject(" from Con1011Db where id = " + Common.getLong(getId()));
		if (obj != null) {
			ServiceGetter.getInstance().getTcbwService().delete(obj);
			setId("");
		}else{
			throw new Exception("查無資料，無法解除，請重新操作 !");
		}
	}

	public String getKindName() {
		return checkGet(kindName);
	}

	public void setKindName(String kindName) {
		this.kindName = checkSet(kindName);
	}

	public String getQ_kindName() {
		return checkGet(q_kindName);
	}

	public void setQ_kindName(String qKindName) {
		q_kindName = checkSet(qKindName);
	}

	public String getId() {
		return checkGet(id);
	}

	public void setId(String id) {
		this.id = checkSet(id);
	}

	public String getFormType() {
		return checkGet(formType);
	}

	public void setFormType(String formType) {
		this.formType = checkSet(formType);
	}

	public String getKind() {
		return checkGet(kind);
	}

	public void setKind(String kind) {
		this.kind = checkSet(kind);
	}

	public String getYear() {
		return checkGet(year);
	}

	public void setYear(String year) {
		this.year = checkSet(year);
	}
	
	public String getNum() {
		if (num == 0) {
			return "";
		} else {
			return String.valueOf(num);
		}
	}

	public void setNum(String num) {
		this.num = Integer.parseInt(num.trim());
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
	
	public String getQ_formType() {
		return checkGet(q_formType);
	}

	public void setQ_formType(String qFormType) {
		q_formType = checkSet(qFormType);
	}

	public String getQ_kind() {
		return checkGet(q_kind);
	}

	public void setQ_kind(String qKind) {
		q_kind = checkSet(qKind);
	}
	
}
