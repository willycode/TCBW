package com.kangdainfo.tcbw.view.cosin;

import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import com.kangdainfo.ServiceGetter;
import com.kangdainfo.common.model.bo.CommonCode;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.View;
import com.kangdainfo.tcbw.model.bo.Cos0001Db;
import com.kangdainfo.tcbw.model.bo.Cos0002Db;
import com.kangdainfo.tcbw.model.bo.Cos0003Db;
import com.kangdainfo.tcbw.model.bo.Cos0004Db;
import com.kangdainfo.tcbw.model.bo.Cos0005Db;
import com.kangdainfo.tcbw.model.bo.Cos1001Db;
import com.kangdainfo.tcbw.util.TCBWCommon;

public class COSIN0102Q extends COSIN0901F {

	private String tabId;
	private String cos40001Id;
	
	private String q_notifierRevDateSHis;
	private String q_notifierRevDateEHis;
	private String[] q_cosTypeHis;	
	private String q_adverseConditionHis;
	private String[] q_mainCodeHis;
	private String q_notifierTypeHis;
	private String q_notifierDeptHis;
	
	private String q_isQuery;
	private String q_id;				//ID
	private String q_applNoS;			//案件編號S
	private String q_applNoE;			//案件編號E
	private String q_occurDateS;		//發生日期S
	private String q_occurDateE;		//發生日期E
	private String q_notifierRevDateS;	//通報日期S
	private String q_notifierRevDateE;	//通報日期E
	private String q_notifierRepDateS;	//收案日期S
	private String q_notifierRepDateE;	//收案日期E
	private String q_notifierSource;  	//通報來源
	private String q_notifierType;		//通報單位
	private String q_notifierDept;		//通報者服務機構
	private String q_permitKey;			//許可證字
	private String q_permitNo;			//許可證號
	private String q_chProduct;			//化粧品品名
	private String[] q_cosType;			//不良事件類別
	private String q_manufactorName;	//製造廠/進品代理商
	private String q_status;			//案件狀態
	private String[] q_mainCode;		//不良品缺陷
	private String q_preResult;
	private String q_isTrans;  	        //資料狀態(1:僅顯示歷史資料;2:僅顯示登錄資料)

	
	private String isCloseUserInfo;		// 是否遮蔽個資

	public String getTabId() {return checkGet(tabId);}
	public void setTabId(String tabId) {this.tabId = checkSet(tabId);}
	public String getCos40001Id() {return checkGet(cos40001Id);}
	public void setCos40001Id(String cos40001Id) {this.cos40001Id = checkSet(cos40001Id);}
	
	public String getQ_notifierRevDateSHis() {return checkGet(q_notifierRevDateSHis);}
	public void setQ_notifierRevDateSHis(String qNotifierRevDateSHis) {q_notifierRevDateSHis = checkSet(qNotifierRevDateSHis);}
	public String getQ_notifierRevDateEHis() {return checkGet(q_notifierRevDateEHis);}
	public void setQ_notifierRevDateEHis(String qNotifierRevDateEHis) {q_notifierRevDateEHis = checkSet(qNotifierRevDateEHis);}
	public String[] getQ_cosTypeHis() {return q_cosTypeHis;}
	public void setQ_cosTypeHis(String[] qCosTypeHis) {q_cosTypeHis = qCosTypeHis;}
	public String getQ_adverseConditionHis() {return checkGet(q_adverseConditionHis);}
	public void setQ_adverseConditionHis(String qAdverseConditionHis) {q_adverseConditionHis = checkSet(qAdverseConditionHis);}
	public String[] getQ_mainCodeHis() {return q_mainCodeHis;}
	public void setQ_mainCodeHis(String[] qMainCodeHis) {q_mainCodeHis = qMainCodeHis;}
	public String getQ_notifierTypeHis() {return checkGet(q_notifierTypeHis);}
	public void setQ_notifierTypeHis(String qNotifierTypeHis) {q_notifierTypeHis = checkSet(qNotifierTypeHis);}
	public String getQ_notifierDeptHis() {return checkGet(q_notifierDeptHis);}
	public void setQ_notifierDeptHis(String qNotifierDeptHis) {q_notifierDeptHis = checkSet(qNotifierDeptHis);}
	
	public String getIsCloseUserInfo() {return checkGet(isCloseUserInfo);}
	public void setIsCloseUserInfo(String isCloseUserInfo) {this.isCloseUserInfo = checkSet(isCloseUserInfo);}
	
	public String getQ_isQuery() {	return checkGet(q_isQuery);	}
	public void setQ_isQuery(String qIsQuery) {	q_isQuery = checkSet(qIsQuery);	}	
	public String getQ_id() {return checkGet(q_id);	}
	public void setQ_id(String q_id) {	this.q_id = checkSet(q_id);	}
	public String getQ_applNoS() {	return checkGet(q_applNoS);	}
	public void setQ_applNoS(String q_applNoS) {	this.q_applNoS = checkSet(q_applNoS);	}	
	public String getQ_applNoE() {	return checkGet(q_applNoE);	}
	public void setQ_applNoE(String q_applNoE) {	this.q_applNoE = checkSet(q_applNoE);	}
	public String getQ_occurDateS() {	return checkGet(q_occurDateS);	}
	public void setQ_occurDateS(String q_occurDateS) {	this.q_occurDateS = checkSet(q_occurDateS);	}	
	public String getQ_occurDateE() {	return checkGet(q_occurDateE);	}
	public void setQ_occurDateE(String q_occurDateE) {	this.q_occurDateE = checkSet(q_occurDateE);	}
	public String getQ_notifierRevDateS() {	return checkGet(q_notifierRevDateS);	}
	public void setQ_notifierRevDateS(String q_notifierRevDateS) {	this.q_notifierRevDateS = checkSet(q_notifierRevDateS);	}	
	public String getQ_notifierRevDateE() {	return checkGet(q_notifierRevDateE);	}
	public void setQ_notifierRevDateE(String q_notifierRevDateE) {	this.q_notifierRevDateE = checkSet(q_notifierRevDateE);	}
	public String getQ_notifierRepDateS() {	return checkGet(q_notifierRepDateS);	}
	public void setQ_notifierRepDateS(String q_notifierRepDateS) {	this.q_notifierRepDateS = checkSet(q_notifierRepDateS);	}	
	public String getQ_notifierRepDateE() {	return checkGet(q_notifierRepDateE);	}
	public void setQ_notifierRepDateE(String q_notifierRepDateE) {	this.q_notifierRepDateE = checkSet(q_notifierRepDateE);	}
	public String getQ_notifierSource() {	return checkGet(q_notifierSource);	}
	public void setQ_notifierSource(String q_notifierSource) {	this.q_notifierSource = checkSet(q_notifierSource);	}
	public String getQ_permitKey() {	return checkGet(q_permitKey);	}
	public void setQ_permitKey(String q_permitKey) {	this.q_permitKey = checkSet(q_permitKey);	}	
	public String getQ_permitNo() {	return checkGet(q_permitNo);	}
	public void setQ_permitNo(String q_permitNo) {	this.q_permitNo = checkSet(q_permitNo);	}
	public String getQ_chProduct() {	return checkGet(q_chProduct);	}
	public void setQ_chProduct(String q_chProduct) {	this.q_chProduct = checkSet(q_chProduct);	}
	public String getQ_manufactorName() {	return checkGet(q_manufactorName);	}
	public void setQ_manufactorName(String q_manufactorName) {	this.q_manufactorName = checkSet(q_manufactorName);	}
	public String getQ_status() {	return checkGet(q_status);	}
	public void setQ_status(String q_status) {	this.q_status = checkSet(q_status);	}	
	public String getQ_notifierDept() {	return checkGet(q_notifierDept);	}
	public void setQ_notifierDept(String qNotifierDept) {	q_notifierDept = checkSet(qNotifierDept);	}
	public String[] getQ_cosType() {	return q_cosType;	}
	public void setQ_cosType(String[] qCosType) {	q_cosType = qCosType;	}
	public String[] getQ_mainCode() {	return q_mainCode;	}
	public void setQ_mainCode(String[] qMainCode) {	q_mainCode = qMainCode;	}	
	public String getQ_notifierType() {	return checkGet(q_notifierType);	}
	public void setQ_notifierType(String qNotifierType) {	q_notifierType = checkSet(qNotifierType);	}	
	public String getQ_preResult() {	return checkGet(q_preResult);	}
	public void setQ_preResult(String q_preResult) {	this.q_preResult = checkSet(q_preResult);	}	
	public String getQ_isTrans() {	return checkGet(q_isTrans);	}
	public void setQ_isTrans(String q_isTrans) {	this.q_isTrans = checkSet(q_isTrans);	}
	/*
	public DefaultTableModel getMasterTableModel() throws Exception {
		DefaultTableModel model = null;
		String[] cols = new String[]{ "occurDate", "notifierRevDate", "notifierRepDate", "notifierSource", "notifierName", "notifierTel", "notifierEmail", "address",
				  "isContactYn", "cosType", "chProduct", "enProduct", "permitKey", "permitNo", "traffickWay", "tradePlace", 
				  "BusinessName", "manufactorName", "manufactorNo", "expirationDate", "tradeDate", "isSampleYn", "evenContactYn", "dealWith", 
				  "isSimilarYn", "isRecurrenceYn", "isOtherDeptYn", "objM1", "objM2", "isPrint1", "isPrint2", "objM3", "isPrint3", "ingredient", "manufactorAddr", "manufactorTel" };
		
		java.util.ArrayList<Object[]> arrList = new java.util.ArrayList<Object[]>();
		String hql = "from Cos0001Db where 1=1 ";
		if(!"".equals(getId()))
			hql += " and id = " + Common.getLong(getId());
		if(!"".equals(getApplNo()))
			hql += " and applNo = " + Common.sqlChar(getApplNo());
		System.out.println("[TCBW]-[COSEX0102F]-[QUERY] : " + hql);
		
		int ch1 = 0;
		int ch2 = 0;
		int ch3 = 0;
		java.util.List list = ServiceGetter.getInstance().getTcbwService().load(hql);
		if(list!=null && list.size()>0){
			for(int i=0; i<list.size(); i++){
				Cos0001Db obj = (Cos0001Db) list.get(i);
				
				Object rowArray[] = new Object[cols.length];
				rowArray[0] = Common.formatYYYMMDD(obj.getOccurDate(), 2);
				rowArray[1] = Common.formatYYYMMDD(obj.getNotifierRevDate(), 2); 
				rowArray[2] = Common.formatYYYMMDD(obj.getNotifierRepDate(), 2);
				rowArray[3] = getNotifierSource(Common.get(obj.getNotifierSource()), Common.get(obj.getNotifierSourceOther()));
			
				if(("Y".equals(Common.get(getIsCloseUserInfo()))) && (!"".equals(Common.get(obj.getNotifierName())))){
					rowArray[4] = "●●●";
				}else if("N".equals(Common.get(getIsCloseUserInfo()))){
					rowArray[4] = Common.get(obj.getNotifierName());
				}else{
					rowArray[4] = Common.get(obj.getNotifierName());
				}
			
				if(("Y".equals(Common.get(getIsCloseUserInfo()))) && (!"".equals(Common.get(obj.getNotifierTel())))){
					rowArray[5] = "●●●●●●●●";
				}else if("N".equals(Common.get(getIsCloseUserInfo()))){
					rowArray[5] = Common.get(obj.getNotifierTel());
				}else{
					rowArray[5] = Common.get(obj.getNotifierTel());
				}
				
				if(("Y".equals(Common.get(getIsCloseUserInfo()))) && (!"".equals(Common.get(obj.getNotifierEamil())))){
					rowArray[6] = "●●●●●●●●●●●●●●●●●●●●●";
				}else if("N".equals(Common.get(getIsCloseUserInfo()))){
					rowArray[6] = Common.get(obj.getNotifierEamil());
				}else{
					rowArray[6] = Common.get(obj.getNotifierEamil());
				}
			
				if(("Y".equals(Common.get(getIsCloseUserInfo()))) && (!"".equals(Common.get(obj.getAddress())))){
					rowArray[7] = "●●●●●●●●●●●●●●●●";
				}else if("N".equals(Common.get(getIsCloseUserInfo()))){
					rowArray[7] = Common.get(obj.getAddress());
				}else{
					rowArray[7] = Common.get(obj.getAddress());
				}
				
				if("Y".equals(Common.get(obj.getIsContactYn()))) {
					rowArray[8] = "■是　　□否";
				} else {
					rowArray[8] = "□是　　■否";
				}
				rowArray[9] = getCosType(Common.get(obj.getCosType()));
				rowArray[10] = Common.get(obj.getChProduct());
				rowArray[11] = Common.get(obj.getEnProduct());
				if(!"".equals(Common.get(obj.getPermitKey()))) {
					rowArray[12] = "□無　■有　" + getPermitKey(Common.get(obj.getPermitKey()));
					rowArray[13] = Common.get(obj.getPermitNo());
				} else {
					rowArray[12] = "■無　□有　" + getPermitKey(Common.get(obj.getPermitKey()));
					rowArray[13] = Common.get(obj.getPermitNo());
				}
				rowArray[14] = getTraffickWay(Common.get(obj.getTraffickWay()), Common.get(obj.getTraffickWayOther()));
				rowArray[15] = View.getLookupField("select codeName from CommonCode where commonCodeKind.codeKindId = 'CTY' and codeId = " + Common.sqlChar(obj.getTradePlace())) + 
				   			   View.getLookupField("select zipname from Con1002Db where zipcode = " + Common.sqlChar(obj.getTradePlaceZipCode())) + 
				   			   Common.get(obj.getTradePlaceAddr());
				rowArray[16] = Common.get(obj.getBusinessName());
				rowArray[17] = Common.get(obj.getManufactorName());
				rowArray[18] = Common.get(obj.getManufactorNo());
				rowArray[19] = Common.formatYYYMMDD(obj.getExpirationDate());
				rowArray[20] = Common.formatYYYMMDD(obj.getTradeDate(), 2);
				
				if("Y".equals(Common.get(obj.getIsSampleYn()))) {
					rowArray[21] = "■是　　□否";
				} else {
					rowArray[21] = "□是　　■否";
				}
				if("Y".equals(Common.get(obj.getEvenContactYn()))) {
					rowArray[22]="■是　　□否";
				} else {
					rowArray[22]="□是　　■否";
				}
				
				if("N".equals(Common.get(obj.getDealWith()))) {
					rowArray[23] = "■無處理　　□單一換貨　　□整批換貨";
				} else if("O".equals(Common.get(obj.getDealWith()))) {
					rowArray[23] = "□無處理　　■單一換貨　　□整批換貨";
				} else if("A".equals(Common.get(obj.getDealWith()))) {
					rowArray[23] = "□無處理　　□單一換貨　　■整批換貨";
				} else {
					rowArray[23]="□無處理　　□單一換貨　　□整批換貨";
				}
				
				if("Y".equals(Common.get(obj.getIsSimilarYn()))) {
					rowArray[24] = "■是　　□否";
				} else {
					rowArray[24] = "□是　　■否";
				}
				
				if("Y".equals(Common.get(obj.getIsRecurrenceYn()))) {
					rowArray[25] = "■是　　□否";
				} else {
					rowArray[25] = "□是　　■否";
				}
				
				if("Y".equals(Common.get(obj.getIsOtherDeptYn()))) {
					rowArray[26] = "■是" + Common.get(obj.getOtherDpetName()) +  "　　□否";
				} else {
					rowArray[26] = "□是　　■否";
				}
				
				// 不良品缺陷描述 - 配合報表變更為固定作異動
			//	if("1".equals(Common.get(obj.getCosType())) || "3".equals(Common.get(obj.getCosType()))){
					rowArray[27] = new JRTableModelDataSource(getDetail1TableModel(obj));
					rowArray[29] = "tmp" + (++ch1);
			///	}else{
			//		rowArray[27] = null;
			//		rowArray[29] = "tmp" + ch1;
			//	}
				
				// 不良反應 - 配合報表變更為固定作異動
			//	if("2".equals(Common.get(obj.getCosType())) || "3".equals(Common.get(obj.getCosType()))){
					rowArray[28] = new JRTableModelDataSource(getDetail2TableModel(obj));
					rowArray[30] = "tmp" + (++ch2);
			//	}else{
			//		rowArray[28] = null;
			//		rowArray[30] = "tmp" + ch2;
			//	}
				
				// 不良反應-就醫狀況與併用其它化粧品
			//	if("2".equals(Common.get(obj.getCosType())) || "3".equals(Common.get(obj.getCosType()))){
					rowArray[31] = new JRTableModelDataSource(getDetail3TableModel(obj));
					rowArray[32] = "tmp" + (++ch3);
			//	}else{
			//		rowArray[31] = null;
			//		rowArray[32] = "tmp" + ch3;
			//	}
				
				rowArray[33] = getIngredient(obj.getIngredient());
				rowArray[34] = View.getLookupField("select codeName from CommonCode where commonCodeKind.codeKindId = 'CTY' and codeId = " + Common.sqlChar(obj.getManufactorArea())) + 
				   			   View.getLookupField("select zipname from Con1002Db where zipcode = " + Common.sqlChar(obj.getManufactorZipCode())) + 
				   			   Common.get(obj.getManufactorAddr());
				rowArray[35] = (Common.get(obj.getManufactorTelArea()).equals("")?"":Common.get(obj.getManufactorTelArea())) + 
							   (Common.get(obj.getManufactorTel()).equals("")?"":("-" + Common.get(obj.getManufactorTel()))) + 
							   (Common.get(obj.getManufactorTelExt()).equals("")?"":("#" + Common.get(obj.getManufactorTelExt())));
				
				arrList.add(rowArray);
			}
		}
		
		if(arrList!=null && arrList.size()>0){
			model = new javax.swing.table.DefaultTableModel();
			Object[][] rs = new Object[0][0];
			rs = (Object[][])arrList.toArray(rs);
			model.setDataVector(rs, cols);
		}
		
		return model;
	}
	*/
	
	// 設定子報表路徑
	public void setParameter(java.util.HashMap<String, Object> params){
		String subreportDetail0FilePath = 
			ServiceGetter.getInstance().getMyServletContext().getServletContext().getRealPath("/report/cos/COSIN0401R_DETAIL0.jasper");
		String subreportDetail1FilePath = 
			ServiceGetter.getInstance().getMyServletContext().getServletContext().getRealPath("/report/cos/COSIN0401R_DETAIL1.jasper");
		params.put("subreport0", subreportDetail0FilePath);
		params.put("subreport1", subreportDetail1FilePath);
	}
	
	public boolean checkIsNeedSubReport(Cos0001Db obj){
		if(obj != null){
			if(Common.get(obj.getOtherExplain()).length() > 15){
				return true;
			}
			
			// 不良反應
			Cos0002Db cos0002Db = null;
			if(obj.getCos0002Dbs()!=null && obj.getCos0002Dbs().size()>0){
				for(Object dtl02Obj : obj.getCos0002Dbs()){
					Cos0002Db dtl02 = (Cos0002Db)dtl02Obj;
					cos0002Db = dtl02;
					
					if(cos0002Db != null){
						break;
					}
				}
			}
			if(cos0002Db != null){
				if(Common.get(cos0002Db.getNonSeriousOther()).length() > 18){
					return true;
				}
				if(Common.get(cos0002Db.getNonSeriousDis()).length() > 25){
					return true;
				}
				
				// 長度判斷由手動計算取得，之後有變動，此處也要跟著變
				if(Common.get(getCos0004Db(cos0002Db)).length() > 46){
					return true;
				}
				if(Common.get(getUseStatus(cos0002Db)).length() > 43){
					return true;
				}
				
				if(cos0002Db.getCos0005Dbs()!=null && cos0002Db.getCos0005Dbs().size()>0){
					return true;
				}
			}
		}
		return false;
	}
	
	public DefaultTableModel getTableModel() throws Exception {
		DefaultTableModel model = null;
		String[] cols = new String[]{ "applNo", "occurDate", "notifierRevDate", "notifierRepDate", "notifierSource",
									  "notifierName", "notifierTel", "notifierEamil", "address", "isContactYn",
									  "permitKey", "productName", "traffickWay", "businessName", "tradePlace", "manufactorName", "manufactorPlace", "manufactorTel",
									  "manufactorNo", "expirationDate", "tradeDate", "isSampleYn", "evenContactYn", "dealWith", "isRecurrenceYn", "isSimilarYn", "isOtherDeptYn",
									  "cos03", "isDamageYn", "otherExplain", "nonSeriousOther", "nonSeriousDis", "doc", "adverseCondition", 
									  "useStatus", "cos05", "isMitigateYn", "isRecurYn", "obj", "isCh" };
		
		java.util.ArrayList<Object[]> arrList = new java.util.ArrayList<Object[]>();
		String hql = "from Cos0001Db where 1=1 ";
		if(!"".equals(getId()))
			hql += " and id = " + Common.getLong(getId());
		if(!"".equals(getApplNo()))
			hql += " and applNo = " + Common.sqlChar(getApplNo());
		System.out.println("[TCBW]-[COSIN0102Q]-[QUERY] : " + hql);
		
		java.util.List list = ServiceGetter.getInstance().getTcbwService().load(hql);
		if(list!=null && list.size()>0){
			for(Object dtlObj : list){
				Cos0001Db dtl = (Cos0001Db)dtlObj;
				
				boolean isChange = checkIsNeedSubReport(dtl);
				
				Object[] rowArray = new Object[cols.length];
				rowArray[0] = Common.get(dtl.getApplNo());
				rowArray[1] = Common.formatYYYMMDD(dtl.getOccurDate(), 2);
				rowArray[2] = Common.formatYYYMMDD(dtl.getNotifierRevDate(), 2);
				rowArray[3] = Common.formatYYYMMDD(dtl.getNotifierRepDate(), 2);
				rowArray[4] = getNotifierSource(dtl.getNotifierSource(), dtl.getNotifierSourceOther());
				
				if(("Y".equals(Common.get(getIsCloseUserInfo()))) && (!"".equals(Common.get(dtl.getNotifierName())))){
					rowArray[5] = "●●●";
				}else{
					rowArray[5] = Common.get(dtl.getNotifierName());
				}
				
				if(("Y".equals(Common.get(getIsCloseUserInfo()))) && (!"".equals(Common.get(dtl.getNotifierTel())))){
					rowArray[6] = "●●●●●●●●";
				}else{
					rowArray[6] = Common.get(dtl.getNotifierTel());
				}
				
				if(("Y".equals(Common.get(getIsCloseUserInfo()))) && (!"".equals(Common.get(dtl.getNotifierEamil())))){
					rowArray[7] = "●●●●●●●●●●●●●●●●●●●●●";
				}else{
					rowArray[7] = Common.get(dtl.getNotifierEamil());
				}
			
				if(("Y".equals(Common.get(getIsCloseUserInfo()))) && 
						((!"".equals(Common.get(dtl.getAddress()))) || !"".equals(Common.get(dtl.getNotifierZipCode())) || !"".equals(Common.get(dtl.getNotifierArea()))) ){
					rowArray[8] = "●●●●●●●●●●●●●●●●";
				}else{
					rowArray[8] = View.getLookupField("select codeName from CommonCode where commonCodeKind.codeKindId = 'CTY' and codeId = " + Common.sqlChar(dtl.getNotifierArea())) +
		  			  			  View.getLookupField("select zipname from Con1002Db where zipcode = " + Common.sqlChar(dtl.getNotifierZipCode())) + 
		  			  			  Common.get(dtl.getAddress());
				}
				
				if("Y".equals(Common.get(dtl.getIsContactYn()))){
					rowArray[9] = "■是　□否";
				}else if("N".equals(Common.get(dtl.getIsContactYn()))){
					rowArray[9] = "□是　■否";
				}else{
					rowArray[9] = "□是　□否";
				}
				
				if(!"".equals(Common.get(dtl.getPermitKey()))){
					rowArray[10] = "□ 無" + "\n" + "■ 有 : " + getPermitKey(Common.get(dtl.getPermitKey())) + " 字第 " + Common.get(dtl.getPermitNo()) + " 號";
				}else{
					rowArray[10] = "■ 無" + "\n" + "■ 有 : " + "_____字第__________號";
				}
				rowArray[11] = "(中 文) " + Common.get(dtl.getChProduct()) + "\n" + "(英 文) " + Common.get(dtl.getEnProduct());
				rowArray[12] = getTraffickWay(Common.get(dtl.getTraffickWay()), Common.get(dtl.getTraffickWayOther()));
				rowArray[13] = Common.get(dtl.getBusinessName());
				rowArray[14] = View.getLookupField("select codeName from CommonCode where commonCodeKind.codeKindId = 'CTY' and codeId = " + Common.sqlChar(dtl.getTradePlace())) + 
				   			   View.getLookupField("select zipname from Con1002Db where zipcode = " + Common.sqlChar(dtl.getTradePlaceZipCode())) + 
				   			   Common.get(dtl.getTradePlaceAddr());
				rowArray[15] = Common.get(dtl.getManufactorName());
				rowArray[16] = View.getLookupField("select codeName from CommonCode where commonCodeKind.codeKindId = 'CTY' and codeId = " + Common.sqlChar(dtl.getManufactorArea())) + 
	   			   			   View.getLookupField("select zipname from Con1002Db where zipcode = " + Common.sqlChar(dtl.getManufactorZipCode())) + 
	   			   			   Common.get(dtl.getManufactorAddr());
				rowArray[17] = (Common.get(dtl.getManufactorTelArea()).equals("")?"":Common.get(dtl.getManufactorTelArea())) + 
				   			   (Common.get(dtl.getManufactorTel()).equals("")?"":("-" + Common.get(dtl.getManufactorTel()))) + 
				   			   (Common.get(dtl.getManufactorTelExt()).equals("")?"":("#" + Common.get(dtl.getManufactorTelExt())));
				rowArray[18] = Common.get(dtl.getManufactorNo());
				rowArray[19] = Common.formatYYYMMDD(dtl.getExpirationDate());
				rowArray[20] = Common.formatYYYMMDD(dtl.getTradeDate(), 3);
				
				if("Y".equals(Common.get(dtl.getIsSampleYn()))){
					rowArray[21] = "■ 是　□ 否";
				}else{
					rowArray[21] = "□ 是　■ 否";
				}
				
				if("Y".equals(Common.get(dtl.getEvenContactYn()))){
					rowArray[22] = "■ 是　□ 否";
				}else{
					rowArray[22] = "□ 是　■ 否";
				}
				if("N".equals(Common.get(dtl.getDealWith()))){
					rowArray[23] = "■無處理  □單一換貨  □整批換貨";
				} else if("O".equals(Common.get(dtl.getDealWith()))){
					rowArray[23] = "□無處理  ■單一換貨  □整批換貨";
				}else if("A".equals(Common.get(dtl.getDealWith()))){
					rowArray[23] = "□無處理  □單一換貨  ■整批換貨";
				}else{
					rowArray[23] = "□無處理  □單一換貨  □整批換貨";
				}
				if("Y".equals(Common.get(dtl.getIsRecurrenceYn()))){
					rowArray[24] = "■ 是   □ 否";
				}else{
					rowArray[24] = "□ 是   ■ 否";
				}
				
				if("Y".equals(Common.get(dtl.getIsSimilarYn()))){
					rowArray[25] = "■ 是	 □ 否";
				}else{
					rowArray[25] = "□ 是	 ■ 否";
				}
				
				if("Y".equals(Common.get(dtl.getIsOtherDeptYn()))) {
					rowArray[26] = "■ 是 : " + Common.get(dtl.getOtherDpetName()) + "\n" + "□ 否 ";
				} else {
					rowArray[26] = "□ 是 : __________" + "\n" + "■ 否 ";
				}
				
				String main = "";
				String sub = "";
				if(dtl.getCos0003Dbs()!=null && dtl.getCos0003Dbs().size()>0){
					for(Object dtl03Obj : dtl.getCos0003Dbs()){
						Cos0003Db cos0003Db = (Cos0003Db)dtl03Obj;
						main += cos0003Db.getMainCode() + ",";
						sub += cos0003Db.getSubCode() + ",";
					}
					
					String[] maincode = main.split(",");
					String[] subcode = sub.split(",");
					rowArray[27] = getCheckBoxOption2("select codeId, codeName from CommonCode where commonCodeKind.codeKindId='COSDPD' order by codeId", maincode, subcode, Common.get(dtl.getId()));
				}else{
					rowArray[27] = getCheckBoxOption2("select codeId, codeName from CommonCode where commonCodeKind.codeKindId='COSDPD' order by codeId", null, null, Common.get(dtl.getId()));
				}
				
				if("Y".equals(dtl.getIsDamageYn())){
					rowArray[28] = "■ 是，請描述：" + Common.get(dtl.getOtherInformation()) + " □ 否" + "\n" + "□ 其他，請描述 : _____";
				}else if("N".equals(dtl.getIsDamageYn())){
					rowArray[28] = "□ 是，請描述：_____　■ 否" + "\n" + "□ 其他";
				}else if("O".equals(dtl.getIsDamageYn())){
					rowArray[28] = "□ 是，請描述：_____　□ 否" + "\n" + "■ 其他";
				}else{
					rowArray[28] = "□ 是，請描述：_____　□ 否" + "\n" + "□ 其他";
				}
				
				if(Common.get(dtl.getOtherExplain()).length() > 15){
					rowArray[29] = Common.get(dtl.getOtherExplain()).substring(0, 5) + "(完整內容詳見第二頁)";
				}else{
					rowArray[29] = Common.get(dtl.getOtherExplain());
				}
				
				Cos0002Db cos0002Db = null;
				if(dtl.getCos0002Dbs()!=null && dtl.getCos0002Dbs().size()>0){
					for(Object dtl02Obj : dtl.getCos0002Dbs()){
						Cos0002Db dtl02 = (Cos0002Db)dtl02Obj;
						cos0002Db = dtl02;
						
						if(cos0002Db != null){
							break;
						}
					}
				}
				if(cos0002Db != null){
					if(Common.get(cos0002Db.getNonSeriousOther()).length() > 18){
						rowArray[30] = Common.get(cos0002Db.getNonSeriousOther()).substring(0, 5) + "(完整內容詳見第二頁)";
					}else{
						rowArray[30] = Common.get(cos0002Db.getNonSeriousOther());
					}
					if(Common.get(cos0002Db.getNonSeriousDis()).length() > 25){
						rowArray[31] = Common.get(cos0002Db.getNonSeriousDis()).substring(0, 5) + "(完整內容詳見第二頁)";
					}else{
						rowArray[31] = Common.get(cos0002Db.getNonSeriousDis());
					}
					
					String doc = getCos0004Db(cos0002Db);
					if(doc.length() > 46){
						rowArray[32] = doc.substring(0,9) + "(完整內容詳見第二頁)";
					}else{
						rowArray[32] = "";
					}
					rowArray[33] = getAdverseCondition(Common.get(cos0002Db.getAdverseCondition()));
					
					String useStatus = getUseStatus(cos0002Db);
					if(useStatus.length() > 43){
						rowArray[34] = getPresentUseStatus(cos0002Db);//useStatus.substring(0,5) + "(完整內容詳見第二頁)";
					}else{
						rowArray[34] = useStatus;
					}
					
					if(cos0002Db.getCos0005Dbs()!=null && cos0002Db.getCos0005Dbs().size()>0){
						rowArray[35] = "(完整內容詳見第二頁)";
					}else{
						rowArray[35] = "無";
					}
					
					rowArray[36] = getYNU(Common.get(cos0002Db.getIsMitigateYn()));
					rowArray[37] = getYNU(Common.get(cos0002Db.getIsRecurYn()));
				}else{
					rowArray[30] = "";
					rowArray[31] = "";
					rowArray[32] = "";
					rowArray[33] = getAdverseCondition("");
					rowArray[34] = getUseStatus(null);
					rowArray[35] = "無";
					rowArray[36] = getYNU("");
					rowArray[37] = getYNU("");
				}
				
				if(isChange){
					rowArray[38] = new JRTableModelDataSource(getPage2TableModel(dtl));
					rowArray[39] = "Y";
				}else{
					rowArray[38] = null;
					rowArray[39] = "N";
				}
				arrList.add(rowArray);
			}
			list.clear();
		}
		list = null;
		
		if(arrList!=null && arrList.size()>0){
			model = new javax.swing.table.DefaultTableModel();
			Object[][] rs = new Object[0][0];
			rs = (Object[][])arrList.toArray(rs);
			model.setDataVector(rs, cols);
		}
		
		return model;
	}
	
	public DefaultTableModel getPage2TableModel(Cos0001Db obj) throws Exception {
		if(obj == null){
			return null;
		}
		DefaultTableModel model = new javax.swing.table.DefaultTableModel();
		java.util.ArrayList<Object[]> arrList = new java.util.ArrayList<Object[]>();
		String[] cols = new String[]{ "otherExplain", "nonSeriousOther", "nonSeriousDis", "doc", "useStatus", "dtl", "isShow" };
		
		Object[] rowArray = new Object[cols.length];
		rowArray[0] = Common.get(obj.getOtherExplain());
		
		// 不良反應
		Cos0002Db cos0002Db = null;
		if(obj.getCos0002Dbs()!=null && obj.getCos0002Dbs().size()>0){
			for(Object dtl02Obj : obj.getCos0002Dbs()){
				Cos0002Db dtl02 = (Cos0002Db)dtl02Obj;
				cos0002Db = dtl02;
				
				if(cos0002Db != null){
					break;
				}
			}
		}
		
		if(cos0002Db != null){
			rowArray[1] = Common.get(cos0002Db.getNonSeriousOther());
			rowArray[2] = Common.get(cos0002Db.getNonSeriousDis());
			
			String doc = getCos0004Db(cos0002Db);
			if(doc.length() > 46){
				rowArray[3] = doc;
			}else{
				rowArray[3] = "";
			}
			if(cos0002Db.getCos0005Dbs()!=null && cos0002Db.getCos0005Dbs().size()>0){
				rowArray[5] = new JRTableModelDataSource(getCos05SubModel(cos0002Db.getCos0005Dbs()));
				rowArray[6] = "Y";
			}else{
				rowArray[5] = null;
				rowArray[6] = "N";
			}
		}else{
			rowArray[1] = "";
			rowArray[2] = "";
			rowArray[3] = "";
			rowArray[5] = null;
			rowArray[6] = "N";
		}
		rowArray[4] = getUseStatus(cos0002Db);
		arrList.add(rowArray);
		
		if(arrList!=null && arrList.size()>0){
			Object[][] rs = new Object[0][0];
			rs = (Object[][])arrList.toArray(rs);
			model.setDataVector(rs, cols);
		}
		return model;
	}
	
	public String getCos0004Db(Cos0002Db cos0002Db){
		StringBuffer doc = new StringBuffer();
		if(cos0002Db != null){
			doc.append("醫師診斷證明 : ").append(Common.get(cos0002Db.getDiagnosisProof()).equals("")?"":Common.get(cos0002Db.getDiagnosisProof()));
			doc.append("\n");
			doc.append("就醫記錄(病歷報告) : ").append(Common.get(cos0002Db.getDiagnosisReport()).equals("")?"":Common.get(cos0002Db.getDiagnosisReport()));
			doc.append("\n");
			doc.append("其他相關資料 : ").append(Common.get(cos0002Db.getDiagnosisOther()).equals("")?"":Common.get(cos0002Db.getDiagnosisOther()));
			doc.append("\n");
			doc.append("相關檢查及檢驗數據 : ");
			if(cos0002Db.getCos0004Dbs()!=null && cos0002Db.getCos0004Dbs().size()>0){
				for(Object dtl04Obj : cos0002Db.getCos0004Dbs()){
					Cos0004Db cos0004Db = (Cos0004Db)dtl04Obj;
					if("".equals(Common.get(cos0004Db.getTestDate())) && "".equals(Common.get(cos0004Db.getTestItems())) && "".equals(Common.get(cos0004Db.getTestNum()))){
						continue;
					}
					doc.append("\n");
					doc.append(Common.get(cos0004Db.getTestDate()))
					   .append(Common.get(cos0004Db.getTestItems()).equals("")?"":("、" + Common.get(cos0004Db.getTestItems())))
					   .append(Common.get(cos0004Db.getTestNum()).equals("")?"":("、" + Common.get(cos0004Db.getTestNum())));
				}
			}
			
		}
		
		return doc.toString();
	}
	
	public String getUseStatus(Cos0002Db cos0002Db){
		StringBuffer sb = new StringBuffer();
		if(cos0002Db != null){
			sb.append("起迄時間 : ")
			  .append(Common.get(cos0002Db.getUseDateS()).equals("")?"  年  月  日" : Common.formatYYYMMDD(cos0002Db.getUseDateS(), 2))
			  .append(" ~")
			  .append(Common.get(cos0002Db.getUseDateE()).equals("")?"  年  月  日" : Common.formatYYYMMDD(cos0002Db.getUseDateE(), 2));
			sb.append("\n");
			sb.append("使用方法 : ").append(Common.get(cos0002Db.getUseMethod()).equals("")?"":("\n" + Common.get(cos0002Db.getUseMethod())));
			sb.append("\n");
			sb.append("使用頻率 : ").append(Common.get(cos0002Db.getUseRate()).equals("")?"":("\n" + Common.get(cos0002Db.getUseRate())));
		}else{
			sb.append("起迄時間 : ")
			  .append("  年  月  日")
			  .append(" ~")
			  .append("  年  月  日");
			sb.append("\n");
			sb.append("使用方法 : ");
			sb.append("\n");
			sb.append("使用頻率 : ");
		}
		return sb.toString();
	}
	
	public String getPresentUseStatus(Cos0002Db cos0002Db){
		StringBuffer sb = new StringBuffer();
		if(cos0002Db != null){
			sb.append("起迄時間 : ")
			  .append(Common.get(cos0002Db.getUseDateS()).equals("")?"  年  月  日" : Common.formatYYYMMDD(cos0002Db.getUseDateS(), 2))
			  .append(" ~")
			  .append(Common.get(cos0002Db.getUseDateE()).equals("")?"  年  月  日" : Common.formatYYYMMDD(cos0002Db.getUseDateE(), 2));
			sb.append("\n");
			
			String method = Common.get(cos0002Db.getUseMethod()).length()>5?Common.get(cos0002Db.getUseMethod()).substring(0,5):Common.get(cos0002Db.getUseMethod());
			sb.append("使用方法 : ").append(method.equals("")?"":(method + "(完整內容詳見第二頁)"));
			sb.append("\n");
			
			String rate = Common.get(cos0002Db.getUseRate()).length()>5?Common.get(cos0002Db.getUseRate()).substring(0,5):Common.get(cos0002Db.getUseRate());
			sb.append("使用頻率 : ").append(rate.equals("")?"":( rate + "(完整內容詳見第二頁)"));
		}
		return sb.toString();
	}
	
	public String getFieldCommonCodeKindHQL(String codeKindId) {
		return "select codeId, codeName from CommonCode where commonCodeKind.codeKindId = " + Common.sqlChar(codeKindId);
	}

	// 通報來源
	public String getNotifierSource(String NotifierSource, String NotifierSourceOther) {	
		String hql = getFieldCommonCodeKindHQL("CIS");
		StringBuffer sb = new StringBuffer();
		
		java.util.List list = ServiceGetter.getInstance().getTcbwService().load(hql);
		if(list!=null && list.size()>0){
			for(int i=0; i<list.size(); i++){
				Object[] dtl = (Object[])list.get(i);
				
				String checkbox = "□";
				if(Common.get(dtl[0]).equals(Common.get(NotifierSource))){
					checkbox = "■" + Common.get(dtl[1]);
					if("07".equals(Common.get(NotifierSource))){
						checkbox += "，" + NotifierSourceOther;
					}
				}else if("07".equals(Common.get(dtl[0]))){
					checkbox += Common.get(dtl[1]) + "，" + "_______";
				}else{
					checkbox += Common.get(dtl[1]);
				}
				
				if(i!=0 && i%3==0){
					sb.append(checkbox).append("\n");
				}else{
					sb.append(checkbox).append("　");
				}
			}
			list.clear();
		}
		list = null;
		
		return sb.toString();
	}
	
	// 許可證字
	public String getPermitKey(String PermitKey) {
		String hql = getFieldCommonCodeKindHQL("CPT");
		StringBuffer sb = new StringBuffer();
		
		java.util.List list = ServiceGetter.getInstance().getTcbwService().load(hql);
		if(list!=null && list.size()>0){
			for(int i=0; i<list.size(); i++){
				Object[] dtl = (Object[])list.get(i);
				String checkbox = "";				
				if(Common.get(dtl[0]).equals(PermitKey)){
					checkbox = Common.get(dtl[1]);
				}
				sb.append(checkbox);
				if(!"".equals(checkbox)){
					break;
				}
			}
			list.clear();
		}
		list = null;
		
		return sb.toString();
	}
	
	// 販賣通路
	public String getTraffickWay(String TraffickWay, String TraffickWayOther){
		String hql = getFieldCommonCodeKindHQL("CSP");
		StringBuffer sb = new StringBuffer();
		
		java.util.List list = ServiceGetter.getInstance().getTcbwService().load(hql);
		if(list!=null && list.size()>0){
			for(int i=0; i<list.size(); i++){
				Object[] dtl = (Object[])list.get(i);
				
				String checkbox = "□";
				if(Common.get(dtl[0]).equals(Common.get(TraffickWay))){
					checkbox = "■" + Common.get(dtl[1]);
					if("10".equals(Common.get(TraffickWay))) {
						checkbox += "，" + Common.get(TraffickWayOther);
					}
				}else if("10".equals(dtl[0])) {
					checkbox = checkbox + Common.get(dtl[1]) + "，" + "_______";
				}else{
					checkbox = checkbox + Common.get(dtl[1]);
				}
				if((i+1)%2==0){
					sb.append(checkbox).append("\n");
				}else{
					sb.append(checkbox).append("　");
				}
			}
			list.clear();
		}
		list = null;
		
		return sb.toString();
	}
	
	// 不良品缺陷之描述
	public static String getCheckBoxOption2(String sql, String[] selectedCheckBox1, String[] selectedCheckBox2, String id){
		StringBuilder sb = new StringBuilder();
	    java.util.List list1 = ServiceGetter.getInstance().getTcbwService().load(sql);
	    if(list1!=null && list1.size()>0){
	        for(int i=0; i<list1.size(); i++){
	        	Object[] o = (Object[]) list1.get(i);
	        	if(Common.get(o[0]).length() < 2){
        			continue;
        		}
	        	
	        	// 為了報表漂亮
	        	if(i == 0){
	        		sb.append("(").append(i+1).append(")").append(Common.get(o[1])).append(" : ");
	        	}else if(i==1 || i==2 || i==3){
	        		sb.append("(").append(i+1).append(")").append(Common.get(o[1])).append(" : ");
	        	}else if(i==4 || i==5){
	        		sb.append("(").append(i+1).append(")").append(Common.get(o[1])).append("   ");
	        	}
	        	
        		String showType = "1"; 						// 描述說明顯示方式

        		// 第2層 
        		java.util.List list2 = ServiceGetter.getInstance().getTcbwService().load(" select dpdKind, dpdKindName from Cos1001Db where substring(dpdKind,1,2) = " + Common.sqlChar(Common.get(o[0]).substring(0,2)) +   
        																				 " and isStop = 'N' order by dpdKind");
        		if(list2!=null && list2.size()>0){
        			for(int k=0; k<list2.size(); k++){
        				boolean isSelect = false;
        				Object[] o2 = (Object[]) list2.get(k);
                		if(selectedCheckBox2!=null && selectedCheckBox2.length>0){
                			for(int l=0; l<selectedCheckBox2.length; l++){
                				if(Common.get(o2[0]).equals(selectedCheckBox2[l])) {
                					sb.append("■");
                					isSelect = true;
                				}
                			}
                		}
                		if(!isSelect){
                			sb.append("□");
                		}
                		
                		// 為了報表漂亮
                		if(i==2 && k==3){
                			sb.append(o2[1]).append("\n").append("              ");
                		}else{
                			sb.append(o2[1]).append(" ");
                		}
                		
        			}
        			list2.clear();
        		}else{
        			showType = "2";
        		}
        		list2 = null;
        		
        		// 放置說明的value
        		String otherDescribeValue = View.getLookupField(" select otherDescribe from Cos4003Db where cos4001Db.id = " + Common.getLong(id) + 
        														" and mainCode = " + Common.sqlChar(Common.get(o[0])));	
        		if("2".equals(showType)){
        			if(!"".equals(otherDescribeValue)) {
	        		    sb.append("請描述：" ).append(otherDescribeValue).append("\n");
        			}else{
        				sb.append("請描述：" ).append("_____").append("\n");
        			}
        		}else{
        			if(!"".equals(otherDescribeValue)) {
	        			sb.append(otherDescribeValue).append( "\n");
        			}else{
	        			sb.append("_____").append( "\n");
        			}
        		}
        	}    		
	        list1.clear();
    	}
	    list1 = null;
	    
        return sb.toString();
	}
	
	// 不良反應結果
	public String getAdverseCondition(String AdverseCondition) {
		String hql = getFieldCommonCodeKindHQL("CAC");
		StringBuilder sb = new StringBuilder();
		
		java.util.List list = ServiceGetter.getInstance().getTcbwService().load(hql);
		if(list!=null && list.size()>0){
			for(int i=0; i<list.size(); i++){
				Object[] obj = (Object[]) list.get(i);
				String checkbox = "□";
				if(Common.get(obj[0]).equals(Common.get(AdverseCondition))){
					checkbox = "(" + (i+1) + ") " + "■" + Common.get(obj[1]);
				}else{
					checkbox = "(" + (i+1) + ") " + checkbox + Common.get(obj[1]);
				}
				if(sb.toString().length() > 0){
					sb.append("\n");
				}
				sb.append(checkbox);
			}
		}
		
		return sb.toString();
	}
	
	// 併用其它化粧品
	public DefaultTableModel getCos05SubModel(java.util.Set cos0005Dbs) throws Exception {
		String[] col = new String[]{ "cName", "manufactorName", "useDate",
									 "useRate", "useMethod", "manufactorNo", "expirationDate", "tradeDate"};
		DefaultTableModel model = new javax.swing.table.DefaultTableModel();
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		
		if(cos0005Dbs!=null && cos0005Dbs.size()>0){
			for(Object dtlObj : cos0005Dbs){
				Cos0005Db cos0005Db = (Cos0005Db)dtlObj;
				
				String[] rowArray = new String[col.length];
				rowArray[0] = Common.get(cos0005Db.getcName());
				rowArray[1] = Common.get(cos0005Db.getManufactorName());
				if(!"".equals(Common.get(cos0005Db.getUseDateS())) && !"".equals(Common.get(cos0005Db.getUseDateE()))){
					rowArray[2] = Common.formatYYYMMDD(cos0005Db.getUseDateS(), 2) + "\n" + Common.formatYYYMMDD(cos0005Db.getUseDateE(), 2);
				}else{
					rowArray[2] = Common.formatYYYMMDD(cos0005Db.getUseDateS(), 2) + Common.formatYYYMMDD(cos0005Db.getUseDateE(), 2);
				}
				rowArray[3] = Common.get(cos0005Db.getUseRate());
				rowArray[4] = Common.get(cos0005Db.getUseMethod());
				rowArray[5] = Common.get(cos0005Db.getManufactorNo());
				rowArray[6] = Common.formatYYYMMDD(cos0005Db.getExpirationDate(),2);
				rowArray[7] = Common.formatYYYMMDD(cos0005Db.getTradeDate(),2);
				arrList.add(rowArray);
			}
		}
		
		if(null != arrList && arrList.size() >0){
			String[][] rs = new String[0][0];
			rs = (String[][])arrList.toArray(rs);
			model.setDataVector(rs, col);
		}
		return model;
	}
	
	public String getYNU(String YNU){
		StringBuffer sb = new StringBuffer();
		if("Y".equals(Common.get(YNU))){
			sb.append("■ 是    □ 否    □ 無法得知");
		}else if("N".equals(Common.get(YNU))){
			sb.append("□ 是    ■ 否    □ 無法得知");
		}else if("U".equals(Common.get(YNU))){
			sb.append("□ 是    □ 否    ■ 無法得知");
		}else{
			sb.append("□ 是    □ 否    □ 無法得知");
		}
		return sb.toString();
	}

	@Override
	public Object doQueryOne() throws Exception {
		COSIN0102Q obj = this;
		Cos0001Db c = (Cos0001Db)View.getObject(" from Cos0001Db where id = " + Common.getLong(getId()));		
		if(c != null){
			obj.setCos40001Id(c.getCos4001DbId().toString());
			setBaseData(obj, c);	
			setAssessPageData(obj, c);
			setDisPageData(obj, c);					
			setProcessPage(obj, c, false);
			setEndCasePage(obj, c);
		}
		return obj;
	}
	
	public Object doQueryHistory() throws Exception {		
		String hql = getHql();		
		System.out.println("[TCBW]-[COSIN0102Q]-[化妝品-doQueryHistory] : " + hql);		
		
		java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
		java.util.List objList = ServiceGetter.getInstance().getTcbwService().load(hql);
		this.processCurrentPageAttribute(objList!=null?objList.size():0);
		if (getTotalRecord() > 0) {
			if (getState().indexOf("query")<0) 
				ServiceGetter.getInstance().getCommonService().getHibernateTemplate().clear();
			
			objList = ServiceGetter.getInstance().getTcbwService().load(hql + " order by id", this.getRecordStart(), this.getPageSize());
			if (objList != null && objList.size() > 0) {
				java.util.Map<String, String> cosMap = TCBWCommon.getCommonCodeMap("CCT");
				java.util.Map<String, String> cacMap = TCBWCommon.getCommonCodeMap("CAC");
				java.util.Map<String, String> mainCodeMap = TCBWCommon.getCommonCodeMap("COSDPD");
				java.util.Map<String, String> subCodeMap = getCos101DbCode();
				java.util.Map<String, String> notifierTypeMap = TCBWCommon.getCommonCodeMap("CONNFT1");
				for(Object dtlObj : objList) {				
					Cos0001Db dtl = (Cos0001Db)dtlObj;
					String[] rowArray = new String[11];
					rowArray[0] = Common.get(dtl.getId());
					rowArray[1] = Common.get(dtl.getNotifierRevDate());
					rowArray[2] = Common.get(dtl.getApplNo());
					rowArray[3] = Common.get(cosMap.get(Common.get(dtl.getCosType()))).equals("")?Common.get("不良品與不良反應"):cosMap.get(Common.get(dtl.getCosType()));
					rowArray[4] = Common.get(dtl.getChProduct());
					rowArray[5] = Common.get(dtl.getEnProduct());
					rowArray[6] = Common.get(dtl.getManufactorName());
					rowArray[7] = "";
					if(dtl.getCos0002Dbs() != null && dtl.getCos0002Dbs().size() > 0){
						for(Object o:dtl.getCos0002Dbs()){
							Cos0002Db a = (Cos0002Db)o;
							rowArray[7] = Common.get(cacMap.get(a.getAdverseCondition()));
						}
					}
					rowArray[8] = "";
					if(dtl.getCos0003Dbs() != null && dtl.getCos0003Dbs().size() > 0){
						String tempCode = "";
						for(Object o:dtl.getCos0003Dbs()){
							Cos0003Db a = (Cos0003Db)o;
							String[] tempSubCode = a.getSubCode().split(",");
							tempCode += Common.get(mainCodeMap.get(a.getMainCode())) + ":";
							for(String s:tempSubCode){
								tempCode += Common.get(subCodeMap.get(s)) + ",";
							}
							tempCode = tempCode.substring(0, tempCode.length()-1) + "、";
						}
						rowArray[8] = tempCode.substring(0, tempCode.length()-1);
					}
					rowArray[9] = Common.get(notifierTypeMap.get(dtl.getNotifierType()));
					rowArray[10] = "附件";
					arrList.add(rowArray);
				}
				objList.clear();			
			}	
		}
		return arrList;
	}
	
	public java.util.Map<String,String> getCos101DbCode() throws Exception{
		java.util.Map<String, String> codeMap = new java.util.HashMap<String, String>();
		java.util.List<Cos1001Db> cosList = ServiceGetter.getInstance().getTcbwService().load(" from Cos1001Db where isStop = 'N' order by dpdKind ");
		if(cosList!=null && cosList.size()>0){
			for(Cos1001Db cos : cosList){
				codeMap.put(cos.getDpdKind(), cos.getDpdKindName());
			}
			cosList.clear();
		}
		
		return codeMap;
	}
	
	public String getCOS0003DbCheckBoxOption(String className, String checkBoxFieldName, String[] checkedOne) throws Exception{
		StringBuilder sb = new StringBuilder();		
		java.util.List<CommonCode> commonCodeList = ServiceGetter.getInstance().getCommonService().getCommonCodeDao().getCommonCodeList("COSDPD");
		if(commonCodeList!=null && commonCodeList.size()>0){
			java.util.Map<String, String> selectOneMap = new java.util.HashMap<String, String>();
			if(checkedOne!=null && checkedOne.length>0){
				for(String rid : checkedOne){
					selectOneMap.put(Common.get(rid), "Y");
				}
			}
			for(CommonCode dtl : commonCodeList){
				sb.append(Common.get(dtl.getCodeName())).append(" : ");
				java.util.List<Cos1001Db> cosList = ServiceGetter.getInstance().getTcbwService().load(" from Cos1001Db where cdpCode = " + Common.sqlChar(dtl.getCodeId()) + " and isStop = 'N' order by dpdKind ");
				if(cosList!=null && cosList.size()>0){
					for(Cos1001Db cos : cosList){
						sb.append("<input class='" + className + "'").append(" type='checkbox' name='" + checkBoxFieldName + "' value='" + cos.getDpdKind() + "' ");
						sb.append( Common.get(selectOneMap.get(Common.get(cos.getDpdKind()))).equals("")?"":"checked" );
						sb.append(">");
						sb.append(Common.get(cos.getDpdKindName()));
					}
					cosList.clear();
				}
				sb.append("<br>");
			}
			selectOneMap.clear();
		}
		return sb.toString();
	}
	
	public DefaultTableModel getHistoryDefaultTableModel() throws Exception {
		DefaultTableModel model = new javax.swing.table.DefaultTableModel();
    	String[] cols = new String[]{"NO", "NotifierRepDate", "ApplNo", "ChProduct", "EnProduct", "cosType",
    								 "ManufactorName", "adverseCondition", "cos0003", "NotifierType"};
    	java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
    	
		String hql = getHql();
		java.util.List objList = new java.util.ArrayList<String[]>();
	    objList = ServiceGetter.getInstance().getTcbwService().load(hql + " order by id");
    	if(objList!=null && objList.size()>0){
    		int i = 1;
			java.util.Map<String, String> cosMap = TCBWCommon.getCommonCodeMap("CCT");
			java.util.Map<String, String> cacMap = TCBWCommon.getCommonCodeMap("CAC");
			java.util.Map<String, String> mainCodeMap = TCBWCommon.getCommonCodeMap("COSDPD");
			java.util.Map<String, String> subCodeMap = getCos101DbCode();
			java.util.Map<String, String> notifierTypeMap = TCBWCommon.getCommonCodeMap("CONNFT1");
			for(Object dtlObj : objList) {				
				Cos0001Db dtl = (Cos0001Db)dtlObj;
				String[] rowArray = new String[cols.length];
				rowArray[0] = String.valueOf(i++);
				rowArray[1] = Common.get(dtl.getNotifierRevDate());
				rowArray[2] = Common.get(dtl.getApplNo());
				rowArray[3] = Common.get(cosMap.get(Common.get(dtl.getCosType()))).equals("")?Common.get("不良品與不良反應"):cosMap.get(Common.get(dtl.getCosType()));
				rowArray[4] = Common.get(dtl.getChProduct());
				rowArray[5] = Common.get(dtl.getEnProduct());
				rowArray[6] = Common.get(dtl.getManufactorName());
				rowArray[7] = "";
				if(dtl.getCos0002Dbs() != null && dtl.getCos0002Dbs().size() > 0){
					for(Object o:dtl.getCos0002Dbs()){
						Cos0002Db a = (Cos0002Db)o;
						rowArray[7] = Common.get(cacMap.get(a.getAdverseCondition()));
					}
				}
				rowArray[8] = "";
				if(dtl.getCos0003Dbs() != null && dtl.getCos0003Dbs().size() > 0){
					String tempCode = "";
					for(Object o:dtl.getCos0003Dbs()){
						Cos0003Db a = (Cos0003Db)o;
						String[] tempSubCode = a.getSubCode().split(",");
						tempCode += Common.get(mainCodeMap.get(a.getMainCode())) + ":";
						for(String s:tempSubCode){
							tempCode += Common.get(subCodeMap.get(s)) + ",";
						}
						tempCode = tempCode.substring(0, tempCode.length()-1) + "、";
					}
					rowArray[8] = tempCode.substring(0, tempCode.length()-1);
				}
				rowArray[9] = Common.get(notifierTypeMap.get(dtl.getNotifierType()));
				arrList.add(rowArray);
			}
    	}
    	
		if(arrList!=null && arrList.size()>0){
			String[][] rs = new String[0][0];
			rs = (String[][])arrList.toArray(rs);
			model.setDataVector(rs, cols);
		}	
		return model;   
	}
	
	
	public String getHql(){
		String hql = " from Cos0001Db where notifierRevDate <= " + Common.sqlChar(getNotifierRepDate()) +
					 " and applNo is not null and (permitKey||permitNo = " + Common.sqlChar(getPermitKey() + getPermitNo()) +
					 " or caseNo = " + Common.sqlChar(getCaseNo()) + ")";

		if(!"".equals(getQ_notifierRevDateSHis()))
			hql += " and notifierRevDate >= " + Common.sqlChar(getQ_notifierRevDateSHis());
		if(!"".equals(getQ_notifierRevDateEHis()))
			hql += " and notifierRevDate <= " + Common.sqlChar(getQ_notifierRevDateEHis());	

		if(!"".equals(Common.get(getQ_cosTypeHis()))){
			if(getQ_cosTypeHis().length == 1){
				for(String a : getQ_cosTypeHis()){
					hql += " and cosType = " + Common.sqlChar(a);
				}
			}else{
				hql += " and cosType in ('1','2','3')";
			}
		}

		if(!"".equals(getQ_adverseConditionHis()))
			hql += " and id in (select cos0001Db.id from Cos0002Db where adverseCondition = " + Common.sqlChar(getQ_adverseConditionHis()) + ")";	
	
		if(!"".equals(Common.get(getQ_mainCodeHis()))){
			String type = "";
			for(String a: getQ_mainCodeHis()){
				type += "subCode like " + Common.sqlChar("%" + a + "%") + " or ";
			}
			if(!"".equals(type))
				hql += " and id in (select cos0001Db.id from Cos0003Db where 1 = 1 and " + type.substring(0, type.length()-3) + ")";
		}

		if(!"".equals(getQ_notifierDeptHis())) 
			hql += " and notifierDept like " + Common.sqlChar("%" + getQ_notifierDeptHis() + "%");
	
		return hql;
	}

}
