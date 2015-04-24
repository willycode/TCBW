package com.kangdainfo.tcbw.view.hfrin;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Hfr1002Db;
import com.kangdainfo.tcbw.model.bo.Hfr1003Db;
import com.kangdainfo.tcbw.model.bo.Hfr1004Db;
import com.kangdainfo.tcbw.util.TCBWCommon;

public class HFRIN0302F extends HFRIN0301F{
	
	private String detailid;
	private String hfr02id;
	private String name;
	private String termSdate;
	private String termEdate;
	private String postLev;
	private String unionID;
	private String committeeNum;

	public String getCommitteeNum() {
		return checkGet(committeeNum);
	}

	public void setCommitteeNum(String committeeNum) {
		this.committeeNum = checkSet(committeeNum);
	}

	public String getDetailid() {
		return checkGet(detailid);
	}

	public void setDetailid(String detailid) {
		this.detailid = checkSet(detailid);
	}

	public String getHfr02id() {
		return checkGet(hfr02id);
	}

	public void setHfr02id(String hfr02id) {
		this.hfr02id = checkSet(hfr02id);
	}

	public String getName() {
		return checkGet(name);
	}

	public void setName(String name) {
		this.name = checkSet(name);
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

	@Override
	public Object doQueryOne() throws Exception {
		HFRIN0302F obj = this;
		Hfr1004Db c = (Hfr1004Db)View.getObject("from Hfr1004Db where id = " + Common.getLong(getDetailid()));		
		if(c != null){
			java.util.Map<String, String> postLevMap = TCBWCommon.getCommonCodeMap("PL");
			java.util.Map<String, String> unionMap = TCBWCommon.getCommonCodeMap("UI");
			obj.setDetailid(Common.get(c.getId()));
			obj.setId(Common.get(c.getHfr1003Db().getId()));
			obj.setHfr02id(Common.get(c.getHfr1002Db().getId()));
			obj.setCommitteeNum(Common.get(c.getHfr1002Db().getHfr1001Db().getCommitteeNum()));
			obj.setName(c.getHfr1002Db().getHfr1001Db().getName());
			obj.setTermSdate(c.getHfr1002Db().getTermSdate());
			obj.setTermEdate(c.getHfr1002Db().getTermEdate());
			obj.setPostLev(postLevMap.get(c.getHfr1002Db().getPostLev()));
			obj.setUnionID(unionMap.get(c.getHfr1002Db().getUnionID()));
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
		String hql = " from Hfr1004Db where 1 = 1 and hfr1003Db.id = " + getId();
			
		this.processCurrentPageAttribute(ServiceGetter.getInstance().getTcbwService().loadCount(hql));
		if (getTotalRecord() > 0){
			if (getState().indexOf("query")<0) 
				ServiceGetter.getInstance().getCommonService().getHibernateTemplate().clear();
			java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(hql + " order by id", this.getRecordStart(), this.getPageSize());
			if (objList != null && objList.size() > 0) {
				java.util.Map<String, String> postLevMap = TCBWCommon.getCommonCodeMap("PL");
				java.util.Map<String, String> unionMap = TCBWCommon.getCommonCodeMap("UI");
				
				for(Object dtlObj : objList) {				
					Hfr1004Db dtl = (Hfr1004Db)dtlObj;
					String[] rowArray = new String[5];
					rowArray[0] = Common.get(dtl.getId());	
//					rowArray[1] = Common.get(dtl.getHfr1002Db().getId());						
					rowArray[1] = Common.get(dtl.getHfr1002Db().getHfr1001Db().getName());
					rowArray[2] = Common.get(postLevMap.get(dtl.getHfr1002Db().getPostLev()));
					rowArray[3] = Common.get(unionMap.get(dtl.getHfr1002Db().getUnionID()));
					rowArray[4] = Common.formatYYYMMDD(dtl.getHfr1002Db().getTermSdate(), 4) + "~" + Common.formatYYYMMDD(dtl.getHfr1002Db().getTermEdate(), 4);
					arrList.add(rowArray);
				}
				objList.clear();
			}
		
		}
		return arrList;
	}

	protected String[][] getInsertCheckSQL(){	
		String[][] checkSQLArray = new String[1][4];
	 	checkSQLArray[0][0]="select count(*) from Hfr1004Db where hfr1002Db.id=" + Common.getLong(getHfr02id());
		checkSQLArray[0][1]=">";
		checkSQLArray[0][2]="0";
		checkSQLArray[0][3]="該委員已重複，請重新輸入！";
		return checkSQLArray;
	}
	@Override
	public void doCreate() throws Exception {
		Hfr1004Db obj = new Hfr1004Db();
		Hfr1002Db hfr1002db = (Hfr1002Db)View.getObject(" from Hfr1002Db where id = " + Common.getLong(getHfr02id()));
		Hfr1003Db hfr1003db = (Hfr1003Db)View.getObject(" from Hfr1003Db where id = " + Common.getLong(getId()));
		obj.setHfr1002Db(hfr1002db);
		obj.setHfr1003Db(hfr1003db);
		obj.setCreator(getEditID());
		obj.setCreateDate(getEditDate());
		obj.setCreateTime(getEditTime());
	    obj.setModifier(getEditID());
	    obj.setModifyDate(getEditDate());
	    obj.setModifyTime(getEditTime());
		ServiceGetter.getInstance().getTcbwService().save(obj);
		setDetailid(Common.get(obj.getId()));
	}
	
	@Override
	public void doUpdate() throws Exception {
		Hfr1004Db obj = (Hfr1004Db)View.getObject("from Hfr1004Db where id = " + Common.getLong(getDetailid()));		
		if(obj != null){
			Hfr1002Db hfr1002db = (Hfr1002Db)View.getObject(" from Hfr1002Db where id = " + Common.getLong(getHfr02id()));
			obj.setHfr1002Db(hfr1002db);
			obj.setHfr1003Db(obj.getHfr1003Db());
		    obj.setModifier(getEditID());
		    obj.setModifyDate(getEditDate());
		    obj.setModifyTime(getEditTime());
			ServiceGetter.getInstance().getTcbwService().update(obj);
			setDetailid(Common.get(obj.getId()));
		}
	}

	@Override
	public void doDelete() throws Exception {
		Hfr1004Db obj = (Hfr1004Db)View.getObject("from Hfr1004Db where id = " + Common.getLong(getDetailid()));
		if(obj != null){
			ServiceGetter.getInstance().getTcbwService().delete(obj);
			setDetailid("");
		}else{
			throw new Exception("查無資料，無法刪除，請重新操作 !");
		}
	}
	
    public String getPopHfr0002(String className ,String nameField,String nameFieldValue,String idField){
    	StringBuilder sb = new StringBuilder(); 
    	sb.append("<input class=\"").append( "field_RO" ).append( "\" type=\"text\" id=\"" ).append( nameField ).append( "\" name=\"" ).append( nameField ).append( "\" size=\"").append(10).append("\" maxlength=\"10\" value=\"" ).append( nameFieldValue ).append( "\">\n");
    	sb.append("<input class=\"" ).append( className ).append( "\" type=\"button\" id=\"btn_" ).append( nameField ).append( "\" name=\"btn_" ).append( nameField ).append( "\" onclick=\"popHFR0002('" ).append( nameField ).append( "','" ).append( idField ).append( "')\" value=\"選擇評估委員\" title=\"評估委員輔助視窗\">");   	  	
    	return sb.toString(); 
    }
}
