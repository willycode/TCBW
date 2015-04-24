package com.kangdainfo.tcbw.view.drgex;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.Datetime;
import com.kangdainfo.common.util.SuperBean;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Drg4002Db;
import com.kangdainfo.tcbw.model.bo.Drg4009Db;
import com.kangdainfo.tcbw.util.TCBWCommon;


public class DRGEX0308F extends SuperBean{
	
	private String q_drg49Id;
	private String q_userId;
	
	private String id;
	
	public String getQ_drg49Id() {return checkSet(q_drg49Id);}
	public void setQ_drg49Id(String s) {this.q_drg49Id = checkSet(s);}
	public String getQ_userId() {return checkSet(q_userId);}
	public void setQ_userId(String s) {this.q_userId = checkSet(s);}
	
	public String getId() {return checkSet(id);}
	public void setId(String s) {this.id = checkSet(s);}
	
	javax.servlet.ServletRequest httpRequest;
	public javax.servlet.ServletRequest getHttpRequest() {	return httpRequest;	}
	public void setHttpRequest(javax.servlet.ServletRequest r) {	this.httpRequest = r;	}
	
	
	String[] drg49Row;
	public String[] getDrg49Row() {return drg49Row;}
	public void setDrg49Row(String[] drg49Row) {this.drg49Row = drg49Row;}
	public final String[] arrDrg49FieldName = {"ingredient","isEffectChange","isBrandChange","noBrandChange","beforeBrand","afterBrand","comment"};
	String drg49JSBuilder;
	public String getDrg49JSBuilder() {
		if (drg49JSBuilder!=null) return drg49JSBuilder;
		else return "";
	}
	public void setDrg49JSBuilder(String drg49JSBuilder) {this.drg49JSBuilder = drg49JSBuilder;}
	public String genDrg4009DbSet(List<Drg4009Db> drg49List) throws Exception {
		if(null != drg49List && !drg49List.isEmpty()){
	    	StringBuilder sb = new StringBuilder(1024);  
	    	int j=0;
	    	for (Drg4009Db dtl : drg49List) {
	    		sb.append("addDrg49Row('drg49Table'");
				for (j=0; j<arrDrg49FieldName.length; j++) {
					sb.append(",").append(Common.sqlChar(checkGet(Common.escapeJavaScript(BeanUtils.getProperty(dtl, arrDrg49FieldName[j])))));
				}        
				sb.append(",'").append(dtl.getId()!=null?dtl.getId():"").append("'");
				sb.append(");\n");
	    	}
	    	this.setDrg49JSBuilder(sb.toString());
	    	return sb.toString();    	
	    } else if (httpRequest!=null && this.getDrg49Row()!=null && this.getDrg49Row().length>0) {
			String v = "";
			StringBuilder sb = new StringBuilder(1024);
			for (int i=0; i<getDrg49Row().length; i++) {
				String rid = getDrg49Row()[i];
				sb.append("addDrg49Row('drg49Table'");
				for (int j=0; j<arrDrg49FieldName.length; j++) {				
					v = Common.escapeReturnChar(checkGet(httpRequest.getParameter(arrDrg49FieldName[j] + rid)),true);				
					sb.append(",").append(Common.sqlChar(v));				
				}
				sb.append(",'").append(checkGet(org.apache.commons.lang.StringEscapeUtils.escapeHtml(httpRequest.getParameter("drg49Id" + rid)))).append("'");
				sb.append(");\n");
				
			}
			this.setDrg49JSBuilder(sb.toString());
			return sb.toString();
		}
	    return "";
	}

	@Override
	public Object doQueryOne() throws Exception {
		// TODO Auto-generated method stub
		DRGEX0308F obj = this;
		String hql = " from Drg4009Db where (replyDate is null or replyDate = '') and (drg4005Db.isClose is null or drg4005Db.isClose ='' or drg4005Db.isClose ='N') "
				+ " and con1009Db.id in (select con1009Db.id from Con1010Db "
				+ " where commonUser.userId = " + Common.sqlChar(getQ_userId())+")";
		if(null != getQ_drg49Id() && !"".equals(getQ_drg49Id())){
			hql += " and drg4005Db.id = " + Common.getLong(getQ_drg49Id());
		}
		System.out.println(hql);
		List<Drg4009Db> drg49List = ServiceGetter.getInstance().getCommonService().load(hql);
		obj.setId("");
		if(null != drg49List && !drg49List.isEmpty()){
			obj.setId(Common.get(drg49List.get(0).getId()));
			obj.setDrg49JSBuilder(this.genDrg4009DbSet(drg49List)); 
		}
		
		return obj;
	}

	@Override
	public Object doQueryAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void doCreate() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doUpdate() throws Exception {
		List updList = new ArrayList();
		if (true) {
    		if (getHttpRequest()!=null && getDrg49Row()!=null && getDrg49Row().length>0) {	
    			for (int i=0; i<getDrg49Row().length; i++) {
    				String rid = getDrg49Row()[i];
    				Drg4009Db dtl = (Drg4009Db) View.getObject("from Drg4009Db where id="+Common.getLong(getHttpRequest().getParameter("drg49Id" + rid)));
    				if (dtl!=null) {
    					for (int j=0; j<arrDrg49FieldName.length; j++) {
        					String v = getHttpRequest().getParameter(arrDrg49FieldName[j] + rid);
        					BeanUtils.setProperty(dtl, arrDrg49FieldName[j], v);
        				}		
    					dtl.setReplyDate(Datetime.getYYYMMDD());
    					dtl.setModifier(getUserID());
        				dtl.setModifyDate(Datetime.getYYYMMDD());
        				dtl.setModifyTime(Datetime.getHHMMSS());
        				
        				updList.add(dtl);
    				}
    			}
    		}
    	}
		if(null != updList && !updList.isEmpty()){
			ServiceGetter.getInstance().getCommonService().updateBatch(updList);
		}
	}

	@Override
	public void doDelete() throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	
}
