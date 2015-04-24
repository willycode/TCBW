package com.kangdainfo.tcbw.view.sdrg;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Drg8004Db;

public class SDRG0601F extends SDRG0501F{
	
	//Drg8004Db
	private String anadate;	                //案件分析-分析日期
	private String anamedicineType;	        //案件分析-學名藥/原廠藥
	private String anaproduceType;	        //案件分析-國產/輸入
	private String analotType;	            //案件分析-批號範圍
	private String anarecyclereason;	    //案件分析-回收原因
	private String anarecyclersdesc;	    //案件分析-回收原因說明
	private String[] anasurvey;	            //案件分析-調查結果
	private String anasurveyOther;        	//案件分析-調查結果說明
	private String[] anaprecaution;        	//案件分析-預防措施
	private String anaprecautionOther;      //案件分析-預防措施說明


	//案件狀態(Drg8004Db)
	private String status2;

	public void doUpdate0601() throws Exception{
		ServiceGetter.getInstance().getTcbwService().getSdrgDao().updateBySdrg0601F(this);
	}    
	
	public Object doQueryOne0601() throws Exception {
		SDRG0601F obj = this;	
		
		Drg8004Db c = (Drg8004Db) View.getObject(" from Drg8004Db where drg8001Db.id=" + Common.getInt(obj.getId()));
		
		//System.out.println("[TCBW]-[SDRG0601F]-[doQueryOne]- Drg8001Db.id : " + obj.getId());
		
		if (c!=null) {			
			obj.setStatus2(c.getStatus());
			obj.setAnadate(Common.get(c.getAnadate()));	                           //案件分析-分析日期
			obj.setAnamedicineType(Common.get(c.getAnamedicineType()));	           //案件分析-學名藥/原廠藥
			obj.setAnaproduceType(Common.get(c.getAnaproduceType()));	           //案件分析-國產/輸入
			obj.setAnalotType(Common.get(c.getAnalotType()));	                   //案件分析-批號範圍
			obj.setAnarecyclereason(Common.get(c.getAnarecyclereason()));	       //案件分析-回收原因
			obj.setAnarecyclersdesc(Common.get(c.getAnarecyclersdesc()));	       //案件分析-回收原因說明			
			String[] anasurvey = null;
			if(c.getAnasurvey() != null && c.getAnasurvey().length()>0){
				anasurvey = new String[c.getAnasurvey().length()];
				anasurvey = c.getAnasurvey().split(",");
			}
			obj.setAnasurvey(anasurvey);	                                       //案件分析-調查結果
			obj.setAnasurveyOther(Common.get(c.getAnasurveyOther()));        	   //案件分析-調查結果說明
			String[] anaprecaution = null;
			if(c.getAnaprecaution() != null && c.getAnaprecaution().length()>0){
				anaprecaution = new String[c.getAnaprecaution().length()];
				anaprecaution = c.getAnaprecaution().split(",");
			}
			obj.setAnaprecaution(anaprecaution);                                   //案件分析-預防措施
			obj.setAnaprecautionOther(Common.get(c.getAnaprecautionOther()));      //案件分析-預防措施說明			
		}
		this.setState("queryOneSuccess");
		return obj;
	}
	
	public String getStatus2() {
		return checkGet(status2);
	}
	public void setStatus2(String status2) {
		this.status2 = checkSet(status2);
	}

	public String getAnadate() {
		return checkGet(anadate);
	}

	public void setAnadate(String anadate) {
		this.anadate = checkSet(anadate);
	}

	public String getAnamedicineType() {
		return checkGet(anamedicineType);
	}

	public void setAnamedicineType(String anamedicineType) {
		this.anamedicineType = checkSet(anamedicineType);
	}

	public String getAnaproduceType() {
		return checkGet(anaproduceType);
	}

	public void setAnaproduceType(String anaproduceType) {
		this.anaproduceType = checkSet(anaproduceType);
	}

	public String getAnalotType() {
		return checkGet(analotType);
	}

	public void setAnalotType(String analotType) {
		this.analotType = checkSet(analotType);
	}

	public String getAnarecyclereason() {
		return checkGet(anarecyclereason);
	}

	public void setAnarecyclereason(String anarecyclereason) {
		this.anarecyclereason = checkSet(anarecyclereason);
	}

	public String getAnarecyclersdesc() {
		return checkGet(anarecyclersdesc);
	}

	public void setAnarecyclersdesc(String anarecyclersdesc) {
		this.anarecyclersdesc = checkSet(anarecyclersdesc);
	}
	
	public String[] getAnasurvey() {
		return anasurvey;
	}

	public void setAnasurvey(String[] anasurvey) {
		this.anasurvey = anasurvey;
	}

	public String getAnasurveyOther() {
		return checkGet(anasurveyOther);
	}

	public void setAnasurveyOther(String anasurveyOther) {
		this.anasurveyOther = checkSet(anasurveyOther);
	}

	public String[] getAnaprecaution() {
		return anaprecaution;
	}

	public void setAnaprecaution(String[] anaprecaution) {
		this.anaprecaution = anaprecaution;
	}

	public String getAnaprecautionOther() {
		return checkGet(anaprecautionOther);
	}

	public void setAnaprecautionOther(String anaprecautionOther) {
		this.anaprecautionOther = checkSet(anaprecautionOther);
	}
	
}
