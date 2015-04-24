package com.kangdainfo.tcbw.view.conin;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.Datetime;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Con1017Db;

public class CONIN1101F extends SuperBean {
	
	private String id;
	private String permitKey;
	private String permitNo;
	private String chProduct;
	private String remark;
	
	private String q_id;
	private String q_permitKey;
	private String q_permitNo;
	private String q_chProduct;
	private String q_isQuery;	

	public String getId() {
		return checkGet(id);
	}

	public void setId(String id) {
		this.id = checkSet(id);
	}

	public String getPermitKey() {
		return checkGet(permitKey);
	}

	public void setPermitKey(String permitKey) {
		this.permitKey = checkSet(permitKey);
	}

	public String getPermitNo() {
		return checkGet(permitNo);
	}

	public void setPermitNo(String permitNo) {
		this.permitNo = checkSet(permitNo);
	}

	public String getChProduct() {
		return checkGet(chProduct);
	}

	public void setChProduct(String chProduct) {
		this.chProduct = checkSet(chProduct);
	}

	public String getRemark() {
		return checkGet(remark);
	}

	public void setRemark(String remark) {
		this.remark = checkSet(remark);
	}

	public String getQ_id() {
		return checkGet(q_id);
	}

	public void setQ_id(String qId) {
		q_id = checkSet(qId);
	}

	public String getQ_permitKey() {
		return checkGet(q_permitKey);
	}

	public void setQ_permitKey(String qPermitKey) {
		q_permitKey = checkSet(qPermitKey);
	}

	public String getQ_permitNo() {
		return checkGet(q_permitNo);
	}

	public void setQ_permitNo(String qPermitNo) {
		q_permitNo = checkSet(qPermitNo);
	}

	public String getQ_chProduct() {
		return checkGet(q_chProduct);
	}

	public void setQ_chProduct(String qChProduct) {
		q_chProduct = checkSet(qChProduct);
	}
	
	public String getQ_isQuery() {
		return checkGet(q_isQuery);
	}

	public void setQ_isQuery(String qIsQuery) {
		q_isQuery = checkSet(qIsQuery);
	}

	@Override
	public Object doQueryOne() throws Exception {
		CONIN1101F obj = this;
		Con1017Db c = (Con1017Db)View.getObject("from Con1017Db where id = " + Common.getLong(getId()));
		if(c != null){
			obj.setId(Common.get(c.getId()));
			obj.setPermitKey(Common.get(c.getPermitKey()));
			obj.setPermitNo(Common.get(c.getPermitNo()));
			obj.setChProduct(Common.get(c.getChProduct()));
			obj.setRemark(Common.get(c.getRemark()));
		}
		return obj;
	}
	
	@Override
	public Object doQueryAll() throws Exception {
		if(!"".equals(getQ_isQuery())){
			setQ_id("");
		}
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		StringBuffer hql = new StringBuffer();
		hql.append("from Con1017Db where 1 = 1");
		if(!"".equals(getQ_id())){
			hql.append(" and id = " + Common.getLong(getQ_id()));
		}
		else{
			if(!"".equals(getQ_permitKey()))
				hql.append(" and permitKey = " + Common.sqlChar(getQ_permitKey()));		
			if(!"".equals(getQ_permitNo()))
				hql.append(" and permitNo = " + Common.sqlChar(getQ_permitNo()));		
			if(!"".equals(getQ_chProduct()))
				hql.append(" and chProduct like " + Common.sqlChar("%" + getQ_chProduct() + "%"));
		}
		this.processCurrentPageAttribute(ServiceGetter.getInstance().getTcbwService().loadCount(hql.toString()));		
		if(getTotalRecord() > 0){
			if(getState().indexOf("query") < 0)
				ServiceGetter.getInstance().getCommonService().getHibernateTemplate().clear();
		}
		
		java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(hql.toString() + "order by id", this.getRecordStart(), this.getPageSize());
		if(objList != null && objList.size() > 0){
			
			for(Object dtlObj : objList){
				Con1017Db dtl = (Con1017Db)dtlObj;
				String[] rowArray = new String[4];
				rowArray[0] = Common.get(dtl.getId());
				rowArray[1] = Common.get(dtl.getPermitKey()) + Common.get(dtl.getPermitNo());
				rowArray[2] = Common.get(dtl.getChProduct());
				rowArray[3] = Common.get(dtl.getRemark());
				arrList.add(rowArray);
			}
			objList.clear();
		}
		return arrList;
	}	
	
	public String[][] getInsertCheckSQL(){
		String[][] checkSQLArray = new String[1][4];
		checkSQLArray[0][0] = "select count(*) from Con1017Db where permitKey = " + Common.sqlChar(getPermitKey()) + " and permitNo = " + Common.sqlChar(getPermitNo());
		checkSQLArray[0][1] = ">";
		checkSQLArray[0][2] = "0";
		checkSQLArray[0][3] = "該許可證字號已存在，請重新輸入!";		
		return checkSQLArray;
	}
	
	@Override
	public void doCreate() throws Exception {
		Con1017Db obj = new Con1017Db();
		obj.setPermitKey(permitKey);
		obj.setPermitNo(permitNo);
		obj.setChProduct(chProduct);
		obj.setRemark(remark);
		obj.setCreateDate(Datetime.getYYYMMDD()); 
		obj.setCreateTime(Datetime.getHHMMSS()); 
		ServiceGetter.getInstance().getTcbwService().save(obj); 	
		setId(Common.get(obj.getId()));
	}	

	public String[][] getUpdateCheckSQL(){
		String[][] checkSQLArray = new String[1][4];
		checkSQLArray[0][0] = "select count(*) from Con1017Db where id != " + Common.getLong(getId()) + "and permitKey = " + Common.sqlChar(getPermitKey()) + " and permitNo = " + Common.sqlChar(getPermitNo());
		checkSQLArray[0][1] = ">";
		checkSQLArray[0][2] = "0";
		checkSQLArray[0][3] = "該許可證字號已存在，請重新輸入!";		
		return checkSQLArray;
	}
	
	@Override
	public void doUpdate() throws Exception {
		Con1017Db obj = (Con1017Db)View.getObject("from Con1017Db where id = " + Common.getLong(getId()));
		if(obj != null){
			obj.setPermitKey(permitKey);
			obj.setPermitNo(permitNo);
			obj.setChProduct(chProduct);
			obj.setRemark(remark);			
			obj.setModifyDate(Datetime.getYYYMMDD()); 
			obj.setModifyTime(Datetime.getHHMMSS());
			ServiceGetter.getInstance().getTcbwService().update(obj);
			setId(Common.get(obj.getId()));
		}
	}

	@Override
	public void doDelete() throws Exception {
		Con1017Db obj = (Con1017Db)View.getObject("from Con1017Db where id = " + Common.getLong(getId()));
		if(obj != null){
			ServiceGetter.getInstance().getTcbwService().delete(obj);
			setId("");
		}
		else{
			throw new Exception("查無資料，無法刪除，請重新操作 !");		
		}

	}
}
