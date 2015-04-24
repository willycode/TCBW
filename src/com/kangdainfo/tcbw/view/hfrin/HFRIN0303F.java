package com.kangdainfo.tcbw.view.hfrin;

import java.util.Map;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Hfr0001Db;
import com.kangdainfo.tcbw.model.bo.Hfr1003Db;
import com.kangdainfo.tcbw.model.bo.Hfr1005Db;
import com.kangdainfo.tcbw.util.TCBWCommon;

public class HFRIN0303F extends HFRIN0301F{
	
	private String detailid2;
	private String caseno;
	private String casestatus;

	public String getDetailid2() {
		return checkGet(detailid2);
	}

	public void setDetailid2(String detailid2) {
		this.detailid2 = checkSet(detailid2);
	}

	public String getCaseno() {
		return checkGet(caseno);
	}

	public void setCaseno(String caseno) {
		this.caseno = checkSet(caseno);
	}

	public String getCasestatus() {
		return checkGet(casestatus);
	}

	public void setCasestatus(String casestatus) {
		this.casestatus = checkSet(casestatus);
	}

	@Override
	public Object doQueryOne() throws Exception {
		HFRIN0303F obj = this;
		Hfr1005Db c = (Hfr1005Db)View.getObject("from Hfr1005Db where id = " + Common.getLong(getDetailid2()));
		if(c != null){
			obj.setDetailid2(Common.get(c.getId()));
			obj.setId(Common.get(c.getHfr1003Db().getId()));
			//obj.setHfr02id(Common.get(c.getHfr0002Db().getId()));
			obj.setCaseno(Common.get(c.getCaseno()));
			obj.setCasestatus("");
			java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(" from Hfr0001Db where applNo= "+Common.sqlChar(c.getCaseno()));
			if (objList != null && objList.size() > 0) {
				Hfr0001Db dtl = (Hfr0001Db)objList.get(0);	
				obj.setCasestatus(Common.get(dtl.getStatus()));
				objList.clear();
			}

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
		String hql = "from Hfr1005Db a where 1 = 1 and hfr1003Db.id = " + getId();
			
		this.processCurrentPageAttribute(ServiceGetter.getInstance().getTcbwService().loadCount(hql));
		if (getTotalRecord() > 0){
			if (getState().indexOf("query")<0) 
				ServiceGetter.getInstance().getCommonService().getHibernateTemplate().clear();
			java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(hql + " order by id", this.getRecordStart(), this.getPageSize());
			if (objList != null && objList.size() > 0) {
				java.util.Map<String, String> FCSMap = ServiceGetter.getInstance().getCommonService().getCommonCodeDao().getCommonCodeNameMap("FCS", null);
				for(Object dtlObj : objList) {				
					Hfr1005Db dtl = (Hfr1005Db)dtlObj;
					String[] rowArray = new String[3];
					rowArray[0] = Common.get(dtl.getId());	
					rowArray[1] = Common.get(dtl.getCaseno());
					rowArray[2] = Common.get("");
					java.util.List objList2 = ServiceGetter.getInstance().getTcbwService().load(" from Hfr0001Db where applNo= "+Common.sqlChar(dtl.getCaseno()));
					if (objList2 != null && objList2.size() > 0) {
						Hfr0001Db dtl2 = (Hfr0001Db)objList2.get(0);	
						rowArray[2] = Common.get(FCSMap.get(Common.get(dtl2.getStatus()))).equals("")?Common.get(dtl2.getStatus()):Common.get(FCSMap.get(Common.get(dtl2.getStatus())));
						objList2.clear();
					}
					
					arrList.add(rowArray);
				}
				objList.clear();
			}
		
		}
		return arrList;
	}

	protected String[][] getInsertCheckSQL(){	
		String[][] checkSQLArray = new String[1][4];
	 	checkSQLArray[0][0]="select count(*) from Hfr1005Db where hfr1003Db.id=" + Common.getLong(getId()) + " and caseno = " + Common.sqlChar(getCaseno());
		checkSQLArray[0][1]=">";
		checkSQLArray[0][2]="0";
		checkSQLArray[0][3]="該案件編號已重複，請重新輸入！";
		return checkSQLArray;
	}
	
	@Override
	public void doCreate() throws Exception {
		Hfr1005Db obj = new Hfr1005Db();
		//Hfr0002Db hfr0002db = (Hfr0002Db)View.getObject(" from Hfr0002Db where id = " + Common.getLong(getHfr02id()));
		Hfr1003Db hfr1003db = (Hfr1003Db)View.getObject(" from Hfr1003Db where id = " + Common.getLong(getId()));
		//obj.setHfr0002Db(hfr0002db);
		obj.setHfr1003Db(hfr1003db);
		obj.setCaseno(caseno);
		obj.setCreator(getEditID());
		obj.setCreateDate(getEditDate());
		obj.setCreateTime(getEditTime());
	    obj.setModifier(getEditID());
	    obj.setModifyDate(getEditDate());
	    obj.setModifyTime(getEditTime());
		ServiceGetter.getInstance().getTcbwService().save(obj);
		setDetailid2(Common.get(obj.getId()));
	}
	
	@Override
	public void doUpdate() throws Exception {
		Hfr1005Db obj = (Hfr1005Db)View.getObject("from Hfr1005Db where id = " + Common.getLong(getDetailid2()));		
		if(obj != null){
			//Hfr0002Db hfr0002db = (Hfr0002Db)View.getObject(" from Hfr0002Db where id = " + Common.getLong(getHfr02id()));
			//obj.setHfr0002Db(hfr0002db);
			obj.setHfr1003Db(obj.getHfr1003Db());
			obj.setCaseno(caseno);
		    obj.setModifier(getEditID());
		    obj.setModifyDate(getEditDate());
		    obj.setModifyTime(getEditTime());
			ServiceGetter.getInstance().getTcbwService().update(obj);
			setDetailid2(Common.get(obj.getId()));
		}
	}

	@Override
	public void doDelete() throws Exception {
		Hfr1005Db obj = (Hfr1005Db)View.getObject("from Hfr1005Db where id = " + Common.getLong(getDetailid2()));
		if(obj != null){
			ServiceGetter.getInstance().getTcbwService().delete(obj);
			setDetailid2("");
		}else{
			throw new Exception("查無資料，無法刪除，請重新操作 !");
		}
	}
	
    public String getPopHfr0006(String className ,String nameField,String nameFieldValue,String idField){
    	StringBuilder sb = new StringBuilder(); 
    	sb.append("<input class=\"").append( "field_RO" ).append( "\" type=\"text\" id=\"" ).append( nameField ).append( "\" name=\"" ).append( nameField ).append( "\" size=\"").append(10).append("\" maxlength=\"10\" value=\"" ).append( nameFieldValue ).append( "\">\n");
    	sb.append("<input class=\"" ).append( className ).append( "\" type=\"button\" id=\"btn_" ).append( nameField ).append( "\" name=\"btn_" ).append( nameField ).append( "\" onclick=\"popHFR0006('" ).append( nameField ).append( "','" ).append( idField ).append( "')\" value=\"選擇案件編號\" title=\"案件編號輔助視窗\">");
    	System.out.println("SB: " + sb.toString());
    	return sb.toString(); 
    }
}
