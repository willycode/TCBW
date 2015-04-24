package com.kangdainfo.tcbw.view.medse;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Cos1001Db;
import com.kangdainfo.tcbw.model.bo.Med1004Db;
import com.kangdainfo.tcbw.util.TCBWCommon;

public class MEDSE0005F extends SuperBean{

	private String id;
	private String prodCoedB;
	private String prodCoedS;
	private String prodName;
	private String isStop;
	
	private String q_isQuery;
	private String q_id;
	private String q_prodCodeB;
	private String q_prodCodeS;
	private String q_prodName;
	private String q_isStop;	
	
	public String getId() {
		return checkGet(id);
	}

	public void setId(String id) {
		this.id = checkSet(id);
	}

	public String getProdCodeB() {
		return checkGet(prodCoedB);
	}

	public void setProdCodeB(String prodCoedB) {
		this.prodCoedB = checkSet(prodCoedB);
	}

	public String getProdCodeS() {
		return checkGet(prodCoedS);
	}

	public void setProdCodeS(String prodCoedS) {
		this.prodCoedS = checkSet(prodCoedS);
	}

	public String getProdName() {
		return checkGet(prodName);
	}

	public void setProdName(String prodName) {
		this.prodName = checkSet(prodName);
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

	public String getQ_prodCodeB() {
		return checkGet(q_prodCodeB);
	}

	public void setQ_prodCodeB(String qProdCodeB) {
		q_prodCodeB = checkSet(qProdCodeB);
	}

	public String getQ_prodCodeS() {
		return checkGet(q_prodCodeS);
	}

	public void setQ_prodCodeS(String qProdCodeS) {
		q_prodCodeS = checkSet(qProdCodeS);
	}

	public String getQ_prodName() {
		return checkGet(q_prodName);
	}

	public void setQ_prodName(String qProdName) {
		q_prodName = checkSet(qProdName);
	}

	public String getQ_isStop() {
		return checkGet(q_isStop);
	}

	public void setQ_isStop(String qIsStop) {
		q_isStop = checkSet(qIsStop);
	}

	@Override
	public Object doQueryOne() throws Exception {
		MEDSE0005F obj = this;
		Med1004Db m = (Med1004Db)View.getObject("from Med1004Db where id = " + Common.getLong(getId()));		
		if(m != null){
			obj.setId(Common.get(m.getId()));
			obj.setProdCodeB(m.getProdCode().substring(0, 2));
			obj.setProdCodeS(m.getProdCode().substring(2));
			obj.setProdName(m.getProdName());
			obj.setIsStop(m.getIsStop());
			obj.setEditID(m.getModifier());
			obj.setEditDate(m.getModifyDate());		
		}
		return obj;
	}

	@Override
	public Object doQueryAll() throws Exception {
		if (!"".equals(getQ_isQuery())){
			setQ_id("");
		}
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		String hql = " from Med1004Db where 1 = 1 ";
		if(!"".equals(getQ_id())){
			hql += " and id = " + Common.getLong(getQ_id());
		}else{
			if(!"".equals(getQ_prodCodeB()))
				hql += " and substring(prodCode,1,2) = " + Common.sqlChar( getQ_prodCodeB());
			if(!"".equals(getQ_prodCodeS()))
				hql += " and substring(prodCode,3,2) = " + Common.sqlChar( getQ_prodCodeS());
			if(!"".equals(getQ_prodName()))
				hql += " and prodName like " + Common.sqlChar("%" + getQ_prodName() + "%");
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
				java.util.Map<String, String> prodCodeMap = TCBWCommon.getCommonCodeMap("MEDAEPI");
				
				for(Object dtlObj : objList) {				
					Med1004Db dtl = (Med1004Db)dtlObj;
					String[] rowArray = new String[5];
					rowArray[0] = Common.get(dtl.getId());											
					rowArray[1] = Common.get(prodCodeMap.get(dtl.getProdCode().substring(0, 2)));						
					rowArray[2] = Common.get(dtl.getProdCode().substring(2));
					rowArray[3] = Common.get(dtl.getProdName());		
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
	 	checkSQLArray[0][0] = "select count(*) from Med1004Db where prodCode = " + Common.sqlChar(getProdCodeB() + getProdCodeS());
		checkSQLArray[0][1] = ">";
		checkSQLArray[0][2] = "0";
		checkSQLArray[0][3] = "該產品問題代碼已存在，請重新輸入！";
		return checkSQLArray;
	}

	@Override
	public void doCreate() throws Exception {
		Med1004Db obj = new Med1004Db();
	    obj.setProdCode(getProdCodeB() + getProdCodeS());
	    obj.setProdName(getProdName());
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
		checkSQLArray[0][0] = "select count(*) from Med1004Db where prodCode = " + Common.sqlChar(getProdCodeB() + getProdCodeS()) + " and id != " + Common.getLong(getId()); 	
	 	checkSQLArray[0][1] = ">";
		checkSQLArray[0][2] = "0";
		checkSQLArray[0][3] = "該產品問題代碼代碼已存在，請重新輸入！";	
		return checkSQLArray;		
	}

	@Override
	public void doUpdate() throws Exception {
		Med1004Db obj = (Med1004Db)View.getObject(" from Med1004Db where id = " + Common.getLong(getId()));
		if(obj != null){		
		    obj.setProdCode(getProdCodeB() + getProdCodeS());
		    obj.setProdName(getProdName());
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
		Med1004Db obj = (Med1004Db)View.getObject(" from Med1004Db where id = " + Common.getLong(getId()));
		if(obj != null){
			ServiceGetter.getInstance().getTcbwService().delete(obj);
			setId("");
		}else{
			throw new Exception("查無資料，無法刪除，請重新操作 !");
		}
	}

}
