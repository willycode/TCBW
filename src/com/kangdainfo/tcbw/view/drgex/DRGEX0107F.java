package com.kangdainfo.tcbw.view.drgex;


import java.util.Map;

import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.Datetime;
import com.kangdainfo.common.util.View;
import com.kangdainfo.persistence.PersistenceServiceGetter;
import com.kangdainfo.tcbw.model.bo.Con1015Db;
import com.kangdainfo.tcbw.model.bo.Drg0001Db;
import com.kangdainfo.tcbw.model.bo.Drg0002Db;
import com.kangdainfo.tcbw.model.bo.Drg0006Db;
import com.kangdainfo.tcbw.model.bo.Drg5002Db;
import com.kangdainfo.tcbw.util.TCBWCommon;
import com.kangdainfo.tcbw.view.drgin.DRGIN0102F;


public class DRGEX0107F extends DRGEX0101F{
	
	String caseType ;
	public String getCaseType() {return checkGet(caseType);}
	public void setCaseType(String caseType) {this.caseType = checkSet(caseType);}
	
	javax.servlet.ServletRequest httpRequest;
	public javax.servlet.ServletRequest getHttpRequest() {return httpRequest;}
	public void setHttpRequest(javax.servlet.ServletRequest r) {this.httpRequest = r;}
	
	private String applNo;	//案件號碼	VARCHAR(11)
	private String status;	//案件狀態	VARCHAR(2)
	
	private String analyDate06;	//分析日期	VARCHAR(7)
	private String medicineType06;	//學名藥/原廠藥	VARCHAR(1)
	private String produceType06;	//國產/輸入	VARCHAR(1)
	private String lotType06;	//批號範圍	VARCHAR(1)
	private String defect06;	//不良品缺陷	VARCHAR(2)
	private String defectOther06;	//不良品缺陷(其他)	NVARCHAR(50)
	private String survey06;	//調查結果	VARCHAR(2)
	private String surveyOther06;	//調查結果(其他)	NVARCHAR(50)
	private String precaution06;	//預防措施	VARCHAR(2)
	private String precautionOther06;	//預防措施(其他)	NVARCHAR(50)
	
	private String permitKey;	//許可證字	VARCHAR(14)
	private String permitNo;	//許可證號	VARCHAR(14)
	private String chProduct; 	//商品名稱中文	NVARCHAR(100)
	private String enProduct; 	//商品名稱英文	VARCHAR(100)
	private String ingredient;	//有效成分名稱	NVARCHAR(50)
	private String medModel;	//劑型	VARCHAR(2)
	private String medModelOther;	//劑型(描述)	NVARCHAR(50)
	private String applicationName;	//藥商/申請商	NVARCHAR(50)
	private String manufactorName;	//製造商/製造廠	NVARCHAR(50)
	private String manufactorNo; //批號
	
	public void doCheckNo() throws Exception{
		ServiceGetter.getInstance().getTcbwService().getDrgexDao().checkNoByDrgEX0107F(this);
	}
	
	public void doUpdateType() throws Exception{
		
		String yyymmdd = Datetime.getYYYMMDD();
		String hhmmss = Datetime.getHHMMSS();
		Drg0006Db obj = (Drg0006Db)View.getObject(" from Drg0006Db where applNo = " + Common.sqlChar(getApplNo()));
		if(obj != null)
		{			
			obj.setMedicineType(Common.get(getMedicineType06()));	//學名藥/原廠藥
			obj.setProduceType(Common.get(getProduceType06()));	//國產/輸入
			obj.setLotType(Common.get(getLotType06()));	//批號範圍
			obj.setDefect(Common.get(getDefect06()));	//不良品缺陷
			obj.setDefectOther(Common.get(getDefectOther06()));	//不良品缺陷(其他)
			obj.setSurvey(Common.get(getSurvey06()));	//調查結果
			obj.setSurveyOther(Common.get(getSurveyOther06())); //調查結果(其他)
			obj.setPrecaution(Common.get(getPrecaution06()));	//預防措施
			obj.setPrecautionOther(Common.get(getPrecautionOther06()));	//預防措施(其他)
			obj.setModifier(getUserID());
			obj.setModifyDate(yyymmdd);
			obj.setModifyTime(hhmmss);
			
			ServiceGetter.getInstance().getCommonService().update(obj);
		}
	}
	
	public void doAnalyOver() throws Exception{
		
		String yyymmdd = Datetime.getYYYMMDD();
		String hhmmss = Datetime.getHHMMSS();
		
		Drg0006Db obj = (Drg0006Db)View.getObject(" from Drg0006Db where applNo = " + Common.sqlChar(getApplNo()));
		if(obj != null)
		{			
            obj.setAnalyDate(yyymmdd);  //按下分析完成，才寫入此欄位
			obj.setMedicineType(Common.get(getMedicineType06()));	//學名藥/原廠藥
			obj.setProduceType(Common.get(getProduceType06()));	//國產/輸入
			obj.setLotType(Common.get(getLotType06()));	//批號範圍
			obj.setDefect(Common.get(getDefect06()));	//不良品缺陷
			obj.setDefectOther(Common.get(getDefectOther06()));	//不良品缺陷(其他)
			obj.setSurvey(Common.get(getSurvey06()));	//調查結果
			obj.setSurveyOther(Common.get(getSurveyOther06())); //調查結果(其他)
			obj.setPrecaution(Common.get(getPrecaution06()));	//預防措施
			obj.setPrecautionOther(Common.get(getPrecautionOther06()));	//預防措施(其他)
			obj.setModifier(getUserID());
			obj.setModifyDate(yyymmdd);
			obj.setModifyTime(hhmmss);
			
			ServiceGetter.getInstance().getCommonService().update(obj);
		}
	}
	
	public Object doQueryOne() throws Exception {
		DRGEX0107F obj = this;
		String hql = " from Drg0006Db where applNo = ( select applNo from Drg5001Db where id=" + Common.getLong(obj.getId())+" )";
		Drg0006Db c = (Drg0006Db) View.getObject(hql);		
		System.out.println("[TCBW]-[DRGEX0107F]-[doQueryOne] : " + hql);		
		if (c!=null) {
			obj.setApplNo(Common.get(c.getApplNo()));
			obj.setAnalyDate06(Common.get(c.getAnalyDate()));
			obj.setMedicineType06(Common.get(c.getMedicineType())); //學名藥/原廠藥
			obj.setProduceType06(Common.get(c.getProduceType())); //國產/輸入
			obj.setLotType06(Common.get(c.getLotType())); //批號範圍
			obj.setDefect06(Common.get(c.getDefect())); //不良品缺陷
			obj.setDefectOther06(Common.get(c.getDefectOther())); //不良品缺陷(其他)
			obj.setSurvey06(Common.get(c.getSurvey())); //調查結果
			obj.setSurveyOther06(Common.get(c.getSurveyOther())); //調查結果(其他)
			obj.setPrecaution06(Common.get(c.getPrecaution())); //預防措施
			obj.setPrecautionOther06(Common.get(c.getPrecautionOther())); //預防措施(其他)
		}
		String hql2 = " from Drg0001Db where applNo =" + Common.sqlChar(c.getApplNo());
		System.out.println("[TCBW]-[DRGEX0107F]-[doQueryOne2] : " + hql2);
		Drg0001Db drg01 = (Drg0001Db) View.getObject(hql2);
		if (drg01!=null) {
			obj.setPermitKey(Common.get(drg01.getPermitKey()));
			obj.setPermitNo(Common.get(drg01.getPermitNo()));
			obj.setChProduct(Common.get(drg01.getChProduct()));
			obj.setEnProduct(Common.get(drg01.getEnProduct()));
			obj.setIngredient(Common.get(drg01.getIngredient()));
			obj.setMedModel(Common.get(drg01.getMedModel()));		
			obj.setMedModelOther(Common.get(drg01.getMedModelOther()));
			obj.setApplicationName(Common.get(drg01.getApplicationName()));		
			obj.setManufactorName(Common.get(drg01.getManufactorName()));		
			obj.setManufactorNo(Common.get(drg01.getManufactorNo()));			

		}
		return obj;
	}
	
	public boolean checkNo() throws Exception{		
		boolean isCheck = false;
		String yyymmdd = Datetime.getYYYMMDD();
		String drgLev = View.getLookupField("select drgLev from Drg0004Db where applNo="+Common.sqlChar(getApplNo()));
		String replyDate = View.getLookupField("select replyDate from Drg0005Db where applNo="+Common.sqlChar(getApplNo()));
		if("04".equals(drgLev) && "".equals(Common.get(replyDate))){
			String payDate = View.getLookupField("select payDate from Drg0008Db where applNo="+Common.sqlChar(getApplNo()));
			if(Common.getInt(yyymmdd) < Common.getInt(payDate)) isCheck = true;			
		}
		return isCheck;
	}
	
	public int countDrg07() throws Exception{		
		int countDrg07 = 0;
		String hql =" from Drg0007Db a where a.applNo="+Common.sqlChar(getApplNo())+" and a.applNo in ( select b.applNo from Drg0006Db b where b.analyDate is not null)";
		countDrg07 = ServiceGetter.getInstance().getCommonService().loadCount(hql);
		return countDrg07;
	}

	public String getApplNo() {return checkGet(applNo);}
	public void setApplNo(String applNo) {this.applNo = checkSet(applNo);}
	public String getStatus() {return checkGet(status);}
	public void setStatus(String status) {this.status = checkSet(status);}
	
	public String getPermitKey() {return checkGet(permitKey);}
	public void setPermitKey(String permitKey) {this.permitKey = checkSet(permitKey);}
	public String getPermitNo() {return checkGet(permitNo);}
	public void setPermitNo(String permitNo) {this.permitNo = checkSet(permitNo);}
	public String getChProduct() {return checkGet(chProduct);}
	public void setChProduct(String chProduct) {this.chProduct = checkSet(chProduct);}
	public String getEnProduct() {return get(enProduct);}
	public void setEnProduct(String enProduct) {this.enProduct = checkSet(enProduct);}
	public String getIngredient() {return checkGet(ingredient);}
	public void setIngredient(String ingredient) {this.ingredient = checkSet(ingredient);}	
	public String getMedModel() {return checkGet(medModel);}
	public void setMedModel(String medModel) {this.medModel = checkSet(medModel);}
	public String getMedModelOther() {return checkGet(medModelOther);}
	public void setMedModelOther(String medModelOther) {this.medModelOther = checkSet(medModelOther);}
	public String getApplicationName() {return checkGet(applicationName);}
	public void setApplicationName(String applicationName) {this.applicationName = checkSet(applicationName);}
	public String getManufactorName() {return checkGet(manufactorName);}
	public void setManufactorName(String manufactorName) {this.manufactorName = checkSet(manufactorName);}
	public String getManufactorNo() {return checkGet(manufactorNo);}
	public void setManufactorNo(String manufactorNo) {this.manufactorNo = checkSet(manufactorNo);}
	
	public String getAnalyDate06() {return checkGet(analyDate06);}
	public void setAnalyDate06(String analyDate06) {this.analyDate06 = checkSet(analyDate06);}
	public String getMedicineType06() {return checkGet(medicineType06);}
	public void setMedicineType06(String medicineType06) {this.medicineType06 = checkSet(medicineType06);}
	public String getProduceType06() {return checkGet(produceType06);}
	public void setProduceType06(String produceType06) {this.produceType06 = checkSet(produceType06);}
	public String getLotType06() {return checkGet(lotType06);}
	public void setLotType06(String lotType06) {this.lotType06 = checkSet(lotType06);}
	public String getDefect06() {return checkGet(defect06);}
	public void setDefect06(String defect06) {this.defect06 = checkSet(defect06);}
	public String getDefectOther06() {return checkGet(defectOther06);}
	public void setDefectOther06(String defectOther06) {this.defectOther06 = checkSet(defectOther06);}
	public String getSurvey06() {return checkGet(survey06);}
	public void setSurvey06(String survey06) {this.survey06 = checkSet(survey06);}
	public String getSurveyOther06() {return checkGet(surveyOther06);}
	public void setSurveyOther06(String surveyOther06) {this.surveyOther06 = checkSet(surveyOther06);}
	public String getPrecaution06() {return checkGet(precaution06);}
	public void setPrecaution06(String precaution06) {this.precaution06 = checkSet(precaution06);}
	public String getPrecautionOther06() {return checkGet(precautionOther06);}
	public void setPrecautionOther06(String precautionOther06) {this.precautionOther06 = checkSet(precautionOther06);}
}
