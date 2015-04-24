package com.kangdainfo.tcbw.view.sdrg;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.model.bo.CommonDepartment;
import com.kangdainfo.common.model.bo.CommonUser;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.Datetime;

import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Con0001Db;
import com.kangdainfo.tcbw.model.bo.Drg8001Db;
import com.kangdainfo.tcbw.model.bo.Drg8002Db;
import com.kangdainfo.tcbw.model.bo.Drg8003Db;
import com.kangdainfo.tcbw.util.TCBWCommon;

public class SDRG0201F extends SDRG0101F{
	
	//Drg8001Db
	private String postDate;	//發文日期
	private String postNo;	//回收文號
	private String recycleclass;	//回收分級
	private String msgsource;	//訊息來源
	private String msgsourcedesc;	//訊息來源說明
	private String cureapplno;	//通報編號
	private String quaapplno;	//警訊編號
	private String recycledeadline;	//回收期限
	private String healthbureau1;	//主管衛生局一
	private String healthbureau1Name;	//主管衛生局一名稱(畫面用)
	private String healthbureautype1;	//主管衛生局一類型
	private String healthbureau2;	//主管衛生局二
	private String healthbureau2Name;	//主管衛生局二名稱(畫面用)
	private String healthbureautype2;	//主管衛生局二類型
	
	//Drg8003Db
	private String q_extendate;	//申請展延日期
	private String q_extenreason; //展延理由
	private String q_extendeadline;	//展延後回收期限

	
	javax.servlet.ServletRequest httpRequest;
	public javax.servlet.ServletRequest getHttpRequest() {	return httpRequest;	}
	public void setHttpRequest(javax.servlet.ServletRequest r) {	this.httpRequest = r;	}
	

	public void doUpdate0201() throws Exception{
		ServiceGetter.getInstance().getTcbwService().getSdrgDao().updateBySdrg0201F(this);
	}	
    
	public Object doQueryOne0201() throws Exception {
		SDRG0201F obj = this;	
		
		Drg8001Db c = (Drg8001Db) View.getObject(" from Drg8001Db where id=" + Common.getInt(obj.getId()));
		
		//System.out.println("[TCBW]-[SDRG0201F]-[doQueryOne]- Drg8001Db.id : " + obj.getId());
		
		if (c!=null) {
			java.util.Map<String, String> healthMap = TCBWCommon.getMap("select id,unionName from Con1003Db");			
			
			obj.setPostDate(Common.get(c.getPostDate()));	                 //發文日期
			obj.setPostNo(Common.get(c.getPostNo()));	                     //回收文號
			obj.setRecycleclass(Common.get(c.getRecycleclass()));	         //回收分級
			obj.setMsgsource(Common.get(c.getMsgsource()));	                 //訊息來源
			obj.setMsgsourcedesc(Common.get(c.getMsgsourcedesc()));	         //訊息來源說明
			obj.setCureapplno(Common.get(c.getCureapplno()));	             //通報編號
			obj.setQuaapplno(Common.get(c.getQuaapplno()));	                 //警訊編號
			obj.setRecycledeadline(Common.get(c.getRecycledeadline()));	     //回收期限
			obj.setHealthbureau1(Common.get(c.getHealthbureau1()));	         //主管衛生局一
			obj.setHealthbureau1Name(!"".equals(Common.get(c.getHealthbureau1()))?
					healthMap.get(Common.get(c.getHealthbureau1())):"");	 //主管衛生局一名稱
			obj.setHealthbureautype1(Common.get(c.getHealthbureautype1()));  //主管衛生局一類型
			obj.setHealthbureau2(Common.get(c.getHealthbureau2()));	         //主管衛生局二
			obj.setHealthbureau2Name(!"".equals(Common.get(c.getHealthbureau2()))?
					healthMap.get(Common.get(c.getHealthbureau2())):"");	 //主管衛生局一名稱
			obj.setHealthbureautype2(Common.get(c.getHealthbureautype2()));	 //主管衛生局二類型
			if(c.getDrg8003Dbs() != null && c.getDrg8003Dbs().size()>0){
				for (Object dtlObj : c.getDrg8003Dbs()) {
					Drg8003Db dtl = (Drg8003Db) dtlObj; 
					obj.setQ_extendate(Common.get(dtl.getExtendate()));	      //申請展延日期
					obj.setQ_extenreason(Common.get(dtl.getExtenreason()));     //展延理由
					obj.setQ_extendeadline(Common.get(dtl.getExtendeadline())); //展延後回收期限
				}
			}			
		}
		this.setState("queryOneSuccess");
		return obj;
	}
	
	
	public String getPostDate() {
		return checkGet(postDate);
	}
	public void setPostDate(String postDate) {
		this.postDate = checkSet(postDate);
	}
	public String getPostNo() {
		return checkGet(postNo);
	}
	public void setPostNo(String postNo) {
		this.postNo = checkSet(postNo);
	}
	public String getRecycleclass() {
		return checkGet(recycleclass);
	}
	public void setRecycleclass(String recycleclass) {
		this.recycleclass = checkSet(recycleclass);
	}
	public String getMsgsource() {
		return checkGet(msgsource);
	}
	public void setMsgsource(String msgsource) {
		this.msgsource = checkSet(msgsource);
	}
	public String getMsgsourcedesc() {
		return checkGet(msgsourcedesc);
	}
	public void setMsgsourcedesc(String msgsourcedesc) {
		this.msgsourcedesc = checkSet(msgsourcedesc);
	}
	public String getCureapplno() {
		return checkGet(cureapplno);
	}
	public void setCureapplno(String cureapplno) {
		this.cureapplno = checkSet(cureapplno);
	}
	public String getQuaapplno() {
		return checkGet(quaapplno);
	}
	public void setQuaapplno(String quaapplno) {
		this.quaapplno = checkSet(quaapplno);
	}
	public String getRecycledeadline() {
		return checkGet(recycledeadline);
	}
	public void setRecycledeadline(String recycledeadline) {
		this.recycledeadline = checkSet(recycledeadline);
	}
	public String getHealthbureau1() {
		return checkGet(healthbureau1);
	}
	public void setHealthbureau1(String healthbureau1) {
		this.healthbureau1 = checkSet(healthbureau1);
	}	
	public String getHealthbureau1Name() {
		return checkGet(healthbureau1Name);
	}
	public void setHealthbureau1Name(String healthbureau1Name) {
		this.healthbureau1Name = checkSet(healthbureau1Name);
	}
	public String getHealthbureautype1() {
		return checkGet(healthbureautype1);
	}
	public void setHealthbureautype1(String healthbureautype1) {
		this.healthbureautype1 = checkSet(healthbureautype1);
	}
	public String getHealthbureau2() {
		return checkGet(healthbureau2);
	}
	public void setHealthbureau2(String healthbureau2) {
		this.healthbureau2 = checkSet(healthbureau2);
	}	
	public String getHealthbureau2Name() {
		return checkGet(healthbureau2Name);
	}
	public void setHealthbureau2Name(String healthbureau2Name) {
		this.healthbureau2Name = checkSet(healthbureau2Name);
	}
	public String getHealthbureautype2() {
		return checkGet(healthbureautype2);
	}
	public void setHealthbureautype2(String healthbureautype2) {
		this.healthbureautype2 = checkSet(healthbureautype2);
	}
	public String getQ_extendate() {
		return checkGet(q_extendate);
	}
	public void setQ_extendate(String q_extendate) {
		this.q_extendate = checkSet(q_extendate);
	}
	public String getQ_extenreason() {
		return checkGet(q_extenreason);
	}
	public void setQ_extenreason(String q_extenreason) {
		this.q_extenreason = checkSet(q_extenreason);
	}
	public String getQ_extendeadline() {
		return checkGet(q_extendeadline);
	}
	public void setQ_extendeadline(String q_extendeadline) {
		this.q_extendeadline = checkSet(q_extendeadline);
	}	
}
