package com.kangdainfo.tcbw.view.hfrin;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Hfr1001Db;
import com.kangdainfo.tcbw.model.bo.Hfr1002Db;
import com.kangdainfo.tcbw.util.TCBWCommon;

public class HFRIN0201F extends SuperBean{
	
	private String id;
	private String name;
	private String termSdate;
	private String termEdate;
	private String postLev;
	private String unionID;
	private String address;
	private String email;
	private String telArea;
	private String tel;
	private String faxArea;
	private String fax;
	
	private String q_isQuery;
	private String q_id;
	private String q_termYear;
	private String q_termSdate;
	private String q_termEdate;
	private String q_postLev;
	private String q_unionID;
	
	public String getId() {
		return checkGet(id);
	}

	public void setId(String id) {
		this.id = checkSet(id);
	}

	public String getName() {
		return checkGet(name);
	}

	public void setName(String name) {
		this.name = checkSet(name);
	}

	public String getQ_termYear() {
		return checkGet(q_termYear);
	}

	public void setQ_termYear(String qTermYear) {
		q_termYear = checkSet(qTermYear);
	}
	
	public String getTermSdate() {
		return checkGet(termSdate);
	}

	public void setTermSdate(String termSdate) {
		this.termSdate = checkSet(termSdate);
	}

	public String getTermEdate() {
		return checkGet(termEdate);
	}

	public void setTermEdate(String termEdate) {
		this.termEdate = checkSet(termEdate);
	}

	public String getPostLev() {
		return checkGet(postLev);
	}

	public void setPostLev(String postLev) {
		this.postLev = checkSet(postLev);
	}
	
	public String getUnionID() {
		return checkGet(unionID);
	}

	public void setUnionID(String unionID) {
		this.unionID = checkSet(unionID);
	}
	
	public String getAddress() {
		return checkGet(address);
	}

	public void setAddress(String address) {
		this.address = checkSet(address);
	}

	public String getEmail() {
		return checkGet(email);
	}

	public void setEmail(String email) {
		this.email = checkSet(email);
	}

	public String getTelArea() {
		return checkGet(telArea);
	}

	public void setTelArea(String telArea) {
		this.telArea = checkSet(telArea);
	}

	public String getTel() {
		return checkGet(tel);
	}

	public void setTel(String tel) {
		this.tel = checkSet(tel);
	}

	public String getFaxArea() {
		return checkGet(faxArea);
	}

	public void setFaxArea(String faxArea) {
		this.faxArea = checkSet(faxArea);
	}

	public String getFax() {
		return checkGet(fax);
	}

	public void setFax(String fax) {
		this.fax = checkSet(fax);
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

	public String getQ_termSdate() {
		return checkGet(q_termSdate);
	}

	public void setQ_termSdate(String qTermSdate) {
		q_termSdate = checkSet(qTermSdate);
	}

	public String getQ_termEdate() {
		return checkGet(q_termEdate);
	}

	public void setQ_termEdate(String qTermEdate) {
		q_termEdate = checkSet(qTermEdate);
	}

	public String getQ_postLev() {
		return checkGet(q_postLev);
	}

	public void setQ_postLev(String qPostLev) {
		q_postLev = checkSet(qPostLev);
	}

	public String getQ_unionID() {
		return checkGet(q_unionID);
	}

	public void setQ_unionID(String qUnionID) {
		q_unionID = checkSet(qUnionID);
	}

	@Override
	public Object doQueryOne() throws Exception {
		HFRIN0201F obj = this;
		Hfr1002Db c = (Hfr1002Db)View.getObject("from Hfr1002Db where id = " + Common.getLong(getId()));		
		if(c != null){
			obj.setId(Common.get(c.getId()));
			obj.setName(Common.get(c.getHfr1001Db().getId()));
			obj.setTermSdate(c.getTermSdate());
			obj.setTermEdate(c.getTermEdate());
			obj.setPostLev(c.getPostLev());
			obj.setUnionID(c.getUnionID());
			obj.setAddress(c.getAddress());
			obj.setEmail(c.getEmail());
			obj.setTel(c.getTel());
			obj.setFax(c.getFax());
			obj.setTelArea(c.getTelArea());
			obj.setFaxArea(c.getFaxArea());
			obj.setEditID(c.getModifier());
			obj.setEditDate(c.getModifyDate());
			System.out.println("NAME: " + Common.get(c.getHfr1001Db().getId()));
		}
		return obj;
	}

	@Override
	public Object doQueryAll() throws Exception {
		if (!"".equals(getQ_isQuery())){
			setQ_id("");
		}
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		String hql = " from Hfr1002Db where 1 = 1 ";
		if(!"".equals(getQ_id())){
			hql += " and id = " + Common.getLong(getQ_id());
		}else{
			if(!"".equals(getQ_termYear()))
				hql += " and substring(termSdate,1,3) = " + Common.sqlChar(getQ_termYear());
			if(!"".equals(getQ_termSdate()))
				hql += " and termSdate >= " + Common.sqlChar(getQ_termSdate());
			if(!"".equals(getQ_termEdate()))
				hql += " and termEdate <= " + Common.sqlChar(getQ_termEdate());
			if(!"".equals(getQ_postLev()))
				hql += " and postLev = " + Common.sqlChar(getQ_postLev());
			if(!"".equals(getQ_unionID()))
				hql += " and unionID = " + Common.sqlChar(getQ_unionID());
		}
		this.processCurrentPageAttribute(ServiceGetter.getInstance().getTcbwService().loadCount(hql));
		if (getTotalRecord() > 0){
			if (getState().indexOf("query")<0) 
				ServiceGetter.getInstance().getCommonService().getHibernateTemplate().clear();
			java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(hql + " order by id", this.getRecordStart(), this.getPageSize());
			if (objList != null && objList.size() > 0) 
			{
				//使用map先將資料撈出來
				java.util.Map<String, String> postLevMap = TCBWCommon.getCommonCodeMap("PL");
				java.util.Map<String, String> unionMap = TCBWCommon.getCommonCodeMap("UI");
				
				for(Object dtlObj : objList) 
				{				
					Hfr1002Db dtl = (Hfr1002Db)dtlObj;
					String[] rowArray = new String[5];
					rowArray[0] = Common.get(dtl.getId());											
					rowArray[1] = Common.formatYYYMMDD(dtl.getTermSdate(), 4) + "~" + Common.formatYYYMMDD(dtl.getTermEdate(), 4);						
					rowArray[2] = Common.get(dtl.getHfr1001Db().getName());
					//postLev,unionID 暫無代碼
					rowArray[3] = Common.get(postLevMap.get(dtl.getPostLev()));
					//rowArray[3] = Common.get(View.getOneCodeName("PL",Common.get(dtl.getPostLev())));
					rowArray[4] = Common.get(unionMap.get(dtl.getUnionID()));
					arrList.add(rowArray);
				}
				objList.clear();
			}
		
		}
		return arrList;
	}

	@Override
	public void doCreate() throws Exception {
		Hfr1001Db hfr1001db = (Hfr1001Db)View.getObject("from Hfr1001Db where id = " + Common.getLong(getName()));
		Hfr1002Db obj = new Hfr1002Db();
		obj.setHfr1001Db(hfr1001db);
		obj.setTermSdate(getTermSdate());
		obj.setTermEdate(getTermEdate());
		obj.setPostLev(getPostLev());
		obj.setUnionID(getUnionID());
		obj.setAddress(getAddress());
		obj.setEmail(getEmail());
		obj.setTel(getTel());
		obj.setFax(getFax());
		obj.setTelArea(getTelArea());
		obj.setFaxArea(getFaxArea());
		obj.setCreator(getEditID());
		obj.setCreateDate(getEditDate());
		obj.setCreateTime(getEditTime());
	    obj.setModifier(getEditID());
	    obj.setModifyDate(getEditDate());
	    obj.setModifyTime(getEditTime());
		ServiceGetter.getInstance().getTcbwService().save(obj);
		setId(Common.get(obj.getId()));
	}

	@Override
	public void doUpdate() throws Exception {
		Hfr1001Db hfr1001db = (Hfr1001Db)View.getObject("from Hfr1001Db where id = " + Common.getLong(getName()));
		Hfr1002Db obj = (Hfr1002Db)View.getObject(" from Hfr1002Db where id = " + Common.getLong(getId()));
		if(obj != null){		
		    obj.setHfr1001Db(hfr1001db);
		    obj.setTermSdate(getTermSdate());
		    obj.setTermEdate(getTermEdate());
		    obj.setPostLev(getPostLev());
		    obj.setUnionID(getUnionID());
		    obj.setAddress(getAddress());
		    obj.setEmail(getEmail());
		    obj.setTel(getTel());
		    obj.setFax(getFax());
		    obj.setTelArea(getTelArea());
		    obj.setFaxArea(getFaxArea());
		    obj.setModifier(getEditID());
		    obj.setModifyDate(getEditDate());
		    obj.setModifyTime(getEditTime());
			ServiceGetter.getInstance().getTcbwService().update(obj);
			setId(Common.get(obj.getId()));
		}
	}

	@Override
	public void doDelete() throws Exception {
		Hfr1002Db obj = (Hfr1002Db)View.getObject(" from Hfr1002Db where id = " + Common.getLong(getId()));
		if(obj != null){
			ServiceGetter.getInstance().getTcbwService().delete(obj);
			setId("");
		}else{
			throw new Exception("查無資料，無法刪除，請重新操作 !");
		}
	}
}
